package battleships.server;

import java.awt.HeadlessException;

import javax.swing.JOptionPane;

import battleships.server.com.ComServer;
import battleships.util.CONSTANTS;

public class BattleshipsServer {
	private IGameEngine engine;
	private ComServer comServer;
	private int maxPlayer = 2;

	public BattleshipsServer() {
		int size=0;
		do{
		try {
			size = Integer.parseInt((String)JOptionPane.showInputDialog(
			                    null,
			                    "Enter field size",
			                    "Enter field size",
			                    JOptionPane.PLAIN_MESSAGE,
			                    null,
			                    null,
			                    ""));
		} catch (NumberFormatException e) {
			size=0;
		} catch (HeadlessException e) {
			size=0;
			e.printStackTrace();
		}
		}while(size==0);
		CONSTANTS.GAME_SIZE=size;
		engine = new BattleshipsGameEngine(maxPlayer, CONSTANTS.GAME_SIZE,CONSTANTS.GAME_SIZE);
		comServer = new ComServer(CONSTANTS.PORT, engine);

	}
}
