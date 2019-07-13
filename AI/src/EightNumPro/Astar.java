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
    private int depth;                    //当前的深度即走到当前状态的步骤
    private int evaluation;                //从起始状态到目标的最小估计值
    private int misposition;            //到目标的最小估计
    private Astar parent;            //当前状态的父状态
    
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
     * 判断当前状态是否为目标状态
     * @param target
     * @return
     */
    public boolean isTarget(Astar target){
        return Arrays.equals(getNum(), target.getNum());
    }
    
    /**
     * 求f(n) = g(n)+h(n);
     * 初始化状态信息
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
     * 求逆序值并判断是否有解
     * @param target
     * @return 有解：true 无解：false
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
        return this.evaluation-c.getEvaluation();//默认排序为f(n)由小到大排序
    }
    /**
     * @return 返回0在八数码中的位置
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
     * @param open    状态集合
     * @return 判断当前状态是否存在于open表中
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
     * 判断当前键能否上下左右移动
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
     * @param move 0：上，1：下，2：左，3：右
     * @return 返回移动后的状态
     */
    public Astar moveUp(int move){
        Astar temp = new Astar();
        int[] tempnum = (int[])num.clone();
        temp.setNum(tempnum);
        int position = getZeroPosition();    //0的位置
        int p=0;                            //与0换位置的位置
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
     *根据父状态与子状态0的位置，得到移动序列
     *return string‘UDLR’ 
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
     * @param open open表
     * @param close close表
     * @param parent 父状态
     * @param target 目标状态
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
        //定义open表
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
//      long startTime=System.currentTimeMillis();   //获取开始时间
        
        if(start.isSolvable(target)){
            start.init(target);                    	//初始化初始状态 
            open.add(start);
            while(open.isEmpty() == false){
            	int move;
                Collections.sort(open);            //按照evaluation的值排序
                Astar best = open.get(0);    //从open表中取出最小估值的状态并移除open表
                open.remove(0);
                close.add(best);
                              
                if(best.isTarget(target)){         //输出运行时间，走步
                    best.getpath();
                /*  long end=System.currentTimeMillis(); //获取结束时间  
                    System.out.println("程序运行时间： "+(end-startTime)+"ms");*/
                    System.out.print(path.toString());
                    return path.toString();
                }
                /*
                *由best状态进行扩展并加入到open表中
                *0的位置移动之后状态不在close和open中设定best为其父状态，并初始化f(n)估值函数 
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
            System.out.println("没有解，请重新输入。");
        	return path.toString();
    }
    
}