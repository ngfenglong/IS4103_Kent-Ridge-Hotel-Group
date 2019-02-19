/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Customer;
import error.NoResultException;
import etc.RandomPassword;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.List;
import java.util.Properties;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
    public void deleteCustomer(Long cID) throws NoResultException {
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
    public void updateCustomer(Customer c) throws NoResultException {
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

    // Encryption with SHA-1
    private static String encryptPassword(String password) {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sha1;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    @Override
    public boolean Login(Customer c) {
        Query q = em.createQuery("SELECT c FROM Customer c WHERE "
                + "LOWER(c.email) = :email");
        q.setParameter("email", c.getEmail().toLowerCase());

        if (!q.getResultList().isEmpty()) {
            Customer checkC = (Customer) q.getResultList().get(0);
            if (checkC.getPassword().equals(c.getPassword())) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    @Override
    public void changePasword(Customer c, String newPass) {
        Customer customer = em.find(Customer.class, c.getCustomerID());
        customer.setPassword(newPass);
        em.flush();
    }

    @Override
    public boolean forgetPassword(Customer c) {
        Customer cust = em.find(Customer.class, c.getCustomerID());
        String recipientEmail = cust.getEmail();
        String newPassword = new RandomPassword().generateRandomPassword();
        cust.setPassword(encryptPassword(newPassword));
        em.flush();

        String msg = "Your password has been reset! Please login with the new password:\n\"" + newPassword + "\"";
        sendEmail(recipientEmail, "Reset Password", msg);

        return true;
    }

    @Override
    public void deactivateAccount(Long cId) {
        Customer customer = em.find(Customer.class, cId);
        customer.setAccountStatus(false);
        em.flush();
    }

    @Override
    public void activateAccount(Long cId) {
        Customer customer = em.find(Customer.class, cId);
        customer.setAccountStatus(true);
        em.flush();
    }

      public static void sendEmail(String recipient, String subject, String msg) {

        String username = "automessage.kentridgehotelgroup@gmail.com";
        String password = "krhg1234";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("Do-not-reply@KentRidgeHotelGroup.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(msg);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    
    public void checkProfile(Customer c) {
        c.getPoints();
        c.getBookingHistories();
        c.getEmail();
        c.getMobileNum();
        c.getDateJoined();
        c.getNric();
        c.getPassportNum();
    }

    
    public void updateProfile(Customer c) {
            c.setPoints(c.getPoints());
            c.setBookingHistories(c.getBookingHistories());
            c.setEmail(c.getEmail());
            c.setMobileNum(c.getMobileNum());
    }

    
    public void checkPoints(Customer c) {
        c.getPoints();
    }

    
    public void checkPastBookings(Customer c) throws NoResultException{
        if (!c.getBookingHistories().isEmpty()) {
        c.getBookingHistories();
        }
        
        else {
            throw new NoResultException("No prior bookings were made.");
        }
    }

    
    public void viewCurrentBookings(Customer c) throws NoResultException{
        if (!c.getCurrentBookings().isEmpty()) {
        c.getCurrentBookings();
        }

        else {
            throw new NoResultException("No bookings have been made.");
        }
        }

    
    public void cancelBookings(Customer c) throws NoResultException{
        if (!c.getCurrentBookings().isEmpty()) {
        c.getCurrentBookings();
        //modify currentbookings to remove selected bookings
        }
        
        else {
            throw new NoResultException("No bookings found.");
        }
        
    }

    //incomplete
    public void signOut(Customer c) {
        
    }

    //incomplete
    public void submitFeedback(Customer c) {
        
    }


}
