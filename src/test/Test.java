package test;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Test implements Job{
	public Test() {
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.err.println("Hello " +new Date());
	}
}
