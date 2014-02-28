package battleships.server;

import battleships.util.CONSTANTS;
import battleships.util.FieldId;
import battleships.util.Logger;

public class ServerMap {
	private final static String TAG="ServerMap";
	/**
	 * Creates a new ServerMap from String
	 * 
	 * @param s
	 * @return
	 */
	public static ServerMap createFromString(String s, int x, int y)
			throws MapInvalidException {
		ServerMap serverMap;
		try {
			if (x < 1 || y < 1) {
				return null;
			}
			if (s == null || s.equals("")) {
				throw new MapInvalidException("String is empty");
			}
			if (x != Integer.parseInt(s.split(":")[0]) || y != x) {
				throw new MapInvalidException("Mapsize is invalid");
			}
			String[] map = s.split("\n");

			serverMap = new ServerMap(x, y, FieldId.WATER);

			try {
				for (int i = 0; i < x; i++) {
					String row = map[i + 1];
					for (int j = 0; j < y; j++) {

						serverMap.map[i][j] = row.charAt(j);
					}
				}
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				throw new MapInvalidException("Map doesn�t fit Mapsize");
			}

			if (!serverMap.isValid()) {
				throw new MapInvalidException("Ship placement is false");
			}
		} catch (NumberFormatException e) {
			Logger.e(TAG, "Map String invalid",e);
			throw new MapInvalidException("Map String invalid");
		}
		
		return serverMap;
	}

	private int[][] map;

	/**
	 * Creates a x*y map and fills it with the given id
	 * 
	 * @param x
	 *            x-size
	 * @param y
	 *            y-size
	 * @param standard
	 *            Default Id
	 */
	private ServerMap(int x, int y, int standard) {
		map = new int[x][y];

		if (standard != 0) {
			for (int i = 0; i < x; i++) {
				for (int j = 0; j < y; j++) {
					map[i][j] = standard;
				}
			}
		}
	}

	public int getFieldId(int x, int y) {
		return map[x][y];
	}

	/**
	 * Checks if the ships are placed correctly
	 * 
	 * @return Valid map
	 */
	public boolean isValid() {
		if(shipFieldCount()!=CONSTANTS.getShipFieldCount()){
			Logger.w(TAG, "Map is invalid: ShipField count is wrong");
			return false;
		}
		return true;
		//TODO check if valid
	}
	
	/**
	 * Returns how many ship fields are in the map
	 * @return Ship field count
	 */
	private int shipFieldCount(){
		int count=0;
		for(int[] m:map){
			for(int f:m){
				if(f==FieldId.SHIP){
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * Checks if there is a shippart in the four adjacent cells. Can be used to
	 * check if a ship is completly destroyed
	 * 
	 * @param x
	 *            X-Coord
	 * @param y
	 *            Y-Coord
	 * @return Ship nearby
	 */
	public boolean shipNearby(int x, int y) {
		try {
			if (map[x - 1][y] == FieldId.SHIP) {
				return true;
			}
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			if (map[x + 1][y] == FieldId.SHIP) {
				return true;
			}
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			if (map[x][y - 1] == FieldId.SHIP) {
				return true;
			}
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			if (map[x][y + 1] == FieldId.SHIP) {
				return true;
			}
		} catch (IndexOutOfBoundsException e) {

		}
		return false;
	}

	/**
	 * Checks if there are any ship parts left, which are not sunk.
	 * 
	 * @return Ships left
	 */
	public boolean shipsLeft() {
		for (int a[] : map) {
			for (int b : a) {
				if (b == FieldId.SHIP) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Shoots at the given Location. If a shippart is hit, it is set to sunk.
	 * 
	 * @param x
	 *            X-Coord
	 * @param y
	 *            Y-Coord
	 * @return Hit
	 */
	public boolean shoot(int x, int y) {
		if (map[x][y] == FieldId.SHIP) {
			map[x][y] = FieldId.SUNKEN_SHIP;
			return true;
		}
		return false;
	}

	public static class MapInvalidException extends Exception {

		public MapInvalidException(String additionalInfos) {
			super("The map or map string is invalid.\n Additional Info: ");
		}

	}

}
