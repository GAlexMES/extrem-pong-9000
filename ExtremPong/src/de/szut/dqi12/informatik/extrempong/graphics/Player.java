package de.szut.dqi12.informatik.extrempong.graphics;

import java.awt.Color;

import javax.swing.JComponent;

import de.szut.dqi12.informatik.extrempong.settings.KeyController;
import de.szut.dqi12.informatik.extrempong.settings.Position;

public class Player extends JComponent {
	
	private static final long serialVersionUID = 4693508921747774837L;

	public static final int DEPTH = 35;
	
	private Position position;
	private String name;
	private KeyController keys;
	private Color color;
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
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Player(int x, int y, Color c){
		this.color = c;
		position = new Position();
		position.setX(x);
		position.setY(y);
		position.setHeight(Player.DEPTH);
		position.setWidth(150);
	}
	
	public void move(String direction){
		
	}
	
	public boolean isHit(){
		return false;
	}
}
