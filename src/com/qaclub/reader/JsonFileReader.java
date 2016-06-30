package com.qaclub.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.json.JSONArray;

public class JsonFileReader {

	public static void main(String[] args) {
		loadTestDataFromJson();
	}

	public static void loadTestDataFromJson() {

		String filePath = "testdata/qa-currency.json";
		Scanner scanner;
		String inputString = "";
		JSONArray jsonArray;

		try {
			scanner = new Scanner(new File(filePath));

			while (scanner.hasNext()) {
				inputString += scanner.nextLine();
			}

			scanner.close();

			// build a JSON array
			jsonArray = new JSONArray(inputString);
			for (int i = 0; i < jsonArray.length(); i++)
			{
				System.out.println(jsonArray.getJSONObject(i).getString("cc") + " (" 
						+ jsonArray.getJSONObject(i).getString("txt") + ") : " 
						+ jsonArray.getJSONObject(i).getDouble("rate"));
			}

		} catch (FileNotFoundException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}
