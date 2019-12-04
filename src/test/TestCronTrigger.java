package test;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class TestCronTrigger {
	 private SchedulerFactory schedFact;
	 private Scheduler sched;

	public TestCronTrigger() {	
		try {
			  schedFact = new StdSchedulerFactory();
			   sched = schedFact.getScheduler();
			   sched.start();

			JobDetail job = JobBuilder.newJob(Test.class)
					.withIdentity("testjob")
					.build();
			
			SimpleTrigger simpleTigger = TriggerBuilder.newTrigger()
					.withIdentity("trigger1","group1")
					.startNow()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule()
														.withIntervalInSeconds(20)
														.repeatForever())
					.build();
			sched.scheduleJob(job,simpleTigger);

			JobDetail job1 = JobBuilder.newJob(Test1.class)
					.withIdentity("testjob1")
					.build();
			
			SimpleTrigger simpleTigger1 = TriggerBuilder.newTrigger()
					.withIdentity("trigger2","group2")
					.startNow()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule()
														.withIntervalInSeconds(30)
														.repeatForever())
					.build();
			sched.scheduleJob(job1,simpleTigger1);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
	}
	public static void main(String[] args) {
		new TestCronTrigger();

	}

}
	

