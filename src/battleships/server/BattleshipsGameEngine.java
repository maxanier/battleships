package battleships.server;

import java.util.ArrayList;
import java.util.Map;

import battleships.util.Player;

public class BattleshipsGameEngine implements IGameEngine {

	@Override
	public boolean shoot(Player attacker, Player victim, int x, int y) {
		// TODO Auto-generated method stub
		return true;

	}

	@Override
	public boolean setPlayerMap(Player player, Map map) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRunning() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void playerLeft(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean start(ArrayList<Player> players) {

		return false;
	}

	@Override
	public int getMapSizeX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMapSizeY() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Game Listener,  a object implementing this should send the given information to the client
	 */
	public interface GameListener{
		/**
		 * Notify all Players about shot result
		 * @param victim Victim player
		 * @param x X-Coord
		 * @param y Y-Coord
		 * @param newId New FieldId
		 * @param sunk whether a ship was completly destroyed or not
		 */
		public void notifyShotResult(Player victim,int x,int y,int newId,boolean sunk);
		
		/**
		 * Notify the player, which is next
		 * @param player Next player
		 */
		public void notifyTurn(Player player);
		
		/**
		 * Notify all players that the game is over
		 * @param winner Winning player
		 */
		public void notifyEnd(Player winner);
	}

}
