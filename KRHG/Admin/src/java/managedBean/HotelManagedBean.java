/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.Customer;
import entity.ExtraSurcharge;
import entity.Feedback;
import entity.HolidaySurcharge;
import entity.Hotel;
import entity.HotelFacility;
import entity.Logging;
import entity.MailingList;
import entity.MemberTier;
import entity.MinibarItem;
import entity.PaymentTransaction;
import entity.PromoCode;
import entity.Room;
import entity.RoomFacility;
import entity.RoomPricing;
import entity.Shift;
import entity.Staff;
import entity.StaffType;
import entity.WeeklySchedule;
import error.NoResultException;
import etc.RandomPassword;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Properties;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Part;
import sessionBeans.CustomerSessionLocal;
import sessionBeans.FeedbackSessionLocal;
import sessionBeans.HotelFacilitySessionLocal;

import sessionBeans.HotelSessionLocal;
import sessionBeans.HouseKeepingOrderSessionLocal;
import sessionBeans.LogSessionLocal;
import sessionBeans.MailingListSessionLocal;
import sessionBeans.MemberTierSessionLocal;
import sessionBeans.PaymentTransactionSessionLocal;
import sessionBeans.PromoCodeSessionLocal;
import sessionBeans.RoomFacilitySessionLocal;
import sessionBeans.RoomPricingSessionLocal;
import sessionBeans.RoomSessionLocal;
import sessionBeans.ShiftSessionLocal;
import sessionBeans.StaffSessionLocal;
import sessionBeans.WeeklyScheduleSessionLocal;
import sun.misc.IOUtils;

/**
 *
 * @author fengl
 */
@ManagedBean
@SessionScoped
public class HotelManagedBean implements Serializable {

    @EJB
    HotelSessionLocal hotelSessionLocal;
    @EJB
    HotelFacilitySessionLocal hotelFacilitySessionLocal;
    @EJB
    HouseKeepingOrderSessionLocal houseKeepingOrderSessionLocal;
    @EJB
    RoomFacilitySessionLocal roomFacilitySessionLocal;
    @EJB
    RoomSessionLocal roomSessionLocal;
    @EJB
    FeedbackSessionLocal feedbackSessionLocal;
    @EJB
    LogSessionLocal logSessionLocal;
    @EJB
    StaffSessionLocal staffSessionLocal;
    @EJB
    RoomPricingSessionLocal roomPricingSessionLocal;
    @EJB
    MemberTierSessionLocal memberTierSessionLocal;
    @EJB
    MailingListSessionLocal mailingListSessionLocal;
    @EJB
    CustomerSessionLocal customerSessionLocal;
    @EJB
    WeeklyScheduleSessionLocal weeklyScheduleSessionLocal;
    @EJB
    ShiftSessionLocal shiftSessionLocal;
    @EJB
    PaymentTransactionSessionLocal paymentTransactionSessionLocal;
    @EJB
    PromoCodeSessionLocal promocodesessionlocal;

    private String loggedInUser;

    private String logActivityName;
    private String noImageStr = "Noimage.jpg";
    private Part file;
    private Part iconFile;
    private Part hotelIconFile;

    public Long selectedMailingListID;
    public List<String> tempMailingList = new ArrayList<String>();

    public String selectedHotel;
    public Staff selectedStaff;
    public HotelFacility selectedFacilityObj;
    public HolidaySurcharge selectedHoliday;
    public Hotel selectedHotelObj;
    public MinibarItem selectedMinibarItem;
    public Room selectedRoom;
    public RoomFacility selectedRoomFacility;
    public ExtraSurcharge selectedSurcharge;
    public Feedback selectedFeedback;
    public RoomPricing selectedRoomPricing;
    public MemberTier selectedMemberTier;
    public MailingList selectedMailingList;
    public Customer selectedCustomer;
    public Long selectedRFID;
    public Long selectedHFID;

    public String hotelName;
    public String hotelCode;
    public String address;
    public int hotelStar;
    public String contactNumber;
    public String[] hotelFacilitiesArr;
    private String[] selectedHotelFacilities;

    public String rpHotelTB;
    public String rpRoomTypeTB;
    public double rpPriceTb;

    public String mtNameTB;
    public int mtPointsTB;

    Long selectedRoomID;
    Long selectedHotelID;

    //Create Room
    public String tempHotelNameTB;
    public String roomName;
    public String roomNumber;
    public String roomType;
    public int roomPax;
    public String[] minibarItems;
    public String[] roomFacilities;
    public String addRoomHotelName;

    public String selectedFacility;

    public String hfName;
    public String hfImage;
    public String hfDescription;

    public String mlListNameTB;

    public String holName;
    public String holDate;
    public double holPrice;

    public String miName;
    public double miPrice;

    public String rfName;
    public String rfCategory;
    public String rfIconImg;

    public String esName;
    public String esDateFrom;
    public String esDateTo;
    public String[] esDaysToCharge;
    public double esPrice;

    public String stName;
    public String stPassword;
    public String stEmail;
    public String stPhoneNumber;
    public String stGender;
    public String stNric;
    public String stAddress;
    public String stHotel;
    public String stJobTitle;
    public String stDepartment;
    public int stLeave;
    public String stNokName;
    public String stNokAddress;
    public String stNokPhoneNumber;
    public String[] stStaffType;

    public String emailText;

    public String subjectTB;
    public String emailMsg;
    public String[] mailingListToSend;

    public String[][] hqSchedule;

    public String scheduleDate;

    public String selectedDateDropDown;

    public String topGrossingHotel;

    public double topGrossingAmount = 0;
    //promocode
    public List<PromoCode> allPromoCode;
    private String promocodeName;
    private String promoStartDate;
    private String promoEndDate;
    private double promodiscount;
    private String promoStatus;
    private PromoCode editPromo;

    @ManagedProperty(value = "#{authenticationManagedBean}")
    private AuthenticationManagedBean authBean;

    public AuthenticationManagedBean getAuthBean() {
        return authBean;
    }

    public void setAuthBean(AuthenticationManagedBean authBean) {
        this.authBean = authBean;
    }

    public HotelManagedBean() {

    }

    public List<Hotel> getAllHotels() {
        return hotelSessionLocal.getAllHotels();
    }

    public List<HotelFacility> getAllHotelFacility() {
        return hotelFacilitySessionLocal.getAllHotelFacilities();
    }

    public List<MemberTier> getAllMemberTier() throws NoResultException {
        return memberTierSessionLocal.getAllMemberTier();
    }

    public List<MailingList> getAllMailingList() throws NoResultException {
        return mailingListSessionLocal.getAllMailingList();
    }

    public List<String> getAllCustomerEmail() throws NoResultException {
        List<String> returnList = new ArrayList<String>();

        for (Customer c : getAllCustomer()) {
            String tempEmail = c.getEmail();
            returnList.add(tempEmail);
        }

        return returnList;
    }

    public List<HolidaySurcharge> getAllHolidaySurcharge() {
        return roomSessionLocal.getAllHolidaySurcharge();
    }

    public List<MinibarItem> getAllMinibarItem() {
        return roomSessionLocal.getAllMinibarItemWithAvailable();
    }

    public List<RoomPricing> getAllRoomPricing() {
        return roomPricingSessionLocal.getAllRoomPricings();
    }

    public List<HotelFacility> getHotelFacilities() throws NoResultException {
        Hotel hotel = hotelSessionLocal.getHotelByName(selectedHotel);
        return hotel.getHotelFacilities();
    }

    public List<Feedback> getHotelFeedbacks() throws NoResultException {
        Hotel hotel = hotelSessionLocal.getHotelByName(selectedHotel);
        return hotel.getFeedbacks();
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

    public List<Logging> getAllLogging() {
        return logSessionLocal.getAllLoggingActivities();
    }

    public List<StaffType> getAllStaffType() {
        return staffSessionLocal.getAllStaffTypes();
    }

    public List<Staff> getAllStaff() {
        return staffSessionLocal.getAllStaffs();
    }

    public List<Customer> getAllCustomer() {
        return customerSessionLocal.getAllCustomers();
    }

    public String getTopGrossingHotel() throws Exception {
        double roundOff = getKrgTotalRevenue();
        if (roundOff > topGrossingAmount) {
            topGrossingAmount = roundOff;
            topGrossingHotel = "Kent Ridge Grand";
        }

        double roundOff2 = getKrcTotalRevenue();
        if (roundOff2 > topGrossingAmount) {
            topGrossingAmount = roundOff;
            topGrossingHotel = "Kent Ridge Central";
        }
        double roundOff3 = getKrnTotalRevenue();
        if (roundOff3 > topGrossingAmount) {
            topGrossingAmount = roundOff;
            topGrossingHotel = "Kent Ridge North";
        }
        double roundOff4 = getKrsTotalRevenue();
        if (roundOff4 > topGrossingAmount) {
            topGrossingAmount = roundOff;
            topGrossingHotel = "Kent Ridge South";
        }
        double roundOff5 = getKreTotalRevenue();
        if (roundOff5 > topGrossingAmount) {
            topGrossingAmount = roundOff;
            topGrossingHotel = "Kent Ridge East";
        }

        double roundOff6 = getKrwTotalRevenue();
        if (roundOff6 > topGrossingAmount) {
            topGrossingAmount = roundOff;
            topGrossingHotel = "Kent Ridge West";
        }
        double roundOff7 = getKrneTotalRevenue();
        if (roundOff7 > topGrossingAmount) {
            topGrossingAmount = roundOff;
            topGrossingHotel = "Kent Ridge North East";
        }

        double roundOff8 = getKrnwTotalRevenue();
        if (roundOff8 > topGrossingAmount) {
            topGrossingAmount = roundOff;
            topGrossingHotel = "Kent Ridge North West";
        }

        double roundOff9 = getKrseTotalRevenue();
        if (roundOff9 > topGrossingAmount) {
            topGrossingAmount = roundOff;
            topGrossingHotel = "Kent Ridge South East";
        }

        double roundOff10 = getKrswTotalRevenue();
        if (roundOff10 > topGrossingAmount) {
            topGrossingAmount = roundOff;
            topGrossingHotel = "Kent Ridge South West";
        }
        return topGrossingHotel;
    }

    public String getTopMonth() throws NoResultException, ParseException {
        double highest = 0;
        String topMonth = "";
        double roundOff1 = getJanTotalRevenue();
        if (roundOff1 > highest) {
            highest = roundOff1;
            topMonth = "January";
        }

        double roundOff2 = getFebTotalRevenue();
        if (roundOff2 > highest) {
            highest = roundOff2;
            topMonth = "February";
        }

        double roundOff3 = getMarTotalRevenue();
        if (roundOff3 > highest) {
            highest = roundOff3;
            topMonth = "March";
        }

        double roundOff4 = getAprTotalRevenue();
        if (roundOff4 > highest) {
            highest = roundOff4;
            topMonth = "April";
        }

        return topMonth;
    }

    public String getTotalRevenue() throws NoResultException, ParseException {
        double totalRevenue = 0;
        totalRevenue = totalRevenue + getJanTotalRevenue();
        totalRevenue = totalRevenue + getFebTotalRevenue();
        totalRevenue = totalRevenue + getMarTotalRevenue();
        totalRevenue = totalRevenue + getAprTotalRevenue();

        double roundOff = (double) Math.round(totalRevenue * 100) / 100;
        String roundOffString = "$" + String.format("%,.2f", roundOff);
        return roundOffString;

    }

    public void setTopGrossingHotel(String topGrossingHotel) {
        this.topGrossingHotel = topGrossingHotel;
    }

    public String convertDateFormat(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    public String getOccupancyRate() {
        List<Room> rooms = roomSessionLocal.getAllRooms();
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

    public String getFeedbackRate() {
        List<Feedback> feedbacks = feedbackSessionLocal.getAllFeedbacks();
        int ratingTotal = 0;
        for (Feedback f : feedbacks) {
            ratingTotal = ratingTotal + f.getFeedbackRating();
        }
        double feedbackTotal = ratingTotal * 1.0;
        double feedbackBase = feedbacks.size() * 1.0;
        double feedbackRate = feedbackTotal / feedbackBase;
        double roundOff = (double) Math.round(feedbackRate * 10) / 10.0;
        String str1 = Double.toString(roundOff);
        return str1;
    }

    public String getFeedbackRatePct() {
        List<Feedback> feedbacks = feedbackSessionLocal.getAllFeedbacks();
        int ratingTotal = 0;
        for (Feedback f : feedbacks) {
            ratingTotal = ratingTotal + f.getFeedbackRating();
        }
        double feedbackTotal = ratingTotal * 1.0;
        double feedbackBase = feedbacks.size() * 1.0;
        double feedbackRate = feedbackTotal / feedbackBase;
        double feedbackPct = feedbackRate * 20.0;
        double roundOff = (double) Math.round(feedbackPct * 10) / 10.0;
        String str1 = Double.toString(roundOff);
        return str1;
    }

    public String getTopPerformingHotel() throws NoResultException {
        List<Hotel> hotels = hotelSessionLocal.getAllHotels();

        int index = 0;
        double highestOccRate = 0.0;
        String highestHotelName = "";
        List<Room> occupiedRooms = new ArrayList<Room>();
        while (index < hotels.size()) {
            if(hotels.get(index).getRooms()!=null || !hotels.get(index).getRooms().isEmpty() ){
                System.out.println("in here");
            List<Room> tempRoom = roomSessionLocal.getRoomByHotelCodeName(hotels.get(index).getHotelCodeName());
            for (Room r : tempRoom) {
                if (r.getStatus().equals("Occupied")) {
                    Room temp = r;
                    occupiedRooms.add(temp);
                }
            }

            double ocr = occupiedRooms.size() * 1.0;
            double rs = tempRoom.size() * 1.0;
            double rate = ocr / rs;

            if (rate > highestOccRate) {
                highestOccRate = rate;
                highestHotelName = hotels.get(index).getHotelName();
            }
            occupiedRooms.clear();

            index++;
            }
            
        }//end of while-loop

        return highestHotelName;
    }

    public String getTopOccupiedRate() throws NoResultException {
        List<Hotel> hotels = hotelSessionLocal.getAllHotels();

        int index = 0;
        double highestOccRate = 0.0;
        String highestHotelName = "";
        List<Room> occupiedRooms = new ArrayList<Room>();
        while (index < hotels.size()) {
            List<Room> tempRoom = roomSessionLocal.getRoomByHotelCodeName(hotels.get(index).getHotelCodeName());
            for (Room r : tempRoom) {
                if (r.getStatus().equals("Occupied")) {
                    Room temp = r;
                    occupiedRooms.add(temp);
                }
            }

            double ocr = occupiedRooms.size() * 1.0;
            double rs = tempRoom.size() * 1.0;
            double rate = ocr / rs;

            if (rate > highestOccRate) {
                highestOccRate = rate;
                highestHotelName = hotels.get(index).getHotelName();
            }
            occupiedRooms.clear();
            index++;
        }//end of while-loop

        double ratePercent = highestOccRate * 100;
        double roundOff = (double) Math.round(ratePercent * 100) / 100;
        String str1 = Double.toString(roundOff);
        return str1;
    }

    public String getHotelOccupancyRate() throws NoResultException {
        List<Room> rooms = roomSessionLocal.getRoomByHotelName(selectedHotel);
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

    public String getHotelAvailableRoom() throws NoResultException {
        List<Room> rooms = roomSessionLocal.getRoomByHotelName(selectedHotel);
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
        List<Room> rooms = roomSessionLocal.getRoomByHotelName(selectedHotel);
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

    public String getHotelFeedbackRate() throws NoResultException {
        List<Feedback> feedbacks = feedbackSessionLocal.getAllFeedbacks();
        int ratingTotal = 0;
        int feedbackBase = 0;
        for (Feedback f : feedbacks) {
            if (f.getHotel().getHotelName().equals(selectedHotel)) {
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

    public String getHotelFeedbackRatePct() throws NoResultException {
        List<Feedback> feedbacks = feedbackSessionLocal.getAllFeedbacks();
        int ratingTotal = 0;
        int feedbackBase = 0;
        for (Feedback f : feedbacks) {
            if (f.getHotel().getHotelName().equals(selectedHotel)) {
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

    public double getJanTotalRevenue() throws NoResultException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<PaymentTransaction> pts = paymentTransactionSessionLocal.getAllPaymentTransaction();
        double totalJan = 0;
        for (PaymentTransaction pt : pts) {
            if (pt.getTransactionDateTime().compareTo(format.parse("2018-12-31")) == 1 && pt.getTransactionDateTime().compareTo(format.parse("2019-02-01")) == -1) {
                totalJan = totalJan + pt.getFinalPayment();
            }
        }

        double roundOff = (double) Math.round(totalJan * 100) / 100;
        return roundOff;
    }

    public double getFebTotalRevenue() throws NoResultException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<PaymentTransaction> pts = paymentTransactionSessionLocal.getAllPaymentTransaction();
        double totalJan = 0;
        for (PaymentTransaction pt : pts) {
            if (pt.getTransactionDateTime().compareTo(format.parse("2019-01-31")) == 1 && pt.getTransactionDateTime().compareTo(format.parse("2019-03-01")) == -1) {
                totalJan = totalJan + pt.getFinalPayment();
            }
        }
        double roundOff = (double) Math.round(totalJan * 100) / 100;
        return roundOff;
    }

    public double getMarTotalRevenue() throws NoResultException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<PaymentTransaction> pts = paymentTransactionSessionLocal.getAllPaymentTransaction();
        double totalJan = 0;
        for (PaymentTransaction pt : pts) {
            if (pt.getTransactionDateTime().compareTo(format.parse("2019-02-28")) == 1 && pt.getTransactionDateTime().compareTo(format.parse("2019-04-01")) == -1) {
                totalJan = totalJan + pt.getFinalPayment();
            }
        }
        double roundOff = (double) Math.round(totalJan * 100) / 100;
        return roundOff;
    }

    public double getAprTotalRevenue() throws NoResultException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<PaymentTransaction> pts = paymentTransactionSessionLocal.getAllPaymentTransaction();
        double totalJan = 0;
        for (PaymentTransaction pt : pts) {
            if (pt.getTransactionDateTime().compareTo(format.parse("2019-03-31")) == 1 && pt.getTransactionDateTime().compareTo(format.parse("2019-05-01")) == -1) {
                totalJan = totalJan + pt.getFinalPayment();
            }
        }
        double roundOff = (double) Math.round(totalJan * 100) / 100;
        return roundOff;
    }

    public double getKrgTotalRevenue() throws NoResultException {
        List<PaymentTransaction> pts = paymentTransactionSessionLocal.getAllPaymentTransaction();
        double totalKRG = 0;
        for (PaymentTransaction pt : pts) {
            if (pt.getRoomsBooked().get(0).getBookedRoom().getHotel().getHotelCodeName().equals("KRG")) {
                totalKRG = totalKRG + pt.getFinalPayment();
            }
        }

        double roundOff = (double) Math.round(totalKRG * 100) / 100;
        if (roundOff > topGrossingAmount) {
            topGrossingAmount = roundOff;
            topGrossingHotel = "Kent Ridge Grand";
        }
        return roundOff;
    }

    public double getKrcTotalRevenue() throws NoResultException {
        List<PaymentTransaction> pts = paymentTransactionSessionLocal.getAllPaymentTransaction();
        double totalKRG = 0;
        for (PaymentTransaction pt : pts) {
            if (pt.getRoomsBooked().get(0).getBookedRoom().getHotel().getHotelCodeName().equals("KRC")) {
                totalKRG = totalKRG + pt.getFinalPayment();
            }
        }
        double roundOff = (double) Math.round(totalKRG * 100) / 100;
        if (roundOff > topGrossingAmount) {
            topGrossingAmount = roundOff;
            topGrossingHotel = "Kent Ridge Central";
        }
        return roundOff;
    }

    public double getKrnTotalRevenue() throws NoResultException {
        List<PaymentTransaction> pts = paymentTransactionSessionLocal.getAllPaymentTransaction();
        double totalKRG = 0;
        for (PaymentTransaction pt : pts) {
            if (pt.getRoomsBooked().get(0).getBookedRoom().getHotel().getHotelCodeName().equals("KRN")) {
                totalKRG = totalKRG + pt.getFinalPayment();
            }
        }
        double roundOff = (double) Math.round(totalKRG * 100) / 100;
        if (roundOff > topGrossingAmount) {
            topGrossingAmount = roundOff;
            topGrossingHotel = "Kent Ridge North";
        }
        return roundOff;
    }

    public double getKrsTotalRevenue() throws NoResultException {
        List<PaymentTransaction> pts = paymentTransactionSessionLocal.getAllPaymentTransaction();
        double totalKRG = 0;
        for (PaymentTransaction pt : pts) {
            if (pt.getRoomsBooked().get(0).getBookedRoom().getHotel().getHotelCodeName().equals("KRS")) {
                totalKRG = totalKRG + pt.getFinalPayment();
            }
        }
        double roundOff = (double) Math.round(totalKRG * 100) / 100;
        if (roundOff > topGrossingAmount) {
            topGrossingAmount = roundOff;
            topGrossingHotel = "Kent Ridge South";
        }
        return roundOff;
    }

    public double getKreTotalRevenue() throws NoResultException {
        List<PaymentTransaction> pts = paymentTransactionSessionLocal.getAllPaymentTransaction();
        double totalKRG = 0;
        for (PaymentTransaction pt : pts) {
            if (pt.getRoomsBooked().get(0).getBookedRoom().getHotel().getHotelCodeName().equals("KRE")) {
                totalKRG = totalKRG + pt.getFinalPayment();

            }
        }
        double roundOff = (double) Math.round(totalKRG * 100) / 100;
        if (roundOff > topGrossingAmount) {
            topGrossingAmount = roundOff;
            topGrossingHotel = "Kent Ridge East";
        }
        return roundOff;
    }

    public double getKrwTotalRevenue() throws NoResultException {
        List<PaymentTransaction> pts = paymentTransactionSessionLocal.getAllPaymentTransaction();
        double totalKRG = 0;
        for (PaymentTransaction pt : pts) {
            if (pt.getRoomsBooked().get(0).getBookedRoom().getHotel().getHotelCodeName().equals("KRW")) {
                totalKRG = totalKRG + pt.getFinalPayment();
            }
        }
        double roundOff = (double) Math.round(totalKRG * 100) / 100;
        if (roundOff > topGrossingAmount) {
            topGrossingAmount = roundOff;
            topGrossingHotel = "Kent Ridge West";
        }
        return roundOff;
    }

    public double getKrneTotalRevenue() throws NoResultException {
        List<PaymentTransaction> pts = paymentTransactionSessionLocal.getAllPaymentTransaction();
        double totalKRG = 0;
        for (PaymentTransaction pt : pts) {
            if (pt.getRoomsBooked().get(0).getBookedRoom().getHotel().getHotelCodeName().equals("KRNE")) {
                totalKRG = totalKRG + pt.getFinalPayment();
            }
        }
        double roundOff = (double) Math.round(totalKRG * 100) / 100;
        if (roundOff > topGrossingAmount) {
            topGrossingAmount = roundOff;
            topGrossingHotel = "Kent Ridge North East";
        }
        return roundOff;
    }

    public double getKrnwTotalRevenue() throws NoResultException {
        List<PaymentTransaction> pts = paymentTransactionSessionLocal.getAllPaymentTransaction();
        double totalKRG = 0;
        for (PaymentTransaction pt : pts) {
            if (pt.getRoomsBooked().get(0).getBookedRoom().getHotel().getHotelCodeName().equals("KRNW")) {
                totalKRG = totalKRG + pt.getFinalPayment();
            }
        }
        double roundOff = (double) Math.round(totalKRG * 100) / 100;
        if (roundOff > topGrossingAmount) {
            topGrossingAmount = roundOff;
            topGrossingHotel = "Kent Ridge North West";
        }
        return roundOff;
    }

    public double getKrseTotalRevenue() throws NoResultException {
        List<PaymentTransaction> pts = paymentTransactionSessionLocal.getAllPaymentTransaction();
        double totalKRG = 0;
        for (PaymentTransaction pt : pts) {
            if (pt.getRoomsBooked().get(0).getBookedRoom().getHotel().getHotelCodeName().equals("KRSE")) {
                totalKRG = totalKRG + pt.getFinalPayment();
            }
        }
        double roundOff = (double) Math.round(totalKRG * 100) / 100;
        if (roundOff > topGrossingAmount) {
            topGrossingAmount = roundOff;
            topGrossingHotel = "Kent Ridge South East";
        }
        return roundOff;
    }

    public double getKrswTotalRevenue() throws NoResultException {
        List<PaymentTransaction> pts = paymentTransactionSessionLocal.getAllPaymentTransaction();
        double totalKRG = 0;
        for (PaymentTransaction pt : pts) {
            if (pt.getRoomsBooked().get(0).getBookedRoom().getHotel().getHotelCodeName().equals("KRSW")) {
                totalKRG = totalKRG + pt.getFinalPayment();
            }
        }
        double roundOff = (double) Math.round(totalKRG * 100) / 100;
        if (roundOff > topGrossingAmount) {
            topGrossingAmount = roundOff;
            topGrossingHotel = "Kent Ridge South West";
        }
        return roundOff;
    }

    public String selectMailingList(MailingList m) {
        selectedMailingList = m;
        tempMailingList = selectedMailingList.getListToSend();

        return "editMailingList.xthml?faces-redirect=true";
    }

    public String displayDateRange() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        return (dateFormat.format(selectedSurcharge.getSurchargeFrom()) + " - " + dateFormat.format(selectedSurcharge.getSurchargeTo()));
    }

    public String displayHolidayDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        return (dateFormat.format(selectedHoliday.getHolidayDate()));
    }

    public String displayRoomFacilities() {
        List<RoomFacility> facilities = selectedRoom.getRoomFacilities();
        String returnString = "";
        for (RoomFacility rf : facilities) {
            returnString = returnString + rf.getRoomFacilityName() + ", ";
        }
        if (returnString.length() > 0) {
            returnString.substring(0, returnString.length() - 2);
        }

        return returnString;
    }

    public String displayMinibarItems() {
        List<MinibarItem> items = selectedRoom.getMiniBarItems();
        String returnString = "";
        for (MinibarItem mi : items) {
            returnString = returnString + mi.getItemName() + ", ";
        }
        if (returnString.length() > 0) {
            returnString.substring(0, returnString.length() - 2);
        }

        return returnString;
    }

    public String saveFacility() throws NoResultException {
        hotelFacilitySessionLocal.updateHotelFacility(selectedFacilityObj);

        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        Logging l = new Logging("Hotel Facility", "Update " + selectedFacilityObj.getHotelFacilityName() + " details", loggedInName);
        logSessionLocal.createLogging(l);
        selectedFacilityObj = null;

        return "ViewAllFacility.xhtml?faces-redirect=true";
    }

    public void selectFeedback(Feedback f) {
        selectedFeedback = f;
    }

    public void displayMailingList() throws NoResultException {
        selectedMailingList = mailingListSessionLocal.getMailingListByID(selectedMailingListID);
    }

    public String saveHoliday() throws NoResultException, ParseException {
//        hs.setHolidayDate(new SimpleDateFormat("yyyy-MM-dd").parse(holDate));
        selectedHoliday.setHolidayDate(new SimpleDateFormat("yyyy-MM-dd").parse(holDate));
        roomSessionLocal.updateHolidaySurcarhge(selectedHoliday);

        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        Logging l = new Logging("Holiday Surcharge", "Update " + selectedHoliday.getHolidayName() + " details", loggedInName);
        logSessionLocal.createLogging(l);
        selectedHoliday = null;
        holDate = null;

        return "manageRoomPriceSurcharge.xhtml?faces-redirect=true";
    }

    public String saveHotel() throws NoResultException {
        if (file != null) {
            try {

                InputStream bytes = file.getInputStream();
                //Files.copy(bytes, path, StandardCopyOption.REPLACE_EXISTING);

                URL ftp = new URL("ftp://zetegrdb:iqDcPqo8ornE@zetegral.website/public_html/krhgImages/" + file.getSubmittedFileName());
                URLConnection conn = ftp.openConnection();
                conn.setDoOutput(true);
                OutputStream out = conn.getOutputStream();
                // Copy an InputStream to that OutputStream then
                out.write(IOUtils.readFully(bytes, -1, false));
                out.close();
                selectedHotelObj.setHotelImage(file.getSubmittedFileName());

            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        hotelSessionLocal.updateHotel(selectedHotelObj);

        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        Logging l = new Logging("Hotel", "Update " + selectedHotelObj.getHotelName() + " details", loggedInName);
        logSessionLocal.createLogging(l);
        selectedHotelObj = null;

        return "manageHotel.xhtml?faces-redirect=true";
    }

    public String saveMinibarItem() throws NoResultException {
        roomSessionLocal.updateMinibarItem(selectedMinibarItem);

        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        Logging l = new Logging("Minibar Item", "Update " + selectedMinibarItem.getItemName() + " details", loggedInName);
        logSessionLocal.createLogging(l);
        selectedMinibarItem = null;

        return "manageMinibarItem.xhtml?faces-redirect=true";
    }

    public boolean checkIfEmailExist(String e) throws NoResultException {
        //  selectedMailingList = mailingListSessionLocal.getMailingListByID(selectedMailingListID);
        boolean emailExist = false;
        for (String check : tempMailingList) {
            if (check.equals(e)) {
                emailExist = true;
            }
        }

        return emailExist;
    }

    public void addOrRemoveEmail(String e) throws NoResultException {
        //selectedMailingList = mailingListSessionLocal.getMailingListByID(selectedMailingListID);
        boolean notExist = true;
        for (String check : tempMailingList) {
            if (check.equals(e)) {
                notExist = false;
            }
        }

        if (notExist == true) {
            tempMailingList.add(e);
        } else {
            tempMailingList.remove(e);
        }

        // mailingListSessionLocal.updateMailingList(selectedMailingList);
    }

    public boolean checkIfEmailExistForAdding(String e) throws NoResultException {
        boolean emailExist = false;
        if (tempMailingList != null) {
            for (String check : tempMailingList) {
                if (check.equals(e)) {
                    emailExist = true;
                }
            }
        }
        return emailExist;
    }

    public void addOrRemoveEmailForAdding(String e) throws NoResultException {
        boolean notExist = true;
        if (tempMailingList != null) {
            for (String check : tempMailingList) {
                if (check.equals(e)) {
                    notExist = false;
                }
            }
        }
        if (notExist == true) {
            tempMailingList.add(e);
        } else {
            tempMailingList.remove(e);
        }
    }

    public String updateMailingList() throws NoResultException {

        selectedMailingList.setListToSend(tempMailingList);
        mailingListSessionLocal.updateMailingList(selectedMailingList);

        mlListNameTB = null;
        tempMailingList = new ArrayList<String>();

        return "manageMailingList.xhtml?faces-redirect=true";
    }

    public String addNewMailingList() {
        MailingList ml = new MailingList();
        ml.setListName(mlListNameTB);
        ml.setListToSend(tempMailingList);
        mailingListSessionLocal.createMailingList(ml);

        mlListNameTB = null;
        tempMailingList = new ArrayList<String>();;

        return "manageMailingList.xhtml?faces-redirect=true";
    }

    public String saveMemberTier() throws NoResultException {
        memberTierSessionLocal.updateMemberTier(selectedMemberTier);

        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        Logging l = new Logging("Member Tier", "Update " + selectedMemberTier.getTierName() + " details", loggedInName);
        logSessionLocal.createLogging(l);
        selectedMemberTier = null;

        return "manageMemberTier.xhtml?faces-redirect=true";
    }

    public String saveMailingList() throws NoResultException {
        mailingListSessionLocal.updateMailingList(selectedMailingList);

        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        Logging l = new Logging("Mailing List", "Update " + selectedMailingList.getListName() + " details", loggedInName);
        logSessionLocal.createLogging(l);
        selectedMailingList = null;

        return "manageMailingList.xhtml?faces-redirect=true";
    }

    public String saveProfile() {

        return "index.xhtml?faces-redirect=true";
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

        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        Logging l = new Logging("Room", "Update " + selectedRoom.getRoomName() + " details", loggedInName);
        logSessionLocal.createLogging(l);
        selectedRoom = null;
        minibarItems = null;
        roomFacilities = null;

        return "manageRoom.xhtml?faces-redirect=true";
    }

    public String saveRoomFacility() throws NoResultException {

        if (iconFile != null) {
            try {
                InputStream bytes = iconFile.getInputStream();
                //Files.copy(bytes, path, StandardCopyOption.REPLACE_EXISTING);

                URL ftp = new URL("ftp://zetegrdb:iqDcPqo8ornE@zetegral.website/public_html/krhgImages/" + iconFile.getSubmittedFileName());
                URLConnection conn = ftp.openConnection();
                conn.setDoOutput(true);
                OutputStream out = conn.getOutputStream();
                // Copy an InputStream to that OutputStream then
                out.write(IOUtils.readFully(bytes, -1, false));
                out.close();
                selectedRoomFacility.setIconImg(iconFile.getSubmittedFileName());

            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        roomFacilitySessionLocal.updateRoomFacility(selectedRoomFacility);

        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        Logging l = new Logging("Room Facility", "Update " + selectedRoomFacility.getRoomFacilityName() + " details", loggedInName);
        logSessionLocal.createLogging(l);
        selectedRoomFacility = null;

        return "manageRoomFacility.xhtml?faces-redirect=true";
    }

    public String saveStaff() throws NoResultException {
        ArrayList<StaffType> tempStaffTypes = new ArrayList<StaffType>();
        if (stStaffType != null) {
            for (int i = 0; i < stStaffType.length; i++) {
                tempStaffTypes.add(staffSessionLocal.getStaffTypeByName(stStaffType[i].toString()));
            }
        }
        selectedStaff.setAccountRights(tempStaffTypes);
        staffSessionLocal.updateStaff(selectedStaff);

        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        Logging l = new Logging("Staff", "Update " + selectedStaff.getName() + " details", loggedInName);
        logSessionLocal.createLogging(l);
        selectedStaff = null;
        stStaffType = null;

        return "manageStaff.xhtml?faces-redirect=true";
    }

    public String saveSurcharge() throws NoResultException {
        ArrayList<String> tempSurcharges = new ArrayList<String>();
        if (esDaysToCharge != null) {
            for (int i = 0; i < esDaysToCharge.length; i++) {
                tempSurcharges.add(esDaysToCharge[i].toString());
            }
        }
        selectedSurcharge.setDaysToCharge(tempSurcharges);
        roomSessionLocal.updateExtraSurcarhge(selectedSurcharge);

        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        Logging l = new Logging("Surcharge Holiday", "Update " + selectedSurcharge.getSurchargeName() + " details", loggedInName);
        logSessionLocal.createLogging(l);
        selectedSurcharge = null;
        esDaysToCharge = null;

        return "manageRoomPrice.xhtml?faces-redirect=true";
    }

    public String saveRoomPricing() throws NoResultException {

        selectedRoomPricing.setPricingName(rpHotelTB + "_" + rpRoomTypeTB);

        roomPricingSessionLocal.updateRoomPricing(selectedRoomPricing);

        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        Logging l = new Logging("Room Pricing", "Update " + selectedRoomPricing.getPricingName() + " details", loggedInName);
        logSessionLocal.createLogging(l);
        selectedRoomPricing = null;
        rpRoomTypeTB = null;

        return "manageRoomPrice.xhtml?faces-redirect=true";
    }

    public String editSurcharge(Long sID) throws NoResultException {
        selectedSurcharge = roomSessionLocal.getExtraSurchargeByID(sID);
        List<String> tempSurhcarges = selectedSurcharge.getDaysToCharge();
        if (!tempSurhcarges.isEmpty()) {
            esDaysToCharge = new String[tempSurhcarges.size()];
            for (int i = 0; i < tempSurhcarges.size(); i++) {
                esDaysToCharge[i] = tempSurhcarges.get(i);
            }
        }

//        if (!selectedSurcharge.getDaysToCharge().isEmpty()) {
//            for (int i = 0; i < selectedSurcharge.getDaysToCharge().size(); i++) {
//                esDaysToCharge[i] = selectedSurcharge.getDaysToCharge().get(i);
//            }
//        }
        return "editSurcharge.xhtml?faces-redirect=true";
    }

    public String viewSurcharge(Long sID) throws NoResultException {
        selectedSurcharge = roomSessionLocal.getExtraSurchargeByID(sID);

        return "viewSurcharge.xhtml?faces-redirect=true";
    }

    public String editStaff(Long sID) throws NoResultException {
        selectedStaff = staffSessionLocal.getStaffByID(sID);
        List<StaffType> tempStaffTypes = selectedStaff.getAccountRights();
        if (!tempStaffTypes.isEmpty()) {
            stStaffType = new String[tempStaffTypes.size()];

            for (int i = 0; i < tempStaffTypes.size(); i++) {
                stStaffType[i] = tempStaffTypes.get(i).getStaffTypeName();
            }
        }

        return "editStaff.xhtml?faces-redirect=true";
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

    public String editRoomFacility(Long rfID) throws NoResultException {
        selectedRoomFacility = roomFacilitySessionLocal.getRoomFacilityByID(rfID);

        return "editRoomFacility.xhtml?faces-redirect=true";
    }

    public String viewRoom(Long rID) throws NoResultException {
        System.out.println("in view Room");
        System.out.println("ID: " + rID);
        selectedRoom = roomSessionLocal.getRoomByID(rID);
        selectedRoomID = rID;

        String selectedRoomTxt = selectedRoom.getRoomName();
        return "viewRoom.xhtml?faces-redirect=true";
    }

    public String editProfile(Long pID) {
        return "EditProfile.xhtml?faces-redirect=true";
    }

    public String editMinibarItem(Long miID) throws NoResultException {
        selectedMinibarItem = roomSessionLocal.getMinibarItemByID(miID);

        return "editMinibarItem.xhtml?faces-redirect=true";
    }

    public void selectMinibarItem(Long miID) throws NoResultException {
        selectedMinibarItem = roomSessionLocal.getMinibarItemByID(miID);
    }

    public String editHotel() throws NoResultException {

        return "editHotel.xhtml?faces-redirect=true";
    }

    public String editRoomPricing(Long rpID) throws NoResultException {
        selectedRoomPricing = roomPricingSessionLocal.getRoomPricingByID(rpID);
        rpRoomTypeTB = selectedRoomPricing.roomType();
        rpHotelTB = selectedRoomPricing.hotelCode();

        return "editRoomPricing.xhtml?faces-redirect=true";
    }

    public String deleteRoomPricing(Long rpID) throws NoResultException {
        roomPricingSessionLocal.deleteRoomPricing(rpID);

        return "manageRoomPrice.xhtml?faces-redirect=true";
    }

    public String editMemberTier(Long mbID) throws NoResultException {
        selectedMemberTier = memberTierSessionLocal.getMemberTierByID(mbID);
        return "editMemberTier.xhtml?faces-redirect=true";
    }

    public String deleteMemberTier(Long mbID) throws NoResultException {
        memberTierSessionLocal.deleteMemberTier(mbID);

        return "manageMemberTier.xhtml?faces-redirect=true";
    }

    public String viewMailingList(Long mlID) throws NoResultException {
        selectedMailingList = mailingListSessionLocal.getMailingListByID(mlID);

        return "viewMailingList.xhtml?faces-redirect=true";
    }

    public String editMailingList(Long mlID) throws NoResultException {
        selectedMailingList = mailingListSessionLocal.getMailingListByID(mlID);

        return "editMailingList.xhtml?faces-redirect=true";
    }

    public String deleteMailingList(Long mlID) throws NoResultException {
        mailingListSessionLocal.deleteMailingList(mlID);

        return "manageMailingList.xhtml?faces-redirect=true";
    }

    public String viewHotel(Long hID) throws NoResultException {
        System.out.println("in view hotel");
        System.out.println("ID: " + hID);

        selectedHotelObj = hotelSessionLocal.getHotelByID(hID);
        selectedHotelID = hID;
        List<HotelFacility> tempHotelFacilities = selectedHotelObj.getHotelFacilities();
        if (!tempHotelFacilities.isEmpty()) {
            setSelectedHotelFacilities(new String[tempHotelFacilities.size()]);
            for (int i = 0; i < tempHotelFacilities.size(); i++) {
                selectedHotelFacilities[i] = tempHotelFacilities.get(i).getHotelFacilityName();
//                System.out.println(hotelFacilities[i] = tempHotelFacilities.get(i).getHotelFacilityName());
            }

            for (int r = 0; r < getSelectedHotelFacilities().length; r++) {
                System.out.println(getSelectedHotelFacilities()[r]);
            }
        }
        return "viewHotel.xhtml?faces-redirect=true";
    }

    public String viewCustomer(Long cID) throws NoResultException {
        selectedCustomer = customerSessionLocal.getCustomerByID(cID);

        return "viewCustomer.xhtml?faces-redirect=true";
    }

    public String editHolidaySurcharge(Long hID) throws NoResultException {
        selectedHoliday = roomSessionLocal.getHolidaySurchargeByID(hID);
        DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
        holDate = dateFormat.format(selectedHoliday.getHolidayDate());

        return "editHolidaySurcharge.xhtml?faces-redirect=true";
    }

    public String viewFeedback(Long fID) throws NoResultException {
        selectedFeedback = feedbackSessionLocal.getFeedbackByID(fID);

        return "viewFeedback.xhtml?faces-redirect=true";
    }

    public String viewHolidaySurcharge(Long hID) throws NoResultException {
        selectedHoliday = roomSessionLocal.getHolidaySurchargeByID(hID);

        return "viewHolidaySurcharge.xhtml?faces-redirect=true";
    }

    public String editHotelFacility(Long fID) throws NoResultException {
        selectedFacilityObj = hotelFacilitySessionLocal.getHotelFacilityByID(fID);

        return "editFacility.xhtml?faces-redirect=true";
    }

    public String saveHotelFacility() throws NoResultException {
        if (hotelIconFile != null) {
            try {

                InputStream bytes = hotelIconFile.getInputStream();
                //Files.copy(bytes, path, StandardCopyOption.REPLACE_EXISTING);

                URL ftp = new URL("ftp://zetegrdb:iqDcPqo8ornE@zetegral.website/public_html/krhgImages/" + hotelIconFile.getSubmittedFileName());
                URLConnection conn = ftp.openConnection();
                conn.setDoOutput(true);
                OutputStream out = conn.getOutputStream();
                // Copy an InputStream to that OutputStream then
                out.write(IOUtils.readFully(bytes, -1, false));
                out.close();
                selectedFacilityObj.setHotelFacilityImage(hotelIconFile.getSubmittedFileName());

            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        hotelFacilitySessionLocal.updateHotelFacility(selectedFacilityObj);

        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        Logging l = new Logging("Hotel Facility", "Update " + selectedFacilityObj.getHotelFacilityName() + " details", loggedInName);
        logSessionLocal.createLogging(l);
        selectedFacilityObj = null;

        return "manageFacility.xhtml?faces-redirect=true";
    }

    public String generateNewPassword() {
        stPassword = new RandomPassword().generateRandomPassword();

        return "AddStaff.xhtml?faces-redirect=true";
    }

    public String deleteHotelFacility() throws NoResultException {
        List<Hotel> hotels = hotelSessionLocal.getAllHotels();
        HotelFacility hf = hotelFacilitySessionLocal.getHotelFacilityByID(selectedHFID);
        logActivityName = hf.getHotelFacilityName();
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();

        for (Hotel h : hotels) {
            if (h.getHotelFacilities().contains(hf)) {
                hotelSessionLocal.removeHotelFacility(h.getHotelID(), hf);
            }
        }
        hotelFacilitySessionLocal.deleteHotelFacility(selectedHFID);
        Logging l = new Logging("Hotel Facility", "Delete " + logActivityName + " from Hotel Facility", loggedInName);
        logSessionLocal.createLogging(l);

        return "manageFacility.xhtml?faces-redirect=true";
    }

    public void selectedDeleteHotelFacility(Long tempHFID) {
        selectedHFID = tempHFID;
    }

    public String deleteLog(Long lID) throws NoResultException {
        logSessionLocal.deleteLogging(lID);

        return "ViewLog.xhtml?faces-redirect=true";
    }

    public String removeFacilityFromHotel(Long hfID) throws NoResultException {
        Hotel h = hotelSessionLocal.getHotelByName(selectedHotel);
        HotelFacility hf = hotelFacilitySessionLocal.getHotelFacilityByID(selectedHFID);
        logActivityName = hf.getHotelFacilityName();
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        String tempHotelName = h.getHotelName();

        hotelSessionLocal.removeHotelFacility(h.getHotelID(), hf);
        Logging l = new Logging("Hotel Facility", "Remove " + logActivityName + " from " + tempHotelName, loggedInName);
        logSessionLocal.createLogging(l);

        return "ViewFacility.xhtml?faces-redirect=true";
    }

    public String deleteHotel() throws NoResultException {
        Long hotelID = selectedHotelObj.getHotelID();
        logActivityName = hotelSessionLocal.getHotelByID(hotelID).getHotelName();
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        hotelSessionLocal.deleteHotel(hotelID);

        Logging l = new Logging("Hotel", "Delete " + logActivityName + " from System", loggedInName);
        logSessionLocal.createLogging(l);

        return "manageHotel.xhtml?faces-redirect=true";
    }

    public void selectedDeleteRoomFacility(Long tempID) {
        selectedRFID = tempID;
    }

    public String deleteRoomFacility() throws NoResultException {

        List<Room> rooms = roomSessionLocal.getAllRooms();
        RoomFacility rf = roomFacilitySessionLocal.getRoomFacilityByID(selectedRFID);

        for (Room r : rooms) {
            if (r.getRoomFacilities().contains(rf)) {
                roomSessionLocal.removeRoomFacility(r.getRoomID(), rf);
            }
        }
        logActivityName = rf.getRoomFacilityName();
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        roomFacilitySessionLocal.deleteRoomFacility(selectedRFID);
        Logging l = new Logging("Room Facility", "Delete " + logActivityName + " from Room Facility", loggedInName);
        logSessionLocal.createLogging(l);

        return "manageRoomFacility.xhtml?faces-redirect=true";
    }

    public String deleteFeedback() throws NoResultException {
        Long feedBackID = selectedFeedback.getFeedBackID();
        logActivityName = feedbackSessionLocal.getFeedbackByID(feedBackID).getFeedBackTitle();
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        feedbackSessionLocal.deleteFeedback(feedBackID);
        Logging l = new Logging("Feedback", "Delete " + logActivityName + " from System", loggedInName);
        logSessionLocal.createLogging(l);

        return "manageFeedback.xhtml?faces-redirect=true";
    }

    public String deleteHolidaySurcharge(Long hsID) throws NoResultException {
        logActivityName = roomSessionLocal.getHolidaySurchargeByID(hsID).getHolidayName();
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        roomSessionLocal.deleteHolidaySurcharge(hsID);
        Logging l = new Logging("Holiday Surcharge", "Delete " + logActivityName + " from System", loggedInName);
        logSessionLocal.createLogging(l);

        return "manageRoomPrice.xhtml?faces-redirect=true";
    }

    public String deleteMinibarItem() throws NoResultException {

        MinibarItem mi = selectedMinibarItem;
        mi.setStatus(false);
        roomSessionLocal.updateMinibarItem(mi);

        logActivityName = mi.getItemName();
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        Logging l = new Logging("Minibar", "Soft Delete " + logActivityName + " from System", loggedInName);
        logSessionLocal.createLogging(l);

        return "manageMinibarItem.xhtml?faces-redirect=true";
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
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        roomSessionLocal.deleteRoom(r.getRoomID());
        selectedRoom = null;
        Logging l = new Logging("Room", "Delete " + logActivityName + " from " + tempHotelName, loggedInName);
        logSessionLocal.createLogging(l);

        return "manageRoom.xhtml?faces-redirect=true";
    }

    public String deleteSurcharge(Long sID) throws NoResultException {
        logActivityName = roomSessionLocal.getExtraSurchargeByID(sID).getSurchargeName();
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        roomSessionLocal.deleteExtraSurcharge(sID);
        Logging l = new Logging("Extra Surcharge", "Delete " + logActivityName + " from System", loggedInName);
        logSessionLocal.createLogging(l);

        return "manageRoomPrice.xhtml?faces-redirect=true";
    }

    public String deleteStaff(Long sID) throws NoResultException {
        logActivityName = staffSessionLocal.getStaffByID(sID).getUserName();
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        staffSessionLocal.deleteStaff(sID);
        Logging l = new Logging("Staff", "Delete " + logActivityName + " from System", loggedInName);
        logSessionLocal.createLogging(l);
        try {
            Thread.sleep(4000);
        } catch (Exception ex) {

        }

        return "manageStaff.xhtml?faces-redirect=true";
    }

    public String changeStatus(Long sID) throws NoResultException {
        logActivityName = staffSessionLocal.getStaffByID(sID).getUserName();
        Staff tempStaff = staffSessionLocal.getStaffByID(sID);

        if (tempStaff.getAccountStatus() == true) {
            staffSessionLocal.deactivateStaff(tempStaff);
        } else {
            staffSessionLocal.activateStaff(tempStaff);
        }

        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        Logging l = new Logging("Staff", "Change " + logActivityName + "'s status", loggedInName);
        logSessionLocal.createLogging(l);

        return "ViewStaff.xhtml?faces-redirect=true";
    }

    public List<HotelFacility> getAddableHotelFacilities() throws NoResultException {
        List<HotelFacility> allFacilities = hotelFacilitySessionLocal.getAllHotelFacilities();
        List<HotelFacility> unwantedList = getHotelFacilities();
        List<HotelFacility> returnList = new ArrayList<HotelFacility>();
        for (HotelFacility h : allFacilities) {
            boolean check = false;
            for (HotelFacility h2 : unwantedList) {
                if (h.getHotelFacilityName().equals(h2.getHotelFacilityName())) {
                    check = true;
                }
            }
            if (check == false) {
                returnList.add(h);
            }
        }

        return returnList;
    }

    public String addRoom() throws NoResultException {
        Room r = new Room();
        Hotel h = hotelSessionLocal.getHotelByName(addRoomHotelName);
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
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        hotelSessionLocal.addRoom(h.getHotelID(), r);
        Logging l = new Logging("Room", "Add " + logActivityName + " to " + addRoomHotelName, loggedInName);
        logSessionLocal.createLogging(l);

        roomName = null;
        roomNumber = null;
        roomType = null;
        roomPax = 1;
        minibarItems = null;
        roomFacilities = null;
        selectedHotel = addRoomHotelName;

        return "manageRoom.xhtml?faces-redirect=true";
    }

    public String addFacilityToHotel() throws NoResultException {
        Hotel hotel = hotelSessionLocal.getHotelByName(selectedHotel);
        hotelSessionLocal.addHotelFacility(hotel.getHotelID(), hotelFacilitySessionLocal.getHotelFacilityByName(selectedFacility));

        logActivityName = hotelFacilitySessionLocal.getHotelFacilityByName(selectedFacility).getHotelFacilityName();
        FacesContext context = FacesContext.getCurrentInstance();

        Staff loggedInStaff = (Staff) context.getApplication().createValueBinding("#{authenticationManagedBean.loggedInStaff}").getValue(context);
        String loggedInName = loggedInStaff.getName();
        Logging l = new Logging("Hotel Facility", "Add " + logActivityName + " to " + selectedHotel, loggedInName);
        logSessionLocal.createLogging(l);

        return "ViewFacility.xhtml?faces-redirect=true";
    }

    public String createNewHotel() throws NoResultException {
        Hotel hotel = new Hotel();
        String imgFile = noImageStr;
        hotel.setHotelName(hotelName);
        hotel.setHotelCodeName(hotelCode);
        hotel.setHotelContact(contactNumber);
        hotel.setHotelStar(hotelStar);
        hotel.setHotelAddress(address);
        ArrayList<HotelFacility> hList = new ArrayList<HotelFacility>();
        if (hotelFacilitiesArr != null) {
            for (int i = 0; i < hotelFacilitiesArr.length; i++) {
                hList.add(hotelFacilitySessionLocal.getHotelFacilityByName(hotelFacilitiesArr[i]));
            }
        }
        hotel.setHotelFacilities(hList);

        if (file != null) {
            imgFile = file.getSubmittedFileName();
            try {

                InputStream bytes = file.getInputStream();
                //Files.copy(bytes, path, StandardCopyOption.REPLACE_EXISTING);

                URL ftp = new URL("ftp://zetegrdb:iqDcPqo8ornE@zetegral.website/public_html/krhgImages/" + file.getSubmittedFileName());
                URLConnection conn = ftp.openConnection();
                conn.setDoOutput(true);
                OutputStream out = conn.getOutputStream();
                // Copy an InputStream to that OutputStream then
                out.write(IOUtils.readFully(bytes, -1, false));
                out.close();

            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        hotel.setHotelImage(imgFile);
        hotelSessionLocal.createHotel(hotel);

        logActivityName = hotelName;
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        Logging l = new Logging("Hotel", "Add " + logActivityName + " to System", loggedInName);
        logSessionLocal.createLogging(l);

        hotelFacilitiesArr = null;
        hotelName = null;
        hotelCode = null;
        contactNumber = null;
        hotelStar = 1;
        address = null;

        return "manageHotel.xhtml?faces-redirect=true";
    }

    public String createHotelFacility() {
        HotelFacility hf = new HotelFacility();
        hfImage = "";
        hf.setHotelFacilityName(hfName);
        hf.setHotelFacilityDescription(hfDescription);

        if (hotelIconFile != null) {
            hfImage = hotelIconFile.getSubmittedFileName();
            try {

                InputStream bytes = hotelIconFile.getInputStream();
                //Files.copy(bytes, path, StandardCopyOption.REPLACE_EXISTING);

                URL ftp = new URL("ftp://zetegrdb:iqDcPqo8ornE@zetegral.website/public_html/krhgImages/" + hotelIconFile.getSubmittedFileName());
                URLConnection conn = ftp.openConnection();
                conn.setDoOutput(true);
                OutputStream out = conn.getOutputStream();
                // Copy an InputStream to that OutputStream then
                out.write(IOUtils.readFully(bytes, -1, false));
                out.close();

            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        hf.setHotelFacilityImage(hfImage);
        hotelFacilitySessionLocal.createHotelFacility(hf);

        logActivityName = hfName;
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        Logging l = new Logging("Hotel Facility", "Add " + logActivityName + " to System", loggedInName);
        logSessionLocal.createLogging(l);

        hfImage = null;
        hfName = null;
        hotelIconFile = null;
        hfDescription = null;

        return "manageFacility.xhtml?faces-redirect=true";
    }

    public String createHolidaySurcharge() throws ParseException {
        HolidaySurcharge hs = new HolidaySurcharge();
        hs.setHolidayName(holName);
        hs.setHolidaySurchargePrice(holPrice);
        hs.setHolidayDate(new SimpleDateFormat("yyyy-MM-dd").parse(holDate));
        roomSessionLocal.createHolidaySurcharge(hs);

        logActivityName = holName;
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        Logging l = new Logging("Holiday Surhcarge", "Add " + logActivityName + " to System", loggedInName);
        logSessionLocal.createLogging(l);

        holName = null;
        holPrice = 0.0;
        holDate = null;

        return "manageRoomPrice.xhtml?faces-redirect=true";
    }

    public String createMemberTier() {
        MemberTier mt = new MemberTier();
        mt.setTierName(mtNameTB);
        mt.setTierPoints(mtPointsTB);
        memberTierSessionLocal.createMemberTier(mt);

        logActivityName = mtNameTB;
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        Logging l = new Logging("Member Tier", "Add " + logActivityName + " to System", loggedInName);
        logSessionLocal.createLogging(l);

        mtNameTB = null;
        mtPointsTB = 0;

        return "manageMemberTier.xhtml?faces-redirect=true";
    }

    public String createMinibarItem() {
        MinibarItem mi = new MinibarItem();
        mi.setItemName(miName);
        mi.setQty(1);
        mi.setPrice(miPrice);
        roomSessionLocal.createMinibarItem(mi);

        logActivityName = miName;
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        Logging l = new Logging("Minibar", "Add " + logActivityName + " to System", loggedInName);
        logSessionLocal.createLogging(l);

        miName = null;
        miPrice = 0.0;

        return "manageMinibarItem.xhtml?faces-redirect=true";
    }

    public String displayDays(ArrayList<String> days) {
        String returnString = "";
        for (String s : days) {
            returnString = returnString + s + ", ";
        }
        if (returnString.length() > 0) {
            returnString = returnString.substring(0, returnString.length() - 2);
        }

        return returnString;
    }

    public String displayDaysForViewSelected() {
        String returnString = "";
        ArrayList<String> days = selectedSurcharge.getDaysToCharge();
        for (String s : days) {
            returnString = returnString + s + ", ";
        }
        if (returnString.length() > 0) {
            returnString = returnString.substring(0, returnString.length() - 2);
        }

        return returnString;
    }

    public String displayStaffTypes() {
        String returnString = "";
        for (StaffType s : selectedStaff.getAccountRights()) {
            returnString = returnString + s.getStaffTypeName() + ", ";
        }
        if (returnString.length() > 0) {
            returnString = returnString.substring(0, returnString.length() - 2);
        }

        return returnString;
    }

    public String createRoomFacility() {
        RoomFacility rf = new RoomFacility();
        String imgFile = noImageStr;
        rf.setRoomFacilityName(rfName);
        rf.setRoomFacilityCategory(rfCategory);

        if (iconFile != null) {
            imgFile = iconFile.getSubmittedFileName();
            try {

                InputStream bytes = iconFile.getInputStream();
                //Files.copy(bytes, path, StandardCopyOption.REPLACE_EXISTING);

                URL ftp = new URL("ftp://zetegrdb:iqDcPqo8ornE@zetegral.website/public_html/krhgImages/" + iconFile.getSubmittedFileName());
                URLConnection conn = ftp.openConnection();
                conn.setDoOutput(true);
                OutputStream out = conn.getOutputStream();
                // Copy an InputStream to that OutputStream then
                out.write(IOUtils.readFully(bytes, -1, false));
                out.close();

            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }

        rf.setIconImg(imgFile);
        roomFacilitySessionLocal.createRoomFacility(rf);

        logActivityName = rfName;
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        Logging l = new Logging("Room Facility", "Add " + logActivityName + " to System", loggedInName);
        logSessionLocal.createLogging(l);

        rfName = null;
        rfCategory = null;
        rfIconImg = null;

        return "manageRoomFacility.xhtml?faces-redirect=true";
    }

    public String createSurcharge() throws ParseException {
        ExtraSurcharge es = new ExtraSurcharge();
        es.setSurchargeName(esName);
        es.setSurchargeFrom(new SimpleDateFormat("yyyy-MM-dd").parse(esDateFrom));
        es.setSurchargeTo(new SimpleDateFormat("yyyy-MM-dd").parse(esDateTo));
        es.setSurchargePrice(esPrice);
        ArrayList<String> daysList = new ArrayList<String>();
        if (esDaysToCharge != null) {
            for (int i = 0; i < esDaysToCharge.length; i++) {
                daysList.add(esDaysToCharge[i]);
            }
        }
        es.setDaysToCharge(daysList);
        roomSessionLocal.createExtraSurcharge(es);

        logActivityName = esName;
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        Logging l = new Logging("Extra Surcharge", "Add " + logActivityName + " to System", loggedInName);
        logSessionLocal.createLogging(l);

        esName = null;
        esDateFrom = null;
        esDateTo = null;
        esPrice = 0.0;
        daysList = null;

        return "manageRoomPrice.xhtml?faces-redirect=true";
    }

    public String createRoomPricing() throws ParseException {
        RoomPricing rp = new RoomPricing();
        rp.setPricingName(rpHotelTB + "_" + rpRoomTypeTB);
        rp.setPrice(rpPriceTb);

        roomPricingSessionLocal.createRoomPricing(rp);

        logActivityName = rpHotelTB + "_" + rpRoomTypeTB;
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        Logging l = new Logging("Room Pricing", "Add " + logActivityName + " to System", loggedInName);
        logSessionLocal.createLogging(l);

        rpHotelTB = null;
        rpRoomTypeTB = null;
        rpPriceTb = 0.0;

        return "manageRoomPrice.xhtml?faces-redirect=true";
    }

    public String createStaff() throws NoResultException {
        Staff s = new Staff();
        String tempUsername = stName.trim().replaceAll("\\s", "");
        s.setName(stName.trim());
        s.setPassword(stPassword);
        s.setEmail(stEmail);
        s.setPhoneNumber(stPhoneNumber);
        s.setGender(stGender);
        s.setNric(stNric);
        s.setAddress(stAddress);
        s.setJoinDate(java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        s.setHotelGroup(stHotel);
        s.setJobTitle(stJobTitle);
        s.setDepartment(stDepartment);
        s.setEntitledLeaves(stLeave);
        s.setAccountStatus(true);
        s.setNokName(stNokName);
        s.setNokAddress(stNokAddress);
        s.setNokPhoneNumber(stNokPhoneNumber);

        ArrayList<StaffType> tempStaffTypes = new ArrayList<StaffType>();
        if (stStaffType != null) {
            for (int i = 0; i < stStaffType.length; i++) {
                tempStaffTypes.add(staffSessionLocal.getStaffTypeByName(stStaffType[i].toString()));
            }
        }
        s.setAccountRights(tempStaffTypes);

        staffSessionLocal.createStaff(s);
        stName = null;
        stPassword = null;
        stEmail = null;
        stPhoneNumber = null;
        stGender = null;
        stNric = null;
        stAddress = null;
        stHotel = null;
        stJobTitle = null;
        stDepartment = null;
        stLeave = 7;
        stNokName = null;
        stNokAddress = null;
        stNokPhoneNumber = null;
        stStaffType = null;

        logActivityName = tempUsername;
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        Logging l = new Logging("Staff", "Create new staff " + tempUsername + " to System", loggedInName);
        logSessionLocal.createLogging(l);

        return "manageStaff.xhtml?faces-redirect=true";
    }

    public List<WeeklySchedule> getAllWeeklySchedule() {
        return weeklyScheduleSessionLocal.getAllWeeklySchedule();
    }

    public List<Shift> getAllShift() {
        return shiftSessionLocal.getAllShift();
    }

    public List<RoomPricing> getHotelRoomPricings(String hotelCode) {
        List<RoomPricing> returnList = roomPricingSessionLocal.getAllRoomPricingsByHotel(hotelCode);

        return returnList;
    }

    public List<Staff> getHQStaff() {
        List<Staff> checkList = getAllStaff();
        List<Staff> returnList = new ArrayList<Staff>();
        for (Staff s : checkList) {
            if (s.getHotelGroup().equals("HQ")) {
                Staff tempStaff = s;
                returnList.add(s);
            }
        }
        return returnList;
    }

    public List<WeeklySchedule> getAllWeeklyScheduleByWeek(String WeeklySchedule) throws ParseException {
        Date tempDate = (new SimpleDateFormat("dd-MM-yyyy").parse(WeeklySchedule));

        List<WeeklySchedule> returnList = new ArrayList<WeeklySchedule>();
        List<WeeklySchedule> checkList = weeklyScheduleSessionLocal.getAllWeeklySchedule();
        for (WeeklySchedule w : checkList) {
            if (w.getStartDate().compareTo(tempDate) == 0) {
                WeeklySchedule tempWeekSchdule = w;
                returnList.add(tempWeekSchdule);
            }
        }

        return returnList;
    }

    public String directCreateHQSchedule() {

        hqSchedule = new String[getHQStaff().size()][7];
        for (int i = 0; i < getHQStaff().size(); i++) {
            for (int j = 0; j < 7; j++) {
                hqSchedule[i][j] = "";
            }
        }

        return "createSchedule.xhtml?faces-redirect=true";
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

    public static void sendMailingListEmail(String recipient, String subject, String content) {

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

    public String sendMailingList() throws NoResultException {
        List<String> listToSend = new ArrayList<String>();
        if (mailingListToSend != null) {
            for (int i = 0; i < mailingListToSend.length; i++) {
                listToSend.add(mailingListToSend[i]);
            }
        }

        if (listToSend.contains("Silver Member")) {
            for (Customer c : getAllCustomer()) {
                if (c.getPoints() >= 5000 && c.getPoints() < 20000) {
                    listToSend.add(c.getEmail());
                }
            }
        }
        if (listToSend.contains("Gold Member")) {
            for (Customer c : getAllCustomer()) {
                if (c.getPoints() >= 20000 && c.getPoints() < 50000) {
                    listToSend.add(c.getEmail());
                }
            }
        }
        if (listToSend.contains("Platinum Member")) {
            for (Customer c : getAllCustomer()) {
                if (c.getPoints() >= 50000) {
                    listToSend.add(c.getEmail());
                }
            }
        }

        for (int i = 0; i < listToSend.size(); i++) {
            if (!listToSend.get(i).equals("Silver Member") && !listToSend.get(i).equals("Gold Member") && !listToSend.get(i).equals("Platinum Member")) {
                String content = "<!DOCTYPE html>\n"
                        + "<html lang=\"en\">\n"
                        + "<head>\n"
                        + "  <title>Mailing List </title>\n"
                        + "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n"
                        + "  <meta name=\"viewport\" content=\"width=device-width\">\n"
                        + "</head>\n"
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
                        + "        <table class=\"row content\" style=\"border-collapse: collapse; border-spacing: 0; width: 100%\">\n"
                        + "          <tr>\n"
                        + "            <td class=\"content__cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px\">\n"
                        + "              <center>\n"
                        + "                <table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">\n"
                        + "                  <tr>\n"
                        + "                    <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                        + "\n"
                        + "                      <p style=\"color: #777; font-size: 16px; line-height: 150%; margin: 0\">Dear " + customerSessionLocal.getCustomerByEmail(listToSend.get(i)).getFirstName() + ",<br><br>\n"
                        + "                       " + emailMsg + "</p>\n"
                        + "                     </tr>\n"
                        + "                </table>\n"
                        + "              </center>\n"
                        + "            </td>\n"
                        + "          </tr>\n"
                        + "        </table>\n"
                        + "       <table class=\"row section\" style=\"border-collapse: collapse; border-spacing: 0; border-top-color: #e5e5e5; border-top-style: solid; border-top-width: 1px; width: 100%\">\n"
                        + "          <tr>\n"
                        + "            <td class=\"section__cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding: 40px 0\">\n"
                        + "              <center>\n"
                        + "\n"
                        + "                <table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">\n"
                        + "                  <tr>\n"
                        + "                    <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                        + "                      <table class=\"row\" style=\"border-collapse: collapse; border-spacing: 0; width: 100%\">\n"
                        + "                        <tr class=\"order-list__item order-list__item--single\" style=\"border-bottom-color: #e5e5e5; border-bottom-style: none !important; border-bottom-width: 1px; width: 100%\">\n"
                        + "                          <td class=\"order-list__item__cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding: 0\">\n"
                        + "                            <table style=\"border-collapse: collapse; border-spacing: 0\">\n"
                        + "                            \n"
                        + "                              <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                        + "                                <img src=\"http://zetegral.website/krhgImages/KRHGblack.png\" align=\"left\" width=\"130\" height=\"100\" class=\"order-list__product-image\">\n"
                        + "                              </td>\n"
                        + "                              <td class=\"order-list__product-description-cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; width: 100%\"> \n"
                        + "                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n"
                        + "                                <span class=\"order-list__item-title\" style=\"color: rgb(0, 0, 0); font-size: 12px; font-weight: 600; line-height: 1.4\"><b>" + authBean.getLoggedInStaff().getName() + "(" + authBean.getLoggedInStaff().genderTitle() + ")</b></span>\n"
                        + "                                <br />\n"
                        + "                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n"
                        + "                                <span class=\"order-list__item-title\" style=\"color: rgb(0, 0, 0); font-size: 10px; font-weight: 600; line-height: 1.4\">" + authBean.getLoggedInStaff().getJobTitle() + "</span>\n"
                        + "                                <br />\n"
                        + "                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n"
                        + "                                <span class=\"order-list__item-title\" style=\"color: rgb(0, 0, 0); font-size: 10px; font-weight: 600; line-height: 1.4\">+65 6123 2000</span>\n"
                        + "                                <span class=\"order-list__item-title\" style=\"color: rgb(250, 0, 0); font-size: 10px; font-weight: 600; line-height: 1.4\">|</span>\n"
                        + "                                <span class=\"order-list__item-title\" style=\"color: rgb(0, 0, 0); font-size: 10px; font-weight: 600; line-height: 1.4\">" + authBean.getLoggedInStaff().getEmail() + "</span>\n"
                        + "                                <br />\n"
                        + "                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n"
                        + "                                <span class=\"order-list__item-title\" style=\"color: rgb(0, 0, 0); font-size: 10px; font-weight: 600; line-height: 1.4\">Kent Ridge Grand</span>\n"
                        + "                                <span class=\"order-list__item-title\" style=\"color: rgb(250, 0, 0); font-size: 10px; font-weight: 600; line-height: 1.4\">|</span>\n"
                        + "                                <span class=\"order-list__item-title\" style=\"color: rgb(0, 0, 0); font-size: 10px; font-weight: 600; line-height: 1.4\">63 Somerset Road, Singapore 238163</span>\n"
                        + "                                <br />\n"
                        + "                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n"
                        + "                                <span class=\"order-list__item-title\" style=\"color: rgb(0, 0, 0); font-size: 10px; font-weight: 600; line-height: 1.4\">If you have any questions, contact us at <a href=\"#\" style=\"color: #080e66; font-size: 12px; text-decoration: none\">reservations@KRHG.com</a></span>\n"
                        + "                                <br />\n"
                        + "                              </td>\n"
                        + "                                           \n"
                        + "                            </table>\n"
                        + "                          </td>\n"
                        + "                        </tr>\n"
                        + "                      </table>\n"
                        + "                    </td>\n"
                        + "                    </tr>\n"
                        + "                </table>    \n"
                        + "            </center>\n"
                        + "        </td>\n"
                        + "    </tr>\n"
                        + "  </table>\n"
                        + "  <table class=\"row footer\" style=\"border-collapse: collapse; border-spacing: 0; border-top-color: #e5e5e5; border-top-style: solid; border-top-width: 1px; width: 100%\">\n"
                        + "          <tr>\n"
                        + "            <td class=\"footer__cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding: 35px 0\">\n"
                        + "              <center>\n"
                        + "                <table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">\n"
                        + "                  \n"
                        + "                </table>\n"
                        + "              </center>\n"
                        + "            </td>\n"
                        + "          </tr>\n"
                        + "        </table>\n"
                        + "      </td>\n"
                        + "    </tr>\n"
                        + "  </table>\n"
                        + "</body>\n"
                        + "</html>";
                sendMailingListEmail(listToSend.get(i), subjectTB, content);
                System.out.println(listToSend.get(i));
            }
        }

        subjectTB = null;
        emailMsg = null;
        mailingListToSend = null;

        return "sendMail.xhtml?faces-redirect=true";
    }

    public List<String> getDistinctDate() {
        return weeklyScheduleSessionLocal.getDistinctDate();
    }

    public String viewStaffDetail(Long sID) throws NoResultException {
        selectedStaff = staffSessionLocal.getStaffByID(sID);

        return "viewStaff.xhtml?faces-redirect=true";
    }

    public List<MinibarItem> getMinibarItemList() {
        return houseKeepingOrderSessionLocal.getAllMinibarItem();
    }

    public List<RoomFacility> getRoomFacilityList() {
        return roomFacilitySessionLocal.getAllRoomFacilities();
    }

    public List<Room> getHotelRooms() throws NoResultException {
        Hotel hotel = hotelSessionLocal.getHotelByName(selectedHotel);
        return hotel.getRooms();
    }

    public void setHotelSessionLocal(HotelSessionLocal hotelSessionLocal) {
        this.hotelSessionLocal = hotelSessionLocal;
    }

    public String viewHotelFacilities(String hotelName) {
        selectedHotel = hotelName;

        return "ViewFacility.xhtml?faces-redirect=true";
    }

    public String viewHotelRooms(String hotelName) {
        selectedHotel = hotelName;

        return "manageRoom.xhtml?faces-redirect=true";
    }

    public String viewHotelFeedbacks(String hotelName) {
        selectedHotel = hotelName;

        return "ViewFeedback.xhtml?faces-redirect=true";
    }

    public String getSelectedHotel() {
        return selectedHotel;
    }

    public void setSelectedHotel(String selectedHotel) {
        this.selectedHotel = selectedHotel;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getHotelStar() {
        return hotelStar;
    }

    public void setHotelStar(int hotelStar) {
        this.hotelStar = hotelStar;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public HotelFacilitySessionLocal getHotelFacilitySessionLocal() {
        return hotelFacilitySessionLocal;
    }

    public void setHotelFacilitySessionLocal(HotelFacilitySessionLocal hotelFacilitySessionLocal) {
        this.hotelFacilitySessionLocal = hotelFacilitySessionLocal;
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

    public String getSelectedFacility() {
        return selectedFacility;
    }

    public void setSelectedFacility(String selectedFacility) {
        this.selectedFacility = selectedFacility;
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

    public HouseKeepingOrderSessionLocal getHouseKeepingOrderSessionLocal() {
        return houseKeepingOrderSessionLocal;
    }

    public void setHouseKeepingOrderSessionLocal(HouseKeepingOrderSessionLocal houseKeepingOrderSessionLocal) {
        this.houseKeepingOrderSessionLocal = houseKeepingOrderSessionLocal;
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

    public String getHfName() {
        return hfName;
    }

    public void setHfName(String hfName) {
        this.hfName = hfName;
    }

    public String getHfImage() {
        return hfImage;
    }

    public void setHfImage(String hfImage) {
        this.hfImage = hfImage;
    }

    public String getHfDescription() {
        return hfDescription;
    }

    public void setHfDescription(String hfDescription) {
        this.hfDescription = hfDescription;
    }

    public String getHolName() {
        return holName;
    }

    public void setHolName(String holName) {
        this.holName = holName;
    }

    public String getHolDate() {
        return holDate;
    }

    public void setHolDate(String holDate) {
        this.holDate = holDate;
    }

    public double getHolPrice() {
        return holPrice;
    }

    public void setHolPrice(double holPrice) {
        this.holPrice = holPrice;
    }

    public String getMiName() {
        return miName;
    }

    public void setMiName(String miName) {
        this.miName = miName;
    }

    public double getMiPrice() {
        return miPrice;
    }

    public void setMiPrice(double miPrice) {
        this.miPrice = miPrice;
    }

    public String getRfName() {
        return rfName;
    }

    public void setRfName(String rfName) {
        this.rfName = rfName;
    }

    public String getRfCategory() {
        return rfCategory;
    }

    public void setRfCategory(String rfCategory) {
        this.rfCategory = rfCategory;
    }

    public String getRfIconImg() {
        return rfIconImg;
    }

    public void setRfIconImg(String rfIconImg) {
        this.rfIconImg = rfIconImg;
    }

    public String getEsName() {
        return esName;
    }

    public void setEsName(String esName) {
        this.esName = esName;
    }

    public String getEsDateFrom() {
        return esDateFrom;
    }

    public void setEsDateFrom(String esDateFrom) {
        this.esDateFrom = esDateFrom;
    }

    public String getEsDateTo() {
        return esDateTo;
    }

    public void setEsDateTo(String esDateTo) {
        this.esDateTo = esDateTo;
    }

    public String[] getEsDaysToCharge() {
        return esDaysToCharge;
    }

    public void setEsDaysToCharge(String[] esDaysToCharge) {
        this.esDaysToCharge = esDaysToCharge;
    }

    public double getEsPrice() {
        return esPrice;
    }

    public void setEsPrice(double esPrice) {
        this.esPrice = esPrice;
    }

    public HotelSessionLocal getHotelSessionLocal() {
        return hotelSessionLocal;
    }

    public String getAddRoomHotelName() {
        return addRoomHotelName;
    }

    public void setAddRoomHotelName(String addRoomHotelName) {
        this.addRoomHotelName = addRoomHotelName;
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

    public String getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public String getLogActivityName() {
        return logActivityName;
    }

    public void setLogActivityName(String logActivityName) {
        this.logActivityName = logActivityName;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public String getStPassword() {
        return stPassword;
    }

    public void setStPassword(String stPassword) {
        this.stPassword = stPassword;
    }

    public String getStEmail() {
        return stEmail;
    }

    public void setStEmail(String stEmail) {
        this.stEmail = stEmail;
    }

    public String getStPhoneNumber() {
        return stPhoneNumber;
    }

    public void setStPhoneNumber(String stPhoneNumber) {
        this.stPhoneNumber = stPhoneNumber;
    }

    public String getStGender() {
        return stGender;
    }

    public void setStGender(String stGender) {
        this.stGender = stGender;
    }

    public String getStNric() {
        return stNric;
    }

    public void setStNric(String stNric) {
        this.stNric = stNric;
    }

    public String getStAddress() {
        return stAddress;
    }

    public void setStAddress(String stAddress) {
        this.stAddress = stAddress;
    }

    public String getStHotel() {
        return stHotel;
    }

    public void setStHotel(String stHotel) {
        this.stHotel = stHotel;
    }

    public String getStJobTitle() {
        return stJobTitle;
    }

    public void setStJobTitle(String stJobTitle) {
        this.stJobTitle = stJobTitle;
    }

    public String getStDepartment() {
        return stDepartment;
    }

    public void setStDepartment(String stDepartment) {
        this.stDepartment = stDepartment;
    }

    public int getStLeave() {
        return stLeave;
    }

    public void setStLeave(int stLeave) {
        this.stLeave = stLeave;
    }

    public String getStNokName() {
        return stNokName;
    }

    public void setStNokName(String stNokName) {
        this.stNokName = stNokName;
    }

    public String getStNokAddress() {
        return stNokAddress;
    }

    public void setStNokAddress(String stNokAddress) {
        this.stNokAddress = stNokAddress;
    }

    public String getStNokPhoneNumber() {
        return stNokPhoneNumber;
    }

    public void setStNokPhoneNumber(String stNokPhoneNumber) {
        this.stNokPhoneNumber = stNokPhoneNumber;
    }

    public String[] getStStaffType() {
        return stStaffType;
    }

    public void setStStaffType(String[] stStaffType) {
        this.stStaffType = stStaffType;
    }

    public StaffSessionLocal getStaffSessionLocal() {
        return staffSessionLocal;
    }

    public void setStaffSessionLocal(StaffSessionLocal staffSessionLocal) {
        this.staffSessionLocal = staffSessionLocal;
    }

    public Staff getSelectedStaff() {
        return selectedStaff;
    }

    public void setSelectedStaff(Staff selectedStaff) {
        this.selectedStaff = selectedStaff;
    }

    public HotelFacility getSelectedFacilityObj() {
        return selectedFacilityObj;
    }

    public void setSelectedFacilityObj(HotelFacility selectedFacilityObj) {
        this.selectedFacilityObj = selectedFacilityObj;
    }

    public HolidaySurcharge getSelectedHoliday() {
        return selectedHoliday;
    }

    public void setSelectedHoliday(HolidaySurcharge selectedHoliday) {
        this.selectedHoliday = selectedHoliday;
    }

    public Hotel getSelectedHotelObj() {
        return selectedHotelObj;
    }

    public void setSelectedHotelObj(Hotel selectedHotelObj) {
        this.selectedHotelObj = selectedHotelObj;
    }

    public MinibarItem getSelectedMinibarItem() {
        return selectedMinibarItem;
    }

    public void setSelectedMinibarItem(MinibarItem selectedMinibarItem) {
        this.selectedMinibarItem = selectedMinibarItem;
    }

    public Room getSelectedRoom() {
        return selectedRoom;
    }

    public void setSelectedRoom(Room selectedRoom) {
        this.selectedRoom = selectedRoom;
    }

    public RoomFacility getSelectedRoomFacility() {
        return selectedRoomFacility;
    }

    public void setSelectedRoomFacility(RoomFacility selectedRoomFacility) {
        this.selectedRoomFacility = selectedRoomFacility;
    }

    public ExtraSurcharge getSelectedSurcharge() {
        return selectedSurcharge;
    }

    public void setSelectedSurcharge(ExtraSurcharge selectedSurcharge) {
        this.selectedSurcharge = selectedSurcharge;
    }

    public String getNoImageStr() {
        return noImageStr;
    }

    public void setNoImageStr(String noImageStr) {
        this.noImageStr = noImageStr;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public Part getIconFile() {
        return iconFile;
    }

    public void setIconFile(Part iconFile) {
        this.iconFile = iconFile;
    }

    public Feedback getSelectedFeedback() {
        return selectedFeedback;
    }

    public void setSelectedFeedback(Feedback selectedFeedback) {
        this.selectedFeedback = selectedFeedback;
    }

    public Part getHotelIconFile() {
        return hotelIconFile;
    }

    public void setHotelIconFile(Part hotelIconFile) {
        this.hotelIconFile = hotelIconFile;
    }

    public RoomPricingSessionLocal getRoomPricingSessionLocal() {
        return roomPricingSessionLocal;
    }

    public void setRoomPricingSessionLocal(RoomPricingSessionLocal roomPricingSessionLocal) {
        this.roomPricingSessionLocal = roomPricingSessionLocal;
    }

    public String[] getHotelFacilitiesArr() {
        return hotelFacilitiesArr;
    }

    public void setHotelFacilitiesArr(String[] hotelFacilitiesArr) {
        this.hotelFacilitiesArr = hotelFacilitiesArr;
    }

    public RoomPricing getSelectedRoomPricing() {
        return selectedRoomPricing;
    }

    public void setSelectedRoomPricing(RoomPricing selectedRoomPricing) {
        this.selectedRoomPricing = selectedRoomPricing;
    }

    public String getRpHotelTB() {
        return rpHotelTB;
    }

    public void setRpHotelTB(String rpHotelTB) {
        this.rpHotelTB = rpHotelTB;
    }

    public String getRpRoomTypeTB() {
        return rpRoomTypeTB;
    }

    public void setRpRoomTypeTB(String rpRoomTypeTB) {
        this.rpRoomTypeTB = rpRoomTypeTB;
    }

    public double getRpPriceTb() {
        return rpPriceTb;
    }

    public void setRpPriceTb(double rpPriceTb) {
        this.rpPriceTb = rpPriceTb;
    }

    public MemberTierSessionLocal getMemberTierSessionLocal() {
        return memberTierSessionLocal;
    }

    public void setMemberTierSessionLocal(MemberTierSessionLocal memberTierSessionLocal) {
        this.memberTierSessionLocal = memberTierSessionLocal;
    }

    public MailingListSessionLocal getMailingListSessionLocal() {
        return mailingListSessionLocal;
    }

    public void setMailingListSessionLocal(MailingListSessionLocal mailingListSessionLocal) {
        this.mailingListSessionLocal = mailingListSessionLocal;
    }

    public MemberTier getSelectedMemberTier() {
        return selectedMemberTier;
    }

    public void setSelectedMemberTier(MemberTier selectedMemberTier) {
        this.selectedMemberTier = selectedMemberTier;
    }

    public MailingList getSelectedMailingList() {
        return selectedMailingList;
    }

    public void setSelectedMailingList(MailingList selectedMailingList) {
        this.selectedMailingList = selectedMailingList;
    }

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    public String getMtNameTB() {
        return mtNameTB;
    }

    public void setMtNameTB(String mtNameTB) {
        this.mtNameTB = mtNameTB;
    }

    public int getMtPointsTB() {
        return mtPointsTB;
    }

    public void setMtPointsTB(int mtPointsTB) {
        this.mtPointsTB = mtPointsTB;
    }

    public CustomerSessionLocal getCustomerSessionLocal() {
        return customerSessionLocal;
    }

    public void setCustomerSessionLocal(CustomerSessionLocal customerSessionLocal) {
        this.customerSessionLocal = customerSessionLocal;
    }

    public String getTempHotelNameTB() {
        return tempHotelNameTB;
    }

    public void setTempHotelNameTB(String tempHotelNameTB) {
        this.tempHotelNameTB = tempHotelNameTB;
    }

    public Long getSelectedMailingListID() {
        return selectedMailingListID;
    }

    public void setSelectedMailingListID(Long selectedMailingListID) {
        this.selectedMailingListID = selectedMailingListID;
    }

    public List<String> getTempMailingList() {
        return tempMailingList;
    }

    public void setTempMailingList(List<String> tempMailingList) {
        this.tempMailingList = tempMailingList;
    }

    public String getMlListNameTB() {
        return mlListNameTB;
    }

    public void setMlListNameTB(String mlListNameTB) {
        this.mlListNameTB = mlListNameTB;
    }

    /**
     * @return the selectedHotelFacilities
     */
    public String[] getSelectedHotelFacilities() {
        return selectedHotelFacilities;
    }

    /**
     * @param selectedHotelFacilities the selectedHotelFacilities to set
     */
    public void setSelectedHotelFacilities(String[] selectedHotelFacilities) {
        this.selectedHotelFacilities = selectedHotelFacilities;
    }

    public Long getSelectedHFID() {
        return selectedHFID;
    }

    public void setSelectedHFID(Long selectedHFID) {
        this.selectedHFID = selectedHFID;
    }

    public Long getSelectedRFID() {
        return selectedRFID;
    }

    public void setSelectedRFID(Long selectedRFID) {
        this.selectedRFID = selectedRFID;
    }

    public Long getSelectedRoomID() {
        return selectedRoomID;
    }

    public void setSelectedRoomID(Long selectedRoomID) {
        this.selectedRoomID = selectedRoomID;
    }

    public Long getSelectedHotelID() {
        return selectedHotelID;
    }

    public void setSelectedHotelID(Long selectedHotelID) {
        this.selectedHotelID = selectedHotelID;
    }

    public List<PromoCode> getAllPromoCode() {
        return allPromoCode = promocodesessionlocal.getAllPromoCodes();
    }

    public void setAllPromoCode(List<PromoCode> allPromoCode) {
        this.allPromoCode = allPromoCode;
    }

    public String getPromocodeName() {
        return promocodeName;
    }

    public void setPromocodeName(String promocodeName) {
        this.promocodeName = promocodeName;
    }

    public String getPromoStartDate() {
        return promoStartDate;
    }

    public void setPromoStartDate(String promoStartDate) {
        this.promoStartDate = promoStartDate;
    }

    public String getPromoEndDate() {
        return promoEndDate;
    }

    public void setPromoEndDate(String promoEndDate) {
        this.promoEndDate = promoEndDate;
    }

    public double getPromodiscount() {
        return promodiscount;
    }

    public void setPromodiscount(double promodiscount) {
        this.promodiscount = promodiscount;
    }

    public String createPromoCode() throws ParseException {
        PromoCode pc = new PromoCode();
        pc.setPromoCode(promocodeName);
        pc.setDiscount(promodiscount);
        pc.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(promoStartDate));
        pc.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(promoEndDate));
        pc.setStatus("Active");

        promocodesessionlocal.createPromoCode(pc);
        promocodeName = null;
        promodiscount = 0.0;
        promoStartDate = null;
        promoEndDate = null;

        return "promocode.xhtml?faces-redirect=true";
    }

    public String editPromo(PromoCode o) {
        editPromo = o;
        promocodeName = o.getPromoCode();
        promodiscount = o.getDiscount() * 100;
        promoStatus = o.getStatus();
        promoStartDate = convertDateFormat(o.getStartDate());
        promoEndDate = convertDateFormat(o.getEndDate());;

        return "editPromoCode.xhtml?faces-redirect=true";
    }

    public String doupdatePromocode()throws ParseException,NoResultException {
        editPromo.setPromoCode(promocodeName);
        editPromo.setDiscount(promodiscount);
        editPromo.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(promoStartDate));
        editPromo.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(promoEndDate));
        editPromo.setStatus(promoStatus);
        
        promocodesessionlocal.updatePromoCode(editPromo);
        
        return "promocode.xhtml?faces-redirect=true";
    }
}
