package battleships.server;

import java.util.ArrayList;

import battleships.abiklassen.List;
import battleships.server.com.GameListener;
import battleships.util.FieldId;
import battleships.util.Logger;
import battleships.util.Player;

public class BattleshipsGameEngine implements IGameEngine {

	private boolean running;
	private int maxPlayers;
	private int mapSizeX;
	private int mapSizeY;
	private GameListener gameListener;
	private List players;
	private final String TAG = "GAME_ENGINE";

	public BattleshipsGameEngine(int maxPlayers, int mapSizeX, int mapSizeY) {
		running = false;// TODO check if always right
		this.maxPlayers = maxPlayers;
		this.mapSizeX = mapSizeX;
		this.mapSizeY = mapSizeY;
	}

	@Override
	public int getMapSizeX() {

		return mapSizeX;
	}

	@Override
	public int getMapSizeY() {

		return mapSizeY;
	}

	@Override
	public int getMaxPlayer() {

		return maxPlayers;
	}

	@Override
	public boolean isRunning() {

		return running;
	}

	@Override
	public void playerLeft(Player player) {
		if(players!=null){
			players.toFirst();
			while(players.hasAccess()&&players.getObject().equals(player)){
				players.next();
			}
			if(players.hasAccess()){
				this.end((Player)players.getObject());
			}
		}

	}

	@Override
	public void setGameListener(GameListener listener) {
		this.gameListener = listener;
	}

	@Override
	public boolean shoot(Player attacker, Player victim, int x, int y) {
		if (gameListener == null) {
			Logger.e(TAG, "No Listener registered");
			return false;
		}
		if (!attacker.equals(getCurrentPlayer())) {
			Logger.w(TAG, "Its not this players turn");
			return false;
		}

		ServerMap map = victim.getMap();

		try {

			map.shoot(x, y);

			gameListener.notifyShotResult(victim, x, y, map.getFieldId(x, y),
					(map.getFieldId(x, y)==FieldId.SUNKEN_SHIP&&!map.shipNearby(x, y)));
		} catch (IndexOutOfBoundsException e) {
			Logger.e(TAG, "Map index out of bound", e);
		}

		if (!map.shipsLeft()) {
			
			end(attacker);
		} else {
			nextPlayer();
		}
		return true;

	}

	@Override
	public boolean start(ArrayList<Player> p) {

		if (running) {
			Logger.w(TAG, "Game is already running and can´t be started");
			return false;
		}
		if (gameListener == null) {
			Logger.e(TAG, "No GameListener registered!");
			return false;
		}
		Logger.i(TAG, "Trying to start the game. " + p.size()
				+ " players registered");

		players = new List();
		int starter = (int) Math.round(Math.random() * p.size())+1;

		Logger.i(TAG, "Start player: " + starter);

		for (int i = starter - 1; i < p.size(); i++) {
			players.append(p.get(i));
		}
		for (int i = 0; i < starter - 1; i++) {
			players.append(p.get(i));
		}
		players.toFirst();

		gameListener.notifyTurn((Player) players.getObject());

		return true;
	}

	private void end(Player winner) {
		gameListener.notifyEnd(winner);

	}

	private Player getCurrentPlayer() {
		return (Player) players.getObject();
	}

	private void nextPlayer() {
		players.next();
		if (!players.hasAccess()) {
			players.toFirst();
		}
		gameListener.notifyTurn(getCurrentPlayer());
	}

}
