package de.szut.dqi12.informatik.extremepong.main;

import java.awt.Color;

import de.szut.dqi12.informatik.extremepong.graphics.Ball;
import de.szut.dqi12.informatik.extremepong.graphics.Field;
import de.szut.dqi12.informatik.extremepong.graphics.MainFrame;
import de.szut.dqi12.informatik.extremepong.graphics.Player;
import de.szut.dqi12.informatik.extremepong.listener.PlayerKeyListener;
import de.szut.dqi12.informatik.extremepong.settings.Direction;
import de.szut.dqi12.informatik.extremepong.settings.KeyController;


public class Main {

	public static void main(String[] args) {
		Field f = Field.getInstance();
		
		f.addBall(new Ball(Color.BLACK));
		
		f.addPlayer(new Player(Color.GREEN, Direction.UP, new KeyController('a', 'd')));
		f.addPlayer(new Player(Color.GREEN, Direction.DOWN, new KeyController('g', 'h')));
		f.addPlayer(new Player(Color.GREEN, Direction.LEFT, new KeyController('j', 'k')));
		f.addPlayer(new Player(Color.GREEN, Direction.RIGHT, new KeyController('l', 'm')));
		
		Thread t = new Thread(f);
		MainFrame.getInstance();
		t.start();
	}
}
