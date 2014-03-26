package de.szut.informatik.extrempong.menue;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class View extends JFrame {

	private JLabel[] namenLabels = new JLabel[4];
	private JLabel[] tastenLabels = new JLabel[4];
	private JLabel[] spielerLabels = new JLabel[4];
	private JTextField[] spielerFelder = new JTextField[4];
	private JTextField[] tastenFelder = new JTextField[8];
	private JLabel[] Spielerpunkte = new JLabel[4];

	private JButton abschicken = new JButton();
	private JLabel powerups = new JLabel();
	private JLabel menueText = new JLabel();

	private Listener listener = new Listener(this);

	private ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton putrue = new JRadioButton();
	private JRadioButton pufalse = new JRadioButton();
	private int[] punkte = new int[4];

	public void setPunkte(int winner) {
		for (int i = 0; i < 4; i++) {
			this.Spielerpunkte[i] = new JLabel();
			this.Spielerpunkte[i].setBounds(400, 100 + i * 70, 100, 30);
			if (i == winner) {
				this.Spielerpunkte[i].setText("Punkte: " + this.punkte[i + 1]);
			} else {

				this.Spielerpunkte[i].setText("Punkte: " + this.punkte[i]);
			}
			this.add(this.Spielerpunkte[i]);
		}
	}

	public JTextField[] getSpielerFelder() {
		return spielerFelder;
	}

	public JTextField[] getTastenFelder() {
		return tastenFelder;
	}

	public JRadioButton getPutrue() {
		return putrue;
	}

	public JRadioButton getPufalse() {
		return pufalse;
	}

	private static View instance = null;

	// Singleton
	public static View getInstance() {
		if (instance == null) {
			instance = new View();
		}
		return instance;
	}

	// namen, tasten powerups ja,nein
	public View() {

		this.setBounds(
				Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 250,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 250,
				500, 500);
		this.setLayout(null);

		for (int i = 0; i < 4; i++) {
			this.namenLabels[i] = new JLabel();
			this.tastenLabels[i] = new JLabel();
			this.spielerLabels[i] = new JLabel();
			this.spielerFelder[i] = new JTextField();
			this.add(this.namenLabels[i]);
			this.namenLabels[i].setText("Name:");
			this.namenLabels[i].setBounds(100, 100 + i * 70, 100, 30);
			this.add(this.tastenLabels[i]);
			tastenLabels[i].setText("Tasten:");
			tastenLabels[i].setBounds(270, 100 + i * 70, 100, 30);
			this.add(spielerLabels[i]);
			spielerLabels[i].setText("Spieler " + i + ":");
			spielerLabels[i].setBounds(10, 100 + i * 70, 130, 30);
			this.add(spielerFelder[i]);
			spielerFelder[i].setBounds(150, 100 + i * 70, 100, 30);
		}

		for (int i = 0; i < 8; i++) {
			this.tastenFelder[i] = new JTextField();

			this.add(tastenFelder[i]);
			if (i % 2 == 0) {
				tastenFelder[i].setBounds(320, 100 + i / 2 * 70, 30, 30);
			} else {

				tastenFelder[i].setBounds(360, 100 + i / 2 * 70, 30, 30);
			}
		}

		this.add(pufalse);
		pufalse.setText("nein");
		pufalse.setBounds(250, 380, 60, 30);

		this.add(putrue);
		putrue.setText("ja");
		putrue.setBounds(200, 380, 50, 30);

		this.add(powerups);
		powerups.setText("Powerups?");
		powerups.setBounds(100, 380, 100, 30);

		this.add(abschicken);
		abschicken.setText("Abschicken");
		abschicken.setBounds(330, 400, 120, 30);
		abschicken.addActionListener(listener);

		this.add(menueText);
		menueText.setText("Wilkommen zu Pong Extreme 9000");
		menueText.setFont(new Font("Arial", 1, 20));
		menueText.setBounds(75, 30, 500, 30);

		buttonGroup.add(pufalse);
		buttonGroup.add(putrue);

		this.setVisible(true);

	}

	public static void main(String[] args) {

		View view = new View();

	}

}
