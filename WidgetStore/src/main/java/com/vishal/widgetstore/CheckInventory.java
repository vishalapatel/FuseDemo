package com.vishal.widgetstore;

import org.apache.camel.Exchange;

import com.vishal.widgetstore.model.OrderItem;

public class CheckInventory {

	public void inventoryCheck(Exchange exchange) {
		
		// Get the result of the SQL call.  If the quantity of widget/gadget is greater than 0
		// add order item back to original message (Aggregate it)
		Integer quantity = (Integer) exchange.getIn().getBody();
		OrderItem item = (OrderItem) exchange.getIn().getHeader("orderItem");
		
		if (quantity > 0)
			exchange.getIn().setBody(item);
		else
			exchange.getIn().setBody(null);
	}
}
