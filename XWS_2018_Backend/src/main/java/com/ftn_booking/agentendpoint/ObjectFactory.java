//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.22 at 11:54:06 PM CEST 
//


package com.ftn_booking.agentendpoint;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ftn_booking.agentendpoint package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ftn_booking.agentendpoint
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ManageMonthlyPricesResponse }
     * 
     */
    public ManageMonthlyPricesResponse createManageMonthlyPricesResponse() {
        return new ManageMonthlyPricesResponse();
    }

    /**
     * Create an instance of {@link ResponseWrapper }
     * 
     */
    public ResponseWrapper createResponseWrapper() {
        return new ResponseWrapper();
    }

    /**
     * Create an instance of {@link ManageMonthlyPricesRequest }
     * 
     */
    public ManageMonthlyPricesRequest createManageMonthlyPricesRequest() {
        return new ManageMonthlyPricesRequest();
    }

    /**
     * Create an instance of {@link MonthlyPrices }
     * 
     */
    public MonthlyPrices createMonthlyPrices() {
        return new MonthlyPrices();
    }

}
