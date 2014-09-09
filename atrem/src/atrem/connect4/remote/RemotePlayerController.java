package atrem.connect4.remote;

import java.rmi.Remote;

import atrem.connect4.game.ResultState;
import atrem.connect4.game.player.PlayerAttributes;

public interface RemotePlayerController extends Remote {
	public abstract void yourTurn();

	public abstract void endOfGame(ResultState resultGame);

	public abstract PlayerAttributes getPlayerAttributes();

}
