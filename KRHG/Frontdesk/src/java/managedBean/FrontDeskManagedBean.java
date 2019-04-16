/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.CreditCard;
import entity.Customer;
import entity.FunctionRoom;
import entity.PaymentTransaction;
import entity.Room;
import entity.RoomBooking;
import entity.RoomPricing;
import error.NoResultException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
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
import sessionBeans.FunctionRoomBookingSessionLocal;
import sessionBeans.FunctionRoomSessionLocal;
import sessionBeans.PaymentTransactionSessionLocal;
import sessionBeans.RoomPricingSessionLocal;
import sessionBeans.RoomSessionLocal;

/**
 *
 * @author Congx2
 */
@ManagedBean
@SessionScoped
public class FrontDeskManagedBean implements Serializable {

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
    @EJB
    private FunctionRoomBookingSessionLocal functionRoomBookingSessionLocal;
    @EJB
    private FunctionRoomSessionLocal functionroomSessionlocal;
    @EJB
    private RoomPricingSessionLocal roomPricingSessionLocal;
    private String mode;

    //customer check in
    private String customerName;
    private String customerFirstName;
    private String customerLastName;
    private String customerRoom;
    private String checkinPassportNumber;
    private List<PaymentTransaction> todaysbookings;
    private List<RoomBooking> Searchbookings;
    private PaymentTransaction roombooking;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Date checkinDate = new Date();
    private Date checkoutDate;
    private String checkinDateString = sdf.format(checkinDate);
    private String checkoutDateString;

    //customer check out
    private List<RoomBooking> todayCheckOutRoom;
    private String checkoutRoom;
    private List<RoomBooking> checkOutRoomResult;

    //Cusrtomer walk in
    private String walkinRoomtype;
    private int walkinPax;
    private int walkinNumberOfday;
    private List<Room> walkinAvailableRoom;
    private double roomPrice;
    private Room selectedRoom;
    private int numOfNights;
    private double totalPrice;
    private boolean hasExtraBed;

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

    private PaymentTransaction selectedCheckInTransaction;

    //payment
    private String paymentNameOnCard;
    private Date paymentExpiryDate;
    private String paymentDigits;
    private String paymentCVV;

    //hotel code
    private String hotelCode = "KRG";

    //reservation 
    private List<FunctionRoom> allFunctionrooms;

    //room pricing 
    private List<RoomPricing> listofPricing;

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

    public void getFunctionRoomWithHotelCode() {
        List<FunctionRoom> functionrooms = new ArrayList<>();
        for (FunctionRoom frb : functionroomSessionlocal.getAllFunctionRooms()) {
            if (frb.getHotel().getHotelCodeName().equals(hotelCode)) {
                functionrooms.add(frb);
            }
        }
        setAllFunctionrooms(functionrooms);
    }

    public List<FunctionRoom> getAllFunctionrooms() {
        getFunctionRoomWithHotelCode();
        return allFunctionrooms;
    }

    public void setAllFunctionrooms(List<FunctionRoom> allFunctionrooms) {
        this.allFunctionrooms = allFunctionrooms;
    }

    public String checkAvailability() {
        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //checkinDate = new Date();
        //System.out.println(checkoutDateString);
        return "walkinState.xhtml?faces-redirect=true";
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
        checkinEmail = PT.getPayer().getEmail();
        roombooking = PT;
        mode = "online";
        return "checkinResult.xhtml?faces-redirect=true";
    }

    public String walkinSelectRoom(Room rm) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        selectedRoom = rm;
        totalPrice = roomPrice * numOfNights;
        checkinDate = new Date();
        c.setTime(checkinDate);
        c.add(Calendar.DATE, numOfNights); //this is to add the number of nights with the numOfNights variable (not used anymore)
        checkoutDate = c.getTime();
        checkinDateString = sdf.format(checkinDate);
        checkoutDateString = sdf.format(checkoutDate);
        return "walkinEnterCustDetails.xhtml?faces-redirect=true";
    }

    public String confirmPage() {
        return "walkinResultCustomerDetails.xhtml?faces-redirect=true";
    }

    public String confirmCheckin() throws ParseException, java.text.ParseException, NoResultException {
        //bookSessionLocal.assignBooking(roombooking);
        //this one i DO ok .
        //get room name and change the status to occupied 
        //roombooking.setRoom(to the above room)
        //change roombooking'Statuses
        //display the room details
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        RoomBooking rb1 = new RoomBooking();
        rb1.setBookInDate(new Date());
        rb1.setBookOutDate(format.parse("2019-03-13"));
        rb1.setStatus("checkedIn");
        rb1.setPrice(450.0);
        rb1.setBookedRoom(selectedRoom);
        rb1.setEmailAddress(checkinEmail);
        rb1.setPassportNum(checkinPassportNumber);
        rb1.setRoomType(selectedRoom.getRoomType());
        rb1.setFirstName(customerFirstName);
        rb1.setLastName(customerLastName);
        rb1.setHasExtraBed(false);
        bookSessionLocal.createRoomBooking(rb1);
        Room rm = roomSessionLocal.getRoomByName(selectedRoom.getRoomName());
        rm.setStatus("Occupied");
        roomSessionLocal.updateRoom(rm);

        mode = "online";
        return "summary.xhtml?faces-redirect=true";
    }

    public String walkinpayment() {
        return "payment.xhtml?faces-redirect=true";
    }

    public String walkinReturnToMenu() {
        return "index.xhtml?faces-redirect=true";
    }

    public String makePayment() throws ParseException, java.text.ParseException, NoResultException {
        CreditCard cc = new CreditCard();
        cc.setCardNum(encryptPassword(paymentDigits));
        cc.setCvv(encryptPassword(paymentCVV));
        cc.setExpiryDate(paymentExpiryDate);
        paymentTransactionSessionLocal.createCreditCard(cc);

        PaymentTransaction pt = new PaymentTransaction();
        pt.setCreditCard(paymentTransactionSessionLocal.getLastCreditCard());
        pt.setTotalPrice(totalPrice);
        pt.setInitialPayment(totalPrice);
        pt.setFinalPayment(totalPrice);
        pt.setPaymentType("Credit Card");
        pt.setTransactionDateTime(new Date());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        RoomBooking rb1 = new RoomBooking();
        rb1.setBookInDate(checkinDate);
        rb1.setBookOutDate(checkoutDate);
        rb1.setStatus("checkedIn");
        rb1.setPrice(totalPrice);
        rb1.setBookedRoom(selectedRoom);
        rb1.setEmailAddress(checkinEmail);
        rb1.setPassportNum(checkinPassportNumber);
        rb1.setRoomType(selectedRoom.getRoomType());
        rb1.setFirstName(customerFirstName);
        rb1.setLastName(customerLastName);
        rb1.setHasExtraBed(false);
        bookSessionLocal.createRoomBooking(rb1);
        Room rm = roomSessionLocal.getRoomByName(selectedRoom.getRoomName());
        rm.setStatus("Occupied");
        roomSessionLocal.updateRoom(rm);

        mode = "online";
        return "summary.xhtml?faces-redirect=true";
        //payment complete and roombooking is done here

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

    public List<PaymentTransaction> getAllBookings() throws NoResultException {
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

    public List<RoomBooking> getTodayCheckOutRoom() throws NoResultException {

        return bookSessionLocal.getAllRoomBookingByStatus("Occupied", null);
    }

    public void setTodayCheckOutRoom(List<RoomBooking> todayCheckOutRoom) {
        this.todayCheckOutRoom = todayCheckOutRoom;
    }

    public String searchRoomAvailable() throws NoResultException {
        List<Room> resultList = new ArrayList<Room>();
        for (Room r : roomSessionLocal.getAllRooms()) {
            if (r.getStatus().equals("Available") && r.getRoomType().equals(walkinRoomtype) && r.getRoomName().startsWith(hotelCode)) {
                Room temp = r;
                resultList.add(temp);
            }
        }
        String roomPriceName = hotelCode + "_" + getWalkinRoomtype();
        setRoomPrice(roomPricingSessionLocal.getRoomPricingByName(roomPriceName).getPrice());
        walkinAvailableRoom = resultList;
        setWalkinAvailableRoom(walkinAvailableRoom);
        setHasExtraBed(false);
        //mode = "walkin";        
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

    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public Room getSelectedRoom() {
        return selectedRoom;
    }

    public void setSelectedRoom(Room selectedRoom) {
        this.selectedRoom = selectedRoom;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public int getNumOfNights() {
        return numOfNights;
    }

    public void setNumOfNights(int numOfNights) {
        this.numOfNights = numOfNights;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCheckinDateString() {
        return checkinDateString;
    }

    public void setCheckinDateString(String checkinDateString) {
        this.checkinDateString = checkinDateString;
    }

    public String getCheckoutDateString() {
        return checkoutDateString;
    }

    public void setCheckoutDateString(String checkoutDateString) {
        this.checkoutDateString = checkoutDateString;
    }

    public boolean getHasExtraBed() {
        return hasExtraBed;
    }

    public void setHasExtraBed(boolean hasExtraBed) {
        this.hasExtraBed = hasExtraBed;
    }

    public List<RoomPricing> getListofPricing() {
        return listofPricing = getRoompricingbasedHotelCode();
    }

    public void setListofPricing(List<RoomPricing> listofPricing) {
        this.listofPricing = listofPricing;
    }

    public List<RoomPricing> getRoompricingbasedHotelCode() {
        List<RoomPricing> RPL = new ArrayList<>();
        for (RoomPricing rp : roomPricingSessionLocal.getAllRoomPricings()) {
            String[] roomsplit = rp.getPricingName().split("_");
            if (roomsplit[0].equalsIgnoreCase(hotelCode)) {
                RPL.add(rp);
            }

        }
        return RPL;
    }

    public String editNameAway(String value) {
        return value.split("_")[1];
    }

    public List<PaymentTransaction> getTodayOrLaterBookings() throws NoResultException {
        Date todayDate = java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        List<PaymentTransaction> returnList = new ArrayList<PaymentTransaction>();
        List<PaymentTransaction> filterList = paymentTransactionSessionLocal.getAllPaymentTransaction();
        for (PaymentTransaction pt : filterList) {
            if (pt.getFunctionRoomBooked() != null) {
                if (pt.getFunctionRoomBooked().getBookedFrom().compareTo(todayDate) >= 0) {
                    PaymentTransaction tempPT = pt;
                    returnList.add(tempPT);
                }
            } else {
                if (pt.getRoomsBooked().get(0).getBookInDate().compareTo(todayDate) >= 0) {
                    PaymentTransaction tempPT = pt;
                    returnList.add(tempPT);
                }
            }
        }
        return returnList;
    }

    public String getBookingDateStr(PaymentTransaction pt) throws NoResultException {
        Date todayDate = java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        if (pt.getFunctionRoomBooked() != null) {
            if (pt.getFunctionRoomBooked().getBookedFrom().compareTo(todayDate) >= 0) {
                return dateFormat.format(pt.getFunctionRoomBooked().getBookedFrom());
            }
        } else {
            if (pt.getRoomsBooked().get(0).getBookInDate().compareTo(todayDate) >= 0) {
                return dateFormat.format(pt.getRoomsBooked().get(0).getBookInDate());
            }
        }
        return "";
    }

    public RoomSessionLocal getRoomSessionLocal() {
        return roomSessionLocal;
    }

    public void setRoomSessionLocal(RoomSessionLocal roomSessionLocal) {
        this.roomSessionLocal = roomSessionLocal;
    }

    public BookingSessionLocal getBookSessionLocal() {
        return bookSessionLocal;
    }

    public void setBookSessionLocal(BookingSessionLocal bookSessionLocal) {
        this.bookSessionLocal = bookSessionLocal;
    }

    public CustomerSessionLocal getCustomerSessionLocal() {
        return customerSessionLocal;
    }

    public void setCustomerSessionLocal(CustomerSessionLocal customerSessionLocal) {
        this.customerSessionLocal = customerSessionLocal;
    }

    public PaymentTransactionSessionLocal getPaymentTransactionSessionLocal() {
        return paymentTransactionSessionLocal;
    }

    public void setPaymentTransactionSessionLocal(PaymentTransactionSessionLocal paymentTransactionSessionLocal) {
        this.paymentTransactionSessionLocal = paymentTransactionSessionLocal;
    }

    public FunctionRoomBookingSessionLocal getFunctionRoomBookingSessionLocal() {
        return functionRoomBookingSessionLocal;
    }

    public void setFunctionRoomBookingSessionLocal(FunctionRoomBookingSessionLocal functionRoomBookingSessionLocal) {
        this.functionRoomBookingSessionLocal = functionRoomBookingSessionLocal;
    }

    public FunctionRoomSessionLocal getFunctionroomSessionlocal() {
        return functionroomSessionlocal;
    }

    public void setFunctionroomSessionlocal(FunctionRoomSessionLocal functionroomSessionlocal) {
        this.functionroomSessionlocal = functionroomSessionlocal;
    }

    public Customer getEditCustomer() {
        return editCustomer;
    }

    public void setEditCustomer(Customer editCustomer) {
        this.editCustomer = editCustomer;
    }

    public PaymentTransaction getSelectedCheckInTransaction() {
        return selectedCheckInTransaction;
    }

    public void setSelectedCheckInTransaction(PaymentTransaction selectedCheckInTransaction) {
        this.selectedCheckInTransaction = selectedCheckInTransaction;
    }

}
