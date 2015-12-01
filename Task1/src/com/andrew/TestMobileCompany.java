package com.andrew;

import java.util.List;
import java.util.ArrayList;
import com.andrew.tarifs.*;

/**
 * 
 * Class for testing a model of Mobile company
 *
 */
public class TestMobileCompany {

	public static void main(String[] args) {
		
		MobileCompany company = new MobileCompany("Life");
		
		List<Tarif> tarifs = new ArrayList<>();
		tarifs.add(new TarifCrazyDay());
		tarifs.add(new TarifFreeLife());
		tarifs.add(new TarifSmartfone());
		tarifs.add(new TarifVipSmartfone());
		
		company.addTarifs(tarifs);
		
		company.addClient(tarifs.get(0), 10);
		company.addClient(tarifs.get(1), 20);
		company.addClient(tarifs.get(2), 30);
		company.addClient(tarifs.get(3), 40);
		
		System.out.println("Total number clients = " + company.countNumberClientsTotal());
		
		List<Tarif> sortedTarifs = company.sortTarifsByCallCost();
		System.out.println(sortedTarifs);
			
		Tarif searchTarif = company.findTarifByParams(0.5, 0.43, 0.7, 1.4);
		if(searchTarif != null)
			System.out.println("Found tarif = " + searchTarif);
		
	}
}
