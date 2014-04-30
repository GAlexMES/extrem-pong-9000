package de.szut.dqi12.extremepong.objects;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;

import org.lwjgl.util.Rectangle;

import de.szut.dqi12.extremepong.util.Bounds;
import de.szut.dqi12.extremepong.util.Direction;

public class Ball {

	protected Rectangle hitbox;
	protected Bounds bounds;
	protected boolean vx = true;
	protected boolean vy = true;
	protected int velocityx = 2;
	protected int velocityy = 3;
	protected int count = 0;
	protected int lastPlayerHit = 0;
	protected int vxOffset = 1;
	protected int vyOffset = 1;

	public Ball(int x, int y, int height, int width) {
		bounds = new Bounds(x, y, height, width);
		hitbox = new Rectangle();
		this.recalcHitbox();
	}

	public boolean intersects(Player p) {
		this.recalcHitbox();
		if(hitbox.intersects(p.hitbox)){
			switch(p.direction){
			case UP:
				lastPlayerHit = 1;
				break;
			case RIGHT:
				lastPlayerHit = 2;
				break;
			case DOWN:
				lastPlayerHit = 3;
				break;
			case LEFT:
				lastPlayerHit = 4;
				break;
			}
		}
		return hitbox.intersects(p.hitbox);
	}

	public Bounds getBounds() {
		return this.bounds;
	}

	public void render() {
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
	}
	
	public void recalcHitbox(){
		hitbox.setBounds(this.bounds.getX() - this.bounds.getWidth(),
				this.bounds.getY() - this.bounds.getHeight(),
				this.bounds.getWidth() * 2, this.bounds.getHeight() * 2);
	}

	public void setVelocityY(int v) {
		this.velocityy = v;
	}

	public void setVelocityX(int v) {
		this.velocityx = v;
	}

	public void setVY(boolean y) {
		this.vy = y;
	}

	public void setVX(boolean x) {
		this.vx = x;
	}

	public void changeDir(Direction dir) {
		switch (dir) {
		case UP:
			this.velocityx += (Math.random()*vxOffset);
			this.velocityy += (Math.random()*vyOffset);
			this.vy = true;
			this.vx = !this.vx;
			break;
		case DOWN:
			this.velocityx += (Math.random()*vxOffset);
			this.velocityy += (Math.random()*vyOffset);
			this.vy = false;
			this.vx = !this.vx;
			break;
		case LEFT:
			this.velocityx += (Math.random()*vxOffset);
			this.velocityy += (Math.random()*vyOffset);
			this.vx = true;
			this.vy = !this.vy;
			break;
		case RIGHT:
			this.velocityx += (Math.random()*vxOffset);
			this.velocityy += (Math.random()*vyOffset);
			this.vx = false;
			this.vy = !this.vy;
			break;
		}
	}

	public void move() {
		if (vx && vy) {
			bounds.setX(bounds.getX() + velocityx);
			bounds.setY(bounds.getY() + velocityy);
		} else if (vx && !vy) {
			bounds.setX(bounds.getX() + velocityx);
			bounds.setY(bounds.getY() - velocityy);
		} else if (!vx && vy) {
			bounds.setX(bounds.getX() - velocityx);
			bounds.setY(bounds.getY() + velocityy);
		} else {
			bounds.setX(bounds.getX() - velocityx);
			bounds.setY(bounds.getY() - velocityy);
		}
		this.recalcHitbox();
	}
}
