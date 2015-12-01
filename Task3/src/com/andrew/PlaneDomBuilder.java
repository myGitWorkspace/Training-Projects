package com.andrew;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.ArrayList;

/**
 * 
 *  Plane object builder that uses DocumentBuilder to parse XML file
 *
 */
public class PlaneDomBuilder extends AbstractPlanesBuilder {

	private DocumentBuilder docBuilder;
	
	public PlaneDomBuilder() {
		this.planes = new ArrayList<Plane>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			docBuilder = factory.newDocumentBuilder();
		} catch(ParserConfigurationException e) {
			System.err.println("Error in parser configuration: "+e);
		}
	}
	
	/**
	 * 
	 * Builds a list of Plane objects, parsing particular XML file
	 * 
	 * @param filename Name of the XML file to be parsed
	 *
	 */
	public void buildListPlanes(String filename) {
		Document document = null;
		try {
			document = docBuilder.parse(filename);
			Element root = document.getDocumentElement();
			NodeList planesList = root.getElementsByTagName("pln:plane");
			
			for(int i=0; i<planesList.getLength(); i++) {
				Element planeElement = (Element)planesList.item(i);
				Plane plane = buildPlane(planeElement);
				planes.add(plane);
			}
		} catch(IOException e) {
			System.err.println("File error or I/O error: "+e);
		} catch(SAXException e) {
			System.err.println("Parsing failure: "+e);
		}
	}
	
	/**
	 * 
	 * Builds Plane object, starting from given node element of XML file
	 * 
	 * @param planeElement Node element of XML file 
	 * that holds all information to build a Plane object
	 * @return Plane object, filled with data from XML file
	 * 
	 */
	private Plane buildPlane(Element planeElement) {
		Plane plane = new Plane();		

		Element modelElement = (Element)planeElement.getElementsByTagName("pln:model").item(0);		
		plane.setModelId(modelElement.getAttribute("modelId"));
		plane.setModel(getElementTextContent(planeElement, "pln:model"));
		plane.setOrigin(getElementTextContent(planeElement, "pln:origin"));
		plane.setPrice(Integer.parseInt(getElementTextContent(planeElement, "pln:price")));
		Plane.Chars chars = plane.getChars();
		Element charsElement = (Element)planeElement.getElementsByTagName("pln:chars").item(0);
		chars.setType(getElementTextContent(charsElement, "pln:type"));
		chars.setPlaces( getElementTextContent(charsElement, "pln:places") );
		Element gunsElement = (Element)charsElement.getElementsByTagName("pln:guns").item(0);
		String present = gunsElement.getAttribute("present");
		chars.setPresent(present);
		int numberGuns = 0;
		if(present.equals("yes"))
			numberGuns = Integer.parseInt( getElementTextContent(charsElement, "pln:guns") );
		chars.setGuns(numberGuns);
		chars.setRadar(getElementTextContent(charsElement, "pln:radar"));
		plane.setChars(chars);
		Plane.Params params = plane.getParams();
		Element paramsElement = (Element)planeElement.getElementsByTagName("pln:params").item(0);
		params.setLength( Double.parseDouble(getElementTextContent(paramsElement, "pln:length")) );
		params.setWidth( Double.parseDouble(getElementTextContent(paramsElement, "pln:width")) );
		params.setHeight( Double.parseDouble(getElementTextContent(paramsElement, "pln:height")) );
		plane.setParams(params);		
		
		return plane;		
	}
	
	/**
	 * 
	 * Returns text element from the specified tag name
	 * 
	 * @param element Node element of XML file 
	 * @param elementName Name of the XML tag to be found 
	 * @return String Text data of the specified tag name
	 * 
	 */
	private static String getElementTextContent(Element element, String elementName) {
		NodeList nodeList = element.getElementsByTagName(elementName);
		Node node = nodeList.item(0);
		String text = node.getTextContent();
		return text;
	}
}
