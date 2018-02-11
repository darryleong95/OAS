package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class CreditPackage implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long creditPackageId;
    
    @Column(nullable = false, precision = 18, scale = 4, unique = true)
    private Double amtCredit; 
    
    @Column()
    private Boolean inUse;
    
    public CreditPackage(){
        inUse = false; 
    }
    
    public CreditPackage(double amtCredit){
        this();
        this.amtCredit = amtCredit; 
    }
    
    
    
    public Long getCreditPackageId() {
        return creditPackageId;
    }

    public void setCreditPackageId(Long id) {
        this.creditPackageId = id;
    }

    public double getAmtCredit(){
        return amtCredit;
    }
    
    public void setAmtCredit (double amtCredit){
        this.amtCredit = amtCredit;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditPackage)) {
            return false;
        }
        CreditPackage other = (CreditPackage) object;
        if ((this.creditPackageId == null && other.creditPackageId != null) || (this.creditPackageId != null && !this.creditPackageId.equals(other.creditPackageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CreditPackage[ id=" + creditPackageId + " ]";
    }

    /**
     * @return the inUse
     */
    public Boolean getInUse() {
        return inUse;
    }

    /**
     * @param inUse the inUse to set
     */
    public void setInUse(Boolean inUse) {
        this.inUse = inUse;
    }
}
