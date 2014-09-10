package testy.game.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import atrem.connect4.game.GameController;
import atrem.connect4.game.ResultState;
import atrem.connect4.game.player.PlayerAttributes;
import atrem.connect4.game.player.PlayerController;
import atrem.connect4.game.player.PlayerId;

public class RemotePlayerControlerImpl extends UnicastRemoteObject implements
		RemotePlayerController {

	private PlayerController player;

	protected RemotePlayerControlerImpl() throws RemoteException {
		super();
	}

	@Override
	public String getName() throws RemoteException {
		return player.getName();
	}

	@Override
	public void setName(String playerName) throws RemoteException {

		player.setName(playerName);

	}

	@Override
	public PlayerId getPlayerId() throws RemoteException {

		return player.getPlayerId();
	}

	@Override
	public void yourTurn() throws RemoteException {
		player.yourTurn();

	}

	@Override
	public void setGamecontroller(GameController gamecontroller)
			throws RemoteException {
		player.setGamecontroller(gamecontroller);

	}

	@Override
	public void endOfGame(ResultState resultGame) throws RemoteException {
		player.endOfGame(resultGame);

	}

	@Override
	public int getPlayerPoints() throws RemoteException {
		return player.getPlayerPoints();

	}

	@Override
	public PlayerAttributes getPlayerAttributes() throws RemoteException {
		return player.getPlayerAttributes();
	}

}