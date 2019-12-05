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


import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;


@WebServlet("/QuartzStop2")
public class QuartzStop2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SchedulerFactory schedulerFactory = new StdSchedulerFactory();
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
			
			Scheduler scheduler1 = schedulerFactory.getScheduler();
			JobKey jobKey1 = new JobKey("job1", "group1");
			scheduler1.deleteJob(jobKey1);
			
			Scheduler scheduler3 = schedulerFactory.getScheduler();
			JobKey jobKey3 = new JobKey("job3", "group3");
			scheduler3.deleteJob(jobKey3);

			} catch (Exception e) {
			e.printStackTrace();
		}
	}
}