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
	JMenu menu_0 = new JMenu("¼����Ϣ");
	JMenuItem mntmNewMenuItem_00= new JMenuItem("¼�������Ϣ");
	JMenuItem menuItem_01 = new JMenuItem("¼���ҽ��Ϣ");
	JMenuItem menuItem_02 = new JMenuItem("¼�����Ա��Ϣ");
	JMenuItem menuItem_03 = new JMenuItem("¼�����Ա��Ϣ");
	JMenuItem menuItem_04 = new JMenuItem("¼���˹�Ա��Ϣ");
	JMenu menu_1 = new JMenu("�ϴ��ļ�");
	JMenuItem menuItem_10 = new JMenuItem("ѡ���ļ�");
	JMenu menu_3 = new JMenu("�޸�����");
	JMenuItem menuItem_30 = new JMenuItem("�޸��Լ�������");
	JMenu menu_4 = new JMenu("�˳�");
	JMenuItem menuItem_exit = new JMenuItem("ȷ���˳�");
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
		super("��ӭ"+Tname);
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
	        jfc.showDialog(new JLabel(), "ѡ��");  
	        File file=jfc.getSelectedFile();  
	        if(file.isDirectory()){  
	        	JOptionPane.showMessageDialog(Team.this,"�ļ���:"+file.getAbsolutePath()+ "   �ϴ��ɹ���");
	            //System.out.println("�ļ���:"+file.getAbsolutePath());  
	        }else if(file.isFile()){  
	        	JOptionPane.showMessageDialog(Team.this,"�ļ�:"+file.getAbsolutePath()+ "   �ϴ��ɹ���");
	            //System.out.println("�ļ�:"+file.getAbsolutePath());  
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
	
/*�˵�0 ¼����Ϣ*/
		/*��1����ӺͶ�ҽ����Ϣ�����������֤���绰��
��2���˶�Ա��Ϣ�����������֤�����䣬����˶�Ա�Ļ��ɼ��������ȱ��������Ϻ���ר����Ա¼�룩������ı�����Ŀ����ѡ����
��3������Ա��Ϣ���������绰���Ա����֤�ţ�
��4������Ա��Ϣ�����������֤���롢�绰��
*/

/*�˵�һ �ϴ��ļ�*/		


				
	//initialize
		
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}
}
