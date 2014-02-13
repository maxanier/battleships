package battleships.server.com;

import java.util.ArrayList;
import java.util.HashMap;

import battleships.abiklassen.enhanced.EnhancedServer.Client;
import battleships.util.Player;

/**
 * Manages Players at serverside. Saves players in a hashmap with a client object, consisting of ip and port, as key.
 * 
 * @author Max Becker
 * 
 */
public class PlayerList {
	HashMap<Client, Player> players;

	public PlayerList() {
		players = new HashMap<Client, Player>();

	}

	/**
	 * Adds a player with the given ip and port to the List
	 * 
	 * @param ip
	 * @param port
	 * @param player
	 */
	public void addPlayer(String ip, Integer port, Player player) {
		players.put(new Client(ip, port), player);
	}

	/**
	 * Removes player from List
	 * 
	 * @param ip
	 * @param port
	 * @return whether player existed or not
	 */
	public boolean removePlayer(String ip, Integer port) {
		return players.remove(new Client(ip, port)) != null;
	}

	/**
	 * Returns Player belonging to ip and port
	 * 
	 * @param ip
	 *            Playerip
	 * @param port
	 *            Playerport
	 * @return Player or null if no player with this ip and port exists
	 */
	public Player getPlayer(String ip, Integer port) {
		if (ip != null) {
			return players.get(new Client(ip, port));
		}
		return null;
	}

	/**
	 * Returns String representation of a ArrayList with all Players, which can be sent to the clients.
	 * 
	 * @return String representation
	 */
	public String getPlayerListString() {
		return players.values().toString();
	}

	/**
	 * Return a Player Arraylist
	 * 
	 * @return players
	 */
	public ArrayList<Player> getPlayers() {
		return new ArrayList<Player>(players.values());
	}
	
	/**
	 * Checks if all Players have a map set
	 * @return All set
	 */
	public boolean allMapsSet(){
		for(Player p:players.values()){
			if(p.getMap()==null){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns player with given id, null if not in list
	 * @param id Id
	 * @return Player
	 */
	public Player getPlayer(int id){
		for(Player p:players.values()){
			if(p.getId()==id){
				return p;
			}
		}
		return null;
	}
	
	public Client getPlayerClient(Player p){
		for(Client c:players.keySet()){
			if(players.get(c).equals(p)){
				return c;
			}
		}
		return null;
	}

	

	public int getSize() {
		return players.size();
	}
}
