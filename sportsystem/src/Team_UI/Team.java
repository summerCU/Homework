package Team_UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Operation.UpdateMypw;
import People.Account;
import Surface.login;

public class Team extends JFrame implements ActionListener,MouseListener{
	private JLabel lblNewLabel;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	JMenuBar menuBar = new JMenuBar();
	JMenu menu_0 = new JMenu("录入信息");
	JMenuItem mntmNewMenuItem_00= new JMenuItem("录入领队信息");
	JMenuItem menuItem_01 = new JMenuItem("录入队医信息");
	JMenuItem menuItem_02 = new JMenuItem("录入教练员信息");
	JMenuItem menuItem_03 = new JMenuItem("录入裁判员信息");
	JMenuItem menuItem_04 = new JMenuItem("录入运功员信息");
	JMenu menu_1 = new JMenu("上传文件");
	JMenuItem menuItem_10 = new JMenuItem("选择文件");
	JMenu menu_3 = new JMenu("修改密码");
	JMenuItem menuItem_30 = new JMenuItem("修改自己的密码");
	JMenu menu_4 = new JMenu("退出");
	JMenuItem menuItem_exit = new JMenuItem("确定退出");
	private JTable table;
	public static String teamname;
	public static String teamid;
	
	public void Setname(String Name){
		this.teamname = Name;
	}
	public String Getname(){
		return this.teamname;
	}
	
	public void Setid(String Id){
		this.teamid = Id;
	}
	public String Getid(){
		return this.teamid;
	}
	
	public Team(String Tname) {
		super("欢迎"+Tname);
		setup();
		setSize(750, 500);
		setVisible(true);
		mntmNewMenuItem_00.addActionListener(this);
		menuItem_01.addActionListener(this);
		menuItem_02.addActionListener(this);
		menuItem_03.addActionListener(this);
		menuItem_04.addActionListener(this);
		menuItem_30.addActionListener(this);
		menuItem_10.addActionListener(this);
		menuItem_exit.addActionListener(this);
	}
	
	private void setup() {
		menuBar.add(menu_0);
		menuBar.add(menu_1);
		menuBar.add(menu_3);
		menuBar.add(menu_4);
		menu_0.add(mntmNewMenuItem_00);
		menu_0.add(menuItem_01);
		menu_0.add(menuItem_02);
		menu_0.add(menuItem_03);
		menu_0.add(menuItem_04);
		menu_1.add(menuItem_10);
		menu_3.add(menuItem_30);
		menu_4.add(menuItem_exit);
		this.setJMenuBar(menuBar);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.pack();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==menuItem_02|e.getSource()==menuItem_01|e.getSource()==mntmNewMenuItem_00){
			 message mes=new message();
		}
		else if(e.getSource()==menuItem_03){
			 re_mes cui=new re_mes();
		}
		else if(e.getSource()==menuItem_04){
			 athlete_ui athui=new athlete_ui();
		}
		else if(e.getSource()==menuItem_10){
			JFileChooser jfc=new JFileChooser();  
	        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
	        jfc.showDialog(new JLabel(), "选择");  
	        File file=jfc.getSelectedFile();  
	        if(file.isDirectory()){  
	        	JOptionPane.showMessageDialog(Team.this,"文件夹:"+file.getAbsolutePath()+ "   上传成功！");
	            //System.out.println("文件夹:"+file.getAbsolutePath());  
	        }else if(file.isFile()){  
	        	JOptionPane.showMessageDialog(Team.this,"文件:"+file.getAbsolutePath()+ "   上传成功！");
	            //System.out.println("文件:"+file.getAbsolutePath());  
	        }  
	        //System.out.println(jfc.getSelectedFile().getName()); 
		}
		else if(e.getSource()==menuItem_30){
			UpdateMypw pw = new UpdateMypw();
			pw.Setacc(teamid);
			pw.UpdateMy();
		}
		else if(e.getSource()==menuItem_exit){
			this.dispose();
			login a=new login();
		}
		
	}
	
/*菜单0 录入信息*/
		/*（1）领队和队医的信息：姓名、身份证、电话；
（2）运动员信息：姓名、身份证、年龄，组别、运动员文化成绩（此项空缺，比赛完毕后有专门人员录入），参与的比赛项目（多选）；
（3）教练员信息：姓名、电话、性别、身份证号；
（4）裁判员信息：姓名、身份证号码、电话。
*/

/*菜单一 上传文件*/		


				
	//initialize
		
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}
}
