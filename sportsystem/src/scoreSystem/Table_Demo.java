/*
 * Table_Demo�ࣺ�������ݿ��¼������ֱ��
 * ��7��������6�ܱ��Demo
 * ������crtate_table(int,String):��������ɼ����                       ���ʽ����  Deom1
 * crtate_table_1(int,String):�����Ŷӻ��ֱ��                             ���ʽ����  Deom2
 * crtate_table_2(int,String):��������ȫ���ֱܷ��                      ���ʽ����  Deom3
 * crtate_table_3(int,String):�������˵������÷ֱ��               ���ʽ����  Deom4
 * crtate_table_4(int,String):�����Ŷӻ��־���÷ֱ��               ���ʽ����  Deom5
 * crtate_table_5(int,String):��������ȫ���ܷ־���÷ֱ��        ���ʽ����  Deom6
 * crtate_table_6(int,String):��������ɼ����                              ���ʽ����  Deom1
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
	            if (kkk.getTable() != null) {// ���жϱ�ģ���Ƿ�Ϊ��,����������������
	                JFrame f = new JFrame();
	                f.remove(kkk.getTable());
	            }
	            kkk.setTable(demo1.search());// ��ֵ
	            //table.setAutoCreateRowSorter(true);
	        } catch (Exception ex) {
	        }
			
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
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
	            if (kkk.getTable_1() != null) {// ���жϱ�ģ���Ƿ�Ϊ��,����������������
	                JFrame f = new JFrame();
	                f.remove(kkk.getTable_1());
	            }
	            kkk.setTable_1(demo2.search());// ��ֵ
	            //table.setAutoCreateRowSorter(true);
	        } catch (Exception ex) {
	        }
			
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
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
	            if (kkk.getTable_2() != null) {// ���жϱ�ģ���Ƿ�Ϊ��,����������������
	                JFrame f = new JFrame();
	                f.remove(kkk.getTable_2());
	            }
	            kkk.setTable_2(demo3.search());// ��ֵ
	            //table.setAutoCreateRowSorter(true);
	        } catch (Exception ex) {
	        }
			
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
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
	            if (Detail.getTable() != null) {// ���жϱ�ģ���Ƿ�Ϊ��,����������������
	                JFrame f = new JFrame();
	                f.remove(Detail.getTable());
	            }
	            Detail.setTable(demo4.search());// ��ֵ
	            //table.setAutoCreateRowSorter(true);
	        } catch (Exception ex) {
	        }
			
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
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
			cells[r][0] = "�Ŷ��ܻ���";
			cells[r][3] = String.valueOf(all);
			demo5.setCells(cells);
			try {
	            if (Detail.getTable() != null) {// ���жϱ�ģ���Ƿ�Ϊ��,����������������
	                JFrame f = new JFrame();
	                f.remove(Detail.getTable());
	            }
	            Detail.setTable(demo5.search());// ��ֵ
	            //table.setAutoCreateRowSorter(true);
	        } catch (Exception ex) {
	        }
			
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
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
			cells[r][0] = "����ȫ���ܷ�";
			cells[r][1] = String.valueOf(all);
			
			demo6.setCells(cells);
			try {
	            if (Detail.getTable() != null) {// ���жϱ�ģ���Ƿ�Ϊ��,����������������
	                JFrame f = new JFrame();
	                f.remove(Detail.getTable());
	            }
	            Detail.setTable(demo6.search());// ��ֵ
	            //table.setAutoCreateRowSorter(true);
	        } catch (Exception ex) {
	        }
			
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
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
	            if (Preliminary_contest.getTable() != null) {// ���жϱ�ģ���Ƿ�Ϊ��,����������������
	                JFrame f = new JFrame();
	                f.remove(kkk.getTable());
	            }
	            Preliminary_contest.setTable(demo1.search());// ��ֵ
	            //table.setAutoCreateRowSorter(true);
	        } catch (Exception ex) {
	        }
			
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}		
	}

}
class Demo1 {
    private String[] columnNames = { "��Ŀ", "�˶�Ա���", "�ɼ�", "С������", "�����Ŷ�", };
    private Object[][] cells = {};

    public JTable search() {
    	
    	//TableModel model = new DefaultTableModel(cells, columnNames); 
    	//�����Դ���setRowSorter����ʵ��������
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
       
        //JTable JTable = new JTable(cells, columnNames);// �������
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
    private String[] columnNames = { "�Ŷ���", "�ܷ�", "����",};
    private Object[][] cells = {};

    public JTable search() {
    	
    	//TableModel model = new DefaultTableModel(cells, columnNames); 
    	//�����Դ���setRowSorter����ʵ��������
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
       
        //JTable JTable = new JTable(cells, columnNames);// �������
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
    private String[] columnNames = { "����", "���", "ȫ���ܷ�", "����", "�����Ŷ�", };
    private Object[][] cells = {};

    public JTable search() {
    	
    	//TableModel model = new DefaultTableModel(cells, columnNames); 
    	//�����Դ���setRowSorter����ʵ��������
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
       
        //JTable JTable = new JTable(cells, columnNames);// �������
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
    private String[] columnNames = { "��Ŀ", "����", "�˶�Ա���", "����1���", "����2���", "����3���", "����4���", "����5���", "D��", "P��", "�ܷ�", "�����Ŷ�", };
    private Object[][] cells = {};

    public JTable search() {
    	
    	//TableModel model = new DefaultTableModel(cells, columnNames); 
    	//�����Դ���setRowSorter����ʵ��������
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
       
        //JTable JTable = new JTable(cells, columnNames);// �������
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
    private String[] columnNames = { "�˶�Ա���", "��Ŀ���", "С������", "����",};
    private Object[][] cells = {};

    public JTable search() {
    	
    	//TableModel model = new DefaultTableModel(cells, columnNames); 
    	//�����Դ���setRowSorter����ʵ��������
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
       
        //JTable JTable = new JTable(cells, columnNames);// �������
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
    private String[] columnNames = { "��Ŀ���", "����",};
    private Object[][] cells = {};

    public JTable search() {
    	
    	//TableModel model = new DefaultTableModel(cells, columnNames); 
    	//�����Դ���setRowSorter����ʵ��������
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
       
        //JTable JTable = new JTable(cells, columnNames);// �������
        return JTable;
    }
    
      
    public Object[][] getCells() {
		return cells;
	}


	public void setCells(Object[][] cells) {
		this.cells = cells;
	}
}