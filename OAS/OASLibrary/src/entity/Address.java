package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private String address;
    private boolean disabled;
    private boolean isDeliveryAddress;
    @OneToMany
    private List<AuctionListing> auctionListingList; 
    
    //if a bid wins anything, this address can be assigned to ONE winning bid
    @OneToOne 
    private Bid winningBid;
    
    @ManyToOne
    private Customer customer;


    public Address() {
    }

    public Address(String address, boolean disabled, Customer customer) {
        this.address = address;
        this.disabled = disabled;
        this.customer = customer;
        this.isDeliveryAddress = false;
        auctionListingList = new ArrayList<AuctionListing>();
    }
    
    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (addressId != null ? addressId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.addressId == null && other.addressId != null) || (this.addressId != null && !this.addressId.equals(other.addressId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Address[ id=" + addressId + " ]";
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
    
    public void setCustomer (Customer customer) {
        this.customer = customer;
    }
    
    public Customer getCustomer () {
        return customer;
    }

    public Bid getWinningBid() {
        return winningBid;
    }

    public void setWinningBid(Bid winningBid) {
        this.winningBid = winningBid;
    }

    public boolean isIsDeliveryAddress() {
        return isDeliveryAddress;
    }

    public void setIsDeliveryAddress(boolean isDeliveryAddress) {
        this.isDeliveryAddress = isDeliveryAddress;
    }

}
