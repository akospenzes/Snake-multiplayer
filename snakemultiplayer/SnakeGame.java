package snakemultiplayer;

import java.io.IOException;

public class SnakeGame {
	
	public GameFrame gameframe;
	
	public SnakeGame() throws ClassNotFoundException, IOException {
		
		gameframe = new GameFrame();
		gameframe.menupanel = new MenuPanel();
		gameframe.add(gameframe.menupanel);
		gameframe.menupanel.AddButtonListeners();
		gameframe.pack();
	}
}
