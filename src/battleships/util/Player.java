package battleships.util;

import java.util.ArrayList;

import battleships.server.ServerMap;

public class Player {
	private String nickname;
	private int id;
	private ServerMap map;
	
	public Player(String nickname,int id){
		this.nickname=nickname;
		this.id=id;
	}
	
	public String toString(){
		return null;//TODO Implement
	}
	
	public static Player fromString(String s){
		return null;//TODO Implement
	}
	
	public static ArrayList<Player> getPlayerListFromString(String s){
		return null;//TODO Implement
	}
	
	public boolean isRegistered(){
		return nickname==null;
	}

	public String getNickname() {
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
}
