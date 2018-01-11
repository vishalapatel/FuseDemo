package com.vishal.widgetstore.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.vishal.widgetstore.model.Order;
import com.vishal.widgetstore.model.OrderItem;

public class ProcessOrderProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		
		Order order = (Order) exchange.getIn().getBody();
		exchange.getIn().setHeader("firstname", order.getCustomer().getFirstname());
		exchange.getIn().setHeader("lastname", order.getCustomer().getLastname());
		exchange.getIn().setHeader("street", order.getCustomer().getStreet());
		exchange.getIn().setHeader("order", order);
		
		Double orderTotal = 0.0;
		
		// total the order as well...
		for (OrderItem item : order.getOrderItems()) {
			Integer quantity = item.getQuantity();
			Double price = item.getPrice();

			orderTotal += Math.round(price*quantity);
		}
		
		order.setOrderTotal(orderTotal);		
		exchange.getIn().setBody(exchange.getIn().getBody());
	}

}
