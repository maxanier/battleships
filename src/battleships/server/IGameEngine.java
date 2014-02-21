package battleships.server;

import java.util.ArrayList;

import battleships.server.com.GameListener;
import battleships.util.Player;

public interface IGameEngine {

	public int getMapSizeX();

	public int getMapSizeY();

	public int getMaxPlayer();

	/**
	 * Returns if game is running
	 */
	public boolean isRunning();

	/**
	 * Notifies the engine that a player left
	 * 
	 * @param leaving
	 *            player
	 */
	public void playerLeft(Player player);

	public void setGameListener(GameListener listener);

	/**
	 * Called when a player shoots at a cell
	 * 
	 * @param attacker
	 *            Shooting player
	 * @param victim
	 *            Player whose cell is shot at
	 * @param x
	 *            x-Koord of cell
	 * @param y
	 *            y-Koord of cell
	 * @return Is allowed
	 */
	public boolean shoot(Player attacker, Player victim, int x, int y);

	/**
	 * Starts game
	 * 
	 * @param players
	 *            Playerlist
	 * @return succes
	 */
	public boolean start(ArrayList<Player> players);
}
