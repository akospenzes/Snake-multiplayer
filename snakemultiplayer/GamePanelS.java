package snakemultiplayer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class GamePanelS extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	protected int WIDTH, HEIGHT, FIELD_SIZE, DELAY;
	
	protected Snake s;
	
	protected boolean running, movekeypressed;
	
	protected char dir;
	
	protected Fruit f;

	protected Timer timer;
	
	protected String playername;
	
	public GamePanelS(String pn) {
		
		WIDTH = 1000;
		HEIGHT = 800;
		playername = pn;
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setBackground(Color.BLACK);
		addKeyListener(new KeyLis());
		
		runGame();
	}
	
	public void runGame() {
		FIELD_SIZE = 25;
		DELAY = 50;
		s = new Snake(WIDTH,HEIGHT,FIELD_SIZE,Color.GREEN);
		s.SnakeBody.get(0).setColor(Color.CYAN);			//így jobban lehet követni, hogy merre halad.
		dir = 'X';
		running = true;
		movekeypressed = false;
		f = newFruit();
		timer = new Timer(DELAY,this);
		timer.start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawFields(g);
	}
	
	public void drawFields(Graphics g) {
		for(int i = 0 ;i < WIDTH;i+=FIELD_SIZE) {
			for(int j = 0 ;j < HEIGHT; j+=FIELD_SIZE) {
				for(int k = 0 ; k < s.SnakeBody.size(); k++) {
					if(i/FIELD_SIZE == s.SnakeBody.get(k).getX() && j/FIELD_SIZE == s.SnakeBody.get(k).getY()) {
						g.setColor(s.SnakeBody.get(k).getColor());
						g.fillRect(i, j, FIELD_SIZE, FIELD_SIZE);
					}
				}
			}
		}
		
		
		for(int i = 0; i < WIDTH; i+=FIELD_SIZE) {
			for(int j = 0; j < HEIGHT; j+=FIELD_SIZE) {
				if(i/FIELD_SIZE==f.getX() && j/FIELD_SIZE == f.getY()) {
					g.setColor(f.getColor());
					g.fillOval(i,j,FIELD_SIZE,FIELD_SIZE);
					g.setColor(Color.WHITE);
					g.drawOval(i,j,FIELD_SIZE,FIELD_SIZE);
				}
			}
		}
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN,40));
		g.drawString("Score: "+s.getScore(),5,HEIGHT-10);
	}
	
	public void check() {
		
		if(s.SnakeBody.get(0).getX() < 0 || s.SnakeBody.get(0).getX() > (WIDTH/FIELD_SIZE)-1)
			running=false;
		
		if(s.SnakeBody.get(0).getY() < 0 || s.SnakeBody.get(0).getY() > (HEIGHT/FIELD_SIZE)-1)
			running=false;
		
		if(s.SnakeBody.get(0).getX() == f.getX() && s.SnakeBody.get(0).getY() == f.getY()) {
			f.eatenBySnake(s);
			f = newFruit();
		}
		
	}
	
	public void gameOver() {
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		GameFrame ParentFrame = (GameFrame) topFrame;
		
		
		Score sc = new Score(playername,s.getScore());
		ParentFrame.scores.add(sc);
		
		Collections.sort(ParentFrame.scores, ParentFrame.score_comp);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		ParentFrame.getContentPane().removeAll();
		ParentFrame.menupanel = new MenuPanel();
		ParentFrame.add(ParentFrame.menupanel);
		ParentFrame.menupanel.AddButtonListeners();
		ParentFrame.menupanel.requestFocus();
		ParentFrame.revalidate();
	}
	
	public Fruit newFruit() {
		Random r = new Random();
		int x,y,type;
		x = r.nextInt(WIDTH/FIELD_SIZE);
		y = r.nextInt(HEIGHT/FIELD_SIZE);
		type = r.nextInt(3);
		Fruit fr = null;
		
		switch(type) {
		case 0 :
			fr = new RedFruit(x,y);
			break;
		case 1 :
			fr = new BlueFruit(x,y);
			break;
		case 2 :
			fr = new YellowFruit(x,y);
			break;
		}
		return fr;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(running) {
			s.setDirection(dir);
			movekeypressed = false;
			running = s.moveHead();
			if(running) {
				check();
			}
			s.moveBody();
			repaint();
		}
		else {
			timer.stop();
			gameOver();
		}
	}
	
	public class KeyLis extends KeyAdapter {
	      @Override
	      public void keyPressed(KeyEvent e) {
	         switch (e.getKeyCode()) {
	         case KeyEvent.VK_LEFT:
	        	 if(dir == 'R' || movekeypressed == true) {
	        		 break;
	        	 }
	        	 dir = 'L';
	        	 movekeypressed = true;
	        	 break;
	         case KeyEvent.VK_RIGHT:
	        	 if(dir == 'L' || movekeypressed == true) {
	        		 break;
	        	 }
	        	 dir = 'R';
	        	 movekeypressed = true;
	        	 break;
	         case KeyEvent.VK_UP:
	        	 if(dir == 'D' || movekeypressed == true) {
	        		 break;
	        	 }
	        	 dir = 'U';
	        	 movekeypressed = true;
	        	 break;
	         case KeyEvent.VK_DOWN:
	        	 if(dir == 'U' || movekeypressed == true) {
	        		 break;
	        	 }
	        	 dir = 'D';
	        	 movekeypressed = true;
	        	 break;
	         case KeyEvent.VK_ESCAPE:
	        	 running = false;
	        	 break;
	         default : break;
	         }
	      }
	}

}
