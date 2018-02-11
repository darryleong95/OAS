
package ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for address complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="address">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="customer" type="{http://ws.ejb/}customer" minOccurs="0"/>
 *         &lt;element name="disabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isDeliveryAddress" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="winningBid" type="{http://ws.ejb/}bid" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "address", propOrder = {
    "address",
    "addressId",
    "customer",
    "disabled",
    "isDeliveryAddress",
    "winningBid"
})
public class Address {

    protected String address;
    protected Long addressId;
    protected Customer customer;
    protected boolean disabled;
    protected boolean isDeliveryAddress;
    protected Bid winningBid;

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
     * Gets the value of the addressId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAddressId() {
        return addressId;
    }

    /**
     * Sets the value of the addressId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAddressId(Long value) {
        this.addressId = value;
    }

    /**
     * Gets the value of the customer property.
     * 
     * @return
     *     possible object is
     *     {@link Customer }
     *     
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the value of the customer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Customer }
     *     
     */
    public void setCustomer(Customer value) {
        this.customer = value;
    }

    /**
     * Gets the value of the disabled property.
     * 
     */
    public boolean isDisabled() {
        return disabled;
    }

    /**
     * Sets the value of the disabled property.
     * 
     */
    public void setDisabled(boolean value) {
        this.disabled = value;
    }

    /**
     * Gets the value of the isDeliveryAddress property.
     * 
     */
    public boolean isIsDeliveryAddress() {
        return isDeliveryAddress;
    }

    /**
     * Sets the value of the isDeliveryAddress property.
     * 
     */
    public void setIsDeliveryAddress(boolean value) {
        this.isDeliveryAddress = value;
    }

    /**
     * Gets the value of the winningBid property.
     * 
     * @return
     *     possible object is
     *     {@link Bid }
     *     
     */
    public Bid getWinningBid() {
        return winningBid;
    }

    /**
     * Sets the value of the winningBid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bid }
     *     
     */
    public void setWinningBid(Bid value) {
        this.winningBid = value;
    }

}
