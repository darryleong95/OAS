package ejb.session.stateless;

import entity.Employee;
import javax.ejb.Local;
import util.Exception.EmployeeNotFoundException;

public interface EmployeeControllerLocal {

    public Employee createNewEmployee(Employee newEmployee);

    public Employee retrieveEmployeeByUserName(String userName) throws EmployeeNotFoundException;
}
