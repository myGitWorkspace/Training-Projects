package com.andrew.tarifs;

import com.andrew.Tarif;

/**
 * 
 * Class represents Vip Smartfone Tarif
 *
 */
public class TarifVipSmartfone extends Tarif {
	
	public TarifVipSmartfone() {
		this.name = "VipSmartfone";
		this.callCost = 0.6;
		this.smsCost = 0.54;
		this.internetCost = 0.8;
		this.roamingCost = 1.5;
	}
	
	/**
	 * 
	 * Counts discount for Vip Smartfone Tarif
	 * 
	 * @return Discount value for current tarif
	 *
	 */
	public double countDiscount() {
		
		return 0.4*callCost*smsCost - 0.05*smsCost*roamingCost + 7;
	}
}
