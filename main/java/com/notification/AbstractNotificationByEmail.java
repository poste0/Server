package com.notification;

import com.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileNotFoundException;

public abstract class AbstractNotificationByEmail implements Notification {

    public AbstractNotificationByEmail() {}

    String email;
    String message;
    String subject;
    EmailService emailService;

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public abstract void notifyUser() throws FileNotFoundException;

}