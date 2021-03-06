package atrem.connect4.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameFrame extends JFrame {
	/**
	 * Serial
	 */
	private static final long serialVersionUID = -1454524828392484548L;
	private SwingPresenter swingPresenter;
	private GameBoard board;
	private SideBoard sideBoard;
	private JLabel sideToken;
	private Stats stats;
	private Image c4Icon = new ResourceLoader().getIcon().getImage();

	/**
	 * Create the frame.
	 */
	public GameFrame(SwingPresenter swingPresenter) {
		this.swingPresenter = swingPresenter;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(c4Icon);

		Container mainContainer = getContentPane();
		getContentPane().setLayout(
				new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		board = new GameBoard(swingPresenter);
		board.setAlignmentX(Component.RIGHT_ALIGNMENT);
		board.setBackground(new Color(255, 255, 255));
		getContentPane().add(board);

		sideBoard = new SideBoard(swingPresenter.getPlayerId(),
				swingPresenter.getPlayerColor(),
				swingPresenter.getOpponentColor());
		getContentPane().add(sideBoard);
		stats = new Stats();
		stats.setBounds(-15, 129, 342, 207);
		sideBoard.add(stats);

		this.setVisible(true);
	}

	public Stats getStats() {
		return stats;
	}

	public GameBoard getGameBoard() {
		return board;
	}

	public SideBoard getSideBoard() {
		return sideBoard;
	}

	public JLabel getSideToken() {
		return sideToken;
	}
}
