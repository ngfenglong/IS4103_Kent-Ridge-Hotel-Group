package managedBean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import entity.HouseKeepingOrder;
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
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import sessionBeans.BookingSessionLocal;
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

    //self-checkin
    private Long checkinBookingID;
    private String checkinLastName;
    private String checkinFirstname;
    private String checkinContact;
    private String checkinEmail;
    private PaymentTransaction checkinPayment;
    private String checkinPassport;

    private int checkinStandard;
    private int checkinPremium;
    private int checkinDeluxe;
    private int checkinSuite;
    private int checkinPenthouse;

    private List<String> allocatedRoomNumbers;

    private String hotelCode;

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

    public KioskmanagedBean() {
    }

    public String searchCheckin() throws IOException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        PrintWriter out = response.getWriter();
        try {
            checkinPayment = getPaymentTransactionWithbookingID();

            if (checkinPayment == null) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('No result found');");
                out.println("</script>");
                return "checkin.xhtml?faces-redirect=true";
            } else {

                for (RoomBooking rb : checkinPayment.getRoomsBooked()) {
                    if (rb.getRoomType().equalsIgnoreCase("standard")) {
                        setCheckinStandard(checkinStandard++);
                    } else if (rb.getRoomType().equalsIgnoreCase("Premium")) {
                        setCheckinPremium(checkinPremium++);
                    } else if (rb.getRoomType().equalsIgnoreCase("Deluxe")) {
                        setCheckinDeluxe(checkinDeluxe++);
                    } else if (rb.getRoomType().equalsIgnoreCase("Suite")) {
                        setCheckinSuite(checkinSuite++);
                    } else if (rb.getRoomType().equalsIgnoreCase("Penthouse")) {
                        setCheckinPenthouse(checkinPenthouse++);
                    }
                }
                return "checkinResult.xhtml?faces-redirect=true";
            }
        } catch (NoResultException e) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('No result found');");
            out.println("</script>");
            return "checkin.xhtml?faces-redirect=true";
        }
    }

    public PaymentTransaction getPaymentTransactionWithbookingID() throws NoResultException {
        for (PaymentTransaction pt : paymentTransactionSessionLocal.getAllPaymentTransaction()) {
            if (pt.getPayer().getLastName().equalsIgnoreCase(checkinLastName)) {
                return pt;
            }
        }
        return null;

    }

    public String searchCheckout() throws NoResultException {
        checkoutRoombooking = bookSessionLocal.getRoomBookingByRoomNumber(checkoutRoomNumber, "Occupied", hotelCode).get(0);
        checkoutRoom = getRoomWithRoomNumber();
        checkoutName = checkoutRoombooking.getLastName() + " " + checkoutRoombooking.getFirstName();
        //checkOutcharges =checkoutRoombooking.get from the food or extra stuff that happen
        checkoutRoomType = checkoutRoombooking.getRoomType();
        checkoutPaymentTransaction = getPaymentTransactionWithRoombookingID();
        checkoutBookingNumber = checkoutPaymentTransaction.getTransactionID();

        return "checkouttimer.xhtml?faces-redirect=true";
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

    public Room getRoomWithRoomNumber() {
        for (Room room : roomSessionLocal.getAllRooms()) {
            if (room.getRoomNumber().equals(checkoutRoomNumber)) {
                return room;
            }
        }
        return null;
    }

    public String allocateRoom() throws NoResultException, IOException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        PrintWriter out = response.getWriter();
        if (checkinPassport.equals(checkinPayment.getRoomsBooked().get(0).getPassportNum())) {
            allocatedRoomNumbers = new ArrayList<>();
            for (RoomBooking rm : checkinPayment.getRoomsBooked()) {
                String roomtype = rm.getRoomType();
                //Room room = roomSessionLocal.getRoomByType(roomstatus, hotelCode,roomtype);
                //get first result on the list 
                Room bookedRoom = null;
                rm.setBookedRoom(bookedRoom);
                allocatedRoomNumbers.add(bookedRoom.getRoomNumber());
                bookSessionLocal.updateRoomBooking(rm);
                bookedRoom.setStatus("Occupied");
                roomSessionLocal.updateRoom(bookedRoom);
            }
            return "roomallocation.xhtml?faces-redirect=true";
        } else {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Invalid passport Number');");
            out.println("</script>");
            return "checkinresult.xhtml?faces-redirect=true";
        }
    }

    public String continueAllocation() {
        allocatedRoomNumbers.remove(0);
        if (allocatedRoomNumbers.size() == 0) {
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

}
