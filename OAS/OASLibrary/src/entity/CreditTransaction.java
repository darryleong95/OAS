package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import util.Enum.TransactionTypeEnum;

@Entity
public class CreditTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long creditTransactionId;

    private TransactionTypeEnum transactionType;

    @Column(nullable = false, precision = 18, scale = 4)
    private Double amtCredit;

    private AuctionListing auctionListing;

    private CreditPackage creditPackage;

    @ManyToOne
    private Customer customer;

    public CreditTransaction() {
    }

    public CreditTransaction(TransactionTypeEnum transactionType, double amtCredit, Customer customer, AuctionListing auctionListing) {
        this.transactionType = transactionType;
        this.amtCredit = amtCredit;
        this.customer = customer;
        this.auctionListing = auctionListing;
    }

    public CreditTransaction(TransactionTypeEnum transactionType, double amtCredit, Customer customer, CreditPackage creditPackage) {
        this.transactionType = transactionType;
        this.amtCredit = amtCredit;
        this.customer = customer;
        this.creditPackage = creditPackage;
    }

    public CreditTransaction(TransactionTypeEnum transactionType, double amtCredit, Customer customer) {
        this.transactionType = transactionType;
        this.amtCredit = amtCredit;
        this.customer = customer;
    }

    public Long getCreditTransactionId() {
        return creditTransactionId;
    }

    public TransactionTypeEnum getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionTypeEnum transactionType) {
        this.transactionType = transactionType;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public Double getAmtCredit() {
        return amtCredit;
    }

    public void setAmtCredit(Double amtCredit) {
        this.amtCredit = amtCredit;
    }

    public AuctionListing getAuctionListing() {
        return this.auctionListing;
    }

    public void setAuctionListing(AuctionListing auctionListing) {
        this.auctionListing = auctionListing;
    }

    public CreditPackage getCreditPackage() {
        return creditPackage;
    }

    public void setCreditPackage(CreditPackage creditPackage) {
        this.creditPackage = creditPackage;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the creditTransactionId fields are not set
        if (!(object instanceof CreditTransaction)) {
            return false;
        }
        CreditTransaction other = (CreditTransaction) object;
        if ((this.creditTransactionId == null && other.creditTransactionId != null) || (this.creditTransactionId != null && !this.creditTransactionId.equals(other.creditTransactionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CreditTransaction[ creditTransactionId=" + creditTransactionId + " ]";
    }

}
