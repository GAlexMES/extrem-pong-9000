package de.szut.informatik.extrempong.menue;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;

public class Picture extends JComponent {

	private static final long serialVersionUID = -8209997026268424362L;
	private Image image;
    private int x, y;
    
    public void drawImage(Image image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if(image != null) g.drawImage(image, x, y, this);
    }
}
