package com.intellect.batch.configuration;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@EnableConfigurationProperties(MailProperties.class) 

public class EmailConfiguration {

	 @Autowired
	    private MailProperties mailProperties;

	// @Bean
	    public JavaMailSender javaMailSender() {
	        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	        Properties emailProps = new Properties();
	        emailProps.put("mail.smtp.auth", mailProperties.getSmtp().isAuth());
	        emailProps.put("mail.smtp.starttls.enable", mailProperties.getSmtp().isStarttlsEnable());
	        emailProps.put("mail.smtp.starttls.enable", mailProperties.getSmtp().isStarttlsEnable());
	        emailProps.put("mail.smtp.starttls.enable", mailProperties.getSmtp().isStarttlsEnable());

	        mailSender.setJavaMailProperties(emailProps);
	        mailSender.setHost(mailProperties.getHost());
	        mailSender.setPort(mailProperties.getPort());
	        mailSender.setProtocol("smtp");
	        mailSender.setUsername(mailProperties.getUsername());
	        mailSender.setPassword(mailProperties.getPassword());
	        return mailSender;
	    }
}

