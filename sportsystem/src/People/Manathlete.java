package People;

import java.io.Serializable;

public class Manathlete  implements Serializable {
	private int seq;		//–Ú∫≈
	private String IDnum;	//…Ì∑›÷§
	private String name;
	private int age;
	private int group;
	private String sex;
	private String team;
	private String teamID;
	private String match;
	private int score1;
	private	int score2;
	private int rank1;
	private	int rank2;
	/*
	private String dangang;
	private String shuanggang;
	private String diaohuan;
	private String tiaoma;
	private String ticao;
	private String anma;
	private String bengchuang;
	*/
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
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
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
	public void setMatch(String match){
		this.match = match;
	}
	public String getMatch() {
		return match;
	}
	public int getScore1() {
		return score1;
	}
	public void setScore1(int score1) {
		this.score1 = score1;
	}
	public int getScore2() {
		return score2;
	}
	public void setScore2(int score1) {
		this.score2 = score2;
	}
	
	public int getRank1() {
		return rank1;
	}
	public void setRank1(int rank1) {
		this.rank1 = rank1;
	}
	public int getRank2() {
		return rank2;
	}
	public void setRank2(int rank1) {
		this.rank2 = rank2;
	}
	
	/*
	public String getDangang() {
		return dangang;
	}
	public void setDangang(String dangang) {
		this.dangang = dangang;
	}
	
	public String getShuanggang() {
		return shuanggang;
	}
	public void setShuanggang(String shuanggang) {
		this.shuanggang = shuanggang;
	}
	
	public String getDiaohuan() {
		return diaohuan;
	}
	public void setDiaohuan(String diaohuan) {
		this.diaohuan = diaohuan;
	}
	
	public String getTiaoma() {
		return tiaoma;
	}
	public void setTiaoma(String tiaoma) {
		this.tiaoma = tiaoma;
	}
	
	public String getTicao() {
		return ticao;
	}
	public void setTicao(String ticao) {
		this.ticao = ticao;
	}
	
	public String getAnma() {
		return anma;
	}
	public void setAnma(String anma) {
		this.anma = anma;
	}
	
	public String getBengchuang() {
		return bengchuang;
	}
	public void setBengchuang(String bengchuang) {
		this.bengchuang = bengchuang;
	}
*/
	
	public String toString() {
		return "Student [seq=" + seq + ", IDnum=" + IDnum + ", name=" + name
				+ ", age=" + age + ", sex=" + sex + "]";
	}
	public String[] toStringArr() {
		return new String[] { match, Integer.toString(age),sex,name,IDnum,Integer.toString(group) };
	}
}
