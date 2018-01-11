package com.vishal.widgetstore.model;

import java.io.Serializable;

public class OrderItem implements Serializable {

	private static final long serialVersionUID = -5414269009954183028L;
	
	protected Integer id;
	protected String type;
    protected Double price;
    protected Integer quantity;
    
    public Integer getId() {
    	return id;
    }
    
    public void setId(Integer value) {
    	this.id = value;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String value) {
        this.type = value;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(Double value) {
        this.price = value;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer value) {
        this.quantity = value;
    }
}
