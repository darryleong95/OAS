package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity


public class ProxyBid implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long proxyBidId;
    
    @JoinColumn
    @ManyToOne
    private Customer customer;
    @JoinColumn
    @ManyToOne
    private AuctionListing auctionListing;
    @Column(nullable = false)
    private Double maxAmount;

    public ProxyBid() {
    }

    public ProxyBid(Customer customer, AuctionListing auctionListing, Double maxAmount) {
        this.customer = customer;
        this.auctionListing = auctionListing;
        this.maxAmount = maxAmount;
    }


    public Long getProxyBidId() {
        return proxyBidId;
    }

    public void setProxyBidId(Long proxyBidId) {
        this.setProxyBidId(proxyBidId);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getProxyBidId() != null ? getProxyBidId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProxyBid)) {
            return false;
        }
        ProxyBid other = (ProxyBid) object;
        if ((this.getProxyBidId() == null && other.getProxyBidId() != null) || (this.getProxyBidId() != null && !this.proxyBidId.equals(other.proxyBidId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ProxyBid[ proxyBidid=" + getProxyBidId() + " ]";
    }
    
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

    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }
    
}
