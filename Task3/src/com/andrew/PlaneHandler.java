package com.andrew;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.util.List;
import java.util.ArrayList;
import java.util.EnumSet;

/**
 * 
 * Handler class of SAX builder to process all tags in XML file 
 * 
 */
public class PlaneHandler extends DefaultHandler {

	private List<Plane> planes;
	private Plane currentPlane = null;
	private PlaneEnum currentEnum = null;
	private EnumSet<PlaneEnum> parsedFileTitles;
	
	public enum PlaneEnum {
		PLANES("planes"),
		PLANE("plane"),
		MODEL("model"),
		MODELID("modelId"),
		ORIGIN("origin"),
		PRICE("price"),
		CHARS("chars"),
		TYPE("type"),
		PLACES("places"),
		GUNS("guns"),
		PRESENT("present"),
		RADAR("radar"),
		PARAMS("params"),
		LENGTH("count"),
		WIDTH("favouriteNumber"),
		HEIGHT("number");
		
		private String value;
		private PlaneEnum(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}
	
	public PlaneHandler() {
		planes = new ArrayList<>();
		parsedFileTitles = EnumSet.range(PlaneEnum.PLANES, PlaneEnum.HEIGHT);
	}
	
	/**
	 * 
	 * Returns all parsed Plane objects
	 * 
	 * @return List of Plane objects
	 * 
	 */
	public List<Plane> getPlanes() {
		return planes;
	}
	
	/**
	 * 
	 * Handler method to process opening tags of XML file 
	 *  
	 * @param localName Name of the current XML tag
	 * @param attributes Attributes of the current XML tag
	 * 
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		if ("plane".equals(localName)) {
			currentPlane = new Plane();			
		} else if ("model".equals(localName)) {
			currentPlane.setModelId(attributes.getValue(0));
		} else if ("guns".equals(localName)) {
			currentPlane.getChars().setPresent(attributes.getValue(0));
		}
		
		PlaneEnum tempEnum = PlaneEnum.valueOf(localName.toUpperCase());
		if (parsedFileTitles.contains(tempEnum))
			currentEnum = tempEnum;
	}
	
	/**
	 * 
	 * Handler method to process closing tags of XML file 
	 *  
	 * @param localName Name of the current XML tag
	 * 
	 */
	public void endElement(String uri, String localName, String qName) {
		if ("plane".equals(localName)) {
			planes.add(currentPlane);			
		}			
	}
	
	/**
	 * 
	 * Process text data of each XML tag
	 * 
	 * @param chars Chars array with raw text data 
	 * @param start Start index of current tag data 
	 * @param length Text length of current tag data
	 * 
	 */
	public void characters(char[] chars, int start, int length) {
		String currentString = new String(chars, start, length).trim();
		if (currentEnum != null) {
			switch(currentEnum) {
			case PLANES:
				break;
			case PLANE:
				break;
			case MODEL:				
				currentPlane.setModel(currentString);
				break;				
			case ORIGIN:
				currentPlane.setOrigin(currentString);
				break;
			case PRICE:
				currentPlane.setPrice(Integer.parseInt(currentString));
				break;
			case CHARS:
				break;
			case TYPE:
				currentPlane.getChars().setType(currentString);
				break;
			case PLACES:				
				currentPlane.getChars().setPlaces(currentString);
				break;
			case GUNS:
				int gunsNumber = 0;
				if(currentPlane.getChars().getPresent().equals("yes"))
					gunsNumber = Integer.parseInt(currentString);
				currentPlane.getChars().setGuns(gunsNumber);
				break;
			case RADAR:
				currentPlane.getChars().setRadar(currentString);
				break;
			case PARAMS:
				break;
			case LENGTH:
				currentPlane.getParams().setLength(Double.parseDouble(currentString));
				break;
			case WIDTH:
				currentPlane.getParams().setWidth(Double.parseDouble(currentString));
				break;
			case HEIGHT:
				currentPlane.getParams().setHeight(Double.parseDouble(currentString));
				break;
			default:
				throw new EnumConstantNotPresentException(currentEnum.getDeclaringClass(), currentEnum.name());
			}
		}
		currentEnum = null;
	}
	
}
