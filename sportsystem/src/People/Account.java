package People;

import java.io.Serializable;

public class Account implements Serializable{
	private String account;
	private String password;
	private String type;
	private String name;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Account [ account=" + account + ", password=" + password+ ", name=" + name + ", type=" + type + "]";
	}
	public String[] toStringArr() {
		return new String[] { account, password, name, type };
	}
}
