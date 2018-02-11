
package ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for creditTransaction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="creditTransaction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amtCredit" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="auctionListing" type="{http://ws.ejb/}auctionListing" minOccurs="0"/>
 *         &lt;element name="creditPackage" type="{http://ws.ejb/}creditPackage" minOccurs="0"/>
 *         &lt;element name="customer" type="{http://ws.ejb/}customer" minOccurs="0"/>
 *         &lt;element name="transactionType" type="{http://ws.ejb/}transactionTypeEnum" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "creditTransaction", propOrder = {
    "amtCredit",
    "auctionListing",
    "creditPackage",
    "customer",
    "transactionType"
})
public class CreditTransaction {

    protected Double amtCredit;
    protected AuctionListing auctionListing;
    protected CreditPackage creditPackage;
    protected Customer customer;
    protected TransactionTypeEnum transactionType;

    /**
     * Gets the value of the amtCredit property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAmtCredit() {
        return amtCredit;
    }

    /**
     * Sets the value of the amtCredit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAmtCredit(Double value) {
        this.amtCredit = value;
    }

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
     * Gets the value of the creditPackage property.
     * 
     * @return
     *     possible object is
     *     {@link CreditPackage }
     *     
     */
    public CreditPackage getCreditPackage() {
        return creditPackage;
    }

    /**
     * Sets the value of the creditPackage property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditPackage }
     *     
     */
    public void setCreditPackage(CreditPackage value) {
        this.creditPackage = value;
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
     * Gets the value of the transactionType property.
     * 
     * @return
     *     possible object is
     *     {@link TransactionTypeEnum }
     *     
     */
    public TransactionTypeEnum getTransactionType() {
        return transactionType;
    }

    /**
     * Sets the value of the transactionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionTypeEnum }
     *     
     */
    public void setTransactionType(TransactionTypeEnum value) {
        this.transactionType = value;
    }

}
