package battleships.server.com;

import battleships.abiklassen.enhanced.EnhancedServer;
import battleships.server.BattleshipsGameEngine.GameListener;
import battleships.server.IGameEngine;
import battleships.server.ServerMap;
import battleships.server.ServerMap.MapInvalidException;
import battleships.util.PROTOKOLL;
import battleships.util.Player;

/**
 * Manages communication and game-stages
 * 
 * @author Max Becker
 * 
 */
public class ComServer extends EnhancedServer implements GameListener{

	private IGameEngine engine;
	private PlayerList players;
	private int maxConnections;
	private int mode;
	private final int WAITING_MODE = 0;
	private final int MAP_CREATION_MODE = 1;
	private final int PLAYING_MODE = 2;

	public ComServer(int pPortNr, IGameEngine engine, int maxConnections) {
		super(pPortNr);
		mode = WAITING_MODE;
		this.engine = engine;
		players = new PlayerList();
		this.maxConnections = maxConnections;

	}

	@Override
	public void processNewEnhancedConnection(String ip, int port) {
		if (mode != WAITING_MODE) {
			this.send(ip, port, PROTOKOLL.SC_PLAYING);
			return;
		}
		if (players.getSize() < maxConnections) {
			int id = ip.hashCode() / 100 + port;
			players.addPlayer(ip, port, new Player("", id));
			this.send(ip, port, PROTOKOLL.SC_HELLO);
			if (players.getSize() == maxConnections) {
				startMapCreationStage();
			}
		} else {
			this.send(ip, port, PROTOKOLL.SC_FULL);
		}
	}

	private boolean startGame() {

		if (engine.start(players.getPlayers())) {
			this.sendToAll(PROTOKOLL.SC_START+" "+players.toString());
			mode = PLAYING_MODE;
			return true;
		}
		return false;

	}

	private void startMapCreationStage() {
		mode = MAP_CREATION_MODE;
		this.sendToAll(PROTOKOLL.SC_CREATE_MAP);
	}

	@Override
	public void processEnhancedMessage(String ip, int port, String pMessage) {
		if (pMessage.startsWith(PROTOKOLL.CS_REGISTER)) {
			if (mode == PLAYING_MODE) {
				this.send(ip, port, PROTOKOLL.SC_PLAYING);
				return;
			}

			try {
				String nickname = pMessage.substring(PROTOKOLL.CS_REGISTER.length()).trim();
				players.getPlayer(ip, port).setNickname(nickname);
				this.send(ip, port, PROTOKOLL.SC_NAME_ACCEPTED);
			} catch (StringIndexOutOfBoundsException e) {

				e.printStackTrace();
				this.send(ip, port, PROTOKOLL.MISSING_PARAMETER);
			}
			return;
		}

		if (mode == MAP_CREATION_MODE) {
			if (pMessage.startsWith(PROTOKOLL.CS_MAP)) {
				try {

					String s = pMessage.substring(PROTOKOLL.CS_MAP.length()).trim();
					ServerMap map = ServerMap.createFromString(s, engine.getMapSizeX(), engine.getMapSizeY());
					players.getPlayer(ip, port).setMap(map);
					this.send(ip, port, PROTOKOLL.SC_MAP_ACCEPTED);
					
					if(players.allMapsSet()){
						startGame();
					}

				} catch (StringIndexOutOfBoundsException e) {

					e.printStackTrace();
					this.send(ip, port, PROTOKOLL.MISSING_PARAMETER);
				} catch (MapInvalidException e) {
					e.printStackTrace();
					this.send(ip, port, PROTOKOLL.SC_MAP_INVALID);
				}
				return;
			}
		}
		if(mode==PLAYING_MODE){
			if(pMessage.startsWith(PROTOKOLL.CS_SHOOT)){
				try {

					String s = pMessage.substring(PROTOKOLL.CS_SHOOT.length()).trim();
					String param[] = s.split(" ");
					if(param.length!=3){
						throw new StringIndexOutOfBoundsException("Cannot find the three params");
					}
					Player victim = players.getPlayer(Integer.parseInt(param[0]));
					if(victim==null){
						this.send(ip, port, PROTOKOLL.SC_PLAYER_NOT_FOUND);
						return;
					}
					
					if(!engine.shoot(players.getPlayer(ip, port),victim, Integer.parseInt(param[1]), Integer.parseInt(param[2]))){
						this.send(ip, port, PROTOKOLL.SC_NOT_ALLOWED);
						return;
					}


				} catch (StringIndexOutOfBoundsException e) {

					e.printStackTrace();
					this.send(ip, port, PROTOKOLL.MISSING_PARAMETER);
				}
				catch(NumberFormatException e){
					e.printStackTrace();
					this.send(ip, port, PROTOKOLL.MISSING_PARAMETER);
				}
				return;
			}
		}
		this.send(ip, port, PROTOKOLL.UNKNOWN);

	}

	@Override
	public void processClosedEnhancedConnection(String ip, int port) {
		engine.playerLeft(players.getPlayer(ip, port));
		players.removePlayer(ip, port);

	}

	@Override
	public void notifyShotResult(Player victim, int x, int y, int newId,
			boolean sunk) {
		int sunkint=0;
		if(sunk){
			sunkint=1;
		}
		
		this.sendToAll(PROTOKOLL.SC_SHOT_RESULT+" "+victim.getId()+" "+x+" "+y+" "+newId+" "+sunkint);
		
	}

	@Override
	public void notifyTurn(Player player) {
		Client c=players.getPlayerClient(player);
		this.send(c, PROTOKOLL.SC_NOTIFY);
		
	}

	@Override
	public void notifyEnd(Player winner) {
		this.sendToAll(PROTOKOLL.SC_END+" "+winner.getId());
		
	}

}
