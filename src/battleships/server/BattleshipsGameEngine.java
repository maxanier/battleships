package battleships.server;

import java.util.ArrayList;
import java.util.Map;

import battleships.util.Logger;
import battleships.util.Player;

public class BattleshipsGameEngine implements IGameEngine {
	
	private boolean running;
	private int maxPlayers;
	private int mapSizeX;
	private int mapSizeY;
	private GameListener gameListener;
	
	public BattleshipsGameEngine(int maxPlayers,int mapSizeX,int mapSizeY){
		running=true;//TODO check if always right
		this.maxPlayers=maxPlayers;
		this.mapSizeX=mapSizeX;
		this.mapSizeY=mapSizeY;
	}

	@Override
	public boolean shoot(Player attacker, Player victim, int x, int y){
		if(gameListener==null){
			Logger.e("GAME_ENGINE","No Listener registered");
			return false;
		}

		return true;

	}

	@Override
	public boolean isRunning() {
		
		return running;
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
		
		return mapSizeX;
	}

	@Override
	public int getMapSizeY() {

		return mapSizeY;
	}
	
	public void setGameListener(GameListener listener){
		this.gameListener=listener;
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

	
	@Override
	public int getMaxPlayer() {
		
		return maxPlayers;
	}
	
	public class NoListenerException extends Exception{
		
	}

}
