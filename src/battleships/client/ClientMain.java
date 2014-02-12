package battleships.client;

import javax.swing.JFrame;

public class ClientMain {
	ClientGUI gui;

	// TODO: ClientMain, another way to set the size (set by server or so).
	// This is just a temporary solution
	private final int size = 10;

	public ClientMain() {
		// TODO: ClientMain, constructor
		gui = new ClientGUI(size);
		initGFGUI();
	}

	// Initializes the gamefield-GUI
	private void initGFGUI() {
		JFrame f = new JFrame("Battleships Client");
		f.add(gui);
		f.setResizable(true);
		f.setSize(500, 500);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO: ClientMain, main method
		ClientMain main = new ClientMain();
	}

}
