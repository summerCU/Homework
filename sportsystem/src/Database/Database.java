package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Database {
	private String driver = "com.mysql.jdbc.Driver";
	//URLָ��Ҫ���ʵ����ݿ���mydata
	private String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&amp:character=UTF-8";
	//MySQL����ʱ���û���
	private String user = "root";
	//MySQL����ʱ������
	private String password = "";
	public Connection conn;
	public Statement stmt;
	
	public void connecton(){
		try{
			Class.forName(driver);
			this.conn = DriverManager.getConnection(url,user,password);
			this.stmt=conn.createStatement();
			
		}
		catch(Exception sqle){
			System.err.println(sqle);
		} 	
	}
}
