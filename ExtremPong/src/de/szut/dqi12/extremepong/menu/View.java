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

import de.szut.dqi12.extremepong.util.JTextFieldLimit;

public class View extends JFrame {

	private static final long serialVersionUID = 4021128003943864237L;
	private JLabel[] spielerLabels = new JLabel[4];
	private JTextField[] tastenFelder = new JTextField[8];
	private JLabel[] Spielerpunkte = new JLabel[4];
	private JLabel winnerlabel = new JLabel();
	private JButton info = new JButton();

	private JButton abschicken = new JButton();
	private JLabel powerups = new JLabel();
	private JLabel menueText = new JLabel();

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

	public void selbstZerstoerungsKnopf() {
		System.exit(-1);
	}

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
			this.spielerLabels[i] = new JLabel();
			this.add(spielerLabels[i]);
			spielerLabels[i].setBounds(100, 100 + i * 70, 200, 30);
		}
		spielerLabels[0].setText("(Tasten) Spieler Oberer Rand:");
		spielerLabels[1].setText("(Tasten) Spieler Rechter Rand:");
		spielerLabels[2].setText("(Tasten) Spieler Unterer Rand:");
		spielerLabels[3].setText("(Tasten) Spieler Linker Rand:");
		// es werden Textfelder erzeugt und der View hinzugefuegt
		for (int i = 0; i < 8; i++) {
			this.tastenFelder[i] = new JTextField();
			this.tastenFelder[i].setDocument(new JTextFieldLimit(1, true));
			this.add(tastenFelder[i]);
			if (i % 2 == 0) {
				tastenFelder[i].setBounds(310, 100 + i / 2 * 70, 30, 30);
			} else {
				tastenFelder[i].setBounds(350, 100 + i / 2 * 70, 30, 30);
			}
		}

		// StandardBelegung der Tasten
		tastenFelder[0].setText("A");
		tastenFelder[1].setText("S");
		tastenFelder[2].setText("D");
		tastenFelder[3].setText("F");
		tastenFelder[4].setText("G");
		tastenFelder[5].setText("H");
		tastenFelder[6].setText("J");
		tastenFelder[7].setText("K");

		winnerlabel.setBounds(this.getBounds().width / 2 - 30, 65, 200, 30);
		this.add(winnerlabel);

		// Radiobuttons werden erstellt, einer Radiobuttongroup hinzugefuegt und
		// der View hinzugefuegt
		this.add(powerupsaus);
		powerupsaus.setSelected(true);
		powerupsaus.setText("nein");
		powerupsaus.setBounds(280, 360, 60, 30);

		this.add(powerupsein);
		powerupsein.setText("ja");
		powerupsein.setBounds(230, 360, 50, 30);

		this.add(powerups);
		powerups.setText("Powerups?");
		powerups.setBounds(130, 360, 100, 30);

		// Ein Button wird erstellt und der View hinzugefuegt.
		this.add(abschicken);
		abschicken.setText("Abschicken");
		abschicken.setBounds(330, 400, 120, 30);
		
		// Dem Button wird einem ActionListener hinzugefuegt.
		abschicken.addActionListener(new Listener());
		
		this.add(info);
		info.setText("Ufo (Info)");
		info.setBounds(50, 400, 120, 30);
		info.addActionListener(new InfoListener());
		

		this.add(menueText);
		menueText.setText("Wilkommen zu Extreme Pong 9000");
		menueText.setFont(new Font("Arial", 1, 20));
		menueText.setBounds(75, 30, 500, 30);

		buttonGroup.add(powerupsaus);
		buttonGroup.add(powerupsein);

		// Die Oberflaeche wird sichtbar gemacht.
		this.setVisible(true);

	}

	public void setWinnerlabel(String str) {
		winnerlabel.setText("Gewinner: " + str);
	}
}