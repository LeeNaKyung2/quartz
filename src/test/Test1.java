package test;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Test1 implements Job {
	public Test1() {
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.err.println("¾È³ç " + new Date());
	}
}
