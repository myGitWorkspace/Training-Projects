package com.andrew;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.andrew.PlaneHandler.PlaneEnum;

/**
 * 
 * Parse XML file using methods of STAX parser
 *  
 */
public class PlaneStaxBuilder extends AbstractPlanesBuilder {

	
	private List<Plane> planes = new ArrayList<>();
	private XMLInputFactory inputFactory;	
	
	public PlaneStaxBuilder() {
		inputFactory = XMLInputFactory.newInstance();
		super.planes = this.planes;
	}
	
	/**
	 * 
	 * Builds a list of Plane objects, parsing particular XML file
	 * 
	 * @param filename Name of the XML file to be parsed
	 *
	 */
	public void buildListPlanes(String filename) {
		FileInputStream inputStream = null;
		XMLStreamReader reader = null;
		String name;
		try {
			inputStream = new FileInputStream(new File(filename));
			reader = inputFactory.createXMLStreamReader(inputStream);
			while(reader.hasNext()) {
				int type = reader.next();
				if (type == XMLStreamConstants.START_ELEMENT) {
					name = reader.getLocalName();
					if (PlaneEnum.valueOf(name.toUpperCase()) == PlaneEnum.PLANE) {
						Plane plane = buildPlane(reader);
						planes.add(plane);
					}
				}
			}
		} catch(XMLStreamException e) {
			System.err.println("StAX parsing error! " + e.getMessage());
		} catch(FileNotFoundException e) {
			System.err.println("File " + filename+" not found! " + e);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch(IOException e) {
				System.err.println("Impossible close file " + filename + " : " + e);
			}
		}
		
	}
	
	/**
	 * 
	 * Builds Plane object
	 * 
	 * @param reader XMLStreamReader that reads each XML element in file 
	 * @return Plane object, filled with data from XML file
	 * @throws XMLStreamException
	 * 
	 */
	private Plane buildPlane(XMLStreamReader reader) throws XMLStreamException {
		Plane plane = new Plane();
		String name;
		while(reader.hasNext()) {
			int type = reader.next();
			switch(type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				switch(PlaneEnum.valueOf(name.toUpperCase())) {
				case MODEL:
					String modelId = reader.getAttributeValue(null, PlaneEnum.MODELID.getValue());
					plane.setModelId(modelId);
					plane.setModel(getXMLText(reader));
					break;				
				case ORIGIN:
					plane.setOrigin(getXMLText(reader));
					break;
				case PRICE:
					plane.setPrice(Integer.parseInt(getXMLText(reader)));
					break;					
				case CHARS:
					plane.setChars(getXMLChars(reader));
					break;					
				case PARAMS:
					plane.setParams(getXMLParams(reader));
					break;
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if(PlaneEnum.valueOf(name.toUpperCase()) == PlaneEnum.PLANE)
					return plane;
				break;
			}
		}
		throw new XMLStreamException("Unknown element in tag Plane");
	}
	
	/**
	 * 
	 * Parses Plane characteristics and saves it to the Plane.chars field of Plane object
	 * 
	 * @param reader XMLStreamReader that reads each XML element in file 
	 * @return Plane.Chars Plane characteristics object, filled with data from XML file
	 * @throws XMLStreamException
	 * 
	 */
	private Plane.Chars getXMLChars(XMLStreamReader reader) throws XMLStreamException {
		Plane.Chars chars = new Plane.Chars();
		int type;
		String name;
		while(reader.hasNext()) {
			type = reader.next();
			switch(type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				switch(PlaneEnum.valueOf(name.toUpperCase())) {
				case TYPE:
					chars.setType(getXMLText(reader));
					break;
				case PLACES:				
					chars.setPlaces(getXMLText(reader));
					break;
				case GUNS:
					String present = reader.getAttributeValue(null, PlaneEnum.PRESENT.getValue());
					int gunsNumber = 0;
					if(present.equals("yes"))
						gunsNumber = Integer.parseInt(getXMLText(reader));
					chars.setGuns(gunsNumber);
					chars.setPresent(present);
					break;
				case RADAR:
					chars.setRadar(getXMLText(reader));					
					break;
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if( PlaneEnum.valueOf(name.toUpperCase()) == PlaneEnum.CHARS) {
					return chars;
				}
				break;
			}
		}
		throw new XMLStreamException("Unknown element in tag chars");
	}
	
	/**
	 * 
	 * Parses Plane parameters and saves them to the Plane.params field of Plane object
	 * 
	 * @param reader XMLStreamReader that reads each XML element in file 
	 * @return Plane.Params Plane params object, filled with data from XML file
	 * @throws XMLStreamException
	 * 
	 */
	private Plane.Params getXMLParams(XMLStreamReader reader) throws XMLStreamException {
		Plane.Params params = new Plane.Params();
		int type;
		String name;
		while(reader.hasNext()) {
			type = reader.next();
			switch(type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				switch(PlaneEnum.valueOf(name.toUpperCase())) {
				case LENGTH:					
					params.setLength( Double.parseDouble(getXMLText(reader)) );
					break;
				case WIDTH:				
					params.setWidth( Double.parseDouble(getXMLText(reader)) );
					break;
				case HEIGHT:
					params.setHeight( Double.parseDouble(getXMLText(reader)) );				
					break;
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if( PlaneEnum.valueOf(name.toUpperCase()) == PlaneEnum.PARAMS) {
					return params;
				}
				break;
			}
		}
		throw new XMLStreamException("Unknown element in tag chars");
	}
	
	/**
	 * 
	 * Parses text from current XML element
	 * 
	 * @param reader XMLStreamReader that reads each XML element in file 
	 * @return String text from current XML element
	 * @throws XMLStreamException
	 * 
	 */
	private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
		String text = null;
		if(reader.hasNext()) {
			reader.next();
			text = reader.getText();
		}
		return text;
	}
	
}
