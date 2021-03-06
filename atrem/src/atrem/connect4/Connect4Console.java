package atrem.connect4;

import atrem.connect4.game.GameController;
import atrem.connect4.game.GameFactory;

/*
 * Uruchomienie gry Connect4 w konsoli
 */
public class Connect4Console {
	private GameFactory gameFactory;
	private GameController gameController;

	public Connect4Console(GameFactory gameFactory) {
		this.gameFactory = gameFactory;
	}

	public void init() {
		gameFactory.createGameController();
		gameController = gameFactory.getGameController();
		gameController.startGameLoop();
	}
}
