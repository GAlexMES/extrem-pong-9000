package de.szut.dqi12.extremepong.util;

public class Bounds {
	private int x;
	private int y;
	private int height;
	private int width;

	public Bounds(int x, int y, int height, int width){
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}
	
	public Bounds(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
