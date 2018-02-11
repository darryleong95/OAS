package ejb.session.stateless;
import entity.AuctionListing;
import entity.Customer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.Exception.AuctionListingNotFoundException;
import util.Exception.CustomerNotFoundException;

@Stateless
public class EjbTimerSessionBean implements EjbTimerSessionBeanRemote, EjbTimerSessionBeanLocal {

    @EJB
    private CustomerControllerLocal customerControllerLocal;

    @PersistenceContext(unitName = "OAS-ejbPU")
    private EntityManager em;

    @EJB(name = "AuctionListingControllerLocal")
    private AuctionListingControllerLocal auctionListingControllerLocal;

    @Resource
    private SessionContext context;

    @Schedule(hour = "*", minute = "*", second = "*/1", info = "checkAuctionTimer", persistent = false)
    public void checkAuctionTimer() {
        List<AuctionListing> auctionListing = auctionListingControllerLocal.retrieveAllAuctionListings();
        for (AuctionListing auctionListingEntity : auctionListing) {
            try {
                Date startDate = auctionListingEntity.getStartDateTime();
                Date endDate = auctionListingEntity.getEndDateTime();

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date currentDateTime = new Date();

                String _current = dateFormat.format(currentDateTime);
                if (dateFormat.parse(_current).compareTo(startDate) >= 0
                        && dateFormat.parse(_current).compareTo(endDate) < 0) { //if Today's date is within range of auction listing active period
                    if (auctionListingEntity.getActive() == false) {
                        auctionListingEntity.setActive(true);
                        System.out.println("Auction Listing: " + auctionListingEntity.getListingId() + " has been activated and is now available for viewing");
                    } else {
                        System.out.println("Auction Listing: " + auctionListingEntity.getListingId() + " is already active");
                    }
                } else {
                    if (auctionListingEntity.getActive() == true) {
                        auctionListingEntity.setActive(false); //disables the active status
                        //1st Case: Auction Listing has no bid and thus no winner
                        if (auctionListingEntity.getBidList().isEmpty()) {
                            auctionListingEntity.setRequireIntervention(false);
                            auctionListingEntity.setWinningBid(null);
                            //2nd Case: Auction Listing no reserve price, has >=1 bid
                        } else if (!auctionListingEntity.getBidList().isEmpty() && auctionListingEntity.getReservePrice().equals(0.0)) {
                            auctionListingEntity.setWinningBid(auctionListingEntity.getBidList().get(auctionListingEntity.getBidList().size() - 1));
                            auctionListingEntity.setRequireIntervention(false);
                            Customer winningCustomer = auctionListingEntity.getBidList().get(auctionListingEntity.getBidList().size() - 1).getCustomer();
                            winningCustomer.addAuctionListingWon(auctionListingEntity);
                            auctionListingControllerLocal.updateAuctionListing(auctionListingEntity);
                            customerControllerLocal.updateCustomer(winningCustomer);

                            //3rd Case: Auction Listing has reserve price, highest bid > reserve price
                        } else if (!auctionListingEntity.getBidList().isEmpty() && auctionListingEntity.getCurrentBidAmt() > auctionListingEntity.getReservePrice()) {
                            auctionListingEntity.setWinningBid(auctionListingEntity.getBidList().get(auctionListingEntity.getBidList().size() - 1));

                            Customer winningCustomer = auctionListingEntity.getBidList().get(auctionListingEntity.getBidList().size() - 1).getCustomer();

                            winningCustomer.addAuctionListingWon(auctionListingEntity);

                            auctionListingEntity.setCustomer(winningCustomer);

                            auctionListingEntity.setRequireIntervention(false);

                            auctionListingControllerLocal.updateAuctionListing(auctionListingEntity);
                            customerControllerLocal.updateCustomer(winningCustomer);
                            //4th Case: Auction Listing has reserve price, highest bid < reserve price
                        } else if (!auctionListingEntity.getBidList().isEmpty() && auctionListingEntity.getCurrentBidAmt() <= auctionListingEntity.getReservePrice()) {
                            auctionListingEntity.setRequireIntervention(true);
                        }
                    }
                }
                em.merge(auctionListingEntity);
            } catch (ParseException ex) {
                Logger.getLogger(EjbTimerSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } //end of checkauctiontimer

    public void createNewTimer(Date date, Long auctionListingId, Double maxAmount, Long customerId) {
        TimerService timerService = context.getTimerService();
        timerService.createSingleActionTimer(date, new TimerConfig("" + auctionListingId + " " + maxAmount + " " + customerId, true)); //create a timer w expiration stipulated as the date input
    }

    @Timeout
    public void doOneLastBid(Timer timer) throws CustomerNotFoundException {
        //assigning the variables with the data retrieved from timer
        String[] information = timer.getInfo().toString().split(" ");
        Long auctionListingId = Long.valueOf(information[0]);
        Double maxAmount = Double.valueOf(information[1]);
        Long customerId = Long.valueOf(information[2]);

        AuctionListing auctionListing = null;
        Customer customer = null;

        try {
            auctionListing = auctionListingControllerLocal.retrieveAuctionListingById(auctionListingId);
            customer = customerControllerLocal.retrieveCustomerByCustomerId(customerId);
        } catch (AuctionListingNotFoundException ex) {
            System.out.println("Auction Listing cannot be found!");
        }

        auctionListingControllerLocal.makeBid(auctionListing, customer, maxAmount);
    }
}
