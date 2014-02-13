package battleships.server;

import java.util.ArrayList;
import java.util.Map;

import battleships.util.Player;

public interface IGameEngine {
	
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
	 * Returns if game is running
	 */
	public boolean isRunning();

	/**
	 * Starts game
	 * 
	 * @param players
	 *            Playerlist
	 * @return succes
	 */
	public boolean start(ArrayList<Player> players);

	/**
	 * Notifies the engine that a player left
	 * 
	 * @param leaving
	 *            player
	 */
	public void playerLeft(Player player);

	public int getMapSizeX();

	public int getMapSizeY();
	
	public int getMaxPlayer();
}
