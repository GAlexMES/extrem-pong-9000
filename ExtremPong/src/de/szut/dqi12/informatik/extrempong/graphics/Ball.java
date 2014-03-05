package de.szut.dqi12.informatik.extrempong.graphics;

import java.awt.Color;

import de.szut.dqi12.informatik.extrempong.settings.Position;

public class Ball {

	private Position position = new Position();
	private Color color;
	
	private int vely = 1;
	private int velx = 2;
	private int windowWidth;
	private int windowHeight;

	private boolean verticalDirection = true;
	private boolean horizontalDirection = true;
	
	public static final int CHANGE_X = 0;
	public static final int CHANGE_Y = 1;

	public Ball(Color c) {
		frameSize();
		position.setHeight(50);
		position.setWidth(50);
		position.setX(100);
		position.setY(100);
		color=c;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public void move() {
		frameSize();
		if (horizontalDirection) {
			position.setY(position.getY()+vely);
			if (position.getY() >= (windowHeight - position.getHeight())) {
				horizontalDirection = false;
			}
		} else {
			position.setY(position.getY()-vely);
			if (position.getY() <= 0) {
				horizontalDirection = true;
			}
		}

		if (verticalDirection) {
			position.setX(position.getX()+velx);
			if (position.getX() >= (windowWidth - position.getWidth())) {
				verticalDirection = false;
			}
		} else {
			position.setX(position.getX()-velx);
			if (position.getX() <= 0) {
				verticalDirection = true;
			}
		}
	}
	
	public void changeDirection(int cood){
		if(cood == CHANGE_X){
			horizontalDirection = !horizontalDirection;
		}
		else if (cood==CHANGE_Y){
			verticalDirection = !verticalDirection;
		}
		
	}

	private void frameSize() {
		windowWidth = (int) MainFrame.getInstance().getContentPane()
				.getSize().getWidth();
		windowHeight = (int) MainFrame.getInstance().getContentPane()
				.getSize().getHeight();
	}

}