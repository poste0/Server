package com;



import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class MainTest
{
	public MainTest()
	{
	}

	public static void main(String[] args) throws IOException, DocumentException, InterruptedException
	{

		String fileName = "out" + UUID.randomUUID() + ".pdf";
		FileOutputStream out = new FileOutputStream(fileName);
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document , out);
		document.open();
		BarcodeQRCode code = new BarcodeQRCode("Hello Here" , 100 , 100 , null);
		Image image = code.getImage();
		document.add(image);
		document.close();
		File file = new File(fileName);
		Thread.sleep(5000);
		file.delete();

		

		

	}

}