package battleships.client;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import battleships.client.com.GameListener;
import battleships.util.CONSTANTS;
import battleships.util.Logger;
import battleships.util.Player;

/**
 * Game logic and GUI managment
 * @author Max Becker
 *
 */
public class ClientGameEngine implements IClientEngine, IGUIListener{

	private ClientGUI gui;
	private GameListener gameListener;
	private boolean playing;
	private ArrayList<Player> players;
	private int playerId;
	private Player enemy;
	private boolean myturn;
	private final String TAG="CLientGameEngine";
	
	public ClientGameEngine(){
		createGUI(CONSTANTS.GAME_SIZE);
		myturn=false;
	}
	@Override
	public void notifyConnected(int id) {
		playerId=id;
		String nickname =(String)JOptionPane.showInputDialog(
                null,
                "Enter nickname",
                "Choose nickname",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");
		if(nickname!=null&&!nickname.equals("")){
			gameListener.setNickname(nickname);
		}
		
	}

	@Override
	public void shotResult(int victimId, int x, int y, int newId, boolean sunk) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyYourTurn() {
		myturn=true;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyCreateMap() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startGame(ArrayList<Player> players) {
		this.players=players;
		for(Player p:players){
			if(p.getId()!=playerId){
				enemy=p;
			}
		}
		
		//TODO start
		
	}

	@Override
	public void setGameListener(GameListener l) {
		this.gameListener=l;
		
	}

	@Override
	public void mapSet(boolean success) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyError(String message) {
		JOptionPane.showMessageDialog(null, "Error: "+message);
		
	}

	@Override
	public void notifyEnd(int winnerId) {
		// TODO Auto-generated method stub
		
	}
	
	private void gameFieldGUIBuilder() {
		
	}
	private void createGUI(int size) {
		gui = new ClientGUI(size);
		JFrame f = new JFrame("Battleships Client");
		f.add(gui);
		f.setResizable(true);
		f.setSize(500, 600);
		f.setVisible(true);
	}
	
	@Override
	public void shoot(int x, int y) {
		if(myturn){
		gameListener.shoot(enemy, x, y);
		}
		else{
			Logger.w(TAG, "Trying to shoot, but it´s not my turn");
		}
		
	}

}
