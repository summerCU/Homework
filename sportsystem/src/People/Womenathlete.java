package People;

import java.io.Serializable;

public class Womenathlete  implements Serializable{

	private int seq;		//序号
	private String IDnum;	//身份证
	private String name;
	private String age;
	private String sex;
	private String team;
	private String teamID;
	private String match;
	private int score1;
	private	int score2;
	private int rank1;
	private	int rank2;
	
	/*
	private String gaodigang;
	private String pinghengmu;
	private String ticao;
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
		this.sex = "女";
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
	public String getGaodigang() {
		return gaodigang;
	}
	public void setGaodigang(String gaodigang) {
		this.gaodigang = gaodigang;
	}
	
	public String getPinghengmu() {
		return pinghengmu;
	}
	public void setPinghengmu(String pinghengmu) {
		this.pinghengmu = pinghengmu;
	}
	
	public String getTicao() {
		return ticao;
	}
	public void setTicao(String ticao) {
		this.ticao = ticao;
	}
	
	public String getBengchuang() {
		return bengchuang;
	}
	public void setBengchuang(String bengchuang) {
		this.bengchuang = bengchuang;
	}
*/


}
