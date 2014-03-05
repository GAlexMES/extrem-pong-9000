package de.szut.dqi12.informatik.extrempong.graphics;

import java.awt.Color;


public class Main {

	public static void main(String[] args) {
		Field f = new Field();
		f.addBall(new Ball(Color.BLUE));
		f.addBall(new Ball(Color.BLACK));
		f.addPlayer(new Player(435, 555, Color.GREEN));
		
		Thread t = new Thread(f);
		MainFrame.getInstance();
		t.start();
	}
}
