package Team_UI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

public class Getconnection1 {

		public static final String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false";  
	    public static final String uname = "com.mysql.jdbc.Driver";  
	    public static final String user = "root";  
	    public static final String password = "";  
	  
	    public static Connection conn = null;  
	    public PreparedStatement pst = null;  
	  
	    public Getconnection1() { 
	    }  
	    
	    public int insert(String name,String IDnum,int age,String sex,String team,String teamid,String match,int score1,int rank1,int score2,int rank2){
		    try {  
	            Class.forName(uname);//指定连接类型  
	            conn = DriverManager.getConnection(url, user, password);//获取连接   
	            Statement stmt=conn.createStatement();
				String sql="Insert into athlete values(?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1,name);
				ps.setString(2,IDnum);
				ps.setInt(3,age);
				ps.setString(4,sex);
				ps.setString(5,team);
				ps.setString(6,teamid);
				ps.setString(7,match);
				ps.setInt(8, score1);
				ps.setInt(9, rank1);
				ps.setInt(10, score2);
				ps.setInt(11, rank2);
				ps.executeUpdate();
				ps.close(); 
				stmt.close();
				conn.close();
				return 1;
		}
			catch(Exception sqle){
					System.err.println(sqle);
					return 2;
				}
	    }
	    
	    public int insertre(String rename,String reid,String identity){
	    	 try {  
		            Class.forName(uname);//指定连接类型  
		            conn = DriverManager.getConnection(url, user, password);//获取连接   
		            Statement stmt=conn.createStatement();
					String sql="Insert into refere values(?,?,?)";
					PreparedStatement ps=conn.prepareStatement(sql);
					ps.setString(1,rename);
					ps.setString(2,reid);
					ps.setString(3,identity);
					ps.executeUpdate();
					ps.close(); 
					stmt.close();
					conn.close();
					return 1;
			}
				catch(Exception sqle){
						System.err.println(sqle);
						return 2;
					}
	    }
}



