package de.szut.dqi12.extremepong;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import org.lwjgl.input.*;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import de.szut.dqi12.extremepong.menu.Spieler;
import de.szut.dqi12.extremepong.menu.View;
import de.szut.dqi12.extremepong.objects.Ball;
import de.szut.dqi12.extremepong.menu.Controller;
import de.szut.dqi12.extremepong.objects.Player;
import de.szut.dqi12.extremepong.util.Direction;

public class PongMainRender {
	public static String TITLE = "Extreme Pong";
	public static ArrayList<Ball> balls = new ArrayList<Ball>();
	public static ArrayList<Player> players = new ArrayList<Player>();
	public static final int fieldWidth = 600;
	public static final int fieldHeight = 600;
	public static void main(String[] args) {
		View.getInstance();
	}

	public PongMainRender() {
		// Display Erstellung
		try {
			Display.setDisplayMode(new DisplayMode(fieldWidth, fieldHeight));
			Display.setTitle(TITLE);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}

		// OpenGL Initialization Code
		glMatrixMode(GL11.GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, fieldWidth, fieldHeight, 0, 1, -1);
		glMatrixMode(GL11.GL_MODELVIEW);

		balls.add(new Ball(fieldWidth/2, fieldHeight/2, 4, 4));

		int count = 0;
		for(Spieler spieler: Controller.getInstance().getSpieler()){
			
			char keyleft = spieler.getTaste1();
			char keyright = spieler.getTaste2();
			
			System.out.println(keyleft + " " + keyright);
			
			switch(count){
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
			
			
			count++;
		}
		
		// Render loop
		while (!Display.isCloseRequested()) {

			for (Ball b: balls) {
				b.render();
				b.move();
				
				for(Player p: players){
					if(b.intersects(p)){
						b.changeDir(p.direction);
					}
				}
				
				if(b.getBounds().getX()+b.getBounds().getWidth() > fieldWidth){
					b.changeDir(Direction.RIGHT);
					players.get(1).setIngame(false);
				}else if(b.getBounds().getY()-b.getBounds().getHeight() < 0){
					b.changeDir(Direction.UP);
					players.get(0).setIngame(false);
				}else if(b.getBounds().getX()-b.getBounds().getWidth() < 0){
					b.changeDir(Direction.LEFT);
					players.get(3).setIngame(false);
				}else if(b.getBounds().getY()+b.getBounds().getHeight() > fieldHeight){
					b.changeDir(Direction.DOWN);
					players.get(2).setIngame(false);
				}
			}
			
			for(Player p: players){
				p.render();
				int keyleft = Keyboard.getKeyIndex(Character.toString(p.getKeys().getLeftKey()));
				int keyright = Keyboard.getKeyIndex(Character.toString(p.getKeys().getRightKey()));
			
				if(Keyboard.isKeyDown(keyleft)){
					p.move(p.getKeys().getLeftKey());
				}

				if(Keyboard.isKeyDown(keyright)){
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
}
