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
 * 
 * @author Max Becker
 * 
 */
public class ClientGameEngine implements IClientEngine, IGUIListener {

	private ClientGUI gui;
	private GameListener gameListener;
	private ArrayList<Player> players;
	private Player myPlayer;
	private Player enemy;// only needed as long as two player game
	private boolean myturn;
	private final String TAG = "CLientGameEngine";

	public ClientGameEngine() {
		createGUI(CONSTANTS.GAME_SIZE);
		myturn = false;
	}

	@Override
	public ArrayList<Player> getPlayers() {
		return players;
	}

	@Override
	public void mapSet(boolean success) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyConnected() {

		String nickname = (String) JOptionPane.showInputDialog(null,
				"Enter nickname", "Choose nickname", JOptionPane.PLAIN_MESSAGE,
				null, null, "");
		if (nickname != null && !nickname.equals("")) {
			gameListener.setNickname(nickname);
		}

	}

	@Override
	public void notifyCreateMap() {

		gameListener.sendMap(CONSTANTS.getTestMap());

	}

	@Override
	public void notifyEnd(Player winner) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyError(String message) {
		JOptionPane.showMessageDialog(null, "Error: " + message);

	}

	@Override
	public void notifyYourTurn() {
		myturn = true;
		gui.acceptMyTurn(this);

	}

	@Override
	public void setGameListener(GameListener l) {
		this.gameListener = l;

	}

	@Override
	public void shoot(int x, int y) {
		if (myturn) {
			gameListener.shoot(enemy, x, y);
		} else {
			Logger.w(TAG, "Trying to shoot, but it´s not my turn");
		}

	}

	@Override
	public void shotResult(Player victim, int x, int y, int newId, boolean sunk) {
		gui.showShotResult(victim.equals(myPlayer), x, y, newId, sunk);

	}

	@Override
	public void startGame(ArrayList<Player> players, Player me) {
		this.players = players;
		this.myPlayer = me;

		for (Player p : players) {
			if (!p.equals(me)) {
				enemy = p;
			}
		}

		// TODO start

	}

	private void createGUI(int size) {
		gui = new ClientGUI(size);
		JFrame f = new JFrame("Battleships Client");
		f.add(gui);
		f.setResizable(true);
		f.setSize(500, 600);
		f.setVisible(true);
	}

	private void gameFieldGUIBuilder() {

	}

}
