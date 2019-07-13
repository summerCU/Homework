/*
 * Table_Demo类：根据数据库记录输出各种表格
 * 共7个方法和6总表格Demo
 * 方法：crtate_table(int,String):决赛单项成绩表格                       表格式样：  Deom1
 * crtate_table_1(int,String):决赛团队积分表格                             表格式样：  Deom2
 * crtate_table_2(int,String):决赛个人全能总分表格                      表格式样：  Deom3
 * crtate_table_3(int,String):决赛单人单项具体得分表格               表格式样：  Deom4
 * crtate_table_4(int,String):决赛团队积分具体得分表格               表格式样：  Deom5
 * crtate_table_5(int,String):决赛个人全能总分具体得分表格        表格式样：  Deom6
 * crtate_table_6(int,String):初赛单项成绩表格                              表格式样：  Deom1
 */
package scoreSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;



public class Table_Demo {
	
	public void create_table(int sign, String id){
		String sql = null;
		switch(sign) {
		case 2:
			sql = "select * from finals";
			break;
		case 0:
			sql = "select * from finals where IDnum='"+ id + "'";
			break;
		case 1:
			sql = "select * from finals where matchtype='"+ id + "' order by score desc";
			break;
		}
		
		ResultSet rs;
		Statement stmt = null;
		try {
			Jdbctest sm = new Jdbctest();
			sm.jdbclink();
			stmt = sm.conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			Demo1 demo1 = new Demo1();
			int k = 0;
			rs.last();
			int r = rs.getRow();
			rs.first();
			String[][] cells = new String[r][5];
			if(r != 0) {
				do {
					String sportid = rs.getString(1).trim();
					String playerid = rs.getString(5).trim();
					int score = rs.getInt(14);
					int rank = rs.getInt(15);
					String teamname = rs.getString(17).trim();
					
					cells[k][0] = sportid;
					cells[k][1] = String.valueOf(playerid);
					cells[k][2] = String.valueOf(score);
					cells[k][3] = String.valueOf(rank);
					cells[k][4] = teamname;
					
					k = k + 1;
				}while(rs.next());
			}
			demo1.setCells(cells);
			try {
	            if (kkk.getTable() != null) {// 先判断表模型是否为空,如果不是则先清除表
	                JFrame f = new JFrame();
	                f.remove(kkk.getTable());
	            }
	            kkk.setTable(demo1.search());// 表赋值
	            //table.setAutoCreateRowSorter(true);
	        } catch (Exception ex) {
	        }
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}		
	}
	
	public void create_table_1(){
		String sql = "select * from team_score order by team_ranking";
		ResultSet rs;
		Statement stmt = null;
		try {
			Jdbctest sm = new Jdbctest();
			sm.jdbclink();
			stmt = sm.conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			Demo2 demo2 = new Demo2();
			int k = 0;
			rs.last();
			int r = rs.getRow();
			rs.first();
			String[][] cells = new String[r][3];
			do {
				int score = rs.getInt(2);
				int rank = rs.getInt(3);
				String teamname = rs.getString(1).trim();
				
				cells[k][0] = teamname;
				cells[k][1] = String.valueOf(score);
				cells[k][2] = String.valueOf(rank);
				
				k = k + 1;
			}while(rs.next());
			
			demo2.setCells(cells);
			try {
	            if (kkk.getTable_1() != null) {// 先判断表模型是否为空,如果不是则先清除表
	                JFrame f = new JFrame();
	                f.remove(kkk.getTable_1());
	            }
	            kkk.setTable_1(demo2.search());// 表赋值
	            //table.setAutoCreateRowSorter(true);
	        } catch (Exception ex) {
	        }
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public void create_table_2(){
		String sql = "select * from player_score order by player_ranking";
		ResultSet rs;
		Statement stmt = null;
		try {
			Jdbctest sm = new Jdbctest();
			sm.jdbclink();
			stmt = sm.conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			Demo3 demo3 = new Demo3();
			int k = 0;
			rs.last();
			int r = rs.getRow();
			rs.first();
			String[][] cells = new String[r][5];
			do {
				String playername = rs.getString(5).trim();
				String playerid = rs.getString(1).trim();
				int score = rs.getInt(3);
				int rank = rs.getInt(4);
				String teamname = rs.getString(2).trim();
				
				cells[k][0] = playername;
				cells[k][1] = String.valueOf(playerid);
				cells[k][2] = String.valueOf(score);
				cells[k][3] = String.valueOf(rank);
				cells[k][4] = teamname;
				
				k = k + 1;
			}while(rs.next());
			
			demo3.setCells(cells);
			try {
	            if (kkk.getTable_2() != null) {// 先判断表模型是否为空,如果不是则先清除表
	                JFrame f = new JFrame();
	                f.remove(kkk.getTable_2());
	            }
	            kkk.setTable_2(demo3.search());// 表赋值
	            //table.setAutoCreateRowSorter(true);
	        } catch (Exception ex) {
	        }
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public void create_table_3(String sql){
		//String sql = "select * from player_score order by player_ranking";
		ResultSet rs;
		Statement stmt = null;
		try {
			Jdbctest sm = new Jdbctest();
			sm.jdbclink();
			stmt = sm.conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			Demo4 demo4 = new Demo4();
			int k = 0;
			rs.last();
			int r = rs.getRow();
			rs.first();
			String[][] cells = new String[r][12];
			do {
				String sportid = rs.getString(1).trim();
				String playername = rs.getString(4).trim();
				String playerid = rs.getString(5).trim();
				int score1 = rs.getInt(7);
				int score2 = rs.getInt(8);
				int score3 = rs.getInt(9);
				int score4 = rs.getInt(10);
				int score5 = rs.getInt(11);
				int d = rs.getInt(12);
				int p = rs.getInt(13);
				int score = rs.getInt(14);
				String teamname = rs.getString(17).trim();
				
				cells[k][0] = sportid;
				cells[k][1] = playername;
				cells[k][2] = playerid;
				cells[k][3] = String.valueOf(score1);
				cells[k][4] = String.valueOf(score2);
				cells[k][5] = String.valueOf(score3);
				cells[k][6] = String.valueOf(score4);
				cells[k][7] = String.valueOf(score5);
				cells[k][8] = String.valueOf(d);
				cells[k][9] = String.valueOf(p);
				cells[k][10] = String.valueOf(score);
				cells[k][11] = teamname;
				
				k = k + 1;
			}while(rs.next());
			
			demo4.setCells(cells);
			try {
	            if (Detail.getTable() != null) {// 先判断表模型是否为空,如果不是则先清除表
	                JFrame f = new JFrame();
	                f.remove(Detail.getTable());
	            }
	            Detail.setTable(demo4.search());// 表赋值
	            //table.setAutoCreateRowSorter(true);
	        } catch (Exception ex) {
	        }
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public void create_table_4(String sql){
		//String sql = "select * from player_score order by player_ranking";
		ResultSet rs;
		Statement stmt = null;
		try {
			Jdbctest sm = new Jdbctest();
			sm.jdbclink();
			stmt = sm.conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			Demo5 demo5 = new Demo5();
			int k = 0;
			rs.last();
			int r = rs.getRow();
			rs.first();
			String[][] cells = new String[r+1][4];
			int all = 0;
			do {
				String playerid = rs.getString(5).trim();
				String sportid = rs.getString(1).trim();
				int ranking = rs.getInt(15);
				cells[k][0] = playerid;
				cells[k][1] = sportid;
				cells[k][2] = String.valueOf(ranking);
				
				switch(ranking) {
				case 1:
    				cells[k][3] = "10";
    				all = all + 10;
    				break;
    			case 2:
    				cells[k][3] = "5";
    				all = all + 5;
    				break;
    			case 3:
    				cells[k][3] = "3";
    				all = all + 3;
    				break;
    			case 4:
    				cells[k][3] = "1";
    				all = all + 1;
    				break;
    			case 5:
    				cells[k][3] = "0";
    				all = all + 0;
    				break;
				}
				
				k=k+1;
				
			}while(rs.next());
			cells[r][0] = "团队总积分";
			cells[r][3] = String.valueOf(all);
			demo5.setCells(cells);
			try {
	            if (Detail.getTable() != null) {// 先判断表模型是否为空,如果不是则先清除表
	                JFrame f = new JFrame();
	                f.remove(Detail.getTable());
	            }
	            Detail.setTable(demo5.search());// 表赋值
	            //table.setAutoCreateRowSorter(true);
	        } catch (Exception ex) {
	        }
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public void create_table_5(String sql){
		//String sql = "select * from player_score order by player_ranking";
		ResultSet rs;
		Statement stmt = null;
		try {
			Jdbctest sm = new Jdbctest();
			sm.jdbclink();
			stmt = sm.conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			Demo6 demo6 = new Demo6();
			int k = 0;
			rs.last();
			int r = rs.getRow();
			rs.first();
			String[][] cells = new String[r+1][12];
			int all = 0;
			do {
				String sportid = rs.getString(1).trim();				
				int score = rs.getInt(14);
				all = all + score;
				cells[k][0] = sportid;
				cells[k][1] = String.valueOf(score);
				
				k = k + 1;
			}while(rs.next());
			cells[r][0] = "个人全能总分";
			cells[r][1] = String.valueOf(all);
			
			demo6.setCells(cells);
			try {
	            if (Detail.getTable() != null) {// 先判断表模型是否为空,如果不是则先清除表
	                JFrame f = new JFrame();
	                f.remove(Detail.getTable());
	            }
	            Detail.setTable(demo6.search());// 表赋值
	            //table.setAutoCreateRowSorter(true);
	        } catch (Exception ex) {
	        }
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public void create_table_6(int sign, String id){
		String sql = null;
		switch(sign) {
		case 2:
			sql = "select * from preliminaries";
			break;
		case 0:
			sql = "select * from preliminaries where IDnum='"+ id + "'";
			break;
		case 1:
			sql = "select * from preliminaries where matchtype='"+ id + "' order by score desc";
			break;
		}
		
		ResultSet rs;
		Statement stmt = null;
		try {
			Jdbctest sm = new Jdbctest();
			sm.jdbclink();
			stmt = sm.conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			Demo1 demo1 = new Demo1();
			int k = 0;
			rs.last();
			int r = rs.getRow();
			rs.first();
			String[][] cells = new String[r][5];
			if(r != 0) {
				do {
					String sportid = rs.getString(1).trim();
					String playerid = rs.getString(5).trim();
					int score = rs.getInt(14);
					int rank = rs.getInt(15);
					String teamname = rs.getString(17).trim();
					
					cells[k][0] = sportid;
					cells[k][1] = String.valueOf(playerid);
					cells[k][2] = String.valueOf(score);
					cells[k][3] = String.valueOf(rank);
					cells[k][4] = teamname;
					
					k = k + 1;
				}while(rs.next());
			}
			
			demo1.setCells(cells);
			try {
	            if (Preliminary_contest.getTable() != null) {// 先判断表模型是否为空,如果不是则先清除表
	                JFrame f = new JFrame();
	                f.remove(kkk.getTable());
	            }
	            Preliminary_contest.setTable(demo1.search());// 表赋值
	            //table.setAutoCreateRowSorter(true);
	        } catch (Exception ex) {
	        }
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}		
	}

}
class Demo1 {
    private String[] columnNames = { "项目", "运动员编号", "成绩", "小组排名", "所属团队", };
    private Object[][] cells = {};

    public JTable search() {
    	
    	//TableModel model = new DefaultTableModel(cells, columnNames); 
    	//利用自带的setRowSorter方法实现排序功能
    	TableModel model = new DefaultTableModel(cells, columnNames) {  
    	    public Class getColumnClass(int column) {  
    	        Class returnValue;  
    	        if ((column >= 0) && (column < getColumnCount())) {  
    	            returnValue = getValueAt(0, column).getClass();  
    	        } else {  
    	            returnValue = Object.class;  
    	        }  
    	        return returnValue;  
    	    }  
    	};  
    	JTable JTable = new JTable(model);
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);  
        JTable.setRowSorter(sorter); 
       
        //JTable JTable = new JTable(cells, columnNames);// 创建表格
        return JTable;
    }
    
      
    public Object[][] getCells() {
		return cells;
	}


	public void setCells(Object[][] cells) {
		this.cells = cells;
	}
}

class Demo2 {
    private String[] columnNames = { "团队名", "总分", "排名",};
    private Object[][] cells = {};

    public JTable search() {
    	
    	//TableModel model = new DefaultTableModel(cells, columnNames); 
    	//利用自带的setRowSorter方法实现排序功能
    	TableModel model = new DefaultTableModel(cells, columnNames) {  
    	    public Class getColumnClass(int column) {  
    	        Class returnValue;  
    	        if ((column >= 0) && (column < getColumnCount())) {  
    	            returnValue = getValueAt(0, column).getClass();  
    	        } else {  
    	            returnValue = Object.class;  
    	        }  
    	        return returnValue;  
    	    }  
    	};  
    	JTable JTable = new JTable(model);
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);  
        JTable.setRowSorter(sorter); 
       
        //JTable JTable = new JTable(cells, columnNames);// 创建表格
        return JTable;
    }
    
      
    public Object[][] getCells() {
		return cells;
	}


	public void setCells(Object[][] cells) {
		this.cells = cells;
	}
}

class Demo3 {
    private String[] columnNames = { "姓名", "编号", "全能总分", "排名", "所属团队", };
    private Object[][] cells = {};

    public JTable search() {
    	
    	//TableModel model = new DefaultTableModel(cells, columnNames); 
    	//利用自带的setRowSorter方法实现排序功能
    	TableModel model = new DefaultTableModel(cells, columnNames) {  
    	    public Class getColumnClass(int column) {  
    	        Class returnValue;  
    	        if ((column >= 0) && (column < getColumnCount())) {  
    	            returnValue = getValueAt(0, column).getClass();  
    	        } else {  
    	            returnValue = Object.class;  
    	        }  
    	        return returnValue;  
    	    }  
    	};  
    	JTable JTable = new JTable(model);
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);  
        JTable.setRowSorter(sorter); 
       
        //JTable JTable = new JTable(cells, columnNames);// 创建表格
        return JTable;
    }
    
      
    public Object[][] getCells() {
		return cells;
	}


	public void setCells(Object[][] cells) {
		this.cells = cells;
	}
}

class Demo4 {
    private String[] columnNames = { "项目", "姓名", "运动员编号", "裁判1打分", "裁判2打分", "裁判3打分", "裁判4打分", "裁判5打分", "D分", "P分", "总分", "所属团队", };
    private Object[][] cells = {};

    public JTable search() {
    	
    	//TableModel model = new DefaultTableModel(cells, columnNames); 
    	//利用自带的setRowSorter方法实现排序功能
    	TableModel model = new DefaultTableModel(cells, columnNames) {  
    	    public Class getColumnClass(int column) {  
    	        Class returnValue;  
    	        if ((column >= 0) && (column < getColumnCount())) {  
    	            returnValue = getValueAt(0, column).getClass();  
    	        } else {  
    	            returnValue = Object.class;  
    	        }  
    	        return returnValue;  
    	    }  
    	};  
    	JTable JTable = new JTable(model);
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);  
        JTable.setRowSorter(sorter); 
       
        //JTable JTable = new JTable(cells, columnNames);// 创建表格
        return JTable;
    }
    
      
    public Object[][] getCells() {
		return cells;
	}


	public void setCells(Object[][] cells) {
		this.cells = cells;
	}
}

class Demo5 {
    private String[] columnNames = { "运动员编号", "项目编号", "小组排名", "积分",};
    private Object[][] cells = {};

    public JTable search() {
    	
    	//TableModel model = new DefaultTableModel(cells, columnNames); 
    	//利用自带的setRowSorter方法实现排序功能
    	TableModel model = new DefaultTableModel(cells, columnNames) {  
    	    public Class getColumnClass(int column) {  
    	        Class returnValue;  
    	        if ((column >= 0) && (column < getColumnCount())) {  
    	            returnValue = getValueAt(0, column).getClass();  
    	        } else {  
    	            returnValue = Object.class;  
    	        }  
    	        return returnValue;  
    	    }  
    	};  
    	JTable JTable = new JTable(model);
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);  
        JTable.setRowSorter(sorter); 
       
        //JTable JTable = new JTable(cells, columnNames);// 创建表格
        return JTable;
    }
    
      
    public Object[][] getCells() {
		return cells;
	}


	public void setCells(Object[][] cells) {
		this.cells = cells;
	}
}

class Demo6 {
    private String[] columnNames = { "项目编号", "分数",};
    private Object[][] cells = {};

    public JTable search() {
    	
    	//TableModel model = new DefaultTableModel(cells, columnNames); 
    	//利用自带的setRowSorter方法实现排序功能
    	TableModel model = new DefaultTableModel(cells, columnNames) {  
    	    public Class getColumnClass(int column) {  
    	        Class returnValue;  
    	        if ((column >= 0) && (column < getColumnCount())) {  
    	            returnValue = getValueAt(0, column).getClass();  
    	        } else {  
    	            returnValue = Object.class;  
    	        }  
    	        return returnValue;  
    	    }  
    	};  
    	JTable JTable = new JTable(model);
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);  
        JTable.setRowSorter(sorter); 
       
        //JTable JTable = new JTable(cells, columnNames);// 创建表格
        return JTable;
    }
    
      
    public Object[][] getCells() {
		return cells;
	}


	public void setCells(Object[][] cells) {
		this.cells = cells;
	}
}