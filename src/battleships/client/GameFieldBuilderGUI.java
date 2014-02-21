package battleships.client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameFieldBuilderGUI extends JPanel{
	private GameField field;
	private JButton b_done;
	private JButton[] b_ships;
	private int[] shipAmounts;
	

	public GameFieldBuilderGUI(int size) {
		JDialog d = new JDialog();
		d.setSize(400,400);
		d.setResizable(true);

		field = new GameField(size, 1);
		d.add(field);
		field.initButtons(Color.BLUE);
		
		shipAmounts = new int[5];
		for(int i=0; i<3; i++)
			shipAmounts[i] = size/10;
		for(int i=3;i<5;i++)
			shipAmounts[i] = size/5;
		
		b_ships = new JButton[5];
		b_ships[0] = new JButton("Aircraft Carrier: " + shipAmounts[0]);
		b_ships[1] = new JButton("Battleship: " + shipAmounts[1]);
		b_ships[2] = new JButton("Cruiser: " + shipAmounts[2]);
		b_ships[3] = new JButton("Destroyer: " + shipAmounts[3]);
		b_ships[4] = new JButton("Submarine: " + shipAmounts[4]);
		
		for(int i=0; i<5;i++)
			b_ships[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//TODO Button clicked
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
				//TODO Done button
			}
		});
		
		d.add(field);
		add(b_done);
		for(int i=0;i<b_ships.length;i++) 
			add(b_ships[i]);
		d.setVisible(true);
	}
}

