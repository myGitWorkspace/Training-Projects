package com.andrew;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * Class for modeling a text sentence
 *
 */
public class Sentence implements Comparable<Sentence> {

	private String sentence;
	private List<Word> words = new ArrayList<>();
	private Symbol stopSymbol;	
	
	public Sentence(String sentence, String stopSymbol) {
		this.sentence = sentence;
		this.stopSymbol = new Symbol(stopSymbol);
	}
	
	/**
	 * 
	 * Method converts sentence to list of Words
	 *
	 */
	public void sentenceToWordsArray() {
		
		Pattern pattern = Pattern.compile("([^\\s*,:;\\-\"\\[\\]\\(\\)\\{\\}\\.\\!\\?]+?)([\\s*,:;\\-\"\\[\\]\\(\\)\\{\\}\\.\\!\\?])");
		Matcher matcher = pattern.matcher(this.sentence);
		
		while(matcher.find()) {
			Word word = new Word(matcher.group(1).trim(), matcher.group(2));
			word.wordToSymbolArray();
			words.add( word );
		}
		
	}
	
	/**
	 * 
	 * Overrides equal method of Object class
	 *
	 * @param object2 Object to be compared with
	 * @return true if objects are equal, false - otherwise
	 * 
	 */
	@Override
	public boolean equals(Object object2) {
		
		if (object2 == null) 
			return false;
		if(this.getClass() != object2.getClass())
			return false;
		
		Sentence sentence2 = (Sentence)object2;
		if (!this.stopSymbol.equals(sentence2.getStopSymbol()))
			return false;
		for(int i=0; i<words.size(); i++) {
			if ( !words.get(i).equals(sentence2.getWordAt(i)) )
				return false;
		}
			
		return true;
	}
	
	/**
	 * 
	 * Overrides hashcode method of the object class
	 * 
	 * @return Hashcode number of the object
	 *
	 */
	@Override
	public int hashCode() {
		return sentence.hashCode();
	}
	
	/**
	 * 
	 * Overrides compareTo method of Comparable
	 *
	 * @param sentence2 Object to be compared with
	 * @return 0 - if objects are equal, 1 - if current object is greater, -1 - if current object is less
	 * 
	 */
	@Override
	public int compareTo(Sentence sentence2) {
	
		if ( sentence.length() == sentence2.getLength() ) {
			if ( stopSymbol.equals(sentence2.getStopSymbol()) && this.equals(sentence2) )
				return 0;			
		}
		return (sentence.length() > sentence2.getLength()) ? 1 : -1; 		
	}
	
	/**
	 * 
	 * Get word at position index
	 *
	 * @param index index
	 * @return Word object at specified position
	 * 
	 */
	public Word getWordAt(int index) {
		
		if (index < 0 || index >= words.size()) 
			throw new IllegalArgumentException("index parameter in getWordAt method is wrong!!!");
		
		return words.get(index);
	}
	
	/**
	 * 
	 * Returns symbol size of sentence
	 * 
	 * @return Number of symbols in the sentence
	 *
	 */
	public int getLength() {
		return sentence.length();
	}	
	
	/**
	 * 
	 * Returns number of words in the sentence
	 * 
	 * @return Number of words in the sentence
	 *
	 */
	public int getNumberWords() {
		return words.size();
	}
	
	/**
	 * 
	 * Returns the stop symbol of the sentence
	 * 
	 * @return The last symbol in the sentence 
	 *
	 */
	public Symbol getStopSymbol() {
		return stopSymbol;
	}
	
	/**
	 * 
	 * Returns string representation of the object
	 * 
	 * @return String representation of the object
	 *
	 */
	@Override
	public String toString() {
		return sentence;
	}
	
	/**
	 * 
	 * Returns the list of words
	 * 
	 * @return List of all words in the sentence
	 *
	 */
	public List<Word> getWords() {
		return this.words;
	}
	
}
