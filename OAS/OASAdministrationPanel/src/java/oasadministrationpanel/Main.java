package oasadministrationpanel;


import ejb.session.stateless.EmployeeControllerRemote;
import ejb.session.stateless.AuctionListingControllerRemote;
import ejb.session.stateless.BidControllerRemote;
import ejb.session.stateless.CreditPackageControllerRemote;
import ejb.session.stateless.CreditTransactionControllerRemote;
import ejb.session.stateless.CustomerControllerRemote;
import javax.ejb.EJB;
import util.Exception.AuctionListingNotFoundException;
import util.Exception.InvalidAccessRightException;

public class Main {

    @EJB(name = "CustomerControllerRemote")
    private static CustomerControllerRemote customerControllerRemote;

    @EJB(name = "CreditTransactionControllerRemote")
    private static CreditTransactionControllerRemote creditTransactionControllerRemote;

    @EJB(name = "CreditPackageControllerRemote")
    private static CreditPackageControllerRemote creditPackageControllerRemote;

    @EJB(name = "BidControllerRemote")
    private static BidControllerRemote bidControllerRemote;

    @EJB(name = "EmployeeControllerRemote")
    private static EmployeeControllerRemote employeeControllerRemote;

    @EJB(name = "AuctionListingControllerRemote")
    private static AuctionListingControllerRemote auctionListingControllerRemote;
    
    public static void main(String[] args) throws InvalidAccessRightException, AuctionListingNotFoundException {
        
       MainApp mainApp = new MainApp(customerControllerRemote,creditTransactionControllerRemote,creditPackageControllerRemote, bidControllerRemote, employeeControllerRemote, auctionListingControllerRemote);
       mainApp.runApp();
    }
    
}
