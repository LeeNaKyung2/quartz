package quartzScheduler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class InsertQuartz implements Job{
	
	Connection conn =null;
	PreparedStatement insertPstmt = null;
	
	public InsertQuartz() {
	}
	
	@Override
	public void execute(JobExecutionContext execute) throws JobExecutionException {
	    try {
	    	Context initContext = new InitialContext();
		    Context envContext  = (Context) initContext.lookup("java:/comp/env");
		    DataSource ds = (DataSource) envContext.lookup("jdbc/Oracle11g");
		    conn = ds.getConnection();
		    
			insertPstmt = conn.prepareStatement("insert into Quartz(num,type1,type1_time) values(num1.nextval,'SCH #1',to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'))");
			insertPstmt.executeUpdate();
			 
			System.out.println(new Date());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (insertPstmt != null) {
				try {
					insertPstmt.close();
				} catch (Exception e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
				}
			}
		}
	}
}
