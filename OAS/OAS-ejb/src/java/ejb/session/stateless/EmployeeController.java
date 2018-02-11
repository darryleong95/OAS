/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Employee;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.Exception.EmployeeNotFoundException;
import util.Exception.InvalidLoginCredentialException;

/**
 *
 * @author User
 */
@Stateless
@Local(EmployeeControllerLocal.class)
@Remote(EmployeeControllerRemote.class)

public class EmployeeController implements EmployeeControllerRemote, EmployeeControllerLocal {

    @PersistenceContext(unitName = "OAS-ejbPU")
    private EntityManager em;
    
    @Override
    public Employee createNewEmployee(Employee newEmployee) {
        em.persist(newEmployee);
        em.flush();
        em.refresh(newEmployee);
        return newEmployee;
    }
    
    @Override
    public Employee retrieveEmployeeById(Long employeeId) throws EmployeeNotFoundException{
        Employee employee = em.find(Employee.class, employeeId);
        if(employee != null)
        {
            return employee;
        }
        else
        {
            throw new EmployeeNotFoundException("Employee does not exist!");
        }
    }
    
    @Override
    public Employee retrieveEmployeeByUserName(String userName) throws EmployeeNotFoundException
    {
        Query query = em.createQuery("SELECT e FROM Employee e WHERE e.userName = :userName");
        query.setParameter("userName", userName);
        try
        {
            return (Employee) query.getSingleResult();
        }
        catch(NoResultException | NonUniqueResultException ex)
        {
            throw new EmployeeNotFoundException("Employee does not exist!");
        }
    }
    
    public Employee retrieveEmployeeByIdentificationNumber(String identificationNumber) throws EmployeeNotFoundException
    {
        Query query = em.createQuery("SELECT e FROM Employee e WHERE e.identificationNumber = :identificationNumber");
        query.setParameter("identificationNumber", identificationNumber);
        try
        {
            return (Employee) query.getSingleResult();
        }
        catch(NoResultException | NonUniqueResultException ex)
        {
            throw new EmployeeNotFoundException("Employee does not exist!");
        }
    }
    
    @Override
    public List<Employee> retrieveEmployeeList(){
        Query query = em.createQuery("SELECT e FROM Employee e");
        return query.getResultList();
    }
    
    @Override
    public void updateEmployee(Employee updateEmployee){
        em.merge(updateEmployee);
    }
    
    @Override
    public void deleteEmployee(String userName) throws EmployeeNotFoundException
    {
        Query query = em.createQuery("SELECT e FROM Employee e WHERE e.userName = :userName");
        query.setParameter("userName", userName);
        try
        {
            Employee deleteMe = (Employee)query.getSingleResult();
            em.remove(deleteMe);
            em.flush();
        }
        catch(NoResultException | NonUniqueResultException ex){
            throw new EmployeeNotFoundException("Employee does not exist!");
        }
    }
    
    @Override
    public Employee employeeLogin(String username, String password) throws InvalidLoginCredentialException{
        try
        {
            Employee employee = retrieveEmployeeByUserName(username);
            
            if(employee.getPassWord().equals(password))
            {
                return employee;
            }
            else
            {
                throw new InvalidLoginCredentialException("Username does not exist or invalid password!");
            }
        }
        catch(EmployeeNotFoundException ex)
        {
            throw new InvalidLoginCredentialException("Username does not exist or invalid password!");
        }
    }
   
}
