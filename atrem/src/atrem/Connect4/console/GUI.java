package atrem.Connect4.console;

import atrem.Connect4.Game.Board;
import atrem.Connect4.Game.Game;
import atrem.Connect4.Game.HoleState;
import atrem.Connect4.Game.player.PlayerController;

/*
 * Rysowanie GUI konsolowego
 * w tym: planszy i jej obja�nie�
 */
public class GUI {

	public void displayGame(Game game) {
		this.drawBoard(game.getBoard());
		this.drawSlots(game.getBoard().getSlots());
		if (game.getPlayerTurn() == 1) {
			this.writeCurrentPlayer(game.getPlayer1());
		}
		if (game.getPlayerTurn() == 2) {
			this.writeCurrentPlayer(game.getPlayer2());
		}
		this.displayParagraph();
	}

	public void displayResults(Game game) {
		this.drawBoard(game.getBoard());
		this.displayParagraph();
	}

	private void drawBoard(Board BoardTmp) {
		HoleState HoleTmp;
		for (int j = 0; j < BoardTmp.getRows(); j++) {
			for (int i = 0; i < BoardTmp.getSlots(); i++) {

				HoleTmp = BoardTmp.getHoleState(j, i);
				System.out.print("| " + HoleTmp.getNumber() + " |");
			}
			System.out.println("");
		}

	}

	private void drawSlots(int slots) {
		System.out.println("Numery slot�w:");
		for (int i = 1; i <= slots; i++) {
			System.out.print("| " + i + " |");
		}
		System.out.println();

	}

	private void writeCurrentPlayer(PlayerController player) {
		String name = player.getName();
		System.out.println("Obecny ruch wykonuje:  " + name + " ");
	}

	private void displayParagraph() {
		System.out.println("\n");
		System.out.println("************************************");
	}

	public void writeFullSlots() {
		System.out.println("Slot jest pelen, podaj inny: ");
	}

	public boolean showWinner(HoleState holeState, Game game) {
		if (holeState.name() == "PLAYER1") {
			System.out.println("	Wygra�: " + game.getNamePlayer1());
		} else if (holeState.name() == "PLAYER2") {
			System.out.println("Wygra� " + game.getNamePlayer2());
		}
		return true;
	}

	public void showRemis() {
		System.out.println("Gra bez rostrzygniecia \n Remis.");
	}

}