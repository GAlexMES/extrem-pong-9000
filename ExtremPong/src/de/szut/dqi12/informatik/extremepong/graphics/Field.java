package de.szut.dqi12.informatik.extremepong.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import de.szut.dqi12.informatik.extremepong.listener.PlayerKeyListener;


public class Field implements Runnable {
	
	private ArrayList<Ball> balls;
	private ArrayList<Player> players;
	
	private static Field instance = null;
	
	private BufferedImage frame = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
	
	public static Field getInstance(){
		if(instance == null){
			instance = new Field();
		}
		
		return instance;
	}
	
	public Field(){
		MainFrame.getInstance().addKeyListener(new PlayerKeyListener());
		
		balls = new ArrayList<Ball>();
		players = new ArrayList<Player>();
	}
	
	public void addBall(Ball b){
		balls.add(b);
	}
	
	public void addPlayer(Player p){
		players.add(p);
	}
	
	public ArrayList<Player> getPlayers(){
		return this.players;
	}
	
	@Override
	public void run(){
		while(true){
			Graphics g2 = (Graphics) frame.getGraphics();
			g2.setColor(Color.WHITE);
			g2.fillRect(0, 0, 1000, 1000);
			for(Ball ball: balls){
				g2.setColor(ball.getColor());
				g2.fillOval(ball.getPosition().getX(),ball.getPosition().getY(),
						ball.getPosition().getWidth(), ball.getPosition().getHeight());
				//ball bewegt sich
				ball.move();
			}
			
			for(Player player: players){
				g2.setColor(player.getColor());
				switch(player.getDirection()){
				case LEFT:
				case RIGHT:
					g2.fillRect(player.getPosition().getX(), player.getPosition().getY(), 
							player.getPosition().getHeight(), player.getPosition().getWidth());
					break;
				case UP:
				case DOWN:
					g2.fillRect(player.getPosition().getX(), player.getPosition().getY(), 
							player.getPosition().getWidth(), player.getPosition().getHeight());
					break;
				}
				
				int x = player.getPosition().getX();
				int y = player.getPosition().getY();
				int h = player.getPosition().getHeight();
				int w = player.getPosition().getWidth();
				
				for(Ball ball: balls){
					int ballx = ball.getPosition().getX();
					int bally = ball.getPosition().getY();
					int ballh = ball.getPosition().getHeight();
					int ballw = ball.getPosition().getWidth();
					
					if(ballx == x + h || ballx == x - h 
							|| bally == y + h || bally == y-h){
						switch(player.getDirection()){
						case RIGHT:
						case LEFT:
							ball.changeDirection(Ball.CHANGE_Y);
							break;
						case DOWN:
						case UP:
							ball.changeDirection(Ball.CHANGE_X);
							break;
						}
					}
				}
			}
			
			MainFrame.getInstance().setImg(frame);
						
			//warte X ms
			try{
				Thread.sleep(5);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void movePlayer(Player player){
		for(Player play: players){
			if(play.equals(player)){
//				play.move();
			}
		}
	}
}
