package de.szut.dqi12.informatik.extrempong.graphics;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Field extends JPanel {
	
	public ArrayList<Ball> balls;
	public ArrayList<Player> players;
	
	public Field(){
		balls = new ArrayList<Ball>();
		players = new ArrayList<Player>();
		setVisible(true);
		setSize(1000, 1000);
		setLayout(new BorderLayout());
	}
	
	public void addBall(Ball ball){
		balls.add(ball);
	}
	
	public void addPlayer(Player player){
		players.add(player);
	}
	
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
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
		
	}
	
	public void addDrawable(JComponent c){
		this.add(c);
	}
}
