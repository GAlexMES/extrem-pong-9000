package de.szut.dqi12.extremepong.menu;

import de.szut.dqi12.extremepong.PongMainRender;

public class Controller {

	private Spieler[] spieler = new Spieler[4];
	private static Controller instance = null;
	private View view;

	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}

	public void SpielEnde(int winner) {
		this.view.setPunkte(winner);
		view.setVisible(true);
	}
	
	public Spieler[] getSpieler(){
		return spieler;
	}

	public void startGame(View view) {
		if (this.checkInput(view)) {
			this.returnValues();
			view.setVisible(false);

			//Pong (lwjgl)
			new PongMainRender();
		} else {
			Fehlermeldung.getInstance().move();
			Fehlermeldung.getInstance().setVisible(true);
		}
	}

	private boolean checkInput(View view) {
		this.view = view;
		for (int i = 0; i < view.getTastenFelder().length; i++) {
			if (view.getTastenFelder()[i].getText().length() != 1) {
				return false;
			}

		}

		for (int i = 0; i < view.getSpielerFelder().length; i++) {
			if (view.getSpielerFelder()[i].getText().equals("")
					|| view.getSpielerFelder()[i].getText().equals(null)) {
				return false;
			}

		}
		if (!view.getPufalse().isSelected() && !view.getPutrue().isSelected()) {
			return false;
		}
		return true;
	}

	private void returnValues() {
		for (int i = 0; i < 4; i++) {
			this.spieler[i] = new Spieler();
			this.spieler[i].setName(view.getSpielerFelder()[i].getText());
			this.spieler[i].setTaste1(view.getTastenFelder()[i * 2].getText()
					.toCharArray()[0]);
			this.spieler[i].setTaste2(view.getTastenFelder()[i * 2 + 1]
					.getText().toCharArray()[0]);

		}
	}
}
