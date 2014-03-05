package de.szut.dqi12.informatik.extrempong.graphics;

import de.szut.dqi12.informatik.extrempong.settings.Colors;
import de.szut.dqi12.informatik.extrempong.settings.Position;

public class Ball {

	private Position position = new Position();
	private Colors color = new Colors();
	

	private Double vely = 1.0;
	private Double velx = 2.0;
	private int windowWidth;
	private int windowHeight;

	private boolean verticalDirection = true;
	private boolean horizontalDirection = true;
	
	public static final int CHANGE_X = 0;
	public static final int CHANGE_Y = 1;

	public Ball() {
		frameSize();
		position.setHeight(50);
		position.setWidth(50);
		position.setX(100.0);
		position.setY(100.0);

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