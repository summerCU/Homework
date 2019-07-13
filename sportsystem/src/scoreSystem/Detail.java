/*
 * Detail类显示具体得分情况的窗口
 * 
 */
package scoreSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Detail extends JFrame {

	private JPanel contentPane;
	private static JTable table;

	/**
	 * Launch the application.
	 */
	public void a(String sql,int sign) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Detail frame = new Detail(sql,sign);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param sign 
	 */
	public Detail(String sql, int sign) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 749, 287);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 713, 228);
		contentPane.add(scrollPane);
		
		setTable(new JTable());
		Table_Demo td = new Table_Demo();
		switch(sign) {
		case 0:
			td.create_table_3(sql);
			break;
		case 1:
			td.create_table_4(sql);
			break;
		case 2:
			td.create_table_5(sql);
			break;
		}
		//td.create_table_3(sql);
		//table = new JTable();
		scrollPane.setViewportView(table);
	}

	public static JTable getTable() {
		return table;
	}

	public static void setTable(JTable table) {
		Detail.table = table;
	}
}
