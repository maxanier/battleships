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

	//Extends JButton a little by adding coordinates
	class GFButton extends JButton {
		private int coordX, coordY;
		
		public GFButton(String text, int pX, int pY) {
			super(text);
			coordX = pX;
			coordY = pY;
		}
		public int getCoordX() {
			return coordX;
		}
		public int getCoordY() {
			return coordY;
		}
	}
	
	/*
	 * class for the game field. Contains 2-dimensional JButton-array 1. index:
	 * x-coordinate of the button 2. index: y-coordinate of the button Contains
	 * functions to disable/enable/initialize buttons and so on
	 */
	class GameField extends JPanel {
		private GFButton[][] b_fields;

		public GameField(int size) {
			super(new GridBagLayout());
			b_fields = new GFButton[size][size];
		}

		// Initializes all buttons and sets their color to clr
		public void initButtons(Color clr) {
			GridBagConstraints gbc = new GridBagConstraints();
			for (int i = 0; i < b_fields.length; i++) {
				gbc.gridx = i;
				for (int j = 0; j < b_fields[i].length; j++) {
					b_fields[i][j] = new GFButton(" ", i, j);
					b_fields[i][j].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							GFButton src = (GFButton) e.getSource();
							fieldPressed(src.getCoordX(), src.getCoordY());
						}
					});
					gbc.gridy = j;
					if (clr != null)
						b_fields[i][j].setBackground(clr);
					add(b_fields[i][j], gbc);
				}
			}
		}

		// calls .setEnabled(flag) on all buttons
		public void enableButtons(boolean flag) {
			for (int i = 0; i < b_fields.length; i++)
				for (int j = 0; j < b_fields[i].length; j++)
					b_fields[i][j].setEnabled(flag);
		}

		// Set the color of the specified buttons. X and y must have the same
		// length
		public void setButtonColors(int[] x, int[] y, Color clr)
				throws OutOfBoundsException {
			if (x.length != y.length)
				return;
			for (int i = 0; i < x.length; i++) {
				if (x[i] > b_fields.length || y[i] > b_fields.length) {
					throw new OutOfBoundsException(
							"setButtonColors-call raised exception. One coordinate at "
									+ i + " was too large");
				}
			}
			for (int i = 0; i < x.length; i++)
				b_fields[x[i]][y[i]].setBackground(clr);
		}

		public void setButtonColor(int x, int y, Color clr)
				throws OutOfBoundsException {
			int[] a = { x };
			int[] b = { y };
			setButtonColors(a, b, clr);
		}
		
		public void fieldPressed(int x, int y) {
			//TODO Action for button press
		}
	}

	//Class for a dialog in which the player constructs his/her game field
	private class GFBuilderGUI extends Thread {
		private GameField field;
		private JLabel l_ships;
		private JButton b_done;
		private JPanel p_panel;

		public GFBuilderGUI(int size) {
			JDialog d = new JDialog();
			d.setSize(400,400);
			d.setResizable(true);

			field = new GameField(size);
			d.add(field);
			field.initButtons(Color.BLUE);

			l_ships = new JLabel();
			initLShips(amountLarge, amountLarge, amountLarge, amountSmall,
					amountSmall);
			
			b_done = new JButton("Done");
			b_done.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//TODO adfasdfaegf
				}
			});
			p_panel = new JPanel();
			p_panel.add(l_ships);
			p_panel.add(b_done);
			
			d.add(field);
			d.add(p_panel);
			d.setVisible(true);
		}
		public void run() {
			
		}

		public void initLShips(int a, int b, int s, int c, int p) {
			l_ships.setText("<html>Aircraft Carriers: " + a
					+ "<br>Battleships: " + b + "<br>Submarines: " + s
					+ "<br>Cruisers: " + c + "<br>Patrol Boats: " + p
					+ "</html>");
		}
	}

	/*
	 * Start of ClientGUI class, the main class of the GUI
	 */
	private GameField ownGF, enemyGF;
	private int amountLarge, amountSmall;

	public ClientGUI(int size) {
		amountLarge = size / 10;
		amountSmall = size / 5;
		GFBuilderGUI builderGUI = new GFBuilderGUI(size);
		builderGUI.start();
		try {
			builderGUI.join();
		} catch (InterruptedException e) {e.printStackTrace();}
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
