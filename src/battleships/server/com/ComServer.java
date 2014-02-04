package battleships.server.com;

import battleships.abiklassen.enhanced.EnhancedServer;
import battleships.server.IGameEngine;

public class ComServer extends EnhancedServer {
	
	private IGameEngine engine;
	
	public ComServer(int pPortNr,IGameEngine engine) {
		super(pPortNr);
		this.engine=engine;
		
	}

}
