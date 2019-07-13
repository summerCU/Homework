package Operation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import People.Account;

public class UpdateMypw extends JFrame{

	JFrame frame = new JFrame("修改密码");
	JTextField textField = new JTextField();
	JPasswordField textField_1 = new JPasswordField();
	JPasswordField textField_2 = new JPasswordField();
	JPasswordField textField_3 = new JPasswordField();
	
	public String account;
	public void Setacc(String acc){
		this.account=acc;
	}
	public void UpdateMy(){
	frame.setBounds(100, 100, 600, 500);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	
	JLabel label = new JLabel("账号");
	label.setBounds(86, 39, 54, 15);
	frame.getContentPane().add(label);	
	textField.setBounds(200, 36, 134, 21);
	frame.getContentPane().add(textField);
	textField.setColumns(10);
	
	textField.setText(account);
	textField.setEditable(false);
	
	
	JLabel label_1 = new JLabel("旧密码");
	label_1.setBounds(86, 76, 54, 15);
	frame.getContentPane().add(label_1);
	textField_1.setBounds(200, 73, 134, 21);
	frame.getContentPane().add(textField_1);
	textField_1.setColumns(10);
	
	JLabel label_2 = new JLabel("新密码");
	label_2.setBounds(86, 117, 54, 15);
	frame.getContentPane().add(label_2);
	textField_2.setBounds(200, 114, 134, 21);
	frame.getContentPane().add(textField_2);
	textField_2.setColumns(10);

	JLabel label_3 = new JLabel("请重新输入新密码");
	label_3.setBounds(86, 157, 120, 15);
	frame.getContentPane().add(label_3);
	textField_3.setBounds(200, 154, 134, 21);
	frame.getContentPane().add(textField_3);
	textField_3.setColumns(10);
	
/*设置按钮*/	
	
	
	
	JButton button_0 = new JButton("修改");
	button_0.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int j;
			Account account=new Account();
			account.setAccount(textField.getText());
			account.setPassword(textField_1.getText());
			account.setName(textField_2.getText());
			account.setType(textField_3.getText());
			if((account.getAccount()!=null)&&(account.getName().equals(account.getType()))){
				j = Updatepw(account);
				if(j==1)
					JOptionPane.showMessageDialog(UpdateMypw.this,"修改成功！");
				else
					JOptionPane.showMessageDialog(UpdateMypw.this,"原始密码错误！");
			}
			else if(account.getAccount()==null){
				JOptionPane.showMessageDialog(UpdateMypw.this,"账号密码不能为空！");
			}
			else if(!(account.getName().equals(account.getType()))){
				JOptionPane.showMessageDialog(UpdateMypw.this,"两次输入的新密码不同！");
			}
			else{
				
			}
		}
	});
	button_0.setBounds(86, 250, 93, 23);
	frame.getContentPane().add(button_0);
	
	
	JButton button_1 = new JButton("取消");
	button_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
		}
	});
	button_1.setBounds(198, 250, 93, 23);
	frame.getContentPane().add(button_1);
	frame.setVisible(true);
	}
	
	
	public int Updatepw(Account acc){
		try{
			String driverClassName = "com.mysql.jdbc.Driver";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&amp;character=UTF-8","root","");
			PreparedStatement ps;
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from account where Uid='"+acc.getAccount()+"' and Passwd='"+acc.getPassword()+"'");
			if (rs.next()){
				String sql="Update account set Passwd='"+acc.getName()+"',Name='"+rs.getString(3)+"',Type='"+rs.getString(4)+"' where Uid='"+acc.getAccount()+"'";
				stmt.executeUpdate(sql);
				stmt.close();
				conn.close();
				return 1;
			}
			else{
				System.out.println(acc.getAccount());
				return 2;
			}
		}
		catch(Exception aqle){
				System.err.println(aqle);
				return 3;
			}
		
	
	}
	public void Select(Account acc){}


}
