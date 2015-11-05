package com.intellect.batch.email;

import javax.mail.MessagingException;

public interface MailSender {

	public void send(String to, String from, String body) throws MessagingException;
}
