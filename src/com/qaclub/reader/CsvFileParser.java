package com.qaclub.reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.opencsv.CSVReader;

import com.qaclub.model.Product;

/**
 * CsvFileParser uses 3rd party library http://opencsv.sourceforge.net/apidocs/
 * 
 */
public class CsvFileParser {

	public static void main(String[] args) {
		loadTestDataFromCsv();
	}

	public static void loadTestDataFromCsv() {

		String filePath = "testdata/qa-prices.csv";
		ArrayList<Product> products = new ArrayList<Product>();
		
		CSVReader reader;
		String [] nextLine;

		try {
			reader = new CSVReader(new FileReader(filePath));

			while ((nextLine = reader.readNext()) != null) {
				products.add(new Product(nextLine[0], nextLine[1], nextLine[2]));
			}

		} catch (FileNotFoundException e) {
			System.err.println("Error: " + e.getMessage());
		} catch (IOException e) {
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
