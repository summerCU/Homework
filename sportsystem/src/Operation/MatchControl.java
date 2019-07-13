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


	JFrame frame = new JFrame("设置比赛");
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
	
	JLabel label = new JLabel("单项最大报名人数");
	label.setBounds(86, 39, 120, 15);
	frame.getContentPane().add(label);	
	textField.setBounds(200, 36, 134, 21);
	frame.getContentPane().add(textField);
	textField.setColumns(10);
	
	
	JLabel label_1 = new JLabel("每小组人数");
	label_1.setBounds(86, 76, 120, 15);
	frame.getContentPane().add(label_1);
	textField_1.setBounds(200, 73, 134, 21);
	frame.getContentPane().add(textField_1);
	textField_1.setColumns(10);
	
	JLabel label_2 = new JLabel("团体总分名次");
	label_2.setBounds(86, 117, 120, 15);
	frame.getContentPane().add(label_2);
	textField_2.setBounds(200, 114, 134, 21);
	frame.getContentPane().add(textField_2);
	textField_2.setColumns(10);

	JLabel label_3 = new JLabel("最小报名人数");
	label_3.setBounds(86, 157, 120, 15);
	frame.getContentPane().add(label_3);
	textField_3.setBounds(200, 154, 134, 21);
	frame.getContentPane().add(textField_3);
	textField_3.setColumns(10);
	
/*设置按钮*/	
	
	
	/*更新比赛参数*/
	JButton button_0 = new JButton("设置");
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
					JOptionPane.showMessageDialog(MatchControl.this,"设置成功！");
					frame.dispose();
				}
				else
					JOptionPane.showMessageDialog(MatchControl.this,"设置失败！");
			}
			else{
				JOptionPane.showMessageDialog(MatchControl.this,"参数填写不能为空！");
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
	
	int Setpara(Bisaicanshu acc){

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&amp:character=UTF-8","root","");
			Statement stmt=conn.createStatement();
			String sql0="delete * from matchpara";		//先删除原来的设置
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
