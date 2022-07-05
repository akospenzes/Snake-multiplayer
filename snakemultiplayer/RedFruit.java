package snakemultiplayer;

import java.awt.Color;

public class RedFruit extends Fruit{

	public RedFruit(int rX, int rY) {
		x = rX;
		y = rY;
		c = Color.RED;
	}

	@Override
	public void eatenBySnake(Snake s) {

		s.addScore(10);
		
		s.addBodyPart(s.getNextX(),s.getNextY());
		
	}
	
}
