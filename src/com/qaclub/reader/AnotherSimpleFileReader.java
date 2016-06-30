package com.qaclub.reader;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class AnotherSimpleFileReader {

	public static void main(String[] args) {
		loadTestDataFromFile();
	}

	public static void loadTestDataFromFile() {

		String filePath 	= "testdata/qa-test-simple.txt";
		char[] charArray	= new char[255];

		try {
			// Reading data
			Reader fileReader 	= new FileReader(filePath);
			int data = fileReader.read();
			int i = 0;
			
			while(data != -1) {
				charArray[i] = (char)data;
				data = fileReader.read();
				i++;
			}
			
			// Closing file
			fileReader.close();

		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}

		// Printing result to console
		System.out.println("Result of reading file using FileReader:");
		for (int j=0; j < charArray.length; j++) {
			// Preparing char from byte we have read
			System.out.print(charArray[j]);
		}

	}

}
