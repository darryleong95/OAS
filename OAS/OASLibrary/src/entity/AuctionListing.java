package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlTransient;

@Entity

public class AuctionListing implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long listingId;
    @Column(length = 32, nullable = false)
    private String itemName;
    @Column(nullable = true)
    private Double startBidAmt;
    @Column(nullable = true)
    private Date startDateTime;
    @Column(nullable = true)
    private Date endDateTime;
    @Column(nullable = true)
    private Boolean active;
    @Column(nullable = true)
    private Double reservePrice;
    @Column(nullable = true)
    private Double currentBidAmt;
    private Boolean requireIntervention = false;
    @ManyToOne
    private Address deliveryAddress;

    @JoinColumn(nullable = true)
    @OneToMany(mappedBy = "auctionListing")
    private List<Bid> bidList;

    //to designate winning customer (dunno if need or not
    @JoinColumn
    @ManyToOne
    private Customer customer;

    //to designate winning bid
    @OneToOne
    private Bid winningBid;

    @OneToOne(mappedBy = "auctionListing")
    private CreditTransaction creditTransaction;

    @JoinColumn
    @OneToMany(mappedBy="auctionListing")
    private List<ProxyBid> proxyBids;

    

    public AuctionListing() {
        proxyBids = new ArrayList<ProxyBid>();
    }

    public AuctionListing(String itemName, Double startBidAmt, Date startDateTime, Date endDateTime, Boolean active, Double reservePrice, Double currentBidAmt) {
        this();
        
        this.itemName = itemName;
        this.startBidAmt = startBidAmt;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.active = true;
        this.reservePrice = reservePrice;
        this.currentBidAmt = currentBidAmt;
        this.bidList = new ArrayList<Bid>();
        deliveryAddress = null;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getListingId() != null ? getListingId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuctionListing)) {
            return false;
        }
        AuctionListing other = (AuctionListing) object;
        if ((this.getListingId() == null && other.getListingId() != null) || (this.getListingId() != null && !this.listingId.equals(other.listingId))) {
            return false;
        }
        return true;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    @Override
    public String toString() {
        return "entity.AuctionListing[ id=" + getListingId() + " ]";
    }

    public Long getListingId() {
        return listingId;
    }

    public void setListingId(Long listingId) {
        this.listingId = listingId;
    }

    public Double getStartBidAmt() {
        return startBidAmt;
    }

    public void setStartBidAmt(Double startBidAmt) {
        this.startBidAmt = startBidAmt;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Double getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(Double reservePrice) {
        this.reservePrice = reservePrice;
    }

    public Double getCurrentBidAmt() {
        return currentBidAmt;
    }

    public void setCurrentBidAmt(Double currentBidAmt) {
        this.currentBidAmt = currentBidAmt;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    @XmlTransient
    public Bid getWinningBid() {
        return winningBid;
    }

    public void setWinningBid(Bid winningBid) {
        this.winningBid = winningBid;
    }

    public Boolean getRequireIntervention() {
        return this.requireIntervention;
    }

    public void setRequireIntervention(Boolean requireIntervenetion) {
        this.requireIntervention = requireIntervenetion;
    }

    public void decideAuctionItemFate(Scanner sc) {
        while (true) {
            String decidingInput = sc.nextLine();
            if (decidingInput.trim().equals('1')) {
                this.winningBid = null;
                break;
            } else if (decidingInput.trim().equals('2')) {
                break;
            } else {
                System.out.println("Sorry, you have entered an invalid input, please try again");
                System.out.println("Please try again: ");
            }
        }
    }
    
    @XmlTransient
    public List<Bid> getBidList() {
        return bidList;
    }

    public void setBidList(List<Bid> bidList) {
        this.bidList = bidList;
    }

    public void addBidList(Bid bid) {
        this.bidList.add(bid);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    @XmlTransient
    public List<ProxyBid> getProxyBids() {
        return proxyBids;
    }

    public void setProxyBids(List<ProxyBid> proxyBids) {
        this.proxyBids = proxyBids;
    }
    
    public void addProxyBid(ProxyBid proxyBid) {
        this.proxyBids.add(proxyBid);
    }
    
    @XmlTransient
    public CreditTransaction getCreditTransaction() {
        return creditTransaction;
    }

    public void setCreditTransaction(CreditTransaction creditTransaction) {
        this.creditTransaction = creditTransaction;
    }

}
