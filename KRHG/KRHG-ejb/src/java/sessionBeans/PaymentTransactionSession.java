/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.CreditCard;
import entity.PaymentTransaction;
import error.NoResultException;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PaymentTransactionSession implements PaymentTransactionSessionLocal {
    @PersistenceContext
    EntityManager em;
    
    
    @Override
    public List<PaymentTransaction> getAllPaymentTransaction() throws NoResultException {
        Query q;
        q = em.createQuery("SELECT pt FROM PaymentTransaction pt ");
        return q.getResultList();
    }

    @Override
    public PaymentTransaction getPaymentTransactionByID(Long ptID) throws NoResultException {
        PaymentTransaction pt = em.find(PaymentTransaction.class, ptID);
        if (pt != null) {
            return pt;
        } else {
            throw new NoResultException("Payment Transaction not found.");
        }
    }

    @Override
    public void updatePaymentTransaction(PaymentTransaction pt) throws NoResultException {
        PaymentTransaction oldPT = em.find(PaymentTransaction.class, pt.getTransactionID());
        if (oldPT != null) {
            oldPT.setCreditCard(pt.getCreditCard());
            oldPT.setTotalPrice(pt.getTotalPrice());
            oldPT.setInitialPayment(pt.getInitialPayment());
            oldPT.setFinalPayment(pt.getFinalPayment());
            oldPT.setPaymentType(pt.getPaymentType());
            oldPT.setTransactionDateTime(pt.getTransactionDateTime());
            oldPT.setPayer(pt.getPayer());
            oldPT.setFunctionRoomBooked(pt.getFunctionRoomBooked());
            oldPT.setTransportBooked(pt.getTransportBooked());
            oldPT.setRoomsBooked(pt.getRoomsBooked());
        } else {
            throw new NoResultException("Payment Transaction not found.");
        }
    }

    @Override
    public void deletePaymentTransaction(Long ptID) throws NoResultException {
        PaymentTransaction pt = em.find(PaymentTransaction.class, ptID);
        if (pt != null) {
            em.remove(pt);
        } else {
            throw new NoResultException("Payment Transaction not found");
        }
    }

    @Override
    public void createPaymentTransaction(PaymentTransaction pt) {
        em.persist(pt);
    }

    @Override
    public List<PaymentTransaction> getAllPaymentTransactionByDate(Date selectedDate) throws NoResultException {
        Query q;
        q = em.createQuery("SELECT pt FROM PaymentTransaction pt WHERE "
                + "pt.transactionDateTime = :selectedDate");
        q.setParameter("selectedDate", selectedDate);
        return q.getResultList();
    }


}
