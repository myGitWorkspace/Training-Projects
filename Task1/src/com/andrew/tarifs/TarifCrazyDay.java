package com.andrew.tarifs;

import com.andrew.Tarif;

/**
 * 
 * Class represents Crazy Day Tarif
 *
 */
public class TarifCrazyDay extends Tarif {
	
	public TarifCrazyDay() {
		this.name = "CrazyDay";
		this.callCost = 0.4;
		this.smsCost = 0.42;
		this.internetCost = 0.6;
		this.roamingCost = 1.3;
	}
	
	/**
	 * 
	 * Counts discount for Crazy Day Tarif
	 * 
	 * @return Discount value for current tarif
	 *
	 */
	public double countDiscount() {
		
		return 0.6*callCost*smsCost + internetCost;
	}
}
