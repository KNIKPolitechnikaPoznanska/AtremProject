package atrem.Connect4.swing;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import atrem.Connect4.Game.board.HoleState;

public class SideBoard extends JPanel {
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 9208904490186715373L;
	ResourceLoader icon = new ResourceLoader();
	String pl1Name, pl2Name, message;
	JLabel token1, token2, player1, player2, dialogWinow;
	JPanel namesAndColours;

	public SideBoard() {
		setLayout(null);

		namesAndColours = new JPanel();
		namesAndColours.setBounds(10, 11, 169, 115);
		add(namesAndColours);
		namesAndColours.setLayout(null);

		player1 = new JLabel(pl1Name);
		player1.setBounds(5, 16, 91, 14);
		namesAndColours.add(player1);

		token1 = new JLabel("");
		token1.setBounds(119, 3, 40, 40);
		namesAndColours.add(token1);

		player2 = new JLabel(pl2Name);
		player2.setBounds(5, 78, 91, 14);
		namesAndColours.add(player2);

		token2 = new JLabel("");
		token2.setBounds(119, 65, 40, 40);
		namesAndColours.add(token2);
		token2.setIcon(null); // do metody bez obrazka BARTEK

		dialogWinow = new JLabel(message);
		dialogWinow.setVerticalAlignment(SwingConstants.TOP);
		dialogWinow.setBounds(8, 137, 169, 129);
		add(dialogWinow);
	}

	@Deprecated
	public void displayCurrentToken(int player) {
		if (player == 1) {
			token1.setIcon(icon.get(HoleState.PLAYER1));
			token2.setIcon(null);
		} else {
			token2.setIcon(icon.get(HoleState.PLAYER2));
			token1.setIcon(null);
		}
	}

	public ResourceLoader getIconResource() {
		return icon;
	}

	/**
	 * METODY do komunikacji z graczem ! BARTEK
	 */
	public void sendMessage(String message) {
		message = "Witamy w Connect4!";
		this.message = message;
	}

	public void setPl1Name(String name) {
		this.pl1Name = name;
		player1.setText(pl1Name);
	}

	public void setPl2Name(String name) {
		this.pl2Name = name;
		player2.setText(pl2Name);
	}

	public void setTokenPl1() {
		token1.setIcon(ResourceLoader.iconPl2);
	}

	public void setTokenPl2() {
		token1.setIcon(ResourceLoader.iconPl1);
	}
}