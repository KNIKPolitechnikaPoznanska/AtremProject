package atrem.Connect4.Game;

import java.util.Stack;

import atrem.Connect4.Game.board.Board;
import atrem.Connect4.Game.board.HoleState;
import atrem.Connect4.Game.player.PlayerController;
import atrem.Connect4.swing.SwingPresenter;

public class GameController implements Runnable {
	private Logic logic;
	private Board board;
	private int doneMoves;
	private LastMove lastMove;
	private PlayerController currentPlayer, player1, player2;
	private int emptySpot, slot;
	private PlayerId playerTurn = PlayerId.Player1;
	private ResultState resultState = ResultState.NoWin;
	private GameState gameState = GameState.preInit;
	private Stack<LastMove> lastMoveStack;

	/**
	 * Sprawdza kt�rego z graczy jest kolej
	 * 
	 * @return player1 lub player2
	 */
	private PlayerController currentPlayer() {
		switch (playerTurn) {
		case Player1:
			return player1;
		case Player2:
			return player2;
		default:
			return player1;
		}
	}

	/**
	 * @param slot
	 * @return puste miejsce w Board
	 */
	public synchronized int move(int slot) {
		this.currentPlayer = currentPlayer();
		this.slot = slot;
		emptySpot = board.findFreeSpot(slot);
		if (emptySpot == -1) {
			return emptySpot;
		}
		board.setHoleState(emptySpot, slot, currentPlayer.getPlayerId());
		board.setLastSlot(slot);
		board.setLastSpot(emptySpot);
		lastMove.saveLastMove(slot, emptySpot, playerTurn);

		gameState = GameState.moveDone;
		if (emptySpot != -1) {
			notifyAll();
		}

		return emptySpot;
	}

	/**
	 * Glowna petla gry
	 */
	public void startNewGame() {
		board = new Board(board.getLastRow(), board.getLastSlot());
		resultState = ResultState.NoWin;
		gameState = GameState.nextGame;
		if (player1 instanceof SwingPresenter) {
			player1 = new SwingPresenter(player1.getName(), PlayerId.Player1,
					this, true);
		}
		if (player2 instanceof SwingPresenter) {
			player2 = new SwingPresenter(player2.getName(), PlayerId.Player2,
					this, false);
		}

		startGameLoop();

	}

	private synchronized void gameLoop() {// ma odczytywa� GameState
		boolean resultGame;
		logic = new Logic(this);
		lastMove = new LastMove();
		System.out.println("przed init");
		if (gameState != GameState.nextGame)
			waitForInit();
		while (resultState == ResultState.NoWin) {
			currentPlayer = currentPlayer();
			try {
				this.wait(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			currentPlayer.yourTurn();
			System.out.println("po ");
			gameState = GameState.waitingForMove;
			waitForMove();
			doneMoves++;
			resultGame = logic.checkResult(doneMoves);

			if (resultGame == true) {
				changePlayer();
				currentPlayer = currentPlayer();
				currentPlayer.yourTurn();
				player1.endOfGame(resultState);

				player2.endOfGame(resultState);
				return;
			}
			changePlayer();

		}
	}

	private synchronized void waitForMove() {
		while (gameState != GameState.moveDone) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private synchronized void waitForInit() {
		while (gameState != GameState.endInitAll) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void endInitPlayer() {
		switch (gameState) {
		case preInit:
			gameState = GameState.endInit1;
			break;
		case endInit1:
			gameState = GameState.endInitAll;
			this.notifyAll();
			break;
		}
	}

	@Override
	public void run() {
		gameLoop();

	}

	public void startGameLoop() {
		new Thread(this, "W�tek kontrolera gry").start();
	}

	/**
	 * Zmiana tury gracza.
	 */
	private void changePlayer() {
		if (playerTurn == PlayerId.Player1) {
			setPlayerTurn(PlayerId.Player2);
		} else {
			setPlayerTurn(PlayerId.Player1);
		}
	}

	public void wakeUp() {
		notifyAll();
	}

	public HoleState getHoleState(int rows, int slots) {
		return this.board.getHoleState(rows, slots);
	}

	public void setResult(ResultState result) {
		this.resultState = result;
	}

	public PlayerId getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(PlayerId playerTurn) {
		this.playerTurn = playerTurn;
	}

	public int getEmptySpot() {
		return emptySpot;
	}

	public ResultState getResult() {
		return resultState;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public PlayerController getPlayer1() {
		return player1;
	}

	public void setPlayer1(PlayerController player1) {
		this.player1 = player1;
	}

	public PlayerController getPlayer2() {
		return player2;
	}

	public void setPlayer2(PlayerController player2) {
		this.player2 = player2;
	}

	public GameState getGamestate() {
		return gameState;
	}

	public LastMove getLastMove() {
		return lastMove;
	}

	public void setGamestate(GameState gamestate) {
		this.gameState = gamestate;
	}

	public Logic getLogic() {
		return logic;
	}

}
