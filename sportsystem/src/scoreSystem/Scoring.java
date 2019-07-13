/*
 * Scoring���������㵥��ɼ��������ֺܷ͸���ȫ���ܷ�
 * ������������final_scoring()   ���㵥��ɼ�
 *         team_total_scoring()    ���������ܷ�
 *         player_total_scoring    �������ȫ���ܷ�
 */

package scoreSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class Scoring {
	private static Statement stmt = null;
	
	public void final_scoring() {
		
		String sql1 = "select * from finals";
		ResultSet rs;
		
		try {//�������ݿ���ԭʼ��¼��Ľ����rs
			stmt = kkk.sm.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql1);
			//PreparedStatement pstmt = sm.conn.prepareStatement("select * from student_table",   
            //        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); // �ɹ����ɸ���  
           // ResultSet rs = pstmt.executeQuery();
			rs.first();
			do{
				//�����߼���ͷ��Լ�δȥ�������ͷֵ��ܷ�
	        	int max = rs.getInt(7);       
	        	int min = rs.getInt(7);         
	        	int sum = 0;
	        	for(int i=7;i<=11;i++) {
	        		if(max < rs.getInt(i))
	        			max = rs.getInt(i);
	        		if(min > rs.getInt(i))
	        			min = rs.getInt(i);
	        		sum = sum + rs.getInt(i);
	        	}
	        	@SuppressWarnings("unused")
				int score;
	        	score = ((sum-min-max)/3)*5+rs.getInt(12)-rs.getInt(13);   //�ܷ�=ȥ�������ͷֵ�ƽ����*������ + D��  - P��
	        	rs.updateInt(14, score);      //���ּܷ�¼�����ݿ���
	        	rs.updateRow();
	        }while(rs.next());
			
			
		} catch (SQLException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
	}
	
	public void preliminary_scoring() {
		
		String sql1 = "select * from finals";
		ResultSet rs;
		
		try {//�������ݿ���ԭʼ��¼��Ľ����rs
			Jdbctest sm = new Jdbctest();
			sm.jdbclink();
			stmt = sm.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			//stmt = kkk.sm.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql1);
			//PreparedStatement pstmt = sm.conn.prepareStatement("select * from student_table",   
            //        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); // �ɹ����ɸ���  
           // ResultSet rs = pstmt.executeQuery();
			rs.first();
			do{
				//�����߼���ͷ��Լ�δȥ�������ͷֵ��ܷ�
	        	int max = rs.getInt(7);       
	        	int min = rs.getInt(7);         
	        	int sum = 0;
	        	for(int i=7;i<=11;i++) {
	        		if(max < rs.getInt(i))
	        			max = rs.getInt(i);
	        		if(min > rs.getInt(i))
	        			min = rs.getInt(i);
	        		sum = sum + rs.getInt(i);
	        	}
	        	@SuppressWarnings("unused")
				int score;
	        	score = ((sum-min-max)/3)*5+rs.getInt(12)-rs.getInt(13);   //�ܷ�=ȥ�������ͷֵ�ƽ����*������ + D��  - P��
	        	rs.updateInt(14, score);      //���ּܷ�¼�����ݿ���
	        	rs.updateRow();
	        }while(rs.next());
			
			
		} catch (SQLException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
	}
	
	
	public void player_total_scoring() {
		Statement stmt1 = null;
		
		String sql1 = "select * from athlete";
		String sql2 = "select * from finals";
		String sql3 = "select * from player_score";
		
		ResultSet rs1;
		ResultSet rs2;
		ResultSet rs3;
		
		try {
			//�����Ŷ�Զ��Ա�ı���ý����rs1
			stmt1 = kkk.sm.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs1 = stmt1.executeQuery(sql1);
			
			rs1.last();
			int m = rs1.getRow();        //mΪrs1��¼����
			rs1.first();
			String[] playerid = new String[m];       //playerid[]���������˶�Ա���
			String[] playername = new String[m];
			String[] teamname = new String[m]; //teamname���������˶�Ա�����Ŷ��������Ϊplayerid[i]���˶�Ա�����Ŷ�Ϊteamname[i]
			int[] playerscore = new int[m];    //playerscore[i]Ϊ�˶�Աplayerid[i]��ȫ���ܷ�
			Arrays.fill(playerscore, 0);
			int playercount = 0;
			for (int i = 0; i < m; i++) {      //����˶�Ա��ż������ŶӲ�ȥ���ظ�
				boolean flag = true;
				if(playercount == 0) {
					playerid[playercount] = rs1.getString(2).trim();
					playername[playercount] = rs1.getString(1).trim();
					teamname[playercount] = rs1.getString(5).trim();
					playercount = playercount + 1;
					flag = false;
				}
				else {
					for(int j = 0; j < playercount; j++)
						if( rs1.getString(2).trim().equals(playerid[j]))
							flag = false;	
				}
				if(flag){
					playerid[playercount] = rs1.getString(2).trim();
					playername[playercount] = rs1.getString(1).trim();
					teamname[playercount] = rs1.getString(5).trim();
					playercount = playercount + 1;
				}
				
				
				//playerid[i] = rs1.getInt(1);
				//teamname[i] = rs1.getString(3).trim();
				rs1.next();
			}
			
		    rs2 = stmt1.executeQuery(sql2);
		    for(int j = 0; j < playercount; j++) {       //��ԭʼ��¼������˶�Ա��ȫ���ܷ�
		    	rs2.first();
		    	do {
		    		if(rs2.getString(5).trim().equals(playerid[j]))
		    			playerscore[j] = playerscore[j] + rs2.getInt(14);
		    	}while(rs2.next());
		    }
		    
		    	    
		    for (int i = 0; i < playercount; i++) {		//������ȫ���ܷ���Ϣ��ŵ���Ӧ�ı� 
		    	rs3 = stmt1.executeQuery(sql3);	
		    	boolean a = true;
		    	while(rs3.next()) {
		    		if(rs3.getString(1).trim().equals(playerid[i])) {
		    			rs3.updateInt(3, playerscore[i]);
		    			rs3.updateRow();
		    			a = false;
		    		}
		    	}
		    	rs3.first();
		    	//System.out.print(a);
		    	if(a) {
			    	rs3.moveToInsertRow();
		        	rs3.updateString(1, playerid[i]);
		        	rs3.updateString(2,teamname[i]);
		        	rs3.updateInt(3, playerscore[i]);
		        	rs3.updateString(5, playername[i]);
		        	rs3.insertRow(); 
		    	}
			}
			
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}		
	}
	/*
	public void team_total_scoring() {
		Statement stmt1 = null;
		
		String sql1 = "select * from team_player";
		String sql2 = "select * from player_score order by player_total_score desc";
		String sql3 = "select * from team_score";
		
		ResultSet rs1;
		ResultSet rs2;
		ResultSet rs3;
		
		try {
			stmt1 = kkk.sm.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs1 = stmt1.executeQuery(sql1);
			
			rs1.last();
			int m = rs1.getRow();
			rs1.first();
			String[] teamname = new String[m];
			int[] teamscore = new int[m];
			int[][][] playerscore = new int[m][4][2];
			Arrays.fill(teamscore, 0);
			int team_count = 0;
			int player_count = 0;
			for (int i = 0; i < m; i++) {
				boolean flag = true;
				if(team_count==0) {
					teamname[team_count] = rs1.getString(3).trim();
					team_count = team_count + 1;
					flag = false;
				}
				else {
					for(int j = 0; j < team_count; j++) {
						if(rs1.getString(3).trim().equals(teamname[j]))
							flag = false;
					}
				}
				if(flag) {
					teamname[team_count] = rs1.getString(3).trim();
					team_count = team_count + 1;
				}
				rs1.next();
			}
			//System.out.print("teamcount:"+team_count);
		    rs2 = stmt1.executeQuery(sql2);
		    for(int j = 0; j < team_count; j++) {
		    	player_count = 0;
		    	rs2.first();
		    	do {
		    		if(rs2.getString(2).trim().equals(teamname[j])&&player_count <= 3) {
		    			playerscore[j][player_count][0] = rs2.getInt(1);
		    			playerscore[j][player_count][1] = rs2.getInt(3);
		    			teamscore[j] = teamscore[j] + rs2.getInt(3);		    			
		    			player_count = player_count + 1;
		    		}
		    	}while(rs2.next());
		    	//System.out.print("playercount:"+player_count);
		    	if(player_count < 4)
		    		teamscore[j] = 0;
		    }
		    
		    rs3 = stmt1.executeQuery(sql3);		    
		    for (int i = 0; i < team_count; i++) {		    	
		    	boolean a = true;
		    	while(rs3.next()) {
		    		if(rs3.getString(1).trim().equals(teamname[i]))
		    			a = false;
		    	}
		    	rs3.first();
		    	//System.out.print(a);
		    	if(a&&teamscore[i]!=0) {
			    	rs3.moveToInsertRow();
		        	rs3.updateString(1, teamname[i]);
		        	rs3.updateInt(2,teamscore[i]);
		        	//rs3.updateInt(4, playerscore[i][]);
		        	for(int j = 0; j < 4; j++) {
		        		rs3.updateInt((2*j)+4, playerscore[i][j][0]);
		        		rs3.updateInt((2*j)+5, playerscore[i][j][1]);
		        	}
		        	rs3.insertRow();
		    	}
			}
			
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	*/
	
	//�����ּܷ���
	public void team_total_scoring() {
		Statement stmt1 = null;
		
		String sql1 = "select * from athlete";
		String sql2 = "select * from finals";
		String sql3 = "select * from team_score";
		
		ResultSet rs1;
		ResultSet rs2;
		ResultSet rs3;
		
		try {
			stmt1 = kkk.sm.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs1 = stmt1.executeQuery(sql1);        //�����˶�Ա���ý����rs1
			
			rs1.last();
			int m = rs1.getRow();                 //rs1�ܼ�¼����
			rs1.first();                         //teamname[],teamscore[]�ֱ�洢�Ŷ����ֺͻ���
			String[] teamname = new String[m];
			int[] teamscore = new int[m];
			//int[][][] playerscore = new int[m][4][2];
			Arrays.fill(teamscore, 0);
			int team_count = 0;                  //�����Ŷ���
			//int player_count = 0;
			for (int i = 0; i < m; i++) {          //����Ŷ����洢��teamname[]��
				boolean flag = true;
				if(team_count==0) {
					teamname[team_count] = rs1.getString(5).trim();
					team_count = team_count + 1;
					flag = false;
				}
				else {
					for(int j = 0; j < team_count; j++) {
						if(rs1.getString(5).trim().equals(teamname[j]))
							flag = false;
					}
				}
				if(flag) {
					teamname[team_count] = rs1.getString(5).trim();
					team_count = team_count + 1;
				}
				rs1.next();
			}
			//System.out.print("teamcount:"+team_count);
		    rs2 = stmt1.executeQuery(sql2);
		    //int[] teamscore = new int[team_count];
		    for(int i = 0; i < team_count; i++) {           //�����Ŷӻ��֣�ǰ�������ֱַ�Ϊ10,5,3,1��0�����洢��teamscore[]��
		    	rs2.first();
		    	do {
		    		if(rs2.getString(17).trim().equals(teamname[i])) {
		    			switch(rs2.getInt(15)) {
		    			case 1:
		    				teamscore[i] = teamscore[i] + 10;
		    				break;
		    			case 2:
		    				teamscore[i] = teamscore[i] + 5;
		    				break;
		    			case 3:
		    				teamscore[i] = teamscore[i] + 3;
		    				break;
		    			case 4:
		    				teamscore[i] = teamscore[i] + 1;
		    				break;
		    			case 5:
		    				teamscore[i] = teamscore[i] + 0;
		    				break;
		    			}
		    		}
		    	}while(rs2.next());
		    }
		    
		    	    
		    for (int i = 0; i < team_count; i++) {	         //���Ŷӻ��ָ��µ����ݿ��team_score��
		    	rs3 = stmt1.executeQuery(sql3);	
		    	boolean a = true;
		    	while(rs3.next()) {
		    		if(rs3.getString(1).trim().equals(teamname[i])) {
		    			rs3.updateInt(2, teamscore[i]);
		    			rs3.updateRow();
		    			a = false;
		    		}
		    	}
		    	rs3.first();
		    	//System.out.print(a);
		    	if(a) {
			    	rs3.moveToInsertRow();
		        	rs3.updateString(1, teamname[i]);
		        	rs3.updateInt(2,teamscore[i]);
		        	//rs3.updateInt(4, playerscore[i][]);
		        	rs3.insertRow();
		    	}
			}
			
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
}
