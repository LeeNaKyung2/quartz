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

public class UpdateQuartz implements Job {

	Connection conn = null;
	PreparedStatement UpdatePstmt = null;

	public UpdateQuartz() {
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
	    	Context initContext = new InitialContext();
		    Context envContext  = (Context) initContext.lookup("java:/comp/env");
		    DataSource ds = (DataSource) envContext.lookup("jdbc/Oracle11g");
		    conn = ds.getConnection();
			
		    UpdatePstmt = conn.prepareStatement("update Quartz set type2 = 'SCH #2',type2_time = to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') where type2 is null");
		    UpdatePstmt.executeUpdate();
		    
		    System.out.println("update " +new Date()  );
		}catch(Exception e) {
	    	e.printStackTrace();
	    }finally {
	    	if(UpdatePstmt != null) {
				try {
					UpdatePstmt.close();
				}catch(Exception e) {
				}	
			}if(conn != null) {
				try {
					conn.close();
				}catch(Exception e) {
				}
			}
	    }
	}
}
