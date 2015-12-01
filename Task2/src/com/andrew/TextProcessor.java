package com.andrew;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/**
 * 
 * Class for modeling a text processor
 *
 */
public class TextProcessor {

	private String stringText;
	private List<Sentence> sentences = new ArrayList<>();	
	
	public TextProcessor(String stringText) {
		this.stringText = stringText;
	}
	
	/**
	 * 
	 * Converts text to the list of sentences
	 *
	 */
	public void textToSentenceArray() {		
		Pattern pattern = Pattern.compile("([^\\.\\!\\?]+?)([\\.\\!\\?])");
		Matcher matcher = pattern.matcher(this.stringText);
		
		while(matcher.find()) {
			Sentence sentence = new Sentence(matcher.group(1).trim()+matcher.group(2), matcher.group(2));
			sentence.sentenceToWordsArray();
			sentences.add( sentence );
		}
	}
	
	/**
	 * 
	 * Method prints without repeat all words in question sentences that match given size
	 *
	 * @param wordLength Length of words to be found
	 * @return Set of all words in the sentence
	 * 
	 */ 
	public Set<String> printWordsInQuestionSentencesByLength(int wordLength) {
		
		if (wordLength <= 0 ) 
			throw new IllegalArgumentException("wordLength parameter in printWordsInQuestionSentencesByLength method is wrong!!!");
		
		Set<String> wordsSet = new HashSet<>();
		for(Sentence sentence : sentences) {
			if ( sentence.getStopSymbol().toString().equals("?") ) {	
				for(Word word : sentence.getWords()) {
					if (word.getWordLength() == wordLength)
						wordsSet.add(word.toString());
				}
			}
		}

		if (wordsSet.isEmpty())			
			return null;
		
		return wordsSet;
	}
	
	/**
	 * 
	 * String representation of the TextProcessor object
	 * 
	 * @return String representation of the object
	 *
	 */
	@Override
	public String toString() {
		return stringText;
	}
	
}
