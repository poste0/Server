package com.services;

import com.configuration.MailSenderConfiguration;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
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

	public void sendMessageWithAttachment(String to , String subject , String fileName) throws MessagingException, IOException
	{
		try
		{
			File file = new File(fileName);
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.addAttachment("Ticket.pdf" , file);
			mailSender.send(message);
			file.delete();
		}
		catch (MessagingException e){
			throw e;
		}

	}
}