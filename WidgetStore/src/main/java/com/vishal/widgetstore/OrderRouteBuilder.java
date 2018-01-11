package com.vishal.widgetstore;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;

import com.vishal.widgetstore.model.Order;

public class OrderRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		onException(Exception.class)
		    .handled(true)
		    .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
		    .setHeader(Exchange.CONTENT_TYPE, constant("text/plain"))
		    .setBody().constant("There is an issue with the request.");
		    
		restConfiguration()
		    .component("spark-rest").port(8080)
			.bindingMode(RestBindingMode.json)
		    .apiContextPath("api-doc")
		    .apiProperty("api.title", "Order REST Service API")
		    .apiProperty("api.version", "1")
		    .contextPath("/");
		
		rest("/order")
	    	.get("{id}")
	        	.param()
	            	.name("id").type(RestParamType.path).description("Order ID")
	            .endParam()
	            .description("Get an existing order by its ID")
	            .to("direct:getOrder")
	        .post()
	        	.type(Order.class)
	        	.consumes("application/json")
	        	.description("Place a new order via web")
	        	.responseMessage().code(200).message("The created customer id").endResponseMessage()
	        	.to("direct:webNewOrder");		
		
		from("direct:checkInventory")
			.routeId("_checkInventoryRoute")
			.choice()
				.when(simple("${body.type} == 'WIDGET'"))
					.process("checkInventoryProcessor")
					.to("sql:SELECT quantity FROM Widgets?dataSource=dataSource&outputType=SelectOne")
					.bean(CheckInventory.class, "inventoryCheck(${exchange})")
				.otherwise()
					.process("checkInventoryProcessor")
					.to("sql:SELECT quantity FROM Gadgets?dataSource=dataSource&outputType=SelectOne")
					.bean(CheckInventory.class, "inventoryCheck(${exchange})");
		
		from("direct:checkCustomerExists")
			.routeId("_checkCustomerExistsRoute")
			.to("sql:SELECT * FROM Customers WHERE firstname=:#firstname AND lastname=:#lastname AND street=:#street?dataSource=dataSource&outputType=SelectOne&outputClass=com.vishal.widgetstore.model.Customer")
			.bean(CheckCustomer.class, "customerCheck(${exchange})")
			.choice()
				.when(simple("${body.orderTotal} > 50000 || ${body.customer.valid} == 'false'"))
					.to("activemq:queue:accounting?disableReplyTo=false")
				.otherwise()
					.to("activemq:queue:fulfill_order?disableReplyTo=false");
		
		// case where customer exists...
		from("direct:fulfillOrder")
			.routeId("_completeOrder")
			.setHeader("order", simple("${body}"))
			
			// add an order to the orders table
			.to("sql:INSERT INTO Orders (customer_id) VALUES (:#${body.customer.id})?dataSource=dataSource&outputType=SelectOne")
			
			// save the generated order ID and put it in a header
			.to("sql:SELECT MAX(ID) from Orders?dataSource=dataSource&outputType=SelectOne")
			.setHeader("orderId", simple("${body}"))
			
			// iterate through each order item and add a record to the order items table
			.loop(simple("${headers.order.orderItems.size()}"))
				// get the order item at the current index of the loop. set headers for each param in sql query.
				.setHeader("orderItem", simple("${headers.order.orderItems[${exchangeProperty.CamelLoopIndex}]}"))
				.setHeader("type", simple("${headers.orderItem.type}"))
				.setHeader("price", simple("${headers.orderItem.price}"))
				.setHeader("quantity", simple("${headers.orderItem.quantity}"))
				.to("sql:INSERT INTO OrderItems (order_id, type, price, quantity) "
						+ "VALUES (:#orderId,:#type,:#price,:#quantity)?dataSource=dataSource")
			.end()
			.bean(OrderResponse.class, "setResponse(${exchange})")
			.log("Successfully placed order!");
		
		
	}
}
