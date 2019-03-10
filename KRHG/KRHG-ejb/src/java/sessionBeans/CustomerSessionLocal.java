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
    
    
    //Logging in
    public boolean Login(Customer c);
    public void changePasword(Customer c, String newPass);
    public boolean forgetPassword(Customer c);
    public void deactivateAccount(Long cId) throws NoResultException;
    public void activateAccount(Long cId) throws NoResultException;
    public void checkProfile(Customer c);
    public void updateProfile(Customer c);
    public void checkPoints(Customer c);
    public void checkPastBookings(Customer c) throws NoResultException;
    public void viewCurrentBookings(Customer c) throws NoResultException;
    public void cancelBookings(Customer c) throws NoResultException;
    public void signOut(Customer c);
    public void submitFeedback(Customer c);
    public String getTierByPoints(Long cID);
    
}
