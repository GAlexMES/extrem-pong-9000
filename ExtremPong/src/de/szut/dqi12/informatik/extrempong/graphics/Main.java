package de.szut.dqi12.informatik.extrempong.graphics;

import java.awt.BorderLayout;

public class Main {

	public static void main(String[] args) {
		
		MainFrame.getInstance().getContentPane().add(new Ball());
		MainFrame.getInstance().getContentPane().add(new Ball());
		MainFrame.getInstance().getContentPane().add(new Player(435.0, 30.0));
		MainFrame.getInstance().getContentPane().add(new Player(435.0, 555.0));
	}
}
