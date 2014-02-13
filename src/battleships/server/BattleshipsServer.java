package battleships.server;

import battleships.server.com.ComServer;
import battleships.util.CONSTANTS;

public class BattleshipsServer {
	private IGameEngine engine;
	private ComServer comServer;
	private int maxPlayer = 2;
	private int mapSize=10;

	public BattleshipsServer() {
		engine = new BattleshipsGameEngine(maxPlayer,mapSize,mapSize);
		comServer = new ComServer(CONSTANTS.PORT, engine);

	}
}
