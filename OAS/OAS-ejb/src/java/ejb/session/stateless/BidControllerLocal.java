package ejb.session.stateless;

import entity.Bid;
import java.util.List;
import util.Exception.InvalidBidException;

public interface BidControllerLocal {

    public Bid createNewBid(Bid bid);

    public List<Bid> retrieveAllBids();

    public Bid retrieveByBidId(Long bidId) throws InvalidBidException;

    public void updateBid(Bid bid);

    public void deleteBid(Long bidId) throws InvalidBidException;

}
