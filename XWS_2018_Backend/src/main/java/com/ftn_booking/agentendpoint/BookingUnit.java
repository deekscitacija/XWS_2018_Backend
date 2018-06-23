//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.23 at 03:17:15 AM CEST 
//


package com.ftn_booking.agentendpoint;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BookingUnit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BookingUnit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cityMainServerId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="peopleNo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="agentMainServerId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="accTypeMainServerId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="accCategoryMainServerId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="bonusFeaturesMainServerIds" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="base64ImagesList" type="{http://ftn-booking.com/agentEndpoint}hMapStringStringElement" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BookingUnit", propOrder = {
    "name",
    "cityMainServerId",
    "address",
    "description",
    "peopleNo",
    "agentMainServerId",
    "accTypeMainServerId",
    "accCategoryMainServerId",
    "bonusFeaturesMainServerIds",
    "base64ImagesList"
})
public class BookingUnit {

    @XmlElement(required = true)
    protected String name;
    protected long cityMainServerId;
    @XmlElement(required = true)
    protected String address;
    @XmlElement(required = true)
    protected String description;
    protected int peopleNo;
    protected long agentMainServerId;
    protected long accTypeMainServerId;
    protected long accCategoryMainServerId;
    @XmlElement(type = Long.class)
    protected List<Long> bonusFeaturesMainServerIds;
    protected List<HMapStringStringElement> base64ImagesList;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the cityMainServerId property.
     * 
     */
    public long getCityMainServerId() {
        return cityMainServerId;
    }

    /**
     * Sets the value of the cityMainServerId property.
     * 
     */
    public void setCityMainServerId(long value) {
        this.cityMainServerId = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the peopleNo property.
     * 
     */
    public int getPeopleNo() {
        return peopleNo;
    }

    /**
     * Sets the value of the peopleNo property.
     * 
     */
    public void setPeopleNo(int value) {
        this.peopleNo = value;
    }

    /**
     * Gets the value of the agentMainServerId property.
     * 
     */
    public long getAgentMainServerId() {
        return agentMainServerId;
    }

    /**
     * Sets the value of the agentMainServerId property.
     * 
     */
    public void setAgentMainServerId(long value) {
        this.agentMainServerId = value;
    }

    /**
     * Gets the value of the accTypeMainServerId property.
     * 
     */
    public long getAccTypeMainServerId() {
        return accTypeMainServerId;
    }

    /**
     * Sets the value of the accTypeMainServerId property.
     * 
     */
    public void setAccTypeMainServerId(long value) {
        this.accTypeMainServerId = value;
    }

    /**
     * Gets the value of the accCategoryMainServerId property.
     * 
     */
    public long getAccCategoryMainServerId() {
        return accCategoryMainServerId;
    }

    /**
     * Sets the value of the accCategoryMainServerId property.
     * 
     */
    public void setAccCategoryMainServerId(long value) {
        this.accCategoryMainServerId = value;
    }

    /**
     * Gets the value of the bonusFeaturesMainServerIds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bonusFeaturesMainServerIds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBonusFeaturesMainServerIds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getBonusFeaturesMainServerIds() {
        if (bonusFeaturesMainServerIds == null) {
            bonusFeaturesMainServerIds = new ArrayList<Long>();
        }
        return this.bonusFeaturesMainServerIds;
    }

    /**
     * Gets the value of the base64ImagesList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the base64ImagesList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBase64ImagesList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HMapStringStringElement }
     * 
     * 
     */
    public List<HMapStringStringElement> getBase64ImagesList() {
        if (base64ImagesList == null) {
            base64ImagesList = new ArrayList<HMapStringStringElement>();
        }
        return this.base64ImagesList;
    }

}
