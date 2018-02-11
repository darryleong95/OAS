
package ws.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for customer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="customer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amtCredit" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="auctionListingWon" type="{http://ws.ejb/}auctionListing" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="bidListing" type="{http://ws.ejb/}bid" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="creditTransactionList" type="{http://ws.ejb/}creditTransaction" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="customerId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificationNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isPremium" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobileNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="passWord" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proxyBids" type="{http://ws.ejb/}proxyBid" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customer", propOrder = {
    "amtCredit",
    "auctionListingWon",
    "bidListing",
    "creditTransactionList",
    "customerId",
    "email",
    "firstName",
    "identificationNumber",
    "isPremium",
    "lastName",
    "mobileNumber",
    "passWord",
    "proxyBids",
    "userName"
})
public class Customer {

    protected double amtCredit;
    @XmlElement(nillable = true)
    protected List<AuctionListing> auctionListingWon;
    @XmlElement(nillable = true)
    protected List<Bid> bidListing;
    @XmlElement(nillable = true)
    protected List<CreditTransaction> creditTransactionList;
    protected Long customerId;
    protected String email;
    protected String firstName;
    protected String identificationNumber;
    protected Boolean isPremium;
    protected String lastName;
    protected int mobileNumber;
    protected String passWord;
    @XmlElement(nillable = true)
    protected List<ProxyBid> proxyBids;
    protected String userName;

    /**
     * Gets the value of the amtCredit property.
     * 
     */
    public double getAmtCredit() {
        return amtCredit;
    }

    /**
     * Sets the value of the amtCredit property.
     * 
     */
    public void setAmtCredit(double value) {
        this.amtCredit = value;
    }

    /**
     * Gets the value of the auctionListingWon property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the auctionListingWon property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAuctionListingWon().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AuctionListing }
     * 
     * 
     */
    public List<AuctionListing> getAuctionListingWon() {
        if (auctionListingWon == null) {
            auctionListingWon = new ArrayList<AuctionListing>();
        }
        return this.auctionListingWon;
    }

    /**
     * Gets the value of the bidListing property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bidListing property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBidListing().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Bid }
     * 
     * 
     */
    public List<Bid> getBidListing() {
        if (bidListing == null) {
            bidListing = new ArrayList<Bid>();
        }
        return this.bidListing;
    }

    /**
     * Gets the value of the creditTransactionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the creditTransactionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCreditTransactionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CreditTransaction }
     * 
     * 
     */
    public List<CreditTransaction> getCreditTransactionList() {
        if (creditTransactionList == null) {
            creditTransactionList = new ArrayList<CreditTransaction>();
        }
        return this.creditTransactionList;
    }

    /**
     * Gets the value of the customerId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * Sets the value of the customerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCustomerId(Long value) {
        this.customerId = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the identificationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificationNumber() {
        return identificationNumber;
    }

    /**
     * Sets the value of the identificationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificationNumber(String value) {
        this.identificationNumber = value;
    }

    /**
     * Gets the value of the isPremium property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsPremium() {
        return isPremium;
    }

    /**
     * Sets the value of the isPremium property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsPremium(Boolean value) {
        this.isPremium = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the mobileNumber property.
     * 
     */
    public int getMobileNumber() {
        return mobileNumber;
    }

    /**
     * Sets the value of the mobileNumber property.
     * 
     */
    public void setMobileNumber(int value) {
        this.mobileNumber = value;
    }

    /**
     * Gets the value of the passWord property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * Sets the value of the passWord property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassWord(String value) {
        this.passWord = value;
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
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

}
