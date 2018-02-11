package oasauctionclient;

import entity.Customer;
import ejb.session.stateless.BidControllerRemote;
import ejb.session.stateless.CreditPackageControllerRemote;
import ejb.session.stateless.CreditTransactionControllerRemote;
import ejb.session.stateless.AddressControllerRemote;
import ejb.session.stateless.CustomerControllerRemote;
import ejb.session.stateless.AuctionListingControllerRemote;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import util.Exception.CustomerNotFoundException;
import util.Exception.InvalidLoginCredentialException;

public class MainApp {

    private BidControllerRemote bidControllerRemote;
    private CreditPackageControllerRemote creditPackageControllerRemote;
    private CreditTransactionControllerRemote creditTransactionControllerRemote;
    private AddressControllerRemote addressControllerRemote;
    private CustomerControllerRemote customerControllerRemote;
    private AuctionListingControllerRemote auctionListingControllerRemote;

    private Customer customer;
    Scanner sc = new Scanner(System.in);

    private CustomerModule customerModule;

    public MainApp() {
    }

    public MainApp(BidControllerRemote bidControllerRemote, CreditPackageControllerRemote creditPackageControllerRemote, CreditTransactionControllerRemote creditTransactionControllerRemote, AddressControllerRemote addressControllerRemote, CustomerControllerRemote customerControllerRemote, AuctionListingControllerRemote auctionListingControllerRemote) {
        this.bidControllerRemote = bidControllerRemote;
        this.creditPackageControllerRemote = creditPackageControllerRemote;
        this.creditTransactionControllerRemote = creditTransactionControllerRemote;
        this.addressControllerRemote = addressControllerRemote;
        this.customerControllerRemote = customerControllerRemote;
        this.auctionListingControllerRemote = auctionListingControllerRemote;
    }

    public void run() {

        String response;

        // while (true) {
        while (true) {
            System.out.println("*** OAS Auction Client ***\n");
            System.out.println("1: Login");
            System.out.println("2: Register");
            System.out.println("3: Exit\n");
            System.out.print("> ");
            response = sc.nextLine();
            System.out.println();
            if (response.equals("1")) {
                try {
                    Customer customer = doLogin();
                    customerModule = new CustomerModule(bidControllerRemote, creditPackageControllerRemote, creditTransactionControllerRemote, addressControllerRemote, customerControllerRemote, auctionListingControllerRemote, customer);
                    customerModule.menuMain();
                } catch (InvalidLoginCredentialException e) {
                }
            } else if (response.equals("2")) {
                Customer customer = register();
                customerModule = new CustomerModule(bidControllerRemote, creditPackageControllerRemote, creditTransactionControllerRemote, addressControllerRemote, customerControllerRemote, auctionListingControllerRemote, customer);
                customerModule.menuMain();
            } else if (response.equals("3")) {
                break;
            } else {
                System.out.println("Invalid option, please try again!\n");
            }
        }
        //  break;
        //}
    }

    private Customer doLogin() throws InvalidLoginCredentialException {
        String username;
        String password;
        System.out.println("*** OAS Auction Client :: Login ***\n");
        try {
            System.out.print("Enter username: ");
            username = sc.nextLine().trim();
            System.out.print("Enter password: ");
            password = sc.nextLine().trim();
            System.out.println();

            if (username.length() > 0 && password.length() > 0) {
                customer = customerControllerRemote.customerLogin(username, password);
                System.out.println("SUCCESS: Login successful!\n");
                return customer;
            } else {
                System.out.println("ERROR: Invalid login credentials!\n");
                throw new InvalidLoginCredentialException();
            }
        } catch (InvalidLoginCredentialException ex) {
            System.out.println("ERROR: Invalid login credential: " + ex.getMessage() + "\n");
            throw new InvalidLoginCredentialException();
        }
    }

    private Customer register() {

        System.out.println("*** OAS Auction Client :: Register ***\n");

        Customer customer = new Customer();

        String username;
        String password;
        String email;
        int mobileNumber;
        String identificationNumber;
        String firstName;
        String lastName;

        while (true) {
            System.out.print("Enter username: ");
            username = sc.nextLine().trim();
            if (username.length() == 0) {
                System.out.println("Error: Username cannot be empty!");
            }

            Customer checker = null;
            try {
                checker = customerControllerRemote.retrieveCustomerByUserName(username);
            } catch (CustomerNotFoundException ex) {
            }

            if (checker != null) {
                System.out.println("Error: Username is already used!");
            } else {
                customer.setUserName(username);
                break;
            }
        }

        while (true) {
            System.out.print("Enter password: ");
            password = sc.nextLine().trim();
            if (password.length() <= 7) {
                System.out.println("Error: Password field must have at least 8 characters");
            } else {
                customer.setPassWord(password);
                break;
            }
        }

        while (true) {
            System.out.print("Enter email: ");
            email = sc.nextLine().trim();
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
                    break;
                }
            }
        }

        while (true) {
            System.out.print("Enter mobile number: ");
            try {
                mobileNumber = sc.nextInt();
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
                        break;
                    }
                }
            } catch (InputMismatchException Ex) {
                sc.nextLine();
                System.out.println("Invalid input entered! Only numerals allowed\n");
            }
        }

        while (true) {
            System.out.print("Enter identification number: ");
            identificationNumber = sc.nextLine().trim();
            if (identificationNumber.length() != 9) {
                System.out.println("Error: Identification number must be 9 characters!");
            } else {

                Customer checker = null;
                try {
                    checker = customerControllerRemote.retrieveCustomerByIdentificationNumber(identificationNumber);
                } catch (CustomerNotFoundException ex) {
                }

                if (checker != null) {
                    System.out.println("Error: Identification Number is already used!");
                } else {
                    customer.setIdentificationNumber(identificationNumber);
                    break;
                }
            }
        }

        while (true) {
            System.out.print("Enter first name: ");
            firstName = sc.nextLine().trim();
            if (firstName.length() == 0) {
                System.out.println("Error: First name cannot be empty");
            } else {
                customer.setFirstName(firstName);
                break;
            }

        }

        while (true) {
            System.out.print("Enter last name: ");
            lastName = sc.nextLine().trim();
            if (lastName.length() == 0) {
                System.out.println("Error: Last name cannot be empty!");
            } else {
                customer.setLastName(lastName);
                break;
            }
        }

        customer = customerControllerRemote.persistCustomer(customer);

        System.out.println("Customer successfully registered! Username: " + customer.getUserName() + " customerId: " + customer.getCustomerId());
        System.out.println("Proceeding to main menu \n");
        return customer;
    }

    private CustomerControllerRemote lookupCustomerControllerRemote() {
        try {
            Context c = new InitialContext();
            return (CustomerControllerRemote) c.lookup("java:comp/env/CustomerControllerRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
