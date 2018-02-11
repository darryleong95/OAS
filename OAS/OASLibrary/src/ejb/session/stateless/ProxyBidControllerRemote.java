package ejb.session.stateless;

import entity.ProxyBid;
import java.util.List;

public interface ProxyBidControllerRemote {
    
    public ProxyBid createNewProxyBid(ProxyBid proxyBid);
    
    public List<ProxyBid> retrieveAllProxyBids();
    
}
