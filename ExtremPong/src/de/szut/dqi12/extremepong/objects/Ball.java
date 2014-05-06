package de.szut.dqi12.extremepong.objects;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.Rectangle;

import de.szut.dqi12.extremepong.PongMainRender;
import de.szut.dqi12.extremepong.menu.View;
import de.szut.dqi12.extremepong.util.Bounds;
import de.szut.dqi12.extremepong.util.Direction;

/**
 * 
 * @author dqi12schlechtweg
 * 
 */
public class Ball {

	protected Rectangle hitbox;
	protected Bounds bounds;
	protected boolean vx = true;
	protected boolean vy = true;
	protected int velocityx = 2;
	protected int velocityy = 3;
	protected int count = 0;
	protected int lastPlayerHit = 0;
	protected int vxOffset = 2;
	protected int vyOffset = 2;
	protected boolean nextHit = true;
	protected int lastHits = 0;

	public Ball(int x, int y, int height, int width) {
		bounds = new Bounds(x, y, height, width);
		hitbox = new Rectangle();
		this.recalcHitbox();
	}

	public boolean intersects(Player p) {
		this.recalcHitbox();

		int tempLastPlayerHit = this.lastPlayerHit;

		if (nextHit) {
			if (hitbox.intersects(p.hitbox)) {
				switch (p.direction) {
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
			nextHit = false;
		}

		if (lastHits >= 5 || tempLastPlayerHit != lastPlayerHit) {
			nextHit = true;
		}

		lastHits++;
		return hitbox.intersects(p.hitbox);
	}

	public Bounds getBounds() {
		return this.bounds;
	}

	public void render() {
		if(View.getInstance().getPutrue().isSelected()){
			GL11.glColor3f(0.4f,0.5f,0.5f);
		}
		glBegin(GL_QUADS);
		glVertex2i(bounds.getX() - bounds.getWidth(), bounds.getY()
				- bounds.getHeight());
		glVertex2i(bounds.getX() - bounds.getWidth(), bounds.getY()
				+ bounds.getHeight());
		glVertex2i(bounds.getX() + bounds.getWidth(), bounds.getY()
				+ bounds.getHeight());
		glVertex2i(bounds.getX() + bounds.getWidth(), bounds.getY()
				- bounds.getHeight());
		glEnd();
		GL11.glColor3f(0f, 0f, 0f);
	}

	public void recalcHitbox() {
		hitbox.setBounds(this.bounds.getX() - this.bounds.getWidth(),
				this.bounds.getY() - this.bounds.getHeight(), this.bounds
						.getWidth() * 2, this.bounds.getHeight() * 2);
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
		if (PongMainRender.DEBUG)
			System.out.println("[!] Ball changed direction.");

		switch (dir) {
		case UP:
			this.vy = true;
			break;
		case DOWN:
			this.vy = false;
			break;
		case LEFT:
			this.vx = true;
			break;
		case RIGHT:
			this.vx = false;
			break;
		}

		if (velocityx <= 5 && velocityy <= 5 && View.getInstance().getPutrue().isSelected()) {
			this.velocityx += (Math.random() * vxOffset);
			this.velocityy += (Math.random() * vyOffset);
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
