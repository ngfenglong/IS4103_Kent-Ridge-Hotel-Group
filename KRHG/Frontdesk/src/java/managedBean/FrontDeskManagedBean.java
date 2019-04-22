/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.CreditCard;
import entity.Customer;
import entity.ExtraSurcharge;
import entity.Feedback;
import entity.FoodOrder;
import entity.FunctionRoom;
import entity.HolidaySurcharge;
import entity.Hotel;
import entity.LaundryOrder;
import entity.Logging;
import entity.MinibarItem;
import entity.MinibarOrder;
import entity.PaymentTransaction;
import entity.Room;
import entity.RoomBooking;
import entity.RoomFacility;
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
import sessionBeans.FeedbackSessionLocal;
import sessionBeans.FunctionRoomBookingSessionLocal;
import sessionBeans.FunctionRoomSessionLocal;
import sessionBeans.HotelSessionLocal;
import sessionBeans.HouseKeepingOrderSessionLocal;
import sessionBeans.LogSessionLocal;
import sessionBeans.PaymentTransactionSessionLocal;
import sessionBeans.RoomFacilitySessionLocal;
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
    private HouseKeepingOrderSessionLocal houseKeepingOrderSessionLocal;
    @EJB
    private LogSessionLocal logSessionLocal;
    @EJB
    private FunctionRoomBookingSessionLocal functionRoomBookingSessionLocal;
    @EJB
    private FunctionRoomSessionLocal functionroomSessionlocal;
    @EJB
    private RoomPricingSessionLocal roomPricingSessionLocal;
    @EJB
    private HotelSessionLocal hotelSessionLocal;
    @EJB
    private FeedbackSessionLocal feedbackSessionLocal;
    @EJB
    private RoomFacilitySessionLocal roomFacilitySessionLocal;

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
    private Date checkinDate;
    private Date checkoutDate;
    private String checkinDateString;
    private String checkoutDateString;

    public Room selectedRoom;
    public Long selectedRoomID;

    //Create Room\
    public String tempHotelNameTB;
    public String roomName;
    public String roomNumber;
    public String roomType;
    public int roomPax;
    public String[] minibarItems;
    public String[] roomFacilities;
    public String addRoomHotelName;

    private String logActivityName;

    private String transactionID;
    private RoomBooking roomBookingToDisplay;
    private String checkoutRoomNumber;
    private RoomBooking checkoutRoomBooking;
    //customer check out
    private List<RoomBooking> todayCheckOutRoom;
    private String checkoutRoom;
    private List<RoomBooking> checkOutRoomResult;
    private List<LaundryOrder> listOfLaundryOrders;
    private List<FoodOrder> listOfFoodOrders;
    private List<MinibarOrder> listOfMinibarOrders;

    //Customer walk in
    private int walkinPax;
    private int walkinNumberOfday;
    private List<Room> walkinAvailableRoom;
    private double roomPrice;
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

    //Room Booked Page
    private int numOfStandardRooms;
    private int numOfDeluxeRooms;
    private int numOfPremiumRooms;
    private int numOfSuiteRooms;
    private int numOfPentHouseRooms;
    private List<Room> walkInRoomsChecking;
    private List<Room> roomBookedOnWalkin = new ArrayList<Room>();
    private String walkinRoomtype;
    private int roomAvailableToBook;
    private boolean[] extraBedBox;

    private String ccName;
    private String ccCardNumber;
    private String ccExpDate;
    private String ccCVV;
    private String ccMonth;
    private String ccYear;

    private boolean[] selectRoomCB;
    //reservation 
    private List<FunctionRoom> allFunctionrooms;

    //room pricing 
    private List<RoomPricing> listofPricing;
    private List<HolidaySurcharge> hoidaySurchargesIncurred = new ArrayList<HolidaySurcharge>();
    private List<Date> extraSurchargesIncurred = new ArrayList<Date>();

    private PaymentTransaction selectedPaymentTransaction;

    public boolean checkDeluxe(Room room) {
        if (checkForLineBreak(room) && room.getRoomType().equals("Deluxe")) {
            return true;
        }
        return false;
    }

    public boolean checkSuite(Room room) {
        if (checkForLineBreak(room) && room.getRoomType().equals("Suite")) {
            return true;
        }
        return false;
    }

    public String saveRoom() throws NoResultException {
        ArrayList<RoomFacility> tempRoomFacilities = new ArrayList<RoomFacility>();
        if (roomFacilities != null) {
            for (int i = 0; i < roomFacilities.length; i++) {
                tempRoomFacilities.add(roomFacilitySessionLocal.getRoomFacilityByName(roomFacilities[i].toString()));
            }
        }
        selectedRoom.setRoomFacilities(tempRoomFacilities);
        ArrayList<MinibarItem> tempMinibarItem = new ArrayList<MinibarItem>();
        if (minibarItems != null) {
            for (int i = 0; i < minibarItems.length; i++) {
                tempMinibarItem.add(roomSessionLocal.getMinibarItemByName(minibarItems[i].toString()));
            }
        }
        selectedRoom.setMiniBarItems(tempMinibarItem);
        roomSessionLocal.updateRoom(selectedRoom);

//        FacesContext context = FacesContext.getCurrentInstance();
//        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
//        Logging l = new Logging("Room", "Update " + selectedRoom.getRoomName() + " details", loggedInName);
//        logSessionLocal.createLogging(l);
        selectedRoom = null;
        minibarItems = null;
        roomFacilities = null;

        return "manageRoom.xhtml?faces-redirect=true";
    }

    public String viewRoom(Long rID) throws NoResultException {
        System.out.println("in view Room");
        System.out.println("ID: " + rID);
        selectedRoom = roomSessionLocal.getRoomByID(rID);
        selectedRoomID = rID;

        String selectedRoomTxt = selectedRoom.getRoomName();
        return "viewRoom.xhtml?faces-redirect=true";
    }

    public String deleteRoom() throws NoResultException {
        List<Hotel> hotels = hotelSessionLocal.getAllHotels();
        Room r = selectedRoom;
        String tempHotelName = "";
        for (Hotel h : hotels) {
            if (h.getRooms().contains(r)) {
                hotelSessionLocal.removeRoom(h.getHotelID(), r);
                tempHotelName = h.getHotelName();
            }
        }
        logActivityName = r.getRoomName();
//        FacesContext context = FacesContext.getCurrentInstance();
//        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        roomSessionLocal.deleteRoom(r.getRoomID());
        selectedRoom = null;
//        Logging l = new Logging("Room", "Delete " + logActivityName + " from " + tempHotelName, loggedInName);
//        logSessionLocal.createLogging(l);

        return "manageRoom.xhtml?faces-redirect=true";
    }

    public String editRoom() throws NoResultException {
        selectedRoom = roomSessionLocal.getRoomByID(selectedRoomID);
        List<RoomFacility> tempRoomFacilities = selectedRoom.getRoomFacilities();
        List<MinibarItem> tempMinibarItems = selectedRoom.getMiniBarItems();
        if (!tempRoomFacilities.isEmpty()) {
            roomFacilities = new String[tempRoomFacilities.size()];
            for (int i = 0; i < tempRoomFacilities.size(); i++) {
                roomFacilities[i] = tempRoomFacilities.get(i).getRoomFacilityName();
            }
        }

        if (!tempMinibarItems.isEmpty()) {
            minibarItems = new String[tempMinibarItems.size()];
            for (int i = 0; i < tempMinibarItems.size(); i++) {
                minibarItems[i] = tempMinibarItems.get(i).getItemName();
            }
        }
        tempHotelNameTB = selectedRoom.getHotel().getHotelName();
        return "editRoom.xhtml?faces-redirect=true";
    }

    public List<MinibarItem> getMinibarItemList() {
        return houseKeepingOrderSessionLocal.getAllMinibarItem();
    }

    public String getSelectedHotelName() throws NoResultException {
        Hotel tempHotel = hotelSessionLocal.getHotelByCode(hotelCode);
        return tempHotel.getHotelName();

    }

    public String addRoom() throws NoResultException {
        Room r = new Room();
        Hotel h = hotelSessionLocal.getHotelByCode(hotelCode);
        roomName = h.getHotelCodeName() + "_" + roomNumber;
        r.setRoomName(roomName);
        r.setRoomNumber(roomNumber);
        r.setRoomType(roomType);
        r.setRoomPax(roomPax);
        r.setStatus("Available");
        ArrayList<MinibarItem> mbList = new ArrayList<MinibarItem>();
        ArrayList<RoomFacility> rfList = new ArrayList<RoomFacility>();

        if (minibarItems != null) {
            for (int i = 0; i < minibarItems.length; i++) {
                mbList.add(houseKeepingOrderSessionLocal.getMinibarItemByItemName(minibarItems[i]));
            }
        }

        if (roomFacilities != null) {
            for (int i = 0; i < roomFacilities.length; i++) {
                rfList.add(roomFacilitySessionLocal.getRoomFacilityByName(roomFacilities[i]));
            }
        }
        r.setMiniBarItems(mbList);
        r.setRoomFacilities(rfList);
        roomSessionLocal.createRoom(r);
        r = roomSessionLocal.getRoomByName(roomName);

        logActivityName = roomName;
        FacesContext context = FacesContext.getCurrentInstance();
//        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        hotelSessionLocal.addRoom(h.getHotelID(), r);
//        Logging l = new Logging("Room", "Add " + logActivityName + " to " + addRoomHotelName, loggedInName);
//        logSessionLocal.createLogging(l);

        roomName = null;
        roomNumber = null;
        roomType = null;
        roomPax = 1;
        minibarItems = null;
        roomFacilities = null;

        return "manageRoom.xhtml?faces-redirect=true";
    }

    public String getHotelFeedbackRatePct() throws NoResultException {
        List<Feedback> feedbacks = feedbackSessionLocal.getAllFeedbacks();
        int ratingTotal = 0;
        int feedbackBase = 0;
        for (Feedback f : feedbacks) {
            if (f.getHotel().getHotelName().equals(getSelectedHotelName())) {
                ratingTotal = ratingTotal + f.getFeedbackRating();
                feedbackBase = feedbackBase + 1;
            }
        }
        double feedbackTotal = ratingTotal * 1.0;
        double feedbackRate = feedbackTotal / feedbackBase;
        double feedbackPct = feedbackRate * 20.0;
        double roundOff = (double) Math.round(feedbackPct * 10) / 10.0;
        String str1 = Double.toString(roundOff);
        return str1;
    }

    public String getHotelAvailableRoom() throws NoResultException {
        List<Room> rooms = roomSessionLocal.getRoomByHotelName(getSelectedHotelName());
        List<Room> occupiedRooms = new ArrayList<Room>();
        for (Room r : rooms) {
            if (r.getStatus().equals("Available")) {
                Room tempRoom = r;
                occupiedRooms.add(tempRoom);
            }
        }

        String str1 = Integer.toString(occupiedRooms.size());
        return str1;
    }

    public String getHotelUnavailableRoom() throws NoResultException {
        List<Room> rooms = roomSessionLocal.getRoomByHotelName(getSelectedHotelName());
        List<Room> occupiedRooms = new ArrayList<Room>();
        for (Room r : rooms) {
            if (r.getStatus().equals("Unavailable")) {
                Room tempRoom = r;
                occupiedRooms.add(tempRoom);
            }
        }

        String str1 = Integer.toString(occupiedRooms.size());
        return str1;
    }

    public List<Room> getHotelRooms() throws NoResultException {
        Hotel hotel = hotelSessionLocal.getHotelByName(getSelectedHotelName());
        return hotel.getRooms();
    }

    public String getHotelFeedbackRate() throws NoResultException {
        List<Feedback> feedbacks = feedbackSessionLocal.getAllFeedbacks();
        int ratingTotal = 0;
        int feedbackBase = 0;
        for (Feedback f : feedbacks) {
            if (f.getHotel().getHotelName().equals(getSelectedHotelName())) {
                ratingTotal = ratingTotal + f.getFeedbackRating();
                feedbackBase = feedbackBase + 1;
            }
        }
        double feedbackTotal = ratingTotal * 1.0;
        double feedbackRate = feedbackTotal / feedbackBase;
        double roundOff = (double) Math.round(feedbackRate * 10) / 10.0;
        String str1 = Double.toString(roundOff);
        return str1;
    }

    public String getHotelOccupancyRate() throws NoResultException {

        List<Room> rooms = roomSessionLocal.getRoomByHotelName(getSelectedHotelName());
        List<Room> occupiedRooms = new ArrayList<Room>();
        for (Room r : rooms) {
            if (r.getStatus().equals("Occupied")) {
                Room tempRoom = r;
                occupiedRooms.add(tempRoom);
            }
        }
        double ocr = occupiedRooms.size() * 1.0;
        double rs = rooms.size() * 1.0;

        double rate = ocr / rs;
        double ratePercent = rate * 100;
        double roundOff = (double) Math.round(ratePercent * 100) / 100;
        String str1 = Double.toString(roundOff);
        return str1;
    }

    public boolean checkStandard(Room room) {
        if (checkForLineBreak(room) && room.getRoomType().equals("Standard")) {
            return true;
        }
        return false;
    }

    public boolean checkPremium(Room room) {
        if (checkForLineBreak(room) && room.getRoomType().equals("Premium")) {
            return true;
        }
        return false;
    }

    public boolean checkPenthouse(Room room) {
        if (checkForLineBreak(room) && room.getRoomType().equals("Penthouse")) {
            return true;
        }
        return false;
    }

    public boolean checkIsDeluxe(Room room) {
        if (room.getRoomType().equals("Deluxe")) {
            return true;
        }
        return false;
    }

    public boolean checkIsSuite(Room room) {
        if (room.getRoomType().equals("Suite")) {
            return true;
        }
        return false;
    }

    public boolean checkIsStandard(Room room) {
        if (room.getRoomType().equals("Standard")) {
            return true;
        }
        return false;
    }

    public boolean checkIsPremium(Room room) {
        if (room.getRoomType().equals("Premium")) {
            return true;
        }
        return false;
    }

    public boolean checkIsPenthouse(Room room) {
        if (room.getRoomType().equals("Penthouse")) {
            return true;
        }
        return false;
    }

    public String checkRooms(String roomType) throws NoResultException {
        walkinRoomtype = roomType;
        walkInRoomsChecking = getRoomsByHotelAndType();
        if (roomBookedOnWalkin.size() > 0) {
            for (Room r : roomBookedOnWalkin) {
                for (Room w : walkInRoomsChecking) {
                    if (r.getRoomName().equals(w.getRoomName())) {
                        w.setStatus("Selected");
                    }
                }
            }
        }
        setRoomAvailToBook();
        return "walkinRooms.xhtml?faces-redirect=true";
    }

    public double getRoomPriceFromTransaction(Room r) {
        for (RoomBooking rb : selectedPaymentTransaction.getRoomsBooked()) {
            if (rb.getBookedRoom().getRoomNumber() == r.getRoomNumber()) {
                return rb.getPrice();
            }
        }
        return 0;
    }

    public boolean checkIfRoomIsBook(Room r) {
        return !r.getStatus().equals("Available");
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

    public void checkToBookRoom(int i) {
        if (walkInRoomsChecking.get(i).getStatus().equals("Available") && (roomAvailableToBook > 0)) {
            walkInRoomsChecking.get(i).setStatus("Selected");
            roomAvailableToBook--;
            Room tempR = walkInRoomsChecking.get(i);
            roomBookedOnWalkin.add(tempR);
            decreaseSelectedRoomNumber();
        } else if (walkInRoomsChecking.get(i).getStatus().equals("Selected")) {
            walkInRoomsChecking.get(i).setStatus("Available");
            increaseSelectedRoomNumber();
            Room tempR = walkInRoomsChecking.get(i);
            roomBookedOnWalkin.remove(tempR);
            roomAvailableToBook++;
        } else {
        }
    }

    public void increaseSelectedRoomNumber() {
        if (walkinRoomtype.equals("Standard")) {
            numOfStandardRooms++;
        } else if (walkinRoomtype.equals("Deluxe")) {
            numOfDeluxeRooms++;
        } else if (walkinRoomtype.equals("Premium")) {
            numOfPremiumRooms++;
        } else if (walkinRoomtype.equals("Suite")) {
            numOfSuiteRooms++;
        } else {
            numOfPentHouseRooms++;
        }
    }

    public void decreaseSelectedRoomNumber() {
        if (walkinRoomtype.equals("Standard")) {
            numOfStandardRooms--;
        } else if (walkinRoomtype.equals("Deluxe")) {
            numOfDeluxeRooms--;
        } else if (walkinRoomtype.equals("Premium")) {
            numOfPremiumRooms--;
        } else if (walkinRoomtype.equals("Suite")) {
            numOfSuiteRooms--;
        } else {
            numOfPentHouseRooms--;
        }
    }

    public boolean checkForLineBreak(Room r) {
        if (Integer.parseInt(r.getRoomNumber()) % 100 == 1) {
            return true;
        }
        return false;
    }

    public int getRoomLevel(Room r) {
        return (Integer.parseInt(r.getRoomNumber()) / 100);
    }

    public boolean checkForLineBreakForStandard(Room r) {
        if (Integer.parseInt(r.getRoomNumber()) % 10 == 6 && r.getRoomType().equals("Standard")) {
            return true;
        }
        return false;
    }

    public boolean checkForLineBreakForDeluxe(Room r) {
        if (Integer.parseInt(r.getRoomNumber()) % 10 == 4 && r.getRoomType().equals("Deluxe")) {
            return true;
        }
        return false;
    }

    public boolean checkForLineBreakForPremium(Room r) {
        if (Integer.parseInt(r.getRoomNumber()) % 10 == 5 && r.getRoomType().equals("Premium")) {
            return true;
        }
        return false;
    }

    public boolean checkForLineBreakForSuite(Room r) {
        if (Integer.parseInt(r.getRoomNumber()) % 10 == 3 && r.getRoomType().equals("Suite")) {
            return true;
        }
        return false;
    }

    public boolean checkForLineBreakForPenthouse(Room r) {
        if (Integer.parseInt(r.getRoomNumber()) % 10 == 3 && r.getRoomType().equals("Penthouse")) {
            return true;
        }
        return false;
    }

    public boolean checkIsOdd(Room r) {
        if (Integer.parseInt(r.getRoomNumber()) % 2 == 1) {
            return true;
        }
        return false;
    }

    public String checkClassColor(int i) {
        if (walkInRoomsChecking.get(i).getStatus().equals("Available")) {
            return "turquoise";
        } else if (walkInRoomsChecking.get(i).getStatus().equals("Selected")) {
            return "orange";
        } else {
            return "lightgrey";
        }
    }

    public String addMoreRoomsToWalkIn() throws NoResultException {
        return "walkinState.xhtml?faces-redirect=true";
    }

    public String directToCustomerDetails() {

        return "walkinEnterCustDetails.xhtml?faces-rediret=true";
    }

    public void setRoomAvailToBook() {
        if (walkinRoomtype.equals("Standard")) {
            roomAvailableToBook = numOfStandardRooms;
        } else if (walkinRoomtype.equals("Deluxe")) {
            roomAvailableToBook = numOfDeluxeRooms;
        } else if (walkinRoomtype.equals("Premium")) {
            roomAvailableToBook = numOfPremiumRooms;
        } else if (walkinRoomtype.equals("Suite")) {
            roomAvailableToBook = numOfSuiteRooms;
        } else {
            roomAvailableToBook = numOfPentHouseRooms;
        }
    }

    public String showWalkInSummary() {
        extraBedBox = new boolean[roomBookedOnWalkin.size()];
        for (int i = 0; i < roomBookedOnWalkin.size(); i++) {
            extraBedBox[i] = false;
        }

        return "walkinSummary.xhtml?faces-redirect=true";
    }

    public List<FunctionRoom> getAllFunctionrooms() {
        getFunctionRoomWithHotelCode();
        return allFunctionrooms;
    }

    public void setAllFunctionrooms(List<FunctionRoom> allFunctionrooms) {
        this.allFunctionrooms = allFunctionrooms;
    }

    public String checkAvailability() throws java.text.ParseException, NoResultException {
        Date currentdate = java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        checkinDateString = getTodayDate();
        checkinDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateFormat.format(currentdate));
        checkoutDate = new SimpleDateFormat("yyyy-MM-dd").parse(checkoutDateString);
        numOfStandardRooms = 0;
        numOfDeluxeRooms = 0;
        numOfPremiumRooms = 0;
        numOfSuiteRooms = 0;
        numOfPentHouseRooms = 0;

        int numberOfRoomTypes = 0;
        for (RoomPricing rp : roomPricingSessionLocal.getAllRoomPricings()) {
            if (rp.hotelCode().equals(hotelCode)) {
                numberOfRoomTypes++;
            }
        }

        if (numberOfRoomTypes >= 3) {
            numOfStandardRooms = checkNumberOfRoomAvail("Standard", checkinDate, checkoutDate, hotelCode);
            numOfDeluxeRooms = checkNumberOfRoomAvail("Deluxe", checkinDate, checkoutDate, hotelCode);
            numOfPremiumRooms = checkNumberOfRoomAvail("Premium", checkinDate, checkoutDate, hotelCode);
        }
        if (numberOfRoomTypes >= 4) {
            numOfSuiteRooms = checkNumberOfRoomAvail("Suite", checkinDate, checkoutDate, hotelCode);
        }
        if (numberOfRoomTypes == 5) {
            numOfPentHouseRooms = checkNumberOfRoomAvail("Penthouse", checkinDate, checkoutDate, hotelCode);
        }

        return "walkinState.xhtml?faces-redirect=true";
    }

    public int getNightStay() {
        long diff = checkoutDate.getTime() - checkinDate.getTime();
        int daysDiff = (int) (diff / (1000 * 60 * 60 * 24));

        return daysDiff;
    }

    public String getTodayDate() {
        Date currentdate = java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return (dateFormat.format(currentdate));
    }

    public String getTomorrowDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        cal.add(Calendar.DATE, 1);
        Date modifiedDate = cal.getTime();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return (dateFormat.format(modifiedDate));
    }

    public int checkNumberOfRoomAvail(String roomType, Date checkIn, Date checkOut, String hotelName) throws NoResultException {
        Hotel tempHotel = hotelSessionLocal.getHotelByCode(hotelCode);
        List<Room> allRooms = roomSessionLocal.getRoomByHotelNameAndRoomType(roomType, hotelCode);
        int totalRoomSizeForType = allRooms.size();

        if (checkOut.compareTo(checkIn) > 0) {

            List<RoomBooking> allBooking = bookSessionLocal.getAllRoomBookingByHotel(hotelCode);
            List<RoomBooking> checkList = new ArrayList<RoomBooking>();
            List<Room> returnList = new ArrayList<Room>();

            //Convert String to Date
            //Get roombooking by type & Status
            if (allBooking != null) {
                for (RoomBooking rb : allBooking) {
                    if (rb.getRoomType() != null && rb.getStatus() != null) {
                        if (rb.getRoomType().equals(roomType) && !rb.getStatus().equals("checkedout")) {
                            RoomBooking tempRoomBooking = rb;
                            checkList.add(tempRoomBooking);
                        }
                    }

                }

                allBooking = checkList;
                checkList.clear();
                for (RoomBooking rb : allBooking) {
                    // Didn't Hit the Booking Date
                    if ((checkIn.before(rb.getBookInDate()) && checkOut.before(rb.getBookInDate())) || (checkIn.after(rb.getBookInDate()) && checkOut.after(rb.getBookInDate()))) {

                    } // Hit the booking date AKA Not Available
                    else {
                        RoomBooking tempRoomBooking = rb;
                        checkList.add(tempRoomBooking);
                    }
                }

                if (checkList.size() < roomSessionLocal.getRoomByHotelNameAndRoomType(roomType, hotelCode).size()) {
                    List<Room> filterList = roomSessionLocal.getRoomByHotelNameAndRoomType(roomType, tempHotel.getHotelCodeName());
                    for (Room r : filterList) {
                        if (!r.getStatus().toLowerCase().equals("occupied") && !r.getStatus().toLowerCase().equals("unavailable") && !r.getStatus().equals("Selected")) {
                            Room tempRoom = r;
                            returnList.add(r);
                        }
                    }
                    return returnList.size();
                }
            }
        }
        return 0;
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

    public List<String> getRoomTypesListStr() throws NoResultException {
        Hotel tempHotel = hotelSessionLocal.getHotelByCode(hotelCode);
        List<String> returnList = hotelSessionLocal.getRoomTypes(tempHotel.getHotelID());

        return returnList;
    }

    public int getNumberOfAvailByRoomType(String roomType) {
        if (roomType.equals("Standard")) {
            return numOfStandardRooms;
        } else if (roomType.equals("Deluxe")) {
            return numOfDeluxeRooms;
        } else if (roomType.equals("Premium")) {
            return numOfPremiumRooms;
        } else if (roomType.equals("Suite")) {
            return numOfSuiteRooms;
        } else {
            return numOfPentHouseRooms;
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

    public boolean checkEmailExist() throws NoResultException {
        if (checkinEmail == null) {
            return false;
        } else {
            if (customerSessionLocal.getCustomerByEmail(checkinEmail) == null) {
                return false;
            } else {
                return true;
            }
        }
    }

    public String confirmPage() throws NoResultException {
        Customer tempCust = new Customer();
        if (!checkinEmail.equals("")) {
            if (customerSessionLocal.getCustomerByEmail(checkinEmail) != null) {

                tempCust = customerSessionLocal.getCustomerByEmail(checkinEmail);

            }
        }

        List<RoomBooking> tempRoomBookingList = new ArrayList<RoomBooking>();
        double transactionTotal = 0;
        for (Room r : roomBookedOnWalkin) {

            RoomBooking tempRB = new RoomBooking();
            tempRB.setBookingDate(checkinDate);
            tempRB.setBookInDate(checkinDate);
            tempRB.setBookOutDate(checkoutDate);
            tempRB.setStatus("checkedIn");
            tempRB.setPrice(calculatePrice(r));
            tempRB.setHasTransport(false);
            double price = calculatePrice(r);
            transactionTotal += price;
            tempRB.setPrice(price);
            Room tempRoom = r;
            tempRoom.setStatus("Occupied");
            roomSessionLocal.updateRoom(tempRoom);
            tempRB.setBookedRoom(tempRoom);
            if (tempCust != null) {
                tempRB.setBookedBy(tempCust);
            }
            tempRB.setEmailAddress(checkinEmail);
            tempRB.setPassportNum(checkinPassportNumber);
            tempRB.setFirstName(customerFirstName);
            tempRB.setFirstName(customerLastName);
            tempRB.setRoomType(r.getRoomType());
            bookSessionLocal.createRoomBooking(tempRB);
            tempRB = bookSessionLocal.getLastRoomBooking();
            tempRoomBookingList.add(tempRB);
        }

        selectedPaymentTransaction = new PaymentTransaction();
        selectedPaymentTransaction.setFirstName(customerFirstName);
        selectedPaymentTransaction.setLastName(customerLastName);
        selectedPaymentTransaction.setEmail(checkinEmail);
        if (tempCust != null) {
            selectedPaymentTransaction.setPayer(tempCust);
        }
        selectedPaymentTransaction.setTransactionDateTime(java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        selectedPaymentTransaction.setRoomsBooked(tempRoomBookingList);
        selectedPaymentTransaction.setTotalPrice(transactionTotal);
        selectedPaymentTransaction.setTransportBooked(null);
        selectedPaymentTransaction.setFunctionRoomBooked(null);

        paymentTransactionSessionLocal.createPaymentTransaction(selectedPaymentTransaction);
        selectedPaymentTransaction = paymentTransactionSessionLocal.getLastPaymentTransaction();

        return "walkinResultCustomerDetails.xhtml?faces-redirect=true";
    }

    public double calculatePrice(Room r) throws NoResultException {
        double totalPrice = 0;
        double perNightPrice = roomPricingSessionLocal.getRoomPricingByName(hotelCode + "_" + r.getRoomType()).getPrice();
        int numberOfNight = getNightStay();

        double subTotal = perNightPrice * numberOfNight;
        totalPrice += subTotal;
//        for(int i= 0; i <numberOfNights){
//            if()
//        }
        List<Date> dateWithExtraSurcharges = new ArrayList<Date>();
        for (ExtraSurcharge es : roomSessionLocal.getAllExtraSurcharge()) {
            long diff = es.getSurchargeTo().getTime() - es.getSurchargeFrom().getTime();
            int numOfDays = (int) (diff / 1000 / 60 / 60 / 24) + 1;
            boolean checkMon = false;
            boolean checkTue = false;
            boolean checkWed = false;
            boolean checkThurs = false;
            boolean checkFri = false;
            boolean checkSat = false;
            boolean checkSun = false;

            for (String check : es.getDaysToCharge()) {
                if (check.equals("MON")) {
                    checkMon = true;
                }
                if (check.equals("TUE")) {
                    checkTue = true;
                }
                if (check.equals("WED")) {
                    checkWed = true;
                }
                if (check.equals("THURS")) {
                    checkThurs = true;
                }
                if (check.equals("FRI")) {
                    checkFri = true;
                }
                if (check.equals("SAT")) {
                    checkSat = true;
                }
                if (check.equals("SUN")) {
                    checkSun = true;
                }
            }
            for (int i = 0; i < numOfDays; i++) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(es.getSurchargeFrom());
                cal.add(Calendar.DATE, i);
                Date modifiedDate = cal.getTime();
                if (modifiedDate.compareTo(checkinDate) >= 0 && modifiedDate.compareTo(checkoutDate) < 0) {
                    if (cal.get(Calendar.DAY_OF_WEEK) == 1 && checkSun == true) {
                        extraSurchargesIncurred.add(modifiedDate);
                        totalPrice += es.getSurchargePrice();
                    }
                    if (cal.get(Calendar.DAY_OF_WEEK) == 2 && checkMon == true) {
                        extraSurchargesIncurred.add(modifiedDate);
                        totalPrice += es.getSurchargePrice();
                    }
                    if (cal.get(Calendar.DAY_OF_WEEK) == 3 && checkTue == true) {
                        extraSurchargesIncurred.add(modifiedDate);
                        totalPrice += es.getSurchargePrice();
                    }
                    if (cal.get(Calendar.DAY_OF_WEEK) == 4 && checkWed == true) {
                        extraSurchargesIncurred.add(modifiedDate);
                        totalPrice += es.getSurchargePrice();
                    }
                    if (cal.get(Calendar.DAY_OF_WEEK) == 5 && checkThurs == true) {
                        extraSurchargesIncurred.add(modifiedDate);
                        totalPrice += es.getSurchargePrice();
                    }
                    if (cal.get(Calendar.DAY_OF_WEEK) == 6 && checkFri == true) {
                        extraSurchargesIncurred.add(modifiedDate);
                        totalPrice += es.getSurchargePrice();
                    }
                    if (cal.get(Calendar.DAY_OF_WEEK) == 7 && checkSat == true) {
                        extraSurchargesIncurred.add(modifiedDate);
                        totalPrice += es.getSurchargePrice();
                    }
                }
            }
        }

        for (HolidaySurcharge hs : roomSessionLocal.getAllHolidaySurcharge()) {
            if (hs.getHolidayDate().compareTo(checkinDate) >= 0 && hs.getHolidayDate().compareTo(checkoutDate) < 0) {
                HolidaySurcharge tempHs = hs;
                hoidaySurchargesIncurred.add(tempHs);
                totalPrice += tempHs.getHolidaySurchargePrice();
            }
        }

        return totalPrice;
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

    public String makePayment() throws NoResultException {
        CreditCard cc = new CreditCard();
        cc.setCardNum(ccName);
        cc.setCvv(ccCVV);
        cc.setExpiryDate(ccMonth + "/" + ccYear);
        cc.setCardNum(ccCardNumber);
        paymentTransactionSessionLocal.createCreditCard(cc);
        cc = paymentTransactionSessionLocal.getLastCreditCard();
        selectedPaymentTransaction.setCreditCard(cc);
        paymentTransactionSessionLocal.updatePaymentTransaction(selectedPaymentTransaction);
        numOfStandardRooms = 0;
        numOfDeluxeRooms = 0;
        numOfPremiumRooms = 0;
        numOfSuiteRooms = 0;
        numOfPentHouseRooms = 0;
        walkInRoomsChecking = new ArrayList<Room>();
        roomBookedOnWalkin = new ArrayList<Room>();
        walkinRoomtype = null;
        roomAvailableToBook = 0;

        ccName = null;
        ccCardNumber = null;
        ccExpDate = null;
        ccCVV = null;
        ccMonth = null;
        ccYear = null;

        checkinDate = null;
        checkoutDate = null;
        checkinDateString = null;
        checkoutDateString = null;

        return "index.xhtml?faces-redirect=true";
    }

//    public String makePayment() throws ParseException, java.text.ParseException, NoResultException {
//        CreditCard cc = new CreditCard();
//        cc.setCardNum(encryptPassword(paymentDigits));
//        cc.setCvv(encryptPassword(paymentCVV));
//        cc.setExpiryDate(paymentExpiryDate);
//        paymentTransactionSessionLocal.createCreditCard(cc);
//
//        PaymentTransaction pt = new PaymentTransaction();
//        pt.setCreditCard(paymentTransactionSessionLocal.getLastCreditCard());
//        pt.setTotalPrice(totalPrice);
//        pt.setInitialPayment(totalPrice);
//        pt.setFinalPayment(totalPrice);
//        pt.setPaymentType("Credit Card");
//        pt.setTransactionDateTime(new Date());
//
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        RoomBooking rb1 = new RoomBooking();
//        rb1.setBookInDate(checkinDate);
//        rb1.setBookOutDate(checkoutDate);
//        rb1.setStatus("checkedIn");
//        rb1.setPrice(totalPrice);
//        rb1.setBookedRoom(selectedRoom);
//        rb1.setEmailAddress(checkinEmail);
//        rb1.setPassportNum(checkinPassportNumber);
//        rb1.setRoomType(selectedRoom.getRoomType());
//        rb1.setFirstName(customerFirstName);
//        rb1.setLastName(customerLastName);
//        rb1.setHasExtraBed(false);
//        bookSessionLocal.createRoomBooking(rb1);
//        Room rm = roomSessionLocal.getRoomByName(selectedRoom.getRoomName());
//        rm.setStatus("Occupied");
//        roomSessionLocal.updateRoom(rm);
//
//        mode = "online";
//        return "summary.xhtml?faces-redirect=true";
//        //payment complete and roombooking is done here
//
//    }
    public String searchCheckout() {
        //bookSessionLocal.getRoombookingbyRoomNumber(String roomNumber,String status); check status, dont just anyhow get room. 

        //setCheckOutRoomResult(checkOutRoomResult);
        return "checkoutResult.xhtml?faces-redirect=true";
    }

    public String displayCheckoutOrders(RoomBooking rb) {
        //do check out 
        //update check out room and change roombooking status
        try {
            setListOfFoodOrders(null);
            setListOfLaundryOrders(null);
            setListOfMinibarOrders(null);
            setCheckoutRoomBooking(rb);
            setListOfFoodOrders(rb.getListOfFoodOrders());
            setListOfLaundryOrders(rb.getListOfLaundryOrders());
            setListOfMinibarOrders(rb.getListOfMinibarOrders());
            return "checkoutOrderSummary.xhtml?faces-redirect=true";
        } catch (Exception e) {
            return "checkout.xhtml?faces-redirect=true";
        }
    }

    public String checkOut() {
        try {
            checkoutRoomBooking.setStatus("checkedOut");
            checkoutRoomBooking.getBookedRoom().setStatus("Unavailable");
            roomSessionLocal.updateRoom(checkoutRoomBooking.getBookedRoom());
            bookSessionLocal.updateRoomBooking(checkoutRoomBooking);
        } catch (Exception e) {
            return "checkout.xhtml?faces-redirect=true";
        }
        return "checkout.xhtml?faces-redirect=true";
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

        return bookSessionLocal.getAllRoomBookingByStatus("checkedIn", null);
    }

    public void setTodayCheckOutRoom(List<RoomBooking> todayCheckOutRoom) {
        this.todayCheckOutRoom = todayCheckOutRoom;
    }

    public List<Hotel> getAllHotels() {
        return hotelSessionLocal.getAllHotels();
    }

    public List<RoomFacility> getRoomFacilityList() {
        return roomFacilitySessionLocal.getAllRoomFacilities();
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

    public RoomPricingSessionLocal getRoomPricingSessionLocal() {
        return roomPricingSessionLocal;
    }

    public void setRoomPricingSessionLocal(RoomPricingSessionLocal roomPricingSessionLocal) {
        this.roomPricingSessionLocal = roomPricingSessionLocal;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public int getNumOfStandardRooms() {
        return numOfStandardRooms;
    }

    public void setNumOfStandardRooms(int numOfStandardRooms) {
        this.numOfStandardRooms = numOfStandardRooms;
    }

    public int getNumOfDeluxeRooms() {
        return numOfDeluxeRooms;
    }

    public void setNumOfDeluxeRooms(int numOfDeluxeRooms) {
        this.numOfDeluxeRooms = numOfDeluxeRooms;
    }

    public int getNumOfPremiumRooms() {
        return numOfPremiumRooms;
    }

    public void setNumOfPremiumRooms(int numOfPremiumRooms) {
        this.numOfPremiumRooms = numOfPremiumRooms;
    }

    public int getNumOfSuiteRooms() {
        return numOfSuiteRooms;
    }

    public void setNumOfSuiteRooms(int numOfSuiteRooms) {
        this.numOfSuiteRooms = numOfSuiteRooms;
    }

    public int getNumOfPentHouseRooms() {
        return numOfPentHouseRooms;
    }

    public void setNumOfPentHouseRooms(int numOfPentHouseRooms) {
        this.numOfPentHouseRooms = numOfPentHouseRooms;
    }

    public HotelSessionLocal getHotelSessionLocal() {
        return hotelSessionLocal;
    }

    public void setHotelSessionLocal(HotelSessionLocal hotelSessionLocal) {
        this.hotelSessionLocal = hotelSessionLocal;
    }

    public boolean[] getSelectRoomCB() {
        return selectRoomCB;
    }

    public void setSelectRoomCB(boolean[] selectRoomCB) {
        this.selectRoomCB = selectRoomCB;
    }

    public FrontDeskManagedBean() {
    }

    public String getPaymentNameOnCard() {
        return paymentNameOnCard;
    }

    public List<Room> getRoomsByHotelAndType() throws NoResultException {

        return roomSessionLocal.getRoomByHotelNameAndRoomType(walkinRoomtype, hotelCode);
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

    public List<Room> getWalkInRoomsChecking() {
        return walkInRoomsChecking;
    }

    public void setWalkInRoomsChecking(List<Room> walkInRoomsChecking) {
        this.walkInRoomsChecking = walkInRoomsChecking;
    }

    public List<Room> getRoomBookedOnWalkin() {
        return roomBookedOnWalkin;
    }

    public void setRoomBookedOnWalkin(List<Room> roomBookedOnWalkin) {
        this.roomBookedOnWalkin = roomBookedOnWalkin;
    }

    public int getRoomAvailableToBook() {
        return roomAvailableToBook;
    }

    public void setRoomAvailableToBook(int roomAvailableToBook) {
        this.roomAvailableToBook = roomAvailableToBook;
    }

    public boolean[] getExtraBedBox() {
        return extraBedBox;
    }

    public void setExtraBedBox(boolean[] extraBedBox) {
        this.extraBedBox = extraBedBox;
    }

    public PaymentTransaction getSelectedPaymentTransaction() {
        return selectedPaymentTransaction;
    }

    public void setSelectedPaymentTransaction(PaymentTransaction selectedPaymentTransaction) {
        this.selectedPaymentTransaction = selectedPaymentTransaction;
    }

    public String getCcName() {
        return ccName;
    }

    public void setCcName(String ccName) {
        this.ccName = ccName;
    }

    public String getCcCardNumber() {
        return ccCardNumber;
    }

    public void setCcCardNumber(String ccCardNumber) {
        this.ccCardNumber = ccCardNumber;
    }

    public String getCcExpDate() {
        return ccExpDate;
    }

    public void setCcExpDate(String ccExpDate) {
        this.ccExpDate = ccExpDate;
    }

    public String getCcCVV() {
        return ccCVV;
    }

    public void setCcCVV(String ccCVV) {
        this.ccCVV = ccCVV;
    }

    public List<HolidaySurcharge> getHoidaySurchargesIncurred() {
        return hoidaySurchargesIncurred;
    }

    public void setHoidaySurchargesIncurred(List<HolidaySurcharge> hoidaySurchargesIncurred) {
        this.hoidaySurchargesIncurred = hoidaySurchargesIncurred;
    }

    public List<Date> getExtraSurchargesIncurred() {
        return extraSurchargesIncurred;
    }

    public void setExtraSurchargesIncurred(List<Date> extraSurchargesIncurred) {
        this.extraSurchargesIncurred = extraSurchargesIncurred;
    }

    public RoomBooking getCheckoutRoomBooking() {
        return checkoutRoomBooking;
    }

    public void setCheckoutRoomBooking(RoomBooking checkoutRoomBooking) {
        this.checkoutRoomBooking = checkoutRoomBooking;
    }

    public List<LaundryOrder> getListOfLaundryOrders() {
        return listOfLaundryOrders;
    }

    public void setListOfLaundryOrders(List<LaundryOrder> listOfLaundryOrders) {
        this.listOfLaundryOrders = listOfLaundryOrders;
    }

    public List<FoodOrder> getListOfFoodOrders() {
        return listOfFoodOrders;
    }

    public void setListOfFoodOrders(List<FoodOrder> listOfFoodOrders) {
        this.listOfFoodOrders = listOfFoodOrders;
    }

    public List<MinibarOrder> getListOfMinibarOrders() {
        return listOfMinibarOrders;
    }

    public void setListOfMinibarOrders(List<MinibarOrder> listOfMinibarOrders) {
        this.listOfMinibarOrders = listOfMinibarOrders;
    }

    public String getCheckoutRoomNumber() {
        return checkoutRoomNumber;
    }

    public void setCheckoutRoomNumber(String checkoutRoomNumber) {
        this.checkoutRoomNumber = checkoutRoomNumber;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public FeedbackSessionLocal getFeedbackSessionLocal() {
        return feedbackSessionLocal;
    }

    public void setFeedbackSessionLocal(FeedbackSessionLocal feedbackSessionLocal) {
        this.feedbackSessionLocal = feedbackSessionLocal;
    }

    public Long getSelectedRoomID() {
        return selectedRoomID;
    }

    public void setSelectedRoomID(Long selectedRoomID) {
        this.selectedRoomID = selectedRoomID;
    }

    public RoomBooking getRoomBookingToDisplay() {
        return roomBookingToDisplay;
    }

    public void setRoomBookingToDisplay(RoomBooking roomBookingToDisplay) {
        this.roomBookingToDisplay = roomBookingToDisplay;
    }

    public String getCcMonth() {
        return ccMonth;
    }

    public void setCcMonth(String ccMonth) {
        this.ccMonth = ccMonth;
    }

    public String getCcYear() {
        return ccYear;
    }

    public void setCcYear(String ccYear) {
        this.ccYear = ccYear;
    }

    public String getTempHotelNameTB() {
        return tempHotelNameTB;
    }

    public void setTempHotelNameTB(String tempHotelNameTB) {
        this.tempHotelNameTB = tempHotelNameTB;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getRoomPax() {
        return roomPax;
    }

    public void setRoomPax(int roomPax) {
        this.roomPax = roomPax;
    }

    public String[] getMinibarItems() {
        return minibarItems;
    }

    public void setMinibarItems(String[] minibarItems) {
        this.minibarItems = minibarItems;
    }

    public String[] getRoomFacilities() {
        return roomFacilities;
    }

    public void setRoomFacilities(String[] roomFacilities) {
        this.roomFacilities = roomFacilities;
    }

    public String getAddRoomHotelName() {
        return addRoomHotelName;
    }

    public void setAddRoomHotelName(String addRoomHotelName) {
        this.addRoomHotelName = addRoomHotelName;
    }

}
