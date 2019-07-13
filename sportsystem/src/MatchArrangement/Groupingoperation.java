package MatchArrangement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Database.Database;
import People.Account;
import People.Manathlete;

public class Groupingoperation {
	
	
	public static List<Manathlete> getList(String sex,String matchtype,String age) {
		int age1,age2;
		int result = 0;
		if(age.equals("7-8")){
			age1=7;
			age2=8;
		}
		else if(age.endsWith("9-10")){
			age1=9;
			age2=10;
		}else{
			age1=11;
			age2=12;
		}
		result = FindAthlete(sex,matchtype,age1,age2,age);
		List<Manathlete> alList = new ArrayList<>();
		if(result==1){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&amp;character=UTF-8","root","");
				Statement stmt=conn.createStatement();
				String	sql = "select * from preliminaries where sex='"+sex+"' and matchtype='"+matchtype+"' and age in ('"+age1+"','"+age2+"')";
				if (conn != null) {
					ResultSet rs = stmt.executeQuery(sql);
					while (rs.next()) {
						Manathlete acc = new Manathlete();
						acc.setMatch(rs.getString(1));
						acc.setAge(rs.getInt(2));
						acc.setSex(rs.getString(3));
						acc.setName(rs.getString(4));
						acc.setIDNum(rs.getString(5));
						acc.setGroup(rs.getInt(6));
						alList.add(acc);
						//System.out.println(acc.getName());
					}
					rs.close();
				}
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return alList;
			}else{
				return alList;
			}
	}
	
	//找到该项目的所有运动员
	public static int FindAthlete(String sex,String matchtype,int age1,int age2,String age){
		int result=0;
		int i=0;
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&amp;character=UTF-8","root","");
			Statement stmt=conn.createStatement();
			String	sql = "select * from athlete where sex='"+sex+"' and matchtype='"+matchtype+"' and age in ('"+age1+"','"+age2+"')";	//
			if (conn != null) {
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					Manathlete M = new Manathlete();
					M.setName(rs.getString(1));
					M.setIDNum(rs.getString(2));
					M.setAge(rs.getInt(3));
					M.setSex(rs.getString(4));
					M.setTeam(rs.getString(5));
					M.setTeamID(rs.getString(6));
					M.setMatch(rs.getString(7));
					result = Insertathlete(M,i,age);
					i=i+1;
					//System.out.println(M.getName()+"  "+i+"  "+M.getMatch()+M.getSex());
				}
				rs.close();
			}
			stmt.close();
			conn.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}

	}
	/*将运动员按项目插入到初赛赛表*/
	public static int Insertathlete(Manathlete M,int i,String age){
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&amp;character=UTF-8","root","");
			Statement stmt=conn.createStatement();
			//先删除实现分组
			ResultSet rs1=stmt.executeQuery("select * from Preliminaries where IDnum='"+M.getIDNum()+"' and matchtype='"+M.getMatch()+"' and age='"+M.getAge()+"'");
			if (rs1.next()){
				String sql="delete from Preliminaries where IDnum='"+M.getIDNum()+"' and matchtype='"+M.getMatch()+"' and age='"+M.getAge()+"'";
				stmt.executeUpdate(sql);

			}
			
			String sql="insert into Preliminaries values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql); 
			ps.setString(1,M.getMatch());
			ps.setInt(2,M.getAge());
			ps.setString(3,M.getSex());
			ps.setString(4,M.getName());
			ps.setString(5,M.getIDNum());
			ps.setInt(6,i/5);
			for(int j=7;j<=15;j++)
				ps.setInt(j,0);
			ps.setString(16,M.getMatch()+age+M.getSex()+(i/5));
			ps.setString(17,M.getTeam());
			//System.out.println(M.getTeam());
			ps.executeUpdate();
			ps.close(); 
			stmt.close();
			conn.close();
			return 1;
		}
		catch(Exception sqle){
			System.err.println(sqle);
			return 0;
		} 	
	}
	
}
