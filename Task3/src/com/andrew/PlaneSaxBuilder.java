package com.andrew;

import java.io.IOException;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * 
 * Parse XML file using methods of SAX parser
 *  
 */
public class PlaneSaxBuilder extends AbstractPlanesBuilder {
	
	private PlaneHandler planeHandler;
	private XMLReader reader;
	
	public PlaneSaxBuilder() {
		planeHandler = new PlaneHandler();
		try {
			reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(planeHandler);
		} catch(SAXException e) {
			System.err.print("error in SAX parser: " + e);
		}
	}
	
	/**
	 * 
	 * Builds a list of Plane objects from XML file
	 * 
	 * @param filename Name of XML file to parsed
	 *  
	 */
	public void buildListPlanes(String filename) {
		try {
			reader.parse(filename);
		} catch(SAXException e) {
			System.err.print("error in SAX parser: " + e);
		} catch(IOException e) {
			System.err.print("error in I/O streaming: " + e);
		}
		planes = planeHandler.getPlanes();
	}
}
