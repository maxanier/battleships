package battleships.server.com;

import battleships.util.Player;

/**
 * Game Listener, a object implementing this should send the given information
 * to the client
 */
public interface GameListener {
	/**
	 * Notify all players that the game is over
	 * 
	 * @param winner
	 *            Winning player
	 */
	public void notifyEnd(Player winner);

	/**
	 * Notify all Players about shot result
	 * 
	 * @param victim
	 *            Victim player
	 * @param x
	 *            X-Coord
	 * @param y
	 *            Y-Coord
	 * @param newId
	 *            New FieldId
	 * @param sunk
	 *            whether a ship was completly destroyed or not
	 */
	public void notifyShotResult(Player victim, int x, int y, int newId,
			boolean sunk);

	/**
	 * Notify the player, which is next
	 * 
	 * @param player
	 *            Next player
	 */
	public void notifyTurn(Player player);
}