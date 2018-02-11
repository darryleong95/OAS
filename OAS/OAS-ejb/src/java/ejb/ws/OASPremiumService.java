package ejb.ws;

import ejb.session.stateless.AuctionListingControllerLocal;
import ejb.session.stateless.CustomerControllerLocal;
import ejb.session.stateless.EjbTimerSessionBeanLocal;
import ejb.session.stateless.ProxyBidControllerLocal;
import entity.AuctionListing;
import entity.Bid;
import entity.Customer;
import entity.ProxyBid;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import util.Exception.AuctionListingNotFoundException;
import util.Exception.CustomerNotFoundException;
import util.Exception.InvalidLoginCredentialException;

@WebService(serviceName = "OASPremiumService")
@Stateless()
public class OASPremiumService {

    @EJB
    private EjbTimerSessionBeanLocal ejbTimerSessionBeanLocal;

    @EJB(name = "ProxyBidControllerLocal")
    private ProxyBidControllerLocal proxyBidControllerLocal;

    @EJB
    private AuctionListingControllerLocal auctionListingControllerLocal;

    @EJB
    private CustomerControllerLocal customerControllerLocal;
    

    @WebMethod(operationName = "premiumRegistration")
    public Boolean premiumRegistration(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {

        Customer customer;

        try {
            customer = customerControllerLocal.customerLogin(username, password);
        } catch (InvalidLoginCredentialException ex) {
            return false;
        }

        if (customer != null) {
            customer.setIsPremium(Boolean.TRUE);
            return true; //returns true if customer has been successfully registered as premium user
        }

        return false; //returns false if customer does not exist / use wrong password and username 
    }

    @WebMethod(operationName = "remoteLogin")
    public Customer remoteLogin(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {

        Customer customer;

        try {
            customer = customerControllerLocal.customerLogin(username, password);
        } catch (InvalidLoginCredentialException ex) {
            return null;
        }

        if (customer.getIsPremium() == true) {
            return customer; //return customer if he/she is customer
        } else {
            return null; //return null if customer is not a premium customer
        }
    } 
    
    @WebMethod(operationName="remoteViewCreditBalance")
    public Double remoteViewCreditBalance(Long customerId) throws CustomerNotFoundException {
        return customerControllerLocal.retrieveCustomerByCustomerId(customerId).getAmtCredit();
    }
    
    @WebMethod(operationName="remoteViewAuctionListingDetails")
    public AuctionListing remoteViewAuctionListingDetails(Long listingId) throws AuctionListingNotFoundException {
        return auctionListingControllerLocal.retrieveAuctionListingById(listingId);
    }
    
    //NEED THROW EXCEPTION
    @WebMethod(operationName="configureProxyBiddingForAuctionListing")
    public ProxyBid configureProxyBiddingForAuctionListing(AuctionListing containerAuctionListing, Customer containerCustomer, Double maxAmount) {
        
        AuctionListing auctionListing = null;
        Customer customer = null;
        
        try {
            auctionListing = auctionListingControllerLocal.retrieveAuctionListingById(containerAuctionListing.getListingId());
        } catch (AuctionListingNotFoundException ex) {}
        
        try {
            customer = customerControllerLocal.retrieveCustomerByCustomerId(containerCustomer.getCustomerId());
        } catch (CustomerNotFoundException ex) {}
        
       
        ProxyBid proxyBid = proxyBidControllerLocal.createNewProxyBid(new ProxyBid(customer, auctionListing, maxAmount));
        customer.addProxyBid(proxyBid);
        auctionListing.addProxyBid(proxyBid);
        customerControllerLocal.updateCustomer(customer);
        auctionListingControllerLocal.updateAuctionListing(auctionListing);
        
        if (auctionListing.getBidList().isEmpty()) {

            Double newBidAmount = auctionListing.getStartBidAmt();
            Double bidIncrement = 0.0;

            if (newBidAmount >= 5000) {
                bidIncrement = 100.0;
            } else if (newBidAmount >= 2500) {
                bidIncrement = 50.0;
            } else if (newBidAmount >= 1000) {
                bidIncrement = 25.00;
            } else if (newBidAmount >= 500) {
                bidIncrement = 10.00;
            } else if (newBidAmount >= 250) {
                bidIncrement = 5.00;
            } else if (newBidAmount >= 100) {
                bidIncrement = 2.50;
            } else if (newBidAmount >= 25) {
                bidIncrement = 1.00;
            } else if (newBidAmount >= 5) {
                bidIncrement = 0.50;
            } else if (newBidAmount >= 1) {
                bidIncrement = 0.25;
            } else if (newBidAmount >= 0.00) {
                bidIncrement = 0.05;
            }
            newBidAmount = newBidAmount + bidIncrement;
            Bid firstBid = auctionListingControllerLocal.makeBid(auctionListing, customer, newBidAmount);

        }
 
        return proxyBid;
    }
    
    //NEED TO DO
    @WebMethod(operationName="configureSnipingForAuctionListing")
    public void configureSnipingForAuctionListing(int numberOfSeconds, Long auctionListingId, double maxAmount, Long customerId) throws AuctionListingNotFoundException {
        
        AuctionListing auctionListing = auctionListingControllerLocal.retrieveAuctionListingById(auctionListingId);
        
        Calendar calendar = Calendar.getInstance(); //create a new calendar
        calendar.setTime(auctionListing.getEndDateTime()); //set calendar to hold the time for end date of auction listing
        calendar.add(calendar.SECOND, numberOfSeconds *= -1); //subtract the end date of auction listing by number of seconds; will give the exact time the final bid is made
        
        ejbTimerSessionBeanLocal.createNewTimer(calendar.getTime(), auctionListingId, maxAmount, customerId);
    }
    
    
    @WebMethod(operationName="remoteBrowseAllAuctionListings")
    public List<AuctionListing> remoteBrowseAllAuctionListings() {
        return auctionListingControllerLocal.retrieveAllAuctionListings();
    }
    
    @WebMethod(operationName="remoteViewWonAuctionListings")
    public List<AuctionListing> remoteViewWonAuctionListings (Long customerId) {
        Customer customer = null;
        try {
        customer = customerControllerLocal.retrieveCustomerByCustomerId(customerId);
        } catch (CustomerNotFoundException ex) {
        }
        return customer.getAuctionListingWon();
    }
    
}
