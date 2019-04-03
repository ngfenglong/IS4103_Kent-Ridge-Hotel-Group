/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.CreditCard;
import entity.Customer;
import entity.FunctionRoomBooking;
import entity.PaymentTransaction;
import entity.Room;
import entity.RoomBooking;
import error.NoResultException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import sessionBeans.BookingSessionLocal;
import sessionBeans.CustomerSessionLocal;
import sessionBeans.PaymentTransactionSessionLocal;
import sessionBeans.RoomSessionLocal;

/**
 *
 * @author Congx2
 */
@ManagedBean
@SessionScoped
public class FrontDeskManagedBean {

    /**
     * Creates a new instance of FrontDeskManagedBean
     */
    @EJB
    private RoomSessionLocal roomSessionLocal;
    @EJB
    private BookingSessionLocal bookSessionLocal;
    @EJB
    private CustomerSessionLocal customerSessionLocal;
    @EJB
    private PaymentTransactionSessionLocal paymentTransactionSessionLocal;

    private String mode;

    //customer check in
    private String customerName;
    private String customerRoom;
    private String checkinPassportNumber;
    private List<PaymentTransaction> todaysbookings;
    private List<RoomBooking> Searchbookings;
    private PaymentTransaction roombooking;
    private Date checkinDate;
    private Date checkoutDate;

    //customer check out
    private List<RoomBooking> todayCheckOutRoom;
    private String checkoutRoom;
    private List<RoomBooking> checkOutRoomResult;

    //Cusrtomer walk in
    private String walkinRoomtype;
    private int walkinPax;
    private int walkinNumberOfday;
    private List<Room> walkinAvailableRoom;

    //walk in details
    //reuse checkinPassportNumber for walk in too
    private String checkinName;
    private String checkinEmail;

    //manage account
    private List<Customer> allCustomer;
    //customer
    private String CreateCustomerEmail;
    private String CreateCustomerPassword;
    private String createCustomerMobileNumber;
    private String CreateCustomerFirstName;
    private String CreateCustomerLastName;
    private String createConfirmCustomerPassword;

    //customer edit
    private Customer editCustomer;
    private String editCustomerFirstName;
    private String editCustomerLastName;
    private int editCustomerPoint;
    private String editCustomerEmail;
    private String editCustomerMobileNumber;
    private boolean editCustomerStatus;

    //payment
    private String paymentNameOnCard;
    private Date paymentExpiryDate;
    private String paymentDigits;
    private String paymentCVV;

    //hotel code
    private String hotelCode;

    //reservation 
    private List<FunctionRoomBooking> allFunctionrooms;

    public FrontDeskManagedBean() {
    }

    public String getPaymentNameOnCard() {
        return paymentNameOnCard;
    }

    public void setPaymentNameOnCard(String paymentNameOnCard) {
        this.paymentNameOnCard = paymentNameOnCard;
    }

    public Date getPaymentExpiryDate() {
        return paymentExpiryDate;
    }

    public void setPaymentExpiryDate(Date paymentExpiryDate) {
        this.paymentExpiryDate = paymentExpiryDate;
    }

    public String getPaymentDigits() {
        return paymentDigits;
    }

    public void setPaymentDigits(String paymentDigits) {
        this.paymentDigits = paymentDigits;
    }

    public String getPaymentCVV() {
        return paymentCVV;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public void setPaymentCVV(String paymentCVV) {
        this.paymentCVV = paymentCVV;
    }

    public String getCheckinName() {
        return checkinName;
    }

    public void setCheckinName(String checkinName) {
        this.checkinName = checkinName;
    }

    public String getCheckinEmail() {
        return checkinEmail;
    }

    public void setCheckinEmail(String checkinEmail) {
        this.checkinEmail = checkinEmail;
    }

    public String getPassportNumber() {
        return checkinPassportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.checkinPassportNumber = passportNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String CustomerName) {
        this.customerName = CustomerName;
    }

    public String getCustomerRoom() {
        return customerRoom;
    }

    public void setCustomerRoom(String customerRoom) {
        this.customerRoom = customerRoom;
    }

    public List<FunctionRoomBooking> getAllFunctionrooms() {
        return allFunctionrooms;
    }

    public void setAllFunctionrooms(List<FunctionRoomBooking> allFunctionrooms) {
        this.allFunctionrooms = allFunctionrooms;
    }

    public String searchCustomerForCheckin() {
        try {
            Searchbookings = bookSessionLocal.getRoomBookingByPassportNum(checkinPassportNumber, hotelCode);
            checkinPassportNumber = null;
            return "checkinResult.xhtml?faces-redirect=true";
        } catch (NoResultException e) {
            return "checkinResult.xhtml?faces-redirect=true";
        }
    }

    public String checkin(PaymentTransaction PT) {
        checkinPassportNumber = PT.getRoomsBooked().get(0).getPassportNum();
        checkinDate = PT.getRoomsBooked().get(0).getBookInDate();
        checkoutDate = PT.getRoomsBooked().get(0).getBookOutDate();

        checkinName = PT.getPayer().getFirstName() + " " + PT.getPayer().getLastName();
        checkinEmail=PT.getPayer().getEmail();
        roombooking = PT;
        mode = "online";
        return "checkinResult.xhtml?faces-redirect=true";
    }

    public String confirmCheckin() {
        //bookSessionLocal.assignBooking(roombooking);
        //this one i DO ok .
        //get room name and change the status to occupied 
        //roombooking.setRoom(to the above room)
        //change roombooking'Statuses
        //display the room details
        mode = "online";
        return "summary.xhmtl?faces-redirect=true";
    }

    public String walkinpayment() {
        return "payment.xhtml?faces-redirect=true";
    }

    public String makePayment() {
        CreditCard cc = new CreditCard();
        cc.setCardNum(encryptPassword(paymentDigits));
        cc.setCvv(encryptPassword(paymentCVV));
        cc.setExpiryDate(paymentExpiryDate);
        //payment complete and roombooking is done here

        return null;
    }

    public String searchCheckout() {
        //bookSessionLocal.getRoombookingbyRoomNumber(String roomNumber,String status); check status, dont just anyhow get room. 

        //setCheckOutRoomResult(checkOutRoomResult);
        return "checkoutResult.xhtml?faces-redirect=true";
    }

    public String checkOut(RoomBooking rm) {
        //do check out 
        //update check out room and change roombooking status
        return "";
    }

    public List<PaymentTransaction> getTodaysbookings() throws NoResultException {
        return paymentTransactionSessionLocal.getAllPaymentTransaction();

    }

    public String convertDateFormat(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    public void setTodaysbookings(List<PaymentTransaction> todaysbookings) {
        this.todaysbookings = todaysbookings;
    }

    public List<RoomBooking> getSearchbookings() {
        return Searchbookings;
    }

    public void setSearchbookings(List<RoomBooking> Searchbookings) {
        this.Searchbookings = Searchbookings;
    }

    public List<Customer> getAllCustomer() {
        return allCustomer = customerSessionLocal.getAllCustomers();
    }

    public void setAllCustomer(List<Customer> allCustomer) {
        this.allCustomer = allCustomer;
    }

    public String createAccount() throws IOException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        PrintWriter out = response.getWriter();

        if (CreateCustomerPassword.equals(createConfirmCustomerPassword)) {
            Customer c = new Customer();
            c.setEmail(CreateCustomerEmail);
            c.setMobileNum(createCustomerMobileNumber);
            c.setFirstName(CreateCustomerFirstName);
            c.setLastName(CreateCustomerLastName);
            c.setPassword(encryptPassword(CreateCustomerPassword));
            c.setAccountStatus(true);
            c.setPoints(0);
            c.setDateJoined(java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            customerSessionLocal.createCustomer(c);

            CreateCustomerEmail = null;
            createCustomerMobileNumber = null;
            CreateCustomerLastName = null;
            CreateCustomerFirstName = null;
            CreateCustomerPassword = null;
            createConfirmCustomerPassword = null;

            out.println("<script type=\"text/javascript\">");
            out.println("alert('Register Succesful!');");
            out.println("</script>");

            return "walkin.xhtml?faces-redirect=true";

        } else {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Confirm Password doesn't match! !');");
            out.println("</script>");

            return "customerAccount.xhtml?faces-redirect=true";
        }
    }

    public String viewAccount(Customer customer) {
        editCustomer = customer;
        editCustomerFirstName = customer.getFirstName();
        editCustomerLastName = customer.getLastName();

        editCustomerPoint = customer.getPoints();
        editCustomerEmail = customer.getEmail();
        editCustomerMobileNumber = customer.getMobileNum();
        editCustomerStatus = customer.getAccountStatus();

        return "manageAccountEdit.xhtml?faces-redirect=true";
    }

    public int getEditCustomerPoint() {
        return editCustomerPoint;
    }

    public void setEditCustomerPoint(int editCustomerPoint) {
        this.editCustomerPoint = editCustomerPoint;
    }

    public String getEditCustomerEmail() {
        return editCustomerEmail;
    }

    public void setEditCustomerEmail(String editCustomerEmail) {
        this.editCustomerEmail = editCustomerEmail;
    }

    public String getEditCustomerMobileNumber() {
        return editCustomerMobileNumber;
    }

    public void setEditCustomerMobileNumber(String editCustomerMobileNumber) {
        this.editCustomerMobileNumber = editCustomerMobileNumber;
    }

    public boolean isEditCustomerStatus() {
        return editCustomerStatus;
    }

    public void setEditCustomerStatus(boolean editCustomerStatus) {
        this.editCustomerStatus = editCustomerStatus;
    }

    public String getCreateCustomerEmail() {
        return CreateCustomerEmail;
    }

    public void setCreateCustomerEmail(String CreateCustomerEmail) {
        this.CreateCustomerEmail = CreateCustomerEmail;
    }

    public String getCreateCustomerPassword() {
        return CreateCustomerPassword;
    }

    public void setCreateCustomerPassword(String CreateCustomerPassword) {
        this.CreateCustomerPassword = CreateCustomerPassword;
    }

    public String getCreateCustomerMobileNumber() {
        return createCustomerMobileNumber;
    }

    public void setCreateCustomerMobileNumber(String createCustomerMobileNumber) {
        this.createCustomerMobileNumber = createCustomerMobileNumber;
    }

    public String getCreateConfirmCustomerPassword() {
        return createConfirmCustomerPassword;
    }

    public void setCreateConfirmCustomerPassword(String createConfirmCustomerPassword) {
        this.createConfirmCustomerPassword = createConfirmCustomerPassword;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String UpdateAccount() throws NoResultException, IOException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        PrintWriter out = response.getWriter();
        editCustomer.setAccountStatus(editCustomerStatus);
        editCustomer.setFirstName(editCustomerFirstName);
        editCustomer.setLastName(editCustomerLastName);
        editCustomer.setEmail(editCustomerEmail);
        editCustomer.setPoints(editCustomerPoint);
        editCustomer.setMobileNum(editCustomerMobileNumber);
        customerSessionLocal.updateCustomer(editCustomer);
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Confirm Password doesn't match! !');");
        out.println("</script>");

        return "manageAccount.xhtml?faces-redirect=true";
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

    public String getCheckinPassportNumber() {
        return checkinPassportNumber;
    }

    public void setCheckinPassportNumber(String checkinPassportNumber) {
        this.checkinPassportNumber = checkinPassportNumber;
    }

    public List<RoomBooking> getTodayCheckOutRoom() {
        //bookSessionLocal.getbookingbyCheckoutDate(new Date());
        //to get today's date
        //bookSessionLocal.getAllRoomBookingByStatus(String Status)

        return null;
    }

    public void setTodayCheckOutRoom(List<RoomBooking> todayCheckOutRoom) {
        this.todayCheckOutRoom = todayCheckOutRoom;
    }

    public String searchRoomAvailable() {
        // return todayCheckOutRoom = bookSessionLocal.getAllRoomBookingByCheckOutDate(todayDate);
        //some algorithm to get availble room to view and return list of room, group by room type
        //wait feng long 
        mode = "walkin";
        setWalkinAvailableRoom(walkinAvailableRoom);

        return "walkinResult.xhtml?faces-redirect=true";

    }

    public String confirmWalkin(Room room) {

        return "walkinSummary.xhtml?faces-redirect=true";
    }

    public String getWalkinRoomtype() {
        return walkinRoomtype;
    }

    public void setWalkinRoomtype(String walkinRoomtype) {
        this.walkinRoomtype = walkinRoomtype;
    }

    public int getWalkinPax() {
        return walkinPax;
    }

    public void setWalkinPax(int walkinPax) {
        this.walkinPax = walkinPax;
    }

    public int getWalkinNumberOfday() {
        return walkinNumberOfday;
    }

    public void setWalkinNumberOfday(int walkinNumberOfday) {
        this.walkinNumberOfday = walkinNumberOfday;
    }

    public List<Room> getWalkinAvailableRoom() {

        //need to return pricing too
        return walkinAvailableRoom;
    }

    public void setWalkinAvailableRoom(List<Room> walkinAvailableRoom) {
        this.walkinAvailableRoom = walkinAvailableRoom;
    }

    public PaymentTransaction getRoombooking() {
        return roombooking;
    }

    public void setRoombooking(PaymentTransaction roombooking) {
        this.roombooking = roombooking;
    }

    public String getCheckoutRoom() {
        return checkoutRoom;
    }

    public void setCheckoutRoom(String checkoutRoom) {
        this.checkoutRoom = checkoutRoom;
    }

    public List<RoomBooking> getCheckOutRoomResult() {
        return checkOutRoomResult;
    }

    public void setCheckOutRoomResult(List<RoomBooking> checkOutRoomResult) {
        this.checkOutRoomResult = checkOutRoomResult;
    }

    public String getCreateCustomerFirstName() {
        return CreateCustomerFirstName;
    }

    public void setCreateCustomerFirstName(String CreateCustomerFirstName) {
        this.CreateCustomerFirstName = CreateCustomerFirstName;
    }

    public String getCreateCustomerLastName() {
        return CreateCustomerLastName;
    }

    public void setCreateCustomerLastName(String CreateCustomerLastName) {
        this.CreateCustomerLastName = CreateCustomerLastName;
    }

    public String getEditCustomerFirstName() {
        return editCustomerFirstName;
    }

    public void setEditCustomerFirstName(String editCustomerFirstName) {
        this.editCustomerFirstName = editCustomerFirstName;
    }

    public String getEditCustomerLastName() {
        return editCustomerLastName;
    }

    public void setEditCustomerLastName(String editCustomerLastName) {
        this.editCustomerLastName = editCustomerLastName;
    }

}
