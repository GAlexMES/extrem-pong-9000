package de.szut.dqi12.informatik.extremepong.main;

import de.szut.dqi12.informatik.extremepong.graphics.MainFrame;
import de.szut.dqi12.informatik.extremepong.threads.RenderThread;
import de.szut.informatik.extrempong.menue.View;


public class Main {

	public static void main(String[] args) {
//		RenderThread f = RenderThread.getInstance();
		
//		f.addBall(new BallObject(Color.BLACK));
//		
//		f.addPlayer(new PlayerObject(Color.GREEN, Direction.UP, new Keys('a', 'd')));
//		f.addPlayer(new PlayerObject(Color.GREEN, Direction.DOWN, new Keys('g', 'h'), null, null, null));
//		f.addPlayer(new PlayerObject(Color.GREEN, Direction.LEFT, new Keys('j', 'k')));
//		f.addPlayer(new PlayerObject(Color.GREEN, Direction.RIGHT, new Keys('l', 'm')));
//		
//		Thread t = new Thread(f);
//		MainFrame.getInstance();
		View.getInstance();
//		t.start();
	}
}
