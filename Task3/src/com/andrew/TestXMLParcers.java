package com.andrew;

/**
 * 
 * Class for testing XML parsers
 *
 */
public class TestXMLParcers {

	public static void main(String[] args) {
		
		String xmlFilename = "xml\\planes.xml";		
		String schemaFilename = "xml\\planes.xsd";
		String xslFilename = "xml\\planes.xsl";
		String newHTMLfilename = "xml\\planes.html";
		
		ValidatorSAXXSD validator = new ValidatorSAXXSD(xmlFilename, schemaFilename);
		System.out.println( "Validation of xml file " + xmlFilename + " is " + validator.validate() );
		
		PlanesBuilderFactory planeFactory = new PlanesBuilderFactory();
		AbstractPlanesBuilder builder = planeFactory.getPlanesBuilder("dom");
		builder.buildListPlanes(xmlFilename);
		System.out.println(builder.getSortedPlanes());
		
		planeFactory = new PlanesBuilderFactory();
		builder = planeFactory.getPlanesBuilder("sax");
		builder.buildListPlanes(xmlFilename);
		System.out.println(builder.getSortedPlanes());
		
		planeFactory = new PlanesBuilderFactory();
		builder = planeFactory.getPlanesBuilder("stax");
		builder.buildListPlanes(xmlFilename);				
		System.out.println(builder.getSortedPlanes());
		
		XSLTransformation transformer = new XSLTransformation(xslFilename, xmlFilename, newHTMLfilename);
		System.out.println(transformer.createHTMLfromXML());
	}
}
