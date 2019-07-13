package Team_UI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class athlete_ui extends JFrame implements ActionListener,MouseListener{

	public static final String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "";
	
	String Names[] = { "姓名", "序号", "年龄", "性别", "队伍名称","队伍ID","比赛项目" };
	DefaultTableModel defaultTableModel = new DefaultTableModel(Names, 20);
	JTable table = new JTable(defaultTableModel){
		public boolean isCellEditable(int rowIndex, int ColIndex){
         return true;
        }    
	} ;
	JLabel name1 = new JLabel("姓名");
	JTextField name2 = new JTextField(15);
	JLabel sname = new JLabel("序号");
	JTextField sname1 = new JTextField(15);
	JLabel sex = new JLabel("性别");
	JTextField sex1 = new JTextField(15);
	JLabel age = new JLabel("年龄");
	JTextField age1 = new JTextField(15);
	JLabel dept = new JLabel("院系");
	JTextField dept1 = new JTextField(15);
	JScrollPane scroll = new JScrollPane();
	Getconnection1 Getconnection = new Getconnection1();
	
	JPanel jp1 = new JPanel();
	JPanel jp2 = new JPanel();
	JButton confirm= new JButton("确定");
	JButton cancel= new JButton("取消");
	
	public athlete_ui() {
		super("信息录入");
		setup();
		confirm.addActionListener(this);
		cancel.addActionListener(this);
		table.setModel(defaultTableModel);
		table.setVisible(true);
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scroll.setViewportView(table);
		this.setVisible(true);
		setSize(750, 500);
	}
	
	private void setup() {
		jp1.setLayout(new BorderLayout(0, 0));
		jp1.setBorder(new EmptyBorder(30, 5, 5, 5));
		jp1.add(scroll);
		jp2.add(confirm);
		jp2.add(cancel);
		add(jp1);
		add(jp2);
		this.setLayout(new GridLayout(2,1));
		this.pack();
	}

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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirm) {
			int i=0;int q=0;
			while((String)table.getModel().getValueAt(i,0)!=null){
				String name2=(String)table.getModel().getValueAt(i, 0);
				String IDnum2=(String)table.getModel().getValueAt(i, 1);
				int age2= Integer.parseInt((String)table.getModel().getValueAt(i, 2));
				String sex2=(String)table.getModel().getValueAt(i, 3);
				String team2=(String)table.getModel().getValueAt(i, 4);
				String teamid2=(String)table.getModel().getValueAt(i, 5);
				String match2=(String)table.getModel().getValueAt(i, 6);
				q = Getconnection.insert(name2, IDnum2, age2, sex2, team2, teamid2, match2,-1,-1,-1,-1);
			i++;
			}
			if(q==1){
				JOptionPane.showMessageDialog(athlete_ui.this, "添加成功！");
			}
		}
		else if (e.getSource() == cancel){
			this.setVisible(false);
		}
	}
	
	

}
