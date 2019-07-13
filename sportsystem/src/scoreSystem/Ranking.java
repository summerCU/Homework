/*
 * Ranking�ࣺ�����������
 * ��3��������player_ranking()  �������ȫ���ܷ�����
 * team_ranking()     �����Ŷӻ�������
 * ranking()         ����С������
 */
package scoreSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class Ranking {
	private static Statement stmt = null,stmt2=null;
	
	public void player_ranking() {
		String sql = "select * from player_score order by player_total_score desc";
		ResultSet rs;
		
		try {
			stmt = kkk.sm.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);
			
			rs.last();
			int m = rs.getRow();
			int[] flag = new int[m] ;           //flag[]�Ǹ�ǰһ��ɼ���ͬ�ı�־��1 ��ʾ��ǰһ���ɼ���ͬ
			Arrays.fill(flag, 0);
			rs.first();
			for(int i = 1; i < m; i++) {
				int pre = rs.getInt(3);
				rs.next();
				if(rs.getInt(3)==pre)
					flag[i] = 1;				
			}
			
			int[] rank = new int[m];
			for(int i = 0; i < m; i++) {
				rank[i]=i+1;
				int j = i;
				while(flag[j] == 1) {
					rank[i] = rank[i] - 1;
					j--;
				}
			}
			
			rs.first();
			int count = 0;
			do {
				rs.updateInt(4, rank[count]);
				rs.updateRow();
				count = count + 1;
			}while(rs.next());
			
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}		
	}
	
	public void team_ranking() {
		String sql = "select * from team_score order by team_total_score desc";
		ResultSet rs;
		
		try {
			stmt = kkk.sm.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);
			
			rs.last();
			int m = rs.getRow();
			int[] flag = new int[m] ;
			Arrays.fill(flag, 0);
			rs.first();
			for(int i = 1; i < m; i++) {
				int pre = rs.getInt(2);
				rs.next();
				if(rs.getInt(2)==pre)
					flag[i] = 1;				
			}
			
			int[] rank = new int[m];
			for(int i = 0; i < m; i++) {
				rank[i]=i+1;
				int j = i;
				while(flag[j] == 1) {
					rank[i] = rank[i] - 1;
					j--;
				}
			}
			
			rs.first();
			int count = 0;
			do {
				rs.updateInt(3, rank[count]);
				rs.updateRow();
				count = count + 1;
			}while(rs.next());
			
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}		
	}
	
	public void ranking() {
		String sql = "select * from finals";
		ResultSet rs;
		
		try {
			stmt = kkk.sm.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);
			
			rs.last();
			int m = rs.getRow();
			rs.first();
			int groupcount = 0;
			String[] groupid = new String[m];      //groupid[]�洢��ͬС��
			for (int i = 0; i < m; i++) {
				boolean flag = true;
				if(groupcount==0) {
					groupid[groupcount] = rs.getString(16).trim();    
					groupcount = groupcount + 1;
					flag = false;
				}
				else {
					for(int j = 0; j < groupcount; j++) {
						if(rs.getString(16).trim().equals(groupid[j]))
							flag = false;
					}
				}
				if(flag) {
					groupid[groupcount] = rs.getString(16).trim();
					groupcount = groupcount + 1;
				}
				rs.next();
			}
			for (int j = 0; j < groupcount; j++) {
				sql = "select * from finals where seq='"+ groupid[j] + "' order by score desc";
				ResultSet rs1;
				
				//stmt = kkk.sm.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				rs1 = stmt.executeQuery(sql);
				
				rs1.last();
				int n = rs1.getRow();
				int[] flag = new int[n] ;
				Arrays.fill(flag, 0);
				rs1.first();
				for(int i = 1; i < n; i++) {
					int pre = rs1.getInt(14);
					rs1.next();
					if(rs1.getInt(14) == pre)
						flag[i] = 1;				
				}
				
				int[] rank = new int[n];
				for(int i = 0; i < n; i++) {
					rank[i]=i+1;
					int k = i;
					while(flag[k] == 1) {
						rank[i] = rank[i] - 1;
						k--;
					}
				}
				
				rs1.first();
				int count = 0;
				do {
					rs1.updateInt(15, rank[count]);
					rs1.updateRow();
					count = count + 1;
				}while(rs1.next());
			}
			
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}		
	}

}
