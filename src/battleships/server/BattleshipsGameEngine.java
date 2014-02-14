package battleships.server;

import java.util.ArrayList;

import battleships.abiklassen.List;
import battleships.server.com.GameListener;
import battleships.util.Logger;
import battleships.util.Player;

public class BattleshipsGameEngine implements IGameEngine {
	
	private boolean running;
	private int maxPlayers;
	private int mapSizeX;
	private int mapSizeY;
	private GameListener gameListener;
	private List players;
	private final String TAG="GAME_ENGINE";
	
	public BattleshipsGameEngine(int maxPlayers,int mapSizeX,int mapSizeY){
		running=false;//TODO check if always right
		this.maxPlayers=maxPlayers;
		this.mapSizeX=mapSizeX;
		this.mapSizeY=mapSizeY;
	}

	@Override
	public boolean shoot(Player attacker, Player victim, int x, int y){
		if(gameListener==null){
			Logger.e(TAG,"No Listener registered");
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
	public boolean start(ArrayList<Player> p) {
		
		if(running){
			Logger.w(TAG,"Game is already running and can´t be started");
			return false;
		}
		if(gameListener ==null){
			Logger.e(TAG, "No GameListener registered!");
			return false;
		}
		Logger.i(TAG, "Trying to start the game. "+p.size()+" players registered");
		
		players=new List();
		int starter=(int) Math.round(Math.random()*p.size());
		
		Logger.i(TAG, "Start player: "+starter);
		
		for(int i =starter-1;i<p.size();i++){
			players.append(p.get(i));
		}
		for(int i=0;i<starter-1;i++){
			players.append(p.get(i));
		}
		players.toFirst();
		
		gameListener.notifyTurn((Player)players.getObject());
		
		return true;
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
	
	

	
	@Override
	public int getMaxPlayer() {
		
		return maxPlayers;
	}
	


}
