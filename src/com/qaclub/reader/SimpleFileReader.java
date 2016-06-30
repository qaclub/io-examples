package com.qaclub.reader;

import java.io.FileInputStream;
import java.io.IOException;

public class SimpleFileReader {

	public static void main(String[] args) {
		loadTestDataFromFile();
	}

	public static void loadTestDataFromFile() {

		String filePath = "testdata/qa-test-simple.txt";
		FileInputStream fis;
		byte[] bytes = new byte[255];
		char c;

		try {
			fis = new FileInputStream(filePath);
			System.out.println("Reading data from file" + filePath);

			// Sizes of bytes available for reading
			int bytesAvailable = fis.available();
			System.out.println("Ready for reading: " + bytesAvailable + " bytes");

			// Reading data
			fis.read(bytes);
			
			// Closing file
			fis.close();

		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
		
		// Printing result to console
		System.out.println("Result of reading file using FileInputStream:");
		for (int i=0; i < bytes.length; i++) {
			// Preparing char from byte we have read
			c = (char)(bytes[i] & 0xFF);
			System.out.print(c);
		}

	}

}
