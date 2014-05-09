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
import de.szut.dqi12.extremepong.util.Direction;


//Ein Powerup Objekt wird immer dann generiert wenn ein neues Powerup erstellt wurde
public class Powerup {

	// Verschiedene Variablen für:
	
	//Position
	private int xPosition;
	private int yPosition;
	
	//Art des Powerups
	private int typeOfPowerup;
	
	//Den Lebenszyklus
	private int startTick;
	private int liveTicks;
	
	//eine ArrayList für die Größe und Position der Bälle
	private ArrayList<Bounds> ballsBounds = new ArrayList<Bounds>();
	
	// die Farben, in welchen das Powerup erscheint
	private double red;
	private double green;
	private double blue;
	
	// die Position eines weiteren Balles
	private int positionOfExtraBall;
	
	//Hitbox uns Bounds für das Powerups selber
	protected Rectangle hitbox;
	protected Bounds bounds;

	
	//Konstruktor erstellt das Powerup an einer beliebigen Stelle und fügt
	// die Farben und die Hitbox hinzu
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

	// Renderer für Powerups
	public void render() {
		
		//Zeichnet ein Quadrat in der vorher definierten Farbe
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
	
	// Methoden die Aufgerufen werden, wenn ein Powerup berührt/ausgeführt wird:
	
	
	//case 0: der Ball verdoppelt seine Größe
	public void biggerBall(){
		typeOfPowerup = 0;
		if(PongMainRender.DEBUG) System.out.println("[+] Bigger Ball!");
		// Es wird durch die Liste der Bälle iteriert und von jedem Ball die Größe angepasst
		for (Ball b : PongMainRender.getInstance().balls) {
			Bounds ballBounds = b.getBounds();
			ballsBounds.add(ballBounds);
			b.setBounds(new Bounds(ballBounds.getX(), ballBounds.getY(),
					ballBounds.getWidth() * 2, ballBounds.getHeight() * 2));

		}

	}
	
	//case 1: ein neuer Ball wird erzeugt
	public void newBall(){
		typeOfPowerup = 1;
		if(PongMainRender.DEBUG) System.out.println("[+] New Ball!");
		positionOfExtraBall=PongMainRender.getInstance().balls.size()-1;

		// ein neuer Ball wird der Ball Liste hinzugefügt durch eine in der PongMainRender verankerten Funktion
		PongMainRender.getInstance().newBall(PongMainRender.getInstance().balls.get(0).getBounds().getWidth());
	}
	
	//case 2: der Ball wird verlangsamt	
	public void slowmotion(){
		typeOfPowerup = 2;
		//Geschwindigkeit aller Bälls wird auf Standart gesetzt
		for(Ball b : PongMainRender.getInstance().balls){
			b.setVelocityX(1);
			b.setVelocityY(1);
		}
	}
	
	// case 4: der Ball wird an eine beliebige Stelle gebeamt
	public void beamBall(){
		// es wird durch alle Bälle durch iteriert und für jeden eine zufällige Position generiert
		// die Größe bleibt dabei gleich
		for (Ball b : PongMainRender.getInstance().balls){
			for(int i = 0; i<3;i++){
			b.setBounds(new Bounds(randInt(100,500),randInt(100,500),b.getBounds().getWidth(),b.getBounds().getHeight()));
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
	}
	
	//Case 5: und case 6: die Schläger werden größer oder kleiner
	// beide Funktionen werden in der destroy() Methode benutzt um sich gegenseitig aufzuheben
	
	//Schläger wird vergrößert
	public void biggerPlayers(){
		
		// es wird durch die Schläger iteriert und ihre Größe angepasst (verdoppelt)
		for(Player p: PongMainRender.getInstance().players){
			if(p.isInGame){
				p.setBounds(new Bounds(p.getBounds().getX(),p.getBounds().getY(),p.getBounds().getHeight()*2,p.getBounds().getWidth()));
			}
		}		
	}
	
	// Schläger wird verkleinert
	public void smallerPlayers(){
		
		//Es wird durch die Schläger iteriert und ihre Größe angepasst (halbiert)
		for(Player p: PongMainRender.getInstance().players){
			if(p.isInGame){
				p.setBounds(new Bounds(p.getBounds().getX(),p.getBounds().getY(),p.getBounds().getHeight()/2,p.getBounds().getWidth()));
			}
		}		
	}
	
	// case7: der Ball prallt vom Powerup ab
	public void returnBall(Ball b,Direction dir){
		
		// jenachdem welcher Spieler als letztes berührt wurde bestimmt die
		// Richtung in die der Ball dann fliegt
		switch(dir){
		case LEFT: b.changeDir(Direction.RIGHT);
					break;
		case RIGHT: b.changeDir(Direction.LEFT);
					break;
		case UP:	b.changeDir(Direction.DOWN);
					break;
		case DOWN:	b.changeDir(Direction.UP);
					break;
		}
		
	}
	
	// case8: vertauscht die Tasten der Spieler
	
	public void switchControlling(){
		//Es wird durch die Spieler iteriert und die Linke Taste mit der rechten getauscht
		for(Player p : PongMainRender.getInstance().players){
			p.toggleControll();
		}
	}
	
	// Ende der Aktion Methoden, welche bei berühren/aktivieren der Powerups aufgerufen werden
	
	// Destroy wird aufgerufen wenn der Lebenszyklus eines Powerups abgelaufen ist
	public void destroy(){
		//Anhand des gesetzten Types bestimmt die switch/case Anweisung
		// ob, und wenn ja, welche Funktion aufgerufen werden muss um die Wirkung des 
		// Powerups zu neutralisieren
		
		switch (typeOfPowerup) {
		case 0:
			destroyBiggerBall();
			break;
		case 1:
			removeBall();
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			smallerPlayers();
			break;
		case 6:
			biggerPlayers();
			break;
		case 7:
			break;
		case 8:
			break;
		}
	}
	
	// Wird aufgerufen um die Größe eines Balles wieder zu halbieren
	private void destroyBiggerBall() {
		if(PongMainRender.DEBUG) System.out.println("[!] Tiny Ball!");
		for (int i = 0; i < PongMainRender.getInstance().balls.size(); i++) {
			//Für jeden Ball wird ein neuer Bounds erstellt, welcher die Position des alten Balles
			// jedoch eine neue Größe bekommt
			PongMainRender.getInstance().balls.get(i).setBounds(
					new Bounds(
							PongMainRender.getInstance().balls.get(i).getBounds().getX(),
							PongMainRender.getInstance().balls.get(i).getBounds().getY(),
							PongMainRender.getInstance().balls.get(i).getBounds().getWidth() / 2,
							PongMainRender.getInstance().balls.get(i).getBounds().getHeight() / 2));
			}
		}
	
	
	// entfernt einen Ball aus der Liste der Bälle
	public void removeBall(){
		if(PongMainRender.DEBUG) System.out.println("[!] Old Ball destroyed!");
		PongMainRender.getInstance().balls.remove(positionOfExtraBall);
	}
	

	
	 // berechnet anhand des aktuellen tick, des startTicks und der Anzahl der Tick,
	// wie lange das Powerup leben soll, ob es noch aktiv bleiben soll oder deaktiviert werden soll
	public boolean alreadyAlive(int ticks) {
		if(PongMainRender.DEBUG)System.out.println("[!] TypeOfPowerup:"+ typeOfPowerup + " Ticks:"+ticks+" Start:"+startTick+" Leben:"+liveTicks);
		if (ticks - startTick >= liveTicks) {
			return false;
		} else {
			return true;
		}
	}

	
	// erzeugt eine Hitbox um das Powerup
	public void recalcHitbox() {
		hitbox.setBounds(this.bounds.getX() - this.bounds.getWidth(),
				this.bounds.getY() - this.bounds.getHeight(),
				this.bounds.getWidth() * 2, this.bounds.getHeight() * 2);
	}

	// setz den StartTick um den Lebenszyklus zu berechnen
	public void setStartTick(int tick) {
		this.startTick = tick;
	}
	
	//generiert einen zufälligen Int der zwischen den beiden parametern liegt	
	public int randInt(int min, int max) {		
		return new Random().nextInt((max - min) + 1) + min;
	}
	
	public void setTypeOfPowerup (int type){
		this.typeOfPowerup = type;
	}
}
