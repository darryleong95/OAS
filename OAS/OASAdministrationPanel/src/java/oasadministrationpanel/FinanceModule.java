/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oasadministrationpanel;

import util.Enum.AccountTypeEnum;
import ejb.session.stateless.AuctionListingControllerRemote;
import ejb.session.stateless.BidControllerRemote;
import ejb.session.stateless.CreditPackageControllerRemote;
import ejb.session.stateless.CreditTransactionControllerRemote;
import ejb.session.stateless.CustomerControllerRemote;
import ejb.session.stateless.EmployeeControllerRemote;
import entity.CreditPackage;
import entity.Employee;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javax.security.auth.login.CredentialNotFoundException;
import util.Exception.InvalidAccessRightException;
import util.Exception.InvalidCreditPackageException;

/**
 *
 * @author User
 */
public class FinanceModule {

    private EmployeeControllerRemote employeeControllerRemote;
    private AuctionListingControllerRemote auctionListingControllerRemote;
    private BidControllerRemote bidControllerRemote;
    private CreditPackageControllerRemote creditPackageControllerRemote;
    private CreditTransactionControllerRemote creditTransactionControllerRemote;
    private CustomerControllerRemote customerControllerRemote;

    private Employee employeePassedThrough;

    public FinanceModule() {
    }

    public FinanceModule(EmployeeControllerRemote employeeControllerRemote, AuctionListingControllerRemote auctionListingControllerRemote, BidControllerRemote bidControllerRemote, CreditPackageControllerRemote creditPackageControllerRemote, CreditTransactionControllerRemote creditTransactionControllerRemote, CustomerControllerRemote customerControllerRemote, Employee employeePassedThrough) {
        this.employeeControllerRemote = employeeControllerRemote;
        this.auctionListingControllerRemote = auctionListingControllerRemote;
        this.bidControllerRemote = bidControllerRemote;
        this.creditPackageControllerRemote = creditPackageControllerRemote;
        this.creditTransactionControllerRemote = creditTransactionControllerRemote;
        this.customerControllerRemote = customerControllerRemote;
        this.employeePassedThrough = employeePassedThrough;
    }

    public void menuFinanceAdministration() throws InvalidAccessRightException {
        if (employeePassedThrough.getAccountTypeEnum() != AccountTypeEnum.FINANCE) {
            throw new InvalidAccessRightException("You don't have FINANCE ADMIN rights to access the Finance Administration module.");
        }
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;
        while (true) {
            try {
                System.out.println("*** OAS Administration Panel System :: Finance Administration ***\n");
                System.out.println("1: Create New Credit Package");
                System.out.println("2: View Credit Package details");
                System.out.println("3: View all Credit Package");
                System.out.println("4: Back\n");
                response = 0;
                OUTER:
                while (response < 1 || response > 6) {
                    System.out.print("> ");
                    response = scanner.nextInt();
                    switch (response) {
                        case 1:
                            doCreateNewCreditPackage(scanner);
                            break;
                        case 2:
                            doViewCreditPackageDetails(scanner);
                            break;
                        case 3:
                            doViewAllCreditPackage();
                            break;
                        case 4:
                            break OUTER;
                        default:
                            System.out.println("Invalid option, please try again!\n");
                            break;
                    }
                }
                if (response == 4) {
                    break;
                }
            } catch (InputMismatchException ex) {
                System.out.println(ex + " Try again.");
            }
        }
    }

    public void doCreateNewCreditPackage(Scanner scanner) {
        while (true) {
            try {

                System.out.println("*** POS System :: System Administration :: Create New Credit Package ***\n");
                System.out.print("Enter amount to credit: ");
                Double newCreditPackageAmount = scanner.nextDouble();
                scanner.nextLine();
                if (newCreditPackageAmount <= 0) {
                    System.out.println("Invalid Amount specified, Credit package value cannot be below 0");
                }
                else{
                    try{
                        CreditPackage creditPackage_found = creditPackageControllerRemote.retrieveCreditPackageByAmtCredit(newCreditPackageAmount);
                        System.out.println("Credit package with amount specified already exists, please try again");
                    }
                    catch(InvalidCreditPackageException ex){
                        CreditPackage newCreditPackage = new CreditPackage();
                        newCreditPackage.setAmtCredit(newCreditPackageAmount);
                        creditPackageControllerRemote.createNewCreditPackage(newCreditPackage);
                        System.out.println("New Credit Package has been created");
                        System.out.println();
                        break; 
                    }
                }
            } 
            catch (InputMismatchException ex) {
                System.out.println(ex + ": Try again.");
                scanner.nextLine();
                System.out.println();
            }
        }
    }

    public void doViewCreditPackageDetails(Scanner scanner) {
        Outer:
        while (true) {
            try {
                System.out.print("Enter Credit Package Id (Enter 0 to break): ");
                Long option = scanner.nextLong();
                scanner.nextLine();
                
                if(option == 0){
                    return;
                }
                
                CreditPackage creditPackage = creditPackageControllerRemote.retrieveByCreditPackageId(option);
                System.out.println("Credit Package Id    : " + creditPackage.getCreditPackageId());
                System.out.println("Credit amount        : " + creditPackage.getAmtCredit());
                while (true) {
                    System.out.println("1: Update Credit Package");
                    System.out.println("2: Delete Credit Package");
                    System.out.println("3: Go back");
                    System.out.print("> ");
                    int x = scanner.nextInt();
                    switch (x) {
                        case 1:
                            doUpdateCreditPackage(creditPackage, scanner);
                            break Outer;
                        case 2:
                            doDeleteCreditPackage(creditPackage, scanner);
                            break Outer;
                        case 3:
                            break Outer;
                        default:
                            System.out.println("Invalid input, please try again");
                            break;
                    }
                }
            } catch (InvalidCreditPackageException ex) {
                System.out.println("Credit package does not exist!");
            } catch (InputMismatchException ex) {
                System.out.println("Input mismatch exception: Try again.");
                scanner.nextLine();
            }
        }
    }

    public void doUpdateCreditPackage(CreditPackage creditPackage, Scanner scanner) {
        while(true){
            System.out.print("Enter Credit amount     : ");
            Double newAmt = scanner.nextDouble();
            scanner.nextLine();
            if(newAmt <= 0){
                System.out.println("Invalid Amount specified, Credit package value cannot be below 0");
            }
            else{
                try{
                    CreditPackage creditPackage_found = creditPackageControllerRemote.retrieveCreditPackageByAmtCredit(newAmt);
                    System.out.println("Credit package with amount specified already exists, please try again");
                }
                catch(InvalidCreditPackageException ex){
                    creditPackage.setAmtCredit(newAmt);
                    creditPackageControllerRemote.updateCreditPackage(creditPackage);
                    System.out.println("Credit package with ID: " + creditPackage.getCreditPackageId() + " has been updated with a new credit amount of " + creditPackage.getAmtCredit());
                    System.out.println();
                    break;
                }
            }
        }
    }

    public void doDeleteCreditPackage(CreditPackage creditPackage, Scanner scanner) {
        if (creditPackage.getInUse()){
            System.out.println("Credit Package is in used. Unable to delete!");
            return;
        }
        
        try {
            Long Id = creditPackage.getCreditPackageId();
            creditPackageControllerRemote.deleteCreditPackage(Id);
            System.out.println("Credit package has successfully been deleted");
        } catch (InvalidCreditPackageException ex) {
            System.out.println("Credit package does not exist!");
        }
    }

    public void doViewAllCreditPackage() {
        List<CreditPackage> creditPackageList = creditPackageControllerRemote.retrieveAllCreditPackages();
        for (int i = 0; i < creditPackageList.size(); i++) {
            System.out.println("Credit Package Id: " + creditPackageList.get(i).getCreditPackageId());
            System.out.println("Credit amount    : " + creditPackageList.get(i).getAmtCredit());
            System.out.println();
        }
    }
}
