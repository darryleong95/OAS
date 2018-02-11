package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

@Entity

public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private Integer mobileNumber;
    @Column(length = 32, nullable = false, unique = true)
    private String userName;
    @Column(length = 32, nullable = false)
    private String passWord;
    @Column(length = 9, nullable = false, unique = true)
    private String identificationNumber;
    @Column(nullable = false)
    private Double amtCredit;
    @Column(nullable = false)
    private Boolean isPremium;
    @Column(length = 32, nullable = false)
    private String firstName;
    @Column(length = 32, nullable = false)
    private String lastName;
    
    @JoinColumn
    @OneToMany
    private List<Address> addressList;

    @JoinColumn
    @OneToMany
    private List<CreditTransaction> creditTransactionList;

    @JoinColumn
    @OneToMany
    private List<Bid> bidListing;

    @JoinColumn
    @OneToMany
    private List<AuctionListing> auctionListingWon;

    @JoinColumn
    @OneToMany(mappedBy = "customer")
    private List<ProxyBid> proxyBids;

    public Customer() {
        amtCredit = 0.0;
        addressList = new ArrayList<>();
        creditTransactionList = new ArrayList<>();
        bidListing = new ArrayList<>();
        auctionListingWon = new ArrayList<>();
        isPremium = false;
        proxyBids = new ArrayList<>();
    }

    public Customer(String email, Integer mobileNumber, String userName, String passWord, Double amtCredit, String identificationNumber, String firstName, String lastName) {

        this.email = email;
        this.mobileNumber = mobileNumber;
        this.userName = userName;
        this.passWord = passWord;
        this.addressList = addressList;
        this.amtCredit = amtCredit;
        this.identificationNumber = identificationNumber;
        this.creditTransactionList = creditTransactionList;
        this.bidListing = bidListing;
        this.auctionListingWon = auctionListingWon;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Boolean getIsPremium() {
        return this.isPremium;
    }

    public void setIsPremium(Boolean isPremium) {
        this.isPremium = isPremium;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerId != null ? customerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.customerId == null && other.customerId != null) || (this.customerId != null && !this.customerId.equals(other.customerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Customer[ id=" + customerId + " ]";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    
    @XmlTransient
    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public double getAmtCredit() {
        return amtCredit;
    }

    public void setAmtCredit(double amtCredit) {
        this.amtCredit = amtCredit;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void addAddressList(Address address) {
        addressList.add(address);
    }
    
    @XmlTransient
    public List<CreditTransaction> getCreditTransactionList() {
        return creditTransactionList;
    }

    public void setCreditTransactionList(List<CreditTransaction> creditTransactionList) {
        this.creditTransactionList = creditTransactionList;
    }

    public void addCreditTransactionList(CreditTransaction creditTransaction) {
        creditTransactionList.add(creditTransaction);
    }

    public List<AuctionListing> getAuctionListingWon() {
        return auctionListingWon;
    }
    
    @XmlTransient
    public void setAuctionListingWon(List<AuctionListing> auctionListingWon) {
        this.auctionListingWon = auctionListingWon;
    }

    public void addAuctionListingWon(AuctionListing auctionListing) {
        auctionListingWon.add(auctionListing);
    }
    
    @XmlTransient
    public List<Bid> getBidListing() {
        return bidListing;
    }

    public void setBidListing(List<Bid> bidListing) {
        this.bidListing = bidListing;
    }

    public void addBidListing(Bid bid) {
        bidListing.add(bid);
    }
    
    @XmlTransient
    public List<ProxyBid> getProxyBids() {
        return proxyBids;
    }

    public void setProxyBids(List<ProxyBid> proxyBids) {
        this.proxyBids = proxyBids;
    }

    public void addProxyBid(ProxyBid proxyBid) {
        proxyBids.add(proxyBid);
    }
}
