package com.qaclub.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.qaclub.model.Product;

public class CsvFileReader {

	public static void main(String[] args) {
		loadTestDataFromCsv();
	}
	
	public static void loadTestDataFromCsv() {

		String filePath = "testdata/qa-prices.csv";
		Scanner scanner;
		
		ArrayList<Product> products = new ArrayList<Product>();
		
		try {
			scanner = new Scanner(new File(filePath));
			
			while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        String[] lineArray = line.split(",");
		        products.add(new Product(lineArray[0], lineArray[1], lineArray[2]));
		    }
			

		} catch (FileNotFoundException e) {
			System.err.println("Error: " + e.getMessage());
		}
		
		// Opening Chrome browser
		WebDriver driver = new ChromeDriver();
				
		// Check if title and price matches
		for (int i = 0; i < products.size(); i++) {
			
			// Load Product page in browser
			driver.get(products.get(i).getLink());
			
			// Wait 3 seconds
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			
			WebElement price = driver.findElement(By.id("price_label"));
			WebElement title = driver.findElement(By.tagName("h1"));
			
			// Check product's price
			if (price.getText().equalsIgnoreCase(products.get(i).getPrice())) {
				System.out.println("Passed: Price is " + price.getText());
			} else {
				System.out.println("Failed: Price is " + price.getText());
			}
			
			// Check product's title
			if (title.getText().trim().equalsIgnoreCase(products.get(i).getTitle())) {
				System.out.println("Passed: Title is " + title.getText());
			} else {
				System.out.println("Failed: Title is [" + title.getText() + "]");
			}
			
		}

		driver.close();

	}
	
	
}
