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
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Enumeration;
import java.util.Formatter;
import java.util.List;
import java.util.Map;
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
import javax.servlet.http.HttpServletRequest;
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

    private String testSt;

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
                return "/admin/adminIndex.xhtml?faces-redirect=true";

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
        return "profile.xhtml?faces-redirect=true";
    }

    public boolean isFinanceStaff() {
        boolean status = false;
        if (loggedInStaff != null) {
            List<StaffType> checkList = loggedInStaff.getAccountRights();
            for (StaffType st : checkList) {
                if (st.getStaffTypeName().equals("Finance Staff") || st.getStaffTypeName().equals("Finance Manager")) {
                    status = true;
                }
            }
        }
        return status;
    }

    public boolean isITStaff() {
        boolean status = false;
        if (loggedInStaff != null) {
            List<StaffType> checkList = loggedInStaff.getAccountRights();
            for (StaffType st : checkList) {
                if (st.getStaffTypeName().equals("IT Staff") || st.getStaffTypeName().equals("Sales and Marketing Manager")) {
                    status = true;
                }
            }
        }
        return status;
    }

    public boolean isHRStaff() {
        boolean status = false;
        if (loggedInStaff != null) {
            List<StaffType> checkList = loggedInStaff.getAccountRights();
            for (StaffType st : checkList) {
                if (st.getStaffTypeName().equals("HR Staff") || st.getStaffTypeName().equals("HR Manager")) {
                    status = true;
                }
            }
        }
        return status;
    }

    public boolean isSMAdmin() {
        boolean status = false;
        if (loggedInStaff != null) {
            List<StaffType> checkList = loggedInStaff.getAccountRights();
            for (StaffType st : checkList) {
                if (st.getStaffTypeName().equals("Sales and Marketing Staff") || st.getStaffTypeName().equals("Sales and Marketing Manager")) {
                    status = true;
                }
            }
        }
        return status;
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

    public String displayStaffTypesInLines() {
        String returnString = "";
        for (StaffType s : loggedInStaff.getAccountRights()) {
            returnString = returnString + s.getStaffTypeName() + "\n";
        }
        if (returnString.length() > 0) {
            returnString = returnString.substring(0, returnString.length() - 1);
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

    public boolean getResetPasswordStatus() throws NoResultException {
        String base64decodedString = Base64.getDecoder().decode(testSt).toString();
        Long userID = Long.parseLong(base64decodedString);

        Staff tempStaff = staffSessionLocal.getStaffByID(userID);
        if (tempStaff != null) {
            if (tempStaff.isCanReset() == true) {
                return true;
            }
        }
        return false;
    }

    public String resetNewPassword() throws UnsupportedEncodingException, NoResultException, IOException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        PrintWriter out = response.getWriter();

        if (newPassword.equals(confirmPassword)) {
            System.out.println(testSt);
            String testingbase64decodedString = new String(Base64.getDecoder().decode("MTY="));
            String base64decodedString = new String(Base64.getDecoder().decode(testSt));
            Long userID = Long.parseLong(base64decodedString);
            System.out.println(userID);

            Staff tempStaff = staffSessionLocal.getStaffByID(userID);

            if (tempStaff.isCanReset() == true) {
                staffSessionLocal.changePasword(tempStaff, encryptPassword(newPassword));

                newPassword = null;
                confirmPassword = null;
                testSt = null;
                return "login.xhtml?faces-redirect=true";
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Unauthorize Request!');");
                out.println("</script>");

            }
        }

        out.println("<script type=\"text/javascript\">");
        out.println("alert('Confirm password is incorrect!');");
        out.println("</script>");

        newPassword = null;
        testSt = null;
        confirmPassword = null;
        return "resetPassword.xhtml?faces-redirect=true";
    }

    public String resetPassword() throws NoResultException, IOException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        PrintWriter out = response.getWriter();

        Staff tempStaff;
//        tempStaff = staffSessionLocal.getStaffByUsename(resetEmail);
//        if (tempStaff == null) {
        tempStaff = staffSessionLocal.getStaffByEmail(resetEmail);
//        }

        if (tempStaff == null) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Wrong Email or Username is input!');");
            out.println("</script>");
        } else {
            //String newPass = new RandomPassword().generateRandomPassword();
            String email = tempStaff.getEmail();

//            staffSessionLocal.changePasword(tempStaff, encryptPassword(newPass));
            Logging l = new Logging("Staff", "Request Password Reset for " + tempStaff.getName(), tempStaff.getName());
            logSessionLocal.createLogging(l);
            String encodedID = URLEncoder.encode(tempStaff.getStaffID().toString(), "UTF-8");
            String base64encodedString = Base64.getEncoder().encodeToString(
                    tempStaff.getStaffID().toString().getBytes("utf-8"));
            tempStaff.setCanReset(true);
            staffSessionLocal.updateStaff(tempStaff);

            String content = "<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "<head>\n"
                    + "  <title>Reset Password</title>\n"
                    + "  <meta http-equiv=\"Content-Type\" content=\"text/html\"; charset=utf-8\">\n"
                    + "  <meta name=\"viewport\" content=\"width=device-width\">\n"
                    + "</head>\n"
                    + "\n"
                    + "<body style=\"margin: 0\">\n"
                    + "  <style type=\"text/css\">\n"
                    + "    body {\n"
                    + "      margin: 0;\n"
                    + "      }\n"
                    + "      h1 a:hover {\n"
                    + "      font-size: 30px; color: #333;\n"
                    + "      }\n"
                    + "      h1 a:active {\n"
                    + "      font-size: 30px; color: #333;\n"
                    + "      }\n"
                    + "      h1 a:visited {\n"
                    + "      font-size: 30px; color: #333;\n"
                    + "      }\n"
                    + "      a:hover {\n"
                    + "      text-decoration: none;\n"
                    + "      }\n"
                    + "      a:active {\n"
                    + "      text-decoration: none;\n"
                    + "      }\n"
                    + "      a:visited {\n"
                    + "      text-decoration: none;\n"
                    + "      }\n"
                    + "      .button__text:hover {\n"
                    + "      color: #fff; text-decoration: none;\n"
                    + "      }\n"
                    + "      .button__text:active {\n"
                    + "      color: #fff; text-decoration: none;\n"
                    + "      }\n"
                    + "      .button__text:visited {\n"
                    + "      color: #fff; text-decoration: none;\n"
                    + "      }\n"
                    + "      a:hover {\n"
                    + "      color: #080e66;\n"
                    + "      }\n"
                    + "      a:active {\n"
                    + "      color: #080e66;\n"
                    + "      }\n"
                    + "      a:visited {\n"
                    + "      color: #080e66;\n"
                    + "      }\n"
                    + "      @media (max-width: 600px) {\n"
                    + "        .container {\n"
                    + "          width: 94% !important;\n"
                    + "        }\n"
                    + "        .main-action-cell {\n"
                    + "          float: none !important; margin-right: 0 !important;\n"
                    + "        }\n"
                    + "        .secondary-action-cell {\n"
                    + "          text-align: center; width: 100%;\n"
                    + "        }\n"
                    + "        .header {\n"
                    + "          margin-top: 20px !important; margin-bottom: 2px !important;\n"
                    + "        }\n"
                    + "        .shop-name__cell {\n"
                    + "          display: block;\n"
                    + "        }\n"
                    + "        .order-number__cell {\n"
                    + "          display: block; text-align: left !important; margin-top: 20px;\n"
                    + "        }\n"
                    + "        .button {\n"
                    + "          width: 100%;\n"
                    + "        }\n"
                    + "        .or {\n"
                    + "          margin-right: 0 !important;\n"
                    + "        }\n"
                    + "        .apple-wallet-button {\n"
                    + "          text-align: center;\n"
                    + "        }\n"
                    + "        .customer-info__item {\n"
                    + "          display: block; width: 100% !important;\n"
                    + "        }\n"
                    + "        .spacer {\n"
                    + "          display: none;\n"
                    + "        }\n"
                    + "        .subtotal-spacer {\n"
                    + "          display: none;\n"
                    + "        }\n"
                    + "      }\n"
                    + "  </style>\n"
                    + "  <table class=\"body\" style=\"border-collapse: collapse; border-spacing: 0; height: 100% !important; width: 100% !important\">\n"
                    + "    <tr>\n"
                    + "      <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                    + "        <table class=\"header row\" style=\"border-collapse: collapse; border-spacing: 0; margin: 40px 0 20px; width: 100%\">\n"
                    + "          <tr>\n"
                    + "            <td class=\"header__cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                    + "              <center>\n"
                    + "                <table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">\n"
                    + "                  <tr>\n"
                    + "                    <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                    + "                      <table class=\"row\" style=\"border-collapse: collapse; border-spacing: 0; width: 100%\">\n"
                    + "                        <tr>\n"
                    + "                          <td class=\"shop-name__cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; text-align: -webkit-center;\"\" >\n"
                    + "                            <img src=\"http://zetegral.website/krhgImages/KRHGblack.png\" width=\"240px\">\n"
                    + "                          </td>\n"
                    + "                        </tr>\n"
                    + "                      </table>\n"
                    + "                    </td>\n"
                    + "                  </tr>\n"
                    + "                </table>\n"
                    + "              </center>\n"
                    + "            </td>\n"
                    + "          </tr>\n"
                    + "        </table>\n"
                    + "\n"
                    + "        <br><br>\n"
                    + "        \n"
                    + "        <table class=\"row content\" style=\"border-collapse: collapse; border-spacing: 0; width: 100%\">\n"
                    + "          <tr>\n"
                    + "            <td class=\"content__cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px\">\n"
                    + "              <center>\n"
                    + "                <table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">\n"
                    + "                  <tr>\n"
                    + "                    <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                    + "                      <h2 style=\"font-size: 24px; font-weight: normal; margin: 0 0 10px\">Dear " + tempStaff.getName() + ",</h2>\n"
                    + "\n"
                    + "                      <p style=\"color: #777; font-size: 14px; line-height: 150%; margin: 0\">We have received your change password request. Please click on the button to reset your password.</p>\n"
                    + "                      <table class=\"row actions\" style=\"border-collapse: collapse; border-spacing: 0; margin-top: 20px; width: 100%\">\n"
                    + "                        <tr>\n"
                    + "                          <td class=\"actions__cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                    + "                            <table class=\"button main-action-cell\" style=\"border-collapse: collapse; border-spacing: 0; float: right; margin-right: 15px\">\n"
                    + "                              <tr>\n"
                    + "                                <td class=\"button__cell\" style=\"background: #080e66; border-radius: 4px; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding: 12px 15px; text-align: center\"\n"
                    + "                                  align=\"center\" bgcolor=\"#080e66\">\n"
                    + "                                  <a href=\"http://localhost:38201/Admin/resetPassword.xhtml?userID=" + base64encodedString + "\" class=\"button__text\" style=\"color: #fff; font-size: 14px; text-decoration: none\">Reset Password</a>\n"
                    + "                                </td>\n"
                    + "                              </tr>\n"
                    + "                            </table>\n"
                    + "                          </td>\n"
                    + "                        </tr>\n"
                    + "                      </table>\n"
                    + "                    </td>\n"
                    + "                  </tr>\n"
                    + "                </table>\n"
                    + "              </center>\n"
                    + "            </td>\n"
                    + "          </tr>\n"
                    + "        </table>\n"
                    + "        <table class=\"row footer\" style=\"border-collapse: collapse; border-spacing: 0; border-top-color: #e5e5e5; border-top-style: solid; border-top-width: 1px; width: 100%\">\n"
                    + "          <tr>\n"
                    + "            <td class=\"footer__cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding: 35px 0\">\n"
                    + "              <center>\n"
                    + "                <table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">\n"
                    + "                  <tr>\n"
                    + "                    <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                    + "                      <p class=\"disclaimer__subtext\" style=\"color: #999; font-size: 14px; line-height: 150%; margin: 0\">If you have any questions, contact us at\n"
                    + "                        <a href=\"#\" style=\"color: #080e66; font-size: 14px; text-decoration: none\">contact@KRHG.com</a>\n"
                    + "                      </p>\n"
                    + "                    </td>\n"
                    + "                  </tr>\n"
                    + "                </table>\n"
                    + "              </center>\n"
                    + "            </td>\n"
                    + "          </tr>\n"
                    + "        </table>\n"
                    + "        \n"
                    + "      </td>\n"
                    + "    </tr>\n"
                    + "  </table>\n"
                    + "</body>\n"
                    + "</html>";
            sendWebPage(email, "Reset Password", content);

            out.println("<script type=\"text/javascript\">");
            out.println("alert('An email has been sent');");
            out.println("</script>");
        }

        return "login.xhtml?faces-redirect=true";
    }

    public String updateProfile() throws NoResultException {
        staffSessionLocal.updateStaff(loggedInStaff);
        Logging l = new Logging("Staff", "Update " + loggedInStaff.getName() + "'s profile", loggedInStaff.getName());
        logSessionLocal.createLogging(l);

        return "profile.xhtml?faces-redirect=true";
    }

    public static void sendEmail(String recipient, String subject, String msg) {

        final String username = "automessage.kentridgehotelgroup@gmail.com";
        final String password = "krhg1234";

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

    public static void sendWebPage(String recipient, String subject, String content) {

        final String username = "automessage.kentridgehotelgroup@gmail.com";
        final String password = "krhg1234";

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
            message.setContent(content, "text/html");

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

    public String getTestSt() {
        return testSt;
    }

    public void setTestSt(String testSt) {
        this.testSt = testSt;
    }

}
