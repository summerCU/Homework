package Team_UI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

public class Getconnection {

		public static final String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false";  
	    public static final String uname = "com.mysql.jdbc.Driver";  
	    public static final String user = "root";  
	    public static final String password = "";  
	  
	    public static Connection conn = null;  
	    public PreparedStatement pst = null;  
	  
	    public Getconnection() { 
	    }  
	    
	    public int insert(String name,String IDnum,String age,String sex,String team,String teamid,String match){
		    try {  
	            Class.forName(uname);//指定连接类型  
	            conn = DriverManager.getConnection(url, user, password);//获取连接   
	            Statement stmt=conn.createStatement();
				String sql="Insert into athlete (name,IDnum,age,sex,team,teamid,match) values(?,?,?,?,?,?,?)";
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1,name);
				ps.setString(2,IDnum);
				ps.setString(3,age);
				ps.setString(4,sex);
				ps.setString(5,team);
				ps.setString(6,teamid);
				ps.setString(7,match);
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
					String sql="Insert into refere (name,reid,identity) values(?,?,?)";
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



