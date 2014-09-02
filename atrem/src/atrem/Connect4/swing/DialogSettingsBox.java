package atrem.Connect4.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import atrem.Connect4.Connect4Swing;
import atrem.Connect4.classtempbacups.DBoxSettPrev;

public class DialogSettingsBox extends JDialog {
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 973461278407448042L;
	private final JPanel contentPanel = new JPanel();
	private static DBoxSettPrev dialog;
	private JTextField pl2Txt, pl1Txt, TxtSlots, TxtRows;
	private JButton startButton, btnDefault, cancelButton;
	private JPanel buttonPane;
	private JLabel pl1NameLab, pl2NameLab, LabelSlotSet, LabelRowSet,
			labSpacer, LabelsetBoard, LabelSetPlayers;
	private JCheckBox CPUmark;
	private String defPl1Name = "Gracz 1", defPl2Name = "Gracz 2", pl1Name,
			pl2Name;
	private int defSlots = 7, defRows = 6, slots, rows;
	private boolean CPU;
	private String pl1GameType, pl2GameType;

	public String getPl1GameType() {
		return pl1GameType;
	}

	public void setPl1GameType(String pl1GameType) {
		this.pl1GameType = pl1GameType;
	}

	public String getPl2GameType() {
		return pl2GameType;
	}

	public void setPl2GameType(String pl2GameType) {
		this.pl2GameType = pl2GameType;
	}

	private SwingConfig swingConfig;
	private JLabel PlayerLbl1;
	private JLabel playerLbl2;
	private JRadioButton rdbtnConsole1;
	private JRadioButton rdbtnSwing1;
	private JRadioButton rdbtnNet1;
	private JRadioButton rdbtnConsole2;
	private JRadioButton rdbtnSwing2;
	private JRadioButton rdbtnNet2;

	/**
	 * Create the settings dialog.
	 * 
	 * @param swingConfig
	 */
	public DialogSettingsBox(SwingConfig swingConfig) {
		this.swingConfig = swingConfig;
		setTitle("Connect4 Settings");
		setBounds(100, 100, 404, 443);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		ButtonGroup pl1BoardType = new ButtonGroup();
		{
			pl1BoardType.add(rdbtnConsole1);
			pl1BoardType.add(rdbtnSwing1);
			pl1BoardType.add(rdbtnNet1);
		}
		ButtonGroup pl2BoardType = new ButtonGroup();
		{
			pl1BoardType.add(rdbtnConsole2);
			pl1BoardType.add(rdbtnSwing2);
			pl1BoardType.add(rdbtnNet2);
		}
		{
			LabelSetPlayers = new JLabel("     Ustawienia graczy      ");
			LabelSetPlayers.setFont(new Font("Tahoma", Font.PLAIN, 24));
			contentPanel.add(LabelSetPlayers);
		}
		{
			pl1NameLab = new JLabel("Imi\u0119 Gracza 1");
			pl1NameLab.setFont(new Font("Tahoma", Font.PLAIN, 20));
			contentPanel.add(pl1NameLab);
		}
		{
			pl1Txt = new JTextField();
			pl1Txt.setFont(new Font("Tahoma", Font.PLAIN, 20));
			pl1Txt.setText(defPl1Name);
			contentPanel.add(pl1Txt);
			pl1Txt.setColumns(10);
		}
		{
			pl2NameLab = new JLabel("Imi\u0119 Gracza 2");
			pl2NameLab.setFont(new Font("Tahoma", Font.PLAIN, 20));
			contentPanel.add(pl2NameLab);
		}
		{
			pl2Txt = new JTextField();
			pl2Txt.setText(defPl2Name);
			pl2Txt.setFont(new Font("Tahoma", Font.PLAIN, 20));
			contentPanel.add(pl2Txt);
			pl2Txt.setColumns(10);
		}
		{
			CPUmark = new JCheckBox("Gracz 2 - CPU");
			CPUmark.setFont(new Font("Tahoma", Font.PLAIN, 20));
			CPUmark.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					cpuCheckBoxCheck();

				}
			});
			CPUmark.setActionCommand("markCPU");
			contentPanel.add(CPUmark);
		}
		{
			labSpacer = new JLabel("                      ");
			contentPanel.add(labSpacer);
		}
		{
			LabelsetBoard = new JLabel(
					"         Ustawienia planszy            ");
			LabelsetBoard.setFont(new Font("Tahoma", Font.PLAIN, 24));
			contentPanel.add(LabelsetBoard);
		}
		{
			LabelSlotSet = new JLabel("Ilo\u015B\u0107 slot\u00F3w (> 4):");
			LabelSlotSet.setFont(new Font("Tahoma", Font.PLAIN, 20));
			contentPanel.add(LabelSlotSet);
		}
		{
			TxtSlots = new JTextField();
			TxtSlots.setFont(new Font("Tahoma", Font.PLAIN, 20));
			TxtSlots.setText(Integer.toString(defSlots));
			contentPanel.add(TxtSlots);
			TxtSlots.setColumns(10);
		}
		{
			LabelRowSet = new JLabel("Ilo\u015B\u0107 wierszy (> 4):");
			LabelRowSet.setFont(new Font("Tahoma", Font.PLAIN, 20));
			contentPanel.add(LabelRowSet);
		}
		{
			TxtRows = new JTextField();
			TxtRows.setFont(new Font("Tahoma", Font.PLAIN, 20));
			TxtRows.setText(Integer.toString(defRows));
			contentPanel.add(TxtRows);
			TxtRows.setColumns(10);
		}
		{
			PlayerLbl1 = new JLabel("Gracz 1:");
			contentPanel.add(PlayerLbl1);
		}
		{
			rdbtnConsole1 = new JRadioButton("Console");
			contentPanel.add(rdbtnConsole1);
		}
		{
			rdbtnSwing1 = new JRadioButton("Swing");
			contentPanel.add(rdbtnSwing1);
		}
		{
			rdbtnNet1 = new JRadioButton("Multi");
			contentPanel.add(rdbtnNet1);
		}
		{
			playerLbl2 = new JLabel("Gracz 2:");
			contentPanel.add(playerLbl2);
		}
		{
			rdbtnConsole2 = new JRadioButton("Console");
			contentPanel.add(rdbtnConsole2);
		}
		{
			rdbtnSwing2 = new JRadioButton("Swing");
			contentPanel.add(rdbtnSwing2);
		}
		{
			rdbtnNet2 = new JRadioButton("Multi");
			contentPanel.add(rdbtnNet2);
		}
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				startButton = new JButton("Start");
				startButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						startButtonPressed();

					}
				});
				startButton.setFont(new Font("Tahoma", Font.PLAIN, 20));

				buttonPane.add(startButton);
				getRootPane().setDefaultButton(startButton);
			}
			{
				btnDefault = new JButton("Default");
				btnDefault.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						defalutButtonPressed();

					}
				});
				btnDefault.setFont(new Font("Tahoma", Font.PLAIN, 20));
				buttonPane.add(btnDefault);
			}
			{
				cancelButton = new JButton("Anuluj");
				cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						cancelButtonPressed();
					}
				});
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 20));

				buttonPane.add(cancelButton);
			}
		}
	}

	private void cancelButtonPressed() {
		System.exit(0);
	}

	private void cpuCheckBoxCheck() {
		setCPU(CPUmark.getModel().isSelected());
	}

	private void defalutButtonPressed() {
		setDefaults();
	}

	public JCheckBox getIsCPU() {
		return CPUmark;
	}

	public String getPl1Name() {
		return pl1Name;
	}

	public String getPl2Name() {
		return pl2Name;
	}

	public int getRows() {
		return rows;
	}

	public int getSlots() {
		return slots;
	}

	public boolean isCPU() {
		return CPU;
	}

	protected boolean saveSettings() {
		setPl1Name(pl1Txt.getText());
		setPl2Name(pl2Txt.getText());

		// mo�na bez wyj�tku? PAWE�&BARTEK
		try {
			setSlots(Integer.parseInt(TxtSlots.getText()));
			setRows(Integer.parseInt(TxtRows.getText()));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(dialog,
					"Wpisz poprawn� warto�� pola!");
			return false;
		}

		if (getSlots() < 4 || getRows() < 4) {
			JOptionPane.showMessageDialog(dialog, "Sloty i Wiersze > 4!");
			return false;
		}

		if (rdbtnConsole1.isSelected())
			setPl1GameType("console");
		if (rdbtnConsole2.isSelected())
			setPl2GameType("console");
		if (rdbtnSwing1.isSelected())
			setPl1GameType("swing");
		if (rdbtnSwing2.isSelected())
			setPl2GameType("swing");

		return true;
	}

	public void setCPU(boolean cPU) {
		CPU = cPU;
	}

	protected void setDefaults() {
		pl1Txt.setText(defPl1Name);
		pl2Txt.setText(defPl2Name);
		TxtSlots.setText(Integer.toString(defSlots));
		TxtRows.setText(Integer.toString(defRows));
	}

	public void setIsCPU(JCheckBox isCPU) {
		this.CPUmark = isCPU;
	}

	public void setPl1Name(String pl1Name) {
		this.pl1Name = pl1Name;
	}

	public void setPl2Name(String pl2Name) {
		this.pl2Name = pl2Name;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public void setSlots(int slots) {
		this.slots = slots;
	}

	private void startButtonPressed() {
		if (saveSettings()) {
			swingConfig.setSettings();
			new Connect4Swing().init();
			dispose();
		}
	}
}