package com.lavalloisir.beans;

public class Category {

	private final long id;
    private String label;
    private String description;
    
    public Category(long id) {
    	super();
    	this.id = id;
    }    
    
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public long getId() {
		return id;
	}   
    
}
