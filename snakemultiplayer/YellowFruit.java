package snakemultiplayer;

import java.awt.Color;

public class YellowFruit extends Fruit{
	
	public YellowFruit(int randomX, int randomY) {
		x = randomX;
		y = randomY;
		c = Color.YELLOW;
	}

	@Override
	public void eatenBySnake(Snake s) {
		s.addScore(10);
		
		s.addBodyPart(s.getNextX(),s.getNextY());
		
		for(int i = 1; i< s.SnakeBody.size();i++) {
			s.SnakeBody.get(i).setColor(s.col);
		}
		
	}

}
