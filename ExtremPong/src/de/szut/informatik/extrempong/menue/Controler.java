package de.szut.informatik.extrempong.menue;

public class Controler {

	private Spieler[] spieler = new Spieler[4];
	private static Controler instance = null;
	private View view;

	public static Controler getInstance() {
		if (instance == null) {
			instance = new Controler();
		}
		return instance;
	}

	public void SpielEnde(int winner) {
		this.view.setPunkte(winner);
		view.setVisible(true);
	}

	public void startGame(View view) {
		if (this.checkInput(view)) {
			this.returnValues();
			view.setVisible(false);
		} else {
			Fehlermeldung fehlerView = Fehlermeldung.getInstance();
			fehlerView.setVisible(true);
		}
	}

	private boolean checkInput(View view) {
		this.view = view;
		for (int i = 0; i < view.getTastenFelder().length; i++) {
			if (view.getTastenFelder()[i].getText().length() != 1) {
				return false;
			}// noch auf gleichheit pruefen

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
			this.spieler[i].setTaste1(view.getTastenFelder()[i * 2 + 1]
					.getText().toCharArray()[0]);

		}

		if (view.getPufalse().isSelected()) {
			boolean powerups = false;
		} else {
			boolean powerups = true;
		}
		// TILLSPROGRAMM.STARTEN(this.spieler, powerups);
	}

}
