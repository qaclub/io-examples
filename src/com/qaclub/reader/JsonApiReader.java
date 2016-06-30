package com.qaclub.reader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;

public class JsonApiReader {

	public static void main(String[] args) {
		loadTestDataFromJson();
	}

	public static void loadTestDataFromJson() {

		String webLink = "http://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?date=20160629&json";
		Scanner scanner;
		String inputString = "";
		JSONArray jsonArray;

		try {
			URL url = new URL(webLink);
			
			scanner = new Scanner(url.openStream());

			while (scanner.hasNext()) {
				inputString += scanner.nextLine();
			}

			scanner.close();

			// Build a JSON array from String
			jsonArray = new JSONArray(inputString);
			for (int i = 0; i < jsonArray.length(); i++)
			{
				System.out.println(jsonArray.getJSONObject(i).getString("cc") + " (" 
						+ jsonArray.getJSONObject(i).getString("txt") + ") : " 
						+ jsonArray.getJSONObject(i).getDouble("rate"));
			}

		} catch (MalformedURLException e) {
			System.err.println("Error: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}
