package battleships.client;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Exceptions.OutOfBoundsException;

public class ClientGUI extends JPanel {	

	/*
	 * Start of ClientGUI class, the main class of the GUI
	 */
	private GameField ownGF, enemyGF;
	private int amountLarge, amountSmall;

	public ClientGUI(int size) {
		amountLarge = size / 10;
		amountSmall = size / 5;
		GameFieldBuilderGUI builderGUI = new GameFieldBuilderGUI(size);
		enemyGF = new GameField(size);
		enemyGF.initButtons(Color.GRAY);
		enemyGF.enableButtons(false);
		
		ownGF = new GameField(size);
		ownGF.initButtons(Color.GRAY);
		ownGF.enableButtons(false);

		add(enemyGF);
		add(ownGF);
	}
}
