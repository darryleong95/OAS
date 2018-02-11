package oasauctionclient;

import ejb.session.stateless.AddressControllerRemote;
import ejb.session.stateless.AuctionListingControllerRemote;
import ejb.session.stateless.BidControllerRemote;
import ejb.session.stateless.CreditPackageControllerRemote;
import ejb.session.stateless.CreditTransactionControllerRemote;
import ejb.session.stateless.CustomerControllerRemote;
import entity.Address;
import entity.AuctionListing;
import entity.Bid;
import entity.CreditPackage;
import entity.CreditTransaction;
import entity.Customer;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import util.Enum.TransactionTypeEnum;
import util.Exception.AddressNotFoundException;
import util.Exception.AuctionListingNotFoundException;
import util.Exception.CustomerNotFoundException;
import util.Exception.InvalidCreditPackageException;

public class CustomerModule {

    private BidControllerRemote bidControllerRemote;
    private CreditPackageControllerRemote creditPackageControllerRemote;
    private CreditTransactionControllerRemote creditTransactionControllerRemote;
    private AddressControllerRemote addressControllerRemote;
    private CustomerControllerRemote customerControllerRemote;
    private AuctionListingControllerRemote auctionListingControllerRemote;
    private Customer customerPassedThrough;

    private Bid bid;
    private Address address;
    private AuctionListing auctionListing;
    private CreditPackage creditPackage;
    private CreditTransaction creditTransaction;

    Scanner sc = new Scanner(System.in);

    public CustomerModule() {
    }

    public CustomerModule(BidControllerRemote bidControllerRemote, CreditPackageControllerRemote creditPackageControllerRemote, CreditTransactionControllerRemote creditTransactionControllerRemote, AddressControllerRemote addressControllerRemote, CustomerControllerRemote customerControllerRemote, AuctionListingControllerRemote auctionListingControllerRemote, Customer customerPassedThrough) {
        this.bidControllerRemote = bidControllerRemote;
        this.creditPackageControllerRemote = creditPackageControllerRemote;
        this.creditTransactionControllerRemote = creditTransactionControllerRemote;
        this.addressControllerRemote = addressControllerRemote;
        this.customerControllerRemote = customerControllerRemote;
        this.auctionListingControllerRemote = auctionListingControllerRemote;
        this.customerPassedThrough = customerPassedThrough;
    }

    public void menuMain() {

        Integer response = 0;

        while (true) {

            try {
                System.out.println("*** OAS Auction Client : Welcome ***\n");
                System.out.println("You are logged in as " + customerPassedThrough.getFirstName() + "\n");
                System.out.println("1: Profile Related Operations");
                System.out.println("2: Address Related Operations");
                System.out.println("3: Credit Related Operations");
                System.out.println("4: Auction Listing Operations");
                System.out.println("5: Logout\n");
                response = 0;

                while (response < 1 || response > 5) {
                    System.out.print("> ");

                    response = sc.nextInt();
                    sc.nextLine();

                    if (response == 1) {
                        profileOperation(customerPassedThrough, sc);
                    } else if (response == 2) {
                        addressOperation(customerPassedThrough, sc);
                    } else if (response == 3) {
                        creditOperation(customerPassedThrough, sc);
                    } else if (response == 4) {
                        auctionListingOperation(customerPassedThrough, sc);
                    } else if (response == 5) {
                        return;
                    }
                }
            } catch (InputMismatchException ex) {
                System.out.println("Invalid function entered!");
                sc.nextLine();
            }

        }
    }

    private void profileOperation(Customer customer, Scanner sc) {
        Integer response = 0;

        try {
            customer = customerControllerRemote.retrieveCustomerByCustomerId(customer.getCustomerId());
        } catch (CustomerNotFoundException ex) {
        }

        while (true) {
            System.out.println("*** OAS Auction Client : Profile Operation ***\n");
            System.out.println("1: View Profile");
            System.out.println("2: Update Profile");
            System.out.println("3: Go back\n");
            response = 0;

            while (response < 1 || response > 3) {
                System.out.print("> ");

                response = sc.nextInt();
                sc.nextLine();

                if (response == 1) {
                    viewCustomerProfile(customer, sc);
                } else if (response == 2) {
                    updateCustomerProfile(customer, sc);
                } else if (response == 3) {
                    return;
                }
            }
        }
    }

    public void viewCustomerProfile(Customer customer, Scanner sc) {

        System.out.println("*** View customer profile ***\n");
        System.out.println("Username: " + customer.getUserName());
        System.out.println("Name: " + customer.getFirstName() + " " + customer.getLastName());
        System.out.println("Identification Number: " + customer.getIdentificationNumber());
        System.out.println("Mobile Number: " + customer.getMobileNumber());
        System.out.println("Email: " + customer.getEmail());
        System.out.println("CustomerId: " + customer.getCustomerId());
        System.out.println("Amount of Credits: " + customer.getAmtCredit());
        System.out.println("\n");

    }

    public void updateCustomerProfile(Customer customer, Scanner sc) {

        System.out.println("*** Update Customer Profile ***\n");

        OUTER:
        while (true) {
            System.out.println("Enter number corresponding to which entry to change\n");
            System.out.println("1) First Name");
            System.out.println("2) Last Name");
            System.out.println("3) Identification Number");
            System.out.println("4) Mobile Number");
            System.out.println("5) Email");
            System.out.println("6) Password");
            System.out.println("0) Go Back\n");

            try {
                int input = sc.nextInt();
                sc.nextLine();
                switch (input) {
                    case 0:
                        break OUTER;
                    case 1:
                        while (true) {
                            System.out.print("Enter your new first name: ");
                            String newFirstName = sc.nextLine();
                            System.out.println();
                            if (newFirstName.length() == 0) {
                                System.out.println("First name cannot be empty!\n");
                            } else {
                                customer.setFirstName(newFirstName);
                                customerControllerRemote.updateCustomer(customer);
                                System.out.println("First name updated!\n");
                                break;
                            }
                        }
                        break;
                    case 2:
                        while (true) {
                            System.out.print("Enter your new last name: ");
                            String newLastName = sc.nextLine();
                            System.out.println();
                            if (newLastName.length() == 0) {
                                System.out.println("Last name cannot be empty!\n");
                            } else {
                                customer.setLastName(newLastName);
                                customerControllerRemote.updateCustomer(customer);
                                System.out.println("Last name updated!");
                                break;
                            }
                        }
                        break;
                    case 3:
                        while (true) {
                            System.out.print("Enter your new identification number!");
                            String newIdentificationNumber = sc.nextLine();
                            if (newIdentificationNumber.length() != 9) {
                                System.out.println("Identification Number entered is of invalid length!");
                            } else {
                                Customer checker = null;
                                try {
                                    checker = customerControllerRemote.retrieveCustomerByIdentificationNumber(newIdentificationNumber);
                                } catch (CustomerNotFoundException ex) {
                                }

                                if (checker != null) {
                                    System.out.println("Error: Identification Number is already used!");
                                } else {
                                    customer.setIdentificationNumber(newIdentificationNumber);
                                    customerControllerRemote.updateCustomer(customer);
                                    System.out.println("Identification Number updated!\n");
                                    break;
                                }

                            }
                        }
                        break;
                    case 4:
                        while (true) {
                            System.out.print("Enter your new mobile number: ");
                            try {
                                int mobileNumber = sc.nextInt();
                                sc.nextLine();
                                String tester = Integer.toString(mobileNumber);
                                if (tester.length() == 0) {
                                    System.out.println("Error: Mobile Number field cannot be empty");
                                } else {
                                    Customer checker = null;
                                    try {
                                        checker = customerControllerRemote.retrieveCustomerByMobileNumber(mobileNumber);
                                    } catch (CustomerNotFoundException ex) {
                                    }

                                    if (checker != null) {
                                        System.out.println("Error: Mobile Number is already used!");
                                    } else {
                                        customer.setMobileNumber(mobileNumber);
                                        System.out.println("Mobile Number updated!");
                                        break;
                                    }
                                }
                            } catch (InputMismatchException Ex) {
                                sc.nextLine();
                                System.out.println("Invalid input entered! Only numerals allowed\n");
                            }
                        }
                        break;
                    case 5:
                        while (true) {
                            System.out.print("Enter your updated email: ");
                            String email = sc.nextLine().trim();
                            if (email.length() == 0) {
                                System.out.println("Error: Email field cannot be empty");
                            } else {

                                Customer checker = null;
                                try {
                                    checker = customerControllerRemote.retrieveCustomerByEmail(email);
                                } catch (CustomerNotFoundException ex) {
                                }

                                if (checker != null) {
                                    System.out.println("Error: Email is already used!");
                                } else {
                                    customer.setEmail(email);
                                    System.out.println("Email updated!\n");
                                    break;
                                }
                            }
                        }
                        break;
                    case 6:
                        while (true) {
                            System.out.print("Enter your new password (Min 8 characters):");
                            String newPassword = sc.nextLine();
                            if (newPassword.length() < 8) {
                                System.out.println("Password must have 8 or more characters!\n");
                            } else {
                                customer.setPassWord(newPassword);
                                customerControllerRemote.updateCustomer(customer);
                                System.out.println("Password updated!\n");
                                break;
                            }
                        }

                        break;
                    default:
                        System.out.println("Wrong input number entered! Enter a number corresponding to the fields mentioned");
                        break;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input entered! Please try again.\n");
                sc.nextLine();
            }

        }
    }

    private void addressOperation(Customer customer, Scanner sc) {
        String response;

        while (true) {
            System.out.println("*** OAS Auction Client : Address Operation ***\n");
            System.out.println("1: Create Address");
            System.out.println("2: View Address Detail");
            System.out.println("3: View All Addresses");
            System.out.println("4: Go back\n");

            System.out.print("> ");
            response = sc.nextLine();

            if (response.equals("1")) {
                createAddress(customer, sc);
            } else if (response.equals("2")) {
                viewAddressDetails(customer, sc);
            } else if (response.equals("3")) {
                viewAllAddresses(customer, sc);
            } else if (response.equals("4")) {
                return;
            }
        }
    }

    public void createAddress(Customer customer, Scanner sc) {

        System.out.println("*** Create New Address ***");

        try {
            customer = customerControllerRemote.retrieveCustomerByCustomerId(customer.getCustomerId());
        } catch (CustomerNotFoundException ex) {
        }

        Address newAddress = new Address();
        newAddress = addressControllerRemote.persistAddress(newAddress);
        while (true) {
            System.out.println("Enter new address! :");
            String input = sc.nextLine();
            if (input.length() == 0) {
                System.out.println("Address entered cannot be empty!\n");
            } else {
                newAddress.setAddress(input);
                newAddress.setDisabled(false);
                newAddress.setCustomer(customer);
                customer.getAddressList().add(newAddress);
                customerControllerRemote.updateCustomer(customer);
                addressControllerRemote.updateAddress(newAddress);
                System.out.println("Address successfully added!\n");
                break;
            }
        }
    }

    public void viewAddressDetails(Customer customer, Scanner sc) {
        System.out.println("*** View Address Details ***\n");

        Address address;

        while (true) {
            try {
                System.out.println("Enter address id: ");
                Long input = sc.nextLong();
                sc.nextLine();
                address = addressControllerRemote.retrieveAddressByAddressId(input);
                if (!address.getCustomer().equals(customer)) {
                    System.out.println("Address does not belong to customer!");
                    return;
                } else {
                    System.out.println("Address Id: " + address.getAddressId());
                    if (!address.isDisabled()) {
                        System.out.println("Address is still in use.");
                    } else {
                        System.out.println("Address is disactivated and not in use");
                    }

                    System.out.println("Address Line: " + address.getAddress() + "\n");

                    while (true) {
                        System.out.println("Update or delete address?\n");
                        System.out.println("1 - Update address");
                        System.out.println("2 - Delete address");
                        System.out.println("0 - Go back");
                        String inputEntry = sc.nextLine().trim();
                        if (inputEntry.equals("1")) {
                            updateAddress(customer, sc, address);
                        } else if (inputEntry.equals("2")) {
                            deleteAddress(customer, address);
                            break;
                        } else if (inputEntry.equals("0")) {
                            break;
                        } else {
                            System.out.println("Invalid input entered! Please enter again.\n");
                        }
                    }
                    break;
                }
            } catch (AddressNotFoundException ex) {
                System.out.println("Address Id not found.\n");
                sc.nextLine();
            } catch (InputMismatchException ex) {
                System.out.println("Wrong function input entered.\n");
                sc.nextLine();
            }
        }

    }

    public void updateAddress(Customer customer, Scanner sc, Address address) {

        System.out.println("*** Update address ***\n");

        while (true) {
            String addressLine;
            System.out.println("Enter new address details:");
            addressLine = sc.nextLine();
            if (addressLine.length() == 0) {
                System.out.println("Address cannot be empty!");
            } else {
                address.setAddress(addressLine);
                break;
            }
        }

        addressControllerRemote.updateAddress(address);
        System.out.println("Address successfully updated! \n");
        // System.out.println("New address Line: " + address.getAddress() + "\n");

    }

    public void deleteAddress(Customer customer, Address addressToRemove) {

        try {
            customer = customerControllerRemote.retrieveCustomerByCustomerId(customer.getCustomerId());
        } catch (CustomerNotFoundException ex) {
        }

        System.out.println("*** Delete Address ***\n");
        List<Address> addresses = customer.getAddressList();
        if (!addressToRemove.isIsDeliveryAddress()) {
            addressToRemove.setDisabled(true);
            addresses.remove(addressToRemove);
            customer.setAddressList(addresses);
            customerControllerRemote.updateCustomer(customer);
            addressControllerRemote.deleteAddress(addressToRemove.getAddressId());
            System.out.println("Address successfully removed from user.\n");
        } else {
            System.out.println("This address is being used as a delivery address!");
            System.out.println("Unable to delete this address \n");
        }
    }

    public void viewAllAddresses(Customer customer, Scanner sc) {

        System.out.println("*** View All Addresses ***\n");

        try {
            customer = customerControllerRemote.retrieveCustomerByCustomerId(customer.getCustomerId());
        } catch (CustomerNotFoundException ex) {
        }

        List<Address> addresses = customer.getAddressList();
        System.out.println("List of addresses:\n");
        int n = 1;
        for (Address address : addresses) {
            if (!address.isDisabled()) {
                System.out.print(n + ". " + address.getAddress() + " [ID: " + address.getAddressId() + "]");
                if (address.isIsDeliveryAddress()) {
                    System.out.println(" >> This address is used as a delivery address!");
                } else {
                    System.out.println();
                }
            }
            n++;
        }
        System.out.println();
    }

    private void creditOperation(Customer customer, Scanner sc) {
        Integer response = 0;

        while (true) {
            try {
                System.out.println("*** OAS Auction Client : Credit Operations ***\n");
                System.out.println("1: View Credit Balance");
                System.out.println("2: View Credit Transaction History");
                System.out.println("3: Purchase Credit Package");
                System.out.println("4: Go back\n");
                response = 0;

                System.out.print("> ");

                response = sc.nextInt();
                sc.nextLine();

                if (response == 1) {
                    viewCreditBalance(customer, sc);
                } else if (response == 2) {
                    viewCreditTransactionHistory(customer, sc);
                } else if (response == 3) {
                    purchaseCreditPackage(customer, sc);
                } else if (response == 4) {
                    return;
                } else {
                    System.out.println("Invalid function input entered!\n");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Invalid function input entered!\n");
                sc.nextLine();
            }
        }
    }

    public void viewCreditBalance(Customer customer, Scanner sc) {

        try {
            customer = customerControllerRemote.retrieveCustomerByCustomerId(customer.getCustomerId());
        } catch (CustomerNotFoundException ex) {
        }

        System.out.println("*** View Credit Balance ***\n");
        System.out.println("Customer's Credit Balance: " + customer.getAmtCredit() + "\n");
    }

    public void viewCreditTransactionHistory(Customer customer, Scanner sc) {

        System.out.println("*** View Credit Transaction History ***\n");

        try {
            customer = customerControllerRemote.retrieveCustomerByCustomerId(customer.getCustomerId());
        } catch (CustomerNotFoundException ex) {
        }

        List<CreditTransaction> creditTransactions = customer.getCreditTransactionList();
        if (creditTransactions.isEmpty()) {
            System.out.println("Customer has made no transactions with credit yet!\n");
        } else {
            int n = 1;
            System.out.println("List of Credit Transactions: \n");
            for (CreditTransaction creditTransaction : creditTransactions) {
                if (creditTransaction.getTransactionType().toString().equals("PURCHASE")) {
                    System.out.println(n + ") Purchase " + creditTransaction.getCreditPackage().getAmtCredit() + " Credits (Balance: +" + creditTransaction.getAmtCredit() + ")");
                } else if (creditTransaction.getTransactionType().toString().equals("BIDDING")) {
                    System.out.println(n + ") Bidding for " + creditTransaction.getAuctionListing().getItemName() + " (Balance: -" + creditTransaction.getAmtCredit() + ")");
                } else {
                    System.out.println(n + ") Outbidded Refund (Balance: +" + creditTransaction.getAmtCredit() + ")");
                }
                n++;
            }
            System.out.println("\n");
        }
    }

    public void purchaseCreditPackage(Customer customer, Scanner sc) {

        try {
            customer = customerControllerRemote.retrieveCustomerByCustomerId(customer.getCustomerId());
        } catch (CustomerNotFoundException ex) {
        }

        System.out.println("*** Purchase Credit Package ***\n");

        List<CreditPackage> creditPackages = creditPackageControllerRemote.retrieveAllCreditPackages();
        if (creditPackages.isEmpty()) {
            System.out.println("No credit packages available for purchase!\n");
        } else {
            int n = 1;
            System.out.println("List of credit packages available for purchase: \n");
            for (CreditPackage creditPackage : creditPackages) {
                System.out.println(n + ": " + creditPackage.getAmtCredit());
                n++;
            }
            System.out.println();

            while (true) {
                try {
                    System.out.print("Enter credit package amount to purchase: ");
                    Double inputAmt = sc.nextDouble();
                    System.out.println();
                    sc.nextLine();
                    try {
                        creditPackage = creditPackageControllerRemote.retrieveCreditPackageByAmtCredit(inputAmt);
                        customer.setAmtCredit(customer.getAmtCredit() + inputAmt);
                        customer.addCreditTransactionList(new CreditTransaction(TransactionTypeEnum.PURCHASE, inputAmt, customer, creditPackage));
                        creditPackage.setInUse(Boolean.TRUE);
                        customerControllerRemote.updateCustomer(customer);
                        System.out.println("Credit Package successfully purchased!");
                        System.out.println("New amount of credits: " + customer.getAmtCredit() + "\n");
                        break;
                    } //catch (InputMismatchException ex) {
                    // System.out.println("Invalid input entered. Please try again\n");
                    // sc.nextLine();
                    //}
                    catch (InvalidCreditPackageException ex) {
                        System.out.println(ex.getMessage());
                        sc.nextLine();
                    }

                } catch (InputMismatchException ex) {
                    System.out.println("Invalid input type entered! Please try again\n");
                    sc.nextLine();
                }
            }

        }
    }

    private void auctionListingOperation(Customer customer, Scanner sc) {

        String response;

        while (true) {
            System.out.println("*** OAS Auction Client : Auction Listing Operations ***\n");

            System.out.println("1: Browse All Auction Listings");
            System.out.println("2: View Auction Listing Details");
            System.out.println("3: Browse Won Auction Listings");
            System.out.println("4: Select Delivery Address for Won Auction Listing");
            System.out.println("5: Go back\n");

            //while (true) {
            System.out.print("> ");
            response = sc.nextLine().trim();

            if (response.equals("1")) {
                browseAllAuctionListings(customer, sc);
            } else if (response.equals("2")) {
                viewAuctionListingDetails(customer, sc);
            } else if (response.equals("3")) {
                browseWonAuctionListings(customer, sc);
            } else if (response.equals("4")) {
                selectDeliveryAddressForWonAuctionListing(customer, sc);
            } else if (response.equals("5")) {
                break;
            } else {
                System.out.println("Incorrect function type entered! Please entry \n");
            }
            //}
        }
    }

    private void browseAllAuctionListings(Customer customer, Scanner sc) {

        System.out.println("*** Browse All Auction Listings ***\n");

        List<AuctionListing> auctionListings = auctionListingControllerRemote.retrieveAllAuctionListings();
        int count = 0;
        if (!auctionListings.isEmpty()) {
            for (AuctionListing auctionListing : auctionListings) {
                if (auctionListing.getActive()) {
                    count++;
                    System.out.println("No. " + count + ") " + auctionListing.getItemName() + " [ListingId: " + auctionListing.getListingId() + "]");
                }
            }
            if (count == 0) {
                System.out.println("No Auction listing currently available\n");
            }
        } else {
            System.out.println("No Auction listing currently available\n");
        }
    }

    private void viewAuctionListingDetails(Customer customer, Scanner sc) {

        while (true) {
            try {
                System.out.println("Please enter ID of Auction Listing: (enter 0 to exit)\n");
                System.out.print("> ");
                Long auctionListingId = sc.nextLong();
                sc.nextLine();
                if (auctionListingId == 0) {
                    break;
                }
                AuctionListing auctionListing = auctionListingControllerRemote.retrieveAuctionListingById(auctionListingId);
                if (!auctionListing.getActive()) {
                    System.out.println("This auction has closed and is no longer active! \n");
                } else {
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
                        System.out.println("Make bid or Refresh Auction Listing Bids?\n");
                        System.out.println("1 - Make bid");
                        System.out.println("2 - Refresh Auction Listing Bid");
                        System.out.println("0 - Go Back\n");
                        System.out.print("> ");
                        String inputEntry = sc.nextLine().trim();
                        if (inputEntry.equals("1")) {
                            placeNewBid(customer, auctionListing, sc);
                            break;
                        } else if (inputEntry.equals("2")) {
                            refreshAuctionListingBids(auctionListing);
                            break;
                        } else if (inputEntry.equals("0")) {
                            break;
                        } else {
                            System.out.println("Invalid input entered! Please enter again.\n");
                        }
                    }
                    break;
                }
            } catch (AuctionListingNotFoundException ex) {
                sc.nextLine();
                System.out.println(ex.getMessage());
            } catch (InputMismatchException ex) {
                sc.nextLine();
                System.out.println("Invalid input entered! ID can only be numerical!");
                System.out.println("Please try again\n");
            }

        }

    }

    private void placeNewBid(Customer customer, AuctionListing auctionListing, Scanner sc) {

        try {
            customer = customerControllerRemote.retrieveCustomerByCustomerId(customer.getCustomerId());
        } catch (CustomerNotFoundException ex) {
        }

        double bidIncrement = 0;
        double currentPrice = 0;
        double newBidAmount;

        if (auctionListing.getCurrentBidAmt() == null) {
            currentPrice = auctionListing.getStartBidAmt();
        } else {
            currentPrice = auctionListing.getCurrentBidAmt();
        }

        if (currentPrice >= 5000) {
            bidIncrement = 100.0;
        } else if (currentPrice >= 2500) {
            bidIncrement = 50.0;
        } else if (currentPrice >= 1000) {
            bidIncrement = 25.00;
        } else if (currentPrice >= 500) {
            bidIncrement = 10.00;
        } else if (currentPrice >= 250) {
            bidIncrement = 5.00;
        } else if (currentPrice >= 100) {
            bidIncrement = 2.50;
        } else if (currentPrice >= 25) {
            bidIncrement = 1.00;
        } else if (currentPrice >= 5) {
            bidIncrement = 0.50;
        } else if (currentPrice >= 1) {
            bidIncrement = 0.25;
        } else if (currentPrice >= 0.00) {
            bidIncrement = 0.05;
        }
        newBidAmount = currentPrice + bidIncrement;

        System.out.println("Current bid to be added is: " + newBidAmount);
        System.out.println("Press Y OR N to confirm new bid: ");
        System.out.print("> ");
        String response = sc.nextLine();
        if (response.equals("Y")) {
            if (customer.getAmtCredit() < newBidAmount) {
                System.out.println("Insufficient credits available for bidding!");
                System.out.println("Bid not made!\n");
            } else {
                Bid newBid = auctionListingControllerRemote.makeBid(auctionListing, customer, newBidAmount);
                System.out.println("Bid successfully made!\n");
            }
        } else if (response.equals("N")) {
            System.out.println("Bid purchase cancelled! \n");
        }
    }

    private void refreshAuctionListingBids(AuctionListing auctionListing) {
        System.out.println("*** View all bids made ***\n");

        try {
            auctionListing = auctionListingControllerRemote.retrieveAuctionListingById(auctionListing.getListingId());
        } catch (AuctionListingNotFoundException ex) {
        }

        List<Bid> bids = auctionListing.getBidList();
        if (bids.isEmpty()) {
            System.out.println("No bids were made for this auction yet. \n");
        } else {
            System.out.println("List of bids made for this auction:");
            int n = 0;
            for (Bid bid : bids) {
                n++;
                System.out.println(n + ") " + bid.getCustomer().getUserName() + " Bid: " + bid.getBidAmt());
            }
            System.out.println("\n");
            System.out.println("Total Number of bids: " + n + "\n");
        }
    }

    private List<AuctionListing> browseWonAuctionListings(Customer customer, Scanner sc) {
        System.out.println("*** View all auction listings won ***\n");

        try {
            customer = customerControllerRemote.retrieveCustomerByCustomerId(customer.getCustomerId());
        } catch (CustomerNotFoundException ex) {
        }

        List<AuctionListing> listOfAuctionListingWon = customer.getAuctionListingWon();
        if (listOfAuctionListingWon.isEmpty()) {
            System.out.println("User has won no auction listings :(\n");
        } else {
            System.out.println("All Auction Listings the user has won are:\n");
            int count = 0;
            for (AuctionListing auctionListing : listOfAuctionListingWon) {
                count++;
                System.out.println(count + ". " + auctionListing.getItemName() + " [Auction Listing ID: " + auctionListing.getListingId() + "]");
                // System.out.println("Bid made: " + auctionListing.getBidList().get(auctionListing.getBidList().size()-1).getBidAmt());
            }
            System.out.println();
        }
        return listOfAuctionListingWon;
    }

    private void selectDeliveryAddressForWonAuctionListing(Customer customer, Scanner sc) {

        System.out.println("*** Select Delivery Address for Won Auction Listings ***\n");

        try {
            try {
                customer = customerControllerRemote.retrieveCustomerByCustomerId(customer.getCustomerId());
            } catch (CustomerNotFoundException ex) {
            }

            //List<AuctionListing> listOfAuctionListingsWon = browseWonAuctionListings(customer, sc);
            List<AuctionListing> listOfAuctionListingsWon = customer.getAuctionListingWon();
            if (listOfAuctionListingsWon.isEmpty()) {
                System.out.println("User has won no auction listings :(\n");
            } else {
                System.out.println("List of Auction Listings the user has won that need a delivery address: \n");
                int count = 0;
                Boolean checker = false;
                for (AuctionListing auctionListing : listOfAuctionListingsWon) {
                    if (auctionListing.getDeliveryAddress() == null) {
                        count++;
                        checker = true;
                        System.out.println(count + ". " + auctionListing.getItemName() + " [Auction Listing ID: " + auctionListing.getListingId() + "]");
                        // System.out.println("Bid made: " + auctionListing.getBidList().get(auctionListing.getBidList().size()-1).getBidAmt());
                    }
                }
                if (!checker) {
                    System.out.println("No auction listing won needs to be assigned!");
                }
                System.out.println();
            }

            if (listOfAuctionListingsWon.isEmpty()) {
                System.out.println("User has won no auction listing!");
                System.out.println("No delivery address may be selected.\n");
                return;
            }

            System.out.println("Please enter Listing ID of Auction Listing to assign delivery address: \n");
            Long auctionListingId = sc.nextLong();
            AuctionListing auctionListing = auctionListingControllerRemote.retrieveAuctionListingById(auctionListingId);
            sc.nextLine();

            if (!listOfAuctionListingsWon.contains(auctionListing)) {
                System.out.println("Invalid auction listing selected.\n");
                return;
            } else {
                System.out.println("Valid auction listing ID entered!\n");
                System.out.println("Please enter the address ID of the address to be used as delivery address: \n");
                viewAllAddresses(customer, sc);
                System.out.print(">");
                Long addressId = sc.nextLong();
                sc.nextLine();

                try {
                    Address addressSelected = addressControllerRemote.retrieveAddressByAddressId(addressId);
                    if (!addressSelected.getCustomer().getIdentificationNumber().equals(customer.getIdentificationNumber())) {
                        System.out.println("Address is not registered under the user!\n");
                        return;
                    } else if (addressSelected.isDisabled()) {
                        System.out.println("Address is disabled!\n");
                        return;
                    } else {
                        //need to add in delivery address as attribute in auction listing and edit method accordingly
                        if (addressSelected.isIsDeliveryAddress()) {
                            System.out.println("This address is already used as a delivery address!");
                            System.out.println("Please choose another address\n");
                        } else {
                            auctionListing.setDeliveryAddress(addressSelected);
                            addressSelected.setIsDeliveryAddress(true);
                            auctionListingControllerRemote.updateAuctionListing(auctionListing);
                            addressControllerRemote.updateAddress(addressSelected);
                            System.out.println("Delivery address set! Thank you for using our service :>\n");
                        }
                    }
                } catch (AddressNotFoundException ex) {
                    System.out.println("Address ID not found.\n");
                }

            }
        } catch (AuctionListingNotFoundException ex) {
            sc.nextLine();
            System.out.println("Auction Lisiting does not exist\n");
        }

    }
}
