package com.intellect.batch.launch;

import java.util.Date;

import javax.mail.MessagingException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.intellect.batch.email.MailSender;

@Component
public class Launcher {

	@Autowired
	private JobLauncher launcher;

	@Autowired
	@Qualifier(value = "missedBookingsJob")
	private Job bookingsJob;

	@Autowired
	@Qualifier(value = "missedHblJob")
	private Job hblJob;

	@Autowired
	private MailSender mailSender;
	
	@Scheduled(cron = "0/20 * * * * ?")
	public void launchBookigsJob() {
		try {
			String dateParam = new Date().toString();
			JobParameters param = new JobParametersBuilder().addString("date",
					dateParam).toJobParameters();
			System.out.println(dateParam);
			JobExecution execution = launcher.run(bookingsJob, param);
			System.out.println("Exit Status : " + execution.getStatus());
		} catch (Exception e) {
		}
	}

	@Scheduled(cron = "0 29 21 * * ?")
	public void launchHBLsJob() {
		try {
			String dateParam = new Date().toString();
			JobParameters param = new JobParametersBuilder().addString("date",
					dateParam).toJobParameters();
			System.out.println(dateParam);
			JobExecution execution = launcher.run(hblJob, param);
			System.out.println("Exit Status : " + execution.getStatus());
		} catch (Exception e) {
		}
	}
	

	@Scheduled(cron = "0 27 16 * * ?")
	public void sendEmail() {
		
		System.out.println("emailing");
		
		try {
			mailSender.send("skantipudi@intellecttech.com", "testEmail", "This is final report");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
