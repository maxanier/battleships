package battleships.client;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import Exceptions.OutOfBoundsException;

public class ClientGUI extends JPanel {

	/*
	 * class for the game field. Contains 2-dimensional JButton-array 1. index: x-coordinate of the button 2. index: y-coordinate
	 * of the button Contains functions to disable/enable/initialize buttons and so on
	 */
	class GameField extends JPanel {
		private JButton[][] fields;

		public GameField(int size) {
			super(new GridBagLayout());
			fields = new JButton[size][size];
		}

		// Initializes all buttons and sets their color to clr
		public void initButtons(Color clr) {
			GridBagConstraints gbc = new GridBagConstraints();
			for (int i = 0; i < fields.length; i++) {
				gbc.gridx = i;
				for (int j = 0; j < fields[i].length; j++) {
					fields[i][j] = new JButton(" ");
					gbc.gridy = j;
					if (clr != null)
						fields[i][j].setBackground(clr);
					add(fields[i][j], gbc);

				}
			}
		}

		// calls .setEnabled(flag) on all buttons
		public void enableButtons(boolean flag) {
			for (int i = 0; i < fields.length; i++)
				for (int j = 0; j < fields[i].length; j++)
					fields[i][j].setEnabled(flag);
		}

		// Set the color of the specified buttons. X and y must have the same
		// length
		public void setButtonColors(int[] x, int[] y, Color clr) throws OutOfBoundsException {
			if (x.length != y.length)
				return;
			for (int i = 0; i < x.length; i++) {
				if (x[i] > fields.length || y[i] > fields.length) {
					throw new OutOfBoundsException("setButtonColors-call raised exception. One coordinate at " + i
							+ " was too large");
				}
			}
			for (int i = 0; i < x.length; i++)
				fields[x[i]][y[i]].setBackground(clr);
		}

		public void setButtonColor(int x, int y, Color clr) throws OutOfBoundsException {
			int[] a = { x };
			int[] b = { y };
			setButtonColors(a, b, clr);
		}
	}

	/*
	 * Start of ClientGUI class, the main class of the GUI
	 */
	public ClientGUI(int size) {
		GameField gf = new GameField(size);
		gf.initButtons(Color.gray);
		add(gf);
		try {
			gf.setButtonColor(5, 5, Color.blue);
		} catch (OutOfBoundsException e) {
		}
	}
}
