package com.notification;

import com.repositories.FilmRepository;
import com.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileNotFoundException;

/**
 * Contains email of the user. It's abstract where methods notifyUser is not created.
 */
public abstract class AbstractNotificationByEmail implements Notification {
	public AbstractNotificationByEmail() {
	}

	protected String email;

	protected EmailService emailService;

	protected String message;

	protected String subject;


	@Autowired
	public void setEmailService(EmailService emailService){this.emailService = emailService;}

	public abstract void notifyUser() throws FileNotFoundException;
}