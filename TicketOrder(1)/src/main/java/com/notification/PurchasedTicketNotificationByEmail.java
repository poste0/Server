package com.notification;

import com.entities.Order;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfWriter;

import javax.mail.MessagingException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PurchasedTicketNotificationByEmail extends OrderNotificationByEmail{
	public PurchasedTicketNotificationByEmail()
	{
	}

	public PurchasedTicketNotificationByEmail(Order order , String email){
		super(order , email);
	}

	private String fileName = "Out";

	private void add(String text , Font font , Document document) throws DocumentException
	{
		Paragraph paragraph = new Paragraph(text , font);
		document.add(paragraph);
	}

	@Override
	public void notifyUser()
	{
		fileName += order.getId() + ".pdf";
		Document document = new Document();
		try(FileOutputStream out = new FileOutputStream(fileName))
		{

			PdfWriter writer = PdfWriter.getInstance(document, out);
			document.open();
			BarcodeQRCode code = new BarcodeQRCode(order.getId().toString(), 100, 100, null);
			Image image = code.getImage();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			font.setStyle(Font.NORMAL);
			add("This is your ticket" , font , document );
			add("Session : " + order.getSession().getFilm() + order.getSession().getTime().toString() , font , document);
			document.add(image);
			document.close();
			emailService.sendMessageWithAttachment(email , subject , fileName);
		}
		catch (DocumentException | MessagingException | IOException e)
		{
			e.printStackTrace();
		}


	}
}