package battleships.server.com;

import battleships.abiklassen.enhanced.EnhancedServer;
import battleships.server.IGameEngine;
import battleships.util.PROTOKOLL;
import battleships.util.Player;

/**
 * Manages communication and game-stages
 * @author Max
 *
 */
public class ComServer extends EnhancedServer {
	
	private IGameEngine engine;
	private PlayerList players;
	private int maxConnections;
	
	public ComServer(int pPortNr,IGameEngine engine,int maxConnections) {
		super(pPortNr);
		this.engine=engine;
		players=new PlayerList();
		this.maxConnections=maxConnections;
		
	}

	@Override
	public void processNewConnection(String ip, int port) {
		if(engine.isRunning()){
			this.send(ip, port, PROTOKOLL.SC_PLAYING);
			return;
		}
		if(players.getSize()<maxConnections){
			int id=ip.hashCode()/100+port;
			players.addPlayer(ip, port, new Player("",id));
			this.send(ip, port, PROTOKOLL.SC_HELLO);
			if(players.getSize()==maxConnections){
				startGame();
			}
		}
		else{
			this.send(ip, port, PROTOKOLL.SC_FULL);
		}
	}

	private void startGame() {
		engine.start();
		
	}

	@Override
	public void processMessage(String ip, int port, String pMessage) {
		if(pMessage.startsWith(PROTOKOLL.CS_REGISTER)){
			if(engine.isRunning()){
				this.send(ip, port, PROTOKOLL.SC_PLAYING);
				return;
			}
			
			try {
				String nickname = pMessage.substring(PROTOKOLL.CS_REGISTER.length()).trim();
				players.getPlayer(ip, port).setNickname(nickname);
				this.send(ip, port, PROTOKOLL.SC_ACCEPTED);
			} catch (StringIndexOutOfBoundsException e) {
				
				e.printStackTrace();
				this.send(ip, port, PROTOKOLL.MISSING_PARAMETER);
			}
		}
		
	}

	@Override
	public void processClosedConnection(String ip, int port) {
		engine.playerLeft(players.getPlayer(ip, port));
		players.removePlayer(ip, port);
		
	}

}
