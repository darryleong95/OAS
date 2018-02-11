package ejb.session.singleton;

import util.Enum.AccountTypeEnum;
import ejb.session.stateless.EmployeeControllerLocal;
import entity.Employee;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import util.Exception.EmployeeNotFoundException;

@Singleton
@LocalBean
@Startup
public class DataInitializationSessionBean {

    @EJB
    private EmployeeControllerLocal EmployeeControllerLocal;

    @PostConstruct
    public void postConstruct() {
        try {
            EmployeeControllerLocal.retrieveEmployeeByUserName("administrator");
        } 
        catch (EmployeeNotFoundException ex) {
            initializeData();
        }
    }

    private void initializeData() {
        
        Employee employee = new Employee ("administrator", "password","Admin","Tan","S1234567A", AccountTypeEnum.ADMIN);
        EmployeeControllerLocal.createNewEmployee(employee);
    }
}