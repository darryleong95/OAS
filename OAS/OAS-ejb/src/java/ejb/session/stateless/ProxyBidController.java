package ejb.session.stateless;

import entity.AuctionListing;
import entity.Bid;
import entity.Customer;
import entity.ProxyBid;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@Local(ProxyBidControllerLocal.class)
@Remote(ProxyBidControllerRemote.class)
public class ProxyBidController implements ProxyBidControllerRemote, ProxyBidControllerLocal {

    @EJB
    private AuctionListingControllerLocal auctionListingControllerLocal;

    @Resource
    private SessionContext context;

    @PersistenceContext(unitName = "OAS-ejbPU")
    private EntityManager em;

    @Override
    public ProxyBid createNewProxyBid(ProxyBid proxyBid) {
        em.persist(proxyBid);
        em.flush();
        em.refresh(proxyBid);

        createNewProxyTimer(proxyBid);

        return proxyBid;
    }

    @Override
    public List<ProxyBid> retrieveAllProxyBids() {
        Query query = em.createQuery("SELECT p FROM ProxyBid p");
        return query.getResultList();
    }

    @Override
    public ProxyBid retrieveProxyBidById(Long proxyBidId) {
        return em.find(ProxyBid.class, proxyBidId);
    }

    public void createNewProxyTimer(ProxyBid proxyBid) {
        TimerService timerService = context.getTimerService();
        timerService.createIntervalTimer(0, 1000, new TimerConfig("" + proxyBid.getProxyBidId(), true));
    }

    @Timeout
    public void checkBidding(Timer timer) {
        //retrieve all the information on auction listing
        Long proxyBidId = Long.valueOf(timer.getInfo().toString());
        ProxyBid proxyBid = retrieveProxyBidById(proxyBidId);
        AuctionListing auctionListing = proxyBid.getAuctionListing();
        Double maxAmount = proxyBid.getMaxAmount();
        Customer customer = proxyBid.getCustomer();
        List<Bid> bidList = auctionListing.getBidList();

        //check whether last bid was made by the proxy bidder
        if (auctionListing.getBidList() == null || !auctionListing.getBidList().get(auctionListing.getBidList().size() - 1).getCustomer().equals(customer)) {

            Double bidIncrement = 0.0;
            Double currentHighestBid = bidList.get(bidList.size() - 1).getBidAmt();

            //check if suitable to make a bid
            if (currentHighestBid <= proxyBid.getMaxAmount()) {

                if (currentHighestBid >= 5000) {
                    bidIncrement = 100.0;
                } else if (currentHighestBid >= 2500) {
                    bidIncrement = 50.0;
                } else if (currentHighestBid >= 1000) {
                    bidIncrement = 25.00;
                } else if (currentHighestBid >= 500) {
                    bidIncrement = 10.00;
                } else if (currentHighestBid >= 250) {
                    bidIncrement = 5.00;
                } else if (currentHighestBid >= 100) {
                    bidIncrement = 2.50;
                } else if (currentHighestBid >= 25) {
                    bidIncrement = 1.00;
                } else if (currentHighestBid >= 5) {
                    bidIncrement = 0.50;
                } else if (currentHighestBid >= 1) {
                    bidIncrement = 0.25;
                } else if (currentHighestBid >= 0.01) {
                    bidIncrement = 0.05;
                }

                Double newBidAmount = currentHighestBid + bidIncrement;
                System.out.println("Our new bids amounts is " + newBidAmount);

                if (newBidAmount <= proxyBid.getMaxAmount()) {
                    Bid bid = auctionListingControllerLocal.makeBid(auctionListing, customer, newBidAmount);
                }
                
                else{
                    timer.cancel();
                }
                    

            }

        }
    }
}
