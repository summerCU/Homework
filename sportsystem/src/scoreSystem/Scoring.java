/*
 * Scoring类用来计算单项成绩、团体总分和个人全能总分
 * 有三个方法：final_scoring()   计算单项成绩
 *         team_total_scoring()    计算团体总分
 *         player_total_scoring    计算个人全能总分
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
		
		try {//链接数据库获得原始记录表的结果集rs
			stmt = kkk.sm.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql1);
			//PreparedStatement pstmt = sm.conn.prepareStatement("select * from student_table",   
            //        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); // 可滚动可更新  
           // ResultSet rs = pstmt.executeQuery();
			rs.first();
			do{
				//获得最高及最低分以及未去掉最高最低分的总分
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
	        	score = ((sum-min-max)/3)*5+rs.getInt(12)-rs.getInt(13);   //总分=去掉最高最低分的平均分*裁判数 + D分  - P分
	        	rs.updateInt(14, score);      //将总分记录在数据库里
	        	rs.updateRow();
	        }while(rs.next());
			
			
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
	}
	
	public void preliminary_scoring() {
		
		String sql1 = "select * from finals";
		ResultSet rs;
		
		try {//链接数据库获得原始记录表的结果集rs
			Jdbctest sm = new Jdbctest();
			sm.jdbclink();
			stmt = sm.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			//stmt = kkk.sm.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql1);
			//PreparedStatement pstmt = sm.conn.prepareStatement("select * from student_table",   
            //        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); // 可滚动可更新  
           // ResultSet rs = pstmt.executeQuery();
			rs.first();
			do{
				//获得最高及最低分以及未去掉最高最低分的总分
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
	        	score = ((sum-min-max)/3)*5+rs.getInt(12)-rs.getInt(13);   //总分=去掉最高最低分的平均分*裁判数 + D分  - P分
	        	rs.updateInt(14, score);      //将总分记录在数据库里
	        	rs.updateRow();
	        }while(rs.next());
			
			
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
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
			//链接团队远动员的表，获得结果集rs1
			stmt1 = kkk.sm.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs1 = stmt1.executeQuery(sql1);
			
			rs1.last();
			int m = rs1.getRow();        //m为rs1记录条数
			rs1.first();
			String[] playerid = new String[m];       //playerid[]用来储存运动员编号
			String[] playername = new String[m];
			String[] teamname = new String[m]; //teamname用来储存运动员所属团队例：编号为playerid[i]的运动员所属团队为teamname[i]
			int[] playerscore = new int[m];    //playerscore[i]为运动员playerid[i]的全能总分
			Arrays.fill(playerscore, 0);
			int playercount = 0;
			for (int i = 0; i < m; i++) {      //获得运动员编号及所属团队并去除重复
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
		    for(int j = 0; j < playercount; j++) {       //从原始记录表计算运动员的全能总分
		    	rs2.first();
		    	do {
		    		if(rs2.getString(5).trim().equals(playerid[j]))
		    			playerscore[j] = playerscore[j] + rs2.getInt(14);
		    	}while(rs2.next());
		    }
		    
		    	    
		    for (int i = 0; i < playercount; i++) {		//将个人全能总分信息存放到对应的表 
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
			// TODO 自动生成的 catch 块
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	*/
	
	//团体总分计算
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
			rs1 = stmt1.executeQuery(sql1);        //链接运动员表获得结果集rs1
			
			rs1.last();
			int m = rs1.getRow();                 //rs1总记录条数
			rs1.first();                         //teamname[],teamscore[]分别存储团队名字和积分
			String[] teamname = new String[m];
			int[] teamscore = new int[m];
			//int[][][] playerscore = new int[m][4][2];
			Arrays.fill(teamscore, 0);
			int team_count = 0;                  //计算团队数
			//int player_count = 0;
			for (int i = 0; i < m; i++) {          //获得团队名存储在teamname[]中
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
		    for(int i = 0; i < team_count; i++) {           //计算团队积分（前五名积分分别为10,5,3,1，0）并存储在teamscore[]中
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
		    
		    	    
		    for (int i = 0; i < team_count; i++) {	         //将团队积分更新到数据库表team_score中
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
