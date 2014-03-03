package de.szut.dqi12.informatik.extrempong.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

import de.szut.dqi12.informatik.extrempong.settings.Colors;
import de.szut.dqi12.informatik.extrempong.settings.KeyController;
import de.szut.dqi12.informatik.extrempong.settings.Position;

public class Player extends JComponent {
	
	public static final int DEPTH = 35;
	
	private Position position;
	private String name;
	private KeyController keys;
	private Colors color;
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public KeyController getKeys() {
		return keys;
	}
	public void setKeys(KeyController keys) {
		this.keys = keys;
	}
	public Colors getColor() {
		return color;
	}
	public void setColor(Colors color) {
		this.color = color;
	}
	
	public Player(Double x, Double y){
		
		position = new Position();
		position.setX(x);
		position.setY(y);
		position.setHeight(Player.DEPTH);
		position.setWidth(150);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D.Double rec = new Rectangle2D.Double(position.getX(), position.getY(), 
				position.getHeight(), position.getWidth());
		g2.fill(rec);
	}
	
	public void move(String direction){
		
	}
	
	public boolean isHit(){
		return false;
	}
}
