package battleships.util;

public class PROTOKOLL {

	/*
	 * Client to Server(CS) Server to Client(SC)
	 */

	// General
	public final static String ERROR = "-ERR:";
	public final static String MISSING_PARAMETER = ERROR + " Missing Parameter";
	public final static String UNKNOWN="UNKNOWN COMMAND";

	// Lobby-stage
	public final static String SC_HELLO = "Hi, Please register";
	public final static String CS_REGISTER = "REGISTER"; // <nickname>
	public final static String SC_NAME_ACCEPTED = "+N_ACCEPTED";
	public final static String SC_FULL = ERROR + " Full";// If server is full
	public final static String SC_PLAYING = ERROR + " Playing";// If game is running
	public final static String SC_CREATE_MAP = "CREATEMAP";

	// Creating-map-stage
	public final static String CS_MAP = "MAP";// <ship placement map as String>
	public final static String SC_MAP_INVALID = ERROR + " Map invalid";// If map is invalid or not enough ships are placed
	public final static String SC_MAP_ACCEPTED = "+M_ACCEPTED";
	public final static String SC_START = "STARTGAME";// <playerlist as String>

	// Game-stage
	public final static String CS_SHOOT = "SHOOT";// <playerid>(who is the victim) <x> <y>(coordinate of cell)
	public final static String SC_SHOT_RESULT = "SHOOT_RESULT";// <playerId> <x> <y> <newId> <sunk>(Whether a ship was completly destroyed or not=
	public final static String SC_NOTIFY = "YOUR_TURN";// Notifies client when its his turn
	public final static String SC_NOT_ALLOWED = ERROR + " Not allowed";// e.g. if client wants to shoot but its not his turn
	public final static String SC_PLAYER_NOT_FOUND=ERROR+" Player not found";
	public final static String SC_END = "END";// <winning playerId>

}
