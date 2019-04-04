/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.CreditCard;
import entity.ExtraSurcharge;
import entity.Feedback;
import entity.HolidaySurcharge;
import entity.Hotel;
import entity.HotelFacility;
import entity.MinibarItem;
import entity.PaymentTransaction;
import entity.Room;
import entity.RoomBooking;
import entity.RoomFacility;
import error.NoResultException;
import javax.inject.Named;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import sessionBeans.BookingSessionLocal;
import sessionBeans.FeedbackSessionLocal;
import sessionBeans.HotelFacilitySessionLocal;
import sessionBeans.HotelSessionLocal;
import sessionBeans.LogSessionLocal;
import sessionBeans.PaymentTransactionSessionLocal;
import sessionBeans.RoomFacilitySessionLocal;
import sessionBeans.RoomPricingSessionLocal;
import sessionBeans.RoomSessionLocal;

/**
 *
 * @author fengl
 */
@ManagedBean
@SessionScoped
public class WebsiteManagedBean implements Serializable {

    /**
     * Creates a new instance of WebsiteManagedBean
     */
    @EJB
    HotelSessionLocal hotelSessionLocal;
    @EJB
    HotelFacilitySessionLocal hotelFacilitySessionLocal;
    @EJB
    RoomFacilitySessionLocal roomFacilitySessionLocal;
    @EJB
    RoomSessionLocal roomSessionLocal;
    @EJB
    FeedbackSessionLocal feedbackSessionLocal;
    @EJB
    LogSessionLocal logSessionLocal;
    @EJB
    BookingSessionLocal bookingSessionLocal;
    @EJB
    RoomPricingSessionLocal roomPricingSessionLocal;
    @EJB
    PaymentTransactionSessionLocal paymentTransactionSessionLocal;

    @ManagedProperty(value = "#{websiteAuthenticationManagedBean}")
    private WebsiteAuthenticationManagedBean authBean;

    public WebsiteAuthenticationManagedBean getAuthBean() {
        return authBean;
    }

    public void setAuthBean(WebsiteAuthenticationManagedBean authBean) {
        this.authBean = authBean;
    }

    Hotel selectedHotel;
    String checkInTB;
    String checkOutTB;
    int guestTB;
    String roomTypeTB;
    int noOfRoomTB;

    String genderTB;
    String fNameTB;
    String lNameTB;
    String emailAddressTB;
    String contactNumberTB;
    String paymentMethod;
    String creditCardTB;
    String cvsTB;
    String expiryDateTB;
    String passportNumberTB;

    String pickUpLocationTB;
    int seatsTB;
    String pickUpDateTB;
    String pickUpTimeTB;

    List<Room> roomAvailable = new ArrayList<Room>();
    boolean hasRoom;

    public WebsiteManagedBean() {
    }

    public String viewHotel(Long hID) throws NoResultException {
        selectedHotel = hotelSessionLocal.getHotelByID(hID);

        return "booking1.xhtml?faces-redirect=true";
    }

    public String getDisplayRoomImage(String roomType) {
        if (roomType.equals("Standard")) {
            return "booking_1.jpg";
        } else if (roomType.equals("Deluxe")) {
            return "booking_2.jpg";
        } else if (roomType.equals("Premium")) {
            return "booking_3.jpg";
        } else if (roomType.equals("Suite")) {
            return "booking_4.jpg";
        } else {
            return "booking_5.jpg";
        }
    }

    public String checkAvailability() throws NoResultException, ParseException {
        List<RoomBooking> allBooking = bookingSessionLocal.getAllRoomBookingByStatus("checkedout", selectedHotel.getHotelCodeName());
        List<RoomBooking> checkList = new ArrayList<RoomBooking>();
        List<Room> returnList = new ArrayList<Room>();
        hasRoom = false;

        //Convert String to Date
        Date tempCheckIn = new SimpleDateFormat("yyyy-MM-dd").parse(checkInTB);
        Date tempCheckOut = new SimpleDateFormat("yyyy-MM-dd").parse(checkOutTB);

        //Get roombooking by type & Status
        if (allBooking != null) {
            for (RoomBooking rb : allBooking) {
                if (rb.getRoomType().equals(roomTypeTB) && rb.getStatus().equals("checkedout")) {
                    RoomBooking tempRoomBooking = rb;
                    checkList.add(tempRoomBooking);
                }
            }

            allBooking = checkList;
            checkList.clear();
            for (RoomBooking rb : allBooking) {
                // Didn't Hit the Booking Date
                if ((tempCheckIn.before(rb.getBookInDate()) && tempCheckOut.before(rb.getBookInDate())) || (tempCheckIn.after(rb.getBookInDate()) && tempCheckOut.after(rb.getBookInDate()))) {

                } // Hit the booking date AKA Not Available
                else {
                    RoomBooking tempRoomBooking = rb;
                    checkList.add(tempRoomBooking);
                }
            }

            if (checkList.size() < roomSessionLocal.getRoomByHotelNameAndRoomType(roomTypeTB, selectedHotel.getHotelCodeName()).size()) {
                hasRoom = true;
                List<Room> filterList = roomSessionLocal.getRoomByHotelNameAndRoomType(roomTypeTB, selectedHotel.getHotelCodeName());
                for (Room r : filterList) {
                    if (!r.getStatus().toLowerCase().equals("occupied")) {
                        Room tempRoom = r;
                        returnList.add(r);
                    }
                }
                roomAvailable = returnList;
            }
        } else {
            roomAvailable = roomSessionLocal.getRoomByHotelNameAndRoomType(roomTypeTB, selectedHotel.getHotelCodeName());
        }
        return "CheckAvail.xhtml?faces-redirect=true";
    }

    public String makePayment() throws ParseException, NoResultException {

        List<RoomBooking> roomsBooked = new ArrayList<RoomBooking>();
        PaymentTransaction p = new PaymentTransaction();

        double totalPrice = 0;
        for (int i = 0; i < noOfRoomTB; i++) {
            RoomBooking rb = new RoomBooking();
            rb.setBookInDate(new SimpleDateFormat("yyyy-MM-dd").parse(checkInTB));
            rb.setBookOutDate(new SimpleDateFormat("yyyy-MM-dd").parse(checkOutTB));
            rb.setBookingDate(java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            rb.setPassportNum(passportNumberTB);
            rb.setPrice(roomPricingSessionLocal.getRoomPricingByName(selectedHotel.getHotelCodeName() + "_" + roomTypeTB).getPrice());
            totalPrice += roomPricingSessionLocal.getRoomPricingByName(selectedHotel.getHotelCodeName() + "_" + roomTypeTB).getPrice();
            rb.setStatus("reserved");
            if (authBean.getLoggedInCustomer() == null) {
                rb.setBookedBy(authBean.getLoggedInCustomer());
                rb.setFirstName(fNameTB);
                rb.setLastName(lNameTB);
                rb.setEmailAddress(emailAddressTB);
                rb.setRoomType(roomTypeTB);
            } else {
                rb.setFirstName(fNameTB);
                rb.setLastName(lNameTB);
                rb.setEmailAddress(emailAddressTB);
            }
            if (!pickUpLocationTB.equals("-")) {
                rb.setHasTransport(true);
                rb.setTransportTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(pickUpDateTB + " " +pickUpTimeTB));
            } else {
                rb.setHasTransport(false);
            }
            bookingSessionLocal.createRoomBooking(rb);
            rb = bookingSessionLocal.getLastRoomBooking();
            roomsBooked.add(rb);
        }
        CreditCard cc = new CreditCard();
        cc.setCardNum(creditCardTB);
        cc.setCvv(cvsTB);
        cc.setExpiryDate(new SimpleDateFormat("yy/MM").parse(expiryDateTB));
        paymentTransactionSessionLocal.createCreditCard(cc);
        cc = paymentTransactionSessionLocal.getLastCreditCard();
        p.setCreditCard(cc);
        p.setTotalPrice(totalPrice);
        p.setInitialPayment(totalPrice);
        
        p.setPaymentType(paymentMethod);
        p.setRoomsBooked(roomsBooked);
        paymentTransactionSessionLocal.createPaymentTransaction(p);

        return "index.xhtml?faces-redirect=true";
    }

    public String goConfirmation() {

        return "confirmation.xhtml?faces-redirect=true";
    }

    public String goPayment() {
        if (authBean.getLoggedInCustomer() != null) {
            fNameTB = authBean.getLoggedInCustomer().getFirstName();
            lNameTB = authBean.getLoggedInCustomer().getLastName();
            emailAddressTB = authBean.getLoggedInCustomer().getEmail();
            genderTB = authBean.getLoggedInCustomer().getGender();
        }

        return "payment.xhtml?faces-redirect=true";
    }

    public String getRoomPrice() throws NoResultException {
        return "$"+roomPricingSessionLocal.getRoomPricingByName(selectedHotel.getHotelCodeName() + "_" + roomTypeTB).getPrice() + "0";
    //    return "120.0";
    }

    public List<HotelFacility> getSelectedHotelFacility(Long hID) throws NoResultException {
        Hotel tempHotel = hotelSessionLocal.getHotelByID(hID);
        return tempHotel.getHotelFacilities();
    }

    public List<RoomFacility> getDisplayRoomFacilities(String type) throws NoResultException {
        return hotelSessionLocal.getRoomDisplayByRoomType(selectedHotel.getHotelID(), type).getRoomFacilities();
    }

    public List<String> getHotelRoomTypes() throws NoResultException {
        return hotelSessionLocal.getRoomTypes(selectedHotel.getHotelID());
    }

    public List<Hotel> getAllHotels() {
        return hotelSessionLocal.getAllHotels();
    }

    public List<HotelFacility> getAllHotelFacility() {
        return hotelFacilitySessionLocal.getAllHotelFacilities();
    }

    public List<HolidaySurcharge> getAllHolidaySurcharge() {
        return roomSessionLocal.getAllHolidaySurcharge();
    }

    public List<MinibarItem> getAllMinibarItem() {
        return roomSessionLocal.getAllMinibarItem();
    }

    public List<RoomFacility> getAllRoomFacility() {
        return roomFacilitySessionLocal.getAllRoomFacilities();
    }

    public List<ExtraSurcharge> getAllSurcharge() {
        return roomSessionLocal.getAllExtraSurcharge();
    }

    public List<Feedback> getAllFeedbacks() {
        return feedbackSessionLocal.getAllFeedbacks();
    }

    public HotelSessionLocal getHotelSessionLocal() {
        return hotelSessionLocal;
    }

    public void setHotelSessionLocal(HotelSessionLocal hotelSessionLocal) {
        this.hotelSessionLocal = hotelSessionLocal;
    }

    public HotelFacilitySessionLocal getHotelFacilitySessionLocal() {
        return hotelFacilitySessionLocal;
    }

    public void setHotelFacilitySessionLocal(HotelFacilitySessionLocal hotelFacilitySessionLocal) {
        this.hotelFacilitySessionLocal = hotelFacilitySessionLocal;
    }

    public RoomFacilitySessionLocal getRoomFacilitySessionLocal() {
        return roomFacilitySessionLocal;
    }

    public void setRoomFacilitySessionLocal(RoomFacilitySessionLocal roomFacilitySessionLocal) {
        this.roomFacilitySessionLocal = roomFacilitySessionLocal;
    }

    public RoomSessionLocal getRoomSessionLocal() {
        return roomSessionLocal;
    }

    public void setRoomSessionLocal(RoomSessionLocal roomSessionLocal) {
        this.roomSessionLocal = roomSessionLocal;
    }

    public FeedbackSessionLocal getFeedbackSessionLocal() {
        return feedbackSessionLocal;
    }

    public void setFeedbackSessionLocal(FeedbackSessionLocal feedbackSessionLocal) {
        this.feedbackSessionLocal = feedbackSessionLocal;
    }

    public LogSessionLocal getLogSessionLocal() {
        return logSessionLocal;
    }

    public void setLogSessionLocal(LogSessionLocal logSessionLocal) {
        this.logSessionLocal = logSessionLocal;
    }

    public Hotel getSelectedHotel() {
        return selectedHotel;
    }

    public void setSelectedHotel(Hotel selectedHotel) {
        this.selectedHotel = selectedHotel;
    }

    public String getCheckInTB() {
        return checkInTB;
    }

    public void setCheckInTB(String checkInTB) {
        this.checkInTB = checkInTB;
    }

    public String getCheckOutTB() {
        return checkOutTB;
    }

    public void setCheckOutTB(String checkOutTB) {
        this.checkOutTB = checkOutTB;
    }

    public int getGuestTB() {
        return guestTB;
    }

    public void setGuestTB(int guestTB) {
        this.guestTB = guestTB;
    }

    public String getRoomTypeTB() {
        return roomTypeTB;
    }

    public void setRoomTypeTB(String roomTypeTB) {
        this.roomTypeTB = roomTypeTB;
    }

    public BookingSessionLocal getBookingSessionLocal() {
        return bookingSessionLocal;
    }

    public void setBookingSessionLocal(BookingSessionLocal bookingSessionLocal) {
        this.bookingSessionLocal = bookingSessionLocal;
    }

    public List<Room> getRoomAvailable() {
        return roomAvailable;
    }

    public void setRoomAvailable(List<Room> roomAvailable) {
        this.roomAvailable = roomAvailable;
    }

    public boolean isHasRoom() {
        return hasRoom;
    }

    public void setHasRoom(boolean hasRoom) {
        this.hasRoom = hasRoom;
    }

    public int getNoOfRoomTB() {
        return noOfRoomTB;
    }

    public void setNoOfRoomTB(int noOfRoomTB) {
        this.noOfRoomTB = noOfRoomTB;
    }

    public String getGenderTB() {
        return genderTB;
    }

    public void setGenderTB(String genderTB) {
        this.genderTB = genderTB;
    }

    public String getfNameTB() {
        return fNameTB;
    }

    public void setfNameTB(String fNameTB) {
        this.fNameTB = fNameTB;
    }

    public String getlNameTB() {
        return lNameTB;
    }

    public void setlNameTB(String lNameTB) {
        this.lNameTB = lNameTB;
    }

    public String getEmailAddressTB() {
        return emailAddressTB;
    }

    public void setEmailAddressTB(String emailAddressTB) {
        this.emailAddressTB = emailAddressTB;
    }

    public String getContactNumberTB() {
        return contactNumberTB;
    }

    public void setContactNumberTB(String contactNumberTB) {
        this.contactNumberTB = contactNumberTB;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCreditCardTB() {
        return creditCardTB;
    }

    public void setCreditCardTB(String creditCardTB) {
        this.creditCardTB = creditCardTB;
    }

    public String getCvsTB() {
        return cvsTB;
    }

    public void setCvsTB(String cvsTB) {
        this.cvsTB = cvsTB;
    }

    public String getExpiryDateTB() {
        return expiryDateTB;
    }

    public void setExpiryDateTB(String expiryDateTB) {
        this.expiryDateTB = expiryDateTB;
    }

    public String getPickUpLocationTB() {
        return pickUpLocationTB;
    }

    public void setPickUpLocationTB(String pickUpLocationTB) {
        this.pickUpLocationTB = pickUpLocationTB;
    }

    public int getSeatsTB() {
        return seatsTB;
    }

    public void setSeatsTB(int seatsTB) {
        this.seatsTB = seatsTB;
    }

    public String getPickUpDateTB() {
        return pickUpDateTB;
    }

    public void setPickUpDateTB(String pickUpDateTB) {
        this.pickUpDateTB = pickUpDateTB;
    }

    public String getPickUpTimeTB() {
        return pickUpTimeTB;
    }

    public void setPickUpTimeTB(String pickUpTimeTB) {
        this.pickUpTimeTB = pickUpTimeTB;
    }

    public String getPassportNumberTB() {
        return passportNumberTB;
    }

    public void setPassportNumberTB(String passportNumberTB) {
        this.passportNumberTB = passportNumberTB;
    }

    public RoomPricingSessionLocal getRoomPricingSessionLocal() {
        return roomPricingSessionLocal;
    }

    public void setRoomPricingSessionLocal(RoomPricingSessionLocal roomPricingSessionLocal) {
        this.roomPricingSessionLocal = roomPricingSessionLocal;
    }

    public PaymentTransactionSessionLocal getPaymentTransactionSessionLocal() {
        return paymentTransactionSessionLocal;
    }

    public void setPaymentTransactionSessionLocal(PaymentTransactionSessionLocal paymentTransactionSessionLocal) {
        this.paymentTransactionSessionLocal = paymentTransactionSessionLocal;
    }

}
