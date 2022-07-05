package snakemultiplayer;

import java.awt.Color;
import java.util.Random;

public class BlueFruit extends Fruit{
	
	public BlueFruit(int randomX, int randomY) {
		x = randomX;
		y = randomY;
		c = Color.BLUE;
	}

	@Override
	public void eatenBySnake(Snake s) {
		
		s.addScore(10);
		
		float r,g,b;
		Random random = new Random();
		
		s.addBodyPart(s.getNextX(), s.getNextY());
		
		for(int i = 1; i<s.SnakeBody.size()-1;i++) {
			r = random.nextFloat();
			g = random.nextFloat();
			b = random.nextFloat();
			Color c1 = new Color(r,g,b);
			s.SnakeBody.get(i).setColor(c1);
		}
		
		
	}

}
