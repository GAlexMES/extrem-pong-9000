package de.szut.dqi12.informatik.extremepong.graphics;

import java.awt.Color;

import javax.swing.JComponent;

import de.szut.dqi12.informatik.extremepong.settings.Direction;
import de.szut.dqi12.informatik.extremepong.settings.KeyController;
import de.szut.dqi12.informatik.extremepong.settings.Position;

public class Player extends JComponent {
	
	private static final long serialVersionUID = 4693508921747774837L;

	public static final int DEPTH = 35;
	
	private Position position;
	private String name;
	private KeyController keys;
	private Color color;
	private Direction dir;
	
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
	
	public Player(Color c, Direction direction, KeyController keys){
		this.color = c;
		position = new Position();
		this.keys = keys;
		this.dir = direction;
		
		position.setHeight(Player.DEPTH);
		position.setWidth(150);
		
		switch(dir){
		case LEFT:
			position.setX(35);
			position.setY(500-(int)(position.getWidth()/2));
			break;
		case RIGHT:
			position.setX(910);
			position.setY(500-(int)(position.getWidth()/2));
			break;
		case UP:
			position.setX(500-(int)(position.getWidth()/2));
			position.setY(35);
			break;
		case DOWN:
			position.setX(500-(int)(position.getWidth()/2));
			position.setY(910);
			System.out.println(position.getX());
			break;
		}
	}
	
	public Direction getDirection(){
		return this.dir;
	}
	
	public void move(char c){
		int y = this.position.getY();
		int x = this.position.getX();
		
		if(keys.getLeftKey() == c){
			
			switch(dir){
			case RIGHT:
			case LEFT:
				if(y >= 100){
					this.position.setY(y-4);
				}
				break;
			case DOWN:
			case UP:
				if(x >= 100){
					this.position.setX(x-4);
				}
				break;
			}
		}else if(keys.getRightKey() == c){
			
			switch(dir){
			case RIGHT:
			case LEFT:
				if(y <= 900){
					this.position.setY(y+4);
				}
				break;
			case DOWN:
			case UP:
				if(x <= 900){
					this.position.setX(x+4);
				}
				break;
			}
		}
	}
}
