package EightNumPro;

import java.awt.*;  
import javax.swing.*;  
import java.awt.event.*;  
import java.util.*;  
  
  
public class EightNumFrame extends Frame implements ActionListener,KeyListener  
{  
/*	JMenu choose = new JMenu("ѡ���㷨");//�������˵�������ѡ��  
	JMenuItem width=new JMenuItem("��������㷨");
	JMenuItem aa=new JMenuItem("A*�㷨");
	*/
    Button restart = new Button("�������");
    Button nextPath = new Button("��ʾ�߷�");  
    Button printPath = new Button("��ʼ�Զ��߲�");  
    Button exit = new Button("�˳�����");  
    Button path = new Button("�ܹ�����");  
    Button[] button;  
    Panel panel,panel1,panel2;  
    int row,col;  
    private static int position,cellNum;  
    
    Label l1=new Label("��ѡ���㷨");  
    ButtonGroup buttonGroup = new ButtonGroup();
    JRadioButton s1=new JRadioButton("A*�㷨");  
    JRadioButton s2=new JRadioButton("��������㷨",true);
    
    final int dr[] = { 0,-1, 0, 1};  
    final int dc[] = {-1, 0, 1, 0};  
    public int flag = 0;			//�����ж�ʹ�������㷨
    
    public EightNumFrame(int row,int col) {  
        try {  
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); //����Windows���
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        this.row = row;  
        this.col = col;  
        cellNum = row*col;  
       
        restart.addActionListener(this);  
        exit.addActionListener(this);  
        nextPath.addActionListener(this);  
        printPath.addActionListener(this);  
        path.addActionListener(this);  
        s1.addActionListener(this);
        s2.addActionListener(this);  
        
        panel1 = new Panel(new GridLayout(1,3)) ;  
//   	panel1.add(choose);
        panel1.add(restart);  
        panel1.add(nextPath);  
        panel1.add(path);  
        panel1.add(printPath);  
        panel1.add(exit);  
        
        panel2 = new Panel();
        panel2.add(l1);
        buttonGroup.add(s2);
        buttonGroup.add(s1);
        panel2.add(s1);
        panel2.add(s2);
        
        panel = new Panel(new GridLayout(row,col)) ;  //���÷���
        button = new Button[cellNum];  
        for(int i = 0; i < cellNum; i++) {  		//����Ŀ��״̬
            if(i == cellNum - 1) {  
                button[i] = new Button(" ");  
            }else {  
                button[i] = new Button(String.valueOf(i + 1));  
            }  
            button[i].setFont(new Font("Courier", 10, 20));  
            button[i].addActionListener(this);  
            button[i].addKeyListener(this);  
            panel.add(button[i]);  
        }  
        position = cellNum - 1;  
        this.add(BorderLayout.NORTH,panel1);  
        this.add(BorderLayout.CENTER,panel);  
        this.add(BorderLayout.SOUTH,panel2); 
        
        this.setTitle("����������    2015301500234-��ͩ");  
        this.setVisible(true);  
        this.setSize(500,500);  	//���ô����С
          
        Toolkit kit = Toolkit.getDefaultToolkit();  		//���ô������
        Dimension screenSize = kit.getScreenSize();  
        int screenWidth = screenSize.width/2;  
        int screenHeight = screenSize.height/2;  
        int height = this.getHeight();  
        int width = this.getWidth();  
        this.setLocation(screenWidth-width/2, screenHeight-height/2);  
        this.addWindowListener(new WindowAdapter() {  
            public void windowClosing(WindowEvent e) {  
                System.exit(0);  
            }  
        });  
    }  
    //�ж������㷨
    public void setflag(){
    	if(s1.isSelected()){
    	    flag = 0;
    	}
    	else if(s2.isSelected())
    		flag = 1;
    }
    //������ɳ�ʼ״̬
    void start() {
        int a[] = new int[9];  
        do {  
            int k = 0;  
            Random random=new Random();  
            Set set=new HashSet();  
            while(set.size() < cellNum-1) {  
                int n=random.nextInt(cellNum-1)+1;  
                if(!set.contains(n)) {  
                    set.add(n);  
                    a[k++] = n;  
                }  
            }  
            a[k] = 0;  
        }while(!BFS.isok(a));  	//�ж��Ƿ��н�
        for(int i = 0; i < 9; i++)  
            button[i].setLabel(String.valueOf(a[i]));  
        button[cellNum-1].setLabel(" ");  
        position = cellNum - 1;  
    }  
    //�ж��Ƿ����
    boolean win() { 
        for(int i = 0; i < cellNum - 1; i++) {  
            if(button[i].getLabel().equals(" ")) {  
                return false;  
            }else if(Integer.valueOf(button[i].getLabel()) != i+1) {  
                return false;  
            }  
        }  
        return true;  
    }  
    
    //�ж����������ܷ񻥻�
    private boolean judge(Button a, Button b) {  		
        for(int i = 0; i < 4; i++) {  
            if( (a.getX() == b.getX() + dr[i]*a.getWidth())   
                    && (a.getY() == b.getY() + dc[i]*a.getHeight())) {  
                return true;  
            }  
        }  
        return false;  
    }  
  
  
    public void actionPerformed(ActionEvent e)   
    {  
        StringBuffer state = new StringBuffer();
        
        if(e.getSource() == s1)   
        {  
            setflag(); 
        } 
        else if(e.getSource() == s2)   
        {  
            setflag();  
            return;  
        } 
        
        if(e.getSource() == restart)   
        {  
            start();  
            return;  
        }  
        else if(e.getSource() == exit)   		//�˳�����
        {  
            System.exit(0);  
            return;  
        }  
        else if(e.getSource() == nextPath)   	//���·��
        {  
            for(int i = 0; i < cellNum; i++)   
            {  
                if(button[i].getLabel().equals(" "))   
                {  
                    state.append('0');  
                }  
                else   
                {  
                    state.append(button[i].getLabel());  
                }  
            }  
            String path = new String();
            if(flag==1)
            	path = BFS.solve(state.toString());  
            else if(flag==0)
            	path = Astar.Asolve(state.toString());
            JOptionPane.showMessageDialog(this,"�����ߣ�"+path);  
            return;  
        }  
        else if(e.getSource() == path)   		//�ܲ���
        {  
            for(int i = 0; i < cellNum; i++)   
            {  
                if(button[i].getLabel().equals(" "))   
                {  
                    state.append('0');  
                }  
                else   
                {  
                    state.append(button[i].getLabel());  
                }  
            }
            String path = new String();
            if(flag==1)
            	path = BFS.solve(state.toString());  
            else if(flag==0)
            	path = Astar.Asolve(state.toString());
            JOptionPane.showMessageDialog(this,"������Ҫ"+path.length()+"��!");  
            return;  
        }  
        else if(e.getSource() == printPath)   	//�����Զ��߲�
        {  
            for(int i = 0; i < cellNum; i++)   
            {  
                if(button[i].getLabel().equals(" "))   
                {  
                    state.append('0');  
                }  
                else   
                {  
                    state.append(button[i].getLabel());  
                }  
            }  
            String path = new String();
            if(flag==1)
            	path = BFS.solve(state.toString());  
            else if(flag==0)
            	path = Astar.Asolve(state.toString()); //�õ���ʼ״̬
            for(int i = 0; i < path.length(); i++) {  
                switch(path.charAt(i)) {  
                case 'U':  
                    go(KeyEvent.VK_UP);  
                    break;  
                case 'D':  
                    go(KeyEvent.VK_DOWN);  
                    break;  
                case 'L':  
                    go(KeyEvent.VK_LEFT);  
                    break;  
                case 'R':  
                    go(KeyEvent.VK_RIGHT);  
                    break;  
                }  
                try {  
                    Thread.sleep(1000);  
                } catch (InterruptedException e1) {  
                    e1.printStackTrace();  
                }  
            }  
        }  
        
        for(int i = 0; i < cellNum; i++) {  
            if(e.getSource() == button[i]) {  
                if(!button[i].getLabel().equals(" ") && judge(button[i],button[position])) {  
                    button[position].setLabel(button[i].getLabel());  
                    button[i].setLabel(" ");  
                    position = i;  
                }  
            }  
        }  
        if(win()) {  
            JOptionPane.showMessageDialog(this,"������⣡��");  
        }  
    }  
    void go(int dir) {  	//���Ʒ����߲�
        int x = position / col;  
        int y = position % col;  
        switch(dir) {  
        case KeyEvent.VK_UP:  
            if(x != 0) {  
                button[position].setLabel(button[position-col].getLabel());  
                button[position-col].setLabel(" ");  
                position -= col;  
            }  
            break;  
        case KeyEvent.VK_DOWN:  
            if(x != row-1) {  
                button[position].setLabel(button[position+col].getLabel());  
                button[position+col].setLabel(" ");  
                position += col;  
            }  
            break;  
        case KeyEvent.VK_LEFT:  
            if(y != 0) {  
                button[position].setLabel(button[position-1].getLabel());  
                button[position-1].setLabel(" ");  
                position -= 1;  
            }  
            break;  
        case KeyEvent.VK_RIGHT:  
            if(y != col-1) {  
                button[position].setLabel(button[position+1].getLabel());  
                button[position+1].setLabel(" ");  
                position += 1;  
            }  
            break;  
        }  
    }  
    public void keyPressed(KeyEvent e) {  
        go(e.getKeyCode());  
        if(win()) {  
            JOptionPane.showMessageDialog(this,"��ɰ��������⣡��");  
        }  
    }  
    public void keyReleased(KeyEvent e) {}  
    public void keyTyped(KeyEvent e) {}  
    
    
    public static void main(String[] args) {  
        new EightNumFrame(3, 3);  
    }  
}