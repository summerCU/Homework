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
		
		
		frame = new JFrame("ϵͳ����Ա�˺�");
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

/*�˵�0 ����˺�*/
		JMenu menu_0 = new JMenu("����˺�");
		menuBar.add(menu_0);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("��Ӵ�����˺�");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Addaccount(1);
			}
		});
		menu_0.add(mntmNewMenuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("��Ӳ����˺�");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Addaccount(2);
			}  	
		});
		menu_0.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("��Ӳ��г��˺�");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Addaccount(3);
			}  	
		});
		menu_0.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("������¹���Ա�˺�");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Addaccount(4);
			}  	
		});
		menu_0.add(menuItem_3);
		
		JMenuItem menuItem_4 = new JMenuItem("����ο��˺�");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Addaccount(5);
			}  	
		});
		menu_0.add(menuItem_4);
		
		
		
/*�˵�һ�޸��˺�*/		
		JMenu menu_1 = new JMenu("�༭�˺�");
		menuBar.add(menu_1);
		
		JMenuItem menuItem_10 = new JMenuItem("�༭�˺�");
		menuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				UpdateAcc updateacc = new UpdateAcc();
			}
		});
		menu_1.add(menuItem_10);
		

/*�˵��� �޸��Լ��˺�����*/
		JMenu menu_2 = new JMenu("�޸�����");
		JMenuItem menuItem_20 = new JMenuItem("�޸��Լ�������");
		menuItem_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*����һ���� ʵ���޸��Լ����˺�����Ĺ���
				 * ��Ҫ����ԭ����
				 * �Լ�����ȷ�������롣��֤����ȷ��*/
				UpdateMypw pw = new UpdateMypw();
				pw.Setacc(Adid);
				pw.UpdateMy();
				
				
			}
		});
		menu_2.add(menuItem_20);
		menuBar.add(menu_2);

/*�˵��� ˢ��*/
		JMenu menu_3 = new JMenu("ˢ��");
		
		JMenuItem menuItem_30 = new JMenuItem("ˢ��������Ϣ");
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
				lblNewLabel.setText("����" + adList1.size() + "����¼��");
			}
		});
		/*menu_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				show();
				System.out.println("xxxxxxxxxxxx!");
			}  	
		});*/		

		menuBar.add(menu_3);
		
		
		JMenu menu_4 = new JMenu("��������");
		JMenuItem menuItem_40 = new JMenuItem("���ñ�������");
		menu_4.add(menuItem_40);
		menuItem_40.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MatchControl a=new MatchControl();
			}
		});

		menuBar.add(menu_4);


/*�˵��� �˳�*/
		JMenu menu_5 = new JMenu("�˳�");
		JMenuItem menuItem_exit = new JMenuItem("ȷ���˳�");
		menu_5.add(menuItem_exit);
		menuItem_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				login a=new login();
			}
		});

		menuBar.add(menu_5);

/*�˵�������*/		

		
//��ʾ���

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		lblNewLabel = new JLabel("״̬��");
		
		JScrollPane scrollPane = new JScrollPane();	//������
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setRowHeight(30);
		table.setModel(new DefaultTableModel(new Object[][] {},new String[] {"�˺�", "����", "����", "���"}));
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
		lblNewLabel.setText("����" + adList.size() + "����¼��");
		panel.add(lblNewLabel);
		scrollPane.setViewportView(table);
		
		
	
		
				
	}//initialize
	
	public void Addaccount(int a){
		String accountType;
		switch(a){
		case 1:
			accountType = "�����";
		    break;
		case 2:
			accountType = "����Ա";
		    break;
		case 3:
			accountType = "���г�";
		    break;
		case 4:
			accountType = "���¹���Ա";
		    break;
		case 5:
			accountType = "�ο�";
		    break;
		default:
		    accountType = "NULL";
		    break;
		}
		
		frame = new JFrame("����˺�");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("����");
		label.setBounds(86, 39, 54, 15);
		frame.getContentPane().add(label);	
		textField = new JTextField();
		textField.setBounds(157, 36, 134, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("�˺�");
		label_1.setBounds(86, 76, 54, 15);
		frame.getContentPane().add(label_1);
		textField_1 = new JTextField();
		textField_1.setBounds(157, 73, 134, 21);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_2 = new JLabel("����");
		label_2.setBounds(86, 117, 54, 15);
		frame.getContentPane().add(label_2);
		textField_2 = new JTextField();
		textField_2.setBounds(157, 114, 134, 21);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		
		JButton button = new JButton("ȷ��");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Account account=new Account();
				account.setName(textField.getText());
				account.setAccount(textField_1.getText());
				account.setPassword(textField_2.getText());
				account.setType(accountType);
				if (!Adminop.add(account)) {
					JOptionPane.showMessageDialog(frame, "���ʧ��", "warning", JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(frame,Admin.this,"��ӳɹ���", 1);
					frame.dispose();	
				}
				}
		});
		button.setBounds(86, 210, 93, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("ȡ��");
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
	
