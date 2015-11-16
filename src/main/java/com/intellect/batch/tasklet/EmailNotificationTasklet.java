package com.intellect.batch.tasklet;

import java.io.StringWriter;
import java.util.List;

import javax.annotation.Nullable;
import javax.annotation.PostConstruct;
import javax.mail.MessagingException;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Multimaps;
import com.intellect.batch.email.MailSender;
import com.intellect.batch.model.Alert;

public class EmailNotificationTasklet implements Tasklet{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private VelocityEngine engine;
	
	private String selectAlertsQuery;
	
	@Autowired
	private MailSender mailSender;
	
	
	private String emailTemplatePath;
	
	private String alertSubject;
	
	Template template;

	@PostConstruct
	public void init(){
		template = this.engine.getTemplate( emailTemplatePath);
	}
	
	
	public void setSelectAlertsQuery(String selectAlertsQuery) {
		this.selectAlertsQuery = selectAlertsQuery;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {
		
		System.out.println("Emailing job");
		
		List<Alert> alertList = jdbcTemplate.query(selectAlertsQuery, new BeanPropertyRowMapper(Alert.class));    		
	 
		Function<Alert, String> alertMapFunction = new Function<Alert, String>(){

			@Override
			@Nullable
			public String apply(@Nullable Alert input) {
				return input.getEmail();
			}
			
			
		};
		
		ImmutableListMultimap<String, Alert> aletsMap = Multimaps.index(alertList, alertMapFunction);

		for(String email: aletsMap.keySet()){
			
			ImmutableList<Alert> alertMap = aletsMap.get(email);
			
			String fullName = alertMap.get(0).getFullName();
			VelocityContext context = new VelocityContext();
	        context.put("alertList", alertMap);
	        context.put("fullName", fullName);
	        StringWriter writer = new StringWriter();
	        template.merge( context, writer );
	        
			System.out.println("emailing"+writer.toString());
			
			try {
				mailSender.send(email, alertSubject.replace("{fullName}", fullName), writer.toString());
			} catch (MessagingException e) {
				
				
			}
			
		}
		
		
		return RepeatStatus.FINISHED;
	}

	public void setEmailTemplatePath(String emailTemplatePath) {
		this.emailTemplatePath = emailTemplatePath;
	}


	public void setAlertSubject(String alertSubject) {
		this.alertSubject = alertSubject;
	}

}
