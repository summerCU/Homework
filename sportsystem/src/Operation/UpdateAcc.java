package Operation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import People.Account;
import Surface.Admin;


public class UpdateAcc extends JFrame {
	JFrame frame = new JFrame("�༭�˺�");
	JTextField textField = new JTextField();
	JTextField textField_1 = new JTextField();
	JTextField textField_2 = new JTextField();
	JTextField textField_3 = new JTextField();
	public UpdateAcc(){
	frame.setBounds(100, 100, 600, 500);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	
	JLabel label = new JLabel("�˺�");
	label.setBounds(86, 39, 54, 15);
	frame.getContentPane().add(label);	
	textField.setBounds(157, 36, 134, 21);
	frame.getContentPane().add(textField);
	textField.setColumns(10);
	
	JLabel label_1 = new JLabel("����");
	label_1.setBounds(86, 76, 54, 15);
	frame.getContentPane().add(label_1);
	textField_1.setBounds(157, 73, 134, 21);
	frame.getContentPane().add(textField_1);
	textField_1.setColumns(10);
	
	JLabel label_2 = new JLabel("����");
	label_2.setBounds(86, 117, 54, 15);
	frame.getContentPane().add(label_2);
	textField_2.setBounds(157, 114, 134, 21);
	frame.getContentPane().add(textField_2);
	textField_2.setColumns(10);

	JLabel label_3 = new JLabel("����");
	label_3.setBounds(86, 157, 54, 15);
	frame.getContentPane().add(label_3);
	textField_3.setBounds(157, 154, 134, 21);
	frame.getContentPane().add(textField_3);
	textField_3.setColumns(10);
	
/*���ð�ť*/	
	JButton button_2 = new JButton("����");
	button_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Account account=new Account();
			account.setName(textField_2.getText());
			account.setAccount(textField.getText());
			account.setPassword(textField_1.getText());
			account.setType(textField_3.getText());
			Select(account);
			}
	});
	button_2.setBounds(80, 250, 70, 23);
	frame.getContentPane().add(button_2);
	
	
	JButton button_3 = new JButton("ɾ��");
	button_3.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int j;
			Account account=new Account();
			account.setName(textField_2.getText());
			account.setAccount(textField.getText());
			account.setPassword(textField_1.getText());
			account.setType(textField_3.getText());
			if((account.getAccount()!=null)&&(account.getPassword()!=null)){
				j = Adminop.Delete(account);
				if(j==1){
					JOptionPane.showMessageDialog(UpdateAcc.this,"ɾ���ɹ���");
					textField.setText(null);
					textField_1.setText(null);
					textField_2.setText(null);
					textField_3.setText(null);
				}
				else if(j==3)
					JOptionPane.showMessageDialog(UpdateAcc.this,"ϵͳ���ϣ����Ժ����ԣ�");
				else
					JOptionPane.showMessageDialog(UpdateAcc.this,"���˺Ų����ڣ�");
			}
			else{
				JOptionPane.showMessageDialog(UpdateAcc.this,"�˺����벻��Ϊ�գ�");
			}
		}
	});
	button_3.setBounds(170, 250, 70, 23);
	frame.getContentPane().add(button_3);
	
	
	JButton button_0 = new JButton("�޸�");
	button_0.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int j;
			Account account=new Account();
			account.setName(textField_2.getText());
			account.setAccount(textField.getText());
			account.setPassword(textField_1.getText());
			account.setType(textField_3.getText());
			if((account.getAccount()!=null)&&(account.getPassword()!=null)){
				j = Adminop.Update(account);
				if(j==1)
					JOptionPane.showMessageDialog(UpdateAcc.this,"�޸ĳɹ���");
				else if(j==3)
					JOptionPane.showMessageDialog(UpdateAcc.this,"ϵͳ���ϣ����Ժ����ԣ�");
				else
					JOptionPane.showMessageDialog(UpdateAcc.this,"���˶�Ա�����ڣ�");
			}
			else{
				JOptionPane.showMessageDialog(UpdateAcc.this,"�˺����벻��Ϊ�գ�");
			}
		}
	});
	button_0.setBounds(260, 250, 70, 23);
	frame.getContentPane().add(button_0);
	
	
	JButton button_1 = new JButton("�ر�");
	button_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
		}
	});
	button_1.setBounds(350, 250, 70, 23);
	frame.getContentPane().add(button_1);
	frame.setVisible(true);
	}
	
	
	
	public void Select(Account acc){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&amp;character=UTF-8","root","");
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from account where Uid='"+acc.getAccount()+"'");
		
			if(rs.next())
			{
				textField.setText(rs.getString("Uid"));
				textField_1.setText(rs.getString("Passwd"));
				textField_2.setText(rs.getString("Name"));
				textField_3.setText(rs.getString("Type"));
				rs.close();
				stmt.close();
				conn.close();
			}
			else
				JOptionPane.showMessageDialog(UpdateAcc.this,"���˺Ų����ڣ�");
		}
		catch(Exception sqle){
				System.err.println(sqle);
				JOptionPane.showMessageDialog(UpdateAcc.this,"ϵͳ���ϣ����Ժ����ԣ�");
		}
	}
}
