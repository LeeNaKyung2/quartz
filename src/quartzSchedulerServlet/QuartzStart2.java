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

import quartzScheduler.UpdateQuartz;

@WebServlet("/QuartzStart2")
public class QuartzStart2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
		private SchedulerFactory schedulerFactory;
		private Scheduler scheduler3;

		Connection conn = null;

		protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");

			try {
				
				Context initContext = new InitialContext();
				Context envContext = (Context) initContext.lookup("java:/comp/env");
				DataSource ds = (DataSource) envContext.lookup("jdbc/Oracle11g");
				conn = ds.getConnection();

				schedulerFactory = new StdSchedulerFactory();
				scheduler3 = schedulerFactory.getScheduler();
				scheduler3.start();

				JobDetail job3 = JobBuilder.newJob(UpdateQuartz.class)
										   .withIdentity("job3", "group3")
										   .build();

				SimpleTrigger simpleTigger3 = TriggerBuilder.newTrigger()
															.withIdentity("trigger3", "group3")
															.startNow()
															.withSchedule(SimpleScheduleBuilder.simpleSchedule()
															.withIntervalInSeconds(30)
															.repeatForever())
															.build();
				scheduler3.scheduleJob(job3, simpleTigger3);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	