package com.vishal.widgetstore.aggregators;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import com.vishal.widgetstore.model.OrderItem;

public class OrderItemAggregator implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		
		if (oldExchange == null) {
			return newExchange;
		}
		
		// create a list of items to add to the new exchange.
		List<OrderItem> items = new ArrayList<OrderItem>();
		
		// if the new exchange is a single order item add it to the list.
		if (newExchange.getIn().getBody() instanceof OrderItem)
			items.add((OrderItem) newExchange.getIn().getBody());
		
		// if the old exchange is a single order item that means only one order has been aggregated
		if (oldExchange.getIn().getBody() instanceof OrderItem) {
			items.add((OrderItem) oldExchange.getIn().getBody());
		
		// if the old exchange has an arraylist of items that means more than one order item has been added to the new exchange
		// add the new item and all old ones.
		} else if (oldExchange.getIn().getBody() instanceof ArrayList) {
			items.addAll((ArrayList) oldExchange.getIn().getBody());
		}
		newExchange.getIn().setBody(items);

		return newExchange;
	}
}
