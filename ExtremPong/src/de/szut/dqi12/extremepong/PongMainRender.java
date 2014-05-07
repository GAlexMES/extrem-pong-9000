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
import de.szut.dqi12.extremepong.objects.Powerup;
import de.szut.dqi12.extremepong.util.Direction;

/**
 * 
 * @author dqi12schlechtweg
 * 
 */
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
	public ArrayList<Player> playersOut = new ArrayList<Player>();
	public ArrayList<Powerup> powerups = new ArrayList<Powerup>();
	public ArrayList<Powerup> activePowerups = new ArrayList<Powerup>();
	private ArrayList<Powerup> removeablePowerups = new ArrayList<Powerup>();

	// Singleton (nur eine instance)
	private static PongMainRender instance = null;

	private boolean nextGame = false;
	private int ticks = 0;
	private int newBall = 7000;
	private int slomotion = 7000;
	private int position;

	// Main
	public static void main(String[] args) {
		View.getInstance();
	}

	public static PongMainRender getInstance() {
		if (instance == null) {
			instance = new PongMainRender();
		}
		return instance;
	}

	public void renderPong() {
		powerups.clear();
		activePowerups.clear();		
		ticks = 0;
		// Display Erstellung
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle(TITLE);
			Display.create();
			if (DEBUG)
				System.out.println("[+] Display created.");
		} catch (LWJGLException e) {
			if (DEBUG)
				System.out.println("[-] Display couldn't be created!");
			Display.destroy();
			System.exit(1);
		}

		// OpenGL Initialization Code
		glMatrixMode(GL11.GL_PROJECTION);
		glLoadIdentity();
		// Ortho gibt an wie das Koordinatensystem aufgebaut ist,
		// in unserem Fall ist TOP = 0 und BOTTOM = HEIGHT und LEFT = 0 und
		// RIGHT = WIDTH
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

		// Render Schleife, solange das Display nicht geschlossen werden soll
		while (!Display.isCloseRequested()) {

			// Ball Render
			for (Ball b : balls) {
				removeablePowerups.clear();
				b.render();
				b.move();

				for (Player p : players) {
					if (b.intersectsPlayer(p)) {
						b.changeDir(p.direction);
						if(p.isInGame()){
							ticks=ticks+1;
							if(ticks%2==0){								
								powerups.add(new Powerup());
								}
							
							for(Powerup power : activePowerups){
								if(!power.alreadyAlive(ticks)){
									removeablePowerups.add(power);
								}
							}
							
						}
					}
				}
						
				for(int i=0; i<powerups.size();i++){					
					if(b.intersectsPowerup(powerups.get(i))){
						boolean activated = false;
						while (!activated){			
						
						int randomInt = powerups.get(i).randInt(0, 2);
						switch(randomInt){
							case 0: powerups.get(i).biggerBall();
									activated = true;
									break;	
							case 1: newBall = activePowerups.size();
									activated = true;
									break;
							case 2: switch(powerups.get(i).randInt(0,3)){
									case 0: slomotion = position = activePowerups.size();
											activated = true;
											break;
									default: activated = false;
											break;
									}
									break;								
							}
						}
						
						powerups.get(i).setStartTick(ticks);
						activePowerups.add(powerups.get(i));
						break;
						
					}
				}
				
				for(Powerup power : activePowerups){
					int position=0;
					boolean remove = false;
					for(int i=0; i<powerups.size();i++){
						if(power.equals(powerups.get(i))){
							position=i;
							remove = true;
						}
					}
					if(remove){
						remove=false;
						powerups.remove(position);
					}
				}
				

				// Wenn der Ball eine Kante trifft, wird der jewailige Player
				// aus dem Game gekickt.
				if (b.getBounds().getX() + b.getBounds().getWidth() > WIDTH) {
					if(DEBUG) System.out.println("[+] Right Player Out!");
					b.changeDir(Direction.RIGHT);
					players.get(1).setInGame(false);
				} else if (b.getBounds().getY() - b.getBounds().getHeight() < 0) {
					if(DEBUG) System.out.println("[+] Upper Player Out!");
					b.changeDir(Direction.UP);
					players.get(0).setInGame(false);
				} else if (b.getBounds().getX() - b.getBounds().getWidth() < 0) {
					if(DEBUG) System.out.println("[+] Left Player Out!");
					b.changeDir(Direction.LEFT);
					players.get(3).setInGame(false);
				} else if (b.getBounds().getY() + b.getBounds().getHeight() > HEIGHT) {
					if(DEBUG) System.out.println("[+] Bottom Player Out!");
					b.changeDir(Direction.DOWN);
					players.get(2).setInGame(false);
				}
			}
			
			for(Powerup power : removeablePowerups){
				power.destroy();
				int position = 0;
				boolean remove = false;
				for(int i=0; i<activePowerups.size();i++){
					if(power.equals(activePowerups.get(i))){
						position=i;
						remove = true;
					}
				}
				if(remove){
					remove = false;
					activePowerups.remove(position);
					if(DEBUG)System.out.println("[!] Removed powerup out of active powerups!");
				}
			}
			
			if(newBall<=activePowerups.size()){
				activePowerups.get(newBall).newBall();
				newBall=7000;
			}
			
			if(slomotion<=activePowerups.size()){
				activePowerups.get(slomotion).slowmotion();
				slomotion = 7000;
			}
			
			for(Powerup p : powerups){
				p.render();
			}
			
			

			// Player Render
			for (Player p : players) {

				try {
					p.render();
				} catch (RuntimeException e) {
					if (DEBUG)
						System.out
								.println("[-] RuntimeException - Player can't be rendered!");
				}

				if (p.isInGame) {
					int keyleft = Keyboard.getKeyIndex(Character.toString(p
							.getKeys().getLeftKey()));
					int keyright = Keyboard.getKeyIndex(Character.toString(p
							.getKeys().getRightKey()));

					try {
						if (Keyboard.isKeyDown(keyleft)) {
							p.move(p.getKeys().getLeftKey());
						}
						if (Keyboard.isKeyDown(keyright)) {
							p.move(p.getKeys().getRightKey());
						}
					} catch (IllegalStateException e) {
						if (DEBUG)
							System.out
									.println("[-] IllegalStateException - Keyboard broken!");
					}
				}

				if (!p.isInGame) {
					if (!playersOut.contains(p)) {
						playersOut.add(p);
					}
				}
			}

			if (playersOut.size() > 2) {
				View.getInstance().setVisible(true);
				nextGame = true;
				for (Player p : players) {
					if (!playersOut.contains(p)) {

						String name = "Niemand";

						switch (p.direction) {
						case UP:
							name = "Oberer Spieler";
							break;
						case RIGHT:
							name = "Rechter Spieler";
							break;
						case DOWN:
							name = "Unterer Spieler";
							break;
						case LEFT:
							name = "Linker Spieler";
							break;
						}
						View.getInstance().setWinnerlabel(name);
					}
				}
				Display.destroy();
				break;
			}
			try {
				Display.update();
				Display.sync(60);
				glClear(GL_COLOR_BUFFER_BIT);
			} catch (IllegalStateException e) {
				if (DEBUG)
					System.out
							.println("[-] IllegalStateException - no display!");
			}
		}
		if (!nextGame) {
			Display.destroy();
			View.getInstance().selbstZerstoerungsKnopf();
		} else {
			nextGame = false;
			playersOut.clear();
			players.clear();
			balls.clear();
		}
	}

	public void newBall(int size) {
		if (balls.size() <= 5) {
			balls.add(new Ball(WIDTH / 2, HEIGHT / 2, size, size));
		}
	}
}
