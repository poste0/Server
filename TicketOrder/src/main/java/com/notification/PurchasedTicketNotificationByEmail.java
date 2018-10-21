package com.notification;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfWriter;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PurchasedTicketNotificationByEmail extends OrderNotificationByEmail{
	public PurchasedTicketNotificationByEmail()
	{
	}

	private String fileName = "Out";
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