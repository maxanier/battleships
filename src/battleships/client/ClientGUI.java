package battleships.client;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import battleships.util.FieldId;
import battleships.util.Logger;

import Exceptions.OutOfBoundsException;

public class ClientGUI extends JPanel {	

	private static final String TAG = "ClientGUI";
	/*
	 * Start of ClientGUI class, the main class of the GUI
	 */
	private GameField ownGF, enemyGF;
	private int amountLarge, amountSmall;

	public ClientGUI(int size) {
		amountLarge = size / 10;
		amountSmall = size / 5;
		GameFieldBuilderGUI builderGUI = new GameFieldBuilderGUI(size);
		while(!builderGUI.done) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
		}
		ownGF = builderGUI.getField();
		ownGF.enableButtons(false);
		
		
		enemyGF = new GameField(size, 0);
		enemyGF.initButtons(Color.GRAY);
		enemyGF.enableButtons(false);
		

		add(enemyGF);
		add(ownGF);
	}
	
	public void acceptMyTurn(IGUIListener listener) {
		enemyGF.addListener(listener);
		enemyGF.enableButtons(true);
	}
	
	public void showShotResult(boolean mine, int x, int y, int newId, boolean sunk) {
		if(mine) {
			try {
				ownGF.setButtonColor(x, y, FieldId.getColor(newId));
			} catch (OutOfBoundsException e) {
				Logger.e(TAG, "The coordinates for the shot are out of bounds. Bad aim!", e);
			}
		}
		else {
			try {
				enemyGF.setButtonColor(x, y, FieldId.getColor(newId));
			} catch (OutOfBoundsException e) {
				Logger.e(TAG, "The coordinates for the shot are out of bounds. Bad aim!", e);
			}
		}
		if(sunk)
			JOptionPane.showMessageDialog(this, "Ship sunken");
	}
}
