package snakemultiplayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFrame;

public class GameFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public MenuPanel menupanel;
	public GamePanelS Single_gamepanel;
	public GamePanelM Multi_gamepanel;
	
	public ArrayList<Score> scores;
	
	public ScoreComparator score_comp;
	
	public String name1,name2;
	
	public GameFrame() throws IOException, ClassNotFoundException {
		super("Snake");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		scores = new ArrayList<Score>();
		score_comp = new ScoreComparator();
		
		if( ! new File("Leaderboard.txt").isFile()) {				//Ha nem létezik a file
			File ujFile = new File ("Leaderboard.txt");
			ujFile.createNewFile();
		}
		
		FileInputStream f =new FileInputStream("Leaderboard.txt");
		if(f.available()>0) {										//Ha üres a file
			ObjectInputStream in =new ObjectInputStream(f);
			ArrayList<?> ar =(ArrayList<?>) in.readObject();
			
			for(Object x : ar) {
				scores.add((Score) x);
			}
			
			in.close();
		}
	}
	
	public void saveleaderboard() throws IOException {
		FileOutputStream f = new FileOutputStream("Leaderboard.txt");
		ObjectOutputStream out = new ObjectOutputStream(f);
		out.writeObject(scores);
		out.close();
	}
	
}
