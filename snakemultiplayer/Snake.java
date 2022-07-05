package snakemultiplayer;

import java.awt.Color;
import java.util.ArrayList;

public class Snake{
	
	public ArrayList<BodyPart> SnakeBody;
	
	private int score;
	
	private char direction;
	
	private int nextX, nextY;										//a következõ BodyPart
	
	private int tempX, tempY;
	
	protected Color col;
	
	
	public Snake(int screenW, int screenH, int fieldSize,Color c) {
		SnakeBody = new ArrayList<BodyPart>();
		score = 0;
		direction = 'X';									//nem halad egyik irányba sem
		col = c;
		
		for(int i = 0; i<3 ; i++) {							//3 db BodyPart-tal kezd
			addBodyPart(screenW/(2*fieldSize),screenH/(2*fieldSize));
		}
		
		nextX = SnakeBody.get(SnakeBody.size()-1).getX();
		nextY = SnakeBody.get(SnakeBody.size()-1).getY();
	}
	
	public void addBodyPart(int x2, int y2) {
		BodyPart bp = new BodyPart(x2,y2,col);
		SnakeBody.add(bp);
	}
	
	
	public boolean moveHead() {
		
		nextX = SnakeBody.get(SnakeBody.size()-1).getX();
		nextY = SnakeBody.get(SnakeBody.size()-1).getY();
		
		tempX = SnakeBody.get(0).getX();
		tempY = SnakeBody.get(0).getY();
		
		switch(direction) {
			case 'L' :
				SnakeBody.get(0).setX(tempX-1);
				break;
			case 'R' :
				SnakeBody.get(0).setX(tempX+1);
				break;
			case 'D' :
				SnakeBody.get(0).setY(tempY+1);
				break;
			case 'U' :
				SnakeBody.get(0).setY(tempY-1);
				break;
		}
		
		if(SnakeBody.size()>=5) {
			for(int i = 3 ; i< SnakeBody.size()-1; i++) {
				if(SnakeBody.get(0).getX() == SnakeBody.get(i).getX() && SnakeBody.get(0).getY() == SnakeBody.get(i).getY()) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public void moveBody() {
		for(int i = SnakeBody.size()-1; i>1; i--) {
			SnakeBody.get(i).setX(SnakeBody.get(i-1).getX());
			SnakeBody.get(i).setY(SnakeBody.get(i-1).getY());
		}
		
		SnakeBody.get(1).setX(tempX);
		SnakeBody.get(1).setY(tempY);	
	}
	
	public int getNextX() {
		return nextX;
	}
	
	public int getNextY() {
		return nextY;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getTempX() {
		return tempX;
	}
	
	public int getTempY() {
		return tempY;
	}

	public char getDir() {
		return direction;
	}
	
	public void setDirection(char dir) {
		direction = dir;
	}
	
	public void addScore(int plus) {
		score += plus;
	}
	
}
