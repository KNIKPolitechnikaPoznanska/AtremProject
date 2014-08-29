package atrem.Connect4.swing;

import java.awt.EventQueue;

import javax.swing.JLabel;

import atrem.Connect4.Game.Game;
import atrem.Connect4.Game.GameController;
import atrem.Connect4.Game.ResultState;
import atrem.Connect4.Game.player.PlayerController;

public class SwingPresenter implements PlayerController {
	private Game game;
	private int Slots;
	private int Rows;
	private int playerTurn;
	private int LastRow;
	private int LastSlot;
	private int emptySpot;
	private ResultState result;
	private GameController gameController;
	private Panel panel;
	private StatsPanel statsPanel;
	private GameFrame gameFrame;
	private JLabel pl1Label, pl2Label;

	public int getSlots() {
		return Slots;
	}

	public void setSlots(int slots) {
		Slots = slots;
	}

	public int getRows() {
		return Rows;
	}

	public void setRows(int rows) {
		Rows = rows;
	}

	public void setPresenter(Game game, GameController gameController) {
		this.game = game;
		this.gameController = gameController;
		setSettings();
	}

	public void setSettings() {
		Rows = game.getBoardRows();
		Slots = game.getBoardSlots();

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					GameFrame frame = new GameFrame(SwingPresenter.this);
					frame.setTitle("Connect 4");
					frame.setVisible(true);
					playerTurn = gameController.getPlayerTurn();
					changeDispTurn(playerTurn);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void goView(int Row, int Slot, int playerTurn) {
		changeDispTurn(playerTurn);
		panel.setFreeRow(Row, Slot, playerTurn);
	}

	public void getSlotFromView(int LastSlot) {
		gameController.move(LastSlot);
		emptySpot = gameController.getEmptySpot();
		result = gameController.getResult();
		playerTurn = gameController.getPlayerTurn();
		goView(emptySpot, LastSlot, playerTurn);
	}

	public void setPanels() {
		panel = gameFrame.getPanel();
		statsPanel = gameFrame.getStatsPanel();
		pl1Label = statsPanel.getPl1Label();
		pl2Label = statsPanel.getPl2Label();
		setNames();
	}

	public void getFrame(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		setPanels();
	}

	public void changeDispTurn(int playerTurn) {
		if (playerTurn == 1) {
			pl1Label.setVisible(false);
			pl2Label.setVisible(true);
		} else if (playerTurn == 2) {
			pl2Label.setVisible(false);
			pl1Label.setVisible(true);
		}
	}

	public void setNames() {
		pl1Label.setText(game.getNamePlayer1());
		pl2Label.setText(game.getNamePlayer2());
	}

}
