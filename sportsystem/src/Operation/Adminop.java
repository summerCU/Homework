package Operation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import People.Account;


public class Adminop {
	
	public Adminop(){
		
	}
	
	public static boolean add(Account acc){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&amp:character=UTF-8","root","");
			Statement stmt=conn.createStatement();
			String sql="insert into account values(?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql); 
			//ps.setString(1,numb);
			ps.setString(1,acc.getAccount());
			ps.setString(2,acc.getPassword());
			ps.setString(3,acc.getName());
			ps.setString(4,acc.getType());
			ps.executeUpdate();
			ps.close(); 
			stmt.close();
			conn.close();
			return true;
		}
		catch(Exception sqle){
			System.err.println(sqle);
			return false;
		} 	
	}
	
	
	public static List<Account> getList(String sql) {
		if (sql == null || sql.length() < 1)
			sql = "select * from account";
		List<Account> alList = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&amp:character=UTF-8","root","");
			Statement stmt=conn.createStatement();
			if (conn != null) {
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					Account acc = new Account();
					acc.setAccount(rs.getString(1));
					acc.setPassword(rs.getString(2));
					acc.setName(rs.getString(3));
					acc.setType(rs.getString(4));
					alList.add(acc);
				}
				rs.close();
			}
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alList;
	}
	
	public static int Delete(Account acc){
		try{
			String driverClassName = "com.mysql.jdbc.Driver";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&amp;character=UTF-8","root","");
			PreparedStatement ps;
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from account where Uid='"+acc.getAccount()+"' and Passwd='"+acc.getPassword()+"'");
			if (rs.next()){
				String sql="delete from account where Uid ='"+acc.getAccount()+"' and Passwd ='"+acc.getPassword()+"'";
				stmt.executeUpdate(sql);
				stmt.close();
				conn.close();
				return 1;
			}
			else{
				return 2;
			}
		}
		catch(Exception aqle){
				System.err.println(aqle);
				return 3;
			}
		
	}
	
	
	public static int Update(Account acc){
		try{
			String driverClassName = "com.mysql.jdbc.Driver";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&amp;character=UTF-8","root","");
			PreparedStatement ps;
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from account where Uid='"+acc.getAccount()+"'");
			if (rs.next()){
				String sql="Update account set Passwd='"+acc.getPassword()+"',Name='"+acc.getName()+"',Type='"+acc.getType()+"' where Uid='"+acc.getAccount()+"'";
				stmt.executeUpdate(sql);
				stmt.close();
				conn.close();
				return 1;
			}
			else{
				return 2;
			}
		}
		catch(Exception aqle){
				System.err.println(aqle);
				return 3;
			}
		
	}


}
