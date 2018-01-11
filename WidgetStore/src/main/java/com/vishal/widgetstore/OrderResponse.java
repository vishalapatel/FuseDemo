package com.vishal.widgetstore;

import org.apache.camel.Exchange;

public class OrderResponse {

	public void setResponse(Exchange exchange) {
		
		// Check to see if the customer exists in the Customers Table, if so then set is valid to true...
		Integer orderId = (Integer) exchange.getIn().getBody();		
		exchange.getIn().setBody("Successfully placed order " + orderId);
	}
}
