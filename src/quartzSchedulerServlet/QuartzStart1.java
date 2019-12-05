package quartzSchedulerServlet;

import java.io.IOException;
import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import quartzScheduler.InsertQuartz;

@WebServlet("/QuartzStart1")
public class QuartzStart1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	private SchedulerFactory schedulerFactory;
	Connection conn = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		try {

			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/Oracle11g");
			conn = ds.getConnection();
			
			schedulerFactory = new StdSchedulerFactory();
			Scheduler scheduler2 = schedulerFactory.getScheduler();
			scheduler2 = schedulerFactory.getScheduler();
			scheduler2.start();
			
			JobDetail job2 = JobBuilder.newJob(InsertQuartz.class)
									   .withIdentity("job2" , "group2")
									   .build();

			SimpleTrigger simpleTigger2 = TriggerBuilder.newTrigger()
									   				   .withIdentity("trigger2", "group2")
									   				   .startNow()
									   				   .withSchedule(SimpleScheduleBuilder.simpleSchedule()	
									   				   .withIntervalInSeconds(20)
									   				   .repeatForever())
									   				   .build();
			scheduler2.scheduleJob(job2,simpleTigger2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
