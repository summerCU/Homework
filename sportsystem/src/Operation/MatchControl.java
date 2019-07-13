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

import People.Bisaicanshu;

public class MatchControl extends JFrame{


	JFrame frame = new JFrame("���ñ���");
	JTextField textField = new JTextField();
	JTextField textField_1 = new JTextField();
	JTextField textField_2 = new JTextField();
	JTextField textField_3 = new JTextField();
	
	public MatchControl(){
		innitial();
	}
	
	public void innitial(){
	frame.setBounds(100, 100, 600, 500);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	
	JLabel label = new JLabel("�������������");
	label.setBounds(86, 39, 120, 15);
	frame.getContentPane().add(label);	
	textField.setBounds(200, 36, 134, 21);
	frame.getContentPane().add(textField);
	textField.setColumns(10);
	
	
	JLabel label_1 = new JLabel("ÿС������");
	label_1.setBounds(86, 76, 120, 15);
	frame.getContentPane().add(label_1);
	textField_1.setBounds(200, 73, 134, 21);
	frame.getContentPane().add(textField_1);
	textField_1.setColumns(10);
	
	JLabel label_2 = new JLabel("�����ܷ�����");
	label_2.setBounds(86, 117, 120, 15);
	frame.getContentPane().add(label_2);
	textField_2.setBounds(200, 114, 134, 21);
	frame.getContentPane().add(textField_2);
	textField_2.setColumns(10);

	JLabel label_3 = new JLabel("��С��������");
	label_3.setBounds(86, 157, 120, 15);
	frame.getContentPane().add(label_3);
	textField_3.setBounds(200, 154, 134, 21);
	frame.getContentPane().add(textField_3);
	textField_3.setColumns(10);
	
/*���ð�ť*/	
	
	
	/*���±�������*/
	JButton button_0 = new JButton("����");
	button_0.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int j;
			Bisaicanshu parameter=new Bisaicanshu();
			parameter.setMaxsize(Integer.parseInt (textField.getText()));
			parameter.setMatchsize(Integer.parseInt (textField_1.getText()));
			parameter.setTeamscore(Integer.parseInt ((textField_2.getText())));
			parameter.setMinsize(Integer.parseInt ((textField_3.getText())));
			if((textField.getText()!=null)&&(textField_1.getText()!=null)&&(textField_2.getText()!=null)&&(textField_3.getText()!=null)){
				j = Setpara(parameter);
				if(j==1){
					JOptionPane.showMessageDialog(MatchControl.this,"���óɹ���");
					frame.dispose();
				}
				else
					JOptionPane.showMessageDialog(MatchControl.this,"����ʧ�ܣ�");
			}
			else{
				JOptionPane.showMessageDialog(MatchControl.this,"������д����Ϊ�գ�");
			}
		}
	});
	button_0.setBounds(86, 250, 93, 23);
	frame.getContentPane().add(button_0);
	
	
	JButton button_1 = new JButton("ȡ��");
	button_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
		}
	});
	button_1.setBounds(198, 250, 93, 23);
	frame.getContentPane().add(button_1);
	frame.setVisible(true);
	}
	
	int Setpara(Bisaicanshu acc){

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&amp:character=UTF-8","root","");
			Statement stmt=conn.createStatement();
			String sql0="delete * from matchpara";		//��ɾ��ԭ��������
			PreparedStatement ps0=conn.prepareStatement(sql0); 
			String sql="insert into matchpara values(?,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql); 
			ps.setInt(1,0);
			ps.setInt(2,acc.getMaxsize());
			ps.setInt(3,acc.getMatchsize());
			ps.setInt(4,acc.getTeamscore());
			ps.setInt(5,acc.getMinsize());
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
