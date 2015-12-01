package com.andrew;

/**
 * 
 *  Class for modeling Plane entity
 *
 */
public class Plane {

	private String model;
	private String modelId;
	private String origin;
	private int price;
	private Chars chars = new Chars();
	private Params params = new Params();
	
	public Plane() {
		
	}
	
	public Plane(String model, String modelId, String origin, int price, Chars chars, Params params) {
		this.model = model;
		this.modelId = modelId;
		this.price = price;
		this.chars = chars;
		this.params = params;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getModel() {
		return this.model;
	}
	
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	
	public String getModelId() {
		return this.modelId;
	}
	
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	public String getOrigin() {
		return this.origin;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public void setChars(Chars chars) {
		this.chars = chars;
	}
	
	public Chars getChars() {
		return this.chars;
	}
	
	public void setParams(Params params) {
		this.params = params;
	}
	
	public Params getParams() {
		return this.params;
	}

	@Override
	public String toString() {
		return "[ model=" +  model + ", modelId=" + modelId + ", origin=" + origin + ", price=" + price + ", chars="
				+ chars + ", params=" + params + "]";
	}
	
	/**
	 * 
	 *  Static nested class for holding Plane base characteristics
	 *
	 */
	static class Chars {
		private Type type;
		private Places places;
		private String present;
		private int guns;
		private Radar radar;
		
		enum Type {SUPPORT, TRACKING, FIGHTER, INTERCEPTOR, SCOUT};
		enum Places {ONE, TWO};
		enum Radar {YES, NO};
		
		public Chars() {
			
		}
		
		public Chars(Type type, Places places, String present, int guns, Radar radar) {
			this.type = type;
			this.places = places;
			this.present = present;
			this.guns = guns;
			this.radar = radar;			
		}
		
		public void setType(String type) {
			Type value = null;
		    try {
		    	value = Type.valueOf(type.toUpperCase());
		    } catch (IllegalArgumentException iae) {
		    	value = Type.SUPPORT;
		    }
		    this.type = value;
		}
		
		public Type getType() {
			return this.type;
		}
		
		public void setPlaces(String places) {
			if(places.equals("2"))
				this.places = Places.TWO;
			else
				this.places = Places.ONE;			
		}
		
		public Places getPlaces() {
			return this.places;
		}
		
		public void setPresent(String present) {
			this.present = present;
		}
		
		public String getPresent() {
			return this.present;
		}
		
		public void setGuns(int guns) {
			this.guns = guns;
		}
		
		public int getGuns() {
			return this.guns;
		}
		
		public void setRadar(String radar) {
			Radar value = null;
		    try {
		    	value = Radar.valueOf(radar.toUpperCase());
		    } catch (IllegalArgumentException iae) {
		    	value = Radar.NO;
		    }
		    this.radar = value;
		}
		
		public Radar getRadar() {
			return this.radar;
		}
		
		@Override
		public String toString() {
			return "[ type=" +  type + ", places=" + places + ", present=" + present + ", guns="
					+ guns + ", radar=" + radar + "]";
		}
	}
	
	/**
	 * 
	 *  Static nested class for holding Plane geometrical parameters
	 *
	 */
	static class Params {
		private double width;
		private double length;
		private double height;
		
		public Params() {
			
		}
		
		public Params(double width, double length, double height) {
			this.width = width;
			this.length = length;
			this.height = height;
		}
		
		public void setWidth(double width) {
			this.width = width;
		}
		
		public double getWidth() {
			return this.width;
		}
		
		public void setLength(double length) {
			this.length = length;
		}
		
		public double getLength() {
			return this.length;
		}
		
		public void setHeight(double height) {
			this.height = height;
		}
		
		public double getHeight() {
			return this.height;
		}
		
		@Override
		public String toString() {
			return "[ width=" +  width + ", length=" + length + ", height=" + height + "]";
		}
	}
}
