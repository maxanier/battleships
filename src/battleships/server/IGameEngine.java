package battleships.server;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Map;

import battleships.util.Player;

public interface IGameEngine {
	/**
	 * Called when a player shoots at a cell
	 * @param attacker Shooting player
	 * @param victim Player whose cell is shot at
	 * @param x x-Koord of cell
	 * @param y y-Koord of cell
	 */
	public void shoot(Player attacker,Player victim,int x,int y);
	
	/**
	 * Sets a ship placement map for the given player
	 * @param player Player
	 * @param map Map
	 * @return Success
	 */
	public boolean setPlayerMap(Player player,Map map);
	
	/**
	 * Returns if game is running
	 */
	public boolean isRunning();
	
	/**
	 * Starts game
	 * @param players Playerlist
	 * @return succes
	 */
	public boolean start(ArrayList<Player> players);
	
	
	
	/**
	 * Notifies the engine that a player left
	 * @param leaving player
	 */
	public void playerLeft(Player player);
	
	public int getMapSizeX();
	
	public int getMapSizeY();
}
