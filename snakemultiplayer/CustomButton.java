package snakemultiplayer;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class CustomButton extends JButton{

	private static final long serialVersionUID = 1L;
	
	public CustomButton(String t) {
		super(t);
		setForeground(Color.GREEN);
		setBackground(Color.BLACK);
		setFont(new Font("Arial", Font.PLAIN, 30));
		setFocusable(false);
	}

}
