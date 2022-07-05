package snakemultiplayer;

import java.io.Serializable;

public class Score implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String nev;
	private int pontszam;
	
	public Score(String n, int p) {
		nev = n;
		pontszam = p;
	}
	
	public int getScore() {
		return pontszam;
	}
	
	public String getName() {
		return nev;
	}
}


