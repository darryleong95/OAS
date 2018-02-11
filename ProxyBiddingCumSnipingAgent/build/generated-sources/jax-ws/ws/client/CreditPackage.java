
package ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for creditPackage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="creditPackage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amtCredit" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="creditPackageId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "creditPackage", propOrder = {
    "amtCredit",
    "creditPackageId"
})
public class CreditPackage {

    protected double amtCredit;
    protected Long creditPackageId;

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
     * Gets the value of the creditPackageId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCreditPackageId() {
        return creditPackageId;
    }

    /**
     * Sets the value of the creditPackageId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCreditPackageId(Long value) {
        this.creditPackageId = value;
    }

}
