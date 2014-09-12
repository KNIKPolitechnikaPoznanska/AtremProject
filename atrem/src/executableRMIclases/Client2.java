package executableRMIclases;

import java.awt.Color;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.GameControllerService;
import rmi.RemoteGameController;
import rmi.RemotePlayerController;
import rmi.RemotePlayerControllerImp;
import atrem.connect4.game.GameController;
import atrem.connect4.game.player.PlayerAttributes;

public class Client2 {

	public static void main(String[] args) throws RemoteException,
			NotBoundException, InterruptedException {
		System.out.println("client2 start (pawel)");
		PlayerAttributes playerAttributes = new PlayerAttributes("pawel", null,
				0, new Color(20, 255, 3));
		Registry registry = LocateRegistry.getRegistry(80);

		RemoteGameController remoteGameController = (RemoteGameController) registry
				.lookup("gc");

		GameController gameControllerService = new GameControllerService(
				remoteGameController);
		RemotePlayerController remotePlayerController = new RemotePlayerControllerImp();
		if (remoteGameController.getPlayer1Attributes() == null) {
			remoteGameController.setPlayer1Attributes(playerAttributes);
		} else if (remoteGameController.getPlayer2Attributes() == null) {
			remoteGameController.setPlayer2Attributes(playerAttributes);
		}
		Thread.sleep(10000);
		remotePlayerController.createPlayer(gameControllerService,
				playerAttributes);
		remoteGameController.addPlayerController(remotePlayerController);

	}
}
