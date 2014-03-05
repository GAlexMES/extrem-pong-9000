package de.szut.dqi12.informatik.extrempong.graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 7240759665414068037L;
	private BufferedImage img = null;
	private static MainFrame instance = null;
	private JPanel gamePanel=new JPanel();
	
	public static MainFrame getInstance(){
		if(instance == null){
			instance = new MainFrame();
		}
		return instance;
	}
	
	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
		repaint();
		
	}
	
	public MainFrame(){
		instance = this;
		
		setSize(1000+16, 1000+38);
		setTitle("Extrem Pong 9000");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setContentPane(gamePanel);
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 16, 38, 1000, 1000, new ImageObserver() {
			
			@Override
			public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3,
					int arg4, int arg5) {
				// TODO Auto-generated method stub
				return true;
			}
		});
	
	}
}
