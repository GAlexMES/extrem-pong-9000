package de.szut.dqi12.informatik.extremepong.objects;

import java.awt.Color;
import java.awt.Graphics;

import de.szut.dqi12.informatik.extremepong.settings.Direction;
import de.szut.dqi12.informatik.extremepong.settings.Position;
import de.szut.dqi12.informatik.extremepong.settings.Size;

public abstract class AbstractObject {
	public String name;
	public Position position;
	public Direction direction;
	public Color color;
	public Size size;
	
	public AbstractObject(){};
	
	public AbstractObject(Position position, Direction direction,  
			Color color, String name, Size size){
		this.position = position;
		this.direction = direction;
		this.name = name;
		this.color = color;
		this.size = size;
	}
	
	public boolean isHitBy(AbstractObject ob){
		
		int x1 = this.position.getX();
		int y1 = this.position.getY();
		int w1 = this.size.getWidth();
		int h1 = this.size.getHeight();
		
		int x2 = ob.position.getX();
		int y2 = ob.position.getY();
		int w2 = ob.size.getWidth();
		int h2 = ob.size.getHeight();
		
		if (x1 + w1 > x2)
            if (x1 < x2 + w2)
                if (y1 + h1 > y2)
                    if (y1 < y2 + h2)
                        return true;
		
		return false;
	}
	
	public abstract Graphics draw(Graphics g2);
}
