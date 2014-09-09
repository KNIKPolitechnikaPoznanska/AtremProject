package atrem.connect4.game.player;

import atrem.connect4.game.GameController;
import atrem.connect4.game.ResultState;

public interface PlayerController {

	public abstract String getName();

	public abstract void setName(String playerName);

	public abstract PlayerId getPlayerId();

	// public abstract void yourTurn(LastMove lastMove);

	public abstract void yourTurn();

	public abstract void endOfGame(ResultState resultGame);

	public abstract PlayerAttributes getPlayerAttributes();

	public abstract void setGamecontroller(GameController gamecontroller);

	public abstract int getPlayerPoints();

}