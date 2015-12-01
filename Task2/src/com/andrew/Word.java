package com.andrew;

import java.util.List;
import java.util.ArrayList;

/**
 * 
 * Class to model a word object
 *
 */
public class Word implements Comparable<Word> {

	private String word;
	private List<Symbol> symbols = new ArrayList<>();
	private Symbol punctuation;
	
	public Word(String word, String punctuation) {
		this.word = word;
		this.punctuation = new Symbol(punctuation);
	}
	
	/**
	 * 
	 * Method converts word to the list of symbols
	 *
	 */
	public void wordToSymbolArray() {
		char[] charArray = this.word.toCharArray();		
		for (int i=0; i<charArray.length; i++)
			symbols.add( new Symbol(String.valueOf(charArray[i])) );
	}
	
	/**
	 * 
	 * Method returns symbol at index position
	 *
	 * @param index Position of the symbol to be found
	 * @return Symbol object at the specified position
	 * 
	 */
	public Symbol getSymbolAt(int index) {
		
		if (index < 0 || index >= symbols.size()) 
			throw new IllegalArgumentException("index parameter in getSymbolAt method is wrong!!!");
		
		return symbols.get(index);
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
		
		if (object2.getClass() != object2.getClass())
			return false;
		
		Word word2 = (Word)object2;
		int length1 = word.length();
		int length2 = word2.getWordLength();
		if ( length1 == length2 ) {			
			for (int i=0; i<length1; i++) {					
				if ( !symbols.get(i).equals(word2.getSymbolAt(i)) )
					return false;
			}
			return true;
		}

		return false;
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
		return word.hashCode();
	}
	
	/**
	 * 
	 * Overrides compareTo method of the Comparable
	 * 
	 * @return 0 - if objects are equal, 1 - if current object is greater, -1 - if current object is less
	 *
	 */
	@Override
	public int compareTo(Word word2) {
		
		int length1 = word.length();
		int length2 = word2.getWordLength();
		if ( length1 == length2 ) {			
			for (int i=0; i<length1; i++) {
				int comp = symbols.get(i).compareTo(word2.getSymbolAt(i));
				if ( comp != 0 )
					return comp;
			}
			return 0;
		}
		return (length1 > length2) ? 1 : -1;
	}
	
	/**
	 * 
	 * Method returns the length of the word
	 * 
	 * @return Number of symbols in the word
	 *
	 */
	public int getWordLength() {
		return word.length();
	}
	
	/**
	 * 
	 * Method returns the punctuation symbol of the word
	 * 
	 * @return Punctuation symbol for current word
	 *
	 */
	public Symbol getPunctuation() {
		return punctuation;
	}
	
	/**
	 * 
	 * Method returns a string representation of the Word object
	 * 
	 * @return String representation of the object
	 *
	 */
	@Override
	public String toString() {
		return word;
	}
	
}
