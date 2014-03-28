package battleships.client;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import battleships.util.FieldId;
import battleships.util.Logger;

import Exceptions.OutOfBoundsException;

public class ClientGUI extends JPanel {	

	private static final String TAG = "ClientGUI";
	private int size;
	/*
	 * Start of ClientGUI class, the main class of the GUI
	 */
	private GameField ownGF, enemyGF;
	private JFrame frame;
	private JCheckBox cb_hide;
	
	public ClientGUI(int pSize, JFrame pFrame) {
		size = pSize;
		frame = pFrame;
		cb_hide = new JCheckBox("Hide own gamefield");
		cb_hide.setSelected(true);
		cb_hide.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent evt) {
				toggleOwnGF();
			}
		});
	}
	
	//Initializes the GUI, ownGF is needed first
	public void init() {
		this.removeAll();
		enemyGF = new GameField(size, 0);
		enemyGF.initButtons(Color.GRAY);
		enemyGF.enableButtons(false);
		
		ownGF.setMode(0);
		ownGF.enableButtons(false);
		
		add(enemyGF);
		add(cb_hide);
		add(ownGF);
		ownGF.setVisible(false);
		
		frame.setVisible(true);
		frame.pack();
		this.repaint();
		frame.repaint();
	}
	
	private void toggleOwnGF() {
		if(ownGF != null) 
			ownGF.setVisible(!cb_hide.isSelected());
		frame.pack();
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
	
	public String getMap() {
		if(ownGF == null)
			return null;
		return ownGF.toString();
	}
	
	public void createMap(IGUIListener listener) {
		GameFieldBuilderGUI builderGUI = new GameFieldBuilderGUI(size);
		while(!builderGUI.done) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
		}
		ownGF = builderGUI.getField();
		listener.mapCreated(ownGF.toString());
	}
}
