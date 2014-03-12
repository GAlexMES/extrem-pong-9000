package de.szut.dqi12.informatik.extremepong.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.szut.dqi12.informatik.extremepong.graphics.Field;
import de.szut.dqi12.informatik.extremepong.graphics.Player;

public class PlayerKeyListener implements KeyListener {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyChar());
		for(Player player: Field.getInstance().getPlayers()){
			if(player.getKeys().getLeftKey() == e.getKeyChar()){
				player.move(e.getKeyChar());
			}else if(player.getKeys().getRightKey() == e.getKeyChar()){
				player.move(e.getKeyChar());
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
