package battleships.util;

import java.util.ArrayList;

public class Player {
	private String nickname;
	private int id;
	
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
}
