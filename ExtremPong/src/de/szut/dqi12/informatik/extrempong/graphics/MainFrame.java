package de.szut.dqi12.informatik.extrempong.graphics;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	private static MainFrame instance = null;
	
	public static MainFrame getInstance(){
		if(instance == null){
			instance = new MainFrame();
		}
		return instance;
		
	}
	
	public MainFrame(){
		setSize(1000+16, 1000+38);
		setTitle("Extrem Pong 9000");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setContentPane(new Field());
	}
	
	public static void main(String[] args) {
		MainFrame.getInstance().getContentPane().add(new Ball());
		MainFrame.getInstance().getContentPane().add(new Ball());
		MainFrame.getInstance().getContentPane().add(new Player(435.0, 30.0));
	}
}
