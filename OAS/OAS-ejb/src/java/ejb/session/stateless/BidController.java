package ejb.session.stateless;

import entity.Bid;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.Exception.InvalidBidException;

@Stateless
@Local(BidControllerLocal.class)
@Remote(BidControllerRemote.class)
public class BidController implements BidControllerRemote, BidControllerLocal {

    @PersistenceContext(unitName = "OAS-ejbPU")
    private EntityManager em;
    
    @Override
    public Bid createNewBid(Bid bid) {
        em.persist(bid);
        em.flush();
        em.refresh(bid);
        return bid;
    }

    @Override
    public List<Bid> retrieveAllBids() {
        Query query = em.createQuery("SELECT s FROM Bid s");

        return query.getResultList();
    }

    @Override
    public Bid retrieveByBidId(Long bidId) throws InvalidBidException {
        Bid bid = em.find(Bid.class, bidId);
        if(bid != null){
            return bid;
        } 
        else{
            throw new InvalidBidException("Invalid bid ID");
        }
    }

    @Override
    public void updateBid(Bid bid){
        em.merge(bid);
    }

    @Override
    public void deleteBid(Long bidId) throws InvalidBidException {
        Bid bid = em.find(Bid.class, bidId);
        try {
            em.remove(bid);
        } catch (NoResultException ex) {
            throw new InvalidBidException("Invalid bid ID. Bid does not exists.");
        }
    }

    public void persist(Object object) {
        em.persist(object);
    }
}
