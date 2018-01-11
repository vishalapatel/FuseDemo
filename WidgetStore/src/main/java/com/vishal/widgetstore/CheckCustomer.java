package com.vishal.widgetstore;

import org.apache.camel.Exchange;

import com.vishal.widgetstore.model.Customer;
import com.vishal.widgetstore.model.Order;

public class CheckCustomer {

	public void customerCheck(Exchange exchange) {
		
		// Check to see if the customer exists in the Customers Table, if so then set is valid to true...
		Customer customer = (Customer) exchange.getIn().getBody();	
		Order item = (Order) exchange.getIn().getHeader("order");

		if (customer != null && customer.getFirstname() != null && customer.getLastname() != null && customer.getStreet() != null) {
			item.getCustomer().setIsValid(true);
			item.getCustomer().setId(customer.getId());
			exchange.getIn().setBody(item);
		} else {
			item.getCustomer().setIsValid(false);
			exchange.getIn().setBody(item);
		}
	}
}
