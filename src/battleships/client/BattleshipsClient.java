package battleships.client;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
		
		String s = (String)JOptionPane.showInputDialog(
		                    null,
		                    "Enter server ip",
		                    "Choose Ip",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    null,
		                    "");
		//TODO check ip
		
		engine=new ClientGameEngine();
		comClient=new ComClient(serverIp,CONSTANTS.PORT,engine);
	}
	
	


}
