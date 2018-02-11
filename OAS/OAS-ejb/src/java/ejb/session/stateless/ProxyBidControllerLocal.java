package ejb.session.stateless;

import entity.ProxyBid;
import java.util.List;

public interface ProxyBidControllerLocal {
    
    public ProxyBid createNewProxyBid(ProxyBid proxyBid);
    
    public List<ProxyBid> retrieveAllProxyBids();

    public ProxyBid retrieveProxyBidById(Long proxyBidId);
    
}
