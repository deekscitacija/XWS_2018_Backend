//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.24 at 04:41:59 AM CEST 
//


package com.ftn_booking.agentendpoint;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Reservation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Reservation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bookingUnitMainServerId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="reserveeFirstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="reserveeLastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dateFrom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dateTo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Reservation", propOrder = {
    "bookingUnitMainServerId",
    "reserveeFirstName",
    "reserveeLastName",
    "dateFrom",
    "dateTo"
})
public class Reservation {

    protected long bookingUnitMainServerId;
    @XmlElement(required = true)
    protected String reserveeFirstName;
    @XmlElement(required = true)
    protected String reserveeLastName;
    @XmlElement(required = true)
    protected String dateFrom;
    @XmlElement(required = true)
    protected String dateTo;

    /**
     * Gets the value of the bookingUnitMainServerId property.
     * 
     */
    public long getBookingUnitMainServerId() {
        return bookingUnitMainServerId;
    }

    /**
     * Sets the value of the bookingUnitMainServerId property.
     * 
     */
    public void setBookingUnitMainServerId(long value) {
        this.bookingUnitMainServerId = value;
    }

    /**
     * Gets the value of the reserveeFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserveeFirstName() {
        return reserveeFirstName;
    }

    /**
     * Sets the value of the reserveeFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserveeFirstName(String value) {
        this.reserveeFirstName = value;
    }

    /**
     * Gets the value of the reserveeLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserveeLastName() {
        return reserveeLastName;
    }

    /**
     * Sets the value of the reserveeLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserveeLastName(String value) {
        this.reserveeLastName = value;
    }

    /**
     * Gets the value of the dateFrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateFrom() {
        return dateFrom;
    }

    /**
     * Sets the value of the dateFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateFrom(String value) {
        this.dateFrom = value;
    }

    /**
     * Gets the value of the dateTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateTo() {
        return dateTo;
    }

    /**
     * Sets the value of the dateTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateTo(String value) {
        this.dateTo = value;
    }

}
