package battleships.util;

import java.util.ArrayList;

import battleships.abiklassen.enhanced.EnhancedServer.Client;
import battleships.server.ServerMap;

public class Player {
	private String nickname;
	private int id;
	private ServerMap map;

	public Player(String nickname, int id) {
		this.nickname = nickname;
		this.id = id;
	}
	
	private Player(){
		
	}

	@Override
	public String toString() {
		return nickname+";"+id;
	}

	/**
	 * Creates a new Player with the string data
	 * @param s Playerstring
	 * @return created Player
	 */
	public static Player fromString(String s) {
		try {
			String ss[]= s.split(",");
			Player p=new Player();
			p.nickname=ss[0];
			p.id=Integer.parseInt(ss[1]);
			if(p.nickname.equals("")){
				p.nickname="Player";
			}
			return p;
		} catch (NumberFormatException e) {
			Logger.e("PLAYER", "Error while parsing Playerstring",e);
			return null;
		}
	}

	public static ArrayList<Player> getPlayerListFromString(String s) {
			ArrayList<Player> players=new ArrayList<Player>();
			s=s.replace("[","");
			s=s.replace("]", "");
			String ss[]=s.split(",");
			for(String sss:ss){
				Player p=fromString(sss);
				if(p!=null){
					players.add(p);
				}
				else{
					Logger.w("PLAYER_PLAYERLIST","PLAYER NULL");
				}
			}
			return players;
		
	}

	public boolean isRegistered() {
		return nickname == null;
	}

	public String getNickname() {
		if(nickname==null||nickname.equals("")){
			return "Player";
		}
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getId() {
		return id;
	}

	public ServerMap getMap() {
		return map;
	}

	public void setMap(ServerMap map) {
		this.map = map;
	}
	
	@Override
	public boolean equals(Object o){
		if (this == o)
			return true;// if same object
		if (!(o instanceof Player))
			return false;// if o is from a different class
		Player p=(Player)o;
		return(p.id==this.id);
	}
}
