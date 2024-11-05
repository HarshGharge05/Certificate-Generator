package com.csv.certificateGenerator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.imageio.ImageIO;

import com.opencsv.CSVReader;

public class App 
{
	public static void generateCertificate(String templatePath, String saveOutput, String id, String name, String domain, String startDate, String endDate) throws IOException {
		
		BufferedImage templateImage = ImageIO.read(new File(templatePath));
		
		Graphics2D g2d = templateImage.createGraphics();
		
		LocalDate today = LocalDate.now();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		String todaysDate = today.format(formatter);
		
		// setting font and color
		g2d.setFont(new Font("Serif", Font.BOLD, 50));
		g2d.setColor(Color.black);
		
		// setting position 
//		g2d.drawString(todaysDate, (float) 191.842519685, (float) 413.54330709);
		g2d.drawString(name, (float) 692.80629921, (float) 583.31338583);
		g2d.drawString(domain, (float) 705.95905512, (float) 750.8992126);
		g2d.drawString(startDate, (float)648.50708661, (float)855.97795276);
		g2d.drawString(endDate, (float)948.50708661, (float)855.97795276);
		
		g2d.dispose();
		
		File outputFile = new File(saveOutput+"Certificate_"+id+".png");
		ImageIO.write(templateImage, "png", outputFile);
		
		System.out.println("Certificate generated for "+name);
		
	}
	
    public static void main( String[] args )
    {
        String csvFilePath = "F:\\C-DAC Mumbai Internship\\5th week\\CSV_file\\internData.csv";
        String templatePath = "F:\\C-DAC Mumbai Internship\\5th week\\Certificate_Template\\certificateTemplate.png";
        String saveOutput = "F:\\C-DAC Mumbai Internship\\5th week\\Generated_Certificates";
        
        try {
        	
        	CSVReader reader = new CSVReader(new FileReader(csvFilePath));
        	
        	boolean isFirstRow = true;
        	
        	List<String[]> records = reader.readAll();
        	
        	for(String[] record : records) {
        		
        		 // Skip the header row
                if (isFirstRow) {
                    isFirstRow = false;
                    continue;
                }
        		
        		String id = record[0];
        		System.out.println(record[0]);
        		String name = record[1];
        		System.out.println(record[1]);
        		String domain = record[2];
        		System.out.println(record[2]);
        		String startDate = record[3];
        		System.out.println(record[3]);
        		String endDate = record[4];
        		System.out.println(record[4]);
        		
        		generateCertificate(templatePath, saveOutput, id, name, domain, startDate, endDate);
        	}
        	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
}
