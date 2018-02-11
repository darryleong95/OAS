package oasadministrationpanel;

import util.Enum.AccountTypeEnum;
import entity.Employee;

import ejb.session.stateless.EmployeeControllerRemote;
import ejb.session.stateless.AuctionListingControllerRemote;
import ejb.session.stateless.BidControllerRemote;
import ejb.session.stateless.CreditPackageControllerRemote;
import ejb.session.stateless.CreditTransactionControllerRemote;
import ejb.session.stateless.CustomerControllerRemote;
import java.util.InputMismatchException;
import java.util.Scanner;
import util.Exception.AuctionListingNotFoundException;
import util.Exception.InvalidAccessRightException;

import util.Exception.InvalidLoginCredentialException;

/**
 *
 * @author KEKSTER
 */
public class MainApp {

    private EmployeeControllerRemote employeeControllerRemote;
    private AuctionListingControllerRemote auctionListingControllerRemote;
    private BidControllerRemote bidControllerRemote;
    private CreditPackageControllerRemote creditPackageControllerRemote;
    private CreditTransactionControllerRemote creditTransactionControllerRemote;
    private CustomerControllerRemote customerControllerRemote;

    private Employee employee;
    private AdministrationModule administrationModule;
    private SalesModule salesModule;
    private FinanceModule financeModule;

    public MainApp() {

    }

    public MainApp(CustomerControllerRemote customerControllerRemote, CreditTransactionControllerRemote creditTransactionControllerRemote, CreditPackageControllerRemote creditPackageControllerRemote, BidControllerRemote bidControllerRemote, EmployeeControllerRemote employeeControllerRemote, AuctionListingControllerRemote auctionListingControllerRemote) throws InputMismatchException {
        this();
        this.employeeControllerRemote = employeeControllerRemote;
        this.auctionListingControllerRemote = auctionListingControllerRemote;
        this.customerControllerRemote = customerControllerRemote;
        this.creditPackageControllerRemote = creditPackageControllerRemote;
        this.creditTransactionControllerRemote = creditTransactionControllerRemote;
        this.bidControllerRemote = bidControllerRemote;
    }

    public void runApp() throws InvalidAccessRightException, AuctionListingNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;
        while (true) {
            try{
                System.out.println("*** Welcome to OSA Administration Panel ***\n");
                System.out.println("1: Login");
                System.out.println("2: Exit\n");
                response = 0;

                while (response < 1 || response > 2) {
                    System.out.print("> ");

                    response = scanner.nextInt();

                    if (response == 1) {
                        try {
                            doLogin();
                            financeModule = new FinanceModule(employeeControllerRemote, auctionListingControllerRemote, bidControllerRemote, creditPackageControllerRemote, creditTransactionControllerRemote, customerControllerRemote, employee);
                            salesModule = new SalesModule(employeeControllerRemote, auctionListingControllerRemote, bidControllerRemote, creditPackageControllerRemote, creditTransactionControllerRemote, customerControllerRemote, employee);
                            administrationModule = new AdministrationModule(employeeControllerRemote, auctionListingControllerRemote, bidControllerRemote, creditPackageControllerRemote, creditTransactionControllerRemote, customerControllerRemote, employee);
                            menuMain();
                        } catch (InvalidLoginCredentialException ex) {
                            System.out.println("Invalid input, please try again");
                        }
                    } else if (response == 2) {
                        break;
                    } else {
                        System.out.println("Invalid option, please try again!\n");
                    }
                }
                if (response == 2) {
                    break;
                }
            }
            catch(InputMismatchException ex){
                System.out.println("Invalid input, please try again");
                scanner.nextLine();
            }   
        }
    }

    private void doLogin() throws InvalidLoginCredentialException {
        Scanner scanner = new Scanner(System.in);
        String username = "";
        String password = "";

        System.out.println("*** OAS Administration Panel :: Login ***\n");
        while(true){
            System.out.print("Enter username: ");
            username = scanner.nextLine().trim();
            if(username.isEmpty()){
                System.out.println("Username cannot be empty, please try again");
            }
            else break;
        }
        while(true){
            System.out.print("Enter password: ");
            password = scanner.nextLine().trim();
            if(password.isEmpty()){
                System.out.println("Password cannot be empty, please try again");
            }
            else break;
        }
        if (username.length() > 0 && password.length() > 0) {
            try {
                //employee log in not done
                employee = employeeControllerRemote.employeeLogin(username, password);
                System.out.println("Login successful!\n");
            } catch (InvalidLoginCredentialException ex) {
                System.out.println("Invalid login credential: " + ex.getMessage() + "\n");

                throw new InvalidLoginCredentialException();
            }
        } else {
            System.out.println("Invalid login credential!");
        }
    }

    private void menuMain() throws InvalidAccessRightException, AuctionListingNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;

        while (true) {
            try{
                System.out.println("*** OAS Administration Panel ***\n");
                System.out.println("You are login as " + employee.getFirstName() + " " + employee.getLastName() + " with " + employee.getAccountTypeEnum().toString() + " rights\n");
                String type;
                if (employee.getAccountTypeEnum() == AccountTypeEnum.ADMIN) {
                    type = "Administration";
                } else if (employee.getAccountTypeEnum() == AccountTypeEnum.FINANCE) {
                    type = "Finance";
                } else {
                    type = "Sales";
                }
                System.out.println("1: " + type + " operations");
                System.out.println("2: Log out");
                System.out.println("3: Change password");
                System.out.print("> ");
                response = scanner.nextInt();
                scanner.nextLine();
                if (response == 1) {
                    if (employee.getAccountTypeEnum() == AccountTypeEnum.ADMIN) {
                        administrationModule.menuSystemAdministration();
                    } else if (employee.getAccountTypeEnum() == AccountTypeEnum.FINANCE) {
                        financeModule.menuFinanceAdministration();
                    } else {
                        salesModule.menuSalesAdministration();
                    }
                } else if (response == 2) {
                    break;
                } else if (response == 3) {
                    while (true) {
                        //Correct current password must be provided
                        System.out.print("Please enter current password  : ");
                        String currentPassword = scanner.nextLine();
                        if (!currentPassword.equals(employee.getPassWord()) || currentPassword.length() <= 0) {
                            System.out.println("Invalid password entered!");
                            break; //if wrong current password entered, exit from use case
                        } else {
                            //else asks for new password
                            System.out.print("Please enter new password  : ");
                            String newPassword = scanner.nextLine();
                            while (newPassword.equals(employee.getPassWord())) {
                                System.out.print("Please enter a password that is different from initial password!");
                                newPassword = scanner.nextLine();
                            } //forces users to enter a password diff from old password

                            //checks through new password given to ensure correct input
                            System.out.print("Please confirm new password: ");
                            String confirmation = scanner.nextLine();
                            if (newPassword.equals(confirmation)) {
                                employee.setPassWord(newPassword);
                                employeeControllerRemote.updateEmployee(employee);

                                System.out.println("Password has been changed successfully");
                                System.out.println("Press enter to continue \n");

                                scanner.nextLine();

                                break;
                            } else {
                                System.out.println("Sorry, your passwords did not match, please try again");
                            }
                        }
                    }
                } else {
                    System.out.println("You have entered an invalid input, please try again");
                    System.out.println();
                }
            }
            catch(InputMismatchException ex){
                System.out.println(ex + ": Try again.");
                scanner.nextLine();
            }
        }
    }
}
