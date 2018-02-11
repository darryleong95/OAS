package ejb.session.stateless;

import entity.Customer;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.Exception.CustomerNotFoundException;
import util.Exception.InvalidLoginCredentialException;

@Stateless
@Local(CustomerControllerLocal.class)
@Remote(CustomerControllerRemote.class)
public class CustomerController implements CustomerControllerRemote, CustomerControllerLocal {

    @PersistenceContext(unitName = "OAS-ejbPU")
    private EntityManager em;

    @Override
    public Customer persistCustomer(Customer customer) {
        em.persist(customer);
        em.flush();
        return customer;
    }

    @Override
    public void updateCustomer(Customer customer) {
        em.merge(customer);
    }

    @Override
    public Customer retrieveCustomerByCustomerId(Long customerId) throws CustomerNotFoundException {
        Customer customer = em.find(Customer.class, customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found! \n");
        }
        customer.getAddressList().size();
        customer.getAuctionListingWon().size();
        customer.getBidListing().size();
        customer.getCreditTransactionList().size();
        customer.getProxyBids().size();
        return customer;
    }

    @Override
    public List<Customer> retrieveAllCustomers() {
        Query query = em.createQuery("SELECT * FROM Customer c");
        List<Customer> customers = query.getResultList();
        for (Customer customer : customers) {
            customer.getAddressList().size();
            customer.getAuctionListingWon().size();
            customer.getBidListing().size();
            customer.getCreditTransactionList().size();
            customer.getProxyBids().size();
        }
        return customers;
    }

    @Override
    public Customer retrieveCustomerByUserName(String userName) throws CustomerNotFoundException {
        Query query = em.createQuery("SELECT c FROM Customer c WHERE c.userName = :userName");
        query.setParameter("userName", userName);
        try {
            Customer customer = (Customer) query.getSingleResult();
            customer.getAddressList().size();
            customer.getAuctionListingWon().size();
            customer.getBidListing().size();
            customer.getCreditTransactionList().size();
            customer.getProxyBids().size();
            return customer;
        } catch (NoResultException | NonUniqueResultException ex) {
            throw new CustomerNotFoundException("Customer Username " + userName + " does not exist!");
        }
    }

    @Override
    public Customer retrieveCustomerByEmail(String email) throws CustomerNotFoundException {
        Query query = em.createQuery("SELECT c FROM Customer c WHERE c.email = :email");
        query.setParameter("email", email);
        try {
            Customer customer = (Customer) query.getSingleResult();
            customer.getAddressList().size();
            customer.getAuctionListingWon().size();
            customer.getBidListing().size();
            customer.getCreditTransactionList().size();
            customer.getProxyBids().size();
            return customer;
        } catch (NoResultException | NonUniqueResultException ex) {
            throw new CustomerNotFoundException("Customer with email " + email + " does not exist!");
        }
    }
    
    @Override
    public Customer retrieveCustomerByMobileNumber(int mobileNumber) throws CustomerNotFoundException {
        Query query = em.createQuery("SELECT c FROM Customer c WHERE c.mobileNumber = :mobileNumber");
        query.setParameter("mobileNumber", mobileNumber);
        try {
            Customer customer = (Customer) query.getSingleResult();
            customer.getAddressList().size();
            customer.getAuctionListingWon().size();
            customer.getBidListing().size();
            customer.getCreditTransactionList().size();
            customer.getProxyBids().size();
            return customer;
        } catch (NoResultException | NonUniqueResultException ex) {
            throw new CustomerNotFoundException("Customer with mobile number " + mobileNumber + " does not exist!");
        }

    }

    @Override
    public Customer retrieveCustomerByIdentificationNumber(String identificationNumber) throws CustomerNotFoundException {
        Query query = em.createQuery("SELECT c FROM Customer c WHERE c.identificationNumber = :identificationNumber");
        query.setParameter("identificationNumber", identificationNumber);
        try {
            Customer customer = (Customer) query.getSingleResult();
            customer.getAddressList().size();
            customer.getAuctionListingWon().size();
            customer.getBidListing().size();
            customer.getCreditTransactionList().size();
            customer.getProxyBids().size();
            return customer;
        } catch (NoResultException | NonUniqueResultException ex) {
            throw new CustomerNotFoundException("Customer with identification Number " + identificationNumber + " does not exist!");
        }
    }

    @Override
    public Customer customerLogin(String username, String password) throws InvalidLoginCredentialException {
        try {
            Customer customer = retrieveCustomerByUserName(username);
            if (customer.getPassWord().equals(password)) {
                return customer;
            } else {
                throw new InvalidLoginCredentialException("Username does not exist or invalid password!");
            }
        } catch (CustomerNotFoundException ex) {
            throw new InvalidLoginCredentialException("Username does not exist or invalid password!");
        }
    }

    @Override
    public void deleteCustomer(Long customerId) throws CustomerNotFoundException {
        Customer customerToRemove = retrieveCustomerByCustomerId(customerId);
        try {
            em.remove(customerToRemove);
        } catch (NoResultException ex) {
            throw new CustomerNotFoundException("Customer does not exist!");
        }
    }

    public void persist(Object object) {
        em.persist(object);
    }
}
