
package ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for proxyBid complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="proxyBid">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="auctionListing" type="{http://ws.ejb/}auctionListing" minOccurs="0"/>
 *         &lt;element name="customer" type="{http://ws.ejb/}customer" minOccurs="0"/>
 *         &lt;element name="maxAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="proxyBidId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "proxyBid", propOrder = {
    "auctionListing",
    "customer",
    "maxAmount",
    "proxyBidId"
})
public class ProxyBid {

    protected AuctionListing auctionListing;
    protected Customer customer;
    protected Double maxAmount;
    protected Long proxyBidId;

    /**
     * Gets the value of the auctionListing property.
     * 
     * @return
     *     possible object is
     *     {@link AuctionListing }
     *     
     */
    public AuctionListing getAuctionListing() {
        return auctionListing;
    }

    /**
     * Sets the value of the auctionListing property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuctionListing }
     *     
     */
    public void setAuctionListing(AuctionListing value) {
        this.auctionListing = value;
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
     * Gets the value of the maxAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMaxAmount() {
        return maxAmount;
    }

    /**
     * Sets the value of the maxAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMaxAmount(Double value) {
        this.maxAmount = value;
    }

    /**
     * Gets the value of the proxyBidId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProxyBidId() {
        return proxyBidId;
    }

    /**
     * Sets the value of the proxyBidId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProxyBidId(Long value) {
        this.proxyBidId = value;
    }

}
