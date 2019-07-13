package Operation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Surface.Admin;
import Surface.ChiefReferee;
import Surface.Manager;
import Surface.Referee;
import Team_UI.Team;
import scoreSystem.kkk;


public class CheckUser {
	private static String needname=null;
	private static String needid=null;
	public CheckUser(){
	}
	
	public static int Checkid(String id, String password, String identify){
		int result=0;
		switch (identify){
		case "�����":
			result = Teamlaod(id, password);
		    break;
		case "ϵͳ����Ա":
			result = Adlaod(id, password);
		    break;
		case "���¹���Ա":
			result = Malaod(id, password);
		    break;
		case "���г�":
			result = CRlaod(id, password);
		    break;
		case "����Ա":
			result = Relaod(id, password);
		    break;
		case "�ο�":
			result = Vilaod(id, password);
			System.out.println(result);
		    break;
		}
		return result;	
	}
	
	
	public static int Teamlaod(String id, String password){
		int flag = Pacheck(id,password,"�����");
		if(flag==1){
			Team zhuce=new Team(needname);
			zhuce.Setname(needname);
			zhuce.Setid(id);
			return 1;
		}
		else if (flag==-1){
			return -1;
		}
		else
			return 0;
	}
	
	public static int Adlaod(String id, String password){
		int flag = Pacheck(id,password,"ϵͳ����Ա");
		if(flag==1){
			Admin zhuce=new Admin();
			zhuce.Setname(needname);
			zhuce.Setid(id);
			//zhuce.initialize();
			return 1;
		}
		else if (flag==-1){
			return -1;
		}
		else
			return 0;
	}
	public static int Malaod(String id, String password){
		int flag = Pacheck(id,password,"���¹���Ա");
		if(flag==1){
			Manager zhuce=new Manager();
			zhuce.Setname(needname);
			zhuce.Setid(id);
			//zhuce.initialize();
			return 1;
		}
		else if (flag==-1){
			return -1;
		}
		else
			return 0;
	}
	public static int CRlaod(String id, String password){
		int flag = Pacheck(id,password,"���г�");
		if(flag==1){
			ChiefReferee zhuce=new ChiefReferee();
			zhuce.Setname(needname);
			zhuce.Setid(id);
			zhuce.initialize();
			return 1;
		}
		else if (flag==-1){
			return -1;
		}
		else
			return 0;
	}
	public static int Relaod(String id, String password){
		int flag = Pacheck(id,password,"����Ա");
		if(flag==1){
			Referee zhuce=new Referee();
			zhuce.Setname(needname);
			zhuce.Setid(id);
			zhuce.initialize();
			return 1;
		}
		else if (flag==-1){
			return -1;
		}
		else
			return 0;
	}
	public static int Vilaod(String id, String password){
		int flag = Pacheck(id,password,"�ο�");
		if(flag==1){
			String[] args = new String[1];
			kkk.main(args);
			return 1;
		}
		else if (flag==-1){
			return -1;
		}
		else
			return 0;
	}
	
	private static int Pacheck(String id, String password, String type ){
		String pa=null;
//		System.out.println(id+password+type);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&amp;character=UTF-8","root","");
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from account where Uid='"+id+"' and Type='"+type+"'");
			while(rs.next()){
			pa=rs.getString("Passwd");
			needname=rs.getString("Name");
			}
			rs.close();
			stmt.close();
			conn.close();
//			System.out.println(pa);
			if(pa.equals(password)){
				return 1;
			}
			else
				return 0;
		}
		catch(Exception sqle){
			System.err.println(sqle);
			return -1;
		}
	
	}
}
