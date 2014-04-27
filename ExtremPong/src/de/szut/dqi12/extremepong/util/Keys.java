package de.szut.dqi12.extremepong.util;

public class Keys {
	
	private char leftKey;
	private char rightKey;
	
	public Keys(char leftKey, char rightKey){
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
