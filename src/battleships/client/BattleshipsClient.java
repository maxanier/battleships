package battleships.client;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JFrame;

import battleships.client.com.GameListener;
import battleships.util.Player;

public class BattleshipsClient implements IClientEngine{

	private GameListener gameListener;
	private ClientGUI gui;
	@Override
	public void shotResult(Player victim, int x, int y, int newId, boolean sunk) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyYourTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyCreateMap() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startGame(ArrayList<Player> players) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyConnected() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGameListener(GameListener l) {
		gameListener=l;
		
	}


	@Override
	public void mapSet(boolean success) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyError(String message) {
		// TODO Auto-generated method stub
		
	}
	
	public void gameFieldGUIBuilder() {
		
	}
	public void createGUI(int size) {
		gui = new ClientGUI(size);
		JFrame f = new JFrame("Battleships Client");
		f.add(gui);
		f.setResizable(true);
		f.setSize(500, 600);
		f.setVisible(true);
	}

}
