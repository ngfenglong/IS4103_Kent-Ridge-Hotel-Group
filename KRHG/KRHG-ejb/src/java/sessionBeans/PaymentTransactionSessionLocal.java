/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.CreditCard;
import entity.PaymentTransaction;
import error.NoResultException;
import java.util.List;
import javax.ejb.Local;

@Local
public interface PaymentTransactionSessionLocal {
    public List<PaymentTransaction> getAllPaymentTransaction() throws NoResultException;
    public PaymentTransaction getPaymentTransactionByID(Long ptID) throws NoResultException;
    public void updatePaymentTransaction (PaymentTransaction pt) throws NoResultException;
    public void deletePaymentTransaction (Long ptID) throws NoResultException;
    public void createPaymentTransaction (PaymentTransaction pt);
    public PaymentTransaction getLastPaymentTransaction() throws NoResultException;
    
    public void createCreditCard(CreditCard cc);
    public CreditCard getLastCreditCard() throws NoResultException;
    
    
}
