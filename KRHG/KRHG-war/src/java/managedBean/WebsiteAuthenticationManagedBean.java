/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.Logging;
import entity.Customer;
import error.NoResultException;
import etc.RandomPassword;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import javax.ejb.EJB;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Formatter;
import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import sessionBeans.CustomerSessionLocal;
import sessionBeans.LogSessionLocal;

/**
 *
 * @author fengl
 */
@ManagedBean
@SessionScoped
public class WebsiteAuthenticationManagedBean implements Serializable {

    private String email;
    private String password;
    private String name = null;

    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    private String regName;
    private String regNric;
    private String regPassword;
    private String regConfirmPassword;
    private String regEmail;
    private String regMobileNum;
    private String regPassportNum;

    private String rpEmail;

    private Long id = -1L;

    private Customer loggedInCustomer;

    @EJB
    private CustomerSessionLocal customerSessionLocal;

    @EJB
    private LogSessionLocal logSessionLocal;

    /**
     * Creates a new instance of AuthenticationManagedBean
     */
    public WebsiteAuthenticationManagedBean() {
    }

    public String login() throws IOException, NoResultException {
        Customer c = new Customer(email, encryptPassword(password));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        PrintWriter out = response.getWriter();

        if (customerSessionLocal.Login(c) == true) {
            if (customerSessionLocal.getCustomerByEmail(email).getAccountStatus() == false) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Your account has been banned! Please contact the administrator!');");
                out.println("</script>");
                return "/login.xhtml";
            } else {

                loggedInCustomer = customerSessionLocal.getCustomerByEmail(email);
                id = loggedInCustomer.getCustomerID();
                setName(loggedInCustomer.getName());

                Logging l = new Logging("Customer", "Login Successfuly as " + name, name);
                logSessionLocal.createLogging(l);

                out.println("<script type=\"text/javascript\">");
                out.println("alert('Login Succesful!');");
                out.println("</script>");
                return "/index.xhtml?faces-redirect=true";

            }
        } else {

            name = null;
            password = null;
            id = -1L;
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Email or password incorrect');");
            out.println("</script>");
            return "/login.xhtml";
        }
    }

    public String register() throws IOException {

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        PrintWriter out = response.getWriter();

        if (regPassword.equals(regConfirmPassword)) {
            Customer c = new Customer();
            c.setName(regName);
            c.setEmail(regEmail);
            c.setMobileNum(regMobileNum);

            c.setEmail(regEmail);
            c.setMobileNum(regMobileNum);
   
            c.setPassword(encryptPassword(regPassword));
            c.setAccountStatus(true);
            c.setPoints(0);
            c.setDateJoined(java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            customerSessionLocal.createCustomer(c);

            String tempName = regName;
            Logging l = new Logging("Customer", "Register new User " + tempName, tempName);
            logSessionLocal.createLogging(l);

            regName = null;
            regNric = null;
            regEmail = null;
            regMobileNum = null;
            regPassportNum = null;
            regPassword = null;
            regConfirmPassword = null;

            out.println("<script type=\"text/javascript\">");
            out.println("alert('Register Succesful!');");
            out.println("</script>");

            return "login.xhtml?faces-redirect=true";

        } else {

            out.println("<script type=\"text/javascript\">");
            out.println("alert('Confirm Password doesn't match! !');");
            out.println("</script>");

            return "register.xhtml?faces-redirect=true";
        }

    }

    public String deactivateAccount() throws NoResultException, IOException {
        String logActivityName = loggedInCustomer.getName();
        customerSessionLocal.deactivateAccount(loggedInCustomer.getCustomerID());

        Logging l = new Logging("Customer", "Deactivate " + logActivityName + " account", logActivityName);
        logSessionLocal.createLogging(l);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Please email to admin to reactivate your account!');");
        out.println("</script>");

        return logout();
    }

    public void forgetPassword() throws NoResultException, IOException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        PrintWriter out = response.getWriter();

        Customer tempCust;
        tempCust = customerSessionLocal.getCustomerByEmail(rpEmail);

        if (tempCust == null) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Wrong Email is input!');");
            out.println("</script>");
        } else {
            String newPass = new RandomPassword().generateRandomPassword();
            String emailToSent = tempCust.getEmail();

            customerSessionLocal.changePasword(tempCust, encryptPassword(newPass));

            String msg2 = "Dear User\n\n\n"
                    + "We have received your password change request. Please click here to enter your new password. Access code: F$%f4syb\n\nSincerely\n\n\n"
                    + "Kent Ridge Hotels Group";
            //String msg = "Your password has been reset! Please login with the new password:\n\"" + newPass + "\"";
            sendEmail(emailToSent, "Reset Password", msg2);

            out.println("<script type=\"text/javascript\">");
            out.println("alert('New password has been sent');");
            out.println("</script>");
        }
    }

    public String logout() {
        String tempName = name;

        loggedInCustomer = null;
        id = -1L;
        name = null;

        Logging l = new Logging("Customer", "Logout from " + tempName, tempName);
        logSessionLocal.createLogging(l);
        return "index.xhtml?faces-redirect=true";
    }

    public String changePassword() throws IOException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        PrintWriter out = response.getWriter();

        if (!encryptPassword(oldPassword).equals(loggedInCustomer.getPassword())) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Password is incorrect!');");
            out.println("</script>");
            return "/changePassword.xhtml";
        } else if (!newPassword.equals(confirmPassword)) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Confirm password is incorrect!');");
            out.println("</script>");
            return "/changePassword.xhtml";
        } else {
            customerSessionLocal.changePasword(loggedInCustomer, encryptPassword(newPassword));
            Logging l = new Logging("Profile", loggedInCustomer.getName() + " has just changed password", loggedInCustomer.getName());
            logSessionLocal.createLogging(l);

            oldPassword = null;
            newPassword = null;
            confirmPassword = null;

            return logout();
        }
    }

    public void resetPassword() throws NoResultException, IOException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        PrintWriter out = response.getWriter();

        Customer tempCust;

        tempCust = customerSessionLocal.getCustomerByEmail(email);

        if (tempCust == null) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Wrong Email or Username is input!');");
            out.println("</script>");
        } else {
            String newPass = new RandomPassword().generateRandomPassword();
            String email = tempCust.getEmail();

            customerSessionLocal.changePasword(tempCust, encryptPassword(newPass));

            String msg = "Your password has been reset! Please login with the new password:\n\"" + newPass + "\"";
            sendEmail(email, "Reset Password", msg);

            out.println("<script type=\"text/javascript\">");
            out.println("alert('New password has been sent');");
            out.println("</script>");
        }
    }

    public String updateProfile() throws NoResultException {
        customerSessionLocal.updateCustomer(loggedInCustomer);

        return "Profile.xhtml?faces-redirect=true";
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getLoggedInCustomer() {
        return loggedInCustomer;
    }

    public void setLoggedInCustomer(Customer loggedInCustomer) {
        this.loggedInCustomer = loggedInCustomer;
    }

    public CustomerSessionLocal getCustomerSessionLocal() {
        return customerSessionLocal;
    }

    public void setCustomerSessionLocal(CustomerSessionLocal customerSessionLocal) {
        this.customerSessionLocal = customerSessionLocal;
    }

    public LogSessionLocal getLogSessionLocal() {
        return logSessionLocal;
    }

    public void setLogSessionLocal(LogSessionLocal logSessionLocal) {
        this.logSessionLocal = logSessionLocal;
    }

    public String getRegName() {
        return regName;
    }

    public void setRegName(String regName) {
        this.regName = regName;
    }

    public String getRegNric() {
        return regNric;
    }

    public void setRegNric(String regNric) {
        this.regNric = regNric;
    }

    public String getRegPassword() {
        return regPassword;
    }

    public void setRegPassword(String regPassword) {
        this.regPassword = regPassword;
    }

    public String getRegConfirmPassword() {
        return regConfirmPassword;
    }

    public void setRegConfirmPassword(String regConfirmPassword) {
        this.regConfirmPassword = regConfirmPassword;
    }

    public String getRegEmail() {
        return regEmail;
    }

    public void setRegEmail(String regEmail) {
        this.regEmail = regEmail;
    }

    public String getRegMobileNum() {
        return regMobileNum;
    }

    public void setRegMobileNum(String regMobileNum) {
        this.regMobileNum = regMobileNum;
    }

    public String getRegPassportNum() {
        return regPassportNum;
    }

    public void setRegPassportNum(String regPassportNum) {
        this.regPassportNum = regPassportNum;
    }

    public String getRpEmail() {
        return rpEmail;
    }

    public void setRpEmail(String rpEmail) {
        this.rpEmail = rpEmail;
    }

}
