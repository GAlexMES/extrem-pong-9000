package de.szut.dqi12.informatik.extrempong.graphics;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Field implements Runnable {
	
	public ArrayList<Ball> balls;
	public ArrayList<Player> players;
	
	private BufferedImage frame =new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
	
	public Field(){
		balls = new ArrayList<Ball>();
		players = new ArrayList<Player>();
	}
	
	@Override
	public void run(){
		while(true){
			Graphics2D g2 = (Graphics2D) frame.getGraphics();
			for(Ball ball: balls){
				Ellipse2D.Double ballell = new Ellipse2D.Double(ball.getPosition().getX(),ball.getPosition().getY(),
						ball.getPosition().getWidth(), ball.getPosition().getHeight());
				g2.fill(ballell);
			}
			
			for(Player player: players){
				Rectangle2D.Double rec = new Rectangle2D.Double(player.getPosition().getX(), player.getPosition().getY(), 
						player.getPosition().getHeight(), player.getPosition().getWidth());
				g2.fill(rec);
			}
			MainFrame.getInstance().setImg(frame);
		}
	}
	
	public void addBall(Ball ball){
		balls.add(ball);
	}
	
	public void addPlayer(Player player){
		players.add(player);
	}
}
