package de.szut.dqi12.extremepong.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener implements ActionListener {

	View view;

	public Listener(View view) {
		// Ein Object von View wird uebernommen und in view geschrieben.
		this.view = view;
		Controller.getInstance();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Das Spiel wird gestartet.
		Controller.getInstance().startGame(view);

	}

}
