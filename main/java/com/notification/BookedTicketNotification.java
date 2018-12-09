package com.notification;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class BookedTicketNotification extends OrderNotification {
    public BookedTicketNotification() {
    }

    private String fileName = "Out";

    private void add(String text, Font font, Document document) throws DocumentException {
        Paragraph paragraph = new Paragraph(text, font);
        document.add(paragraph);
    }

    @Override
    public void notifyUser() {
        fileName += booking.getId() + ".pdf";
        Document document = new Document();
        try (FileOutputStream out = new FileOutputStream(fileName)) {
            PdfWriter writer = PdfWriter.getInstance(document, out);
            document.open();
            BarcodeQRCode code = new BarcodeQRCode(booking.getId().toString(), 100, 100, null);
            Image image = code.getImage();
            Font font = new Font(Font.FontFamily.TIMES_ROMAN);
            font.setStyle(Font.NORMAL);
            add("This is your ticket", font, document);
            add("Session : " + booking.getSession().getFilm() + booking.getSession().getTime().toString(), font, document);
            add("Ticket will be activated when you purchase it", font, document);
            document.add(image);
            document.close();
            emailService.sendMessageWithAttachment(email, subject, fileName);
        } catch (DocumentException | MessagingException | IOException e) {
            e.printStackTrace();
        }


    }
}