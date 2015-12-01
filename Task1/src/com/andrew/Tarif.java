package com.andrew;

/**
 * 
 * Class models entity of Tarif of mobile company.
 *
 */
public abstract class Tarif{

	protected String name;
	protected double callCost;
	protected double smsCost;
	protected double internetCost;
	protected double roamingCost;
	
	public Tarif() {
		
	}
	
	public Tarif(String name, double callCost, double smsCost, double internetCost,
			double roamingCost) {
		this.name = name;
		this.callCost = callCost;
		this.smsCost = smsCost;
		this.internetCost = internetCost;
		this.roamingCost = roamingCost;		
	}
	
	/**
	 * 
	 * Check if two Tarif objects are equal.
	 *
	 * @param tarif2 Object to be compared with
	 * @return true if objects are equal, false - otherwise
	 * 
	 */
	@Override
	public boolean equals(Object tarif2) {
		if(tarif2 == null)
			return false;
		if(tarif2.getClass() != this.getClass())
			return false;
		if(this.name.equals(((Tarif)tarif2).name))
			return true;
		return false;
	}
	
	/**
	 * 
	 * Hashcode of the Tarif object
	 * 
	 * @return Hashcode number of the object
	 *
	 */
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
	
	/**
	 * 
	 * Method returns the cost of current Tarif
	 * 
	 * @return The cost of the call for current tarif
	 *
	 */
	public double getCallCost() {		
		return this.callCost;
	}
	
	/**
	 * 
	 * Method returns the sms cost of the Tarif
	 * 
	 * @return The cost of sms service for current tarif
	 *
	 */
	public double getSmsCost() {		
		return this.smsCost;
	}
	
	/**
	 * 
	 * Method returns internet cost of the Tarif
	 * 
	 * @return The cost of internet service for current tarif
	 *
	 */
	public double getInternetCost() {		
		return this.internetCost;
	}
	
	/**
	 * 
	 * Method returns roaming cost of the Tarif
	 * 
	 * @return The cost of roaming service for current tarif
	 *
	 */
	public double getRoamingCost() {		
		return this.roamingCost;
	}
	
	/**
	 * 
	 * Method for string representation of the Tarif
	 * 
	 * @return String representation of the object
	 *
	 */
	@Override
	public String toString() {
		return " [ " + this.name + ", callCost = " + this.callCost + " ] ";
	}
	
	/**
	 * 
	 * Abstract method to count the discount of the Tarif
	 *
	 */
	public abstract double countDiscount();
}
