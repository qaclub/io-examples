package com.qaclub.reader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class IniFileReader {

	public static void main(String[] args) {
		loadTestDataFromIni();
	}

	public static void loadTestDataFromIni() {

		String filePath = "testdata/qa-test-parameters.ini";
		FileInputStream fis;
		Properties params = new Properties();

		try {
			fis = new FileInputStream(filePath);
			System.out.println("Reading parameters from file" + filePath);
			params.load(fis);

			// Closing file
			fis.close();

		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}

		// Printing results to console
		System.out.println("page link = " + params.getProperty("page.link"));
		System.out.println("page title = " +  params.getProperty("page.title"));

		// Opening Chrome browser
		WebDriver driver = new ChromeDriver();
		driver.get(params.getProperty("page.link"));
		
		// Wait 5 seconds
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		// Verify page title
		WebElement title = driver.findElement(By.tagName("h1"));
		if (title.getText().equalsIgnoreCase(params.getProperty("page.title"))) {
			System.out.println("Passed: Element Found");
		} else {
			System.out.println("Failed: Element Not Found");
		}

		driver.close();

	}
}
