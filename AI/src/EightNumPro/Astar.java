package EightNumPro;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

@SuppressWarnings("rawtypes")
public class Astar implements Comparable{
    private int[] num = new int[9];
    private int depth;                    //��ǰ����ȼ��ߵ���ǰ״̬�Ĳ���
    private int evaluation;                //����ʼ״̬��Ŀ�����С����ֵ
    private int misposition;            //��Ŀ�����С����
    private Astar parent;            //��ǰ״̬�ĸ�״̬
    
    final static String dir = "UDLR";
    public static StringBuffer path;
    
    public int[] getNum() {
        return num;
    }
    public void setNum(int[] num) {
        this.num = num;
    }
    public int getDepth() {
        return depth;
    }
    public void setDepth(int depth) {
        this.depth = depth;
    }
    public int getEvaluation() {
        return evaluation;
    }
    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }
    public int getMisposition() {
        return misposition;
    }
    public void setMisposition(int misposition) {
        this.misposition = misposition;
    }
    public Astar getParent() {
        return parent;
    }
    public void setParent(Astar parent) {
        this.parent = parent;
    }
    
    /**
     * �жϵ�ǰ״̬�Ƿ�ΪĿ��״̬
     * @param target
     * @return
     */
    public boolean isTarget(Astar target){
        return Arrays.equals(getNum(), target.getNum());
    }
    
    /**
     * ��f(n) = g(n)+h(n);
     * ��ʼ��״̬��Ϣ
     */
    public void init(Astar target){
        int temp = 0;
        for(int i=0;i<9;i++){
            if(num[i]!=target.getNum()[i])
                temp++;
        }
        this.setMisposition(temp);
        if(this.getParent()==null){
            this.setDepth(0);
        }else{
            this.depth = this.parent.getDepth()+1;
        }
        this.setEvaluation(this.getDepth()+this.getMisposition());
    }
    
    /**
     * ������ֵ���ж��Ƿ��н�
     * @param target
     * @return �н⣺true �޽⣺false
     */
    public boolean isSolvable(Astar target){
        int reverse = 0;
        for(int i=0;i<9;i++){
            for(int j=0;j<i;j++){
                if(num[j]>num[i])
                    reverse++;
                if(target.getNum()[j]>target.getNum()[i])
                    reverse++;
            }
        }
        if(reverse % 2 == 0)
            return true;
        return false;
    }
    @Override
    public int compareTo(Object o) {
        Astar c = (Astar) o;
        return this.evaluation-c.getEvaluation();//Ĭ������Ϊf(n)��С��������
    }
    /**
     * @return ����0�ڰ������е�λ��
     */
    public int getZeroPosition(){
        int position = -1;
        for(int i=0;i<9;i++){
            if(this.num[i] == 0){
                position = i;
            }
        }
        return position;
    }
    /**
     * @param open    ״̬����
     * @return �жϵ�ǰ״̬�Ƿ������open����
     */
    public int isContains(ArrayList<Astar> open){
        for(int i=0;i<open.size();i++){
            if(Arrays.equals(open.get(i).getNum(), getNum())){
                return i;
            }
        }
        return -1;
    }
    /**
     * �жϵ�ǰ���ܷ����������ƶ�
     */
    public boolean isMoveUp() {
        int position = getZeroPosition();
        if(position<=2){
            return false;
        }
        return true;
    }
    public boolean isMoveDown() {
        int position = getZeroPosition();
        if(position>=6){
            return false;
        }
        return true;
    }
    public boolean isMoveLeft() {
        int position = getZeroPosition();
        if(position%3 == 0){
            return false;
        }
        return true;
    }
    public boolean isMoveRight() {
        int position = getZeroPosition();
        if((position)%3 == 2){
            return false;
        }
        return true;
    }
    /**
     * @param move 0���ϣ�1���£�2����3����
     * @return �����ƶ����״̬
     */
    public Astar moveUp(int move){
        Astar temp = new Astar();
        int[] tempnum = (int[])num.clone();
        temp.setNum(tempnum);
        int position = getZeroPosition();    //0��λ��
        int p=0;                            //��0��λ�õ�λ��
        switch(move){
            case 0:
                p = position-3;
                temp.getNum()[position] = num[p];
                break;
            case 1:
                p = position+3;
                temp.getNum()[position] = num[p];
                break;
            case 2:
                p = position-1;
                temp.getNum()[position] = num[p];
                break;
            case 3:
                p = position+1;
                temp.getNum()[position] = num[p];
                break;
        }
        temp.getNum()[p] = 0;
        return temp;
    }    
    /**
     *���ݸ�״̬����״̬0��λ�ã��õ��ƶ�����
     *return string��UDLR�� 
    */
    public void getpath(){
    	 Astar temp = null;
    	 Astar temppa = null;
         temp = this;
         int ta,pa;
         temppa = temp.getParent();
         while(temp!=null && (temppa!=null)){
        	 ta = temp.getZeroPosition();
        	 pa = temppa.getZeroPosition();
        	 switch(ta-pa) {  
        	 case 3:  
        		 path.append(dir.charAt(0));
        		 break;  
        	 case -3:  
        		 path.append(dir.charAt(1));
        		 break;  
        	 case 1:  
        		 path.append(dir.charAt(2));
        		 break;  
        	 case -1:  
        		 path.append(dir.charAt(3)); 
        		 break; 
        	 }
        	 temp = temp.getParent();
        	 if(temp!=null)
        		 temppa = temp.getParent();
         }
    }
    
    /** 
     * @param open open��
     * @param close close��
     * @param parent ��״̬
     * @param target Ŀ��״̬
     */
    public void operation(ArrayList<Astar> open,ArrayList<Astar> close,Astar parent,Astar target){
        if(this.isContains(close) == -1){
            int position = this.isContains(open);
            if(position == -1){
                this.parent = parent;
                this.init(target);
                open.add(this);
            }else{
                if(this.getDepth() < open.get(position).getDepth()){
                    open.remove(position);
                    this.parent = parent;
                    this.init(target);
                    open.add(this);
                }
            }
        }
    }
    
    
    @SuppressWarnings("unchecked")
    public static String Asolve(String state){
        //����open��
        ArrayList<Astar> open = new ArrayList<Astar>();
        ArrayList<Astar> close = new ArrayList<Astar>();
        Astar start = new Astar();
        Astar target = new Astar();
        String lineContent = null;   
        path = new StringBuffer();
        int order = 0;
        int stnum[] = {1,2,3,4,5,6,7,8,0};
        int tanum[] = {1,2,3,4,5,6,7,8,0};
		for(int i = 0; i < state.length(); i++) {
			tanum[i] = Integer.valueOf(state.charAt(i)) - '0';
		}
        start.setNum(stnum);
        target.setNum(tanum);
//      long startTime=System.currentTimeMillis();   //��ȡ��ʼʱ��
        
        if(start.isSolvable(target)){
            start.init(target);                    	//��ʼ����ʼ״̬ 
            open.add(start);
            while(open.isEmpty() == false){
            	int move;
                Collections.sort(open);            //����evaluation��ֵ����
                Astar best = open.get(0);    //��open����ȡ����С��ֵ��״̬���Ƴ�open��
                open.remove(0);
                close.add(best);
                              
                if(best.isTarget(target)){         //�������ʱ�䣬�߲�
                    best.getpath();
                /*  long end=System.currentTimeMillis(); //��ȡ����ʱ��  
                    System.out.println("��������ʱ�䣺 "+(end-startTime)+"ms");*/
                    System.out.print(path.toString());
                    return path.toString();
                }
                /*
                *��best״̬������չ�����뵽open����
                *0��λ���ƶ�֮��״̬����close��open���趨bestΪ�丸״̬������ʼ��f(n)��ֵ���� 
                */
                if(best.isMoveUp()){
                    move = 0;
                    Astar up = best.moveUp(move);
                    up.operation(open, close, best, target);
                }
                if(best.isMoveDown()){
                    move = 1;
                    Astar up = best.moveUp(move);
                    up.operation(open, close, best, target);
                }
                if(best.isMoveLeft()){
                    move = 2;
                    Astar up = best.moveUp(move);
                    up.operation(open, close, best, target);
                }
                if(best.isMoveRight()){
                    move = 3;
                    Astar up = best.moveUp(move);
                    up.operation(open, close, best, target);
                }
                
            }
        }else 
            System.out.println("û�н⣬���������롣");
        	return path.toString();
    }
    
}