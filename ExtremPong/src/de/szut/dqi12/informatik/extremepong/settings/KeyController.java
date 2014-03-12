package de.szut.dqi12.informatik.extremepong.settings;

public class KeyController {
	
	private char leftKey;
	private char rightKey;
	
	public KeyController(char leftKey, char rightKey){
		this.leftKey = leftKey;
		this.rightKey = rightKey;
	}
	
	public char getLeftKey() {
		return leftKey;
	}
	public void setLeftKey(char leftKey) {
		this.leftKey = leftKey;
	}
	public char getRightKey() {
		return rightKey;
	}
	public void setRightKey(char rightKey) {
		this.rightKey = rightKey;
	}
}
