package MatchArrangement;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import People.Manathlete;

public class Creatfinals extends JFrame{


	private JFrame frame;
	private String matchtype;
	private String age;
	private JTable table;
	private JLabel lblNewLabel;
	private String sex;
	public void Setsex(String sex){
		this.sex = sex;
	}
	public String Getsex(){
		return this.sex;
	}
	
	public Creatfinals(){
	}
	
	
	public void innitial(){
	JFrame frame = new JFrame(sex+"����Ŀ���ɾ�������");
	frame.setBounds(100, 100, 1000, 700);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	
	JPanel p1=new JPanel();
    p1.setBounds(0,20,1000,70);
	
	JLabel label = new JLabel("������Ŀ");
	label.setBounds(0, 20, 50, 30);
	JComboBox comboBox=new JComboBox();
	comboBox.setBounds(60,20,100,30);
	if(sex.equals("��")){
		comboBox.addItem("��ѡ�������Ŀ");  
		comboBox.addItem("����");  
		comboBox.addItem("˫��");  
		comboBox.addItem("����");  
		comboBox.addItem("����");
		comboBox.addItem("����");  
		comboBox.addItem("�Ĵ�");
		comboBox.addItem("�������");
    }
	else{
		comboBox.addItem("��ѡ�������Ŀ");  
		comboBox.addItem("�ߵ͸�");  
		comboBox.addItem("����");
		comboBox.addItem("ƽ��ľ");  
		comboBox.addItem("�Ĵ�");
		comboBox.addItem("�������");
	}
    comboBox .addItemListener(new java.awt.event.ItemListener() {
    	public void itemStateChanged(java.awt.event.ItemEvent evt) {
    		matchtype = (String)comboBox.getSelectedItem();
    	}
    });
	p1.add(label);
	p1.add(comboBox);
	
	JLabel label2 = new JLabel("�����");
	label2.setBounds(450, 20, 50, 30);
	JComboBox comboBox2=new JComboBox();
	comboBox2.setBounds(510,20,100,30);
	comboBox2.addItem("��ѡ�������");  
    comboBox2.addItem("7-8");  
    comboBox2.addItem("9-10");  
    comboBox2.addItem("11-12");  
    comboBox2.addItemListener(new java.awt.event.ItemListener() {
    	public void itemStateChanged(java.awt.event.ItemEvent evt) {
    		age = (String)comboBox2.getSelectedItem();
    	}
    });
	p1.add(label2);
	p1.add(comboBox2);
	
	
	/*��ʾ���չʾ��������*/
	JPanel jp1 = new JPanel();
	jp1.setBounds(0,50,1000,450);
	jp1.setLayout(new BorderLayout(0, 0));
	jp1.setBorder(new EmptyBorder(30, 5, 5, 5));
	String Names[] = { "��Ŀ","����", "�Ա�", "����", "С���"};
	DefaultTableModel defaultTableModel = new DefaultTableModel(Names, 20);
	JTable table = new JTable(defaultTableModel){
		public boolean isCellEditable(int rowIndex, int ColIndex){
         return false;
        }    
	} ;
	JScrollPane scroll = new JScrollPane();
	table.setModel(defaultTableModel);
	table.setVisible(true);
	table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	scroll.setViewportView(table);			
	jp1.add(scroll);
	frame.add(jp1);
	
/*���ð�ť*/	
	JButton button_0 = new JButton("��������");
	button_0.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//System.out.println(matchtype+"  "+age);
			List<Manathlete> adList1 = new ArrayList<Manathlete>();
			adList1 = Creatoperation.getList(sex,matchtype,age);
			if(adList1.size() != 0){
				int i=0;
				for (Manathlete a : adList1) {
					table.setValueAt(a.getMatch(), i, 0);
					table.setValueAt(a.getName(), i, 1);
					table.setValueAt(a.getSex(), i, 2);
					table.setValueAt(Integer.toString(a.getAge()), i, 3);
					table.setValueAt(Integer.toString(a.getGroup()), i, 4);
					i++;
				}	
				JOptionPane.showMessageDialog(Creatfinals.this,"���ɾ��������ɹ���");
			}
			else{
				JOptionPane.showMessageDialog(Creatfinals.this,"ϵͳ���ϣ����Ժ����ԣ�");
			}
			
		}
	});
	button_0.setBounds(750, 20, 90, 30);
	p1.add(button_0);
	
	JButton button_1 = new JButton("�˳�");
	button_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
		}
	});
	button_1.setBounds(950, 20, 90, 30);
	p1.add(button_1);
	frame.add(p1);
	frame.setVisible(true);
	}
}
