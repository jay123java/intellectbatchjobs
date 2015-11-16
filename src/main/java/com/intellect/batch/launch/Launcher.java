package com.intellect.batch.launch;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.intellect.batch.email.MailSender;
import com.intellect.batch.model.Alert;

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
	
	@Autowired
	private VelocityEngine engine;
	
	@Scheduled(cron = "0/20 * * * * ?")
	public void launchBookigsJob() {
		
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("time", new Date());
		model.put("message", "mytest");
		
		Alert al = new Alert();
		al.setAlertNumber("12345");
		al.setCompanyCode("CS");
		al.setCustomerName("jonathan ");

		Alert al2 = new Alert();
		al2.setAlertNumber("34567");
		al2.setCompanyCode("ME");
		al2.setCustomerName("meghan ");		
		
		List<Alert> alertList = Arrays.asList(al,al2);
		
        VelocityContext context = new VelocityContext();
        context.put("alertList", alertList);
        Template t = this.engine.getTemplate( "./templates/bookingAlert.vm" );
		
/*		System.out.println(VelocityEngineUtils.mergeTemplateIntoString(this.engine,
				"bookingAlert.vm", "UTF-8", model));*/
        StringWriter writer = new StringWriter();

        t.merge( context, writer );
        //System.out.println( writer.toString() );

		/*System.out.println(VelocityEngineUtils.mergeTemplateIntoString(this.engine,
				"bookingAlert.vm", "UTF-8", model));*/
		
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
