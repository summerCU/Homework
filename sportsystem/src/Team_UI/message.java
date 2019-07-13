package Team_UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

public class message extends JFrame implements ActionListener,MouseListener{
	JPanel jp1 = new JPanel();
	JPanel jp2 = new JPanel();
	JLabel name = new JLabel("姓名");
	JTextField name1 = new JTextField(15);
	JLabel team = new JLabel("队伍");
	JTextField team1 = new JTextField(15);
	JLabel tel = new JLabel("联系方式");
	JTextField tel1 = new JTextField(15);
	JButton confirm= new JButton("确定");
	JButton cancel= new JButton("取消");
	
	public message() {
		super("信息录入");
		setup();
		confirm.addActionListener(this);
		cancel.addActionListener(this);
		this.setVisible(true);
		setSize(750, 500);
	}

	private void setup() {
		jp1.add(name);
		jp1.add(name1);
		jp1.add(team);
		jp1.add(team1);
		jp1.add(tel);
		jp1.add(tel1);
		jp1.setLayout(new FlowLayout());
		jp2.add(confirm);
		jp2.add(cancel);	
		add(jp1);
		add(jp2);
		this.setLayout(new GridLayout(2,1));
		this.pack();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirm) {
			JOptionPane.showMessageDialog(message.this, "提交成功！");
		}
		else if (e.getSource() == cancel){
			this.setVisible(false);
		}
	}
}
