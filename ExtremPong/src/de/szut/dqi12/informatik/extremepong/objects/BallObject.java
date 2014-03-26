package de.szut.dqi12.informatik.extremepong.objects;

import java.awt.Color;
import java.awt.Graphics;

import de.szut.dqi12.informatik.extremepong.graphics.Screen;
import de.szut.dqi12.informatik.extremepong.settings.Direction;
import de.szut.dqi12.informatik.extremepong.settings.Position;
import de.szut.dqi12.informatik.extremepong.settings.Size;

public class BallObject extends AbstractObject {
	
	private int velocityY = 1;
	private int velocityX = 2;
	
	private boolean verticalDirection = true;
	private boolean horizontalDirection = true;
	
	public static final int CHANGE_X = 0;
	public static final int CHANGE_Y = 1;
	
	public BallObject(Position position, Direction direction, Color color, String name, Size size){
		super(position, direction, color, name, size);
		
		position.setX(Screen.getWidth()/2);
		position.setY(Screen.getHeight()/2);
	}
	
	public void move(){
		if(horizontalDirection){
			position.setY(position.getY() + this.velocityY);
			if (position.getY() >= (Screen.getHeight() - size.getHeight())){
				horizontalDirection = false;
			} else {
				position.setY(position.getY() - this.velocityY);
				if (position.getY() <= 0) {
					horizontalDirection = true;
				}
			}

			if (verticalDirection) {
				position.setX(position.getX() + this.velocityX);
				if (position.getX() >= (Screen.getWidth() - size.getWidth())) {
					verticalDirection = false;
				}
			} else {
				position.setX(position.getX() - velocityX);
				if (position.getX() <= 0) {
					verticalDirection = true;
				}
			}
		}
	}
	
	public void changeDirection(int change){
		if(change == CHANGE_X){
			horizontalDirection = !horizontalDirection;
		}else if(change == CHANGE_Y){
			verticalDirection = !verticalDirection;
		}
	}

	@Override
	public Graphics draw(Graphics g2) {
		this.move();
		g2.setColor(this.color);
		g2.fillOval(this.position.getX(), this.position
				.getY(), this.size.getWidth(), this.size.getHeight());
		return g2;
	}
}
