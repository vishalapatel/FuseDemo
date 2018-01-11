package com.vishal.widgetstore.aggregators;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import com.vishal.widgetstore.model.Order;
import com.vishal.widgetstore.model.OrderItem;

public class EnrichAggregator implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		
		if (oldExchange == null) {
			return newExchange;
		}
		
		Order originalOrder = null;
		List<OrderItem> items = new ArrayList<OrderItem>();

		if (oldExchange.getIn().getBody() instanceof Order) {
			originalOrder = (Order) oldExchange.getIn().getBody();

			if (newExchange.getIn().getBody() instanceof ArrayList) {
				items = (List<OrderItem>) newExchange.getIn().getBody();
				originalOrder.setOrderItems(items);
				newExchange.getIn().setBody(originalOrder);
			} else if (newExchange.getIn().getBody() instanceof OrderItem) {
				items.add((OrderItem) newExchange.getIn().getBody());
				originalOrder.setOrderItems(items);
				newExchange.getIn().setBody(originalOrder);
			}
		}

		return newExchange;
	}
}
