package com.services;

import com.configuration.MailSenderConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.InputStreamResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.IOException;

@Component("emailService")
@ComponentScan(basePackageClasses = MailSenderConfiguration.class)
public class EmailService {
	@Autowired
	private JavaMailSender mailSender;

	
	public void sendSimpleMessage(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		mailSender.send(message);
	}

	public void sendMessageWithAttachment(String to , String subject , Image attachment) throws MessagingException, IOException
	{
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setTo(to);
		helper.setSubject(subject);
		//TODO Attach 

	}
}