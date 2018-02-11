package entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Bid implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bidId;
    
    @Column (nullable = false, precision = 18, scale = 4)
    private Double bidAmt;
    
    @ManyToOne
    private Customer customer;
    
    @ManyToOne
    private Address addressListing;

    @ManyToOne
    private AuctionListing auctionListing;
    

    public Bid() {
    }

    public Bid(double bidAmt, Customer customer, AuctionListing auctionListing) {
        this.bidAmt = bidAmt;
        this.customer = customer;
        //this.timeOfBid = timeOfBid;
        //this.dateOfBid = dateOfBid;
        this.auctionListing = auctionListing;
    }

    /*public Date getDateOfBid() {
        return dateOfBid;
    }

    public void setDateOfBid(Date date) {
        this.dateOfBid = date;
    }*/

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public AuctionListing getAuctionListing() {
        return auctionListing;
    }

    public void setAuctionListing(AuctionListing auctionListing) {
        this.auctionListing = auctionListing;
    }

    public Long getBidId() {
        return bidId;
    }

    public void setBidId(Long id) {
        this.bidId = id;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bid)) {
            return false;
        }
        Bid other = (Bid) object;
        if ((this.bidId == null && other.bidId != null) || (this.bidId != null && !this.bidId.equals(other.bidId))) {
            return false;
        }
        return true;
    }

    public double getBidAmt() {
        return bidAmt;
    }

    public void setBidAmt(double bidAmt) {
        this.bidAmt = bidAmt;
    }

    /*public Time getTimeOfBid() {
        return timeOfBid;
    }

    public void setTimeOfBid(Time time) {
        this.timeOfBid = time;
    }*/

}
