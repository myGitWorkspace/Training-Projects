package com.andrew;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;

/**
 * 
 * Class models entity Mobile company.
 *
 */
public class MobileCompany {

	private List<Tarif> tarifs = new ArrayList<>();
	private String name;
	private Map<Tarif,Integer> numberClients = new HashMap<>();
	
	public MobileCompany(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * Method counts the total number of clients in the company
	 * 
	 * @return Number of clients in the company
	 *
	 */
	public int countNumberClientsTotal() {
		int counter = 0;
		for(int number : numberClients.values())
			counter += number;
		return counter;
	}
	
	/**
	 * 
	 * Method sorts the tarifs of the company by callCost parameter
	 * 
	 * @return Sorted list of tarifs
	 *
	 */
	public List<Tarif> sortTarifsByCallCost() {
		List<Tarif> sortedTarifs = new ArrayList<>(tarifs);
		Collections.sort(sortedTarifs, new Comparator<Tarif>() {
			@Override
			public int compare(Tarif tarif1, Tarif tarif2) {
				if(tarif1.getCallCost() == tarif2.getCallCost())
					return 0;
				return (tarif1.getCallCost() > tarif2.getCallCost()) ? 1 : -1;
			}
		});
		return sortedTarifs;
	}
	
	/**
	 * 
	 * Method finds concrete Tarif by input parameters.
	 *
	 * @param callCost The cost of the call
	 * @param smsCost The cost of the sms service
	 * @param internetCost The cost of the internet service
	 * @param roamingCost The cost of the roaming call
	 * @return Tarif object that matches search parameters
	 * 
	 */
	public Tarif findTarifByParams(double callCost, double smsCost, double internetCost,
			double roamingCost) {
		
		for(Tarif tarif : tarifs) {
			if(tarif.getCallCost() == callCost && tarif.getSmsCost() == smsCost && 
					tarif.getInternetCost() == internetCost && tarif.getRoamingCost() == roamingCost)
				return tarif;
		}
		return null;
	}
	
	/**
	 * 
	 * Method adds new client to the company.
	 *
	 * @param targetTarif The tarif for a new client
	 * @param number Number of clients to be added
	 */
	public void addClient(Tarif targetTarif, int number) {
		int newNumber = number;
		if(numberClients.containsKey(targetTarif))
			newNumber += numberClients.get(targetTarif);
		numberClients.put(targetTarif, newNumber);
	}
	
	/**
	 * 
	 * Method adds new list of tarifs to the company.
	 *
	 * @param list List of added tarifs
	 */
	public void addTarifs(List<Tarif> list) {
		tarifs.addAll(list);
	}
	
	/**
	 * 
	 * Method returns the name of the company.
	 * 
	 * @return Name of the company
	 *
	 */
	public String getName() {
		return this.name;
	}
}
