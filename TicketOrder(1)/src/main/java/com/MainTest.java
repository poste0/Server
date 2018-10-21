package com;



import com.entities.*;
import com.entities.users.Admin;
import com.entities.users.Visitor;
import com.events.Event;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class MainTest
{
	public MainTest()
	{
	}

	public static void mains(String[] args) throws IOException, DocumentException, InterruptedException
	{

		String fileName = "out" + UUID.randomUUID() + ".pdf";
		FileOutputStream out = new FileOutputStream(fileName);
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document , out);
		document.open();
		BarcodeQRCode code = new BarcodeQRCode("Hello Here" , 100 , 100 , null);
		Image image = code.getImage();

		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		font.setStyle(Font.ITALIC);
		font.setColor(BaseColor.BLUE);
		Paragraph paragraph = new Paragraph("This is your ticket." , font);
		document.add(paragraph);
		font = new Font(Font.FontFamily.TIMES_ROMAN);
		font.setStyle(Font.ITALIC);
		font.setColor(BaseColor.BLUE);
		paragraph = new Paragraph("Film : " + new Film("Title" , "Descr" , new Rating() , new Date()).getTitle() , font);
		document.add(paragraph);
		document.add(image);
		document.close();
		File file = new File(fileName);

		

		

	}

	public static void main(String[] args) throws Exception
	{
		Visitor visitor = new Visitor("test" , "test" , 22);
		Film film = new Film("test" , "test" , new Rating() , new Date());
		Admin admin = new Admin("admin" , new Date() , 20);
		Session session = new Session(film , admin.createHall(100) , new Date() , 500d);
		visitor.setBoughtTickets(21);
		Event event = new Event("AGE> 21,50,%");
		Event.addEvent(event);
		Order order = new Booking(session , visitor , 10);
		System.out.println(order.getCost());
	}






}