package proxybiddingcumsnipingagent;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import ws.client.AuctionListing;
import ws.client.AuctionListingNotFoundException;
import ws.client.AuctionListingNotFoundException_Exception;
import ws.client.Customer;
import ws.client.ProxyBid;
import java.util.Calendar;

public class MainApp {

    Scanner sc = new Scanner(System.in);

    public void run() {

        while (true) {

            System.out.println("*** WELCOME TO THE PROXY BIDDING CUM SNIPPING AGENT! ***");
            System.out.println();
            System.out.println("1 - Login");
            System.out.println("2 - Register as a Premium Member");
            System.out.println("3 - Terminate\n");
            System.out.print("> ");

            String input = sc.nextLine();

            if (input.equals("1")) {
                login();
            } else if (input.equals("2")) {
                register();
            } else if (input.equals("3")) {
                break;
            } else {
                System.out.println("Invalid function input entered!");
                System.out.println("Please try again :<\n");
            }
        }

    }

    private void register() {
        System.out.println("*** Register as Premium Member ***");
        System.out.println("Enter your username and password\n");
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        Boolean registrationStatus = premiumRegistration(username, password);
        if (registrationStatus) {
            System.out.println("Successfully registered as premium member!\n");
        } else {
            System.out.println("Incorrect username and password entered\n");
        }
    }

    private void login() {
        System.out.println("*** Login to Premium Portal ***");
        System.out.println("Enter your username and password\n");
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        
        System.out.println();
        
        Customer customer = remoteLogin(username, password);
        if (customer == null) {
            System.out.println("Customer not found!\n");
        } else if (!customer.isIsPremium()) {
            System.out.println("Customer is not a premium member!\n");
        } else {
            System.out.println("Logging in...");
            menu(customer);
        }
    }

    private void menu(Customer customer) {

        while (true) {
            System.out.println("*** Premium Customer Portal ***\n");
            System.out.println("1 - Remote view credit balance");
            System.out.println("2 - Remote view auction listing details");
            System.out.println("3 - Browse all auction listings");
            System.out.println("4 - View won auction listings");
            System.out.println("5 - Log out\n");
            System.out.print("> ");

            String input = sc.nextLine();
            System.out.println();

            if (input.equals("1")) {
                remoteViewCreditBalance(customer);
            } else if (input.equals("2")) {
                viewAuctionListingDetails(customer);
            } else if (input.equals("3")) {
                browseAuctionListings();
            } else if (input.equals("4")) {
                viewWonAuctionListings(customer);
            } else if (input.equals("5")) {
                break;
            } else {
                System.out.println("Invalid function input entered!");
                System.out.println("Please try again :<");
            }

        }

    }

    private void remoteViewCreditBalance(Customer customer) {

        System.out.println("*** View Credit Balance ***\n");
        System.out.println("Credit Balance: " + customer.getAmtCredit() + "\n");

    }

    private void viewAuctionListingDetails(Customer customer) /*throws ParseException*/ {

        System.out.println("*** View Auction Listing Details ***\n");

        while (true) {
            try {
                System.out.print("Enter auction listing Id (0 to exit) :");
                Long input = sc.nextLong();
                sc.nextLine();

                //need call webservice client
                try {
                    if (input == 0) {
                        break;
                    }
                    AuctionListing auctionListing = remoteViewAuctionListingDetails(input);

                    if (!auctionListing.isActive()) {
                        System.out.println("This auction is not active! \n");
                    } else {
                        System.out.println("[AUCTION LISTING ID: " + input + "]");
                        System.out.println("Listing ID: " + auctionListing.getListingId() + " for item " + auctionListing.getItemName() + " with current price at " + auctionListing.getCurrentBidAmt() + ".");
                        System.out.println("Item name: " + auctionListing.getItemName());
                        System.out.println("Starting Bid: " + auctionListing.getStartBidAmt());
                        System.out.println("Start Date: " + auctionListing.getStartDateTime());
//                    System.out.println("Start Time: " + auctionListing.getStartTime());
                        System.out.println("End Date: " + auctionListing.getEndDateTime());
//                    System.out.println("End Time: " + auctionListing.getEndTime());
                        if (auctionListing.getCurrentBidAmt() == null) {
                            System.out.println("No one has bidded for this auction yet.");
                        } else {
                            System.out.println("Current Bid Amount:" + auctionListing.getCurrentBidAmt());
                        }
                        if (auctionListing.getReservePrice() == 0) {
                            System.out.println("The listing has no reserve price.\n");
                        } else {
                            System.out.println("The listing has a reserve price of $" + auctionListing.getReservePrice() + "\n");
                        }

                        while (true) {
                            System.out.println("Configure Proxy Bidding or Sniping for Auction Listing?\n");
                            System.out.println("1 - Congifure Proxy Bidding");
                            System.out.println("2 - Configure Sniping");
                            System.out.println("0 - Terminate\n");
                            System.out.print("> ");
                            String inputEntry = sc.nextLine().trim();
                            if (inputEntry.equals("1")) {
                                configureProxyBidding(auctionListing, customer);
                                break;
                            } else if (inputEntry.equals("2")) {
                                configureSniping(customer);
                                break;
                            } else if (inputEntry.equals("0")) {
                                break;
                            } else {
                                System.out.println("Invalid input entered! Please enter again.\n");
                            }
                        }
                        break;
                    }
                } catch (AuctionListingNotFoundException_Exception ex) {
                    System.out.println("Auction Listing Not Found!");
                }
            } catch (InputMismatchException ex) {
                sc.nextLine();
                System.out.println("Incorrect ID entered!");
            }
        }
    }

    private void configureProxyBidding(AuctionListing auctionListing, Customer customer) {

        System.out.println("*** CONFIGURE PROXY BIDDING ***\n");

        try {
            System.out.print("Enter max amount: ");
            Double maxAmount = sc.nextDouble();
            sc.nextLine();

            ProxyBid proxyBid = configureProxyBiddingForAuctionListing(auctionListing, customer, maxAmount);
            if (proxyBid != null) {
                System.out.println("Proxy Bidding for this auction of max bid " + proxyBid.getMaxAmount() + " has been made!\n");
            } else {
                System.out.println("Proxy Bid for this auction had already been made!\n");
            }

        } catch (InputMismatchException ex) {
            System.out.println("Incorrect max amount entered!\n");
            sc.nextLine();
        }
    }

    private void configureSniping(Customer customer) {

        System.out.println("*** CONFIGURE SNIPING BIDDING ***\n");

        try {

            System.out.println("\nEnter the number of seconds (0<seconds<=60) before listing expires to place bid: ");
            int numberOfSeconds = sc.nextInt();
            sc.nextLine();

            while (numberOfSeconds <= 0) {
                //ensuring that number of seconds does not exceed norm of 60s in a minute

                System.out.println("Please enter a valid number: ");
                numberOfSeconds = sc.nextInt();
                sc.nextLine();
            }

            System.out.println("\nEnter the auction listing ID: ");
            String auctionListingId = sc.nextLine();

            while (true) {
                try {
                    System.out.println("Enter maximum snipping amount: ");
                    Double amount = sc.nextDouble();
                    sc.nextLine();
                    configureSnipingForAuctionListing(numberOfSeconds, Long.valueOf(auctionListingId.trim()), amount, customer.getCustomerId());
                    System.out.println("Sniping for this auction made!");
                    break;
                } catch (InputMismatchException ex) {
                    System.out.println("Incorrect amount input entered!\n");
                    sc.nextLine();
                } catch (AuctionListingNotFoundException_Exception ex) {
                }
            }

        } catch (InputMismatchException ex) {
            System.out.println("Incorrect date format entered!\n");
            sc.nextLine();
        }
    }

    private void browseAuctionListings() {

        System.out.println("*** Browse Auction Listings ***\n");
        //callwebservice method here
        List<AuctionListing> auctionListings = remoteBrowseAllAuctionListings();
        int count = 0;
        if (!auctionListings.isEmpty()) {
            for (AuctionListing auctionListing : auctionListings) {
                if (auctionListing.isActive()) {
                    count++;
                    System.out.println("No. " + count + ") " + auctionListing.getItemName() + " [ListingId: " + auctionListing.getListingId() + "]");
                }
            }
            System.out.println();
            if (count == 0) {
                System.out.println("No Auction listing currently available\n");
            }
        } else {
            System.out.println("No Auction listing currently available\n");
        }
    }

    private void viewWonAuctionListings(Customer customer) {
        //need change something about this
        System.out.println("*** View all auction listings won ***\n");
        List<AuctionListing> listOfAuctionListingWon = remoteViewWonAuctionListings(customer.getCustomerId());
        if (listOfAuctionListingWon.isEmpty()) {
            System.out.println("User has won no auction listings.");
        } else {
            System.out.println("All Auction Listings Won are as follows: \n");
            int count = 0;
            for (AuctionListing auctionListing : listOfAuctionListingWon) {
                count++;
                System.out.println(count + ": " + auctionListing.getItemName() + " [Auction Listing ID: " + auctionListing.getListingId() + "]");
            }
            System.out.println();
        }
    }

    private static Boolean premiumRegistration(java.lang.String username, java.lang.String password) {
        ws.client.OASPremiumService_Service service = new ws.client.OASPremiumService_Service();
        ws.client.OASPremiumService port = service.getOASPremiumServicePort();
        return port.premiumRegistration(username, password);
    }

    private static Customer remoteLogin(java.lang.String username, java.lang.String password) {
        ws.client.OASPremiumService_Service service = new ws.client.OASPremiumService_Service();
        ws.client.OASPremiumService port = service.getOASPremiumServicePort();
        return port.remoteLogin(username, password);
    }

    private static AuctionListing remoteViewAuctionListingDetails(java.lang.Long arg0) throws AuctionListingNotFoundException_Exception {
        ws.client.OASPremiumService_Service service = new ws.client.OASPremiumService_Service();
        ws.client.OASPremiumService port = service.getOASPremiumServicePort();
        return port.remoteViewAuctionListingDetails(arg0);
    }

    private static java.util.List<ws.client.AuctionListing> remoteBrowseAllAuctionListings() {
        ws.client.OASPremiumService_Service service = new ws.client.OASPremiumService_Service();
        ws.client.OASPremiumService port = service.getOASPremiumServicePort();
        return port.remoteBrowseAllAuctionListings();
    }

    private static ProxyBid configureProxyBiddingForAuctionListing(ws.client.AuctionListing arg0, ws.client.Customer arg1, java.lang.Double arg2) {
        ws.client.OASPremiumService_Service service = new ws.client.OASPremiumService_Service();
        ws.client.OASPremiumService port = service.getOASPremiumServicePort();
        return port.configureProxyBiddingForAuctionListing(arg0, arg1, arg2);
    }

    private static void configureSnipingForAuctionListing(int arg0, java.lang.Long arg1, double arg2, java.lang.Long arg3) throws AuctionListingNotFoundException_Exception {
        ws.client.OASPremiumService_Service service = new ws.client.OASPremiumService_Service();
        ws.client.OASPremiumService port = service.getOASPremiumServicePort();
        port.configureSnipingForAuctionListing(arg0, arg1, arg2, arg3);
    }

    private static java.util.List<ws.client.AuctionListing> remoteViewWonAuctionListings(java.lang.Long arg0) {
        ws.client.OASPremiumService_Service service = new ws.client.OASPremiumService_Service();
        ws.client.OASPremiumService port = service.getOASPremiumServicePort();
        return port.remoteViewWonAuctionListings(arg0);
    }

    
    
    
}
