package scoreSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;


import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.transform.Result;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import Surface.login;

import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

//�ɼ���ѯ���洰����
public class kkk {

	private JFrame frame;
	
	//table,table_1,table_2�ֱ�Ϊ����ɼ��������ֱܷ�����ȫ���ֱܷ�
	private static JTable table;                         
	private static JTable table_1;
	private static JTable table_2;
	
	public static Jdbctest sm = new Jdbctest();
	
	//id �ǻ�ȡҪ��ѯ���˶�Ա����Ŀ��ŵ��ı���
	private JTextField id;
	private static JScrollPane scrollPane;
	
	
	/**
	 * Launch the application.
	 */
	public static kkk window;
	public static void main(String[] args) {
		
		//�������ݿ�
		sm.jdbclink();
		
		//Scoring�Ǽ��㵥��ɼ��������ܷ֣�����ȫ���ֵܷ���
		Scoring s = new Scoring();
		s.final_scoring();
		s.player_total_scoring();
		s.team_total_scoring();
		
		//Ranking�Ǽ�����������������ȫ����������
		Ranking r = new Ranking();
		r.player_ranking();
		r.team_ranking();
		r.ranking();
		
		s.team_total_scoring();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new kkk();
					//kkk window = new kkk();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public kkk() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u51B3\u8D5B\u6210\u7EE9\u67E5\u8BE2");
		frame.setBounds(100, 100, 590, 489);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//check_way��ѯ��ʽ��������
		JComboBox check_way = new JComboBox();
		check_way.setModel(new DefaultComboBoxModel(new String[] {"\u8FD0\u52A8\u5458\u67E5\u8BE2", "\u9879\u76EE\u67E5\u8BE2"}));
		check_way.setBounds(10, 406, 93, 27);
		frame.getContentPane().add(check_way);
		
		//check��ť�ǲ�ѯ��ť
		JButton check = new JButton("\u67E5\u8BE2");
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id.getText().equals("")) 
					JOptionPane.showMessageDialog(null, "����дҪ��ѯ���˶�Ա����Ŀ��ţ�");
				else {
					String sport_or_player_id = id.getText();            //��ȡid�ı������ݼ�check_way�������Index
					int checkway = check_way.getSelectedIndex(); 
					//�����ѯ������
					setTable(new JTable());
					Table_Demo td = new Table_Demo();
					td.create_table(checkway,sport_or_player_id);
					scrollPane.setViewportView(getTable());	
				}
			}
		});
		check.setBounds(314, 406, 65, 27);
		frame.getContentPane().add(check);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 556, 349);
		frame.getContentPane().add(tabbedPane);
		
		//����ɼ����
		scrollPane = new JScrollPane();
		tabbedPane.addTab("\u5355\u9879\u6210\u7EE9", null, scrollPane, null);
		
		setTable(new JTable());
		Table_Demo td = new Table_Demo();
		td.create_table(2,"11");
		scrollPane.setViewportView(getTable());	
		
		//�����ֱܷ��
		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("\u56E2\u4F53\u603B\u5206", null, scrollPane_1, null);
		
		setTable_1(new JTable());
		td.create_table_1();
		//table_1 = new JTable();
		scrollPane_1.setViewportView(getTable_1());
		
		//����ȫ���ֱܷ��
		JScrollPane scrollPane_2 = new JScrollPane();
		tabbedPane.addTab("\u4E2A\u4EBA\u5168\u80FD\u603B\u5206", null, scrollPane_2, null);
		
		setTable_2(new JTable());
		td.create_table_2();
		//table_2 = new JTable();
		scrollPane_2.setViewportView(getTable_2());
		
		JLabel lblNewLabel = new JLabel("\u67E5\u8BE2\u65B9\u5F0F\uFF1A");
		lblNewLabel.setBounds(10, 369, 65, 27);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u9879\u76EE\u6216\u8FD0\u52A8\u5458\u7F16\u53F7\uFF1A");
		lblNewLabel_1.setBounds(129, 369, 124, 27);
		frame.getContentPane().add(lblNewLabel_1);
		
		id = new JTextField();
		id.setBounds(129, 406, 155, 27);
		frame.getContentPane().add(id);
		id.setColumns(10);
		
		//btnNewButton�ǲ�ѯ����÷�����İ�ť
		JButton btnNewButton = new JButton("\u5177\u4F53\u5F97\u5206\u60C5\u51B5");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tabbedPane.getSelectedIndex();     //��ȡ��ǰ��ǩ����񣩵�Index
				if (index ==0) {                         //��ǰ���Ϊ����ɼ���ʱ
					int rowNum = table.getSelectedRow();                  //����б�ѡ�е���
	    			if (rowNum == -1) {   //���δ�б�ѡ�е���ʱ
	    				JOptionPane.showMessageDialog(null, "��ѡ��һ��","����Ի���",JOptionPane.WARNING_MESSAGE);  
	    				return;
	    			}
	    			else {//��ȡѡ���е��˶�Ա����Ŀ��Ų���ѯ�������÷����
	    				String sportid = table.getModel().getValueAt(rowNum, 0).toString();
	    				String playerid = table.getModel().getValueAt(rowNum, 1).toString();
	    				String sql = "select * from finals where IDnum='" + playerid + "' and matchtype='" + sportid + "'";
	    				//System.out.print(sql);
	    				Detail d = new Detail(sql, index);
	    				d.a(sql, index);
	    			}
				}
				else if (index == 1) {             //��ǰ���Ϊ�����ֱܷ�ʱ
					int rowNum = table_1.getSelectedRow();                  //����б�ѡ�е���
	    			if (rowNum == -1) {
	    				JOptionPane.showMessageDialog(null, "��ѡ��һ��","����Ի���",JOptionPane.WARNING_MESSAGE);  
	    				return;
	    			}
	    			else {//��ȡ����������ѯ�������������
	    				String teamname = table_1.getModel().getValueAt(rowNum, 0).toString();
	    				String sql = "select * from finals where teamname='" + teamname + "'";
	    				//System.out.print(sql);
	    				Detail d = new Detail(sql, index);
	    				d.a(sql, index);
	    			}
				}
				else if (index == 2) {                   //��ǰ���Ϊ����ȫ���ֱܷ�ʱ
					int rowNum = table_2.getSelectedRow();                  //����б�ѡ�е���
	    			if (rowNum == -1) {
	    				JOptionPane.showMessageDialog(null, "��ѡ��һ��","����Ի���",JOptionPane.WARNING_MESSAGE);  
	    				return;
	    			}
	    			else {//��ȡ�˶�Ա��Ų���ѯ�������÷����
	    				String playerid = table_2.getModel().getValueAt(rowNum, 1).toString();
	    				String sql = "select * from finals where IDnum='" + playerid + "'";
	    				//System.out.print(sql);
	    				Detail d = new Detail(sql, index);
	    				d.a(sql, index);
	    			}
				}
			}
		});
		btnNewButton.setBounds(440, 369, 124, 27);
		frame.getContentPane().add(btnNewButton);
		
		//check_all������ѯ���е���ɼ�
		JButton check_all = new JButton("\u67E5\u770B\u6240\u6709");
		check_all.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTable(new JTable());
				Table_Demo td = new Table_Demo();
				td.create_table(2,"");
				scrollPane.setViewportView(getTable());
			}
		});
		check_all.setBounds(301, 369, 87, 27);
		frame.getContentPane().add(check_all);
		
		//back��ť���ص�¼����
		JButton back = new JButton("\u8FD4\u56DE");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * ���ذ�ť��Ӧ�¼�
				 */
				frame.dispose();
				login a = new login();
			}
		});
		back.setBounds(499, 406, 65, 27);
		frame.getContentPane().add(back);
		
		//Preliminary��ťת������ɼ���ѯ����
		JButton Preliminary = new JButton("\u521D\u8D5B\u67E5\u8BE2");
		Preliminary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Preliminary_contest p = new Preliminary_contest();
				p.p(true);
				window.frame.setVisible(false);
			}
		});
		Preliminary.setBounds(396, 406, 93, 27);
		frame.getContentPane().add(Preliminary);
		
		
	}
	
	public static JTable getTable() {
		return table;
	}

	public static void setTable(JTable table) {
		kkk.table = table;
	}
	
	
	public static JTable getTable_1() {
		return table_1;
	}

	public static void setTable_1(JTable table) {
		kkk.table_1 = table;
	}
	
	
	public static JTable getTable_2() {
		return table_2;
	}

	public static void setTable_2(JTable table) {
		kkk.table_2 = table;
	}
	
	public static void setvis(){
    	window.frame.setVisible(true);;
    }
	
}




