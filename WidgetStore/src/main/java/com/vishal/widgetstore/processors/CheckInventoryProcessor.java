package com.vishal.widgetstore.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class CheckInventoryProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		exchange.getIn().setHeader("orderItem", exchange.getIn().getBody());
	}

}
