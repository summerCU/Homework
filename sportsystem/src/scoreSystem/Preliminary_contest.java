/*
 * 初赛成绩查询窗口类
 */

package scoreSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Preliminary_contest extends JFrame {

	private JPanel finals;
	private static JTable table;
	private JTextField id;

	/**
	 * Launch the application.
	 */
	public static void p(boolean a) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Preliminary_contest frame = new Preliminary_contest();
					frame.setVisible(a);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Preliminary_contest() {
		setTitle("\u521D\u8D5B\u6210\u7EE9\u67E5\u8BE2");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 487);
		finals = new JPanel();
		finals.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(finals);
		finals.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 634, 347);
		finals.add(scrollPane);
		
		setTable(new JTable());
		Table_Demo td = new Table_Demo();
		td.create_table_6(2,"");
		//scrollPane.setViewportView(getTable());	
		//table = new JTable();
		scrollPane.setViewportView(table);
		
		//查询方式下拉框
		JComboBox check_way = new JComboBox();
		check_way.setModel(new DefaultComboBoxModel(new String[] {"\u8FD0\u52A8\u5458\u67E5\u8BE2", "\u9879\u76EE\u67E5\u8BE2"}));
		check_way.setBounds(10, 367, 93, 32);
		finals.add(check_way);
		
		//查询按钮
		JButton check = new JButton("\u67E5\u8BE2");
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id.getText().equals("")) 
					JOptionPane.showMessageDialog(null, "请填写要查询的运动员或项目编号！");
				else {
					String sport_or_player_id = id.getText();            //获取id文本框内容及check_way下拉框的Index
					int checkway = check_way.getSelectedIndex(); 
					//输出查询结果表格
					setTable(new JTable());
					Table_Demo td = new Table_Demo();
					td.create_table_6(checkway,sport_or_player_id);
					scrollPane.setViewportView(getTable());	
				}
			}
		});
		
		
		check.setBounds(448, 367, 93, 32);
		finals.add(check);
		
		//运动员编号或项目文本框
		id = new JTextField();
		id.setBounds(254, 367, 144, 32);
		finals.add(id);
		id.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u8FD0\u52A8\u5458\u6216\u9879\u76EE\u7F16\u53F7\uFF1A");
		lblNewLabel.setBounds(135, 367, 118, 29);
		finals.add(lblNewLabel);
		
		//显示所有按钮
		JButton check_all = new JButton("\u67E5\u770B\u6240\u6709");
		check_all.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTable(new JTable());
				Table_Demo td = new Table_Demo();
				td.create_table_6(2,"");
				scrollPane.setViewportView(getTable());
			}
		});
		check_all.setBounds(551, 367, 93, 33);
		finals.add(check_all);
		
		//返回按钮
		JButton back = new JButton("返回");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * 返回按钮响应事件
				 */
				
			}
		});
		back.setBounds(551, 410, 93, 32);
		finals.add(back);
		
		//决赛成绩查询
		JButton btnNewButton = new JButton("\u51B3\u8D5B\u67E5\u8BE2");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				kkk.setvis();
			}
		});
		btnNewButton.setBounds(448, 409, 93, 33);
		finals.add(btnNewButton);
	}
	public static JTable getTable() {
		return table;
	}

	public static void setTable(JTable table) {
		Preliminary_contest.table = table;
	}
}
