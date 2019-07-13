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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import People.PReferee;

public class Startmatch extends JFrame{
	private JFrame frame;
	private String matchtype;
	private String age;
	private String sex;
	private String cj;
	public void Setsex(String sex){
		this.sex = sex;
	}
	public String Getsex(){
		return this.sex;
	}
	
	
	public void Setcj(String cj){
		this.cj = cj;
	}
	public String Getcj(){
		return this.cj;
	}
	
	public Startmatch(){
	}
	
	public void innitial(){
	JFrame frame = new JFrame(sex+"子项目开始"+cj);
	frame.setBounds(100, 100, 1000, 700);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	
	JPanel p1=new JPanel();
    p1.setBounds(0,5,1000,70);
	
	JLabel label = new JLabel("比赛项目");
	label.setBounds(0, 5, 50, 30);
	JComboBox comboBox=new JComboBox();
	comboBox.setBounds(60,5,100,30);
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
	label2.setBounds(450, 5, 50, 30);
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
	
	ImageIcon img = new ImageIcon("src/img/1.jpg");//这是背景图片  
    JLabel imgLabel = new JLabel(img);//将背景图放在标签里。  
    imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
    frame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。     
    imgLabel.setBounds(0,20,1000,700);//设置背景标签的位置 
    frame.add(imgLabel);
	
	
/*设置按钮*/	
	JButton button_0 = new JButton("开始"+cj);
	button_0.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//System.out.println(matchtype+"  "+age);
			int i = start(sex,matchtype,age,cj);
			if(i != 0){
				JOptionPane.showMessageDialog(Startmatch.this,sex+"子项目开始"+cj+"成功");
			}
			else{
				JOptionPane.showMessageDialog(Startmatch.this,"系统故障，请稍后在试！");
			}
			
		}
	});
	button_0.setBounds(750, 5, 90, 30);
	p1.add(button_0);
	
	JButton button_1 = new JButton("退出");
	button_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
		}
	});
	button_1.setBounds(950, 5, 90, 30);
	p1.add(button_1);
	frame.add(p1);
	frame.setVisible(true);
	
	


	}

	private static int start(String sex,String matchtype,String age,String type){
		int result=0;
		int i=0;
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&amp;character=UTF-8","root","");
			Statement stmt=conn.createStatement();
			Statement stmt2=conn.createStatement();
			String	sql = "select * from matchrefere where sex='"+sex+"' and matchtype='"+matchtype+"' and age='"+age+"'";
			if (conn != null) {
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()){
					if(type.equals("初赛")){
						String sql1="Update matchrefere set flag1=1 where sex='"+sex+"' and matchtype='"+matchtype+"' and age='"+age+"'";
						stmt2.executeUpdate(sql1);
						i=i+1;
					}
					else if(type.equals("决赛")){
						String sql2="Update matchrefere set flag2=1 where sex='"+sex+"' and matchtype='"+matchtype+"' and age='"+age+"'";
						stmt2.executeUpdate(sql2);
						i=i+1;
					}
				}
				rs.close();
			}
			stmt.close();
			stmt.close();
			conn.close();
			if(i!=0)	result=1;
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
	
	}
}
