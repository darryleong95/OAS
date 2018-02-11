
package ws.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for auctionListing complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="auctionListing">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="active" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="creditTransaction" type="{http://ws.ejb/}creditTransaction" minOccurs="0"/>
 *         &lt;element name="currentBidAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="customer" type="{http://ws.ejb/}customer" minOccurs="0"/>
 *         &lt;element name="deliveryAddress" type="{http://ws.ejb/}address" minOccurs="0"/>
 *         &lt;element name="endDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="itemName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="listingId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="proxyBids" type="{http://ws.ejb/}proxyBid" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="requireIntervention" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="reservePrice" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="startBidAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="startDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
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
@XmlType(name = "auctionListing", propOrder = {
    "active",
    "creditTransaction",
    "currentBidAmt",
    "customer",
    "deliveryAddress",
    "endDateTime",
    "itemName",
    "listingId",
    "proxyBids",
    "requireIntervention",
    "reservePrice",
    "startBidAmt",
    "startDateTime",
    "winningBid"
})
public class AuctionListing {

    protected Boolean active;
    protected CreditTransaction creditTransaction;
    protected Double currentBidAmt;
    protected Customer customer;
    protected Address deliveryAddress;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDateTime;
    protected String itemName;
    protected Long listingId;
    @XmlElement(nillable = true)
    protected List<ProxyBid> proxyBids;
    protected Boolean requireIntervention;
    protected Double reservePrice;
    protected Double startBidAmt;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDateTime;
    protected Bid winningBid;

    /**
     * Gets the value of the active property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isActive() {
        return active;
    }

    /**
     * Sets the value of the active property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setActive(Boolean value) {
        this.active = value;
    }

    /**
     * Gets the value of the creditTransaction property.
     * 
     * @return
     *     possible object is
     *     {@link CreditTransaction }
     *     
     */
    public CreditTransaction getCreditTransaction() {
        return creditTransaction;
    }

    /**
     * Sets the value of the creditTransaction property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditTransaction }
     *     
     */
    public void setCreditTransaction(CreditTransaction value) {
        this.creditTransaction = value;
    }

    /**
     * Gets the value of the currentBidAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCurrentBidAmt() {
        return currentBidAmt;
    }

    /**
     * Sets the value of the currentBidAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCurrentBidAmt(Double value) {
        this.currentBidAmt = value;
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
     * Gets the value of the deliveryAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    /**
     * Sets the value of the deliveryAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setDeliveryAddress(Address value) {
        this.deliveryAddress = value;
    }

    /**
     * Gets the value of the endDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDateTime() {
        return endDateTime;
    }

    /**
     * Sets the value of the endDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDateTime(XMLGregorianCalendar value) {
        this.endDateTime = value;
    }

    /**
     * Gets the value of the itemName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Sets the value of the itemName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemName(String value) {
        this.itemName = value;
    }

    /**
     * Gets the value of the listingId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getListingId() {
        return listingId;
    }

    /**
     * Sets the value of the listingId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setListingId(Long value) {
        this.listingId = value;
    }

    /**
     * Gets the value of the proxyBids property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the proxyBids property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProxyBids().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProxyBid }
     * 
     * 
     */
    public List<ProxyBid> getProxyBids() {
        if (proxyBids == null) {
            proxyBids = new ArrayList<ProxyBid>();
        }
        return this.proxyBids;
    }

    /**
     * Gets the value of the requireIntervention property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRequireIntervention() {
        return requireIntervention;
    }

    /**
     * Sets the value of the requireIntervention property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRequireIntervention(Boolean value) {
        this.requireIntervention = value;
    }

    /**
     * Gets the value of the reservePrice property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getReservePrice() {
        return reservePrice;
    }

    /**
     * Sets the value of the reservePrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setReservePrice(Double value) {
        this.reservePrice = value;
    }

    /**
     * Gets the value of the startBidAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getStartBidAmt() {
        return startBidAmt;
    }

    /**
     * Sets the value of the startBidAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setStartBidAmt(Double value) {
        this.startBidAmt = value;
    }

    /**
     * Gets the value of the startDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDateTime() {
        return startDateTime;
    }

    /**
     * Sets the value of the startDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDateTime(XMLGregorianCalendar value) {
        this.startDateTime = value;
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
