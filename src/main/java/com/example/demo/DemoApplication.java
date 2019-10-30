package com.example.demo;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



import javax.sql.DataSource;
import java.util.Date;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {



	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Autowired
	TestComponent testComponent;



	public void run(String... args) throws Exception {


	}



	@Bean
	public Scheduler scheduler(Trigger trigger,JobDetail job) throws Exception{
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();
		scheduler.scheduleJob(job, trigger);
		scheduler.start();
		return scheduler;
	}





	@Bean
	public JobDetail jobDetail() {
		return JobBuilder.newJob(HelloJob.class)
				.withIdentity("myJob", "group1")
				.usingJobData("jobSays", "Hello World!")
				.usingJobData("myFloatValue", 3.141f)
				.build();
	}




	@Bean
	public Trigger trigger(JobDetail job) {
		return TriggerBuilder.newTrigger().forJob(job)
				.withIdentity("Qrtz_Trigger")
				.withDescription("Sample trigger")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInSeconds(1))
				.build();
	}



}
