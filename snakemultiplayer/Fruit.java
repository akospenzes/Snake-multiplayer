package snakemultiplayer;

import java.awt.Color;

public abstract class Fruit {

	public int x,y;
	public Color c;
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Color getColor() {
		return c;
	}
	
	public abstract void eatenBySnake(Snake s);
}
