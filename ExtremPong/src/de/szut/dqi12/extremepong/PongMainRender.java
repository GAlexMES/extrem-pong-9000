package de.szut.dqi12.extremepong;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import de.szut.dqi12.extremepong.menu.Controller;
import de.szut.dqi12.extremepong.menu.View;
import de.szut.dqi12.extremepong.objects.Ball;
import de.szut.dqi12.extremepong.objects.Player;
import de.szut.dqi12.extremepong.util.Direction;

public class PongMainRender {
	// Konstanten
	public static final String TITLE = "Extreme Pong";
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	
	// Debug (mit DebugMeldungen oder ohne)
	public static final boolean DEBUG = true;

	// Objektlisten
	public ArrayList<Ball> balls = new ArrayList<Ball>();
	public ArrayList<Player> players = new ArrayList<Player>();

	// Singleton (nur eine instance)
	private static PongMainRender instance = null;

	private int out = 0;
	
	// Main
	public static void main(String[] args) {
		View.getInstance();
	}

	public static PongMainRender getInstance() {
		if (instance == null) {
			instance = new PongMainRender();
			instance.renderShit();
		}
		return instance;
	}

	public void renderShit() {
		// Display Erstellung
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle(TITLE);
			Display.create();
			if(DEBUG)System.out.println("[+] Display created.");
		} catch (LWJGLException e) {
			if(DEBUG)System.out.println("[-] Display couldn't be created!");
			Display.destroy();
			System.exit(1);
		}

		// OpenGL Initialization Code
		glMatrixMode(GL11.GL_PROJECTION);
		glLoadIdentity();
		// Ortho gibt an wie das Koordinatensystem aufgebaut ist,
		// in unserem Fall ist TOP = 0 und BOTTOM = HEIGHT und LEFT = 0 und
		// RIGHT = WIDTH
		// 0
		// 0 WIDTH
		// HEIGHT
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		glMatrixMode(GL11.GL_MODELVIEW);

		// Der Ball der von Anfang an da ist
		balls.add(new Ball(WIDTH / 2, HEIGHT / 2, 4, 4));

		for (int i = 0; i <= 3; i++) {
			char keyleft = Controller.getInstance().getSpieler()[i].getTaste1();
			char keyright = Controller.getInstance().getSpieler()[i]
					.getTaste2();

			switch (i) {
			case 0:
				players.add(new Player(keyleft, keyright, Direction.UP));
				break;
			case 1:
				players.add(new Player(keyleft, keyright, Direction.RIGHT));
				break;
			case 2:
				players.add(new Player(keyleft, keyright, Direction.DOWN));
				break;
			case 3:
				players.add(new Player(keyleft, keyright, Direction.LEFT));
				break;
			}
		}

		boolean newBall = false;

		// Render Schleife, solange das Display nicht geschlossen werden soll
		while (!Display.isCloseRequested()) {

			// Ball Render
			for (Ball b : balls) {
				b.render();
				b.move();

				for (Player p : players) {
					if (b.intersects(p)) {
						b.changeDir(p.direction);
						if(DEBUG)System.out.println("[!] Ball changed direction.");
					}
				}


				// Wenn der Ball eine Kante trifft, wird der jewailige Player
				// aus dem Game gekickt.
				if (b.getBounds().getX() + b.getBounds().getWidth() > WIDTH) {
					b.changeDir(Direction.RIGHT);
					players.get(1).setIngame(false);
					out++;
				} else if (b.getBounds().getY() - b.getBounds().getHeight() < 0) {
					b.changeDir(Direction.UP);
					players.get(0).setIngame(false);
					out++;
				} else if (b.getBounds().getX() - b.getBounds().getWidth() < 0) {
					b.changeDir(Direction.LEFT);
					players.get(3).setIngame(false);
					out++;
				} else if (b.getBounds().getY() + b.getBounds().getHeight() > HEIGHT) {
					b.changeDir(Direction.DOWN);
					players.get(2).setIngame(false);
					out++;
				}
			}
			
			// Player Render
			for (Player p : players) {
				p.render();		
				if(out >= 3){
					Display.destroy();
					View.getInstance().setVisible(true);
				}
				int keyleft = Keyboard.getKeyIndex(Character.toString(p
						.getKeys().getLeftKey()));
				int keyright = Keyboard.getKeyIndex(Character.toString(p
						.getKeys().getRightKey()));

				if (Keyboard.isKeyDown(keyleft)) {
					p.move(p.getKeys().getLeftKey());
				}

				if (Keyboard.isKeyDown(keyright)) {
					p.move(p.getKeys().getRightKey());
				}
			}

			Display.update();
			Display.sync(60);
			glClear(GL_COLOR_BUFFER_BIT);
		}
		Display.destroy();
		View.getInstance().SelbstZerstoerungsKnopf();
	}

	public void newBall(int size) {
		if (balls.size() <= 5) {
			balls.add(new Ball(WIDTH / 2, HEIGHT / 2, size, size));
		}
	}
}
