//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.23 at 04:15:15 PM CEST 
//


package com.ftn_booking.agentendpoint;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="responseWrapper" type="{http://ftn-booking.com/agentEndpoint}ResponseWrapper"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "responseWrapper"
})
@XmlRootElement(name = "sendMessageResponse")
public class SendMessageResponse {

    @XmlElement(required = true)
    protected ResponseWrapper responseWrapper;

    /**
     * Gets the value of the responseWrapper property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseWrapper }
     *     
     */
    public ResponseWrapper getResponseWrapper() {
        return responseWrapper;
    }

    /**
     * Sets the value of the responseWrapper property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseWrapper }
     *     
     */
    public void setResponseWrapper(ResponseWrapper value) {
        this.responseWrapper = value;
    }

}