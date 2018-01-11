package com.vishal.widgetstore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import com.vishal.widgetstore.model.Order;
import com.vishal.widgetstore.model.OrderItem;

public class CsvToOrder {

	public static Order map(String csv) throws Exception, IOException {
		
		String line = new String();
		BufferedReader br = new BufferedReader(new StringReader(csv));
	
		Order order = new Order();
		
		try {
			
			// Read the first line
			if ((line = br.readLine()) != null) {
				String[] customer = line.split(",");
				
				order.getCustomer().setFirstname(customer[0]);
				order.getCustomer().setLastname(customer[1]);
				order.getCustomer().setStreet(customer[2]);

			}
			
            while ((line = br.readLine()) != null) {
            	
            	String[] sOrderItem = line.split(",");
            	
            	OrderItem orderItem = new OrderItem();
            	orderItem.setType( (sOrderItem[0].equalsIgnoreCase("W")) ? "WIDGET" : "GADGET" );            	
            	orderItem.setPrice(new Double(sOrderItem[1]));
            	orderItem.setQuantity(new Integer(sOrderItem[2]));
            	
            	order.getOrderItems().add(orderItem);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	br.close();
        }
		
		return order;
	}
}
