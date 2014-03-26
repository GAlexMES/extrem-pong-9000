package de.szut.dqi12.informatik.extremepong.graphics;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Screen {
	public Dimension getDimension() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}

	public static int getHeight() {
		return Toolkit.getDefaultToolkit().getScreenSize().height;
	}

	public static int getWidth() {
		return Toolkit.getDefaultToolkit().getScreenSize().width;
	}
}
