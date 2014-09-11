package atrem.connect4.game;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

import atrem.connect4.factory.GameFactory;
import atrem.connect4.game.board.Board;
import atrem.connect4.game.board.HoleState;
import atrem.connect4.game.player.PlayerAttributes;
import atrem.connect4.game.player.PlayerController;
import atrem.connect4.game.player.PlayerDecision;
import atrem.connect4.game.player.PlayerId;
import atrem.connect4.swing.SwingPresenter;

public class GameControllerImpl implements Runnable, GameController {
	private Logic logic;
	private Board board;
	private int doneMoves;
	private LastMove lastMove;
	private PlayerController currentPlayer, player1, player2;
	private PlayerAttributes player1Attributes, player2Attributes;
	private int emptySpot, slot;
	private PlayerId playerTurn = PlayerId.PLAYER2;
	private ResultState resultState = ResultState.NO_WIN;
	private GameState gameState = GameState.PRE_INIT;

	/**
	 * Sprawdza kt�rego z graczy jest kolej
	 * 
	 * @return player1 lub player2
	 */
	private PlayerController changeCurrentPlayer() {
		switch (playerTurn) {
			case PLAYER1 :
				return player1;
			case PLAYER2 :
				return player2;
			default :
				return null; // currentPlayer
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see atrem.connect4.game.GameController#move(int)
	 */
	@Override
	public synchronized int move(int slot) {
		this.currentPlayer = changeCurrentPlayer();
		this.slot = slot;
		emptySpot = board.findFreeSpot(slot);
		if (emptySpot == -1) {
			return emptySpot;
		}
		board.setHoleState(emptySpot, slot, currentPlayer.getPlayerId());
		board.setLastSlot(slot);
		board.setLastSpot(emptySpot);
		lastMove.saveLastMove(slot, emptySpot, playerTurn);
		// lastMoveStack.push(lastMove);
		if (emptySpot != -1) {
			gameState = GameState.MOVE_DONE;
			notifyAll();
		}
		return emptySpot;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see atrem.connect4.game.GameController#startNewGame()
	 */
	@Override
	public void startNewGame() {
		int row = board.getRows();
		int slot = board.getSlots();
		PlayerAttributes player1Attributes = player1.getPlayerAttributes();
		PlayerAttributes player2Attributes = player2.getPlayerAttributes();
		Color player1Color = player1Attributes.getPlayerColor();
		Color player2Color = player2Attributes.getPlayerColor();
		lastMove = new LastMove();
		board = new Board(row, slot);
		resultState = ResultState.NO_WIN;
		gameState = GameState.PRE_INIT;
		startGameLoop();
		if (player1 instanceof SwingPresenter) {
			player1 = new SwingPresenter(this, player1Attributes, player2Color,
					player1.getPlayerPoints());
		} else
			connectPlayer();
		if (player2 instanceof SwingPresenter) {
			player2 = new SwingPresenter(this, player2Attributes, player1Color,
					player2.getPlayerPoints());
		} else
			connectPlayer();
		doneMoves = 0;
	}

	private synchronized void gameLoop() {// ma odczytywa� GameState
		boolean endGame;
		logic = new Logic(this);
		lastMove = new LastMove();
		System.out.println("Oczekuje na graczy.");
		waitForPlayers();
		while (resultState == ResultState.NO_WIN) {
			currentPlayer = changeCurrentPlayer();
			System.out.println("Ruch gracza: "
					+ changeCurrentPlayer().getName());
			gameState = GameState.WAITING_FOR_MOVE;
			currentPlayer.yourTurn();
			waitForMove();
			doneMoves++;
			endGame = logic.getResultOfMove(lastMove.getLastRow(),
					lastMove.getLastSlot(), doneMoves);

			if (endGame) {
				gameState = GameState.PRE_INIT;
				changePlayer();
				currentPlayer = changeCurrentPlayer();
				currentPlayer.yourTurn();
				player1.endOfGame(resultState);
				player2.endOfGame(resultState);
				return;
			}
			changePlayer();
		}
	}

	private synchronized void waitForMove() {
		while (gameState != GameState.MOVE_DONE) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private synchronized void waitForPlayers() {
		while (gameState != GameState.END_INIT_ALL) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see atrem.connect4.game.GameController#wakeUpGCr()
	 */
	@Override
	public synchronized void connectPlayer() {
		switch (gameState) {
			case PRE_INIT :
				gameState = GameState.END_INIT_1;
				break;
			case END_INIT_1 :
				gameState = GameState.END_INIT_ALL;
				this.notifyAll();
				break;
			default :
				break;
		}
	}

	public void analyseDecision() {
		if (player1.getPlayerAttributes().getPlayerDecision() == PlayerDecision.NEW_GAME
				&& player2.getPlayerAttributes().getPlayerDecision() == PlayerDecision.NEW_GAME)
			startNewGame();
		else if (player1.getPlayerAttributes().getPlayerDecision() == PlayerDecision.MENU
				|| player2.getPlayerAttributes().getPlayerDecision() == PlayerDecision.MENU)
			backToMenu();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see atrem.connect4.game.GameController#run()
	 */
	@Override
	public void run() {
		gameLoop();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see atrem.connect4.game.GameController#startGameLoop()
	 */
	@Override
	public void startGameLoop() {
		new Thread(this, "W�tek kontrolera gry").start();
	}

	/**
	 * Zmiana tury gracza.
	 */
	private void changePlayer() {
		if (playerTurn == PlayerId.PLAYER1) {
			setPlayerTurn(PlayerId.PLAYER2);
		} else {
			setPlayerTurn(PlayerId.PLAYER1);
		}
	}

	public void backToMenu() {
		GameFactory gameFactory = new GameFactory();
		GameConfig config = new GameConfig();
		config.showOfflineDBox();
		config.showOnlineDBox();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see atrem.connect4.game.GameController#getHoleState(int, int)
	 */
	@Override
	public HoleState getHoleState(int rows, int slots) {
		return this.board.getHoleState(rows, slots);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * atrem.connect4.game.GameController#setResult(atrem.connect4.game.ResultState
	 * )
	 */
	@Override
	public void setResult(ResultState result) {
		this.resultState = result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see atrem.connect4.game.GameController#getPlayerTurn()
	 */
	@Override
	public PlayerId getPlayerTurn() {
		return playerTurn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * atrem.connect4.game.GameController#setPlayerTurn(atrem.connect4.game.
	 * player.PlayerId)
	 */
	@Override
	public void setPlayerTurn(PlayerId playerTurn) {
		this.playerTurn = playerTurn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see atrem.connect4.game.GameController#getEmptySpot()
	 */
	@Override
	public int getEmptySpot() {
		return emptySpot;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see atrem.connect4.game.GameController#getResult()
	 */
	@Override
	public ResultState getResult() {
		return resultState;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see atrem.connect4.game.GameController#getBoard()
	 */
	@Override
	public Board getBoard() {
		return board;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * atrem.connect4.game.GameController#setBoard(atrem.connect4.game.board
	 * .Board)
	 */
	@Override
	public void setBoard(Board board) {
		this.board = board;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see atrem.connect4.game.GameController#getPlayer1()
	 */
	@Override
	public PlayerController getPlayer1() {
		return player1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * atrem.connect4.game.GameController#setPlayer1(atrem.connect4.game.player
	 * .PlayerController)
	 */
	@Override
	public void setPlayer1(PlayerController player1) {
		this.player1 = player1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see atrem.connect4.game.GameController#getPlayer2()
	 */
	@Override
	public PlayerController getPlayer2() {
		return player2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * atrem.connect4.game.GameController#setPlayer2(atrem.connect4.game.player
	 * .PlayerController)
	 */
	@Override
	public void setPlayer2(PlayerController player2) {
		this.player2 = player2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see atrem.connect4.game.GameController#getGamestate()
	 */
	@Override
	public GameState getGamestate() {
		return gameState;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see atrem.connect4.game.GameController#getCurrentPlayer()
	 */
	@Override
	public PlayerController getCurrentPlayer() {
		return currentPlayer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see atrem.connect4.game.GameController#getLastMove()
	 */
	@Override
	public LastMove getLastMove() {
		return lastMove;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * atrem.connect4.game.GameController#setGamestate(atrem.connect4.game.GameState
	 * )
	 */
	@Override
	public void setGamestate(GameState gamestate) {
		this.gameState = gamestate;
	}

	public Logic getLogic() {
		return logic;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see atrem.connect4.game.GameController#getWinningCoordinates()
	 */
	@Override
	public List<Point> getWinningCoordinates() {
		return logic.getWinningCoordinates();
	}

	@Override
	public PlayerAttributes getPlayer1Attributes() {
		return player1Attributes;
	}

	@Override
	public PlayerAttributes getPlayer2Attributes() {
		return player2Attributes;
	}

	@Override
	public void setPlayer1Attributes(PlayerAttributes player1Attributes) {
		this.player1Attributes = player1Attributes;
	}

	@Override
	public void setPlayer2Attributes(PlayerAttributes player2Attributes) {
		this.player2Attributes = player2Attributes;
	}
}
