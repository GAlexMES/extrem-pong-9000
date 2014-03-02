package de.szut.dqi12.informatik.extrempong.main;

import de.szut.dqi12.informatik.extrempong.graphics.Ball;
import de.szut.dqi12.informatik.extrempong.graphics.Field;

public class Controller {

	private Field field;
	private static Controller instance = null;
	
	public static Controller getInstance(){
		if(instance ==null){
			instance = new Controller();
			return instance;
		}
		else{
			return instance;
		}
	}
	
	
	public void startGame(){
		field = new Field();
		field.setHeight(700);
		field.setWidth(700);
		field.generateJFrame();
		Ball ball = new Ball(field);
		field.addDrawable(ball);
	}
	
	public Field getField(){
		return field;
	}
}
