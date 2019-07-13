package MatchArrangement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import People.Manathlete;

public class Creatoperation {

	
	
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
				String	sql = "select * from finals where sex='"+sex+"' and matchtype='"+matchtype+"' and age in ('"+age1+"','"+age2+"')";
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
		int i=0,group;
		int rank1=1,rank2=2,rank3=3;
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&amp;character=UTF-8","root","");
			Statement stmt=conn.createStatement();
			String	sql = "select * from preliminaries where sex='"+sex+"' and matchtype='"+matchtype+"' and age in ('"+age1+"','"+age2+"') and rank in ('"+rank1+"','"+rank2+"','"+rank3+"')";	//
			if (conn != null) {
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					Manathlete M = new Manathlete();
					M.setName(rs.getString(4));
					M.setIDNum(rs.getString(5));
					M.setAge(rs.getInt(2));
					M.setSex(rs.getString(3));
					M.setTeam(rs.getString(17));
					M.setMatch(rs.getString(1));
					M.setGroup(rs.getInt(6));
					String seq = rs.getString(16);
					result = Insertathlete(M,age,seq,i);
					i++;
					//System.out.println(M.getName()+"  "+"  "+M.getMatch()+M.getSex());
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
	public static int Insertathlete(Manathlete M,String age,String seq,int i){
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&amp;character=UTF-8","root","");
			Statement stmt=conn.createStatement();
			//先删除实现分组
			if(i==0){
			ResultSet rs1=stmt.executeQuery("select * from finals where matchtype='"+M.getMatch()+"' and age='"+M.getAge()+"' and sex='"+M.getSex()+"'");
				if (rs1.next()){
					String sql="delete from finals where and matchtype='"+M.getMatch()+"' and age='"+M.getAge()+"' and sex='"+M.getSex()+"'";
					stmt.executeUpdate(sql);

				}
			}
			
			String sql="insert into finals values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql); 
			ps.setString(1,M.getMatch());
			ps.setInt(2,M.getAge());
			ps.setString(3,M.getSex());
			ps.setString(4,M.getName());
			ps.setString(5,M.getIDNum());
			ps.setInt(6,M.getGroup());
			for(int j=7;j<=15;j++)
				ps.setInt(j,0);
			ps.setString(16,seq);
			ps.setString(17,M.getTeam());
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
