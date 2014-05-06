package de.szut.dqi12.extremepong.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener implements ActionListener {

	public Listener() {
		// Ein Object von View wird uebernommen und in view geschrieben.
		Controller.getInstance();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Das Spiel wird gestartet.
		Controller.getInstance().startGame(View.getInstance());

	}

}
