package battleships.util;

import javax.swing.JButton;

public class CONSTANTS {
	public static final String LOG_DIRECTORY = "";
	public static final int PORT = 6000;
	public static final String LOG_FILE_NAME = "log.txt";
	public static  int GAME_SIZE=15;
	
	public static int[] getShipAmount(){
		int [] shipAmounts = new int[5];
		for(int i=0; i<3; i++)
			shipAmounts[i] = GAME_SIZE/10;
		for(int i=3;i<5;i++)
			shipAmounts[i] = GAME_SIZE/5;
		
		return shipAmounts;
	}
	
	public static int getShipFieldCount(){
		int[] ships = getShipAmount();
		int count=0;
		count+=ships[0]*5;
		count+=ships[1]*4;
		count+=ships[2]*3;
		count+=ships[3]*2;
		count+=ships[4]*1;
		return count;
	}
	
	public static String getTestMap(){
		return("10:0111110000:0000000001:0100000001:0000000000:0000000001:0100000000:0100000010:0100000010:0100110010:0000000000");
	}
}
