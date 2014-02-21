package battleships.client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameFieldBuilderGUI {
	
	class BuilderGameField extends GameField {

		public BuilderGameField(int size) {
			super(size);
		}
		
	}
	private GameField field;
	private JLabel l_ships;
	private JButton b_done;
	private JPanel p_panel;
	

	public GameFieldBuilderGUI(int size) {
		JDialog d = new JDialog();
		d.setSize(400,400);
		d.setResizable(true);

		field = new GameField(size);
		d.add(field);
		field.initButtons(Color.BLUE);

		l_ships = new JLabel();
		initLShips(size/10, size/10, size/10, size/5,
				size/5);
		
		b_done = new JButton("Done");
		b_done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Done button
			}
		});
		p_panel = new JPanel();
		p_panel.add(l_ships);
		p_panel.add(b_done);
		
		d.add(field);
		d.add(p_panel);
		d.setVisible(true);
	}

	public void initLShips(int a, int b, int s, int c, int p) {
		l_ships.setText("<html>Aircraft Carriers: " + a
				+ "<br>Battleships: " + b + "<br>Submarines: " + s
				+ "<br>Cruisers: " + c + "<br>Patrol Boats: " + p
				+ "</html>");
	}
}

