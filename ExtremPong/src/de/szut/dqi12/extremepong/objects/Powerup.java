package de.szut.dqi12.extremepong.objects;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Rectangle;

import de.szut.dqi12.extremepong.PongMainRender;
import de.szut.dqi12.extremepong.menu.View;
import de.szut.dqi12.extremepong.util.Bounds;

public class Powerup {

	private int xPosition;
	private int yPosition;
	private int typeOfPowerup;
	private int startTick;
	private int liveTicks;
	private ArrayList<Bounds> ballsBounds = new ArrayList<Bounds>();
	private double red;
	private double green;
	private double blue;
	private int positionOfExtraBall;
	protected Rectangle hitbox;
	protected Bounds bounds;

	public Powerup() {
		xPosition = randInt(100, 500);
		yPosition = randInt(100, 500);
		liveTicks = randInt(1, 5);
		bounds = new Bounds(xPosition, yPosition, 50, 50);
		red = Math.random();
		blue  = Math.random();
		green = Math.random();
		hitbox = new Rectangle();
		
		this.recalcHitbox();
	}

	public int randInt(int min, int max) {
		
		return new Random().nextInt((max - min) + 1) + min;
	}

	public void render() {
		if (View.getInstance().getPutrue().isSelected()) {
			GL11.glColor3d(red, green, blue);
		}

		glBegin(GL_QUADS);
		glVertex2i(bounds.getX() - bounds.getWidth(),
				bounds.getY() - bounds.getHeight());
		glVertex2i(bounds.getX() - bounds.getWidth(),
				bounds.getY() + bounds.getHeight());
		glVertex2i(bounds.getX() + bounds.getWidth(),
				bounds.getY() + bounds.getHeight());
		glVertex2i(bounds.getX() + bounds.getWidth(),
				bounds.getY() - bounds.getHeight());
		glEnd();

		GL11.glColor3f(1f, 1f, 1f);
		


	}
	
	public void slowmotion(){
		typeOfPowerup = 2;
		for(Ball b : PongMainRender.getInstance().balls){
			b.setVelocityX(1);
			b.setVelocityY(1);
		}
	}
	
	public void newBall(){
		typeOfPowerup = 1;
		if(PongMainRender.DEBUG) System.out.println("[+] New Ball!");
		positionOfExtraBall=PongMainRender.getInstance().balls.size()-1;
		PongMainRender.getInstance().newBall(PongMainRender.getInstance().balls.get(0).getBounds().getWidth());
	}
	
	public void removeBall(){
		if(PongMainRender.DEBUG) System.out.println("[!] Old Ball destroyed!");
		PongMainRender.getInstance().balls.remove(positionOfExtraBall);
	}

	public void biggerBall() {
		typeOfPowerup = 0;
		if(PongMainRender.DEBUG) System.out.println("[+] Bigger Ball!");
		for (Ball b : PongMainRender.getInstance().balls) {
			Bounds ballBounds = b.getBounds();
			ballsBounds.add(ballBounds);
			b.setBounds(new Bounds(ballBounds.getX(), ballBounds.getY(),
					ballBounds.getWidth() * 2, ballBounds.getHeight() * 2));

		}

	}

	private void destroyBiggerBall() {
		if(PongMainRender.DEBUG) System.out.println("[!] Tiny Ball!");
		for (int i = 0; i < PongMainRender.getInstance().balls.size(); i++) {
			PongMainRender.getInstance().balls.get(i).setBounds(
					new Bounds(
							PongMainRender.getInstance().balls.get(i).getBounds().getX(),
							PongMainRender.getInstance().balls.get(i).getBounds().getY(),
							PongMainRender.getInstance().balls.get(i).getBounds().getWidth() / 2,
							PongMainRender.getInstance().balls.get(i).getBounds().getHeight() / 2));
			}
		}
	
	public void destroy(){
		switch(typeOfPowerup){
		case 0: destroyBiggerBall();
				break;			
		case 1:
				removeBall();
				break;
		case 2: break;
		}
	}
	

	public boolean alreadyAlive(int ticks) {
		if(PongMainRender.DEBUG)System.out.println("[!] TypeOfPowerup:"+ typeOfPowerup + " Ticks:"+ticks+" Start:"+startTick+" Leben:"+liveTicks);
		if (ticks - startTick >= liveTicks) {
			return false;
		} else {
			return true;
		}
	}

	public void recalcHitbox() {
		hitbox.setBounds(this.bounds.getX() - this.bounds.getWidth(),
				this.bounds.getY() - this.bounds.getHeight(),
				this.bounds.getWidth() * 2, this.bounds.getHeight() * 2);
	}

	public void setStartTick(int tick) {
		this.startTick = tick;
	}
}
