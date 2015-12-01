package com.andrew;

/**
 * 
 * Factory of DOM, SAX and STAX builders
 *  
 */
public class PlanesBuilderFactory {

	private enum ParserType {DOM, SAX, STAX};
	
	/**
	 * 
	 * Returns particular Plane builder defined by parserType parameter 
	 * 
	 * @param parserType String name of parser type
	 * @return Plane builder object  
	 * 
	 */
	public AbstractPlanesBuilder getPlanesBuilder(String parserType) {
		
		ParserType parser = ParserType.valueOf(parserType.toUpperCase());
		
		switch(parser) {
		case DOM:
			return new PlaneDomBuilder();			
		case SAX:
			return new PlaneSaxBuilder();
		case STAX:
			return new PlaneStaxBuilder();
			default:
				throw new EnumConstantNotPresentException(parser.getDeclaringClass(), parser.name());
		}
	}
}
