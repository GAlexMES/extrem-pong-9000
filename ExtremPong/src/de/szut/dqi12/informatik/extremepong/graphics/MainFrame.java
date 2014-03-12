package de.szut.dqi12.informatik.extremepong.graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.szut.dqi12.informatik.extremepong.listener.PlayerKeyListener;

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
	
		setSize(1000, 1000);
		setTitle("Extreme Pong 9000");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setContentPane(gamePanel);
		setResizable(false);
	}
	
	@Override
	public void paint(Graphics g) {
		int height = (int)(MainFrame.getInstance().getContentPane().getSize().getHeight());
		int width = (int)(MainFrame.getInstance().getContentPane().getSize().getWidth());
		int heightOffset = MainFrame.getInstance().getHeight();
		int widthOffset = MainFrame.getInstance().getWidth();
		
		g.drawImage(img, widthOffset-width, heightOffset-height, 1000, 1000, new ImageObserver() {
			
			@Override
			public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3,
					int arg4, int arg5) {
				// TODO Auto-generated method stub
				return true;
			}
		});
	
	}
}
