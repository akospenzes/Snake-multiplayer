package snakemultiplayer;

import java.awt.Color;

public class BodyPart {

	public int x;
	public int y;
	Color c;
	
	public BodyPart(int x1,int y1,Color col) {
		x = x1;
		y = y1;
		c = col;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x1) {
		x=x1;
	}
	
	public void setY(int y1) {
		y=y1;
	}
	
	public Color getColor() {
		return c;
	}
	
	public void setColor(Color c1) {
		c=c1;
	}
	
}
