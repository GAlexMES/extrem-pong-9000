package de.szut.dqi12.informatik.extrempong.graphics;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Field extends JPanel {
	public Field(){
		setLayout(null);
		setSize(MainFrame.getInstance().getContentPane().getWidth(), MainFrame.getInstance().getContentPane().getHeight());
	}
	
	public void addDrawable(JComponent c){
		this.add(c);
	}
}
