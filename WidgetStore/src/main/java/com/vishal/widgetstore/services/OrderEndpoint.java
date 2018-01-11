package com.vishal.widgetstore.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.0
 * 2017-10-12T22:48:20.391-04:00
 * Generated source version: 3.2.0
 * 
 */
@WebService(targetNamespace = "com.vishal.partdepot", name = "OrderEndpoint")
@XmlSeeAlso({ObjectFactory.class})
public interface OrderEndpoint {

    @WebMethod(operationName = "PlaceOrder", action = "com.vishal.partdepot/PlaceOrder")
    @RequestWrapper(localName = "PlaceOrder", targetNamespace = "com.vishal.partdepot", className = "com.vishal.widgetstore.services.PlaceOrder")
    @ResponseWrapper(localName = "PlaceOrderResponse", targetNamespace = "com.vishal.partdepot", className = "com.vishal.widgetstore.services.PlaceOrderResponse")
    @WebResult(name = "out", targetNamespace = "")
    public java.lang.String placeOrder(
        @WebParam(name = "order", targetNamespace = "")
        com.vishal.widgetstore.services.Order order
    );
}
