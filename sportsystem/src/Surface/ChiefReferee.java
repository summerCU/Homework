package Surface;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Operation.UpdateMypw;

public class ChiefReferee {
	public static final String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "";
	
	private JFrame frame,frame1,frame2;
	private JTable table;
	
	public static String CRname;
	public static String CRid;
	
	public int rowIndex=0;
	public String aname="";
	
	public void Setname(String Name){
		this.CRname = Name;
	}
	public String Getname(){
		return this.CRname;
	}
	public void Setid(String Id){
		this.CRid = Id;
	}
	public String Getid(){
		return this.CRid;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChiefReferee window = new ChiefReferee();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
	
	
	public void initialize() {
		String rename0=Getname();
		frame = new JFrame("总裁判裁判界面");		
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		ImageIcon img = new ImageIcon("src/img/1.jpg");//这是背景图片  
        JLabel imgLabel = new JLabel(img);//将背景图放在标签里。  
        frame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。     
        imgLabel.setBounds(0,0,1000,700);//设置背景标签的位置 
        frame.add(imgLabel);

/*菜单0 打分*/
		
		JMenu menu_0 = new JMenu("审核打分");
		JMenuItem menuItem_00 = new JMenuItem("初赛打分");
		menuItem_00.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame1 = new JFrame("初赛裁判长打分");
				frame1.setBounds(100, 100, 1000, 800);
				frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame1.setVisible(true);
				JPanel jp1 = new JPanel();
				JPanel jp2 = new JPanel();
				String Names[] = { "姓名", "年龄", "性别", "比赛项目" ,"得分1","得分2","得分3","得分4","得分5","D分","P分"};
				DefaultTableModel defaultTableModel = new DefaultTableModel(Names, 20);
				JTable table = new JTable(defaultTableModel){
					public boolean isCellEditable(int rowIndex, int ColIndex){
			         return false;
			        }    
				} ;
				JScrollPane scroll = new JScrollPane();
				table.setModel(defaultTableModel);
				table.setVisible(true);
				table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scroll.setViewportView(table);
				JLabel D2 = new JLabel("D分");
				JTextField D = new JTextField(15);
				JLabel P2 = new JLabel("P分");
				JTextField P = new JTextField(15);
				JButton confirm = new JButton("确定");
				JButton exit = new JButton("退出");
				jp1.setLayout(new BorderLayout(0, 0));
				jp1.setBorder(new EmptyBorder(30, 5, 5, 5));			
				jp1.add(scroll);
				jp2.add(D2);
				jp2.add(D);
				jp2.add(P2);
				jp2.add(P);
				jp2.add(confirm);
				jp2.add(exit);
				frame1.add(jp1);
				frame1.add(jp2);
				frame1.setLayout(new FlowLayout());
				
				
				try {
					Class.forName(name);// 指定连接类型
					Connection conn = DriverManager.getConnection(url, user, password);// 获取连接
					Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					ResultSet rs0 = stmt.executeQuery("select matchtype,age,sex,seq,flag1 from matchrefere where name='"+rename0+"'");
					int i = 0;
					String matchtype = "";
					String age="";
					String sex="";
					int seq=0;
					int age1=0,age2=0,flag1=0;
					while(rs0.next()){
						matchtype = rs0.getString("matchtype");
						age=rs0.getString("age");
						sex=rs0.getString("sex");
						seq=rs0.getInt("seq");
						flag1=rs0.getInt("flag1");
						switch(age){
							case "7-8":age1=7;age2=8;
								break;
							case "9-10":age1=9;age2=10;
								break;
							case "11-12":age1=11;age2=12;
								break;
							default:break;
						}
					if(rs0.getInt("flag1")==1){
						try{
							Class.forName(name);// 指定连接类型
							Connection conn1 = DriverManager.getConnection(url, user, password);// 获取连接
							Statement stmt1 = conn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
							ResultSet rs = stmt1.executeQuery("select * from preliminaries  where matchtype='"+matchtype+"' and sex='"+sex+"' and age in ('"+age1+"','"+age2+"')");
							while (rs.next()) {
								table.setValueAt(rs.getString("name"), i, 0);
								table.setValueAt(Integer.toString(rs.getInt("age")), i, 1);
								table.setValueAt(rs.getString("sex"), i, 2);
								table.setValueAt(rs.getString("matchtype"), i, 3);
								table.setValueAt(rs.getString("score1"), i, 4);
								table.setValueAt(rs.getString("score2"), i, 5);
								table.setValueAt(rs.getString("score3"), i, 6);
								table.setValueAt(rs.getString("score4"), i, 7);
								table.setValueAt(rs.getString("score5"), i, 8);
								i++;
							}
							rs.close();
							stmt1.close();
							conn1.close();
						}catch (Exception e1) {
							System.err.println(e1);
						}
					}
					}
					rs0.close();
					stmt.close();
					conn.close();
				} catch (Exception e1) {
					System.err.println(e1);
				}
				table.addMouseListener(new MouseAdapter(){ 
						public void mouseClicked(MouseEvent e) {
						    rowIndex = table.getSelectedRow();
							aname = (String)table.getValueAt(rowIndex, 0);
						}
				});
				confirm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						table.setValueAt(D.getText(), rowIndex, 9);
						table.setValueAt(P.getText(), rowIndex, 10);
						int D1=Integer.parseInt(D.getText());
						int P1=Integer.parseInt(P.getText());
						String matchtype=(String)table.getModel().getValueAt(rowIndex, 3);
						String sex=(String)table.getModel().getValueAt(rowIndex, 2);
						int age=Integer.parseInt((String)table.getModel().getValueAt(rowIndex, 1));
						try {
							Class.forName(name);// 指定连接类型
							Connection conn = DriverManager.getConnection(url, user, password);// 获取连接
							Statement stmt = conn.createStatement();
							stmt.executeUpdate("update preliminaries set D="+D1+",P="+P1+" where name='"+aname+"' and matchtype='"+matchtype+"' and sex='"+sex+"' and age='"+age+"'");
							stmt.close();
							conn.close();
						} catch (Exception e1) {
							System.err.println(e1);
						}
					}
				});
				exit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame1.dispose();
					}
			});
			}
		});
		
		
		
		
		menu_0.add(menuItem_00);	
		JMenuItem menuItem_01 = new JMenuItem("决赛打分");
		menuItem_01.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame2 = new JFrame("决赛裁判长打分");
				frame2.setBounds(100, 100, 1000, 800);
				frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame2.setVisible(true);
				JPanel jp1 = new JPanel();
				JPanel jp2 = new JPanel();
				String Names[] = { "姓名", "年龄", "性别", "比赛项目" ,"得分1","得分2","得分3","得分4","得分5","D分","P分"};
				DefaultTableModel defaultTableModel = new DefaultTableModel(Names, 20);
				JTable table = new JTable(defaultTableModel){
					public boolean isCellEditable(int rowIndex, int ColIndex){
			         return false;
			        }    
				} ;
				JScrollPane scroll = new JScrollPane();
				table.setModel(defaultTableModel);
				table.setVisible(true);
				table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scroll.setViewportView(table);
				JLabel D2 = new JLabel("D分");
				JTextField D = new JTextField(15);
				JLabel P2 = new JLabel("P分");
				JTextField P = new JTextField(15);
				JButton confirm = new JButton("确定");
				JButton exit = new JButton("退出");
				jp1.setLayout(new BorderLayout(0, 0));
				jp1.setBorder(new EmptyBorder(30, 5, 5, 5));			
				jp1.add(scroll);
				jp2.add(D2);
				jp2.add(D);
				jp2.add(P2);
				jp2.add(P);
				jp2.add(confirm);
				jp2.add(exit);
				frame2.add(jp1);
				frame2.add(jp2);
				frame2.setLayout(new FlowLayout());
				try {
					Class.forName(name);// 指定连接类型
					Connection conn = DriverManager.getConnection(url, user, password);// 获取连接
					Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					ResultSet rs0 = stmt.executeQuery("select matchtype,age,sex,seq,flag1 from matchrefere where name='"+rename0+"'");
					int i = 0;
					String matchtype = "";
					String age="";
					String sex="";
					int seq=0;
					int age1=0,age2=0,flag2=0;
					while(rs0.next()){
						matchtype = rs0.getString("matchtype");
						age=rs0.getString("age");
						sex=rs0.getString("sex");
						seq=rs0.getInt("seq");
						flag2=rs0.getInt("flag2");
						switch(age){
							case "7-8":age1=7;age2=8;
								break;
							case "9-10":age1=9;age2=10;
								break;
							case "11-12":age1=11;age2=12;
								break;
							default:break;
						}
					if(rs0.getInt("flag1")==1){
						try{
							Class.forName(name);// 指定连接类型
							Connection conn1 = DriverManager.getConnection(url, user, password);// 获取连接
							Statement stmt1 = conn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
							ResultSet rs = stmt1.executeQuery("select * from preliminaries  where matchtype='"+matchtype+"' and sex='"+sex+"' and age in ('"+age1+"','"+age2+"')");
							while (rs.next()) {
								table.setValueAt(rs.getString("name"), i, 0);
								table.setValueAt(Integer.toString(rs.getInt("age")), i, 1);
								table.setValueAt(rs.getString("sex"), i, 2);
								table.setValueAt(rs.getString("matchtype"), i, 3);
								table.setValueAt(rs.getString("score1"), i, 4);
								table.setValueAt(rs.getString("score2"), i, 5);
								table.setValueAt(rs.getString("score3"), i, 6);
								table.setValueAt(rs.getString("score4"), i, 7);
								table.setValueAt(rs.getString("score5"), i, 8);
								i++;
							}
							rs.close();
							stmt1.close();
							conn1.close();
						}catch (Exception e1) {
							System.err.println(e1);
						}
					}
					}
					rs0.close();
					stmt.close();
					conn.close();
				} catch (Exception e1) {
					System.err.println(e1);
				}
				table.addMouseListener(new MouseAdapter(){ 
						public void mouseClicked(MouseEvent e) {
						    rowIndex = table.getSelectedRow();
							aname = (String)table.getValueAt(rowIndex, 0);
						}
				});
				confirm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						table.setValueAt(D.getText(), rowIndex, 9);
						table.setValueAt(P.getText(), rowIndex, 10);
						int D1=Integer.parseInt(D.getText());
						int P1=Integer.parseInt(P.getText());
						String matchtype=(String)table.getModel().getValueAt(rowIndex, 3);
						String sex=(String)table.getModel().getValueAt(rowIndex, 2);
						int age=Integer.parseInt((String)table.getModel().getValueAt(rowIndex, 1));
						try {
							Class.forName(name);// 指定连接类型
							Connection conn = DriverManager.getConnection(url, user, password);// 获取连接
							Statement stmt = conn.createStatement();
							stmt.executeUpdate("update finals set D="+D1+",P="+P1+" where name='"+aname+"' and matchtype='"+matchtype+"' and sex='"+sex+"' and age='"+age+"'");
							stmt.close();
							conn.close();
						} catch (Exception e1) {
							System.err.println(e1);
						}
					}
				});
				exit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame2.dispose();
					}
			});
			}
		});
		menu_0.add(menuItem_01);
		menuBar.add(menu_0);
		

		
		
/*菜单一 修改自己账号密码*/
		JMenu menu_2 = new JMenu("修改密码");
		JMenuItem menuItem_20 = new JMenuItem("修改自己的密码");
		menuItem_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*在这一部分 实现修改自己的账号密码的功能
				 * 需要输入原密码
				 * 以及两次确定新密码。保证其正确性*/
				UpdateMypw pw = new UpdateMypw();
				pw.Setacc(CRid);
				pw.UpdateMy();
				
				
			}
		});
		menu_2.add(menuItem_20);
		menuBar.add(menu_2);

/*菜单二 退出*/
		JMenu menu_3 = new JMenu("退出");
		JMenuItem menuItem_exit = new JMenuItem("确定退出");
		menuItem_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				login a=new login();
			}
		});
		menu_3.add(menuItem_exit);
		menuBar.add(menu_3);

				
	}//initialize
	
	public void Addaccount(int a){}

}
