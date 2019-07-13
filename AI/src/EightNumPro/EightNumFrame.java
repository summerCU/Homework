package EightNumPro;

import java.awt.*;  
import javax.swing.*;  
import java.awt.event.*;  
import java.util.*;  
  
  
public class EightNumFrame extends Frame implements ActionListener,KeyListener  
{  
/*	JMenu choose = new JMenu("选择算法");//在下拉菜单中设置选项  
	JMenuItem width=new JMenuItem("宽度优先算法");
	JMenuItem aa=new JMenuItem("A*算法");
	*/
    Button restart = new Button("随机打乱");
    Button nextPath = new Button("提示走法");  
    Button printPath = new Button("开始自动走步");  
    Button exit = new Button("退出程序");  
    Button path = new Button("总共步数");  
    Button[] button;  
    Panel panel,panel1,panel2;  
    int row,col;  
    private static int position,cellNum;  
    
    Label l1=new Label("请选择算法");  
    ButtonGroup buttonGroup = new ButtonGroup();
    JRadioButton s1=new JRadioButton("A*算法");  
    JRadioButton s2=new JRadioButton("宽度优先算法",true);
    
    final int dr[] = { 0,-1, 0, 1};  
    final int dc[] = {-1, 0, 1, 0};  
    public int flag = 0;			//用于判断使用哪种算法
    
    public EightNumFrame(int row,int col) {  
        try {  
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); //设置Windows风格
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
        
        panel = new Panel(new GridLayout(row,col)) ;  //设置方格
        button = new Button[cellNum];  
        for(int i = 0; i < cellNum; i++) {  		//设置目标状态
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
        
        this.setTitle("八数码问题    2015301500234-夏桐");  
        this.setVisible(true);  
        this.setSize(500,500);  	//设置窗体大小
          
        Toolkit kit = Toolkit.getDefaultToolkit();  		//设置窗体居中
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
    //判断所用算法
    public void setflag(){
    	if(s1.isSelected()){
    	    flag = 0;
    	}
    	else if(s2.isSelected())
    		flag = 1;
    }
    //随机生成初始状态
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
        }while(!BFS.isok(a));  	//判断是否有解
        for(int i = 0; i < 9; i++)  
            button[i].setLabel(String.valueOf(a[i]));  
        button[cellNum-1].setLabel(" ");  
        position = cellNum - 1;  
    }  
    //判断是否完成
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
    
    //判断两个方格能否互换
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
        else if(e.getSource() == exit)   		//退出程序
        {  
            System.exit(0);  
            return;  
        }  
        else if(e.getSource() == nextPath)   	//输出路径
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
            JOptionPane.showMessageDialog(this,"建议走："+path);  
            return;  
        }  
        else if(e.getSource() == path)   		//总步数
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
            JOptionPane.showMessageDialog(this,"最少需要"+path.length()+"步!");  
            return;  
        }  
        else if(e.getSource() == printPath)   	//进行自动走步
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
            	path = Astar.Asolve(state.toString()); //得到初始状态
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
            JOptionPane.showMessageDialog(this,"完成问题！！");  
        }  
    }  
    void go(int dir) {  	//控制方格走步
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
            JOptionPane.showMessageDialog(this,"完成八数码问题！！");  
        }  
    }  
    public void keyReleased(KeyEvent e) {}  
    public void keyTyped(KeyEvent e) {}  
    
    
    public static void main(String[] args) {  
        new EightNumFrame(3, 3);  
    }  
}