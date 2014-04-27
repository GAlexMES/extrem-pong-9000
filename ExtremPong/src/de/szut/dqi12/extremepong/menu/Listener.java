package de.szut.dqi12.extremepong.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener implements ActionListener {

	View view;

	public Listener(View view) {
		this.view = view;
		Controller.getInstance();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Controller.getInstance().startGame(view);

	}

}
