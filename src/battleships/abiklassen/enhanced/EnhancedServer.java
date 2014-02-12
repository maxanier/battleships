package battleships.abiklassen.enhanced;

import battleships.abiklassen.Server;

public abstract class EnhancedServer extends Server {

	public EnhancedServer(int pPortNr) {
		super(pPortNr);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Ein neuer Client hat sich angemeldet.<br>
	 * Diese leere Methode kann in einer Unterklasse realisiert werden (Begr&uuml;&szlig;ung).
	 * 
	 * @param pClientIP
	 *            IP-Nummer des Clients, der neu angemeldet ist
	 * @param pClientPort
	 *            Port-Nummer des Clients, der neu angemeldet ist
	 */
	@Override
	public abstract void processNewConnection(String ip, int port);

	/**
	 * Eine Nachricht von einem Client wurde bearbeitet.<br>
	 * Diese leere Methode sollte in Unterklassen &uuml;berschrieben werden.
	 * 
	 * @param pClientIP
	 *            IP-Nummer des Clients, der die Nachricht geschickt hat
	 * @param pClientPort
	 *            Port-Nummer des Clients, der die Nachricht geschickt hat
	 * @param pMessage
	 *            Die empfangene Nachricht, die bearbeitet werden soll
	 */

	@Override
	public abstract void processMessage(String ip, int port, String message);

	/**
	 * Die Verbindung mit einem Client wurde beendet oder verloren.<br>
	 * Diese leere Methode kann in einer Unterklasse realisiert werden.
	 * 
	 * @param pClientIP
	 *            IP-Nummer des Clients, mit dem die Verbindung beendet wurde
	 * @param pClientPort
	 *            Port-Nummer des Clients, mit dem die Verbindung beendet wurde
	 */
	@Override
	public abstract void processClosedConnection(String ip, int port);

}
