package snakemultiplayer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class MenuPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public CustomButton singleplayer_button,multiplayer_button,leaderboard_button,exit_button;
	
	public JTextField player1name,player2name;
	
	public JLabel player1,player2;
	
	public MenuPanel() {
		setPreferredSize(new Dimension(1000,800));
		setBackground(Color.BLACK);
		setLayout(new GridLayout(2,4,0,0));	
		
		player1 = new JLabel("First player's name: (Singleplayer)");
		player1.setForeground(Color.CYAN);
		player1.setFont(new Font("Arial", Font.PLAIN, 15));
		player1.setVerticalAlignment(JLabel.BOTTOM);
		
		player1name = new JTextField();
		player1name.setForeground(Color.CYAN);
		player1name.setBackground(Color.BLACK);
		player1name.setFont(new Font("Arial", Font.PLAIN, 20));
		player1name.setHorizontalAlignment(JTextField.CENTER);
		
		player2 = new JLabel("Second player's name:");
		player2.setForeground(Color.ORANGE);
		player2.setFont(new Font("Arial", Font.PLAIN, 15));
		player2.setVerticalAlignment(JLabel.BOTTOM);
		player2.setHorizontalAlignment(JLabel.RIGHT);
		
		player2name = new JTextField();
		player2name.setForeground(Color.ORANGE);
		player2name.setBackground(Color.BLACK);
		player2name.setFont(new Font("Arial", Font.PLAIN, 20));
		player2name.setHorizontalAlignment(JTextField.CENTER);
		
		singleplayer_button = new CustomButton("Singleplayer");
		
		multiplayer_button = new CustomButton("Multiplayer");

		leaderboard_button = new CustomButton("Leaderboard");
		
		exit_button = new CustomButton("Exit");
		
		add(player1);
		add(singleplayer_button);
		add(multiplayer_button);
		add(player2);
		add(player1name);
		add(leaderboard_button);
		add(exit_button);
		add(player2name);
		
	}

	public void AddButtonListeners() {
		
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		GameFrame ParentFrame = (GameFrame) topFrame;
		
		singleplayer_button.addActionListener(new ActionListener() {  
			  public void actionPerformed(ActionEvent e) {
				  String p1 = player1name.getText();
				  ParentFrame.Single_gamepanel = new GamePanelS(p1);
				  ParentFrame.getContentPane().removeAll();
				  ParentFrame.add(ParentFrame.Single_gamepanel);
				  ParentFrame.Single_gamepanel.requestFocus();
				  ParentFrame.revalidate();
			  }
		});
		
		multiplayer_button.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				  String p1 = player1name.getText();
				  String p2 = player2name.getText();
				  ParentFrame.Multi_gamepanel = new GamePanelM(p1,p2);
				  ParentFrame.getContentPane().removeAll();
				  ParentFrame.add(ParentFrame.Multi_gamepanel);
				  ParentFrame.Multi_gamepanel.requestFocus();
				  ParentFrame.revalidate();
			  } 
		});
		
		leaderboard_button.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  
				  JFrame leaderboard_frame = new JFrame();
				  leaderboard_frame = new JFrame("Leaderboard");
				  leaderboard_frame.setSize(new Dimension(800,600));
				  leaderboard_frame.setLocationRelativeTo(null);
				  
				  DefaultTableModel model = new DefaultTableModel(); 
				  JTable table = new JTable();
				  table = new JTable(model); 
				  model.addColumn("Name"); 
				  model.addColumn("Score");
				  
				  
				  for(int i = 0; i<ParentFrame.scores.size();i++) {
					  String nev = ParentFrame.scores.get(i).getName();
					  String pont =String.valueOf(ParentFrame.scores.get(i).getScore());
					  model.addRow(new Object[]{nev,pont});
				  }
				  			  
				  leaderboard_frame.add(new JScrollPane(table));
				  leaderboard_frame.setVisible(true);

			  } 
		});
		
		exit_button.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  try {
					ParentFrame.saveleaderboard();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				  System.exit(0);
			  } 
		});
		
	}
}
