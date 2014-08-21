package atrem.Connect4.Game;
/*
 * Klasa tworz�ca plansz� (Board)
 */
public class GameFactory {

	private Board board;
	private PlayerType player1, player2;

	public void setGameFactory(Game game, int rows, int slots, String im1,
			String im2) {
		board = new Board(rows, slots);
		player1 = new Player(im1, HoleState.PLAYER1);
		player2 = new Player(im2, HoleState.PLAYER2);
		game.setBoard(board);
		game.setPlayer1(player1);
		game.setPlayer2(player2);
	}

}
