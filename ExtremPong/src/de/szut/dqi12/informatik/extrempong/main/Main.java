package de.szut.dqi12.informatik.extrempong.main;

import javax.swing.JFrame;

import de.szut.dqi12.informatik.extrempong.graphics.Ball;
import de.szut.dqi12.informatik.extrempong.graphics.Field;

public class Main {

	public static void main(String[] args){		
		Controller controller = Controller.getInstance();
		controller.startGame();
	}
}
