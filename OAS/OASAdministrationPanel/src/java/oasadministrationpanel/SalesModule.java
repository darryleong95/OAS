/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oasadministrationpanel;

import ejb.session.stateless.AuctionListingControllerRemote;
import ejb.session.stateless.BidControllerRemote;
import ejb.session.stateless.CreditPackageControllerRemote;
import ejb.session.stateless.CreditTransactionControllerRemote;
import ejb.session.stateless.CustomerControllerRemote;
import ejb.session.stateless.EmployeeControllerRemote;
import entity.AuctionListing;
import entity.Employee;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import util.Exception.AuctionListingNotFoundException;

/**
 *
 * @author User
 */
public class SalesModule {

    private EmployeeControllerRemote employeeControllerRemote;
    private AuctionListingControllerRemote auctionListingControllerRemote;
    private BidControllerRemote bidControllerRemote;
    private CreditPackageControllerRemote creditPackageControllerRemote;
    private CreditTransactionControllerRemote creditTransactionControllerRemote;
    private CustomerControllerRemote customerControllerRemote;

    private Employee employeePassedThrough;

    public SalesModule() {
    }

    public SalesModule(EmployeeControllerRemote employeeControllerRemote, AuctionListingControllerRemote auctionListingControllerRemote, BidControllerRemote bidControllerRemote, CreditPackageControllerRemote creditPackageControllerRemote, CreditTransactionControllerRemote creditTransactionControllerRemote, CustomerControllerRemote customerControllerRemote, Employee employeePassedThrough) {
        this.employeeControllerRemote = employeeControllerRemote;
        this.auctionListingControllerRemote = auctionListingControllerRemote;
        this.bidControllerRemote = bidControllerRemote;
        this.creditPackageControllerRemote = creditPackageControllerRemote;
        this.creditTransactionControllerRemote = creditTransactionControllerRemote;
        this.customerControllerRemote = customerControllerRemote;
        this.employeePassedThrough = employeePassedThrough;
    }

    void menuSalesAdministration() throws AuctionListingNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;
        while (true) {
            try{
                System.out.println("*** OAS Administration Panel System :: Sales Administration ***\n");
                System.out.println("1: Create New Auction List");
                System.out.println("2: View Auction List details");
                System.out.println("3: View all Auction Lists");
                System.out.println("4: View all Auction Lists with bid below Reserve Price ");
                System.out.println("5: Assign Winning Bid for Listing with Bids but Below Reserve Price");
                System.out.println("6: Back\n");
                response = 0;

                while (response < 1 || response > 6) {
                    System.out.print("> ");

                    response = scanner.nextInt();
                    scanner.nextLine();
                    if (response == 1) {
                        doCreateNewAuctionListing(scanner);
                    } else if (response == 2) {
                        doViewAuctionListingDetails(scanner);
                    } else if (response == 3) {
                        doViewAllAuctionListing();
                    } else if (response == 4) {
                        doViewAllBelowReservePrice();
                    } else if (response == 5) {
                        assignWinningBidForListingWithBidsButBelowReservePrice(scanner);
                    } else if (response == 6) {
                        break;
                    } else {
                        System.out.println("Invalid option, please try again!\n");
                    }
                }

                if (response == 6) {
                    break;
                }
            }
            catch(InputMismatchException ex){
                System.out.println(ex + ": Try again.");
                scanner.nextLine();
            }
        }
    }

    public void doCreateNewAuctionListing(Scanner scanner) {        
        try{
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            AuctionListing auctionListing = new AuctionListing();
            while(true){
                System.out.print("Enter Item name                                           : ");
                String itemName = scanner.nextLine().trim();
                if(itemName.isEmpty()){
                    System.out.println("Item name cannot be empty, please try again"); 
                }
                else{
                    auctionListing.setItemName(itemName);
                    break;
                }   
            }
            Date startDate = null;
            while(true){
                System.out.print("Enter starting Date and Time in YYYY/MM/dd HH:mm:ss format: ");
                try{
                    startDate = dateFormat.parse(scanner.nextLine());
                    auctionListing.setStartDateTime(startDate);
                    break;
                }
                catch(InputMismatchException ex){
                    System.out.println(ex+ ": Try again.");
                    scanner.nextLine();
                }
                catch(ParseException ex){
                    System.out.println(ex + ", please try again");
                }
            }
            while(true){
                System.out.print("Enter ending Date and Time in YYYY/MM/dd HH:mm:ss format  : ");
                try{
                    Date endDate = dateFormat.parse(scanner.nextLine());
                    if(startDate.compareTo(endDate) >= 1){ 
                        System.out.println("End date has to be later than Start date, please try again.");
                        System.out.println();
                    }
                    else{ 
                        auctionListing.setEndDateTime(endDate);
                        break;                    
                    }
                }
                catch(InputMismatchException ex){
                    System.out.println(ex+ ": Try again.");
                    scanner.nextLine();
                }
                catch(ParseException ex){
                    System.out.println(ex + ", please try again");
                }   
            }       
            while(true){
                System.out.print("Enter Reserve price                                       : ");
                Double reservePrice = scanner.nextDouble();
                scanner.nextLine();
                if(reservePrice < 0) {
                    System.out.println("Reserve price cannot be set below 0, please try again");
                }
                else {
                    auctionListing.setReservePrice(reservePrice);
                    break;
                }
            }
            while(true){
                System.out.print("Enter starting Bid amount                                 : ");
                Double startingBid = scanner.nextDouble();
                scanner.nextLine();
                if(startingBid <0) System.out.println("Starting bid cannot be set below 0, please try again");
                else{
                    auctionListing.setStartBidAmt(startingBid);
                    break;
                }
            }
                
            auctionListing.setActive(false);
            
            auctionListing = auctionListingControllerRemote.createNewAuctionListing(auctionListing);
            System.out.println("New auction listing with ID " + auctionListing.getListingId() + " has been created");
            System.out.println();
        }
        catch(InputMismatchException ex){
            System.out.println(ex+ ": Try again.");
            scanner.nextLine();
        }
    }

    public void doViewAuctionListingDetails(Scanner scanner) {
        System.out.print("Enter Auction Listing ID to view: ");
        try {
            AuctionListing auctionListing = auctionListingControllerRemote.retrieveAuctionListingById(scanner.nextLong());
            scanner.nextLine();
            System.out.println("Listing ID        : " + auctionListing.getListingId());
            System.out.println("Listing name      : " + auctionListing.getItemName());
            System.out.println("Start Bid Amount  : " + auctionListing.getStartBidAmt());
            System.out.println("Current Bid Amount: " + auctionListing.getCurrentBidAmt());
            System.out.println("Start Date/Time   : " + auctionListing.getStartDateTime());
            System.out.println("End Date/Time     : " + auctionListing.getEndDateTime());
            Boolean active = auctionListing.getActive();
            if (active == true) {
                System.out.println("Activity Status: True\n");
            } else {
                System.out.println("Activity Status: False");
                System.out.println("Highest current Bid Amount: " + auctionListing.getCurrentBidAmt());
                System.out.println("Item Name: " + auctionListing.getItemName() + "\n");
                System.out.println();
            }
            Outer:
            while (true) {
                System.out.println("1: Update Auction Listing");
                System.out.println("2: Delete Auction Listing");
                System.out.println("3: Go back");
                System.out.print("> ");
                int x = scanner.nextInt();
                scanner.nextLine();
                switch (x) {
                    case 1:
                        doUpdateAuctionListing(auctionListing, scanner);
                        break;
                    case 2:
                        doDeleteAuctionListing(auctionListing, scanner);
                        break Outer;
                    case 3:
                        break Outer;
                    default:
                        System.out.println("Sorry, you have entered an invalid input, please try again");
                        break;
                }
            }
        } 
        catch (AuctionListingNotFoundException ex) {
            System.out.println("An error has occurred while retrieving Auction Listing: " + ex.getMessage() + "\n");
        }
        catch(InputMismatchException ex){
            System.out.println(ex + ": Try again.");
            scanner.nextLine();
        }
    }

    public void doUpdateAuctionListing(AuctionListing auctionList, Scanner scanner) {
        if(!auctionList.getActive()){
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            System.out.print("Click Y to update Item Name or any other character otherwise          : ");
            String choice = scanner.nextLine();
            if (choice.equals("Y")) {
                while(true){
                    System.out.print("Enter new Item Name                                                   : ");
                    String newItemName = scanner.nextLine();
                    if(newItemName.trim().isEmpty()) System.out.println("Item name cannot be empty, please try again");
                    else{
                        auctionList.setItemName(newItemName);
                        break;
                    }
                }
            }
            System.out.print("Click Y to update Starting Bid Amount or any other character otherwise: ");
            choice = scanner.nextLine();
            if (choice.equals("Y")) {
                while(true){
                    System.out.print("Enter new Starting Bid Amount                                         : ");
                    Double startBidAmt = scanner.nextDouble();
                    scanner.nextLine();
                    if(startBidAmt < 0) System.out.println("Starting Bid cannot be below 0, please try again");
                    else{
                        auctionList.setStartBidAmt(startBidAmt);
                        break;
                    }
                }
            }
            System.out.print("Click Y to update Reserve Price or any other character otherwise      : ");
            choice = scanner.nextLine();
            if (choice.equals("Y")) {
                while(true){
                    System.out.print("Enter new Reserve price                                               : ");
                    Double reservePrice = scanner.nextDouble();
                    scanner.nextLine();
                    if(reservePrice < 0) System.out.println("Reserve price cannot be set below 0, please try again");
                    else {
                        auctionList.setReservePrice(reservePrice);
                        break;
                    }
                }
            }
            System.out.print("Click Y to update Start Date/Time or any other character otherwise    : ");
            choice = scanner.nextLine();
            Date new_StartDateTime = null;
            if (choice.equals("Y")) {
                while(true){
                    System.out.print("Enter Start Date/Time in YYYY-MM-DD HH:mm:ss format                   : ");
                    try{
                        Date current_EndDateTime = auctionList.getEndDateTime();
                        new_StartDateTime = dateFormat.parse(scanner.nextLine());
                        if(new_StartDateTime.compareTo(current_EndDateTime) >= 0) System.out.println("Starting Date & Time cannot be set after/on End Date & Time, please try again");
                        else {
                            auctionList.setStartDateTime(new_StartDateTime);
                            break;
                        }
                    }                
                    catch(ParseException ex){
                        System.out.println(ex + ", please try again.");
                    }
                }
            }
            System.out.print("Click Y to update End Date or any other character otherwise           : ");
            choice = scanner.nextLine();
            if (choice.equals("Y")) {
                while(true){
                    try{
                        System.out.print("Enter End Date/Time in YYYY-MM-DD HH:mm:ss format                     : ");
                        Date new_EndDateTime = dateFormat.parse(scanner.nextLine());
                        Date current_StartDateTime;
                        if(new_StartDateTime == null){
                            current_StartDateTime = auctionList.getStartDateTime();
                        }
                        else{
                            current_StartDateTime = new_StartDateTime;
                        }
                        if(current_StartDateTime.compareTo(new_EndDateTime) >= 0) System.out.println("Starting Date & Time cannot be set after/on End Date & Time, please try again");
                        else{
                            auctionList.setEndDateTime(new_EndDateTime);
                            break;
                        }
                    }
                    catch(ParseException ex){
                        System.out.println(ex + ", please try again.");
                    }
                }
            }

            auctionListingControllerRemote.updateAuctionListing(auctionList);
            System.out.println("Auction listing updated!");
            System.out.println();
            System.out.println("Press enter to continue");
            scanner.nextLine();
            System.out.println();
        }
        else System.out.println("Auction has started. Unable to update auction once it has started");
    }

    public void doDeleteAuctionListing(AuctionListing auctionList, Scanner scanner) {
        try {
            Long id = auctionList.getListingId();
            if (auctionList.getActive()){
                System.out.println("Auction Listing is active, therefore cannot be deleted!");
                return;
            }
            auctionListingControllerRemote.deleteAuctionListing(id);
            System.out.println("Auction listing successfully deleted!");
            System.out.println();
            System.out.println("Press enter to continue");
            scanner.nextLine();
            System.out.println();
        } catch (AuctionListingNotFoundException ex) {
            System.out.println("An error has occurred while retrieving Auction Listing: " + ex.getMessage() + "\n");
        }
    }

    public void doViewAllAuctionListing() {
        List<AuctionListing> auctionListingList = auctionListingControllerRemote.retrieveAllAuctionListings();
        System.out.println();
        for (int i = 0; i < auctionListingList.size(); i++) {
            System.out.println("Listing ID        : " + auctionListingList.get(i).getListingId());
            System.out.println("Start Bid Amount  : " + auctionListingList.get(i).getStartBidAmt());
            System.out.println("Start Date        : " + auctionListingList.get(i).getStartDateTime());
            System.out.println("End Date          : " + auctionListingList.get(i).getEndDateTime());
            Boolean active = auctionListingList.get(i).getActive();
            if (active == true) {
                System.out.println("Activity Status   : True");
            } else {
                System.out.println("Activity Status   : False");
                System.out.println("Current Bid Amount: " + auctionListingList.get(i).getCurrentBidAmt());
                //System.out.println("Winning Bid Amount: " + auctionListingList.get(i).getWinningBid());
                System.out.println("Item Name         : " + auctionListingList.get(i).getItemName());
                System.out.println("Requires Intervenetion: " + auctionListingList.get(i).getRequireIntervention().toString());
            }
            System.out.println();
        }
        if(auctionListingList.size() == 0){
            System.out.println("No Auction Listings currently available");
        }
    }

    public void doViewAllBelowReservePrice() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nThe following are the closed auction listings with bids but below reserve price:\n");
        boolean atLeastOne = false;
        //commence search through list of auctionListings, to display whichever fulfill criteria
        List<AuctionListing> auctionListings = auctionListingControllerRemote.retrieveAllAuctionListings();
        for (AuctionListing al : auctionListings) {
            if (al.getActive() == false && al.getRequireIntervention() == true) {
                System.out.println("\nName of Product:" + al.getItemName());
                System.out.println("Auction Listing ID:" + al.getListingId());
                System.out.println("Auction Listing Reserve Price:" + al.getReservePrice());
                System.out.println("Auction Listing Current Price:" + al.getCurrentBidAmt() + "\n");
                atLeastOne = true;
            }
        }
        if (!atLeastOne) {
            System.out.println("There is currently no auction listing that requires manual intervenetion.");
            System.out.println("Press Enter to continue...\n");
            sc.nextLine();
        }
    }

    public void assignWinningBidForListingWithBidsButBelowReservePrice(Scanner sc) {
        doViewAllBelowReservePrice();
        List<AuctionListing> auctionListingsRequireIntervenetion = new ArrayList<AuctionListing>();
        //checks through all listings to ensure there is at least one that requires intervenetion
        boolean atLeastOne = false;
        for (AuctionListing al : auctionListingControllerRemote.retrieveAllAuctionListings()) {
            if (al.getRequireIntervention()) {
                atLeastOne = true;
                auctionListingsRequireIntervenetion.add(al);
            }
        }
        //if there is at least one listing that requires intervenetion
        if (atLeastOne == true) {

            System.out.println("\n Please enter the Auction Listing ID of the auction listing you wish to intervene: \n");
            System.out.print(">");
            Long auctionListingId = sc.nextLong();
            sc.nextLine(); //absorbs line
            try {
                while (auctionListingControllerRemote.retrieveAuctionListingById(auctionListingId).equals(null) || !auctionListingsRequireIntervenetion.contains(auctionListingControllerRemote.retrieveAuctionListingById(auctionListingId))) {
                    System.out.println("\nPlease re-enter Auction Listing ID:");
                    auctionListingId = sc.nextLong();
                    sc.nextLine();
                }//ensure auction listing does require intervenetion, and is valid
                //retrieves all auction listing 
                AuctionListing auctionListing = auctionListingControllerRemote.retrieveAuctionListingById(auctionListingId);
                //shows details of auction listing in question
                System.out.println("\nAuction Listing ID:" + auctionListing.getListingId());
                System.out.println("Highest Bid was made by " + auctionListing.getBidList().get(auctionListing.getBidList().size() - 1).getCustomer().getUserName()); //show username of customer who placed last bid
                System.out.println("Auction Listing Current Price:" + auctionListing.getCurrentBidAmt());
                System.out.println("Auction Listing Reserve Price:" + auctionListing.getReservePrice() + "\n");
                Outer:
                while (true) {

                    int input = 0;
                    System.out.println("Mark Highest Bid as Winning Bid?");
                    System.out.println("1: Yes");
                    System.out.println("2: No");

                    while (input < 1 || input > 2) {
                        System.out.print(">");
                        input = sc.nextInt();
                        sc.nextLine();
                        switch (input) {
                            case 1:
                                auctionListingControllerRemote.setWinnerManually(auctionListing, true);
                                System.out.println("Winning bid assigned!\n");
                                break Outer;
                            case 2:
                                auctionListingControllerRemote.setWinnerManually(auctionListing, false);
                                System.out.println("Auction Listing has been set to no winner!\n");
                                break Outer;
                            default:
                                System.out.println("Invalid option. Please try again.");
                                break;
                        }
                    }
                }
            } catch (AuctionListingNotFoundException ex) {
                System.out.println("Auction Listing not found!");
            }  
        }
    }
}
