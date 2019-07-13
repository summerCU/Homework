package Surface;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;

import MatchArrangement.Creatfinals;
import MatchArrangement.Groupingman;
import MatchArrangement.Groupingre;
import MatchArrangement.Startmatch;
import Operation.UpdateMypw;
import scoreSystem.Jdbctest;
import scoreSystem.Ranking;
import scoreSystem.Scoring;

public class Manager {


	private JFrame frame;
	private JTable table;

	public static String Maname;
	public static String Maid;
	
	public void Setname(String Name){
		this.Maname = Name;
	}
	public String Getname(){
		return this.Maname;
	}
	public void Setid(String Id){
		this.Maid = Id;
	}
	public String Getid(){
		return this.Maid;
	}


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manager window = new Manager();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
	
	public Manager() {
		initialize();
	}
	
	public void initialize() {
		frame = new JFrame("赛事管理员界面");		
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		//ImageIcon img = new ImageIcon("C:/Users/Summer_Cu/Desktop/1.jpg");//这是背景图片     
		ImageIcon img = new ImageIcon("src/img/1.jpg");//这是背景图片  
        JLabel imgLabel = new JLabel(img);//将背景图放在标签里。  
        frame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。     
        imgLabel.setBounds(0,0,1000,700);//设置背景标签的位置 
        frame.add(imgLabel);
        
        
        

/*菜单0 安排赛事*/
		
		JMenu menu_0 = new JMenu("人员分配");
		menuBar.add(menu_0);
		
		JMenuItem mntmNewMenuItem_00= new JMenuItem("男运动员分组");
		mntmNewMenuItem_00.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*该功能块主要为运动员进行分组*/
				Groupingman g = new Groupingman();
				g.Setsex("男");
				g.innitial();
			}
		});
		menu_0.add(mntmNewMenuItem_00);
		
		JMenuItem mntmNewMenuItem_02= new JMenuItem("女运动员分组");
		mntmNewMenuItem_02.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*该功能块主要为运动员进行分组*/
				Groupingman g = new Groupingman();
				g.Setsex("女");
				g.innitial();
			}
		});
		menu_0.add(mntmNewMenuItem_02);
		
		
		JMenuItem menuItem_01 = new JMenuItem("男子项目分配裁判");
		menuItem_01.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Groupingre g = new Groupingre();
				g.Setsex("男");
				g.innitial();
			}  	
		});
		menu_0.add(menuItem_01);
		
		
		JMenuItem menuItem_03 = new JMenuItem("女子项目分配裁判");
		menuItem_03.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Groupingre g = new Groupingre();
				g.Setsex("女");
				g.innitial();
			}  	
		});
		menu_0.add(menuItem_03);

		

/*初赛控制*/
		JMenu menu_3 = new JMenu("初赛控制");
				/*该功能块 主要为控制每个比赛的初赛开始*/
				JMenuItem menuItem_31 = new JMenuItem("男子项目开始初赛");
				menuItem_31.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Startmatch g = new Startmatch();
						g.Setsex("男");
						g.Setcj("初赛");
						g.innitial();
					}  	
				});
				menu_3.add(menuItem_31);
				
				
				JMenuItem menuItem_32 = new JMenuItem("女子项目开始初赛");
				menuItem_32.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Startmatch g = new Startmatch();
						g.Setsex("女");
						g.Setcj("初赛");
						g.innitial();
					}  	
				});
				menu_3.add(menuItem_32);
		
		menuBar.add(menu_3);
		

/*决赛名单生成*/
		JMenu menu_4 = new JMenu("生成决赛名单");
		JMenuItem menuItem_40 = new JMenuItem("生成男子项目决赛名单");
		menuItem_40.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*该功能块 生成决赛名单
				 * 注意只有进行了初赛的项目菜能生成决赛名单
				 * 该名单 生成后需要录入数据库
				 * 决赛的裁判与初赛可相同。。当然重新安排也可以
				 * */
				//Scoring S = new Scoring();
				preliminary_scoring();  			//先计算总分
				rankp();
				
				Creatfinals g = new Creatfinals();	//生成名单
				g.Setsex("男");
				g.innitial();
			}
		});
		menu_4.add(menuItem_40);
		
		JMenuItem menuItem_41 = new JMenuItem("女子项目决赛名单");
		menuItem_41.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Scoring S = new Scoring();
				//S.preliminary_scoring();  			//先计算总分

				preliminary_scoring();  			//先计算总分
				rankp();
				Creatfinals g = new Creatfinals();
				g.Setsex("女");
				g.innitial();
			}  	
		});
		menu_4.add(menuItem_41);
		
		
		
		menuBar.add(menu_4);
		

/*决赛控制*/
		JMenu menu_5 = new JMenu("决赛控制");
				/*该功能块 控制决赛的进行
				 * */
		JMenuItem menuItem_51 = new JMenuItem("男子项目开始决赛");
		menuItem_51.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Startmatch g = new Startmatch();
				g.Setsex("男");
				g.Setcj("决赛");
				g.innitial();
			}  	
		});
		menu_5.add(menuItem_51);
		
		
		JMenuItem menuItem_52 = new JMenuItem("女子项目开始决赛");
		menuItem_52.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Startmatch g = new Startmatch();
				g.Setsex("女");
				g.Setcj("决赛");
				g.innitial();
			}  	
		});
		menu_5.add(menuItem_52);
		
		menuBar.add(menu_5);
		

		
		
/*菜单一 修改自己账号密码*/
		JMenu menu_1 = new JMenu("修改密码");
		JMenuItem menuItem_20 = new JMenuItem("修改自己的密码");
		menuItem_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateMypw pw = new UpdateMypw();
				pw.Setacc(Maid);
				pw.UpdateMy();
				
				
			}
		});
		menu_1.add(menuItem_20);
		menuBar.add(menu_1);
/*菜单二 退出*/
		JMenu menu_2 = new JMenu("退出");
		JMenuItem menuItem_exit = new JMenuItem("确定退出");
		menu_2.add(menuItem_exit);
		menuItem_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				login a=new login();
			}
		});

		menuBar.add(menu_2);
		
		
		
				
	}//initialize
	
	private Container getLayeredPane() {
		// TODO Auto-generated method stub
		return null;
	}

	public void rankp() {
		String sql = "select * from preliminaries";
		ResultSet rs;
		try {
			String driverClassName = "com.mysql.jdbc.Driver";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&amp;character=UTF-8","root","");
			PreparedStatement ps;
			Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			//Jdbctest sm = new Jdbctest();
			//sm.jdbclink();
			//stmt = sm.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			//stmt = kkk.sm.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);
			
			rs.last();
			int m = rs.getRow();
			rs.first();
			int groupcount = 0;
			String[] groupid = new String[m];      //groupid[]存储不同小组
			for (int i = 0; i < m; i++) {
				boolean flag = true;
				if(groupcount==0) {
					groupid[groupcount] = rs.getString(16).trim();    
					groupcount = groupcount + 1;
					flag = false;
				}
				else {
					for(int j = 0; j < groupcount; j++) {
						if(rs.getString(16).trim().equals(groupid[j]))
							flag = false;
					}
				}
				if(flag) {
					groupid[groupcount] = rs.getString(16).trim();
					groupcount = groupcount + 1;
				}
				rs.next();
			}
			rs.first();
			for (int j = 0; j < groupcount; j++) {
				try{
					String driverClassName1 = "com.mysql.jdbc.Driver";
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&amp;character=UTF-8","root","");
					Statement stmt3=conn3.createStatement();
					sql = "select * from preliminaries where seq='"+ groupid[j] + "' order by score desc";
					ResultSet rs1;
					rs1 = stmt3.executeQuery(sql);	
					rs1.last();
					int n = rs1.getRow();
					int[] flag = new int[n] ;
					Arrays.fill(flag, 0);
					rs1.first();
					
					
					for(int i = 1; i < n; i++) {
						int pre = rs1.getInt(14);
						rs1.next();
						if(rs1.getInt(14) == pre)
							flag[i] = 1;				
					}
				
					int[] rank = new int[n];
					for(int i = 0; i < n; i++) {
						rank[i]=i+1;
						int k = i;
						while(flag[k] == 1) {
							rank[i] = rank[i] - 1;
							k--;
						}	
					}
					rs1.first();
					int count = 0;
					do {
						//rs1.updateInt("rank", rank[count]);
						//rs1.updateRow();
						int rank1 = rank[count];
						String matchtype = rs1.getString("matchtype");
						int age = rs1.getInt("age");
						String sex = rs.getString("sex");
						String name = rs1.getString("name");
						up(rank1,matchtype,age,sex,name);
						//System.out.println(rs1.getInt("rank")+"  "+rs1.getString(4)+"  "+rank[count]); 
						count = count + 1;
					}while(rs1.next());
				}catch(Exception aqle){
					System.err.println(aqle);
				}	
			}	
		}catch(Exception aqle){
			System.err.println(aqle);
		}
	}
	public void up(int rank1,String matchtype,int age,String sex,String name){
		try{
			String driverClassName1 = "com.mysql.jdbc.Driver";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&amp;character=UTF-8","root","");
			Statement stmt2=conn1.createStatement();
			String sqlup="Update preliminaries set rank='"+rank1+"' where matchtype='"+matchtype+"' and age='"+age+"' and sex='"+sex+"' and name='"+name+"'";
			stmt2.executeUpdate(sqlup);
			stmt2.close();
			conn1.close();
		}catch(Exception aqle){
			System.err.println(aqle);
		}	
	}
	
	public void preliminary_scoring() {
		
		String sql1 = "select * from preliminaries";
		ResultSet rs;
		
		try {//链接数据库获得原始记录表的结果集rs
			String driverClassName1 = "com.mysql.jdbc.Driver";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&amp;character=UTF-8","root","");
			Statement stmt=conn.createStatement();
			rs = stmt.executeQuery(sql1);
			rs.first();
			do{
				//获得最高及最低分以及未去掉最高最低分的总分
	        	int max = rs.getInt(7);       
	        	int min = rs.getInt(7);         
	        	int sum = 0;
	        	for(int i=7;i<=11;i++) {
	        		if(max < rs.getInt(i))
	        			max = rs.getInt(i);
	        		if(min > rs.getInt(i))
	        			min = rs.getInt(i);
	        		sum = sum + rs.getInt(i);
	        	}
	        	@SuppressWarnings("unused")
				int score;
	        	score = ((sum-min-max)/3)*5+rs.getInt(12)-rs.getInt(13);   //总分=去掉最高最低分的平均分*裁判数 + D分  - P分
	        	upscore(score,rs.getString("matchtype"),rs.getInt("age"),rs.getString("sex"),rs.getString("name"));
	        }while(rs.next());
			
			
		}catch(Exception aqle){
			System.err.println(aqle);
		}	
	}
	
	public void upscore(int score,String matchtype,int age,String sex,String name){
		try{
			String driverClassName1 = "com.mysql.jdbc.Driver";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&amp;character=UTF-8","root","");
			Statement stmt2=conn1.createStatement();
			String sqlup="Update preliminaries set score='"+score+"' where matchtype='"+matchtype+"' and age='"+age+"' and sex='"+sex+"' and name='"+name+"'";
			stmt2.executeUpdate(sqlup);
			stmt2.close();
			conn1.close();
		}catch(Exception aqle){
			System.err.println(aqle);
		}	
	}
	

}
