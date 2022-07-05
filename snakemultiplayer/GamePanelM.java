package snakemultiplayer;

import java.awt.BorderLayout;
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

public class GamePanelM extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	protected int WIDTH, HEIGHT, FIELD_SIZE, DELAY;
	
	public Snake s1,s2;
	
	public boolean running, s1wins, s2wins, tie, move1keypressed,move2keypressed;
	
	public char dir1,dir2;
	
	public Fruit f;
	
	public Timer timer;
	
	public String player1name,player2name;
	
	public GamePanelM(String p1n,String p2n) {
		WIDTH = 1000;
		HEIGHT = 800;
		player1name = p1n;
		player2name = p2n;
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.setLayout(new BorderLayout());
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(new KeyLisM());
		
		runGame();
	}
	
	public void runGame() {
		
		FIELD_SIZE = 25;
		DELAY = 50;
		
		s1 = new Snake(450,HEIGHT,FIELD_SIZE,Color.BLUE);
		s1.SnakeBody.get(0).setColor(Color.CYAN);		//így jobban lehet követni, hogy merre halad.
		
		s2 = new Snake(1500,HEIGHT,FIELD_SIZE,Color.RED);
		s2.SnakeBody.get(0).setColor(Color.ORANGE);		//így jobban lehet követni, hogy merre halad.
		
		dir1 = 'X';
		dir2 = 'X';
		running = true;
		move1keypressed = false;
		move2keypressed = false;
		s1wins = false;
		s2wins = false;
		tie = false;
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
				for(int k = 0 ; k < s1.SnakeBody.size(); k++) {
					if(i/FIELD_SIZE == s1.SnakeBody.get(k).getX() && j/FIELD_SIZE == s1.SnakeBody.get(k).getY()) {
						g.setColor(s1.SnakeBody.get(k).getColor());
						g.fillRect(i, j, FIELD_SIZE, FIELD_SIZE);
					}
				}
				for(int k = 0 ; k < s2.SnakeBody.size(); k++) {
					if(i/FIELD_SIZE == s2.SnakeBody.get(k).getX() && j/FIELD_SIZE == s2.SnakeBody.get(k).getY()) {
						g.setColor(s2.SnakeBody.get(k).getColor());
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
		g.setFont(new Font("Arial", Font.PLAIN,30));
		g.drawString("Score: "+s1.getScore(),5,HEIGHT-10);
		g.drawString("Score: "+s2.getScore(),WIDTH-165,HEIGHT-10);
	}
	
	public void check() {
		
		if(s1.SnakeBody.get(0).getX() < 0 || s1.SnakeBody.get(0).getX() > (WIDTH/FIELD_SIZE)-1) {
			running = false;
			s2wins = true;
		}
		
		if(s1.SnakeBody.get(0).getY() < 0 || s1.SnakeBody.get(0).getY() > (HEIGHT/FIELD_SIZE)-1) {
			running = false;
			s2wins = true;
		}
		
		if(s2.SnakeBody.get(0).getX() < 0 || s2.SnakeBody.get(0).getX() > (WIDTH/FIELD_SIZE)-1) {
			running = false;
			s1wins = true;
		}
		
		if(s2.SnakeBody.get(0).getY() < 0 || s2.SnakeBody.get(0).getY() > (HEIGHT/FIELD_SIZE)-1) {
			running = false;
			s1wins = true;
		}
		//egymás fejének ütköznek
		if(s1.SnakeBody.get(0).getX() == s2.SnakeBody.get(0).getX() && s1.SnakeBody.get(0).getY() == s2.SnakeBody.get(0).getY()) {
			running = false;
			tie = true;
		}
		//egymás fejének ütköznek
		if(s1.SnakeBody.get(0).getX() == s2.getTempX() && s1.SnakeBody.get(0).getY() == s2.getTempY() && s2.SnakeBody.get(0).getX() == s1.getTempX() && s2.SnakeBody.get(0).getY() == s1.getTempY()) {
			running = false;
			tie = true;
		}
		
		if(s1.SnakeBody.get(0).getX() == s2.getTempX() && s1.SnakeBody.get(0).getY() == s2.getTempY()) {
			running = false;
			s2wins = true;
		}
		
		if(s2.SnakeBody.get(0).getX() == s1.getTempX() && s2.SnakeBody.get(0).getY() == s1.getTempY()) {
			running = false;
			s1wins = true;
		}
		
		for(int i = 1 ; i< s2.SnakeBody.size()-1; i++) {
			if(s1.SnakeBody.get(0).getX() == s2.SnakeBody.get(i).getX() && s1.SnakeBody.get(0).getY() == s2.SnakeBody.get(i).getY()) {
				running = false;
				s2wins = true;
			}
		}
		
		for(int i = 1 ; i< s1.SnakeBody.size()-1; i++) {
			if(s2.SnakeBody.get(0).getX() == s1.SnakeBody.get(i).getX() && s2.SnakeBody.get(0).getY() == s1.SnakeBody.get(i).getY()) {
				running = false;
				s1wins = true;
			}
		}
		
		if(s1.SnakeBody.get(0).getX()==f.getX() && s1.SnakeBody.get(0).getY()==f.getY()) {
			f.eatenBySnake(s1);
			f=newFruit();
		}
		
		if(s2.SnakeBody.get(0).getX()==f.getX() && s2.SnakeBody.get(0).getY()==f.getY()) {
			f.eatenBySnake(s2);
			f=newFruit();
		}
		
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
	
	public void gameOver() {
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		GameFrame ParentFrame = (GameFrame) topFrame;
		
		if(s1wins == true && s2wins == true) { //ha egyszerre veszítenek
			tie = true;
		}
		
		if(tie) {
			Score sc1 = new Score(player1name,s1.getScore());
			Score sc2 = new Score(player2name,s2.getScore());
			ParentFrame.scores.add(sc1);
			ParentFrame.scores.add(sc2);
		}
		if(s1wins == true && s2wins == false) {
			Score sc1 = new Score(player1name,s1.getScore());
			ParentFrame.scores.add(sc1);
		}
		if(s2wins == true && s1wins == false) {
			Score sc2 = new Score(player2name,s2.getScore());
			ParentFrame.scores.add(sc2);
		}
		
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
		
	private class KeyLisM extends KeyAdapter {
	      @Override
	      public void keyPressed(KeyEvent e) {
	    	  switch(e.getKeyCode()) {
	    	  case KeyEvent.VK_A:
	    		  if(dir1 =='R' || move1keypressed == true){
	    			  break;
	    		  }
	    		  dir1 = 'L';
	    		  move1keypressed = true;
	    		  break;
	    	  case KeyEvent.VK_D:
	    		  if(dir1 =='L' || move1keypressed == true) {
	    			  break;
	    		  }
	    		  dir1 = 'R';
	    		  move1keypressed = true;
	    		  break;
	    	  case KeyEvent.VK_W:
	    		  if(dir1 =='D' || move1keypressed == true) {
	    			  break;
	    		  }
	    		  dir1 = 'U';
	    		  move1keypressed = true;
	    		  break;
	    	  case KeyEvent.VK_S:
	    		  if(dir1 =='U' || move1keypressed == true) {
	    			  break;
	    		  }
	    		  dir1 = 'D';
	    		  move1keypressed = true;
	    		  break;
	    	  case KeyEvent.VK_LEFT:
	    		  if(dir2 =='R' || move2keypressed == true) {
	    			  break;
	    		  }
	    		  dir2 = 'L';
	    		  move2keypressed = true;
	    		  break;
	    	  case KeyEvent.VK_RIGHT:
	    		  if(dir2 =='L' || move2keypressed == true) {
	    			  break;
	    		  }
	    		  dir2 = 'R';
	    		  move2keypressed = true;
	    		  break;
	    	  case KeyEvent.VK_UP:
	    		  if(dir2 =='D' || move2keypressed == true) {
	    			  break;
	    		  }
	    		  dir2 = 'U';
	    		  move2keypressed = true;
	    		  break;
	    	  case KeyEvent.VK_DOWN:
	    		  if(dir2 =='U' || move2keypressed == true) {
	    			  break;
	    		  }
	    		  dir2 = 'D';
	    		  move2keypressed = true;
	    		  break;
	    	  case KeyEvent.VK_ESCAPE:
		        	 running = false;
		        	 break;
		         default : break;
	    	  }
	      }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(running) {
			s1.setDirection(dir1);
			move1keypressed = false;
			s2.setDirection(dir2);
			move2keypressed = false;
			boolean firstrunning = s1.moveHead();
			boolean secondrunning = s2.moveHead();
			running = firstrunning && secondrunning;
			check();
			s1.moveBody();
			s2.moveBody();
			repaint();
		}
		else {
			timer.stop();
			gameOver();
		}
	}

}
