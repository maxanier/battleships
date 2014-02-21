package battleships.client;

public class ClientMain {
	private BattleshipsClient bsClient;

	public static void main(String[] args) {
		BattleshipsClient bsClient = new BattleshipsClient();
		//TODO another way to set the size
		bsClient.createGUI(10);
	}

}
