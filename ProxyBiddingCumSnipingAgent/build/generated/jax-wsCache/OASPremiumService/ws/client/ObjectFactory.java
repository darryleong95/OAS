
package ws.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RemoteBrowseAllAuctionListings_QNAME = new QName("http://ws.ejb/", "remoteBrowseAllAuctionListings");
    private final static QName _RemoteBrowseAllAuctionListingsResponse_QNAME = new QName("http://ws.ejb/", "remoteBrowseAllAuctionListingsResponse");
    private final static QName _RemoteViewCreditBalanceResponse_QNAME = new QName("http://ws.ejb/", "remoteViewCreditBalanceResponse");
    private final static QName _RemoteViewCreditBalance_QNAME = new QName("http://ws.ejb/", "remoteViewCreditBalance");
    private final static QName _RemoteViewWonAuctionListingsResponse_QNAME = new QName("http://ws.ejb/", "remoteViewWonAuctionListingsResponse");
    private final static QName _AuctionListingNotFoundException_QNAME = new QName("http://ws.ejb/", "AuctionListingNotFoundException");
    private final static QName _ConfigureSnipingForAuctionListingResponse_QNAME = new QName("http://ws.ejb/", "configureSnipingForAuctionListingResponse");
    private final static QName _RemoteLoginResponse_QNAME = new QName("http://ws.ejb/", "remoteLoginResponse");
    private final static QName _RemoteViewWonAuctionListings_QNAME = new QName("http://ws.ejb/", "remoteViewWonAuctionListings");
    private final static QName _ConfigureProxyBiddingForAuctionListing_QNAME = new QName("http://ws.ejb/", "configureProxyBiddingForAuctionListing");
    private final static QName _RemoteViewAuctionListingDetailsResponse_QNAME = new QName("http://ws.ejb/", "remoteViewAuctionListingDetailsResponse");
    private final static QName _CustomerNotFoundException_QNAME = new QName("http://ws.ejb/", "CustomerNotFoundException");
    private final static QName _RemoteViewAuctionListingDetails_QNAME = new QName("http://ws.ejb/", "remoteViewAuctionListingDetails");
    private final static QName _ConfigureProxyBiddingForAuctionListingResponse_QNAME = new QName("http://ws.ejb/", "configureProxyBiddingForAuctionListingResponse");
    private final static QName _PremiumRegistrationResponse_QNAME = new QName("http://ws.ejb/", "premiumRegistrationResponse");
    private final static QName _RemoteLogin_QNAME = new QName("http://ws.ejb/", "remoteLogin");
    private final static QName _ConfigureSnipingForAuctionListing_QNAME = new QName("http://ws.ejb/", "configureSnipingForAuctionListing");
    private final static QName _PremiumRegistration_QNAME = new QName("http://ws.ejb/", "premiumRegistration");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConfigureProxyBiddingForAuctionListingResponse }
     * 
     */
    public ConfigureProxyBiddingForAuctionListingResponse createConfigureProxyBiddingForAuctionListingResponse() {
        return new ConfigureProxyBiddingForAuctionListingResponse();
    }

    /**
     * Create an instance of {@link RemoteViewAuctionListingDetails }
     * 
     */
    public RemoteViewAuctionListingDetails createRemoteViewAuctionListingDetails() {
        return new RemoteViewAuctionListingDetails();
    }

    /**
     * Create an instance of {@link PremiumRegistrationResponse }
     * 
     */
    public PremiumRegistrationResponse createPremiumRegistrationResponse() {
        return new PremiumRegistrationResponse();
    }

    /**
     * Create an instance of {@link RemoteLogin }
     * 
     */
    public RemoteLogin createRemoteLogin() {
        return new RemoteLogin();
    }

    /**
     * Create an instance of {@link PremiumRegistration }
     * 
     */
    public PremiumRegistration createPremiumRegistration() {
        return new PremiumRegistration();
    }

    /**
     * Create an instance of {@link ConfigureSnipingForAuctionListing }
     * 
     */
    public ConfigureSnipingForAuctionListing createConfigureSnipingForAuctionListing() {
        return new ConfigureSnipingForAuctionListing();
    }

    /**
     * Create an instance of {@link ConfigureProxyBiddingForAuctionListing }
     * 
     */
    public ConfigureProxyBiddingForAuctionListing createConfigureProxyBiddingForAuctionListing() {
        return new ConfigureProxyBiddingForAuctionListing();
    }

    /**
     * Create an instance of {@link RemoteViewAuctionListingDetailsResponse }
     * 
     */
    public RemoteViewAuctionListingDetailsResponse createRemoteViewAuctionListingDetailsResponse() {
        return new RemoteViewAuctionListingDetailsResponse();
    }

    /**
     * Create an instance of {@link CustomerNotFoundException }
     * 
     */
    public CustomerNotFoundException createCustomerNotFoundException() {
        return new CustomerNotFoundException();
    }

    /**
     * Create an instance of {@link ConfigureSnipingForAuctionListingResponse }
     * 
     */
    public ConfigureSnipingForAuctionListingResponse createConfigureSnipingForAuctionListingResponse() {
        return new ConfigureSnipingForAuctionListingResponse();
    }

    /**
     * Create an instance of {@link RemoteLoginResponse }
     * 
     */
    public RemoteLoginResponse createRemoteLoginResponse() {
        return new RemoteLoginResponse();
    }

    /**
     * Create an instance of {@link RemoteViewWonAuctionListings }
     * 
     */
    public RemoteViewWonAuctionListings createRemoteViewWonAuctionListings() {
        return new RemoteViewWonAuctionListings();
    }

    /**
     * Create an instance of {@link RemoteBrowseAllAuctionListings }
     * 
     */
    public RemoteBrowseAllAuctionListings createRemoteBrowseAllAuctionListings() {
        return new RemoteBrowseAllAuctionListings();
    }

    /**
     * Create an instance of {@link RemoteViewCreditBalance }
     * 
     */
    public RemoteViewCreditBalance createRemoteViewCreditBalance() {
        return new RemoteViewCreditBalance();
    }

    /**
     * Create an instance of {@link RemoteViewWonAuctionListingsResponse }
     * 
     */
    public RemoteViewWonAuctionListingsResponse createRemoteViewWonAuctionListingsResponse() {
        return new RemoteViewWonAuctionListingsResponse();
    }

    /**
     * Create an instance of {@link RemoteBrowseAllAuctionListingsResponse }
     * 
     */
    public RemoteBrowseAllAuctionListingsResponse createRemoteBrowseAllAuctionListingsResponse() {
        return new RemoteBrowseAllAuctionListingsResponse();
    }

    /**
     * Create an instance of {@link RemoteViewCreditBalanceResponse }
     * 
     */
    public RemoteViewCreditBalanceResponse createRemoteViewCreditBalanceResponse() {
        return new RemoteViewCreditBalanceResponse();
    }

    /**
     * Create an instance of {@link AuctionListingNotFoundException }
     * 
     */
    public AuctionListingNotFoundException createAuctionListingNotFoundException() {
        return new AuctionListingNotFoundException();
    }

    /**
     * Create an instance of {@link CreditTransaction }
     * 
     */
    public CreditTransaction createCreditTransaction() {
        return new CreditTransaction();
    }

    /**
     * Create an instance of {@link AuctionListing }
     * 
     */
    public AuctionListing createAuctionListing() {
        return new AuctionListing();
    }

    /**
     * Create an instance of {@link Address }
     * 
     */
    public Address createAddress() {
        return new Address();
    }

    /**
     * Create an instance of {@link CreditPackage }
     * 
     */
    public CreditPackage createCreditPackage() {
        return new CreditPackage();
    }

    /**
     * Create an instance of {@link ProxyBid }
     * 
     */
    public ProxyBid createProxyBid() {
        return new ProxyBid();
    }

    /**
     * Create an instance of {@link Bid }
     * 
     */
    public Bid createBid() {
        return new Bid();
    }

    /**
     * Create an instance of {@link Customer }
     * 
     */
    public Customer createCustomer() {
        return new Customer();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoteBrowseAllAuctionListings }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.ejb/", name = "remoteBrowseAllAuctionListings")
    public JAXBElement<RemoteBrowseAllAuctionListings> createRemoteBrowseAllAuctionListings(RemoteBrowseAllAuctionListings value) {
        return new JAXBElement<RemoteBrowseAllAuctionListings>(_RemoteBrowseAllAuctionListings_QNAME, RemoteBrowseAllAuctionListings.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoteBrowseAllAuctionListingsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.ejb/", name = "remoteBrowseAllAuctionListingsResponse")
    public JAXBElement<RemoteBrowseAllAuctionListingsResponse> createRemoteBrowseAllAuctionListingsResponse(RemoteBrowseAllAuctionListingsResponse value) {
        return new JAXBElement<RemoteBrowseAllAuctionListingsResponse>(_RemoteBrowseAllAuctionListingsResponse_QNAME, RemoteBrowseAllAuctionListingsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoteViewCreditBalanceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.ejb/", name = "remoteViewCreditBalanceResponse")
    public JAXBElement<RemoteViewCreditBalanceResponse> createRemoteViewCreditBalanceResponse(RemoteViewCreditBalanceResponse value) {
        return new JAXBElement<RemoteViewCreditBalanceResponse>(_RemoteViewCreditBalanceResponse_QNAME, RemoteViewCreditBalanceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoteViewCreditBalance }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.ejb/", name = "remoteViewCreditBalance")
    public JAXBElement<RemoteViewCreditBalance> createRemoteViewCreditBalance(RemoteViewCreditBalance value) {
        return new JAXBElement<RemoteViewCreditBalance>(_RemoteViewCreditBalance_QNAME, RemoteViewCreditBalance.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoteViewWonAuctionListingsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.ejb/", name = "remoteViewWonAuctionListingsResponse")
    public JAXBElement<RemoteViewWonAuctionListingsResponse> createRemoteViewWonAuctionListingsResponse(RemoteViewWonAuctionListingsResponse value) {
        return new JAXBElement<RemoteViewWonAuctionListingsResponse>(_RemoteViewWonAuctionListingsResponse_QNAME, RemoteViewWonAuctionListingsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuctionListingNotFoundException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.ejb/", name = "AuctionListingNotFoundException")
    public JAXBElement<AuctionListingNotFoundException> createAuctionListingNotFoundException(AuctionListingNotFoundException value) {
        return new JAXBElement<AuctionListingNotFoundException>(_AuctionListingNotFoundException_QNAME, AuctionListingNotFoundException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfigureSnipingForAuctionListingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.ejb/", name = "configureSnipingForAuctionListingResponse")
    public JAXBElement<ConfigureSnipingForAuctionListingResponse> createConfigureSnipingForAuctionListingResponse(ConfigureSnipingForAuctionListingResponse value) {
        return new JAXBElement<ConfigureSnipingForAuctionListingResponse>(_ConfigureSnipingForAuctionListingResponse_QNAME, ConfigureSnipingForAuctionListingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoteLoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.ejb/", name = "remoteLoginResponse")
    public JAXBElement<RemoteLoginResponse> createRemoteLoginResponse(RemoteLoginResponse value) {
        return new JAXBElement<RemoteLoginResponse>(_RemoteLoginResponse_QNAME, RemoteLoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoteViewWonAuctionListings }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.ejb/", name = "remoteViewWonAuctionListings")
    public JAXBElement<RemoteViewWonAuctionListings> createRemoteViewWonAuctionListings(RemoteViewWonAuctionListings value) {
        return new JAXBElement<RemoteViewWonAuctionListings>(_RemoteViewWonAuctionListings_QNAME, RemoteViewWonAuctionListings.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfigureProxyBiddingForAuctionListing }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.ejb/", name = "configureProxyBiddingForAuctionListing")
    public JAXBElement<ConfigureProxyBiddingForAuctionListing> createConfigureProxyBiddingForAuctionListing(ConfigureProxyBiddingForAuctionListing value) {
        return new JAXBElement<ConfigureProxyBiddingForAuctionListing>(_ConfigureProxyBiddingForAuctionListing_QNAME, ConfigureProxyBiddingForAuctionListing.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoteViewAuctionListingDetailsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.ejb/", name = "remoteViewAuctionListingDetailsResponse")
    public JAXBElement<RemoteViewAuctionListingDetailsResponse> createRemoteViewAuctionListingDetailsResponse(RemoteViewAuctionListingDetailsResponse value) {
        return new JAXBElement<RemoteViewAuctionListingDetailsResponse>(_RemoteViewAuctionListingDetailsResponse_QNAME, RemoteViewAuctionListingDetailsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerNotFoundException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.ejb/", name = "CustomerNotFoundException")
    public JAXBElement<CustomerNotFoundException> createCustomerNotFoundException(CustomerNotFoundException value) {
        return new JAXBElement<CustomerNotFoundException>(_CustomerNotFoundException_QNAME, CustomerNotFoundException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoteViewAuctionListingDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.ejb/", name = "remoteViewAuctionListingDetails")
    public JAXBElement<RemoteViewAuctionListingDetails> createRemoteViewAuctionListingDetails(RemoteViewAuctionListingDetails value) {
        return new JAXBElement<RemoteViewAuctionListingDetails>(_RemoteViewAuctionListingDetails_QNAME, RemoteViewAuctionListingDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfigureProxyBiddingForAuctionListingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.ejb/", name = "configureProxyBiddingForAuctionListingResponse")
    public JAXBElement<ConfigureProxyBiddingForAuctionListingResponse> createConfigureProxyBiddingForAuctionListingResponse(ConfigureProxyBiddingForAuctionListingResponse value) {
        return new JAXBElement<ConfigureProxyBiddingForAuctionListingResponse>(_ConfigureProxyBiddingForAuctionListingResponse_QNAME, ConfigureProxyBiddingForAuctionListingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PremiumRegistrationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.ejb/", name = "premiumRegistrationResponse")
    public JAXBElement<PremiumRegistrationResponse> createPremiumRegistrationResponse(PremiumRegistrationResponse value) {
        return new JAXBElement<PremiumRegistrationResponse>(_PremiumRegistrationResponse_QNAME, PremiumRegistrationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoteLogin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.ejb/", name = "remoteLogin")
    public JAXBElement<RemoteLogin> createRemoteLogin(RemoteLogin value) {
        return new JAXBElement<RemoteLogin>(_RemoteLogin_QNAME, RemoteLogin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfigureSnipingForAuctionListing }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.ejb/", name = "configureSnipingForAuctionListing")
    public JAXBElement<ConfigureSnipingForAuctionListing> createConfigureSnipingForAuctionListing(ConfigureSnipingForAuctionListing value) {
        return new JAXBElement<ConfigureSnipingForAuctionListing>(_ConfigureSnipingForAuctionListing_QNAME, ConfigureSnipingForAuctionListing.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PremiumRegistration }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.ejb/", name = "premiumRegistration")
    public JAXBElement<PremiumRegistration> createPremiumRegistration(PremiumRegistration value) {
        return new JAXBElement<PremiumRegistration>(_PremiumRegistration_QNAME, PremiumRegistration.class, null, value);
    }

}
