/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.Hotel;
import entity.Logging;
import entity.Staff;
import entity.StaffType;
import error.NoResultException;
import etc.RandomPassword;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Named;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.Properties;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import sessionBeans.LogSessionLocal;
import sessionBeans.StaffSessionLocal;

/**
 *
 * @author fengl
 */
@ManagedBean
@SessionScoped
public class AuthenticationManagedBean implements Serializable {

    private String username = null;
    private String password = null;
    private String name = null;

    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    private String resetEmail;

    private Long id = -1L;
    private Staff loggedInStaff;

    @EJB
    private StaffSessionLocal staffSessionLocal;

    @EJB
    private LogSessionLocal logSessionLocal;

    /**
     * Creates a new instance of AuthenticationManagedBean
     */
    public AuthenticationManagedBean() {

    }

    public String login() throws IOException, NoResultException {
        Staff s = new Staff(username, encryptPassword(password));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        PrintWriter out = response.getWriter();

        if (staffSessionLocal.Login(s) == true) {
            if (staffSessionLocal.getStaffByUsename(username).getAccountStatus() == false) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Your account has been banned! Please contact the administrator!');");
                out.println("</script>");
                return "/login.xhtml";
            } else {

                loggedInStaff = staffSessionLocal.getStaffByUsename(username);
                id = loggedInStaff.getStaffID();
                setName(loggedInStaff.getName());

                Logging l = new Logging("Staff", "Login Successfuly as " + name, name);
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
            out.println("alert('User or password incorrect');");
            out.println("</script>");
            return "/login.xhtml";
        }
    }

    public String logout() {
        String tempName = name;

        loggedInStaff = null;
        id = -1L;
        name = null;

        Logging l = new Logging("Staff", "Logout from " + tempName, tempName);
        logSessionLocal.createLogging(l);
        return "loginpage.xhtml?faces-redirect=true";
    }

    public String displayStaffTypes() {
        String returnString = "";
        for (StaffType s : loggedInStaff.getAccountRights()) {
            returnString = returnString + s.getStaffTypeName() + ", ";
        }
        if (returnString.length() > 0) {
            returnString = returnString.substring(0, returnString.length() - 2);
        }

        return returnString;
    }

    public String changePassword() throws IOException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        PrintWriter out = response.getWriter();

        if (!encryptPassword(oldPassword).equals(loggedInStaff.getPassword())) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Password is incorrect!');");
            out.println("</script>");
            return "/ChangePassword.xhtml";
        } else if (!newPassword.equals(confirmPassword)) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Confirm password is incorrect!');");
            out.println("</script>");
            return "/ChangePassword.xhtml";
        } else {
            staffSessionLocal.changePasword(loggedInStaff, encryptPassword(newPassword));
            Logging l = new Logging("Profile", loggedInStaff.getUserName() + " has just changed password", loggedInStaff.getUserName());
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

        Staff tempStaff;
        tempStaff = staffSessionLocal.getStaffByUsename(resetEmail);
        if (tempStaff == null) {
            tempStaff = staffSessionLocal.getStaffByEmail(name);
        }

        if (tempStaff == null) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Wrong Email or Username is input!');");
            out.println("</script>");
        } else {
            String newPass = new RandomPassword().generateRandomPassword();
            String email = tempStaff.getEmail();

            staffSessionLocal.changePasword(tempStaff, encryptPassword(newPass));
            Logging l = new Logging("Staff", "Reset Password for " + tempStaff.getName(), tempStaff.getName());
            logSessionLocal.createLogging(l);

            String msg = "Your password has been reset! Please login with the new password:\n\"" + newPass + "\"";
            sendEmail(email, "Reset Password", msg);

            out.println("<script type=\"text/javascript\">");
            out.println("alert('New password has been sent');");
            out.println("</script>");
        }
    }

    public String updateProfile() throws NoResultException {
        staffSessionLocal.updateStaff(loggedInStaff);
        Logging l = new Logging("Staff", "Update " + loggedInStaff.getName() + "'s profile", loggedInStaff.getName());
        logSessionLocal.createLogging(l);
        
        return "index.xhtml?faces-redirect=true";
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Staff getLoggedInStaff() {
        return loggedInStaff;
    }

    public void setLoggedInStaff(Staff loggedInStaff) {
        this.loggedInStaff = loggedInStaff;
    }

    public StaffSessionLocal getStaffSessionLocal() {
        return staffSessionLocal;
    }

    public void setStaffSessionLocal(StaffSessionLocal staffSessionLocal) {
        this.staffSessionLocal = staffSessionLocal;
    }

    public String getResetEmail() {
        return resetEmail;
    }

    public void setResetEmail(String resetEmail) {
        this.resetEmail = resetEmail;
    }

    public LogSessionLocal getLogSessionLocal() {
        return logSessionLocal;
    }

    public void setLogSessionLocal(LogSessionLocal logSessionLocal) {
        this.logSessionLocal = logSessionLocal;
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

}
