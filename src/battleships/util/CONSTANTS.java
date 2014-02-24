package battleships.util;

import javax.swing.JButton;

public class CONSTANTS {
	public static final String LOG_DIRECTORY = "";
	public static final int PORT = 6000;
	public static final String LOG_FILE_NAME = "log.txt";
	public static final int GAME_SIZE=10;
	
	public static int[] getShipAmount(){
		int [] shipAmounts = new int[5];
		for(int i=0; i<3; i++)
			shipAmounts[i] = GAME_SIZE/10;
		for(int i=3;i<5;i++)
			shipAmounts[i] = GAME_SIZE/5;
		
		return shipAmounts;
	}
}
