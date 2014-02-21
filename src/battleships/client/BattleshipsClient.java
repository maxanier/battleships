package battleships.client;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JFrame;

import battleships.client.com.ComClient;
import battleships.client.com.GameListener;
import battleships.util.CONSTANTS;
import battleships.util.Player;

/**
 * Main Client class.
 * Creates ComServer and engine
 * It may do more later
 * @author Max Becker
 *
 */
public class BattleshipsClient {

	private ComClient comClient;
	private ClientGameEngine engine;
	private String serverIp;
	
	public BattleshipsClient(){
		engine=new ClientGameEngine();
		comClient=new ComClient(serverIp,CONSTANTS.PORT,engine);
	}
	
	


}
