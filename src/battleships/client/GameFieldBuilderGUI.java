package battleships.client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import battleships.util.CONSTANTS;
import battleships.util.Logger;

public class GameFieldBuilderGUI extends JPanel implements ShipPlacementListener {
	private GameField field;
	private JButton b_done;
	private JButton[] b_ships;
	private int[] shipAmounts;
	public boolean done;
	
	private final String TAG = "GameFieldBuilderGUI";
	

	public GameFieldBuilderGUI(int size) {
		final JDialog d = new JDialog();
		d.setSize(500,500);
		d.setResizable(true);

		field = new GameField(size, 1, this);
		d.add(field);
		field.initButtons(Color.BLUE);
		shipAmounts=CONSTANTS.getShipAmount();
		
		b_ships = new JButton[5];
		b_ships[0] = new JButton();
		b_ships[1] = new JButton();
		b_ships[2] = new JButton();
		b_ships[3] = new JButton();
		b_ships[4] = new JButton();
		updateButtons();
		
		for(int i=0; i<5;i++)
			b_ships[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for(int i=0; i<5; i++)
						if((JButton) e.getSource() == b_ships[i]) {
							if(i == field.getMode()-1 || i == field.getMode()*(-1)-1)
								field.setMode(field.getMode()*(-1));
							else
								field.setMode(i+1);
						}
				}
			});

		b_done = new JButton("Done");
		b_done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean allPlaced = true;
				for(int tmp : shipAmounts) 
					if(tmp != 0)
						allPlaced = false;
				if(!allPlaced)
					JOptionPane.showMessageDialog(null, "You did not place all ships!", "Error", JOptionPane.ERROR_MESSAGE);
				else
					d.dispose();
				done = allPlaced;
			}
		});
		
		JPanel tmp = new JPanel();
		tmp.add(b_done);
		for(int i=0;i<b_ships.length;i++) 
			tmp.add(b_ships[i]);
		tmp.add(field);
		d.setVisible(true);
		d.add(tmp);
		d.pack();
		d.repaint();
	}
	
	public GameField getField() {
		return field;
	}
	
	public void updateButtons() {
		b_ships[0].setText("Aircraft Carrier: " + shipAmounts[0]);
		b_ships[1].setText("Battleship: " + shipAmounts[1]);
		b_ships[2].setText("Cruiser: " + shipAmounts[2]);
		b_ships[3].setText("Destroyer: " + shipAmounts[3]);
		b_ships[4].setText("Submarine: " + shipAmounts[4]);
	}

	@Override
	public boolean shipPlaced(int mode) {
		if(shipAmounts[mode-1] > 0) {
			shipAmounts[mode-1]--;
			updateButtons();
			boolean allPlaced = true;
			for(int tmp : shipAmounts)
				if(tmp != 0)
					allPlaced = false;
			//if(allPlaced) 
				//JOptionPane.showMessageDialog(this, "All ships placed, please press \"Done\"", "Done", JOptionPane.INFORMATION_MESSAGE);
			return true;
		} else
			return false;
	}
}

