package ejb.session.stateless;

import entity.AuctionListing;
import entity.Bid;
import entity.CreditTransaction;
import entity.Customer;
import entity.ProxyBid;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.Enum.TransactionTypeEnum;
import util.Exception.AuctionListingNotFoundException;
import util.Exception.CustomerNotFoundException;

@Stateless
@Local(AuctionListingControllerLocal.class)
@Remote(AuctionListingControllerRemote.class)

public class AuctionListingController implements AuctionListingControllerRemote, AuctionListingControllerLocal {

    @EJB(name = "CreditTransactionControllerLocal")
    private CreditTransactionControllerLocal creditTransactionControllerLocal;

    @EJB
    private ProxyBidControllerLocal proxyBidControllerLocal;

    @EJB(name = "BidControllerLocal")
    private BidControllerLocal bidControllerLocal;

    @EJB(name = "CustomerControllerLocal")
    private CustomerControllerLocal customerControllerLocal;

    @PersistenceContext(unitName = "OAS-ejbPU")
    private EntityManager em;

    @Override
    public AuctionListing createNewAuctionListing(AuctionListing auctionListing) {
        em.persist(auctionListing);
        em.flush();
        return auctionListing;
    }

    @Override
    public AuctionListing retrieveAuctionListingById(Long auctionListingId) throws AuctionListingNotFoundException {
        AuctionListing auctionListing = em.find(AuctionListing.class, auctionListingId);
        if (auctionListing != null) {
            auctionListing.getBidList().size();
            auctionListing.getProxyBids().size();
            return auctionListing;
        } else {
            throw new AuctionListingNotFoundException("Auction listing " + auctionListing + " does not exist!");
        }
    }

    @Override
    public List<AuctionListing> retrieveAllAuctionListings() {
        Query query = em.createQuery("SELECT a FROM AuctionListing a");
        List<AuctionListing> auctionListings = query.getResultList();
        auctionListings.size();
        System.out.println(auctionListings.size());
        for (AuctionListing auctionListing : auctionListings) {
            auctionListing.getBidList().size();
            auctionListing.getProxyBids().size();
        }
        return query.getResultList();
    }

    @Override
    public void updateAuctionListing(AuctionListing updateAuctionListing) {
        em.merge(updateAuctionListing);
    }

    @Override
    public void deleteAuctionListing(Long auctionListingId) throws AuctionListingNotFoundException {
        AuctionListing auctionListing = em.find(AuctionListing.class, auctionListingId);
        try {
            em.remove(auctionListing);
        } catch (NoResultException | NonUniqueResultException ex) {
            throw new AuctionListingNotFoundException("Auction listing " + auctionListing + " does not exist!");
        }
    }

    @Override
    public Bid makeBid(AuctionListing auctionListing, Customer customer, Double newBidAmount) {
        //retrieve auction listing
        try {
            auctionListing = retrieveAuctionListingById(auctionListing.getListingId());
        } catch (AuctionListingNotFoundException ex) {
        }

        try {
            customer = customerControllerLocal.retrieveCustomerByCustomerId(customer.getCustomerId());
        } catch (CustomerNotFoundException ex) {
        }

        //settle credit transaction of prev customer
        if (!auctionListing.getBidList().isEmpty()) {
            Bid previousBid = auctionListing.getBidList().get(auctionListing.getBidList().size() - 1);
            Customer previousCustomer = null;
            try {
                previousCustomer = customerControllerLocal.retrieveCustomerByCustomerId(previousBid.getCustomer().getCustomerId());
            } catch (CustomerNotFoundException ex) {
            }
            previousCustomer.setAmtCredit(previousCustomer.getAmtCredit() + previousBid.getBidAmt());
            CreditTransaction refundCreditTransaction = new CreditTransaction(TransactionTypeEnum.OUTBIDDED_REFUND, previousBid.getBidAmt(), previousCustomer, auctionListing);
            previousCustomer.addCreditTransactionList(refundCreditTransaction);
            customerControllerLocal.updateCustomer(previousCustomer);
            creditTransactionControllerLocal.createNewCreditTransaction(refundCreditTransaction);
        }

        //create the actual bid itself and add it to auctionlisting and customer
        Bid newBidMade = new Bid(newBidAmount, customer, auctionListing);
        newBidMade = bidControllerLocal.createNewBid(newBidMade);
        auctionListing.setCurrentBidAmt(newBidAmount);

        auctionListing.addBidList(newBidMade);

        customer.setAmtCredit(customer.getAmtCredit() - newBidAmount);

        customer.addBidListing(newBidMade);

        //settle the credit transaction of new customer
        CreditTransaction biddingCreditTransaction = new CreditTransaction(TransactionTypeEnum.BIDDING, newBidAmount, customer, auctionListing);
        customer.addCreditTransactionList(biddingCreditTransaction);
        creditTransactionControllerLocal.createNewCreditTransaction(biddingCreditTransaction);
        updateAuctionListing(auctionListing);
        customerControllerLocal.updateCustomer(customer);
        bidControllerLocal.updateBid(newBidMade);

        //to settle proxy bidding
      
        return newBidMade;
    }

    @Override
    public void setWinnerManually(AuctionListing auctionListing, Boolean setWinner) {
        if (setWinner == true) {
            auctionListing.getBidList().size();
            Bid winningBid = auctionListing.getBidList().get(auctionListing.getBidList().size() - 1);
            auctionListing.setWinningBid(winningBid);

            Customer winningCustomer = winningBid.getCustomer();
            auctionListing.setCustomer(winningCustomer);
            Long customer_id = winningCustomer.getCustomerId();

            winningCustomer = em.find(Customer.class, customer_id);
            winningCustomer.getAuctionListingWon().size();

            winningCustomer.addAuctionListingWon(auctionListing);
            auctionListing.setRequireIntervention(false);
            updateAuctionListing(auctionListing);
            customerControllerLocal.updateCustomer(winningCustomer);
        } else {
            auctionListing.getBidList().size();
            Bid refund_Bid = auctionListing.getBidList().get(auctionListing.getBidList().size() - 1);
            Double refund_Amount = refund_Bid.getBidAmt();
            Customer refund_Customer = refund_Bid.getCustomer();
            refund_Customer.setAmtCredit(refund_Customer.getAmtCredit() + refund_Amount);
            auctionListing.setWinningBid(null);
            auctionListing.setRequireIntervention(false);
            updateAuctionListing(auctionListing);
            customerControllerLocal.updateCustomer(refund_Customer);
        }
    }

}
