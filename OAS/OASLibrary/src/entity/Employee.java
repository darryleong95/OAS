package entity;

import util.Enum.AccountTypeEnum;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeid;
    @Column(length = 32, nullable = true, unique = true)
    private String userName;
    @Column(length = 32, nullable = true)
    private String passWord;
    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private AccountTypeEnum accountTypeEnum;
    @Column(length = 32, nullable = true)
    private String firstName;
    @Column(length = 32, nullable = true)
    private String lastName;
    @Column(length = 32, nullable = true, unique = true)
    private String identificationNumber;

    public Employee() {
    }

    public Employee(String userName, String passWord, String firstName, String lastName, String identificationNumber, AccountTypeEnum accountTypeEnum) {
        this.userName = userName;
        this.passWord = passWord;
        this.accountTypeEnum = accountTypeEnum;
        this.firstName = firstName;
        this.lastName = lastName;
        this.identificationNumber = identificationNumber;
        this.accountTypeEnum = accountTypeEnum;
    }

    public Long getEmployeeId() {
        return employeeid;
    }

    public void setEmployeeId(Long employeeid) {
        this.employeeid = employeeid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employeeid != null ? employeeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.employeeid == null && other.employeeid != null) || (this.employeeid != null && !this.employeeid.equals(other.employeeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Employee[ id=" + employeeid + " ]";
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

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public AccountTypeEnum getAccountTypeEnum() {
        return accountTypeEnum;
    }

    public void setAccountTypeEnum(AccountTypeEnum accountTypeEnum) {
        this.accountTypeEnum = accountTypeEnum;
    }
}
