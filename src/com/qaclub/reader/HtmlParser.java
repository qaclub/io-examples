package com.qaclub.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;

import javax.swing.text.html.HTML.Attribute;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

public class HtmlParser {

	public static void main(String[] args) {
		loadHtmlContent();
	}
	
	public static void loadHtmlContent() {
		
		String filePath = "testdata/test-page.html";
		Scanner scanner;
		String inputString = "";
		
		try {
			scanner = new Scanner(new File(filePath));
			// Saving file's content into string (inputString)
			while (scanner.hasNext()) {
				inputString += scanner.nextLine();
			}
			
			scanner.close();
			
		} catch (FileNotFoundException e) {
			System.err.println("Error: " + e.getMessage());
		}
		
		Reader stringReader = new StringReader(inputString);
		
		HTMLEditorKit htmlKit = new HTMLEditorKit();
		HTMLDocument htmlDoc = (HTMLDocument) htmlKit.createDefaultDocument();
		HTMLEditorKit.Parser parser = new ParserDelegator();
		
		// Preparing HTMLDocument object from the string
		try {
			parser.parse(stringReader, htmlDoc.getReader(0), true);
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
		
		// Getting element with id=head_banner_container
		Element content = htmlDoc.getElement("head_banner_container");
		// Getting element with class=detail-title
		Element title 	= htmlDoc.getElement(content, Attribute.CLASS, "detail-title");
		
		// Printing content of the element's found to the console
		try {
			System.out.println("Title = " + htmlDoc.getText(title.getStartOffset(), title.getEndOffset() - title.getStartOffset()));
		} catch (BadLocationException e) {
			System.err.println("Error: " + e.getMessage());
		}
		
		
	}
	
}
