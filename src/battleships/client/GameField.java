package battleships.client;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import battleships.util.FieldId;

import Exceptions.OutOfBoundsException;

/*
 * class for the game field. Contains 2-dimensional JButton-array 1. index:
 * x-coordinate of the button 2. index: y-coordinate of the button Contains
 * functions to disable/enable/initialize buttons and so on
 */
class GameField extends JPanel {
	
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
		
	private GFButton[][] b_fields;
	int mode;
	boolean orientation = true;

	public GameField(int size, int pMode) {
		super(new GridBagLayout());
		b_fields = new GFButton[size][size];
		mode= pMode;
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
					}
				});
				b_fields[i][j].addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent e) {
						GFButton src = (GFButton) e.getSource();
						fieldPressed(src.getCoordX(), src.getCoordY());
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						GFButton src = (GFButton) e.getSource();
						fieldEntered(src.getCoordX(), src.getCoordY());
					}
					@Override
					public void mouseExited(MouseEvent e) {
						GFButton src = (GFButton) e.getSource();
						fieldExited(src.getCoordX(), src.getCoordY());
					}
					@Override
					public void mousePressed(MouseEvent e) {}
					@Override
					public void mouseReleased(MouseEvent e) {}
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
		//TODO Field pressed
	}
	public void fieldEntered(int x, int y) {
		//TODO Field entered
		//If building mode is active
		if(mode!=0) {
			
		}
		//If playing mode is active
		else {
			
		}
	}
	public void fieldExited(int x, int y) {
		//TODO Field exited
	}
	
	
	public String toString() {
		String s = "" + b_fields.length;
		for(int i = 0; i<b_fields.length; i++) {
			s = s + "\n";
			for(int j = 0; j<b_fields.length;i++) {
				if(b_fields[i][j].getBackground() == Color.BLUE)
					s = s + FieldId.WATER;
				else if(b_fields[i][j].getBackground() == Color.BLACK)
					s = s + FieldId.SHIP;
				else if(b_fields[i][j].getBackground() == Color.RED)
					s = s + FieldId.SUNKEN_SHIP;
				else
					s = s + FieldId.UNKNOWN;
			}
		}
		return s;
	}
	
	public void setMode(int pMode) {
		mode = pMode;
	}
	public int getMode() {
		return mode;
	}
}
