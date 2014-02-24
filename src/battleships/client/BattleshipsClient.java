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
	
	public BattleshipsClient(){
		
		String ip;
		do{
		ip = (String)JOptionPane.showInputDialog(
		                    null,
		                    "Enter server ip",
		                    "Choose Ip",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    null,
		                    "");
		}while(ip==null||ip.equals(""));
		
		engine=new ClientGameEngine();
		comClient=new ComClient(ip,CONSTANTS.PORT,engine);
	}
	
	


}
