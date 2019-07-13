package People;

import java.io.Serializable;

public class Bisaicanshu implements Serializable{
	private int maxsize;
	private int matchsize;
	private int teamscore;
	private int minsize;
	
	public int getMaxsize() {
		return maxsize;
	}
	public void setMaxsize(int maxsize) {
		this.maxsize = maxsize;
	}
	
	public int getMatchsize() {
		return matchsize;
	}
	public void setMatchsize(int matchsize) {
		this.matchsize = matchsize;
	}
	
	public int getTeamscore() {
		return teamscore;
	}
	public void setTeamscore(int teamscore) {
		this.teamscore = teamscore;
	}
	
	public int getMinsize() {
		return minsize;
	}
	public void setMinsize(int minsize) {
		this.minsize = minsize;
	}
	


}
