package de.szut.dqi12.extremepong.objects;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.Rectangle;

import de.szut.dqi12.extremepong.util.Bounds;
import de.szut.dqi12.extremepong.util.Direction;
import de.szut.dqi12.extremepong.util.Keys;

/**
 * 
 * @author dqi12schlechtweg
 * 
 */
public class Player {

	public Keys keys;
	public Direction direction;
	protected Bounds bounds;
	public Rectangle hitbox;
	public boolean isInGame = true;

	public void setInGame(boolean bool) {
		this.isInGame = bool;
	}

	public boolean isInGame(boolean bool) {
		return this.isInGame;
	}

	public Player(char keyleft, char keyright, Direction dir) {
		this.direction = dir;
		this.keys = new Keys(keyleft, keyright);
		hitbox = new Rectangle();

		switch (this.direction) {
		case LEFT:
			bounds = new Bounds(10, Display.getHeight() / 2);
			hitbox.setBounds(this.bounds.getX() - this.bounds.getWidth(),
					this.bounds.getY() - this.bounds.getHeight(), this.bounds
							.getWidth() * 2, this.bounds.getHeight() * 2);
			break;
		case RIGHT:
			bounds = new Bounds(Display.getWidth() - 10,
					Display.getHeight() / 2);
			hitbox.setBounds(this.bounds.getX() - this.bounds.getWidth(),
					this.bounds.getY() - this.bounds.getHeight(), this.bounds
							.getWidth() * 2, this.bounds.getHeight() * 2);
			break;
		case UP:
			bounds = new Bounds(Display.getWidth() / 2, 10);
			hitbox.setBounds(this.bounds.getX() - this.bounds.getHeight(),
					this.bounds.getY() - this.bounds.getWidth(), this.bounds
							.getHeight() * 2, this.bounds.getWidth() * 2);
			break;
		case DOWN:
			bounds = new Bounds(Display.getWidth() / 2,
					Display.getHeight() - 10);
			hitbox.setBounds(this.bounds.getX() - this.bounds.getHeight(),
					this.bounds.getY() - this.bounds.getWidth(), this.bounds
							.getHeight() * 2, this.bounds.getWidth() * 2);
			break;
		}

		bounds.setHeight(40);
		bounds.setWidth(2);
	}

	public void move(char c) {
		// Checks if the Player is still inGame
		if (isInGame) {
			int y = this.bounds.getY();
			int x = this.bounds.getX();

			// Wenn der Player nach Links will
			if (keys.getLeftKey() == c) {
				switch (direction) {
				case RIGHT:
				case LEFT:
					if (y > 9 + this.bounds.getHeight()) {
						this.bounds.setY(y - 4);
					}
					// Hitbox wird neu berechnet
					hitbox.setBounds(this.bounds.getX()
							- this.bounds.getWidth(), this.bounds.getY()
							- this.bounds.getHeight(),
							this.bounds.getWidth() * 2,
							this.bounds.getHeight() * 2);
					break;
				case DOWN:
				case UP:
					if (x > 9 + this.bounds.getHeight()) {
						this.bounds.setX(x - 4);
					}
					// Hitbox wird neu berechnet
					hitbox.setBounds(this.bounds.getX()
							- this.bounds.getHeight(), this.bounds.getY()
							- this.bounds.getWidth(),
							this.bounds.getHeight() * 2,
							this.bounds.getWidth() * 2);
					break;

				}
				// Wenn der Player nach Rechts will
			} else if (keys.getRightKey() == c) {

				switch (direction) {
				case RIGHT:
				case LEFT:
					if (y < Display.getHeight() - (9 + this.bounds.getHeight())) {
						this.bounds.setY(y + 4);
					}
					// Hitbox wird neu berechnet
					hitbox.setBounds(this.bounds.getX()
							- this.bounds.getWidth(), this.bounds.getY()
							- this.bounds.getHeight(),
							this.bounds.getWidth() * 2,
							this.bounds.getHeight() * 2);
					break;
				case DOWN:
				case UP:
					if (x < Display.getWidth() - (9 + this.bounds.getHeight())) {
						this.bounds.setX(x + 4);
					}
					// Hitbox wird neu berechnet
					hitbox.setBounds(this.bounds.getX()
							- this.bounds.getHeight(), this.bounds.getY()
							- this.bounds.getWidth(),
							this.bounds.getHeight() * 2,
							this.bounds.getWidth() * 2);
					break;
				}
			}
		}
	}

	public void render() {

		// Spieler noch im Spiel?
		if (isInGame) {
			switch (this.direction) {
			case LEFT:
				hitbox
						.setBounds(this.bounds.getX() - this.bounds.getWidth(),
								this.bounds.getY() - this.bounds.getHeight(),
								this.bounds.getWidth() * 2, this.bounds
										.getHeight() * 2);
				break;
			case RIGHT:
				hitbox
						.setBounds(this.bounds.getX() - this.bounds.getWidth(),
								this.bounds.getY() - this.bounds.getHeight(),
								this.bounds.getWidth() * 2, this.bounds
										.getHeight() * 2);
				break;
			case UP:
				hitbox
						.setBounds(
								this.bounds.getX() - this.bounds.getHeight(),
								this.bounds.getY() - this.bounds.getWidth(),
								this.bounds.getHeight() * 2, this.bounds
										.getWidth() * 2);
				break;
			case DOWN:
				hitbox
						.setBounds(
								this.bounds.getX() - this.bounds.getHeight(),
								this.bounds.getY() - this.bounds.getWidth(),
								this.bounds.getHeight() * 2, this.bounds
										.getWidth() * 2);
				break;
			}

			int x, y, height, width;
			x = this.bounds.getX();
			y = this.bounds.getY();
			height = this.bounds.getHeight();
			width = this.bounds.getWidth();

			// Fuer UNTEN und OBEN wird die Hoehe und Breite vertauscht
			switch (this.direction) {
			case LEFT:
			case RIGHT:
				glBegin(GL_QUADS);
				glVertex2i(x - width, y - height);
				glVertex2i(x - width, y + height);
				glVertex2i(x + width, y + height);
				glVertex2i(x + width, y - height);
				glEnd();

				break;
			case UP:
			case DOWN:
				glBegin(GL_QUADS);
				glVertex2i(x - height, y - width);
				glVertex2i(x - height, y + width);
				glVertex2i(x + height, y + width);
				glVertex2i(x + height, y - width);
				glEnd();

				break;
			}
		} else {
			int height, width, x, y;
			x = this.bounds.getX();
			y = this.bounds.getY();
			this.bounds.setHeight(800);
			this.bounds.setWidth(2);
			height = this.bounds.getHeight();
			width = this.bounds.getWidth();

			// Hier wird für jede Seite einzeln der gesammte Balken gerendert
			switch (this.direction) {
			case LEFT:
				glBegin(GL_QUADS);
				glVertex2i(x - 8 - width, y - height);
				glVertex2i(x - 8 - width, y + height);
				glVertex2i(x - 8 + width, y + height);
				glVertex2i(x - 8 + width, y - height);
				glEnd();
				hitbox.setBounds(0, 0, 2, 600);
				break;
			case RIGHT:
				glBegin(GL_QUADS);
				glVertex2i(x + 8 - width, y - height);
				glVertex2i(x + 8 - width, y + height);
				glVertex2i(x + 8 + width, y + height);
				glVertex2i(x + 8 + width, y - height);
				glEnd();
				hitbox.setBounds(600, 0, 2, 600);
				break;
			case UP:
				glBegin(GL_QUADS);
				glVertex2i(x - height, y - 8 - width);
				glVertex2i(x - height, y - 8 + width);
				glVertex2i(x + height, y - 8 + width);
				glVertex2i(x + height, y - 8 - width);
				glEnd();
				hitbox.setBounds(0, 0, 600, 2);
				break;
			case DOWN:
				glBegin(GL_QUADS);
				glVertex2i(x - height, y + 8 - width);
				glVertex2i(x - height, y + 8 + width);
				glVertex2i(x + height, y + 8 + width);
				glVertex2i(x + height, y + 8 - width);
				glEnd();
				hitbox.setBounds(0, 600, 600, 2);
				break;
			}
		}
	}

	public Keys getKeys() {
		return this.keys;
	}
}
