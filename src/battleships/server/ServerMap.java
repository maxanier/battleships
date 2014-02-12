package battleships.server;

import battleships.util.FieldId;

public class ServerMap {
	int[][] map;

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

	/**
	 * Creates a new ServerMap from String
	 * 
	 * @param s
	 * @return
	 */
	public static ServerMap createFromString(String s, int x, int y) throws MapInvalidException {
		if (x < 1 || y < 1) {
			return null;
		}
		if (s == null || s.equals("")) {
			throw new MapInvalidException("String is empty");
		}
		ServerMap serverMap = new ServerMap(x, y, FieldId.WATER);
		// TODO Throw exception if invalid

		return serverMap;
	}

	/**
	 * Checks if the ships are placed correctly
	 * 
	 * @return Valid map
	 */
	public boolean isValid() {
		return true;
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

	/**
	 * Checks if there is a shippart in the four adjacent cells. Can be used to check if a ship is completly destroyed
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

	public static class MapInvalidException extends Exception {

		public MapInvalidException(String additionalInfos) {
			super("The map or map string is invalid.\n Additional Info: ");
		}

	}

}
