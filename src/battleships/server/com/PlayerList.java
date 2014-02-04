package battleships.server.com;


import java.util.HashMap;


import battleships.util.Player;

/**
 * Manages Players at serverside.
 * Saves players in a hashmap with a client object, consisting of ip and port, as key.
 * 
 * @author Max Becker
 *
 */
public class PlayerList {
	HashMap<Client,Player> players;
	
	public PlayerList(){
		players=new HashMap<Client,Player>();
		
	}
	
	/**
	 * Adds a player with the given ip and port to the List
	 * @param ip
	 * @param port
	 * @param player
	 */
	public void addPlayer(String ip,Integer port,Player player){
		players.put(new Client(ip,port),player);
	}
	
	/**
	 * Returns Player belonging to ip and port
	 * @param ip Playerip
	 * @param port Playerport
	 * @return Player or null if no player with this ip and port exists
	 */
	public Player getPlayer(String ip,Integer port){
		if(ip!=null){
		return players.get(new Client(ip,port));
		}
		return null;
	}
	
	/**
	 * Returns String representation of a ArrayList with all Players, which can be sent to the clients.
	 * @return String representation
	 */
	public String getPlayerListForClient(){
		return players.values().toString();
	}
	
	/**
	 * Simple key class for the PlayerList.
	 * @author Max Becker
	 *
	 */
	private class Client {
		private String ip;
		private int port;
		
		public Client(String ip,int port){
			this.ip=ip;
			this.port=port;
		}
		
		
		@Override
		public boolean equals(Object o){
			if(this==o) return true;//if same object
			if(!(o instanceof Client)) return false;//if o is from a different class
			Client client=(Client)o;
			if(ip.equals(client.ip)&&port==client.port){
				return true;//if client has the same ip and port
			}
			return false;
		}
		
		@Override
		public int hashCode(){
			return ip.hashCode()+port*31;//Returns hashCode based on ip and port
		}
	}
}
