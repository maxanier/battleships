
package battleships.client.com;

import battleships.util.Player;

/**
 * Game Listener
 * Any class implementing this, should send the given information to the server
 * @author Max Becker
 *
 */
public interface GameListener {
	
	/**
	 * Sends the nickname to the server
	 * @param n Nickname
	 * @return success
	 */
	public void setNickname(String n);
	
	/**
	 * Sends the ship layout to the server
	 * @param map Ship layout as String
	 * @return success
	 */
	public void sendMap(String map);
	
	/**
	 * Sends the shoot command to the server
	 * @param victim victim player
	 * @param x X-Coord
	 * @param y Y-Coord
	 * @return success, false if not your turn
	 */
	public void shoot(Player victim,int x,int y);
}
