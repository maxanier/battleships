package battleships.client.com;

import battleships.abiklassen.enhanced.EnhancedClient;
import battleships.client.IClientEngine;
import battleships.util.PROTOKOLL;
import battleships.util.Player;

public class ComClient extends EnhancedClient implements GameListener{
	private IClientEngine engine;
	private boolean waitForNameAccepted=false;
	private boolean waitForMapAccepted=false;
	private boolean waitForShotAccepted=false;
	
	
	public ComClient(String pIPAdresse, int pPortNr, IClientEngine engine) {
		super(pIPAdresse, pPortNr);
		this.engine=engine;
		
	}

	@Override
	public void processEnhancedMessage(String message) {
		if(message.startsWith(PROTOKOLL.SC_HELLO)){
			engine.notifyConnected();
		}
		else if(message.startsWith(PROTOKOLL.SC_NAME_ACCEPTED)){
			waitForNameAccepted=false;
		}
		else if(message.startsWith(PROTOKOLL.SC_FULL)){
			engine.notifyError("Server is Full");
		}
		else if(message.startsWith(PROTOKOLL.SC_PLAYING)){
			engine.notifyError("Servergame is running");
		}
		else if(message.startsWith(PROTOKOLL.SC_CREATE_MAP)){
			engine.notifyCreateMap();
		}
		else if(message.startsWith(PROTOKOLL.SC_MAP_ACCEPTED)){
			engine.mapSet(true);
			waitForMapAccepted=false;
		}
		else if(message.startsWith(PROTOKOLL.SC_MAP_INVALID)){
			engine.notifyError("Map invalid");
			engine.mapSet(false);
			waitForMapAccepted=false;
		}
		
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
