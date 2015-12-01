package com.andrew;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * 
 * Abstract builder class for Plane entity. 
 * Contains single abstract method - buildListPlanes
 *
 */
public abstract class AbstractPlanesBuilder {

	protected List<Plane> planes;
	
	public AbstractPlanesBuilder() {
		
	}
	
	public AbstractPlanesBuilder(List<Plane> planes) {
		this.planes = planes;
	}
	
	/**
	 * 
	 * Get the resulted list of parsed planes
	 * 
	 * @return List of all parsed planes
	 *
	 */
	public List<Plane> getPlanes() {
		return this.planes;
	}
	
	/**
	 * 
	 * Get sorted list of parsed planes
	 * 
	 * @return Sorted list of all parsed planes
	 *
	 */
	public List<Plane> getSortedPlanes() {
		List<Plane> sortedPlanes = new ArrayList<>();
		sortedPlanes.addAll(planes);
		Collections.sort(sortedPlanes, new Comparator<Plane>(){
			public int compare(Plane plane1, Plane plane2) {
				return plane1.getPrice() - plane2.getPrice();
			}
		});
		return this.planes;
	}
	
	/**
	 * 
	 * Abstract method for parsing all Plane objects from the specified XML file
	 * 
	 * @param filename XML file to be parsed
	 *
	 */
	public abstract void buildListPlanes(String filename);
}
