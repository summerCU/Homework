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
		frame = new JFrame("���¹���Ա����");		
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		//ImageIcon img = new ImageIcon("C:/Users/Summer_Cu/Desktop/1.jpg");//���Ǳ���ͼƬ     
		ImageIcon img = new ImageIcon("src/img/1.jpg");//���Ǳ���ͼƬ  
        JLabel imgLabel = new JLabel(img);//������ͼ���ڱ�ǩ�  
        frame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//ע�������ǹؼ�����������ǩ��ӵ�jfram��LayeredPane����     
        imgLabel.setBounds(0,0,1000,700);//���ñ�����ǩ��λ�� 
        frame.add(imgLabel);
        
        
        

/*�˵�0 ��������*/
		
		JMenu menu_0 = new JMenu("��Ա����");
		menuBar.add(menu_0);
		
		JMenuItem mntmNewMenuItem_00= new JMenuItem("���˶�Ա����");
		mntmNewMenuItem_00.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*�ù��ܿ���ҪΪ�˶�Ա���з���*/
				Groupingman g = new Groupingman();
				g.Setsex("��");
				g.innitial();
			}
		});
		menu_0.add(mntmNewMenuItem_00);
		
		JMenuItem mntmNewMenuItem_02= new JMenuItem("Ů�˶�Ա����");
		mntmNewMenuItem_02.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*�ù��ܿ���ҪΪ�˶�Ա���з���*/
				Groupingman g = new Groupingman();
				g.Setsex("Ů");
				g.innitial();
			}
		});
		menu_0.add(mntmNewMenuItem_02);
		
		
		JMenuItem menuItem_01 = new JMenuItem("������Ŀ�������");
		menuItem_01.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Groupingre g = new Groupingre();
				g.Setsex("��");
				g.innitial();
			}  	
		});
		menu_0.add(menuItem_01);
		
		
		JMenuItem menuItem_03 = new JMenuItem("Ů����Ŀ�������");
		menuItem_03.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Groupingre g = new Groupingre();
				g.Setsex("Ů");
				g.innitial();
			}  	
		});
		menu_0.add(menuItem_03);

		

/*��������*/
		JMenu menu_3 = new JMenu("��������");
				/*�ù��ܿ� ��ҪΪ����ÿ�������ĳ�����ʼ*/
				JMenuItem menuItem_31 = new JMenuItem("������Ŀ��ʼ����");
				menuItem_31.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Startmatch g = new Startmatch();
						g.Setsex("��");
						g.Setcj("����");
						g.innitial();
					}  	
				});
				menu_3.add(menuItem_31);
				
				
				JMenuItem menuItem_32 = new JMenuItem("Ů����Ŀ��ʼ����");
				menuItem_32.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Startmatch g = new Startmatch();
						g.Setsex("Ů");
						g.Setcj("����");
						g.innitial();
					}  	
				});
				menu_3.add(menuItem_32);
		
		menuBar.add(menu_3);
		

/*������������*/
		JMenu menu_4 = new JMenu("���ɾ�������");
		JMenuItem menuItem_40 = new JMenuItem("����������Ŀ��������");
		menuItem_40.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*�ù��ܿ� ���ɾ�������
				 * ע��ֻ�н����˳�������Ŀ�������ɾ�������
				 * ������ ���ɺ���Ҫ¼�����ݿ�
				 * �����Ĳ������������ͬ������Ȼ���°���Ҳ����
				 * */
				//Scoring S = new Scoring();
				preliminary_scoring();  			//�ȼ����ܷ�
				rankp();
				
				Creatfinals g = new Creatfinals();	//��������
				g.Setsex("��");
				g.innitial();
			}
		});
		menu_4.add(menuItem_40);
		
		JMenuItem menuItem_41 = new JMenuItem("Ů����Ŀ��������");
		menuItem_41.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Scoring S = new Scoring();
				//S.preliminary_scoring();  			//�ȼ����ܷ�

				preliminary_scoring();  			//�ȼ����ܷ�
				rankp();
				Creatfinals g = new Creatfinals();
				g.Setsex("Ů");
				g.innitial();
			}  	
		});
		menu_4.add(menuItem_41);
		
		
		
		menuBar.add(menu_4);
		

/*��������*/
		JMenu menu_5 = new JMenu("��������");
				/*�ù��ܿ� ���ƾ����Ľ���
				 * */
		JMenuItem menuItem_51 = new JMenuItem("������Ŀ��ʼ����");
		menuItem_51.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Startmatch g = new Startmatch();
				g.Setsex("��");
				g.Setcj("����");
				g.innitial();
			}  	
		});
		menu_5.add(menuItem_51);
		
		
		JMenuItem menuItem_52 = new JMenuItem("Ů����Ŀ��ʼ����");
		menuItem_52.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Startmatch g = new Startmatch();
				g.Setsex("Ů");
				g.Setcj("����");
				g.innitial();
			}  	
		});
		menu_5.add(menuItem_52);
		
		menuBar.add(menu_5);
		

		
		
/*�˵�һ �޸��Լ��˺�����*/
		JMenu menu_1 = new JMenu("�޸�����");
		JMenuItem menuItem_20 = new JMenuItem("�޸��Լ�������");
		menuItem_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateMypw pw = new UpdateMypw();
				pw.Setacc(Maid);
				pw.UpdateMy();
				
				
			}
		});
		menu_1.add(menuItem_20);
		menuBar.add(menu_1);
/*�˵��� �˳�*/
		JMenu menu_2 = new JMenu("�˳�");
		JMenuItem menuItem_exit = new JMenuItem("ȷ���˳�");
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
			String[] groupid = new String[m];      //groupid[]�洢��ͬС��
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
		
		try {//�������ݿ���ԭʼ��¼��Ľ����rs
			String driverClassName1 = "com.mysql.jdbc.Driver";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&amp;character=UTF-8","root","");
			Statement stmt=conn.createStatement();
			rs = stmt.executeQuery(sql1);
			rs.first();
			do{
				//�����߼���ͷ��Լ�δȥ�������ͷֵ��ܷ�
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
	        	score = ((sum-min-max)/3)*5+rs.getInt(12)-rs.getInt(13);   //�ܷ�=ȥ�������ͷֵ�ƽ����*������ + D��  - P��
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
