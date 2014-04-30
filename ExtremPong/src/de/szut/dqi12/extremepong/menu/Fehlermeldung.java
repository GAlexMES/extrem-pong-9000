package de.szut.dqi12.extremepong.menu;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Fehlermeldung extends JFrame {

	private static final long serialVersionUID = 7836464055107097280L;
	private JLabel fehler = new JLabel();
	private static Fehlermeldung instance = null;

	//Singleton
	public static Fehlermeldung getInstance() {
		if (instance == null) {
			instance = new Fehlermeldung("Falsche Eingabe!!!");
		}
		return instance;
	}
	
	public Fehlermeldung(String text) {
		// Die Position und groesse des angezeigten Texts wird festgelegt.
		this.fehler.setBounds(40, 70, 120, 20);
		// Die Position und der Ramen der Oberflaeche wird festgelegt.
		this.setBounds(View.getInstance().getBounds().x
				+ View.getInstance().getBounds().width / 2 - 100, View
				.getInstance().getBounds().y
				+ View.getInstance().getBounds().height / 2 - 100, 200, 200);
		// Der Oberflaeche wird kein Layout uebergeben.
		this.setLayout(null);
		// Der Text wird hinzugefuegt
		this.fehler.setText(text);
		this.add(this.fehler);
		// Das Fenster wird Sichtbar gemacht.
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
