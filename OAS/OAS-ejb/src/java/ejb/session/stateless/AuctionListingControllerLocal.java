package ejb.session.stateless;

import entity.AuctionListing;
import entity.Bid;
import entity.Customer;
import java.util.List;
import util.Exception.AuctionListingNotFoundException;

public interface AuctionListingControllerLocal {

    public AuctionListing createNewAuctionListing(AuctionListing auctionListing);

    public AuctionListing retrieveAuctionListingById(Long auctionListingId) throws AuctionListingNotFoundException;

    public List<AuctionListing> retrieveAllAuctionListings();

    public void updateAuctionListing(AuctionListing updateAuctionListing);

    public void deleteAuctionListing(Long auctionListingId) throws AuctionListingNotFoundException;
    
    public Bid makeBid(AuctionListing auctionListing, Customer customer, Double newBidAmount);

}
