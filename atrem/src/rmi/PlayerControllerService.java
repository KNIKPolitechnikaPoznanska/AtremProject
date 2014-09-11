package rmi;

import java.awt.Color;
import java.rmi.RemoteException;

import atrem.connect4.game.GameController;
import atrem.connect4.game.ResultState;
import atrem.connect4.game.player.PlayerAttributes;
import atrem.connect4.game.player.PlayerController;
import atrem.connect4.game.player.PlayerId;

public class PlayerControllerService implements PlayerController {

	RemotePlayerController remotePlayerController;

	public PlayerControllerService(RemotePlayerController remotePlayerController) {
		this.remotePlayerController = remotePlayerController;
	}

	@Override
	public String getName() {

		try {
			return remotePlayerController.getName();
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out.println("Wyjatek w remotePlayerController");
		}
		return null;
	}

	@Override
	public void setName(String playerName) {
		try {
			remotePlayerController.setName(playerName);
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out.println("Wyjatek w remotePlayerController");
		}

	}

	@Override
	public PlayerId getPlayerId() {
		try {
			return remotePlayerController.getPlayerId();
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out.println("Wyjatek w remotePlayerController");
		}
		return null;
	}

	@Override
	public void yourTurn() {
		try {
			remotePlayerController.yourTurn();
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out.println("Wyjatek w remotePlayerController");
		}

	}

	@Override
	public void setGamecontroller(GameController gamecontroller) {
		try {
			remotePlayerController.setGamecontroller(gamecontroller);
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out.println("Wyjatek w remotePlayerController");
		}

	}

	@Override
	public void endOfGame(ResultState resultGame) {
		try {
			remotePlayerController.endOfGame(resultGame);
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out.println("Wyjatek w remotePlayerController");
		}

	}

	@Override
	public int getPlayerPoints() {
		try {
			return remotePlayerController.getPlayerPoints();
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out.println("Wyjatek w remotePlayerController");
		}
		return 0;
	}

	@Override
	public PlayerAttributes getPlayerAttributes() {
		try {
			return remotePlayerController.getPlayerAttributes();
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out.println("Wyjatek w remotePlayerController");
		}
		return null;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getOppColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPlayerId(PlayerId playerId) {
		// TODO Auto-generated method stub

	}

}