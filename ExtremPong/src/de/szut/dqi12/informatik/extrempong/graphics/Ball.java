package de.szut.dqi12.informatik.extrempong.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;
import javax.swing.Timer;

import de.szut.dqi12.informatik.extrempong.settings.Colors;
import de.szut.dqi12.informatik.extrempong.settings.Position;

public class Ball {

	private Position position = new Position();
	private Colors color = new Colors();
	
	private Double x = 0.0;
	private Double y = 0.0;
	private Double yelx = 1.0;
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
			y = y + yelx;
			if (y >= (windowHeight - position.getHeight())) {
				horizontalDirection = false;
			}
		} else {
			y = y - yelx;
			if (y <= 0) {
				horizontalDirection = true;
			}
		}

		if (verticalDirection) {
			x = x + velx;
			if (x >= (windowWidth - position.getWidth())) {
				verticalDirection = false;
			}
		} else {
			x = x - velx;
			if (x <= 0) {
				verticalDirection = true;
			}
		}
		position.setX(x);
		position.setY(y);
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