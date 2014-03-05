package de.szut.dqi12.informatik.extrempong.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Field implements Runnable {
	
	public ArrayList<Ball> balls;
	public ArrayList<Player> players;
	
	private BufferedImage frame = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
	
	
	public Field(){
		balls = new ArrayList<Ball>();
		players = new ArrayList<Player>();
	}
	
	public void addBall(Ball b){
		balls.add(b);
	}
	
	public void addPlayer(Player p){
		players.add(p);
	}
	
	@Override
	public void run(){
		while(true){
			Graphics g2= (Graphics) frame.getGraphics();
			g2.setColor(Color.WHITE);
			g2.fillRect(0, 0, 1000, 1000);
			for(Ball ball: balls){
				g2.setColor(ball.getColor());
				ball.move();
				g2.fillOval(ball.getPosition().getX(),ball.getPosition().getY(),
						ball.getPosition().getWidth(), ball.getPosition().getHeight());
			}
			
			for(Player player: players){
				g2.setColor(player.getColor());
				g2.fillRect(player.getPosition().getX(), player.getPosition().getY(), 
						player.getPosition().getHeight(), player.getPosition().getWidth());
			}
			
			//Warte Xms und drehe secondFrame um
			try{
				Thread.sleep(20);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			//entscheided welches frame gerendert wird.
			MainFrame.getInstance().setImg(frame);
			
		}
	}
	
	
}
