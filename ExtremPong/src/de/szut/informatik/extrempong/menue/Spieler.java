package de.szut.informatik.extrempong.menue;

public class Spieler {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getTaste1() {
		return taste1;
	}

	public void setTaste1(char taste1) {
		this.taste1 = taste1;
	}

	public char getTaste2() {
		return taste2;
	}

	public void setTaste2(char taste2) {
		this.taste2 = taste2;
	}

	private char taste1;
	private char taste2;

}
