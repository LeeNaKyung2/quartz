package quartzSchedulerServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
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

import com.google.gson.Gson;

import quartzScheduler.InsertQuartz;
import quartzScheduler.QuartzVO;
import quartzScheduler.UpdateQuartz;

public class Quartz extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 private SchedulerFactory schedulerFactory;
	 private Scheduler scheduler;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		Connection conn = null;

		try {

			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/Oracle11g");
			conn = ds.getConnection();
			
			/*schedulerFactory = new StdSchedulerFactory();
			scheduler = schedulerFactory.getScheduler();
			scheduler.start();
			// insert 20초
			JobDetail job = JobBuilder.newJob(InsertQuartz.class)
									  .withIdentity("job", "group")
									  .build();

			SimpleTrigger simpleTigger = TriggerBuilder.newTrigger()
													   .withIdentity("trigger", "group")
													   .startNow()
													   .withSchedule(SimpleScheduleBuilder.simpleSchedule()	
													   .withIntervalInSeconds(20)
													   .repeatForever())
													   .build();
			scheduler.scheduleJob(job, simpleTigger);
			
			// update 30초
			JobDetail job1 = JobBuilder.newJob(UpdateQuartz.class)
									   .withIdentity("job1", "group1")
									   .build();

			SimpleTrigger simpleTigger1 = TriggerBuilder.newTrigger()
														.withIdentity("trigger1", "group1")
														.startNow()
														.withSchedule(SimpleScheduleBuilder.simpleSchedule()
														.withIntervalInSeconds(30)
														.repeatForever())
														.build();
			scheduler.scheduleJob(job1, simpleTigger1);*/
			
			List<QuartzVO> list = getquartzList(conn);

			Gson gson = new Gson();
			String jsonPlacetest = gson.toJson(list);
			PrintWriter out = response.getWriter();
			out.write(jsonPlacetest);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public List<QuartzVO> getquartzList(Connection conn) throws Exception {

		List<QuartzVO> quartzList = new ArrayList<QuartzVO>();
		PreparedStatement pstmtList = null;
		ResultSet rsList = null;

		try {
			// 리스트 출력
			pstmtList = conn.prepareStatement("select * from quartz ORDER BY num desc");
			rsList = pstmtList.executeQuery();

			while (rsList.next()) {
				QuartzVO quartzInfo = new QuartzVO();
				quartzInfo.setNum(rsList.getInt("num"));
				quartzInfo.setType1(rsList.getString("type1"));
				quartzInfo.setType1_Time(rsList.getString("type1_Time"));
				quartzInfo.setType2(rsList.getString("type2"));
				quartzInfo.setType2_Time(rsList.getString("type2_Time"));
				quartzList.add(quartzInfo);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (rsList != null) {
				try {
					rsList.close();
				} catch (Exception e) {
				}
			}
			if (pstmtList != null) {
				try {
					pstmtList.close();
				} catch (Exception e) {
				}
			}
		}
		return quartzList;
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			schedulerFactory = new StdSchedulerFactory();
			scheduler = schedulerFactory.getScheduler();
			scheduler.start();

			// insert 20초
			JobDetail job = JobBuilder.newJob(InsertQuartz.class).withIdentity("job", "group").build();

			SimpleTrigger simpleTigger = TriggerBuilder.newTrigger().withIdentity("trigger", "group").startNow()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(20).repeatForever())
					.build();
			scheduler.scheduleJob(job, simpleTigger);

			// update 30초
			JobDetail job1 = JobBuilder.newJob(UpdateQuartz.class).withIdentity("job1", "group1").build();

			SimpleTrigger simpleTigger1 = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startNow()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(30).repeatForever())
					.build();
			scheduler.scheduleJob(job1, simpleTigger1);
			super.init(config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
