package com.vishal.widgetstore.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {

	private static final long serialVersionUID = -313699101519249238L;
	
	protected Integer id;
	protected Customer customer;
	protected List<OrderItem> orderItems;
	protected Double orderTotal;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer value) {
    	this.id = value;
    }
	
	public Customer getCustomer() {
		if (this.customer == null)
			this.customer = new Customer();
        return customer;
    }

    public void setCustomer(Customer value) {
        this.customer = value;
    }
    
    public List<OrderItem> getOrderItems() {
    	if (this.orderItems == null)
    		this.orderItems = new ArrayList<OrderItem>();
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> value) {
        this.orderItems = value;
    }
    
    public Double getOrderTotal() {
		return orderTotal;
	}
	
	public void setOrderTotal(Double value) {
    	this.orderTotal = value;
    }
}
