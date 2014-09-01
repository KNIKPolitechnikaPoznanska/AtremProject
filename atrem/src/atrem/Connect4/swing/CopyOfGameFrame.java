package atrem.Connect4.swing;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

public class CopyOfGameFrame extends JFrame {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 9162854519449667347L;
	private Panel panel;
	private StatsPanel statsPanel;

	/**
	 * Create the frame.
	 * 
	 * @param game
	 */
	public CopyOfGameFrame(SwingPresenter swingPresenter) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 550, 300);
		Container mainContainer = getContentPane();
		panel = new Panel(swingPresenter);
		statsPanel = new StatsPanel(swingPresenter);
		mainContainer.setLayout(new BorderLayout(0, 0));
		mainContainer.add(panel, BorderLayout.CENTER);
		mainContainer.add(statsPanel, BorderLayout.EAST);
		swingPresenter.getFrame(this);
	}

	public Panel getPanel() {
		return panel;
	}

	public StatsPanel getStatsPanel() {
		return statsPanel;
	}

	public void setStatsPanel(StatsPanel statsPanel) {
		this.statsPanel = statsPanel;
	}
}