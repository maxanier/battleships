package battleships.abiklassen.enhanced;

import battleships.abiklassen.Server;
import battleships.util.Logger;


/**
 * Enhances the Abi-Server
 * Adds e.g. logging;
 * @author Max Becker
 *
 */
public abstract class EnhancedServer extends Server {

	private final String TAG="Server";
	public EnhancedServer(int pPortNr) {
		super(pPortNr);
		Logger.init();
	}

	/**
	 * Ein neuer Client hat sich angemeldet.<br>
	 * Diese leere Methode kann in einer Unterklasse realisiert werden (Begr&uuml;&szlig;ung).
	 * 
	 * @param ip
	 *            IP-Nummer des Clients, der neu angemeldet ist
	 * @param port
	 *            Port-Nummer des Clients, der neu angemeldet ist
	 */
	public abstract void processNewEnhancedConnection(String ip,int port);
	
	
	@Override
	public final void processNewConnection(String ip, int port){
		Logger.i(TAG,"New connection: "+ip+":"+port);
		processNewEnhancedConnection(ip,port);
	}

	/**
	 * Eine Nachricht von einem Client wurde bearbeitet.<br>
	 * Diese leere Methode sollte in Unterklassen &uuml;berschrieben werden.
	 * 
	 * @param ip
	 *            IP-Nummer des Clients, der die Nachricht geschickt hat
	 * @param port
	 *            Port-Nummer des Clients, der die Nachricht geschickt hat
	 * @param message
	 *            Die empfangene Nachricht, die bearbeitet werden soll
	 */
	public abstract void processEnhancedMessage(String ip,int port,String message);
	
	@Override
	public final void processMessage(String ip, int port, String message){
		Logger.i(TAG, "Received new message from: "+ip+":"+port+" \""+message+"\"");
		processEnhancedMessage(ip,port,message);
	}

	/**
	 * Die Verbindung mit einem Client wurde beendet oder verloren.<br>
	 * Diese leere Methode kann in einer Unterklasse realisiert werden.
	 * 
	 * @param ip
	 *            IP-Nummer des Clients, mit dem die Verbindung beendet wurde
	 * @param port
	 *            Port-Nummer des Clients, mit dem die Verbindung beendet wurde
	 */
	public abstract void processClosedEnhancedConnection(String ip,int port);
	
	@Override
	public final void processClosedConnection(String ip, int port){
		Logger.i(TAG,"Closing connection: "+ip+":"+port);
		processClosedEnhancedConnection(ip,port);
	}
	
	
	/**
	 * Eine Nachricht wurde an einen Client geschickt.
	 * 
	 * @param ip
	 *            IP-Nummer des Empf&auml;ngers
	 * @param port
	 *            Port-Nummer des Empf&auml;ngers
	 * @param message
	 *            die verschickte Nachricht
	 */
	@Override
	public void send(String ip,int port,String message){
		Logger.i(TAG,"Sending message to "+ip+":"+port+" \""+message+"\"");
		super.send(ip, port, message);
	}
	
	
	/**
	 * Eine Nachricht wurde an alle verbundenen Clients geschickt.
	 * 
	 * @param message
	 * 
	 */
	@Override
	public void sendToAll(String message){
		Logger.i(TAG, "Sending message to all clients: "+" \""+message+"\"");
		super.sendToAll(message);
	}

}
