
package ws.client;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for transactionTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="transactionTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PURCHASE"/>
 *     &lt;enumeration value="BIDDING"/>
 *     &lt;enumeration value="OUTBIDDED_REFUND"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "transactionTypeEnum")
@XmlEnum
public enum TransactionTypeEnum {

    PURCHASE,
    BIDDING,
    OUTBIDDED_REFUND;

    public String value() {
        return name();
    }

    public static TransactionTypeEnum fromValue(String v) {
        return valueOf(v);
    }

}
