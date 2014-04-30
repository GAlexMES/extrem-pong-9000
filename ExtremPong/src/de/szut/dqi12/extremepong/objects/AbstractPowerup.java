package de.szut.dqi12.extremepong.objects;

import org.lwjgl.util.Rectangle;

import de.szut.dqi12.extremepong.util.Bounds;

public abstract class AbstractPowerup implements Powerup {

	public Bounds bounds;
	public Rectangle hitbox;
	
	public AbstractPowerup(int x, int y, int width, int height){
		bounds = new Bounds(x,y,width,height);
		recalcHitbox();
	}
	
	public void recalcHitbox(){
		hitbox.setBounds(this.bounds.getX() - this.bounds.getWidth(),
				this.bounds.getY() - this.bounds.getHeight(),
				this.bounds.getWidth() * 2, this.bounds.getHeight() * 2);
	}
}
