package battleships.util;

import java.awt.Color;

public class FieldId {

	public static final int UNKNOWN = 3;
	public static final int WATER = 0;
	public static final int SHIP = 1;
	public static final int SUNKEN_SHIP = 2;
	public static final int WATER_HIT = 4;
	
	public static Color getColor(int id) {
		switch (id) {
		case WATER: return Color.BLUE;
		case SHIP: return Color.BLACK;
		case SUNKEN_SHIP: return Color.RED;
		case WATER_HIT: return new Color(128,0,255);
		default: return Color.GRAY;
		}
	}
}
