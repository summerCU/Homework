/*
 * 数据库链接
 */

package scoreSystem;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbctest {            //jdbc:mysql://127.0.0.1/text

	public static final String url = "jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=utf-8&useSSL=false";  
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "root";  
    public static final String password = "";  

    public Connection conn = null;
    //private Statement stmt = null;
    public PreparedStatement pst = null;  
  
    public void jdbclink() {  
        try {  
            Class.forName(name);//指定连接类型  
            conn = DriverManager.getConnection(url, user, password);//获取连接  
           // stmt = conn.createStatement();
            //System.out.print(conn);
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public void close() {  
        try {  
            this.conn.close();  
            this.pst.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    } 
}
