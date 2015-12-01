package com.andrew.tarifs;

import com.andrew.Tarif;

/**
 * 
 * Class represents Free Life Tarif
 *
 */
public class TarifFreeLife extends Tarif {
	
	public TarifFreeLife() {
		this.name = "FreeLife";
		this.callCost = 0.5;
		this.smsCost = 0.43;
		this.internetCost = 0.7;
		this.roamingCost = 1.4;
	}
	
	/**
	 * 
	 * Counts discount for Free Life Tarif
	 * 
	 * @return Discount value for current tarif
	 *
	 */
	public double countDiscount() {
		
		return 0.7*callCost*smsCost - 0.01*callCost + 2;
	}
}