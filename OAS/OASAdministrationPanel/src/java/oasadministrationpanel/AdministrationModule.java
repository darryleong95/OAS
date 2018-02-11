package oasadministrationpanel;

import util.Enum.AccountTypeEnum;
import static util.Enum.AccountTypeEnum.ADMIN;
import static util.Enum.AccountTypeEnum.FINANCE;
import static util.Enum.AccountTypeEnum.SALES;
import ejb.session.stateless.AuctionListingControllerRemote;
import ejb.session.stateless.BidControllerRemote;
import ejb.session.stateless.CreditPackageControllerRemote;
import ejb.session.stateless.CreditTransactionControllerRemote;
import ejb.session.stateless.CustomerControllerRemote;
import ejb.session.stateless.EmployeeControllerRemote;
import entity.Employee;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import util.Exception.EmployeeNotFoundException;
import util.Exception.InvalidAccessRightException;

public class AdministrationModule {

    private EmployeeControllerRemote employeeControllerRemote;
    private AuctionListingControllerRemote auctionListingControllerRemote;
    private BidControllerRemote bidControllerRemote;
    private CreditPackageControllerRemote creditPackageControllerRemote;
    private CreditTransactionControllerRemote creditTransactionControllerRemote;
    private CustomerControllerRemote customerControllerRemote;
    
    private Employee employeePassedThrough;

    
    public AdministrationModule(){
        
    }
    
    public AdministrationModule(EmployeeControllerRemote employeeControllerRemote, AuctionListingControllerRemote auctionListingControllerRemote, BidControllerRemote bidControllerRemote, CreditPackageControllerRemote creditPackageControllerRemote, CreditTransactionControllerRemote creditTransactionControllerRemote, CustomerControllerRemote customerControllerRemote, Employee employeePassedThrough) {
        this.employeeControllerRemote = employeeControllerRemote;
        this.auctionListingControllerRemote = auctionListingControllerRemote;
        this.bidControllerRemote = bidControllerRemote;
        this.creditPackageControllerRemote = creditPackageControllerRemote;
        this.creditTransactionControllerRemote = creditTransactionControllerRemote;
        this.customerControllerRemote = customerControllerRemote;
        this.employeePassedThrough = employeePassedThrough;
    }
    
    public void menuSystemAdministration() throws InvalidAccessRightException {
        if(employeePassedThrough.getAccountTypeEnum()!= AccountTypeEnum.ADMIN)
        {
            throw new InvalidAccessRightException("You don't have SYSTEM ADMINISTRATOR rights to access the system administration module.");
        }
        
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;
        boolean flag = true;
        Outer:
        while(true)
        {
            try{
                System.out.println("*** OAS Administration Panel System :: System Administration ***\n");
                System.out.println("1: Create New Employee");
                System.out.println("2: View Employee details");
                System.out.println("3: View all Employees");
                System.out.println("4: Back\n");
                response = 0;
                while (response < 1 || response > 4) {
                    System.out.print("> ");
                    response = scanner.nextInt();
                    switch (response) {
                        case 1:
                            doCreateNewEmployee();
                            break;
                        case 2:
                            doViewEmployeeDetails();
                            break;
                        case 3:
                            doViewAllEmployee();
                            break;
                        case 4:
                            break Outer;
                        default:
                            System.out.println("Invalid option, please try again!\n");
                            break;                
                    }
                }
            }
            catch(InputMismatchException ex){
                System.out.println(ex + ": Try again.");
                scanner.nextLine();
            }
        }
    }
    
    private void doCreateNewEmployee(){
        Scanner scanner = new Scanner(System.in);
        try{
            Employee newEmployee = new Employee();
            System.out.println("*** POS System :: System Administration :: Create New Employee ***\n");
            
            while(true){
                System.out.print("Enter First Name                                                       : ");
                String firstName = scanner.nextLine().trim();
                if(firstName.isEmpty()){
                    System.out.println("First name cannot be empty, please try again");
                }
                else{
                    newEmployee.setFirstName(firstName);
                    break;
                }  
            }
            while(true){
                System.out.print("Enter Last Name                                                        : ");
                String lastName = scanner.nextLine().trim();
                if(lastName.isEmpty()){
                    System.out.println("Last name cannot be empty, please try again");
                }
                else{
                    newEmployee.setLastName(lastName);
                    break;
                }
            }
            while(true){
                System.out.print("Enter Identification number                                            : ");
                String identificaiton_number = scanner.nextLine().trim();
                if(identificaiton_number.isEmpty()){
                    System.out.println("Identification number cannot be empty, please try again");
                }
                else{
                    try{
                        Employee employee_found = employeeControllerRemote.retrieveEmployeeByIdentificationNumber(identificaiton_number);
                        System.out.println("Identification number already in use, please try again.");
                    }
                    catch(EmployeeNotFoundException ex){
                        newEmployee.setIdentificationNumber(identificaiton_number);
                        break;
                    }
                }      
            }
            while(true)
            {
                System.out.print("Select Access Right (1: System Admin, 2: Finance Staff, 3: Sales Staff): ");
                Integer accessRightInt = scanner.nextInt();
                scanner.nextLine();
                if(accessRightInt >= 1 && accessRightInt <= 3)
                {
                    newEmployee.setAccountTypeEnum(AccountTypeEnum.values()[accessRightInt-1]);
                    break;
                }
                else
                {
                    System.out.println("Invalid option, please try again!\n");
                }
            }
            while(true){
                System.out.print("Enter Username                                                         : ");
                String userName = scanner.nextLine().trim();
                if(userName.isEmpty()){
                    System.out.println("Username cannot be empty, please try again");
                }
                else{
                    try{
                        Employee employee_found = employeeControllerRemote.retrieveEmployeeByUserName(userName);
                        System.out.println("Username already exists, please try again");
                    }
                    catch(EmployeeNotFoundException ex){
                        newEmployee.setUserName(userName);
                        break;
                    }
                }   
            }
            while(true){ 
                System.out.print("Enter Password                                                         : ");
                String password = scanner.nextLine().trim();
                if(password.isEmpty()){
                    System.out.println("Password cannot be empty, please try again");
                }
                else {
                    newEmployee.setPassWord(password);
                    break;
                }
            }

            newEmployee = employeeControllerRemote.createNewEmployee(newEmployee);
            System.out.println("New " + newEmployee.getAccountTypeEnum() + " Employee created");
            System.out.println();
        }
        catch(InputMismatchException ex){
            System.out.println(ex + ": Try again.");
        }
    }
    
    private void doViewEmployeeDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Username of Employee you want to view details of: ");
        try{
            Employee employee = employeeControllerRemote.retrieveEmployeeByUserName(scanner.nextLine().trim());
            //System.out.println("Employee Id: " + employee.getEmployeeId()+ "\nUserName: " + employee.getUserName() + "\nPassword: " + employee.getPassWord()+ "\nAccount Type: " + employee.getAccountTypeEnum()+ "\nFirst Name: " + employee.getFirstName() + "\nLast Name: " + employee.getLastName() + "\nIdentification Number: " + employee.getIdentificationNumber() + "\nDate of Birth: " + employee.getDateOfBirth().toString());
            System.out.println("Employee Name                                       : " + employee.getFirstName() + " " + employee.getLastName());
            System.out.println("Employee ID                                         : " + employee.getEmployeeId());
            System.out.println("Employee Identification Number                      : " + employee.getIdentificationNumber());
            System.out.println("Account type                                        : " + employee.getAccountTypeEnum());
            System.out.println();
            Outer:
            while(true){
                System.out.println("1: Update Employee information");
                System.out.println("2: Delete Employee record");
                System.out.println("3: Go back");
                System.out.print("> ");
                int x = scanner.nextInt();
                scanner.nextLine();
                switch (x) {
                    case 1:
                        doUpdateEmployee(employee);
                        break;
                    case 2:
                        doDeleteEmployee(employee);
                        break Outer;
                    case 3:
                        break Outer;
                    default:
                        System.out.println("Invalid input, please try again.");
                        break;
                }
            }
        }
        catch(EmployeeNotFoundException ex)
        {
            System.out.println("An error has occurred while retrieving Employee: " + ex.getMessage() + "\n");
        }
        catch(InputMismatchException ex){
            System.out.println(ex + ": Try again.");
        }
        
    }
    
    private void doUpdateEmployee(Employee employee) {
        Scanner scanner = new Scanner(System.in);
        String choice;
        System.out.print("Click Y to update username or any other character otherwise          : ");
        choice = scanner.nextLine();
        if(choice.equals("Y"))
        {
            while(true){
                System.out.println("Current Username                                                     : " + employee.getUserName());
                System.out.print("Enter new Username                                                   : ");
                String newUserName = scanner.nextLine().trim();
                if(newUserName.isEmpty()){
                    System.out.println("Username cannot be empty, please try again");
                }
                else{
                    try{
                        Employee employee_found = employeeControllerRemote.retrieveEmployeeByUserName(newUserName);
                        System.out.println("Username already exists, please try again");
                    }
                    catch(EmployeeNotFoundException ex){
                        employee.setUserName(newUserName);
                        break;
                    }
                }  
            }
        }
        System.out.print("Click Y to update password or any other character otherwise          : ");
        choice = scanner.nextLine();
        if(choice.equals("Y"))
        {
            while(true){
                System.out.println("Current Password                                                     : " + employee.getPassWord());
                System.out.print("Enter new Password                                                   : ");
                String newPassword = scanner.nextLine();
                if(newPassword.isEmpty()){
                    System.out.println("Password cannot be empty, please try again");
                }
                else{
                    employee.setPassWord(newPassword);
                    break;
                }
            }
        }
        System.out.print("Click Y to update account type or any other character otherwise      : ");
        choice = scanner.nextLine();
        if(choice.equals("Y"))
        {
            System.out.println("Current Account type                                                 : " + employee.getAccountTypeEnum());
            System.out.print("Enter 1 for FINANCE, 2 for ADMIN, 3 for SALES                        : ");
            Integer option = scanner.nextInt();
            if(option == 1)
            {
                employee.setAccountTypeEnum(FINANCE);
            }
            else if(option == 2)
            {
                employee.setAccountTypeEnum(ADMIN);
            }
            else if(option == 3)
            {
                employee.setAccountTypeEnum(SALES);
            }
            scanner.nextLine();
        }
        System.out.print("Click Y to update first name or any other character otherwise        : ");
        choice = scanner.nextLine();
        if(choice.equals("Y"))
        {
            while(true){
                System.out.println("Current first name                                                   : " + employee.getFirstName());
                System.out.print("Enter new first name                                                 : ");
                String firstName = scanner.nextLine().trim();
                if(firstName.isEmpty()){
                    System.out.println("First name cannot be empty, please try again");
                }
                else{
                    employee.setFirstName(firstName);
                    break;
                }
            }                
        }
        System.out.print("Click Y to update last name or any other character otherwise         : ");
        choice = scanner.nextLine();
        if(choice.equals("Y"))
        {
            while(true){
                System.out.println("Current last name                                                    : " + employee.getLastName());
                System.out.print("Enter new last name                                                  : ");
                String lastName = scanner.nextLine();
                if(lastName.isEmpty()){
                    System.out.println("Last name cannot be empty, please try again");
                }
                else{
                    employee.setLastName(lastName);
                    break;
                }
            }
        }
        System.out.print("Click Y to update identification No. or any other character otherwise: ");
        choice = scanner.nextLine();
        if(choice.equals("Y"))
        {
            while(true){
                System.out.println("Current Identification number                                        : " + employee.getIdentificationNumber());
                System.out.print("Enter new identification number                                      : ");
                String newIdentificationNumber = scanner.nextLine().trim();
                if(newIdentificationNumber.isEmpty()){
                    System.out.println("Identificaiton number cannot be empty, please try again");
                }
                else{
                    try{
                        Employee employee_found = employeeControllerRemote.retrieveEmployeeByIdentificationNumber(newIdentificationNumber);
                        System.out.println("Identification number already in use, please try again");
                    }
                    catch(EmployeeNotFoundException ex){
                        employee.setIdentificationNumber(newIdentificationNumber);
                        break;
                    }
                }
            }
                
        }
        employeeControllerRemote.updateEmployee(employee);
        System.out.println();
        System.out.println("Employee successfully updated");
        System.out.println();
        
    }
        
   
    private void doDeleteEmployee(Employee employee){
        Scanner scanner = new Scanner(System.in);
        String username = employee.getUserName();
        if(!username.equals("administrator")){
            try{
                String fullName = employee.getFirstName() + " " + employee.getLastName();
                employeeControllerRemote.deleteEmployee(employee.getUserName());        
                System.out.println("Employee: " + fullName + " has been successfully been deleted");
                System.out.println();
            }
            catch(EmployeeNotFoundException ex){
                System.out.println("An error has occurred while retrieving employee: " + ex.getMessage() + "\n");
            }
        }
        else System.out.println("Unable to delete Administrator account");   
    }
    
    private void doViewAllEmployee(){
        List<Employee> employeeList = employeeControllerRemote.retrieveEmployeeList();
        for(int i = 0; i < employeeList.size(); i++)
        {
            System.out.println("Employee Id          : " + employeeList.get(i).getEmployeeId()+ "\nUserName             : " + employeeList.get(i).getUserName() + "\nPassword             : " + employeeList.get(i).getPassWord()+ "\nAccount Type         : " + employeeList.get(i).getAccountTypeEnum()+ "\nFirst Name           : " + employeeList.get(i).getFirstName() + "\nLast Name            : " + employeeList.get(i).getLastName() + "\nIdentification Number: " + employeeList.get(i).getIdentificationNumber());
            System.out.println();
        }
    }
    
}
