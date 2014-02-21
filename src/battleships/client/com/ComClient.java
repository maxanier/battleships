package battleships.client.com;

import java.util.ArrayList;

import battleships.abiklassen.enhanced.EnhancedClient;
import battleships.client.IClientEngine;
import battleships.util.Logger;
import battleships.util.PROTOKOLL;
import battleships.util.Player;

public class ComClient extends EnhancedClient implements GameListener{
	private IClientEngine engine;
	private boolean waitForNameAccepted=false;
	private boolean waitForMapAccepted=false;
	private boolean waitForShotAccepted=false;
	private final String TAG="ComClient";
	
	
	public ComClient(String pIPAdresse, int pPortNr, IClientEngine engine) {
		super(pIPAdresse, pPortNr);
		this.engine=engine;
		engine.setGameListener(this);
		
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
		else if(message.startsWith(PROTOKOLL.SC_NOT_ALLOWED)){
			waitForShotAccepted=false;
			engine.notifyError("It´s not your turn");
		}
		else if(message.startsWith(PROTOKOLL.SC_NOTIFY)){
			engine.notifyYourTurn();
		}
		else if(message.startsWith(PROTOKOLL.SC_PLAYER_NOT_FOUND)){
			waitForShotAccepted=false;
			engine.notifyError("Player not found");
		}
		else if(message.startsWith(PROTOKOLL.SC_END)){
			try {
				int winner=Integer.parseInt(message.substring(PROTOKOLL.SC_END.length()+1));
				engine.notifyEnd(winner);
			} catch (NumberFormatException e) {
				
				e.printStackTrace();
				this.send(PROTOKOLL.MISSING_PARAMETER);
			}
		}
		else if(message.startsWith(PROTOKOLL.SC_SHOT_RESULT)){
			try {
				String[] params=message.split(" ");
				int playerId=Integer.parseInt(params[1]);
				int x=Integer.parseInt(params[2]);
				int y=Integer.parseInt(params[3]);
				int newId=Integer.parseInt(params[4]);
				int sunk=Integer.parseInt(params[5]);
				engine.shotResult(playerId, x, y, newId, sunk==1);
			} catch (NumberFormatException e) {
				
				e.printStackTrace();
				this.send(PROTOKOLL.MISSING_PARAMETER);
			}
		}
		else if(message.startsWith(PROTOKOLL.SC_START)){
			String playerlist=message.substring(PROTOKOLL.SC_START.length()+1);
			ArrayList<Player> players=Player.getPlayerListFromString(playerlist);
			if(players.size()<2){
				Logger.w(TAG, "Playerlist from Server contains less than two players");
				this.send(PROTOKOLL.MISSING_PARAMETER);
				engine.notifyError("Playerlist from Server contains less than two players");
			}
			else{
				engine.startGame(players);
			}
		}
		else if(message.startsWith(PROTOKOLL.MISSING_PARAMETER)){
			if(waitForShotAccepted){
				engine.notifyError("Missing Parameter in shoot command");
				Logger.w(TAG, "Missing Parameter in shoot command");
			}
			else if(waitForMapAccepted){
				engine.notifyError("Missing Parameter in setMap command or map invalid");
				Logger.w(TAG, "Missing Parameter in setMap command or map invalid");
			}
			else if(waitForNameAccepted){
				engine.notifyError("Missing Parameter in setName command");
				Logger.w(TAG, "Missing Parameter in setName command");
			}
			
		}
		else if(message.startsWith(PROTOKOLL.UNKNOWN)){
			engine.notifyError("Unkown command");
			Logger.w(TAG, "Unknown command");
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
