/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Customer;
import error.NoResultException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author dk349
 */
@Stateless
public class CustomerSession implements CustomerSessionLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Customer> getAllCustomers() {
        Query q;
        q = em.createQuery("SELECT c FROM Customer c");
        return q.getResultList();
    }

    @Override
    public Customer getCustomerByID(Long cID) throws NoResultException {
        Customer c = em.find(Customer.class, cID);
        if (c != null) {
            return c;
        } else {
            throw new NoResultException("Customer not found.");
        }
    }

    @Override
    public Customer getCustomerByNric(String nric) throws NoResultException {
        Query q;
        q = em.createQuery("SELECT c FROM Customer c WHERE "
                + "LOWER(c.nric) = :nric");
        q.setParameter("nric", nric.toLowerCase());

        if (!q.getResultList().isEmpty()) {
            return (Customer) q.getResultList().get(0);
        } else {
            throw new NoResultException("Customer not found.");
        }
    }

    @Override
    public Customer getCustomerByEmail(String email) throws NoResultException {
        Query q;
        q = em.createQuery("SELECT c FROM Customer c WHERE "
                + "LOWER(c.email) = :email");
        q.setParameter("email", email.toLowerCase());

        if (!q.getResultList().isEmpty()) {
            return (Customer) q.getResultList().get(0);
        } else {
            throw new NoResultException("Customer not found.");
        }
    }

    @Override
    public Customer getCustomerByMobileNum(String mobileNum) throws NoResultException {
        Query q;
        q = em.createQuery("SELECT c FROM Customer c WHERE "
                + "LOWER(c.mobileNum) = :mobileNum");
        q.setParameter("mobileNum", mobileNum.toLowerCase());

        if (!q.getResultList().isEmpty()) {
            return (Customer) q.getResultList().get(0);
        } else {
            throw new NoResultException("Customer not found.");
        }
    }

    @Override
    public Customer getCustomerByPassportNum(String passportNum) throws NoResultException {
        Query q;
        q = em.createQuery("SELECT c FROM Customer c WHERE "
                + "LOWER(c.passportNum) = :passportNum");
        q.setParameter("passportNum", passportNum.toLowerCase());

        if (!q.getResultList().isEmpty()) {
            return (Customer) q.getResultList().get(0);
        } else {
            throw new NoResultException("Customer not found.");
        }
    }

    @Override
    public void deleteCustomer(Long cID) throws NoResultException{
        Customer c = em.find(Customer.class, cID);
        if (c != null) {
            em.remove(c);
        } else {
            throw new NoResultException("Customer not found");
        }
    }

    @Override
    public void createCustomer(Customer c) {
        em.persist(c);
    }

    @Override
    public void updateCustomer(Customer c) throws NoResultException{
        Customer oldC = em.find(Customer.class, c.getCustomerID());
        if (oldC != null) {
            oldC.setPassword(c.getPassword());
            oldC.setPoints(c.getPoints());
            oldC.setBookingHistories(c.getBookingHistories());
            oldC.setEmail(c.getEmail());
            oldC.setMobileNum(c.getMobileNum());
        } else {
            throw new NoResultException("Customer Not found");
        }
    }

}
