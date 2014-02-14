package battleships.client.com;

import battleships.abiklassen.enhanced.EnhancedClient;
import battleships.util.Player;

public class ComClient extends EnhancedClient implements GameListener{

	
	public ComClient(String pIPAdresse, int pPortNr) {
		super(pIPAdresse, pPortNr);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void processEnhancedMessage(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNickname(String n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendMap(String map) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void shoot(Player victim, int x, int y) {
		// TODO Auto-generated method stub
	
	}

}
