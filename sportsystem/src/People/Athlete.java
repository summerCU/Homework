package People;

import java.io.Serializable;
@SuppressWarnings("serial")
public class Athlete implements Serializable {
	private int seq;		//–Ú∫≈
	private String IDnum;	//…Ì∑›÷§
	private String name;
	private String age;
	private String sex;
	private String team;
	private String teamID;
	private String score;
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	public String getIDNum() {
		return IDnum;
	}
	public void setIDNum(String IDnum) {
		this.IDnum = IDnum;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public void setTeam(String team){
		this.team = team;
	}
	public String getTeam() {
		return team;
	}
	
	public void setTeamID(String teamID){
		this.teamID = teamID;
	}
	public String getTeamID() {
		return teamID;
	}
	
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "Student [seq=" + seq + ", IDnum=" + IDnum + ", name=" + name
				+ ", age=" + age + ", sex=" + sex + "]";
	}
	public String[] toStringArr() {
		return new String[] { seq + "", IDnum, name, age, sex };
	}
	
}
