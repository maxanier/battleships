package battleships.client;

import java.util.ArrayList;

import battleships.client.com.GameListener;
import battleships.util.Player;

public interface IClientEngine {
	
	/**
	 * Should be called when the connection is established and a nickname should be set
	 */
	public void notifyConnected();
	
	/**
	 * A field was shot.
	 * @param victim Victim player
	 * @param x X-Coord
	 * @param y Y-Coord
	 * @param newId New FieldId
	 * @param sunk whether a ship was completly destroyed or not
	 */
	public void shotResult(Player victim,int x,int y,int newId, boolean sunk);
	
	/**
	 * Should be called if its the clients turn.
	 */
	public void notifyYourTurn();
	
	/**
	 * Should be called when a the player/client should create and send his map
	 */
	public void notifyCreateMap();
	
	/**
	 * Should be called when the game starts
	 * @param players ArrayList of all participating players
	 */
	public void startGame(ArrayList<Player> players);
	
	public void setGameListener(GameListener l);
	
	/**
	 * Should be called if the server responded to setting nickname
	 * @param success whether succesful or not
	 */
	public void nicknameSet(boolean success);
	
	/**
	 * Should be called if the server responded to setting map
	 * @param success whether succesful or not
	 */
	public void mapSet(boolean success);
	
	
}
