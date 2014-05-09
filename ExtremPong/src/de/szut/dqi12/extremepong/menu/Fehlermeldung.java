package de.szut.dqi12.extremepong.menu;

import javax.swing.JFrame;
import javax.swing.JLabel;

import de.szut.dqi12.extremepong.util.Bounds;

public class Fehlermeldung extends JFrame {

	private static final long serialVersionUID = 7836464055107097280L;
	private JLabel fehler = new JLabel();
	private Bounds bounds;
	
	public Fehlermeldung(String text, Bounds bounds) {
		this.bounds = bounds;
		// Die Position und groesse des angezeigten Texts wird festgelegt
		this.fehler.setBounds(bounds.getWidth()/2-75, bounds.getHeight()/2-200, 150, 400);
		// Die Position und der Ramen der Oberflaeche wird festgelegt
		this.setBounds(bounds.getX()-bounds.getWidth()/2, bounds.getY()-bounds.getHeight()/2, this.bounds.getWidth(), this.bounds.getHeight());
		// Der Oberflaeche wird kein Layout uebergeben.
		this.setLayout(null);
		// Der Text wird hinzugefuegt
		this.fehler.setText(text);
		this.add(this.fehler);
		this.pack();
		this.setVisible(true);
	}

	public void move() {
		this.setBounds(bounds.getX()-bounds.getWidth()/2, bounds.getY()-bounds.getHeight()/2, this.bounds.getWidth(), this.bounds.getHeight());
	}
	
	public void remove(){
		this.setVisible(false);
	}
}
