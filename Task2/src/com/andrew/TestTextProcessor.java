package com.andrew;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * Class for testing a model of Text processor
 *
 */
public class TestTextProcessor {

	public static void main(String[] args) {
		
		String filename = "InputText.txt";
		File file = new File(filename);
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		String textToParse = "";
		String data = "";
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			while ( (data = bufferedReader.readLine()) != null ) {
				textToParse += data;
			}
		} catch (IOException e) {
			System.err.println("File error"+e);
		} finally {
			try {
				if (bufferedReader != null)
					bufferedReader.close();
			} catch (IOException e) {
				System.err.println("Closing file error"+e);
			}
		}
		
		TextProcessor textProcessor = new TextProcessor(textToParse);
		textProcessor.textToSentenceArray();
		
		System.out.println(textProcessor.printWordsInQuestionSentencesByLength(8));
				
	}

	
}
