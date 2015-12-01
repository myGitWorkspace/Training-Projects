package com.andrew.tarifs;

import com.andrew.Tarif;

/**
 * 
 * Class represents Smartfone Tarif
 *
 */
public class TarifSmartfone extends Tarif {
	
	public TarifSmartfone() {
		this.name = "Smartfone";
		this.callCost = 0.3;
		this.smsCost = 0.32;
		this.internetCost = 0.5;
		this.roamingCost = 1.2;
	}
	
	/**
	 * 
	 * Counts discount for Smartfone Tarif
	 * 
	 * @return Discount value for current tarif
	 *
	 */
	public double countDiscount() {
		
		return 0.3*callCost*smsCost;
	}
}
