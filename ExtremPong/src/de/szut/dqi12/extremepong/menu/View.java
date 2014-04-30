package de.szut.dqi12.extremepong.menu;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.JTextField;

public class View extends JFrame {

	private static final long serialVersionUID = 4021128003943864237L;
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
	private JRadioButton powerupsein = new JRadioButton();
	private JRadioButton powerupsaus = new JRadioButton();
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
		return powerupsein;
	}

	public JRadioButton getPufalse() {
		return powerupsaus;
	}

	private static View instance = null;

	// Singleton
	public static View getInstance() {
		if (instance == null) {
			instance = new View();
		}
		return instance;
	}

	public void SelbstZerstoerungsKnopf() {
		System.exit(-1);
	}

	// namen, tasten powerups ja,nein
	private View() {

		this.setBounds(
				Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 250,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 250,
				500, 500);
		this.setLayout(null);

		setResizable(false);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.ERROR_DIALOG);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// es werden Arrays von Komponenten erzeugt und der View hinzugefuegt
		// die Komponenten werden in in gleichen abstaenden angeordnet
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
		// es werden Textfelder erzeugt und der View hinzugefuegt
		for (int i = 0; i < 8; i++) {
			this.tastenFelder[i] = new JTextField();

			this.add(tastenFelder[i]);
			if (i % 2 == 0) {
				tastenFelder[i].setBounds(320, 100 + i / 2 * 70, 30, 30);
			} else {

				tastenFelder[i].setBounds(360, 100 + i / 2 * 70, 30, 30);
			}
		}
		// Radiobuttons werden erstellt, einer Radiobuttongroup hinzugefuegt und
		// der View hinzugefuegt
		this.add(powerupsaus);
		powerupsaus.setText("nein");
		powerupsaus.setBounds(250, 380, 60, 30);

		this.add(powerupsein);
		powerupsein.setText("ja");
		powerupsein.setBounds(200, 380, 50, 30);

		this.add(powerups);
		powerups.setText("Powerups?");
		powerups.setBounds(100, 380, 100, 30);

		// Ein Button wird erstellt und der View hinzugefuegt.
		this.add(abschicken);
		abschicken.setText("Abschicken");
		abschicken.setBounds(330, 400, 120, 30);
		// Dem Button wird einem ActionListener hinzugefuegt.
		abschicken.addActionListener(listener);

		this.add(menueText);
		menueText.setText("Wilkommen zu Extreme Pong 9000");
		menueText.setFont(new Font("Arial", 1, 20));
		menueText.setBounds(75, 30, 500, 30);

		buttonGroup.add(powerupsaus);
		buttonGroup.add(powerupsein);

		// Die Oberflaeche wird sichtbar gemacht.
		this.setVisible(true);

	}
}