package de.szut.dqi12.informatik.extrempong.graphics;

import java.awt.BorderLayout;

public class Main {

	public static void main(String[] args) {
		Field f = new Field();
		f.addBall(new Ball());
		f.addBall(new Ball());
		f.addPlayer(new Player(435.0, 555.0));
		
		Thread t = new Thread(f);
		MainFrame.getInstance();
		t.start();
	}
}
