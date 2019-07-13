package Surface;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Operation.Adminop;
import Operation.MatchControl;
import Operation.UpdateAcc;
import Operation.UpdateMypw;
import People.Account;





public class Admin {
	public static String Adid;
	private JFrame frame;
	private JLabel lblNewLabel;
	private Box b1, b2;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JFrame frm; 
	public JTable table;
	
	public int i=0;
	public static String Adname;
	
	
	public void Setname(String Name){
		this.Adname = Name;
	}
	public String Getname(){
		return this.Adname;
	}
	public void Setid(String Id){
		this.Adid = Id;
	}
	public String Getid(){
		return this.Adid;
	}


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin window = new Admin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
	
	public Admin() {
		initialize();
	}
	
	public void initialize() {
		
		
		frame = new JFrame("系统管理员账号");
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

/*菜单0 添加账号*/
		JMenu menu_0 = new JMenu("添加账号");
		menuBar.add(menu_0);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("添加代表队账号");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Addaccount(1);
			}
		});
		menu_0.add(mntmNewMenuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("添加裁判账号");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Addaccount(2);
			}  	
		});
		menu_0.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("添加裁判长账号");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Addaccount(3);
			}  	
		});
		menu_0.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("添加赛事管理员账号");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Addaccount(4);
			}  	
		});
		menu_0.add(menuItem_3);
		
		JMenuItem menuItem_4 = new JMenuItem("添加游客账号");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Addaccount(5);
			}  	
		});
		menu_0.add(menuItem_4);
		
		
		
/*菜单一修改账号*/		
		JMenu menu_1 = new JMenu("编辑账号");
		menuBar.add(menu_1);
		
		JMenuItem menuItem_10 = new JMenuItem("编辑账号");
		menuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				UpdateAcc updateacc = new UpdateAcc();
			}
		});
		menu_1.add(menuItem_10);
		

/*菜单三 修改自己账号密码*/
		JMenu menu_2 = new JMenu("修改密码");
		JMenuItem menuItem_20 = new JMenuItem("修改自己的密码");
		menuItem_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*在这一部分 实现修改自己的账号密码的功能
				 * 需要输入原密码
				 * 以及两次确定新密码。保证其正确性*/
				UpdateMypw pw = new UpdateMypw();
				pw.Setacc(Adid);
				pw.UpdateMy();
				
				
			}
		});
		menu_2.add(menuItem_20);
		menuBar.add(menu_2);

/*菜单四 刷新*/
		JMenu menu_3 = new JMenu("刷新");
		
		JMenuItem menuItem_30 = new JMenuItem("刷新所有信息");
		menu_3.add(menuItem_30);
		menuItem_30.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				tableModel.getDataVector().removeAllElements(); 
				tableModel.setRowCount(0);
				List<Account> adList1 = new ArrayList<Account>();
				adList1 = Adminop.getList(null);
				for (Account a : adList1) {
					tableModel.addRow(a.toStringArr());
				}
				lblNewLabel.setText("载入" + adList1.size() + "条记录！");
			}
		});
		/*menu_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				show();
				System.out.println("xxxxxxxxxxxx!");
			}  	
		});*/		

		menuBar.add(menu_3);
		
		
		JMenu menu_4 = new JMenu("比赛设置");
		JMenuItem menuItem_40 = new JMenuItem("设置比赛参数");
		menu_4.add(menuItem_40);
		menuItem_40.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MatchControl a=new MatchControl();
			}
		});

		menuBar.add(menu_4);


/*菜单四 退出*/
		JMenu menu_5 = new JMenu("退出");
		JMenuItem menuItem_exit = new JMenuItem("确定退出");
		menu_5.add(menuItem_exit);
		menuItem_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				login a=new login();
			}
		});

		menuBar.add(menu_5);

/*菜单栏结束*/		

		
//显示表格

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		lblNewLabel = new JLabel("状态栏");
		
		JScrollPane scrollPane = new JScrollPane();	//滚动条
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setRowHeight(30);
		table.setModel(new DefaultTableModel(new Object[][] {},new String[] {"账号", "密码", "名字", "身份"}));
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(30);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.getColumnModel().getColumn(3).setPreferredWidth(30);
		 
		
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
	
		List<Account> adList = new ArrayList<Account>();
		adList = Adminop.getList(null);
		for (Account a : adList) {
			tableModel.addRow(a.toStringArr());
		}
		lblNewLabel.setText("载入" + adList.size() + "条记录！");
		panel.add(lblNewLabel);
		scrollPane.setViewportView(table);
		
		
	
		
				
	}//initialize
	
	public void Addaccount(int a){
		String accountType;
		switch(a){
		case 1:
			accountType = "代表队";
		    break;
		case 2:
			accountType = "裁判员";
		    break;
		case 3:
			accountType = "裁判长";
		    break;
		case 4:
			accountType = "赛事管理员";
		    break;
		case 5:
			accountType = "游客";
		    break;
		default:
		    accountType = "NULL";
		    break;
		}
		
		frame = new JFrame("添加账号");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("名称");
		label.setBounds(86, 39, 54, 15);
		frame.getContentPane().add(label);	
		textField = new JTextField();
		textField.setBounds(157, 36, 134, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("账号");
		label_1.setBounds(86, 76, 54, 15);
		frame.getContentPane().add(label_1);
		textField_1 = new JTextField();
		textField_1.setBounds(157, 73, 134, 21);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_2 = new JLabel("密码");
		label_2.setBounds(86, 117, 54, 15);
		frame.getContentPane().add(label_2);
		textField_2 = new JTextField();
		textField_2.setBounds(157, 114, 134, 21);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		
		JButton button = new JButton("确定");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Account account=new Account();
				account.setName(textField.getText());
				account.setAccount(textField_1.getText());
				account.setPassword(textField_2.getText());
				account.setType(accountType);
				if (!Adminop.add(account)) {
					JOptionPane.showMessageDialog(frame, "添加失败", "warning", JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(frame,Admin.this,"添加成功！", 1);
					frame.dispose();	
				}
				}
		});
		button.setBounds(86, 210, 93, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("取消");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		button_1.setBounds(198, 210, 93, 23);
		frame.getContentPane().add(button_1);
		frame.setVisible(true);
	}
	
	
	
}
	
