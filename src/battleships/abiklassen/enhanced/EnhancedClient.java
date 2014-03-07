package battleships.abiklassen.enhanced;

import battleships.abiklassen.Client;
import battleships.util.Logger;

public abstract class EnhancedClient extends Client {

	private final String TAG="Client";
	protected boolean ready=false;
	
	public EnhancedClient(String pIPAdresse, int pPortNr) {
		super(pIPAdresse, pPortNr);
		Logger.init();
	}

	/**
	 * Eine Nachricht vom Server wurde bearbeitet.<br>
	 * Diese abstrakte Methode muss in Unterklassen &uuml;berschrieben werden.
	 * 
	 * @param message
	 *            die empfangene Nachricht, die bearbeitet werden soll
	 */
	public abstract void processEnhancedMessage(String message);
	
	@Override
	public final void processMessage(String message) {
		Logger.i(TAG, "Received new message: \""+message+"\"");
		while(!ready){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		}
		processEnhancedMessage(message);
	}
	
	@Override
	public void send(String message){
		Logger.i(TAG, "Sending message: \""+message+"\"");
		super.send(message);
	}

}
