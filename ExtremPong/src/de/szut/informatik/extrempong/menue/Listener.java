package de.szut.informatik.extrempong.menue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener implements ActionListener {

	View view;
	Controler controler;

	public Listener(View view) {
		this.view = view;
		controler = controler.getInstance();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		this.controler.startGame(view);

	}

}
