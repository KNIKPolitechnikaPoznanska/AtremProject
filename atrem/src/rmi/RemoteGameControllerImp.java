package rmi;

import java.awt.Point;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import atrem.connect4.game.GameController;
import atrem.connect4.game.GameState;
import atrem.connect4.game.LastMove;
import atrem.connect4.game.Logic;
import atrem.connect4.game.ResultState;
import atrem.connect4.game.board.Board;
import atrem.connect4.game.board.HoleState;
import atrem.connect4.game.player.PlayerAttributes;
import atrem.connect4.game.player.PlayerController;
import atrem.connect4.game.player.PlayerId;

public class RemoteGameControllerImp extends UnicastRemoteObject implements
		RemoteGameController {

	GameController gameController;

	public RemoteGameControllerImp(GameController gameController)
			throws RemoteException {
		super();
		this.gameController = gameController;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int move(int slot) throws RemoteException {

		return gameController.move(slot);
	}

	@Override
	public void startNewGame() throws RemoteException {
		gameController.startNewGame();

	}

	@Override
	public void wakeUpGCr() throws RemoteException {
		gameController.wakeUpGCr();

	}

	@Override
	public void analyseDecision() throws RemoteException {
		// TODO Auto-generated method stub
		gameController.analyseDecision();
	}

	@Override
	public void startGameLoop() throws RemoteException {
		gameController.startGameLoop();

	}

	@Override
	public void backToMenu() throws RemoteException {
		gameController.backToMenu();

	}

	@Override
	public void wakeUp() throws RemoteException {
		gameController.wakeUp();

	}

	@Override
	public HoleState getHoleState(int rows, int slots) throws RemoteException {
		return gameController.getHoleState(rows, slots);
	}

	@Override
	public void setResult(ResultState result) throws RemoteException {
		gameController.setResult(result);

	}

	@Override
	public PlayerId getPlayerTurn() throws RemoteException {
		return gameController.getPlayerTurn();
	}

	@Override
	public void setPlayerTurn(PlayerId playerTurn) throws RemoteException {

		gameController.setPlayerTurn(playerTurn);
	}

	@Override
	public int getEmptySpot() throws RemoteException {

		return gameController.getEmptySpot();
	}

	@Override
	public ResultState getResult() throws RemoteException {
		return gameController.getResult();
	}

	@Override
	public Board getBoard() throws RemoteException {
		return gameController.getBoard();
	}

	@Override
	public void setBoard(Board board) throws RemoteException {
		gameController.setBoard(board);

	}

	@Override
	public PlayerController getPlayer1() throws RemoteException {
		return gameController.getPlayer1();
	}

	@Override
	public void setPlayer1(PlayerController player1) throws RemoteException {
		gameController.setPlayer1(player1);

	}

	@Override
	public PlayerController getPlayer2() throws RemoteException {
		return gameController.getPlayer2();

	}

	@Override
	public void setPlayer2(PlayerController player2) throws RemoteException {
		gameController.setPlayer2(player2);

	}

	@Override
	public GameState getGamestate() throws RemoteException {
		return gameController.getGamestate();
	}

	@Override
	public PlayerController getCurrentPlayer() throws RemoteException {
		// TODO Auto-generated method stub
		return gameController.getCurrentPlayer();
	}

	@Override
	public LastMove getLastMove() throws RemoteException {
		return gameController.getLastMove();
	}

	@Override
	public void setGamestate(GameState gamestate) throws RemoteException {
		gameController.setGamestate(gamestate);

	}

	@Override
	public Logic getLogic() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Point> getWinningCoordinates() throws RemoteException {
		return gameController.getWinningCoordinates();
	}

	@Override
	public void run() throws RemoteException {
		gameController.run();

	}

	@Override
	public void addPlayerController(
			RemotePlayerController remotePlayerController)
			throws RemoteException {
		PlayerController playerController = new PlayerControllerService(
				remotePlayerController);
		if (gameController.getPlayer1() == null) {
			playerController.setPlayerId(PlayerId.PLAYER1);
			System.out.println("if" + playerController.getPlayerId());
			gameController.setPlayer1(playerController);
		} else if (gameController.getPlayer2() == null) {
			playerController.setPlayerId(PlayerId.PLAYER2);
			System.out.println("else" + playerController.getPlayerId());
			gameController.setPlayer2(playerController);
		}
	}

	@Override
	public PlayerAttributes getPlayer1Attributes() {
		return gameController.getPlayer1Attributes();
	}

	@Override
	public void setPlayer1Attributes(PlayerAttributes player1Attributes) {
		gameController.setPlayer1Attributes(player1Attributes);

	}

	@Override
	public PlayerAttributes getPlayer2Attributes() {
		return gameController.getPlayer2Attributes();
	}

	@Override
	public void setPlayer2Attributes(PlayerAttributes player2Attributes) {
		gameController.setPlayer2Attributes(player2Attributes);

	}
}
