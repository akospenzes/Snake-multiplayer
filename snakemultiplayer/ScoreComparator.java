package snakemultiplayer;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Score>{

	@Override
	public int compare(Score s1, Score s2) {
		return -1 * (s1.getScore() - s2.getScore());		//csökkenõ sorrendben lesznek.
	}

}
