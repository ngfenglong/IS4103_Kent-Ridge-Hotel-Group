/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.Logging;
import entity.Customer;
import entity.PaymentTransaction;
import entity.RoomBooking;
import error.NoResultException;
import etc.RandomPassword;
import java.io.IOException;
import java.io.PrintWriter;
import javax.faces.bean.SessionScoped;
import javax.ejb.EJB;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
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
import sessionBeans.BookingSessionLocal;
import sessionBeans.CustomerSessionLocal;
import sessionBeans.LogSessionLocal;
import sessionBeans.PaymentTransactionSessionLocal;

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

    private String regFName;
    private String regLName;
    private String regNric;
    private String regPassword;
    private String regConfirmPassword;
    private String regEmail;
    private String regMobileNum;
    private String regPassportNum;

    private String rpEmail;

    //BookingHistory
    private List <RoomBooking> pastBookings;
    private List <PaymentTransaction> pastTransactions;
    private Long selectedTransactionID;
    private PaymentTransaction selectedTransaction;
    
    private Long id = -1L;

    private Customer loggedInCustomer;

    @EJB
    private CustomerSessionLocal customerSessionLocal;

    @EJB
    private PaymentTransactionSessionLocal paymentTransactionSessionLocal;
    @EJB
    private LogSessionLocal logSessionLocal;
    @EJB
    private BookingSessionLocal bookingSessionLocal;

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
                setName(loggedInCustomer.getLastName() + " " + loggedInCustomer.getFirstName());

                Logging l = new Logging("Customer", "Login Successfuly as " + name, name);
                logSessionLocal.createLogging(l);
                getAllPastBookings();
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
            c.setFirstName(regFName);
            c.setLastName(regLName);
            c.setEmail(regEmail);
            c.setMobileNum(regMobileNum);
            c.setMobileNum(regMobileNum);
   
            c.setPassword(encryptPassword(regPassword));
            c.setAccountStatus(true);
            c.setPoints(0);
            c.setDateJoined(java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            customerSessionLocal.createCustomer(c);

            String tempName = regLName + " " + regFName;
            Logging l = new Logging("Customer", "Register new User " + tempName, tempName);
            logSessionLocal.createLogging(l);

            regFName = null;
            regLName = null;
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
        String logActivityName = loggedInCustomer.getLastName() + " " + loggedInCustomer.getFirstName() ;
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
            Logging l = new Logging("Profile", loggedInCustomer.getLastName() + " " + loggedInCustomer.getFirstName()+ " has just changed password", loggedInCustomer.getLastName() + " " + loggedInCustomer.getFirstName());
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
    
    public void getAllPastBookings() throws NoResultException{
        List<PaymentTransaction> allPaymentTransactions = paymentTransactionSessionLocal.getAllPaymentTransaction();
        List<PaymentTransaction> allCustomerTransactions = new ArrayList();
        
        for(PaymentTransaction p:allPaymentTransactions){
            if(p.getEmail()!=null){
             if(p.getEmail().equals(loggedInCustomer.getEmail())&& p.getRoomsBooked()!=null){
                allCustomerTransactions.add(p);
            }
            }
           
        }
        
        pastTransactions = allCustomerTransactions;
    }
    
    public String getTypeOfFacilityBooked(PaymentTransaction t){
        String facilitiesBooked = "";
        if(t.getRoomsBooked() != null){
            if(facilitiesBooked !=null){
                facilitiesBooked = facilitiesBooked+", room";
            } else {
                facilitiesBooked = "room";
            }
        }
        if(t.getFunctionRoomBooked() !=null){
            if(facilitiesBooked !=null){
                facilitiesBooked = facilitiesBooked+", Function Room";
            } else {
                facilitiesBooked = "Function Room";
            }
        }      
        return facilitiesBooked;
    }
    
    public void selectTransaction(Long transactionId)throws NoResultException{
        selectedTransactionID = transactionId;
        paymentTransactionSessionLocal.getPaymentTransactionByID(transactionId);
    }
    
    public List<RoomBooking> getAllRoomBookingFromTransaction(PaymentTransaction t)throws NoResultException{
        List <RoomBooking> allBooking = t.getRoomsBooked();
        List <RoomBooking> roomBookingsByTransaction = new ArrayList();
        for(RoomBooking r: allBooking){
            roomBookingsByTransaction.add(bookingSessionLocal.getRoomBookingByID(r.getRoomBookingID()));
        }
         
        return roomBookingsByTransaction;
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
            message.setContent("<body>\n" +
"\n" +
"<div class=\"super_container\">\n" +
"	\n" +
"	<!-- Header -->\n" +
"\n" +
"	<header class=\"header\">\n" +
"		<div class=\"header_content d-flex flex-row align-items-center justify-content-start\">\n" +
"			<div class=\"logo\"><a href=\"#\">KRHG</a></div>\n" +
"			<div class=\"ml-auto d-flex flex-row align-items-center justify-content-start\">\n" +
"				<nav class=\"main_nav\">\n" +
"					<ul class=\"d-flex flex-row align-items-start justify-content-start\">\n" +
"						<li><a href=\"index.html\">Home</a></li>\n" +
"						<li><a href=\"about.html\">About us</a></li>\n" +
"						<li><a href=\"hotels.html\">Hotels</a></li>\n" +
"						<li><a href=\"contact.html\">Contact us</a></li>\n" +
"					</ul>\n" +
"				</nav>\n" +
"				<div class=\"book_button\"><a href=\"login.html\">Login/Signup</a></div>\n" +
"\n" +
"\n" +
"				<!-- Hamburger Menu -->\n" +
"				<div class=\"hamburger\"><i class=\"fa fa-bars\" aria-hidden=\"true\"></i></div>\n" +
"			</div>\n" +
"		</div>\n" +
"	</header>\n" +
"\n" +
"	<!-- Menu -->\n" +
"\n" +
"	<div class=\"menu trans_400 d-flex flex-column align-items-end justify-content-start\">\n" +
"		<div class=\"menu_close\"><i class=\"fa fa-times\" aria-hidden=\"true\"></i></div>\n" +
"		<div class=\"menu_content\">\n" +
"			<nav class=\"menu_nav text-right\">\n" +
"				<ul>\n" +
"					<li><a href=\"index.html\">Home</a></li>\n" +
"					<li><a href=\"about.html\">About us</a></li>\n" +
"					<li><a href=\"hotels.html\">Hotels</a></li>\n" +
"					<li><a href=\"contact.html\">Contact us</a></li>\n" +
"				</ul>\n" +
"			</nav>\n" +
"		</div>\n" +
"		<div class=\"menu_extra\">\n" +
"			<div class=\"menu_book text-right\"><a href=\"#\">Reserve Now</a></div>\n" +
"			<div class=\"menu_phone d-flex flex-row align-items-center justify-content-center\">\n" +
"				<img src=\"images/phone-2.png\" alt=\"\">\n" +
"				<span>1234 5678</span>\n" +
"			</div>\n" +
"		</div>\n" +
"	</div>\n" +
"\n" +
"	<!-- Home -->\n" +
"\n" +
"	<div class=\"home\">\n" +
"		<div class=\"background_image\" style=\"background-image:url(images/about.jpg)\"></div>\n" +
"		<div class=\"home_container\">\n" +
"			<div class=\"container\">\n" +
"				<div class=\"row\">\n" +
"					<div class=\"col\">\n" +
"						<div class=\"home_content text-center\">\n" +
"							<div class=\"home_title\">About us</div>\n" +
"\n" +
"						</div>\n" +
"					</div>\n" +
"				</div>\n" +
"			</div>\n" +
"		</div>\n" +
"	</div>\n" +
"\n" +
"	<!-- About -->\n" +
"\n" +
"	<div class=\"about\">\n" +
"		<div class=\"container\">\n" +
"			<div class=\"row\">\n" +
"				<div class=\"col-lg-6\">\n" +
"					<div class=\"about_title\"><h2>Welcome to Kent Ridge Hotel Group</h2></div>\n" +
"				</div>\n" +
"			</div>\n" +
"			<div class=\"row about_row\">\n" +
"				\n" +
"				<!-- About Content -->\n" +
"				<div class=\"col-lg-6\">\n" +
"					<div class=\"about_content\">\n" +
"						<div class=\"about_text\">\n" +
"							Kent Ridge Hotels Group opened its first hotel, the now Kent Ridge Central in Ang Mo Kio in 2000. We have built a reputation over the years to provide premium service quality and affordable accommodation. Over the years, we have grown from strength to strength, opening more hotels islandwide, and opening the Group’s new flagship, Kent Ridge Grand in the heart of Singapore’s Shopping Belt, Orchard Road, in 2012. Today, the Group operates 10 hotels around Singapore and the Kent Ridge brand has become well established in the local hospitality industry.\n" +
"									Most of our hotels are strategically located in the City, or in major regional centers of Singapore, and are easily accessible by road, buses or the MRT system. Our hotels are also located near major tourist attractions and convention centers. Equipped with good price, locations, service, cleanliness and a hassle-free booking experience, Kent Ridge Hotels will continue to be the ideal choice for travelers providing comfort to all.\n" +
"									\n" +
"						</div>\n" +
"						<div class=\"about_sig\"><img src=\"images/sig.png\" alt=\"\"></div>\n" +
"					</div>\n" +
"				</div>\n" +
"\n" +
"				<!-- About Images -->\n" +
"				<div class=\"col-lg-6\">\n" +
"					<div class=\"about_images d-flex flex-row align-items-start justify-content-between flex-wrap\">\n" +
"						<img src=\"images/about_1.png\" alt=\"\">\n" +
"						<img src=\"images/about_2.png\" alt=\"\">\n" +
"						<img src=\"images/about_3.png\" alt=\"\">\n" +
"					</div>\n" +
"				</div>\n" +
"			</div>\n" +
"		</div>\n" +
"	</div>\n" +
"\n" +
"	<!-- Split Section Right -->\n" +
"\n" +
"	<div class=\"split_section_right container_custom\">\n" +
"		<div class=\"container\">\n" +
"			<div class=\"row row-xl-eq-height\">\n" +
"				\n" +
"				<div class=\"col-xl-6 order-xl-1 order-2\">\n" +
"					<div class=\"split_section_image\">\n" +
"						<div class=\"background_image\" style=\"background-image:url(images/milestones.jpg)\"></div>\n" +
"					</div>\n" +
"				</div>\n" +
"\n" +
"				<div class=\"col-xl-6 order-xl-2 order-1\">\n" +
"					<div class=\"split_section_right_content\">\n" +
"						<div class=\"split_section_title\"><h2>Mission</h2></div>\n" +
"						<div class=\"split_section_text\">\n" +
"							<p>The mission of Kent Ridge Hotels Group is to provide the highest level of hospitality services to make the stay of our guests enjoyable. We aim to make all KRHG hotels a pleasant place to stay.</p>\n" +
"						</div>\n" +
"\n" +
"					</div>\n" +
"				</div>\n" +
"\n" +
"			</div>\n" +
"		</div>\n" +
"	</div>\n" +
"\n" +
"	<!-- Split Section Left -->\n" +
"\n" +
"	<div class=\"split_section_left container_custom\">\n" +
"		<div class=\"container\">\n" +
"			<div class=\"row\">\n" +
"				<div class=\"col-xl-6\">\n" +
"					<div class=\"split_section_left_content\">\n" +
"						<div class=\"split_section_title\"><h2>Vision</h2></div>\n" +
"						<div class=\"split_section_text\">\n" +
"							<p>Our vision is to apply and set the highest standards of service quality. We aim to use and constantly introduce technologically advanced processes to maintain our competitive advantage, whilst still remaining true to our core value of service quality. </p>\n" +
"						</div>\n" +
"\n" +
"\n" +
"					</div>\n" +
"				</div>\n" +
"\n" +
"				<!-- Loaders Image -->\n" +
"				<div class=\"col-xl-6\">\n" +
"					<div class=\"split_section_image split_section_left_image\">\n" +
"						<div class=\"background_image\" style=\"background-image:url(images/loaders.jpg)\"></div>\n" +
"					</div>\n" +
"				</div>\n" +
"\n" +
"			</div>\n" +
"		</div>\n" +
"	</div>","text/html");

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

    public String getRegFName() {
        return regFName;
    }

    public void setRegFName(String regFName) {
        this.regFName = regFName;
    }

    public String getRegLName() {
        return regLName;
    }

    public void setRegLName(String regLName) {
        this.regLName = regLName;
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

    /**
     * @return the pastBookings
     */
    public List <RoomBooking> getPastBookings() {
        return pastBookings;
    }

    /**
     * @param pastBookings the pastBookings to set
     */
    public void setPastBookings(List <RoomBooking> pastBookings) {
        this.pastBookings = pastBookings;
    }

    /**
     * @return the pastTransactions
     */
    public List <PaymentTransaction> getPastTransactions() {
        return pastTransactions;
    }

    /**
     * @param pastTransactions the pastTransactions to set
     */
    public void setPastTransactions(List <PaymentTransaction> pastTransactions) {
        this.pastTransactions = pastTransactions;
    }

    /**
     * @return the selectedTransactionID
     */
    public Long getSelectedTransactionID() {
        return selectedTransactionID;
    }

    /**
     * @param selectedTransactionID the selectedTransactionID to set
     */
    public void setSelectedTransactionID(Long selectedTransactionID) {
        this.selectedTransactionID = selectedTransactionID;
    }

    /**
     * @return the selectedTransaction
     */
    public PaymentTransaction getSelectedTransaction() {
        return selectedTransaction;
    }

    /**
     * @param selectedTransaction the selectedTransaction to set
     */
    public void setSelectedTransaction(PaymentTransaction selectedTransaction) {
        this.selectedTransaction = selectedTransaction;
    }

}
