package com.qaclub.writer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SimpleFileWriter {

	public static void main(String[] args) {
		saveDataToFile();
	}

	public static void saveDataToFile() {

		String inputFilePath = "testdata/qa-test-simple.txt";
		String outputFilePath = "testdata/qa-test-simple-output.txt";
		
		try {
			FileInputStream fis = new FileInputStream(inputFilePath);
			FileOutputStream fout = new FileOutputStream(outputFilePath);
			
			int c;
			while ((c = fis.read()) != -1) {
				fout.write(c);
			}
			
			if (fis != null) {
				fis.close();
			}
			if (fout != null) {
				fout.close();
			}
			
		} catch (IOException e) {
			System.out.println("Error : " + e.getMessage());
		}
		
		System.out.println("Data saved to file: " + outputFilePath);

	}

}
