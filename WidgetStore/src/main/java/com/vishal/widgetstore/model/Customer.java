package com.vishal.widgetstore.model;

import java.io.Serializable;

public class Customer implements Serializable {

	private static final long serialVersionUID = 5883677349206436653L;
	
	protected Integer id;
	protected String firstname;
    protected String lastname;
    protected String street;
    protected boolean isValid;

    public Integer getId() {
        return id;
    }
	
    public void setId(Integer value) {
        this.id = value;
    }
    
	public String getFirstname() {
        return firstname;
    }
	
    public void setFirstname(String value) {
        this.firstname = value;
    }
    
	public String getLastname() {
        return lastname;
    }
	
    public void setLastname(String value) {
        this.lastname = value;
    }
	
    public String getStreet() {
        return street;
    }
	
    public void setStreet(String value) {
        this.street = value;
    }
    
    public boolean isValid() {
        return isValid;
    }
	
    public void setIsValid(boolean value) {
        this.isValid = value;
    }
}
