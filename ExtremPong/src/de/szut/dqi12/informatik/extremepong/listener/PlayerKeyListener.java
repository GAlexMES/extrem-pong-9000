package de.szut.dqi12.informatik.extremepong.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class PlayerKeyListener implements KeyListener {
	public static ArrayList<Character> buttonsPressed = new ArrayList<Character>();

	@Override
	public void keyPressed(KeyEvent e) {
		if(!buttonsPressed.contains((Object)e.getKeyChar())){
			buttonsPressed.add((Character)e.getKeyChar());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		buttonsPressed.remove((Object)e.getKeyChar());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
