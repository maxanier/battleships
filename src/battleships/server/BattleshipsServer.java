package battleships.server;

import battleships.server.com.ComServer;
import battleships.util.CONSTANTS;

public class BattleshipsServer {
	private IGameEngine engine;
	private ComServer comServer;
	private int maxPlayer = 2;

	public BattleshipsServer() {
		engine = new BattleshipsGameEngine();
		comServer = new ComServer(CONSTANTS.PORT, engine, maxPlayer);

	}
}
