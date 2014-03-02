package de.szut.dqi12.informatik.extrempong.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;
import javax.swing.Timer;

import de.szut.dqi12.informatik.extrempong.main.Controller;
import de.szut.dqi12.informatik.extrempong.settings.Colors;
import de.szut.dqi12.informatik.extrempong.settings.Position;

public class Ball extends JComponent implements ActionListener {

	private Controller controller = Controller.getInstance();
	private Position position = new Position();
	private Colors color = new Colors();
	private int speed = 1;

	private Timer tm = new Timer(speed, this);
	
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

	public Ball(Field field) {
		frameSize();
		position.setHeight(50);
		position.setWidth(50);
		position.setX(0.0);
		position.setY(0.0);

	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Ellipse2D.Double ellipse1 = new Ellipse2D.Double(position.getX(),
				position.getY(), position.getWidth(), position.getHeight());
		g2.fill(ellipse1);

		tm.start();
	}

	public void richtungAendern() {

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
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
		repaint();

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
		windowWidth = (int) controller.getField().getJFrame().getContentPane()
				.getSize().getWidth();
		windowHeight = (int) controller.getField().getJFrame().getContentPane()
				.getSize().getHeight();
	}

}