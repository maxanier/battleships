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

}
