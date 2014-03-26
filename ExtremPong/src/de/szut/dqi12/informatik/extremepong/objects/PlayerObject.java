package de.szut.dqi12.informatik.extremepong.objects;

import java.awt.Color;
import java.awt.Graphics;

import de.szut.dqi12.informatik.extremepong.graphics.Screen;
import de.szut.dqi12.informatik.extremepong.settings.Direction;
import de.szut.dqi12.informatik.extremepong.settings.Keys;
import de.szut.dqi12.informatik.extremepong.settings.Position;
import de.szut.dqi12.informatik.extremepong.settings.Size;

public class PlayerObject extends AbstractObject {
	public Keys keys;

	public PlayerObject(Color c, Direction direction, Keys keys, String name,
			Position position, Size size) {
		super(position, direction, c, name, size);

		switch (direction) {
		case LEFT:
			position.setX(35);
			position.setY(Screen.getHeight() / 2 - (size.getHeight() / 2));
			break;
		case RIGHT:
			position.setX(Screen.getWidth() - 35);
			position.setY(Screen.getHeight() / 2 - (size.getHeight() / 2));
			break;
		case UP:
			position.setX(Screen.getWidth() / 2 - (size.getWidth() / 2));
			position.setY(35);
			break;
		case DOWN:
			position.setX(Screen.getWidth() / 2 - (size.getWidth() / 2));
			position.setY(Screen.getHeight() - 35);
			break;

		}

		this.keys = keys;
	}
	
	public void move(char c){
		int y = this.position.getY();
		int x = this.position.getX();
		
		if(keys.getLeftKey() == c){
			
			switch(direction){
			case RIGHT:
			case LEFT:
				if(y > 104){
					this.position.setY(y-4);
				}
				break;
			case DOWN:
			case UP:
				if(x > 104){
					this.position.setX(x-4);
				}
				break;
			}
		}else if(keys.getRightKey() == c){
			
			switch(direction){
			case RIGHT:
			case LEFT:
				if(y < Screen.getHeight()-104){
					this.position.setY(y+4);
				}
				break;
			case DOWN:
			case UP:
				if(x < Screen.getWidth()-104){
					this.position.setX(x+4);
				}
				break;
			}
		}
	}

	@Override
	public Graphics draw(Graphics g2) {
		g2.setColor(this.color);
		switch(this.direction){
		case LEFT:
		case RIGHT:
			g2.fillRect(this.position.getX(), this.position.getY(), 
					this.size.getHeight(), this.size.getWidth());
			break;
		case UP:
		case DOWN:
			g2.fillRect(this.position.getX(), this.position.getY(), 
					this.size.getWidth(), this.size.getHeight());
			break;
		}
		
//		for(BallObject ball: RenderThread.getInstance().getBalls()){
//			if(this.isHitBy(ball)){
//				
//				switch(this.direction)){
//				case LEFT:
//				case RIGHT:
//					ball.changeDirection(Ball.CHANGE_X);
//					break;
//				case UP:
//				case DOWN:
//					ball.changeDirection(Ball.CHANGE_Y);
//					break;
//				
//				}
//			}
//		}
		return g2;
	}
}
