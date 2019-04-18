package managedBean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import entity.FoodOrder;
import entity.Hotel;
import entity.HouseKeepingOrder;
import entity.LaundryOrder;
import entity.MinibarOrder;
import entity.PaymentTransaction;
import entity.Room;
import entity.RoomBooking;
import error.NoResultException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import sessionBeans.BookingSessionLocal;
import sessionBeans.HotelSessionLocal;
import sessionBeans.HouseKeepingOrderSessionLocal;
import sessionBeans.PaymentTransactionSessionLocal;
import sessionBeans.RoomSessionLocal;

/**
 *
 * @author Congx2
 */
@ManagedBean
@SessionScoped
public class KioskmanagedBean implements Serializable {

    /**
     * Creates a new instance of KioskmanagedBean
     */
    @EJB
    private RoomSessionLocal roomSessionLocal;
    @EJB
    private BookingSessionLocal bookSessionLocal;
    @EJB
    private PaymentTransactionSessionLocal paymentTransactionSessionLocal;
    @EJB
    private HouseKeepingOrderSessionLocal housekeepingOrderSessionlocal;
    @EJB
    private HotelSessionLocal hotelsessionlocal;

    //login
    private String loginHotelCode;
    private String loginPassword;

    //self-checkin
    private String CheckinName;
    private Long checkinBookingID;
    private String checkinLastName;
    private String checkinFirstname;
    private String checkinContact;
    private String checkinEmail;
    private PaymentTransaction checkinPayment;
    private String checkinPassport;
    private int checkinNumberOfRoomBooking;

    private int checkinStandard;
    private int checkinPremium;
    private int checkinDeluxe;
    private int checkinSuite;
    private int checkinPenthouse;

    private List<String> allocatedRoomNumbers;

    private String hotelCode = "KRN";
// cahnge back hotelcode
    //check out
    private String checkoutRoomNumber;
    private RoomBooking checkoutRoombooking;

    private String checkoutName;
    private double checkOutcharges;
    private String checkoutRoomType;
    private Long checkoutBookingNumber;
    private String checkoutPassport;
    private PaymentTransaction checkoutPaymentTransaction;
    private Room checkoutRoom;

    private String GSTPrice;
    private double totaltotalPrice = 0;
    private double GST;
    private double TAX;

    public KioskmanagedBean() {
    }

    public String login() throws IOException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        PrintWriter out = response.getWriter();
        if (loginHotelCode.equals(loginPassword)) {
            if (getHotel() == null) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Invalid credetial');");
                out.println("</script>");
                return "login.xhtml?faces-redirect=true";
            } else {
                hotelCode = getHotel().getHotelCodeName();
                return "";
            }

        } else {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Invalid credetial');");
            out.println("</script>");
            return "login.xhtml?faces-redirect=true";
        }

    }

    public Hotel getHotel() {
        for (Hotel h : hotelsessionlocal.getAllHotels()) {
            if (h.getHotelCodeName().equals(loginHotelCode)) {
                return h;
            }
        }
        return null;
    }

    public String searchCheckin() throws IOException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        PrintWriter out = response.getWriter();
        try {
            checkinPayment = getPaymentTransactionWithbookingID();

            if (checkinPayment == null) {
                checkinBookingID = null;
                checkinLastName = null;
                out.println("<script type=\"text/javascript\">");
                out.println("alert('No result found');");
                out.println("</script>");
                return "checkin.xhtml";
            }
            if (nevercheckCheckinBefore() == true) {
                checkinNumberOfRoomBooking = checkinPayment.getRoomsBooked().size();
                checkinPassport = hidepassport(checkinPayment.getRoomsBooked().get(0).getPassportNum());
                checkinEmail = checkinPayment.getPayer().getEmail();
                checkinContact = checkinPayment.getPayer().getMobileNum();
                CheckinName = checkinLastName + " " + checkinLastName;
                for (RoomBooking rb : checkinPayment.getRoomsBooked()) {
                    if (rb.getRoomType().equalsIgnoreCase("standard")) {
                        checkinStandard++;
                    } else if (rb.getRoomType().equalsIgnoreCase("Premium")) {
                        checkinPremium++;
                    } else if (rb.getRoomType().equalsIgnoreCase("Deluxe")) {
                        checkinDeluxe++;
                    } else if (rb.getRoomType().equalsIgnoreCase("Suite")) {
                        checkinSuite++;
                    } else if (rb.getRoomType().equalsIgnoreCase("Penthouse")) {
                        checkinPenthouse++;
                    }
                }
                return "checkinResult.xhtml?faces-redirect=true";
            } else {
                checkinBookingID = null;
                checkinLastName = null;
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Room checked in');");
                out.println("</script>");
                return "checkin.xhtml";
            }
        } catch (NoResultException e) {
            checkinBookingID = null;
            checkinLastName = null;
            out.println("<script type=\"text/javascript\">");
            out.println("alert('No result found');");
            out.println("</script>");
            return "checkin.xhtml";
        }
    }

    public boolean nevercheckCheckinBefore() {
        if (checkinPayment.getRoomsBooked().get(0).getBookedRoom() == null) {
            return true;
        }
        return false;
    }

    public PaymentTransaction getPaymentTransactionWithbookingID() throws NoResultException {
        for (PaymentTransaction pt : paymentTransactionSessionLocal.getAllPaymentTransaction()) {
            if (pt.getPayer().getLastName().equalsIgnoreCase(checkinLastName)) {
                return pt;
            }
        }
        return null;

    }

    public String searchCheckout() throws IOException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        PrintWriter out = response.getWriter();
        try {
            //Room checkoutRoombooking = roomSessionLocal.getRoom(roomNumber,hotelcode);
            checkoutRoom = getRoom();
            if (checkoutRoom == null) {
                checkoutRoomNumber = null;
                out.println("<script type=\"text/javascript\">");
                out.println("alert('No result found');");
                out.println("</script>");
                return "checkout.xhtml";
            }

            checkoutRoombooking = getRoombooking();
            if (checkoutRoombooking == null) {
                checkoutRoomNumber = null;
                out.println("<script type=\"text/javascript\">");
                out.println("alert('No result found');");
                out.println("</script>");
                return "checkout.xhtml";
            }

            checkoutName = checkoutRoombooking.getLastName() + " " + checkoutRoombooking.getFirstName();

            //checkOutcharges =checkoutRoombooking.get from the food or extra stuff that happen
            checkoutRoomType = checkoutRoombooking.getRoomType();
            checkoutPaymentTransaction = getPaymentTransactionWithRoombookingID();
            checkoutBookingNumber = checkoutPaymentTransaction.getTransactionID();

            return "checkouttimer.xhtml?faces-redirect=true";

        } catch (NoResultException e) {
            checkoutRoomNumber = null;
            out.println("<script type=\"text/javascript\">");
            out.println("alert('No result found');");
            out.println("</script>");
            return "checkout.xhtml?faces-redirect=true";
        }
    }

    public PaymentTransaction getPaymentTransactionWithRoombookingID() throws NoResultException {
        for (PaymentTransaction pt : paymentTransactionSessionLocal.getAllPaymentTransaction()) {
            for (RoomBooking rm : pt.getRoomsBooked()) {
                if (rm.getRoomBookingID().equals(checkoutRoombooking.getRoomBookingID())) {
                    return pt;
                }
            }
        }
        return null;
    }

    public String hidepassport(String passport) {
        String newstring = "****";
        newstring += passport.substring((passport.length() - 1) / 2, passport.length() - 1);
        return newstring;
    }

    public RoomBooking getRoombooking() {
        try {
            for (RoomBooking rb : bookSessionLocal.getAllRoomBookingByStatus("checkedin", hotelCode)) {
                if (rb.getBookedRoom().getRoomID().equals(checkoutRoom.getRoomID())) {
                    return rb;
                }
            }
            return null;
        } catch (NoResultException e) {
            return null;
        }
    }

    public Room getRoom() {
        for (Room rm : roomSessionLocal.getAllRooms()) {
            if (rm.getRoomNumber().equals(checkoutRoomNumber) && rm.getHotel().getHotelCodeName().equals(hotelCode) && rm.getStatus().equalsIgnoreCase("occupied")) {
                return rm;
            }
        }
        return null;
    }

    public String allocateRoom() throws NoResultException, IOException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        PrintWriter out = response.getWriter();
        System.out.println("AllocateRoom");
        allocatedRoomNumbers = new ArrayList<>();
        for (RoomBooking rm : checkinPayment.getRoomsBooked()) {
            String roomtype = rm.getRoomType();

            Room bookedRoom = roomSessionLocal.getSingleRoomByType(roomtype, hotelCode, "Available").get(0);
            rm.setBookedRoom(bookedRoom);
            rm.setStatus("checkedin");
            allocatedRoomNumbers.add(bookedRoom.getRoomNumber());
            bookSessionLocal.updateRoomBooking(rm);
            bookedRoom.setStatus("Occupied");
            roomSessionLocal.updateRoom(bookedRoom);
        }
        return "roomallocation.xhtml?faces-redirect=true";

    }

    public String continueAllocation() {
        allocatedRoomNumbers.remove(0);
        if (allocatedRoomNumbers.isEmpty()) {
            CheckinName = null;
            checkinBookingID = null;
            checkinLastName = null;
            checkinFirstname = null;
            checkinContact = null;
            checkinEmail = null;
            checkinPayment = null;
            checkinPassport = null;
            checkinNumberOfRoomBooking = 0;

            checkinStandard = 0;
            checkinPremium = 0;
            checkinDeluxe = 0;
            checkinSuite = 0;
            checkinPenthouse = 0;

            return "thankyou.xhtml";
        } else {

            return "roomallocation.xhtml?faces-redirect=true";
        }
    }

    public String docheckout() throws NoResultException {
        checkoutRoombooking.setStatus("checkedout");
        checkoutRoom.setStatus("unavailable");
        roomSessionLocal.updateRoom(checkoutRoom);
        bookSessionLocal.updateRoomBooking(checkoutRoombooking);
        createHousekeepingRequest();
//sEND email
        checkoutRoomNumber = null;
        checkoutRoombooking = null;

        checkoutName = null;
        checkOutcharges = 0;
        checkoutRoomType = null;
        checkoutBookingNumber = null;
        checkoutPassport = null;
        checkoutPaymentTransaction = null;
        checkoutRoom = null;
        return "checkoutConfirmation.xhtml";
    }

    public void createHousekeepingRequest() {
        HouseKeepingOrder HKO = new HouseKeepingOrder();
        HKO.setRoom(checkoutRoom);
        HKO.setOrderDateTime(new Date());
        HKO.setStatus("incomplete");
        HKO.setRequestType("housekeeping");
        HKO.setSpecialRequest("Prepare for the next customer by 3pm.");
        HKO.setLevel(getLevel(checkoutRoomNumber));

        housekeepingOrderSessionlocal.createHouseKeepingOrder(HKO);

    }

    public int getLevel(String roomnumber) {
        if (roomnumber.length() == 3) {
            return Integer.parseInt(roomnumber.substring(0, 1));
        } else {
            return Integer.parseInt(roomnumber.substring(0, 2));
        }
    }

    public String getCheckinFirstname() {
        return checkinFirstname;
    }

    public void setCheckinFirstname(String checkinFirstname) {
        this.checkinFirstname = checkinFirstname;
    }

    public String getCheckinContact() {
        return checkinContact;
    }

    public void setCheckinContact(String checkinContact) {
        this.checkinContact = checkinContact;
    }

    public String getCheckinEmail() {
        return checkinEmail;
    }

    public void setCheckinEmail(String checkinEmail) {
        this.checkinEmail = checkinEmail;
    }

    public PaymentTransaction getCheckinPayment() {
        return checkinPayment;
    }

    public void setCheckinPayment(PaymentTransaction checkinPayment) {
        this.checkinPayment = checkinPayment;
    }

    public Long getCheckinBookingID() {
        return checkinBookingID;
    }

    public void setCheckinBookingID(Long checkinBookingID) {
        this.checkinBookingID = checkinBookingID;
    }

    public String getCheckinLastName() {
        return checkinLastName;
    }

    public void setCheckinLastName(String checkinLastName) {
        this.checkinLastName = checkinLastName;
    }

    public String getCheckinPassport() {
        return checkinPassport;
    }

    public void setCheckinPassport(String checkinPassport) {
        this.checkinPassport = checkinPassport;
    }

    public int getCheckinStandard() {
        return checkinStandard;
    }

    public void setCheckinStandard(int checkinStandard) {
        this.checkinStandard = checkinStandard;
    }

    public int getCheckinPremium() {
        return checkinPremium;
    }

    public void setCheckinPremium(int checkinPremium) {
        this.checkinPremium = checkinPremium;
    }

    public int getCheckinDeluxe() {
        return checkinDeluxe;
    }

    public void setCheckinDeluxe(int checkinDeluxe) {
        this.checkinDeluxe = checkinDeluxe;
    }

    public int getCheckinSuite() {
        return checkinSuite;
    }

    public void setCheckinSuite(int checkinSuite) {
        this.checkinSuite = checkinSuite;
    }

    public int getCheckinPenthouse() {
        return checkinPenthouse;
    }

    public void setCheckinPenthouse(int checkinPenthouse) {
        this.checkinPenthouse = checkinPenthouse;
    }

    public List<String> getAllocatedRoomNumbers() {
        return allocatedRoomNumbers;
    }

    public void setAllocatedRoomNumbers(List<String> allocatedRoomNumbers) {
        this.allocatedRoomNumbers = allocatedRoomNumbers;
    }

    public String getCheckoutRoomNumber() {
        return checkoutRoomNumber;
    }

    public void setCheckoutRoomNumber(String checkoutRoomNumber) {
        this.checkoutRoomNumber = checkoutRoomNumber;
    }

    public RoomBooking getCheckoutRoombooking() {
        return checkoutRoombooking;
    }

    public void setCheckoutRoombooking(RoomBooking checkoutRoombooking) {
        this.checkoutRoombooking = checkoutRoombooking;
    }

    public String getCheckoutName() {
        return checkoutName;
    }

    public void setCheckoutName(String checkoutName) {
        this.checkoutName = checkoutName;
    }

    public double getCheckOutcharges() {
        return checkOutcharges;
    }

    public void setCheckOutcharges(double checkOutcharges) {
        this.checkOutcharges = checkOutcharges;
    }

    public String getCheckoutRoomType() {
        return checkoutRoomType;
    }

    public void setCheckoutRoomType(String checkoutRoomType) {
        this.checkoutRoomType = checkoutRoomType;
    }

    public Long getCheckoutBookingNumber() {
        return checkoutBookingNumber;
    }

    public void setCheckoutBookingNumber(Long checkoutBookingNumber) {
        this.checkoutBookingNumber = checkoutBookingNumber;
    }

    public String getCheckoutPassport() {
        return checkoutPassport;
    }

    public void setCheckoutPassport(String checkoutPassport) {
        this.checkoutPassport = checkoutPassport;
    }

    public PaymentTransaction getCheckoutPaymentTransaction() {
        return checkoutPaymentTransaction;
    }

    public void setCheckoutPaymentTransaction(PaymentTransaction checkoutPaymentTransaction) {
        this.checkoutPaymentTransaction = checkoutPaymentTransaction;
    }

    public String getLoginHotelCode() {
        return loginHotelCode;
    }

    public void setLoginHotelCode(String loginHotelCode) {
        this.loginHotelCode = loginHotelCode;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public int getCheckinNumberOfRoomBooking() {
        return checkinNumberOfRoomBooking;
    }

    public void setCheckinNumberOfRoomBooking(int checkinNumberOfRoomBooking) {
        this.checkinNumberOfRoomBooking = checkinNumberOfRoomBooking;
    }

    public String getCheckinName() {
        return CheckinName;
    }

    public void setCheckinName(String CheckinName) {
        this.CheckinName = CheckinName;
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

    public String minibarPrice() {

        List<RoomBooking> rb = checkoutPaymentTransaction.getRoomsBooked();
        int totalPrice = 0;

        for (RoomBooking rbb : rb) {
            for (MinibarOrder mbO : rbb.getListOfMinibarOrders()) {
                totalPrice += mbO.getTotalPrice();
            }
        }
        totaltotalPrice += totalPrice;
        return "$" + totalPrice;
    }

    public String roomServicePrice() {
        List<RoomBooking> rb = checkoutPaymentTransaction.getRoomsBooked();
        int totalPrice = 0;
        for (RoomBooking rbb : rb) {
            for (FoodOrder mbO : rbb.getListOfFoodOrders()) {
                totalPrice += mbO.getTotalPrice();
            }
        }
        totaltotalPrice += totalPrice;
        return "$" + totalPrice;
    }

    public String laundryPrice() {
        List<RoomBooking> rb = checkoutPaymentTransaction.getRoomsBooked();
        int totalPrice = 0;
        for (RoomBooking rbb : rb) {
            for (LaundryOrder mbO : rbb.getListOfLaundryOrders()) {
                totalPrice += mbO.getTotalPrice();
            }
        }
        totaltotalPrice += totalPrice;
        return "$" + totalPrice;
    }

    public String GSTPrice() {
        double gst = totaltotalPrice * 0.1;
        GST = gst;
        return "$" + gst;
    }

    public String TaxPrice() {
        double tax = totaltotalPrice * 0.07;
        TAX = tax;
        return "$" + tax;
    }
    
    public String totaltotaltotalPrice(){
       totaltotalPrice = totaltotalPrice + TAX + GST;
       return "$"+totaltotalPrice;
    }
}
