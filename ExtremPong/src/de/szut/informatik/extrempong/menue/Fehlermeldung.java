package de.szut.informatik.extrempong.menue;

import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Fehlermeldung extends JFrame {

	private static final long serialVersionUID = 7836464055107097280L;
	private JLabel fehler = new JLabel();
	private static Fehlermeldung instance = null;

	public static Fehlermeldung getInstance() {
		if (instance == null) {
			instance = new Fehlermeldung("Richtige Eingabe!");
		}
		return instance;
	}

	public Fehlermeldung(String text) {
		this.fehler.setBounds(40, 70, 120, 20);
		this.setBounds(View.getInstance().getBounds().x
				+ View.getInstance().getBounds().width / 2 - 100, View
				.getInstance().getBounds().y
				+ View.getInstance().getBounds().height / 2 - 100, 200, 200);
		this.setLayout(null);
		this.fehler.setText(text);
		this.add(this.fehler);
		this.setVisible(true);
		this.pack();
	}

	public void move() {
		this.setBounds(View.getInstance().getBounds().x
				+ View.getInstance().getBounds().width / 2 - 100, View
				.getInstance().getBounds().y
				+ View.getInstance().getBounds().height / 2 - 100, 200, 200);
	}

}
