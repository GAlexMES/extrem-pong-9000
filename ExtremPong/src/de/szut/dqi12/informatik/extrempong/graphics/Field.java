package de.szut.dqi12.informatik.extrempong.graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

import de.szut.dqi12.informatik.extrempong.settings.Position;

public class Field{

	private Position position = new Position();
	private JFrame window;

	public void setWidth(int width) {
		position.setWidth(width);
	}

	public int getWidth() {
		return position.getWidth();
	}

	public void setHeight(int height) {
		position.setHeight(height);
	}

	public int getHeight() {
		return position.getHeight();
	}

	public void generateJFrame() {
		window = new JFrame();
		window.setSize(position.getWidth(), position.getHeight());
		window.setTitle("Extrem Pong 9000");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public void addDrawable(JComponent drawable){
		window.add(drawable);
	}
	
	public JFrame getJFrame(){
		return window;
	}
}
