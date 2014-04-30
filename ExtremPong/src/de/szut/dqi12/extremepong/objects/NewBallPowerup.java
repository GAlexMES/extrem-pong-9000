package de.szut.dqi12.extremepong.objects;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;
import de.szut.dqi12.extremepong.PongMainRender;

public class NewBallPowerup extends AbstractPowerup {

	public NewBallPowerup(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void hit(Ball b) {
		if(hitbox.intersects(b.hitbox)){
			PongMainRender.getInstance().newBall((int)Math.random()*5);
		}
	}

	@Override
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



}
