package com.school.springboot.config.quartz;

import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@DisallowConcurrentExecution
@SuppressWarnings("all")
@SuppressAjWarnings
public class TestJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // TODO Auto-generated method stub
        // System.err.println(context.getJobDetail());
    }
    
}
