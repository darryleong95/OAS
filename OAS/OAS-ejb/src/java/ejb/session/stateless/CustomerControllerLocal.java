package ejb.session.stateless;

import entity.Customer;
import java.util.List;
import util.Exception.CustomerNotFoundException;
import util.Exception.InvalidLoginCredentialException;

public interface CustomerControllerLocal {

    public Customer persistCustomer(Customer customer);

    public void updateCustomer(Customer customer);

    public Customer retrieveCustomerByCustomerId(Long customerId) throws CustomerNotFoundException;

    public List<Customer> retrieveAllCustomers();

    public Customer retrieveCustomerByUserName(String username) throws CustomerNotFoundException;

    public Customer customerLogin(String username, String password) throws InvalidLoginCredentialException;

    public void deleteCustomer(Long customerId) throws CustomerNotFoundException;

    public Customer retrieveCustomerByEmail(String email) throws CustomerNotFoundException;

    public Customer retrieveCustomerByIdentificationNumber(String identificationNumber) throws CustomerNotFoundException;

    public Customer retrieveCustomerByMobileNumber(int mobileNumber) throws CustomerNotFoundException;

}
