package de.szut.informatik.extrempong.menue;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Fehlermeldung extends JFrame {
	private JLabel fehler = new JLabel();
	private static Fehlermeldung instance = null;

	public static Fehlermeldung getInstance() {
		if (instance == null) {
			instance = new Fehlermeldung("Falsche Eingabe!!!");
		}
		return instance;
	}

	public Fehlermeldung(String text) {
		this.setBounds(
				Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 100,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 100,
				200, 200);
		this.setLayout(null);
		this.fehler.setText(text);
		this.fehler.setBounds(40, 70, 120, 20);
		this.add(this.fehler);
		this.setVisible(true);
	}

}
