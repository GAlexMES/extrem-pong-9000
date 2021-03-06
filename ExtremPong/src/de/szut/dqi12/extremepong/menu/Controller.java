package de.szut.dqi12.extremepong.menu;

import de.szut.dqi12.extremepong.PongMainRender;
import de.szut.dqi12.extremepong.util.Bounds;
import de.szut.dqi12.extremepong.util.Screen;

public class Controller {

	private Spieler[] spieler = new Spieler[4];
	private static Controller instance = null;
	private View view;
	private Fehlermeldung fehler = null;

	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}

	private Controller(){
		
	}
	
	public void SpielEnde(int winner) {
		// der View wird den Gewinner uebergeben und sie Sichtbar gemacht.
		this.view.setPunkte(winner);
		view.setVisible(true);
	}

	public Spieler[] getSpieler() {
		// Getter fuer Spieler.
		return spieler;
	}

	public void startGame(View view) {
		// wennn das Menue richtig benutzt wurde wird ein Objekt von
		// PongMainRender erzeugt und die View unsichtbar gemacht.
		if (this.checkInput(view)) {
			this.returnValues();
			view.setVisible(false);

			// Pong (lwjgl)
			if(PongMainRender.DEBUG) System.out.println("[+] Spiel gestartet!");
			PongMainRender.getInstance().renderPong();
		} else {
			// Falls der/die User was falsch eingeben haben, wird eine Fehlermeldung ausgeben
			this.fehler = new Fehlermeldung("Falsche Eingabe!!!", new Bounds(Screen.WIDTH/2, Screen.HEIGHT/2, 400, 400));
			this.fehler.move();
		}
	}

	private boolean checkInput(View view) {
		// Die eingaben des Useres werden auf Vollstendigkeit ueberprueft.
		// Ist die Eingabe richtig, wird ein true zurueckgegeben, sonst ein
		// false.
		this.view = view;
		for (int i = 0; i < view.getTastenFelder().length; i++) {
			if (view.getTastenFelder()[i].getText().length() != 1) {
				return false;
			}
			for(int b=0; b< view.getTastenFelder().length;b++){
				if(b!=i){
					if(view.getTastenFelder()[i].getText().equals(view.getTastenFelder()[b].getText())){
						return false;
					}
				}
			}
		}

		if (!view.getPufalse().isSelected() && !view.getPutrue().isSelected()) {
			return false;
		}
		return true;
	}

	private void returnValues() {
		// Dem Memberarray von Spielern werden Werte uebegeben.
		for (int i = 0; i < 4; i++) {
			this.spieler[i] = new Spieler();
			this.spieler[i].setTaste1(view.getTastenFelder()[i * 2].getText()
					.toCharArray()[0]);
			this.spieler[i].setTaste2(view.getTastenFelder()[i * 2 + 1]
					.getText().toCharArray()[0]);

		}
	}
}
