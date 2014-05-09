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
	private boolean clearPowerups = false;
	private int beamBall = 7000;
	private Direction lastPlayer = null;
	
	// Main
	public static void main(String[] args) {
		View.getInstance();
	}

	//Singleton Pattern
	public static PongMainRender getInstance() {
		if (instance == null) {
			instance = new PongMainRender();
		}
		return instance;
	}
	
	//MainRenderer welche beim Start eines neuen SPiels ausgeführt wird

	public void renderPong() {
		// Powerup listen werden gecleart, damit keine Powerups aus 
		// vorherigen Spielen aktiv sind
		powerups.clear();
		activePowerups.clear();	
		// Anzahl der Ticks (Schlägerberührungen) wird auf 0 gesetzt
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

		// Startball wird definiert
		balls.add(new Ball(WIDTH / 2, HEIGHT / 2, 4, 4));
		
		// Vier Spieler werden mit ihren entsprechenden Spieltasten definiert
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
				//Abgelaufene Powerups werden entfernt
				removeablePowerups.clear();
				//Ball wird gerendert und bewegt
				b.render();
				b.move();
				
				//Berührung zwischen Ball und Spieler wird überprüft
				for (Player p : players) {
					if (b.intersectsPlayer(p)) {
						//Bei Berührung wird die Richtung des Balles geändert
						lastPlayer = p.getDirection();
						b.changeDir(p.direction);
						//Wenn der Spieler noch im Spiel ist wird die Anzahl der Ticks erhöht
						if(p.isInGame()){
							ticks=ticks+1;
							// Bei jeder zweiten Berührung wird ein Powerup erstellt
							if(ticks%2==0){								
								powerups.add(new Powerup());
								if(DEBUG)System.out.println("[+] new poweruph");
								}
							// Falls der Lebenszyklus einer Powerups abgelaufen ist
							// wird es in die Liste removeablePowerups hinzugefügt
							for(Powerup power : activePowerups){
								if(!power.alreadyAlive(ticks)){
									removeablePowerups.add(power);
								}
							}
							
						}
					}
				}
				// Überprüfung zwischen Ball und Spieler abgeschlossen
				
				// Überprüfung zwischen Ball und Powerup
				for(int i=0; i<powerups.size();i++){					
					if(b.intersectsPowerup(powerups.get(i))){
						//Bei Überschneidung wird per Zufallszahl ermittelt
						// Welche Eigenschaft das Powerup haben soll
						
						// Boolean für Verschachtelte switch/case
						boolean activated = false;
						
						//Solange keine Eigenschaft festgelegt wurde läuft die while weiter
						while (!activated){			
							// zufälliger Wert wird generiert
							int randomInt = powerups.get(i).randInt(0, 8);
							//Anhand des zufall Wertes wird eine Eigenschaft ermittelt
							switch (randomInt) {
							case 0:
								//Funktion zum Vergrößern des Balles wird aufgerufen
								powerups.get(i).biggerBall();
								powerups.get(i).setTypeOfPowerup(randomInt);
								activated = true;
								break;
							case 1:
								// In newBall wird die Position des aktuellen Powerups gespeichert
								// Da durch das Bälle Array iteriert wird kann an dieser Stelle kein Ball
								// hinzugefügt werden. Dies geschieht nach der Iteration.
								newBall = activePowerups.size();
								powerups.get(i).setTypeOfPowerup(randomInt);
								activated = true;
								break;
							case 2:
								 // Da diese Eigenschaft weniger oft auftreten soll
								 // wird ein zweiter zufalls Wert ermittelt
								
								switch (powerups.get(i).randInt(0, 3)) {
								case 0:
									// siehe Kommentar zu case 1.
									// Der Ball wird extrem verlangsamt und beschleunigt sich 
									// erst mit der Zeit wieder
									slomotion = activePowerups.size();
									powerups.get(i).setTypeOfPowerup(randomInt);
									activated = true;
									break;
								default:
									activated = false;
									break;
								}
								break;

							case 3:
								 // Da diese Eigenschaft weniger oft auftreten soll
								 // wird ein zweiter zufalls Wert ermittelt
								switch (powerups.get(i).randInt(0, 1)) {
								case 0:
									// Bei eintreten werden alle Powerups gelöscht, die bereits
									// gezeichnet aber noch nicht getroffen wurden
									// Dies kann jedoch auch nicht hier geschehen, da dann das Array,
									// durch welches aktuell iteriert wird bearbeitet werden müsste
									// Durch das setzen des clearPowerups Booleans wird dies später erledigt
									powerups.get(i).setTypeOfPowerup(randomInt);
									clearPowerups = true;
									activated = true;
									break;
								default:
									activated = false;
								}
								break;
							case 4:
								// Siehe Kommentar Case 1
								// Ball wird an eine andere Stelle "gebeamt" fliegt jedoch in die
								// selbe Richtung weiter
								powerups.get(i).setTypeOfPowerup(randomInt);
								activated = true;
								beamBall = activePowerups.size();
								;
								break;
							case 5:
								// Funktion, welche die Schläger der Spieler vergrößert
								// wird aufgerufen
								activated = true;
								powerups.get(i).setTypeOfPowerup(randomInt);
								powerups.get(i).biggerPlayers();
								break;
							case 6:
								//Funktion, welche die Schläger der Spieler verkleinert,
								// wird aufgerufen
								activated = true;
								powerups.get(i).setTypeOfPowerup(randomInt);
								powerups.get(i).smallerPlayers();
								break;
							case 7:
								//Funktion, welche den Ball vom Powerup wie von einem
								// Schläger abprallen lässt, wird aufgerufen
								activated = true;
								powerups.get(i).setTypeOfPowerup(randomInt);
								powerups.get(i).returnBall(b, lastPlayer);
								break;
							case 8:
								//Funktion, welche die Tasten der Spieler vertauscht, wird aufgerufen
								activated = true;
								powerups.get(i).setTypeOfPowerup(randomInt);
								powerups.get(i).switchControlling();
								break;
							}

						}
						
						// Bei dem Powerup wird der StartTick gesetzt, welcher für den Lebenszyklus benötigt wird
						powerups.get(i).setStartTick(ticks);
						//Powerup wird zu den activePowerups hinzu gefügt
						activePowerups.add(powerups.get(i));
						break;						
					}
				}
				
				// Es wird durch die Liste der activePowerups iteriert
				for(Powerup power : activePowerups){
					// Es wird überprüft ob sich ein Powerup in activePowerup
					// auch in powerups befindet
					// Falls dies so ist wird es aus powerups entfernt um nicht
					//mehr gerendert zu werden
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
			
			//Wenn der Wert der beamBall Variable kleiner ist als die Größe der activePowerups,
			// wurde er geändert und diese Funktion muss aufgerufen werden
			if(beamBall<=activePowerups.size()){
				if(DEBUG)System.out.println("[!] Beam Ball");
				//Funktion die den Powerup an eine beliebige Stelle beamt
				activePowerups.get(beamBall).beamBall();
				// beamBall wird auf 7000 gesetzt, damit die Funktion nicht dauerhaft aufgerufen wird
				beamBall=7000;				
			}
			
			// Wenn clearPowerups true ist wird es wieder auf false gesetzt
			// um einen Mehrfachaufruf zu vermeiden
			// Alle Array, die etwas mit Powerups zu tun haben, werden gecleart
			if(clearPowerups){
				clearPowerups = false;
				powerups.clear();
				activePowerups.clear();
				removeablePowerups.clear();				
			}
			
			// Wenn der Lebenszyklus eines Powerups abgelaufen ist gehört es zu den removablePowerups
			// Durch diese wird hier itteriert und die destroy() Funktion aufgerufen
			// Diese wird nicht bei jedem Powerup benötigt, die Überprüfung erfolgt in der Funktion selber
			
			for(Powerup power : removeablePowerups){
				power.destroy();
				int position = 0;
				boolean remove = false;
				
				// Hinzu kommt, dass an dieser Stelle das Powerup auch aus den activePowrups entfernt wird
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
			
			//Wenn der Wert der newBall Variable kleiner ist als die Größe der activePowerups,
			// wurde er geändert und diese Funktion muss aufgerufen werden
			if(newBall<=activePowerups.size()){
				//Die Entsprechende Funktion innerhalb der Powerup Klasse wird aufgerufen
				activePowerups.get(newBall).newBall();
				// newBall wird auf 7000 gesetzt, damit die Funktion nicht dauerhaft aufgerufen wird
				newBall=7000;
			}
			
			//Siehe überliegende If Abfrage
			if(slomotion<=activePowerups.size()){
				activePowerups.get(slomotion).slowmotion();
				slomotion = 7000;
			}
			
			//Alle Powerups werden gerendert
			// Dadurch werden berührte nicht mehr angezeigt und neue können hinzugefügt werden
			
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

				// Nur wenn der Spieler noch im Spiel ist wird eine Tasteneingabe verarbeitet
				// um Resourcen zu sparen
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
				
				// Wenn der Spieler rausgeflogen ist wird er der playersOut Liste hinzugefügt

				if (!p.isInGame) {
					if (!playersOut.contains(p)) {
						playersOut.add(p);
					}
				}
			}
			
			// Wenn die playersOut Liste mehr als zwei Spieler beinhaltet steht der Gewinner fest

			if (playersOut.size() > 2) {
				View.getInstance().setVisible(true);
				nextGame = true;
				//Das Menü wird neu generiert, dieses mal jedoch mit einem Gewinner Label
				// Welches den Gewinner der vergangenen Runde anzeigt
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
			
			//Nachdem alles neu gerendert wurde und alle Funktionen und Powerups ausgeführt worden sind
			// Wird das OpenGl Fenster geupdated
			try {
				Display.update();
				//Anzahl der Updates
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

	//Funktion welchen einen neuen Ball zur aktuellen Liste hinzu fügt.
	public void newBall(int size) {
		if (balls.size() <= 5) {
			balls.add(new Ball(WIDTH / 2, HEIGHT / 2, size, size));
		}
	}
}
