
package ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bid complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bid">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="auctionListing" type="{http://ws.ejb/}auctionListing" minOccurs="0"/>
 *         &lt;element name="bidAmt" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="bidId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="customer" type="{http://ws.ejb/}customer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bid", propOrder = {
    "auctionListing",
    "bidAmt",
    "bidId",
    "customer"
})
public class Bid {

    protected AuctionListing auctionListing;
    protected double bidAmt;
    protected Long bidId;
    protected Customer customer;

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
     * Gets the value of the bidAmt property.
     * 
     */
    public double getBidAmt() {
        return bidAmt;
    }

    /**
     * Sets the value of the bidAmt property.
     * 
     */
    public void setBidAmt(double value) {
        this.bidAmt = value;
    }

    /**
     * Gets the value of the bidId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBidId() {
        return bidId;
    }

    /**
     * Sets the value of the bidId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBidId(Long value) {
        this.bidId = value;
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

}
