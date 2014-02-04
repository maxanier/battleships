package battleships.server;

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
}
