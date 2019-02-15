/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Customer;
import error.NoResultException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author dk349
 */
@Local
public interface CustomerSessionLocal {
    public List<Customer> getAllCustomers();
    public Customer getCustomerByID(Long cID) throws NoResultException;
    public Customer getCustomerByNric(String nric) throws NoResultException;
    public Customer getCustomerByEmail(String email) throws NoResultException;
    public Customer getCustomerByMobileNum(String mobileNum) throws NoResultException;
    public Customer getCustomerByPassportNum(String passportNum) throws NoResultException;
    public void deleteCustomer(Long cID) throws NoResultException;
    public void createCustomer(Customer c); 
    public void updateCustomer(Customer c) throws NoResultException;
}
