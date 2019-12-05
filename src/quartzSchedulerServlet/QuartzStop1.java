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




@WebServlet("/QuartzStop1")
public class QuartzStop1 extends HttpServlet {
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

			Scheduler scheduler = schedulerFactory.getScheduler();
			JobKey jobKey = new JobKey("job", "group");
			scheduler.deleteJob(jobKey);
			
			Scheduler scheduler2 = schedulerFactory.getScheduler();
			JobKey jobKey2 = new JobKey("job2", "group2");
			scheduler2.deleteJob(jobKey2);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
