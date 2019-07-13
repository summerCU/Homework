package Team_UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Team_UI.Getconnection1;

public class re_mes extends JFrame implements ActionListener,MouseListener{
	public static final String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false";  
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "root";  
    public static final String password = "";  
    
    public static Connection conn = null;  
  	Getconnection1 Getconnection = new Getconnection1();
  	
	JPanel jp1 = new JPanel();
	JPanel jp2 = new JPanel();
	JLabel rename = new JLabel("姓名");
	JTextField rename1 = new JTextField(15);
	JLabel reid = new JLabel("裁判ID");
	JTextField reid1 = new JTextField(15);
	JLabel identity = new JLabel("身份");
	JTextField identity1 = new JTextField(15);
	JButton confirm= new JButton("确定");
	JButton cancel= new JButton("取消");

	
	public re_mes() {
		super("信息录入");
		setup();
		confirm.addActionListener(this);
		cancel.addActionListener(this);
		this.setVisible(true);
		setSize(450, 300);
	}

	private void setup() {
		jp1.add(rename);
		jp1.add(rename1);
		jp1.add(reid);
		jp1.add(reid1);
		jp1.add(identity);
		jp1.add(identity1);
		jp1.setLayout(new FlowLayout());
		jp2.add(confirm);
		jp2.add(cancel);	
		add(jp1);
		add(jp2);
		this.setLayout(new GridLayout(2,1));	
		this.pack();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirm) {
			int q=Getconnection.insertre(rename1.getText(), reid1.getText(), identity1.getText());
			if(q==1){
				JOptionPane.showMessageDialog(re_mes.this, "添加成功！");
			}
	    }
		else if (e.getSource() == cancel){
			this.setVisible(false);
		}
	}
}