package com.andrew;

/**
 * 
 * Class for modeling a symbol
 *
 */
public class Symbol implements Comparable<Symbol> {

	private char charSymbol;
	private boolean isCapital = false;
	
	public Symbol(String stringSymbol) {
		this.charSymbol = stringSymbol.substring(0, 1).charAt(0);
		this.isCapital = Character.isUpperCase(charSymbol);
	}

	/**
	 * 
	 * Overrides equals method of Object class
	 *
	 * @param object2 Object to be compared with
	 * @return true if objects are equal, false - otherwise
	 * 
	 */
	@Override
	public boolean equals(Object object2) {
		
		if (object2 == null) 
			return false;
		
		if (this.getClass() != object2.getClass()) 
			return false;
		
		Symbol symbol2 = (Symbol)object2;
		
		return charSymbol == symbol2.getCharSymbol();
	}
	
	/**
	 * 
	 * Overrides hashCode method of Object class
	 * 
	 * @return Hashcode number of the object
	 *
	 */
	@Override
	public int hashCode() {
		return String.valueOf(charSymbol).hashCode();
	}
	
	/**
	 * 
	 * Overrides compareTo method of the Comparable
	 *
	 * @param symbol2 Object to be compared with
	 * @return 0 - if objects are equal, 1 - if current object is greater, -1 - if current object is less
	 * 
	 */
	@Override
	public int compareTo(Symbol symbol2) {
		char char2 = symbol2.toString().charAt(0);
		if(charSymbol == char2)
			return 0;
		return (charSymbol > char2) ? 1 : -1;
	}
	
	/**
	 * 
	 * Checks if symbol is capital
	 * 
	 * @return true, if symbol is in capital case, false - otherwise
	 *
	 */
	public boolean isCapital() {
		return isCapital;
	}
	
	/**
	 * 
	 * Method returns char representation of Symbol object
	 * 
	 * @return Current symbol
	 *
	 */
	public char getCharSymbol() {
		return this.charSymbol;
	}
	
	/**
	 * 
	 * String representation of the Symbol object
	 * 
	 * @return String representation of the object
	 *
	 */
	@Override
	public String toString() {
		return String.valueOf(charSymbol);
	}
	
}
