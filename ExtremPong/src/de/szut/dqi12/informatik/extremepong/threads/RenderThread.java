package de.szut.dqi12.informatik.extremepong.threads;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import de.szut.dqi12.informatik.extremepong.graphics.MainFrame;
import de.szut.dqi12.informatik.extremepong.graphics.Screen;
import de.szut.dqi12.informatik.extremepong.listener.PlayerKeyListener;
import de.szut.dqi12.informatik.extremepong.objects.BallObject;
import de.szut.dqi12.informatik.extremepong.objects.AbstractObject;
import de.szut.dqi12.informatik.extremepong.objects.PlayerObject;

public class RenderThread implements Runnable {

	private ArrayList<BallObject> balls;
	private ArrayList<AbstractObject> objects;
	private ArrayList<PlayerObject> player; 

	private static RenderThread instance = null;
	private int FPS = 60;

	private BufferedImage frame = new BufferedImage(1000, 1000,
			BufferedImage.TYPE_INT_ARGB);

	public static RenderThread getInstance() {
		if (instance == null) {
			instance = new RenderThread();
		}

		return instance;
	}

	public RenderThread() {
		MainFrame.getInstance().addKeyListener(new PlayerKeyListener());

		balls = new ArrayList<BallObject>();
		objects = new ArrayList<AbstractObject>();
		player = new ArrayList<PlayerObject>();
	}

	public void addBall(BallObject b) {
		balls.add(b);
	}

	public void addObject(AbstractObject ob) {
		objects.add(ob);
	}

	public ArrayList<AbstractObject> getObjects() {
		return this.objects;
	}
	
	public void addPlayer(PlayerObject player){
		this.player.add(player);
	}
	
	public ArrayList<PlayerObject> getPlayers(){
		return this.player;
	}

	@Override
	public void run() {
		while (true) {
			Graphics g2 = (Graphics) frame.getGraphics();
			g2.setColor(Color.WHITE);
			g2.fillRect(0, 0, Screen.getWidth(), Screen.getHeight());

			for (AbstractObject ob : objects) {
				ob.draw(g2);
			}

			MainFrame.getInstance().setImg(frame);

			try {
				Thread.sleep(1000/FPS);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
