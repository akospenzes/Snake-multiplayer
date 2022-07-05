package snakemultiplayer;

import java.io.IOException;

public class Main {
	
	public static SnakeGame snakegame;
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {

		snakegame = new SnakeGame();
		snakegame.gameframe.setLocationRelativeTo(null);
		snakegame.gameframe.setVisible(true);
		
	}

}
