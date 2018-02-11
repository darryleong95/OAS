package ejb.session.stateless;

import entity.Employee;
import java.util.List;
import util.Exception.EmployeeNotFoundException;
import util.Exception.InvalidLoginCredentialException;

public interface EmployeeControllerRemote {

    public Employee createNewEmployee(Employee newEmployee);

    public Employee retrieveEmployeeById(Long employeeId) throws EmployeeNotFoundException;

    public Employee retrieveEmployeeByUserName(String userName) throws EmployeeNotFoundException;
    
    public Employee retrieveEmployeeByIdentificationNumber(String identificationNumber) throws EmployeeNotFoundException;

    public List<Employee> retrieveEmployeeList();

    public void updateEmployee(Employee updateEmployee);

    public void deleteEmployee(String identificationNumber) throws EmployeeNotFoundException;

    public Employee employeeLogin(String username, String password) throws InvalidLoginCredentialException;
}
