package MatchArrangement;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import People.Manathlete;
import People.PReferee;
import Surface.Referee;

public class Groupingre extends JFrame{


	private JFrame frame;
	private String matchtype;
	private String age;
	private JTable table;
	private JLabel lblNewLabel;
	private String sex;
	public void Setsex(String sex){
		this.sex = sex;
	}
	public String Getsex(){
		return this.sex;
	}
	
	public Groupingre(){
	}
	
	
	public void innitial(){
	JFrame frame = new JFrame("为"+sex+"子项目分配裁判");
	frame.setBounds(100, 100, 1000, 700);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	
	JPanel p1=new JPanel();
    p1.setBounds(0,20,1000,70);
	frame.setVisible(true);
	JLabel label = new JLabel("比赛项目");
	label.setBounds(0, 20, 50, 30);
	JComboBox comboBox=new JComboBox();
	comboBox.setBounds(60,20,100,30);
	if(sex.equals("男")){
		comboBox.addItem("请选择比赛项目");  
		comboBox.addItem("单杠");  
		comboBox.addItem("双杠");  
		comboBox.addItem("吊环");  
		comboBox.addItem("跳马");
		comboBox.addItem("鞍马");  
		comboBox.addItem("蹦床");
		comboBox.addItem("自由体操");
    }
	else{
		comboBox.addItem("请选择比赛项目");  
		comboBox.addItem("高低杠");  
		comboBox.addItem("跳马");
		comboBox.addItem("平衡木");  
		comboBox.addItem("蹦床");
		comboBox.addItem("自由体操");
	}
    comboBox .addItemListener(new java.awt.event.ItemListener() {
    	public void itemStateChanged(java.awt.event.ItemEvent evt) {
    		matchtype = (String)comboBox.getSelectedItem();
    	}
    });
	p1.add(label);
	p1.add(comboBox);
	
	JLabel label2 = new JLabel("年龄段");
	label2.setBounds(450, 20, 50, 30);
	JComboBox comboBox2=new JComboBox();
	comboBox2.setBounds(510,20,100,30);
	comboBox2.addItem("请选择年龄段");  
    comboBox2.addItem("7-8");  
    comboBox2.addItem("9-10");  
    comboBox2.addItem("11-12");  
    comboBox2.addItemListener(new java.awt.event.ItemListener() {
    	public void itemStateChanged(java.awt.event.ItemEvent evt) {
    		age = (String)comboBox2.getSelectedItem();
    	}
    });
	p1.add(label2);
	p1.add(comboBox2);
	
	
	JPanel jp1 = new JPanel();
	jp1.setBounds(0,50,1000,450);
	jp1.setLayout(new BorderLayout(0, 0));
	jp1.setBorder(new EmptyBorder(30, 5, 5, 5));
	String Names[] = { "项目","年龄", "性别","姓名", "身份","序号"};
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
	jp1.add(scroll);
	frame.add(jp1);
	
/*设置按钮*/	
	JButton button_0 = new JButton("开始分配裁判");
	button_0.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//System.out.println(matchtype+"  "+age);
			int p = groupingreop(sex,matchtype,age,"裁判员");
			int j = groupingreop(sex,matchtype,age,"裁判长");
			List<PReferee> adList1 = new ArrayList<PReferee>();
			adList1 = getList(sex,matchtype,age);
			if(adList1.size() != 0){
				int i=0;
				for (PReferee a : adList1) {
					table.setValueAt(a.getMatch(), i, 0);
					table.setValueAt(a.getSex(), i, 1);
					table.setValueAt(a.getAge(), i, 2);
					table.setValueAt(a.getName(), i, 3);
					table.setValueAt(a.getType(), i, 4);
					table.setValueAt(Integer.toString(a.getSeq()), i, 5);
					i++;
				}		
			}
			
			if(p != 0 && (j != 0)){
				JOptionPane.showMessageDialog(Groupingre.this,"分配成功！");
			}
			else{
				JOptionPane.showMessageDialog(Groupingre.this,"系统故障，请稍后在试！");
			}
			
		}
	});
	button_0.setBounds(750, 20, 90, 30);
	p1.add(button_0);
	
	JButton button_1 = new JButton("退出");
	button_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
		}
	});
	button_1.setBounds(950, 20, 90, 30);
	p1.add(button_1);
	frame.add(p1);
	}
	public static List<PReferee> getList(String sex,String matchtype,String age) {
		List<PReferee> alList = new ArrayList<>();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&amp;character=UTF-8","root","");
				Statement stmt=conn.createStatement();
				String	sql = "select * from matchrefere where sex='"+sex+"' and matchtype='"+matchtype+"' and age ='"+age+"'";
				if (conn != null) {
					ResultSet rs = stmt.executeQuery(sql);
					while (rs.next()) {
						PReferee acc = new PReferee();
						acc.setMatch(rs.getString(1));
						acc.setAge(rs.getString(2));
						acc.setSex(rs.getString(3));
						acc.setName(rs.getString(4));
						acc.setType(rs.getString(6));
						acc.setSeq(rs.getInt(9));
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
	}
	
	
	private static int groupingreop(String sex,String matchtype,String age,String type){

		int result=0;
		int i=0,j=0;
		int rand;
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&amp;character=UTF-8","root","");
			Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String	sql = "select * from refere where identity='"+type+"'";	//
			if (conn != null) {
				ResultSet rs = stmt.executeQuery(sql);
				rs.last();//移到最后一行
				int length = rs.getRow();
				rs.beforeFirst();//移到初始位置
				//System.out.println(length);
				if(type.equals("裁判员")){
					SecureRandom random = new SecureRandom();
					rand = random.nextInt(length-6);
				}else{
					SecureRandom random = new SecureRandom();
					rand = random.nextInt(length-1);
				}
				while (rs.next()) {
					if(type.equals("裁判员")){
						if(i<5&&j>rand){
							PReferee R = new PReferee();
							R.setName(rs.getString(1));
							R.setIDNum(rs.getString(2));
							R.setType(type);
							R.setMatch(matchtype);
							R.setSex(sex);
							R.setAge(age);
							result = InsertRE(R,i+1);
							i=i+1;
						}
					}
					else if(type.equals("裁判长")){
						if(j==rand){
							PReferee R = new PReferee();
							R.setName(rs.getString(1));
							R.setIDNum(rs.getString(2));
							R.setType(type);
							R.setMatch(matchtype);
							R.setSex(sex);
							R.setAge(age);
							result = InsertRE(R,10);
						}
					}
					j=j+1;
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
	
	private static int InsertRE (PReferee M,int seq){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&amp;character=UTF-8","root","");
			Statement stmt=conn.createStatement();	
			System.out.println(M.getAge()+M.getSex()+M.getMatch());
			if(seq==1){
				int j = 0;
				ResultSet rs1=stmt.executeQuery("select * from matchrefere where sex='"+M.getSex()+"' and matchtype='"+M.getMatch()+"' and age='"+M.getAge()+"'");
				while(rs1.next()){
					j++;
				}
				if(j!=0){
					String sql2="delete from matchrefere where sex='"+M.getSex()+"' and matchtype='"+M.getMatch()+"' and age='"+M.getAge()+"'";
					stmt.executeUpdate(sql2);
				}
			}
			
			String sql="insert into matchrefere values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql); 
			ps.setString(1,M.getMatch());
			ps.setString(2,M.getAge());
			ps.setString(3,M.getSex());
			ps.setString(4,M.getName());
			ps.setString(5,M.getID());
			ps.setString(6,M.getType());
			ps.setInt(7,0);
			ps.setInt(8,0);
			ps.setInt(9,seq);
			//System.out.println(M.getName()+"  "+i+"  "+M.getMatch()+M.getSex());
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
