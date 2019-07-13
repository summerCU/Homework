package People;

import java.io.Serializable;

public class PReferee implements Serializable {
	private String id;
	private String name;
	private String type;
	private String match;
	private String age;
	private String sex;
	private int seq;
	
	
	public String getID() {
		return id;
	}
	public void setIDNum(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public void setMatch(String match){
		this.match = match;
	}
	public String getMatch() {
		return match;
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
}
