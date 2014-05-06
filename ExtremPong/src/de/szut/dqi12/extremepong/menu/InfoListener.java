package de.szut.dqi12.extremepong.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.szut.dqi12.extremepong.util.Bounds;
import de.szut.dqi12.extremepong.util.Screen;

public class InfoListener implements ActionListener {

	public InfoListener() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Fehlermeldung fehler = new Fehlermeldung("<html><bold><center>" +
				"Extreme Pong 9000<br>" +
				"No info available<br>" +
				"---------------------------------" +
				"<br>Credits:<br>" +
				"Lead Developer:<br>" +
				"Till Schlechtweg<br>" +
				"Robin Bley<br>" +
				"Kevin Neumeyer<br>" +
				"Alexander Brennecke<br>" +
				"<br>" +
				"Lead Art Design:<br>" +
				"Till Schlechtweg<br>" +
				"<br>" +
				"Marketing:<br>" +
				"Alexander Brennecke<br>" +
				"<br>" +
				"GUI Design:<br>" +
				"Robin Bley<br>" +
				"<br>" +
				"\"Ich mach alles\" <br>(also nix):<br>" +
				"Kevin Neumeyer<br>" +
				"</center></bold></html>",
				 new Bounds(Screen.WIDTH/2, Screen.HEIGHT/2, 450, 400));
		fehler.move();
	}
}
