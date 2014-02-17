package battleships.client.com;

import battleships.abiklassen.enhanced.EnhancedClient;
import battleships.server.IGameEngine;
import battleships.util.PROTOKOLL;
import battleships.util.Player;

public class ComClient extends EnhancedClient implements GameListener{
	private IGameEngine engine;
	private boolean waitForNameAccepted=false;
	private boolean waitForMapAccepted=false;
	private boolean waitForShotAccepted=false;
	
	public ComClient(String pIPAdresse, int pPortNr, IGameEngine engine) {
		super(pIPAdresse, pPortNr);
		this.engine=engine;
	}

	@Override
	public void processEnhancedMessage(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNickname(String n) {
		this.send(PROTOKOLL.CS_REGISTER+" "+n);
		waitForNameAccepted=true;
	}

	@Override
	public void sendMap(String map) {
		this.send(PROTOKOLL.CS_MAP+" "+map);
		waitForNameAccepted=true;
	}

	@Override
	public void shoot(Player victim, int x, int y) {
		this.send(PROTOKOLL.CS_SHOOT+" "+victim.getId()+" "+x+" "+y);
		waitForShotAccepted=true;
	}

}
