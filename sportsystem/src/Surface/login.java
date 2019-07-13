package Surface;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Operation.CheckUser;


public class login {
	JFrame f = new JFrame("体操赛事计分系统");
	JTextField name = new JTextField(20);
	JPasswordField mima = new JPasswordField(20);
	private static String loadtype;
	public login(){
		f.setBounds(450,200,600,450);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setLayout(null);
		
		JLabel label1=new JLabel("欢迎登陆体操赛事计分系统！");
		JLabel label2=new JLabel("账    号：");
		JLabel label3=new JLabel("密    码：");
		JLabel label4=new JLabel("登陆身份：");
		JComboBox comboBox=new JComboBox();
		comboBox.addItem("请选择登陆身份");  
        comboBox.addItem("系统管理员");  
        comboBox.addItem("代表队");  
        comboBox.addItem("赛事管理员");  
        comboBox.addItem("裁判员");
        comboBox.addItem("裁判长");  
        comboBox.addItem("游客"); 
        
        comboBox .addItemListener(new java.awt.event.ItemListener() {
        	public void itemStateChanged(java.awt.event.ItemEvent evt) {
        		loadtype = (String)comboBox.getSelectedItem();
        	}
        });
        
        
		label1.setFont(new Font("Serif",Font.BOLD,20));
        label1.setBounds(150,0,300,40);
		f.add(label1);
		
        label2.setFont(new Font("Serif",Font.BOLD,15));
		label3.setFont(new Font("Serif",Font.BOLD,15));
		label4.setFont(new Font("Serif",Font.BOLD,15));
		JButton button1=new JButton("登录");
		JButton button2=new JButton("取消");
	    BHandler h=new BHandler();
		button1.addActionListener(h);
		button2.addActionListener(h);

		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		JPanel p3=new JPanel();
		JPanel p4=new JPanel();
		JPanel p5=new JPanel();
     	p1.setBounds(50,70,380,30);
		p1.add(label2);
		p1.add(name);

		p2.setBounds(50,120,380,50);
		p2.add(label3);
	    p2.add(mima);
			  
	    p3.setBounds(25,170,380,50);
		p3.add(label4);
		p3.add(comboBox);
		
	    p4.setBounds(70,220,190,50);
		p4.add(button1);
		
		p5.setBounds(190,220,190,50);
		p5.add(button2);
		
		f.add(p1);
		f.add(p2);
		f.add(p3);
		f.add(p4);
		f.add(p5);
		f.setVisible(true);
	}


	public static void main(String[] args) {  
		login a=new login();
		}

    public class BHandler implements ActionListener {
		@Override
		public void actionPerformed (ActionEvent event){
			String na = (String)name.getText();
			String mi = (String)mima.getText();
			if(event.getActionCommand()=="登录"){
				if(na!=null&&loadtype!=null){
					int loadres;
					loadres = CheckUser.Checkid(na,mi,loadtype);
					if(loadres == 1 ){
						f.dispose();
					}
					else if(loadres == -1){
						JOptionPane.showMessageDialog(f,login.this,"系统故障，请稍后在试！",0);
					}
					else
						JOptionPane.showMessageDialog(f,login.this,"卡号或密码不正确",0);	
				}
				else if(loadtype==null){
					JOptionPane.showMessageDialog(f,login.this,"未选择登陆身份",0);
				}
				else{
					JOptionPane.showMessageDialog(f,login.this,"账号不能为空",0);
				}
			}
			else
				f.dispose();
		}
  	}
}
