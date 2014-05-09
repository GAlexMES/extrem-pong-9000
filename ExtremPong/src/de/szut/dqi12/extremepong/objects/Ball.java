package de.szut.dqi12.extremepong.objects;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;

import org.lwjgl.opengl.GL11;
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
	
	public boolean intersectsPowerup(Powerup p) {
		this.recalcHitbox();		
		return  hitbox.intersects(p.hitbox);
	}

	public boolean intersectsPlayer(Player p) {
		this.recalcHitbox();

		int tempLastPlayerHit = this.lastPlayerHit;

		if (nextHit) {
			if (hitbox.intersects(p.hitbox)) {
				// lastPlayerHit gibt an welcher Spieler als letztes getroffen wurde
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

		// Es werden immer 5 frames gewartet oder wenn ein anderer
		// Spieler als der letzte getroffen wird, bevor die Richtung
		// gewechselt wird.
		if (lastHits >= 5 || tempLastPlayerHit != lastPlayerHit) {
			// nexHit zeigt ob der nächte "hit" auch die Richtung ändert
			// oder ob der Ball sich noch im Spieler befindet
			nextHit = true;
		}

		lastHits++;
		// Wenn die Beiden sich berühren oder übereinander liegen
		// wird true zurückgegeben
		return hitbox.intersects(p.hitbox);
	}

	public Bounds getBounds() {
		return this.bounds;
	}
	
	public void setBounds(Bounds b){
		this.bounds=b;
	}

	public void render() {
		// Wenn Powerups an sind hat der Ball eine graue Farbe
		if(View.getInstance().getPutrue().isSelected()){
			GL11.glColor3f(0.4f,0.5f,0.5f);
		}
		
		// Hier wird gesagt ich möchte ein Quadrat zeichnen und dann
		// alle Punkte die zu diesem benötigt werden glEnd() "beendet" das zeichnen
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
		
		// Die Farbe des "Zeichenstiftes" wird zurückgesetzt
		GL11.glColor3f(1f, 1f, 1f);
	}

	public void recalcHitbox() {
		// Diese Funktion setzt die Hitbox neu auf die aktuelle Position
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

		//Je nach Spieler ändert sich dementsprechend die Richtung
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

		// Wenn die Geschwindigkeit des Balls noch kleiner als 5 Pixel ist und Powerups an sind
		// Dann wird der Ball schneller gemacht
		if (velocityx <= 5 && velocityy <= 5 && View.getInstance().getPutrue().isSelected()) {
			this.velocityx += (Math.random() * vxOffset);
			this.velocityy += (Math.random() * vyOffset);
		}
	}

	public void move() {
		// Der Ball bewegt sich in die jeweilige Richtung
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
