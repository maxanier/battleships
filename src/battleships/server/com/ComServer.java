package battleships.server.com;

import battleships.abiklassen.enhanced.EnhancedServer;
import battleships.server.IGameEngine;
import battleships.server.ServerMap;
import battleships.server.ServerMap.MapInvalidException;
import battleships.util.Logger;
import battleships.util.PROTOKOLL;
import battleships.util.Player;

/**
 * Manages communication and game-stages
 * 
 * @author Max Becker
 * 
 */
public class ComServer extends EnhancedServer implements GameListener {

	private IGameEngine engine;
	private PlayerList players;
	private int mode;
	private final int WAITING_MODE = 0;
	private final int MAP_CREATION_MODE = 1;
	private final int PLAYING_MODE = 2;
	private final String TAG = "ComServer";

	public ComServer(int pPortNr, IGameEngine engine) {
		super(pPortNr);
		mode = WAITING_MODE;
		this.engine = engine;
		players = new PlayerList();

	}

	@Override
	public void notifyEnd(Player winner) {
		this.sendToAll(PROTOKOLL.SC_END + " " + winner.getId());

	}

	@Override
	public void notifyShotResult(Player victim, int x, int y, int newId,
			boolean sunk) {
		int sunkint = 0;
		if (sunk) {
			sunkint = 1;
		}

		this.sendToAll(PROTOKOLL.SC_SHOT_RESULT + " " + victim.getId() + " "
				+ x + " " + y + " " + newId + " " + sunkint);

	}

	@Override
	public void notifyTurn(Player player) {
		Client c = players.getPlayerClient(player);
		this.send(c, PROTOKOLL.SC_NOTIFY);
		Logger.i(TAG, "Next Player: " + player);

	}

	@Override
	public void processClosedEnhancedConnection(String ip, int port) {
		engine.playerLeft(players.getPlayer(ip, port));
		players.removePlayer(ip, port);

	}

	@Override
	public void processEnhancedMessage(String ip, int port, String pMessage) {
		if (pMessage.startsWith(PROTOKOLL.CS_REGISTER)) {
			if (mode == PLAYING_MODE) {
				Logger.w(TAG, "Already playing, so no new name can be set");
				this.send(ip, port, PROTOKOLL.SC_PLAYING);
				return;
			}

			try {
				String nickname = pMessage.substring(
						PROTOKOLL.CS_REGISTER.length()).trim();
				players.getPlayer(ip, port).setNickname(nickname);
				Logger.i(TAG, "Player set name to " + nickname);
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

					String s = pMessage.substring(PROTOKOLL.CS_MAP.length())
							.trim();
					ServerMap map = ServerMap.createFromString(s,
							engine.getMapSizeX(), engine.getMapSizeY());
					players.getPlayer(ip, port).setMap(map);
					Logger.i(TAG, "Player set map to " + s);
					this.send(ip, port, PROTOKOLL.SC_MAP_ACCEPTED);

					if (players.allMapsSet()) {
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
		if (mode == PLAYING_MODE) {
			if (pMessage.startsWith(PROTOKOLL.CS_SHOOT)) {
				try {

					String s = pMessage.substring(PROTOKOLL.CS_SHOOT.length())
							.trim();
					String param[] = s.split(" ");
					if (param.length != 3) {
						throw new StringIndexOutOfBoundsException(
								"Cannot find the three params");
					}
					Player victim = players.getPlayer(Integer
							.parseInt(param[0]));
					if (victim == null) {
						this.send(ip, port, PROTOKOLL.SC_PLAYER_NOT_FOUND);
						return;
					}

					Logger.i(
							TAG,
							"Shoot command found. Victim: "
									+ victim.getNickname() + " X: "
									+ Integer.parseInt(param[1]) + " Y: "
									+ Integer.parseInt(param[2]));
					if (!engine.shoot(players.getPlayer(ip, port), victim,
							Integer.parseInt(param[1]),
							Integer.parseInt(param[2]))) {
						this.send(ip, port, PROTOKOLL.SC_NOT_ALLOWED);
						return;
					}

				} catch (StringIndexOutOfBoundsException e) {

					Logger.e(TAG, "Missing Parameter", e);
					this.send(ip, port, PROTOKOLL.MISSING_PARAMETER);
				} catch (NumberFormatException e) {
					Logger.e(TAG, "Parameter are no Integers", e);
					this.send(ip, port, PROTOKOLL.MISSING_PARAMETER);
				}
				return;
			}
		}
		this.send(ip, port, PROTOKOLL.UNKNOWN);

	}

	@Override
	public void processNewEnhancedConnection(String ip, int port) {
		if (mode != WAITING_MODE) {
			Logger.w(TAG, "Cannot accept new players");
			this.send(ip, port, PROTOKOLL.SC_PLAYING);
			this.closeConnection(ip, port);
			return;
		}
		if (players.getSize() < engine.getMaxPlayer()) {
			int id = ip.hashCode() / 100 + port;
			players.addPlayer(ip, port, new Player("", id));
			this.send(ip, port, PROTOKOLL.SC_HELLO+" "+id);
			Logger.i(TAG, "Added new player (" + ip + ":" + port + ") ID: "
					+ id);
			if (players.getSize() == engine.getMaxPlayer()) {
				startMapCreationStage();
			}

		} else {
			this.send(ip, port, PROTOKOLL.SC_FULL);
			this.closeConnection(ip, port);
		}
	}

	private boolean startGame() {
		engine.setGameListener(this);
		this.sendToAll(PROTOKOLL.SC_START + " " + players.getPlayerListString());
		if (engine.start(players.getPlayers())) {

			mode = PLAYING_MODE;
			Logger.i(TAG, "Started game");
			return true;
		}
		Logger.w(TAG, "Failed to start game");
		return false;

	}

	private void startMapCreationStage() {
		Logger.i(TAG, "Started map creation phase");
		mode = MAP_CREATION_MODE;
		this.sendToAll(PROTOKOLL.SC_CREATE_MAP);
	}

}
