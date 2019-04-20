/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.CreditCard;
import entity.Customer;
import entity.Feedback;
import entity.FoodMenuItem;
import entity.FoodOrder;
import entity.FoodOrderedItem;
import entity.FunctionRoom;
import entity.Hotel;
import entity.HotelFacility;
import entity.HouseKeepingOrder;
import entity.LaundryOrder;
import entity.LaundryOrderedItem;
import entity.LaundryType;
import entity.LostAndFoundReport;
import entity.MaintainenceOrder;
import entity.MemberTier;
import entity.MinibarItem;
import entity.MinibarOrder;
import entity.MinibarOrderedItem;
import entity.MinibarStock;
import entity.PaymentTransaction;
import entity.PromoCode;
import entity.Room;
import entity.RoomBooking;
import entity.RoomFacility;
import entity.RoomPricing;
import entity.Staff;
import entity.StaffType;
import error.NoResultException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author dk349
 */
@Singleton
@LocalBean
@Startup
public class DataInitializationSessionBean {

    @PersistenceContext(unitName = "KRHG-ejbPU")
    private EntityManager em;

    @EJB
    HotelSessionLocal hotelSessionLocal;
    @EJB
    HotelFacilitySessionLocal hotelFacilitySessionLocal;
    @EJB
    RoomSessionLocal roomSessionLocal;
    @EJB
    StaffSessionLocal staffSessionLocal;
    @EJB
    RoomFacilitySessionLocal roomFacilitySessionLocal;
    @EJB
    HouseKeepingOrderSessionLocal houseKeepingOrderSessionLocal;
    @EJB
    CustomerSessionLocal customerSessionLocal;
    @EJB
    BookingSessionLocal bookingSessionLocal;
    @EJB
    MaintainenceOrderSessionLocal maintainenceOrderSessionLocal;
    @EJB
    LostAndFoundSessionLocal lostAndFoundSessionLocal;
    @EJB
    FoodMenuItemSessionLocal foodMenuItemSessionLocal;
    @EJB
    PaymentTransactionSessionLocal paymentTransactionSessionLocal;
    @EJB
    FunctionRoomSessionLocal functionRoomSessionLocal;
    @EJB
    RoomPricingSessionLocal roomPricingSessionLocal;
    @EJB
    PromoCodeSessionLocal promoCodeSessionLocal;
    @EJB
    FeedbackSessionLocal feedbackSessionLocal;
    @EJB
    LaundrySessionLocal laundrySessionLocal;
    @EJB
    FoodOrderSessionLocal foodOrderSessionLocal;
    @EJB
    MemberTierSessionLocal memberTierSessionLocal;

    public DataInitializationSessionBean() {
    }

    @PostConstruct
    public void postConstruct() {
        if (em.find(Hotel.class, 1l) == null) {
            try {
                initializeData();
                initializeKRGRoom();
                initializeKRCRoom();
                initializeKRNRoom();
                initializeKRSRoom();
                initializeKRERoom();
                initializeKRWRoom();
                initializeKRNERoom();
                initializeKRNWRoom();
                initializeKRSERoom();
                initializeKRSWRoom();
                intializeRoomBookingsAndCustomer();
				initializeRoomBooking2();
                intializeRequests();
            } catch (NoResultException ex) {
                ex.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }

    public void initializeData() throws NoResultException, ParseException {
        //********************************************* Member Tier ************************************************
        MemberTier mt1 = new MemberTier("Silver Member", 5000);
        memberTierSessionLocal.createMemberTier(mt1);
        MemberTier mt2 = new MemberTier("Gold Member", 20000);
        memberTierSessionLocal.createMemberTier(mt2);
        MemberTier mt3 = new MemberTier("Platinum Member", 50000);
        memberTierSessionLocal.createMemberTier(mt3);

//*********************************************Staff Type************************************************
        StaffType st1 = new StaffType("Housekeeping Staff");
        staffSessionLocal.createStaffType(st1);
        st1 = staffSessionLocal.getStaffTypeByName("Housekeeping Staff");

        StaffType st2 = new StaffType("Housekeeping Manager");
        staffSessionLocal.createStaffType(st2);
        st2 = staffSessionLocal.getStaffTypeByName("Housekeeping Manager");

        StaffType st3 = new StaffType("Laundry Staff");
        staffSessionLocal.createStaffType(st3);
        st3 = staffSessionLocal.getStaffTypeByName("Laundry Staff");

        StaffType st4 = new StaffType("Laundry Manager");
        staffSessionLocal.createStaffType(st4);
        st4 = staffSessionLocal.getStaffTypeByName("Laundry Manager");

        StaffType st5 = new StaffType("Kitchen Staff");
        staffSessionLocal.createStaffType(st5);
        st5 = staffSessionLocal.getStaffTypeByName("Kitchen Staff");

        StaffType st6 = new StaffType("Kitchen Manager");
        staffSessionLocal.createStaffType(st6);
        st6 = staffSessionLocal.getStaffTypeByName("Kitchen Manager");

        StaffType st7 = new StaffType("Sales and Marketing Staff");
        staffSessionLocal.createStaffType(st7);
        st7 = staffSessionLocal.getStaffTypeByName("Sales and Marketing Staff");

        StaffType st8 = new StaffType("Sales and Marketing Manager");
        staffSessionLocal.createStaffType(st8);
        st8 = staffSessionLocal.getStaffTypeByName("Sales and Marketing Manager");

        StaffType st9 = new StaffType("Front Desk Staff");
        staffSessionLocal.createStaffType(st9);
        st9 = staffSessionLocal.getStaffTypeByName("Front Desk Staff");

        StaffType st10 = new StaffType("Front Desk Manager");
        staffSessionLocal.createStaffType(st10);
        st10 = staffSessionLocal.getStaffTypeByName("Front Desk Manager");

        StaffType st11 = new StaffType("IT Staff");
        staffSessionLocal.createStaffType(st11);
        st11 = staffSessionLocal.getStaffTypeByName("IT Staff");

        StaffType st12 = new StaffType("Finance Staff");
        staffSessionLocal.createStaffType(st12);
        st12 = staffSessionLocal.getStaffTypeByName("Finance Staff");

        StaffType st13 = new StaffType("Finance Manager");
        staffSessionLocal.createStaffType(st13);
        st13 = staffSessionLocal.getStaffTypeByName("Finance Manager");

        StaffType st14 = new StaffType("HR Staff");
        staffSessionLocal.createStaffType(st14);
        st14 = staffSessionLocal.getStaffTypeByName("HR Staff");

        StaffType st15 = new StaffType("General Manager");
        staffSessionLocal.createStaffType(st15);
        st15 = staffSessionLocal.getStaffTypeByName("General Manager");

        StaffType st16 = new StaffType("Testing");
        staffSessionLocal.createStaffType(st16);
        st16 = staffSessionLocal.getStaffTypeByName("Testing");

//*********************************************Staff************************************************
        Staff s0 = new Staff("Test User", "test", encryptPassword("test"), "zell1502@hotmail.com", "88244165", "male", "S9226940Z", "7 Lok Yang Vista", new Date(), "Kent Ridge North", "Laundry Staff", "Laundry", 7, true, "Ee Pui Ling", "7 Lok Yang Vista", "68701722");
        s0.addAccountRights(st1);
        s0.addAccountRights(st2);
        s0.addAccountRights(st3);
        s0.addAccountRights(st4);
        s0.addAccountRights(st5);
        s0.addAccountRights(st6);
        s0.addAccountRights(st7);
        s0.addAccountRights(st8);
        s0.addAccountRights(st9);
        s0.addAccountRights(st10);
        s0.addAccountRights(st11);
        s0.addAccountRights(st12);
        s0.addAccountRights(st13);
        s0.addAccountRights(st14);
        s0.addAccountRights(st15);
        s0.addAccountRights(st16);
        staffSessionLocal.createStaff(s0);

        Staff s1 = new Staff("Kenny Ee", "kennyee", encryptPassword("password"), "kennyee@krhg.com.sg", "88244165", "Male", "S9226940Z", "7 Lok Yang Vista", new Date(), "Kent Ridge West", "Laundry Staff", "Laundry", 7, true, "Ee Pui Ling", "7 Lok Yang Vista", "68701722");
        s1.addAccountRights(st3);
        staffSessionLocal.createStaff(s1);

        Staff s2 = new Staff("David Chia Zhi Jie", "davidchia", encryptPassword("password"), "davidchia@krhg.com.sg", "98861094", "Male", "S6831300G", "Blk 226 Lorong 7 Pasir Ris, #04-32", new Date(), "Kent Ridge Grand", "Kitchen Manager", "Kitchen", 14, true, "Chia Xian Siew", "Blk 226 Lorong 7 Pasir Ris, #04-32", "67494068");
        s2.addAccountRights(st6);
        s2.addAccountRights(st5);
        staffSessionLocal.createStaff(s2);

        Staff s3 = new Staff("Alice Chai", "alicechai", encryptPassword("password"), "alicechai@krhg.com.sg", "93070252", "Female", "S3543767C", "Blk 377 Serangoon North Street 88, #15-09", new Date(), "Kent Ridge Central", "Housekeeping Manager", "Housekeeping", 14, true, "Chai Li Ting", "Blk 377 Serangoon North Street 88, #15-09", "61935979");
        s3.addAccountRights(st2);
        s3.addAccountRights(st1);
        staffSessionLocal.createStaff(s3);

        Staff s4 = new Staff("Siti Riduan", "sitiriduan", encryptPassword("password"), "sitiriduan@krhg.com.sg", "93497066", "Female", "S1730049J", "Blk 29 Geylang Street 21, #01-27", new Date(), "Kent Ridge Central", "Housekeeping Staff", "Housekeeping", 7, true, "Riduan Mohd Yaccob", "Blk 29 Geylang Street 21, #01-27", "67603364");
        s4.addAccountRights(st1);
        staffSessionLocal.createStaff(s4);

        Staff s5 = new Staff("Geoffrey Gan", "geoffreygan", encryptPassword("password"), "geoffreygan@krhg.com.sg", "91574480", "Male", "F9117753Q", "Blk 364 Geylang Street 17, #18-06", new Date(), "Kent Ridge North East", "Housekeeping Manager", "Housekeeping", 14, true, "Gan Kim Hock", "Blk 364 Geylang Street 17, #18-06", "61446071");
        s5.addAccountRights(st2);
        s5.addAccountRights(st1);
        staffSessionLocal.createStaff(s5);

        Staff s6 = new Staff("Khor Yuanruo Gene", "genekhor", encryptPassword("password"), "genekhor@krhg.com.sg", "95189616", "Male", "S7665201E", "Blk 34 Tampines Street 74, #11-44", new Date(), "Kent Ridge West", "Laundry Manager", "Laundry", 14, true, "Khor Ru Shan", "Blk 34 Tampines Street 74, #11-44", "66910568");
        s6.addAccountRights(st4);
        s6.addAccountRights(st3);
        staffSessionLocal.createStaff(s6);

        Staff s7 = new Staff("Dakota Chee", "dakotachee", encryptPassword("password"), "dakotachee@krhg.com.sg", "94094757", "Female", "S0297606D", "Blk 38 Lorong 8 Rochor, #01-04", new Date(), "Kent Ridge South East", "Housekeeping Manager", "Housekeeping", 14, true, "Chee Hong Chye", "Blk 38 Lorong 8 Rochor, #01-04", "68669896");
        s7.addAccountRights(st2);
        s7.addAccountRights(st1);
        staffSessionLocal.createStaff(s7);

        Staff s8 = new Staff("Winston Shum", "winstonshum", encryptPassword("password"), "winstonshum@krhg.com.sg", "96531553", "Male", "S8400752H", "6 Dover Green, #02-31", new Date(), "Kent Ridge North", "Laundry Staff", "Laundry", 7, true, "Shum Chee Ping", "6 Dover Green, #02-31", "64907198");
        s8.addAccountRights(st3);
        staffSessionLocal.createStaff(s8);

        Staff s9 = new Staff("Jonathan Loy", "jonathanloy", encryptPassword("password"), "jonathanloy@krhg.com.sg", "96717486", "Male", "S8017091B", "6 Innova Estate", new Date(), "Kent Ridge East", "Kitchen Manager", "Kitchen", 14, true, "Loy Tian Kiew", "6 Innova Estate", "65353918");
        s9.addAccountRights(st6);
        s9.addAccountRights(st5);
        staffSessionLocal.createStaff(s9);

        Staff s10 = new Staff("Anabelle Loh", "anabelleloh", encryptPassword("password"), "anabelleloh@krhg.com.sg", "91526387", "Female", "F7623475L", "57 Jalan Damai", new Date(), "Kent Ridge West", "Housekeeping Staff", "Housekeeping", 7, true, "Loh Siew Kim", "57 Jalan Damai", "67262650");
        s10.addAccountRights(st1);
        staffSessionLocal.createStaff(s10);

        Staff s11 = new Staff("Isabell Teoh", "isabellteoh", encryptPassword("password"), "isabellteoh@krhg.com.sg", "94070476", "Female", "S3085023H", "Blk 382 Toa Payoh Street 27, #16-08", new Date(), "Kent Ridge East", "Housekeeping Staff", "Housekeeping", 7, true, "Teoh Chai Yee", "Blk 382 Toa Payoh Street 27, #16-08", "65648264");
        s11.addAccountRights(st1);
        staffSessionLocal.createStaff(s11);

        Staff s12 = new Staff("Esther Chai", "estherchai", encryptPassword("password"), "estherchai@krhg.com.sg", "87698197", "Female", "S8765702G", "1 Bukit Ho Swee Crescent", new Date(), "Kent Ridge West", "Front Desk Manager", "Front Desk", 14, true, "Chai Sum Ping", "1 Bukit Ho Swee Crescent", "67143224");
        s12.addAccountRights(st10);
        s12.addAccountRights(st9);
        staffSessionLocal.createStaff(s12);

        Staff s13 = new Staff("Eric Ong", "ericong", encryptPassword("password"), "ericong@krhg.com.sg", "92237097", "Male", "F8880046W", "7 Bukit Gombak Park, #15-04", new Date(), "HQ", "Admin Staff", "Admin/HR", 7, true, "Ong Tian Cheng", "7 Bukit Gombak Park, #15-04", "60899072");
        s13.addAccountRights(st14);
        staffSessionLocal.createStaff(s13);

        Staff s14 = new Staff("Tan Qing Yi Fatin", "fatintan", encryptPassword("password"), "fatintan@krhg.com.sg", "96228753", "Female", "S8499523A", "4 Tanjong Pagar Lane, #15-15", new Date(), "Kent Ridge East", "Front Desk Manager", "Front Desk", 14, true, "Tan Kim Heng", "4 Tanjong Pagar Lane, #15-15", "66669098");
        s14.addAccountRights(st10);
        s14.addAccountRights(st9);
        staffSessionLocal.createStaff(s14);

        Staff s15 = new Staff("Julia Khim", "juliakhim", encryptPassword("password"), "juliakhim@krhg.com.sg", "96790401", "Female", "S0465886H", "Blk 13 Bedok Street 70, #06-02", new Date(), "Kent Ridge East", "Kitchen Staff", "Kitchen", 7, true, "Khim Seng Chye", "Blk 13 Bedok Street 70, #06-02", "68716399");
        s15.addAccountRights(st5);
        staffSessionLocal.createStaff(s15);

        Staff s16 = new Staff("Kim Goh", "kimgoh", encryptPassword("password"), "kimgoh@krhg.com.sg", "88646281", "Female", "S8213938I", "Blk 144 Pasir Ris Street 76, #05-12", new Date(), "Kent Ridge Grand", "Housekeeping Manager", "Housekeeping", 14, true, "Goh Kum Swee", "Blk 144 Pasir Ris Street 76, #05-12", "61972198");
        s16.addAccountRights(st2);
        s16.addAccountRights(st1);
        staffSessionLocal.createStaff(s16);

        Staff s17 = new Staff("Hudson Lazaroo", "hudsonlazaroo", encryptPassword("password"), "hudsonlazaroo@krhg.com.sg", "96880485", "Male", "S0478883D", "6 Cairnhill Crescent", new Date(), "HQ", "Finance/ Operations Director", "Senior Management", 21, true, "Lazaroo Adam", "6 Cairnhill Crescent", "62651389");
        s17.addAccountRights(st13);
        s17.addAccountRights(st12);
        staffSessionLocal.createStaff(s17);

        Staff s18 = new Staff("Theo Liew Weide", "theoliewweide", encryptPassword("password"), "theoliewweide@krhg.com.sg", "94343719", "Male", "S7394844D", "4 Serangoon North Center", new Date(), "Kent Ridge North", "Kitchen Manager", "Kitchen", 14, true, "Liew Siew Guan", "4 Serangoon North Center", "68614319");
        s18.addAccountRights(st6);
        s18.addAccountRights(st5);
        staffSessionLocal.createStaff(s18);

        Staff s19 = new Staff("Nettie Loh", "nettieloh", encryptPassword("password"), "nettieloh@krhg.com.sg", "96739136", "Female", "S8223938C", "9 Farrer Walk, #01-32", new Date(), "Kent Ridge Central", "Front Desk Manager", "Front Desk", 14, true, "Loh Tian Hock", "9 Farrer Walk, #01-32", "68907701");
        s19.addAccountRights(st10);
        s19.addAccountRights(st9);
        staffSessionLocal.createStaff(s19);

        Staff s20 = new Staff("Foo Wei Sonny", "sonnyfoo", encryptPassword("password"), "sonnyfoo@krhg.com.sg", "93945308", "Male", "S2846900D", "43 Jalan Ria", new Date(), "Kent Ridge Central", "Maintenance", "Maintenance", 7, true, "Foo Chuan Tao", "43 Jalan Ria", "69597789");
        s20.addAccountRights(st1);
        staffSessionLocal.createStaff(s20);

        Staff s21 = new Staff("Min Chia", "minchia", encryptPassword("password"), "minchia@krhg.com.sg", "96092548", "Female", "S8768810J", "Blk 16 Jurong West Street 23, #18-12", new Date(), "Kent Ridge Grand", "Housekeeping Staff", "Housekeeping", 7, true, "Chia Ping Ye", "Blk 16 Jurong West Street 23, #18-12", "62458977");
        s21.addAccountRights(st1);
        staffSessionLocal.createStaff(s21);

        Staff s22 = new Staff("Zoe Pei", "zoepei", encryptPassword("password"), "zoepei@krhg.com.sg", "93287853", "Female", "S3474916G", "82 Yew Tee Terrace, #02-13", new Date(), "Kent Ridge East", "Maintenance", "Maintenance", 7, true, "Pei Oei Kow", "82 Yew Tee Terrace, #02-13", "63399639");
        s22.addAccountRights(st1);
        staffSessionLocal.createStaff(s22);

        Staff s23 = new Staff("Ron Liew Jinrong", "ronliew", encryptPassword("password"), "ronliew@krhg.com.sg", "98796331", "Male", "S7773316G", "Blk 42 Sengkang Street 28, #05-33", new Date(), "Kent Ridge East", "Laundry Manager", "Laundry", 14, true, "Liew Yao Chue", "Blk 42 Sengkang Street 28, #05-33", "68740597");
        s23.addAccountRights(st4);
        s23.addAccountRights(st3);
        staffSessionLocal.createStaff(s23);

        Staff s24 = new Staff("Adlina Chye Wan Yee", "adlinachye", encryptPassword("password"), "adlinachye@krhg.com.sg", "99337234", "Female", "S7748702F", "Blk 183 Sengkang Street 76, #13-17", new Date(), "Kent Ridge North", "Front Desk Manager", "Front Desk", 14, true, "Chye Siew Hun", "Blk 183 Sengkang Street 76, #13-17", "65276645");
        s24.addAccountRights(st10);
        s24.addAccountRights(st9);
        staffSessionLocal.createStaff(s24);

        Staff s25 = new Staff("Kimberly Soin", "kimberlysoin", encryptPassword("password"), "kimberlysoin@krhg.com.sg", "90910322", "Female", "F7040165R", "41 Bidadari Road", new Date(), "Kent Ridge North East", "Maintenance", "Maintenance", 7, true, "Soin Ang Yue", "41 Bidadari Road", "68111147");
        s25.addAccountRights(st1);
        staffSessionLocal.createStaff(s25);

        Staff s26 = new Staff("Betty Ho", "bettyho", encryptPassword("password"), "bettyho@krhg.com.sg", "86707383", "Female", "S8302585I", "Blk 30 Bukit Panjang Street 70, #16-17", new Date(), "Kent Ridge South", "Laundry Staff", "Laundry", 7, true, "Ho Chin Yup", "Blk 30 Bukit Panjang Street 70, #16-17", "62678519");
        s26.addAccountRights(st3);
        staffSessionLocal.createStaff(s26);

        Staff s27 = new Staff("Stephen Chua", "stephenchua", encryptPassword("password"), "stephenchua@krhg.com.sg", "98687431", "Male", "S7603298Z", "9 Dunearn Lane", new Date(), "HQ", "Sales and Marketing Staff", "Sales and Marketing", 7, true, "Chua Hock Oei", "9 Dunearn Lane", "61991461");
        s27.addAccountRights(st7);
        staffSessionLocal.createStaff(s27);

        Staff s28 = new Staff("Samson Lum", "samsonlum", encryptPassword("password"), "samsonlum@krhg.com.sg", "95985187", "Male", "S9823405E", "Blk 148 Pasir Ris Street 25, #14-33", new Date(), "Kent Ridge Central", "Front Desk Staff", "Front Desk", 7, true, "Lum Siew Hun", "Blk 148 Pasir Ris Street 25, #14-33", "60262406");
        s28.addAccountRights(st9);
        staffSessionLocal.createStaff(s28);

        Staff s29 = new Staff("Dave Kwok Yiew Hsien", "davekwok", encryptPassword("password"), "davekwok@krhg.com.sg", "82523699", "Male", "S7548652I", "4 Jalan Kuang", new Date(), "Kent Ridge Central", "Kitchen Manager", "Kitchen", 14, true, "Kwok Chee Siew", "4 Jalan Kuang", "63818471");
        s29.addAccountRights(st6);
        s29.addAccountRights(st5);
        staffSessionLocal.createStaff(s29);

        Staff s30 = new Staff("Lawrence Goh Weida", "lawrencegoh", encryptPassword("password"), "lawrencegoh@krhg.com.sg", "97112720", "Male", "S1992887Z", "Blk 13 Lorong 7 Chong Boon, #06-08", new Date(), "Kent Ridge Grand", "Maintenance", "Maintenance", 7, true, "Goh Mei Ting", "Blk 13 Lorong 7 Chong Boon, #06-08", "68376309");
        s30.addAccountRights(st1);
        staffSessionLocal.createStaff(s30);

        Staff s31 = new Staff("Joshua Lam", "joshualam", encryptPassword("password"), "joshualam@krhg.com.sg", "97315439", "Male", "S0246547G", "48 Telok Blangah View", new Date(), "Kent Ridge Grand", "Kitchen Staff", "Kitchen", 7, true, "Lam Hue Ting", "48 Telok Blangah View", "66601252");
        s31.addAccountRights(st5);
        staffSessionLocal.createStaff(s31);

        Staff s32 = new Staff("Emma Choo", "emmachoo", encryptPassword("password"), "emmachoo@krhg.com.sg", "97010952", "Female", "S3009421B", "13 Jalan Lye Kwee", new Date(), "Kent Ridge North West", "Maintenance", "Maintenance", 7, true, "Choo Mong Lam", "13 Jalan Lye Kwee", "66816679");
        s32.addAccountRights(st1);
        staffSessionLocal.createStaff(s32);

        Staff s33 = new Staff("Ava Fong", "avafong", encryptPassword("password"), "avafong@krhg.com.sg", "95990306", "Female", "S9556331G", "16 Ghim Moh Park", new Date(), "Kent Ridge North East", "Front Desk Manager", "Front Desk", 14, true, "Fong Siew Fong", "16 Ghim Moh Park", "60822895");
        s33.addAccountRights(st10);
        s33.addAccountRights(st9);
        staffSessionLocal.createStaff(s33);

        Staff s34 = new Staff("Chin Hwee Hoon Phoebe", "phoebechin", encryptPassword("password"), "phoebechin@krhg.com.sg", "95014355", "Female", "S7558969G", "7 Boon Keng View", new Date(), "Kent Ridge North West", "General Manager", "Front Desk", 18, true, "Chin Fok Lam", "7 Boon Keng View", "61316647");
        s34.addAccountRights(st15);
        staffSessionLocal.createStaff(s34);

        Staff s35 = new Staff("Mike Chin", "mikechin", encryptPassword("password"), "mikechin@krhg.com.sg", "99985141", "Male", "S9004555E", "Blk 12 Lorong 5 Chong Boon, #09-18", new Date(), "Kent Ridge North West", "Front Desk Staff", "Front Desk", 7, true, "Chin Beng Chun", "Blk 12 Lorong 5 Chong Boon, #09-18", "68272848");
        s35.addAccountRights(st9);
        staffSessionLocal.createStaff(s35);

        Staff s36 = new Staff("Vanessa Sathyalingam", "vanessasathya", encryptPassword("password"), "vanessasathya@krhg.com.sg", "84122183", "Female", "S4498733C", "47 Chin Bee View, #01-40", new Date(), "Kent Ridge North East", "Laundry Manager", "Laundry", 14, true, "Sathyalingam Shanugam", "47 Chin Bee View, #01-40", "65954146");
        s36.addAccountRights(st4);
        s36.addAccountRights(st3);
        staffSessionLocal.createStaff(s36);

        Staff s37 = new Staff("Renie Yang", "renieyang", encryptPassword("password"), "renieyang@krhg.com.sg", "99227891", "Female", "S7890299Z", "Blk 26 Kallang Street 81, #18-03", new Date(), "Kent Ridge North West", "Kitchen Manager", "Kitchen", 14, true, "Yang Hui Ting", "Blk 26 Kallang Street 81, #18-03", "65996087");
        s37.addAccountRights(st6);
        s37.addAccountRights(st5);
        staffSessionLocal.createStaff(s37);

        Staff s38 = new Staff("Felicia Loy", "felicialoy", encryptPassword("password"), "felicialoy@krhg.com.sg", "95186486", "Female", "S6998351J", "Blk 383 Lorong 1 Mattar, #18-15", new Date(), "Kent Ridge South", "Kitchen Manager", "Kitchen", 14, true, "Loy Num Hee", "Blk 383 Lorong 1 Mattar, #18-15", "69092851");
        s38.addAccountRights(st6);
        s38.addAccountRights(st5);
        staffSessionLocal.createStaff(s38);

        Staff s39 = new Staff("Janice Critchley", "janicecritchley", encryptPassword("password"), "janicecritchley@krhg.com.sg", "83084182", "Female", "S6998114C", "Blk 49 Yishun Street 32, #13-17", new Date(), "Kent Ridge Grand", "General Manager", "Front Desk", 7, true, "Critchley Joseph", "Blk 49 Yishun Street 32, #13-17", "64108229");
        s39.addAccountRights(st15);
        staffSessionLocal.createStaff(s39);

        Staff s40 = new Staff("Low Si Ting", "lowsiting", encryptPassword("password"), "lowsiting@krhg.com.sg", "82237276", "Female", "S7461407H", "Blk 40 Jurong East Street 82, #01-31", new Date(), "Kent Ridge South East", "Front Desk Manager", "Front Desk", 14, true, "Low Li Hung", "Blk 40 Jurong East Street 82, #01-31", "64214229");
        s40.addAccountRights(st10);
        s40.addAccountRights(st9);
        staffSessionLocal.createStaff(s40);

        Staff s41 = new Staff("Irwin Hong Kok Hwee", "irwinhong", encryptPassword("password"), "irwinhong@krhg.com.sg", "94538996", "Male", "S6832133F", "Blk 379 Clementi Street 32, #02-04", new Date(), "Kent Ridge North", "General Manager", "Front Desk", 18, true, "Hong Tong Gek", "Blk 379 Clementi Street 32, #02-04", "61142355");
        s41.addAccountRights(st15);
        staffSessionLocal.createStaff(s41);

        Staff s42 = new Staff("Ling Min Qi Connie", "connieling", encryptPassword("password"), "connieling@krhg.com.sg", "84964685", "Female", "S1834949C", "Blk 486 Ang Mo Kio Street 83, #13-22", new Date(), "Kent Ridge North", "Maintenance", "Maintenance", 7, true, "Ling Siew King", "Blk 486 Ang Mo Kio Street 83, #13-22", "64274870");
        s42.addAccountRights(st1);
        staffSessionLocal.createStaff(s42);

        Staff s43 = new Staff("Joel To Yueh", "joelyueh", encryptPassword("password"), "joelyueh@krhg.com.sg", "99021935", "Male", "S7268516D", "28 Dover Field", new Date(), "Kent Ridge North", "Laundry Manager", "Laundry", 14, true, "To Gek King", "28 Dover Field", "62657357");
        s43.addAccountRights(st4);
        s43.addAccountRights(st3);
        staffSessionLocal.createStaff(s43);

        Staff s44 = new Staff("Eileen Yao", "eileenyao", encryptPassword("password"), "eileenyao@krhg.com.sg", "90355949", "Female", "F3218960K", "Blk 430 Toa Payoh Street 33, #18-16", new Date(), "Kent Ridge North", "Kitchen Staff", "Kitchen", 7, true, "Yao Beh Peo", "Blk 430 Toa Payoh Street 33, #18-16", "65871688");
        s44.addAccountRights(st5);
        staffSessionLocal.createStaff(s44);

        Staff s45 = new Staff("Devi Yao", "deviyao", encryptPassword("password"), "deviyao@krhg.com.sg", "93790021", "Female", "S2063617C", "Blk 30 Aljunied Street 73, #07-17", new Date(), "Kent Ridge West", "Housekeeping Manager", "Housekeeping", 14, true, "Yao Cheng Ji", "Blk 30 Aljunied Street 73, #07-17", "60930788");
        s45.addAccountRights(st2);
        s45.addAccountRights(st1);
        staffSessionLocal.createStaff(s45);

        Staff s46 = new Staff("Ooi Wei Quan Chris", "chrisooi", encryptPassword("password"), "chrisooi@krhg.com.sg", "90301549", "Male", "F0402355L", "Blk 29 Geylang Street 80, #09-15", new Date(), "Kent Ridge Grand", "Kitchen Staff", "Kitchen", 7, true, "Ooi Beng Chin", "Blk 29 Geylang Street 80, #09-15", "62736175");
        s46.addAccountRights(st5);
        staffSessionLocal.createStaff(s46);

        Staff s47 = new Staff("Siti Mohamed", "sitimohamed", encryptPassword("password"), "sitimohamed@krhg.com.sg", "93224894", "Female", "S3776248B", "77 Chong Pang Avenue North", new Date(), "Kent Ridge South East", "General Manager", "Front Desk", 18, true, "Mohamed Rahid", "77 Chong Pang Avenue North", "62491275");
        s47.addAccountRights(st15);
        staffSessionLocal.createStaff(s47);

        Staff s48 = new Staff("Gabriel Kumar", "gabrielkumar", encryptPassword("password"), "gabrielkumar@krhg.com.sg", "94466436", "Male", "S7093840E", "5 Pioneer Heights", new Date(), "Kent Ridge South West", "Kitchen Manager", "Kitchen", 14, true, "Kumar Joseph", "5 Pioneer Heights", "69160906");
        s48.addAccountRights(st6);
        s48.addAccountRights(st5);
        staffSessionLocal.createStaff(s48);

        Staff s49 = new Staff("Stephen Keller", "stephenkeller", encryptPassword("password"), "stephenkeller@krhg.com.sg", "80099266", "Male", "S7438436F", "Blk 17 Tampines Street 14, #09-10", new Date(), "Kent Ridge South West", "Front Desk Manager", "Front Desk", 14, true, "Keller Henry", "Blk 17 Tampines Street 14, #09-10", "60459076");
        s49.addAccountRights(st10);
        s49.addAccountRights(st9);
        staffSessionLocal.createStaff(s49);

        Staff s50 = new Staff("Elisa Lim", "elisalim", encryptPassword("password"), "elisalim@krhg.com.sg", "86218501", "Female", "S0407098D", "52 Jalan Jambo Ayer", new Date(), "HQ", "Managing Director", "Senior Management", 21, true, "Lim Chuan Hoo", "52 Jalan Jambo Ayer", "62282110");
        s50.addAccountRights(st15);
        staffSessionLocal.createStaff(s50);

        Staff s51 = new Staff("Harrison Lopez", "harrisonlopez", encryptPassword("password"), "harrisonlopez@krhg.com.sg", "90979239", "Male", "F7710098U", "8 Sungei Gedong Garden", new Date(), "Kent Ridge South", "Maintenance", "Maintenance", 7, true, "Lopez Maria", "8 Sungei Gedong Garden", "66200708");
        s51.addAccountRights(st1);
        staffSessionLocal.createStaff(s51);

        Staff s52 = new Staff("Adrian To", "adrianto", encryptPassword("password"), "adrianto@krhg.com.sg", "94828126", "Male", "S7870185D", "Blk 46 Toa Payoh Street 32, #18-13", new Date(), "Kent Ridge North West", "Laundry Manager", "Laundry", 14, true, "To Seep Koo", "Blk 46 Toa Payoh Street 32, #18-13", "67740236");
        s52.addAccountRights(st4);
        s52.addAccountRights(st3);
        staffSessionLocal.createStaff(s52);

        Staff s53 = new Staff("Kyle Neo", "kyleneo", encryptPassword("password"), "kyleneo@krhg.com.sg", "88892891", "Male", "S9713064G", "5 Admiralty Terrace, #09-39", new Date(), "Kent Ridge West", "Front Desk Staff", "Front Desk", 7, true, "Neo Beng Young", "5 Admiralty Terrace, #09-39", "64285195");
        s53.addAccountRights(st9);
        staffSessionLocal.createStaff(s53);

        Staff s54 = new Staff("Gladys Ee", "gladysee", encryptPassword("password"), "gladysee@krhg.com.sg", "89038687", "Female", "F1447887Q", "7 Bugis Walk", new Date(), "Kent Ridge Central", "Kitchen Staff", "Kitchen", 7, true, "Ee Yung Hun", "7 Bugis Walk", "62498487");
        s54.addAccountRights(st5);
        staffSessionLocal.createStaff(s54);

        Staff s55 = new Staff("Fong Su Wei", "fongsuwei", encryptPassword("password"), "fongsuwei@krhg.com.sg", "86278601", "Female", "S4540945G", "3 Pasir Ris View", new Date(), "Kent Ridge South West", "Laundry Manager", "Laundry", 14, true, "Fong Mooi Chi", "3 Pasir Ris View", "60984582");
        s55.addAccountRights(st4);
        s55.addAccountRights(st3);
        staffSessionLocal.createStaff(s55);

        Staff s56 = new Staff("Preeti Khor", "preetikhor", encryptPassword("password"), "preetikhor@krhg.com.sg", "99525185", "Female", "S8957735G", "9 Pioneer Heights", new Date(), "Kent Ridge South", "Front Desk Staff", "Front Desk", 7, true, "Khor Chee Poh", "9 Pioneer Heights", "63459295");
        s56.addAccountRights(st9);
        staffSessionLocal.createStaff(s56);

        Staff s57 = new Staff("Kim Kok Bok Ai", "kimkok", encryptPassword("password"), "kimkok@krhg.com.sg", "80942114", "Female", "S7445023G", "Blk 16 Lorong 6 MacPherson, #10-38", new Date(), "Kent Ridge South", "Front Desk Manager", "Front Desk", 14, true, "Kok Peh Cheah", "Blk 16 Lorong 6 MacPherson, #10-38", "69276817");
        s57.addAccountRights(st10);
        s57.addAccountRights(st9);
        staffSessionLocal.createStaff(s57);

        Staff s58 = new Staff("Choy Beng Mooi", "choybengmooi", encryptPassword("password"), "choybengmooi@krhg.com.sg", "90509346", "Female", "F3435864T", "Blk 14 Ang Mo Kio Street 87, #05-45", new Date(), "HQ", "Admin Manager", "Admin/HR", 14, true, "Choy Wee Tat", "Blk 14 Ang Mo Kio Street 87, #05-45", "60260999");
        s58.addAccountRights(st14);
        staffSessionLocal.createStaff(s58);

        Staff s59 = new Staff("Amy Chee", "amychee", encryptPassword("password"), "amychee@krhg.com.sg", "87116256", "Female", "S8868188F", "73 Buona Vista Point", new Date(), "Kent Ridge North", "Front Desk Staff", "Front Desk", 7, true, "Chee Chien Boo", "73 Buona Vista Point", "69194607");
        s59.addAccountRights(st9);
        staffSessionLocal.createStaff(s59);

        Staff s60 = new Staff("Maria Poh", "mariapoh", encryptPassword("password"), "mariapoh@krhg.com.sg", "96564109", "Female", "S9708115H", "Blk 44 Lorong 6 Serangoon Gardens, #09-25", new Date(), "Kent Ridge East", "Laundry Staff", "Laundry", 7, true, "Poh Tuan Siew", "Blk 44 Lorong 6 Serangoon Gardens, #09-25", "68165135");
        s60.addAccountRights(st3);
        staffSessionLocal.createStaff(s60);

        Staff s61 = new Staff("Charles Boo", "charlesboo", encryptPassword("password"), "charlesboo@krhg.com.sg", "97940015", "Male", "S2433605J", "5 Tai Seng Green, #02-40", new Date(), "Kent Ridge South", "Laundry Manager", "Laundry", 14, true, "Boo Lieu Ling", "5 Tai Seng Green, #02-40", "61282380");
        s61.addAccountRights(st4);
        s61.addAccountRights(st3);
        staffSessionLocal.createStaff(s61);

        Staff s62 = new Staff("Zhang Chi Chien", "zhangchichien", encryptPassword("password"), "zhangchichien@krhg.com.sg", "99597384", "Female", "S8421189C", "Blk 435 Geylang Street 87, #10-19", new Date(), "Kent Ridge West", "Kitchen Manager", "Kitchen", 14, true, "Zhang Cheng Yue", "Blk 435 Geylang Street 87, #10-19", "66068027");
        s62.addAccountRights(st6);
        s62.addAccountRights(st5);
        staffSessionLocal.createStaff(s62);

        Staff s63 = new Staff("Ahmad Mohamad", "ahmadmohamad", encryptPassword("password"), "ahmadmohamad@krhg.com.sg", "93489085", "Male", "S0471497J", "Blk 31 Geylang East Street 82, #11-20", new Date(), "Kent Ridge Grand", "Laundry Staff", "Laundry", 7, true, "Mohamad Khalid", "Blk 31 Geylang East Street 82, #11-20", "69096201");
        s63.addAccountRights(st3);
        staffSessionLocal.createStaff(s63);

        Staff s64 = new Staff("Caleb Peh", "calebpeh", encryptPassword("password"), "calebpeh@krhg.com.sg", "91293623", "Male", "F6965199L", "Blk 41 Aljunied Street 13, #10-08", new Date(), "Kent Ridge South", "Housekeeping Staff", "Housekeeping", 7, true, "Peh Chuan Nam", "Blk 41 Aljunied Street 13, #10-08", "63717937");
        s64.addAccountRights(st1);
        staffSessionLocal.createStaff(s64);

        Staff s65 = new Staff("Victor Khor Wee Tat", "victorkhor", encryptPassword("password"), "victorkhor@krhg.com.sg", "99183262", "Male", "S7779810B", "86 Pasir Panjang Terrace", new Date(), "HQ", "Finance Staff", "Finance", 7, true, "Khor Leow Sun", "86 Pasir Panjang Terrace", "62833234");
        s65.addAccountRights(st12);
        staffSessionLocal.createStaff(s65);

        Staff s66 = new Staff("Adrian Cheah", "adriancheah", encryptPassword("password"), "adriancheah@krhg.com.sg", "80716031", "Male", "S7880506D", "Blk 143 Jurong East Terrace", new Date(), "Kent Ridge South West", "Front Desk Staff", "Front Desk", 7, true, "Cheah Hui Ling", "Blk 143 Jurong East Terrace", "60068470");
        s66.addAccountRights(st9);
        staffSessionLocal.createStaff(s66);

        Staff s67 = new Staff("Elwin Ling", "elwinling", encryptPassword("password"), "elwinling@krhg.com.sg", "90800799", "Male", "F3374906Q", "1 Boon Lay View, #11-42", new Date(), "Kent Ridge West", "Kitchen Staff", "Kitchen", 7, true, "Ling Soon Kit", "1 Boon Lay View, #11-42", "63364630");
        s67.addAccountRights(st5);
        staffSessionLocal.createStaff(s67);

        Staff s68 = new Staff("Walter Lieu", "walterlieu", encryptPassword("password"), "walterlieu@krhg.com.sg", "84778283", "Male", "S3010260F", "Blk 230 Hougang Street 70, #05-22", new Date(), "Kent Ridge South West", "General Manager", "Front Desk", 18, true, "Lieu Toong Hut", "Blk 230 Hougang Street 70, #05-22", "69534452");
        s68.addAccountRights(st15);
        staffSessionLocal.createStaff(s68);

        Staff s69 = new Staff("Nia Lieu", "nialieu", encryptPassword("password"), "nialieu@krhg.com.sg", "99091599", "Female", "S7531244Z", "Blk 47 Serangoon North Street 19, #16-15", new Date(), "Kent Ridge Central", "Laundry Manager", "Laundry", 14, true, "Lieu Hung Cheng", "Blk 47 Serangoon North Street 19, #16-15", "60770079");
        s69.addAccountRights(st4);
        s69.addAccountRights(st3);
        staffSessionLocal.createStaff(s69);

        Staff s70 = new Staff("Dave Boo", "daveboo", encryptPassword("password"), "daveboo@krhg.com.sg", "85461304", "Male", "S2316051Z", "84 Woodlands Hill, #15-20", new Date(), "Kent Ridge North", "Housekeeping Manager", "Housekeeping", 14, true, "Boo Yu Poh", "84 Woodlands Hill, #15-20", "65798399");
        s70.addAccountRights(st2);
        s70.addAccountRights(st1);
        staffSessionLocal.createStaff(s70);

        Staff s71 = new Staff("Garry Scully", "garryscully", encryptPassword("password"), "garryscully@krhg.com.sg", "99946025", "Male", "S9250025Z", "2 Jurong East Walk, #06-28", new Date(), "Kent Ridge Grand", "Front Desk Staff", "Front Desk", 7, true, "Scully Freddy", "2 Jurong East Walk, #06-28", "67793245");
        s71.addAccountRights(st9);
        staffSessionLocal.createStaff(s71);

        Staff s72 = new Staff("Farida Kadir", "faridakadir", encryptPassword("password"), "faridakadir@krhg.com.sg", "98728893", "Female", "S7400526H", "Blk 425 Geylang Street 14, #02-27", new Date(), "Kent Ridge South East", "Kitchen Manager", "Kitchen", 14, true, "Kadir Yaakob", "Blk 425 Geylang Street 14, #02-27", "67834848");
        s72.addAccountRights(st6);
        s72.addAccountRights(st5);
        staffSessionLocal.createStaff(s72);

        Staff s73 = new Staff("Khalil Ismail", "khalilismail", encryptPassword("password"), "khalilismail@krhg.com.sg", "84919099", "Male", "S2302951J", "9 Jalan Lim Tai See", new Date(), "Kent Ridge South", "General Manager", "Front Desk", 18, true, "Ismail Danial", "9 Jalan Lim Tai See", "68202843");
        s73.addAccountRights(st15);
        staffSessionLocal.createStaff(s73);

        Staff s74 = new Staff("Haziq Mohamed", "haziqmohamed", encryptPassword("password"), "haziqmohamed@krhg.com.sg", "92448615", "Male", "F9226149L", "4 Jalan Ayer", new Date(), "Kent Ridge South", "Housekeeping Manager", "Housekeeping", 14, true, "Mohamed Jihan", "4 Jalan Ayer", "69960072");
        s74.addAccountRights(st2);
        s74.addAccountRights(st1);
        staffSessionLocal.createStaff(s74);

        Staff s75 = new Staff("Gemmie Yu", "gemmieyu", encryptPassword("password"), "gemmieyu@krhg.com.sg", "96387568", "Female", "S9350425I", "Blk 375 Serangoon Gardens Street 31, #10-12", new Date(), "Kent Ridge Grand", "Front Desk Staff", "Front Desk", 7, true, "Yu Chi Loh", "Blk 375 Serangoon Gardens Street 31, #10-12", "61657615");
        s75.addAccountRights(st9);
        staffSessionLocal.createStaff(s75);

        Staff s76 = new Staff("Ayden Leow", "aydenleow", encryptPassword("password"), "aydenleow@krhg.com.sg", "96525791", "Male", "S9815008J", "6 Jalan Seruling", new Date(), "Kent Ridge North East", "Front Desk Staff", "Front Desk", 7, true, "Leow Leong Lee", "6 Jalan Seruling", "66606837");
        s76.addAccountRights(st9);
        staffSessionLocal.createStaff(s76);

        Staff s77 = new Staff("Benjamin Choi", "benjaminchoi", encryptPassword("password"), "benjaminchoi@krhg.com.sg", "81887812", "Male", "S6921042B", "Blk 14 Bedok Street 82, #09-43", new Date(), "Kent Ridge Central", "General Manager", "Front Desk", 18, true, "Choi Steven", "Blk 14 Bedok Street 82, #09-43", "62297369");
        s77.addAccountRights(st15);
        staffSessionLocal.createStaff(s77);

        Staff s78 = new Staff("Aisha Tan", "aishatan", encryptPassword("password"), "aishatan@krhg.com.sg", "99527233", "Female", "S8952070C", "55 Jalan Tenteram", new Date(), "Kent Ridge Grand", "Laundry Staff", "Laundry", 7, true, "Tan Ah Kow", "55 Jalan Tenteram", "67823754");
        s78.addAccountRights(st3);
        staffSessionLocal.createStaff(s78);

        Staff s79 = new Staff("Luke Cheng", "lukecheng", encryptPassword("password"), "lukecheng@krhg.com.sg", "88764415", "Male", "S8097994J", "Blk 19 Marine Parade Street 77, #02-04", new Date(), "Kent Ridge North West", "Front Desk Manager", "Front Desk", 14, true, "Cheng Low Hung", "Blk 19 Marine Parade Street 77, #02-04", "60245816");
        s79.addAccountRights(st10);
        s79.addAccountRights(st9);
        staffSessionLocal.createStaff(s79);

        Staff s80 = new Staff("Marcia Loh", "marcialoh", encryptPassword("password"), "marcialoh@krhg.com.sg", "91720323", "Female", "F9478179P", "Blk 30 Lorong 2 Seletar, #12-38", new Date(), "Kent Ridge South East", "Maintenance", "Maintenance", 7, true, "Loh Tan Low", "Blk 30 Lorong 2 Seletar, #12-38", "64228680");
        s80.addAccountRights(st1);
        staffSessionLocal.createStaff(s80);

        Staff s81 = new Staff("Ari Salim", "arisalim", encryptPassword("password"), "arisalim@krhg.com.sg", "97022200", "Male", "S2765749D", "9 Watten Center, #02-25", new Date(), "Kent Ridge South West", "Housekeeping Manager", "Housekeeping", 14, true, "Salim Adam", "9 Watten Center, #02-25", "63860258");
        s81.addAccountRights(st2);
        s81.addAccountRights(st1);
        staffSessionLocal.createStaff(s81);

        Staff s82 = new Staff("Frederique Leong", "frederiqueleong", encryptPassword("password"), "frederiqueleong@krhg.com.sg", "81452127", "Female", "S7312804H", "Blk 359 Aljunied Street 39, #16-09", new Date(), "Kent Ridge South East", "Laundry Manager", "Laundry", 14, true, "Leong Yew Fatt", "Blk 359 Aljunied Street 39, #16-09", "63563648");
        s82.addAccountRights(st4);
        s82.addAccountRights(st3);
        staffSessionLocal.createStaff(s82);

        Staff s83 = new Staff("Samson Lin", "samsonlin", encryptPassword("password"), "samsonlin@krhg.com.sg", "93263527", "Male", "S3488482Z", "Blk 11 Marine Parade Street 14, #18-22", new Date(), "Kent Ridge Grand", "Housekeeping Staff", "Housekeeping", 7, true, "Lin Yue Chan", "Blk 11 Marine Parade Street 14, #18-22", "67928717");
        s83.addAccountRights(st1);
        staffSessionLocal.createStaff(s83);

        Staff s84 = new Staff("Roman Oh Wei-Lun", "romanoh", encryptPassword("password"), "romanoh@krhg.com.sg", "87723481", "Male", "S8226907Z", "Blk 279 Lorong 7 Chin Bee, #08-19", new Date(), "Kent Ridge Central", "Laundry Staff", "Laundry", 7, true, "Oh Lih Bin", "Blk 279 Lorong 7 Chin Bee, #08-19", "62306331");
        s84.addAccountRights(st3);
        staffSessionLocal.createStaff(s84);

        Staff s85 = new Staff("Amar Salleh", "amarsalleh", encryptPassword("password"), "amarsalleh@krhg.com.sg", "83530914", "Male", "S7329826A", "5 Bukit Gombak Point, #15-41", new Date(), "HQ", "Sales and Marketing Manager", "Sales and Marketing", 14, true, "Salleh Farizan", "5 Bukit Gombak Point, #15-41", "66604735");
        s85.addAccountRights(st8);
        s85.addAccountRights(st7);
        staffSessionLocal.createStaff(s85);

        Staff s86 = new Staff("Tong Teng Kiat Irving", "irvingtong", encryptPassword("password"), "irvingtong@krhg.com.sg", "86293792", "Male", "S9260331H", "Blk 12 Boon Lay Street 72, #12-12", new Date(), "Kent Ridge South East", "Front Desk Staff", "Front Desk", 7, true, "Tong Long Kung", "Blk 12 Boon Lay Street 72, #12-12", "69235006");
        s86.addAccountRights(st9);
        staffSessionLocal.createStaff(s86);

        Staff s87 = new Staff("Geoffrey Tan", "geoffreytan", encryptPassword("password"), "geoffreytan@krhg.com.sg", "91077218", "Male", "F6947231K", "3 Chong Pang Drive, #08-38", new Date(), "Kent Ridge South", "Kitchen Staff", "Kitchen", 7, true, "Tan Geok Lin", "3 Chong Pang Drive, #08-38", "68583265");
        s87.addAccountRights(st5);
        staffSessionLocal.createStaff(s87);

        Staff s88 = new Staff("Gemmie Ramaswamy", "gemmierama", encryptPassword("password"), "gemmierama@krhg.com.sg", "83716773", "Female", "S3711992Z", "Blk 45 Aljunied Street 71, #10-42", new Date(), "Kent Ridge East", "Housekeeping Manager", "Housekeeping", 14, true, "Ramaswamy Stephen", "Blk 45 Aljunied Street 71, #10-42", "67111197");
        s88.addAccountRights(st2);
        s88.addAccountRights(st1);
        staffSessionLocal.createStaff(s88);

        Staff s89 = new Staff("Jaclyn Ong", "jaclynong", encryptPassword("password"), "jaclynong@krhg.com.sg", "85919244", "Female", "S1924229C", "Blk 246 Hougang Street 34, #17-10", new Date(), "Kent Ridge South West", "Maintenance", "Maintenance", 7, true, "Ong Loh Whye", "Blk 246 Hougang Street 34, #17-10", "64449483");
        s89.addAccountRights(st1);
        staffSessionLocal.createStaff(s89);

        Staff s90 = new Staff("Ibrahim Wahid", "ibrahimwahid", encryptPassword("password"), "ibrahimwahid@krhg.com.sg", "83835972", "Male", "S1920500B", "Blk 33 Bukit Panjang Street 16, #03-18", new Date(), "Kent Ridge West", "Maintenance", "Maintenance", 7, true, "Wahid Mizra", "Blk 33 Bukit Panjang Street 16, #03-18", "63574402");
        s90.addAccountRights(st1);
        staffSessionLocal.createStaff(s90);

        Staff s91 = new Staff("Nigel Fong", "nigelfong", encryptPassword("password"), "nigelfong@krhg.com.sg", "97490268", "Male", "S2846520C", "6 Woodlands Point, #01-06", new Date(), "Kent Ridge North East", "General Manager", "Front Desk", 18, true, "Fong Lin Ting", "6 Woodlands Point, #01-06", "66647150");
        s91.addAccountRights(st15);
        staffSessionLocal.createStaff(s91);

        Staff s92 = new Staff("Rena Lim", "renalim", encryptPassword("password"), "renalim@krhg.com.sg", "99032688", "Female", "S7100209H", "Blk 19 Serangoon Gardens Street 21, #16-23", new Date(), "Kent Ridge North East", "Kitchen Manager", "Kitchen", 14, true, "Lim Thing Boong", "Blk 19 Serangoon Gardens Street 21, #16-23", "61412476");
        s92.addAccountRights(st6);
        s92.addAccountRights(st5);
        staffSessionLocal.createStaff(s92);

        Staff s93 = new Staff("Hiram Mohamad", "hirammohamad", encryptPassword("password"), "hirammohamad@krhg.com.sg", "95844103", "Male", "S7153893A", "44 Jalan Sampurna", new Date(), "Kent Ridge Grand", "Maintenance Manager", "Maintenance", 14, true, "Mohamad Shah", "44 Jalan Sampurna", "66169332");
        s93.addAccountRights(st2);
        s93.addAccountRights(st1);
        staffSessionLocal.createStaff(s93);

        Staff s94 = new Staff("Larissa Low", "larissalow", encryptPassword("password"), "larissalow@krhg.com.sg", "80316248", "Female", "S6927334C", "49 Geylang East Heights", new Date(), "Kent Ridge Grand", "Laundry Manager", "Laundry", 14, true, "Low Si Hung", "49 Geylang East Heights", "60679559");
        s94.addAccountRights(st4);
        s94.addAccountRights(st3);
        staffSessionLocal.createStaff(s94);

        Staff s95 = new Staff("Adrienne Chye Chern Nee", "adriennechye", encryptPassword("password"), "adriennechye@krhg.com.sg", "94228920", "Female", "S7123873C", "6 Jalan Kemboja", new Date(), "Kent Ridge North West", "Housekeeping Manager", "Housekeeping", 14, true, "Chye Kim Chew", "6 Jalan Kemboja", "66454464");
        s95.addAccountRights(st2);
        s95.addAccountRights(st1);
        staffSessionLocal.createStaff(s95);

        Staff s96 = new Staff("Lynn Tan", "lynntan", encryptPassword("password"), "lynntan@krhg.com.sg", "87401940", "Female", "S9849384J", "Blk 127 Buona Vista Field, #09-23", new Date(), "Kent Ridge East", "Front Desk Staff", "Front Desk", 7, true, "Tan Bock Chye", "Blk 127 Buona Vista Field, #09-23", "67718013");
        s96.addAccountRights(st9);
        staffSessionLocal.createStaff(s96);

        Staff s97 = new Staff("Sydnie Gafoor", "sydniegafoor", encryptPassword("password"), "sydniegafoor@krhg.com.sg", "94450056", "Female", "S7800121F", "Blk 31 Sengkang Street 21, #17-41", new Date(), "Kent Ridge Grand", "Front Desk Manager", "Front Desk", 14, true, "Gafoor Joseph", "Blk 31 Sengkang Street 21, #17-41", "67835078");
        s97.addAccountRights(st10);
        s97.addAccountRights(st9);
        staffSessionLocal.createStaff(s97);

        Staff s98 = new Staff("Stanton Lee", "stantonlee", encryptPassword("password"), "stantonlee@krhg.com.sg", "98574752", "Male", "S2969652G", "37 Jalan Lembah Bedok", new Date(), "Kent Ridge West", "General Manager", "Front Desk", 18, true, "Lee Chin Loy", "37 Jalan Lembah Bedok", "65266377");
        s98.addAccountRights(st15);
        staffSessionLocal.createStaff(s98);

        Staff s99 = new Staff("Jackson Zhen", "jacksonzhen", encryptPassword("password"), "jacksonzhen@krhg.com.sg", "97238443", "Male", "S3987612D", "Blk 201 Lorong 3 Hougang, #10-11", new Date(), "Kent Ridge East", "General Manager", "Front Desk", 18, true, "Zhen Shao Liang", "Blk 201 Lorong 3 Hougang, #10-11", "66964374");
        s99.addAccountRights(st15);
        staffSessionLocal.createStaff(s99);

        Staff s100 = new Staff("Tiffany Teo", "tiffanyteo", encryptPassword("password"), "tiffanyteo@krhg.com.sg", "91832153", "Female", "F8656804R", "45 Serangoon Gardens Park, #18-18", new Date(), "Kent Ridge North", "Housekeeping Staff", "Housekeeping", 7, true, "Teo Mei Zhen", "45 Serangoon Gardens Park, #18-18", "64022273");
        s100.addAccountRights(st1);
        staffSessionLocal.createStaff(s100);

        Staff s101 = new Staff("Jonas Chye", "jonasc144", encryptPassword("password"), "jonaschye@krhg.com.sg", "86674988", "Male", "S2301378E", "Blk 48 Lorong 7 Boon Keng, #14-01", new Date(), "HQ", "IT Staff", "IT", 7, true, "Chye Hun Soon", "Blk 48 Lorong 7 Boon Keng, #14-01", "68997170");
        s101.addAccountRights(st11);
        staffSessionLocal.createStaff(s101);

        Staff s102 = new Staff("Victor Yong", "victoryon", encryptPassword("password"), "victoryong@krhg.com.sg", "98334463", "Male", "S7617697D", "1 Boon Keng Garden", new Date(), "HQ", "IT Manager", "IT", 14, true, "Meryvyn Yong", "1 Boon Keng Garden", "69768091");
        s102.addAccountRights(st8);
        staffSessionLocal.createStaff(s102);

        Staff s103 = new Staff("Sammy Sim Jinrong", "sammysi306", encryptPassword("password"), "sammysim@krhg.com.sg", "97581486", "Male", "S8054421F", "17 Tampines Lane", new Date(), "HQ", "IT Staff", "IT", 7, true, "Sim Iew Leak", "17 Tampines Lane", "61617202");
        s103.addAccountRights(st11);
        staffSessionLocal.createStaff(s103);

//*********************************************HOTEL FACILIY************************************************
        HotelFacility hf1 = new HotelFacility("Restaurant", "Treat yourself to a tempting array of local and international culinary delights at KRHG Restaurant.", "restaurant.png"); //restaurant.png
        hotelFacilitySessionLocal.createHotelFacility(hf1);
        HotelFacility restaurant = hotelFacilitySessionLocal.getAllHotelFacilities().get(0);

        HotelFacility hf2 = new HotelFacility("Gym", "Discover the fitness centre located in our hotels which features the latest gym equipment", "gym.png");//gym.png
        hotelFacilitySessionLocal.createHotelFacility(hf2);
        HotelFacility gym = hotelFacilitySessionLocal.getAllHotelFacilities().get(1);

        HotelFacility hf3 = new HotelFacility("Spa & Massage", "Treat yourself to a relaxing experience at our hotel spa.", "spa.png");//spa.png
        hotelFacilitySessionLocal.createHotelFacility(hf3);
        HotelFacility spa = hotelFacilitySessionLocal.getAllHotelFacilities().get(2);

        HotelFacility hf4 = new HotelFacility("Swimming Pool", "An outdoor swimming pool and a children’s pool are available at our hotel.", "swimmingPool.png");//swimmingPool.png
        hotelFacilitySessionLocal.createHotelFacility(hf4);
        HotelFacility swimming = hotelFacilitySessionLocal.getAllHotelFacilities().get(3);

        HotelFacility hf5 = new HotelFacility("Recreational Room", "Relax and have fun in our recreational room which consists of 8-ball Pool Table, Arcade Machines, Gaming console and more!", "recreationalRoom.png");//recreationalRoom.png
        hotelFacilitySessionLocal.createHotelFacility(hf5);
        HotelFacility recreational = hotelFacilitySessionLocal.getAllHotelFacilities().get(4);

        HotelFacility hf6 = new HotelFacility("Function Room", "Host successful events at our meeting rooms in KRHG.", "functionRoom.png");//functionRoom.png
        hotelFacilitySessionLocal.createHotelFacility(hf6);
        HotelFacility functionroom = hotelFacilitySessionLocal.getAllHotelFacilities().get(5);

        HotelFacility hf7 = new HotelFacility("Concierge  Service", "We offer luxus concierge services that will make your stay at our luxury hotel in KRHG unforgettable", "concierge.png");//concierge.png
        hotelFacilitySessionLocal.createHotelFacility(hf7);
        HotelFacility concierge = hotelFacilitySessionLocal.getAllHotelFacilities().get(6);

        HotelFacility hf8 = new HotelFacility("Medical Facility", "We have 24/7 medical centre with professional doctors and nurse to assist you your medical needs.", "medical.png");//medical.png
        hotelFacilitySessionLocal.createHotelFacility(hf8);
        HotelFacility medical = hotelFacilitySessionLocal.getAllHotelFacilities().get(7);

        HotelFacility hf9 = new HotelFacility("Gift Shops", "Every souvenir from our Gift Shop holds a story of your unique experiences enjoyed here with us.", "giftShop.png");//giftShop.png
        hotelFacilitySessionLocal.createHotelFacility(hf9);
        HotelFacility gift = hotelFacilitySessionLocal.getAllHotelFacilities().get(8);

        HotelFacility hf10 = new HotelFacility("Convenience Store", "Selling basic items such as eggs, milk and bread, to today’s one-stop-shop convenient solution, offering a wide variety of products, fresh food, and services.", "convenienceStore.png"); //convenienceStore.png
        hotelFacilitySessionLocal.createHotelFacility(hf10);
        HotelFacility convenience = hotelFacilitySessionLocal.getAllHotelFacilities().get(9);

        HotelFacility hf11 = new HotelFacility("Valet Parking", "We have valets to help you park your vehicle in our complimentary carpark exclusively for guests!", "valet.png");//valet.png
        hotelFacilitySessionLocal.createHotelFacility(hf11);
        HotelFacility valet = hotelFacilitySessionLocal.getAllHotelFacilities().get(10);

        HotelFacility hf12 = new HotelFacility("Bar and Lounge", "KRHG Bar is a good place to meet your friends, take a pit-stop between excursions, or just relax in the lounge", "bar.png");//bar.png
        hotelFacilitySessionLocal.createHotelFacility(hf12);
        HotelFacility bar = hotelFacilitySessionLocal.getAllHotelFacilities().get(11);

        HotelFacility hf13 = new HotelFacility("Baby-Friendly Playground", "We have an inddor playground which is toddler-friendly to let your child run wild and have fun!", "playground.png");//playground.png
        hotelFacilitySessionLocal.createHotelFacility(hf13);
        HotelFacility playground = hotelFacilitySessionLocal.getAllHotelFacilities().get(12);

        HotelFacility hf14 = new HotelFacility("Casino", "The KRHG casino offers a choice of table games to suit all players. There are more than 20 games played at the gaming tables which are available.", "casino.png");//casino.png
        hotelFacilitySessionLocal.createHotelFacility(hf14);
        HotelFacility casino = hotelFacilitySessionLocal.getAllHotelFacilities().get(13);

//*********************************************HOTEL************************************************
        Hotel h1 = new Hotel("Kent Ridge Grand", "KRG", "63 Somerset Rd Singapore 238163", 5, "6123 1000", "hotels_10.jpg");
        h1.addHotelFacility(restaurant);
        h1.addHotelFacility(gym);
        h1.addHotelFacility(spa);
        h1.addHotelFacility(swimming);
        h1.addHotelFacility(recreational);
        h1.addHotelFacility(functionroom);
        h1.addHotelFacility(concierge);
        h1.addHotelFacility(medical);
        h1.addHotelFacility(gift);
        h1.addHotelFacility(convenience);
        h1.addHotelFacility(valet);
        h1.addHotelFacility(bar);
        h1.addHotelFacility(playground);
        h1.addHotelFacility(casino);
        hotelSessionLocal.createHotel(h1);
        h1 = hotelSessionLocal.getHotelByName("Kent Ridge Grand");

        Hotel h2 = new Hotel("Kent Ridge Central", "KRC", "33 Ang Mo Kio Ave 3 Singapore 569933", 4, "6123 1100", "hotels_9.jpg");
        h2.addHotelFacility(restaurant);
        h2.addHotelFacility(gym);
        h2.addHotelFacility(spa);
        h2.addHotelFacility(swimming);
        h2.addHotelFacility(recreational);
        h2.addHotelFacility(functionroom);
        h2.addHotelFacility(concierge);
        h2.addHotelFacility(medical);
        h2.addHotelFacility(gift);
        h2.addHotelFacility(convenience);
        h2.addHotelFacility(valet);
        h2.addHotelFacility(bar);
        hotelSessionLocal.createHotel(h2);
        h2 = hotelSessionLocal.getHotelByName("Kent Ridge Central");

        Hotel h3 = new Hotel("Kent Ridge North", "KRN", "930 Yishun Ave 2 Singapore 769098", 4, "6123 1200", "hotels_1.jpg");
        h3.addHotelFacility(restaurant);
        h3.addHotelFacility(gym);
        h3.addHotelFacility(spa);
        h3.addHotelFacility(swimming);
        h3.addHotelFacility(recreational);
        h3.addHotelFacility(functionroom);
        h3.addHotelFacility(concierge);
        h3.addHotelFacility(medical);
        hotelSessionLocal.createHotel(h3);
        h3 = hotelSessionLocal.getHotelByName("Kent Ridge North");

        Hotel h4 = new Hotel("Kent Ridge South", "KRS", "39 Bayfront Ave Singapore 118956", 4, "6123 1300", "hotels_2.jpg");
        h4.addHotelFacility(restaurant);
        h4.addHotelFacility(gym);
        h4.addHotelFacility(spa);
        h4.addHotelFacility(swimming);
        h4.addHotelFacility(recreational);
        h4.addHotelFacility(functionroom);
        h4.addHotelFacility(concierge);
        h4.addHotelFacility(medical);
        hotelSessionLocal.createHotel(h4);
        h4 = hotelSessionLocal.getHotelByName("Kent Ridge South");

        Hotel h5 = new Hotel("Kent Ridge East", "KRE", "5 Pasir Ris Cl Singapore 519599", 4, "6123 1400", "hotels_3.jpg");
        h5.addHotelFacility(restaurant);
        h5.addHotelFacility(gym);
        h5.addHotelFacility(spa);
        h5.addHotelFacility(swimming);
        h5.addHotelFacility(recreational);
        h5.addHotelFacility(functionroom);
        h5.addHotelFacility(concierge);
        h5.addHotelFacility(medical);
        hotelSessionLocal.createHotel(h5);
        h5 = hotelSessionLocal.getHotelByName("Kent Ridge East");

        Hotel h6 = new Hotel("Kent Ridge West", "KRW", "6 Jurong East Central Singapore 609731", 4, "6123 1500", "hotels_4.jpg");
        h6.addHotelFacility(restaurant);
        h6.addHotelFacility(gym);
        h6.addHotelFacility(spa);
        h6.addHotelFacility(swimming);
        h6.addHotelFacility(recreational);
        h6.addHotelFacility(functionroom);
        h6.addHotelFacility(concierge);
        h6.addHotelFacility(medical);
        hotelSessionLocal.createHotel(h6);
        h6 = hotelSessionLocal.getHotelByName("Kent Ridge West");

        Hotel h7 = new Hotel("Kent Ridge North East", "KRNE", "78 Buangkok View Singapore 534191", 3, "6123 1600", "hotels_5.jpg");
        h7.addHotelFacility(restaurant);
        h7.addHotelFacility(gym);
        h7.addHotelFacility(spa);
        h7.addHotelFacility(swimming);
        h7.addHotelFacility(recreational);
        h7.addHotelFacility(functionroom);
        hotelSessionLocal.createHotel(h7);
        h7 = hotelSessionLocal.getHotelByName("Kent Ridge North East");

        Hotel h8 = new Hotel("Kent Ridge North West", "KRNW", "21 Choa Chu Kang Ave 4 Singapore 689812", 3, "6123 1700", "hotels_6.jpg");
        h8.addHotelFacility(restaurant);
        h8.addHotelFacility(gym);
        h8.addHotelFacility(spa);
        h8.addHotelFacility(swimming);
        h8.addHotelFacility(recreational);
        h8.addHotelFacility(functionroom);
        hotelSessionLocal.createHotel(h8);
        h8 = hotelSessionLocal.getHotelByName("Kent Ridge North West");

        Hotel h9 = new Hotel("Kent Ridge South East", "KRSE", "80 Marine Parade Rd Singapore 449269", 3, "6123 1800", "hotels_7.jpg");
        h9.addHotelFacility(restaurant);
        h9.addHotelFacility(gym);
        h9.addHotelFacility(spa);
        h9.addHotelFacility(swimming);
        h9.addHotelFacility(recreational);
        h9.addHotelFacility(functionroom);
        hotelSessionLocal.createHotel(h9);
        h9 = hotelSessionLocal.getHotelByName("Kent Ridge South East");

        Hotel h10 = new Hotel("Kent Ridge South West", "KRSW", "36 Lower Kent Ridge Road Singapore 119077", 3, "6123 1900", "hotels_8.jpg");
        h10.addHotelFacility(restaurant);
        h10.addHotelFacility(gym);
        h10.addHotelFacility(spa);
        h10.addHotelFacility(swimming);
        h10.addHotelFacility(recreational);
        h10.addHotelFacility(functionroom);
        hotelSessionLocal.createHotel(h10);
        h10 = hotelSessionLocal.getHotelByName("Kent Ridge South West");

//*********************************************MINIBAR ITEM************************************************
        MinibarItem m1 = new MinibarItem("Potato Chips", 5, 10.00);
        houseKeepingOrderSessionLocal.createMinibarItem(m1);
        m1 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Potato Chips");
        MinibarStock ms1 = new MinibarStock("Potato Chips", "KRG", 1000, 300);
        houseKeepingOrderSessionLocal.createMinibarStock(ms1);
        MinibarStock ms2 = new MinibarStock("Potato Chips", "KRC", 800, 200);
        houseKeepingOrderSessionLocal.createMinibarStock(ms2);
        MinibarStock ms3 = new MinibarStock("Potato Chips", "KRN", 600, 150);
        houseKeepingOrderSessionLocal.createMinibarStock(ms3);
        MinibarStock ms4 = new MinibarStock("Potato Chips", "KRS", 600, 150);
        houseKeepingOrderSessionLocal.createMinibarStock(ms4);
        MinibarStock ms5 = new MinibarStock("Potato Chips", "KRE", 600, 150);
        houseKeepingOrderSessionLocal.createMinibarStock(ms5);
        MinibarStock ms6 = new MinibarStock("Potato Chips", "KRW", 600, 150);
        houseKeepingOrderSessionLocal.createMinibarStock(ms6);
        MinibarStock ms7 = new MinibarStock("Potato Chips", "KRNE", 400, 100);
        houseKeepingOrderSessionLocal.createMinibarStock(ms7);
        MinibarStock ms8 = new MinibarStock("Potato Chips", "KRNS", 400, 100);
        houseKeepingOrderSessionLocal.createMinibarStock(ms8);
        MinibarStock ms9 = new MinibarStock("Potato Chips", "KRSE", 400, 100);
        houseKeepingOrderSessionLocal.createMinibarStock(ms9);
        MinibarStock ms10 = new MinibarStock("Potato Chips", "KRSW", 400, 100);
        houseKeepingOrderSessionLocal.createMinibarStock(ms10);

        MinibarItem m2 = new MinibarItem("Chocolate", 5, 8.00);
        houseKeepingOrderSessionLocal.createMinibarItem(m2);
        MinibarStock ms11 = new MinibarStock("Chocolate", "KRG", 1000, 300);
        houseKeepingOrderSessionLocal.createMinibarStock(ms11);
        MinibarStock ms12 = new MinibarStock("Chocolate", "KRC", 800, 200);
        houseKeepingOrderSessionLocal.createMinibarStock(ms12);
        MinibarStock ms13 = new MinibarStock("Chocolate", "KRN", 600, 150);
        houseKeepingOrderSessionLocal.createMinibarStock(ms13);
        MinibarStock ms14 = new MinibarStock("Chocolate", "KRS", 600, 150);
        houseKeepingOrderSessionLocal.createMinibarStock(ms14);
        MinibarStock ms15 = new MinibarStock("Chocolate", "KRE", 600, 150);
        houseKeepingOrderSessionLocal.createMinibarStock(ms15);
        MinibarStock ms16 = new MinibarStock("Chocolate", "KRW", 600, 150);
        houseKeepingOrderSessionLocal.createMinibarStock(ms16);
        MinibarStock ms17 = new MinibarStock("Chocolate", "KRNE", 400, 100);
        houseKeepingOrderSessionLocal.createMinibarStock(ms17);
        MinibarStock ms18 = new MinibarStock("Chocolate", "KRNS", 400, 100);
        houseKeepingOrderSessionLocal.createMinibarStock(ms18);
        MinibarStock ms19 = new MinibarStock("Chocolate", "KRSE", 400, 100);
        houseKeepingOrderSessionLocal.createMinibarStock(ms19);
        MinibarStock ms20 = new MinibarStock("Chocolate", "KRSW", 400, 100);
        houseKeepingOrderSessionLocal.createMinibarStock(ms20);

        m2 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Chocolate");

        MinibarItem m3 = new MinibarItem("Mineral Water", 3, 5.00);
        houseKeepingOrderSessionLocal.createMinibarItem(m3);
        MinibarStock ms21 = new MinibarStock("Mineral Water", "KRG", 1000, 300);
        houseKeepingOrderSessionLocal.createMinibarStock(ms21);
        MinibarStock ms22 = new MinibarStock("Mineral Water", "KRC", 800, 200);
        houseKeepingOrderSessionLocal.createMinibarStock(ms22);
        MinibarStock ms23 = new MinibarStock("Mineral Water", "KRN", 600, 150);
        houseKeepingOrderSessionLocal.createMinibarStock(ms23);
        MinibarStock ms24 = new MinibarStock("Mineral Water", "KRS", 600, 150);
        houseKeepingOrderSessionLocal.createMinibarStock(ms24);
        MinibarStock ms25 = new MinibarStock("Mineral Water", "KRE", 600, 150);
        houseKeepingOrderSessionLocal.createMinibarStock(ms25);
        MinibarStock ms26 = new MinibarStock("Mineral Water", "KRW", 600, 150);
        houseKeepingOrderSessionLocal.createMinibarStock(ms26);
        MinibarStock ms27 = new MinibarStock("Mineral Water", "KRNE", 400, 100);
        houseKeepingOrderSessionLocal.createMinibarStock(ms27);
        MinibarStock ms28 = new MinibarStock("Mineral Water", "KRNS", 400, 100);
        houseKeepingOrderSessionLocal.createMinibarStock(ms28);
        MinibarStock ms29 = new MinibarStock("Mineral Water", "KRSE", 400, 100);
        houseKeepingOrderSessionLocal.createMinibarStock(ms29);
        MinibarStock ms30 = new MinibarStock("Mineral Water", "KRSW", 400, 100);
        houseKeepingOrderSessionLocal.createMinibarStock(ms30);

        m3 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Mineral Water");

        MinibarItem m4 = new MinibarItem("Coke", 3, 7.00);
        houseKeepingOrderSessionLocal.createMinibarItem(m4);
        MinibarStock ms31 = new MinibarStock("Coke", "KRG", 1000, 300);
        houseKeepingOrderSessionLocal.createMinibarStock(ms31);
        MinibarStock ms32 = new MinibarStock("Coke", "KRC", 800, 200);
        houseKeepingOrderSessionLocal.createMinibarStock(ms32);
        MinibarStock ms33 = new MinibarStock("Coke", "KRN", 600, 150);
        houseKeepingOrderSessionLocal.createMinibarStock(ms33);
        MinibarStock ms34 = new MinibarStock("Coke", "KRS", 600, 150);
        houseKeepingOrderSessionLocal.createMinibarStock(ms34);
        MinibarStock ms35 = new MinibarStock("Coke", "KRE", 600, 150);
        houseKeepingOrderSessionLocal.createMinibarStock(ms35);
        MinibarStock ms36 = new MinibarStock("Coke", "KRW", 600, 150);
        houseKeepingOrderSessionLocal.createMinibarStock(ms36);
        MinibarStock ms37 = new MinibarStock("Coke", "KRNE", 400, 100);
        houseKeepingOrderSessionLocal.createMinibarStock(ms37);
        MinibarStock ms38 = new MinibarStock("Coke", "KRNS", 400, 100);
        houseKeepingOrderSessionLocal.createMinibarStock(ms38);
        MinibarStock ms39 = new MinibarStock("Coke", "KRSE", 400, 100);
        houseKeepingOrderSessionLocal.createMinibarStock(ms39);
        MinibarStock ms40 = new MinibarStock("Coke", "KRSW", 400, 100);
        houseKeepingOrderSessionLocal.createMinibarStock(ms40);
        m4 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Coke");

        MinibarItem m5 = new MinibarItem("Wine", 5, 60.00);
        houseKeepingOrderSessionLocal.createMinibarItem(m5);
        MinibarStock ms41 = new MinibarStock("Wine", "KRG", 1000, 300);
        houseKeepingOrderSessionLocal.createMinibarStock(ms41);
        MinibarStock ms42 = new MinibarStock("Wine", "KRC", 800, 200);
        houseKeepingOrderSessionLocal.createMinibarStock(ms42);
        MinibarStock ms43 = new MinibarStock("Wine", "KRN", 600, 150);
        houseKeepingOrderSessionLocal.createMinibarStock(ms43);
        MinibarStock ms44 = new MinibarStock("Wine", "KRS", 600, 150);
        houseKeepingOrderSessionLocal.createMinibarStock(ms44);
        MinibarStock ms45 = new MinibarStock("Wine", "KRE", 600, 150);
        houseKeepingOrderSessionLocal.createMinibarStock(ms45);
        MinibarStock ms46 = new MinibarStock("Wine", "KRW", 600, 150);
        houseKeepingOrderSessionLocal.createMinibarStock(ms46);
        MinibarStock ms47 = new MinibarStock("Wine", "KRNE", 400, 100);
        houseKeepingOrderSessionLocal.createMinibarStock(ms47);
        MinibarStock ms48 = new MinibarStock("Wine", "KRNS", 400, 100);
        houseKeepingOrderSessionLocal.createMinibarStock(ms48);
        MinibarStock ms49 = new MinibarStock("Wine", "KRSE", 400, 100);
        houseKeepingOrderSessionLocal.createMinibarStock(ms49);
        MinibarStock ms50 = new MinibarStock("Wine", "KRSW", 400, 100);
        houseKeepingOrderSessionLocal.createMinibarStock(ms50);

        m5 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Wine");

        MinibarItem m6 = new MinibarItem("Beer", 5, 15.00);
        houseKeepingOrderSessionLocal.createMinibarItem(m6);
        MinibarStock ms51 = new MinibarStock("Beer", "KRG", 1000, 300);
        houseKeepingOrderSessionLocal.createMinibarStock(ms51);
        MinibarStock ms52 = new MinibarStock("Beer", "KRC", 800, 200);
        houseKeepingOrderSessionLocal.createMinibarStock(ms52);
        MinibarStock ms53 = new MinibarStock("Beer", "KRN", 600, 150);
        houseKeepingOrderSessionLocal.createMinibarStock(ms53);
        MinibarStock ms54 = new MinibarStock("Beer", "KRS", 600, 150);
        houseKeepingOrderSessionLocal.createMinibarStock(ms54);
        MinibarStock ms55 = new MinibarStock("Beer", "KRE", 600, 150);
        houseKeepingOrderSessionLocal.createMinibarStock(ms55);
        MinibarStock ms56 = new MinibarStock("Beer", "KRW", 600, 150);
        houseKeepingOrderSessionLocal.createMinibarStock(ms56);
        MinibarStock ms57 = new MinibarStock("Beer", "KRNE", 400, 100);
        houseKeepingOrderSessionLocal.createMinibarStock(ms57);
        MinibarStock ms58 = new MinibarStock("Beer", "KRNS", 400, 100);
        houseKeepingOrderSessionLocal.createMinibarStock(ms58);
        MinibarStock ms59 = new MinibarStock("Beer", "KRSE", 400, 100);
        houseKeepingOrderSessionLocal.createMinibarStock(ms59);
        MinibarStock ms60 = new MinibarStock("Beer", "KRSW", 400, 100);
        houseKeepingOrderSessionLocal.createMinibarStock(ms60);

        m6 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Beer");

//*********************************************ROOM FACILIY************************************************
        RoomFacility rf1 = new RoomFacility("Wifi", "Communication", "wifi.png");//wifi.png
        roomFacilitySessionLocal.createRoomFacility(rf1);
        rf1 = roomFacilitySessionLocal.getRoomFacilityByName("Wifi");

        RoomFacility rf2 = new RoomFacility("Minibar", "Refreshment and Dining", "miniBar.png");//miniBar.png
        roomFacilitySessionLocal.createRoomFacility(rf2);
        rf2 = roomFacilitySessionLocal.getRoomFacilityByName("Minibar");

        RoomFacility rf3 = new RoomFacility("Aircon", "Others", "aircon.png");//aircon.png
        roomFacilitySessionLocal.createRoomFacility(rf3);
        rf3 = roomFacilitySessionLocal.getRoomFacilityByName("Aircon");

        RoomFacility rf4 = new RoomFacility("Cable TV", "Entertainment", "//tv.png");//tv.png
        roomFacilitySessionLocal.createRoomFacility(rf4);
        rf4 = roomFacilitySessionLocal.getRoomFacilityByName("Cable TV");

        RoomFacility rf5 = new RoomFacility("Telephone", "Communication", "telephone.png");//telephone.png
        roomFacilitySessionLocal.createRoomFacility(rf5);
        rf5 = roomFacilitySessionLocal.getRoomFacilityByName("Telephone");

        RoomFacility rf6 = new RoomFacility("Water Heater", "Bathroom", "waterHeater.png");//waterHeater.png
        roomFacilitySessionLocal.createRoomFacility(rf6);
        rf6 = roomFacilitySessionLocal.getRoomFacilityByName("Water Heater");

        RoomFacility rf7 = new RoomFacility("Bathroom with Shower Head", "Bathroom", "shower.png");//shower.png
        roomFacilitySessionLocal.createRoomFacility(rf7);
        rf7 = roomFacilitySessionLocal.getRoomFacilityByName("Bathroom with Shower Head");

        RoomFacility rf8 = new RoomFacility("HairDryer", "Bathroom", "hairdryer.png");//hairdryer.png
        roomFacilitySessionLocal.createRoomFacility(rf8);
        rf8 = roomFacilitySessionLocal.getRoomFacilityByName("HairDryer");

        RoomFacility rf9 = new RoomFacility("Safe Deposit Box", "Others", "safe.png");//safe.png
        roomFacilitySessionLocal.createRoomFacility(rf9);
        rf9 = roomFacilitySessionLocal.getRoomFacilityByName("Safe Deposit Box");

        RoomFacility rf10 = new RoomFacility("Water Kettle with complimentary teas/coffee", "Refreshment and Dining", "coffeeTea.png");//coffeeTea.png
        roomFacilitySessionLocal.createRoomFacility(rf10);
        rf10 = roomFacilitySessionLocal.getRoomFacilityByName("Water Kettle with complimentary teas/coffee");

        RoomFacility rf11 = new RoomFacility("Room with Windows", "Others", "windows.png");//windows.png
        roomFacilitySessionLocal.createRoomFacility(rf11);
        rf11 = roomFacilitySessionLocal.getRoomFacilityByName("Room with Windows");

        RoomFacility rf12 = new RoomFacility("Bathtub", "Bathroom", "bathtub.png");//baththub.png
        roomFacilitySessionLocal.createRoomFacility(rf12);
        rf12 = roomFacilitySessionLocal.getRoomFacilityByName("Bathtub");

        RoomFacility rf13 = new RoomFacility("Mini Fridge", "Refreshment and Dining", "miniFridge.png");//miniFridge.png
        roomFacilitySessionLocal.createRoomFacility(rf13);
        rf13 = roomFacilitySessionLocal.getRoomFacilityByName("Mini Fridge");

        RoomFacility rf14 = new RoomFacility("Coffee Machine", "Refreshment and Dining", "coffeeMachine.png");//coffeeMachine.png
        roomFacilitySessionLocal.createRoomFacility(rf14);
        rf14 = roomFacilitySessionLocal.getRoomFacilityByName("Coffee Machine");

        RoomFacility rf15 = new RoomFacility("Balcony", "Others", "balcony.png");//balcony.png
        roomFacilitySessionLocal.createRoomFacility(rf15);
        rf15 = roomFacilitySessionLocal.getRoomFacilityByName("Balcony");

        RoomFacility rf16 = new RoomFacility("Microwave", "Refreshment and Dining", "microwave.png");//microwave.png
        roomFacilitySessionLocal.createRoomFacility(rf16);
        rf16 = roomFacilitySessionLocal.getRoomFacilityByName("Microwave");

        RoomFacility rf17 = new RoomFacility("Gaming Console", "Entertainment", "gaming.png");//gaming.png
        roomFacilitySessionLocal.createRoomFacility(rf17);
        rf17 = roomFacilitySessionLocal.getRoomFacilityByName("Gaming Console");

        RoomFacility rf18 = new RoomFacility("Entertainment System", "Entertainment", "entertainmentSystem.png");//entertainmentSystem.png
        roomFacilitySessionLocal.createRoomFacility(rf18);
        rf18 = roomFacilitySessionLocal.getRoomFacilityByName("Entertainment System");

        RoomFacility rf19 = new RoomFacility("Blackout Curtains", "Others", "curtain.png");//curtain.png
        roomFacilitySessionLocal.createRoomFacility(rf19);
        rf19 = roomFacilitySessionLocal.getRoomFacilityByName("Blackout Curtains");

        RoomFacility rf20 = new RoomFacility("High Ceiling", "Others", "highCeiling.png");//highCeiling.png
        roomFacilitySessionLocal.createRoomFacility(rf20);
        rf20 = roomFacilitySessionLocal.getRoomFacilityByName("High Ceiling");

        RoomFacility rf21 = new RoomFacility("Jaccuzi", "Bathroom", "jacuzzi.png");//jacuzzi.png
        roomFacilitySessionLocal.createRoomFacility(rf21);
        rf21 = roomFacilitySessionLocal.getRoomFacilityByName("Jaccuzi");

        RoomFacility rf22 = new RoomFacility("Kitchen", "Refreshment and Dining", "kitchen.png");//kitchen.png
        roomFacilitySessionLocal.createRoomFacility(rf22);
        rf22 = roomFacilitySessionLocal.getRoomFacilityByName("Kitchen");
//*********************************************CUSTOMER************************************************
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Customer c1 = new Customer("Zack", "Neo Guohui", "Male", encryptPassword("test"), 187, "zell1502@hotmail.com", "+6591321876", format.parse("2019-02-04"), true);
        customerSessionLocal.createCustomer(c1);
        //        c1 = customerSessionLocal.getCustomerByEmail("neoguoh202@hotmail.com");
        c1 = customerSessionLocal.getCustomerByEmail("zell1502@hotmail.com");

        Customer c2 = new Customer("Pillay", "Maureen", "Male", encryptPassword("test"), 31877, "anselm.dm@gmail.com", "+6582375237", format.parse("2019-01-28"), false);
        c2.setPoints(6000);
        customerSessionLocal.createCustomer(c2);
        c2 = customerSessionLocal.getCustomerByEmail("anselm.dm@gmail.com");
        //        c2 = customerSessionLocal.getCustomerByEmail("maureenpi1@yahoo.com.sg");

        Customer c3 = new Customer("Mei Fang", "Chia", "Female", encryptPassword("test"), 1023823, "chiame317@gmail.com", "+6591312719", format.parse("2019-03-01"), true);
        customerSessionLocal.createCustomer(c3);
        c3 = customerSessionLocal.getCustomerByEmail("chiame317@gmail.com");

        Customer c4 = new Customer("Randal", "Ho", "Male", encryptPassword("test"), 5099, "randalh753@gmail.com", "+6593243287", format.parse("2019-01-19"), false);
        customerSessionLocal.createCustomer(c4);
        c4 = customerSessionLocal.getCustomerByEmail("randalh753@gmail.com");

        Customer c5 = new Customer("Lok Li", "Choo", "Female", encryptPassword("test"), 38271, "loklich35@hotmail.com", "+6598768896", format.parse("2019-04-01"), true);
        customerSessionLocal.createCustomer(c5);
        c5 = customerSessionLocal.getCustomerByEmail("loklich35@hotmail.com");

        Customer c6 = new Customer("Mervin", "Ee", "Male", encryptPassword("test"), 200192, "mervinee@live.com", "+6587652376", format.parse("2019-02-27"), true);
        customerSessionLocal.createCustomer(c6);
        c6 = customerSessionLocal.getCustomerByEmail("mervinee@live.com");

        Customer c7 = new Customer("Galuh Jabal", "Thamrin", "Male", encryptPassword("test"), 382, "thamrinjb@live.com", "+622169195347", format.parse("2019-03-06"), true);
        customerSessionLocal.createCustomer(c7);
        c7 = customerSessionLocal.getCustomerByEmail("thamrinjb@live.com");

        Customer c8 = new Customer("Bao Cong", "Chen", "Male", encryptPassword("test"), 4819, "chenbc@163.com", "+8613067076921", format.parse("2019-03-21"), true);
        customerSessionLocal.createCustomer(c8);
        c8 = customerSessionLocal.getCustomerByEmail("chenbc@163.com");

        Customer c9 = new Customer("Meg Kou", "Thong", "Female", encryptPassword("test"), 2938, "thongmk@hotmail.com", "+60188617734", format.parse("2019-03-31"), true);
        customerSessionLocal.createCustomer(c9);
        c9 = customerSessionLocal.getCustomerByEmail("thongmk@hotmail.com");

        Customer c10 = new Customer("Cuong Khanh", "Anh Nhiem", "Male", encryptPassword("test"), 9281, "cukhanhn@gmail.com", "+84843506870", format.parse("2019-04-01"), true);
        customerSessionLocal.createCustomer(c10);
        c10 = customerSessionLocal.getCustomerByEmail("cukhanhn@gmail.com");
//*********************************************FOOD MENU ITEM************************************************
        FoodMenuItem fmi1 = new FoodMenuItem("Pancakes", "Served with maple syrup, wild berry compote and toasted almond flakes", "All Day Breakfast", true, 18.0, "pancakes.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi1);
        fmi1 = foodMenuItemSessionLocal.getFoodMenuItemByName("Pancakes");

        FoodMenuItem fmi2 = new FoodMenuItem("Scrambled Eggs", "Served with chicken sausages, bacon, grilled tomato baked beans and hash brown potato", "All Day Breakfast", true, 22.0, "scrambledeggs.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi2);
        fmi2 = foodMenuItemSessionLocal.getFoodMenuItemByName("Scrambled Eggs");

        FoodMenuItem fmi3 = new FoodMenuItem("Traditional Caesar Salad", "A classic Caesar salad with baby cos lettuce, candied back bacon, croutons, parmesan, white anchovies, Caesar dressing and freshly coddled free-range egg", "Starters", true, 14.0, "caesarsalad.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi3);
        fmi3 = foodMenuItemSessionLocal.getFoodMenuItemByName("Traditional Caesar Salad");

        FoodMenuItem fmi4 = new FoodMenuItem("Mushroom Veloute", "With truffle espuma", "Starters", true, 18.0, "mushroomveloute.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi4);
        fmi4 = foodMenuItemSessionLocal.getFoodMenuItemByName("Mushroom Veloute");

        FoodMenuItem fmi5 = new FoodMenuItem("Club Sandwich", "Chicken breast, crispy smoked back bacon, ripe tomatoes, fried free-range egg, crisp lettuce layered between toasted bread with house mayonnaise", "Sandwiches and Burgers", true, 25.0, "clubsandwich.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi5);
        fmi5 = foodMenuItemSessionLocal.getFoodMenuItemByName("Club Sandwich");

        FoodMenuItem fmi6 = new FoodMenuItem("Australian Black Angus Beef Burger", "Premium Angus patty, crispy bacon, roma tomatoes, shredded lettuce, onion marmalade and Swiss Cheese", "Sandwiches and Burgers", true, 29.0, "beefburger.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi6);
        fmi6 = foodMenuItemSessionLocal.getFoodMenuItemByName("Australian Black Angus Beef Burger");

        FoodMenuItem fmi7 = new FoodMenuItem("Butter Chicken", "Chicken simmered in fragrant creamy tomato sauce served with steamed basmati rice, naan bread and chutney", "Flavours of Asia", true, 27.0, "butterchicken.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi7);
        fmi7 = foodMenuItemSessionLocal.getFoodMenuItemByName("Butter Chicken");

        FoodMenuItem fmi8 = new FoodMenuItem("Char Kway Teow", "Wok-fried flat rice noodles with seafood, cockles, chinese pork sausage and bean sprout", "Flavours of Asia", true, 26.0, "charkwayteow.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi8);
        fmi8 = foodMenuItemSessionLocal.getFoodMenuItemByName("Char Kway Teow");

        FoodMenuItem fmi9 = new FoodMenuItem("Hainanese Chicken Rice", " Hainanese-style poached chicken, chilli sauce, ginger sauce, dark soya sauce, served with fragrant chicken rice ", "Flavours of Asia", true, 27.0, "chickenrice.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi9);
        fmi9 = foodMenuItemSessionLocal.getFoodMenuItemByName("Hainanese Chicken Rice");

        FoodMenuItem fmi10 = new FoodMenuItem("Nasi Goreng Kampung", "Fragrant fried rice with seafood, chicken satay, fried egg, acar, served with crackers", "Flavours of Asia", true, 26.0, "nasigoreng.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi10);
        fmi10 = foodMenuItemSessionLocal.getFoodMenuItemByName("Nasi Goreng Kampung");

        FoodMenuItem fmi11 = new FoodMenuItem("Fish And Chips", " Today’s catch of beer battered reef fish, fries, tartare lemon sauce and green peas on the side ", "Mains", true, 25.0, "fishandchips.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi11);
        fmi11 = foodMenuItemSessionLocal.getFoodMenuItemByName("Fish And Chips");

        FoodMenuItem fmi12 = new FoodMenuItem("Centre Cut Australian Black Angus Sirloin", " With seasonal baby vegetables, truffle celeriac mousseline, french shallot and black garlic ", "Mains", true, 48.0, "australiansirloin.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi12);
        fmi12 = foodMenuItemSessionLocal.getFoodMenuItemByName("Centre Cut Australian Black Angus Sirloin");

        FoodMenuItem fmi13 = new FoodMenuItem("Whole Wheat Spaghetti Pasta ", " With bolognaise sauce ", "Mains", true, 24.0, "spaghettipasta.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi13);
        fmi13 = foodMenuItemSessionLocal.getFoodMenuItemByName("Whole Wheat Spaghetti Pasta");

        FoodMenuItem fmi14 = new FoodMenuItem("Tiger Lager", "", "Beverages (Alcohol)", true, 16.0, "tiger.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi14);
        fmi14 = foodMenuItemSessionLocal.getFoodMenuItemByName("Tiger Lager");

        FoodMenuItem fmi15 = new FoodMenuItem("Corona Extra Beer", "", "Beverages (Alcohol)", true, 16.0, "corona.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi15);
        fmi15 = foodMenuItemSessionLocal.getFoodMenuItemByName("Corona Extra Beer ");

        FoodMenuItem fmi16 = new FoodMenuItem("Heineken", "", "Beverages (Alcohol)", true, 16.0, "heineken.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi16);
        fmi16 = foodMenuItemSessionLocal.getFoodMenuItemByName("Heineken");

        FoodMenuItem fmi17 = new FoodMenuItem("Kirin", "", "Beverages (Alcohol)", true, 16.0, "kirin.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi17);
        fmi17 = foodMenuItemSessionLocal.getFoodMenuItemByName("Kirin");

        FoodMenuItem fmi18 = new FoodMenuItem("Grey Goose (Shot Glass)", "", "Beverages (Alcohol)", true, 20.0, "greygoose.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi18);
        fmi18 = foodMenuItemSessionLocal.getFoodMenuItemByName("Grey Goose (Shot Glass)");

        FoodMenuItem fmi19 = new FoodMenuItem("Johnnie Walker Gold Label (Dram)", "", "Beverages (Alcohol)", true, 20.0, "johnniewalker.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi19);
        fmi19 = foodMenuItemSessionLocal.getFoodMenuItemByName("Johnnie Walker Gold Label (Dram)");

        FoodMenuItem fmi20 = new FoodMenuItem("Orange Juice", "", "Beverages (Chilled Juices)", true, 7.0, "orangejuice.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi20);
        fmi20 = foodMenuItemSessionLocal.getFoodMenuItemByName("Orange Juice");

        FoodMenuItem fmi21 = new FoodMenuItem("Mango Juice", "", "Beverages (Chilled Juices)", true, 7.0, "mangojuice.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi21);
        fmi21 = foodMenuItemSessionLocal.getFoodMenuItemByName("Mango Juice");

        FoodMenuItem fmi22 = new FoodMenuItem("Apple Juice", "", "Beverages (Chilled Juices)", true, 7.0, "applejuice.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi22);
        fmi22 = foodMenuItemSessionLocal.getFoodMenuItemByName("Apple Juice");

        FoodMenuItem fmi23 = new FoodMenuItem("Tomato Juice", "", "Beverages (Chilled Juices)", true, 7.0, "tomatojuice.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi23);
        fmi23 = foodMenuItemSessionLocal.getFoodMenuItemByName("Tomato Juice");

        FoodMenuItem fmi24 = new FoodMenuItem("Cranberry Juice", "", "Beverages (Chilled Juices)", true, 7.0, "cranberryjuice.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi24);
        fmi24 = foodMenuItemSessionLocal.getFoodMenuItemByName("Cranberry Juice");

        FoodMenuItem fmi25 = new FoodMenuItem("Coke", "", "Soft Drinks", true, 7.0, "coke.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi25);
        fmi25 = foodMenuItemSessionLocal.getFoodMenuItemByName("Coke");

        FoodMenuItem fmi26 = new FoodMenuItem("Coke Zero", "", "Soft Drinks", true, 7.0, "cokezero.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi26);
        fmi26 = foodMenuItemSessionLocal.getFoodMenuItemByName("Coke Zero");

        FoodMenuItem fmi27 = new FoodMenuItem("Sprite", "", "Soft Drinks", true, 7.0, "sprite.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi27);
        fmi27 = foodMenuItemSessionLocal.getFoodMenuItemByName("Sprite");

        FoodMenuItem fmi28 = new FoodMenuItem("Espresso (Hot)", "", "Coffee", true, 8.0, "espressohot.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi28);
        fmi28 = foodMenuItemSessionLocal.getFoodMenuItemByName("Espresso (Hot)");

        FoodMenuItem fmi29 = new FoodMenuItem("Espresso (Cold)", "", "Coffee", true, 10.0, "espressocold.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi29);
        fmi29 = foodMenuItemSessionLocal.getFoodMenuItemByName("Espresso (Cold)");

        FoodMenuItem fmi30 = new FoodMenuItem("Cappuccino (Hot)", "", "Coffee", true, 8.0, "cappuccinohot.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi30);
        fmi30 = foodMenuItemSessionLocal.getFoodMenuItemByName("Cappuccino (Hot)");

        FoodMenuItem fmi31 = new FoodMenuItem("Cappuccino (Cold)", "", "Coffee", true, 10.0, "cappuccinocold.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi31);
        fmi31 = foodMenuItemSessionLocal.getFoodMenuItemByName("Cappuccino (Cold)");

        FoodMenuItem fmi32 = new FoodMenuItem("Cafe Latte (Hot)", "", "Coffee", true, 8.0, "cafelattehot.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi32);
        fmi32 = foodMenuItemSessionLocal.getFoodMenuItemByName("Cafe Latte (Hot)");

        FoodMenuItem fmi33 = new FoodMenuItem("Cafe Latte (Cold)", "", "Coffee", true, 10.0, "cafelattecold.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi33);
        fmi33 = foodMenuItemSessionLocal.getFoodMenuItemByName("Cafe Latte (Cold)");

        FoodMenuItem fmi34 = new FoodMenuItem("Mocha (Hot)", "", "Coffee", true, 8.0, "mochahot.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi34);
        fmi34 = foodMenuItemSessionLocal.getFoodMenuItemByName("Mocha (Hot)");

        FoodMenuItem fmi35 = new FoodMenuItem("Mocha (Cold)", "", "Coffee", true, 10.0, "mochacold.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi35);
        fmi35 = foodMenuItemSessionLocal.getFoodMenuItemByName("Mocha (Cold)");

        FoodMenuItem fmi36 = new FoodMenuItem("English Breakfast Tea", "", "Tea", true, 8.0, "englishbreakfasttea.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi36);
        fmi36 = foodMenuItemSessionLocal.getFoodMenuItemByName("English Breakfast Tea");

        FoodMenuItem fmi37 = new FoodMenuItem("Chamomile", "", "Tea", true, 8.0, "chamomile.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi37);
        fmi37 = foodMenuItemSessionLocal.getFoodMenuItemByName("Chamomile");

        FoodMenuItem fmi38 = new FoodMenuItem("Earl Grey", "", "Tea", true, 8.0, "earlgrey.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi38);
        fmi38 = foodMenuItemSessionLocal.getFoodMenuItemByName("Earl Grey");

        FoodMenuItem fmi39 = new FoodMenuItem("Lipton", "", "Tea", true, 8.0, "lipton.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi39);
        fmi39 = foodMenuItemSessionLocal.getFoodMenuItemByName("Lipton");

        FoodMenuItem fmi40 = new FoodMenuItem("Grand Jasmine ", "", "Tea", true, 8.0, "grandjasmine.jpg");
        foodMenuItemSessionLocal.createFoodMenuItem(fmi40);
        fmi40 = foodMenuItemSessionLocal.getFoodMenuItemByName("Grand Jasmine");

//*********************************************ROOM PRICING************************************************
        RoomPricing rp1 = new RoomPricing("KRG_Standard", 150.0);
        roomPricingSessionLocal.createRoomPricing(rp1);
        RoomPricing rp2 = new RoomPricing("KRG_Premium", 200.0);
        roomPricingSessionLocal.createRoomPricing(rp2);
        RoomPricing rp3 = new RoomPricing("KRG_Deluxe", 300.0);
        roomPricingSessionLocal.createRoomPricing(rp3);
        RoomPricing rp4 = new RoomPricing("KRG_Suite", 400.0);
        roomPricingSessionLocal.createRoomPricing(rp4);
        RoomPricing rp5 = new RoomPricing("KRG_Penthouse", 500.0);
        roomPricingSessionLocal.createRoomPricing(rp5);

        RoomPricing rp6 = new RoomPricing("KRC_Standard", 120.0);
        roomPricingSessionLocal.createRoomPricing(rp6);
        RoomPricing rp7 = new RoomPricing("KRC_Premium", 150.0);
        roomPricingSessionLocal.createRoomPricing(rp7);
        RoomPricing rp8 = new RoomPricing("KRC_Deluxe", 200.0);
        roomPricingSessionLocal.createRoomPricing(rp8);
        RoomPricing rp9 = new RoomPricing("KRC_Suite", 250.0);
        roomPricingSessionLocal.createRoomPricing(rp9);

        RoomPricing rp10 = new RoomPricing("KRN_Standard", 120.0);
        roomPricingSessionLocal.createRoomPricing(rp10);
        RoomPricing rp11 = new RoomPricing("KRN_Premium", 150.0);
        roomPricingSessionLocal.createRoomPricing(rp11);
        RoomPricing rp12 = new RoomPricing("KRN_Deluxe", 200.0);
        roomPricingSessionLocal.createRoomPricing(rp12);
        RoomPricing rp13 = new RoomPricing("KRN_Suite", 250.0);
        roomPricingSessionLocal.createRoomPricing(rp13);

        RoomPricing rp14 = new RoomPricing("KRS_Standard", 120.0);
        roomPricingSessionLocal.createRoomPricing(rp14);
        RoomPricing rp15 = new RoomPricing("KRS_Premium", 150.0);
        roomPricingSessionLocal.createRoomPricing(rp15);
        RoomPricing rp16 = new RoomPricing("KRS_Deluxe", 200.0);
        roomPricingSessionLocal.createRoomPricing(rp16);
        RoomPricing rp17 = new RoomPricing("KRS_Suite", 250.0);
        roomPricingSessionLocal.createRoomPricing(rp17);

        RoomPricing rp18 = new RoomPricing("KRE_Standard", 120.0);
        roomPricingSessionLocal.createRoomPricing(rp18);
        RoomPricing rp19 = new RoomPricing("KRE_Premium", 150.0);
        roomPricingSessionLocal.createRoomPricing(rp19);
        RoomPricing rp20 = new RoomPricing("KRE_Deluxe", 200.0);
        roomPricingSessionLocal.createRoomPricing(rp20);
        RoomPricing rp21 = new RoomPricing("KRE_Suite", 250.0);
        roomPricingSessionLocal.createRoomPricing(rp21);

        RoomPricing rp22 = new RoomPricing("KRW_Standard", 120.0);
        roomPricingSessionLocal.createRoomPricing(rp22);
        RoomPricing rp23 = new RoomPricing("KRW_Premium", 150.0);
        roomPricingSessionLocal.createRoomPricing(rp23);
        RoomPricing rp24 = new RoomPricing("KRW_Deluxe", 200.0);
        roomPricingSessionLocal.createRoomPricing(rp24);
        RoomPricing rp25 = new RoomPricing("KRW_Suite", 250.0);
        roomPricingSessionLocal.createRoomPricing(rp25);

        RoomPricing rp26 = new RoomPricing("KRNE_Standard", 100.0);
        roomPricingSessionLocal.createRoomPricing(rp26);
        RoomPricing rp27 = new RoomPricing("KRNE_Premium", 120.0);
        roomPricingSessionLocal.createRoomPricing(rp27);
        RoomPricing rp28 = new RoomPricing("KRNE_Deluxe", 150.0);
        roomPricingSessionLocal.createRoomPricing(rp28);
        RoomPricing rp29 = new RoomPricing("KRNE_Suite", 200.0);
        roomPricingSessionLocal.createRoomPricing(rp29);

        RoomPricing rp30 = new RoomPricing("KRNW_Standard", 100.0);
        roomPricingSessionLocal.createRoomPricing(rp30);
        RoomPricing rp31 = new RoomPricing("KRNW_Premium", 120.0);
        roomPricingSessionLocal.createRoomPricing(rp31);
        RoomPricing rp32 = new RoomPricing("KRNW_Deluxe", 150.0);
        roomPricingSessionLocal.createRoomPricing(rp32);
        RoomPricing rp33 = new RoomPricing("KRNW_Suite", 200.0);
        roomPricingSessionLocal.createRoomPricing(rp33);

        RoomPricing rp34 = new RoomPricing("KRSE_Standard", 100.0);
        roomPricingSessionLocal.createRoomPricing(rp34);
        RoomPricing rp35 = new RoomPricing("KRSE_Premium", 120.0);
        roomPricingSessionLocal.createRoomPricing(rp35);
        RoomPricing rp36 = new RoomPricing("KRSE_Deluxe", 150.0);
        roomPricingSessionLocal.createRoomPricing(rp36);
        RoomPricing rp37 = new RoomPricing("KRSE_Suite", 200.0);
        roomPricingSessionLocal.createRoomPricing(rp37);

        RoomPricing rp38 = new RoomPricing("KRSW_Standard", 100.0);
        roomPricingSessionLocal.createRoomPricing(rp38);
        RoomPricing rp39 = new RoomPricing("KRSW_Premium", 120.0);
        roomPricingSessionLocal.createRoomPricing(rp39);
        RoomPricing rp40 = new RoomPricing("KRSW_Deluxe", 150.0);
        roomPricingSessionLocal.createRoomPricing(rp40);
        RoomPricing rp41 = new RoomPricing("KRSW_Suite", 200.0);
        roomPricingSessionLocal.createRoomPricing(rp41);

//*********************************************PROMO CODE************************************************        
        PromoCode pc1 = new PromoCode();
        pc1.setPromoCode("GROUPBOOKING");
        pc1.setStartDate(format.parse("2019-01-01"));
        pc1.setEndDate(format.parse("2019-12-31"));
        pc1.setStatus("Active");
        pc1.setDiscount(0.05);
        promoCodeSessionLocal.createPromoCode(pc1);

        PromoCode pc2 = new PromoCode();
        pc2.setPromoCode("HAPPYNEWYEAR");
        pc2.setStartDate(format.parse("2019-01-01"));
        pc2.setEndDate(format.parse("2019-12-31"));
        pc2.setStatus("Active");
        pc2.setDiscount(0.1);
        promoCodeSessionLocal.createPromoCode(pc2);

        PromoCode pc3 = new PromoCode();
        pc3.setPromoCode("DISCOUNT 015");
        pc3.setStartDate(format.parse("2019-01-01"));
        pc3.setEndDate(format.parse("2019-12-31"));
        pc3.setStatus("Active");
        pc3.setDiscount(0.15);
        promoCodeSessionLocal.createPromoCode(pc3);

        PromoCode pc4 = new PromoCode();
        pc4.setPromoCode("SPECIALRATE");
        pc4.setStartDate(format.parse("2019-01-01"));
        pc4.setEndDate(format.parse("2019-12-31"));
        pc4.setStatus("Active");
        pc4.setDiscount(0.2);
        promoCodeSessionLocal.createPromoCode(pc4);

        PromoCode pc5 = new PromoCode();
        pc5.setPromoCode("STAFFRATE");
        pc5.setStartDate(format.parse("2019-01-01"));
        pc5.setEndDate(format.parse("2019-12-31"));
        pc5.setStatus("Active");
        pc5.setDiscount(0.25);
        promoCodeSessionLocal.createPromoCode(pc5);

//*********************************************FEEDBACK************************************************
        Feedback fb1 = new Feedback();
        fb1.setFeedBackDate(format.parse("2019-03-01"));
        fb1.setFeedBackTitle("Good Stay");
        fb1.setFeedBackFrom("Randal Ho");
        fb1.setFeedBackMsg("Had a very good stay, efficent booking and comfortable rooms.");
        fb1.setHotel(h1);
        feedbackSessionLocal.createFeedback(fb1);

        Feedback fb2 = new Feedback();
        fb2.setFeedBackDate(format.parse("2019-03-08"));
        fb2.setFeedBackTitle("Food could be better");
        fb2.setFeedBackFrom("Lok Li Choo");
        fb2.setFeedBackMsg("rooms were too cold, food was not very good");
        fb2.setHotel(h1);
        feedbackSessionLocal.createFeedback(fb2);

        Feedback fb3 = new Feedback();
        fb3.setFeedBackDate(format.parse("2019-03-10"));
        fb3.setFeedBackTitle("Rooms very nice");
        fb3.setFeedBackFrom("Chia Mei Fang");
        fb3.setFeedBackMsg("rooms were recently renovated and very comfortable, modern and nice.");
        fb3.setHotel(h1);
        feedbackSessionLocal.createFeedback(fb3);
		
        Feedback fb4 = new Feedback();
        fb4.setFeedBackDate(format.parse("2019-04-01"));
        fb4.setFeedBackTitle("Hotel place is good");
        fb4.setFeedBackFrom("Chaminda");
        fb4.setFeedBackMsg("Room is clean Superb and clean every where. More shopping around ");
        fb4.setHotel(h2);
        fb4.setFeedbackRating(5);
        feedbackSessionLocal.createFeedback(fb4);

        Feedback fb5 = new Feedback();
        fb5.setFeedBackDate(format.parse("2019-04-01"));
        fb5.setFeedBackTitle("Service");
        fb5.setFeedBackFrom("Chaminda");
        fb5.setFeedBackMsg("Rooms are clean and comfortable.Service is good from the hotel. The hotel is very near from mrt. The hotel is very Near from restaurant");
        fb5.setHotel(h2);
        fb5.setFeedbackRating(5);
        feedbackSessionLocal.createFeedback(fb5);

        Feedback fb6 = new Feedback();
        fb6.setFeedBackDate(format.parse("2019-04-04"));
        fb6.setFeedBackTitle("Bang for the buck");
        fb6.setFeedBackFrom("Abhishek N");
        fb6.setFeedBackMsg("Great hotel with air conditioned and clean rooms.Hotel has great wifi connection.Hotel staff was very disciplined and helpful, especially Mr Gill the manager.");
        fb6.setHotel(h2);
        fb6.setFeedbackRating(5);
        feedbackSessionLocal.createFeedback(fb6);

        Feedback fb7 = new Feedback();
        fb7.setFeedBackDate(format.parse("2019-04-01"));
        fb7.setFeedBackTitle("Friendly and helpfull staffs");
        fb7.setFeedBackFrom("Ng Swee Thai");
        fb7.setFeedBackMsg("Staff were amazing and very helpful. Very convenience location. Close to subway and many famous shopping mall ");
        fb7.setHotel(h3);
        fb7.setFeedbackRating(5);
        feedbackSessionLocal.createFeedback(fb7);

        Feedback fb8 = new Feedback();
        fb8.setFeedBackDate(format.parse("2019-03-01"));
        fb8.setFeedBackTitle("A little gem");
        fb8.setFeedBackFrom("Angel Peck");
        fb8.setFeedBackMsg("I found this hotel to be clean & comfortable with fresh toiletries supplied daily.");
        fb8.setHotel(h3);
        fb8.setFeedbackRating(4);
        feedbackSessionLocal.createFeedback(fb8);

        Feedback fb9 = new Feedback();
        fb9.setFeedBackDate(format.parse("2019-03-01"));
        fb9.setFeedBackTitle("Strategic location");
        fb9.setFeedBackFrom("Dibyne");
        fb9.setFeedBackMsg("the hotel is well-maintained and clean.");
        fb9.setHotel(h3);
        fb9.setFeedbackRating(4);
        feedbackSessionLocal.createFeedback(fb9);

        Feedback fb10 = new Feedback();
        fb10.setFeedBackDate(format.parse("2019-02-23"));
        fb10.setFeedBackTitle("Value for money");
        fb10.setFeedBackFrom("Gujarati C");
        fb10.setFeedBackMsg("Very near to metro .. cleanliness was good.. centralised ac and separate ac in room");
        fb10.setHotel(h4);
        fb10.setFeedbackRating(4);
        feedbackSessionLocal.createFeedback(fb10);

        Feedback fb11 = new Feedback();
        fb11.setFeedBackDate(format.parse("2019-02-23"));
        fb11.setFeedBackTitle("great location");
        fb11.setFeedBackFrom("Barry A");
        fb11.setFeedBackMsg("rooms are small but good wifi and new aircons.");
        fb11.setHotel(h4);
        fb11.setFeedbackRating(4);
        feedbackSessionLocal.createFeedback(fb11);

        Feedback fb12 = new Feedback();
        fb12.setFeedBackDate(format.parse("2019-02-14"));
        fb12.setFeedBackTitle("Reasonable hotel in a great location");
        fb12.setFeedBackFrom("James H");
        fb12.setFeedBackMsg("Clean tidy on suit room. Not cheap a night. But the staff are friendly and helpful. ");
        fb12.setHotel(h4);
        fb12.setFeedbackRating(4);
        feedbackSessionLocal.createFeedback(fb12);

        Feedback fb13 = new Feedback();
        fb13.setFeedBackDate(format.parse("2019-01-21"));
        fb13.setFeedBackTitle("Convenient to access");
        fb13.setFeedBackFrom("Eriko");
        fb13.setFeedBackMsg("The room is clean, however, refrigerator and bath tab havent.");
        fb13.setHotel(h5);
        fb13.setFeedbackRating(4);
        feedbackSessionLocal.createFeedback(fb13);

        Feedback fb14 = new Feedback();
        fb14.setFeedBackDate(format.parse("2019-01-23"));
        fb14.setFeedBackTitle("Good value, good service, no thrills hotel");
        fb14.setFeedBackFrom("James Rattigan");
        fb14.setFeedBackMsg("Even though our room was kinda small for a couple we still enjoyed our stay. For 3 days the room was ok, but for longer stay I wouldn't recommend it, because there was no place for our things");
        fb14.setHotel(h5);
        fb14.setFeedbackRating(4);
        feedbackSessionLocal.createFeedback(fb14);

        Feedback fb15 = new Feedback();
        fb15.setFeedBackDate(format.parse("2019-01-02"));
        fb15.setFeedBackTitle("Very friendly staff");
        fb15.setFeedBackFrom("Jan S");
        fb15.setFeedBackMsg("The hotel was clean and quiet.");
        fb15.setHotel(h5);
        fb15.setFeedbackRating(4);
        feedbackSessionLocal.createFeedback(fb15);

        Feedback fb16 = new Feedback();
        fb16.setFeedBackDate(format.parse("2019-01-21"));
        fb16.setFeedBackTitle("Feedback");
        fb16.setFeedBackFrom("Peter B");
        fb16.setFeedBackMsg("Neat and Tidy and its so close to the hotels and all near shopping malls");
        fb16.setHotel(h6);
        fb16.setFeedbackRating(4);
        feedbackSessionLocal.createFeedback(fb16);

        Feedback fb17 = new Feedback();
        fb17.setFeedBackDate(format.parse("2019-01-07"));
        fb17.setFeedBackTitle("Good Hotel for short stays");
        fb17.setFeedBackFrom("Peter B");
        fb17.setFeedBackMsg("Excellent location, good hotel at reasonable rates, very tidy, all amenities provided, very quiet even if facing the stree.");
        fb17.setHotel(h6);
        fb17.setFeedbackRating(4);
        feedbackSessionLocal.createFeedback(fb17);

        Feedback fb18 = new Feedback();
        fb18.setFeedBackDate(format.parse("2019-03-07"));
        fb18.setFeedBackTitle("Worth It");
        fb18.setFeedBackFrom("Rahul");
        fb18.setFeedBackMsg("Decent hotel at an affordable rate.Rooms are extremely small in size without any wardrobe or windows. However hotel staff is somewhat co-operative and friendly.");
        fb18.setHotel(h6);
        fb18.setFeedbackRating(3);
        feedbackSessionLocal.createFeedback(fb18);

        Feedback fb19 = new Feedback();
        fb19.setFeedBackDate(format.parse("2019-03-04"));
        fb19.setFeedBackTitle("Service");
        fb19.setFeedBackFrom("Wesley");
        fb19.setFeedBackMsg("Rooom is clean and very worth the value. 8athroom has hot water always.AIrcon is cool and very cold. Staff are very friendly");
        fb19.setHotel(h7);
        fb19.setFeedbackRating(5);
        feedbackSessionLocal.createFeedback(fb19);

        Feedback fb20 = new Feedback();
        fb20.setFeedBackDate(format.parse("2019-03-04"));
        fb20.setFeedBackTitle("Value for money and great location");
        fb20.setFeedBackFrom("Keshini H");
        fb20.setFeedBackMsg("We booked a standard room online and when we checked in to the hotel we wanted to see the room we booked");
        fb20.setHotel(h7);
        fb20.setFeedbackRating(4);
        feedbackSessionLocal.createFeedback(fb20);

        Feedback fb21 = new Feedback();
        fb21.setFeedBackDate(format.parse("2019-03-05"));
        fb21.setFeedBackTitle("Good location");
        fb21.setFeedBackFrom("Mahendra R");
        fb21.setFeedBackMsg("Easy to travel to many different places.All the rooms is clean.Too small but comfortable");
        fb21.setHotel(h7);
        fb21.setFeedbackRating(4);
        feedbackSessionLocal.createFeedback(fb21);

        Feedback fb22 = new Feedback();
        fb22.setFeedBackDate(format.parse("2019-03-03"));
        fb22.setFeedBackTitle("A visit to Singapore");
        fb22.setFeedBackFrom("Fazel F");
        fb22.setFeedBackMsg("It is a calm and clean place with reasonable price and good service");
        fb22.setHotel(h8);
        fb22.setFeedbackRating(4);
        feedbackSessionLocal.createFeedback(fb22);

        Feedback fb23 = new Feedback();
        fb23.setFeedBackDate(format.parse("2019-03-03"));
        fb23.setFeedBackTitle("Good person");
        fb23.setFeedBackFrom("aashi jain ");
        fb23.setFeedBackMsg("John is very nice guy, he helped us lot in finding out the places how to travel everywhere.");
        fb23.setHotel(h8);
        fb23.setFeedbackRating(5);
        feedbackSessionLocal.createFeedback(fb23);

        Feedback fb24 = new Feedback();
        fb24.setFeedBackDate(format.parse("2019-03-03"));
        fb24.setFeedBackTitle("Between average and very good");
        fb24.setFeedBackFrom("John D");
        fb24.setFeedBackMsg("It's good but not VERY good. For the price it's ok. I was travelling alone but the room was still small with a tiny hanging space.");
        fb24.setHotel(h8);
        fb24.setFeedbackRating(3);
        feedbackSessionLocal.createFeedback(fb24);

        Feedback fb25 = new Feedback();
        fb25.setFeedBackDate(format.parse("2019-02-07"));
        fb25.setFeedBackTitle("Between average and very good");
        fb25.setFeedBackFrom("Caro L");
        fb25.setFeedBackMsg("Ok hotel. Simple room with good beds and shower");
        fb25.setHotel(h9);
        fb25.setFeedbackRating(3);
        feedbackSessionLocal.createFeedback(fb25);

        Feedback fb26 = new Feedback();
        fb26.setFeedBackDate(format.parse("2019-02-07"));
        fb26.setFeedBackTitle("Nice!");
        fb26.setFeedBackFrom("Stephan K");
        fb26.setFeedBackMsg("We enjoyed going out and meeting friends who stayed close to us in another hotel.");
        fb26.setHotel(h9);
        fb26.setFeedbackRating(4);
        feedbackSessionLocal.createFeedback(fb26);

        Feedback fb27 = new Feedback();
        fb27.setFeedBackDate(format.parse("2019-02-07"));
        fb27.setFeedBackTitle("Review");
        fb27.setFeedBackFrom("Felix K");
        fb27.setFeedBackMsg("Rooms are very small and no tables or chairs provided. Door lock not working. Every time someone has to come and open the room door. No dust bin in toilet");
        fb27.setHotel(h9);
        fb27.setFeedbackRating(2);
        feedbackSessionLocal.createFeedback(fb27);

        Feedback fb28 = new Feedback();
        fb28.setFeedBackDate(format.parse("2019-02-10"));
        fb28.setFeedBackTitle("poor service");
        fb28.setFeedBackFrom("Jane N");
        fb28.setFeedBackMsg("The rooms are ok and the location is strategic. But the service is so poor. I had to go up and down 3 times to get assistance from the front desk because the power in our room went off");
        fb28.setHotel(h10);
        fb28.setFeedbackRating(2);
        feedbackSessionLocal.createFeedback(fb28);

        Feedback fb29 = new Feedback();
        fb29.setFeedBackDate(format.parse("2019-02-13"));
        fb29.setFeedBackTitle("Nice and comfortable");
        fb29.setFeedBackFrom("Monica May");
        fb29.setFeedBackMsg("It was my first stay at the hotel .My family and I were greeted with very friendly receptionist");
        fb29.setHotel(h10);
        fb29.setFeedbackRating(4);
        feedbackSessionLocal.createFeedback(fb29);

        Feedback fb30 = new Feedback();
        fb30.setFeedBackDate(format.parse("2019-02-01"));
        fb30.setFeedBackTitle("Good hotel non noncense");
        fb30.setFeedBackFrom("Monica May");
        fb30.setFeedBackMsg("Clean hotel. Nice beds for a good sleep.");
        fb30.setHotel(h10);
        fb30.setFeedbackRating(4);
        feedbackSessionLocal.createFeedback(fb30);		

//*********************************************LAUNDRY TYPE************************************************        
        LaundryType lt1 = new LaundryType();
        lt1.setLaundryName("Top (Dry-Wash)");
        lt1.setPrice(15.0);
        laundrySessionLocal.createLaundryType(lt1);

        LaundryType lt2 = new LaundryType();
        lt2.setLaundryName("Top (Wash)");
        lt2.setPrice(12.0);
        laundrySessionLocal.createLaundryType(lt2);

        LaundryType lt3 = new LaundryType();
        lt3.setLaundryName("Bottom (Dry-Wash)");
        lt3.setPrice(15.0);
        laundrySessionLocal.createLaundryType(lt3);

        LaundryType lt4 = new LaundryType();
        lt4.setLaundryName("Bottom (Wash)");
        lt4.setPrice(12.0);
        laundrySessionLocal.createLaundryType(lt4);

        LaundryType lt5 = new LaundryType();
        lt5.setLaundryName("Inner Wear (Dry-Wash)");
        lt5.setPrice(8.0);
        laundrySessionLocal.createLaundryType(lt5);

        LaundryType lt6 = new LaundryType();
        lt6.setLaundryName("Inner Wear (Wash)");
        lt6.setPrice(6.0);
        laundrySessionLocal.createLaundryType(lt6);

        LaundryType lt7 = new LaundryType();
        lt7.setLaundryName("Outer Wear (Dry-Wash)");
        lt7.setPrice(20.0);
        laundrySessionLocal.createLaundryType(lt7);

        LaundryType lt8 = new LaundryType();
        lt8.setLaundryName("Outer Wear (Wash)");
        lt8.setPrice(18.0);
        laundrySessionLocal.createLaundryType(lt8);

        em.flush();
    }

    public void initializeKRGRoom() throws NoResultException {
        Hotel h1 = hotelSessionLocal.getHotelByName("Kent Ridge Grand");
        MinibarItem m1 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Potato Chips");
        MinibarItem m2 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Chocolate");
        MinibarItem m3 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Mineral Water");
        MinibarItem m4 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Coke");
        MinibarItem m5 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Wine");
        RoomFacility rf1 = roomFacilitySessionLocal.getRoomFacilityByName("Wifi");
        RoomFacility rf2 = roomFacilitySessionLocal.getRoomFacilityByName("Minibar");
        RoomFacility rf3 = roomFacilitySessionLocal.getRoomFacilityByName("Aircon");
        RoomFacility rf4 = roomFacilitySessionLocal.getRoomFacilityByName("Cable TV");
        RoomFacility rf5 = roomFacilitySessionLocal.getRoomFacilityByName("Telephone");
        RoomFacility rf6 = roomFacilitySessionLocal.getRoomFacilityByName("Water Heater");
        RoomFacility rf7 = roomFacilitySessionLocal.getRoomFacilityByName("Bathroom with Shower Head");
        RoomFacility rf8 = roomFacilitySessionLocal.getRoomFacilityByName("HairDryer");
        RoomFacility rf9 = roomFacilitySessionLocal.getRoomFacilityByName("Safe Deposit Box");
        RoomFacility rf10 = roomFacilitySessionLocal.getRoomFacilityByName("Water Kettle with complimentary teas/coffee");
        RoomFacility rf11 = roomFacilitySessionLocal.getRoomFacilityByName("Room with Windows");
        RoomFacility rf12 = roomFacilitySessionLocal.getRoomFacilityByName("Bathtub");
        RoomFacility rf13 = roomFacilitySessionLocal.getRoomFacilityByName("Mini Fridge");
        RoomFacility rf14 = roomFacilitySessionLocal.getRoomFacilityByName("Coffee Machine");
        RoomFacility rf15 = roomFacilitySessionLocal.getRoomFacilityByName("Balcony");
        RoomFacility rf16 = roomFacilitySessionLocal.getRoomFacilityByName("Microwave");
        RoomFacility rf17 = roomFacilitySessionLocal.getRoomFacilityByName("Gaming Console");
        RoomFacility rf18 = roomFacilitySessionLocal.getRoomFacilityByName("Entertainment System");
        RoomFacility rf19 = roomFacilitySessionLocal.getRoomFacilityByName("Blackout Curtains");
        RoomFacility rf20 = roomFacilitySessionLocal.getRoomFacilityByName("High Ceiling");
        RoomFacility rf21 = roomFacilitySessionLocal.getRoomFacilityByName("Jaccuzi");
        RoomFacility rf22 = roomFacilitySessionLocal.getRoomFacilityByName("Kitchen");

        FunctionRoom KRGFR1 = new FunctionRoom("KRGFR1", 20, "Available", 20000.00, h1);
        FunctionRoom KRGFR2 = new FunctionRoom("KRGFR2", 100, "Available", 100000.00, h1);
        FunctionRoom KRGFR3 = new FunctionRoom("KRGFR3", 50, "Available", 50000.00, h1);
        FunctionRoom KRGFR4 = new FunctionRoom("KRGFR4", 70, "Available", 70000.00, h1);
        FunctionRoom KRGFR5 = new FunctionRoom("KRGFR5", 80, "Available", 80000.00, h1);

        functionRoomSessionLocal.createFunctionRoom(KRGFR1);
        functionRoomSessionLocal.createFunctionRoom(KRGFR2);
        functionRoomSessionLocal.createFunctionRoom(KRGFR3);
        functionRoomSessionLocal.createFunctionRoom(KRGFR4);
        functionRoomSessionLocal.createFunctionRoom(KRGFR5);

        h1.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRGFR1"));
        h1.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRGFR2"));
        h1.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRGFR3"));
        h1.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRGFR4"));
        h1.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRGFR5"));

        Room KRGS1 = new Room("KRG_201", "201", "Standard", 2, "Available", h1);
        KRGS1.addRoomFacility(rf1);
        KRGS1.addRoomFacility(rf2);
        KRGS1.addRoomFacility(rf3);
        KRGS1.addRoomFacility(rf4);
        KRGS1.addRoomFacility(rf5);
        KRGS1.addRoomFacility(rf6);
        KRGS1.addRoomFacility(rf7);
        KRGS1.addRoomFacility(rf8);
        KRGS1.addRoomFacility(rf9);
        KRGS1.addRoomFacility(rf10);
        KRGS1.addMinibarItem(m1);
        KRGS1.addMinibarItem(m2);
        KRGS1.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS1);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_201"));

        Room KRGS2 = new Room("KRG_202", "202", "Standard", 2, "Available", h1);
        KRGS2.addRoomFacility(rf1);
        KRGS2.addRoomFacility(rf2);
        KRGS2.addRoomFacility(rf3);
        KRGS2.addRoomFacility(rf4);
        KRGS2.addRoomFacility(rf5);
        KRGS2.addRoomFacility(rf6);
        KRGS2.addRoomFacility(rf7);
        KRGS2.addRoomFacility(rf8);
        KRGS2.addRoomFacility(rf9);
        KRGS2.addRoomFacility(rf10);
        KRGS2.addMinibarItem(m1);
        KRGS2.addMinibarItem(m2);
        KRGS2.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS2);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_202"));

        Room KRGS3 = new Room("KRG_203", "203", "Standard", 2, "Occupied", h1);
        KRGS3.addRoomFacility(rf1);
        KRGS3.addRoomFacility(rf2);
        KRGS3.addRoomFacility(rf3);
        KRGS3.addRoomFacility(rf4);
        KRGS3.addRoomFacility(rf5);
        KRGS3.addRoomFacility(rf6);
        KRGS3.addRoomFacility(rf7);
        KRGS3.addRoomFacility(rf8);
        KRGS3.addRoomFacility(rf9);
        KRGS3.addRoomFacility(rf10);
        KRGS3.addMinibarItem(m1);
        KRGS3.addMinibarItem(m2);
        KRGS3.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS3);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_203"));

        Room KRGS4 = new Room("KRG_204", "204", "Standard", 2, "Unavailable", h1);
        KRGS4.addRoomFacility(rf1);
        KRGS4.addRoomFacility(rf2);
        KRGS4.addRoomFacility(rf3);
        KRGS4.addRoomFacility(rf4);
        KRGS4.addRoomFacility(rf5);
        KRGS4.addRoomFacility(rf6);
        KRGS4.addRoomFacility(rf7);
        KRGS4.addRoomFacility(rf8);
        KRGS4.addRoomFacility(rf9);
        KRGS4.addRoomFacility(rf10);
        KRGS4.addMinibarItem(m1);
        KRGS4.addMinibarItem(m2);
        KRGS4.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS4);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_204"));

        Room KRGS5 = new Room("KRG_205", "205", "Standard", 2, "Available", h1);
        KRGS5.addRoomFacility(rf1);
        KRGS5.addRoomFacility(rf2);
        KRGS5.addRoomFacility(rf3);
        KRGS5.addRoomFacility(rf4);
        KRGS5.addRoomFacility(rf5);
        KRGS5.addRoomFacility(rf6);
        KRGS5.addRoomFacility(rf7);
        KRGS5.addRoomFacility(rf8);
        KRGS5.addRoomFacility(rf9);
        KRGS5.addRoomFacility(rf10);
        KRGS5.addMinibarItem(m1);
        KRGS5.addMinibarItem(m2);
        KRGS5.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS5);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_205"));

        Room KRGS6 = new Room("KRG_206", "206", "Standard", 2, "Available", h1);
        KRGS6.addRoomFacility(rf1);
        KRGS6.addRoomFacility(rf2);
        KRGS6.addRoomFacility(rf3);
        KRGS6.addRoomFacility(rf4);
        KRGS6.addRoomFacility(rf5);
        KRGS6.addRoomFacility(rf6);
        KRGS6.addRoomFacility(rf7);
        KRGS6.addRoomFacility(rf8);
        KRGS6.addRoomFacility(rf9);
        KRGS6.addRoomFacility(rf10);
        KRGS6.addMinibarItem(m1);
        KRGS6.addMinibarItem(m2);
        KRGS6.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS6);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_206"));

        Room KRGS7 = new Room("KRG_207", "207", "Standard", 2, "Available", h1);
        KRGS7.addRoomFacility(rf1);
        KRGS7.addRoomFacility(rf2);
        KRGS7.addRoomFacility(rf3);
        KRGS7.addRoomFacility(rf4);
        KRGS7.addRoomFacility(rf5);
        KRGS7.addRoomFacility(rf6);
        KRGS7.addRoomFacility(rf7);
        KRGS7.addRoomFacility(rf8);
        KRGS7.addRoomFacility(rf9);
        KRGS7.addRoomFacility(rf10);
        KRGS7.addMinibarItem(m1);
        KRGS7.addMinibarItem(m2);
        KRGS7.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS7);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_207"));

        Room KRGS8 = new Room("KRG_208", "208", "Standard", 2, "Occupied", h1);
        KRGS8.addRoomFacility(rf1);
        KRGS8.addRoomFacility(rf2);
        KRGS8.addRoomFacility(rf3);
        KRGS8.addRoomFacility(rf4);
        KRGS8.addRoomFacility(rf5);
        KRGS8.addRoomFacility(rf6);
        KRGS8.addRoomFacility(rf7);
        KRGS8.addRoomFacility(rf8);
        KRGS8.addRoomFacility(rf9);
        KRGS8.addRoomFacility(rf10);
        KRGS8.addMinibarItem(m1);
        KRGS8.addMinibarItem(m2);
        KRGS8.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS8);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_208"));

        Room KRGS9 = new Room("KRG_209", "209", "Standard", 2, "Available", h1);
        KRGS9.addRoomFacility(rf1);
        KRGS9.addRoomFacility(rf2);
        KRGS9.addRoomFacility(rf3);
        KRGS9.addRoomFacility(rf4);
        KRGS9.addRoomFacility(rf5);
        KRGS9.addRoomFacility(rf6);
        KRGS9.addRoomFacility(rf7);
        KRGS9.addRoomFacility(rf8);
        KRGS9.addRoomFacility(rf9);
        KRGS9.addRoomFacility(rf10);
        KRGS9.addMinibarItem(m1);
        KRGS9.addMinibarItem(m2);
        KRGS9.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS9);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_209"));

        Room KRGS10 = new Room("KRG_210", "210", "Standard", 2, "Available", h1);
        KRGS10.addRoomFacility(rf1);
        KRGS10.addRoomFacility(rf2);
        KRGS10.addRoomFacility(rf3);
        KRGS10.addRoomFacility(rf4);
        KRGS10.addRoomFacility(rf5);
        KRGS10.addRoomFacility(rf6);
        KRGS10.addRoomFacility(rf7);
        KRGS10.addRoomFacility(rf8);
        KRGS10.addRoomFacility(rf9);
        KRGS10.addRoomFacility(rf10);
        KRGS10.addMinibarItem(m1);
        KRGS10.addMinibarItem(m2);
        KRGS10.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS10);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_210"));

        Room KRGS11 = new Room("KRG_301", "301", "Standard", 2, "Unavailable", h1);
        KRGS11.addRoomFacility(rf1);
        KRGS11.addRoomFacility(rf2);
        KRGS11.addRoomFacility(rf3);
        KRGS11.addRoomFacility(rf4);
        KRGS11.addRoomFacility(rf5);
        KRGS11.addRoomFacility(rf6);
        KRGS11.addRoomFacility(rf7);
        KRGS11.addRoomFacility(rf8);
        KRGS11.addRoomFacility(rf9);
        KRGS11.addRoomFacility(rf10);
        KRGS11.addMinibarItem(m1);
        KRGS11.addMinibarItem(m2);
        KRGS11.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS11);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_301"));

        Room KRGS12 = new Room("KRG_302", "302", "Standard", 2, "Available", h1);
        KRGS12.addRoomFacility(rf1);
        KRGS12.addRoomFacility(rf2);
        KRGS12.addRoomFacility(rf3);
        KRGS12.addRoomFacility(rf4);
        KRGS12.addRoomFacility(rf5);
        KRGS12.addRoomFacility(rf6);
        KRGS12.addRoomFacility(rf7);
        KRGS12.addRoomFacility(rf8);
        KRGS12.addRoomFacility(rf9);
        KRGS12.addRoomFacility(rf10);
        KRGS12.addMinibarItem(m1);
        KRGS12.addMinibarItem(m2);
        KRGS12.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS12);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_302"));

        Room KRGS13 = new Room("KRG_303", "303", "Standard", 2, "Occupied", h1);
        KRGS13.addRoomFacility(rf1);
        KRGS13.addRoomFacility(rf2);
        KRGS13.addRoomFacility(rf3);
        KRGS13.addRoomFacility(rf4);
        KRGS13.addRoomFacility(rf5);
        KRGS13.addRoomFacility(rf6);
        KRGS13.addRoomFacility(rf7);
        KRGS13.addRoomFacility(rf8);
        KRGS13.addRoomFacility(rf9);
        KRGS13.addRoomFacility(rf10);
        KRGS13.addMinibarItem(m1);
        KRGS13.addMinibarItem(m2);
        KRGS13.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS13);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_303"));

        Room KRGS14 = new Room("KRG_304", "304", "Standard", 2, "Available", h1);
        KRGS14.addRoomFacility(rf1);
        KRGS14.addRoomFacility(rf2);
        KRGS14.addRoomFacility(rf3);
        KRGS14.addRoomFacility(rf4);
        KRGS14.addRoomFacility(rf5);
        KRGS14.addRoomFacility(rf6);
        KRGS14.addRoomFacility(rf7);
        KRGS14.addRoomFacility(rf8);
        KRGS14.addRoomFacility(rf9);
        KRGS14.addRoomFacility(rf10);
        KRGS14.addMinibarItem(m1);
        KRGS14.addMinibarItem(m2);
        KRGS14.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS14);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_304"));

        Room KRGS15 = new Room("KRG_305", "305", "Standard", 2, "Unavailable", h1);
        KRGS15.addRoomFacility(rf1);
        KRGS15.addRoomFacility(rf2);
        KRGS15.addRoomFacility(rf3);
        KRGS15.addRoomFacility(rf4);
        KRGS15.addRoomFacility(rf5);
        KRGS15.addRoomFacility(rf6);
        KRGS15.addRoomFacility(rf7);
        KRGS15.addRoomFacility(rf8);
        KRGS15.addRoomFacility(rf9);
        KRGS15.addRoomFacility(rf10);
        KRGS15.addMinibarItem(m1);
        KRGS15.addMinibarItem(m2);
        KRGS15.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS15);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_305"));

        Room KRGS16 = new Room("KRG_306", "306", "Standard", 2, "Occupied", h1);
        KRGS16.addRoomFacility(rf1);
        KRGS16.addRoomFacility(rf2);
        KRGS16.addRoomFacility(rf3);
        KRGS16.addRoomFacility(rf4);
        KRGS16.addRoomFacility(rf5);
        KRGS16.addRoomFacility(rf6);
        KRGS16.addRoomFacility(rf7);
        KRGS16.addRoomFacility(rf8);
        KRGS16.addRoomFacility(rf9);
        KRGS16.addRoomFacility(rf10);
        KRGS16.addMinibarItem(m1);
        KRGS16.addMinibarItem(m2);
        KRGS16.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS16);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_306"));

        Room KRGS17 = new Room("KRG_307", "307", "Standard", 2, "Available", h1);
        KRGS17.addRoomFacility(rf1);
        KRGS17.addRoomFacility(rf2);
        KRGS17.addRoomFacility(rf3);
        KRGS17.addRoomFacility(rf4);
        KRGS17.addRoomFacility(rf5);
        KRGS17.addRoomFacility(rf6);
        KRGS17.addRoomFacility(rf7);
        KRGS17.addRoomFacility(rf8);
        KRGS17.addRoomFacility(rf9);
        KRGS17.addRoomFacility(rf10);
        KRGS17.addMinibarItem(m1);
        KRGS17.addMinibarItem(m2);
        KRGS17.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS17);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_307"));

        Room KRGS18 = new Room("KRG_308", "308", "Standard", 2, "Available", h1);
        KRGS18.addRoomFacility(rf1);
        KRGS18.addRoomFacility(rf2);
        KRGS18.addRoomFacility(rf3);
        KRGS18.addRoomFacility(rf4);
        KRGS18.addRoomFacility(rf5);
        KRGS18.addRoomFacility(rf6);
        KRGS18.addRoomFacility(rf7);
        KRGS18.addRoomFacility(rf8);
        KRGS18.addRoomFacility(rf9);
        KRGS18.addRoomFacility(rf10);
        KRGS18.addMinibarItem(m1);
        KRGS18.addMinibarItem(m2);
        KRGS18.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS18);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_308"));

        Room KRGS19 = new Room("KRG_309", "309", "Standard", 2, "Unavailable", h1);
        KRGS19.addRoomFacility(rf1);
        KRGS19.addRoomFacility(rf2);
        KRGS19.addRoomFacility(rf3);
        KRGS19.addRoomFacility(rf4);
        KRGS19.addRoomFacility(rf5);
        KRGS19.addRoomFacility(rf6);
        KRGS19.addRoomFacility(rf7);
        KRGS19.addRoomFacility(rf8);
        KRGS19.addRoomFacility(rf9);
        KRGS19.addRoomFacility(rf10);
        KRGS19.addMinibarItem(m1);
        KRGS19.addMinibarItem(m2);
        KRGS19.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS19);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_309"));

        Room KRGS20 = new Room("KRG_310", "310", "Standard", 2, "Occupied", h1);
        KRGS20.addRoomFacility(rf1);
        KRGS20.addRoomFacility(rf2);
        KRGS20.addRoomFacility(rf3);
        KRGS20.addRoomFacility(rf4);
        KRGS20.addRoomFacility(rf5);
        KRGS20.addRoomFacility(rf6);
        KRGS20.addRoomFacility(rf7);
        KRGS20.addRoomFacility(rf8);
        KRGS20.addRoomFacility(rf9);
        KRGS20.addRoomFacility(rf10);
        KRGS20.addMinibarItem(m1);
        KRGS20.addMinibarItem(m2);
        KRGS20.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS20);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_310"));

        Room KRGS21 = new Room("KRG_401", "401", "Standard", 2, "Available", h1);
        KRGS21.addRoomFacility(rf1);
        KRGS21.addRoomFacility(rf2);
        KRGS21.addRoomFacility(rf3);
        KRGS21.addRoomFacility(rf4);
        KRGS21.addRoomFacility(rf5);
        KRGS21.addRoomFacility(rf6);
        KRGS21.addRoomFacility(rf7);
        KRGS21.addRoomFacility(rf8);
        KRGS21.addRoomFacility(rf9);
        KRGS21.addRoomFacility(rf10);
        KRGS21.addMinibarItem(m1);
        KRGS21.addMinibarItem(m2);
        KRGS21.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS21);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_401"));

        Room KRGS22 = new Room("KRG_402", "402", "Standard", 2, "Unavailable", h1);
        KRGS22.addRoomFacility(rf1);
        KRGS22.addRoomFacility(rf2);
        KRGS22.addRoomFacility(rf3);
        KRGS22.addRoomFacility(rf4);
        KRGS22.addRoomFacility(rf5);
        KRGS22.addRoomFacility(rf6);
        KRGS22.addRoomFacility(rf7);
        KRGS22.addRoomFacility(rf8);
        KRGS22.addRoomFacility(rf9);
        KRGS22.addRoomFacility(rf10);
        KRGS22.addMinibarItem(m1);
        KRGS22.addMinibarItem(m2);
        KRGS22.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS22);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_402"));

        Room KRGS23 = new Room("KRG_403", "403", "Standard", 2, "Available", h1);
        KRGS23.addRoomFacility(rf1);
        KRGS23.addRoomFacility(rf2);
        KRGS23.addRoomFacility(rf3);
        KRGS23.addRoomFacility(rf4);
        KRGS23.addRoomFacility(rf5);
        KRGS23.addRoomFacility(rf6);
        KRGS23.addRoomFacility(rf7);
        KRGS23.addRoomFacility(rf8);
        KRGS23.addRoomFacility(rf9);
        KRGS23.addRoomFacility(rf10);
        KRGS23.addMinibarItem(m1);
        KRGS23.addMinibarItem(m2);
        KRGS23.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS23);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_403"));

        Room KRGS24 = new Room("KRG_404", "404", "Standard", 2, "Occupied", h1);
        KRGS24.addRoomFacility(rf1);
        KRGS24.addRoomFacility(rf2);
        KRGS24.addRoomFacility(rf3);
        KRGS24.addRoomFacility(rf4);
        KRGS24.addRoomFacility(rf5);
        KRGS24.addRoomFacility(rf6);
        KRGS24.addRoomFacility(rf7);
        KRGS24.addRoomFacility(rf8);
        KRGS24.addRoomFacility(rf9);
        KRGS24.addRoomFacility(rf10);
        KRGS24.addMinibarItem(m1);
        KRGS24.addMinibarItem(m2);
        KRGS24.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS24);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_404"));

        Room KRGS25 = new Room("KRG_405", "405", "Standard", 2, "Unavailable", h1);
        KRGS25.addRoomFacility(rf1);
        KRGS25.addRoomFacility(rf2);
        KRGS25.addRoomFacility(rf3);
        KRGS25.addRoomFacility(rf4);
        KRGS25.addRoomFacility(rf5);
        KRGS25.addRoomFacility(rf6);
        KRGS25.addRoomFacility(rf7);
        KRGS25.addRoomFacility(rf8);
        KRGS25.addRoomFacility(rf9);
        KRGS25.addRoomFacility(rf10);
        KRGS25.addMinibarItem(m1);
        KRGS25.addMinibarItem(m2);
        KRGS25.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS25);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_405"));

        Room KRGS26 = new Room("KRG_406", "406", "Standard", 2, "Unavailable", h1);
        KRGS26.addRoomFacility(rf1);
        KRGS26.addRoomFacility(rf2);
        KRGS26.addRoomFacility(rf3);
        KRGS26.addRoomFacility(rf4);
        KRGS26.addRoomFacility(rf5);
        KRGS26.addRoomFacility(rf6);
        KRGS26.addRoomFacility(rf7);
        KRGS26.addRoomFacility(rf8);
        KRGS26.addRoomFacility(rf9);
        KRGS26.addRoomFacility(rf10);
        KRGS26.addMinibarItem(m1);
        KRGS26.addMinibarItem(m2);
        KRGS26.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS26);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_406"));

        Room KRGS27 = new Room("KRG_407", "407", "Standard", 2, "Occupied", h1);
        KRGS27.addRoomFacility(rf1);
        KRGS27.addRoomFacility(rf2);
        KRGS27.addRoomFacility(rf3);
        KRGS27.addRoomFacility(rf4);
        KRGS27.addRoomFacility(rf5);
        KRGS27.addRoomFacility(rf6);
        KRGS27.addRoomFacility(rf7);
        KRGS27.addRoomFacility(rf8);
        KRGS27.addRoomFacility(rf9);
        KRGS27.addRoomFacility(rf10);
        KRGS27.addMinibarItem(m1);
        KRGS27.addMinibarItem(m2);
        KRGS27.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS27);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_407"));

        Room KRGS28 = new Room("KRG_408", "408", "Standard", 2, "Unavailable", h1);
        KRGS28.addRoomFacility(rf1);
        KRGS28.addRoomFacility(rf2);
        KRGS28.addRoomFacility(rf3);
        KRGS28.addRoomFacility(rf4);
        KRGS28.addRoomFacility(rf5);
        KRGS28.addRoomFacility(rf6);
        KRGS28.addRoomFacility(rf7);
        KRGS28.addRoomFacility(rf8);
        KRGS28.addRoomFacility(rf9);
        KRGS28.addRoomFacility(rf10);
        KRGS28.addMinibarItem(m1);
        KRGS28.addMinibarItem(m2);
        KRGS28.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS28);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_408"));

        Room KRGS29 = new Room("KRG_409", "409", "Standard", 2, "Available", h1);
        KRGS29.addRoomFacility(rf1);
        KRGS29.addRoomFacility(rf2);
        KRGS29.addRoomFacility(rf3);
        KRGS29.addRoomFacility(rf4);
        KRGS29.addRoomFacility(rf5);
        KRGS29.addRoomFacility(rf6);
        KRGS29.addRoomFacility(rf7);
        KRGS29.addRoomFacility(rf8);
        KRGS29.addRoomFacility(rf9);
        KRGS29.addRoomFacility(rf10);
        KRGS29.addMinibarItem(m1);
        KRGS29.addMinibarItem(m2);
        KRGS29.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS29);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_409"));

        Room KRGS30 = new Room("KRG_410", "410", "Standard", 2, "Unavailable", h1);
        KRGS30.addRoomFacility(rf1);
        KRGS30.addRoomFacility(rf2);
        KRGS30.addRoomFacility(rf3);
        KRGS30.addRoomFacility(rf4);
        KRGS30.addRoomFacility(rf5);
        KRGS30.addRoomFacility(rf6);
        KRGS30.addRoomFacility(rf7);
        KRGS30.addRoomFacility(rf8);
        KRGS30.addRoomFacility(rf9);
        KRGS30.addRoomFacility(rf10);
        KRGS30.addMinibarItem(m1);
        KRGS30.addMinibarItem(m2);
        KRGS30.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS30);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_410"));

        Room KRGS31 = new Room("KRG_501", "501", "Standard", 2, "Available", h1);
        KRGS31.addRoomFacility(rf1);
        KRGS31.addRoomFacility(rf2);
        KRGS31.addRoomFacility(rf3);
        KRGS31.addRoomFacility(rf4);
        KRGS31.addRoomFacility(rf5);
        KRGS31.addRoomFacility(rf6);
        KRGS31.addRoomFacility(rf7);
        KRGS31.addRoomFacility(rf8);
        KRGS31.addRoomFacility(rf9);
        KRGS31.addRoomFacility(rf10);
        KRGS31.addMinibarItem(m1);
        KRGS31.addMinibarItem(m2);
        KRGS31.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS31);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_501"));

        Room KRGS32 = new Room("KRG_502", "502", "Standard", 2, "Occupied", h1);
        KRGS32.addRoomFacility(rf1);
        KRGS32.addRoomFacility(rf2);
        KRGS32.addRoomFacility(rf3);
        KRGS32.addRoomFacility(rf4);
        KRGS32.addRoomFacility(rf5);
        KRGS32.addRoomFacility(rf6);
        KRGS32.addRoomFacility(rf7);
        KRGS32.addRoomFacility(rf8);
        KRGS32.addRoomFacility(rf9);
        KRGS32.addRoomFacility(rf10);
        KRGS32.addMinibarItem(m1);
        KRGS32.addMinibarItem(m2);
        KRGS32.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS32);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_502"));

        Room KRGS33 = new Room("KRG_503", "503", "Standard", 2, "Available", h1);
        KRGS33.addRoomFacility(rf1);
        KRGS33.addRoomFacility(rf2);
        KRGS33.addRoomFacility(rf3);
        KRGS33.addRoomFacility(rf4);
        KRGS33.addRoomFacility(rf5);
        KRGS33.addRoomFacility(rf6);
        KRGS33.addRoomFacility(rf7);
        KRGS33.addRoomFacility(rf8);
        KRGS33.addRoomFacility(rf9);
        KRGS33.addRoomFacility(rf10);
        KRGS33.addMinibarItem(m1);
        KRGS33.addMinibarItem(m2);
        KRGS33.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS33);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_503"));

        Room KRGS34 = new Room("KRG_504", "504", "Standard", 2, "Available", h1);
        KRGS34.addRoomFacility(rf1);
        KRGS34.addRoomFacility(rf2);
        KRGS34.addRoomFacility(rf3);
        KRGS34.addRoomFacility(rf4);
        KRGS34.addRoomFacility(rf5);
        KRGS34.addRoomFacility(rf6);
        KRGS34.addRoomFacility(rf7);
        KRGS34.addRoomFacility(rf8);
        KRGS34.addRoomFacility(rf9);
        KRGS34.addRoomFacility(rf10);
        KRGS34.addMinibarItem(m1);
        KRGS34.addMinibarItem(m2);
        KRGS34.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS34);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_504"));

        Room KRGS35 = new Room("KRG_505", "505", "Standard", 2, "Unavailable", h1);
        KRGS35.addRoomFacility(rf1);
        KRGS35.addRoomFacility(rf2);
        KRGS35.addRoomFacility(rf3);
        KRGS35.addRoomFacility(rf4);
        KRGS35.addRoomFacility(rf5);
        KRGS35.addRoomFacility(rf6);
        KRGS35.addRoomFacility(rf7);
        KRGS35.addRoomFacility(rf8);
        KRGS35.addRoomFacility(rf9);
        KRGS35.addRoomFacility(rf10);
        KRGS35.addMinibarItem(m1);
        KRGS35.addMinibarItem(m2);
        KRGS35.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS35);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_505"));

        Room KRGS36 = new Room("KRG_506", "506", "Standard", 2, "Available", h1);
        KRGS36.addRoomFacility(rf1);
        KRGS36.addRoomFacility(rf2);
        KRGS36.addRoomFacility(rf3);
        KRGS36.addRoomFacility(rf4);
        KRGS36.addRoomFacility(rf5);
        KRGS36.addRoomFacility(rf6);
        KRGS36.addRoomFacility(rf7);
        KRGS36.addRoomFacility(rf8);
        KRGS36.addRoomFacility(rf9);
        KRGS36.addRoomFacility(rf10);
        KRGS36.addMinibarItem(m1);
        KRGS36.addMinibarItem(m2);
        KRGS36.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS36);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_506"));

        Room KRGS37 = new Room("KRG_507", "507", "Standard", 2, "Occupied", h1);
        KRGS37.addRoomFacility(rf1);
        KRGS37.addRoomFacility(rf2);
        KRGS37.addRoomFacility(rf3);
        KRGS37.addRoomFacility(rf4);
        KRGS37.addRoomFacility(rf5);
        KRGS37.addRoomFacility(rf6);
        KRGS37.addRoomFacility(rf7);
        KRGS37.addRoomFacility(rf8);
        KRGS37.addRoomFacility(rf9);
        KRGS37.addRoomFacility(rf10);
        KRGS37.addMinibarItem(m1);
        KRGS37.addMinibarItem(m2);
        KRGS37.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS37);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_507"));

        Room KRGS38 = new Room("KRG_508", "508", "Standard", 2, "Unavailable", h1);
        KRGS38.addRoomFacility(rf1);
        KRGS38.addRoomFacility(rf2);
        KRGS38.addRoomFacility(rf3);
        KRGS38.addRoomFacility(rf4);
        KRGS38.addRoomFacility(rf5);
        KRGS38.addRoomFacility(rf6);
        KRGS38.addRoomFacility(rf7);
        KRGS38.addRoomFacility(rf8);
        KRGS38.addRoomFacility(rf9);
        KRGS38.addRoomFacility(rf10);
        KRGS38.addMinibarItem(m1);
        KRGS38.addMinibarItem(m2);
        KRGS38.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS38);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_508"));

        Room KRGS39 = new Room("KRG_509", "509", "Standard", 2, "Available", h1);
        KRGS39.addRoomFacility(rf1);
        KRGS39.addRoomFacility(rf2);
        KRGS39.addRoomFacility(rf3);
        KRGS39.addRoomFacility(rf4);
        KRGS39.addRoomFacility(rf5);
        KRGS39.addRoomFacility(rf6);
        KRGS39.addRoomFacility(rf7);
        KRGS39.addRoomFacility(rf8);
        KRGS39.addRoomFacility(rf9);
        KRGS39.addRoomFacility(rf10);
        KRGS39.addMinibarItem(m1);
        KRGS39.addMinibarItem(m2);
        KRGS39.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS39);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_509"));

        Room KRGS40 = new Room("KRG_510", "510", "Standard", 2, "Available", h1);
        KRGS40.addRoomFacility(rf1);
        KRGS40.addRoomFacility(rf2);
        KRGS40.addRoomFacility(rf3);
        KRGS40.addRoomFacility(rf4);
        KRGS40.addRoomFacility(rf5);
        KRGS40.addRoomFacility(rf6);
        KRGS40.addRoomFacility(rf7);
        KRGS40.addRoomFacility(rf8);
        KRGS40.addRoomFacility(rf9);
        KRGS40.addRoomFacility(rf10);
        KRGS40.addMinibarItem(m1);
        KRGS40.addMinibarItem(m2);
        KRGS40.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS40);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_510"));

        Room KRGS41 = new Room("KRG_601", "601", "Standard", 2, "Occupied", h1);
        KRGS41.addRoomFacility(rf1);
        KRGS41.addRoomFacility(rf2);
        KRGS41.addRoomFacility(rf3);
        KRGS41.addRoomFacility(rf4);
        KRGS41.addRoomFacility(rf5);
        KRGS41.addRoomFacility(rf6);
        KRGS41.addRoomFacility(rf7);
        KRGS41.addRoomFacility(rf8);
        KRGS41.addRoomFacility(rf9);
        KRGS41.addRoomFacility(rf10);
        KRGS41.addMinibarItem(m1);
        KRGS41.addMinibarItem(m2);
        KRGS41.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS41);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_601"));

        Room KRGS42 = new Room("KRG_602", "602", "Standard", 2, "Available", h1);
        KRGS42.addRoomFacility(rf1);
        KRGS42.addRoomFacility(rf2);
        KRGS42.addRoomFacility(rf3);
        KRGS42.addRoomFacility(rf4);
        KRGS42.addRoomFacility(rf5);
        KRGS42.addRoomFacility(rf6);
        KRGS42.addRoomFacility(rf7);
        KRGS42.addRoomFacility(rf8);
        KRGS42.addRoomFacility(rf9);
        KRGS42.addRoomFacility(rf10);
        KRGS42.addMinibarItem(m1);
        KRGS42.addMinibarItem(m2);
        KRGS42.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS42);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_602"));

        Room KRGS43 = new Room("KRG_603", "603", "Standard", 2, "Available", h1);
        KRGS43.addRoomFacility(rf1);
        KRGS43.addRoomFacility(rf2);
        KRGS43.addRoomFacility(rf3);
        KRGS43.addRoomFacility(rf4);
        KRGS43.addRoomFacility(rf5);
        KRGS43.addRoomFacility(rf6);
        KRGS43.addRoomFacility(rf7);
        KRGS43.addRoomFacility(rf8);
        KRGS43.addRoomFacility(rf9);
        KRGS43.addRoomFacility(rf10);
        KRGS43.addMinibarItem(m1);
        KRGS43.addMinibarItem(m2);
        KRGS43.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS43);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_603"));

        Room KRGS44 = new Room("KRG_604", "604", "Standard", 2, "Occupied", h1);
        KRGS44.addRoomFacility(rf1);
        KRGS44.addRoomFacility(rf2);
        KRGS44.addRoomFacility(rf3);
        KRGS44.addRoomFacility(rf4);
        KRGS44.addRoomFacility(rf5);
        KRGS44.addRoomFacility(rf6);
        KRGS44.addRoomFacility(rf7);
        KRGS44.addRoomFacility(rf8);
        KRGS44.addRoomFacility(rf9);
        KRGS44.addRoomFacility(rf10);
        KRGS44.addMinibarItem(m1);
        KRGS44.addMinibarItem(m2);
        KRGS44.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS44);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_604"));

        Room KRGS45 = new Room("KRG_605", "605", "Standard", 2, "Available", h1);
        KRGS45.addRoomFacility(rf1);
        KRGS45.addRoomFacility(rf2);
        KRGS45.addRoomFacility(rf3);
        KRGS45.addRoomFacility(rf4);
        KRGS45.addRoomFacility(rf5);
        KRGS45.addRoomFacility(rf6);
        KRGS45.addRoomFacility(rf7);
        KRGS45.addRoomFacility(rf8);
        KRGS45.addRoomFacility(rf9);
        KRGS45.addRoomFacility(rf10);
        KRGS45.addMinibarItem(m1);
        KRGS45.addMinibarItem(m2);
        KRGS45.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS45);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_605"));

        Room KRGS46 = new Room("KRG_606", "606", "Standard", 2, "Unavailable", h1);
        KRGS46.addRoomFacility(rf1);
        KRGS46.addRoomFacility(rf2);
        KRGS46.addRoomFacility(rf3);
        KRGS46.addRoomFacility(rf4);
        KRGS46.addRoomFacility(rf5);
        KRGS46.addRoomFacility(rf6);
        KRGS46.addRoomFacility(rf7);
        KRGS46.addRoomFacility(rf8);
        KRGS46.addRoomFacility(rf9);
        KRGS46.addRoomFacility(rf10);
        KRGS46.addMinibarItem(m1);
        KRGS46.addMinibarItem(m2);
        KRGS46.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS46);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_606"));

        Room KRGS47 = new Room("KRG_607", "607", "Standard", 2, "Available", h1);
        KRGS47.addRoomFacility(rf1);
        KRGS47.addRoomFacility(rf2);
        KRGS47.addRoomFacility(rf3);
        KRGS47.addRoomFacility(rf4);
        KRGS47.addRoomFacility(rf5);
        KRGS47.addRoomFacility(rf6);
        KRGS47.addRoomFacility(rf7);
        KRGS47.addRoomFacility(rf8);
        KRGS47.addRoomFacility(rf9);
        KRGS47.addRoomFacility(rf10);
        KRGS47.addMinibarItem(m1);
        KRGS47.addMinibarItem(m2);
        KRGS47.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS47);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_607"));

        Room KRGS48 = new Room("KRG_608", "608", "Standard", 2, "Available", h1);
        KRGS48.addRoomFacility(rf1);
        KRGS48.addRoomFacility(rf2);
        KRGS48.addRoomFacility(rf3);
        KRGS48.addRoomFacility(rf4);
        KRGS48.addRoomFacility(rf5);
        KRGS48.addRoomFacility(rf6);
        KRGS48.addRoomFacility(rf7);
        KRGS48.addRoomFacility(rf8);
        KRGS48.addRoomFacility(rf9);
        KRGS48.addRoomFacility(rf10);
        KRGS48.addMinibarItem(m1);
        KRGS48.addMinibarItem(m2);
        KRGS48.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS48);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_608"));

        Room KRGS49 = new Room("KRG_609", "609", "Standard", 2, "Occupied", h1);
        KRGS49.addRoomFacility(rf1);
        KRGS49.addRoomFacility(rf2);
        KRGS49.addRoomFacility(rf3);
        KRGS49.addRoomFacility(rf4);
        KRGS49.addRoomFacility(rf5);
        KRGS49.addRoomFacility(rf6);
        KRGS49.addRoomFacility(rf7);
        KRGS49.addRoomFacility(rf8);
        KRGS49.addRoomFacility(rf9);
        KRGS49.addRoomFacility(rf10);
        KRGS49.addMinibarItem(m1);
        KRGS49.addMinibarItem(m2);
        KRGS49.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS49);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_609"));

        Room KRGS50 = new Room("KRG_610", "610", "Standard", 2, "Available", h1);
        KRGS50.addRoomFacility(rf1);
        KRGS50.addRoomFacility(rf2);
        KRGS50.addRoomFacility(rf3);
        KRGS50.addRoomFacility(rf4);
        KRGS50.addRoomFacility(rf5);
        KRGS50.addRoomFacility(rf6);
        KRGS50.addRoomFacility(rf7);
        KRGS50.addRoomFacility(rf8);
        KRGS50.addRoomFacility(rf9);
        KRGS50.addRoomFacility(rf10);
        KRGS50.addMinibarItem(m1);
        KRGS50.addMinibarItem(m2);
        KRGS50.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRGS50);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_610"));

        Room KRG_1001 = new Room("KRG_1001", "1001", "Deluxe", 3, "Available", h1);

        KRG_1001.addRoomFacility(rf1);
        KRG_1001.addRoomFacility(rf2);
        KRG_1001.addRoomFacility(rf3);
        KRG_1001.addRoomFacility(rf4);
        KRG_1001.addRoomFacility(rf5);
        KRG_1001.addRoomFacility(rf6);
        KRG_1001.addRoomFacility(rf7);
        KRG_1001.addRoomFacility(rf8);
        KRG_1001.addRoomFacility(rf9);
        KRG_1001.addRoomFacility(rf10);
        KRG_1001.addRoomFacility(rf11);
        KRG_1001.addRoomFacility(rf12);
        KRG_1001.addRoomFacility(rf13);

        KRG_1001.addMinibarItem(m1);
        KRG_1001.addMinibarItem(m2);
        KRG_1001.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_1001);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_1001"));

        Room KRG_1002 = new Room("KRG_1002", "1002", "Deluxe", 3, "Available", h1);

        KRG_1002.addRoomFacility(rf1);
        KRG_1002.addRoomFacility(rf2);
        KRG_1002.addRoomFacility(rf3);
        KRG_1002.addRoomFacility(rf4);
        KRG_1002.addRoomFacility(rf5);
        KRG_1002.addRoomFacility(rf6);
        KRG_1002.addRoomFacility(rf7);
        KRG_1002.addRoomFacility(rf8);
        KRG_1002.addRoomFacility(rf9);
        KRG_1002.addRoomFacility(rf10);
        KRG_1002.addRoomFacility(rf11);
        KRG_1002.addRoomFacility(rf12);
        KRG_1002.addRoomFacility(rf13);

        KRG_1002.addMinibarItem(m1);
        KRG_1002.addMinibarItem(m2);
        KRG_1002.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_1002);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_1002"));

        Room KRG_1003 = new Room("KRG_1003", "1003", "Deluxe", 3, "Occupied", h1);

        KRG_1003.addRoomFacility(rf1);
        KRG_1003.addRoomFacility(rf2);
        KRG_1003.addRoomFacility(rf3);
        KRG_1003.addRoomFacility(rf4);
        KRG_1003.addRoomFacility(rf5);
        KRG_1003.addRoomFacility(rf6);
        KRG_1003.addRoomFacility(rf7);
        KRG_1003.addRoomFacility(rf8);
        KRG_1003.addRoomFacility(rf9);
        KRG_1003.addRoomFacility(rf10);
        KRG_1003.addRoomFacility(rf11);
        KRG_1003.addRoomFacility(rf12);
        KRG_1003.addRoomFacility(rf13);

        KRG_1003.addMinibarItem(m1);
        KRG_1003.addMinibarItem(m2);
        KRG_1003.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_1003);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_1003"));

        Room KRG_1004 = new Room("KRG_1004", "1004", "Deluxe", 3, "Available", h1);

        KRG_1004.addRoomFacility(rf1);
        KRG_1004.addRoomFacility(rf2);
        KRG_1004.addRoomFacility(rf3);
        KRG_1004.addRoomFacility(rf4);
        KRG_1004.addRoomFacility(rf5);
        KRG_1004.addRoomFacility(rf6);
        KRG_1004.addRoomFacility(rf7);
        KRG_1004.addRoomFacility(rf8);
        KRG_1004.addRoomFacility(rf9);
        KRG_1004.addRoomFacility(rf10);
        KRG_1004.addRoomFacility(rf11);
        KRG_1004.addRoomFacility(rf12);
        KRG_1004.addRoomFacility(rf13);

        KRG_1004.addMinibarItem(m1);
        KRG_1004.addMinibarItem(m2);
        KRG_1004.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_1004);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_1004"));

        Room KRG_1005 = new Room("KRG_1005", "1005", "Deluxe", 3, "Available", h1);

        KRG_1005.addRoomFacility(rf1);
        KRG_1005.addRoomFacility(rf2);
        KRG_1005.addRoomFacility(rf3);
        KRG_1005.addRoomFacility(rf4);
        KRG_1005.addRoomFacility(rf5);
        KRG_1005.addRoomFacility(rf6);
        KRG_1005.addRoomFacility(rf7);
        KRG_1005.addRoomFacility(rf8);
        KRG_1005.addRoomFacility(rf9);
        KRG_1005.addRoomFacility(rf10);
        KRG_1005.addRoomFacility(rf11);
        KRG_1005.addRoomFacility(rf12);
        KRG_1005.addRoomFacility(rf13);

        KRG_1005.addMinibarItem(m1);
        KRG_1005.addMinibarItem(m2);
        KRG_1005.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_1005);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_1005"));

        Room KRG_1006 = new Room("KRG_1006", "1006", "Deluxe", 3, "Unavailable", h1);

        KRG_1006.addRoomFacility(rf1);
        KRG_1006.addRoomFacility(rf2);
        KRG_1006.addRoomFacility(rf3);
        KRG_1006.addRoomFacility(rf4);
        KRG_1006.addRoomFacility(rf5);
        KRG_1006.addRoomFacility(rf6);
        KRG_1006.addRoomFacility(rf7);
        KRG_1006.addRoomFacility(rf8);
        KRG_1006.addRoomFacility(rf9);
        KRG_1006.addRoomFacility(rf10);
        KRG_1006.addRoomFacility(rf11);
        KRG_1006.addRoomFacility(rf12);
        KRG_1006.addRoomFacility(rf13);

        KRG_1006.addMinibarItem(m1);
        KRG_1006.addMinibarItem(m2);
        KRG_1006.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_1006);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_1006"));

        Room KRG_1101 = new Room("KRG_1101", "1101", "Deluxe", 3, "Occupied", h1);

        KRG_1101.addRoomFacility(rf1);
        KRG_1101.addRoomFacility(rf2);
        KRG_1101.addRoomFacility(rf3);
        KRG_1101.addRoomFacility(rf4);
        KRG_1101.addRoomFacility(rf5);
        KRG_1101.addRoomFacility(rf6);
        KRG_1101.addRoomFacility(rf7);
        KRG_1101.addRoomFacility(rf8);
        KRG_1101.addRoomFacility(rf9);
        KRG_1101.addRoomFacility(rf10);
        KRG_1101.addRoomFacility(rf11);
        KRG_1101.addRoomFacility(rf12);
        KRG_1101.addRoomFacility(rf13);

        KRG_1101.addMinibarItem(m1);
        KRG_1101.addMinibarItem(m2);
        KRG_1101.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_1101);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_1101"));

        Room KRG_1102 = new Room("KRG_1102", "1102", "Deluxe", 3, "Available", h1);

        KRG_1102.addRoomFacility(rf1);
        KRG_1102.addRoomFacility(rf2);
        KRG_1102.addRoomFacility(rf3);
        KRG_1102.addRoomFacility(rf4);
        KRG_1102.addRoomFacility(rf5);
        KRG_1102.addRoomFacility(rf6);
        KRG_1102.addRoomFacility(rf7);
        KRG_1102.addRoomFacility(rf8);
        KRG_1102.addRoomFacility(rf9);
        KRG_1102.addRoomFacility(rf10);
        KRG_1102.addRoomFacility(rf11);
        KRG_1102.addRoomFacility(rf12);
        KRG_1102.addRoomFacility(rf13);

        KRG_1102.addMinibarItem(m1);
        KRG_1102.addMinibarItem(m2);
        KRG_1102.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_1102);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_1102"));

        Room KRG_1103 = new Room("KRG_1103", "1103", "Deluxe", 3, "Available", h1);

        KRG_1103.addRoomFacility(rf1);
        KRG_1103.addRoomFacility(rf2);
        KRG_1103.addRoomFacility(rf3);
        KRG_1103.addRoomFacility(rf4);
        KRG_1103.addRoomFacility(rf5);
        KRG_1103.addRoomFacility(rf6);
        KRG_1103.addRoomFacility(rf7);
        KRG_1103.addRoomFacility(rf8);
        KRG_1103.addRoomFacility(rf9);
        KRG_1103.addRoomFacility(rf10);
        KRG_1103.addRoomFacility(rf11);
        KRG_1103.addRoomFacility(rf12);
        KRG_1103.addRoomFacility(rf13);

        KRG_1103.addMinibarItem(m1);
        KRG_1103.addMinibarItem(m2);
        KRG_1103.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_1103);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_1103"));

        Room KRG_1104 = new Room("KRG_1104", "1104", "Deluxe", 3, "Unavailable", h1);

        KRG_1104.addRoomFacility(rf1);
        KRG_1104.addRoomFacility(rf2);
        KRG_1104.addRoomFacility(rf3);
        KRG_1104.addRoomFacility(rf4);
        KRG_1104.addRoomFacility(rf5);
        KRG_1104.addRoomFacility(rf6);
        KRG_1104.addRoomFacility(rf7);
        KRG_1104.addRoomFacility(rf8);
        KRG_1104.addRoomFacility(rf9);
        KRG_1104.addRoomFacility(rf10);
        KRG_1104.addRoomFacility(rf11);
        KRG_1104.addRoomFacility(rf12);
        KRG_1104.addRoomFacility(rf13);

        KRG_1104.addMinibarItem(m1);
        KRG_1104.addMinibarItem(m2);
        KRG_1104.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_1104);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_1104"));

        Room KRG_1105 = new Room("KRG_1105", "1105", "Deluxe", 3, "Occupied", h1);

        KRG_1105.addRoomFacility(rf1);
        KRG_1105.addRoomFacility(rf2);
        KRG_1105.addRoomFacility(rf3);
        KRG_1105.addRoomFacility(rf4);
        KRG_1105.addRoomFacility(rf5);
        KRG_1105.addRoomFacility(rf6);
        KRG_1105.addRoomFacility(rf7);
        KRG_1105.addRoomFacility(rf8);
        KRG_1105.addRoomFacility(rf9);
        KRG_1105.addRoomFacility(rf10);
        KRG_1105.addRoomFacility(rf11);
        KRG_1105.addRoomFacility(rf12);
        KRG_1105.addRoomFacility(rf13);

        KRG_1105.addMinibarItem(m1);
        KRG_1105.addMinibarItem(m2);
        KRG_1105.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_1105);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_1105"));

        Room KRG_1106 = new Room("KRG_1106", "1106", "Deluxe", 3, "Available", h1);

        KRG_1106.addRoomFacility(rf1);
        KRG_1106.addRoomFacility(rf2);
        KRG_1106.addRoomFacility(rf3);
        KRG_1106.addRoomFacility(rf4);
        KRG_1106.addRoomFacility(rf5);
        KRG_1106.addRoomFacility(rf6);
        KRG_1106.addRoomFacility(rf7);
        KRG_1106.addRoomFacility(rf8);
        KRG_1106.addRoomFacility(rf9);
        KRG_1106.addRoomFacility(rf10);
        KRG_1106.addRoomFacility(rf11);
        KRG_1106.addRoomFacility(rf12);
        KRG_1106.addRoomFacility(rf13);

        KRG_1106.addMinibarItem(m1);
        KRG_1106.addMinibarItem(m2);
        KRG_1106.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_1106);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_1106"));

        Room KRG_701 = new Room("KRG_701", "701", "Premium", 4, "Available", h1);

        KRG_701.addRoomFacility(rf1);
        KRG_701.addRoomFacility(rf2);
        KRG_701.addRoomFacility(rf3);
        KRG_701.addRoomFacility(rf4);
        KRG_701.addRoomFacility(rf5);
        KRG_701.addRoomFacility(rf6);
        KRG_701.addRoomFacility(rf7);
        KRG_701.addRoomFacility(rf8);
        KRG_701.addRoomFacility(rf9);
        KRG_701.addRoomFacility(rf10);
        KRG_701.addRoomFacility(rf11);
        KRG_701.addRoomFacility(rf12);
        KRG_701.addRoomFacility(rf13);
        KRG_701.addRoomFacility(rf14);
        KRG_701.addRoomFacility(rf15);

        KRG_701.addMinibarItem(m1);
        KRG_701.addMinibarItem(m2);
        KRG_701.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_701);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_701"));

        Room KRG_702 = new Room("KRG_702", "702", "Premium", 4, "Occupied", h1);

        KRG_702.addRoomFacility(rf1);
        KRG_702.addRoomFacility(rf2);
        KRG_702.addRoomFacility(rf3);
        KRG_702.addRoomFacility(rf4);
        KRG_702.addRoomFacility(rf5);
        KRG_702.addRoomFacility(rf6);
        KRG_702.addRoomFacility(rf7);
        KRG_702.addRoomFacility(rf8);
        KRG_702.addRoomFacility(rf9);
        KRG_702.addRoomFacility(rf10);
        KRG_702.addRoomFacility(rf11);
        KRG_702.addRoomFacility(rf12);
        KRG_702.addRoomFacility(rf13);
        KRG_702.addRoomFacility(rf14);
        KRG_702.addRoomFacility(rf15);

        KRG_702.addMinibarItem(m1);
        KRG_702.addMinibarItem(m2);
        KRG_702.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_702);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_702"));

        Room KRG_703 = new Room("KRG_703", "703", "Premium", 4, "Unavailable", h1);

        KRG_703.addRoomFacility(rf1);
        KRG_703.addRoomFacility(rf2);
        KRG_703.addRoomFacility(rf3);
        KRG_703.addRoomFacility(rf4);
        KRG_703.addRoomFacility(rf5);
        KRG_703.addRoomFacility(rf6);
        KRG_703.addRoomFacility(rf7);
        KRG_703.addRoomFacility(rf8);
        KRG_703.addRoomFacility(rf9);
        KRG_703.addRoomFacility(rf10);
        KRG_703.addRoomFacility(rf11);
        KRG_703.addRoomFacility(rf12);
        KRG_703.addRoomFacility(rf13);
        KRG_703.addRoomFacility(rf14);
        KRG_703.addRoomFacility(rf15);

        KRG_703.addMinibarItem(m1);
        KRG_703.addMinibarItem(m2);
        KRG_703.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_703);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_703"));

        Room KRG_704 = new Room("KRG_704", "704", "Premium", 4, "Available", h1);

        KRG_704.addRoomFacility(rf1);
        KRG_704.addRoomFacility(rf2);
        KRG_704.addRoomFacility(rf3);
        KRG_704.addRoomFacility(rf4);
        KRG_704.addRoomFacility(rf5);
        KRG_704.addRoomFacility(rf6);
        KRG_704.addRoomFacility(rf7);
        KRG_704.addRoomFacility(rf8);
        KRG_704.addRoomFacility(rf9);
        KRG_704.addRoomFacility(rf10);
        KRG_704.addRoomFacility(rf11);
        KRG_704.addRoomFacility(rf12);
        KRG_704.addRoomFacility(rf13);
        KRG_704.addRoomFacility(rf14);
        KRG_704.addRoomFacility(rf15);

        KRG_704.addMinibarItem(m1);
        KRG_704.addMinibarItem(m2);
        KRG_704.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_704);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_704"));

        Room KRG_705 = new Room("KRG_705", "705", "Premium", 4, "Occupied", h1);

        KRG_705.addRoomFacility(rf1);
        KRG_705.addRoomFacility(rf2);
        KRG_705.addRoomFacility(rf3);
        KRG_705.addRoomFacility(rf4);
        KRG_705.addRoomFacility(rf5);
        KRG_705.addRoomFacility(rf6);
        KRG_705.addRoomFacility(rf7);
        KRG_705.addRoomFacility(rf8);
        KRG_705.addRoomFacility(rf9);
        KRG_705.addRoomFacility(rf10);
        KRG_705.addRoomFacility(rf11);
        KRG_705.addRoomFacility(rf12);
        KRG_705.addRoomFacility(rf13);
        KRG_705.addRoomFacility(rf14);
        KRG_705.addRoomFacility(rf15);

        KRG_705.addMinibarItem(m1);
        KRG_705.addMinibarItem(m2);
        KRG_705.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_705);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_705"));

        Room KRG_706 = new Room("KRG_706", "706", "Premium", 4, "Available", h1);

        KRG_706.addRoomFacility(rf1);
        KRG_706.addRoomFacility(rf2);
        KRG_706.addRoomFacility(rf3);
        KRG_706.addRoomFacility(rf4);
        KRG_706.addRoomFacility(rf5);
        KRG_706.addRoomFacility(rf6);
        KRG_706.addRoomFacility(rf7);
        KRG_706.addRoomFacility(rf8);
        KRG_706.addRoomFacility(rf9);
        KRG_706.addRoomFacility(rf10);
        KRG_706.addRoomFacility(rf11);
        KRG_706.addRoomFacility(rf12);
        KRG_706.addRoomFacility(rf13);
        KRG_706.addRoomFacility(rf14);
        KRG_706.addRoomFacility(rf15);

        KRG_706.addMinibarItem(m1);
        KRG_706.addMinibarItem(m2);
        KRG_706.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_706);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_706"));

        Room KRG_707 = new Room("KRG_707", "707", "Premium", 4, "Available", h1);

        KRG_707.addRoomFacility(rf1);
        KRG_707.addRoomFacility(rf2);
        KRG_707.addRoomFacility(rf3);
        KRG_707.addRoomFacility(rf4);
        KRG_707.addRoomFacility(rf5);
        KRG_707.addRoomFacility(rf6);
        KRG_707.addRoomFacility(rf7);
        KRG_707.addRoomFacility(rf8);
        KRG_707.addRoomFacility(rf9);
        KRG_707.addRoomFacility(rf10);
        KRG_707.addRoomFacility(rf11);
        KRG_707.addRoomFacility(rf12);
        KRG_707.addRoomFacility(rf13);
        KRG_707.addRoomFacility(rf14);
        KRG_707.addRoomFacility(rf15);

        KRG_707.addMinibarItem(m1);
        KRG_707.addMinibarItem(m2);
        KRG_707.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_707);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_707"));

        Room KRG_708 = new Room("KRG_708", "708", "Premium", 4, "Available", h1);

        KRG_708.addRoomFacility(rf1);
        KRG_708.addRoomFacility(rf2);
        KRG_708.addRoomFacility(rf3);
        KRG_708.addRoomFacility(rf4);
        KRG_708.addRoomFacility(rf5);
        KRG_708.addRoomFacility(rf6);
        KRG_708.addRoomFacility(rf7);
        KRG_708.addRoomFacility(rf8);
        KRG_708.addRoomFacility(rf9);
        KRG_708.addRoomFacility(rf10);
        KRG_708.addRoomFacility(rf11);
        KRG_708.addRoomFacility(rf12);
        KRG_708.addRoomFacility(rf13);
        KRG_708.addRoomFacility(rf14);
        KRG_708.addRoomFacility(rf15);

        KRG_708.addMinibarItem(m1);
        KRG_708.addMinibarItem(m2);
        KRG_708.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_708);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_708"));

        Room KRG_801 = new Room("KRG_801", "801", "Premium", 4, "Unavailable", h1);

        KRG_801.addRoomFacility(rf1);
        KRG_801.addRoomFacility(rf2);
        KRG_801.addRoomFacility(rf3);
        KRG_801.addRoomFacility(rf4);
        KRG_801.addRoomFacility(rf5);
        KRG_801.addRoomFacility(rf6);
        KRG_801.addRoomFacility(rf7);
        KRG_801.addRoomFacility(rf8);
        KRG_801.addRoomFacility(rf9);
        KRG_801.addRoomFacility(rf10);
        KRG_801.addRoomFacility(rf11);
        KRG_801.addRoomFacility(rf12);
        KRG_801.addRoomFacility(rf13);
        KRG_801.addRoomFacility(rf14);
        KRG_801.addRoomFacility(rf15);

        KRG_801.addMinibarItem(m1);
        KRG_801.addMinibarItem(m2);
        KRG_801.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_801);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_801"));

        Room KRG_802 = new Room("KRG_802", "802", "Premium", 4, "Occupied", h1);

        KRG_802.addRoomFacility(rf1);
        KRG_802.addRoomFacility(rf2);
        KRG_802.addRoomFacility(rf3);
        KRG_802.addRoomFacility(rf4);
        KRG_802.addRoomFacility(rf5);
        KRG_802.addRoomFacility(rf6);
        KRG_802.addRoomFacility(rf7);
        KRG_802.addRoomFacility(rf8);
        KRG_802.addRoomFacility(rf9);
        KRG_802.addRoomFacility(rf10);
        KRG_802.addRoomFacility(rf11);
        KRG_802.addRoomFacility(rf12);
        KRG_802.addRoomFacility(rf13);
        KRG_802.addRoomFacility(rf14);
        KRG_802.addRoomFacility(rf15);

        KRG_802.addMinibarItem(m1);
        KRG_802.addMinibarItem(m2);
        KRG_802.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_802);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_802"));

        Room KRG_803 = new Room("KRG_803", "803", "Premium", 4, "Available", h1);

        KRG_803.addRoomFacility(rf1);
        KRG_803.addRoomFacility(rf2);
        KRG_803.addRoomFacility(rf3);
        KRG_803.addRoomFacility(rf4);
        KRG_803.addRoomFacility(rf5);
        KRG_803.addRoomFacility(rf6);
        KRG_803.addRoomFacility(rf7);
        KRG_803.addRoomFacility(rf8);
        KRG_803.addRoomFacility(rf9);
        KRG_803.addRoomFacility(rf10);
        KRG_803.addRoomFacility(rf11);
        KRG_803.addRoomFacility(rf12);
        KRG_803.addRoomFacility(rf13);
        KRG_803.addRoomFacility(rf14);
        KRG_803.addRoomFacility(rf15);

        KRG_803.addMinibarItem(m1);
        KRG_803.addMinibarItem(m2);
        KRG_803.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_803);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_803"));

        Room KRG_804 = new Room("KRG_804", "804", "Premium", 4, "Occupied", h1);

        KRG_804.addRoomFacility(rf1);
        KRG_804.addRoomFacility(rf2);
        KRG_804.addRoomFacility(rf3);
        KRG_804.addRoomFacility(rf4);
        KRG_804.addRoomFacility(rf5);
        KRG_804.addRoomFacility(rf6);
        KRG_804.addRoomFacility(rf7);
        KRG_804.addRoomFacility(rf8);
        KRG_804.addRoomFacility(rf9);
        KRG_804.addRoomFacility(rf10);
        KRG_804.addRoomFacility(rf11);
        KRG_804.addRoomFacility(rf12);
        KRG_804.addRoomFacility(rf13);
        KRG_804.addRoomFacility(rf14);
        KRG_804.addRoomFacility(rf15);

        KRG_804.addMinibarItem(m1);
        KRG_804.addMinibarItem(m2);
        KRG_804.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_804);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_804"));

        Room KRG_805 = new Room("KRG_805", "805", "Premium", 4, "Available", h1);

        KRG_805.addRoomFacility(rf1);
        KRG_805.addRoomFacility(rf2);
        KRG_805.addRoomFacility(rf3);
        KRG_805.addRoomFacility(rf4);
        KRG_805.addRoomFacility(rf5);
        KRG_805.addRoomFacility(rf6);
        KRG_805.addRoomFacility(rf7);
        KRG_805.addRoomFacility(rf8);
        KRG_805.addRoomFacility(rf9);
        KRG_805.addRoomFacility(rf10);
        KRG_805.addRoomFacility(rf11);
        KRG_805.addRoomFacility(rf12);
        KRG_805.addRoomFacility(rf13);
        KRG_805.addRoomFacility(rf14);
        KRG_805.addRoomFacility(rf15);

        KRG_805.addMinibarItem(m1);
        KRG_805.addMinibarItem(m2);
        KRG_805.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_805);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_805"));

        Room KRG_806 = new Room("KRG_806", "806", "Premium", 4, "Unavailable", h1);

        KRG_806.addRoomFacility(rf1);
        KRG_806.addRoomFacility(rf2);
        KRG_806.addRoomFacility(rf3);
        KRG_806.addRoomFacility(rf4);
        KRG_806.addRoomFacility(rf5);
        KRG_806.addRoomFacility(rf6);
        KRG_806.addRoomFacility(rf7);
        KRG_806.addRoomFacility(rf8);
        KRG_806.addRoomFacility(rf9);
        KRG_806.addRoomFacility(rf10);
        KRG_806.addRoomFacility(rf11);
        KRG_806.addRoomFacility(rf12);
        KRG_806.addRoomFacility(rf13);
        KRG_806.addRoomFacility(rf14);
        KRG_806.addRoomFacility(rf15);

        KRG_806.addMinibarItem(m1);
        KRG_806.addMinibarItem(m2);
        KRG_806.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_806);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_806"));

        Room KRG_807 = new Room("KRG_807", "807", "Premium", 4, "Available", h1);

        KRG_807.addRoomFacility(rf1);
        KRG_807.addRoomFacility(rf2);
        KRG_807.addRoomFacility(rf3);
        KRG_807.addRoomFacility(rf4);
        KRG_807.addRoomFacility(rf5);
        KRG_807.addRoomFacility(rf6);
        KRG_807.addRoomFacility(rf7);
        KRG_807.addRoomFacility(rf8);
        KRG_807.addRoomFacility(rf9);
        KRG_807.addRoomFacility(rf10);
        KRG_807.addRoomFacility(rf11);
        KRG_807.addRoomFacility(rf12);
        KRG_807.addRoomFacility(rf13);
        KRG_807.addRoomFacility(rf14);
        KRG_807.addRoomFacility(rf15);

        KRG_807.addMinibarItem(m1);
        KRG_807.addMinibarItem(m2);
        KRG_807.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_807);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_807"));

        Room KRG_808 = new Room("KRG_808", "808", "Premium", 4, "Occupied", h1);

        KRG_808.addRoomFacility(rf1);
        KRG_808.addRoomFacility(rf2);
        KRG_808.addRoomFacility(rf3);
        KRG_808.addRoomFacility(rf4);
        KRG_808.addRoomFacility(rf5);
        KRG_808.addRoomFacility(rf6);
        KRG_808.addRoomFacility(rf7);
        KRG_808.addRoomFacility(rf8);
        KRG_808.addRoomFacility(rf9);
        KRG_808.addRoomFacility(rf10);
        KRG_808.addRoomFacility(rf11);
        KRG_808.addRoomFacility(rf12);
        KRG_808.addRoomFacility(rf13);
        KRG_808.addRoomFacility(rf14);
        KRG_808.addRoomFacility(rf15);

        KRG_808.addMinibarItem(m1);
        KRG_808.addMinibarItem(m2);
        KRG_808.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_808);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_808"));

        Room KRG_901 = new Room("KRG_901", "901", "Premium", 4, "Available", h1);

        KRG_901.addRoomFacility(rf1);
        KRG_901.addRoomFacility(rf2);
        KRG_901.addRoomFacility(rf3);
        KRG_901.addRoomFacility(rf4);
        KRG_901.addRoomFacility(rf5);
        KRG_901.addRoomFacility(rf6);
        KRG_901.addRoomFacility(rf7);
        KRG_901.addRoomFacility(rf8);
        KRG_901.addRoomFacility(rf9);
        KRG_901.addRoomFacility(rf10);
        KRG_901.addRoomFacility(rf11);
        KRG_901.addRoomFacility(rf12);
        KRG_901.addRoomFacility(rf13);
        KRG_901.addRoomFacility(rf14);
        KRG_901.addRoomFacility(rf15);

        KRG_901.addMinibarItem(m1);
        KRG_901.addMinibarItem(m2);
        KRG_901.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_901);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_901"));

        Room KRG_902 = new Room("KRG_902", "902", "Premium", 4, "Available", h1);

        KRG_902.addRoomFacility(rf1);
        KRG_902.addRoomFacility(rf2);
        KRG_902.addRoomFacility(rf3);
        KRG_902.addRoomFacility(rf4);
        KRG_902.addRoomFacility(rf5);
        KRG_902.addRoomFacility(rf6);
        KRG_902.addRoomFacility(rf7);
        KRG_902.addRoomFacility(rf8);
        KRG_902.addRoomFacility(rf9);
        KRG_902.addRoomFacility(rf10);
        KRG_902.addRoomFacility(rf11);
        KRG_902.addRoomFacility(rf12);
        KRG_902.addRoomFacility(rf13);
        KRG_902.addRoomFacility(rf14);
        KRG_902.addRoomFacility(rf15);

        KRG_902.addMinibarItem(m1);
        KRG_902.addMinibarItem(m2);
        KRG_902.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_902);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_902"));

        Room KRG_903 = new Room("KRG_903", "903", "Premium", 4, "Occupied", h1);

        KRG_903.addRoomFacility(rf1);
        KRG_903.addRoomFacility(rf2);
        KRG_903.addRoomFacility(rf3);
        KRG_903.addRoomFacility(rf4);
        KRG_903.addRoomFacility(rf5);
        KRG_903.addRoomFacility(rf6);
        KRG_903.addRoomFacility(rf7);
        KRG_903.addRoomFacility(rf8);
        KRG_903.addRoomFacility(rf9);
        KRG_903.addRoomFacility(rf10);
        KRG_903.addRoomFacility(rf11);
        KRG_903.addRoomFacility(rf12);
        KRG_903.addRoomFacility(rf13);
        KRG_903.addRoomFacility(rf14);
        KRG_903.addRoomFacility(rf15);

        KRG_903.addMinibarItem(m1);
        KRG_903.addMinibarItem(m2);
        KRG_903.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_903);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_903"));

        Room KRG_904 = new Room("KRG_904", "904", "Premium", 4, "Unavailable", h1);

        KRG_904.addRoomFacility(rf1);
        KRG_904.addRoomFacility(rf2);
        KRG_904.addRoomFacility(rf3);
        KRG_904.addRoomFacility(rf4);
        KRG_904.addRoomFacility(rf5);
        KRG_904.addRoomFacility(rf6);
        KRG_904.addRoomFacility(rf7);
        KRG_904.addRoomFacility(rf8);
        KRG_904.addRoomFacility(rf9);
        KRG_904.addRoomFacility(rf10);
        KRG_904.addRoomFacility(rf11);
        KRG_904.addRoomFacility(rf12);
        KRG_904.addRoomFacility(rf13);
        KRG_904.addRoomFacility(rf14);
        KRG_904.addRoomFacility(rf15);

        KRG_904.addMinibarItem(m1);
        KRG_904.addMinibarItem(m2);
        KRG_904.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_904);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_904"));

        Room KRG_905 = new Room("KRG_905", "905", "Premium", 4, "Available", h1);

        KRG_905.addRoomFacility(rf1);
        KRG_905.addRoomFacility(rf2);
        KRG_905.addRoomFacility(rf3);
        KRG_905.addRoomFacility(rf4);
        KRG_905.addRoomFacility(rf5);
        KRG_905.addRoomFacility(rf6);
        KRG_905.addRoomFacility(rf7);
        KRG_905.addRoomFacility(rf8);
        KRG_905.addRoomFacility(rf9);
        KRG_905.addRoomFacility(rf10);
        KRG_905.addRoomFacility(rf11);
        KRG_905.addRoomFacility(rf12);
        KRG_905.addRoomFacility(rf13);
        KRG_905.addRoomFacility(rf14);
        KRG_905.addRoomFacility(rf15);

        KRG_905.addMinibarItem(m1);
        KRG_905.addMinibarItem(m2);
        KRG_905.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_905);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_905"));

        Room KRG_906 = new Room("KRG_906", "906", "Premium", 4, "Available", h1);

        KRG_906.addRoomFacility(rf1);
        KRG_906.addRoomFacility(rf2);
        KRG_906.addRoomFacility(rf3);
        KRG_906.addRoomFacility(rf4);
        KRG_906.addRoomFacility(rf5);
        KRG_906.addRoomFacility(rf6);
        KRG_906.addRoomFacility(rf7);
        KRG_906.addRoomFacility(rf8);
        KRG_906.addRoomFacility(rf9);
        KRG_906.addRoomFacility(rf10);
        KRG_906.addRoomFacility(rf11);
        KRG_906.addRoomFacility(rf12);
        KRG_906.addRoomFacility(rf13);
        KRG_906.addRoomFacility(rf14);
        KRG_906.addRoomFacility(rf15);

        KRG_906.addMinibarItem(m1);
        KRG_906.addMinibarItem(m2);
        KRG_906.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_906);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_906"));

        Room KRG_907 = new Room("KRG_907", "907", "Premium", 4, "Available", h1);

        KRG_907.addRoomFacility(rf1);
        KRG_907.addRoomFacility(rf2);
        KRG_907.addRoomFacility(rf3);
        KRG_907.addRoomFacility(rf4);
        KRG_907.addRoomFacility(rf5);
        KRG_907.addRoomFacility(rf6);
        KRG_907.addRoomFacility(rf7);
        KRG_907.addRoomFacility(rf8);
        KRG_907.addRoomFacility(rf9);
        KRG_907.addRoomFacility(rf10);
        KRG_907.addRoomFacility(rf11);
        KRG_907.addRoomFacility(rf12);
        KRG_907.addRoomFacility(rf13);
        KRG_907.addRoomFacility(rf14);
        KRG_907.addRoomFacility(rf15);

        KRG_907.addMinibarItem(m1);
        KRG_907.addMinibarItem(m2);
        KRG_907.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_907);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_907"));

        Room KRG_908 = new Room("KRG_908", "908", "Premium", 4, "Occupied", h1);

        KRG_908.addRoomFacility(rf1);
        KRG_908.addRoomFacility(rf2);
        KRG_908.addRoomFacility(rf3);
        KRG_908.addRoomFacility(rf4);
        KRG_908.addRoomFacility(rf5);
        KRG_908.addRoomFacility(rf6);
        KRG_908.addRoomFacility(rf7);
        KRG_908.addRoomFacility(rf8);
        KRG_908.addRoomFacility(rf9);
        KRG_908.addRoomFacility(rf10);
        KRG_908.addRoomFacility(rf11);
        KRG_908.addRoomFacility(rf12);
        KRG_908.addRoomFacility(rf13);
        KRG_908.addRoomFacility(rf14);
        KRG_908.addRoomFacility(rf15);

        KRG_908.addMinibarItem(m1);
        KRG_908.addMinibarItem(m2);
        KRG_908.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRG_908);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_908"));

        Room KRG_1201 = new Room("KRG_1201", "1201", "Suite", 4, "Available", h1);

        KRG_1201.addRoomFacility(rf1);
        KRG_1201.addRoomFacility(rf2);
        KRG_1201.addRoomFacility(rf3);
        KRG_1201.addRoomFacility(rf4);
        KRG_1201.addRoomFacility(rf5);
        KRG_1201.addRoomFacility(rf6);
        KRG_1201.addRoomFacility(rf7);
        KRG_1201.addRoomFacility(rf8);
        KRG_1201.addRoomFacility(rf9);
        KRG_1201.addRoomFacility(rf10);
        KRG_1201.addRoomFacility(rf11);
        KRG_1201.addRoomFacility(rf12);
        KRG_1201.addRoomFacility(rf13);
        KRG_1201.addRoomFacility(rf14);
        KRG_1201.addRoomFacility(rf15);
        KRG_1201.addRoomFacility(rf16);
        KRG_1201.addRoomFacility(rf17);
        KRG_1201.addRoomFacility(rf18);
        KRG_1201.addRoomFacility(rf19);

        KRG_1201.addMinibarItem(m1);
        KRG_1201.addMinibarItem(m2);
        KRG_1201.addMinibarItem(m3);
        KRG_1201.addMinibarItem(m4);
        KRG_1201.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRG_1201);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_1201"));

        Room KRG_1202 = new Room("KRG_1202", "1202", "Suite", 4, "Occupied", h1);

        KRG_1202.addRoomFacility(rf1);
        KRG_1202.addRoomFacility(rf2);
        KRG_1202.addRoomFacility(rf3);
        KRG_1202.addRoomFacility(rf4);
        KRG_1202.addRoomFacility(rf5);
        KRG_1202.addRoomFacility(rf6);
        KRG_1202.addRoomFacility(rf7);
        KRG_1202.addRoomFacility(rf8);
        KRG_1202.addRoomFacility(rf9);
        KRG_1202.addRoomFacility(rf10);
        KRG_1202.addRoomFacility(rf11);
        KRG_1202.addRoomFacility(rf12);
        KRG_1202.addRoomFacility(rf13);
        KRG_1202.addRoomFacility(rf14);
        KRG_1202.addRoomFacility(rf15);
        KRG_1202.addRoomFacility(rf16);
        KRG_1202.addRoomFacility(rf17);
        KRG_1202.addRoomFacility(rf18);
        KRG_1202.addRoomFacility(rf19);

        KRG_1202.addMinibarItem(m1);
        KRG_1202.addMinibarItem(m2);
        KRG_1202.addMinibarItem(m3);
        KRG_1202.addMinibarItem(m4);
        KRG_1202.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRG_1202);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_1202"));

        Room KRG_1203 = new Room("KRG_1203", "1203", "Suite", 4, "Unavailable", h1);

        KRG_1203.addRoomFacility(rf1);
        KRG_1203.addRoomFacility(rf2);
        KRG_1203.addRoomFacility(rf3);
        KRG_1203.addRoomFacility(rf4);
        KRG_1203.addRoomFacility(rf5);
        KRG_1203.addRoomFacility(rf6);
        KRG_1203.addRoomFacility(rf7);
        KRG_1203.addRoomFacility(rf8);
        KRG_1203.addRoomFacility(rf9);
        KRG_1203.addRoomFacility(rf10);
        KRG_1203.addRoomFacility(rf11);
        KRG_1203.addRoomFacility(rf12);
        KRG_1203.addRoomFacility(rf13);
        KRG_1203.addRoomFacility(rf14);
        KRG_1203.addRoomFacility(rf15);
        KRG_1203.addRoomFacility(rf16);
        KRG_1203.addRoomFacility(rf17);
        KRG_1203.addRoomFacility(rf18);
        KRG_1203.addRoomFacility(rf19);

        KRG_1203.addMinibarItem(m1);
        KRG_1203.addMinibarItem(m2);
        KRG_1203.addMinibarItem(m3);
        KRG_1203.addMinibarItem(m4);
        KRG_1203.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRG_1203);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_1203"));

        Room KRG_1204 = new Room("KRG_1204", "1204", "Suite", 4, "Available", h1);

        KRG_1204.addRoomFacility(rf1);
        KRG_1204.addRoomFacility(rf2);
        KRG_1204.addRoomFacility(rf3);
        KRG_1204.addRoomFacility(rf4);
        KRG_1204.addRoomFacility(rf5);
        KRG_1204.addRoomFacility(rf6);
        KRG_1204.addRoomFacility(rf7);
        KRG_1204.addRoomFacility(rf8);
        KRG_1204.addRoomFacility(rf9);
        KRG_1204.addRoomFacility(rf10);
        KRG_1204.addRoomFacility(rf11);
        KRG_1204.addRoomFacility(rf12);
        KRG_1204.addRoomFacility(rf13);
        KRG_1204.addRoomFacility(rf14);
        KRG_1204.addRoomFacility(rf15);
        KRG_1204.addRoomFacility(rf16);
        KRG_1204.addRoomFacility(rf17);
        KRG_1204.addRoomFacility(rf18);
        KRG_1204.addRoomFacility(rf19);

        KRG_1204.addMinibarItem(m1);
        KRG_1204.addMinibarItem(m2);
        KRG_1204.addMinibarItem(m3);
        KRG_1204.addMinibarItem(m4);
        KRG_1204.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRG_1204);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_1204"));

        Room KRG_1205 = new Room("KRG_1205", "1205", "Suite", 4, "Occupied", h1);

        KRG_1205.addRoomFacility(rf1);
        KRG_1205.addRoomFacility(rf2);
        KRG_1205.addRoomFacility(rf3);
        KRG_1205.addRoomFacility(rf4);
        KRG_1205.addRoomFacility(rf5);
        KRG_1205.addRoomFacility(rf6);
        KRG_1205.addRoomFacility(rf7);
        KRG_1205.addRoomFacility(rf8);
        KRG_1205.addRoomFacility(rf9);
        KRG_1205.addRoomFacility(rf10);
        KRG_1205.addRoomFacility(rf11);
        KRG_1205.addRoomFacility(rf12);
        KRG_1205.addRoomFacility(rf13);
        KRG_1205.addRoomFacility(rf14);
        KRG_1205.addRoomFacility(rf15);
        KRG_1205.addRoomFacility(rf16);
        KRG_1205.addRoomFacility(rf17);
        KRG_1205.addRoomFacility(rf18);
        KRG_1205.addRoomFacility(rf19);

        KRG_1205.addMinibarItem(m1);
        KRG_1205.addMinibarItem(m2);
        KRG_1205.addMinibarItem(m3);
        KRG_1205.addMinibarItem(m4);
        KRG_1205.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRG_1205);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_1205"));

        Room KRG_1301 = new Room("KRG_1301", "1301", "Suite", 4, "Unavailable", h1);

        KRG_1301.addRoomFacility(rf1);
        KRG_1301.addRoomFacility(rf2);
        KRG_1301.addRoomFacility(rf3);
        KRG_1301.addRoomFacility(rf4);
        KRG_1301.addRoomFacility(rf5);
        KRG_1301.addRoomFacility(rf6);
        KRG_1301.addRoomFacility(rf7);
        KRG_1301.addRoomFacility(rf8);
        KRG_1301.addRoomFacility(rf9);
        KRG_1301.addRoomFacility(rf10);
        KRG_1301.addRoomFacility(rf11);
        KRG_1301.addRoomFacility(rf12);
        KRG_1301.addRoomFacility(rf13);
        KRG_1301.addRoomFacility(rf14);
        KRG_1301.addRoomFacility(rf15);
        KRG_1301.addRoomFacility(rf16);
        KRG_1301.addRoomFacility(rf17);
        KRG_1301.addRoomFacility(rf18);
        KRG_1301.addRoomFacility(rf19);

        KRG_1301.addMinibarItem(m1);
        KRG_1301.addMinibarItem(m2);
        KRG_1301.addMinibarItem(m3);
        KRG_1301.addMinibarItem(m4);
        KRG_1301.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRG_1301);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_1301"));

        Room KRG_1302 = new Room("KRG_1302", "1302", "Suite", 4, "Available", h1);

        KRG_1302.addRoomFacility(rf1);
        KRG_1302.addRoomFacility(rf2);
        KRG_1302.addRoomFacility(rf3);
        KRG_1302.addRoomFacility(rf4);
        KRG_1302.addRoomFacility(rf5);
        KRG_1302.addRoomFacility(rf6);
        KRG_1302.addRoomFacility(rf7);
        KRG_1302.addRoomFacility(rf8);
        KRG_1302.addRoomFacility(rf9);
        KRG_1302.addRoomFacility(rf10);
        KRG_1302.addRoomFacility(rf11);
        KRG_1302.addRoomFacility(rf12);
        KRG_1302.addRoomFacility(rf13);
        KRG_1302.addRoomFacility(rf14);
        KRG_1302.addRoomFacility(rf15);
        KRG_1302.addRoomFacility(rf16);
        KRG_1302.addRoomFacility(rf17);
        KRG_1302.addRoomFacility(rf18);
        KRG_1302.addRoomFacility(rf19);

        KRG_1302.addMinibarItem(m1);
        KRG_1302.addMinibarItem(m2);
        KRG_1302.addMinibarItem(m3);
        KRG_1302.addMinibarItem(m4);
        KRG_1302.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRG_1302);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_1302"));

        Room KRG_1303 = new Room("KRG_1303", "1303", "Suite", 4, "Available", h1);

        KRG_1303.addRoomFacility(rf1);
        KRG_1303.addRoomFacility(rf2);
        KRG_1303.addRoomFacility(rf3);
        KRG_1303.addRoomFacility(rf4);
        KRG_1303.addRoomFacility(rf5);
        KRG_1303.addRoomFacility(rf6);
        KRG_1303.addRoomFacility(rf7);
        KRG_1303.addRoomFacility(rf8);
        KRG_1303.addRoomFacility(rf9);
        KRG_1303.addRoomFacility(rf10);
        KRG_1303.addRoomFacility(rf11);
        KRG_1303.addRoomFacility(rf12);
        KRG_1303.addRoomFacility(rf13);
        KRG_1303.addRoomFacility(rf14);
        KRG_1303.addRoomFacility(rf15);
        KRG_1303.addRoomFacility(rf16);
        KRG_1303.addRoomFacility(rf17);
        KRG_1303.addRoomFacility(rf18);
        KRG_1303.addRoomFacility(rf19);

        KRG_1303.addMinibarItem(m1);
        KRG_1303.addMinibarItem(m2);
        KRG_1303.addMinibarItem(m3);
        KRG_1303.addMinibarItem(m4);
        KRG_1303.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRG_1303);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_1303"));

        Room KRG_1304 = new Room("KRG_1304", "1304", "Suite", 4, "Unavailable", h1);

        KRG_1304.addRoomFacility(rf1);
        KRG_1304.addRoomFacility(rf2);
        KRG_1304.addRoomFacility(rf3);
        KRG_1304.addRoomFacility(rf4);
        KRG_1304.addRoomFacility(rf5);
        KRG_1304.addRoomFacility(rf6);
        KRG_1304.addRoomFacility(rf7);
        KRG_1304.addRoomFacility(rf8);
        KRG_1304.addRoomFacility(rf9);
        KRG_1304.addRoomFacility(rf10);
        KRG_1304.addRoomFacility(rf11);
        KRG_1304.addRoomFacility(rf12);
        KRG_1304.addRoomFacility(rf13);
        KRG_1304.addRoomFacility(rf14);
        KRG_1304.addRoomFacility(rf15);
        KRG_1304.addRoomFacility(rf16);
        KRG_1304.addRoomFacility(rf17);
        KRG_1304.addRoomFacility(rf18);
        KRG_1304.addRoomFacility(rf19);

        KRG_1304.addMinibarItem(m1);
        KRG_1304.addMinibarItem(m2);
        KRG_1304.addMinibarItem(m3);
        KRG_1304.addMinibarItem(m4);
        KRG_1304.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRG_1304);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_1304"));

        Room KRG_1305 = new Room("KRG_1305", "1305", "Suite", 4, "Available", h1);

        KRG_1305.addRoomFacility(rf1);
        KRG_1305.addRoomFacility(rf2);
        KRG_1305.addRoomFacility(rf3);
        KRG_1305.addRoomFacility(rf4);
        KRG_1305.addRoomFacility(rf5);
        KRG_1305.addRoomFacility(rf6);
        KRG_1305.addRoomFacility(rf7);
        KRG_1305.addRoomFacility(rf8);
        KRG_1305.addRoomFacility(rf9);
        KRG_1305.addRoomFacility(rf10);
        KRG_1305.addRoomFacility(rf11);
        KRG_1305.addRoomFacility(rf12);
        KRG_1305.addRoomFacility(rf13);
        KRG_1305.addRoomFacility(rf14);
        KRG_1305.addRoomFacility(rf15);
        KRG_1305.addRoomFacility(rf16);
        KRG_1305.addRoomFacility(rf17);
        KRG_1305.addRoomFacility(rf18);
        KRG_1305.addRoomFacility(rf19);

        KRG_1305.addMinibarItem(m1);
        KRG_1305.addMinibarItem(m2);
        KRG_1305.addMinibarItem(m3);
        KRG_1305.addMinibarItem(m4);
        KRG_1305.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRG_1305);

        h1.addRoom(roomSessionLocal.getRoomByName("KRG_1305"));

        Room KRGP1 = new Room("KRG_1401", "1401", "Penthouse", 4, "Occupied", h1);
        KRGP1.addRoomFacility(rf1);
        KRGP1.addRoomFacility(rf2);
        KRGP1.addRoomFacility(rf3);
        KRGP1.addRoomFacility(rf4);
        KRGP1.addRoomFacility(rf5);
        KRGP1.addRoomFacility(rf6);
        KRGP1.addRoomFacility(rf7);
        KRGP1.addRoomFacility(rf8);
        KRGP1.addRoomFacility(rf9);
        KRGP1.addRoomFacility(rf10);
        KRGP1.addRoomFacility(rf11);
        KRGP1.addRoomFacility(rf12);
        KRGP1.addRoomFacility(rf13);
        KRGP1.addRoomFacility(rf14);
        KRGP1.addRoomFacility(rf15);
        KRGP1.addRoomFacility(rf16);
        KRGP1.addRoomFacility(rf17);
        KRGP1.addRoomFacility(rf18);
        KRGP1.addRoomFacility(rf19);
        KRGP1.addRoomFacility(rf20);
        KRGP1.addRoomFacility(rf21);
        KRGP1.addRoomFacility(rf22);
        KRGP1.addMinibarItem(m1);
        KRGP1.addMinibarItem(m2);
        KRGP1.addMinibarItem(m3);
        KRGP1.addMinibarItem(m4);
        KRGP1.addMinibarItem(m5);
        roomSessionLocal.createRoom(KRGP1);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_1401"));

        Room KRGP2 = new Room("KRG_1402", "1402", "Penthouse", 4, "Available", h1);
        KRGP2.addRoomFacility(rf1);
        KRGP2.addRoomFacility(rf2);
        KRGP2.addRoomFacility(rf3);
        KRGP2.addRoomFacility(rf4);
        KRGP2.addRoomFacility(rf5);
        KRGP2.addRoomFacility(rf6);
        KRGP2.addRoomFacility(rf7);
        KRGP2.addRoomFacility(rf8);
        KRGP2.addRoomFacility(rf9);
        KRGP2.addRoomFacility(rf10);
        KRGP2.addRoomFacility(rf11);
        KRGP2.addRoomFacility(rf12);
        KRGP2.addRoomFacility(rf13);
        KRGP2.addRoomFacility(rf14);
        KRGP2.addRoomFacility(rf15);
        KRGP2.addRoomFacility(rf16);
        KRGP2.addRoomFacility(rf17);
        KRGP2.addRoomFacility(rf18);
        KRGP2.addRoomFacility(rf19);
        KRGP2.addRoomFacility(rf20);
        KRGP2.addRoomFacility(rf21);
        KRGP2.addRoomFacility(rf22);
        KRGP2.addMinibarItem(m1);
        KRGP2.addMinibarItem(m2);
        KRGP2.addMinibarItem(m3);
        KRGP2.addMinibarItem(m4);
        KRGP2.addMinibarItem(m5);
        roomSessionLocal.createRoom(KRGP2);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_1402"));

        Room KRGP3 = new Room("KRG_1403", "1403", "Penthouse", 4, "Available", h1);
        KRGP3.addRoomFacility(rf1);
        KRGP3.addRoomFacility(rf2);
        KRGP3.addRoomFacility(rf3);
        KRGP3.addRoomFacility(rf4);
        KRGP3.addRoomFacility(rf5);
        KRGP3.addRoomFacility(rf6);
        KRGP3.addRoomFacility(rf7);
        KRGP3.addRoomFacility(rf8);
        KRGP3.addRoomFacility(rf9);
        KRGP3.addRoomFacility(rf10);
        KRGP3.addRoomFacility(rf11);
        KRGP3.addRoomFacility(rf12);
        KRGP3.addRoomFacility(rf13);
        KRGP3.addRoomFacility(rf14);
        KRGP3.addRoomFacility(rf15);
        KRGP3.addRoomFacility(rf16);
        KRGP3.addRoomFacility(rf17);
        KRGP3.addRoomFacility(rf18);
        KRGP3.addRoomFacility(rf19);
        KRGP3.addRoomFacility(rf20);
        KRGP3.addRoomFacility(rf21);
        KRGP3.addRoomFacility(rf22);
        KRGP3.addMinibarItem(m1);
        KRGP3.addMinibarItem(m2);
        KRGP3.addMinibarItem(m3);
        KRGP3.addMinibarItem(m4);
        KRGP3.addMinibarItem(m5);
        roomSessionLocal.createRoom(KRGP3);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_1403"));

        Room KRGP4 = new Room("KRG_1404", "1404", "Penthouse", 4, "Occupied", h1);
        KRGP4.addRoomFacility(rf1);
        KRGP4.addRoomFacility(rf2);
        KRGP4.addRoomFacility(rf3);
        KRGP4.addRoomFacility(rf4);
        KRGP4.addRoomFacility(rf5);
        KRGP4.addRoomFacility(rf6);
        KRGP4.addRoomFacility(rf7);
        KRGP4.addRoomFacility(rf8);
        KRGP4.addRoomFacility(rf9);
        KRGP4.addRoomFacility(rf10);
        KRGP4.addRoomFacility(rf11);
        KRGP4.addRoomFacility(rf12);
        KRGP4.addRoomFacility(rf13);
        KRGP4.addRoomFacility(rf14);
        KRGP4.addRoomFacility(rf15);
        KRGP4.addRoomFacility(rf16);
        KRGP4.addRoomFacility(rf17);
        KRGP4.addRoomFacility(rf18);
        KRGP4.addRoomFacility(rf19);
        KRGP4.addRoomFacility(rf20);
        KRGP4.addRoomFacility(rf21);
        KRGP4.addRoomFacility(rf22);
        KRGP4.addMinibarItem(m1);
        KRGP4.addMinibarItem(m2);
        KRGP4.addMinibarItem(m3);
        KRGP4.addMinibarItem(m4);
        KRGP4.addMinibarItem(m5);
        roomSessionLocal.createRoom(KRGP4);
        h1.addRoom(roomSessionLocal.getRoomByName("KRG_1404"));

        em.flush();

    }

    public void initializeKRCRoom() throws NoResultException {
        Hotel h2 = hotelSessionLocal.getHotelByName("Kent Ridge Central");
        MinibarItem m1 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Potato Chips");
        MinibarItem m2 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Chocolate");
        MinibarItem m3 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Mineral Water");
        MinibarItem m4 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Coke");
        MinibarItem m5 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Wine");
        RoomFacility rf1 = roomFacilitySessionLocal.getRoomFacilityByName("Wifi");
        RoomFacility rf2 = roomFacilitySessionLocal.getRoomFacilityByName("Minibar");
        RoomFacility rf3 = roomFacilitySessionLocal.getRoomFacilityByName("Aircon");
        RoomFacility rf4 = roomFacilitySessionLocal.getRoomFacilityByName("Cable TV");
        RoomFacility rf5 = roomFacilitySessionLocal.getRoomFacilityByName("Telephone");
        RoomFacility rf6 = roomFacilitySessionLocal.getRoomFacilityByName("Water Heater");
        RoomFacility rf7 = roomFacilitySessionLocal.getRoomFacilityByName("Bathroom with Shower Head");
        RoomFacility rf8 = roomFacilitySessionLocal.getRoomFacilityByName("HairDryer");
        RoomFacility rf9 = roomFacilitySessionLocal.getRoomFacilityByName("Safe Deposit Box");
        RoomFacility rf10 = roomFacilitySessionLocal.getRoomFacilityByName("Water Kettle with complimentary teas/coffee");
        RoomFacility rf11 = roomFacilitySessionLocal.getRoomFacilityByName("Room with Windows");
        RoomFacility rf12 = roomFacilitySessionLocal.getRoomFacilityByName("Bathtub");
        RoomFacility rf13 = roomFacilitySessionLocal.getRoomFacilityByName("Mini Fridge");
        RoomFacility rf14 = roomFacilitySessionLocal.getRoomFacilityByName("Coffee Machine");
        RoomFacility rf15 = roomFacilitySessionLocal.getRoomFacilityByName("Balcony");
        RoomFacility rf16 = roomFacilitySessionLocal.getRoomFacilityByName("Microwave");
        RoomFacility rf17 = roomFacilitySessionLocal.getRoomFacilityByName("Gaming Console");
        RoomFacility rf18 = roomFacilitySessionLocal.getRoomFacilityByName("Entertainment System");
        RoomFacility rf19 = roomFacilitySessionLocal.getRoomFacilityByName("Blackout Curtains");
        RoomFacility rf20 = roomFacilitySessionLocal.getRoomFacilityByName("High Ceiling");
        RoomFacility rf21 = roomFacilitySessionLocal.getRoomFacilityByName("Jaccuzi");
        RoomFacility rf22 = roomFacilitySessionLocal.getRoomFacilityByName("Kitchen");

        FunctionRoom KRCFR1 = new FunctionRoom("KRCFR1", 20, "Available", 20000.00, h2);
        FunctionRoom KRCFR2 = new FunctionRoom("KRCFR2", 100, "Available", 100000.00, h2);
        FunctionRoom KRCFR3 = new FunctionRoom("KRCFR3", 50, "Available", 50000.00, h2);
        FunctionRoom KRCFR4 = new FunctionRoom("KRCFR4", 70, "Available", 70000.00, h2);
        FunctionRoom KRCFR5 = new FunctionRoom("KRCFR5", 80, "Available", 80000.00, h2);

        functionRoomSessionLocal.createFunctionRoom(KRCFR1);
        functionRoomSessionLocal.createFunctionRoom(KRCFR2);
        functionRoomSessionLocal.createFunctionRoom(KRCFR3);
        functionRoomSessionLocal.createFunctionRoom(KRCFR4);
        functionRoomSessionLocal.createFunctionRoom(KRCFR5);

        h2.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRCFR1"));
        h2.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRCFR2"));
        h2.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRCFR3"));
        h2.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRCFR4"));
        h2.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRCFR5"));

        Room KRCS1 = new Room("KRC_201", "201", "Standard", 2, "Available", h2);
        KRCS1.addRoomFacility(rf1);
        KRCS1.addRoomFacility(rf2);
        KRCS1.addRoomFacility(rf3);
        KRCS1.addRoomFacility(rf4);
        KRCS1.addRoomFacility(rf5);
        KRCS1.addRoomFacility(rf6);
        KRCS1.addRoomFacility(rf7);
        KRCS1.addRoomFacility(rf8);
        KRCS1.addRoomFacility(rf9);
        KRCS1.addRoomFacility(rf10);
        KRCS1.addMinibarItem(m1);
        KRCS1.addMinibarItem(m2);
        KRCS1.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS1);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_201"));

        Room KRCS2 = new Room("KRC_202", "202", "Standard", 2, "Unavailable", h2);
        KRCS2.addRoomFacility(rf1);
        KRCS2.addRoomFacility(rf2);
        KRCS2.addRoomFacility(rf3);
        KRCS2.addRoomFacility(rf4);
        KRCS2.addRoomFacility(rf5);
        KRCS2.addRoomFacility(rf6);
        KRCS2.addRoomFacility(rf7);
        KRCS2.addRoomFacility(rf8);
        KRCS2.addRoomFacility(rf9);
        KRCS2.addRoomFacility(rf10);
        KRCS2.addMinibarItem(m1);
        KRCS2.addMinibarItem(m2);
        KRCS2.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS2);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_202"));

        Room KRCS3 = new Room("KRC_203", "203", "Standard", 2, "Available", h2);
        KRCS3.addRoomFacility(rf1);
        KRCS3.addRoomFacility(rf2);
        KRCS3.addRoomFacility(rf3);
        KRCS3.addRoomFacility(rf4);
        KRCS3.addRoomFacility(rf5);
        KRCS3.addRoomFacility(rf6);
        KRCS3.addRoomFacility(rf7);
        KRCS3.addRoomFacility(rf8);
        KRCS3.addRoomFacility(rf9);
        KRCS3.addRoomFacility(rf10);
        KRCS3.addMinibarItem(m1);
        KRCS3.addMinibarItem(m2);
        KRCS3.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS3);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_203"));

        Room KRCS4 = new Room("KRC_204", "204", "Standard", 2, "Available", h2);
        KRCS4.addRoomFacility(rf1);
        KRCS4.addRoomFacility(rf2);
        KRCS4.addRoomFacility(rf3);
        KRCS4.addRoomFacility(rf4);
        KRCS4.addRoomFacility(rf5);
        KRCS4.addRoomFacility(rf6);
        KRCS4.addRoomFacility(rf7);
        KRCS4.addRoomFacility(rf8);
        KRCS4.addRoomFacility(rf9);
        KRCS4.addRoomFacility(rf10);
        KRCS4.addMinibarItem(m1);
        KRCS4.addMinibarItem(m2);
        KRCS4.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS4);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_204"));

        Room KRCS5 = new Room("KRC_205", "205", "Standard", 2, "Occupied", h2);
        KRCS5.addRoomFacility(rf1);
        KRCS5.addRoomFacility(rf2);
        KRCS5.addRoomFacility(rf3);
        KRCS5.addRoomFacility(rf4);
        KRCS5.addRoomFacility(rf5);
        KRCS5.addRoomFacility(rf6);
        KRCS5.addRoomFacility(rf7);
        KRCS5.addRoomFacility(rf8);
        KRCS5.addRoomFacility(rf9);
        KRCS5.addRoomFacility(rf10);
        KRCS5.addMinibarItem(m1);
        KRCS5.addMinibarItem(m2);
        KRCS5.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS5);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_205"));

        Room KRCS6 = new Room("KRC_206", "206", "Standard", 2, "Available", h2);
        KRCS6.addRoomFacility(rf1);
        KRCS6.addRoomFacility(rf2);
        KRCS6.addRoomFacility(rf3);
        KRCS6.addRoomFacility(rf4);
        KRCS6.addRoomFacility(rf5);
        KRCS6.addRoomFacility(rf6);
        KRCS6.addRoomFacility(rf7);
        KRCS6.addRoomFacility(rf8);
        KRCS6.addRoomFacility(rf9);
        KRCS6.addRoomFacility(rf10);
        KRCS6.addMinibarItem(m1);
        KRCS6.addMinibarItem(m2);
        KRCS6.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS6);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_206"));

        Room KRCS7 = new Room("KRC_207", "207", "Standard", 2, "Available", h2);
        KRCS7.addRoomFacility(rf1);
        KRCS7.addRoomFacility(rf2);
        KRCS7.addRoomFacility(rf3);
        KRCS7.addRoomFacility(rf4);
        KRCS7.addRoomFacility(rf5);
        KRCS7.addRoomFacility(rf6);
        KRCS7.addRoomFacility(rf7);
        KRCS7.addRoomFacility(rf8);
        KRCS7.addRoomFacility(rf9);
        KRCS7.addRoomFacility(rf10);
        KRCS7.addMinibarItem(m1);
        KRCS7.addMinibarItem(m2);
        KRCS7.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS7);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_207"));

        Room KRCS8 = new Room("KRC_208", "208", "Standard", 2, "Available", h2);
        KRCS8.addRoomFacility(rf1);
        KRCS8.addRoomFacility(rf2);
        KRCS8.addRoomFacility(rf3);
        KRCS8.addRoomFacility(rf4);
        KRCS8.addRoomFacility(rf5);
        KRCS8.addRoomFacility(rf6);
        KRCS8.addRoomFacility(rf7);
        KRCS8.addRoomFacility(rf8);
        KRCS8.addRoomFacility(rf9);
        KRCS8.addRoomFacility(rf10);
        KRCS8.addMinibarItem(m1);
        KRCS8.addMinibarItem(m2);
        KRCS8.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS8);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_208"));

        Room KRCS11 = new Room("KRC_301", "301", "Standard", 2, "Occupied", h2);
        KRCS11.addRoomFacility(rf1);
        KRCS11.addRoomFacility(rf2);
        KRCS11.addRoomFacility(rf3);
        KRCS11.addRoomFacility(rf4);
        KRCS11.addRoomFacility(rf5);
        KRCS11.addRoomFacility(rf6);
        KRCS11.addRoomFacility(rf7);
        KRCS11.addRoomFacility(rf8);
        KRCS11.addRoomFacility(rf9);
        KRCS11.addRoomFacility(rf10);
        KRCS11.addMinibarItem(m1);
        KRCS11.addMinibarItem(m2);
        KRCS11.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS11);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_301"));

        Room KRCS12 = new Room("KRC_302", "302", "Standard", 2, "Unavailable", h2);
        KRCS12.addRoomFacility(rf1);
        KRCS12.addRoomFacility(rf2);
        KRCS12.addRoomFacility(rf3);
        KRCS12.addRoomFacility(rf4);
        KRCS12.addRoomFacility(rf5);
        KRCS12.addRoomFacility(rf6);
        KRCS12.addRoomFacility(rf7);
        KRCS12.addRoomFacility(rf8);
        KRCS12.addRoomFacility(rf9);
        KRCS12.addRoomFacility(rf10);
        KRCS12.addMinibarItem(m1);
        KRCS12.addMinibarItem(m2);
        KRCS12.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS12);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_302"));

        Room KRCS13 = new Room("KRC_303", "303", "Standard", 2, "Available", h2);
        KRCS13.addRoomFacility(rf1);
        KRCS13.addRoomFacility(rf2);
        KRCS13.addRoomFacility(rf3);
        KRCS13.addRoomFacility(rf4);
        KRCS13.addRoomFacility(rf5);
        KRCS13.addRoomFacility(rf6);
        KRCS13.addRoomFacility(rf7);
        KRCS13.addRoomFacility(rf8);
        KRCS13.addRoomFacility(rf9);
        KRCS13.addRoomFacility(rf10);
        KRCS13.addMinibarItem(m1);
        KRCS13.addMinibarItem(m2);
        KRCS13.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS13);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_303"));

        Room KRCS14 = new Room("KRC_304", "304", "Standard", 2, "Occupied", h2);
        KRCS14.addRoomFacility(rf1);
        KRCS14.addRoomFacility(rf2);
        KRCS14.addRoomFacility(rf3);
        KRCS14.addRoomFacility(rf4);
        KRCS14.addRoomFacility(rf5);
        KRCS14.addRoomFacility(rf6);
        KRCS14.addRoomFacility(rf7);
        KRCS14.addRoomFacility(rf8);
        KRCS14.addRoomFacility(rf9);
        KRCS14.addRoomFacility(rf10);
        KRCS14.addMinibarItem(m1);
        KRCS14.addMinibarItem(m2);
        KRCS14.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS14);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_304"));

        Room KRCS15 = new Room("KRC_305", "305", "Standard", 2, "Available", h2);
        KRCS15.addRoomFacility(rf1);
        KRCS15.addRoomFacility(rf2);
        KRCS15.addRoomFacility(rf3);
        KRCS15.addRoomFacility(rf4);
        KRCS15.addRoomFacility(rf5);
        KRCS15.addRoomFacility(rf6);
        KRCS15.addRoomFacility(rf7);
        KRCS15.addRoomFacility(rf8);
        KRCS15.addRoomFacility(rf9);
        KRCS15.addRoomFacility(rf10);
        KRCS15.addMinibarItem(m1);
        KRCS15.addMinibarItem(m2);
        KRCS15.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS15);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_305"));

        Room KRCS16 = new Room("KRC_306", "306", "Standard", 2, "Available", h2);
        KRCS16.addRoomFacility(rf1);
        KRCS16.addRoomFacility(rf2);
        KRCS16.addRoomFacility(rf3);
        KRCS16.addRoomFacility(rf4);
        KRCS16.addRoomFacility(rf5);
        KRCS16.addRoomFacility(rf6);
        KRCS16.addRoomFacility(rf7);
        KRCS16.addRoomFacility(rf8);
        KRCS16.addRoomFacility(rf9);
        KRCS16.addRoomFacility(rf10);
        KRCS16.addMinibarItem(m1);
        KRCS16.addMinibarItem(m2);
        KRCS16.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS16);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_306"));

        Room KRCS17 = new Room("KRC_307", "307", "Standard", 2, "Occupied", h2);
        KRCS17.addRoomFacility(rf1);
        KRCS17.addRoomFacility(rf2);
        KRCS17.addRoomFacility(rf3);
        KRCS17.addRoomFacility(rf4);
        KRCS17.addRoomFacility(rf5);
        KRCS17.addRoomFacility(rf6);
        KRCS17.addRoomFacility(rf7);
        KRCS17.addRoomFacility(rf8);
        KRCS17.addRoomFacility(rf9);
        KRCS17.addRoomFacility(rf10);
        KRCS17.addMinibarItem(m1);
        KRCS17.addMinibarItem(m2);
        KRCS17.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS17);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_307"));

        Room KRCS18 = new Room("KRC_308", "308", "Standard", 2, "Available", h2);
        KRCS18.addRoomFacility(rf1);
        KRCS18.addRoomFacility(rf2);
        KRCS18.addRoomFacility(rf3);
        KRCS18.addRoomFacility(rf4);
        KRCS18.addRoomFacility(rf5);
        KRCS18.addRoomFacility(rf6);
        KRCS18.addRoomFacility(rf7);
        KRCS18.addRoomFacility(rf8);
        KRCS18.addRoomFacility(rf9);
        KRCS18.addRoomFacility(rf10);
        KRCS18.addMinibarItem(m1);
        KRCS18.addMinibarItem(m2);
        KRCS18.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS18);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_308"));

        Room KRCS21 = new Room("KRC_401", "401", "Standard", 2, "Unavailable", h2);
        KRCS21.addRoomFacility(rf1);
        KRCS21.addRoomFacility(rf2);
        KRCS21.addRoomFacility(rf3);
        KRCS21.addRoomFacility(rf4);
        KRCS21.addRoomFacility(rf5);
        KRCS21.addRoomFacility(rf6);
        KRCS21.addRoomFacility(rf7);
        KRCS21.addRoomFacility(rf8);
        KRCS21.addRoomFacility(rf9);
        KRCS21.addRoomFacility(rf10);
        KRCS21.addMinibarItem(m1);
        KRCS21.addMinibarItem(m2);
        KRCS21.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS21);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_401"));

        Room KRCS22 = new Room("KRC_402", "402", "Standard", 2, "Available", h2);
        KRCS22.addRoomFacility(rf1);
        KRCS22.addRoomFacility(rf2);
        KRCS22.addRoomFacility(rf3);
        KRCS22.addRoomFacility(rf4);
        KRCS22.addRoomFacility(rf5);
        KRCS22.addRoomFacility(rf6);
        KRCS22.addRoomFacility(rf7);
        KRCS22.addRoomFacility(rf8);
        KRCS22.addRoomFacility(rf9);
        KRCS22.addRoomFacility(rf10);
        KRCS22.addMinibarItem(m1);
        KRCS22.addMinibarItem(m2);
        KRCS22.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS22);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_402"));

        Room KRCS23 = new Room("KRC_403", "403", "Standard", 2, "Available", h2);
        KRCS23.addRoomFacility(rf1);
        KRCS23.addRoomFacility(rf2);
        KRCS23.addRoomFacility(rf3);
        KRCS23.addRoomFacility(rf4);
        KRCS23.addRoomFacility(rf5);
        KRCS23.addRoomFacility(rf6);
        KRCS23.addRoomFacility(rf7);
        KRCS23.addRoomFacility(rf8);
        KRCS23.addRoomFacility(rf9);
        KRCS23.addRoomFacility(rf10);
        KRCS23.addMinibarItem(m1);
        KRCS23.addMinibarItem(m2);
        KRCS23.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS23);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_403"));

        Room KRCS24 = new Room("KRC_404", "404", "Standard", 2, "Available", h2);
        KRCS24.addRoomFacility(rf1);
        KRCS24.addRoomFacility(rf2);
        KRCS24.addRoomFacility(rf3);
        KRCS24.addRoomFacility(rf4);
        KRCS24.addRoomFacility(rf5);
        KRCS24.addRoomFacility(rf6);
        KRCS24.addRoomFacility(rf7);
        KRCS24.addRoomFacility(rf8);
        KRCS24.addRoomFacility(rf9);
        KRCS24.addRoomFacility(rf10);
        KRCS24.addMinibarItem(m1);
        KRCS24.addMinibarItem(m2);
        KRCS24.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS24);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_404"));

        Room KRCS25 = new Room("KRC_405", "405", "Standard", 2, "Occupied", h2);
        KRCS25.addRoomFacility(rf1);
        KRCS25.addRoomFacility(rf2);
        KRCS25.addRoomFacility(rf3);
        KRCS25.addRoomFacility(rf4);
        KRCS25.addRoomFacility(rf5);
        KRCS25.addRoomFacility(rf6);
        KRCS25.addRoomFacility(rf7);
        KRCS25.addRoomFacility(rf8);
        KRCS25.addRoomFacility(rf9);
        KRCS25.addRoomFacility(rf10);
        KRCS25.addMinibarItem(m1);
        KRCS25.addMinibarItem(m2);
        KRCS25.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS25);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_405"));

        Room KRCS26 = new Room("KRC_406", "406", "Standard", 2, "Unavailable", h2);
        KRCS26.addRoomFacility(rf1);
        KRCS26.addRoomFacility(rf2);
        KRCS26.addRoomFacility(rf3);
        KRCS26.addRoomFacility(rf4);
        KRCS26.addRoomFacility(rf5);
        KRCS26.addRoomFacility(rf6);
        KRCS26.addRoomFacility(rf7);
        KRCS26.addRoomFacility(rf8);
        KRCS26.addRoomFacility(rf9);
        KRCS26.addRoomFacility(rf10);
        KRCS26.addMinibarItem(m1);
        KRCS26.addMinibarItem(m2);
        KRCS26.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS26);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_406"));

        Room KRCS27 = new Room("KRC_407", "407", "Standard", 2, "Available", h2);
        KRCS27.addRoomFacility(rf1);
        KRCS27.addRoomFacility(rf2);
        KRCS27.addRoomFacility(rf3);
        KRCS27.addRoomFacility(rf4);
        KRCS27.addRoomFacility(rf5);
        KRCS27.addRoomFacility(rf6);
        KRCS27.addRoomFacility(rf7);
        KRCS27.addRoomFacility(rf8);
        KRCS27.addRoomFacility(rf9);
        KRCS27.addRoomFacility(rf10);
        KRCS27.addMinibarItem(m1);
        KRCS27.addMinibarItem(m2);
        KRCS27.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS27);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_407"));

        Room KRCS28 = new Room("KRC_408", "408", "Standard", 2, "Occupied", h2);
        KRCS28.addRoomFacility(rf1);
        KRCS28.addRoomFacility(rf2);
        KRCS28.addRoomFacility(rf3);
        KRCS28.addRoomFacility(rf4);
        KRCS28.addRoomFacility(rf5);
        KRCS28.addRoomFacility(rf6);
        KRCS28.addRoomFacility(rf7);
        KRCS28.addRoomFacility(rf8);
        KRCS28.addRoomFacility(rf9);
        KRCS28.addRoomFacility(rf10);
        KRCS28.addMinibarItem(m1);
        KRCS28.addMinibarItem(m2);
        KRCS28.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS28);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_408"));

        Room KRCS31 = new Room("KRC_501", "501", "Standard", 2, "Available", h2);
        KRCS31.addRoomFacility(rf1);
        KRCS31.addRoomFacility(rf2);
        KRCS31.addRoomFacility(rf3);
        KRCS31.addRoomFacility(rf4);
        KRCS31.addRoomFacility(rf5);
        KRCS31.addRoomFacility(rf6);
        KRCS31.addRoomFacility(rf7);
        KRCS31.addRoomFacility(rf8);
        KRCS31.addRoomFacility(rf9);
        KRCS31.addRoomFacility(rf10);
        KRCS31.addMinibarItem(m1);
        KRCS31.addMinibarItem(m2);
        KRCS31.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS31);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_501"));

        Room KRCS32 = new Room("KRC_502", "502", "Standard", 2, "Occupied", h2);
        KRCS32.addRoomFacility(rf1);
        KRCS32.addRoomFacility(rf2);
        KRCS32.addRoomFacility(rf3);
        KRCS32.addRoomFacility(rf4);
        KRCS32.addRoomFacility(rf5);
        KRCS32.addRoomFacility(rf6);
        KRCS32.addRoomFacility(rf7);
        KRCS32.addRoomFacility(rf8);
        KRCS32.addRoomFacility(rf9);
        KRCS32.addRoomFacility(rf10);
        KRCS32.addMinibarItem(m1);
        KRCS32.addMinibarItem(m2);
        KRCS32.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS32);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_502"));

        Room KRCS33 = new Room("KRC_503", "503", "Standard", 2, "Available", h2);
        KRCS33.addRoomFacility(rf1);
        KRCS33.addRoomFacility(rf2);
        KRCS33.addRoomFacility(rf3);
        KRCS33.addRoomFacility(rf4);
        KRCS33.addRoomFacility(rf5);
        KRCS33.addRoomFacility(rf6);
        KRCS33.addRoomFacility(rf7);
        KRCS33.addRoomFacility(rf8);
        KRCS33.addRoomFacility(rf9);
        KRCS33.addRoomFacility(rf10);
        KRCS33.addMinibarItem(m1);
        KRCS33.addMinibarItem(m2);
        KRCS33.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS33);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_503"));

        Room KRCS34 = new Room("KRC_504", "504", "Standard", 2, "Unavailable", h2);
        KRCS34.addRoomFacility(rf1);
        KRCS34.addRoomFacility(rf2);
        KRCS34.addRoomFacility(rf3);
        KRCS34.addRoomFacility(rf4);
        KRCS34.addRoomFacility(rf5);
        KRCS34.addRoomFacility(rf6);
        KRCS34.addRoomFacility(rf7);
        KRCS34.addRoomFacility(rf8);
        KRCS34.addRoomFacility(rf9);
        KRCS34.addRoomFacility(rf10);
        KRCS34.addMinibarItem(m1);
        KRCS34.addMinibarItem(m2);
        KRCS34.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS34);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_504"));

        Room KRCS35 = new Room("KRC_505", "505", "Standard", 2, "Available", h2);
        KRCS35.addRoomFacility(rf1);
        KRCS35.addRoomFacility(rf2);
        KRCS35.addRoomFacility(rf3);
        KRCS35.addRoomFacility(rf4);
        KRCS35.addRoomFacility(rf5);
        KRCS35.addRoomFacility(rf6);
        KRCS35.addRoomFacility(rf7);
        KRCS35.addRoomFacility(rf8);
        KRCS35.addRoomFacility(rf9);
        KRCS35.addRoomFacility(rf10);
        KRCS35.addMinibarItem(m1);
        KRCS35.addMinibarItem(m2);
        KRCS35.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS35);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_505"));

        Room KRCS36 = new Room("KRC_506", "506", "Standard", 2, "Occupied", h2);
        KRCS36.addRoomFacility(rf1);
        KRCS36.addRoomFacility(rf2);
        KRCS36.addRoomFacility(rf3);
        KRCS36.addRoomFacility(rf4);
        KRCS36.addRoomFacility(rf5);
        KRCS36.addRoomFacility(rf6);
        KRCS36.addRoomFacility(rf7);
        KRCS36.addRoomFacility(rf8);
        KRCS36.addRoomFacility(rf9);
        KRCS36.addRoomFacility(rf10);
        KRCS36.addMinibarItem(m1);
        KRCS36.addMinibarItem(m2);
        KRCS36.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS36);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_506"));

        Room KRCS37 = new Room("KRC_507", "507", "Standard", 2, "Available", h2);
        KRCS37.addRoomFacility(rf1);
        KRCS37.addRoomFacility(rf2);
        KRCS37.addRoomFacility(rf3);
        KRCS37.addRoomFacility(rf4);
        KRCS37.addRoomFacility(rf5);
        KRCS37.addRoomFacility(rf6);
        KRCS37.addRoomFacility(rf7);
        KRCS37.addRoomFacility(rf8);
        KRCS37.addRoomFacility(rf9);
        KRCS37.addRoomFacility(rf10);
        KRCS37.addMinibarItem(m1);
        KRCS37.addMinibarItem(m2);
        KRCS37.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS37);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_507"));

        Room KRCS38 = new Room("KRC_508", "508", "Standard", 2, "Available", h2);
        KRCS38.addRoomFacility(rf1);
        KRCS38.addRoomFacility(rf2);
        KRCS38.addRoomFacility(rf3);
        KRCS38.addRoomFacility(rf4);
        KRCS38.addRoomFacility(rf5);
        KRCS38.addRoomFacility(rf6);
        KRCS38.addRoomFacility(rf7);
        KRCS38.addRoomFacility(rf8);
        KRCS38.addRoomFacility(rf9);
        KRCS38.addRoomFacility(rf10);
        KRCS38.addMinibarItem(m1);
        KRCS38.addMinibarItem(m2);
        KRCS38.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS38);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_508"));

        Room KRCS41 = new Room("KRC_601", "601", "Standard", 2, "Occupied", h2);
        KRCS41.addRoomFacility(rf1);
        KRCS41.addRoomFacility(rf2);
        KRCS41.addRoomFacility(rf3);
        KRCS41.addRoomFacility(rf4);
        KRCS41.addRoomFacility(rf5);
        KRCS41.addRoomFacility(rf6);
        KRCS41.addRoomFacility(rf7);
        KRCS41.addRoomFacility(rf8);
        KRCS41.addRoomFacility(rf9);
        KRCS41.addRoomFacility(rf10);
        KRCS41.addMinibarItem(m1);
        KRCS41.addMinibarItem(m2);
        KRCS41.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS41);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_601"));

        Room KRCS42 = new Room("KRC_602", "602", "Standard", 2, "Available", h2);
        KRCS42.addRoomFacility(rf1);
        KRCS42.addRoomFacility(rf2);
        KRCS42.addRoomFacility(rf3);
        KRCS42.addRoomFacility(rf4);
        KRCS42.addRoomFacility(rf5);
        KRCS42.addRoomFacility(rf6);
        KRCS42.addRoomFacility(rf7);
        KRCS42.addRoomFacility(rf8);
        KRCS42.addRoomFacility(rf9);
        KRCS42.addRoomFacility(rf10);
        KRCS42.addMinibarItem(m1);
        KRCS42.addMinibarItem(m2);
        KRCS42.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS42);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_602"));

        Room KRCS43 = new Room("KRC_603", "603", "Standard", 2, "Unavailable", h2);
        KRCS43.addRoomFacility(rf1);
        KRCS43.addRoomFacility(rf2);
        KRCS43.addRoomFacility(rf3);
        KRCS43.addRoomFacility(rf4);
        KRCS43.addRoomFacility(rf5);
        KRCS43.addRoomFacility(rf6);
        KRCS43.addRoomFacility(rf7);
        KRCS43.addRoomFacility(rf8);
        KRCS43.addRoomFacility(rf9);
        KRCS43.addRoomFacility(rf10);
        KRCS43.addMinibarItem(m1);
        KRCS43.addMinibarItem(m2);
        KRCS43.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS43);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_603"));

        Room KRCS44 = new Room("KRC_604", "604", "Standard", 2, "Available", h2);
        KRCS44.addRoomFacility(rf1);
        KRCS44.addRoomFacility(rf2);
        KRCS44.addRoomFacility(rf3);
        KRCS44.addRoomFacility(rf4);
        KRCS44.addRoomFacility(rf5);
        KRCS44.addRoomFacility(rf6);
        KRCS44.addRoomFacility(rf7);
        KRCS44.addRoomFacility(rf8);
        KRCS44.addRoomFacility(rf9);
        KRCS44.addRoomFacility(rf10);
        KRCS44.addMinibarItem(m1);
        KRCS44.addMinibarItem(m2);
        KRCS44.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS44);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_604"));

        Room KRCS45 = new Room("KRC_605", "605", "Standard", 2, "Occupied", h2);
        KRCS45.addRoomFacility(rf1);
        KRCS45.addRoomFacility(rf2);
        KRCS45.addRoomFacility(rf3);
        KRCS45.addRoomFacility(rf4);
        KRCS45.addRoomFacility(rf5);
        KRCS45.addRoomFacility(rf6);
        KRCS45.addRoomFacility(rf7);
        KRCS45.addRoomFacility(rf8);
        KRCS45.addRoomFacility(rf9);
        KRCS45.addRoomFacility(rf10);
        KRCS45.addMinibarItem(m1);
        KRCS45.addMinibarItem(m2);
        KRCS45.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS45);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_605"));

        Room KRCS46 = new Room("KRC_606", "606", "Standard", 2, "Available", h2);
        KRCS46.addRoomFacility(rf1);
        KRCS46.addRoomFacility(rf2);
        KRCS46.addRoomFacility(rf3);
        KRCS46.addRoomFacility(rf4);
        KRCS46.addRoomFacility(rf5);
        KRCS46.addRoomFacility(rf6);
        KRCS46.addRoomFacility(rf7);
        KRCS46.addRoomFacility(rf8);
        KRCS46.addRoomFacility(rf9);
        KRCS46.addRoomFacility(rf10);
        KRCS46.addMinibarItem(m1);
        KRCS46.addMinibarItem(m2);
        KRCS46.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS46);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_606"));

        Room KRCS47 = new Room("KRC_607", "607", "Standard", 2, "Available", h2);
        KRCS47.addRoomFacility(rf1);
        KRCS47.addRoomFacility(rf2);
        KRCS47.addRoomFacility(rf3);
        KRCS47.addRoomFacility(rf4);
        KRCS47.addRoomFacility(rf5);
        KRCS47.addRoomFacility(rf6);
        KRCS47.addRoomFacility(rf7);
        KRCS47.addRoomFacility(rf8);
        KRCS47.addRoomFacility(rf9);
        KRCS47.addRoomFacility(rf10);
        KRCS47.addMinibarItem(m1);
        KRCS47.addMinibarItem(m2);
        KRCS47.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS47);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_607"));

        Room KRCS48 = new Room("KRC_608", "608", "Standard", 2, "Unavailable", h2);
        KRCS48.addRoomFacility(rf1);
        KRCS48.addRoomFacility(rf2);
        KRCS48.addRoomFacility(rf3);
        KRCS48.addRoomFacility(rf4);
        KRCS48.addRoomFacility(rf5);
        KRCS48.addRoomFacility(rf6);
        KRCS48.addRoomFacility(rf7);
        KRCS48.addRoomFacility(rf8);
        KRCS48.addRoomFacility(rf9);
        KRCS48.addRoomFacility(rf10);
        KRCS48.addMinibarItem(m1);
        KRCS48.addMinibarItem(m2);
        KRCS48.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRCS48);
        h2.addRoom(roomSessionLocal.getRoomByName("KRC_608"));

        Room KRC_1001 = new Room("KRC_1001", "1001", "Deluxe", 3, "Available", h2);

        KRC_1001.addRoomFacility(rf1);
        KRC_1001.addRoomFacility(rf2);
        KRC_1001.addRoomFacility(rf3);
        KRC_1001.addRoomFacility(rf4);
        KRC_1001.addRoomFacility(rf5);
        KRC_1001.addRoomFacility(rf6);
        KRC_1001.addRoomFacility(rf7);
        KRC_1001.addRoomFacility(rf8);
        KRC_1001.addRoomFacility(rf9);
        KRC_1001.addRoomFacility(rf10);
        KRC_1001.addRoomFacility(rf11);
        KRC_1001.addRoomFacility(rf12);
        KRC_1001.addRoomFacility(rf13);

        KRC_1001.addMinibarItem(m1);
        KRC_1001.addMinibarItem(m2);
        KRC_1001.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRC_1001);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_1001"));

        Room KRC_1002 = new Room("KRC_1002", "1002", "Deluxe", 3, "Available", h2);

        KRC_1002.addRoomFacility(rf1);
        KRC_1002.addRoomFacility(rf2);
        KRC_1002.addRoomFacility(rf3);
        KRC_1002.addRoomFacility(rf4);
        KRC_1002.addRoomFacility(rf5);
        KRC_1002.addRoomFacility(rf6);
        KRC_1002.addRoomFacility(rf7);
        KRC_1002.addRoomFacility(rf8);
        KRC_1002.addRoomFacility(rf9);
        KRC_1002.addRoomFacility(rf10);
        KRC_1002.addRoomFacility(rf11);
        KRC_1002.addRoomFacility(rf12);
        KRC_1002.addRoomFacility(rf13);

        KRC_1002.addMinibarItem(m1);
        KRC_1002.addMinibarItem(m2);
        KRC_1002.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRC_1002);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_1002"));

        Room KRC_1003 = new Room("KRC_1003", "1003", "Deluxe", 3, "Occupied", h2);

        KRC_1003.addRoomFacility(rf1);
        KRC_1003.addRoomFacility(rf2);
        KRC_1003.addRoomFacility(rf3);
        KRC_1003.addRoomFacility(rf4);
        KRC_1003.addRoomFacility(rf5);
        KRC_1003.addRoomFacility(rf6);
        KRC_1003.addRoomFacility(rf7);
        KRC_1003.addRoomFacility(rf8);
        KRC_1003.addRoomFacility(rf9);
        KRC_1003.addRoomFacility(rf10);
        KRC_1003.addRoomFacility(rf11);
        KRC_1003.addRoomFacility(rf12);
        KRC_1003.addRoomFacility(rf13);

        KRC_1003.addMinibarItem(m1);
        KRC_1003.addMinibarItem(m2);
        KRC_1003.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRC_1003);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_1003"));

        Room KRC_1004 = new Room("KRC_1004", "1004", "Deluxe", 3, "Available", h2);

        KRC_1004.addRoomFacility(rf1);
        KRC_1004.addRoomFacility(rf2);
        KRC_1004.addRoomFacility(rf3);
        KRC_1004.addRoomFacility(rf4);
        KRC_1004.addRoomFacility(rf5);
        KRC_1004.addRoomFacility(rf6);
        KRC_1004.addRoomFacility(rf7);
        KRC_1004.addRoomFacility(rf8);
        KRC_1004.addRoomFacility(rf9);
        KRC_1004.addRoomFacility(rf10);
        KRC_1004.addRoomFacility(rf11);
        KRC_1004.addRoomFacility(rf12);
        KRC_1004.addRoomFacility(rf13);

        KRC_1004.addMinibarItem(m1);
        KRC_1004.addMinibarItem(m2);
        KRC_1004.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRC_1004);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_1004"));

        Room KRC_1005 = new Room("KRC_1005", "1005", "Deluxe", 3, "Available", h2);

        KRC_1005.addRoomFacility(rf1);
        KRC_1005.addRoomFacility(rf2);
        KRC_1005.addRoomFacility(rf3);
        KRC_1005.addRoomFacility(rf4);
        KRC_1005.addRoomFacility(rf5);
        KRC_1005.addRoomFacility(rf6);
        KRC_1005.addRoomFacility(rf7);
        KRC_1005.addRoomFacility(rf8);
        KRC_1005.addRoomFacility(rf9);
        KRC_1005.addRoomFacility(rf10);
        KRC_1005.addRoomFacility(rf11);
        KRC_1005.addRoomFacility(rf12);
        KRC_1005.addRoomFacility(rf13);

        KRC_1005.addMinibarItem(m1);
        KRC_1005.addMinibarItem(m2);
        KRC_1005.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRC_1005);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_1005"));

        Room KRC_1101 = new Room("KRC_1101", "1101", "Deluxe", 3, "Occupied", h2);

        KRC_1101.addRoomFacility(rf1);
        KRC_1101.addRoomFacility(rf2);
        KRC_1101.addRoomFacility(rf3);
        KRC_1101.addRoomFacility(rf4);
        KRC_1101.addRoomFacility(rf5);
        KRC_1101.addRoomFacility(rf6);
        KRC_1101.addRoomFacility(rf7);
        KRC_1101.addRoomFacility(rf8);
        KRC_1101.addRoomFacility(rf9);
        KRC_1101.addRoomFacility(rf10);
        KRC_1101.addRoomFacility(rf11);
        KRC_1101.addRoomFacility(rf12);
        KRC_1101.addRoomFacility(rf13);

        KRC_1101.addMinibarItem(m1);
        KRC_1101.addMinibarItem(m2);
        KRC_1101.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRC_1101);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_1101"));

        Room KRC_1102 = new Room("KRC_1102", "1102", "Deluxe", 3, "Unavailable", h2);

        KRC_1102.addRoomFacility(rf1);
        KRC_1102.addRoomFacility(rf2);
        KRC_1102.addRoomFacility(rf3);
        KRC_1102.addRoomFacility(rf4);
        KRC_1102.addRoomFacility(rf5);
        KRC_1102.addRoomFacility(rf6);
        KRC_1102.addRoomFacility(rf7);
        KRC_1102.addRoomFacility(rf8);
        KRC_1102.addRoomFacility(rf9);
        KRC_1102.addRoomFacility(rf10);
        KRC_1102.addRoomFacility(rf11);
        KRC_1102.addRoomFacility(rf12);
        KRC_1102.addRoomFacility(rf13);

        KRC_1102.addMinibarItem(m1);
        KRC_1102.addMinibarItem(m2);
        KRC_1102.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRC_1102);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_1102"));

        Room KRC_1103 = new Room("KRC_1103", "1103", "Deluxe", 3, "Available", h2);

        KRC_1103.addRoomFacility(rf1);
        KRC_1103.addRoomFacility(rf2);
        KRC_1103.addRoomFacility(rf3);
        KRC_1103.addRoomFacility(rf4);
        KRC_1103.addRoomFacility(rf5);
        KRC_1103.addRoomFacility(rf6);
        KRC_1103.addRoomFacility(rf7);
        KRC_1103.addRoomFacility(rf8);
        KRC_1103.addRoomFacility(rf9);
        KRC_1103.addRoomFacility(rf10);
        KRC_1103.addRoomFacility(rf11);
        KRC_1103.addRoomFacility(rf12);
        KRC_1103.addRoomFacility(rf13);

        KRC_1103.addMinibarItem(m1);
        KRC_1103.addMinibarItem(m2);
        KRC_1103.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRC_1103);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_1103"));

        Room KRC_1104 = new Room("KRC_1104", "1104", "Deluxe", 3, "Occupied", h2);

        KRC_1104.addRoomFacility(rf1);
        KRC_1104.addRoomFacility(rf2);
        KRC_1104.addRoomFacility(rf3);
        KRC_1104.addRoomFacility(rf4);
        KRC_1104.addRoomFacility(rf5);
        KRC_1104.addRoomFacility(rf6);
        KRC_1104.addRoomFacility(rf7);
        KRC_1104.addRoomFacility(rf8);
        KRC_1104.addRoomFacility(rf9);
        KRC_1104.addRoomFacility(rf10);
        KRC_1104.addRoomFacility(rf11);
        KRC_1104.addRoomFacility(rf12);
        KRC_1104.addRoomFacility(rf13);

        KRC_1104.addMinibarItem(m1);
        KRC_1104.addMinibarItem(m2);
        KRC_1104.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRC_1104);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_1104"));

        Room KRC_1105 = new Room("KRC_1105", "1105", "Deluxe", 3, "Available", h2);

        KRC_1105.addRoomFacility(rf1);
        KRC_1105.addRoomFacility(rf2);
        KRC_1105.addRoomFacility(rf3);
        KRC_1105.addRoomFacility(rf4);
        KRC_1105.addRoomFacility(rf5);
        KRC_1105.addRoomFacility(rf6);
        KRC_1105.addRoomFacility(rf7);
        KRC_1105.addRoomFacility(rf8);
        KRC_1105.addRoomFacility(rf9);
        KRC_1105.addRoomFacility(rf10);
        KRC_1105.addRoomFacility(rf11);
        KRC_1105.addRoomFacility(rf12);
        KRC_1105.addRoomFacility(rf13);

        KRC_1105.addMinibarItem(m1);
        KRC_1105.addMinibarItem(m2);
        KRC_1105.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRC_1105);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_1105"));

        Room KRC_701 = new Room("KRC_701", "701", "Premium", 4, "Occupied", h2);

        KRC_701.addRoomFacility(rf1);
        KRC_701.addRoomFacility(rf2);
        KRC_701.addRoomFacility(rf3);
        KRC_701.addRoomFacility(rf4);
        KRC_701.addRoomFacility(rf5);
        KRC_701.addRoomFacility(rf6);
        KRC_701.addRoomFacility(rf7);
        KRC_701.addRoomFacility(rf8);
        KRC_701.addRoomFacility(rf9);
        KRC_701.addRoomFacility(rf10);
        KRC_701.addRoomFacility(rf11);
        KRC_701.addRoomFacility(rf12);
        KRC_701.addRoomFacility(rf13);
        KRC_701.addRoomFacility(rf14);
        KRC_701.addRoomFacility(rf15);

        KRC_701.addMinibarItem(m1);
        KRC_701.addMinibarItem(m2);
        KRC_701.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRC_701);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_701"));

        Room KRC_702 = new Room("KRC_702", "702", "Premium", 4, "Available", h2);

        KRC_702.addRoomFacility(rf1);
        KRC_702.addRoomFacility(rf2);
        KRC_702.addRoomFacility(rf3);
        KRC_702.addRoomFacility(rf4);
        KRC_702.addRoomFacility(rf5);
        KRC_702.addRoomFacility(rf6);
        KRC_702.addRoomFacility(rf7);
        KRC_702.addRoomFacility(rf8);
        KRC_702.addRoomFacility(rf9);
        KRC_702.addRoomFacility(rf10);
        KRC_702.addRoomFacility(rf11);
        KRC_702.addRoomFacility(rf12);
        KRC_702.addRoomFacility(rf13);
        KRC_702.addRoomFacility(rf14);
        KRC_702.addRoomFacility(rf15);

        KRC_702.addMinibarItem(m1);
        KRC_702.addMinibarItem(m2);
        KRC_702.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRC_702);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_702"));

        Room KRC_703 = new Room("KRC_703", "703", "Premium", 4, "Available", h2);

        KRC_703.addRoomFacility(rf1);
        KRC_703.addRoomFacility(rf2);
        KRC_703.addRoomFacility(rf3);
        KRC_703.addRoomFacility(rf4);
        KRC_703.addRoomFacility(rf5);
        KRC_703.addRoomFacility(rf6);
        KRC_703.addRoomFacility(rf7);
        KRC_703.addRoomFacility(rf8);
        KRC_703.addRoomFacility(rf9);
        KRC_703.addRoomFacility(rf10);
        KRC_703.addRoomFacility(rf11);
        KRC_703.addRoomFacility(rf12);
        KRC_703.addRoomFacility(rf13);
        KRC_703.addRoomFacility(rf14);
        KRC_703.addRoomFacility(rf15);

        KRC_703.addMinibarItem(m1);
        KRC_703.addMinibarItem(m2);
        KRC_703.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRC_703);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_703"));

        Room KRC_704 = new Room("KRC_704", "704", "Premium", 4, "Unavailable", h2);

        KRC_704.addRoomFacility(rf1);
        KRC_704.addRoomFacility(rf2);
        KRC_704.addRoomFacility(rf3);
        KRC_704.addRoomFacility(rf4);
        KRC_704.addRoomFacility(rf5);
        KRC_704.addRoomFacility(rf6);
        KRC_704.addRoomFacility(rf7);
        KRC_704.addRoomFacility(rf8);
        KRC_704.addRoomFacility(rf9);
        KRC_704.addRoomFacility(rf10);
        KRC_704.addRoomFacility(rf11);
        KRC_704.addRoomFacility(rf12);
        KRC_704.addRoomFacility(rf13);
        KRC_704.addRoomFacility(rf14);
        KRC_704.addRoomFacility(rf15);

        KRC_704.addMinibarItem(m1);
        KRC_704.addMinibarItem(m2);
        KRC_704.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRC_704);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_704"));

        Room KRC_705 = new Room("KRC_705", "705", "Premium", 4, "Occupied", h2);

        KRC_705.addRoomFacility(rf1);
        KRC_705.addRoomFacility(rf2);
        KRC_705.addRoomFacility(rf3);
        KRC_705.addRoomFacility(rf4);
        KRC_705.addRoomFacility(rf5);
        KRC_705.addRoomFacility(rf6);
        KRC_705.addRoomFacility(rf7);
        KRC_705.addRoomFacility(rf8);
        KRC_705.addRoomFacility(rf9);
        KRC_705.addRoomFacility(rf10);
        KRC_705.addRoomFacility(rf11);
        KRC_705.addRoomFacility(rf12);
        KRC_705.addRoomFacility(rf13);
        KRC_705.addRoomFacility(rf14);
        KRC_705.addRoomFacility(rf15);

        KRC_705.addMinibarItem(m1);
        KRC_705.addMinibarItem(m2);
        KRC_705.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRC_705);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_705"));

        Room KRC_706 = new Room("KRC_706", "706", "Premium", 4, "Available", h2);

        KRC_706.addRoomFacility(rf1);
        KRC_706.addRoomFacility(rf2);
        KRC_706.addRoomFacility(rf3);
        KRC_706.addRoomFacility(rf4);
        KRC_706.addRoomFacility(rf5);
        KRC_706.addRoomFacility(rf6);
        KRC_706.addRoomFacility(rf7);
        KRC_706.addRoomFacility(rf8);
        KRC_706.addRoomFacility(rf9);
        KRC_706.addRoomFacility(rf10);
        KRC_706.addRoomFacility(rf11);
        KRC_706.addRoomFacility(rf12);
        KRC_706.addRoomFacility(rf13);
        KRC_706.addRoomFacility(rf14);
        KRC_706.addRoomFacility(rf15);

        KRC_706.addMinibarItem(m1);
        KRC_706.addMinibarItem(m2);
        KRC_706.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRC_706);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_706"));

        Room KRC_707 = new Room("KRC_707", "707", "Premium", 4, "Occupied", h2);

        KRC_707.addRoomFacility(rf1);
        KRC_707.addRoomFacility(rf2);
        KRC_707.addRoomFacility(rf3);
        KRC_707.addRoomFacility(rf4);
        KRC_707.addRoomFacility(rf5);
        KRC_707.addRoomFacility(rf6);
        KRC_707.addRoomFacility(rf7);
        KRC_707.addRoomFacility(rf8);
        KRC_707.addRoomFacility(rf9);
        KRC_707.addRoomFacility(rf10);
        KRC_707.addRoomFacility(rf11);
        KRC_707.addRoomFacility(rf12);
        KRC_707.addRoomFacility(rf13);
        KRC_707.addRoomFacility(rf14);
        KRC_707.addRoomFacility(rf15);

        KRC_707.addMinibarItem(m1);
        KRC_707.addMinibarItem(m2);
        KRC_707.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRC_707);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_707"));

        Room KRC_801 = new Room("KRC_801", "801", "Premium", 4, "Available", h2);

        KRC_801.addRoomFacility(rf1);
        KRC_801.addRoomFacility(rf2);
        KRC_801.addRoomFacility(rf3);
        KRC_801.addRoomFacility(rf4);
        KRC_801.addRoomFacility(rf5);
        KRC_801.addRoomFacility(rf6);
        KRC_801.addRoomFacility(rf7);
        KRC_801.addRoomFacility(rf8);
        KRC_801.addRoomFacility(rf9);
        KRC_801.addRoomFacility(rf10);
        KRC_801.addRoomFacility(rf11);
        KRC_801.addRoomFacility(rf12);
        KRC_801.addRoomFacility(rf13);
        KRC_801.addRoomFacility(rf14);
        KRC_801.addRoomFacility(rf15);

        KRC_801.addMinibarItem(m1);
        KRC_801.addMinibarItem(m2);
        KRC_801.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRC_801);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_801"));

        Room KRC_802 = new Room("KRC_802", "802", "Premium", 4, "Available", h2);

        KRC_802.addRoomFacility(rf1);
        KRC_802.addRoomFacility(rf2);
        KRC_802.addRoomFacility(rf3);
        KRC_802.addRoomFacility(rf4);
        KRC_802.addRoomFacility(rf5);
        KRC_802.addRoomFacility(rf6);
        KRC_802.addRoomFacility(rf7);
        KRC_802.addRoomFacility(rf8);
        KRC_802.addRoomFacility(rf9);
        KRC_802.addRoomFacility(rf10);
        KRC_802.addRoomFacility(rf11);
        KRC_802.addRoomFacility(rf12);
        KRC_802.addRoomFacility(rf13);
        KRC_802.addRoomFacility(rf14);
        KRC_802.addRoomFacility(rf15);

        KRC_802.addMinibarItem(m1);
        KRC_802.addMinibarItem(m2);
        KRC_802.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRC_802);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_802"));

        Room KRC_803 = new Room("KRC_803", "803", "Premium", 4, "Occupied", h2);

        KRC_803.addRoomFacility(rf1);
        KRC_803.addRoomFacility(rf2);
        KRC_803.addRoomFacility(rf3);
        KRC_803.addRoomFacility(rf4);
        KRC_803.addRoomFacility(rf5);
        KRC_803.addRoomFacility(rf6);
        KRC_803.addRoomFacility(rf7);
        KRC_803.addRoomFacility(rf8);
        KRC_803.addRoomFacility(rf9);
        KRC_803.addRoomFacility(rf10);
        KRC_803.addRoomFacility(rf11);
        KRC_803.addRoomFacility(rf12);
        KRC_803.addRoomFacility(rf13);
        KRC_803.addRoomFacility(rf14);
        KRC_803.addRoomFacility(rf15);

        KRC_803.addMinibarItem(m1);
        KRC_803.addMinibarItem(m2);
        KRC_803.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRC_803);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_803"));

        Room KRC_804 = new Room("KRC_804", "804", "Premium", 4, "Unavailable", h2);

        KRC_804.addRoomFacility(rf1);
        KRC_804.addRoomFacility(rf2);
        KRC_804.addRoomFacility(rf3);
        KRC_804.addRoomFacility(rf4);
        KRC_804.addRoomFacility(rf5);
        KRC_804.addRoomFacility(rf6);
        KRC_804.addRoomFacility(rf7);
        KRC_804.addRoomFacility(rf8);
        KRC_804.addRoomFacility(rf9);
        KRC_804.addRoomFacility(rf10);
        KRC_804.addRoomFacility(rf11);
        KRC_804.addRoomFacility(rf12);
        KRC_804.addRoomFacility(rf13);
        KRC_804.addRoomFacility(rf14);
        KRC_804.addRoomFacility(rf15);

        KRC_804.addMinibarItem(m1);
        KRC_804.addMinibarItem(m2);
        KRC_804.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRC_804);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_804"));

        Room KRC_805 = new Room("KRC_805", "805", "Premium", 4, "Available", h2);

        KRC_805.addRoomFacility(rf1);
        KRC_805.addRoomFacility(rf2);
        KRC_805.addRoomFacility(rf3);
        KRC_805.addRoomFacility(rf4);
        KRC_805.addRoomFacility(rf5);
        KRC_805.addRoomFacility(rf6);
        KRC_805.addRoomFacility(rf7);
        KRC_805.addRoomFacility(rf8);
        KRC_805.addRoomFacility(rf9);
        KRC_805.addRoomFacility(rf10);
        KRC_805.addRoomFacility(rf11);
        KRC_805.addRoomFacility(rf12);
        KRC_805.addRoomFacility(rf13);
        KRC_805.addRoomFacility(rf14);
        KRC_805.addRoomFacility(rf15);

        KRC_805.addMinibarItem(m1);
        KRC_805.addMinibarItem(m2);
        KRC_805.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRC_805);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_805"));

        Room KRC_806 = new Room("KRC_806", "806", "Premium", 4, "Occupied", h2);

        KRC_806.addRoomFacility(rf1);
        KRC_806.addRoomFacility(rf2);
        KRC_806.addRoomFacility(rf3);
        KRC_806.addRoomFacility(rf4);
        KRC_806.addRoomFacility(rf5);
        KRC_806.addRoomFacility(rf6);
        KRC_806.addRoomFacility(rf7);
        KRC_806.addRoomFacility(rf8);
        KRC_806.addRoomFacility(rf9);
        KRC_806.addRoomFacility(rf10);
        KRC_806.addRoomFacility(rf11);
        KRC_806.addRoomFacility(rf12);
        KRC_806.addRoomFacility(rf13);
        KRC_806.addRoomFacility(rf14);
        KRC_806.addRoomFacility(rf15);

        KRC_806.addMinibarItem(m1);
        KRC_806.addMinibarItem(m2);
        KRC_806.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRC_806);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_806"));

        Room KRC_807 = new Room("KRC_807", "807", "Premium", 4, "Available", h2);

        KRC_807.addRoomFacility(rf1);
        KRC_807.addRoomFacility(rf2);
        KRC_807.addRoomFacility(rf3);
        KRC_807.addRoomFacility(rf4);
        KRC_807.addRoomFacility(rf5);
        KRC_807.addRoomFacility(rf6);
        KRC_807.addRoomFacility(rf7);
        KRC_807.addRoomFacility(rf8);
        KRC_807.addRoomFacility(rf9);
        KRC_807.addRoomFacility(rf10);
        KRC_807.addRoomFacility(rf11);
        KRC_807.addRoomFacility(rf12);
        KRC_807.addRoomFacility(rf13);
        KRC_807.addRoomFacility(rf14);
        KRC_807.addRoomFacility(rf15);

        KRC_807.addMinibarItem(m1);
        KRC_807.addMinibarItem(m2);
        KRC_807.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRC_807);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_807"));

        Room KRC_901 = new Room("KRC_901", "901", "Premium", 4, "Unavailable", h2);

        KRC_901.addRoomFacility(rf1);
        KRC_901.addRoomFacility(rf2);
        KRC_901.addRoomFacility(rf3);
        KRC_901.addRoomFacility(rf4);
        KRC_901.addRoomFacility(rf5);
        KRC_901.addRoomFacility(rf6);
        KRC_901.addRoomFacility(rf7);
        KRC_901.addRoomFacility(rf8);
        KRC_901.addRoomFacility(rf9);
        KRC_901.addRoomFacility(rf10);
        KRC_901.addRoomFacility(rf11);
        KRC_901.addRoomFacility(rf12);
        KRC_901.addRoomFacility(rf13);
        KRC_901.addRoomFacility(rf14);
        KRC_901.addRoomFacility(rf15);

        KRC_901.addMinibarItem(m1);
        KRC_901.addMinibarItem(m2);
        KRC_901.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRC_901);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_901"));

        Room KRC_902 = new Room("KRC_902", "902", "Premium", 4, "Occupied", h2);

        KRC_902.addRoomFacility(rf1);
        KRC_902.addRoomFacility(rf2);
        KRC_902.addRoomFacility(rf3);
        KRC_902.addRoomFacility(rf4);
        KRC_902.addRoomFacility(rf5);
        KRC_902.addRoomFacility(rf6);
        KRC_902.addRoomFacility(rf7);
        KRC_902.addRoomFacility(rf8);
        KRC_902.addRoomFacility(rf9);
        KRC_902.addRoomFacility(rf10);
        KRC_902.addRoomFacility(rf11);
        KRC_902.addRoomFacility(rf12);
        KRC_902.addRoomFacility(rf13);
        KRC_902.addRoomFacility(rf14);
        KRC_902.addRoomFacility(rf15);

        KRC_902.addMinibarItem(m1);
        KRC_902.addMinibarItem(m2);
        KRC_902.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRC_902);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_902"));

        Room KRC_903 = new Room("KRC_903", "903", "Premium", 4, "Available", h2);

        KRC_903.addRoomFacility(rf1);
        KRC_903.addRoomFacility(rf2);
        KRC_903.addRoomFacility(rf3);
        KRC_903.addRoomFacility(rf4);
        KRC_903.addRoomFacility(rf5);
        KRC_903.addRoomFacility(rf6);
        KRC_903.addRoomFacility(rf7);
        KRC_903.addRoomFacility(rf8);
        KRC_903.addRoomFacility(rf9);
        KRC_903.addRoomFacility(rf10);
        KRC_903.addRoomFacility(rf11);
        KRC_903.addRoomFacility(rf12);
        KRC_903.addRoomFacility(rf13);
        KRC_903.addRoomFacility(rf14);
        KRC_903.addRoomFacility(rf15);

        KRC_903.addMinibarItem(m1);
        KRC_903.addMinibarItem(m2);
        KRC_903.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRC_903);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_903"));

        Room KRC_904 = new Room("KRC_904", "904", "Premium", 4, "Available", h2);

        KRC_904.addRoomFacility(rf1);
        KRC_904.addRoomFacility(rf2);
        KRC_904.addRoomFacility(rf3);
        KRC_904.addRoomFacility(rf4);
        KRC_904.addRoomFacility(rf5);
        KRC_904.addRoomFacility(rf6);
        KRC_904.addRoomFacility(rf7);
        KRC_904.addRoomFacility(rf8);
        KRC_904.addRoomFacility(rf9);
        KRC_904.addRoomFacility(rf10);
        KRC_904.addRoomFacility(rf11);
        KRC_904.addRoomFacility(rf12);
        KRC_904.addRoomFacility(rf13);
        KRC_904.addRoomFacility(rf14);
        KRC_904.addRoomFacility(rf15);

        KRC_904.addMinibarItem(m1);
        KRC_904.addMinibarItem(m2);
        KRC_904.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRC_904);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_904"));

        Room KRC_905 = new Room("KRC_905", "905", "Premium", 4, "Occupied", h2);

        KRC_905.addRoomFacility(rf1);
        KRC_905.addRoomFacility(rf2);
        KRC_905.addRoomFacility(rf3);
        KRC_905.addRoomFacility(rf4);
        KRC_905.addRoomFacility(rf5);
        KRC_905.addRoomFacility(rf6);
        KRC_905.addRoomFacility(rf7);
        KRC_905.addRoomFacility(rf8);
        KRC_905.addRoomFacility(rf9);
        KRC_905.addRoomFacility(rf10);
        KRC_905.addRoomFacility(rf11);
        KRC_905.addRoomFacility(rf12);
        KRC_905.addRoomFacility(rf13);
        KRC_905.addRoomFacility(rf14);
        KRC_905.addRoomFacility(rf15);

        KRC_905.addMinibarItem(m1);
        KRC_905.addMinibarItem(m2);
        KRC_905.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRC_905);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_905"));

        Room KRC_906 = new Room("KRC_906", "906", "Premium", 4, "Available", h2);

        KRC_906.addRoomFacility(rf1);
        KRC_906.addRoomFacility(rf2);
        KRC_906.addRoomFacility(rf3);
        KRC_906.addRoomFacility(rf4);
        KRC_906.addRoomFacility(rf5);
        KRC_906.addRoomFacility(rf6);
        KRC_906.addRoomFacility(rf7);
        KRC_906.addRoomFacility(rf8);
        KRC_906.addRoomFacility(rf9);
        KRC_906.addRoomFacility(rf10);
        KRC_906.addRoomFacility(rf11);
        KRC_906.addRoomFacility(rf12);
        KRC_906.addRoomFacility(rf13);
        KRC_906.addRoomFacility(rf14);
        KRC_906.addRoomFacility(rf15);

        KRC_906.addMinibarItem(m1);
        KRC_906.addMinibarItem(m2);
        KRC_906.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRC_906);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_906"));

        Room KRC_907 = new Room("KRC_907", "907", "Premium", 4, "Available", h2);

        KRC_907.addRoomFacility(rf1);
        KRC_907.addRoomFacility(rf2);
        KRC_907.addRoomFacility(rf3);
        KRC_907.addRoomFacility(rf4);
        KRC_907.addRoomFacility(rf5);
        KRC_907.addRoomFacility(rf6);
        KRC_907.addRoomFacility(rf7);
        KRC_907.addRoomFacility(rf8);
        KRC_907.addRoomFacility(rf9);
        KRC_907.addRoomFacility(rf10);
        KRC_907.addRoomFacility(rf11);
        KRC_907.addRoomFacility(rf12);
        KRC_907.addRoomFacility(rf13);
        KRC_907.addRoomFacility(rf14);
        KRC_907.addRoomFacility(rf15);

        KRC_907.addMinibarItem(m1);
        KRC_907.addMinibarItem(m2);
        KRC_907.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRC_907);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_907"));

        Room KRC_1201 = new Room("KRC_1201", "1201", "Suite", 4, "Occupied", h2);

        KRC_1201.addRoomFacility(rf1);
        KRC_1201.addRoomFacility(rf2);
        KRC_1201.addRoomFacility(rf3);
        KRC_1201.addRoomFacility(rf4);
        KRC_1201.addRoomFacility(rf5);
        KRC_1201.addRoomFacility(rf6);
        KRC_1201.addRoomFacility(rf7);
        KRC_1201.addRoomFacility(rf8);
        KRC_1201.addRoomFacility(rf9);
        KRC_1201.addRoomFacility(rf10);
        KRC_1201.addRoomFacility(rf11);
        KRC_1201.addRoomFacility(rf12);
        KRC_1201.addRoomFacility(rf13);
        KRC_1201.addRoomFacility(rf14);
        KRC_1201.addRoomFacility(rf15);
        KRC_1201.addRoomFacility(rf16);
        KRC_1201.addRoomFacility(rf17);
        KRC_1201.addRoomFacility(rf18);
        KRC_1201.addRoomFacility(rf19);

        KRC_1201.addMinibarItem(m1);
        KRC_1201.addMinibarItem(m2);
        KRC_1201.addMinibarItem(m3);
        KRC_1201.addMinibarItem(m4);
        KRC_1201.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRC_1201);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_1201"));

        Room KRC_1202 = new Room("KRC_1202", "1202", "Suite", 4, "Unavailable", h2);

        KRC_1202.addRoomFacility(rf1);
        KRC_1202.addRoomFacility(rf2);
        KRC_1202.addRoomFacility(rf3);
        KRC_1202.addRoomFacility(rf4);
        KRC_1202.addRoomFacility(rf5);
        KRC_1202.addRoomFacility(rf6);
        KRC_1202.addRoomFacility(rf7);
        KRC_1202.addRoomFacility(rf8);
        KRC_1202.addRoomFacility(rf9);
        KRC_1202.addRoomFacility(rf10);
        KRC_1202.addRoomFacility(rf11);
        KRC_1202.addRoomFacility(rf12);
        KRC_1202.addRoomFacility(rf13);
        KRC_1202.addRoomFacility(rf14);
        KRC_1202.addRoomFacility(rf15);
        KRC_1202.addRoomFacility(rf16);
        KRC_1202.addRoomFacility(rf17);
        KRC_1202.addRoomFacility(rf18);
        KRC_1202.addRoomFacility(rf19);

        KRC_1202.addMinibarItem(m1);
        KRC_1202.addMinibarItem(m2);
        KRC_1202.addMinibarItem(m3);
        KRC_1202.addMinibarItem(m4);
        KRC_1202.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRC_1202);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_1202"));

        Room KRC_1203 = new Room("KRC_1203", "1203", "Suite", 4, "Available", h2);

        KRC_1203.addRoomFacility(rf1);
        KRC_1203.addRoomFacility(rf2);
        KRC_1203.addRoomFacility(rf3);
        KRC_1203.addRoomFacility(rf4);
        KRC_1203.addRoomFacility(rf5);
        KRC_1203.addRoomFacility(rf6);
        KRC_1203.addRoomFacility(rf7);
        KRC_1203.addRoomFacility(rf8);
        KRC_1203.addRoomFacility(rf9);
        KRC_1203.addRoomFacility(rf10);
        KRC_1203.addRoomFacility(rf11);
        KRC_1203.addRoomFacility(rf12);
        KRC_1203.addRoomFacility(rf13);
        KRC_1203.addRoomFacility(rf14);
        KRC_1203.addRoomFacility(rf15);
        KRC_1203.addRoomFacility(rf16);
        KRC_1203.addRoomFacility(rf17);
        KRC_1203.addRoomFacility(rf18);
        KRC_1203.addRoomFacility(rf19);

        KRC_1203.addMinibarItem(m1);
        KRC_1203.addMinibarItem(m2);
        KRC_1203.addMinibarItem(m3);
        KRC_1203.addMinibarItem(m4);
        KRC_1203.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRC_1203);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_1203"));

        Room KRC_1204 = new Room("KRC_1204", "1204", "Suite", 4, "Available", h2);

        KRC_1204.addRoomFacility(rf1);
        KRC_1204.addRoomFacility(rf2);
        KRC_1204.addRoomFacility(rf3);
        KRC_1204.addRoomFacility(rf4);
        KRC_1204.addRoomFacility(rf5);
        KRC_1204.addRoomFacility(rf6);
        KRC_1204.addRoomFacility(rf7);
        KRC_1204.addRoomFacility(rf8);
        KRC_1204.addRoomFacility(rf9);
        KRC_1204.addRoomFacility(rf10);
        KRC_1204.addRoomFacility(rf11);
        KRC_1204.addRoomFacility(rf12);
        KRC_1204.addRoomFacility(rf13);
        KRC_1204.addRoomFacility(rf14);
        KRC_1204.addRoomFacility(rf15);
        KRC_1204.addRoomFacility(rf16);
        KRC_1204.addRoomFacility(rf17);
        KRC_1204.addRoomFacility(rf18);
        KRC_1204.addRoomFacility(rf19);

        KRC_1204.addMinibarItem(m1);
        KRC_1204.addMinibarItem(m2);
        KRC_1204.addMinibarItem(m3);
        KRC_1204.addMinibarItem(m4);
        KRC_1204.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRC_1204);

        h2.addRoom(roomSessionLocal.getRoomByName("KRC_1204"));
        em.flush();

    }

    public void initializeKRNRoom() throws NoResultException {
        Hotel h3 = hotelSessionLocal.getHotelByName("Kent Ridge North");
        MinibarItem m1 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Potato Chips");
        MinibarItem m2 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Chocolate");
        MinibarItem m3 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Mineral Water");
        MinibarItem m4 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Coke");
        MinibarItem m5 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Wine");
        RoomFacility rf1 = roomFacilitySessionLocal.getRoomFacilityByName("Wifi");
        RoomFacility rf2 = roomFacilitySessionLocal.getRoomFacilityByName("Minibar");
        RoomFacility rf3 = roomFacilitySessionLocal.getRoomFacilityByName("Aircon");
        RoomFacility rf4 = roomFacilitySessionLocal.getRoomFacilityByName("Cable TV");
        RoomFacility rf5 = roomFacilitySessionLocal.getRoomFacilityByName("Telephone");
        RoomFacility rf6 = roomFacilitySessionLocal.getRoomFacilityByName("Water Heater");
        RoomFacility rf7 = roomFacilitySessionLocal.getRoomFacilityByName("Bathroom with Shower Head");
        RoomFacility rf8 = roomFacilitySessionLocal.getRoomFacilityByName("HairDryer");
        RoomFacility rf9 = roomFacilitySessionLocal.getRoomFacilityByName("Safe Deposit Box");
        RoomFacility rf10 = roomFacilitySessionLocal.getRoomFacilityByName("Water Kettle with complimentary teas/coffee");
        RoomFacility rf11 = roomFacilitySessionLocal.getRoomFacilityByName("Room with Windows");
        RoomFacility rf12 = roomFacilitySessionLocal.getRoomFacilityByName("Bathtub");
        RoomFacility rf13 = roomFacilitySessionLocal.getRoomFacilityByName("Mini Fridge");
        RoomFacility rf14 = roomFacilitySessionLocal.getRoomFacilityByName("Coffee Machine");
        RoomFacility rf15 = roomFacilitySessionLocal.getRoomFacilityByName("Balcony");
        RoomFacility rf16 = roomFacilitySessionLocal.getRoomFacilityByName("Microwave");
        RoomFacility rf17 = roomFacilitySessionLocal.getRoomFacilityByName("Gaming Console");
        RoomFacility rf18 = roomFacilitySessionLocal.getRoomFacilityByName("Entertainment System");
        RoomFacility rf19 = roomFacilitySessionLocal.getRoomFacilityByName("Blackout Curtains");
        RoomFacility rf20 = roomFacilitySessionLocal.getRoomFacilityByName("High Ceiling");
        RoomFacility rf21 = roomFacilitySessionLocal.getRoomFacilityByName("Jaccuzi");
        RoomFacility rf22 = roomFacilitySessionLocal.getRoomFacilityByName("Kitchen");

        FunctionRoom KRNFR1 = new FunctionRoom("KRNFR1", 20, "Available", 20000.00, h3);
        FunctionRoom KRNFR2 = new FunctionRoom("KRNFR2", 100, "Available", 100000.00, h3);
        FunctionRoom KRNFR3 = new FunctionRoom("KRNFR3", 50, "Available", 50000.00, h3);
        FunctionRoom KRNFR4 = new FunctionRoom("KRNFR4", 70, "Available", 70000.00, h3);
        FunctionRoom KRNFR5 = new FunctionRoom("KRNFR5", 80, "Available", 80000.00, h3);

        functionRoomSessionLocal.createFunctionRoom(KRNFR1);
        functionRoomSessionLocal.createFunctionRoom(KRNFR2);
        functionRoomSessionLocal.createFunctionRoom(KRNFR3);
        functionRoomSessionLocal.createFunctionRoom(KRNFR4);
        functionRoomSessionLocal.createFunctionRoom(KRNFR5);

        h3.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRNFR1"));
        h3.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRNFR2"));
        h3.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRNFR3"));
        h3.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRNFR4"));
        h3.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRNFR5"));

        Room KRNS1 = new Room("KRN_201", "201", "Standard", 2, "Occupied", h3);
        KRNS1.addRoomFacility(rf1);
        KRNS1.addRoomFacility(rf2);
        KRNS1.addRoomFacility(rf3);
        KRNS1.addRoomFacility(rf4);
        KRNS1.addRoomFacility(rf5);
        KRNS1.addRoomFacility(rf6);
        KRNS1.addRoomFacility(rf7);
        KRNS1.addRoomFacility(rf8);
        KRNS1.addRoomFacility(rf9);
        KRNS1.addRoomFacility(rf10);
        KRNS1.addMinibarItem(m1);
        KRNS1.addMinibarItem(m2);
        KRNS1.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS1);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_201"));

        Room KRNS2 = new Room("KRN_202", "202", "Standard", 2, "Available", h3);
        KRNS2.addRoomFacility(rf1);
        KRNS2.addRoomFacility(rf2);
        KRNS2.addRoomFacility(rf3);
        KRNS2.addRoomFacility(rf4);
        KRNS2.addRoomFacility(rf5);
        KRNS2.addRoomFacility(rf6);
        KRNS2.addRoomFacility(rf7);
        KRNS2.addRoomFacility(rf8);
        KRNS2.addRoomFacility(rf9);
        KRNS2.addRoomFacility(rf10);
        KRNS2.addMinibarItem(m1);
        KRNS2.addMinibarItem(m2);
        KRNS2.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS2);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_202"));

        Room KRNS3 = new Room("KRN_203", "203", "Standard", 2, "Available", h3);
        KRNS3.addRoomFacility(rf1);
        KRNS3.addRoomFacility(rf2);
        KRNS3.addRoomFacility(rf3);
        KRNS3.addRoomFacility(rf4);
        KRNS3.addRoomFacility(rf5);
        KRNS3.addRoomFacility(rf6);
        KRNS3.addRoomFacility(rf7);
        KRNS3.addRoomFacility(rf8);
        KRNS3.addRoomFacility(rf9);
        KRNS3.addRoomFacility(rf10);
        KRNS3.addMinibarItem(m1);
        KRNS3.addMinibarItem(m2);
        KRNS3.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS3);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_203"));

        Room KRNS4 = new Room("KRN_204", "204", "Standard", 2, "Unavailable", h3);
        KRNS4.addRoomFacility(rf1);
        KRNS4.addRoomFacility(rf2);
        KRNS4.addRoomFacility(rf3);
        KRNS4.addRoomFacility(rf4);
        KRNS4.addRoomFacility(rf5);
        KRNS4.addRoomFacility(rf6);
        KRNS4.addRoomFacility(rf7);
        KRNS4.addRoomFacility(rf8);
        KRNS4.addRoomFacility(rf9);
        KRNS4.addRoomFacility(rf10);
        KRNS4.addMinibarItem(m1);
        KRNS4.addMinibarItem(m2);
        KRNS4.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS4);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_204"));

        Room KRNS5 = new Room("KRN_205", "205", "Standard", 2, "Available", h3);
        KRNS5.addRoomFacility(rf1);
        KRNS5.addRoomFacility(rf2);
        KRNS5.addRoomFacility(rf3);
        KRNS5.addRoomFacility(rf4);
        KRNS5.addRoomFacility(rf5);
        KRNS5.addRoomFacility(rf6);
        KRNS5.addRoomFacility(rf7);
        KRNS5.addRoomFacility(rf8);
        KRNS5.addRoomFacility(rf9);
        KRNS5.addRoomFacility(rf10);
        KRNS5.addMinibarItem(m1);
        KRNS5.addMinibarItem(m2);
        KRNS5.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS5);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_205"));

        Room KRNS6 = new Room("KRN_206", "206", "Standard", 2, "Available", h3);
        KRNS6.addRoomFacility(rf1);
        KRNS6.addRoomFacility(rf2);
        KRNS6.addRoomFacility(rf3);
        KRNS6.addRoomFacility(rf4);
        KRNS6.addRoomFacility(rf5);
        KRNS6.addRoomFacility(rf6);
        KRNS6.addRoomFacility(rf7);
        KRNS6.addRoomFacility(rf8);
        KRNS6.addRoomFacility(rf9);
        KRNS6.addRoomFacility(rf10);
        KRNS6.addMinibarItem(m1);
        KRNS6.addMinibarItem(m2);
        KRNS6.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS6);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_206"));

        Room KRNS7 = new Room("KRN_207", "207", "Standard", 2, "Available", h3);
        KRNS7.addRoomFacility(rf1);
        KRNS7.addRoomFacility(rf2);
        KRNS7.addRoomFacility(rf3);
        KRNS7.addRoomFacility(rf4);
        KRNS7.addRoomFacility(rf5);
        KRNS7.addRoomFacility(rf6);
        KRNS7.addRoomFacility(rf7);
        KRNS7.addRoomFacility(rf8);
        KRNS7.addRoomFacility(rf9);
        KRNS7.addRoomFacility(rf10);
        KRNS7.addMinibarItem(m1);
        KRNS7.addMinibarItem(m2);
        KRNS7.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS7);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_207"));

        Room KRNS8 = new Room("KRN_208", "208", "Standard", 2, "Occupied", h3);
        KRNS8.addRoomFacility(rf1);
        KRNS8.addRoomFacility(rf2);
        KRNS8.addRoomFacility(rf3);
        KRNS8.addRoomFacility(rf4);
        KRNS8.addRoomFacility(rf5);
        KRNS8.addRoomFacility(rf6);
        KRNS8.addRoomFacility(rf7);
        KRNS8.addRoomFacility(rf8);
        KRNS8.addRoomFacility(rf9);
        KRNS8.addRoomFacility(rf10);
        KRNS8.addMinibarItem(m1);
        KRNS8.addMinibarItem(m2);
        KRNS8.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS8);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_208"));

        Room KRNS11 = new Room("KRN_301", "301", "Standard", 2, "Available", h3);
        KRNS11.addRoomFacility(rf1);
        KRNS11.addRoomFacility(rf2);
        KRNS11.addRoomFacility(rf3);
        KRNS11.addRoomFacility(rf4);
        KRNS11.addRoomFacility(rf5);
        KRNS11.addRoomFacility(rf6);
        KRNS11.addRoomFacility(rf7);
        KRNS11.addRoomFacility(rf8);
        KRNS11.addRoomFacility(rf9);
        KRNS11.addRoomFacility(rf10);
        KRNS11.addMinibarItem(m1);
        KRNS11.addMinibarItem(m2);
        KRNS11.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS11);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_301"));

        Room KRNS12 = new Room("KRN_302", "302", "Standard", 2, "Occupied", h3);
        KRNS12.addRoomFacility(rf1);
        KRNS12.addRoomFacility(rf2);
        KRNS12.addRoomFacility(rf3);
        KRNS12.addRoomFacility(rf4);
        KRNS12.addRoomFacility(rf5);
        KRNS12.addRoomFacility(rf6);
        KRNS12.addRoomFacility(rf7);
        KRNS12.addRoomFacility(rf8);
        KRNS12.addRoomFacility(rf9);
        KRNS12.addRoomFacility(rf10);
        KRNS12.addMinibarItem(m1);
        KRNS12.addMinibarItem(m2);
        KRNS12.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS12);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_302"));

        Room KRNS13 = new Room("KRN_303", "303", "Standard", 2, "Unavailable", h3);
        KRNS13.addRoomFacility(rf1);
        KRNS13.addRoomFacility(rf2);
        KRNS13.addRoomFacility(rf3);
        KRNS13.addRoomFacility(rf4);
        KRNS13.addRoomFacility(rf5);
        KRNS13.addRoomFacility(rf6);
        KRNS13.addRoomFacility(rf7);
        KRNS13.addRoomFacility(rf8);
        KRNS13.addRoomFacility(rf9);
        KRNS13.addRoomFacility(rf10);
        KRNS13.addMinibarItem(m1);
        KRNS13.addMinibarItem(m2);
        KRNS13.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS13);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_303"));

        Room KRNS14 = new Room("KRN_304", "304", "Standard", 2, "Available", h3);
        KRNS14.addRoomFacility(rf1);
        KRNS14.addRoomFacility(rf2);
        KRNS14.addRoomFacility(rf3);
        KRNS14.addRoomFacility(rf4);
        KRNS14.addRoomFacility(rf5);
        KRNS14.addRoomFacility(rf6);
        KRNS14.addRoomFacility(rf7);
        KRNS14.addRoomFacility(rf8);
        KRNS14.addRoomFacility(rf9);
        KRNS14.addRoomFacility(rf10);
        KRNS14.addMinibarItem(m1);
        KRNS14.addMinibarItem(m2);
        KRNS14.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS14);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_304"));

        Room KRNS15 = new Room("KRN_305", "305", "Standard", 2, "Available", h3);
        KRNS15.addRoomFacility(rf1);
        KRNS15.addRoomFacility(rf2);
        KRNS15.addRoomFacility(rf3);
        KRNS15.addRoomFacility(rf4);
        KRNS15.addRoomFacility(rf5);
        KRNS15.addRoomFacility(rf6);
        KRNS15.addRoomFacility(rf7);
        KRNS15.addRoomFacility(rf8);
        KRNS15.addRoomFacility(rf9);
        KRNS15.addRoomFacility(rf10);
        KRNS15.addMinibarItem(m1);
        KRNS15.addMinibarItem(m2);
        KRNS15.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS15);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_305"));

        Room KRNS16 = new Room("KRN_306", "306", "Standard", 2, "Occupied", h3);
        KRNS16.addRoomFacility(rf1);
        KRNS16.addRoomFacility(rf2);
        KRNS16.addRoomFacility(rf3);
        KRNS16.addRoomFacility(rf4);
        KRNS16.addRoomFacility(rf5);
        KRNS16.addRoomFacility(rf6);
        KRNS16.addRoomFacility(rf7);
        KRNS16.addRoomFacility(rf8);
        KRNS16.addRoomFacility(rf9);
        KRNS16.addRoomFacility(rf10);
        KRNS16.addMinibarItem(m1);
        KRNS16.addMinibarItem(m2);
        KRNS16.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS16);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_306"));

        Room KRNS17 = new Room("KRN_307", "307", "Standard", 2, "Available", h3);
        KRNS17.addRoomFacility(rf1);
        KRNS17.addRoomFacility(rf2);
        KRNS17.addRoomFacility(rf3);
        KRNS17.addRoomFacility(rf4);
        KRNS17.addRoomFacility(rf5);
        KRNS17.addRoomFacility(rf6);
        KRNS17.addRoomFacility(rf7);
        KRNS17.addRoomFacility(rf8);
        KRNS17.addRoomFacility(rf9);
        KRNS17.addRoomFacility(rf10);
        KRNS17.addMinibarItem(m1);
        KRNS17.addMinibarItem(m2);
        KRNS17.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS17);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_307"));

        Room KRNS18 = new Room("KRN_308", "308", "Standard", 2, "Available", h3);
        KRNS18.addRoomFacility(rf1);
        KRNS18.addRoomFacility(rf2);
        KRNS18.addRoomFacility(rf3);
        KRNS18.addRoomFacility(rf4);
        KRNS18.addRoomFacility(rf5);
        KRNS18.addRoomFacility(rf6);
        KRNS18.addRoomFacility(rf7);
        KRNS18.addRoomFacility(rf8);
        KRNS18.addRoomFacility(rf9);
        KRNS18.addRoomFacility(rf10);
        KRNS18.addMinibarItem(m1);
        KRNS18.addMinibarItem(m2);
        KRNS18.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS18);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_308"));

        Room KRNS21 = new Room("KRN_401", "401", "Standard", 2, "Available", h3);
        KRNS21.addRoomFacility(rf1);
        KRNS21.addRoomFacility(rf2);
        KRNS21.addRoomFacility(rf3);
        KRNS21.addRoomFacility(rf4);
        KRNS21.addRoomFacility(rf5);
        KRNS21.addRoomFacility(rf6);
        KRNS21.addRoomFacility(rf7);
        KRNS21.addRoomFacility(rf8);
        KRNS21.addRoomFacility(rf9);
        KRNS21.addRoomFacility(rf10);
        KRNS21.addMinibarItem(m1);
        KRNS21.addMinibarItem(m2);
        KRNS21.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS21);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_401"));

        Room KRNS22 = new Room("KRN_402", "402", "Standard", 2, "Unavailable", h3);
        KRNS22.addRoomFacility(rf1);
        KRNS22.addRoomFacility(rf2);
        KRNS22.addRoomFacility(rf3);
        KRNS22.addRoomFacility(rf4);
        KRNS22.addRoomFacility(rf5);
        KRNS22.addRoomFacility(rf6);
        KRNS22.addRoomFacility(rf7);
        KRNS22.addRoomFacility(rf8);
        KRNS22.addRoomFacility(rf9);
        KRNS22.addRoomFacility(rf10);
        KRNS22.addMinibarItem(m1);
        KRNS22.addMinibarItem(m2);
        KRNS22.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS22);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_402"));

        Room KRNS23 = new Room("KRN_403", "403", "Standard", 2, "Occupied", h3);
        KRNS23.addRoomFacility(rf1);
        KRNS23.addRoomFacility(rf2);
        KRNS23.addRoomFacility(rf3);
        KRNS23.addRoomFacility(rf4);
        KRNS23.addRoomFacility(rf5);
        KRNS23.addRoomFacility(rf6);
        KRNS23.addRoomFacility(rf7);
        KRNS23.addRoomFacility(rf8);
        KRNS23.addRoomFacility(rf9);
        KRNS23.addRoomFacility(rf10);
        KRNS23.addMinibarItem(m1);
        KRNS23.addMinibarItem(m2);
        KRNS23.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS23);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_403"));

        Room KRNS24 = new Room("KRN_404", "404", "Standard", 2, "Available", h3);
        KRNS24.addRoomFacility(rf1);
        KRNS24.addRoomFacility(rf2);
        KRNS24.addRoomFacility(rf3);
        KRNS24.addRoomFacility(rf4);
        KRNS24.addRoomFacility(rf5);
        KRNS24.addRoomFacility(rf6);
        KRNS24.addRoomFacility(rf7);
        KRNS24.addRoomFacility(rf8);
        KRNS24.addRoomFacility(rf9);
        KRNS24.addRoomFacility(rf10);
        KRNS24.addMinibarItem(m1);
        KRNS24.addMinibarItem(m2);
        KRNS24.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS24);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_404"));

        Room KRNS25 = new Room("KRN_405", "405", "Standard", 2, "Available", h3);
        KRNS25.addRoomFacility(rf1);
        KRNS25.addRoomFacility(rf2);
        KRNS25.addRoomFacility(rf3);
        KRNS25.addRoomFacility(rf4);
        KRNS25.addRoomFacility(rf5);
        KRNS25.addRoomFacility(rf6);
        KRNS25.addRoomFacility(rf7);
        KRNS25.addRoomFacility(rf8);
        KRNS25.addRoomFacility(rf9);
        KRNS25.addRoomFacility(rf10);
        KRNS25.addMinibarItem(m1);
        KRNS25.addMinibarItem(m2);
        KRNS25.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS25);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_405"));

        Room KRNS26 = new Room("KRN_406", "406", "Standard", 2, "Available", h3);
        KRNS26.addRoomFacility(rf1);
        KRNS26.addRoomFacility(rf2);
        KRNS26.addRoomFacility(rf3);
        KRNS26.addRoomFacility(rf4);
        KRNS26.addRoomFacility(rf5);
        KRNS26.addRoomFacility(rf6);
        KRNS26.addRoomFacility(rf7);
        KRNS26.addRoomFacility(rf8);
        KRNS26.addRoomFacility(rf9);
        KRNS26.addRoomFacility(rf10);
        KRNS26.addMinibarItem(m1);
        KRNS26.addMinibarItem(m2);
        KRNS26.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS26);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_406"));

        Room KRNS27 = new Room("KRN_407", "407", "Standard", 2, "Occupied", h3);
        KRNS27.addRoomFacility(rf1);
        KRNS27.addRoomFacility(rf2);
        KRNS27.addRoomFacility(rf3);
        KRNS27.addRoomFacility(rf4);
        KRNS27.addRoomFacility(rf5);
        KRNS27.addRoomFacility(rf6);
        KRNS27.addRoomFacility(rf7);
        KRNS27.addRoomFacility(rf8);
        KRNS27.addRoomFacility(rf9);
        KRNS27.addRoomFacility(rf10);
        KRNS27.addMinibarItem(m1);
        KRNS27.addMinibarItem(m2);
        KRNS27.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS27);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_407"));

        Room KRNS28 = new Room("KRN_408", "408", "Standard", 2, "Available", h3);
        KRNS28.addRoomFacility(rf1);
        KRNS28.addRoomFacility(rf2);
        KRNS28.addRoomFacility(rf3);
        KRNS28.addRoomFacility(rf4);
        KRNS28.addRoomFacility(rf5);
        KRNS28.addRoomFacility(rf6);
        KRNS28.addRoomFacility(rf7);
        KRNS28.addRoomFacility(rf8);
        KRNS28.addRoomFacility(rf9);
        KRNS28.addRoomFacility(rf10);
        KRNS28.addMinibarItem(m1);
        KRNS28.addMinibarItem(m2);
        KRNS28.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS28);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_408"));

        Room KRNS31 = new Room("KRN_501", "501", "Standard", 2, "Available", h3);
        KRNS31.addRoomFacility(rf1);
        KRNS31.addRoomFacility(rf2);
        KRNS31.addRoomFacility(rf3);
        KRNS31.addRoomFacility(rf4);
        KRNS31.addRoomFacility(rf5);
        KRNS31.addRoomFacility(rf6);
        KRNS31.addRoomFacility(rf7);
        KRNS31.addRoomFacility(rf8);
        KRNS31.addRoomFacility(rf9);
        KRNS31.addRoomFacility(rf10);
        KRNS31.addMinibarItem(m1);
        KRNS31.addMinibarItem(m2);
        KRNS31.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS31);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_501"));

        Room KRNS32 = new Room("KRN_502", "502", "Standard", 2, "Occupied", h3);
        KRNS32.addRoomFacility(rf1);
        KRNS32.addRoomFacility(rf2);
        KRNS32.addRoomFacility(rf3);
        KRNS32.addRoomFacility(rf4);
        KRNS32.addRoomFacility(rf5);
        KRNS32.addRoomFacility(rf6);
        KRNS32.addRoomFacility(rf7);
        KRNS32.addRoomFacility(rf8);
        KRNS32.addRoomFacility(rf9);
        KRNS32.addRoomFacility(rf10);
        KRNS32.addMinibarItem(m1);
        KRNS32.addMinibarItem(m2);
        KRNS32.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS32);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_502"));

        Room KRNS33 = new Room("KRN_503", "503", "Standard", 2, "Available", h3);
        KRNS33.addRoomFacility(rf1);
        KRNS33.addRoomFacility(rf2);
        KRNS33.addRoomFacility(rf3);
        KRNS33.addRoomFacility(rf4);
        KRNS33.addRoomFacility(rf5);
        KRNS33.addRoomFacility(rf6);
        KRNS33.addRoomFacility(rf7);
        KRNS33.addRoomFacility(rf8);
        KRNS33.addRoomFacility(rf9);
        KRNS33.addRoomFacility(rf10);
        KRNS33.addMinibarItem(m1);
        KRNS33.addMinibarItem(m2);
        KRNS33.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS33);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_503"));

        Room KRNS34 = new Room("KRN_504", "504", "Standard", 2, "Available", h3);
        KRNS34.addRoomFacility(rf1);
        KRNS34.addRoomFacility(rf2);
        KRNS34.addRoomFacility(rf3);
        KRNS34.addRoomFacility(rf4);
        KRNS34.addRoomFacility(rf5);
        KRNS34.addRoomFacility(rf6);
        KRNS34.addRoomFacility(rf7);
        KRNS34.addRoomFacility(rf8);
        KRNS34.addRoomFacility(rf9);
        KRNS34.addRoomFacility(rf10);
        KRNS34.addMinibarItem(m1);
        KRNS34.addMinibarItem(m2);
        KRNS34.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS34);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_504"));

        Room KRNS35 = new Room("KRN_505", "505", "Standard", 2, "Available", h3);
        KRNS35.addRoomFacility(rf1);
        KRNS35.addRoomFacility(rf2);
        KRNS35.addRoomFacility(rf3);
        KRNS35.addRoomFacility(rf4);
        KRNS35.addRoomFacility(rf5);
        KRNS35.addRoomFacility(rf6);
        KRNS35.addRoomFacility(rf7);
        KRNS35.addRoomFacility(rf8);
        KRNS35.addRoomFacility(rf9);
        KRNS35.addRoomFacility(rf10);
        KRNS35.addMinibarItem(m1);
        KRNS35.addMinibarItem(m2);
        KRNS35.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS35);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_505"));

        Room KRNS36 = new Room("KRN_506", "506", "Standard", 2, "Occupied", h3);
        KRNS36.addRoomFacility(rf1);
        KRNS36.addRoomFacility(rf2);
        KRNS36.addRoomFacility(rf3);
        KRNS36.addRoomFacility(rf4);
        KRNS36.addRoomFacility(rf5);
        KRNS36.addRoomFacility(rf6);
        KRNS36.addRoomFacility(rf7);
        KRNS36.addRoomFacility(rf8);
        KRNS36.addRoomFacility(rf9);
        KRNS36.addRoomFacility(rf10);
        KRNS36.addMinibarItem(m1);
        KRNS36.addMinibarItem(m2);
        KRNS36.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS36);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_506"));

        Room KRNS37 = new Room("KRN_507", "507", "Standard", 2, "Available", h3);
        KRNS37.addRoomFacility(rf1);
        KRNS37.addRoomFacility(rf2);
        KRNS37.addRoomFacility(rf3);
        KRNS37.addRoomFacility(rf4);
        KRNS37.addRoomFacility(rf5);
        KRNS37.addRoomFacility(rf6);
        KRNS37.addRoomFacility(rf7);
        KRNS37.addRoomFacility(rf8);
        KRNS37.addRoomFacility(rf9);
        KRNS37.addRoomFacility(rf10);
        KRNS37.addMinibarItem(m1);
        KRNS37.addMinibarItem(m2);
        KRNS37.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS37);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_507"));

        Room KRNS38 = new Room("KRN_508", "508", "Standard", 2, "Available", h3);
        KRNS38.addRoomFacility(rf1);
        KRNS38.addRoomFacility(rf2);
        KRNS38.addRoomFacility(rf3);
        KRNS38.addRoomFacility(rf4);
        KRNS38.addRoomFacility(rf5);
        KRNS38.addRoomFacility(rf6);
        KRNS38.addRoomFacility(rf7);
        KRNS38.addRoomFacility(rf8);
        KRNS38.addRoomFacility(rf9);
        KRNS38.addRoomFacility(rf10);
        KRNS38.addMinibarItem(m1);
        KRNS38.addMinibarItem(m2);
        KRNS38.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS38);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_508"));

        Room KRNS41 = new Room("KRN_601", "601", "Standard", 2, "Available", h3);
        KRNS41.addRoomFacility(rf1);
        KRNS41.addRoomFacility(rf2);
        KRNS41.addRoomFacility(rf3);
        KRNS41.addRoomFacility(rf4);
        KRNS41.addRoomFacility(rf5);
        KRNS41.addRoomFacility(rf6);
        KRNS41.addRoomFacility(rf7);
        KRNS41.addRoomFacility(rf8);
        KRNS41.addRoomFacility(rf9);
        KRNS41.addRoomFacility(rf10);
        KRNS41.addMinibarItem(m1);
        KRNS41.addMinibarItem(m2);
        KRNS41.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS41);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_601"));

        Room KRNS42 = new Room("KRN_602", "602", "Standard", 2, "Occupied", h3);
        KRNS42.addRoomFacility(rf1);
        KRNS42.addRoomFacility(rf2);
        KRNS42.addRoomFacility(rf3);
        KRNS42.addRoomFacility(rf4);
        KRNS42.addRoomFacility(rf5);
        KRNS42.addRoomFacility(rf6);
        KRNS42.addRoomFacility(rf7);
        KRNS42.addRoomFacility(rf8);
        KRNS42.addRoomFacility(rf9);
        KRNS42.addRoomFacility(rf10);
        KRNS42.addMinibarItem(m1);
        KRNS42.addMinibarItem(m2);
        KRNS42.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS42);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_602"));

        Room KRNS43 = new Room("KRN_603", "603", "Standard", 2, "Available", h3);
        KRNS43.addRoomFacility(rf1);
        KRNS43.addRoomFacility(rf2);
        KRNS43.addRoomFacility(rf3);
        KRNS43.addRoomFacility(rf4);
        KRNS43.addRoomFacility(rf5);
        KRNS43.addRoomFacility(rf6);
        KRNS43.addRoomFacility(rf7);
        KRNS43.addRoomFacility(rf8);
        KRNS43.addRoomFacility(rf9);
        KRNS43.addRoomFacility(rf10);
        KRNS43.addMinibarItem(m1);
        KRNS43.addMinibarItem(m2);
        KRNS43.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS43);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_603"));

        Room KRNS44 = new Room("KRN_604", "604", "Standard", 2, "Available", h3);
        KRNS44.addRoomFacility(rf1);
        KRNS44.addRoomFacility(rf2);
        KRNS44.addRoomFacility(rf3);
        KRNS44.addRoomFacility(rf4);
        KRNS44.addRoomFacility(rf5);
        KRNS44.addRoomFacility(rf6);
        KRNS44.addRoomFacility(rf7);
        KRNS44.addRoomFacility(rf8);
        KRNS44.addRoomFacility(rf9);
        KRNS44.addRoomFacility(rf10);
        KRNS44.addMinibarItem(m1);
        KRNS44.addMinibarItem(m2);
        KRNS44.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS44);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_604"));

        Room KRNS45 = new Room("KRN_605", "605", "Standard", 2, "Occupied", h3);
        KRNS45.addRoomFacility(rf1);
        KRNS45.addRoomFacility(rf2);
        KRNS45.addRoomFacility(rf3);
        KRNS45.addRoomFacility(rf4);
        KRNS45.addRoomFacility(rf5);
        KRNS45.addRoomFacility(rf6);
        KRNS45.addRoomFacility(rf7);
        KRNS45.addRoomFacility(rf8);
        KRNS45.addRoomFacility(rf9);
        KRNS45.addRoomFacility(rf10);
        KRNS45.addMinibarItem(m1);
        KRNS45.addMinibarItem(m2);
        KRNS45.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS45);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_605"));

        Room KRNS46 = new Room("KRN_606", "606", "Standard", 2, "Available", h3);
        KRNS46.addRoomFacility(rf1);
        KRNS46.addRoomFacility(rf2);
        KRNS46.addRoomFacility(rf3);
        KRNS46.addRoomFacility(rf4);
        KRNS46.addRoomFacility(rf5);
        KRNS46.addRoomFacility(rf6);
        KRNS46.addRoomFacility(rf7);
        KRNS46.addRoomFacility(rf8);
        KRNS46.addRoomFacility(rf9);
        KRNS46.addRoomFacility(rf10);
        KRNS46.addMinibarItem(m1);
        KRNS46.addMinibarItem(m2);
        KRNS46.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS46);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_606"));

        Room KRNS47 = new Room("KRN_607", "607", "Standard", 2, "Available", h3);
        KRNS47.addRoomFacility(rf1);
        KRNS47.addRoomFacility(rf2);
        KRNS47.addRoomFacility(rf3);
        KRNS47.addRoomFacility(rf4);
        KRNS47.addRoomFacility(rf5);
        KRNS47.addRoomFacility(rf6);
        KRNS47.addRoomFacility(rf7);
        KRNS47.addRoomFacility(rf8);
        KRNS47.addRoomFacility(rf9);
        KRNS47.addRoomFacility(rf10);
        KRNS47.addMinibarItem(m1);
        KRNS47.addMinibarItem(m2);
        KRNS47.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS47);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_607"));

        Room KRNS48 = new Room("KRN_608", "608", "Standard", 2, "Occupied", h3);
        KRNS48.addRoomFacility(rf1);
        KRNS48.addRoomFacility(rf2);
        KRNS48.addRoomFacility(rf3);
        KRNS48.addRoomFacility(rf4);
        KRNS48.addRoomFacility(rf5);
        KRNS48.addRoomFacility(rf6);
        KRNS48.addRoomFacility(rf7);
        KRNS48.addRoomFacility(rf8);
        KRNS48.addRoomFacility(rf9);
        KRNS48.addRoomFacility(rf10);
        KRNS48.addMinibarItem(m1);
        KRNS48.addMinibarItem(m2);
        KRNS48.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNS48);
        h3.addRoom(roomSessionLocal.getRoomByName("KRN_608"));

        Room KRN_1001 = new Room("KRN_1001", "1001", "Deluxe", 3, "Available", h3);

        KRN_1001.addRoomFacility(rf1);
        KRN_1001.addRoomFacility(rf2);
        KRN_1001.addRoomFacility(rf3);
        KRN_1001.addRoomFacility(rf4);
        KRN_1001.addRoomFacility(rf5);
        KRN_1001.addRoomFacility(rf6);
        KRN_1001.addRoomFacility(rf7);
        KRN_1001.addRoomFacility(rf8);
        KRN_1001.addRoomFacility(rf9);
        KRN_1001.addRoomFacility(rf10);
        KRN_1001.addRoomFacility(rf11);
        KRN_1001.addRoomFacility(rf12);
        KRN_1001.addRoomFacility(rf13);

        KRN_1001.addMinibarItem(m1);
        KRN_1001.addMinibarItem(m2);
        KRN_1001.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRN_1001);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_1001"));

        Room KRN_1002 = new Room("KRN_1002", "1002", "Deluxe", 3, "Available", h3);

        KRN_1002.addRoomFacility(rf1);
        KRN_1002.addRoomFacility(rf2);
        KRN_1002.addRoomFacility(rf3);
        KRN_1002.addRoomFacility(rf4);
        KRN_1002.addRoomFacility(rf5);
        KRN_1002.addRoomFacility(rf6);
        KRN_1002.addRoomFacility(rf7);
        KRN_1002.addRoomFacility(rf8);
        KRN_1002.addRoomFacility(rf9);
        KRN_1002.addRoomFacility(rf10);
        KRN_1002.addRoomFacility(rf11);
        KRN_1002.addRoomFacility(rf12);
        KRN_1002.addRoomFacility(rf13);

        KRN_1002.addMinibarItem(m1);
        KRN_1002.addMinibarItem(m2);
        KRN_1002.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRN_1002);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_1002"));

        Room KRN_1003 = new Room("KRN_1003", "1003", "Deluxe", 3, "Unavailable", h3);

        KRN_1003.addRoomFacility(rf1);
        KRN_1003.addRoomFacility(rf2);
        KRN_1003.addRoomFacility(rf3);
        KRN_1003.addRoomFacility(rf4);
        KRN_1003.addRoomFacility(rf5);
        KRN_1003.addRoomFacility(rf6);
        KRN_1003.addRoomFacility(rf7);
        KRN_1003.addRoomFacility(rf8);
        KRN_1003.addRoomFacility(rf9);
        KRN_1003.addRoomFacility(rf10);
        KRN_1003.addRoomFacility(rf11);
        KRN_1003.addRoomFacility(rf12);
        KRN_1003.addRoomFacility(rf13);

        KRN_1003.addMinibarItem(m1);
        KRN_1003.addMinibarItem(m2);
        KRN_1003.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRN_1003);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_1003"));

        Room KRN_1004 = new Room("KRN_1004", "1004", "Deluxe", 3, "Occupied", h3);

        KRN_1004.addRoomFacility(rf1);
        KRN_1004.addRoomFacility(rf2);
        KRN_1004.addRoomFacility(rf3);
        KRN_1004.addRoomFacility(rf4);
        KRN_1004.addRoomFacility(rf5);
        KRN_1004.addRoomFacility(rf6);
        KRN_1004.addRoomFacility(rf7);
        KRN_1004.addRoomFacility(rf8);
        KRN_1004.addRoomFacility(rf9);
        KRN_1004.addRoomFacility(rf10);
        KRN_1004.addRoomFacility(rf11);
        KRN_1004.addRoomFacility(rf12);
        KRN_1004.addRoomFacility(rf13);

        KRN_1004.addMinibarItem(m1);
        KRN_1004.addMinibarItem(m2);
        KRN_1004.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRN_1004);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_1004"));

        Room KRN_1005 = new Room("KRN_1005", "1005", "Deluxe", 3, "Available", h3);

        KRN_1005.addRoomFacility(rf1);
        KRN_1005.addRoomFacility(rf2);
        KRN_1005.addRoomFacility(rf3);
        KRN_1005.addRoomFacility(rf4);
        KRN_1005.addRoomFacility(rf5);
        KRN_1005.addRoomFacility(rf6);
        KRN_1005.addRoomFacility(rf7);
        KRN_1005.addRoomFacility(rf8);
        KRN_1005.addRoomFacility(rf9);
        KRN_1005.addRoomFacility(rf10);
        KRN_1005.addRoomFacility(rf11);
        KRN_1005.addRoomFacility(rf12);
        KRN_1005.addRoomFacility(rf13);

        KRN_1005.addMinibarItem(m1);
        KRN_1005.addMinibarItem(m2);
        KRN_1005.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRN_1005);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_1005"));

        Room KRN_1101 = new Room("KRN_1101", "1101", "Deluxe", 3, "Available", h3);

        KRN_1101.addRoomFacility(rf1);
        KRN_1101.addRoomFacility(rf2);
        KRN_1101.addRoomFacility(rf3);
        KRN_1101.addRoomFacility(rf4);
        KRN_1101.addRoomFacility(rf5);
        KRN_1101.addRoomFacility(rf6);
        KRN_1101.addRoomFacility(rf7);
        KRN_1101.addRoomFacility(rf8);
        KRN_1101.addRoomFacility(rf9);
        KRN_1101.addRoomFacility(rf10);
        KRN_1101.addRoomFacility(rf11);
        KRN_1101.addRoomFacility(rf12);
        KRN_1101.addRoomFacility(rf13);

        KRN_1101.addMinibarItem(m1);
        KRN_1101.addMinibarItem(m2);
        KRN_1101.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRN_1101);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_1101"));

        Room KRN_1102 = new Room("KRN_1102", "1102", "Deluxe", 3, "Occupied", h3);

        KRN_1102.addRoomFacility(rf1);
        KRN_1102.addRoomFacility(rf2);
        KRN_1102.addRoomFacility(rf3);
        KRN_1102.addRoomFacility(rf4);
        KRN_1102.addRoomFacility(rf5);
        KRN_1102.addRoomFacility(rf6);
        KRN_1102.addRoomFacility(rf7);
        KRN_1102.addRoomFacility(rf8);
        KRN_1102.addRoomFacility(rf9);
        KRN_1102.addRoomFacility(rf10);
        KRN_1102.addRoomFacility(rf11);
        KRN_1102.addRoomFacility(rf12);
        KRN_1102.addRoomFacility(rf13);

        KRN_1102.addMinibarItem(m1);
        KRN_1102.addMinibarItem(m2);
        KRN_1102.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRN_1102);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_1102"));

        Room KRN_1103 = new Room("KRN_1103", "1103", "Deluxe", 3, "Available", h3);

        KRN_1103.addRoomFacility(rf1);
        KRN_1103.addRoomFacility(rf2);
        KRN_1103.addRoomFacility(rf3);
        KRN_1103.addRoomFacility(rf4);
        KRN_1103.addRoomFacility(rf5);
        KRN_1103.addRoomFacility(rf6);
        KRN_1103.addRoomFacility(rf7);
        KRN_1103.addRoomFacility(rf8);
        KRN_1103.addRoomFacility(rf9);
        KRN_1103.addRoomFacility(rf10);
        KRN_1103.addRoomFacility(rf11);
        KRN_1103.addRoomFacility(rf12);
        KRN_1103.addRoomFacility(rf13);

        KRN_1103.addMinibarItem(m1);
        KRN_1103.addMinibarItem(m2);
        KRN_1103.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRN_1103);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_1103"));

        Room KRN_1104 = new Room("KRN_1104", "1104", "Deluxe", 3, "Available", h3);

        KRN_1104.addRoomFacility(rf1);
        KRN_1104.addRoomFacility(rf2);
        KRN_1104.addRoomFacility(rf3);
        KRN_1104.addRoomFacility(rf4);
        KRN_1104.addRoomFacility(rf5);
        KRN_1104.addRoomFacility(rf6);
        KRN_1104.addRoomFacility(rf7);
        KRN_1104.addRoomFacility(rf8);
        KRN_1104.addRoomFacility(rf9);
        KRN_1104.addRoomFacility(rf10);
        KRN_1104.addRoomFacility(rf11);
        KRN_1104.addRoomFacility(rf12);
        KRN_1104.addRoomFacility(rf13);

        KRN_1104.addMinibarItem(m1);
        KRN_1104.addMinibarItem(m2);
        KRN_1104.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRN_1104);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_1104"));

        Room KRN_1105 = new Room("KRN_1105", "1105", "Deluxe", 3, "Unavailable", h3);

        KRN_1105.addRoomFacility(rf1);
        KRN_1105.addRoomFacility(rf2);
        KRN_1105.addRoomFacility(rf3);
        KRN_1105.addRoomFacility(rf4);
        KRN_1105.addRoomFacility(rf5);
        KRN_1105.addRoomFacility(rf6);
        KRN_1105.addRoomFacility(rf7);
        KRN_1105.addRoomFacility(rf8);
        KRN_1105.addRoomFacility(rf9);
        KRN_1105.addRoomFacility(rf10);
        KRN_1105.addRoomFacility(rf11);
        KRN_1105.addRoomFacility(rf12);
        KRN_1105.addRoomFacility(rf13);

        KRN_1105.addMinibarItem(m1);
        KRN_1105.addMinibarItem(m2);
        KRN_1105.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRN_1105);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_1105"));

        Room KRN_701 = new Room("KRN_701", "701", "Premium", 4, "Occupied", h3);

        KRN_701.addRoomFacility(rf1);
        KRN_701.addRoomFacility(rf2);
        KRN_701.addRoomFacility(rf3);
        KRN_701.addRoomFacility(rf4);
        KRN_701.addRoomFacility(rf5);
        KRN_701.addRoomFacility(rf6);
        KRN_701.addRoomFacility(rf7);
        KRN_701.addRoomFacility(rf8);
        KRN_701.addRoomFacility(rf9);
        KRN_701.addRoomFacility(rf10);
        KRN_701.addRoomFacility(rf11);
        KRN_701.addRoomFacility(rf12);
        KRN_701.addRoomFacility(rf13);
        KRN_701.addRoomFacility(rf14);
        KRN_701.addRoomFacility(rf15);

        KRN_701.addMinibarItem(m1);
        KRN_701.addMinibarItem(m2);
        KRN_701.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRN_701);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_701"));

        Room KRN_702 = new Room("KRN_702", "702", "Premium", 4, "Available", h3);

        KRN_702.addRoomFacility(rf1);
        KRN_702.addRoomFacility(rf2);
        KRN_702.addRoomFacility(rf3);
        KRN_702.addRoomFacility(rf4);
        KRN_702.addRoomFacility(rf5);
        KRN_702.addRoomFacility(rf6);
        KRN_702.addRoomFacility(rf7);
        KRN_702.addRoomFacility(rf8);
        KRN_702.addRoomFacility(rf9);
        KRN_702.addRoomFacility(rf10);
        KRN_702.addRoomFacility(rf11);
        KRN_702.addRoomFacility(rf12);
        KRN_702.addRoomFacility(rf13);
        KRN_702.addRoomFacility(rf14);
        KRN_702.addRoomFacility(rf15);

        KRN_702.addMinibarItem(m1);
        KRN_702.addMinibarItem(m2);
        KRN_702.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRN_702);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_702"));

        Room KRN_703 = new Room("KRN_703", "703", "Premium", 4, "Occupied", h3);

        KRN_703.addRoomFacility(rf1);
        KRN_703.addRoomFacility(rf2);
        KRN_703.addRoomFacility(rf3);
        KRN_703.addRoomFacility(rf4);
        KRN_703.addRoomFacility(rf5);
        KRN_703.addRoomFacility(rf6);
        KRN_703.addRoomFacility(rf7);
        KRN_703.addRoomFacility(rf8);
        KRN_703.addRoomFacility(rf9);
        KRN_703.addRoomFacility(rf10);
        KRN_703.addRoomFacility(rf11);
        KRN_703.addRoomFacility(rf12);
        KRN_703.addRoomFacility(rf13);
        KRN_703.addRoomFacility(rf14);
        KRN_703.addRoomFacility(rf15);

        KRN_703.addMinibarItem(m1);
        KRN_703.addMinibarItem(m2);
        KRN_703.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRN_703);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_703"));

        Room KRN_704 = new Room("KRN_704", "704", "Premium", 4, "Available", h3);

        KRN_704.addRoomFacility(rf1);
        KRN_704.addRoomFacility(rf2);
        KRN_704.addRoomFacility(rf3);
        KRN_704.addRoomFacility(rf4);
        KRN_704.addRoomFacility(rf5);
        KRN_704.addRoomFacility(rf6);
        KRN_704.addRoomFacility(rf7);
        KRN_704.addRoomFacility(rf8);
        KRN_704.addRoomFacility(rf9);
        KRN_704.addRoomFacility(rf10);
        KRN_704.addRoomFacility(rf11);
        KRN_704.addRoomFacility(rf12);
        KRN_704.addRoomFacility(rf13);
        KRN_704.addRoomFacility(rf14);
        KRN_704.addRoomFacility(rf15);

        KRN_704.addMinibarItem(m1);
        KRN_704.addMinibarItem(m2);
        KRN_704.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRN_704);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_704"));

        Room KRN_705 = new Room("KRN_705", "705", "Premium", 4, "Available", h3);

        KRN_705.addRoomFacility(rf1);
        KRN_705.addRoomFacility(rf2);
        KRN_705.addRoomFacility(rf3);
        KRN_705.addRoomFacility(rf4);
        KRN_705.addRoomFacility(rf5);
        KRN_705.addRoomFacility(rf6);
        KRN_705.addRoomFacility(rf7);
        KRN_705.addRoomFacility(rf8);
        KRN_705.addRoomFacility(rf9);
        KRN_705.addRoomFacility(rf10);
        KRN_705.addRoomFacility(rf11);
        KRN_705.addRoomFacility(rf12);
        KRN_705.addRoomFacility(rf13);
        KRN_705.addRoomFacility(rf14);
        KRN_705.addRoomFacility(rf15);

        KRN_705.addMinibarItem(m1);
        KRN_705.addMinibarItem(m2);
        KRN_705.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRN_705);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_705"));

        Room KRN_706 = new Room("KRN_706", "706", "Premium", 4, "Occupied", h3);

        KRN_706.addRoomFacility(rf1);
        KRN_706.addRoomFacility(rf2);
        KRN_706.addRoomFacility(rf3);
        KRN_706.addRoomFacility(rf4);
        KRN_706.addRoomFacility(rf5);
        KRN_706.addRoomFacility(rf6);
        KRN_706.addRoomFacility(rf7);
        KRN_706.addRoomFacility(rf8);
        KRN_706.addRoomFacility(rf9);
        KRN_706.addRoomFacility(rf10);
        KRN_706.addRoomFacility(rf11);
        KRN_706.addRoomFacility(rf12);
        KRN_706.addRoomFacility(rf13);
        KRN_706.addRoomFacility(rf14);
        KRN_706.addRoomFacility(rf15);

        KRN_706.addMinibarItem(m1);
        KRN_706.addMinibarItem(m2);
        KRN_706.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRN_706);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_706"));

        Room KRN_707 = new Room("KRN_707", "707", "Premium", 4, "Available", h3);

        KRN_707.addRoomFacility(rf1);
        KRN_707.addRoomFacility(rf2);
        KRN_707.addRoomFacility(rf3);
        KRN_707.addRoomFacility(rf4);
        KRN_707.addRoomFacility(rf5);
        KRN_707.addRoomFacility(rf6);
        KRN_707.addRoomFacility(rf7);
        KRN_707.addRoomFacility(rf8);
        KRN_707.addRoomFacility(rf9);
        KRN_707.addRoomFacility(rf10);
        KRN_707.addRoomFacility(rf11);
        KRN_707.addRoomFacility(rf12);
        KRN_707.addRoomFacility(rf13);
        KRN_707.addRoomFacility(rf14);
        KRN_707.addRoomFacility(rf15);

        KRN_707.addMinibarItem(m1);
        KRN_707.addMinibarItem(m2);
        KRN_707.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRN_707);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_707"));

        Room KRN_801 = new Room("KRN_801", "801", "Premium", 4, "Available", h3);

        KRN_801.addRoomFacility(rf1);
        KRN_801.addRoomFacility(rf2);
        KRN_801.addRoomFacility(rf3);
        KRN_801.addRoomFacility(rf4);
        KRN_801.addRoomFacility(rf5);
        KRN_801.addRoomFacility(rf6);
        KRN_801.addRoomFacility(rf7);
        KRN_801.addRoomFacility(rf8);
        KRN_801.addRoomFacility(rf9);
        KRN_801.addRoomFacility(rf10);
        KRN_801.addRoomFacility(rf11);
        KRN_801.addRoomFacility(rf12);
        KRN_801.addRoomFacility(rf13);
        KRN_801.addRoomFacility(rf14);
        KRN_801.addRoomFacility(rf15);

        KRN_801.addMinibarItem(m1);
        KRN_801.addMinibarItem(m2);
        KRN_801.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRN_801);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_801"));

        Room KRN_802 = new Room("KRN_802", "802", "Premium", 4, "Occupied", h3);

        KRN_802.addRoomFacility(rf1);
        KRN_802.addRoomFacility(rf2);
        KRN_802.addRoomFacility(rf3);
        KRN_802.addRoomFacility(rf4);
        KRN_802.addRoomFacility(rf5);
        KRN_802.addRoomFacility(rf6);
        KRN_802.addRoomFacility(rf7);
        KRN_802.addRoomFacility(rf8);
        KRN_802.addRoomFacility(rf9);
        KRN_802.addRoomFacility(rf10);
        KRN_802.addRoomFacility(rf11);
        KRN_802.addRoomFacility(rf12);
        KRN_802.addRoomFacility(rf13);
        KRN_802.addRoomFacility(rf14);
        KRN_802.addRoomFacility(rf15);

        KRN_802.addMinibarItem(m1);
        KRN_802.addMinibarItem(m2);
        KRN_802.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRN_802);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_802"));

        Room KRN_803 = new Room("KRN_803", "803", "Premium", 4, "Available", h3);

        KRN_803.addRoomFacility(rf1);
        KRN_803.addRoomFacility(rf2);
        KRN_803.addRoomFacility(rf3);
        KRN_803.addRoomFacility(rf4);
        KRN_803.addRoomFacility(rf5);
        KRN_803.addRoomFacility(rf6);
        KRN_803.addRoomFacility(rf7);
        KRN_803.addRoomFacility(rf8);
        KRN_803.addRoomFacility(rf9);
        KRN_803.addRoomFacility(rf10);
        KRN_803.addRoomFacility(rf11);
        KRN_803.addRoomFacility(rf12);
        KRN_803.addRoomFacility(rf13);
        KRN_803.addRoomFacility(rf14);
        KRN_803.addRoomFacility(rf15);

        KRN_803.addMinibarItem(m1);
        KRN_803.addMinibarItem(m2);
        KRN_803.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRN_803);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_803"));

        Room KRN_804 = new Room("KRN_804", "804", "Premium", 4, "Available", h3);

        KRN_804.addRoomFacility(rf1);
        KRN_804.addRoomFacility(rf2);
        KRN_804.addRoomFacility(rf3);
        KRN_804.addRoomFacility(rf4);
        KRN_804.addRoomFacility(rf5);
        KRN_804.addRoomFacility(rf6);
        KRN_804.addRoomFacility(rf7);
        KRN_804.addRoomFacility(rf8);
        KRN_804.addRoomFacility(rf9);
        KRN_804.addRoomFacility(rf10);
        KRN_804.addRoomFacility(rf11);
        KRN_804.addRoomFacility(rf12);
        KRN_804.addRoomFacility(rf13);
        KRN_804.addRoomFacility(rf14);
        KRN_804.addRoomFacility(rf15);

        KRN_804.addMinibarItem(m1);
        KRN_804.addMinibarItem(m2);
        KRN_804.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRN_804);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_804"));

        Room KRN_805 = new Room("KRN_805", "805", "Premium", 4, "Occupied", h3);

        KRN_805.addRoomFacility(rf1);
        KRN_805.addRoomFacility(rf2);
        KRN_805.addRoomFacility(rf3);
        KRN_805.addRoomFacility(rf4);
        KRN_805.addRoomFacility(rf5);
        KRN_805.addRoomFacility(rf6);
        KRN_805.addRoomFacility(rf7);
        KRN_805.addRoomFacility(rf8);
        KRN_805.addRoomFacility(rf9);
        KRN_805.addRoomFacility(rf10);
        KRN_805.addRoomFacility(rf11);
        KRN_805.addRoomFacility(rf12);
        KRN_805.addRoomFacility(rf13);
        KRN_805.addRoomFacility(rf14);
        KRN_805.addRoomFacility(rf15);

        KRN_805.addMinibarItem(m1);
        KRN_805.addMinibarItem(m2);
        KRN_805.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRN_805);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_805"));

        Room KRN_806 = new Room("KRN_806", "806", "Premium", 4, "Available", h3);

        KRN_806.addRoomFacility(rf1);
        KRN_806.addRoomFacility(rf2);
        KRN_806.addRoomFacility(rf3);
        KRN_806.addRoomFacility(rf4);
        KRN_806.addRoomFacility(rf5);
        KRN_806.addRoomFacility(rf6);
        KRN_806.addRoomFacility(rf7);
        KRN_806.addRoomFacility(rf8);
        KRN_806.addRoomFacility(rf9);
        KRN_806.addRoomFacility(rf10);
        KRN_806.addRoomFacility(rf11);
        KRN_806.addRoomFacility(rf12);
        KRN_806.addRoomFacility(rf13);
        KRN_806.addRoomFacility(rf14);
        KRN_806.addRoomFacility(rf15);

        KRN_806.addMinibarItem(m1);
        KRN_806.addMinibarItem(m2);
        KRN_806.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRN_806);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_806"));

        Room KRN_807 = new Room("KRN_807", "807", "Premium", 4, "Occupied", h3);

        KRN_807.addRoomFacility(rf1);
        KRN_807.addRoomFacility(rf2);
        KRN_807.addRoomFacility(rf3);
        KRN_807.addRoomFacility(rf4);
        KRN_807.addRoomFacility(rf5);
        KRN_807.addRoomFacility(rf6);
        KRN_807.addRoomFacility(rf7);
        KRN_807.addRoomFacility(rf8);
        KRN_807.addRoomFacility(rf9);
        KRN_807.addRoomFacility(rf10);
        KRN_807.addRoomFacility(rf11);
        KRN_807.addRoomFacility(rf12);
        KRN_807.addRoomFacility(rf13);
        KRN_807.addRoomFacility(rf14);
        KRN_807.addRoomFacility(rf15);

        KRN_807.addMinibarItem(m1);
        KRN_807.addMinibarItem(m2);
        KRN_807.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRN_807);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_807"));

        Room KRN_901 = new Room("KRN_901", "901", "Premium", 4, "Unavailable", h3);

        KRN_901.addRoomFacility(rf1);
        KRN_901.addRoomFacility(rf2);
        KRN_901.addRoomFacility(rf3);
        KRN_901.addRoomFacility(rf4);
        KRN_901.addRoomFacility(rf5);
        KRN_901.addRoomFacility(rf6);
        KRN_901.addRoomFacility(rf7);
        KRN_901.addRoomFacility(rf8);
        KRN_901.addRoomFacility(rf9);
        KRN_901.addRoomFacility(rf10);
        KRN_901.addRoomFacility(rf11);
        KRN_901.addRoomFacility(rf12);
        KRN_901.addRoomFacility(rf13);
        KRN_901.addRoomFacility(rf14);
        KRN_901.addRoomFacility(rf15);

        KRN_901.addMinibarItem(m1);
        KRN_901.addMinibarItem(m2);
        KRN_901.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRN_901);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_901"));

        Room KRN_902 = new Room("KRN_902", "902", "Premium", 4, "Available", h3);

        KRN_902.addRoomFacility(rf1);
        KRN_902.addRoomFacility(rf2);
        KRN_902.addRoomFacility(rf3);
        KRN_902.addRoomFacility(rf4);
        KRN_902.addRoomFacility(rf5);
        KRN_902.addRoomFacility(rf6);
        KRN_902.addRoomFacility(rf7);
        KRN_902.addRoomFacility(rf8);
        KRN_902.addRoomFacility(rf9);
        KRN_902.addRoomFacility(rf10);
        KRN_902.addRoomFacility(rf11);
        KRN_902.addRoomFacility(rf12);
        KRN_902.addRoomFacility(rf13);
        KRN_902.addRoomFacility(rf14);
        KRN_902.addRoomFacility(rf15);

        KRN_902.addMinibarItem(m1);
        KRN_902.addMinibarItem(m2);
        KRN_902.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRN_902);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_902"));

        Room KRN_903 = new Room("KRN_903", "903", "Premium", 4, "Occupied", h3);

        KRN_903.addRoomFacility(rf1);
        KRN_903.addRoomFacility(rf2);
        KRN_903.addRoomFacility(rf3);
        KRN_903.addRoomFacility(rf4);
        KRN_903.addRoomFacility(rf5);
        KRN_903.addRoomFacility(rf6);
        KRN_903.addRoomFacility(rf7);
        KRN_903.addRoomFacility(rf8);
        KRN_903.addRoomFacility(rf9);
        KRN_903.addRoomFacility(rf10);
        KRN_903.addRoomFacility(rf11);
        KRN_903.addRoomFacility(rf12);
        KRN_903.addRoomFacility(rf13);
        KRN_903.addRoomFacility(rf14);
        KRN_903.addRoomFacility(rf15);

        KRN_903.addMinibarItem(m1);
        KRN_903.addMinibarItem(m2);
        KRN_903.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRN_903);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_903"));

        Room KRN_904 = new Room("KRN_904", "904", "Premium", 4, "Available", h3);

        KRN_904.addRoomFacility(rf1);
        KRN_904.addRoomFacility(rf2);
        KRN_904.addRoomFacility(rf3);
        KRN_904.addRoomFacility(rf4);
        KRN_904.addRoomFacility(rf5);
        KRN_904.addRoomFacility(rf6);
        KRN_904.addRoomFacility(rf7);
        KRN_904.addRoomFacility(rf8);
        KRN_904.addRoomFacility(rf9);
        KRN_904.addRoomFacility(rf10);
        KRN_904.addRoomFacility(rf11);
        KRN_904.addRoomFacility(rf12);
        KRN_904.addRoomFacility(rf13);
        KRN_904.addRoomFacility(rf14);
        KRN_904.addRoomFacility(rf15);

        KRN_904.addMinibarItem(m1);
        KRN_904.addMinibarItem(m2);
        KRN_904.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRN_904);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_904"));

        Room KRN_905 = new Room("KRN_905", "905", "Premium", 4, "Available", h3);

        KRN_905.addRoomFacility(rf1);
        KRN_905.addRoomFacility(rf2);
        KRN_905.addRoomFacility(rf3);
        KRN_905.addRoomFacility(rf4);
        KRN_905.addRoomFacility(rf5);
        KRN_905.addRoomFacility(rf6);
        KRN_905.addRoomFacility(rf7);
        KRN_905.addRoomFacility(rf8);
        KRN_905.addRoomFacility(rf9);
        KRN_905.addRoomFacility(rf10);
        KRN_905.addRoomFacility(rf11);
        KRN_905.addRoomFacility(rf12);
        KRN_905.addRoomFacility(rf13);
        KRN_905.addRoomFacility(rf14);
        KRN_905.addRoomFacility(rf15);

        KRN_905.addMinibarItem(m1);
        KRN_905.addMinibarItem(m2);
        KRN_905.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRN_905);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_905"));

        Room KRN_906 = new Room("KRN_906", "906", "Premium", 4, "Occupied", h3);

        KRN_906.addRoomFacility(rf1);
        KRN_906.addRoomFacility(rf2);
        KRN_906.addRoomFacility(rf3);
        KRN_906.addRoomFacility(rf4);
        KRN_906.addRoomFacility(rf5);
        KRN_906.addRoomFacility(rf6);
        KRN_906.addRoomFacility(rf7);
        KRN_906.addRoomFacility(rf8);
        KRN_906.addRoomFacility(rf9);
        KRN_906.addRoomFacility(rf10);
        KRN_906.addRoomFacility(rf11);
        KRN_906.addRoomFacility(rf12);
        KRN_906.addRoomFacility(rf13);
        KRN_906.addRoomFacility(rf14);
        KRN_906.addRoomFacility(rf15);

        KRN_906.addMinibarItem(m1);
        KRN_906.addMinibarItem(m2);
        KRN_906.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRN_906);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_906"));

        Room KRN_907 = new Room("KRN_907", "907", "Premium", 4, "Available", h3);

        KRN_907.addRoomFacility(rf1);
        KRN_907.addRoomFacility(rf2);
        KRN_907.addRoomFacility(rf3);
        KRN_907.addRoomFacility(rf4);
        KRN_907.addRoomFacility(rf5);
        KRN_907.addRoomFacility(rf6);
        KRN_907.addRoomFacility(rf7);
        KRN_907.addRoomFacility(rf8);
        KRN_907.addRoomFacility(rf9);
        KRN_907.addRoomFacility(rf10);
        KRN_907.addRoomFacility(rf11);
        KRN_907.addRoomFacility(rf12);
        KRN_907.addRoomFacility(rf13);
        KRN_907.addRoomFacility(rf14);
        KRN_907.addRoomFacility(rf15);

        KRN_907.addMinibarItem(m1);
        KRN_907.addMinibarItem(m2);
        KRN_907.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRN_907);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_907"));

        Room KRN_1201 = new Room("KRN_1201", "1201", "Suite", 4, "Occupied", h3);

        KRN_1201.addRoomFacility(rf1);
        KRN_1201.addRoomFacility(rf2);
        KRN_1201.addRoomFacility(rf3);
        KRN_1201.addRoomFacility(rf4);
        KRN_1201.addRoomFacility(rf5);
        KRN_1201.addRoomFacility(rf6);
        KRN_1201.addRoomFacility(rf7);
        KRN_1201.addRoomFacility(rf8);
        KRN_1201.addRoomFacility(rf9);
        KRN_1201.addRoomFacility(rf10);
        KRN_1201.addRoomFacility(rf11);
        KRN_1201.addRoomFacility(rf12);
        KRN_1201.addRoomFacility(rf13);
        KRN_1201.addRoomFacility(rf14);
        KRN_1201.addRoomFacility(rf15);
        KRN_1201.addRoomFacility(rf16);
        KRN_1201.addRoomFacility(rf17);
        KRN_1201.addRoomFacility(rf18);
        KRN_1201.addRoomFacility(rf19);

        KRN_1201.addMinibarItem(m1);
        KRN_1201.addMinibarItem(m2);
        KRN_1201.addMinibarItem(m3);
        KRN_1201.addMinibarItem(m4);
        KRN_1201.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRN_1201);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_1201"));

        Room KRN_1202 = new Room("KRN_1202", "1202", "Suite", 4, "Available", h3);

        KRN_1202.addRoomFacility(rf1);
        KRN_1202.addRoomFacility(rf2);
        KRN_1202.addRoomFacility(rf3);
        KRN_1202.addRoomFacility(rf4);
        KRN_1202.addRoomFacility(rf5);
        KRN_1202.addRoomFacility(rf6);
        KRN_1202.addRoomFacility(rf7);
        KRN_1202.addRoomFacility(rf8);
        KRN_1202.addRoomFacility(rf9);
        KRN_1202.addRoomFacility(rf10);
        KRN_1202.addRoomFacility(rf11);
        KRN_1202.addRoomFacility(rf12);
        KRN_1202.addRoomFacility(rf13);
        KRN_1202.addRoomFacility(rf14);
        KRN_1202.addRoomFacility(rf15);
        KRN_1202.addRoomFacility(rf16);
        KRN_1202.addRoomFacility(rf17);
        KRN_1202.addRoomFacility(rf18);
        KRN_1202.addRoomFacility(rf19);

        KRN_1202.addMinibarItem(m1);
        KRN_1202.addMinibarItem(m2);
        KRN_1202.addMinibarItem(m3);
        KRN_1202.addMinibarItem(m4);
        KRN_1202.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRN_1202);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_1202"));

        Room KRN_1203 = new Room("KRN_1203", "1203", "Suite", 4, "Available", h3);

        KRN_1203.addRoomFacility(rf1);
        KRN_1203.addRoomFacility(rf2);
        KRN_1203.addRoomFacility(rf3);
        KRN_1203.addRoomFacility(rf4);
        KRN_1203.addRoomFacility(rf5);
        KRN_1203.addRoomFacility(rf6);
        KRN_1203.addRoomFacility(rf7);
        KRN_1203.addRoomFacility(rf8);
        KRN_1203.addRoomFacility(rf9);
        KRN_1203.addRoomFacility(rf10);
        KRN_1203.addRoomFacility(rf11);
        KRN_1203.addRoomFacility(rf12);
        KRN_1203.addRoomFacility(rf13);
        KRN_1203.addRoomFacility(rf14);
        KRN_1203.addRoomFacility(rf15);
        KRN_1203.addRoomFacility(rf16);
        KRN_1203.addRoomFacility(rf17);
        KRN_1203.addRoomFacility(rf18);
        KRN_1203.addRoomFacility(rf19);

        KRN_1203.addMinibarItem(m1);
        KRN_1203.addMinibarItem(m2);
        KRN_1203.addMinibarItem(m3);
        KRN_1203.addMinibarItem(m4);
        KRN_1203.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRN_1203);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_1203"));

        Room KRN_1204 = new Room("KRN_1204", "1204", "Suite", 4, "Unavailable", h3);

        KRN_1204.addRoomFacility(rf1);
        KRN_1204.addRoomFacility(rf2);
        KRN_1204.addRoomFacility(rf3);
        KRN_1204.addRoomFacility(rf4);
        KRN_1204.addRoomFacility(rf5);
        KRN_1204.addRoomFacility(rf6);
        KRN_1204.addRoomFacility(rf7);
        KRN_1204.addRoomFacility(rf8);
        KRN_1204.addRoomFacility(rf9);
        KRN_1204.addRoomFacility(rf10);
        KRN_1204.addRoomFacility(rf11);
        KRN_1204.addRoomFacility(rf12);
        KRN_1204.addRoomFacility(rf13);
        KRN_1204.addRoomFacility(rf14);
        KRN_1204.addRoomFacility(rf15);
        KRN_1204.addRoomFacility(rf16);
        KRN_1204.addRoomFacility(rf17);
        KRN_1204.addRoomFacility(rf18);
        KRN_1204.addRoomFacility(rf19);

        KRN_1204.addMinibarItem(m1);
        KRN_1204.addMinibarItem(m2);
        KRN_1204.addMinibarItem(m3);
        KRN_1204.addMinibarItem(m4);
        KRN_1204.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRN_1204);

        h3.addRoom(roomSessionLocal.getRoomByName("KRN_1204"));

        em.flush();

    }

    public void initializeKRSRoom() throws NoResultException {
        Hotel h4 = hotelSessionLocal.getHotelByName("Kent Ridge South");
        MinibarItem m1 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Potato Chips");
        MinibarItem m2 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Chocolate");
        MinibarItem m3 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Mineral Water");
        MinibarItem m4 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Coke");
        MinibarItem m5 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Wine");
        RoomFacility rf1 = roomFacilitySessionLocal.getRoomFacilityByName("Wifi");
        RoomFacility rf2 = roomFacilitySessionLocal.getRoomFacilityByName("Minibar");
        RoomFacility rf3 = roomFacilitySessionLocal.getRoomFacilityByName("Aircon");
        RoomFacility rf4 = roomFacilitySessionLocal.getRoomFacilityByName("Cable TV");
        RoomFacility rf5 = roomFacilitySessionLocal.getRoomFacilityByName("Telephone");
        RoomFacility rf6 = roomFacilitySessionLocal.getRoomFacilityByName("Water Heater");
        RoomFacility rf7 = roomFacilitySessionLocal.getRoomFacilityByName("Bathroom with Shower Head");
        RoomFacility rf8 = roomFacilitySessionLocal.getRoomFacilityByName("HairDryer");
        RoomFacility rf9 = roomFacilitySessionLocal.getRoomFacilityByName("Safe Deposit Box");
        RoomFacility rf10 = roomFacilitySessionLocal.getRoomFacilityByName("Water Kettle with complimentary teas/coffee");
        RoomFacility rf11 = roomFacilitySessionLocal.getRoomFacilityByName("Room with Windows");
        RoomFacility rf12 = roomFacilitySessionLocal.getRoomFacilityByName("Bathtub");
        RoomFacility rf13 = roomFacilitySessionLocal.getRoomFacilityByName("Mini Fridge");
        RoomFacility rf14 = roomFacilitySessionLocal.getRoomFacilityByName("Coffee Machine");
        RoomFacility rf15 = roomFacilitySessionLocal.getRoomFacilityByName("Balcony");
        RoomFacility rf16 = roomFacilitySessionLocal.getRoomFacilityByName("Microwave");
        RoomFacility rf17 = roomFacilitySessionLocal.getRoomFacilityByName("Gaming Console");
        RoomFacility rf18 = roomFacilitySessionLocal.getRoomFacilityByName("Entertainment System");
        RoomFacility rf19 = roomFacilitySessionLocal.getRoomFacilityByName("Blackout Curtains");
        RoomFacility rf20 = roomFacilitySessionLocal.getRoomFacilityByName("High Ceiling");
        RoomFacility rf21 = roomFacilitySessionLocal.getRoomFacilityByName("Jaccuzi");
        RoomFacility rf22 = roomFacilitySessionLocal.getRoomFacilityByName("Kitchen");

        FunctionRoom KRSFR1 = new FunctionRoom("KRSFR1", 20, "Available", 20000.00, h4);
        FunctionRoom KRSFR2 = new FunctionRoom("KRSFR2", 100, "Available", 100000.00, h4);
        FunctionRoom KRSFR3 = new FunctionRoom("KRSFR3", 50, "Available", 50000.00, h4);
        FunctionRoom KRSFR4 = new FunctionRoom("KRSFR4", 70, "Available", 70000.00, h4);
        FunctionRoom KRSFR5 = new FunctionRoom("KRSFR5", 80, "Available", 80000.00, h4);

        functionRoomSessionLocal.createFunctionRoom(KRSFR1);
        functionRoomSessionLocal.createFunctionRoom(KRSFR2);
        functionRoomSessionLocal.createFunctionRoom(KRSFR3);
        functionRoomSessionLocal.createFunctionRoom(KRSFR4);
        functionRoomSessionLocal.createFunctionRoom(KRSFR5);

        h4.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRSFR1"));
        h4.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRSFR2"));
        h4.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRSFR3"));
        h4.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRSFR4"));
        h4.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRSFR5"));

        Room KRSS1 = new Room("KRS_201", "201", "Standard", 2, "Available", h4);
        KRSS1.addRoomFacility(rf1);
        KRSS1.addRoomFacility(rf2);
        KRSS1.addRoomFacility(rf3);
        KRSS1.addRoomFacility(rf4);
        KRSS1.addRoomFacility(rf5);
        KRSS1.addRoomFacility(rf6);
        KRSS1.addRoomFacility(rf7);
        KRSS1.addRoomFacility(rf8);
        KRSS1.addRoomFacility(rf9);
        KRSS1.addRoomFacility(rf10);
        KRSS1.addMinibarItem(m1);
        KRSS1.addMinibarItem(m2);
        KRSS1.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS1);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_201"));

        Room KRSS2 = new Room("KRS_202", "202", "Standard", 2, "Available", h4);
        KRSS2.addRoomFacility(rf1);
        KRSS2.addRoomFacility(rf2);
        KRSS2.addRoomFacility(rf3);
        KRSS2.addRoomFacility(rf4);
        KRSS2.addRoomFacility(rf5);
        KRSS2.addRoomFacility(rf6);
        KRSS2.addRoomFacility(rf7);
        KRSS2.addRoomFacility(rf8);
        KRSS2.addRoomFacility(rf9);
        KRSS2.addRoomFacility(rf10);
        KRSS2.addMinibarItem(m1);
        KRSS2.addMinibarItem(m2);
        KRSS2.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS2);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_202"));

        Room KRSS3 = new Room("KRS_203", "203", "Standard", 2, "Occupied", h4);
        KRSS3.addRoomFacility(rf1);
        KRSS3.addRoomFacility(rf2);
        KRSS3.addRoomFacility(rf3);
        KRSS3.addRoomFacility(rf4);
        KRSS3.addRoomFacility(rf5);
        KRSS3.addRoomFacility(rf6);
        KRSS3.addRoomFacility(rf7);
        KRSS3.addRoomFacility(rf8);
        KRSS3.addRoomFacility(rf9);
        KRSS3.addRoomFacility(rf10);
        KRSS3.addMinibarItem(m1);
        KRSS3.addMinibarItem(m2);
        KRSS3.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS3);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_203"));

        Room KRSS4 = new Room("KRS_204", "204", "Standard", 2, "Available", h4);
        KRSS4.addRoomFacility(rf1);
        KRSS4.addRoomFacility(rf2);
        KRSS4.addRoomFacility(rf3);
        KRSS4.addRoomFacility(rf4);
        KRSS4.addRoomFacility(rf5);
        KRSS4.addRoomFacility(rf6);
        KRSS4.addRoomFacility(rf7);
        KRSS4.addRoomFacility(rf8);
        KRSS4.addRoomFacility(rf9);
        KRSS4.addRoomFacility(rf10);
        KRSS4.addMinibarItem(m1);
        KRSS4.addMinibarItem(m2);
        KRSS4.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS4);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_204"));

        Room KRSS5 = new Room("KRS_205", "205", "Standard", 2, "Available", h4);
        KRSS5.addRoomFacility(rf1);
        KRSS5.addRoomFacility(rf2);
        KRSS5.addRoomFacility(rf3);
        KRSS5.addRoomFacility(rf4);
        KRSS5.addRoomFacility(rf5);
        KRSS5.addRoomFacility(rf6);
        KRSS5.addRoomFacility(rf7);
        KRSS5.addRoomFacility(rf8);
        KRSS5.addRoomFacility(rf9);
        KRSS5.addRoomFacility(rf10);
        KRSS5.addMinibarItem(m1);
        KRSS5.addMinibarItem(m2);
        KRSS5.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS5);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_205"));

        Room KRSS6 = new Room("KRS_206", "206", "Standard", 2, "Occupied", h4);
        KRSS6.addRoomFacility(rf1);
        KRSS6.addRoomFacility(rf2);
        KRSS6.addRoomFacility(rf3);
        KRSS6.addRoomFacility(rf4);
        KRSS6.addRoomFacility(rf5);
        KRSS6.addRoomFacility(rf6);
        KRSS6.addRoomFacility(rf7);
        KRSS6.addRoomFacility(rf8);
        KRSS6.addRoomFacility(rf9);
        KRSS6.addRoomFacility(rf10);
        KRSS6.addMinibarItem(m1);
        KRSS6.addMinibarItem(m2);
        KRSS6.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS6);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_206"));

        Room KRSS7 = new Room("KRS_207", "207", "Standard", 2, "Available", h4);
        KRSS7.addRoomFacility(rf1);
        KRSS7.addRoomFacility(rf2);
        KRSS7.addRoomFacility(rf3);
        KRSS7.addRoomFacility(rf4);
        KRSS7.addRoomFacility(rf5);
        KRSS7.addRoomFacility(rf6);
        KRSS7.addRoomFacility(rf7);
        KRSS7.addRoomFacility(rf8);
        KRSS7.addRoomFacility(rf9);
        KRSS7.addRoomFacility(rf10);
        KRSS7.addMinibarItem(m1);
        KRSS7.addMinibarItem(m2);
        KRSS7.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS7);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_207"));

        Room KRSS8 = new Room("KRS_208", "208", "Standard", 2, "Unavailable", h4);
        KRSS8.addRoomFacility(rf1);
        KRSS8.addRoomFacility(rf2);
        KRSS8.addRoomFacility(rf3);
        KRSS8.addRoomFacility(rf4);
        KRSS8.addRoomFacility(rf5);
        KRSS8.addRoomFacility(rf6);
        KRSS8.addRoomFacility(rf7);
        KRSS8.addRoomFacility(rf8);
        KRSS8.addRoomFacility(rf9);
        KRSS8.addRoomFacility(rf10);
        KRSS8.addMinibarItem(m1);
        KRSS8.addMinibarItem(m2);
        KRSS8.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS8);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_208"));

        Room KRSS11 = new Room("KRS_301", "301", "Standard", 2, "Available", h4);
        KRSS11.addRoomFacility(rf1);
        KRSS11.addRoomFacility(rf2);
        KRSS11.addRoomFacility(rf3);
        KRSS11.addRoomFacility(rf4);
        KRSS11.addRoomFacility(rf5);
        KRSS11.addRoomFacility(rf6);
        KRSS11.addRoomFacility(rf7);
        KRSS11.addRoomFacility(rf8);
        KRSS11.addRoomFacility(rf9);
        KRSS11.addRoomFacility(rf10);
        KRSS11.addMinibarItem(m1);
        KRSS11.addMinibarItem(m2);
        KRSS11.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS11);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_301"));

        Room KRSS12 = new Room("KRS_302", "302", "Standard", 2, "Occupied", h4);
        KRSS12.addRoomFacility(rf1);
        KRSS12.addRoomFacility(rf2);
        KRSS12.addRoomFacility(rf3);
        KRSS12.addRoomFacility(rf4);
        KRSS12.addRoomFacility(rf5);
        KRSS12.addRoomFacility(rf6);
        KRSS12.addRoomFacility(rf7);
        KRSS12.addRoomFacility(rf8);
        KRSS12.addRoomFacility(rf9);
        KRSS12.addRoomFacility(rf10);
        KRSS12.addMinibarItem(m1);
        KRSS12.addMinibarItem(m2);
        KRSS12.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS12);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_302"));

        Room KRSS13 = new Room("KRS_303", "303", "Standard", 2, "Available", h4);
        KRSS13.addRoomFacility(rf1);
        KRSS13.addRoomFacility(rf2);
        KRSS13.addRoomFacility(rf3);
        KRSS13.addRoomFacility(rf4);
        KRSS13.addRoomFacility(rf5);
        KRSS13.addRoomFacility(rf6);
        KRSS13.addRoomFacility(rf7);
        KRSS13.addRoomFacility(rf8);
        KRSS13.addRoomFacility(rf9);
        KRSS13.addRoomFacility(rf10);
        KRSS13.addMinibarItem(m1);
        KRSS13.addMinibarItem(m2);
        KRSS13.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS13);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_303"));

        Room KRSS14 = new Room("KRS_304", "304", "Standard", 2, "Occupied", h4);
        KRSS14.addRoomFacility(rf1);
        KRSS14.addRoomFacility(rf2);
        KRSS14.addRoomFacility(rf3);
        KRSS14.addRoomFacility(rf4);
        KRSS14.addRoomFacility(rf5);
        KRSS14.addRoomFacility(rf6);
        KRSS14.addRoomFacility(rf7);
        KRSS14.addRoomFacility(rf8);
        KRSS14.addRoomFacility(rf9);
        KRSS14.addRoomFacility(rf10);
        KRSS14.addMinibarItem(m1);
        KRSS14.addMinibarItem(m2);
        KRSS14.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS14);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_304"));

        Room KRSS15 = new Room("KRS_305", "305", "Standard", 2, "Available", h4);
        KRSS15.addRoomFacility(rf1);
        KRSS15.addRoomFacility(rf2);
        KRSS15.addRoomFacility(rf3);
        KRSS15.addRoomFacility(rf4);
        KRSS15.addRoomFacility(rf5);
        KRSS15.addRoomFacility(rf6);
        KRSS15.addRoomFacility(rf7);
        KRSS15.addRoomFacility(rf8);
        KRSS15.addRoomFacility(rf9);
        KRSS15.addRoomFacility(rf10);
        KRSS15.addMinibarItem(m1);
        KRSS15.addMinibarItem(m2);
        KRSS15.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS15);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_305"));

        Room KRSS16 = new Room("KRS_306", "306", "Standard", 2, "Available", h4);
        KRSS16.addRoomFacility(rf1);
        KRSS16.addRoomFacility(rf2);
        KRSS16.addRoomFacility(rf3);
        KRSS16.addRoomFacility(rf4);
        KRSS16.addRoomFacility(rf5);
        KRSS16.addRoomFacility(rf6);
        KRSS16.addRoomFacility(rf7);
        KRSS16.addRoomFacility(rf8);
        KRSS16.addRoomFacility(rf9);
        KRSS16.addRoomFacility(rf10);
        KRSS16.addMinibarItem(m1);
        KRSS16.addMinibarItem(m2);
        KRSS16.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS16);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_306"));

        Room KRSS17 = new Room("KRS_307", "307", "Standard", 2, "Available", h4);
        KRSS17.addRoomFacility(rf1);
        KRSS17.addRoomFacility(rf2);
        KRSS17.addRoomFacility(rf3);
        KRSS17.addRoomFacility(rf4);
        KRSS17.addRoomFacility(rf5);
        KRSS17.addRoomFacility(rf6);
        KRSS17.addRoomFacility(rf7);
        KRSS17.addRoomFacility(rf8);
        KRSS17.addRoomFacility(rf9);
        KRSS17.addRoomFacility(rf10);
        KRSS17.addMinibarItem(m1);
        KRSS17.addMinibarItem(m2);
        KRSS17.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS17);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_307"));

        Room KRSS18 = new Room("KRS_308", "308", "Standard", 2, "Occupied", h4);
        KRSS18.addRoomFacility(rf1);
        KRSS18.addRoomFacility(rf2);
        KRSS18.addRoomFacility(rf3);
        KRSS18.addRoomFacility(rf4);
        KRSS18.addRoomFacility(rf5);
        KRSS18.addRoomFacility(rf6);
        KRSS18.addRoomFacility(rf7);
        KRSS18.addRoomFacility(rf8);
        KRSS18.addRoomFacility(rf9);
        KRSS18.addRoomFacility(rf10);
        KRSS18.addMinibarItem(m1);
        KRSS18.addMinibarItem(m2);
        KRSS18.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS18);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_308"));

        Room KRSS21 = new Room("KRS_401", "401", "Standard", 2, "Available", h4);
        KRSS21.addRoomFacility(rf1);
        KRSS21.addRoomFacility(rf2);
        KRSS21.addRoomFacility(rf3);
        KRSS21.addRoomFacility(rf4);
        KRSS21.addRoomFacility(rf5);
        KRSS21.addRoomFacility(rf6);
        KRSS21.addRoomFacility(rf7);
        KRSS21.addRoomFacility(rf8);
        KRSS21.addRoomFacility(rf9);
        KRSS21.addRoomFacility(rf10);
        KRSS21.addMinibarItem(m1);
        KRSS21.addMinibarItem(m2);
        KRSS21.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS21);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_401"));

        Room KRSS22 = new Room("KRS_402", "402", "Standard", 2, "Occupied", h4);
        KRSS22.addRoomFacility(rf1);
        KRSS22.addRoomFacility(rf2);
        KRSS22.addRoomFacility(rf3);
        KRSS22.addRoomFacility(rf4);
        KRSS22.addRoomFacility(rf5);
        KRSS22.addRoomFacility(rf6);
        KRSS22.addRoomFacility(rf7);
        KRSS22.addRoomFacility(rf8);
        KRSS22.addRoomFacility(rf9);
        KRSS22.addRoomFacility(rf10);
        KRSS22.addMinibarItem(m1);
        KRSS22.addMinibarItem(m2);
        KRSS22.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS22);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_402"));

        Room KRSS23 = new Room("KRS_403", "403", "Standard", 2, "Unavailable", h4);
        KRSS23.addRoomFacility(rf1);
        KRSS23.addRoomFacility(rf2);
        KRSS23.addRoomFacility(rf3);
        KRSS23.addRoomFacility(rf4);
        KRSS23.addRoomFacility(rf5);
        KRSS23.addRoomFacility(rf6);
        KRSS23.addRoomFacility(rf7);
        KRSS23.addRoomFacility(rf8);
        KRSS23.addRoomFacility(rf9);
        KRSS23.addRoomFacility(rf10);
        KRSS23.addMinibarItem(m1);
        KRSS23.addMinibarItem(m2);
        KRSS23.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS23);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_403"));

        Room KRSS24 = new Room("KRS_404", "404", "Standard", 2, "Available", h4);
        KRSS24.addRoomFacility(rf1);
        KRSS24.addRoomFacility(rf2);
        KRSS24.addRoomFacility(rf3);
        KRSS24.addRoomFacility(rf4);
        KRSS24.addRoomFacility(rf5);
        KRSS24.addRoomFacility(rf6);
        KRSS24.addRoomFacility(rf7);
        KRSS24.addRoomFacility(rf8);
        KRSS24.addRoomFacility(rf9);
        KRSS24.addRoomFacility(rf10);
        KRSS24.addMinibarItem(m1);
        KRSS24.addMinibarItem(m2);
        KRSS24.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS24);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_404"));

        Room KRSS25 = new Room("KRS_405", "405", "Standard", 2, "Available", h4);
        KRSS25.addRoomFacility(rf1);
        KRSS25.addRoomFacility(rf2);
        KRSS25.addRoomFacility(rf3);
        KRSS25.addRoomFacility(rf4);
        KRSS25.addRoomFacility(rf5);
        KRSS25.addRoomFacility(rf6);
        KRSS25.addRoomFacility(rf7);
        KRSS25.addRoomFacility(rf8);
        KRSS25.addRoomFacility(rf9);
        KRSS25.addRoomFacility(rf10);
        KRSS25.addMinibarItem(m1);
        KRSS25.addMinibarItem(m2);
        KRSS25.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS25);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_405"));

        Room KRSS26 = new Room("KRS_406", "406", "Standard", 2, "Occupied", h4);
        KRSS26.addRoomFacility(rf1);
        KRSS26.addRoomFacility(rf2);
        KRSS26.addRoomFacility(rf3);
        KRSS26.addRoomFacility(rf4);
        KRSS26.addRoomFacility(rf5);
        KRSS26.addRoomFacility(rf6);
        KRSS26.addRoomFacility(rf7);
        KRSS26.addRoomFacility(rf8);
        KRSS26.addRoomFacility(rf9);
        KRSS26.addRoomFacility(rf10);
        KRSS26.addMinibarItem(m1);
        KRSS26.addMinibarItem(m2);
        KRSS26.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS26);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_406"));

        Room KRSS27 = new Room("KRS_407", "407", "Standard", 2, "Available", h4);
        KRSS27.addRoomFacility(rf1);
        KRSS27.addRoomFacility(rf2);
        KRSS27.addRoomFacility(rf3);
        KRSS27.addRoomFacility(rf4);
        KRSS27.addRoomFacility(rf5);
        KRSS27.addRoomFacility(rf6);
        KRSS27.addRoomFacility(rf7);
        KRSS27.addRoomFacility(rf8);
        KRSS27.addRoomFacility(rf9);
        KRSS27.addRoomFacility(rf10);
        KRSS27.addMinibarItem(m1);
        KRSS27.addMinibarItem(m2);
        KRSS27.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS27);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_407"));

        Room KRSS28 = new Room("KRS_408", "408", "Standard", 2, "Available", h4);
        KRSS28.addRoomFacility(rf1);
        KRSS28.addRoomFacility(rf2);
        KRSS28.addRoomFacility(rf3);
        KRSS28.addRoomFacility(rf4);
        KRSS28.addRoomFacility(rf5);
        KRSS28.addRoomFacility(rf6);
        KRSS28.addRoomFacility(rf7);
        KRSS28.addRoomFacility(rf8);
        KRSS28.addRoomFacility(rf9);
        KRSS28.addRoomFacility(rf10);
        KRSS28.addMinibarItem(m1);
        KRSS28.addMinibarItem(m2);
        KRSS28.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS28);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_408"));

        Room KRSS31 = new Room("KRS_501", "501", "Standard", 2, "Occupied", h4);
        KRSS31.addRoomFacility(rf1);
        KRSS31.addRoomFacility(rf2);
        KRSS31.addRoomFacility(rf3);
        KRSS31.addRoomFacility(rf4);
        KRSS31.addRoomFacility(rf5);
        KRSS31.addRoomFacility(rf6);
        KRSS31.addRoomFacility(rf7);
        KRSS31.addRoomFacility(rf8);
        KRSS31.addRoomFacility(rf9);
        KRSS31.addRoomFacility(rf10);
        KRSS31.addMinibarItem(m1);
        KRSS31.addMinibarItem(m2);
        KRSS31.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS31);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_501"));

        Room KRSS32 = new Room("KRS_502", "502", "Standard", 2, "Unavailable", h4);
        KRSS32.addRoomFacility(rf1);
        KRSS32.addRoomFacility(rf2);
        KRSS32.addRoomFacility(rf3);
        KRSS32.addRoomFacility(rf4);
        KRSS32.addRoomFacility(rf5);
        KRSS32.addRoomFacility(rf6);
        KRSS32.addRoomFacility(rf7);
        KRSS32.addRoomFacility(rf8);
        KRSS32.addRoomFacility(rf9);
        KRSS32.addRoomFacility(rf10);
        KRSS32.addMinibarItem(m1);
        KRSS32.addMinibarItem(m2);
        KRSS32.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS32);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_502"));

        Room KRSS33 = new Room("KRS_503", "503", "Standard", 2, "Available", h4);
        KRSS33.addRoomFacility(rf1);
        KRSS33.addRoomFacility(rf2);
        KRSS33.addRoomFacility(rf3);
        KRSS33.addRoomFacility(rf4);
        KRSS33.addRoomFacility(rf5);
        KRSS33.addRoomFacility(rf6);
        KRSS33.addRoomFacility(rf7);
        KRSS33.addRoomFacility(rf8);
        KRSS33.addRoomFacility(rf9);
        KRSS33.addRoomFacility(rf10);
        KRSS33.addMinibarItem(m1);
        KRSS33.addMinibarItem(m2);
        KRSS33.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS33);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_503"));

        Room KRSS34 = new Room("KRS_504", "504", "Standard", 2, "Available", h4);
        KRSS34.addRoomFacility(rf1);
        KRSS34.addRoomFacility(rf2);
        KRSS34.addRoomFacility(rf3);
        KRSS34.addRoomFacility(rf4);
        KRSS34.addRoomFacility(rf5);
        KRSS34.addRoomFacility(rf6);
        KRSS34.addRoomFacility(rf7);
        KRSS34.addRoomFacility(rf8);
        KRSS34.addRoomFacility(rf9);
        KRSS34.addRoomFacility(rf10);
        KRSS34.addMinibarItem(m1);
        KRSS34.addMinibarItem(m2);
        KRSS34.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS34);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_504"));

        Room KRSS35 = new Room("KRS_505", "505", "Standard", 2, "Occupied", h4);
        KRSS35.addRoomFacility(rf1);
        KRSS35.addRoomFacility(rf2);
        KRSS35.addRoomFacility(rf3);
        KRSS35.addRoomFacility(rf4);
        KRSS35.addRoomFacility(rf5);
        KRSS35.addRoomFacility(rf6);
        KRSS35.addRoomFacility(rf7);
        KRSS35.addRoomFacility(rf8);
        KRSS35.addRoomFacility(rf9);
        KRSS35.addRoomFacility(rf10);
        KRSS35.addMinibarItem(m1);
        KRSS35.addMinibarItem(m2);
        KRSS35.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS35);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_505"));

        Room KRSS36 = new Room("KRS_506", "506", "Standard", 2, "Available", h4);
        KRSS36.addRoomFacility(rf1);
        KRSS36.addRoomFacility(rf2);
        KRSS36.addRoomFacility(rf3);
        KRSS36.addRoomFacility(rf4);
        KRSS36.addRoomFacility(rf5);
        KRSS36.addRoomFacility(rf6);
        KRSS36.addRoomFacility(rf7);
        KRSS36.addRoomFacility(rf8);
        KRSS36.addRoomFacility(rf9);
        KRSS36.addRoomFacility(rf10);
        KRSS36.addMinibarItem(m1);
        KRSS36.addMinibarItem(m2);
        KRSS36.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS36);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_506"));

        Room KRSS37 = new Room("KRS_507", "507", "Standard", 2, "Available", h4);
        KRSS37.addRoomFacility(rf1);
        KRSS37.addRoomFacility(rf2);
        KRSS37.addRoomFacility(rf3);
        KRSS37.addRoomFacility(rf4);
        KRSS37.addRoomFacility(rf5);
        KRSS37.addRoomFacility(rf6);
        KRSS37.addRoomFacility(rf7);
        KRSS37.addRoomFacility(rf8);
        KRSS37.addRoomFacility(rf9);
        KRSS37.addRoomFacility(rf10);
        KRSS37.addMinibarItem(m1);
        KRSS37.addMinibarItem(m2);
        KRSS37.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS37);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_507"));

        Room KRSS38 = new Room("KRS_508", "508", "Standard", 2, "Occupied", h4);
        KRSS38.addRoomFacility(rf1);
        KRSS38.addRoomFacility(rf2);
        KRSS38.addRoomFacility(rf3);
        KRSS38.addRoomFacility(rf4);
        KRSS38.addRoomFacility(rf5);
        KRSS38.addRoomFacility(rf6);
        KRSS38.addRoomFacility(rf7);
        KRSS38.addRoomFacility(rf8);
        KRSS38.addRoomFacility(rf9);
        KRSS38.addRoomFacility(rf10);
        KRSS38.addMinibarItem(m1);
        KRSS38.addMinibarItem(m2);
        KRSS38.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS38);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_508"));

        Room KRSS41 = new Room("KRS_601", "601", "Standard", 2, "Unavailable", h4);
        KRSS41.addRoomFacility(rf1);
        KRSS41.addRoomFacility(rf2);
        KRSS41.addRoomFacility(rf3);
        KRSS41.addRoomFacility(rf4);
        KRSS41.addRoomFacility(rf5);
        KRSS41.addRoomFacility(rf6);
        KRSS41.addRoomFacility(rf7);
        KRSS41.addRoomFacility(rf8);
        KRSS41.addRoomFacility(rf9);
        KRSS41.addRoomFacility(rf10);
        KRSS41.addMinibarItem(m1);
        KRSS41.addMinibarItem(m2);
        KRSS41.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS41);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_601"));

        Room KRSS42 = new Room("KRS_602", "602", "Standard", 2, "Available", h4);
        KRSS42.addRoomFacility(rf1);
        KRSS42.addRoomFacility(rf2);
        KRSS42.addRoomFacility(rf3);
        KRSS42.addRoomFacility(rf4);
        KRSS42.addRoomFacility(rf5);
        KRSS42.addRoomFacility(rf6);
        KRSS42.addRoomFacility(rf7);
        KRSS42.addRoomFacility(rf8);
        KRSS42.addRoomFacility(rf9);
        KRSS42.addRoomFacility(rf10);
        KRSS42.addMinibarItem(m1);
        KRSS42.addMinibarItem(m2);
        KRSS42.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS42);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_602"));

        Room KRSS43 = new Room("KRS_603", "603", "Standard", 2, "Occupied", h4);
        KRSS43.addRoomFacility(rf1);
        KRSS43.addRoomFacility(rf2);
        KRSS43.addRoomFacility(rf3);
        KRSS43.addRoomFacility(rf4);
        KRSS43.addRoomFacility(rf5);
        KRSS43.addRoomFacility(rf6);
        KRSS43.addRoomFacility(rf7);
        KRSS43.addRoomFacility(rf8);
        KRSS43.addRoomFacility(rf9);
        KRSS43.addRoomFacility(rf10);
        KRSS43.addMinibarItem(m1);
        KRSS43.addMinibarItem(m2);
        KRSS43.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS43);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_603"));

        Room KRSS44 = new Room("KRS_604", "604", "Standard", 2, "Available", h4);
        KRSS44.addRoomFacility(rf1);
        KRSS44.addRoomFacility(rf2);
        KRSS44.addRoomFacility(rf3);
        KRSS44.addRoomFacility(rf4);
        KRSS44.addRoomFacility(rf5);
        KRSS44.addRoomFacility(rf6);
        KRSS44.addRoomFacility(rf7);
        KRSS44.addRoomFacility(rf8);
        KRSS44.addRoomFacility(rf9);
        KRSS44.addRoomFacility(rf10);
        KRSS44.addMinibarItem(m1);
        KRSS44.addMinibarItem(m2);
        KRSS44.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS44);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_604"));

        Room KRSS45 = new Room("KRS_605", "605", "Standard", 2, "Occupied", h4);
        KRSS45.addRoomFacility(rf1);
        KRSS45.addRoomFacility(rf2);
        KRSS45.addRoomFacility(rf3);
        KRSS45.addRoomFacility(rf4);
        KRSS45.addRoomFacility(rf5);
        KRSS45.addRoomFacility(rf6);
        KRSS45.addRoomFacility(rf7);
        KRSS45.addRoomFacility(rf8);
        KRSS45.addRoomFacility(rf9);
        KRSS45.addRoomFacility(rf10);
        KRSS45.addMinibarItem(m1);
        KRSS45.addMinibarItem(m2);
        KRSS45.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS45);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_605"));

        Room KRSS46 = new Room("KRS_606", "606", "Standard", 2, "Available", h4);
        KRSS46.addRoomFacility(rf1);
        KRSS46.addRoomFacility(rf2);
        KRSS46.addRoomFacility(rf3);
        KRSS46.addRoomFacility(rf4);
        KRSS46.addRoomFacility(rf5);
        KRSS46.addRoomFacility(rf6);
        KRSS46.addRoomFacility(rf7);
        KRSS46.addRoomFacility(rf8);
        KRSS46.addRoomFacility(rf9);
        KRSS46.addRoomFacility(rf10);
        KRSS46.addMinibarItem(m1);
        KRSS46.addMinibarItem(m2);
        KRSS46.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS46);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_606"));

        Room KRSS47 = new Room("KRS_607", "607", "Standard", 2, "Available", h4);
        KRSS47.addRoomFacility(rf1);
        KRSS47.addRoomFacility(rf2);
        KRSS47.addRoomFacility(rf3);
        KRSS47.addRoomFacility(rf4);
        KRSS47.addRoomFacility(rf5);
        KRSS47.addRoomFacility(rf6);
        KRSS47.addRoomFacility(rf7);
        KRSS47.addRoomFacility(rf8);
        KRSS47.addRoomFacility(rf9);
        KRSS47.addRoomFacility(rf10);
        KRSS47.addMinibarItem(m1);
        KRSS47.addMinibarItem(m2);
        KRSS47.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS47);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_607"));

        Room KRSS48 = new Room("KRS_608", "608", "Standard", 2, "Unavailable", h4);
        KRSS48.addRoomFacility(rf1);
        KRSS48.addRoomFacility(rf2);
        KRSS48.addRoomFacility(rf3);
        KRSS48.addRoomFacility(rf4);
        KRSS48.addRoomFacility(rf5);
        KRSS48.addRoomFacility(rf6);
        KRSS48.addRoomFacility(rf7);
        KRSS48.addRoomFacility(rf8);
        KRSS48.addRoomFacility(rf9);
        KRSS48.addRoomFacility(rf10);
        KRSS48.addMinibarItem(m1);
        KRSS48.addMinibarItem(m2);
        KRSS48.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSS48);
        h4.addRoom(roomSessionLocal.getRoomByName("KRS_608"));

        Room KRS_1001 = new Room("KRS_1001", "1001", "Deluxe", 3, "Occupied", h4);

        KRS_1001.addRoomFacility(rf1);
        KRS_1001.addRoomFacility(rf2);
        KRS_1001.addRoomFacility(rf3);
        KRS_1001.addRoomFacility(rf4);
        KRS_1001.addRoomFacility(rf5);
        KRS_1001.addRoomFacility(rf6);
        KRS_1001.addRoomFacility(rf7);
        KRS_1001.addRoomFacility(rf8);
        KRS_1001.addRoomFacility(rf9);
        KRS_1001.addRoomFacility(rf10);
        KRS_1001.addRoomFacility(rf11);
        KRS_1001.addRoomFacility(rf12);
        KRS_1001.addRoomFacility(rf13);

        KRS_1001.addMinibarItem(m1);
        KRS_1001.addMinibarItem(m2);
        KRS_1001.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRS_1001);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_1001"));

        Room KRS_1002 = new Room("KRS_1002", "1002", "Deluxe", 3, "Available", h4);

        KRS_1002.addRoomFacility(rf1);
        KRS_1002.addRoomFacility(rf2);
        KRS_1002.addRoomFacility(rf3);
        KRS_1002.addRoomFacility(rf4);
        KRS_1002.addRoomFacility(rf5);
        KRS_1002.addRoomFacility(rf6);
        KRS_1002.addRoomFacility(rf7);
        KRS_1002.addRoomFacility(rf8);
        KRS_1002.addRoomFacility(rf9);
        KRS_1002.addRoomFacility(rf10);
        KRS_1002.addRoomFacility(rf11);
        KRS_1002.addRoomFacility(rf12);
        KRS_1002.addRoomFacility(rf13);

        KRS_1002.addMinibarItem(m1);
        KRS_1002.addMinibarItem(m2);
        KRS_1002.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRS_1002);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_1002"));

        Room KRS_1003 = new Room("KRS_1003", "1003", "Deluxe", 3, "Occupied", h4);

        KRS_1003.addRoomFacility(rf1);
        KRS_1003.addRoomFacility(rf2);
        KRS_1003.addRoomFacility(rf3);
        KRS_1003.addRoomFacility(rf4);
        KRS_1003.addRoomFacility(rf5);
        KRS_1003.addRoomFacility(rf6);
        KRS_1003.addRoomFacility(rf7);
        KRS_1003.addRoomFacility(rf8);
        KRS_1003.addRoomFacility(rf9);
        KRS_1003.addRoomFacility(rf10);
        KRS_1003.addRoomFacility(rf11);
        KRS_1003.addRoomFacility(rf12);
        KRS_1003.addRoomFacility(rf13);

        KRS_1003.addMinibarItem(m1);
        KRS_1003.addMinibarItem(m2);
        KRS_1003.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRS_1003);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_1003"));

        Room KRS_1004 = new Room("KRS_1004", "1004", "Deluxe", 3, "Available", h4);

        KRS_1004.addRoomFacility(rf1);
        KRS_1004.addRoomFacility(rf2);
        KRS_1004.addRoomFacility(rf3);
        KRS_1004.addRoomFacility(rf4);
        KRS_1004.addRoomFacility(rf5);
        KRS_1004.addRoomFacility(rf6);
        KRS_1004.addRoomFacility(rf7);
        KRS_1004.addRoomFacility(rf8);
        KRS_1004.addRoomFacility(rf9);
        KRS_1004.addRoomFacility(rf10);
        KRS_1004.addRoomFacility(rf11);
        KRS_1004.addRoomFacility(rf12);
        KRS_1004.addRoomFacility(rf13);

        KRS_1004.addMinibarItem(m1);
        KRS_1004.addMinibarItem(m2);
        KRS_1004.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRS_1004);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_1004"));

        Room KRS_1005 = new Room("KRS_1005", "1005", "Deluxe", 3, "Available", h4);

        KRS_1005.addRoomFacility(rf1);
        KRS_1005.addRoomFacility(rf2);
        KRS_1005.addRoomFacility(rf3);
        KRS_1005.addRoomFacility(rf4);
        KRS_1005.addRoomFacility(rf5);
        KRS_1005.addRoomFacility(rf6);
        KRS_1005.addRoomFacility(rf7);
        KRS_1005.addRoomFacility(rf8);
        KRS_1005.addRoomFacility(rf9);
        KRS_1005.addRoomFacility(rf10);
        KRS_1005.addRoomFacility(rf11);
        KRS_1005.addRoomFacility(rf12);
        KRS_1005.addRoomFacility(rf13);

        KRS_1005.addMinibarItem(m1);
        KRS_1005.addMinibarItem(m2);
        KRS_1005.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRS_1005);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_1005"));

        Room KRS_1101 = new Room("KRS_1101", "1101", "Deluxe", 3, "Unavailable", h4);

        KRS_1101.addRoomFacility(rf1);
        KRS_1101.addRoomFacility(rf2);
        KRS_1101.addRoomFacility(rf3);
        KRS_1101.addRoomFacility(rf4);
        KRS_1101.addRoomFacility(rf5);
        KRS_1101.addRoomFacility(rf6);
        KRS_1101.addRoomFacility(rf7);
        KRS_1101.addRoomFacility(rf8);
        KRS_1101.addRoomFacility(rf9);
        KRS_1101.addRoomFacility(rf10);
        KRS_1101.addRoomFacility(rf11);
        KRS_1101.addRoomFacility(rf12);
        KRS_1101.addRoomFacility(rf13);

        KRS_1101.addMinibarItem(m1);
        KRS_1101.addMinibarItem(m2);
        KRS_1101.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRS_1101);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_1101"));

        Room KRS_1102 = new Room("KRS_1102", "1102", "Deluxe", 3, "Occupied", h4);

        KRS_1102.addRoomFacility(rf1);
        KRS_1102.addRoomFacility(rf2);
        KRS_1102.addRoomFacility(rf3);
        KRS_1102.addRoomFacility(rf4);
        KRS_1102.addRoomFacility(rf5);
        KRS_1102.addRoomFacility(rf6);
        KRS_1102.addRoomFacility(rf7);
        KRS_1102.addRoomFacility(rf8);
        KRS_1102.addRoomFacility(rf9);
        KRS_1102.addRoomFacility(rf10);
        KRS_1102.addRoomFacility(rf11);
        KRS_1102.addRoomFacility(rf12);
        KRS_1102.addRoomFacility(rf13);

        KRS_1102.addMinibarItem(m1);
        KRS_1102.addMinibarItem(m2);
        KRS_1102.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRS_1102);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_1102"));

        Room KRS_1103 = new Room("KRS_1103", "1103", "Deluxe", 3, "Available", h4);

        KRS_1103.addRoomFacility(rf1);
        KRS_1103.addRoomFacility(rf2);
        KRS_1103.addRoomFacility(rf3);
        KRS_1103.addRoomFacility(rf4);
        KRS_1103.addRoomFacility(rf5);
        KRS_1103.addRoomFacility(rf6);
        KRS_1103.addRoomFacility(rf7);
        KRS_1103.addRoomFacility(rf8);
        KRS_1103.addRoomFacility(rf9);
        KRS_1103.addRoomFacility(rf10);
        KRS_1103.addRoomFacility(rf11);
        KRS_1103.addRoomFacility(rf12);
        KRS_1103.addRoomFacility(rf13);

        KRS_1103.addMinibarItem(m1);
        KRS_1103.addMinibarItem(m2);
        KRS_1103.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRS_1103);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_1103"));

        Room KRS_1104 = new Room("KRS_1104", "1104", "Deluxe", 3, "Occupied", h4);

        KRS_1104.addRoomFacility(rf1);
        KRS_1104.addRoomFacility(rf2);
        KRS_1104.addRoomFacility(rf3);
        KRS_1104.addRoomFacility(rf4);
        KRS_1104.addRoomFacility(rf5);
        KRS_1104.addRoomFacility(rf6);
        KRS_1104.addRoomFacility(rf7);
        KRS_1104.addRoomFacility(rf8);
        KRS_1104.addRoomFacility(rf9);
        KRS_1104.addRoomFacility(rf10);
        KRS_1104.addRoomFacility(rf11);
        KRS_1104.addRoomFacility(rf12);
        KRS_1104.addRoomFacility(rf13);

        KRS_1104.addMinibarItem(m1);
        KRS_1104.addMinibarItem(m2);
        KRS_1104.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRS_1104);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_1104"));

        Room KRS_1105 = new Room("KRS_1105", "1105", "Deluxe", 3, "Available", h4);

        KRS_1105.addRoomFacility(rf1);
        KRS_1105.addRoomFacility(rf2);
        KRS_1105.addRoomFacility(rf3);
        KRS_1105.addRoomFacility(rf4);
        KRS_1105.addRoomFacility(rf5);
        KRS_1105.addRoomFacility(rf6);
        KRS_1105.addRoomFacility(rf7);
        KRS_1105.addRoomFacility(rf8);
        KRS_1105.addRoomFacility(rf9);
        KRS_1105.addRoomFacility(rf10);
        KRS_1105.addRoomFacility(rf11);
        KRS_1105.addRoomFacility(rf12);
        KRS_1105.addRoomFacility(rf13);

        KRS_1105.addMinibarItem(m1);
        KRS_1105.addMinibarItem(m2);
        KRS_1105.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRS_1105);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_1105"));

        Room KRS_701 = new Room("KRS_701", "701", "Premium", 4, "Unavailable", h4);

        KRS_701.addRoomFacility(rf1);
        KRS_701.addRoomFacility(rf2);
        KRS_701.addRoomFacility(rf3);
        KRS_701.addRoomFacility(rf4);
        KRS_701.addRoomFacility(rf5);
        KRS_701.addRoomFacility(rf6);
        KRS_701.addRoomFacility(rf7);
        KRS_701.addRoomFacility(rf8);
        KRS_701.addRoomFacility(rf9);
        KRS_701.addRoomFacility(rf10);
        KRS_701.addRoomFacility(rf11);
        KRS_701.addRoomFacility(rf12);
        KRS_701.addRoomFacility(rf13);
        KRS_701.addRoomFacility(rf14);
        KRS_701.addRoomFacility(rf15);

        KRS_701.addMinibarItem(m1);
        KRS_701.addMinibarItem(m2);
        KRS_701.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRS_701);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_701"));

        Room KRS_702 = new Room("KRS_702", "702", "Premium", 4, "Occupied", h4);

        KRS_702.addRoomFacility(rf1);
        KRS_702.addRoomFacility(rf2);
        KRS_702.addRoomFacility(rf3);
        KRS_702.addRoomFacility(rf4);
        KRS_702.addRoomFacility(rf5);
        KRS_702.addRoomFacility(rf6);
        KRS_702.addRoomFacility(rf7);
        KRS_702.addRoomFacility(rf8);
        KRS_702.addRoomFacility(rf9);
        KRS_702.addRoomFacility(rf10);
        KRS_702.addRoomFacility(rf11);
        KRS_702.addRoomFacility(rf12);
        KRS_702.addRoomFacility(rf13);
        KRS_702.addRoomFacility(rf14);
        KRS_702.addRoomFacility(rf15);

        KRS_702.addMinibarItem(m1);
        KRS_702.addMinibarItem(m2);
        KRS_702.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRS_702);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_702"));

        Room KRS_703 = new Room("KRS_703", "703", "Premium", 4, "Available", h4);

        KRS_703.addRoomFacility(rf1);
        KRS_703.addRoomFacility(rf2);
        KRS_703.addRoomFacility(rf3);
        KRS_703.addRoomFacility(rf4);
        KRS_703.addRoomFacility(rf5);
        KRS_703.addRoomFacility(rf6);
        KRS_703.addRoomFacility(rf7);
        KRS_703.addRoomFacility(rf8);
        KRS_703.addRoomFacility(rf9);
        KRS_703.addRoomFacility(rf10);
        KRS_703.addRoomFacility(rf11);
        KRS_703.addRoomFacility(rf12);
        KRS_703.addRoomFacility(rf13);
        KRS_703.addRoomFacility(rf14);
        KRS_703.addRoomFacility(rf15);

        KRS_703.addMinibarItem(m1);
        KRS_703.addMinibarItem(m2);
        KRS_703.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRS_703);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_703"));

        Room KRS_704 = new Room("KRS_704", "704", "Premium", 4, "Available", h4);

        KRS_704.addRoomFacility(rf1);
        KRS_704.addRoomFacility(rf2);
        KRS_704.addRoomFacility(rf3);
        KRS_704.addRoomFacility(rf4);
        KRS_704.addRoomFacility(rf5);
        KRS_704.addRoomFacility(rf6);
        KRS_704.addRoomFacility(rf7);
        KRS_704.addRoomFacility(rf8);
        KRS_704.addRoomFacility(rf9);
        KRS_704.addRoomFacility(rf10);
        KRS_704.addRoomFacility(rf11);
        KRS_704.addRoomFacility(rf12);
        KRS_704.addRoomFacility(rf13);
        KRS_704.addRoomFacility(rf14);
        KRS_704.addRoomFacility(rf15);

        KRS_704.addMinibarItem(m1);
        KRS_704.addMinibarItem(m2);
        KRS_704.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRS_704);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_704"));

        Room KRS_705 = new Room("KRS_705", "705", "Premium", 4, "Occupied", h4);

        KRS_705.addRoomFacility(rf1);
        KRS_705.addRoomFacility(rf2);
        KRS_705.addRoomFacility(rf3);
        KRS_705.addRoomFacility(rf4);
        KRS_705.addRoomFacility(rf5);
        KRS_705.addRoomFacility(rf6);
        KRS_705.addRoomFacility(rf7);
        KRS_705.addRoomFacility(rf8);
        KRS_705.addRoomFacility(rf9);
        KRS_705.addRoomFacility(rf10);
        KRS_705.addRoomFacility(rf11);
        KRS_705.addRoomFacility(rf12);
        KRS_705.addRoomFacility(rf13);
        KRS_705.addRoomFacility(rf14);
        KRS_705.addRoomFacility(rf15);

        KRS_705.addMinibarItem(m1);
        KRS_705.addMinibarItem(m2);
        KRS_705.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRS_705);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_705"));

        Room KRS_706 = new Room("KRS_706", "706", "Premium", 4, "Available", h4);

        KRS_706.addRoomFacility(rf1);
        KRS_706.addRoomFacility(rf2);
        KRS_706.addRoomFacility(rf3);
        KRS_706.addRoomFacility(rf4);
        KRS_706.addRoomFacility(rf5);
        KRS_706.addRoomFacility(rf6);
        KRS_706.addRoomFacility(rf7);
        KRS_706.addRoomFacility(rf8);
        KRS_706.addRoomFacility(rf9);
        KRS_706.addRoomFacility(rf10);
        KRS_706.addRoomFacility(rf11);
        KRS_706.addRoomFacility(rf12);
        KRS_706.addRoomFacility(rf13);
        KRS_706.addRoomFacility(rf14);
        KRS_706.addRoomFacility(rf15);

        KRS_706.addMinibarItem(m1);
        KRS_706.addMinibarItem(m2);
        KRS_706.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRS_706);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_706"));

        Room KRS_707 = new Room("KRS_707", "707", "Premium", 4, "Available", h4);

        KRS_707.addRoomFacility(rf1);
        KRS_707.addRoomFacility(rf2);
        KRS_707.addRoomFacility(rf3);
        KRS_707.addRoomFacility(rf4);
        KRS_707.addRoomFacility(rf5);
        KRS_707.addRoomFacility(rf6);
        KRS_707.addRoomFacility(rf7);
        KRS_707.addRoomFacility(rf8);
        KRS_707.addRoomFacility(rf9);
        KRS_707.addRoomFacility(rf10);
        KRS_707.addRoomFacility(rf11);
        KRS_707.addRoomFacility(rf12);
        KRS_707.addRoomFacility(rf13);
        KRS_707.addRoomFacility(rf14);
        KRS_707.addRoomFacility(rf15);

        KRS_707.addMinibarItem(m1);
        KRS_707.addMinibarItem(m2);
        KRS_707.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRS_707);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_707"));

        Room KRS_801 = new Room("KRS_801", "801", "Premium", 4, "Occupied", h4);

        KRS_801.addRoomFacility(rf1);
        KRS_801.addRoomFacility(rf2);
        KRS_801.addRoomFacility(rf3);
        KRS_801.addRoomFacility(rf4);
        KRS_801.addRoomFacility(rf5);
        KRS_801.addRoomFacility(rf6);
        KRS_801.addRoomFacility(rf7);
        KRS_801.addRoomFacility(rf8);
        KRS_801.addRoomFacility(rf9);
        KRS_801.addRoomFacility(rf10);
        KRS_801.addRoomFacility(rf11);
        KRS_801.addRoomFacility(rf12);
        KRS_801.addRoomFacility(rf13);
        KRS_801.addRoomFacility(rf14);
        KRS_801.addRoomFacility(rf15);

        KRS_801.addMinibarItem(m1);
        KRS_801.addMinibarItem(m2);
        KRS_801.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRS_801);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_801"));

        Room KRS_802 = new Room("KRS_802", "802", "Premium", 4, "Unavailable", h4);

        KRS_802.addRoomFacility(rf1);
        KRS_802.addRoomFacility(rf2);
        KRS_802.addRoomFacility(rf3);
        KRS_802.addRoomFacility(rf4);
        KRS_802.addRoomFacility(rf5);
        KRS_802.addRoomFacility(rf6);
        KRS_802.addRoomFacility(rf7);
        KRS_802.addRoomFacility(rf8);
        KRS_802.addRoomFacility(rf9);
        KRS_802.addRoomFacility(rf10);
        KRS_802.addRoomFacility(rf11);
        KRS_802.addRoomFacility(rf12);
        KRS_802.addRoomFacility(rf13);
        KRS_802.addRoomFacility(rf14);
        KRS_802.addRoomFacility(rf15);

        KRS_802.addMinibarItem(m1);
        KRS_802.addMinibarItem(m2);
        KRS_802.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRS_802);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_802"));

        Room KRS_803 = new Room("KRS_803", "803", "Premium", 4, "Available", h4);

        KRS_803.addRoomFacility(rf1);
        KRS_803.addRoomFacility(rf2);
        KRS_803.addRoomFacility(rf3);
        KRS_803.addRoomFacility(rf4);
        KRS_803.addRoomFacility(rf5);
        KRS_803.addRoomFacility(rf6);
        KRS_803.addRoomFacility(rf7);
        KRS_803.addRoomFacility(rf8);
        KRS_803.addRoomFacility(rf9);
        KRS_803.addRoomFacility(rf10);
        KRS_803.addRoomFacility(rf11);
        KRS_803.addRoomFacility(rf12);
        KRS_803.addRoomFacility(rf13);
        KRS_803.addRoomFacility(rf14);
        KRS_803.addRoomFacility(rf15);

        KRS_803.addMinibarItem(m1);
        KRS_803.addMinibarItem(m2);
        KRS_803.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRS_803);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_803"));

        Room KRS_804 = new Room("KRS_804", "804", "Premium", 4, "Available", h4);

        KRS_804.addRoomFacility(rf1);
        KRS_804.addRoomFacility(rf2);
        KRS_804.addRoomFacility(rf3);
        KRS_804.addRoomFacility(rf4);
        KRS_804.addRoomFacility(rf5);
        KRS_804.addRoomFacility(rf6);
        KRS_804.addRoomFacility(rf7);
        KRS_804.addRoomFacility(rf8);
        KRS_804.addRoomFacility(rf9);
        KRS_804.addRoomFacility(rf10);
        KRS_804.addRoomFacility(rf11);
        KRS_804.addRoomFacility(rf12);
        KRS_804.addRoomFacility(rf13);
        KRS_804.addRoomFacility(rf14);
        KRS_804.addRoomFacility(rf15);

        KRS_804.addMinibarItem(m1);
        KRS_804.addMinibarItem(m2);
        KRS_804.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRS_804);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_804"));

        Room KRS_805 = new Room("KRS_805", "805", "Premium", 4, "Available", h4);

        KRS_805.addRoomFacility(rf1);
        KRS_805.addRoomFacility(rf2);
        KRS_805.addRoomFacility(rf3);
        KRS_805.addRoomFacility(rf4);
        KRS_805.addRoomFacility(rf5);
        KRS_805.addRoomFacility(rf6);
        KRS_805.addRoomFacility(rf7);
        KRS_805.addRoomFacility(rf8);
        KRS_805.addRoomFacility(rf9);
        KRS_805.addRoomFacility(rf10);
        KRS_805.addRoomFacility(rf11);
        KRS_805.addRoomFacility(rf12);
        KRS_805.addRoomFacility(rf13);
        KRS_805.addRoomFacility(rf14);
        KRS_805.addRoomFacility(rf15);

        KRS_805.addMinibarItem(m1);
        KRS_805.addMinibarItem(m2);
        KRS_805.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRS_805);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_805"));

        Room KRS_806 = new Room("KRS_806", "806", "Premium", 4, "Occupied", h4);

        KRS_806.addRoomFacility(rf1);
        KRS_806.addRoomFacility(rf2);
        KRS_806.addRoomFacility(rf3);
        KRS_806.addRoomFacility(rf4);
        KRS_806.addRoomFacility(rf5);
        KRS_806.addRoomFacility(rf6);
        KRS_806.addRoomFacility(rf7);
        KRS_806.addRoomFacility(rf8);
        KRS_806.addRoomFacility(rf9);
        KRS_806.addRoomFacility(rf10);
        KRS_806.addRoomFacility(rf11);
        KRS_806.addRoomFacility(rf12);
        KRS_806.addRoomFacility(rf13);
        KRS_806.addRoomFacility(rf14);
        KRS_806.addRoomFacility(rf15);

        KRS_806.addMinibarItem(m1);
        KRS_806.addMinibarItem(m2);
        KRS_806.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRS_806);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_806"));

        Room KRS_807 = new Room("KRS_807", "807", "Premium", 4, "Available", h4);

        KRS_807.addRoomFacility(rf1);
        KRS_807.addRoomFacility(rf2);
        KRS_807.addRoomFacility(rf3);
        KRS_807.addRoomFacility(rf4);
        KRS_807.addRoomFacility(rf5);
        KRS_807.addRoomFacility(rf6);
        KRS_807.addRoomFacility(rf7);
        KRS_807.addRoomFacility(rf8);
        KRS_807.addRoomFacility(rf9);
        KRS_807.addRoomFacility(rf10);
        KRS_807.addRoomFacility(rf11);
        KRS_807.addRoomFacility(rf12);
        KRS_807.addRoomFacility(rf13);
        KRS_807.addRoomFacility(rf14);
        KRS_807.addRoomFacility(rf15);

        KRS_807.addMinibarItem(m1);
        KRS_807.addMinibarItem(m2);
        KRS_807.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRS_807);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_807"));

        Room KRS_901 = new Room("KRS_901", "901", "Premium", 4, "Available", h4);

        KRS_901.addRoomFacility(rf1);
        KRS_901.addRoomFacility(rf2);
        KRS_901.addRoomFacility(rf3);
        KRS_901.addRoomFacility(rf4);
        KRS_901.addRoomFacility(rf5);
        KRS_901.addRoomFacility(rf6);
        KRS_901.addRoomFacility(rf7);
        KRS_901.addRoomFacility(rf8);
        KRS_901.addRoomFacility(rf9);
        KRS_901.addRoomFacility(rf10);
        KRS_901.addRoomFacility(rf11);
        KRS_901.addRoomFacility(rf12);
        KRS_901.addRoomFacility(rf13);
        KRS_901.addRoomFacility(rf14);
        KRS_901.addRoomFacility(rf15);

        KRS_901.addMinibarItem(m1);
        KRS_901.addMinibarItem(m2);
        KRS_901.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRS_901);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_901"));

        Room KRS_902 = new Room("KRS_902", "902", "Premium", 4, "Occupied", h4);

        KRS_902.addRoomFacility(rf1);
        KRS_902.addRoomFacility(rf2);
        KRS_902.addRoomFacility(rf3);
        KRS_902.addRoomFacility(rf4);
        KRS_902.addRoomFacility(rf5);
        KRS_902.addRoomFacility(rf6);
        KRS_902.addRoomFacility(rf7);
        KRS_902.addRoomFacility(rf8);
        KRS_902.addRoomFacility(rf9);
        KRS_902.addRoomFacility(rf10);
        KRS_902.addRoomFacility(rf11);
        KRS_902.addRoomFacility(rf12);
        KRS_902.addRoomFacility(rf13);
        KRS_902.addRoomFacility(rf14);
        KRS_902.addRoomFacility(rf15);

        KRS_902.addMinibarItem(m1);
        KRS_902.addMinibarItem(m2);
        KRS_902.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRS_902);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_902"));

        Room KRS_903 = new Room("KRS_903", "903", "Premium", 4, "Available", h4);

        KRS_903.addRoomFacility(rf1);
        KRS_903.addRoomFacility(rf2);
        KRS_903.addRoomFacility(rf3);
        KRS_903.addRoomFacility(rf4);
        KRS_903.addRoomFacility(rf5);
        KRS_903.addRoomFacility(rf6);
        KRS_903.addRoomFacility(rf7);
        KRS_903.addRoomFacility(rf8);
        KRS_903.addRoomFacility(rf9);
        KRS_903.addRoomFacility(rf10);
        KRS_903.addRoomFacility(rf11);
        KRS_903.addRoomFacility(rf12);
        KRS_903.addRoomFacility(rf13);
        KRS_903.addRoomFacility(rf14);
        KRS_903.addRoomFacility(rf15);

        KRS_903.addMinibarItem(m1);
        KRS_903.addMinibarItem(m2);
        KRS_903.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRS_903);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_903"));

        Room KRS_904 = new Room("KRS_904", "904", "Premium", 4, "Occupied", h4);

        KRS_904.addRoomFacility(rf1);
        KRS_904.addRoomFacility(rf2);
        KRS_904.addRoomFacility(rf3);
        KRS_904.addRoomFacility(rf4);
        KRS_904.addRoomFacility(rf5);
        KRS_904.addRoomFacility(rf6);
        KRS_904.addRoomFacility(rf7);
        KRS_904.addRoomFacility(rf8);
        KRS_904.addRoomFacility(rf9);
        KRS_904.addRoomFacility(rf10);
        KRS_904.addRoomFacility(rf11);
        KRS_904.addRoomFacility(rf12);
        KRS_904.addRoomFacility(rf13);
        KRS_904.addRoomFacility(rf14);
        KRS_904.addRoomFacility(rf15);

        KRS_904.addMinibarItem(m1);
        KRS_904.addMinibarItem(m2);
        KRS_904.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRS_904);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_904"));

        Room KRS_905 = new Room("KRS_905", "905", "Premium", 4, "Available", h4);

        KRS_905.addRoomFacility(rf1);
        KRS_905.addRoomFacility(rf2);
        KRS_905.addRoomFacility(rf3);
        KRS_905.addRoomFacility(rf4);
        KRS_905.addRoomFacility(rf5);
        KRS_905.addRoomFacility(rf6);
        KRS_905.addRoomFacility(rf7);
        KRS_905.addRoomFacility(rf8);
        KRS_905.addRoomFacility(rf9);
        KRS_905.addRoomFacility(rf10);
        KRS_905.addRoomFacility(rf11);
        KRS_905.addRoomFacility(rf12);
        KRS_905.addRoomFacility(rf13);
        KRS_905.addRoomFacility(rf14);
        KRS_905.addRoomFacility(rf15);

        KRS_905.addMinibarItem(m1);
        KRS_905.addMinibarItem(m2);
        KRS_905.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRS_905);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_905"));

        Room KRS_906 = new Room("KRS_906", "906", "Premium", 4, "Unavailable", h4);

        KRS_906.addRoomFacility(rf1);
        KRS_906.addRoomFacility(rf2);
        KRS_906.addRoomFacility(rf3);
        KRS_906.addRoomFacility(rf4);
        KRS_906.addRoomFacility(rf5);
        KRS_906.addRoomFacility(rf6);
        KRS_906.addRoomFacility(rf7);
        KRS_906.addRoomFacility(rf8);
        KRS_906.addRoomFacility(rf9);
        KRS_906.addRoomFacility(rf10);
        KRS_906.addRoomFacility(rf11);
        KRS_906.addRoomFacility(rf12);
        KRS_906.addRoomFacility(rf13);
        KRS_906.addRoomFacility(rf14);
        KRS_906.addRoomFacility(rf15);

        KRS_906.addMinibarItem(m1);
        KRS_906.addMinibarItem(m2);
        KRS_906.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRS_906);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_906"));

        Room KRS_907 = new Room("KRS_907", "907", "Premium", 4, "Occupied", h4);

        KRS_907.addRoomFacility(rf1);
        KRS_907.addRoomFacility(rf2);
        KRS_907.addRoomFacility(rf3);
        KRS_907.addRoomFacility(rf4);
        KRS_907.addRoomFacility(rf5);
        KRS_907.addRoomFacility(rf6);
        KRS_907.addRoomFacility(rf7);
        KRS_907.addRoomFacility(rf8);
        KRS_907.addRoomFacility(rf9);
        KRS_907.addRoomFacility(rf10);
        KRS_907.addRoomFacility(rf11);
        KRS_907.addRoomFacility(rf12);
        KRS_907.addRoomFacility(rf13);
        KRS_907.addRoomFacility(rf14);
        KRS_907.addRoomFacility(rf15);

        KRS_907.addMinibarItem(m1);
        KRS_907.addMinibarItem(m2);
        KRS_907.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRS_907);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_907"));

        Room KRS_1201 = new Room("KRS_1201", "1201", "Suite", 4, "Available", h4);

        KRS_1201.addRoomFacility(rf1);
        KRS_1201.addRoomFacility(rf2);
        KRS_1201.addRoomFacility(rf3);
        KRS_1201.addRoomFacility(rf4);
        KRS_1201.addRoomFacility(rf5);
        KRS_1201.addRoomFacility(rf6);
        KRS_1201.addRoomFacility(rf7);
        KRS_1201.addRoomFacility(rf8);
        KRS_1201.addRoomFacility(rf9);
        KRS_1201.addRoomFacility(rf10);
        KRS_1201.addRoomFacility(rf11);
        KRS_1201.addRoomFacility(rf12);
        KRS_1201.addRoomFacility(rf13);
        KRS_1201.addRoomFacility(rf14);
        KRS_1201.addRoomFacility(rf15);
        KRS_1201.addRoomFacility(rf16);
        KRS_1201.addRoomFacility(rf17);
        KRS_1201.addRoomFacility(rf18);
        KRS_1201.addRoomFacility(rf19);

        KRS_1201.addMinibarItem(m1);
        KRS_1201.addMinibarItem(m2);
        KRS_1201.addMinibarItem(m3);
        KRS_1201.addMinibarItem(m4);
        KRS_1201.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRS_1201);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_1201"));

        Room KRS_1202 = new Room("KRS_1202", "1202", "Suite", 4, "Occupied", h4);

        KRS_1202.addRoomFacility(rf1);
        KRS_1202.addRoomFacility(rf2);
        KRS_1202.addRoomFacility(rf3);
        KRS_1202.addRoomFacility(rf4);
        KRS_1202.addRoomFacility(rf5);
        KRS_1202.addRoomFacility(rf6);
        KRS_1202.addRoomFacility(rf7);
        KRS_1202.addRoomFacility(rf8);
        KRS_1202.addRoomFacility(rf9);
        KRS_1202.addRoomFacility(rf10);
        KRS_1202.addRoomFacility(rf11);
        KRS_1202.addRoomFacility(rf12);
        KRS_1202.addRoomFacility(rf13);
        KRS_1202.addRoomFacility(rf14);
        KRS_1202.addRoomFacility(rf15);
        KRS_1202.addRoomFacility(rf16);
        KRS_1202.addRoomFacility(rf17);
        KRS_1202.addRoomFacility(rf18);
        KRS_1202.addRoomFacility(rf19);

        KRS_1202.addMinibarItem(m1);
        KRS_1202.addMinibarItem(m2);
        KRS_1202.addMinibarItem(m3);
        KRS_1202.addMinibarItem(m4);
        KRS_1202.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRS_1202);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_1202"));

        Room KRS_1203 = new Room("KRS_1203", "1203", "Suite", 4, "Available", h4);

        KRS_1203.addRoomFacility(rf1);
        KRS_1203.addRoomFacility(rf2);
        KRS_1203.addRoomFacility(rf3);
        KRS_1203.addRoomFacility(rf4);
        KRS_1203.addRoomFacility(rf5);
        KRS_1203.addRoomFacility(rf6);
        KRS_1203.addRoomFacility(rf7);
        KRS_1203.addRoomFacility(rf8);
        KRS_1203.addRoomFacility(rf9);
        KRS_1203.addRoomFacility(rf10);
        KRS_1203.addRoomFacility(rf11);
        KRS_1203.addRoomFacility(rf12);
        KRS_1203.addRoomFacility(rf13);
        KRS_1203.addRoomFacility(rf14);
        KRS_1203.addRoomFacility(rf15);
        KRS_1203.addRoomFacility(rf16);
        KRS_1203.addRoomFacility(rf17);
        KRS_1203.addRoomFacility(rf18);
        KRS_1203.addRoomFacility(rf19);

        KRS_1203.addMinibarItem(m1);
        KRS_1203.addMinibarItem(m2);
        KRS_1203.addMinibarItem(m3);
        KRS_1203.addMinibarItem(m4);
        KRS_1203.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRS_1203);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_1203"));

        Room KRS_1204 = new Room("KRS_1204", "1204", "Suite", 4, "Occupied", h4);

        KRS_1204.addRoomFacility(rf1);
        KRS_1204.addRoomFacility(rf2);
        KRS_1204.addRoomFacility(rf3);
        KRS_1204.addRoomFacility(rf4);
        KRS_1204.addRoomFacility(rf5);
        KRS_1204.addRoomFacility(rf6);
        KRS_1204.addRoomFacility(rf7);
        KRS_1204.addRoomFacility(rf8);
        KRS_1204.addRoomFacility(rf9);
        KRS_1204.addRoomFacility(rf10);
        KRS_1204.addRoomFacility(rf11);
        KRS_1204.addRoomFacility(rf12);
        KRS_1204.addRoomFacility(rf13);
        KRS_1204.addRoomFacility(rf14);
        KRS_1204.addRoomFacility(rf15);
        KRS_1204.addRoomFacility(rf16);
        KRS_1204.addRoomFacility(rf17);
        KRS_1204.addRoomFacility(rf18);
        KRS_1204.addRoomFacility(rf19);

        KRS_1204.addMinibarItem(m1);
        KRS_1204.addMinibarItem(m2);
        KRS_1204.addMinibarItem(m3);
        KRS_1204.addMinibarItem(m4);
        KRS_1204.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRS_1204);

        h4.addRoom(roomSessionLocal.getRoomByName("KRS_1204"));
        em.flush();

    }

    public void initializeKRERoom() throws NoResultException {
        Hotel h5 = hotelSessionLocal.getHotelByName("Kent Ridge East");
        MinibarItem m1 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Potato Chips");
        MinibarItem m2 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Chocolate");
        MinibarItem m3 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Mineral Water");
        MinibarItem m4 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Coke");
        MinibarItem m5 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Wine");
        RoomFacility rf1 = roomFacilitySessionLocal.getRoomFacilityByName("Wifi");
        RoomFacility rf2 = roomFacilitySessionLocal.getRoomFacilityByName("Minibar");
        RoomFacility rf3 = roomFacilitySessionLocal.getRoomFacilityByName("Aircon");
        RoomFacility rf4 = roomFacilitySessionLocal.getRoomFacilityByName("Cable TV");
        RoomFacility rf5 = roomFacilitySessionLocal.getRoomFacilityByName("Telephone");
        RoomFacility rf6 = roomFacilitySessionLocal.getRoomFacilityByName("Water Heater");
        RoomFacility rf7 = roomFacilitySessionLocal.getRoomFacilityByName("Bathroom with Shower Head");
        RoomFacility rf8 = roomFacilitySessionLocal.getRoomFacilityByName("HairDryer");
        RoomFacility rf9 = roomFacilitySessionLocal.getRoomFacilityByName("Safe Deposit Box");
        RoomFacility rf10 = roomFacilitySessionLocal.getRoomFacilityByName("Water Kettle with complimentary teas/coffee");
        RoomFacility rf11 = roomFacilitySessionLocal.getRoomFacilityByName("Room with Windows");
        RoomFacility rf12 = roomFacilitySessionLocal.getRoomFacilityByName("Bathtub");
        RoomFacility rf13 = roomFacilitySessionLocal.getRoomFacilityByName("Mini Fridge");
        RoomFacility rf14 = roomFacilitySessionLocal.getRoomFacilityByName("Coffee Machine");
        RoomFacility rf15 = roomFacilitySessionLocal.getRoomFacilityByName("Balcony");
        RoomFacility rf16 = roomFacilitySessionLocal.getRoomFacilityByName("Microwave");
        RoomFacility rf17 = roomFacilitySessionLocal.getRoomFacilityByName("Gaming Console");
        RoomFacility rf18 = roomFacilitySessionLocal.getRoomFacilityByName("Entertainment System");
        RoomFacility rf19 = roomFacilitySessionLocal.getRoomFacilityByName("Blackout Curtains");
        RoomFacility rf20 = roomFacilitySessionLocal.getRoomFacilityByName("High Ceiling");
        RoomFacility rf21 = roomFacilitySessionLocal.getRoomFacilityByName("Jaccuzi");
        RoomFacility rf22 = roomFacilitySessionLocal.getRoomFacilityByName("Kitchen");

        FunctionRoom KREFR1 = new FunctionRoom("KREFR1", 20, "Available", 20000.00, h5);
        FunctionRoom KREFR2 = new FunctionRoom("KREFR2", 100, "Available", 100000.00, h5);
        FunctionRoom KREFR3 = new FunctionRoom("KREFR3", 50, "Available", 50000.00, h5);
        FunctionRoom KREFR4 = new FunctionRoom("KREFR4", 70, "Available", 70000.00, h5);
        FunctionRoom KREFR5 = new FunctionRoom("KREFR5", 80, "Available", 80000.00, h5);

        functionRoomSessionLocal.createFunctionRoom(KREFR1);
        functionRoomSessionLocal.createFunctionRoom(KREFR2);
        functionRoomSessionLocal.createFunctionRoom(KREFR3);
        functionRoomSessionLocal.createFunctionRoom(KREFR4);
        functionRoomSessionLocal.createFunctionRoom(KREFR5);

        h5.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KREFR1"));
        h5.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KREFR2"));
        h5.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KREFR3"));
        h5.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KREFR4"));
        h5.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KREFR5"));

        Room KRES1 = new Room("KRE_201", "201", "Standard", 2, "Occupied", h5);
        KRES1.addRoomFacility(rf1);
        KRES1.addRoomFacility(rf2);
        KRES1.addRoomFacility(rf3);
        KRES1.addRoomFacility(rf4);
        KRES1.addRoomFacility(rf5);
        KRES1.addRoomFacility(rf6);
        KRES1.addRoomFacility(rf7);
        KRES1.addRoomFacility(rf8);
        KRES1.addRoomFacility(rf9);
        KRES1.addRoomFacility(rf10);
        KRES1.addMinibarItem(m1);
        KRES1.addMinibarItem(m2);
        KRES1.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES1);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_201"));

        Room KRES2 = new Room("KRE_202", "202", "Standard", 2, "Available", h5);
        KRES2.addRoomFacility(rf1);
        KRES2.addRoomFacility(rf2);
        KRES2.addRoomFacility(rf3);
        KRES2.addRoomFacility(rf4);
        KRES2.addRoomFacility(rf5);
        KRES2.addRoomFacility(rf6);
        KRES2.addRoomFacility(rf7);
        KRES2.addRoomFacility(rf8);
        KRES2.addRoomFacility(rf9);
        KRES2.addRoomFacility(rf10);
        KRES2.addMinibarItem(m1);
        KRES2.addMinibarItem(m2);
        KRES2.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES2);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_202"));

        Room KRES3 = new Room("KRE_203", "203", "Standard", 2, "Occupied", h5);
        KRES3.addRoomFacility(rf1);
        KRES3.addRoomFacility(rf2);
        KRES3.addRoomFacility(rf3);
        KRES3.addRoomFacility(rf4);
        KRES3.addRoomFacility(rf5);
        KRES3.addRoomFacility(rf6);
        KRES3.addRoomFacility(rf7);
        KRES3.addRoomFacility(rf8);
        KRES3.addRoomFacility(rf9);
        KRES3.addRoomFacility(rf10);
        KRES3.addMinibarItem(m1);
        KRES3.addMinibarItem(m2);
        KRES3.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES3);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_203"));

        Room KRES4 = new Room("KRE_204", "204", "Standard", 2, "Available", h5);
        KRES4.addRoomFacility(rf1);
        KRES4.addRoomFacility(rf2);
        KRES4.addRoomFacility(rf3);
        KRES4.addRoomFacility(rf4);
        KRES4.addRoomFacility(rf5);
        KRES4.addRoomFacility(rf6);
        KRES4.addRoomFacility(rf7);
        KRES4.addRoomFacility(rf8);
        KRES4.addRoomFacility(rf9);
        KRES4.addRoomFacility(rf10);
        KRES4.addMinibarItem(m1);
        KRES4.addMinibarItem(m2);
        KRES4.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES4);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_204"));

        Room KRES5 = new Room("KRE_205", "205", "Standard", 2, "Available", h5);
        KRES5.addRoomFacility(rf1);
        KRES5.addRoomFacility(rf2);
        KRES5.addRoomFacility(rf3);
        KRES5.addRoomFacility(rf4);
        KRES5.addRoomFacility(rf5);
        KRES5.addRoomFacility(rf6);
        KRES5.addRoomFacility(rf7);
        KRES5.addRoomFacility(rf8);
        KRES5.addRoomFacility(rf9);
        KRES5.addRoomFacility(rf10);
        KRES5.addMinibarItem(m1);
        KRES5.addMinibarItem(m2);
        KRES5.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES5);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_205"));

        Room KRES6 = new Room("KRE_206", "206", "Standard", 2, "Unavailable", h5);
        KRES6.addRoomFacility(rf1);
        KRES6.addRoomFacility(rf2);
        KRES6.addRoomFacility(rf3);
        KRES6.addRoomFacility(rf4);
        KRES6.addRoomFacility(rf5);
        KRES6.addRoomFacility(rf6);
        KRES6.addRoomFacility(rf7);
        KRES6.addRoomFacility(rf8);
        KRES6.addRoomFacility(rf9);
        KRES6.addRoomFacility(rf10);
        KRES6.addMinibarItem(m1);
        KRES6.addMinibarItem(m2);
        KRES6.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES6);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_206"));

        Room KRES7 = new Room("KRE_207", "207", "Standard", 2, "Occupied", h5);
        KRES7.addRoomFacility(rf1);
        KRES7.addRoomFacility(rf2);
        KRES7.addRoomFacility(rf3);
        KRES7.addRoomFacility(rf4);
        KRES7.addRoomFacility(rf5);
        KRES7.addRoomFacility(rf6);
        KRES7.addRoomFacility(rf7);
        KRES7.addRoomFacility(rf8);
        KRES7.addRoomFacility(rf9);
        KRES7.addRoomFacility(rf10);
        KRES7.addMinibarItem(m1);
        KRES7.addMinibarItem(m2);
        KRES7.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES7);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_207"));

        Room KRES8 = new Room("KRE_208", "208", "Standard", 2, "Available", h5);
        KRES8.addRoomFacility(rf1);
        KRES8.addRoomFacility(rf2);
        KRES8.addRoomFacility(rf3);
        KRES8.addRoomFacility(rf4);
        KRES8.addRoomFacility(rf5);
        KRES8.addRoomFacility(rf6);
        KRES8.addRoomFacility(rf7);
        KRES8.addRoomFacility(rf8);
        KRES8.addRoomFacility(rf9);
        KRES8.addRoomFacility(rf10);
        KRES8.addMinibarItem(m1);
        KRES8.addMinibarItem(m2);
        KRES8.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES8);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_208"));

        Room KRES11 = new Room("KRE_301", "301", "Standard", 2, "Occupied", h5);
        KRES11.addRoomFacility(rf1);
        KRES11.addRoomFacility(rf2);
        KRES11.addRoomFacility(rf3);
        KRES11.addRoomFacility(rf4);
        KRES11.addRoomFacility(rf5);
        KRES11.addRoomFacility(rf6);
        KRES11.addRoomFacility(rf7);
        KRES11.addRoomFacility(rf8);
        KRES11.addRoomFacility(rf9);
        KRES11.addRoomFacility(rf10);
        KRES11.addMinibarItem(m1);
        KRES11.addMinibarItem(m2);
        KRES11.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES11);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_301"));

        Room KRES12 = new Room("KRE_302", "302", "Standard", 2, "Available", h5);
        KRES12.addRoomFacility(rf1);
        KRES12.addRoomFacility(rf2);
        KRES12.addRoomFacility(rf3);
        KRES12.addRoomFacility(rf4);
        KRES12.addRoomFacility(rf5);
        KRES12.addRoomFacility(rf6);
        KRES12.addRoomFacility(rf7);
        KRES12.addRoomFacility(rf8);
        KRES12.addRoomFacility(rf9);
        KRES12.addRoomFacility(rf10);
        KRES12.addMinibarItem(m1);
        KRES12.addMinibarItem(m2);
        KRES12.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES12);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_302"));

        Room KRES13 = new Room("KRE_303", "303", "Standard", 2, "Available", h5);
        KRES13.addRoomFacility(rf1);
        KRES13.addRoomFacility(rf2);
        KRES13.addRoomFacility(rf3);
        KRES13.addRoomFacility(rf4);
        KRES13.addRoomFacility(rf5);
        KRES13.addRoomFacility(rf6);
        KRES13.addRoomFacility(rf7);
        KRES13.addRoomFacility(rf8);
        KRES13.addRoomFacility(rf9);
        KRES13.addRoomFacility(rf10);
        KRES13.addMinibarItem(m1);
        KRES13.addMinibarItem(m2);
        KRES13.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES13);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_303"));

        Room KRES14 = new Room("KRE_304", "304", "Standard", 2, "Occupied", h5);
        KRES14.addRoomFacility(rf1);
        KRES14.addRoomFacility(rf2);
        KRES14.addRoomFacility(rf3);
        KRES14.addRoomFacility(rf4);
        KRES14.addRoomFacility(rf5);
        KRES14.addRoomFacility(rf6);
        KRES14.addRoomFacility(rf7);
        KRES14.addRoomFacility(rf8);
        KRES14.addRoomFacility(rf9);
        KRES14.addRoomFacility(rf10);
        KRES14.addMinibarItem(m1);
        KRES14.addMinibarItem(m2);
        KRES14.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES14);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_304"));

        Room KRES15 = new Room("KRE_305", "305", "Standard", 2, "Available", h5);
        KRES15.addRoomFacility(rf1);
        KRES15.addRoomFacility(rf2);
        KRES15.addRoomFacility(rf3);
        KRES15.addRoomFacility(rf4);
        KRES15.addRoomFacility(rf5);
        KRES15.addRoomFacility(rf6);
        KRES15.addRoomFacility(rf7);
        KRES15.addRoomFacility(rf8);
        KRES15.addRoomFacility(rf9);
        KRES15.addRoomFacility(rf10);
        KRES15.addMinibarItem(m1);
        KRES15.addMinibarItem(m2);
        KRES15.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES15);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_305"));

        Room KRES16 = new Room("KRE_306", "306", "Standard", 2, "Available", h5);
        KRES16.addRoomFacility(rf1);
        KRES16.addRoomFacility(rf2);
        KRES16.addRoomFacility(rf3);
        KRES16.addRoomFacility(rf4);
        KRES16.addRoomFacility(rf5);
        KRES16.addRoomFacility(rf6);
        KRES16.addRoomFacility(rf7);
        KRES16.addRoomFacility(rf8);
        KRES16.addRoomFacility(rf9);
        KRES16.addRoomFacility(rf10);
        KRES16.addMinibarItem(m1);
        KRES16.addMinibarItem(m2);
        KRES16.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES16);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_306"));

        Room KRES17 = new Room("KRE_307", "307", "Standard", 2, "Occupied", h5);
        KRES17.addRoomFacility(rf1);
        KRES17.addRoomFacility(rf2);
        KRES17.addRoomFacility(rf3);
        KRES17.addRoomFacility(rf4);
        KRES17.addRoomFacility(rf5);
        KRES17.addRoomFacility(rf6);
        KRES17.addRoomFacility(rf7);
        KRES17.addRoomFacility(rf8);
        KRES17.addRoomFacility(rf9);
        KRES17.addRoomFacility(rf10);
        KRES17.addMinibarItem(m1);
        KRES17.addMinibarItem(m2);
        KRES17.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES17);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_307"));

        Room KRES18 = new Room("KRE_308", "308", "Standard", 2, "Unavailable", h5);
        KRES18.addRoomFacility(rf1);
        KRES18.addRoomFacility(rf2);
        KRES18.addRoomFacility(rf3);
        KRES18.addRoomFacility(rf4);
        KRES18.addRoomFacility(rf5);
        KRES18.addRoomFacility(rf6);
        KRES18.addRoomFacility(rf7);
        KRES18.addRoomFacility(rf8);
        KRES18.addRoomFacility(rf9);
        KRES18.addRoomFacility(rf10);
        KRES18.addMinibarItem(m1);
        KRES18.addMinibarItem(m2);
        KRES18.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES18);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_308"));

        Room KRES21 = new Room("KRE_401", "401", "Standard", 2, "Available", h5);
        KRES21.addRoomFacility(rf1);
        KRES21.addRoomFacility(rf2);
        KRES21.addRoomFacility(rf3);
        KRES21.addRoomFacility(rf4);
        KRES21.addRoomFacility(rf5);
        KRES21.addRoomFacility(rf6);
        KRES21.addRoomFacility(rf7);
        KRES21.addRoomFacility(rf8);
        KRES21.addRoomFacility(rf9);
        KRES21.addRoomFacility(rf10);
        KRES21.addMinibarItem(m1);
        KRES21.addMinibarItem(m2);
        KRES21.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES21);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_401"));

        Room KRES22 = new Room("KRE_402", "402", "Standard", 2, "Available", h5);
        KRES22.addRoomFacility(rf1);
        KRES22.addRoomFacility(rf2);
        KRES22.addRoomFacility(rf3);
        KRES22.addRoomFacility(rf4);
        KRES22.addRoomFacility(rf5);
        KRES22.addRoomFacility(rf6);
        KRES22.addRoomFacility(rf7);
        KRES22.addRoomFacility(rf8);
        KRES22.addRoomFacility(rf9);
        KRES22.addRoomFacility(rf10);
        KRES22.addMinibarItem(m1);
        KRES22.addMinibarItem(m2);
        KRES22.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES22);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_402"));

        Room KRES23 = new Room("KRE_403", "403", "Standard", 2, "Occupied", h5);
        KRES23.addRoomFacility(rf1);
        KRES23.addRoomFacility(rf2);
        KRES23.addRoomFacility(rf3);
        KRES23.addRoomFacility(rf4);
        KRES23.addRoomFacility(rf5);
        KRES23.addRoomFacility(rf6);
        KRES23.addRoomFacility(rf7);
        KRES23.addRoomFacility(rf8);
        KRES23.addRoomFacility(rf9);
        KRES23.addRoomFacility(rf10);
        KRES23.addMinibarItem(m1);
        KRES23.addMinibarItem(m2);
        KRES23.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES23);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_403"));

        Room KRES24 = new Room("KRE_404", "404", "Standard", 2, "Available", h5);
        KRES24.addRoomFacility(rf1);
        KRES24.addRoomFacility(rf2);
        KRES24.addRoomFacility(rf3);
        KRES24.addRoomFacility(rf4);
        KRES24.addRoomFacility(rf5);
        KRES24.addRoomFacility(rf6);
        KRES24.addRoomFacility(rf7);
        KRES24.addRoomFacility(rf8);
        KRES24.addRoomFacility(rf9);
        KRES24.addRoomFacility(rf10);
        KRES24.addMinibarItem(m1);
        KRES24.addMinibarItem(m2);
        KRES24.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES24);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_404"));

        Room KRES25 = new Room("KRE_405", "405", "Standard", 2, "Available", h5);
        KRES25.addRoomFacility(rf1);
        KRES25.addRoomFacility(rf2);
        KRES25.addRoomFacility(rf3);
        KRES25.addRoomFacility(rf4);
        KRES25.addRoomFacility(rf5);
        KRES25.addRoomFacility(rf6);
        KRES25.addRoomFacility(rf7);
        KRES25.addRoomFacility(rf8);
        KRES25.addRoomFacility(rf9);
        KRES25.addRoomFacility(rf10);
        KRES25.addMinibarItem(m1);
        KRES25.addMinibarItem(m2);
        KRES25.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES25);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_405"));

        Room KRES26 = new Room("KRE_406", "406", "Standard", 2, "Occupied", h5);
        KRES26.addRoomFacility(rf1);
        KRES26.addRoomFacility(rf2);
        KRES26.addRoomFacility(rf3);
        KRES26.addRoomFacility(rf4);
        KRES26.addRoomFacility(rf5);
        KRES26.addRoomFacility(rf6);
        KRES26.addRoomFacility(rf7);
        KRES26.addRoomFacility(rf8);
        KRES26.addRoomFacility(rf9);
        KRES26.addRoomFacility(rf10);
        KRES26.addMinibarItem(m1);
        KRES26.addMinibarItem(m2);
        KRES26.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES26);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_406"));

        Room KRES27 = new Room("KRE_407", "407", "Standard", 2, "Available", h5);
        KRES27.addRoomFacility(rf1);
        KRES27.addRoomFacility(rf2);
        KRES27.addRoomFacility(rf3);
        KRES27.addRoomFacility(rf4);
        KRES27.addRoomFacility(rf5);
        KRES27.addRoomFacility(rf6);
        KRES27.addRoomFacility(rf7);
        KRES27.addRoomFacility(rf8);
        KRES27.addRoomFacility(rf9);
        KRES27.addRoomFacility(rf10);
        KRES27.addMinibarItem(m1);
        KRES27.addMinibarItem(m2);
        KRES27.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES27);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_407"));

        Room KRES28 = new Room("KRE_408", "408", "Standard", 2, "Available", h5);
        KRES28.addRoomFacility(rf1);
        KRES28.addRoomFacility(rf2);
        KRES28.addRoomFacility(rf3);
        KRES28.addRoomFacility(rf4);
        KRES28.addRoomFacility(rf5);
        KRES28.addRoomFacility(rf6);
        KRES28.addRoomFacility(rf7);
        KRES28.addRoomFacility(rf8);
        KRES28.addRoomFacility(rf9);
        KRES28.addRoomFacility(rf10);
        KRES28.addMinibarItem(m1);
        KRES28.addMinibarItem(m2);
        KRES28.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES28);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_408"));

        Room KRES29 = new Room("KRE_409", "409", "Standard", 2, "Available", h5);
        KRES29.addRoomFacility(rf1);
        KRES29.addRoomFacility(rf2);
        KRES29.addRoomFacility(rf3);
        KRES29.addRoomFacility(rf4);
        KRES29.addRoomFacility(rf5);
        KRES29.addRoomFacility(rf6);
        KRES29.addRoomFacility(rf7);
        KRES29.addRoomFacility(rf8);
        KRES29.addRoomFacility(rf9);
        KRES29.addRoomFacility(rf10);
        KRES29.addMinibarItem(m1);
        KRES29.addMinibarItem(m2);
        KRES29.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES29);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_409"));

        Room KRES30 = new Room("KRE_410", "410", "Standard", 2, "Available", h5);
        KRES30.addRoomFacility(rf1);
        KRES30.addRoomFacility(rf2);
        KRES30.addRoomFacility(rf3);
        KRES30.addRoomFacility(rf4);
        KRES30.addRoomFacility(rf5);
        KRES30.addRoomFacility(rf6);
        KRES30.addRoomFacility(rf7);
        KRES30.addRoomFacility(rf8);
        KRES30.addRoomFacility(rf9);
        KRES30.addRoomFacility(rf10);
        KRES30.addMinibarItem(m1);
        KRES30.addMinibarItem(m2);
        KRES30.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES30);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_410"));

        Room KRES31 = new Room("KRE_501", "501", "Standard", 2, "Occupied", h5);
        KRES31.addRoomFacility(rf1);
        KRES31.addRoomFacility(rf2);
        KRES31.addRoomFacility(rf3);
        KRES31.addRoomFacility(rf4);
        KRES31.addRoomFacility(rf5);
        KRES31.addRoomFacility(rf6);
        KRES31.addRoomFacility(rf7);
        KRES31.addRoomFacility(rf8);
        KRES31.addRoomFacility(rf9);
        KRES31.addRoomFacility(rf10);
        KRES31.addMinibarItem(m1);
        KRES31.addMinibarItem(m2);
        KRES31.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES31);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_501"));

        Room KRES32 = new Room("KRE_502", "502", "Standard", 2, "Available", h5);
        KRES32.addRoomFacility(rf1);
        KRES32.addRoomFacility(rf2);
        KRES32.addRoomFacility(rf3);
        KRES32.addRoomFacility(rf4);
        KRES32.addRoomFacility(rf5);
        KRES32.addRoomFacility(rf6);
        KRES32.addRoomFacility(rf7);
        KRES32.addRoomFacility(rf8);
        KRES32.addRoomFacility(rf9);
        KRES32.addRoomFacility(rf10);
        KRES32.addMinibarItem(m1);
        KRES32.addMinibarItem(m2);
        KRES32.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES32);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_502"));

        Room KRES33 = new Room("KRE_503", "503", "Standard", 2, "Available", h5);
        KRES33.addRoomFacility(rf1);
        KRES33.addRoomFacility(rf2);
        KRES33.addRoomFacility(rf3);
        KRES33.addRoomFacility(rf4);
        KRES33.addRoomFacility(rf5);
        KRES33.addRoomFacility(rf6);
        KRES33.addRoomFacility(rf7);
        KRES33.addRoomFacility(rf8);
        KRES33.addRoomFacility(rf9);
        KRES33.addRoomFacility(rf10);
        KRES33.addMinibarItem(m1);
        KRES33.addMinibarItem(m2);
        KRES33.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES33);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_503"));

        Room KRES34 = new Room("KRE_504", "504", "Standard", 2, "Occupied", h5);
        KRES34.addRoomFacility(rf1);
        KRES34.addRoomFacility(rf2);
        KRES34.addRoomFacility(rf3);
        KRES34.addRoomFacility(rf4);
        KRES34.addRoomFacility(rf5);
        KRES34.addRoomFacility(rf6);
        KRES34.addRoomFacility(rf7);
        KRES34.addRoomFacility(rf8);
        KRES34.addRoomFacility(rf9);
        KRES34.addRoomFacility(rf10);
        KRES34.addMinibarItem(m1);
        KRES34.addMinibarItem(m2);
        KRES34.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES34);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_504"));

        Room KRES35 = new Room("KRE_505", "505", "Standard", 2, "Available", h5);
        KRES35.addRoomFacility(rf1);
        KRES35.addRoomFacility(rf2);
        KRES35.addRoomFacility(rf3);
        KRES35.addRoomFacility(rf4);
        KRES35.addRoomFacility(rf5);
        KRES35.addRoomFacility(rf6);
        KRES35.addRoomFacility(rf7);
        KRES35.addRoomFacility(rf8);
        KRES35.addRoomFacility(rf9);
        KRES35.addRoomFacility(rf10);
        KRES35.addMinibarItem(m1);
        KRES35.addMinibarItem(m2);
        KRES35.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES35);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_505"));

        Room KRES36 = new Room("KRE_506", "506", "Standard", 2, "Available", h5);
        KRES36.addRoomFacility(rf1);
        KRES36.addRoomFacility(rf2);
        KRES36.addRoomFacility(rf3);
        KRES36.addRoomFacility(rf4);
        KRES36.addRoomFacility(rf5);
        KRES36.addRoomFacility(rf6);
        KRES36.addRoomFacility(rf7);
        KRES36.addRoomFacility(rf8);
        KRES36.addRoomFacility(rf9);
        KRES36.addRoomFacility(rf10);
        KRES36.addMinibarItem(m1);
        KRES36.addMinibarItem(m2);
        KRES36.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES36);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_506"));

        Room KRES37 = new Room("KRE_507", "507", "Standard", 2, "Available", h5);
        KRES37.addRoomFacility(rf1);
        KRES37.addRoomFacility(rf2);
        KRES37.addRoomFacility(rf3);
        KRES37.addRoomFacility(rf4);
        KRES37.addRoomFacility(rf5);
        KRES37.addRoomFacility(rf6);
        KRES37.addRoomFacility(rf7);
        KRES37.addRoomFacility(rf8);
        KRES37.addRoomFacility(rf9);
        KRES37.addRoomFacility(rf10);
        KRES37.addMinibarItem(m1);
        KRES37.addMinibarItem(m2);
        KRES37.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES37);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_507"));

        Room KRES38 = new Room("KRE_508", "508", "Standard", 2, "Occupied", h5);
        KRES38.addRoomFacility(rf1);
        KRES38.addRoomFacility(rf2);
        KRES38.addRoomFacility(rf3);
        KRES38.addRoomFacility(rf4);
        KRES38.addRoomFacility(rf5);
        KRES38.addRoomFacility(rf6);
        KRES38.addRoomFacility(rf7);
        KRES38.addRoomFacility(rf8);
        KRES38.addRoomFacility(rf9);
        KRES38.addRoomFacility(rf10);
        KRES38.addMinibarItem(m1);
        KRES38.addMinibarItem(m2);
        KRES38.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES38);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_508"));

        Room KRES41 = new Room("KRE_601", "601", "Standard", 2, "Unavailable", h5);
        KRES41.addRoomFacility(rf1);
        KRES41.addRoomFacility(rf2);
        KRES41.addRoomFacility(rf3);
        KRES41.addRoomFacility(rf4);
        KRES41.addRoomFacility(rf5);
        KRES41.addRoomFacility(rf6);
        KRES41.addRoomFacility(rf7);
        KRES41.addRoomFacility(rf8);
        KRES41.addRoomFacility(rf9);
        KRES41.addRoomFacility(rf10);
        KRES41.addMinibarItem(m1);
        KRES41.addMinibarItem(m2);
        KRES41.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES41);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_601"));

        Room KRES42 = new Room("KRE_602", "602", "Standard", 2, "Available", h5);
        KRES42.addRoomFacility(rf1);
        KRES42.addRoomFacility(rf2);
        KRES42.addRoomFacility(rf3);
        KRES42.addRoomFacility(rf4);
        KRES42.addRoomFacility(rf5);
        KRES42.addRoomFacility(rf6);
        KRES42.addRoomFacility(rf7);
        KRES42.addRoomFacility(rf8);
        KRES42.addRoomFacility(rf9);
        KRES42.addRoomFacility(rf10);
        KRES42.addMinibarItem(m1);
        KRES42.addMinibarItem(m2);
        KRES42.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES42);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_602"));

        Room KRES43 = new Room("KRE_603", "603", "Standard", 2, "Available", h5);
        KRES43.addRoomFacility(rf1);
        KRES43.addRoomFacility(rf2);
        KRES43.addRoomFacility(rf3);
        KRES43.addRoomFacility(rf4);
        KRES43.addRoomFacility(rf5);
        KRES43.addRoomFacility(rf6);
        KRES43.addRoomFacility(rf7);
        KRES43.addRoomFacility(rf8);
        KRES43.addRoomFacility(rf9);
        KRES43.addRoomFacility(rf10);
        KRES43.addMinibarItem(m1);
        KRES43.addMinibarItem(m2);
        KRES43.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES43);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_603"));

        Room KRES44 = new Room("KRE_604", "604", "Standard", 2, "Available", h5);
        KRES44.addRoomFacility(rf1);
        KRES44.addRoomFacility(rf2);
        KRES44.addRoomFacility(rf3);
        KRES44.addRoomFacility(rf4);
        KRES44.addRoomFacility(rf5);
        KRES44.addRoomFacility(rf6);
        KRES44.addRoomFacility(rf7);
        KRES44.addRoomFacility(rf8);
        KRES44.addRoomFacility(rf9);
        KRES44.addRoomFacility(rf10);
        KRES44.addMinibarItem(m1);
        KRES44.addMinibarItem(m2);
        KRES44.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES44);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_604"));

        Room KRES45 = new Room("KRE_605", "605", "Standard", 2, "Occupied", h5);
        KRES45.addRoomFacility(rf1);
        KRES45.addRoomFacility(rf2);
        KRES45.addRoomFacility(rf3);
        KRES45.addRoomFacility(rf4);
        KRES45.addRoomFacility(rf5);
        KRES45.addRoomFacility(rf6);
        KRES45.addRoomFacility(rf7);
        KRES45.addRoomFacility(rf8);
        KRES45.addRoomFacility(rf9);
        KRES45.addRoomFacility(rf10);
        KRES45.addMinibarItem(m1);
        KRES45.addMinibarItem(m2);
        KRES45.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES45);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_605"));

        Room KRES46 = new Room("KRE_606", "606", "Standard", 2, "Available", h5);
        KRES46.addRoomFacility(rf1);
        KRES46.addRoomFacility(rf2);
        KRES46.addRoomFacility(rf3);
        KRES46.addRoomFacility(rf4);
        KRES46.addRoomFacility(rf5);
        KRES46.addRoomFacility(rf6);
        KRES46.addRoomFacility(rf7);
        KRES46.addRoomFacility(rf8);
        KRES46.addRoomFacility(rf9);
        KRES46.addRoomFacility(rf10);
        KRES46.addMinibarItem(m1);
        KRES46.addMinibarItem(m2);
        KRES46.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES46);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_606"));

        Room KRES47 = new Room("KRE_607", "607", "Standard", 2, "Unavailable", h5);
        KRES47.addRoomFacility(rf1);
        KRES47.addRoomFacility(rf2);
        KRES47.addRoomFacility(rf3);
        KRES47.addRoomFacility(rf4);
        KRES47.addRoomFacility(rf5);
        KRES47.addRoomFacility(rf6);
        KRES47.addRoomFacility(rf7);
        KRES47.addRoomFacility(rf8);
        KRES47.addRoomFacility(rf9);
        KRES47.addRoomFacility(rf10);
        KRES47.addMinibarItem(m1);
        KRES47.addMinibarItem(m2);
        KRES47.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES47);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_607"));

        Room KRES48 = new Room("KRE_608", "608", "Standard", 2, "Available", h5);
        KRES48.addRoomFacility(rf1);
        KRES48.addRoomFacility(rf2);
        KRES48.addRoomFacility(rf3);
        KRES48.addRoomFacility(rf4);
        KRES48.addRoomFacility(rf5);
        KRES48.addRoomFacility(rf6);
        KRES48.addRoomFacility(rf7);
        KRES48.addRoomFacility(rf8);
        KRES48.addRoomFacility(rf9);
        KRES48.addRoomFacility(rf10);
        KRES48.addMinibarItem(m1);
        KRES48.addMinibarItem(m2);
        KRES48.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRES48);
        h5.addRoom(roomSessionLocal.getRoomByName("KRE_608"));

        Room KRE_1201 = new Room("KRE_1201", "1201", "Suite", 4, "Occupied", h5);

        KRE_1201.addRoomFacility(rf1);
        KRE_1201.addRoomFacility(rf2);
        KRE_1201.addRoomFacility(rf3);
        KRE_1201.addRoomFacility(rf4);
        KRE_1201.addRoomFacility(rf5);
        KRE_1201.addRoomFacility(rf6);
        KRE_1201.addRoomFacility(rf7);
        KRE_1201.addRoomFacility(rf8);
        KRE_1201.addRoomFacility(rf9);
        KRE_1201.addRoomFacility(rf10);
        KRE_1201.addRoomFacility(rf11);
        KRE_1201.addRoomFacility(rf12);
        KRE_1201.addRoomFacility(rf13);
        KRE_1201.addRoomFacility(rf14);
        KRE_1201.addRoomFacility(rf15);
        KRE_1201.addRoomFacility(rf16);
        KRE_1201.addRoomFacility(rf17);
        KRE_1201.addRoomFacility(rf18);
        KRE_1201.addRoomFacility(rf19);

        KRE_1201.addMinibarItem(m1);
        KRE_1201.addMinibarItem(m2);
        KRE_1201.addMinibarItem(m3);
        KRE_1201.addMinibarItem(m4);
        KRE_1201.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRE_1201);

        h5.addRoom(roomSessionLocal.getRoomByName("KRE_1201"));

        Room KRE_1202 = new Room("KRE_1202", "1202", "Suite", 4, "Available", h5);

        KRE_1202.addRoomFacility(rf1);
        KRE_1202.addRoomFacility(rf2);
        KRE_1202.addRoomFacility(rf3);
        KRE_1202.addRoomFacility(rf4);
        KRE_1202.addRoomFacility(rf5);
        KRE_1202.addRoomFacility(rf6);
        KRE_1202.addRoomFacility(rf7);
        KRE_1202.addRoomFacility(rf8);
        KRE_1202.addRoomFacility(rf9);
        KRE_1202.addRoomFacility(rf10);
        KRE_1202.addRoomFacility(rf11);
        KRE_1202.addRoomFacility(rf12);
        KRE_1202.addRoomFacility(rf13);
        KRE_1202.addRoomFacility(rf14);
        KRE_1202.addRoomFacility(rf15);
        KRE_1202.addRoomFacility(rf16);
        KRE_1202.addRoomFacility(rf17);
        KRE_1202.addRoomFacility(rf18);
        KRE_1202.addRoomFacility(rf19);

        KRE_1202.addMinibarItem(m1);
        KRE_1202.addMinibarItem(m2);
        KRE_1202.addMinibarItem(m3);
        KRE_1202.addMinibarItem(m4);
        KRE_1202.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRE_1202);

        h5.addRoom(roomSessionLocal.getRoomByName("KRE_1202"));

        Room KRE_1203 = new Room("KRE_1203", "1203", "Suite", 4, "Available", h5);

        KRE_1203.addRoomFacility(rf1);
        KRE_1203.addRoomFacility(rf2);
        KRE_1203.addRoomFacility(rf3);
        KRE_1203.addRoomFacility(rf4);
        KRE_1203.addRoomFacility(rf5);
        KRE_1203.addRoomFacility(rf6);
        KRE_1203.addRoomFacility(rf7);
        KRE_1203.addRoomFacility(rf8);
        KRE_1203.addRoomFacility(rf9);
        KRE_1203.addRoomFacility(rf10);
        KRE_1203.addRoomFacility(rf11);
        KRE_1203.addRoomFacility(rf12);
        KRE_1203.addRoomFacility(rf13);
        KRE_1203.addRoomFacility(rf14);
        KRE_1203.addRoomFacility(rf15);
        KRE_1203.addRoomFacility(rf16);
        KRE_1203.addRoomFacility(rf17);
        KRE_1203.addRoomFacility(rf18);
        KRE_1203.addRoomFacility(rf19);

        KRE_1203.addMinibarItem(m1);
        KRE_1203.addMinibarItem(m2);
        KRE_1203.addMinibarItem(m3);
        KRE_1203.addMinibarItem(m4);
        KRE_1203.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRE_1203);

        h5.addRoom(roomSessionLocal.getRoomByName("KRE_1203"));

        Room KRE_1204 = new Room("KRE_1204", "1204", "Suite", 4, "Occupied", h5);

        KRE_1204.addRoomFacility(rf1);
        KRE_1204.addRoomFacility(rf2);
        KRE_1204.addRoomFacility(rf3);
        KRE_1204.addRoomFacility(rf4);
        KRE_1204.addRoomFacility(rf5);
        KRE_1204.addRoomFacility(rf6);
        KRE_1204.addRoomFacility(rf7);
        KRE_1204.addRoomFacility(rf8);
        KRE_1204.addRoomFacility(rf9);
        KRE_1204.addRoomFacility(rf10);
        KRE_1204.addRoomFacility(rf11);
        KRE_1204.addRoomFacility(rf12);
        KRE_1204.addRoomFacility(rf13);
        KRE_1204.addRoomFacility(rf14);
        KRE_1204.addRoomFacility(rf15);
        KRE_1204.addRoomFacility(rf16);
        KRE_1204.addRoomFacility(rf17);
        KRE_1204.addRoomFacility(rf18);
        KRE_1204.addRoomFacility(rf19);

        KRE_1204.addMinibarItem(m1);
        KRE_1204.addMinibarItem(m2);
        KRE_1204.addMinibarItem(m3);
        KRE_1204.addMinibarItem(m4);
        KRE_1204.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRE_1204);

        h5.addRoom(roomSessionLocal.getRoomByName("KRE_1204"));

        em.flush();

    }

    public void initializeKRWRoom() throws NoResultException {
        Hotel h6 = hotelSessionLocal.getHotelByName("Kent Ridge West");
        MinibarItem m1 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Potato Chips");
        MinibarItem m2 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Chocolate");
        MinibarItem m3 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Mineral Water");
        MinibarItem m4 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Coke");
        MinibarItem m5 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Wine");
        RoomFacility rf1 = roomFacilitySessionLocal.getRoomFacilityByName("Wifi");
        RoomFacility rf2 = roomFacilitySessionLocal.getRoomFacilityByName("Minibar");
        RoomFacility rf3 = roomFacilitySessionLocal.getRoomFacilityByName("Aircon");
        RoomFacility rf4 = roomFacilitySessionLocal.getRoomFacilityByName("Cable TV");
        RoomFacility rf5 = roomFacilitySessionLocal.getRoomFacilityByName("Telephone");
        RoomFacility rf6 = roomFacilitySessionLocal.getRoomFacilityByName("Water Heater");
        RoomFacility rf7 = roomFacilitySessionLocal.getRoomFacilityByName("Bathroom with Shower Head");
        RoomFacility rf8 = roomFacilitySessionLocal.getRoomFacilityByName("HairDryer");
        RoomFacility rf9 = roomFacilitySessionLocal.getRoomFacilityByName("Safe Deposit Box");
        RoomFacility rf10 = roomFacilitySessionLocal.getRoomFacilityByName("Water Kettle with complimentary teas/coffee");
        RoomFacility rf11 = roomFacilitySessionLocal.getRoomFacilityByName("Room with Windows");
        RoomFacility rf12 = roomFacilitySessionLocal.getRoomFacilityByName("Bathtub");
        RoomFacility rf13 = roomFacilitySessionLocal.getRoomFacilityByName("Mini Fridge");
        RoomFacility rf14 = roomFacilitySessionLocal.getRoomFacilityByName("Coffee Machine");
        RoomFacility rf15 = roomFacilitySessionLocal.getRoomFacilityByName("Balcony");
        RoomFacility rf16 = roomFacilitySessionLocal.getRoomFacilityByName("Microwave");
        RoomFacility rf17 = roomFacilitySessionLocal.getRoomFacilityByName("Gaming Console");
        RoomFacility rf18 = roomFacilitySessionLocal.getRoomFacilityByName("Entertainment System");
        RoomFacility rf19 = roomFacilitySessionLocal.getRoomFacilityByName("Blackout Curtains");
        RoomFacility rf20 = roomFacilitySessionLocal.getRoomFacilityByName("High Ceiling");
        RoomFacility rf21 = roomFacilitySessionLocal.getRoomFacilityByName("Jaccuzi");
        RoomFacility rf22 = roomFacilitySessionLocal.getRoomFacilityByName("Kitchen");

        FunctionRoom KRWFR1 = new FunctionRoom("KRWFR1", 20, "Available", 20000.00, h6);
        FunctionRoom KRWFR2 = new FunctionRoom("KRWFR2", 100, "Available", 100000.00, h6);
        FunctionRoom KRWFR3 = new FunctionRoom("KRWFR3", 50, "Available", 50000.00, h6);
        FunctionRoom KRWFR4 = new FunctionRoom("KRWFR4", 70, "Available", 70000.00, h6);
        FunctionRoom KRWFR5 = new FunctionRoom("KRWFR5", 80, "Available", 80000.00, h6);

        functionRoomSessionLocal.createFunctionRoom(KRWFR1);
        functionRoomSessionLocal.createFunctionRoom(KRWFR2);
        functionRoomSessionLocal.createFunctionRoom(KRWFR3);
        functionRoomSessionLocal.createFunctionRoom(KRWFR4);
        functionRoomSessionLocal.createFunctionRoom(KRWFR5);

        h6.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRWFR1"));
        h6.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRWFR2"));
        h6.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRWFR3"));
        h6.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRWFR4"));
        h6.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRWFR5"));

        Room KRWS1 = new Room("KRW_201", "201", "Standard", 2, "Available", h6);
        KRWS1.addRoomFacility(rf1);
        KRWS1.addRoomFacility(rf2);
        KRWS1.addRoomFacility(rf3);
        KRWS1.addRoomFacility(rf4);
        KRWS1.addRoomFacility(rf5);
        KRWS1.addRoomFacility(rf6);
        KRWS1.addRoomFacility(rf7);
        KRWS1.addRoomFacility(rf8);
        KRWS1.addRoomFacility(rf9);
        KRWS1.addRoomFacility(rf10);
        KRWS1.addMinibarItem(m1);
        KRWS1.addMinibarItem(m2);
        KRWS1.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS1);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_201"));

        Room KRWS2 = new Room("KRW_202", "202", "Standard", 2, "Available", h6);
        KRWS2.addRoomFacility(rf1);
        KRWS2.addRoomFacility(rf2);
        KRWS2.addRoomFacility(rf3);
        KRWS2.addRoomFacility(rf4);
        KRWS2.addRoomFacility(rf5);
        KRWS2.addRoomFacility(rf6);
        KRWS2.addRoomFacility(rf7);
        KRWS2.addRoomFacility(rf8);
        KRWS2.addRoomFacility(rf9);
        KRWS2.addRoomFacility(rf10);
        KRWS2.addMinibarItem(m1);
        KRWS2.addMinibarItem(m2);
        KRWS2.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS2);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_202"));

        Room KRWS3 = new Room("KRW_203", "203", "Standard", 2, "Unavailable", h6);
        KRWS3.addRoomFacility(rf1);
        KRWS3.addRoomFacility(rf2);
        KRWS3.addRoomFacility(rf3);
        KRWS3.addRoomFacility(rf4);
        KRWS3.addRoomFacility(rf5);
        KRWS3.addRoomFacility(rf6);
        KRWS3.addRoomFacility(rf7);
        KRWS3.addRoomFacility(rf8);
        KRWS3.addRoomFacility(rf9);
        KRWS3.addRoomFacility(rf10);
        KRWS3.addMinibarItem(m1);
        KRWS3.addMinibarItem(m2);
        KRWS3.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS3);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_203"));

        Room KRWS4 = new Room("KRW_204", "204", "Standard", 2, "Available", h6);
        KRWS4.addRoomFacility(rf1);
        KRWS4.addRoomFacility(rf2);
        KRWS4.addRoomFacility(rf3);
        KRWS4.addRoomFacility(rf4);
        KRWS4.addRoomFacility(rf5);
        KRWS4.addRoomFacility(rf6);
        KRWS4.addRoomFacility(rf7);
        KRWS4.addRoomFacility(rf8);
        KRWS4.addRoomFacility(rf9);
        KRWS4.addRoomFacility(rf10);
        KRWS4.addMinibarItem(m1);
        KRWS4.addMinibarItem(m2);
        KRWS4.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS4);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_204"));

        Room KRWS5 = new Room("KRW_205", "205", "Standard", 2, "Occupied", h6);
        KRWS5.addRoomFacility(rf1);
        KRWS5.addRoomFacility(rf2);
        KRWS5.addRoomFacility(rf3);
        KRWS5.addRoomFacility(rf4);
        KRWS5.addRoomFacility(rf5);
        KRWS5.addRoomFacility(rf6);
        KRWS5.addRoomFacility(rf7);
        KRWS5.addRoomFacility(rf8);
        KRWS5.addRoomFacility(rf9);
        KRWS5.addRoomFacility(rf10);
        KRWS5.addMinibarItem(m1);
        KRWS5.addMinibarItem(m2);
        KRWS5.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS5);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_205"));

        Room KRWS6 = new Room("KRW_206", "206", "Standard", 2, "Available", h6);
        KRWS6.addRoomFacility(rf1);
        KRWS6.addRoomFacility(rf2);
        KRWS6.addRoomFacility(rf3);
        KRWS6.addRoomFacility(rf4);
        KRWS6.addRoomFacility(rf5);
        KRWS6.addRoomFacility(rf6);
        KRWS6.addRoomFacility(rf7);
        KRWS6.addRoomFacility(rf8);
        KRWS6.addRoomFacility(rf9);
        KRWS6.addRoomFacility(rf10);
        KRWS6.addMinibarItem(m1);
        KRWS6.addMinibarItem(m2);
        KRWS6.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS6);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_206"));

        Room KRWS7 = new Room("KRW_207", "207", "Standard", 2, "Available", h6);
        KRWS7.addRoomFacility(rf1);
        KRWS7.addRoomFacility(rf2);
        KRWS7.addRoomFacility(rf3);
        KRWS7.addRoomFacility(rf4);
        KRWS7.addRoomFacility(rf5);
        KRWS7.addRoomFacility(rf6);
        KRWS7.addRoomFacility(rf7);
        KRWS7.addRoomFacility(rf8);
        KRWS7.addRoomFacility(rf9);
        KRWS7.addRoomFacility(rf10);
        KRWS7.addMinibarItem(m1);
        KRWS7.addMinibarItem(m2);
        KRWS7.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS7);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_207"));

        Room KRWS8 = new Room("KRW_208", "208", "Standard", 2, "Available", h6);
        KRWS8.addRoomFacility(rf1);
        KRWS8.addRoomFacility(rf2);
        KRWS8.addRoomFacility(rf3);
        KRWS8.addRoomFacility(rf4);
        KRWS8.addRoomFacility(rf5);
        KRWS8.addRoomFacility(rf6);
        KRWS8.addRoomFacility(rf7);
        KRWS8.addRoomFacility(rf8);
        KRWS8.addRoomFacility(rf9);
        KRWS8.addRoomFacility(rf10);
        KRWS8.addMinibarItem(m1);
        KRWS8.addMinibarItem(m2);
        KRWS8.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS8);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_208"));

        Room KRWS11 = new Room("KRW_301", "301", "Standard", 2, "Unavailable", h6);
        KRWS11.addRoomFacility(rf1);
        KRWS11.addRoomFacility(rf2);
        KRWS11.addRoomFacility(rf3);
        KRWS11.addRoomFacility(rf4);
        KRWS11.addRoomFacility(rf5);
        KRWS11.addRoomFacility(rf6);
        KRWS11.addRoomFacility(rf7);
        KRWS11.addRoomFacility(rf8);
        KRWS11.addRoomFacility(rf9);
        KRWS11.addRoomFacility(rf10);
        KRWS11.addMinibarItem(m1);
        KRWS11.addMinibarItem(m2);
        KRWS11.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS11);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_301"));

        Room KRWS12 = new Room("KRW_302", "302", "Standard", 2, "Occupied", h6);
        KRWS12.addRoomFacility(rf1);
        KRWS12.addRoomFacility(rf2);
        KRWS12.addRoomFacility(rf3);
        KRWS12.addRoomFacility(rf4);
        KRWS12.addRoomFacility(rf5);
        KRWS12.addRoomFacility(rf6);
        KRWS12.addRoomFacility(rf7);
        KRWS12.addRoomFacility(rf8);
        KRWS12.addRoomFacility(rf9);
        KRWS12.addRoomFacility(rf10);
        KRWS12.addMinibarItem(m1);
        KRWS12.addMinibarItem(m2);
        KRWS12.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS12);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_302"));

        Room KRWS13 = new Room("KRW_303", "303", "Standard", 2, "Available", h6);
        KRWS13.addRoomFacility(rf1);
        KRWS13.addRoomFacility(rf2);
        KRWS13.addRoomFacility(rf3);
        KRWS13.addRoomFacility(rf4);
        KRWS13.addRoomFacility(rf5);
        KRWS13.addRoomFacility(rf6);
        KRWS13.addRoomFacility(rf7);
        KRWS13.addRoomFacility(rf8);
        KRWS13.addRoomFacility(rf9);
        KRWS13.addRoomFacility(rf10);
        KRWS13.addMinibarItem(m1);
        KRWS13.addMinibarItem(m2);
        KRWS13.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS13);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_303"));

        Room KRWS14 = new Room("KRW_304", "304", "Standard", 2, "Available", h6);
        KRWS14.addRoomFacility(rf1);
        KRWS14.addRoomFacility(rf2);
        KRWS14.addRoomFacility(rf3);
        KRWS14.addRoomFacility(rf4);
        KRWS14.addRoomFacility(rf5);
        KRWS14.addRoomFacility(rf6);
        KRWS14.addRoomFacility(rf7);
        KRWS14.addRoomFacility(rf8);
        KRWS14.addRoomFacility(rf9);
        KRWS14.addRoomFacility(rf10);
        KRWS14.addMinibarItem(m1);
        KRWS14.addMinibarItem(m2);
        KRWS14.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS14);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_304"));

        Room KRWS15 = new Room("KRW_305", "305", "Standard", 2, "Occupied", h6);
        KRWS15.addRoomFacility(rf1);
        KRWS15.addRoomFacility(rf2);
        KRWS15.addRoomFacility(rf3);
        KRWS15.addRoomFacility(rf4);
        KRWS15.addRoomFacility(rf5);
        KRWS15.addRoomFacility(rf6);
        KRWS15.addRoomFacility(rf7);
        KRWS15.addRoomFacility(rf8);
        KRWS15.addRoomFacility(rf9);
        KRWS15.addRoomFacility(rf10);
        KRWS15.addMinibarItem(m1);
        KRWS15.addMinibarItem(m2);
        KRWS15.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS15);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_305"));

        Room KRWS16 = new Room("KRW_306", "306", "Standard", 2, "Available", h6);
        KRWS16.addRoomFacility(rf1);
        KRWS16.addRoomFacility(rf2);
        KRWS16.addRoomFacility(rf3);
        KRWS16.addRoomFacility(rf4);
        KRWS16.addRoomFacility(rf5);
        KRWS16.addRoomFacility(rf6);
        KRWS16.addRoomFacility(rf7);
        KRWS16.addRoomFacility(rf8);
        KRWS16.addRoomFacility(rf9);
        KRWS16.addRoomFacility(rf10);
        KRWS16.addMinibarItem(m1);
        KRWS16.addMinibarItem(m2);
        KRWS16.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS16);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_306"));

        Room KRWS17 = new Room("KRW_307", "307", "Standard", 2, "Available", h6);
        KRWS17.addRoomFacility(rf1);
        KRWS17.addRoomFacility(rf2);
        KRWS17.addRoomFacility(rf3);
        KRWS17.addRoomFacility(rf4);
        KRWS17.addRoomFacility(rf5);
        KRWS17.addRoomFacility(rf6);
        KRWS17.addRoomFacility(rf7);
        KRWS17.addRoomFacility(rf8);
        KRWS17.addRoomFacility(rf9);
        KRWS17.addRoomFacility(rf10);
        KRWS17.addMinibarItem(m1);
        KRWS17.addMinibarItem(m2);
        KRWS17.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS17);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_307"));

        Room KRWS18 = new Room("KRW_308", "308", "Standard", 2, "Available", h6);
        KRWS18.addRoomFacility(rf1);
        KRWS18.addRoomFacility(rf2);
        KRWS18.addRoomFacility(rf3);
        KRWS18.addRoomFacility(rf4);
        KRWS18.addRoomFacility(rf5);
        KRWS18.addRoomFacility(rf6);
        KRWS18.addRoomFacility(rf7);
        KRWS18.addRoomFacility(rf8);
        KRWS18.addRoomFacility(rf9);
        KRWS18.addRoomFacility(rf10);
        KRWS18.addMinibarItem(m1);
        KRWS18.addMinibarItem(m2);
        KRWS18.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS18);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_308"));

        Room KRWS21 = new Room("KRW_401", "401", "Standard", 2, "Occupied", h6);
        KRWS21.addRoomFacility(rf1);
        KRWS21.addRoomFacility(rf2);
        KRWS21.addRoomFacility(rf3);
        KRWS21.addRoomFacility(rf4);
        KRWS21.addRoomFacility(rf5);
        KRWS21.addRoomFacility(rf6);
        KRWS21.addRoomFacility(rf7);
        KRWS21.addRoomFacility(rf8);
        KRWS21.addRoomFacility(rf9);
        KRWS21.addRoomFacility(rf10);
        KRWS21.addMinibarItem(m1);
        KRWS21.addMinibarItem(m2);
        KRWS21.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS21);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_401"));

        Room KRWS22 = new Room("KRW_402", "402", "Standard", 2, "Available", h6);
        KRWS22.addRoomFacility(rf1);
        KRWS22.addRoomFacility(rf2);
        KRWS22.addRoomFacility(rf3);
        KRWS22.addRoomFacility(rf4);
        KRWS22.addRoomFacility(rf5);
        KRWS22.addRoomFacility(rf6);
        KRWS22.addRoomFacility(rf7);
        KRWS22.addRoomFacility(rf8);
        KRWS22.addRoomFacility(rf9);
        KRWS22.addRoomFacility(rf10);
        KRWS22.addMinibarItem(m1);
        KRWS22.addMinibarItem(m2);
        KRWS22.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS22);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_402"));

        Room KRWS23 = new Room("KRW_403", "403", "Standard", 2, "Unavailable", h6);
        KRWS23.addRoomFacility(rf1);
        KRWS23.addRoomFacility(rf2);
        KRWS23.addRoomFacility(rf3);
        KRWS23.addRoomFacility(rf4);
        KRWS23.addRoomFacility(rf5);
        KRWS23.addRoomFacility(rf6);
        KRWS23.addRoomFacility(rf7);
        KRWS23.addRoomFacility(rf8);
        KRWS23.addRoomFacility(rf9);
        KRWS23.addRoomFacility(rf10);
        KRWS23.addMinibarItem(m1);
        KRWS23.addMinibarItem(m2);
        KRWS23.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS23);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_403"));

        Room KRWS24 = new Room("KRW_404", "404", "Standard", 2, "Occupied", h6);
        KRWS24.addRoomFacility(rf1);
        KRWS24.addRoomFacility(rf2);
        KRWS24.addRoomFacility(rf3);
        KRWS24.addRoomFacility(rf4);
        KRWS24.addRoomFacility(rf5);
        KRWS24.addRoomFacility(rf6);
        KRWS24.addRoomFacility(rf7);
        KRWS24.addRoomFacility(rf8);
        KRWS24.addRoomFacility(rf9);
        KRWS24.addRoomFacility(rf10);
        KRWS24.addMinibarItem(m1);
        KRWS24.addMinibarItem(m2);
        KRWS24.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS24);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_404"));

        Room KRWS25 = new Room("KRW_405", "405", "Standard", 2, "Available", h6);
        KRWS25.addRoomFacility(rf1);
        KRWS25.addRoomFacility(rf2);
        KRWS25.addRoomFacility(rf3);
        KRWS25.addRoomFacility(rf4);
        KRWS25.addRoomFacility(rf5);
        KRWS25.addRoomFacility(rf6);
        KRWS25.addRoomFacility(rf7);
        KRWS25.addRoomFacility(rf8);
        KRWS25.addRoomFacility(rf9);
        KRWS25.addRoomFacility(rf10);
        KRWS25.addMinibarItem(m1);
        KRWS25.addMinibarItem(m2);
        KRWS25.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS25);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_405"));

        Room KRWS26 = new Room("KRW_406", "406", "Standard", 2, "Occupied", h6);
        KRWS26.addRoomFacility(rf1);
        KRWS26.addRoomFacility(rf2);
        KRWS26.addRoomFacility(rf3);
        KRWS26.addRoomFacility(rf4);
        KRWS26.addRoomFacility(rf5);
        KRWS26.addRoomFacility(rf6);
        KRWS26.addRoomFacility(rf7);
        KRWS26.addRoomFacility(rf8);
        KRWS26.addRoomFacility(rf9);
        KRWS26.addRoomFacility(rf10);
        KRWS26.addMinibarItem(m1);
        KRWS26.addMinibarItem(m2);
        KRWS26.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS26);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_406"));

        Room KRWS27 = new Room("KRW_407", "407", "Standard", 2, "Available", h6);
        KRWS27.addRoomFacility(rf1);
        KRWS27.addRoomFacility(rf2);
        KRWS27.addRoomFacility(rf3);
        KRWS27.addRoomFacility(rf4);
        KRWS27.addRoomFacility(rf5);
        KRWS27.addRoomFacility(rf6);
        KRWS27.addRoomFacility(rf7);
        KRWS27.addRoomFacility(rf8);
        KRWS27.addRoomFacility(rf9);
        KRWS27.addRoomFacility(rf10);
        KRWS27.addMinibarItem(m1);
        KRWS27.addMinibarItem(m2);
        KRWS27.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS27);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_407"));

        Room KRWS28 = new Room("KRW_408", "408", "Standard", 2, "Occupied", h6);
        KRWS28.addRoomFacility(rf1);
        KRWS28.addRoomFacility(rf2);
        KRWS28.addRoomFacility(rf3);
        KRWS28.addRoomFacility(rf4);
        KRWS28.addRoomFacility(rf5);
        KRWS28.addRoomFacility(rf6);
        KRWS28.addRoomFacility(rf7);
        KRWS28.addRoomFacility(rf8);
        KRWS28.addRoomFacility(rf9);
        KRWS28.addRoomFacility(rf10);
        KRWS28.addMinibarItem(m1);
        KRWS28.addMinibarItem(m2);
        KRWS28.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS28);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_408"));

        Room KRWS31 = new Room("KRW_501", "501", "Standard", 2, "Available", h6);
        KRWS31.addRoomFacility(rf1);
        KRWS31.addRoomFacility(rf2);
        KRWS31.addRoomFacility(rf3);
        KRWS31.addRoomFacility(rf4);
        KRWS31.addRoomFacility(rf5);
        KRWS31.addRoomFacility(rf6);
        KRWS31.addRoomFacility(rf7);
        KRWS31.addRoomFacility(rf8);
        KRWS31.addRoomFacility(rf9);
        KRWS31.addRoomFacility(rf10);
        KRWS31.addMinibarItem(m1);
        KRWS31.addMinibarItem(m2);
        KRWS31.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS31);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_501"));

        Room KRWS32 = new Room("KRW_502", "502", "Standard", 2, "Unavailable", h6);
        KRWS32.addRoomFacility(rf1);
        KRWS32.addRoomFacility(rf2);
        KRWS32.addRoomFacility(rf3);
        KRWS32.addRoomFacility(rf4);
        KRWS32.addRoomFacility(rf5);
        KRWS32.addRoomFacility(rf6);
        KRWS32.addRoomFacility(rf7);
        KRWS32.addRoomFacility(rf8);
        KRWS32.addRoomFacility(rf9);
        KRWS32.addRoomFacility(rf10);
        KRWS32.addMinibarItem(m1);
        KRWS32.addMinibarItem(m2);
        KRWS32.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS32);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_502"));

        Room KRWS33 = new Room("KRW_503", "503", "Standard", 2, "Available", h6);
        KRWS33.addRoomFacility(rf1);
        KRWS33.addRoomFacility(rf2);
        KRWS33.addRoomFacility(rf3);
        KRWS33.addRoomFacility(rf4);
        KRWS33.addRoomFacility(rf5);
        KRWS33.addRoomFacility(rf6);
        KRWS33.addRoomFacility(rf7);
        KRWS33.addRoomFacility(rf8);
        KRWS33.addRoomFacility(rf9);
        KRWS33.addRoomFacility(rf10);
        KRWS33.addMinibarItem(m1);
        KRWS33.addMinibarItem(m2);
        KRWS33.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS33);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_503"));

        Room KRWS34 = new Room("KRW_504", "504", "Standard", 2, "Occupied", h6);
        KRWS34.addRoomFacility(rf1);
        KRWS34.addRoomFacility(rf2);
        KRWS34.addRoomFacility(rf3);
        KRWS34.addRoomFacility(rf4);
        KRWS34.addRoomFacility(rf5);
        KRWS34.addRoomFacility(rf6);
        KRWS34.addRoomFacility(rf7);
        KRWS34.addRoomFacility(rf8);
        KRWS34.addRoomFacility(rf9);
        KRWS34.addRoomFacility(rf10);
        KRWS34.addMinibarItem(m1);
        KRWS34.addMinibarItem(m2);
        KRWS34.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS34);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_504"));

        Room KRWS35 = new Room("KRW_505", "505", "Standard", 2, "Available", h6);
        KRWS35.addRoomFacility(rf1);
        KRWS35.addRoomFacility(rf2);
        KRWS35.addRoomFacility(rf3);
        KRWS35.addRoomFacility(rf4);
        KRWS35.addRoomFacility(rf5);
        KRWS35.addRoomFacility(rf6);
        KRWS35.addRoomFacility(rf7);
        KRWS35.addRoomFacility(rf8);
        KRWS35.addRoomFacility(rf9);
        KRWS35.addRoomFacility(rf10);
        KRWS35.addMinibarItem(m1);
        KRWS35.addMinibarItem(m2);
        KRWS35.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS35);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_505"));

        Room KRWS36 = new Room("KRW_506", "506", "Standard", 2, "Available", h6);
        KRWS36.addRoomFacility(rf1);
        KRWS36.addRoomFacility(rf2);
        KRWS36.addRoomFacility(rf3);
        KRWS36.addRoomFacility(rf4);
        KRWS36.addRoomFacility(rf5);
        KRWS36.addRoomFacility(rf6);
        KRWS36.addRoomFacility(rf7);
        KRWS36.addRoomFacility(rf8);
        KRWS36.addRoomFacility(rf9);
        KRWS36.addRoomFacility(rf10);
        KRWS36.addMinibarItem(m1);
        KRWS36.addMinibarItem(m2);
        KRWS36.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS36);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_506"));

        Room KRWS37 = new Room("KRW_507", "507", "Standard", 2, "Available", h6);
        KRWS37.addRoomFacility(rf1);
        KRWS37.addRoomFacility(rf2);
        KRWS37.addRoomFacility(rf3);
        KRWS37.addRoomFacility(rf4);
        KRWS37.addRoomFacility(rf5);
        KRWS37.addRoomFacility(rf6);
        KRWS37.addRoomFacility(rf7);
        KRWS37.addRoomFacility(rf8);
        KRWS37.addRoomFacility(rf9);
        KRWS37.addRoomFacility(rf10);
        KRWS37.addMinibarItem(m1);
        KRWS37.addMinibarItem(m2);
        KRWS37.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS37);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_507"));

        Room KRWS38 = new Room("KRW_508", "508", "Standard", 2, "Occupied", h6);
        KRWS38.addRoomFacility(rf1);
        KRWS38.addRoomFacility(rf2);
        KRWS38.addRoomFacility(rf3);
        KRWS38.addRoomFacility(rf4);
        KRWS38.addRoomFacility(rf5);
        KRWS38.addRoomFacility(rf6);
        KRWS38.addRoomFacility(rf7);
        KRWS38.addRoomFacility(rf8);
        KRWS38.addRoomFacility(rf9);
        KRWS38.addRoomFacility(rf10);
        KRWS38.addMinibarItem(m1);
        KRWS38.addMinibarItem(m2);
        KRWS38.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS38);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_508"));

        Room KRWS41 = new Room("KRW_601", "601", "Standard", 2, "Available", h6);
        KRWS41.addRoomFacility(rf1);
        KRWS41.addRoomFacility(rf2);
        KRWS41.addRoomFacility(rf3);
        KRWS41.addRoomFacility(rf4);
        KRWS41.addRoomFacility(rf5);
        KRWS41.addRoomFacility(rf6);
        KRWS41.addRoomFacility(rf7);
        KRWS41.addRoomFacility(rf8);
        KRWS41.addRoomFacility(rf9);
        KRWS41.addRoomFacility(rf10);
        KRWS41.addMinibarItem(m1);
        KRWS41.addMinibarItem(m2);
        KRWS41.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS41);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_601"));

        Room KRWS42 = new Room("KRW_602", "602", "Standard", 2, "Available", h6);
        KRWS42.addRoomFacility(rf1);
        KRWS42.addRoomFacility(rf2);
        KRWS42.addRoomFacility(rf3);
        KRWS42.addRoomFacility(rf4);
        KRWS42.addRoomFacility(rf5);
        KRWS42.addRoomFacility(rf6);
        KRWS42.addRoomFacility(rf7);
        KRWS42.addRoomFacility(rf8);
        KRWS42.addRoomFacility(rf9);
        KRWS42.addRoomFacility(rf10);
        KRWS42.addMinibarItem(m1);
        KRWS42.addMinibarItem(m2);
        KRWS42.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS42);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_602"));

        Room KRWS43 = new Room("KRW_603", "603", "Standard", 2, "Unavailable", h6);
        KRWS43.addRoomFacility(rf1);
        KRWS43.addRoomFacility(rf2);
        KRWS43.addRoomFacility(rf3);
        KRWS43.addRoomFacility(rf4);
        KRWS43.addRoomFacility(rf5);
        KRWS43.addRoomFacility(rf6);
        KRWS43.addRoomFacility(rf7);
        KRWS43.addRoomFacility(rf8);
        KRWS43.addRoomFacility(rf9);
        KRWS43.addRoomFacility(rf10);
        KRWS43.addMinibarItem(m1);
        KRWS43.addMinibarItem(m2);
        KRWS43.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS43);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_603"));

        Room KRWS44 = new Room("KRW_604", "604", "Standard", 2, "Occupied", h6);
        KRWS44.addRoomFacility(rf1);
        KRWS44.addRoomFacility(rf2);
        KRWS44.addRoomFacility(rf3);
        KRWS44.addRoomFacility(rf4);
        KRWS44.addRoomFacility(rf5);
        KRWS44.addRoomFacility(rf6);
        KRWS44.addRoomFacility(rf7);
        KRWS44.addRoomFacility(rf8);
        KRWS44.addRoomFacility(rf9);
        KRWS44.addRoomFacility(rf10);
        KRWS44.addMinibarItem(m1);
        KRWS44.addMinibarItem(m2);
        KRWS44.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS44);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_604"));

        Room KRWS45 = new Room("KRW_605", "605", "Standard", 2, "Available", h6);
        KRWS45.addRoomFacility(rf1);
        KRWS45.addRoomFacility(rf2);
        KRWS45.addRoomFacility(rf3);
        KRWS45.addRoomFacility(rf4);
        KRWS45.addRoomFacility(rf5);
        KRWS45.addRoomFacility(rf6);
        KRWS45.addRoomFacility(rf7);
        KRWS45.addRoomFacility(rf8);
        KRWS45.addRoomFacility(rf9);
        KRWS45.addRoomFacility(rf10);
        KRWS45.addMinibarItem(m1);
        KRWS45.addMinibarItem(m2);
        KRWS45.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS45);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_605"));

        Room KRWS46 = new Room("KRW_606", "606", "Standard", 2, "Available", h6);
        KRWS46.addRoomFacility(rf1);
        KRWS46.addRoomFacility(rf2);
        KRWS46.addRoomFacility(rf3);
        KRWS46.addRoomFacility(rf4);
        KRWS46.addRoomFacility(rf5);
        KRWS46.addRoomFacility(rf6);
        KRWS46.addRoomFacility(rf7);
        KRWS46.addRoomFacility(rf8);
        KRWS46.addRoomFacility(rf9);
        KRWS46.addRoomFacility(rf10);
        KRWS46.addMinibarItem(m1);
        KRWS46.addMinibarItem(m2);
        KRWS46.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS46);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_606"));

        Room KRWS47 = new Room("KRW_607", "607", "Standard", 2, "Available", h6);
        KRWS47.addRoomFacility(rf1);
        KRWS47.addRoomFacility(rf2);
        KRWS47.addRoomFacility(rf3);
        KRWS47.addRoomFacility(rf4);
        KRWS47.addRoomFacility(rf5);
        KRWS47.addRoomFacility(rf6);
        KRWS47.addRoomFacility(rf7);
        KRWS47.addRoomFacility(rf8);
        KRWS47.addRoomFacility(rf9);
        KRWS47.addRoomFacility(rf10);
        KRWS47.addMinibarItem(m1);
        KRWS47.addMinibarItem(m2);
        KRWS47.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS47);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_607"));

        Room KRWS48 = new Room("KRW_608", "608", "Standard", 2, "Occupied", h6);
        KRWS48.addRoomFacility(rf1);
        KRWS48.addRoomFacility(rf2);
        KRWS48.addRoomFacility(rf3);
        KRWS48.addRoomFacility(rf4);
        KRWS48.addRoomFacility(rf5);
        KRWS48.addRoomFacility(rf6);
        KRWS48.addRoomFacility(rf7);
        KRWS48.addRoomFacility(rf8);
        KRWS48.addRoomFacility(rf9);
        KRWS48.addRoomFacility(rf10);
        KRWS48.addMinibarItem(m1);
        KRWS48.addMinibarItem(m2);
        KRWS48.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRWS48);
        h6.addRoom(roomSessionLocal.getRoomByName("KRW_608"));

        Room KRW_1001 = new Room("KRW_1001", "1001", "Deluxe", 3, "Available", h6);

        KRW_1001.addRoomFacility(rf1);
        KRW_1001.addRoomFacility(rf2);
        KRW_1001.addRoomFacility(rf3);
        KRW_1001.addRoomFacility(rf4);
        KRW_1001.addRoomFacility(rf5);
        KRW_1001.addRoomFacility(rf6);
        KRW_1001.addRoomFacility(rf7);
        KRW_1001.addRoomFacility(rf8);
        KRW_1001.addRoomFacility(rf9);
        KRW_1001.addRoomFacility(rf10);
        KRW_1001.addRoomFacility(rf11);
        KRW_1001.addRoomFacility(rf12);
        KRW_1001.addRoomFacility(rf13);

        KRW_1001.addMinibarItem(m1);
        KRW_1001.addMinibarItem(m2);
        KRW_1001.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRW_1001);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_1001"));

        Room KRW_1002 = new Room("KRW_1002", "1002", "Deluxe", 3, "Available", h6);

        KRW_1002.addRoomFacility(rf1);
        KRW_1002.addRoomFacility(rf2);
        KRW_1002.addRoomFacility(rf3);
        KRW_1002.addRoomFacility(rf4);
        KRW_1002.addRoomFacility(rf5);
        KRW_1002.addRoomFacility(rf6);
        KRW_1002.addRoomFacility(rf7);
        KRW_1002.addRoomFacility(rf8);
        KRW_1002.addRoomFacility(rf9);
        KRW_1002.addRoomFacility(rf10);
        KRW_1002.addRoomFacility(rf11);
        KRW_1002.addRoomFacility(rf12);
        KRW_1002.addRoomFacility(rf13);

        KRW_1002.addMinibarItem(m1);
        KRW_1002.addMinibarItem(m2);
        KRW_1002.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRW_1002);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_1002"));

        Room KRW_1003 = new Room("KRW_1003", "1003", "Deluxe", 3, "Available", h6);

        KRW_1003.addRoomFacility(rf1);
        KRW_1003.addRoomFacility(rf2);
        KRW_1003.addRoomFacility(rf3);
        KRW_1003.addRoomFacility(rf4);
        KRW_1003.addRoomFacility(rf5);
        KRW_1003.addRoomFacility(rf6);
        KRW_1003.addRoomFacility(rf7);
        KRW_1003.addRoomFacility(rf8);
        KRW_1003.addRoomFacility(rf9);
        KRW_1003.addRoomFacility(rf10);
        KRW_1003.addRoomFacility(rf11);
        KRW_1003.addRoomFacility(rf12);
        KRW_1003.addRoomFacility(rf13);

        KRW_1003.addMinibarItem(m1);
        KRW_1003.addMinibarItem(m2);
        KRW_1003.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRW_1003);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_1003"));

        Room KRW_1004 = new Room("KRW_1004", "1004", "Deluxe", 3, "Occupied", h6);

        KRW_1004.addRoomFacility(rf1);
        KRW_1004.addRoomFacility(rf2);
        KRW_1004.addRoomFacility(rf3);
        KRW_1004.addRoomFacility(rf4);
        KRW_1004.addRoomFacility(rf5);
        KRW_1004.addRoomFacility(rf6);
        KRW_1004.addRoomFacility(rf7);
        KRW_1004.addRoomFacility(rf8);
        KRW_1004.addRoomFacility(rf9);
        KRW_1004.addRoomFacility(rf10);
        KRW_1004.addRoomFacility(rf11);
        KRW_1004.addRoomFacility(rf12);
        KRW_1004.addRoomFacility(rf13);

        KRW_1004.addMinibarItem(m1);
        KRW_1004.addMinibarItem(m2);
        KRW_1004.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRW_1004);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_1004"));

        Room KRW_1005 = new Room("KRW_1005", "1005", "Deluxe", 3, "Available", h6);

        KRW_1005.addRoomFacility(rf1);
        KRW_1005.addRoomFacility(rf2);
        KRW_1005.addRoomFacility(rf3);
        KRW_1005.addRoomFacility(rf4);
        KRW_1005.addRoomFacility(rf5);
        KRW_1005.addRoomFacility(rf6);
        KRW_1005.addRoomFacility(rf7);
        KRW_1005.addRoomFacility(rf8);
        KRW_1005.addRoomFacility(rf9);
        KRW_1005.addRoomFacility(rf10);
        KRW_1005.addRoomFacility(rf11);
        KRW_1005.addRoomFacility(rf12);
        KRW_1005.addRoomFacility(rf13);

        KRW_1005.addMinibarItem(m1);
        KRW_1005.addMinibarItem(m2);
        KRW_1005.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRW_1005);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_1005"));

        Room KRW_1101 = new Room("KRW_1101", "1101", "Deluxe", 3, "Available", h6);

        KRW_1101.addRoomFacility(rf1);
        KRW_1101.addRoomFacility(rf2);
        KRW_1101.addRoomFacility(rf3);
        KRW_1101.addRoomFacility(rf4);
        KRW_1101.addRoomFacility(rf5);
        KRW_1101.addRoomFacility(rf6);
        KRW_1101.addRoomFacility(rf7);
        KRW_1101.addRoomFacility(rf8);
        KRW_1101.addRoomFacility(rf9);
        KRW_1101.addRoomFacility(rf10);
        KRW_1101.addRoomFacility(rf11);
        KRW_1101.addRoomFacility(rf12);
        KRW_1101.addRoomFacility(rf13);

        KRW_1101.addMinibarItem(m1);
        KRW_1101.addMinibarItem(m2);
        KRW_1101.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRW_1101);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_1101"));

        Room KRW_1102 = new Room("KRW_1102", "1102", "Deluxe", 3, "Unavailable", h6);

        KRW_1102.addRoomFacility(rf1);
        KRW_1102.addRoomFacility(rf2);
        KRW_1102.addRoomFacility(rf3);
        KRW_1102.addRoomFacility(rf4);
        KRW_1102.addRoomFacility(rf5);
        KRW_1102.addRoomFacility(rf6);
        KRW_1102.addRoomFacility(rf7);
        KRW_1102.addRoomFacility(rf8);
        KRW_1102.addRoomFacility(rf9);
        KRW_1102.addRoomFacility(rf10);
        KRW_1102.addRoomFacility(rf11);
        KRW_1102.addRoomFacility(rf12);
        KRW_1102.addRoomFacility(rf13);

        KRW_1102.addMinibarItem(m1);
        KRW_1102.addMinibarItem(m2);
        KRW_1102.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRW_1102);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_1102"));

        Room KRW_1103 = new Room("KRW_1103", "1103", "Deluxe", 3, "Available", h6);

        KRW_1103.addRoomFacility(rf1);
        KRW_1103.addRoomFacility(rf2);
        KRW_1103.addRoomFacility(rf3);
        KRW_1103.addRoomFacility(rf4);
        KRW_1103.addRoomFacility(rf5);
        KRW_1103.addRoomFacility(rf6);
        KRW_1103.addRoomFacility(rf7);
        KRW_1103.addRoomFacility(rf8);
        KRW_1103.addRoomFacility(rf9);
        KRW_1103.addRoomFacility(rf10);
        KRW_1103.addRoomFacility(rf11);
        KRW_1103.addRoomFacility(rf12);
        KRW_1103.addRoomFacility(rf13);

        KRW_1103.addMinibarItem(m1);
        KRW_1103.addMinibarItem(m2);
        KRW_1103.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRW_1103);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_1103"));

        Room KRW_1104 = new Room("KRW_1104", "1104", "Deluxe", 3, "Available", h6);

        KRW_1104.addRoomFacility(rf1);
        KRW_1104.addRoomFacility(rf2);
        KRW_1104.addRoomFacility(rf3);
        KRW_1104.addRoomFacility(rf4);
        KRW_1104.addRoomFacility(rf5);
        KRW_1104.addRoomFacility(rf6);
        KRW_1104.addRoomFacility(rf7);
        KRW_1104.addRoomFacility(rf8);
        KRW_1104.addRoomFacility(rf9);
        KRW_1104.addRoomFacility(rf10);
        KRW_1104.addRoomFacility(rf11);
        KRW_1104.addRoomFacility(rf12);
        KRW_1104.addRoomFacility(rf13);

        KRW_1104.addMinibarItem(m1);
        KRW_1104.addMinibarItem(m2);
        KRW_1104.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRW_1104);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_1104"));

        Room KRW_1105 = new Room("KRW_1105", "1105", "Deluxe", 3, "Occupied", h6);

        KRW_1105.addRoomFacility(rf1);
        KRW_1105.addRoomFacility(rf2);
        KRW_1105.addRoomFacility(rf3);
        KRW_1105.addRoomFacility(rf4);
        KRW_1105.addRoomFacility(rf5);
        KRW_1105.addRoomFacility(rf6);
        KRW_1105.addRoomFacility(rf7);
        KRW_1105.addRoomFacility(rf8);
        KRW_1105.addRoomFacility(rf9);
        KRW_1105.addRoomFacility(rf10);
        KRW_1105.addRoomFacility(rf11);
        KRW_1105.addRoomFacility(rf12);
        KRW_1105.addRoomFacility(rf13);

        KRW_1105.addMinibarItem(m1);
        KRW_1105.addMinibarItem(m2);
        KRW_1105.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRW_1105);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_1105"));

        Room KRW_701 = new Room("KRW_701", "701", "Premium", 4, "Available", h6);

        KRW_701.addRoomFacility(rf1);
        KRW_701.addRoomFacility(rf2);
        KRW_701.addRoomFacility(rf3);
        KRW_701.addRoomFacility(rf4);
        KRW_701.addRoomFacility(rf5);
        KRW_701.addRoomFacility(rf6);
        KRW_701.addRoomFacility(rf7);
        KRW_701.addRoomFacility(rf8);
        KRW_701.addRoomFacility(rf9);
        KRW_701.addRoomFacility(rf10);
        KRW_701.addRoomFacility(rf11);
        KRW_701.addRoomFacility(rf12);
        KRW_701.addRoomFacility(rf13);
        KRW_701.addRoomFacility(rf14);
        KRW_701.addRoomFacility(rf15);

        KRW_701.addMinibarItem(m1);
        KRW_701.addMinibarItem(m2);
        KRW_701.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRW_701);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_701"));

        Room KRW_702 = new Room("KRW_702", "702", "Premium", 4, "Occupied", h6);

        KRW_702.addRoomFacility(rf1);
        KRW_702.addRoomFacility(rf2);
        KRW_702.addRoomFacility(rf3);
        KRW_702.addRoomFacility(rf4);
        KRW_702.addRoomFacility(rf5);
        KRW_702.addRoomFacility(rf6);
        KRW_702.addRoomFacility(rf7);
        KRW_702.addRoomFacility(rf8);
        KRW_702.addRoomFacility(rf9);
        KRW_702.addRoomFacility(rf10);
        KRW_702.addRoomFacility(rf11);
        KRW_702.addRoomFacility(rf12);
        KRW_702.addRoomFacility(rf13);
        KRW_702.addRoomFacility(rf14);
        KRW_702.addRoomFacility(rf15);

        KRW_702.addMinibarItem(m1);
        KRW_702.addMinibarItem(m2);
        KRW_702.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRW_702);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_702"));

        Room KRW_703 = new Room("KRW_703", "703", "Premium", 4, "Unavailable", h6);

        KRW_703.addRoomFacility(rf1);
        KRW_703.addRoomFacility(rf2);
        KRW_703.addRoomFacility(rf3);
        KRW_703.addRoomFacility(rf4);
        KRW_703.addRoomFacility(rf5);
        KRW_703.addRoomFacility(rf6);
        KRW_703.addRoomFacility(rf7);
        KRW_703.addRoomFacility(rf8);
        KRW_703.addRoomFacility(rf9);
        KRW_703.addRoomFacility(rf10);
        KRW_703.addRoomFacility(rf11);
        KRW_703.addRoomFacility(rf12);
        KRW_703.addRoomFacility(rf13);
        KRW_703.addRoomFacility(rf14);
        KRW_703.addRoomFacility(rf15);

        KRW_703.addMinibarItem(m1);
        KRW_703.addMinibarItem(m2);
        KRW_703.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRW_703);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_703"));

        Room KRW_704 = new Room("KRW_704", "704", "Premium", 4, "Available", h6);

        KRW_704.addRoomFacility(rf1);
        KRW_704.addRoomFacility(rf2);
        KRW_704.addRoomFacility(rf3);
        KRW_704.addRoomFacility(rf4);
        KRW_704.addRoomFacility(rf5);
        KRW_704.addRoomFacility(rf6);
        KRW_704.addRoomFacility(rf7);
        KRW_704.addRoomFacility(rf8);
        KRW_704.addRoomFacility(rf9);
        KRW_704.addRoomFacility(rf10);
        KRW_704.addRoomFacility(rf11);
        KRW_704.addRoomFacility(rf12);
        KRW_704.addRoomFacility(rf13);
        KRW_704.addRoomFacility(rf14);
        KRW_704.addRoomFacility(rf15);

        KRW_704.addMinibarItem(m1);
        KRW_704.addMinibarItem(m2);
        KRW_704.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRW_704);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_704"));

        Room KRW_705 = new Room("KRW_705", "705", "Premium", 4, "Available", h6);

        KRW_705.addRoomFacility(rf1);
        KRW_705.addRoomFacility(rf2);
        KRW_705.addRoomFacility(rf3);
        KRW_705.addRoomFacility(rf4);
        KRW_705.addRoomFacility(rf5);
        KRW_705.addRoomFacility(rf6);
        KRW_705.addRoomFacility(rf7);
        KRW_705.addRoomFacility(rf8);
        KRW_705.addRoomFacility(rf9);
        KRW_705.addRoomFacility(rf10);
        KRW_705.addRoomFacility(rf11);
        KRW_705.addRoomFacility(rf12);
        KRW_705.addRoomFacility(rf13);
        KRW_705.addRoomFacility(rf14);
        KRW_705.addRoomFacility(rf15);

        KRW_705.addMinibarItem(m1);
        KRW_705.addMinibarItem(m2);
        KRW_705.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRW_705);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_705"));

        Room KRW_706 = new Room("KRW_706", "706", "Premium", 4, "Available", h6);

        KRW_706.addRoomFacility(rf1);
        KRW_706.addRoomFacility(rf2);
        KRW_706.addRoomFacility(rf3);
        KRW_706.addRoomFacility(rf4);
        KRW_706.addRoomFacility(rf5);
        KRW_706.addRoomFacility(rf6);
        KRW_706.addRoomFacility(rf7);
        KRW_706.addRoomFacility(rf8);
        KRW_706.addRoomFacility(rf9);
        KRW_706.addRoomFacility(rf10);
        KRW_706.addRoomFacility(rf11);
        KRW_706.addRoomFacility(rf12);
        KRW_706.addRoomFacility(rf13);
        KRW_706.addRoomFacility(rf14);
        KRW_706.addRoomFacility(rf15);

        KRW_706.addMinibarItem(m1);
        KRW_706.addMinibarItem(m2);
        KRW_706.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRW_706);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_706"));

        Room KRW_707 = new Room("KRW_707", "707", "Premium", 4, "Occupied", h6);

        KRW_707.addRoomFacility(rf1);
        KRW_707.addRoomFacility(rf2);
        KRW_707.addRoomFacility(rf3);
        KRW_707.addRoomFacility(rf4);
        KRW_707.addRoomFacility(rf5);
        KRW_707.addRoomFacility(rf6);
        KRW_707.addRoomFacility(rf7);
        KRW_707.addRoomFacility(rf8);
        KRW_707.addRoomFacility(rf9);
        KRW_707.addRoomFacility(rf10);
        KRW_707.addRoomFacility(rf11);
        KRW_707.addRoomFacility(rf12);
        KRW_707.addRoomFacility(rf13);
        KRW_707.addRoomFacility(rf14);
        KRW_707.addRoomFacility(rf15);

        KRW_707.addMinibarItem(m1);
        KRW_707.addMinibarItem(m2);
        KRW_707.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRW_707);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_707"));

        Room KRW_801 = new Room("KRW_801", "801", "Premium", 4, "Unavailable", h6);

        KRW_801.addRoomFacility(rf1);
        KRW_801.addRoomFacility(rf2);
        KRW_801.addRoomFacility(rf3);
        KRW_801.addRoomFacility(rf4);
        KRW_801.addRoomFacility(rf5);
        KRW_801.addRoomFacility(rf6);
        KRW_801.addRoomFacility(rf7);
        KRW_801.addRoomFacility(rf8);
        KRW_801.addRoomFacility(rf9);
        KRW_801.addRoomFacility(rf10);
        KRW_801.addRoomFacility(rf11);
        KRW_801.addRoomFacility(rf12);
        KRW_801.addRoomFacility(rf13);
        KRW_801.addRoomFacility(rf14);
        KRW_801.addRoomFacility(rf15);

        KRW_801.addMinibarItem(m1);
        KRW_801.addMinibarItem(m2);
        KRW_801.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRW_801);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_801"));

        Room KRW_802 = new Room("KRW_802", "802", "Premium", 4, "Available", h6);

        KRW_802.addRoomFacility(rf1);
        KRW_802.addRoomFacility(rf2);
        KRW_802.addRoomFacility(rf3);
        KRW_802.addRoomFacility(rf4);
        KRW_802.addRoomFacility(rf5);
        KRW_802.addRoomFacility(rf6);
        KRW_802.addRoomFacility(rf7);
        KRW_802.addRoomFacility(rf8);
        KRW_802.addRoomFacility(rf9);
        KRW_802.addRoomFacility(rf10);
        KRW_802.addRoomFacility(rf11);
        KRW_802.addRoomFacility(rf12);
        KRW_802.addRoomFacility(rf13);
        KRW_802.addRoomFacility(rf14);
        KRW_802.addRoomFacility(rf15);

        KRW_802.addMinibarItem(m1);
        KRW_802.addMinibarItem(m2);
        KRW_802.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRW_802);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_802"));

        Room KRW_803 = new Room("KRW_803", "803", "Premium", 4, "Available", h6);

        KRW_803.addRoomFacility(rf1);
        KRW_803.addRoomFacility(rf2);
        KRW_803.addRoomFacility(rf3);
        KRW_803.addRoomFacility(rf4);
        KRW_803.addRoomFacility(rf5);
        KRW_803.addRoomFacility(rf6);
        KRW_803.addRoomFacility(rf7);
        KRW_803.addRoomFacility(rf8);
        KRW_803.addRoomFacility(rf9);
        KRW_803.addRoomFacility(rf10);
        KRW_803.addRoomFacility(rf11);
        KRW_803.addRoomFacility(rf12);
        KRW_803.addRoomFacility(rf13);
        KRW_803.addRoomFacility(rf14);
        KRW_803.addRoomFacility(rf15);

        KRW_803.addMinibarItem(m1);
        KRW_803.addMinibarItem(m2);
        KRW_803.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRW_803);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_803"));

        Room KRW_804 = new Room("KRW_804", "804", "Premium", 4, "Occupied", h6);

        KRW_804.addRoomFacility(rf1);
        KRW_804.addRoomFacility(rf2);
        KRW_804.addRoomFacility(rf3);
        KRW_804.addRoomFacility(rf4);
        KRW_804.addRoomFacility(rf5);
        KRW_804.addRoomFacility(rf6);
        KRW_804.addRoomFacility(rf7);
        KRW_804.addRoomFacility(rf8);
        KRW_804.addRoomFacility(rf9);
        KRW_804.addRoomFacility(rf10);
        KRW_804.addRoomFacility(rf11);
        KRW_804.addRoomFacility(rf12);
        KRW_804.addRoomFacility(rf13);
        KRW_804.addRoomFacility(rf14);
        KRW_804.addRoomFacility(rf15);

        KRW_804.addMinibarItem(m1);
        KRW_804.addMinibarItem(m2);
        KRW_804.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRW_804);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_804"));

        Room KRW_805 = new Room("KRW_805", "805", "Premium", 4, "Available", h6);

        KRW_805.addRoomFacility(rf1);
        KRW_805.addRoomFacility(rf2);
        KRW_805.addRoomFacility(rf3);
        KRW_805.addRoomFacility(rf4);
        KRW_805.addRoomFacility(rf5);
        KRW_805.addRoomFacility(rf6);
        KRW_805.addRoomFacility(rf7);
        KRW_805.addRoomFacility(rf8);
        KRW_805.addRoomFacility(rf9);
        KRW_805.addRoomFacility(rf10);
        KRW_805.addRoomFacility(rf11);
        KRW_805.addRoomFacility(rf12);
        KRW_805.addRoomFacility(rf13);
        KRW_805.addRoomFacility(rf14);
        KRW_805.addRoomFacility(rf15);

        KRW_805.addMinibarItem(m1);
        KRW_805.addMinibarItem(m2);
        KRW_805.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRW_805);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_805"));

        Room KRW_806 = new Room("KRW_806", "806", "Premium", 4, "Unavailable", h6);

        KRW_806.addRoomFacility(rf1);
        KRW_806.addRoomFacility(rf2);
        KRW_806.addRoomFacility(rf3);
        KRW_806.addRoomFacility(rf4);
        KRW_806.addRoomFacility(rf5);
        KRW_806.addRoomFacility(rf6);
        KRW_806.addRoomFacility(rf7);
        KRW_806.addRoomFacility(rf8);
        KRW_806.addRoomFacility(rf9);
        KRW_806.addRoomFacility(rf10);
        KRW_806.addRoomFacility(rf11);
        KRW_806.addRoomFacility(rf12);
        KRW_806.addRoomFacility(rf13);
        KRW_806.addRoomFacility(rf14);
        KRW_806.addRoomFacility(rf15);

        KRW_806.addMinibarItem(m1);
        KRW_806.addMinibarItem(m2);
        KRW_806.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRW_806);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_806"));

        Room KRW_807 = new Room("KRW_807", "807", "Premium", 4, "Available", h6);

        KRW_807.addRoomFacility(rf1);
        KRW_807.addRoomFacility(rf2);
        KRW_807.addRoomFacility(rf3);
        KRW_807.addRoomFacility(rf4);
        KRW_807.addRoomFacility(rf5);
        KRW_807.addRoomFacility(rf6);
        KRW_807.addRoomFacility(rf7);
        KRW_807.addRoomFacility(rf8);
        KRW_807.addRoomFacility(rf9);
        KRW_807.addRoomFacility(rf10);
        KRW_807.addRoomFacility(rf11);
        KRW_807.addRoomFacility(rf12);
        KRW_807.addRoomFacility(rf13);
        KRW_807.addRoomFacility(rf14);
        KRW_807.addRoomFacility(rf15);

        KRW_807.addMinibarItem(m1);
        KRW_807.addMinibarItem(m2);
        KRW_807.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRW_807);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_807"));

        Room KRW_901 = new Room("KRW_901", "901", "Premium", 4, "Available", h6);

        KRW_901.addRoomFacility(rf1);
        KRW_901.addRoomFacility(rf2);
        KRW_901.addRoomFacility(rf3);
        KRW_901.addRoomFacility(rf4);
        KRW_901.addRoomFacility(rf5);
        KRW_901.addRoomFacility(rf6);
        KRW_901.addRoomFacility(rf7);
        KRW_901.addRoomFacility(rf8);
        KRW_901.addRoomFacility(rf9);
        KRW_901.addRoomFacility(rf10);
        KRW_901.addRoomFacility(rf11);
        KRW_901.addRoomFacility(rf12);
        KRW_901.addRoomFacility(rf13);
        KRW_901.addRoomFacility(rf14);
        KRW_901.addRoomFacility(rf15);

        KRW_901.addMinibarItem(m1);
        KRW_901.addMinibarItem(m2);
        KRW_901.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRW_901);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_901"));

        Room KRW_902 = new Room("KRW_902", "902", "Premium", 4, "Available", h6);

        KRW_902.addRoomFacility(rf1);
        KRW_902.addRoomFacility(rf2);
        KRW_902.addRoomFacility(rf3);
        KRW_902.addRoomFacility(rf4);
        KRW_902.addRoomFacility(rf5);
        KRW_902.addRoomFacility(rf6);
        KRW_902.addRoomFacility(rf7);
        KRW_902.addRoomFacility(rf8);
        KRW_902.addRoomFacility(rf9);
        KRW_902.addRoomFacility(rf10);
        KRW_902.addRoomFacility(rf11);
        KRW_902.addRoomFacility(rf12);
        KRW_902.addRoomFacility(rf13);
        KRW_902.addRoomFacility(rf14);
        KRW_902.addRoomFacility(rf15);

        KRW_902.addMinibarItem(m1);
        KRW_902.addMinibarItem(m2);
        KRW_902.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRW_902);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_902"));

        Room KRW_903 = new Room("KRW_903", "903", "Premium", 4, "Occupied", h6);

        KRW_903.addRoomFacility(rf1);
        KRW_903.addRoomFacility(rf2);
        KRW_903.addRoomFacility(rf3);
        KRW_903.addRoomFacility(rf4);
        KRW_903.addRoomFacility(rf5);
        KRW_903.addRoomFacility(rf6);
        KRW_903.addRoomFacility(rf7);
        KRW_903.addRoomFacility(rf8);
        KRW_903.addRoomFacility(rf9);
        KRW_903.addRoomFacility(rf10);
        KRW_903.addRoomFacility(rf11);
        KRW_903.addRoomFacility(rf12);
        KRW_903.addRoomFacility(rf13);
        KRW_903.addRoomFacility(rf14);
        KRW_903.addRoomFacility(rf15);

        KRW_903.addMinibarItem(m1);
        KRW_903.addMinibarItem(m2);
        KRW_903.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRW_903);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_903"));

        Room KRW_904 = new Room("KRW_904", "904", "Premium", 4, "Available", h6);

        KRW_904.addRoomFacility(rf1);
        KRW_904.addRoomFacility(rf2);
        KRW_904.addRoomFacility(rf3);
        KRW_904.addRoomFacility(rf4);
        KRW_904.addRoomFacility(rf5);
        KRW_904.addRoomFacility(rf6);
        KRW_904.addRoomFacility(rf7);
        KRW_904.addRoomFacility(rf8);
        KRW_904.addRoomFacility(rf9);
        KRW_904.addRoomFacility(rf10);
        KRW_904.addRoomFacility(rf11);
        KRW_904.addRoomFacility(rf12);
        KRW_904.addRoomFacility(rf13);
        KRW_904.addRoomFacility(rf14);
        KRW_904.addRoomFacility(rf15);

        KRW_904.addMinibarItem(m1);
        KRW_904.addMinibarItem(m2);
        KRW_904.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRW_904);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_904"));

        Room KRW_905 = new Room("KRW_905", "905", "Premium", 4, "Unavailable", h6);

        KRW_905.addRoomFacility(rf1);
        KRW_905.addRoomFacility(rf2);
        KRW_905.addRoomFacility(rf3);
        KRW_905.addRoomFacility(rf4);
        KRW_905.addRoomFacility(rf5);
        KRW_905.addRoomFacility(rf6);
        KRW_905.addRoomFacility(rf7);
        KRW_905.addRoomFacility(rf8);
        KRW_905.addRoomFacility(rf9);
        KRW_905.addRoomFacility(rf10);
        KRW_905.addRoomFacility(rf11);
        KRW_905.addRoomFacility(rf12);
        KRW_905.addRoomFacility(rf13);
        KRW_905.addRoomFacility(rf14);
        KRW_905.addRoomFacility(rf15);

        KRW_905.addMinibarItem(m1);
        KRW_905.addMinibarItem(m2);
        KRW_905.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRW_905);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_905"));

        Room KRW_906 = new Room("KRW_906", "906", "Premium", 4, "Occupied", h6);

        KRW_906.addRoomFacility(rf1);
        KRW_906.addRoomFacility(rf2);
        KRW_906.addRoomFacility(rf3);
        KRW_906.addRoomFacility(rf4);
        KRW_906.addRoomFacility(rf5);
        KRW_906.addRoomFacility(rf6);
        KRW_906.addRoomFacility(rf7);
        KRW_906.addRoomFacility(rf8);
        KRW_906.addRoomFacility(rf9);
        KRW_906.addRoomFacility(rf10);
        KRW_906.addRoomFacility(rf11);
        KRW_906.addRoomFacility(rf12);
        KRW_906.addRoomFacility(rf13);
        KRW_906.addRoomFacility(rf14);
        KRW_906.addRoomFacility(rf15);

        KRW_906.addMinibarItem(m1);
        KRW_906.addMinibarItem(m2);
        KRW_906.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRW_906);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_906"));

        Room KRW_907 = new Room("KRW_907", "907", "Premium", 4, "Available", h6);

        KRW_907.addRoomFacility(rf1);
        KRW_907.addRoomFacility(rf2);
        KRW_907.addRoomFacility(rf3);
        KRW_907.addRoomFacility(rf4);
        KRW_907.addRoomFacility(rf5);
        KRW_907.addRoomFacility(rf6);
        KRW_907.addRoomFacility(rf7);
        KRW_907.addRoomFacility(rf8);
        KRW_907.addRoomFacility(rf9);
        KRW_907.addRoomFacility(rf10);
        KRW_907.addRoomFacility(rf11);
        KRW_907.addRoomFacility(rf12);
        KRW_907.addRoomFacility(rf13);
        KRW_907.addRoomFacility(rf14);
        KRW_907.addRoomFacility(rf15);

        KRW_907.addMinibarItem(m1);
        KRW_907.addMinibarItem(m2);
        KRW_907.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRW_907);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_907"));

        Room KRW_1201 = new Room("KRW_1201", "1201", "Suite", 4, "Available", h6);

        KRW_1201.addRoomFacility(rf1);
        KRW_1201.addRoomFacility(rf2);
        KRW_1201.addRoomFacility(rf3);
        KRW_1201.addRoomFacility(rf4);
        KRW_1201.addRoomFacility(rf5);
        KRW_1201.addRoomFacility(rf6);
        KRW_1201.addRoomFacility(rf7);
        KRW_1201.addRoomFacility(rf8);
        KRW_1201.addRoomFacility(rf9);
        KRW_1201.addRoomFacility(rf10);
        KRW_1201.addRoomFacility(rf11);
        KRW_1201.addRoomFacility(rf12);
        KRW_1201.addRoomFacility(rf13);
        KRW_1201.addRoomFacility(rf14);
        KRW_1201.addRoomFacility(rf15);
        KRW_1201.addRoomFacility(rf16);
        KRW_1201.addRoomFacility(rf17);
        KRW_1201.addRoomFacility(rf18);
        KRW_1201.addRoomFacility(rf19);

        KRW_1201.addMinibarItem(m1);
        KRW_1201.addMinibarItem(m2);
        KRW_1201.addMinibarItem(m3);
        KRW_1201.addMinibarItem(m4);
        KRW_1201.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRW_1201);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_1201"));

        Room KRW_1202 = new Room("KRW_1202", "1202", "Suite", 4, "Occupied", h6);

        KRW_1202.addRoomFacility(rf1);
        KRW_1202.addRoomFacility(rf2);
        KRW_1202.addRoomFacility(rf3);
        KRW_1202.addRoomFacility(rf4);
        KRW_1202.addRoomFacility(rf5);
        KRW_1202.addRoomFacility(rf6);
        KRW_1202.addRoomFacility(rf7);
        KRW_1202.addRoomFacility(rf8);
        KRW_1202.addRoomFacility(rf9);
        KRW_1202.addRoomFacility(rf10);
        KRW_1202.addRoomFacility(rf11);
        KRW_1202.addRoomFacility(rf12);
        KRW_1202.addRoomFacility(rf13);
        KRW_1202.addRoomFacility(rf14);
        KRW_1202.addRoomFacility(rf15);
        KRW_1202.addRoomFacility(rf16);
        KRW_1202.addRoomFacility(rf17);
        KRW_1202.addRoomFacility(rf18);
        KRW_1202.addRoomFacility(rf19);

        KRW_1202.addMinibarItem(m1);
        KRW_1202.addMinibarItem(m2);
        KRW_1202.addMinibarItem(m3);
        KRW_1202.addMinibarItem(m4);
        KRW_1202.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRW_1202);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_1202"));

        Room KRW_1203 = new Room("KRW_1203", "1203", "Suite", 4, "Available", h6);

        KRW_1203.addRoomFacility(rf1);
        KRW_1203.addRoomFacility(rf2);
        KRW_1203.addRoomFacility(rf3);
        KRW_1203.addRoomFacility(rf4);
        KRW_1203.addRoomFacility(rf5);
        KRW_1203.addRoomFacility(rf6);
        KRW_1203.addRoomFacility(rf7);
        KRW_1203.addRoomFacility(rf8);
        KRW_1203.addRoomFacility(rf9);
        KRW_1203.addRoomFacility(rf10);
        KRW_1203.addRoomFacility(rf11);
        KRW_1203.addRoomFacility(rf12);
        KRW_1203.addRoomFacility(rf13);
        KRW_1203.addRoomFacility(rf14);
        KRW_1203.addRoomFacility(rf15);
        KRW_1203.addRoomFacility(rf16);
        KRW_1203.addRoomFacility(rf17);
        KRW_1203.addRoomFacility(rf18);
        KRW_1203.addRoomFacility(rf19);

        KRW_1203.addMinibarItem(m1);
        KRW_1203.addMinibarItem(m2);
        KRW_1203.addMinibarItem(m3);
        KRW_1203.addMinibarItem(m4);
        KRW_1203.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRW_1203);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_1203"));

        Room KRW_1204 = new Room("KRW_1204", "1204", "Suite", 4, "Available", h6);

        KRW_1204.addRoomFacility(rf1);
        KRW_1204.addRoomFacility(rf2);
        KRW_1204.addRoomFacility(rf3);
        KRW_1204.addRoomFacility(rf4);
        KRW_1204.addRoomFacility(rf5);
        KRW_1204.addRoomFacility(rf6);
        KRW_1204.addRoomFacility(rf7);
        KRW_1204.addRoomFacility(rf8);
        KRW_1204.addRoomFacility(rf9);
        KRW_1204.addRoomFacility(rf10);
        KRW_1204.addRoomFacility(rf11);
        KRW_1204.addRoomFacility(rf12);
        KRW_1204.addRoomFacility(rf13);
        KRW_1204.addRoomFacility(rf14);
        KRW_1204.addRoomFacility(rf15);
        KRW_1204.addRoomFacility(rf16);
        KRW_1204.addRoomFacility(rf17);
        KRW_1204.addRoomFacility(rf18);
        KRW_1204.addRoomFacility(rf19);

        KRW_1204.addMinibarItem(m1);
        KRW_1204.addMinibarItem(m2);
        KRW_1204.addMinibarItem(m3);
        KRW_1204.addMinibarItem(m4);
        KRW_1204.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRW_1204);

        h6.addRoom(roomSessionLocal.getRoomByName("KRW_1204"));

        em.flush();
    }

    public void initializeKRNERoom() throws NoResultException {
        Hotel h7 = hotelSessionLocal.getHotelByName("Kent Ridge North East");
        MinibarItem m1 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Potato Chips");
        MinibarItem m2 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Chocolate");
        MinibarItem m3 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Mineral Water");
        MinibarItem m4 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Coke");
        MinibarItem m5 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Wine");
        RoomFacility rf1 = roomFacilitySessionLocal.getRoomFacilityByName("Wifi");
        RoomFacility rf2 = roomFacilitySessionLocal.getRoomFacilityByName("Minibar");
        RoomFacility rf3 = roomFacilitySessionLocal.getRoomFacilityByName("Aircon");
        RoomFacility rf4 = roomFacilitySessionLocal.getRoomFacilityByName("Cable TV");
        RoomFacility rf5 = roomFacilitySessionLocal.getRoomFacilityByName("Telephone");
        RoomFacility rf6 = roomFacilitySessionLocal.getRoomFacilityByName("Water Heater");
        RoomFacility rf7 = roomFacilitySessionLocal.getRoomFacilityByName("Bathroom with Shower Head");
        RoomFacility rf8 = roomFacilitySessionLocal.getRoomFacilityByName("HairDryer");
        RoomFacility rf9 = roomFacilitySessionLocal.getRoomFacilityByName("Safe Deposit Box");
        RoomFacility rf10 = roomFacilitySessionLocal.getRoomFacilityByName("Water Kettle with complimentary teas/coffee");
        RoomFacility rf11 = roomFacilitySessionLocal.getRoomFacilityByName("Room with Windows");
        RoomFacility rf12 = roomFacilitySessionLocal.getRoomFacilityByName("Bathtub");
        RoomFacility rf13 = roomFacilitySessionLocal.getRoomFacilityByName("Mini Fridge");
        RoomFacility rf14 = roomFacilitySessionLocal.getRoomFacilityByName("Coffee Machine");
        RoomFacility rf15 = roomFacilitySessionLocal.getRoomFacilityByName("Balcony");
        RoomFacility rf16 = roomFacilitySessionLocal.getRoomFacilityByName("Microwave");
        RoomFacility rf17 = roomFacilitySessionLocal.getRoomFacilityByName("Gaming Console");
        RoomFacility rf18 = roomFacilitySessionLocal.getRoomFacilityByName("Entertainment System");
        RoomFacility rf19 = roomFacilitySessionLocal.getRoomFacilityByName("Blackout Curtains");
        RoomFacility rf20 = roomFacilitySessionLocal.getRoomFacilityByName("High Ceiling");
        RoomFacility rf21 = roomFacilitySessionLocal.getRoomFacilityByName("Jaccuzi");
        RoomFacility rf22 = roomFacilitySessionLocal.getRoomFacilityByName("Kitchen");

        FunctionRoom KRNEFR1 = new FunctionRoom("KRNEFR1", 20, "Available", 20000.00, h7);
        FunctionRoom KRNEFR2 = new FunctionRoom("KRNEFR2", 100, "Available", 100000.00, h7);
        FunctionRoom KRNEFR3 = new FunctionRoom("KRNEFR3", 50, "Available", 50000.00, h7);
        FunctionRoom KRNEFR4 = new FunctionRoom("KRNEFR4", 70, "Available", 70000.00, h7);
        FunctionRoom KRNEFR5 = new FunctionRoom("KRNEFR5", 80, "Available", 80000.00, h7);

        functionRoomSessionLocal.createFunctionRoom(KRNEFR1);
        functionRoomSessionLocal.createFunctionRoom(KRNEFR2);
        functionRoomSessionLocal.createFunctionRoom(KRNEFR3);
        functionRoomSessionLocal.createFunctionRoom(KRNEFR4);
        functionRoomSessionLocal.createFunctionRoom(KRNEFR5);

        h7.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRNEFR1"));
        h7.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRNEFR2"));
        h7.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRNEFR3"));
        h7.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRNEFR4"));
        h7.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRNEFR5"));

        Room KRNES1 = new Room("KRNE_201", "201", "Standard", 2, "Available", h7);
        KRNES1.addRoomFacility(rf1);
        KRNES1.addRoomFacility(rf2);
        KRNES1.addRoomFacility(rf3);
        KRNES1.addRoomFacility(rf4);
        KRNES1.addRoomFacility(rf5);
        KRNES1.addRoomFacility(rf6);
        KRNES1.addRoomFacility(rf7);
        KRNES1.addRoomFacility(rf8);
        KRNES1.addRoomFacility(rf9);
        KRNES1.addRoomFacility(rf10);
        KRNES1.addMinibarItem(m1);
        KRNES1.addMinibarItem(m2);
        KRNES1.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNES1);
        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_201"));

        Room KRNES2 = new Room("KRNE_202", "202", "Standard", 2, "Occupied", h7);
        KRNES2.addRoomFacility(rf1);
        KRNES2.addRoomFacility(rf2);
        KRNES2.addRoomFacility(rf3);
        KRNES2.addRoomFacility(rf4);
        KRNES2.addRoomFacility(rf5);
        KRNES2.addRoomFacility(rf6);
        KRNES2.addRoomFacility(rf7);
        KRNES2.addRoomFacility(rf8);
        KRNES2.addRoomFacility(rf9);
        KRNES2.addRoomFacility(rf10);
        KRNES2.addMinibarItem(m1);
        KRNES2.addMinibarItem(m2);
        KRNES2.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNES2);
        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_202"));

        Room KRNES3 = new Room("KRNE_203", "203", "Standard", 2, "Unavailable", h7);
        KRNES3.addRoomFacility(rf1);
        KRNES3.addRoomFacility(rf2);
        KRNES3.addRoomFacility(rf3);
        KRNES3.addRoomFacility(rf4);
        KRNES3.addRoomFacility(rf5);
        KRNES3.addRoomFacility(rf6);
        KRNES3.addRoomFacility(rf7);
        KRNES3.addRoomFacility(rf8);
        KRNES3.addRoomFacility(rf9);
        KRNES3.addRoomFacility(rf10);
        KRNES3.addMinibarItem(m1);
        KRNES3.addMinibarItem(m2);
        KRNES3.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNES3);
        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_203"));

        Room KRNES4 = new Room("KRNE_204", "204", "Standard", 2, "Available", h7);
        KRNES4.addRoomFacility(rf1);
        KRNES4.addRoomFacility(rf2);
        KRNES4.addRoomFacility(rf3);
        KRNES4.addRoomFacility(rf4);
        KRNES4.addRoomFacility(rf5);
        KRNES4.addRoomFacility(rf6);
        KRNES4.addRoomFacility(rf7);
        KRNES4.addRoomFacility(rf8);
        KRNES4.addRoomFacility(rf9);
        KRNES4.addRoomFacility(rf10);
        KRNES4.addMinibarItem(m1);
        KRNES4.addMinibarItem(m2);
        KRNES4.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNES4);
        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_204"));

        Room KRNES5 = new Room("KRNE_205", "205", "Standard", 2, "Available", h7);
        KRNES5.addRoomFacility(rf1);
        KRNES5.addRoomFacility(rf2);
        KRNES5.addRoomFacility(rf3);
        KRNES5.addRoomFacility(rf4);
        KRNES5.addRoomFacility(rf5);
        KRNES5.addRoomFacility(rf6);
        KRNES5.addRoomFacility(rf7);
        KRNES5.addRoomFacility(rf8);
        KRNES5.addRoomFacility(rf9);
        KRNES5.addRoomFacility(rf10);
        KRNES5.addMinibarItem(m1);
        KRNES5.addMinibarItem(m2);
        KRNES5.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNES5);
        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_205"));

        Room KRNES6 = new Room("KRNE_206", "206", "Standard", 2, "Occupied", h7);
        KRNES6.addRoomFacility(rf1);
        KRNES6.addRoomFacility(rf2);
        KRNES6.addRoomFacility(rf3);
        KRNES6.addRoomFacility(rf4);
        KRNES6.addRoomFacility(rf5);
        KRNES6.addRoomFacility(rf6);
        KRNES6.addRoomFacility(rf7);
        KRNES6.addRoomFacility(rf8);
        KRNES6.addRoomFacility(rf9);
        KRNES6.addRoomFacility(rf10);
        KRNES6.addMinibarItem(m1);
        KRNES6.addMinibarItem(m2);
        KRNES6.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNES6);
        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_206"));

        Room KRNES11 = new Room("KRNE_301", "301", "Standard", 2, "Available", h7);
        KRNES11.addRoomFacility(rf1);
        KRNES11.addRoomFacility(rf2);
        KRNES11.addRoomFacility(rf3);
        KRNES11.addRoomFacility(rf4);
        KRNES11.addRoomFacility(rf5);
        KRNES11.addRoomFacility(rf6);
        KRNES11.addRoomFacility(rf7);
        KRNES11.addRoomFacility(rf8);
        KRNES11.addRoomFacility(rf9);
        KRNES11.addRoomFacility(rf10);
        KRNES11.addMinibarItem(m1);
        KRNES11.addMinibarItem(m2);
        KRNES11.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNES11);
        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_301"));

        Room KRNES12 = new Room("KRNE_302", "302", "Standard", 2, "Available", h7);
        KRNES12.addRoomFacility(rf1);
        KRNES12.addRoomFacility(rf2);
        KRNES12.addRoomFacility(rf3);
        KRNES12.addRoomFacility(rf4);
        KRNES12.addRoomFacility(rf5);
        KRNES12.addRoomFacility(rf6);
        KRNES12.addRoomFacility(rf7);
        KRNES12.addRoomFacility(rf8);
        KRNES12.addRoomFacility(rf9);
        KRNES12.addRoomFacility(rf10);
        KRNES12.addMinibarItem(m1);
        KRNES12.addMinibarItem(m2);
        KRNES12.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNES12);
        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_302"));

        Room KRNES13 = new Room("KRNE_303", "303", "Standard", 2, "Occupied", h7);
        KRNES13.addRoomFacility(rf1);
        KRNES13.addRoomFacility(rf2);
        KRNES13.addRoomFacility(rf3);
        KRNES13.addRoomFacility(rf4);
        KRNES13.addRoomFacility(rf5);
        KRNES13.addRoomFacility(rf6);
        KRNES13.addRoomFacility(rf7);
        KRNES13.addRoomFacility(rf8);
        KRNES13.addRoomFacility(rf9);
        KRNES13.addRoomFacility(rf10);
        KRNES13.addMinibarItem(m1);
        KRNES13.addMinibarItem(m2);
        KRNES13.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNES13);
        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_303"));

        Room KRNES14 = new Room("KRNE_304", "304", "Standard", 2, "Available", h7);
        KRNES14.addRoomFacility(rf1);
        KRNES14.addRoomFacility(rf2);
        KRNES14.addRoomFacility(rf3);
        KRNES14.addRoomFacility(rf4);
        KRNES14.addRoomFacility(rf5);
        KRNES14.addRoomFacility(rf6);
        KRNES14.addRoomFacility(rf7);
        KRNES14.addRoomFacility(rf8);
        KRNES14.addRoomFacility(rf9);
        KRNES14.addRoomFacility(rf10);
        KRNES14.addMinibarItem(m1);
        KRNES14.addMinibarItem(m2);
        KRNES14.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNES14);
        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_304"));

        Room KRNES15 = new Room("KRNE_305", "305", "Standard", 2, "Available", h7);
        KRNES15.addRoomFacility(rf1);
        KRNES15.addRoomFacility(rf2);
        KRNES15.addRoomFacility(rf3);
        KRNES15.addRoomFacility(rf4);
        KRNES15.addRoomFacility(rf5);
        KRNES15.addRoomFacility(rf6);
        KRNES15.addRoomFacility(rf7);
        KRNES15.addRoomFacility(rf8);
        KRNES15.addRoomFacility(rf9);
        KRNES15.addRoomFacility(rf10);
        KRNES15.addMinibarItem(m1);
        KRNES15.addMinibarItem(m2);
        KRNES15.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNES15);
        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_305"));

        Room KRNES16 = new Room("KRNE_306", "306", "Standard", 2, "Occupied", h7);
        KRNES16.addRoomFacility(rf1);
        KRNES16.addRoomFacility(rf2);
        KRNES16.addRoomFacility(rf3);
        KRNES16.addRoomFacility(rf4);
        KRNES16.addRoomFacility(rf5);
        KRNES16.addRoomFacility(rf6);
        KRNES16.addRoomFacility(rf7);
        KRNES16.addRoomFacility(rf8);
        KRNES16.addRoomFacility(rf9);
        KRNES16.addRoomFacility(rf10);
        KRNES16.addMinibarItem(m1);
        KRNES16.addMinibarItem(m2);
        KRNES16.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNES16);
        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_306"));

        Room KRNES21 = new Room("KRNE_401", "401", "Standard", 2, "Available", h7);
        KRNES21.addRoomFacility(rf1);
        KRNES21.addRoomFacility(rf2);
        KRNES21.addRoomFacility(rf3);
        KRNES21.addRoomFacility(rf4);
        KRNES21.addRoomFacility(rf5);
        KRNES21.addRoomFacility(rf6);
        KRNES21.addRoomFacility(rf7);
        KRNES21.addRoomFacility(rf8);
        KRNES21.addRoomFacility(rf9);
        KRNES21.addRoomFacility(rf10);
        KRNES21.addMinibarItem(m1);
        KRNES21.addMinibarItem(m2);
        KRNES21.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNES21);
        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_401"));

        Room KRNES22 = new Room("KRNE_402", "402", "Standard", 2, "Available", h7);
        KRNES22.addRoomFacility(rf1);
        KRNES22.addRoomFacility(rf2);
        KRNES22.addRoomFacility(rf3);
        KRNES22.addRoomFacility(rf4);
        KRNES22.addRoomFacility(rf5);
        KRNES22.addRoomFacility(rf6);
        KRNES22.addRoomFacility(rf7);
        KRNES22.addRoomFacility(rf8);
        KRNES22.addRoomFacility(rf9);
        KRNES22.addRoomFacility(rf10);
        KRNES22.addMinibarItem(m1);
        KRNES22.addMinibarItem(m2);
        KRNES22.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNES22);
        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_402"));

        Room KRNES23 = new Room("KRNE_403", "403", "Standard", 2, "Available", h7);
        KRNES23.addRoomFacility(rf1);
        KRNES23.addRoomFacility(rf2);
        KRNES23.addRoomFacility(rf3);
        KRNES23.addRoomFacility(rf4);
        KRNES23.addRoomFacility(rf5);
        KRNES23.addRoomFacility(rf6);
        KRNES23.addRoomFacility(rf7);
        KRNES23.addRoomFacility(rf8);
        KRNES23.addRoomFacility(rf9);
        KRNES23.addRoomFacility(rf10);
        KRNES23.addMinibarItem(m1);
        KRNES23.addMinibarItem(m2);
        KRNES23.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNES23);
        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_403"));

        Room KRNES24 = new Room("KRNE_404", "404", "Standard", 2, "Occupied", h7);
        KRNES24.addRoomFacility(rf1);
        KRNES24.addRoomFacility(rf2);
        KRNES24.addRoomFacility(rf3);
        KRNES24.addRoomFacility(rf4);
        KRNES24.addRoomFacility(rf5);
        KRNES24.addRoomFacility(rf6);
        KRNES24.addRoomFacility(rf7);
        KRNES24.addRoomFacility(rf8);
        KRNES24.addRoomFacility(rf9);
        KRNES24.addRoomFacility(rf10);
        KRNES24.addMinibarItem(m1);
        KRNES24.addMinibarItem(m2);
        KRNES24.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNES24);
        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_404"));

        Room KRNES25 = new Room("KRNE_405", "405", "Standard", 2, "Available", h7);
        KRNES25.addRoomFacility(rf1);
        KRNES25.addRoomFacility(rf2);
        KRNES25.addRoomFacility(rf3);
        KRNES25.addRoomFacility(rf4);
        KRNES25.addRoomFacility(rf5);
        KRNES25.addRoomFacility(rf6);
        KRNES25.addRoomFacility(rf7);
        KRNES25.addRoomFacility(rf8);
        KRNES25.addRoomFacility(rf9);
        KRNES25.addRoomFacility(rf10);
        KRNES25.addMinibarItem(m1);
        KRNES25.addMinibarItem(m2);
        KRNES25.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNES25);
        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_405"));

        Room KRNES26 = new Room("KRNE_406", "406", "Standard", 2, "Available", h7);
        KRNES26.addRoomFacility(rf1);
        KRNES26.addRoomFacility(rf2);
        KRNES26.addRoomFacility(rf3);
        KRNES26.addRoomFacility(rf4);
        KRNES26.addRoomFacility(rf5);
        KRNES26.addRoomFacility(rf6);
        KRNES26.addRoomFacility(rf7);
        KRNES26.addRoomFacility(rf8);
        KRNES26.addRoomFacility(rf9);
        KRNES26.addRoomFacility(rf10);
        KRNES26.addMinibarItem(m1);
        KRNES26.addMinibarItem(m2);
        KRNES26.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNES26);
        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_406"));

        Room KRNES31 = new Room("KRNE_501", "501", "Standard", 2, "Occupied", h7);
        KRNES31.addRoomFacility(rf1);
        KRNES31.addRoomFacility(rf2);
        KRNES31.addRoomFacility(rf3);
        KRNES31.addRoomFacility(rf4);
        KRNES31.addRoomFacility(rf5);
        KRNES31.addRoomFacility(rf6);
        KRNES31.addRoomFacility(rf7);
        KRNES31.addRoomFacility(rf8);
        KRNES31.addRoomFacility(rf9);
        KRNES31.addRoomFacility(rf10);
        KRNES31.addMinibarItem(m1);
        KRNES31.addMinibarItem(m2);
        KRNES31.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNES31);
        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_501"));

        Room KRNES32 = new Room("KRNE_502", "502", "Standard", 2, "Available", h7);
        KRNES32.addRoomFacility(rf1);
        KRNES32.addRoomFacility(rf2);
        KRNES32.addRoomFacility(rf3);
        KRNES32.addRoomFacility(rf4);
        KRNES32.addRoomFacility(rf5);
        KRNES32.addRoomFacility(rf6);
        KRNES32.addRoomFacility(rf7);
        KRNES32.addRoomFacility(rf8);
        KRNES32.addRoomFacility(rf9);
        KRNES32.addRoomFacility(rf10);
        KRNES32.addMinibarItem(m1);
        KRNES32.addMinibarItem(m2);
        KRNES32.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNES32);
        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_502"));

        Room KRNES33 = new Room("KRNE_503", "503", "Standard", 2, "Available", h7);
        KRNES33.addRoomFacility(rf1);
        KRNES33.addRoomFacility(rf2);
        KRNES33.addRoomFacility(rf3);
        KRNES33.addRoomFacility(rf4);
        KRNES33.addRoomFacility(rf5);
        KRNES33.addRoomFacility(rf6);
        KRNES33.addRoomFacility(rf7);
        KRNES33.addRoomFacility(rf8);
        KRNES33.addRoomFacility(rf9);
        KRNES33.addRoomFacility(rf10);
        KRNES33.addMinibarItem(m1);
        KRNES33.addMinibarItem(m2);
        KRNES33.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNES33);
        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_503"));

        Room KRNES34 = new Room("KRNE_504", "504", "Standard", 2, "Unavailable", h7);
        KRNES34.addRoomFacility(rf1);
        KRNES34.addRoomFacility(rf2);
        KRNES34.addRoomFacility(rf3);
        KRNES34.addRoomFacility(rf4);
        KRNES34.addRoomFacility(rf5);
        KRNES34.addRoomFacility(rf6);
        KRNES34.addRoomFacility(rf7);
        KRNES34.addRoomFacility(rf8);
        KRNES34.addRoomFacility(rf9);
        KRNES34.addRoomFacility(rf10);
        KRNES34.addMinibarItem(m1);
        KRNES34.addMinibarItem(m2);
        KRNES34.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNES34);
        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_504"));

        Room KRNES35 = new Room("KRNE_505", "505", "Standard", 2, "Occupied", h7);
        KRNES35.addRoomFacility(rf1);
        KRNES35.addRoomFacility(rf2);
        KRNES35.addRoomFacility(rf3);
        KRNES35.addRoomFacility(rf4);
        KRNES35.addRoomFacility(rf5);
        KRNES35.addRoomFacility(rf6);
        KRNES35.addRoomFacility(rf7);
        KRNES35.addRoomFacility(rf8);
        KRNES35.addRoomFacility(rf9);
        KRNES35.addRoomFacility(rf10);
        KRNES35.addMinibarItem(m1);
        KRNES35.addMinibarItem(m2);
        KRNES35.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNES35);
        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_505"));

        Room KRNES36 = new Room("KRNE_506", "506", "Standard", 2, "Available", h7);
        KRNES36.addRoomFacility(rf1);
        KRNES36.addRoomFacility(rf2);
        KRNES36.addRoomFacility(rf3);
        KRNES36.addRoomFacility(rf4);
        KRNES36.addRoomFacility(rf5);
        KRNES36.addRoomFacility(rf6);
        KRNES36.addRoomFacility(rf7);
        KRNES36.addRoomFacility(rf8);
        KRNES36.addRoomFacility(rf9);
        KRNES36.addRoomFacility(rf10);
        KRNES36.addMinibarItem(m1);
        KRNES36.addMinibarItem(m2);
        KRNES36.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNES36);
        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_506"));

        Room KRNES41 = new Room("KRNE_601", "601", "Standard", 2, "Available", h7);
        KRNES41.addRoomFacility(rf1);
        KRNES41.addRoomFacility(rf2);
        KRNES41.addRoomFacility(rf3);
        KRNES41.addRoomFacility(rf4);
        KRNES41.addRoomFacility(rf5);
        KRNES41.addRoomFacility(rf6);
        KRNES41.addRoomFacility(rf7);
        KRNES41.addRoomFacility(rf8);
        KRNES41.addRoomFacility(rf9);
        KRNES41.addRoomFacility(rf10);
        KRNES41.addMinibarItem(m1);
        KRNES41.addMinibarItem(m2);
        KRNES41.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNES41);
        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_601"));

        Room KRNES42 = new Room("KRNE_602", "602", "Standard", 2, "Occupied", h7);
        KRNES42.addRoomFacility(rf1);
        KRNES42.addRoomFacility(rf2);
        KRNES42.addRoomFacility(rf3);
        KRNES42.addRoomFacility(rf4);
        KRNES42.addRoomFacility(rf5);
        KRNES42.addRoomFacility(rf6);
        KRNES42.addRoomFacility(rf7);
        KRNES42.addRoomFacility(rf8);
        KRNES42.addRoomFacility(rf9);
        KRNES42.addRoomFacility(rf10);
        KRNES42.addMinibarItem(m1);
        KRNES42.addMinibarItem(m2);
        KRNES42.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNES42);
        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_602"));

        Room KRNES43 = new Room("KRNE_603", "603", "Standard", 2, "Available", h7);
        KRNES43.addRoomFacility(rf1);
        KRNES43.addRoomFacility(rf2);
        KRNES43.addRoomFacility(rf3);
        KRNES43.addRoomFacility(rf4);
        KRNES43.addRoomFacility(rf5);
        KRNES43.addRoomFacility(rf6);
        KRNES43.addRoomFacility(rf7);
        KRNES43.addRoomFacility(rf8);
        KRNES43.addRoomFacility(rf9);
        KRNES43.addRoomFacility(rf10);
        KRNES43.addMinibarItem(m1);
        KRNES43.addMinibarItem(m2);
        KRNES43.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNES43);
        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_603"));

        Room KRNES44 = new Room("KRNE_604", "604", "Standard", 2, "Available", h7);
        KRNES44.addRoomFacility(rf1);
        KRNES44.addRoomFacility(rf2);
        KRNES44.addRoomFacility(rf3);
        KRNES44.addRoomFacility(rf4);
        KRNES44.addRoomFacility(rf5);
        KRNES44.addRoomFacility(rf6);
        KRNES44.addRoomFacility(rf7);
        KRNES44.addRoomFacility(rf8);
        KRNES44.addRoomFacility(rf9);
        KRNES44.addRoomFacility(rf10);
        KRNES44.addMinibarItem(m1);
        KRNES44.addMinibarItem(m2);
        KRNES44.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNES44);
        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_604"));

        Room KRNES45 = new Room("KRNE_605", "605", "Standard", 2, "Occupied", h7);
        KRNES45.addRoomFacility(rf1);
        KRNES45.addRoomFacility(rf2);
        KRNES45.addRoomFacility(rf3);
        KRNES45.addRoomFacility(rf4);
        KRNES45.addRoomFacility(rf5);
        KRNES45.addRoomFacility(rf6);
        KRNES45.addRoomFacility(rf7);
        KRNES45.addRoomFacility(rf8);
        KRNES45.addRoomFacility(rf9);
        KRNES45.addRoomFacility(rf10);
        KRNES45.addMinibarItem(m1);
        KRNES45.addMinibarItem(m2);
        KRNES45.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNES45);
        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_605"));

        Room KRNES46 = new Room("KRNE_606", "606", "Standard", 2, "Available", h7);
        KRNES46.addRoomFacility(rf1);
        KRNES46.addRoomFacility(rf2);
        KRNES46.addRoomFacility(rf3);
        KRNES46.addRoomFacility(rf4);
        KRNES46.addRoomFacility(rf5);
        KRNES46.addRoomFacility(rf6);
        KRNES46.addRoomFacility(rf7);
        KRNES46.addRoomFacility(rf8);
        KRNES46.addRoomFacility(rf9);
        KRNES46.addRoomFacility(rf10);
        KRNES46.addMinibarItem(m1);
        KRNES46.addMinibarItem(m2);
        KRNES46.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNES46);
        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_606"));

        Room KRNE_1001 = new Room("KRNE_1001", "1001", "Deluxe", 3, "Available", h7);

        KRNE_1001.addRoomFacility(rf1);
        KRNE_1001.addRoomFacility(rf2);
        KRNE_1001.addRoomFacility(rf3);
        KRNE_1001.addRoomFacility(rf4);
        KRNE_1001.addRoomFacility(rf5);
        KRNE_1001.addRoomFacility(rf6);
        KRNE_1001.addRoomFacility(rf7);
        KRNE_1001.addRoomFacility(rf8);
        KRNE_1001.addRoomFacility(rf9);
        KRNE_1001.addRoomFacility(rf10);
        KRNE_1001.addRoomFacility(rf11);
        KRNE_1001.addRoomFacility(rf12);
        KRNE_1001.addRoomFacility(rf13);

        KRNE_1001.addMinibarItem(m1);
        KRNE_1001.addMinibarItem(m2);
        KRNE_1001.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNE_1001);

        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_1001"));

        Room KRNE_1002 = new Room("KRNE_1002", "1002", "Deluxe", 3, "Available", h7);

        KRNE_1002.addRoomFacility(rf1);
        KRNE_1002.addRoomFacility(rf2);
        KRNE_1002.addRoomFacility(rf3);
        KRNE_1002.addRoomFacility(rf4);
        KRNE_1002.addRoomFacility(rf5);
        KRNE_1002.addRoomFacility(rf6);
        KRNE_1002.addRoomFacility(rf7);
        KRNE_1002.addRoomFacility(rf8);
        KRNE_1002.addRoomFacility(rf9);
        KRNE_1002.addRoomFacility(rf10);
        KRNE_1002.addRoomFacility(rf11);
        KRNE_1002.addRoomFacility(rf12);
        KRNE_1002.addRoomFacility(rf13);

        KRNE_1002.addMinibarItem(m1);
        KRNE_1002.addMinibarItem(m2);
        KRNE_1002.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNE_1002);

        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_1002"));

        Room KRNE_1003 = new Room("KRNE_1003", "1003", "Deluxe", 3, "Occupied", h7);

        KRNE_1003.addRoomFacility(rf1);
        KRNE_1003.addRoomFacility(rf2);
        KRNE_1003.addRoomFacility(rf3);
        KRNE_1003.addRoomFacility(rf4);
        KRNE_1003.addRoomFacility(rf5);
        KRNE_1003.addRoomFacility(rf6);
        KRNE_1003.addRoomFacility(rf7);
        KRNE_1003.addRoomFacility(rf8);
        KRNE_1003.addRoomFacility(rf9);
        KRNE_1003.addRoomFacility(rf10);
        KRNE_1003.addRoomFacility(rf11);
        KRNE_1003.addRoomFacility(rf12);
        KRNE_1003.addRoomFacility(rf13);

        KRNE_1003.addMinibarItem(m1);
        KRNE_1003.addMinibarItem(m2);
        KRNE_1003.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNE_1003);

        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_1003"));

        Room KRNE_1101 = new Room("KRNE_1101", "1101", "Deluxe", 3, "Occupied", h7);

        KRNE_1101.addRoomFacility(rf1);
        KRNE_1101.addRoomFacility(rf2);
        KRNE_1101.addRoomFacility(rf3);
        KRNE_1101.addRoomFacility(rf4);
        KRNE_1101.addRoomFacility(rf5);
        KRNE_1101.addRoomFacility(rf6);
        KRNE_1101.addRoomFacility(rf7);
        KRNE_1101.addRoomFacility(rf8);
        KRNE_1101.addRoomFacility(rf9);
        KRNE_1101.addRoomFacility(rf10);
        KRNE_1101.addRoomFacility(rf11);
        KRNE_1101.addRoomFacility(rf12);
        KRNE_1101.addRoomFacility(rf13);

        KRNE_1101.addMinibarItem(m1);
        KRNE_1101.addMinibarItem(m2);
        KRNE_1101.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNE_1101);

        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_1101"));

        Room KRNE_1102 = new Room("KRNE_1102", "1102", "Deluxe", 3, "Unavailable", h7);

        KRNE_1102.addRoomFacility(rf1);
        KRNE_1102.addRoomFacility(rf2);
        KRNE_1102.addRoomFacility(rf3);
        KRNE_1102.addRoomFacility(rf4);
        KRNE_1102.addRoomFacility(rf5);
        KRNE_1102.addRoomFacility(rf6);
        KRNE_1102.addRoomFacility(rf7);
        KRNE_1102.addRoomFacility(rf8);
        KRNE_1102.addRoomFacility(rf9);
        KRNE_1102.addRoomFacility(rf10);
        KRNE_1102.addRoomFacility(rf11);
        KRNE_1102.addRoomFacility(rf12);
        KRNE_1102.addRoomFacility(rf13);

        KRNE_1102.addMinibarItem(m1);
        KRNE_1102.addMinibarItem(m2);
        KRNE_1102.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNE_1102);

        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_1102"));

        Room KRNE_1103 = new Room("KRNE_1103", "1103", "Deluxe", 3, "Available", h7);

        KRNE_1103.addRoomFacility(rf1);
        KRNE_1103.addRoomFacility(rf2);
        KRNE_1103.addRoomFacility(rf3);
        KRNE_1103.addRoomFacility(rf4);
        KRNE_1103.addRoomFacility(rf5);
        KRNE_1103.addRoomFacility(rf6);
        KRNE_1103.addRoomFacility(rf7);
        KRNE_1103.addRoomFacility(rf8);
        KRNE_1103.addRoomFacility(rf9);
        KRNE_1103.addRoomFacility(rf10);
        KRNE_1103.addRoomFacility(rf11);
        KRNE_1103.addRoomFacility(rf12);
        KRNE_1103.addRoomFacility(rf13);

        KRNE_1103.addMinibarItem(m1);
        KRNE_1103.addMinibarItem(m2);
        KRNE_1103.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNE_1103);

        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_1103"));

        Room KRNE_701 = new Room("KRNE_701", "701", "Premium", 4, "Available", h7);

        KRNE_701.addRoomFacility(rf1);
        KRNE_701.addRoomFacility(rf2);
        KRNE_701.addRoomFacility(rf3);
        KRNE_701.addRoomFacility(rf4);
        KRNE_701.addRoomFacility(rf5);
        KRNE_701.addRoomFacility(rf6);
        KRNE_701.addRoomFacility(rf7);
        KRNE_701.addRoomFacility(rf8);
        KRNE_701.addRoomFacility(rf9);
        KRNE_701.addRoomFacility(rf10);
        KRNE_701.addRoomFacility(rf11);
        KRNE_701.addRoomFacility(rf12);
        KRNE_701.addRoomFacility(rf13);
        KRNE_701.addRoomFacility(rf14);
        KRNE_701.addRoomFacility(rf15);

        KRNE_701.addMinibarItem(m1);
        KRNE_701.addMinibarItem(m2);
        KRNE_701.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNE_701);

        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_701"));

        Room KRNE_702 = new Room("KRNE_702", "702", "Premium", 4, "Available", h7);

        KRNE_702.addRoomFacility(rf1);
        KRNE_702.addRoomFacility(rf2);
        KRNE_702.addRoomFacility(rf3);
        KRNE_702.addRoomFacility(rf4);
        KRNE_702.addRoomFacility(rf5);
        KRNE_702.addRoomFacility(rf6);
        KRNE_702.addRoomFacility(rf7);
        KRNE_702.addRoomFacility(rf8);
        KRNE_702.addRoomFacility(rf9);
        KRNE_702.addRoomFacility(rf10);
        KRNE_702.addRoomFacility(rf11);
        KRNE_702.addRoomFacility(rf12);
        KRNE_702.addRoomFacility(rf13);
        KRNE_702.addRoomFacility(rf14);
        KRNE_702.addRoomFacility(rf15);

        KRNE_702.addMinibarItem(m1);
        KRNE_702.addMinibarItem(m2);
        KRNE_702.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNE_702);

        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_702"));

        Room KRNE_703 = new Room("KRNE_703", "703", "Premium", 4, "Available", h7);

        KRNE_703.addRoomFacility(rf1);
        KRNE_703.addRoomFacility(rf2);
        KRNE_703.addRoomFacility(rf3);
        KRNE_703.addRoomFacility(rf4);
        KRNE_703.addRoomFacility(rf5);
        KRNE_703.addRoomFacility(rf6);
        KRNE_703.addRoomFacility(rf7);
        KRNE_703.addRoomFacility(rf8);
        KRNE_703.addRoomFacility(rf9);
        KRNE_703.addRoomFacility(rf10);
        KRNE_703.addRoomFacility(rf11);
        KRNE_703.addRoomFacility(rf12);
        KRNE_703.addRoomFacility(rf13);
        KRNE_703.addRoomFacility(rf14);
        KRNE_703.addRoomFacility(rf15);

        KRNE_703.addMinibarItem(m1);
        KRNE_703.addMinibarItem(m2);
        KRNE_703.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNE_703);

        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_703"));

        Room KRNE_704 = new Room("KRNE_704", "704", "Premium", 4, "Occupied", h7);

        KRNE_704.addRoomFacility(rf1);
        KRNE_704.addRoomFacility(rf2);
        KRNE_704.addRoomFacility(rf3);
        KRNE_704.addRoomFacility(rf4);
        KRNE_704.addRoomFacility(rf5);
        KRNE_704.addRoomFacility(rf6);
        KRNE_704.addRoomFacility(rf7);
        KRNE_704.addRoomFacility(rf8);
        KRNE_704.addRoomFacility(rf9);
        KRNE_704.addRoomFacility(rf10);
        KRNE_704.addRoomFacility(rf11);
        KRNE_704.addRoomFacility(rf12);
        KRNE_704.addRoomFacility(rf13);
        KRNE_704.addRoomFacility(rf14);
        KRNE_704.addRoomFacility(rf15);

        KRNE_704.addMinibarItem(m1);
        KRNE_704.addMinibarItem(m2);
        KRNE_704.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNE_704);

        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_704"));

        Room KRNE_801 = new Room("KRNE_801", "801", "Premium", 4, "Available", h7);

        KRNE_801.addRoomFacility(rf1);
        KRNE_801.addRoomFacility(rf2);
        KRNE_801.addRoomFacility(rf3);
        KRNE_801.addRoomFacility(rf4);
        KRNE_801.addRoomFacility(rf5);
        KRNE_801.addRoomFacility(rf6);
        KRNE_801.addRoomFacility(rf7);
        KRNE_801.addRoomFacility(rf8);
        KRNE_801.addRoomFacility(rf9);
        KRNE_801.addRoomFacility(rf10);
        KRNE_801.addRoomFacility(rf11);
        KRNE_801.addRoomFacility(rf12);
        KRNE_801.addRoomFacility(rf13);
        KRNE_801.addRoomFacility(rf14);
        KRNE_801.addRoomFacility(rf15);

        KRNE_801.addMinibarItem(m1);
        KRNE_801.addMinibarItem(m2);
        KRNE_801.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNE_801);

        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_801"));

        Room KRNE_802 = new Room("KRNE_802", "802", "Premium", 4, "Unavailable", h7);

        KRNE_802.addRoomFacility(rf1);
        KRNE_802.addRoomFacility(rf2);
        KRNE_802.addRoomFacility(rf3);
        KRNE_802.addRoomFacility(rf4);
        KRNE_802.addRoomFacility(rf5);
        KRNE_802.addRoomFacility(rf6);
        KRNE_802.addRoomFacility(rf7);
        KRNE_802.addRoomFacility(rf8);
        KRNE_802.addRoomFacility(rf9);
        KRNE_802.addRoomFacility(rf10);
        KRNE_802.addRoomFacility(rf11);
        KRNE_802.addRoomFacility(rf12);
        KRNE_802.addRoomFacility(rf13);
        KRNE_802.addRoomFacility(rf14);
        KRNE_802.addRoomFacility(rf15);

        KRNE_802.addMinibarItem(m1);
        KRNE_802.addMinibarItem(m2);
        KRNE_802.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNE_802);

        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_802"));

        Room KRNE_803 = new Room("KRNE_803", "803", "Premium", 4, "Occupied", h7);

        KRNE_803.addRoomFacility(rf1);
        KRNE_803.addRoomFacility(rf2);
        KRNE_803.addRoomFacility(rf3);
        KRNE_803.addRoomFacility(rf4);
        KRNE_803.addRoomFacility(rf5);
        KRNE_803.addRoomFacility(rf6);
        KRNE_803.addRoomFacility(rf7);
        KRNE_803.addRoomFacility(rf8);
        KRNE_803.addRoomFacility(rf9);
        KRNE_803.addRoomFacility(rf10);
        KRNE_803.addRoomFacility(rf11);
        KRNE_803.addRoomFacility(rf12);
        KRNE_803.addRoomFacility(rf13);
        KRNE_803.addRoomFacility(rf14);
        KRNE_803.addRoomFacility(rf15);

        KRNE_803.addMinibarItem(m1);
        KRNE_803.addMinibarItem(m2);
        KRNE_803.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNE_803);

        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_803"));

        Room KRNE_804 = new Room("KRNE_804", "804", "Premium", 4, "Available", h7);

        KRNE_804.addRoomFacility(rf1);
        KRNE_804.addRoomFacility(rf2);
        KRNE_804.addRoomFacility(rf3);
        KRNE_804.addRoomFacility(rf4);
        KRNE_804.addRoomFacility(rf5);
        KRNE_804.addRoomFacility(rf6);
        KRNE_804.addRoomFacility(rf7);
        KRNE_804.addRoomFacility(rf8);
        KRNE_804.addRoomFacility(rf9);
        KRNE_804.addRoomFacility(rf10);
        KRNE_804.addRoomFacility(rf11);
        KRNE_804.addRoomFacility(rf12);
        KRNE_804.addRoomFacility(rf13);
        KRNE_804.addRoomFacility(rf14);
        KRNE_804.addRoomFacility(rf15);

        KRNE_804.addMinibarItem(m1);
        KRNE_804.addMinibarItem(m2);
        KRNE_804.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNE_804);

        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_804"));

        Room KRNE_901 = new Room("KRNE_901", "901", "Premium", 4, "Available", h7);

        KRNE_901.addRoomFacility(rf1);
        KRNE_901.addRoomFacility(rf2);
        KRNE_901.addRoomFacility(rf3);
        KRNE_901.addRoomFacility(rf4);
        KRNE_901.addRoomFacility(rf5);
        KRNE_901.addRoomFacility(rf6);
        KRNE_901.addRoomFacility(rf7);
        KRNE_901.addRoomFacility(rf8);
        KRNE_901.addRoomFacility(rf9);
        KRNE_901.addRoomFacility(rf10);
        KRNE_901.addRoomFacility(rf11);
        KRNE_901.addRoomFacility(rf12);
        KRNE_901.addRoomFacility(rf13);
        KRNE_901.addRoomFacility(rf14);
        KRNE_901.addRoomFacility(rf15);

        KRNE_901.addMinibarItem(m1);
        KRNE_901.addMinibarItem(m2);
        KRNE_901.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNE_901);

        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_901"));

        Room KRNE_902 = new Room("KRNE_902", "902", "Premium", 4, "Occupied", h7);

        KRNE_902.addRoomFacility(rf1);
        KRNE_902.addRoomFacility(rf2);
        KRNE_902.addRoomFacility(rf3);
        KRNE_902.addRoomFacility(rf4);
        KRNE_902.addRoomFacility(rf5);
        KRNE_902.addRoomFacility(rf6);
        KRNE_902.addRoomFacility(rf7);
        KRNE_902.addRoomFacility(rf8);
        KRNE_902.addRoomFacility(rf9);
        KRNE_902.addRoomFacility(rf10);
        KRNE_902.addRoomFacility(rf11);
        KRNE_902.addRoomFacility(rf12);
        KRNE_902.addRoomFacility(rf13);
        KRNE_902.addRoomFacility(rf14);
        KRNE_902.addRoomFacility(rf15);

        KRNE_902.addMinibarItem(m1);
        KRNE_902.addMinibarItem(m2);
        KRNE_902.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNE_902);

        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_902"));

        Room KRNE_903 = new Room("KRNE_903", "903", "Premium", 4, "Available", h7);

        KRNE_903.addRoomFacility(rf1);
        KRNE_903.addRoomFacility(rf2);
        KRNE_903.addRoomFacility(rf3);
        KRNE_903.addRoomFacility(rf4);
        KRNE_903.addRoomFacility(rf5);
        KRNE_903.addRoomFacility(rf6);
        KRNE_903.addRoomFacility(rf7);
        KRNE_903.addRoomFacility(rf8);
        KRNE_903.addRoomFacility(rf9);
        KRNE_903.addRoomFacility(rf10);
        KRNE_903.addRoomFacility(rf11);
        KRNE_903.addRoomFacility(rf12);
        KRNE_903.addRoomFacility(rf13);
        KRNE_903.addRoomFacility(rf14);
        KRNE_903.addRoomFacility(rf15);

        KRNE_903.addMinibarItem(m1);
        KRNE_903.addMinibarItem(m2);
        KRNE_903.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNE_903);

        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_903"));

        Room KRNE_904 = new Room("KRNE_904", "904", "Premium", 4, "Unavailable", h7);

        KRNE_904.addRoomFacility(rf1);
        KRNE_904.addRoomFacility(rf2);
        KRNE_904.addRoomFacility(rf3);
        KRNE_904.addRoomFacility(rf4);
        KRNE_904.addRoomFacility(rf5);
        KRNE_904.addRoomFacility(rf6);
        KRNE_904.addRoomFacility(rf7);
        KRNE_904.addRoomFacility(rf8);
        KRNE_904.addRoomFacility(rf9);
        KRNE_904.addRoomFacility(rf10);
        KRNE_904.addRoomFacility(rf11);
        KRNE_904.addRoomFacility(rf12);
        KRNE_904.addRoomFacility(rf13);
        KRNE_904.addRoomFacility(rf14);
        KRNE_904.addRoomFacility(rf15);

        KRNE_904.addMinibarItem(m1);
        KRNE_904.addMinibarItem(m2);
        KRNE_904.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNE_904);

        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_904"));

        Room KRNE_1201 = new Room("KRNE_1201", "1201", "Suite", 4, "Occupied", h7);

        KRNE_1201.addRoomFacility(rf1);
        KRNE_1201.addRoomFacility(rf2);
        KRNE_1201.addRoomFacility(rf3);
        KRNE_1201.addRoomFacility(rf4);
        KRNE_1201.addRoomFacility(rf5);
        KRNE_1201.addRoomFacility(rf6);
        KRNE_1201.addRoomFacility(rf7);
        KRNE_1201.addRoomFacility(rf8);
        KRNE_1201.addRoomFacility(rf9);
        KRNE_1201.addRoomFacility(rf10);
        KRNE_1201.addRoomFacility(rf11);
        KRNE_1201.addRoomFacility(rf12);
        KRNE_1201.addRoomFacility(rf13);
        KRNE_1201.addRoomFacility(rf14);
        KRNE_1201.addRoomFacility(rf15);
        KRNE_1201.addRoomFacility(rf16);
        KRNE_1201.addRoomFacility(rf17);
        KRNE_1201.addRoomFacility(rf18);
        KRNE_1201.addRoomFacility(rf19);

        KRNE_1201.addMinibarItem(m1);
        KRNE_1201.addMinibarItem(m2);
        KRNE_1201.addMinibarItem(m3);
        KRNE_1201.addMinibarItem(m4);
        KRNE_1201.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRNE_1201);

        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_1201"));

        Room KRNE_1202 = new Room("KRNE_1202", "1202", "Suite", 4, "Available", h7);

        KRNE_1202.addRoomFacility(rf1);
        KRNE_1202.addRoomFacility(rf2);
        KRNE_1202.addRoomFacility(rf3);
        KRNE_1202.addRoomFacility(rf4);
        KRNE_1202.addRoomFacility(rf5);
        KRNE_1202.addRoomFacility(rf6);
        KRNE_1202.addRoomFacility(rf7);
        KRNE_1202.addRoomFacility(rf8);
        KRNE_1202.addRoomFacility(rf9);
        KRNE_1202.addRoomFacility(rf10);
        KRNE_1202.addRoomFacility(rf11);
        KRNE_1202.addRoomFacility(rf12);
        KRNE_1202.addRoomFacility(rf13);
        KRNE_1202.addRoomFacility(rf14);
        KRNE_1202.addRoomFacility(rf15);
        KRNE_1202.addRoomFacility(rf16);
        KRNE_1202.addRoomFacility(rf17);
        KRNE_1202.addRoomFacility(rf18);
        KRNE_1202.addRoomFacility(rf19);

        KRNE_1202.addMinibarItem(m1);
        KRNE_1202.addMinibarItem(m2);
        KRNE_1202.addMinibarItem(m3);
        KRNE_1202.addMinibarItem(m4);
        KRNE_1202.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRNE_1202);

        h7.addRoom(roomSessionLocal.getRoomByName("KRNE_1202"));

        em.flush();

    }

    public void initializeKRNWRoom() throws NoResultException {
        Hotel h8 = hotelSessionLocal.getHotelByName("Kent Ridge North West");
        MinibarItem m1 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Potato Chips");
        MinibarItem m2 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Chocolate");
        MinibarItem m3 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Mineral Water");
        MinibarItem m4 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Coke");
        MinibarItem m5 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Wine");
        RoomFacility rf1 = roomFacilitySessionLocal.getRoomFacilityByName("Wifi");
        RoomFacility rf2 = roomFacilitySessionLocal.getRoomFacilityByName("Minibar");
        RoomFacility rf3 = roomFacilitySessionLocal.getRoomFacilityByName("Aircon");
        RoomFacility rf4 = roomFacilitySessionLocal.getRoomFacilityByName("Cable TV");
        RoomFacility rf5 = roomFacilitySessionLocal.getRoomFacilityByName("Telephone");
        RoomFacility rf6 = roomFacilitySessionLocal.getRoomFacilityByName("Water Heater");
        RoomFacility rf7 = roomFacilitySessionLocal.getRoomFacilityByName("Bathroom with Shower Head");
        RoomFacility rf8 = roomFacilitySessionLocal.getRoomFacilityByName("HairDryer");
        RoomFacility rf9 = roomFacilitySessionLocal.getRoomFacilityByName("Safe Deposit Box");
        RoomFacility rf10 = roomFacilitySessionLocal.getRoomFacilityByName("Water Kettle with complimentary teas/coffee");
        RoomFacility rf11 = roomFacilitySessionLocal.getRoomFacilityByName("Room with Windows");
        RoomFacility rf12 = roomFacilitySessionLocal.getRoomFacilityByName("Bathtub");
        RoomFacility rf13 = roomFacilitySessionLocal.getRoomFacilityByName("Mini Fridge");
        RoomFacility rf14 = roomFacilitySessionLocal.getRoomFacilityByName("Coffee Machine");
        RoomFacility rf15 = roomFacilitySessionLocal.getRoomFacilityByName("Balcony");
        RoomFacility rf16 = roomFacilitySessionLocal.getRoomFacilityByName("Microwave");
        RoomFacility rf17 = roomFacilitySessionLocal.getRoomFacilityByName("Gaming Console");
        RoomFacility rf18 = roomFacilitySessionLocal.getRoomFacilityByName("Entertainment System");
        RoomFacility rf19 = roomFacilitySessionLocal.getRoomFacilityByName("Blackout Curtains");
        RoomFacility rf20 = roomFacilitySessionLocal.getRoomFacilityByName("High Ceiling");
        RoomFacility rf21 = roomFacilitySessionLocal.getRoomFacilityByName("Jaccuzi");
        RoomFacility rf22 = roomFacilitySessionLocal.getRoomFacilityByName("Kitchen");

        FunctionRoom KRNWFR1 = new FunctionRoom("KRNWFR1", 20, "Available", 20000.00, h8);
        FunctionRoom KRNWFR2 = new FunctionRoom("KRNWFR2", 100, "Available", 100000.00, h8);
        FunctionRoom KRNWFR3 = new FunctionRoom("KRNWFR3", 50, "Available", 50000.00, h8);
        FunctionRoom KRNWFR4 = new FunctionRoom("KRNWFR4", 70, "Available", 70000.00, h8);
        FunctionRoom KRNWFR5 = new FunctionRoom("KRNWFR5", 80, "Available", 80000.00, h8);

        functionRoomSessionLocal.createFunctionRoom(KRNWFR1);
        functionRoomSessionLocal.createFunctionRoom(KRNWFR2);
        functionRoomSessionLocal.createFunctionRoom(KRNWFR3);
        functionRoomSessionLocal.createFunctionRoom(KRNWFR4);
        functionRoomSessionLocal.createFunctionRoom(KRNWFR5);

        h8.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRNWFR1"));
        h8.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRNWFR2"));
        h8.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRNWFR3"));
        h8.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRNWFR4"));
        h8.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRNWFR5"));

        Room KRNWS1 = new Room("KRNW_201", "201", "Standard", 2, "Available", h8);
        KRNWS1.addRoomFacility(rf1);
        KRNWS1.addRoomFacility(rf2);
        KRNWS1.addRoomFacility(rf3);
        KRNWS1.addRoomFacility(rf4);
        KRNWS1.addRoomFacility(rf5);
        KRNWS1.addRoomFacility(rf6);
        KRNWS1.addRoomFacility(rf7);
        KRNWS1.addRoomFacility(rf8);
        KRNWS1.addRoomFacility(rf9);
        KRNWS1.addRoomFacility(rf10);
        KRNWS1.addMinibarItem(m1);
        KRNWS1.addMinibarItem(m2);
        KRNWS1.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNWS1);
        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_201"));

        Room KRNWS2 = new Room("KRNW_202", "202", "Standard", 2, "Available", h8);
        KRNWS2.addRoomFacility(rf1);
        KRNWS2.addRoomFacility(rf2);
        KRNWS2.addRoomFacility(rf3);
        KRNWS2.addRoomFacility(rf4);
        KRNWS2.addRoomFacility(rf5);
        KRNWS2.addRoomFacility(rf6);
        KRNWS2.addRoomFacility(rf7);
        KRNWS2.addRoomFacility(rf8);
        KRNWS2.addRoomFacility(rf9);
        KRNWS2.addRoomFacility(rf10);
        KRNWS2.addMinibarItem(m1);
        KRNWS2.addMinibarItem(m2);
        KRNWS2.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNWS2);
        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_202"));

        Room KRNWS3 = new Room("KRNW_203", "203", "Standard", 2, "Occupied", h8);
        KRNWS3.addRoomFacility(rf1);
        KRNWS3.addRoomFacility(rf2);
        KRNWS3.addRoomFacility(rf3);
        KRNWS3.addRoomFacility(rf4);
        KRNWS3.addRoomFacility(rf5);
        KRNWS3.addRoomFacility(rf6);
        KRNWS3.addRoomFacility(rf7);
        KRNWS3.addRoomFacility(rf8);
        KRNWS3.addRoomFacility(rf9);
        KRNWS3.addRoomFacility(rf10);
        KRNWS3.addMinibarItem(m1);
        KRNWS3.addMinibarItem(m2);
        KRNWS3.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNWS3);
        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_203"));

        Room KRNWS4 = new Room("KRNW_204", "204", "Standard", 2, "Available", h8);
        KRNWS4.addRoomFacility(rf1);
        KRNWS4.addRoomFacility(rf2);
        KRNWS4.addRoomFacility(rf3);
        KRNWS4.addRoomFacility(rf4);
        KRNWS4.addRoomFacility(rf5);
        KRNWS4.addRoomFacility(rf6);
        KRNWS4.addRoomFacility(rf7);
        KRNWS4.addRoomFacility(rf8);
        KRNWS4.addRoomFacility(rf9);
        KRNWS4.addRoomFacility(rf10);
        KRNWS4.addMinibarItem(m1);
        KRNWS4.addMinibarItem(m2);
        KRNWS4.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNWS4);
        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_204"));

        Room KRNWS5 = new Room("KRNW_205", "205", "Standard", 2, "Unavailable", h8);
        KRNWS5.addRoomFacility(rf1);
        KRNWS5.addRoomFacility(rf2);
        KRNWS5.addRoomFacility(rf3);
        KRNWS5.addRoomFacility(rf4);
        KRNWS5.addRoomFacility(rf5);
        KRNWS5.addRoomFacility(rf6);
        KRNWS5.addRoomFacility(rf7);
        KRNWS5.addRoomFacility(rf8);
        KRNWS5.addRoomFacility(rf9);
        KRNWS5.addRoomFacility(rf10);
        KRNWS5.addMinibarItem(m1);
        KRNWS5.addMinibarItem(m2);
        KRNWS5.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNWS5);
        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_205"));

        Room KRNWS6 = new Room("KRNW_206", "206", "Standard", 2, "Available", h8);
        KRNWS6.addRoomFacility(rf1);
        KRNWS6.addRoomFacility(rf2);
        KRNWS6.addRoomFacility(rf3);
        KRNWS6.addRoomFacility(rf4);
        KRNWS6.addRoomFacility(rf5);
        KRNWS6.addRoomFacility(rf6);
        KRNWS6.addRoomFacility(rf7);
        KRNWS6.addRoomFacility(rf8);
        KRNWS6.addRoomFacility(rf9);
        KRNWS6.addRoomFacility(rf10);
        KRNWS6.addMinibarItem(m1);
        KRNWS6.addMinibarItem(m2);
        KRNWS6.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNWS6);
        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_206"));

        Room KRNWS11 = new Room("KRNW_301", "301", "Standard", 2, "Occupied", h8);
        KRNWS11.addRoomFacility(rf1);
        KRNWS11.addRoomFacility(rf2);
        KRNWS11.addRoomFacility(rf3);
        KRNWS11.addRoomFacility(rf4);
        KRNWS11.addRoomFacility(rf5);
        KRNWS11.addRoomFacility(rf6);
        KRNWS11.addRoomFacility(rf7);
        KRNWS11.addRoomFacility(rf8);
        KRNWS11.addRoomFacility(rf9);
        KRNWS11.addRoomFacility(rf10);
        KRNWS11.addMinibarItem(m1);
        KRNWS11.addMinibarItem(m2);
        KRNWS11.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNWS11);
        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_301"));

        Room KRNWS12 = new Room("KRNW_302", "302", "Standard", 2, "Available", h8);
        KRNWS12.addRoomFacility(rf1);
        KRNWS12.addRoomFacility(rf2);
        KRNWS12.addRoomFacility(rf3);
        KRNWS12.addRoomFacility(rf4);
        KRNWS12.addRoomFacility(rf5);
        KRNWS12.addRoomFacility(rf6);
        KRNWS12.addRoomFacility(rf7);
        KRNWS12.addRoomFacility(rf8);
        KRNWS12.addRoomFacility(rf9);
        KRNWS12.addRoomFacility(rf10);
        KRNWS12.addMinibarItem(m1);
        KRNWS12.addMinibarItem(m2);
        KRNWS12.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNWS12);
        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_302"));

        Room KRNWS13 = new Room("KRNW_303", "303", "Standard", 2, "Available", h8);
        KRNWS13.addRoomFacility(rf1);
        KRNWS13.addRoomFacility(rf2);
        KRNWS13.addRoomFacility(rf3);
        KRNWS13.addRoomFacility(rf4);
        KRNWS13.addRoomFacility(rf5);
        KRNWS13.addRoomFacility(rf6);
        KRNWS13.addRoomFacility(rf7);
        KRNWS13.addRoomFacility(rf8);
        KRNWS13.addRoomFacility(rf9);
        KRNWS13.addRoomFacility(rf10);
        KRNWS13.addMinibarItem(m1);
        KRNWS13.addMinibarItem(m2);
        KRNWS13.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNWS13);
        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_303"));

        Room KRNWS14 = new Room("KRNW_304", "304", "Standard", 2, "Occupied", h8);
        KRNWS14.addRoomFacility(rf1);
        KRNWS14.addRoomFacility(rf2);
        KRNWS14.addRoomFacility(rf3);
        KRNWS14.addRoomFacility(rf4);
        KRNWS14.addRoomFacility(rf5);
        KRNWS14.addRoomFacility(rf6);
        KRNWS14.addRoomFacility(rf7);
        KRNWS14.addRoomFacility(rf8);
        KRNWS14.addRoomFacility(rf9);
        KRNWS14.addRoomFacility(rf10);
        KRNWS14.addMinibarItem(m1);
        KRNWS14.addMinibarItem(m2);
        KRNWS14.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNWS14);
        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_304"));

        Room KRNWS15 = new Room("KRNW_305", "305", "Standard", 2, "Available", h8);
        KRNWS15.addRoomFacility(rf1);
        KRNWS15.addRoomFacility(rf2);
        KRNWS15.addRoomFacility(rf3);
        KRNWS15.addRoomFacility(rf4);
        KRNWS15.addRoomFacility(rf5);
        KRNWS15.addRoomFacility(rf6);
        KRNWS15.addRoomFacility(rf7);
        KRNWS15.addRoomFacility(rf8);
        KRNWS15.addRoomFacility(rf9);
        KRNWS15.addRoomFacility(rf10);
        KRNWS15.addMinibarItem(m1);
        KRNWS15.addMinibarItem(m2);
        KRNWS15.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNWS15);
        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_305"));

        Room KRNWS16 = new Room("KRNW_306", "306", "Standard", 2, "Available", h8);
        KRNWS16.addRoomFacility(rf1);
        KRNWS16.addRoomFacility(rf2);
        KRNWS16.addRoomFacility(rf3);
        KRNWS16.addRoomFacility(rf4);
        KRNWS16.addRoomFacility(rf5);
        KRNWS16.addRoomFacility(rf6);
        KRNWS16.addRoomFacility(rf7);
        KRNWS16.addRoomFacility(rf8);
        KRNWS16.addRoomFacility(rf9);
        KRNWS16.addRoomFacility(rf10);
        KRNWS16.addMinibarItem(m1);
        KRNWS16.addMinibarItem(m2);
        KRNWS16.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNWS16);
        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_306"));

        Room KRNWS21 = new Room("KRNW_401", "401", "Standard", 2, "Available", h8);
        KRNWS21.addRoomFacility(rf1);
        KRNWS21.addRoomFacility(rf2);
        KRNWS21.addRoomFacility(rf3);
        KRNWS21.addRoomFacility(rf4);
        KRNWS21.addRoomFacility(rf5);
        KRNWS21.addRoomFacility(rf6);
        KRNWS21.addRoomFacility(rf7);
        KRNWS21.addRoomFacility(rf8);
        KRNWS21.addRoomFacility(rf9);
        KRNWS21.addRoomFacility(rf10);
        KRNWS21.addMinibarItem(m1);
        KRNWS21.addMinibarItem(m2);
        KRNWS21.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNWS21);
        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_401"));

        Room KRNWS22 = new Room("KRNW_402", "402", "Standard", 2, "Occupied", h8);
        KRNWS22.addRoomFacility(rf1);
        KRNWS22.addRoomFacility(rf2);
        KRNWS22.addRoomFacility(rf3);
        KRNWS22.addRoomFacility(rf4);
        KRNWS22.addRoomFacility(rf5);
        KRNWS22.addRoomFacility(rf6);
        KRNWS22.addRoomFacility(rf7);
        KRNWS22.addRoomFacility(rf8);
        KRNWS22.addRoomFacility(rf9);
        KRNWS22.addRoomFacility(rf10);
        KRNWS22.addMinibarItem(m1);
        KRNWS22.addMinibarItem(m2);
        KRNWS22.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNWS22);
        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_402"));

        Room KRNWS23 = new Room("KRNW_403", "403", "Standard", 2, "Unavailable", h8);
        KRNWS23.addRoomFacility(rf1);
        KRNWS23.addRoomFacility(rf2);
        KRNWS23.addRoomFacility(rf3);
        KRNWS23.addRoomFacility(rf4);
        KRNWS23.addRoomFacility(rf5);
        KRNWS23.addRoomFacility(rf6);
        KRNWS23.addRoomFacility(rf7);
        KRNWS23.addRoomFacility(rf8);
        KRNWS23.addRoomFacility(rf9);
        KRNWS23.addRoomFacility(rf10);
        KRNWS23.addMinibarItem(m1);
        KRNWS23.addMinibarItem(m2);
        KRNWS23.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNWS23);
        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_403"));

        Room KRNWS24 = new Room("KRNW_404", "404", "Standard", 2, "Available", h8);
        KRNWS24.addRoomFacility(rf1);
        KRNWS24.addRoomFacility(rf2);
        KRNWS24.addRoomFacility(rf3);
        KRNWS24.addRoomFacility(rf4);
        KRNWS24.addRoomFacility(rf5);
        KRNWS24.addRoomFacility(rf6);
        KRNWS24.addRoomFacility(rf7);
        KRNWS24.addRoomFacility(rf8);
        KRNWS24.addRoomFacility(rf9);
        KRNWS24.addRoomFacility(rf10);
        KRNWS24.addMinibarItem(m1);
        KRNWS24.addMinibarItem(m2);
        KRNWS24.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNWS24);
        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_404"));

        Room KRNWS25 = new Room("KRNW_405", "405", "Standard", 2, "Available", h8);
        KRNWS25.addRoomFacility(rf1);
        KRNWS25.addRoomFacility(rf2);
        KRNWS25.addRoomFacility(rf3);
        KRNWS25.addRoomFacility(rf4);
        KRNWS25.addRoomFacility(rf5);
        KRNWS25.addRoomFacility(rf6);
        KRNWS25.addRoomFacility(rf7);
        KRNWS25.addRoomFacility(rf8);
        KRNWS25.addRoomFacility(rf9);
        KRNWS25.addRoomFacility(rf10);
        KRNWS25.addMinibarItem(m1);
        KRNWS25.addMinibarItem(m2);
        KRNWS25.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNWS25);
        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_405"));

        Room KRNWS26 = new Room("KRNW_406", "406", "Standard", 2, "Available", h8);
        KRNWS26.addRoomFacility(rf1);
        KRNWS26.addRoomFacility(rf2);
        KRNWS26.addRoomFacility(rf3);
        KRNWS26.addRoomFacility(rf4);
        KRNWS26.addRoomFacility(rf5);
        KRNWS26.addRoomFacility(rf6);
        KRNWS26.addRoomFacility(rf7);
        KRNWS26.addRoomFacility(rf8);
        KRNWS26.addRoomFacility(rf9);
        KRNWS26.addRoomFacility(rf10);
        KRNWS26.addMinibarItem(m1);
        KRNWS26.addMinibarItem(m2);
        KRNWS26.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNWS26);
        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_406"));

        Room KRNWS31 = new Room("KRNW_501", "501", "Standard", 2, "Occupied", h8);
        KRNWS31.addRoomFacility(rf1);
        KRNWS31.addRoomFacility(rf2);
        KRNWS31.addRoomFacility(rf3);
        KRNWS31.addRoomFacility(rf4);
        KRNWS31.addRoomFacility(rf5);
        KRNWS31.addRoomFacility(rf6);
        KRNWS31.addRoomFacility(rf7);
        KRNWS31.addRoomFacility(rf8);
        KRNWS31.addRoomFacility(rf9);
        KRNWS31.addRoomFacility(rf10);
        KRNWS31.addMinibarItem(m1);
        KRNWS31.addMinibarItem(m2);
        KRNWS31.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNWS31);
        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_501"));

        Room KRNWS32 = new Room("KRNW_502", "502", "Standard", 2, "Available", h8);
        KRNWS32.addRoomFacility(rf1);
        KRNWS32.addRoomFacility(rf2);
        KRNWS32.addRoomFacility(rf3);
        KRNWS32.addRoomFacility(rf4);
        KRNWS32.addRoomFacility(rf5);
        KRNWS32.addRoomFacility(rf6);
        KRNWS32.addRoomFacility(rf7);
        KRNWS32.addRoomFacility(rf8);
        KRNWS32.addRoomFacility(rf9);
        KRNWS32.addRoomFacility(rf10);
        KRNWS32.addMinibarItem(m1);
        KRNWS32.addMinibarItem(m2);
        KRNWS32.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNWS32);
        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_502"));

        Room KRNWS33 = new Room("KRNW_503", "503", "Standard", 2, "Available", h8);
        KRNWS33.addRoomFacility(rf1);
        KRNWS33.addRoomFacility(rf2);
        KRNWS33.addRoomFacility(rf3);
        KRNWS33.addRoomFacility(rf4);
        KRNWS33.addRoomFacility(rf5);
        KRNWS33.addRoomFacility(rf6);
        KRNWS33.addRoomFacility(rf7);
        KRNWS33.addRoomFacility(rf8);
        KRNWS33.addRoomFacility(rf9);
        KRNWS33.addRoomFacility(rf10);
        KRNWS33.addMinibarItem(m1);
        KRNWS33.addMinibarItem(m2);
        KRNWS33.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNWS33);
        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_503"));

        Room KRNWS34 = new Room("KRNW_504", "504", "Standard", 2, "Available", h8);
        KRNWS34.addRoomFacility(rf1);
        KRNWS34.addRoomFacility(rf2);
        KRNWS34.addRoomFacility(rf3);
        KRNWS34.addRoomFacility(rf4);
        KRNWS34.addRoomFacility(rf5);
        KRNWS34.addRoomFacility(rf6);
        KRNWS34.addRoomFacility(rf7);
        KRNWS34.addRoomFacility(rf8);
        KRNWS34.addRoomFacility(rf9);
        KRNWS34.addRoomFacility(rf10);
        KRNWS34.addMinibarItem(m1);
        KRNWS34.addMinibarItem(m2);
        KRNWS34.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNWS34);
        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_504"));

        Room KRNWS35 = new Room("KRNW_505", "505", "Standard", 2, "Unavailable", h8);
        KRNWS35.addRoomFacility(rf1);
        KRNWS35.addRoomFacility(rf2);
        KRNWS35.addRoomFacility(rf3);
        KRNWS35.addRoomFacility(rf4);
        KRNWS35.addRoomFacility(rf5);
        KRNWS35.addRoomFacility(rf6);
        KRNWS35.addRoomFacility(rf7);
        KRNWS35.addRoomFacility(rf8);
        KRNWS35.addRoomFacility(rf9);
        KRNWS35.addRoomFacility(rf10);
        KRNWS35.addMinibarItem(m1);
        KRNWS35.addMinibarItem(m2);
        KRNWS35.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNWS35);
        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_505"));

        Room KRNWS36 = new Room("KRNW_506", "506", "Standard", 2, "Occupied", h8);
        KRNWS36.addRoomFacility(rf1);
        KRNWS36.addRoomFacility(rf2);
        KRNWS36.addRoomFacility(rf3);
        KRNWS36.addRoomFacility(rf4);
        KRNWS36.addRoomFacility(rf5);
        KRNWS36.addRoomFacility(rf6);
        KRNWS36.addRoomFacility(rf7);
        KRNWS36.addRoomFacility(rf8);
        KRNWS36.addRoomFacility(rf9);
        KRNWS36.addRoomFacility(rf10);
        KRNWS36.addMinibarItem(m1);
        KRNWS36.addMinibarItem(m2);
        KRNWS36.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNWS36);
        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_506"));

        Room KRNWS41 = new Room("KRNW_601", "601", "Standard", 2, "Available", h8);
        KRNWS41.addRoomFacility(rf1);
        KRNWS41.addRoomFacility(rf2);
        KRNWS41.addRoomFacility(rf3);
        KRNWS41.addRoomFacility(rf4);
        KRNWS41.addRoomFacility(rf5);
        KRNWS41.addRoomFacility(rf6);
        KRNWS41.addRoomFacility(rf7);
        KRNWS41.addRoomFacility(rf8);
        KRNWS41.addRoomFacility(rf9);
        KRNWS41.addRoomFacility(rf10);
        KRNWS41.addMinibarItem(m1);
        KRNWS41.addMinibarItem(m2);
        KRNWS41.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNWS41);
        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_601"));

        Room KRNWS42 = new Room("KRNW_602", "602", "Standard", 2, "Available", h8);
        KRNWS42.addRoomFacility(rf1);
        KRNWS42.addRoomFacility(rf2);
        KRNWS42.addRoomFacility(rf3);
        KRNWS42.addRoomFacility(rf4);
        KRNWS42.addRoomFacility(rf5);
        KRNWS42.addRoomFacility(rf6);
        KRNWS42.addRoomFacility(rf7);
        KRNWS42.addRoomFacility(rf8);
        KRNWS42.addRoomFacility(rf9);
        KRNWS42.addRoomFacility(rf10);
        KRNWS42.addMinibarItem(m1);
        KRNWS42.addMinibarItem(m2);
        KRNWS42.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNWS42);
        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_602"));

        Room KRNWS43 = new Room("KRNW_603", "603", "Standard", 2, "Available", h8);
        KRNWS43.addRoomFacility(rf1);
        KRNWS43.addRoomFacility(rf2);
        KRNWS43.addRoomFacility(rf3);
        KRNWS43.addRoomFacility(rf4);
        KRNWS43.addRoomFacility(rf5);
        KRNWS43.addRoomFacility(rf6);
        KRNWS43.addRoomFacility(rf7);
        KRNWS43.addRoomFacility(rf8);
        KRNWS43.addRoomFacility(rf9);
        KRNWS43.addRoomFacility(rf10);
        KRNWS43.addMinibarItem(m1);
        KRNWS43.addMinibarItem(m2);
        KRNWS43.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNWS43);
        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_603"));

        Room KRNWS44 = new Room("KRNW_604", "604", "Standard", 2, "Occupied", h8);
        KRNWS44.addRoomFacility(rf1);
        KRNWS44.addRoomFacility(rf2);
        KRNWS44.addRoomFacility(rf3);
        KRNWS44.addRoomFacility(rf4);
        KRNWS44.addRoomFacility(rf5);
        KRNWS44.addRoomFacility(rf6);
        KRNWS44.addRoomFacility(rf7);
        KRNWS44.addRoomFacility(rf8);
        KRNWS44.addRoomFacility(rf9);
        KRNWS44.addRoomFacility(rf10);
        KRNWS44.addMinibarItem(m1);
        KRNWS44.addMinibarItem(m2);
        KRNWS44.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNWS44);
        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_604"));

        Room KRNWS45 = new Room("KRNW_605", "605", "Standard", 2, "Available", h8);
        KRNWS45.addRoomFacility(rf1);
        KRNWS45.addRoomFacility(rf2);
        KRNWS45.addRoomFacility(rf3);
        KRNWS45.addRoomFacility(rf4);
        KRNWS45.addRoomFacility(rf5);
        KRNWS45.addRoomFacility(rf6);
        KRNWS45.addRoomFacility(rf7);
        KRNWS45.addRoomFacility(rf8);
        KRNWS45.addRoomFacility(rf9);
        KRNWS45.addRoomFacility(rf10);
        KRNWS45.addMinibarItem(m1);
        KRNWS45.addMinibarItem(m2);
        KRNWS45.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNWS45);
        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_605"));

        Room KRNWS46 = new Room("KRNW_606", "606", "Standard", 2, "Available", h8);
        KRNWS46.addRoomFacility(rf1);
        KRNWS46.addRoomFacility(rf2);
        KRNWS46.addRoomFacility(rf3);
        KRNWS46.addRoomFacility(rf4);
        KRNWS46.addRoomFacility(rf5);
        KRNWS46.addRoomFacility(rf6);
        KRNWS46.addRoomFacility(rf7);
        KRNWS46.addRoomFacility(rf8);
        KRNWS46.addRoomFacility(rf9);
        KRNWS46.addRoomFacility(rf10);
        KRNWS46.addMinibarItem(m1);
        KRNWS46.addMinibarItem(m2);
        KRNWS46.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRNWS46);
        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_606"));

        Room KRNW_1001 = new Room("KRNW_1001", "1001", "Deluxe", 3, "Unavailable", h8);

        KRNW_1001.addRoomFacility(rf1);
        KRNW_1001.addRoomFacility(rf2);
        KRNW_1001.addRoomFacility(rf3);
        KRNW_1001.addRoomFacility(rf4);
        KRNW_1001.addRoomFacility(rf5);
        KRNW_1001.addRoomFacility(rf6);
        KRNW_1001.addRoomFacility(rf7);
        KRNW_1001.addRoomFacility(rf8);
        KRNW_1001.addRoomFacility(rf9);
        KRNW_1001.addRoomFacility(rf10);
        KRNW_1001.addRoomFacility(rf11);
        KRNW_1001.addRoomFacility(rf12);
        KRNW_1001.addRoomFacility(rf13);

        KRNW_1001.addMinibarItem(m1);
        KRNW_1001.addMinibarItem(m2);
        KRNW_1001.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNW_1001);

        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_1001"));

        Room KRNW_1002 = new Room("KRNW_1002", "1002", "Deluxe", 3, "Available", h8);

        KRNW_1002.addRoomFacility(rf1);
        KRNW_1002.addRoomFacility(rf2);
        KRNW_1002.addRoomFacility(rf3);
        KRNW_1002.addRoomFacility(rf4);
        KRNW_1002.addRoomFacility(rf5);
        KRNW_1002.addRoomFacility(rf6);
        KRNW_1002.addRoomFacility(rf7);
        KRNW_1002.addRoomFacility(rf8);
        KRNW_1002.addRoomFacility(rf9);
        KRNW_1002.addRoomFacility(rf10);
        KRNW_1002.addRoomFacility(rf11);
        KRNW_1002.addRoomFacility(rf12);
        KRNW_1002.addRoomFacility(rf13);

        KRNW_1002.addMinibarItem(m1);
        KRNW_1002.addMinibarItem(m2);
        KRNW_1002.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNW_1002);

        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_1002"));

        Room KRNW_1003 = new Room("KRNW_1003", "1003", "Deluxe", 3, "Available", h8);

        KRNW_1003.addRoomFacility(rf1);
        KRNW_1003.addRoomFacility(rf2);
        KRNW_1003.addRoomFacility(rf3);
        KRNW_1003.addRoomFacility(rf4);
        KRNW_1003.addRoomFacility(rf5);
        KRNW_1003.addRoomFacility(rf6);
        KRNW_1003.addRoomFacility(rf7);
        KRNW_1003.addRoomFacility(rf8);
        KRNW_1003.addRoomFacility(rf9);
        KRNW_1003.addRoomFacility(rf10);
        KRNW_1003.addRoomFacility(rf11);
        KRNW_1003.addRoomFacility(rf12);
        KRNW_1003.addRoomFacility(rf13);

        KRNW_1003.addMinibarItem(m1);
        KRNW_1003.addMinibarItem(m2);
        KRNW_1003.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNW_1003);

        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_1003"));

        Room KRNW_1101 = new Room("KRNW_1101", "1101", "Deluxe", 3, "Occupied", h8);

        KRNW_1101.addRoomFacility(rf1);
        KRNW_1101.addRoomFacility(rf2);
        KRNW_1101.addRoomFacility(rf3);
        KRNW_1101.addRoomFacility(rf4);
        KRNW_1101.addRoomFacility(rf5);
        KRNW_1101.addRoomFacility(rf6);
        KRNW_1101.addRoomFacility(rf7);
        KRNW_1101.addRoomFacility(rf8);
        KRNW_1101.addRoomFacility(rf9);
        KRNW_1101.addRoomFacility(rf10);
        KRNW_1101.addRoomFacility(rf11);
        KRNW_1101.addRoomFacility(rf12);
        KRNW_1101.addRoomFacility(rf13);

        KRNW_1101.addMinibarItem(m1);
        KRNW_1101.addMinibarItem(m2);
        KRNW_1101.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNW_1101);

        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_1101"));

        Room KRNW_1102 = new Room("KRNW_1102", "1102", "Deluxe", 3, "Available", h8);

        KRNW_1102.addRoomFacility(rf1);
        KRNW_1102.addRoomFacility(rf2);
        KRNW_1102.addRoomFacility(rf3);
        KRNW_1102.addRoomFacility(rf4);
        KRNW_1102.addRoomFacility(rf5);
        KRNW_1102.addRoomFacility(rf6);
        KRNW_1102.addRoomFacility(rf7);
        KRNW_1102.addRoomFacility(rf8);
        KRNW_1102.addRoomFacility(rf9);
        KRNW_1102.addRoomFacility(rf10);
        KRNW_1102.addRoomFacility(rf11);
        KRNW_1102.addRoomFacility(rf12);
        KRNW_1102.addRoomFacility(rf13);

        KRNW_1102.addMinibarItem(m1);
        KRNW_1102.addMinibarItem(m2);
        KRNW_1102.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNW_1102);

        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_1102"));

        Room KRNW_1103 = new Room("KRNW_1103", "1103", "Deluxe", 3, "Available", h8);

        KRNW_1103.addRoomFacility(rf1);
        KRNW_1103.addRoomFacility(rf2);
        KRNW_1103.addRoomFacility(rf3);
        KRNW_1103.addRoomFacility(rf4);
        KRNW_1103.addRoomFacility(rf5);
        KRNW_1103.addRoomFacility(rf6);
        KRNW_1103.addRoomFacility(rf7);
        KRNW_1103.addRoomFacility(rf8);
        KRNW_1103.addRoomFacility(rf9);
        KRNW_1103.addRoomFacility(rf10);
        KRNW_1103.addRoomFacility(rf11);
        KRNW_1103.addRoomFacility(rf12);
        KRNW_1103.addRoomFacility(rf13);

        KRNW_1103.addMinibarItem(m1);
        KRNW_1103.addMinibarItem(m2);
        KRNW_1103.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNW_1103);

        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_1103"));

        Room KRNW_701 = new Room("KRNW_701", "701", "Premium", 4, "Unavailable", h8);

        KRNW_701.addRoomFacility(rf1);
        KRNW_701.addRoomFacility(rf2);
        KRNW_701.addRoomFacility(rf3);
        KRNW_701.addRoomFacility(rf4);
        KRNW_701.addRoomFacility(rf5);
        KRNW_701.addRoomFacility(rf6);
        KRNW_701.addRoomFacility(rf7);
        KRNW_701.addRoomFacility(rf8);
        KRNW_701.addRoomFacility(rf9);
        KRNW_701.addRoomFacility(rf10);
        KRNW_701.addRoomFacility(rf11);
        KRNW_701.addRoomFacility(rf12);
        KRNW_701.addRoomFacility(rf13);
        KRNW_701.addRoomFacility(rf14);
        KRNW_701.addRoomFacility(rf15);

        KRNW_701.addMinibarItem(m1);
        KRNW_701.addMinibarItem(m2);
        KRNW_701.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNW_701);

        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_701"));

        Room KRNW_702 = new Room("KRNW_702", "702", "Premium", 4, "Available", h8);

        KRNW_702.addRoomFacility(rf1);
        KRNW_702.addRoomFacility(rf2);
        KRNW_702.addRoomFacility(rf3);
        KRNW_702.addRoomFacility(rf4);
        KRNW_702.addRoomFacility(rf5);
        KRNW_702.addRoomFacility(rf6);
        KRNW_702.addRoomFacility(rf7);
        KRNW_702.addRoomFacility(rf8);
        KRNW_702.addRoomFacility(rf9);
        KRNW_702.addRoomFacility(rf10);
        KRNW_702.addRoomFacility(rf11);
        KRNW_702.addRoomFacility(rf12);
        KRNW_702.addRoomFacility(rf13);
        KRNW_702.addRoomFacility(rf14);
        KRNW_702.addRoomFacility(rf15);

        KRNW_702.addMinibarItem(m1);
        KRNW_702.addMinibarItem(m2);
        KRNW_702.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNW_702);

        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_702"));

        Room KRNW_703 = new Room("KRNW_703", "703", "Premium", 4, "Occupied", h8);

        KRNW_703.addRoomFacility(rf1);
        KRNW_703.addRoomFacility(rf2);
        KRNW_703.addRoomFacility(rf3);
        KRNW_703.addRoomFacility(rf4);
        KRNW_703.addRoomFacility(rf5);
        KRNW_703.addRoomFacility(rf6);
        KRNW_703.addRoomFacility(rf7);
        KRNW_703.addRoomFacility(rf8);
        KRNW_703.addRoomFacility(rf9);
        KRNW_703.addRoomFacility(rf10);
        KRNW_703.addRoomFacility(rf11);
        KRNW_703.addRoomFacility(rf12);
        KRNW_703.addRoomFacility(rf13);
        KRNW_703.addRoomFacility(rf14);
        KRNW_703.addRoomFacility(rf15);

        KRNW_703.addMinibarItem(m1);
        KRNW_703.addMinibarItem(m2);
        KRNW_703.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNW_703);

        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_703"));

        Room KRNW_704 = new Room("KRNW_704", "704", "Premium", 4, "Available", h8);

        KRNW_704.addRoomFacility(rf1);
        KRNW_704.addRoomFacility(rf2);
        KRNW_704.addRoomFacility(rf3);
        KRNW_704.addRoomFacility(rf4);
        KRNW_704.addRoomFacility(rf5);
        KRNW_704.addRoomFacility(rf6);
        KRNW_704.addRoomFacility(rf7);
        KRNW_704.addRoomFacility(rf8);
        KRNW_704.addRoomFacility(rf9);
        KRNW_704.addRoomFacility(rf10);
        KRNW_704.addRoomFacility(rf11);
        KRNW_704.addRoomFacility(rf12);
        KRNW_704.addRoomFacility(rf13);
        KRNW_704.addRoomFacility(rf14);
        KRNW_704.addRoomFacility(rf15);

        KRNW_704.addMinibarItem(m1);
        KRNW_704.addMinibarItem(m2);
        KRNW_704.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNW_704);

        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_704"));

        Room KRNW_801 = new Room("KRNW_801", "801", "Premium", 4, "Available", h8);

        KRNW_801.addRoomFacility(rf1);
        KRNW_801.addRoomFacility(rf2);
        KRNW_801.addRoomFacility(rf3);
        KRNW_801.addRoomFacility(rf4);
        KRNW_801.addRoomFacility(rf5);
        KRNW_801.addRoomFacility(rf6);
        KRNW_801.addRoomFacility(rf7);
        KRNW_801.addRoomFacility(rf8);
        KRNW_801.addRoomFacility(rf9);
        KRNW_801.addRoomFacility(rf10);
        KRNW_801.addRoomFacility(rf11);
        KRNW_801.addRoomFacility(rf12);
        KRNW_801.addRoomFacility(rf13);
        KRNW_801.addRoomFacility(rf14);
        KRNW_801.addRoomFacility(rf15);

        KRNW_801.addMinibarItem(m1);
        KRNW_801.addMinibarItem(m2);
        KRNW_801.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNW_801);

        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_801"));

        Room KRNW_802 = new Room("KRNW_802", "802", "Premium", 4, "Unavailable", h8);

        KRNW_802.addRoomFacility(rf1);
        KRNW_802.addRoomFacility(rf2);
        KRNW_802.addRoomFacility(rf3);
        KRNW_802.addRoomFacility(rf4);
        KRNW_802.addRoomFacility(rf5);
        KRNW_802.addRoomFacility(rf6);
        KRNW_802.addRoomFacility(rf7);
        KRNW_802.addRoomFacility(rf8);
        KRNW_802.addRoomFacility(rf9);
        KRNW_802.addRoomFacility(rf10);
        KRNW_802.addRoomFacility(rf11);
        KRNW_802.addRoomFacility(rf12);
        KRNW_802.addRoomFacility(rf13);
        KRNW_802.addRoomFacility(rf14);
        KRNW_802.addRoomFacility(rf15);

        KRNW_802.addMinibarItem(m1);
        KRNW_802.addMinibarItem(m2);
        KRNW_802.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNW_802);

        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_802"));

        Room KRNW_803 = new Room("KRNW_803", "803", "Premium", 4, "Occupied", h8);

        KRNW_803.addRoomFacility(rf1);
        KRNW_803.addRoomFacility(rf2);
        KRNW_803.addRoomFacility(rf3);
        KRNW_803.addRoomFacility(rf4);
        KRNW_803.addRoomFacility(rf5);
        KRNW_803.addRoomFacility(rf6);
        KRNW_803.addRoomFacility(rf7);
        KRNW_803.addRoomFacility(rf8);
        KRNW_803.addRoomFacility(rf9);
        KRNW_803.addRoomFacility(rf10);
        KRNW_803.addRoomFacility(rf11);
        KRNW_803.addRoomFacility(rf12);
        KRNW_803.addRoomFacility(rf13);
        KRNW_803.addRoomFacility(rf14);
        KRNW_803.addRoomFacility(rf15);

        KRNW_803.addMinibarItem(m1);
        KRNW_803.addMinibarItem(m2);
        KRNW_803.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNW_803);

        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_803"));

        Room KRNW_804 = new Room("KRNW_804", "804", "Premium", 4, "Available", h8);

        KRNW_804.addRoomFacility(rf1);
        KRNW_804.addRoomFacility(rf2);
        KRNW_804.addRoomFacility(rf3);
        KRNW_804.addRoomFacility(rf4);
        KRNW_804.addRoomFacility(rf5);
        KRNW_804.addRoomFacility(rf6);
        KRNW_804.addRoomFacility(rf7);
        KRNW_804.addRoomFacility(rf8);
        KRNW_804.addRoomFacility(rf9);
        KRNW_804.addRoomFacility(rf10);
        KRNW_804.addRoomFacility(rf11);
        KRNW_804.addRoomFacility(rf12);
        KRNW_804.addRoomFacility(rf13);
        KRNW_804.addRoomFacility(rf14);
        KRNW_804.addRoomFacility(rf15);

        KRNW_804.addMinibarItem(m1);
        KRNW_804.addMinibarItem(m2);
        KRNW_804.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNW_804);

        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_804"));

        Room KRNW_901 = new Room("KRNW_901", "901", "Premium", 4, "Available", h8);

        KRNW_901.addRoomFacility(rf1);
        KRNW_901.addRoomFacility(rf2);
        KRNW_901.addRoomFacility(rf3);
        KRNW_901.addRoomFacility(rf4);
        KRNW_901.addRoomFacility(rf5);
        KRNW_901.addRoomFacility(rf6);
        KRNW_901.addRoomFacility(rf7);
        KRNW_901.addRoomFacility(rf8);
        KRNW_901.addRoomFacility(rf9);
        KRNW_901.addRoomFacility(rf10);
        KRNW_901.addRoomFacility(rf11);
        KRNW_901.addRoomFacility(rf12);
        KRNW_901.addRoomFacility(rf13);
        KRNW_901.addRoomFacility(rf14);
        KRNW_901.addRoomFacility(rf15);

        KRNW_901.addMinibarItem(m1);
        KRNW_901.addMinibarItem(m2);
        KRNW_901.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNW_901);

        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_901"));

        Room KRNW_902 = new Room("KRNW_902", "902", "Premium", 4, "Occupied", h8);

        KRNW_902.addRoomFacility(rf1);
        KRNW_902.addRoomFacility(rf2);
        KRNW_902.addRoomFacility(rf3);
        KRNW_902.addRoomFacility(rf4);
        KRNW_902.addRoomFacility(rf5);
        KRNW_902.addRoomFacility(rf6);
        KRNW_902.addRoomFacility(rf7);
        KRNW_902.addRoomFacility(rf8);
        KRNW_902.addRoomFacility(rf9);
        KRNW_902.addRoomFacility(rf10);
        KRNW_902.addRoomFacility(rf11);
        KRNW_902.addRoomFacility(rf12);
        KRNW_902.addRoomFacility(rf13);
        KRNW_902.addRoomFacility(rf14);
        KRNW_902.addRoomFacility(rf15);

        KRNW_902.addMinibarItem(m1);
        KRNW_902.addMinibarItem(m2);
        KRNW_902.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNW_902);

        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_902"));

        Room KRNW_903 = new Room("KRNW_903", "903", "Premium", 4, "Unavailable", h8);

        KRNW_903.addRoomFacility(rf1);
        KRNW_903.addRoomFacility(rf2);
        KRNW_903.addRoomFacility(rf3);
        KRNW_903.addRoomFacility(rf4);
        KRNW_903.addRoomFacility(rf5);
        KRNW_903.addRoomFacility(rf6);
        KRNW_903.addRoomFacility(rf7);
        KRNW_903.addRoomFacility(rf8);
        KRNW_903.addRoomFacility(rf9);
        KRNW_903.addRoomFacility(rf10);
        KRNW_903.addRoomFacility(rf11);
        KRNW_903.addRoomFacility(rf12);
        KRNW_903.addRoomFacility(rf13);
        KRNW_903.addRoomFacility(rf14);
        KRNW_903.addRoomFacility(rf15);

        KRNW_903.addMinibarItem(m1);
        KRNW_903.addMinibarItem(m2);
        KRNW_903.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNW_903);

        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_903"));

        Room KRNW_904 = new Room("KRNW_904", "904", "Premium", 4, "Available", h8);

        KRNW_904.addRoomFacility(rf1);
        KRNW_904.addRoomFacility(rf2);
        KRNW_904.addRoomFacility(rf3);
        KRNW_904.addRoomFacility(rf4);
        KRNW_904.addRoomFacility(rf5);
        KRNW_904.addRoomFacility(rf6);
        KRNW_904.addRoomFacility(rf7);
        KRNW_904.addRoomFacility(rf8);
        KRNW_904.addRoomFacility(rf9);
        KRNW_904.addRoomFacility(rf10);
        KRNW_904.addRoomFacility(rf11);
        KRNW_904.addRoomFacility(rf12);
        KRNW_904.addRoomFacility(rf13);
        KRNW_904.addRoomFacility(rf14);
        KRNW_904.addRoomFacility(rf15);

        KRNW_904.addMinibarItem(m1);
        KRNW_904.addMinibarItem(m2);
        KRNW_904.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRNW_904);

        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_904"));

        Room KRNW_1201 = new Room("KRNW_1201", "1201", "Suite", 4, "Occupied", h8);

        KRNW_1201.addRoomFacility(rf1);
        KRNW_1201.addRoomFacility(rf2);
        KRNW_1201.addRoomFacility(rf3);
        KRNW_1201.addRoomFacility(rf4);
        KRNW_1201.addRoomFacility(rf5);
        KRNW_1201.addRoomFacility(rf6);
        KRNW_1201.addRoomFacility(rf7);
        KRNW_1201.addRoomFacility(rf8);
        KRNW_1201.addRoomFacility(rf9);
        KRNW_1201.addRoomFacility(rf10);
        KRNW_1201.addRoomFacility(rf11);
        KRNW_1201.addRoomFacility(rf12);
        KRNW_1201.addRoomFacility(rf13);
        KRNW_1201.addRoomFacility(rf14);
        KRNW_1201.addRoomFacility(rf15);
        KRNW_1201.addRoomFacility(rf16);
        KRNW_1201.addRoomFacility(rf17);
        KRNW_1201.addRoomFacility(rf18);
        KRNW_1201.addRoomFacility(rf19);

        KRNW_1201.addMinibarItem(m1);
        KRNW_1201.addMinibarItem(m2);
        KRNW_1201.addMinibarItem(m3);
        KRNW_1201.addMinibarItem(m4);
        KRNW_1201.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRNW_1201);

        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_1201"));

        Room KRNW_1202 = new Room("KRNW_1202", "1202", "Suite", 4, "Available", h8);

        KRNW_1202.addRoomFacility(rf1);
        KRNW_1202.addRoomFacility(rf2);
        KRNW_1202.addRoomFacility(rf3);
        KRNW_1202.addRoomFacility(rf4);
        KRNW_1202.addRoomFacility(rf5);
        KRNW_1202.addRoomFacility(rf6);
        KRNW_1202.addRoomFacility(rf7);
        KRNW_1202.addRoomFacility(rf8);
        KRNW_1202.addRoomFacility(rf9);
        KRNW_1202.addRoomFacility(rf10);
        KRNW_1202.addRoomFacility(rf11);
        KRNW_1202.addRoomFacility(rf12);
        KRNW_1202.addRoomFacility(rf13);
        KRNW_1202.addRoomFacility(rf14);
        KRNW_1202.addRoomFacility(rf15);
        KRNW_1202.addRoomFacility(rf16);
        KRNW_1202.addRoomFacility(rf17);
        KRNW_1202.addRoomFacility(rf18);
        KRNW_1202.addRoomFacility(rf19);

        KRNW_1202.addMinibarItem(m1);
        KRNW_1202.addMinibarItem(m2);
        KRNW_1202.addMinibarItem(m3);
        KRNW_1202.addMinibarItem(m4);
        KRNW_1202.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRNW_1202);

        h8.addRoom(roomSessionLocal.getRoomByName("KRNW_1202"));

        em.flush();
    }

    public void initializeKRSERoom() throws NoResultException {
        Hotel h9 = hotelSessionLocal.getHotelByName("Kent Ridge South East");
        MinibarItem m1 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Potato Chips");
        MinibarItem m2 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Chocolate");
        MinibarItem m3 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Mineral Water");
        MinibarItem m4 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Coke");
        MinibarItem m5 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Wine");
        RoomFacility rf1 = roomFacilitySessionLocal.getRoomFacilityByName("Wifi");
        RoomFacility rf2 = roomFacilitySessionLocal.getRoomFacilityByName("Minibar");
        RoomFacility rf3 = roomFacilitySessionLocal.getRoomFacilityByName("Aircon");
        RoomFacility rf4 = roomFacilitySessionLocal.getRoomFacilityByName("Cable TV");
        RoomFacility rf5 = roomFacilitySessionLocal.getRoomFacilityByName("Telephone");
        RoomFacility rf6 = roomFacilitySessionLocal.getRoomFacilityByName("Water Heater");
        RoomFacility rf7 = roomFacilitySessionLocal.getRoomFacilityByName("Bathroom with Shower Head");
        RoomFacility rf8 = roomFacilitySessionLocal.getRoomFacilityByName("HairDryer");
        RoomFacility rf9 = roomFacilitySessionLocal.getRoomFacilityByName("Safe Deposit Box");
        RoomFacility rf10 = roomFacilitySessionLocal.getRoomFacilityByName("Water Kettle with complimentary teas/coffee");
        RoomFacility rf11 = roomFacilitySessionLocal.getRoomFacilityByName("Room with Windows");
        RoomFacility rf12 = roomFacilitySessionLocal.getRoomFacilityByName("Bathtub");
        RoomFacility rf13 = roomFacilitySessionLocal.getRoomFacilityByName("Mini Fridge");
        RoomFacility rf14 = roomFacilitySessionLocal.getRoomFacilityByName("Coffee Machine");
        RoomFacility rf15 = roomFacilitySessionLocal.getRoomFacilityByName("Balcony");
        RoomFacility rf16 = roomFacilitySessionLocal.getRoomFacilityByName("Microwave");
        RoomFacility rf17 = roomFacilitySessionLocal.getRoomFacilityByName("Gaming Console");
        RoomFacility rf18 = roomFacilitySessionLocal.getRoomFacilityByName("Entertainment System");
        RoomFacility rf19 = roomFacilitySessionLocal.getRoomFacilityByName("Blackout Curtains");
        RoomFacility rf20 = roomFacilitySessionLocal.getRoomFacilityByName("High Ceiling");
        RoomFacility rf21 = roomFacilitySessionLocal.getRoomFacilityByName("Jaccuzi");
        RoomFacility rf22 = roomFacilitySessionLocal.getRoomFacilityByName("Kitchen");

        FunctionRoom KRSEFR1 = new FunctionRoom("KRSEFR1", 20, "Available", 20000.00, h9);
        FunctionRoom KRSEFR2 = new FunctionRoom("KRSEFR2", 100, "Available", 100000.00, h9);
        FunctionRoom KRSEFR3 = new FunctionRoom("KRSEFR3", 50, "Available", 50000.00, h9);
        FunctionRoom KRSEFR4 = new FunctionRoom("KRSEFR4", 70, "Available", 70000.00, h9);
        FunctionRoom KRSEFR5 = new FunctionRoom("KRSEFR5", 80, "Available", 80000.00, h9);

        functionRoomSessionLocal.createFunctionRoom(KRSEFR1);
        functionRoomSessionLocal.createFunctionRoom(KRSEFR2);
        functionRoomSessionLocal.createFunctionRoom(KRSEFR3);
        functionRoomSessionLocal.createFunctionRoom(KRSEFR4);
        functionRoomSessionLocal.createFunctionRoom(KRSEFR5);

        h9.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRSEFR1"));
        h9.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRSEFR2"));
        h9.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRSEFR3"));
        h9.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRSEFR4"));
        h9.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRSEFR5"));

        Room KRSES1 = new Room("KRSE_201", "201", "Standard", 2, "Available", h9);
        KRSES1.addRoomFacility(rf1);
        KRSES1.addRoomFacility(rf2);
        KRSES1.addRoomFacility(rf3);
        KRSES1.addRoomFacility(rf4);
        KRSES1.addRoomFacility(rf5);
        KRSES1.addRoomFacility(rf6);
        KRSES1.addRoomFacility(rf7);
        KRSES1.addRoomFacility(rf8);
        KRSES1.addRoomFacility(rf9);
        KRSES1.addRoomFacility(rf10);
        KRSES1.addMinibarItem(m1);
        KRSES1.addMinibarItem(m2);
        KRSES1.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSES1);
        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_201"));

        Room KRSES2 = new Room("KRSE_202", "202", "Standard", 2, "Occupied", h9);
        KRSES2.addRoomFacility(rf1);
        KRSES2.addRoomFacility(rf2);
        KRSES2.addRoomFacility(rf3);
        KRSES2.addRoomFacility(rf4);
        KRSES2.addRoomFacility(rf5);
        KRSES2.addRoomFacility(rf6);
        KRSES2.addRoomFacility(rf7);
        KRSES2.addRoomFacility(rf8);
        KRSES2.addRoomFacility(rf9);
        KRSES2.addRoomFacility(rf10);
        KRSES2.addMinibarItem(m1);
        KRSES2.addMinibarItem(m2);
        KRSES2.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSES2);
        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_202"));

        Room KRSES3 = new Room("KRSE_203", "203", "Standard", 2, "Available", h9);
        KRSES3.addRoomFacility(rf1);
        KRSES3.addRoomFacility(rf2);
        KRSES3.addRoomFacility(rf3);
        KRSES3.addRoomFacility(rf4);
        KRSES3.addRoomFacility(rf5);
        KRSES3.addRoomFacility(rf6);
        KRSES3.addRoomFacility(rf7);
        KRSES3.addRoomFacility(rf8);
        KRSES3.addRoomFacility(rf9);
        KRSES3.addRoomFacility(rf10);
        KRSES3.addMinibarItem(m1);
        KRSES3.addMinibarItem(m2);
        KRSES3.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSES3);
        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_203"));

        Room KRSES4 = new Room("KRSE_204", "204", "Standard", 2, "Available", h9);
        KRSES4.addRoomFacility(rf1);
        KRSES4.addRoomFacility(rf2);
        KRSES4.addRoomFacility(rf3);
        KRSES4.addRoomFacility(rf4);
        KRSES4.addRoomFacility(rf5);
        KRSES4.addRoomFacility(rf6);
        KRSES4.addRoomFacility(rf7);
        KRSES4.addRoomFacility(rf8);
        KRSES4.addRoomFacility(rf9);
        KRSES4.addRoomFacility(rf10);
        KRSES4.addMinibarItem(m1);
        KRSES4.addMinibarItem(m2);
        KRSES4.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSES4);
        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_204"));

        Room KRSES5 = new Room("KRSE_205", "205", "Standard", 2, "Unavailable", h9);
        KRSES5.addRoomFacility(rf1);
        KRSES5.addRoomFacility(rf2);
        KRSES5.addRoomFacility(rf3);
        KRSES5.addRoomFacility(rf4);
        KRSES5.addRoomFacility(rf5);
        KRSES5.addRoomFacility(rf6);
        KRSES5.addRoomFacility(rf7);
        KRSES5.addRoomFacility(rf8);
        KRSES5.addRoomFacility(rf9);
        KRSES5.addRoomFacility(rf10);
        KRSES5.addMinibarItem(m1);
        KRSES5.addMinibarItem(m2);
        KRSES5.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSES5);
        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_205"));

        Room KRSES6 = new Room("KRSE_206", "206", "Standard", 2, "Available", h9);
        KRSES6.addRoomFacility(rf1);
        KRSES6.addRoomFacility(rf2);
        KRSES6.addRoomFacility(rf3);
        KRSES6.addRoomFacility(rf4);
        KRSES6.addRoomFacility(rf5);
        KRSES6.addRoomFacility(rf6);
        KRSES6.addRoomFacility(rf7);
        KRSES6.addRoomFacility(rf8);
        KRSES6.addRoomFacility(rf9);
        KRSES6.addRoomFacility(rf10);
        KRSES6.addMinibarItem(m1);
        KRSES6.addMinibarItem(m2);
        KRSES6.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSES6);
        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_206"));

        Room KRSES11 = new Room("KRSE_301", "301", "Standard", 2, "Available", h9);
        KRSES11.addRoomFacility(rf1);
        KRSES11.addRoomFacility(rf2);
        KRSES11.addRoomFacility(rf3);
        KRSES11.addRoomFacility(rf4);
        KRSES11.addRoomFacility(rf5);
        KRSES11.addRoomFacility(rf6);
        KRSES11.addRoomFacility(rf7);
        KRSES11.addRoomFacility(rf8);
        KRSES11.addRoomFacility(rf9);
        KRSES11.addRoomFacility(rf10);
        KRSES11.addMinibarItem(m1);
        KRSES11.addMinibarItem(m2);
        KRSES11.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSES11);
        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_301"));

        Room KRSES12 = new Room("KRSE_302", "302", "Standard", 2, "Occupied", h9);
        KRSES12.addRoomFacility(rf1);
        KRSES12.addRoomFacility(rf2);
        KRSES12.addRoomFacility(rf3);
        KRSES12.addRoomFacility(rf4);
        KRSES12.addRoomFacility(rf5);
        KRSES12.addRoomFacility(rf6);
        KRSES12.addRoomFacility(rf7);
        KRSES12.addRoomFacility(rf8);
        KRSES12.addRoomFacility(rf9);
        KRSES12.addRoomFacility(rf10);
        KRSES12.addMinibarItem(m1);
        KRSES12.addMinibarItem(m2);
        KRSES12.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSES12);
        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_302"));

        Room KRSES13 = new Room("KRSE_303", "303", "Standard", 2, "Available", h9);
        KRSES13.addRoomFacility(rf1);
        KRSES13.addRoomFacility(rf2);
        KRSES13.addRoomFacility(rf3);
        KRSES13.addRoomFacility(rf4);
        KRSES13.addRoomFacility(rf5);
        KRSES13.addRoomFacility(rf6);
        KRSES13.addRoomFacility(rf7);
        KRSES13.addRoomFacility(rf8);
        KRSES13.addRoomFacility(rf9);
        KRSES13.addRoomFacility(rf10);
        KRSES13.addMinibarItem(m1);
        KRSES13.addMinibarItem(m2);
        KRSES13.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSES13);
        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_303"));

        Room KRSES14 = new Room("KRSE_304", "304", "Standard", 2, "Available", h9);
        KRSES14.addRoomFacility(rf1);
        KRSES14.addRoomFacility(rf2);
        KRSES14.addRoomFacility(rf3);
        KRSES14.addRoomFacility(rf4);
        KRSES14.addRoomFacility(rf5);
        KRSES14.addRoomFacility(rf6);
        KRSES14.addRoomFacility(rf7);
        KRSES14.addRoomFacility(rf8);
        KRSES14.addRoomFacility(rf9);
        KRSES14.addRoomFacility(rf10);
        KRSES14.addMinibarItem(m1);
        KRSES14.addMinibarItem(m2);
        KRSES14.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSES14);
        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_304"));

        Room KRSES15 = new Room("KRSE_305", "305", "Standard", 2, "Available", h9);
        KRSES15.addRoomFacility(rf1);
        KRSES15.addRoomFacility(rf2);
        KRSES15.addRoomFacility(rf3);
        KRSES15.addRoomFacility(rf4);
        KRSES15.addRoomFacility(rf5);
        KRSES15.addRoomFacility(rf6);
        KRSES15.addRoomFacility(rf7);
        KRSES15.addRoomFacility(rf8);
        KRSES15.addRoomFacility(rf9);
        KRSES15.addRoomFacility(rf10);
        KRSES15.addMinibarItem(m1);
        KRSES15.addMinibarItem(m2);
        KRSES15.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSES15);
        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_305"));

        Room KRSES16 = new Room("KRSE_306", "306", "Standard", 2, "Available", h9);
        KRSES16.addRoomFacility(rf1);
        KRSES16.addRoomFacility(rf2);
        KRSES16.addRoomFacility(rf3);
        KRSES16.addRoomFacility(rf4);
        KRSES16.addRoomFacility(rf5);
        KRSES16.addRoomFacility(rf6);
        KRSES16.addRoomFacility(rf7);
        KRSES16.addRoomFacility(rf8);
        KRSES16.addRoomFacility(rf9);
        KRSES16.addRoomFacility(rf10);
        KRSES16.addMinibarItem(m1);
        KRSES16.addMinibarItem(m2);
        KRSES16.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSES16);
        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_306"));

        Room KRSES21 = new Room("KRSE_401", "401", "Standard", 2, "Occupied", h9);
        KRSES21.addRoomFacility(rf1);
        KRSES21.addRoomFacility(rf2);
        KRSES21.addRoomFacility(rf3);
        KRSES21.addRoomFacility(rf4);
        KRSES21.addRoomFacility(rf5);
        KRSES21.addRoomFacility(rf6);
        KRSES21.addRoomFacility(rf7);
        KRSES21.addRoomFacility(rf8);
        KRSES21.addRoomFacility(rf9);
        KRSES21.addRoomFacility(rf10);
        KRSES21.addMinibarItem(m1);
        KRSES21.addMinibarItem(m2);
        KRSES21.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSES21);
        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_401"));

        Room KRSES22 = new Room("KRSE_402", "402", "Standard", 2, "Available", h9);
        KRSES22.addRoomFacility(rf1);
        KRSES22.addRoomFacility(rf2);
        KRSES22.addRoomFacility(rf3);
        KRSES22.addRoomFacility(rf4);
        KRSES22.addRoomFacility(rf5);
        KRSES22.addRoomFacility(rf6);
        KRSES22.addRoomFacility(rf7);
        KRSES22.addRoomFacility(rf8);
        KRSES22.addRoomFacility(rf9);
        KRSES22.addRoomFacility(rf10);
        KRSES22.addMinibarItem(m1);
        KRSES22.addMinibarItem(m2);
        KRSES22.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSES22);
        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_402"));

        Room KRSES23 = new Room("KRSE_403", "403", "Standard", 2, "Available", h9);
        KRSES23.addRoomFacility(rf1);
        KRSES23.addRoomFacility(rf2);
        KRSES23.addRoomFacility(rf3);
        KRSES23.addRoomFacility(rf4);
        KRSES23.addRoomFacility(rf5);
        KRSES23.addRoomFacility(rf6);
        KRSES23.addRoomFacility(rf7);
        KRSES23.addRoomFacility(rf8);
        KRSES23.addRoomFacility(rf9);
        KRSES23.addRoomFacility(rf10);
        KRSES23.addMinibarItem(m1);
        KRSES23.addMinibarItem(m2);
        KRSES23.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSES23);
        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_403"));

        Room KRSES24 = new Room("KRSE_404", "404", "Standard", 2, "Available", h9);
        KRSES24.addRoomFacility(rf1);
        KRSES24.addRoomFacility(rf2);
        KRSES24.addRoomFacility(rf3);
        KRSES24.addRoomFacility(rf4);
        KRSES24.addRoomFacility(rf5);
        KRSES24.addRoomFacility(rf6);
        KRSES24.addRoomFacility(rf7);
        KRSES24.addRoomFacility(rf8);
        KRSES24.addRoomFacility(rf9);
        KRSES24.addRoomFacility(rf10);
        KRSES24.addMinibarItem(m1);
        KRSES24.addMinibarItem(m2);
        KRSES24.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSES24);
        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_404"));

        Room KRSES25 = new Room("KRSE_405", "405", "Standard", 2, "Available", h9);
        KRSES25.addRoomFacility(rf1);
        KRSES25.addRoomFacility(rf2);
        KRSES25.addRoomFacility(rf3);
        KRSES25.addRoomFacility(rf4);
        KRSES25.addRoomFacility(rf5);
        KRSES25.addRoomFacility(rf6);
        KRSES25.addRoomFacility(rf7);
        KRSES25.addRoomFacility(rf8);
        KRSES25.addRoomFacility(rf9);
        KRSES25.addRoomFacility(rf10);
        KRSES25.addMinibarItem(m1);
        KRSES25.addMinibarItem(m2);
        KRSES25.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSES25);
        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_405"));

        Room KRSES26 = new Room("KRSE_406", "406", "Standard", 2, "Available", h9);
        KRSES26.addRoomFacility(rf1);
        KRSES26.addRoomFacility(rf2);
        KRSES26.addRoomFacility(rf3);
        KRSES26.addRoomFacility(rf4);
        KRSES26.addRoomFacility(rf5);
        KRSES26.addRoomFacility(rf6);
        KRSES26.addRoomFacility(rf7);
        KRSES26.addRoomFacility(rf8);
        KRSES26.addRoomFacility(rf9);
        KRSES26.addRoomFacility(rf10);
        KRSES26.addMinibarItem(m1);
        KRSES26.addMinibarItem(m2);
        KRSES26.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSES26);
        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_406"));

        Room KRSES31 = new Room("KRSE_501", "501", "Standard", 2, "Occupied", h9);
        KRSES31.addRoomFacility(rf1);
        KRSES31.addRoomFacility(rf2);
        KRSES31.addRoomFacility(rf3);
        KRSES31.addRoomFacility(rf4);
        KRSES31.addRoomFacility(rf5);
        KRSES31.addRoomFacility(rf6);
        KRSES31.addRoomFacility(rf7);
        KRSES31.addRoomFacility(rf8);
        KRSES31.addRoomFacility(rf9);
        KRSES31.addRoomFacility(rf10);
        KRSES31.addMinibarItem(m1);
        KRSES31.addMinibarItem(m2);
        KRSES31.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSES31);
        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_501"));

        Room KRSES32 = new Room("KRSE_502", "502", "Standard", 2, "Available", h9);
        KRSES32.addRoomFacility(rf1);
        KRSES32.addRoomFacility(rf2);
        KRSES32.addRoomFacility(rf3);
        KRSES32.addRoomFacility(rf4);
        KRSES32.addRoomFacility(rf5);
        KRSES32.addRoomFacility(rf6);
        KRSES32.addRoomFacility(rf7);
        KRSES32.addRoomFacility(rf8);
        KRSES32.addRoomFacility(rf9);
        KRSES32.addRoomFacility(rf10);
        KRSES32.addMinibarItem(m1);
        KRSES32.addMinibarItem(m2);
        KRSES32.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSES32);
        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_502"));

        Room KRSES33 = new Room("KRSE_503", "503", "Standard", 2, "Unavailable", h9);
        KRSES33.addRoomFacility(rf1);
        KRSES33.addRoomFacility(rf2);
        KRSES33.addRoomFacility(rf3);
        KRSES33.addRoomFacility(rf4);
        KRSES33.addRoomFacility(rf5);
        KRSES33.addRoomFacility(rf6);
        KRSES33.addRoomFacility(rf7);
        KRSES33.addRoomFacility(rf8);
        KRSES33.addRoomFacility(rf9);
        KRSES33.addRoomFacility(rf10);
        KRSES33.addMinibarItem(m1);
        KRSES33.addMinibarItem(m2);
        KRSES33.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSES33);
        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_503"));

        Room KRSES34 = new Room("KRSE_504", "504", "Standard", 2, "Available", h9);
        KRSES34.addRoomFacility(rf1);
        KRSES34.addRoomFacility(rf2);
        KRSES34.addRoomFacility(rf3);
        KRSES34.addRoomFacility(rf4);
        KRSES34.addRoomFacility(rf5);
        KRSES34.addRoomFacility(rf6);
        KRSES34.addRoomFacility(rf7);
        KRSES34.addRoomFacility(rf8);
        KRSES34.addRoomFacility(rf9);
        KRSES34.addRoomFacility(rf10);
        KRSES34.addMinibarItem(m1);
        KRSES34.addMinibarItem(m2);
        KRSES34.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSES34);
        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_504"));

        Room KRSES35 = new Room("KRSE_505", "505", "Standard", 2, "Available", h9);
        KRSES35.addRoomFacility(rf1);
        KRSES35.addRoomFacility(rf2);
        KRSES35.addRoomFacility(rf3);
        KRSES35.addRoomFacility(rf4);
        KRSES35.addRoomFacility(rf5);
        KRSES35.addRoomFacility(rf6);
        KRSES35.addRoomFacility(rf7);
        KRSES35.addRoomFacility(rf8);
        KRSES35.addRoomFacility(rf9);
        KRSES35.addRoomFacility(rf10);
        KRSES35.addMinibarItem(m1);
        KRSES35.addMinibarItem(m2);
        KRSES35.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSES35);
        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_505"));

        Room KRSES36 = new Room("KRSE_506", "506", "Standard", 2, "Available", h9);
        KRSES36.addRoomFacility(rf1);
        KRSES36.addRoomFacility(rf2);
        KRSES36.addRoomFacility(rf3);
        KRSES36.addRoomFacility(rf4);
        KRSES36.addRoomFacility(rf5);
        KRSES36.addRoomFacility(rf6);
        KRSES36.addRoomFacility(rf7);
        KRSES36.addRoomFacility(rf8);
        KRSES36.addRoomFacility(rf9);
        KRSES36.addRoomFacility(rf10);
        KRSES36.addMinibarItem(m1);
        KRSES36.addMinibarItem(m2);
        KRSES36.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSES36);
        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_506"));

        Room KRSES41 = new Room("KRSE_601", "601", "Standard", 2, "Occupied", h9);
        KRSES41.addRoomFacility(rf1);
        KRSES41.addRoomFacility(rf2);
        KRSES41.addRoomFacility(rf3);
        KRSES41.addRoomFacility(rf4);
        KRSES41.addRoomFacility(rf5);
        KRSES41.addRoomFacility(rf6);
        KRSES41.addRoomFacility(rf7);
        KRSES41.addRoomFacility(rf8);
        KRSES41.addRoomFacility(rf9);
        KRSES41.addRoomFacility(rf10);
        KRSES41.addMinibarItem(m1);
        KRSES41.addMinibarItem(m2);
        KRSES41.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSES41);
        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_601"));

        Room KRSES42 = new Room("KRSE_602", "602", "Standard", 2, "Available", h9);
        KRSES42.addRoomFacility(rf1);
        KRSES42.addRoomFacility(rf2);
        KRSES42.addRoomFacility(rf3);
        KRSES42.addRoomFacility(rf4);
        KRSES42.addRoomFacility(rf5);
        KRSES42.addRoomFacility(rf6);
        KRSES42.addRoomFacility(rf7);
        KRSES42.addRoomFacility(rf8);
        KRSES42.addRoomFacility(rf9);
        KRSES42.addRoomFacility(rf10);
        KRSES42.addMinibarItem(m1);
        KRSES42.addMinibarItem(m2);
        KRSES42.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSES42);
        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_602"));

        Room KRSES43 = new Room("KRSE_603", "603", "Standard", 2, "Available", h9);
        KRSES43.addRoomFacility(rf1);
        KRSES43.addRoomFacility(rf2);
        KRSES43.addRoomFacility(rf3);
        KRSES43.addRoomFacility(rf4);
        KRSES43.addRoomFacility(rf5);
        KRSES43.addRoomFacility(rf6);
        KRSES43.addRoomFacility(rf7);
        KRSES43.addRoomFacility(rf8);
        KRSES43.addRoomFacility(rf9);
        KRSES43.addRoomFacility(rf10);
        KRSES43.addMinibarItem(m1);
        KRSES43.addMinibarItem(m2);
        KRSES43.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSES43);
        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_603"));

        Room KRSES44 = new Room("KRSE_604", "604", "Standard", 2, "Available", h9);
        KRSES44.addRoomFacility(rf1);
        KRSES44.addRoomFacility(rf2);
        KRSES44.addRoomFacility(rf3);
        KRSES44.addRoomFacility(rf4);
        KRSES44.addRoomFacility(rf5);
        KRSES44.addRoomFacility(rf6);
        KRSES44.addRoomFacility(rf7);
        KRSES44.addRoomFacility(rf8);
        KRSES44.addRoomFacility(rf9);
        KRSES44.addRoomFacility(rf10);
        KRSES44.addMinibarItem(m1);
        KRSES44.addMinibarItem(m2);
        KRSES44.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSES44);
        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_604"));

        Room KRSES45 = new Room("KRSE_605", "605", "Standard", 2, "Occupied", h9);
        KRSES45.addRoomFacility(rf1);
        KRSES45.addRoomFacility(rf2);
        KRSES45.addRoomFacility(rf3);
        KRSES45.addRoomFacility(rf4);
        KRSES45.addRoomFacility(rf5);
        KRSES45.addRoomFacility(rf6);
        KRSES45.addRoomFacility(rf7);
        KRSES45.addRoomFacility(rf8);
        KRSES45.addRoomFacility(rf9);
        KRSES45.addRoomFacility(rf10);
        KRSES45.addMinibarItem(m1);
        KRSES45.addMinibarItem(m2);
        KRSES45.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSES45);
        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_605"));

        Room KRSES46 = new Room("KRSE_606", "606", "Standard", 2, "Available", h9);
        KRSES46.addRoomFacility(rf1);
        KRSES46.addRoomFacility(rf2);
        KRSES46.addRoomFacility(rf3);
        KRSES46.addRoomFacility(rf4);
        KRSES46.addRoomFacility(rf5);
        KRSES46.addRoomFacility(rf6);
        KRSES46.addRoomFacility(rf7);
        KRSES46.addRoomFacility(rf8);
        KRSES46.addRoomFacility(rf9);
        KRSES46.addRoomFacility(rf10);
        KRSES46.addMinibarItem(m1);
        KRSES46.addMinibarItem(m2);
        KRSES46.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSES46);
        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_606"));

        Room KRSE_1001 = new Room("KRSE_1001", "1001", "Deluxe", 3, "Available", h9);

        KRSE_1001.addRoomFacility(rf1);
        KRSE_1001.addRoomFacility(rf2);
        KRSE_1001.addRoomFacility(rf3);
        KRSE_1001.addRoomFacility(rf4);
        KRSE_1001.addRoomFacility(rf5);
        KRSE_1001.addRoomFacility(rf6);
        KRSE_1001.addRoomFacility(rf7);
        KRSE_1001.addRoomFacility(rf8);
        KRSE_1001.addRoomFacility(rf9);
        KRSE_1001.addRoomFacility(rf10);
        KRSE_1001.addRoomFacility(rf11);
        KRSE_1001.addRoomFacility(rf12);
        KRSE_1001.addRoomFacility(rf13);

        KRSE_1001.addMinibarItem(m1);
        KRSE_1001.addMinibarItem(m2);
        KRSE_1001.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSE_1001);

        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_1001"));

        Room KRSE_1002 = new Room("KRSE_1002", "1002", "Deluxe", 3, "Available", h9);

        KRSE_1002.addRoomFacility(rf1);
        KRSE_1002.addRoomFacility(rf2);
        KRSE_1002.addRoomFacility(rf3);
        KRSE_1002.addRoomFacility(rf4);
        KRSE_1002.addRoomFacility(rf5);
        KRSE_1002.addRoomFacility(rf6);
        KRSE_1002.addRoomFacility(rf7);
        KRSE_1002.addRoomFacility(rf8);
        KRSE_1002.addRoomFacility(rf9);
        KRSE_1002.addRoomFacility(rf10);
        KRSE_1002.addRoomFacility(rf11);
        KRSE_1002.addRoomFacility(rf12);
        KRSE_1002.addRoomFacility(rf13);

        KRSE_1002.addMinibarItem(m1);
        KRSE_1002.addMinibarItem(m2);
        KRSE_1002.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSE_1002);

        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_1002"));

        Room KRSE_1003 = new Room("KRSE_1003", "1003", "Deluxe", 3, "Available", h9);

        KRSE_1003.addRoomFacility(rf1);
        KRSE_1003.addRoomFacility(rf2);
        KRSE_1003.addRoomFacility(rf3);
        KRSE_1003.addRoomFacility(rf4);
        KRSE_1003.addRoomFacility(rf5);
        KRSE_1003.addRoomFacility(rf6);
        KRSE_1003.addRoomFacility(rf7);
        KRSE_1003.addRoomFacility(rf8);
        KRSE_1003.addRoomFacility(rf9);
        KRSE_1003.addRoomFacility(rf10);
        KRSE_1003.addRoomFacility(rf11);
        KRSE_1003.addRoomFacility(rf12);
        KRSE_1003.addRoomFacility(rf13);

        KRSE_1003.addMinibarItem(m1);
        KRSE_1003.addMinibarItem(m2);
        KRSE_1003.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSE_1003);

        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_1003"));

        Room KRSE_1101 = new Room("KRSE_1101", "1101", "Deluxe", 3, "Occupied", h9);

        KRSE_1101.addRoomFacility(rf1);
        KRSE_1101.addRoomFacility(rf2);
        KRSE_1101.addRoomFacility(rf3);
        KRSE_1101.addRoomFacility(rf4);
        KRSE_1101.addRoomFacility(rf5);
        KRSE_1101.addRoomFacility(rf6);
        KRSE_1101.addRoomFacility(rf7);
        KRSE_1101.addRoomFacility(rf8);
        KRSE_1101.addRoomFacility(rf9);
        KRSE_1101.addRoomFacility(rf10);
        KRSE_1101.addRoomFacility(rf11);
        KRSE_1101.addRoomFacility(rf12);
        KRSE_1101.addRoomFacility(rf13);

        KRSE_1101.addMinibarItem(m1);
        KRSE_1101.addMinibarItem(m2);
        KRSE_1101.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSE_1101);

        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_1101"));

        Room KRSE_1102 = new Room("KRSE_1102", "1102", "Deluxe", 3, "Available", h9);

        KRSE_1102.addRoomFacility(rf1);
        KRSE_1102.addRoomFacility(rf2);
        KRSE_1102.addRoomFacility(rf3);
        KRSE_1102.addRoomFacility(rf4);
        KRSE_1102.addRoomFacility(rf5);
        KRSE_1102.addRoomFacility(rf6);
        KRSE_1102.addRoomFacility(rf7);
        KRSE_1102.addRoomFacility(rf8);
        KRSE_1102.addRoomFacility(rf9);
        KRSE_1102.addRoomFacility(rf10);
        KRSE_1102.addRoomFacility(rf11);
        KRSE_1102.addRoomFacility(rf12);
        KRSE_1102.addRoomFacility(rf13);

        KRSE_1102.addMinibarItem(m1);
        KRSE_1102.addMinibarItem(m2);
        KRSE_1102.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSE_1102);

        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_1102"));

        Room KRSE_1103 = new Room("KRSE_1103", "1103", "Deluxe", 3, "Available", h9);

        KRSE_1103.addRoomFacility(rf1);
        KRSE_1103.addRoomFacility(rf2);
        KRSE_1103.addRoomFacility(rf3);
        KRSE_1103.addRoomFacility(rf4);
        KRSE_1103.addRoomFacility(rf5);
        KRSE_1103.addRoomFacility(rf6);
        KRSE_1103.addRoomFacility(rf7);
        KRSE_1103.addRoomFacility(rf8);
        KRSE_1103.addRoomFacility(rf9);
        KRSE_1103.addRoomFacility(rf10);
        KRSE_1103.addRoomFacility(rf11);
        KRSE_1103.addRoomFacility(rf12);
        KRSE_1103.addRoomFacility(rf13);

        KRSE_1103.addMinibarItem(m1);
        KRSE_1103.addMinibarItem(m2);
        KRSE_1103.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSE_1103);

        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_1103"));

        Room KRSE_701 = new Room("KRSE_701", "701", "Premium", 4, "Available", h9);

        KRSE_701.addRoomFacility(rf1);
        KRSE_701.addRoomFacility(rf2);
        KRSE_701.addRoomFacility(rf3);
        KRSE_701.addRoomFacility(rf4);
        KRSE_701.addRoomFacility(rf5);
        KRSE_701.addRoomFacility(rf6);
        KRSE_701.addRoomFacility(rf7);
        KRSE_701.addRoomFacility(rf8);
        KRSE_701.addRoomFacility(rf9);
        KRSE_701.addRoomFacility(rf10);
        KRSE_701.addRoomFacility(rf11);
        KRSE_701.addRoomFacility(rf12);
        KRSE_701.addRoomFacility(rf13);
        KRSE_701.addRoomFacility(rf14);
        KRSE_701.addRoomFacility(rf15);

        KRSE_701.addMinibarItem(m1);
        KRSE_701.addMinibarItem(m2);
        KRSE_701.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSE_701);

        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_701"));

        Room KRSE_702 = new Room("KRSE_702", "702", "Premium", 4, "Available", h9);

        KRSE_702.addRoomFacility(rf1);
        KRSE_702.addRoomFacility(rf2);
        KRSE_702.addRoomFacility(rf3);
        KRSE_702.addRoomFacility(rf4);
        KRSE_702.addRoomFacility(rf5);
        KRSE_702.addRoomFacility(rf6);
        KRSE_702.addRoomFacility(rf7);
        KRSE_702.addRoomFacility(rf8);
        KRSE_702.addRoomFacility(rf9);
        KRSE_702.addRoomFacility(rf10);
        KRSE_702.addRoomFacility(rf11);
        KRSE_702.addRoomFacility(rf12);
        KRSE_702.addRoomFacility(rf13);
        KRSE_702.addRoomFacility(rf14);
        KRSE_702.addRoomFacility(rf15);

        KRSE_702.addMinibarItem(m1);
        KRSE_702.addMinibarItem(m2);
        KRSE_702.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSE_702);

        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_702"));

        Room KRSE_703 = new Room("KRSE_703", "703", "Premium", 4, "Available", h9);

        KRSE_703.addRoomFacility(rf1);
        KRSE_703.addRoomFacility(rf2);
        KRSE_703.addRoomFacility(rf3);
        KRSE_703.addRoomFacility(rf4);
        KRSE_703.addRoomFacility(rf5);
        KRSE_703.addRoomFacility(rf6);
        KRSE_703.addRoomFacility(rf7);
        KRSE_703.addRoomFacility(rf8);
        KRSE_703.addRoomFacility(rf9);
        KRSE_703.addRoomFacility(rf10);
        KRSE_703.addRoomFacility(rf11);
        KRSE_703.addRoomFacility(rf12);
        KRSE_703.addRoomFacility(rf13);
        KRSE_703.addRoomFacility(rf14);
        KRSE_703.addRoomFacility(rf15);

        KRSE_703.addMinibarItem(m1);
        KRSE_703.addMinibarItem(m2);
        KRSE_703.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSE_703);

        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_703"));

        Room KRSE_704 = new Room("KRSE_704", "704", "Premium", 4, "Occupied", h9);

        KRSE_704.addRoomFacility(rf1);
        KRSE_704.addRoomFacility(rf2);
        KRSE_704.addRoomFacility(rf3);
        KRSE_704.addRoomFacility(rf4);
        KRSE_704.addRoomFacility(rf5);
        KRSE_704.addRoomFacility(rf6);
        KRSE_704.addRoomFacility(rf7);
        KRSE_704.addRoomFacility(rf8);
        KRSE_704.addRoomFacility(rf9);
        KRSE_704.addRoomFacility(rf10);
        KRSE_704.addRoomFacility(rf11);
        KRSE_704.addRoomFacility(rf12);
        KRSE_704.addRoomFacility(rf13);
        KRSE_704.addRoomFacility(rf14);
        KRSE_704.addRoomFacility(rf15);

        KRSE_704.addMinibarItem(m1);
        KRSE_704.addMinibarItem(m2);
        KRSE_704.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSE_704);

        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_704"));

        Room KRSE_801 = new Room("KRSE_801", "801", "Premium", 4, "Available", h9);

        KRSE_801.addRoomFacility(rf1);
        KRSE_801.addRoomFacility(rf2);
        KRSE_801.addRoomFacility(rf3);
        KRSE_801.addRoomFacility(rf4);
        KRSE_801.addRoomFacility(rf5);
        KRSE_801.addRoomFacility(rf6);
        KRSE_801.addRoomFacility(rf7);
        KRSE_801.addRoomFacility(rf8);
        KRSE_801.addRoomFacility(rf9);
        KRSE_801.addRoomFacility(rf10);
        KRSE_801.addRoomFacility(rf11);
        KRSE_801.addRoomFacility(rf12);
        KRSE_801.addRoomFacility(rf13);
        KRSE_801.addRoomFacility(rf14);
        KRSE_801.addRoomFacility(rf15);

        KRSE_801.addMinibarItem(m1);
        KRSE_801.addMinibarItem(m2);
        KRSE_801.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSE_801);

        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_801"));

        Room KRSE_802 = new Room("KRSE_802", "802", "Premium", 4, "Available", h9);

        KRSE_802.addRoomFacility(rf1);
        KRSE_802.addRoomFacility(rf2);
        KRSE_802.addRoomFacility(rf3);
        KRSE_802.addRoomFacility(rf4);
        KRSE_802.addRoomFacility(rf5);
        KRSE_802.addRoomFacility(rf6);
        KRSE_802.addRoomFacility(rf7);
        KRSE_802.addRoomFacility(rf8);
        KRSE_802.addRoomFacility(rf9);
        KRSE_802.addRoomFacility(rf10);
        KRSE_802.addRoomFacility(rf11);
        KRSE_802.addRoomFacility(rf12);
        KRSE_802.addRoomFacility(rf13);
        KRSE_802.addRoomFacility(rf14);
        KRSE_802.addRoomFacility(rf15);

        KRSE_802.addMinibarItem(m1);
        KRSE_802.addMinibarItem(m2);
        KRSE_802.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSE_802);

        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_802"));

        Room KRSE_803 = new Room("KRSE_803", "803", "Premium", 4, "Available", h9);

        KRSE_803.addRoomFacility(rf1);
        KRSE_803.addRoomFacility(rf2);
        KRSE_803.addRoomFacility(rf3);
        KRSE_803.addRoomFacility(rf4);
        KRSE_803.addRoomFacility(rf5);
        KRSE_803.addRoomFacility(rf6);
        KRSE_803.addRoomFacility(rf7);
        KRSE_803.addRoomFacility(rf8);
        KRSE_803.addRoomFacility(rf9);
        KRSE_803.addRoomFacility(rf10);
        KRSE_803.addRoomFacility(rf11);
        KRSE_803.addRoomFacility(rf12);
        KRSE_803.addRoomFacility(rf13);
        KRSE_803.addRoomFacility(rf14);
        KRSE_803.addRoomFacility(rf15);

        KRSE_803.addMinibarItem(m1);
        KRSE_803.addMinibarItem(m2);
        KRSE_803.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSE_803);

        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_803"));

        Room KRSE_804 = new Room("KRSE_804", "804", "Premium", 4, "Occupied", h9);

        KRSE_804.addRoomFacility(rf1);
        KRSE_804.addRoomFacility(rf2);
        KRSE_804.addRoomFacility(rf3);
        KRSE_804.addRoomFacility(rf4);
        KRSE_804.addRoomFacility(rf5);
        KRSE_804.addRoomFacility(rf6);
        KRSE_804.addRoomFacility(rf7);
        KRSE_804.addRoomFacility(rf8);
        KRSE_804.addRoomFacility(rf9);
        KRSE_804.addRoomFacility(rf10);
        KRSE_804.addRoomFacility(rf11);
        KRSE_804.addRoomFacility(rf12);
        KRSE_804.addRoomFacility(rf13);
        KRSE_804.addRoomFacility(rf14);
        KRSE_804.addRoomFacility(rf15);

        KRSE_804.addMinibarItem(m1);
        KRSE_804.addMinibarItem(m2);
        KRSE_804.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSE_804);

        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_804"));

        Room KRSE_901 = new Room("KRSE_901", "901", "Premium", 4, "Unavailable", h9);

        KRSE_901.addRoomFacility(rf1);
        KRSE_901.addRoomFacility(rf2);
        KRSE_901.addRoomFacility(rf3);
        KRSE_901.addRoomFacility(rf4);
        KRSE_901.addRoomFacility(rf5);
        KRSE_901.addRoomFacility(rf6);
        KRSE_901.addRoomFacility(rf7);
        KRSE_901.addRoomFacility(rf8);
        KRSE_901.addRoomFacility(rf9);
        KRSE_901.addRoomFacility(rf10);
        KRSE_901.addRoomFacility(rf11);
        KRSE_901.addRoomFacility(rf12);
        KRSE_901.addRoomFacility(rf13);
        KRSE_901.addRoomFacility(rf14);
        KRSE_901.addRoomFacility(rf15);

        KRSE_901.addMinibarItem(m1);
        KRSE_901.addMinibarItem(m2);
        KRSE_901.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSE_901);

        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_901"));

        Room KRSE_902 = new Room("KRSE_902", "902", "Premium", 4, "Available", h9);

        KRSE_902.addRoomFacility(rf1);
        KRSE_902.addRoomFacility(rf2);
        KRSE_902.addRoomFacility(rf3);
        KRSE_902.addRoomFacility(rf4);
        KRSE_902.addRoomFacility(rf5);
        KRSE_902.addRoomFacility(rf6);
        KRSE_902.addRoomFacility(rf7);
        KRSE_902.addRoomFacility(rf8);
        KRSE_902.addRoomFacility(rf9);
        KRSE_902.addRoomFacility(rf10);
        KRSE_902.addRoomFacility(rf11);
        KRSE_902.addRoomFacility(rf12);
        KRSE_902.addRoomFacility(rf13);
        KRSE_902.addRoomFacility(rf14);
        KRSE_902.addRoomFacility(rf15);

        KRSE_902.addMinibarItem(m1);
        KRSE_902.addMinibarItem(m2);
        KRSE_902.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSE_902);

        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_902"));

        Room KRSE_903 = new Room("KRSE_903", "903", "Premium", 4, "Available", h9);

        KRSE_903.addRoomFacility(rf1);
        KRSE_903.addRoomFacility(rf2);
        KRSE_903.addRoomFacility(rf3);
        KRSE_903.addRoomFacility(rf4);
        KRSE_903.addRoomFacility(rf5);
        KRSE_903.addRoomFacility(rf6);
        KRSE_903.addRoomFacility(rf7);
        KRSE_903.addRoomFacility(rf8);
        KRSE_903.addRoomFacility(rf9);
        KRSE_903.addRoomFacility(rf10);
        KRSE_903.addRoomFacility(rf11);
        KRSE_903.addRoomFacility(rf12);
        KRSE_903.addRoomFacility(rf13);
        KRSE_903.addRoomFacility(rf14);
        KRSE_903.addRoomFacility(rf15);

        KRSE_903.addMinibarItem(m1);
        KRSE_903.addMinibarItem(m2);
        KRSE_903.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSE_903);

        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_903"));

        Room KRSE_904 = new Room("KRSE_904", "904", "Premium", 4, "Available", h9);

        KRSE_904.addRoomFacility(rf1);
        KRSE_904.addRoomFacility(rf2);
        KRSE_904.addRoomFacility(rf3);
        KRSE_904.addRoomFacility(rf4);
        KRSE_904.addRoomFacility(rf5);
        KRSE_904.addRoomFacility(rf6);
        KRSE_904.addRoomFacility(rf7);
        KRSE_904.addRoomFacility(rf8);
        KRSE_904.addRoomFacility(rf9);
        KRSE_904.addRoomFacility(rf10);
        KRSE_904.addRoomFacility(rf11);
        KRSE_904.addRoomFacility(rf12);
        KRSE_904.addRoomFacility(rf13);
        KRSE_904.addRoomFacility(rf14);
        KRSE_904.addRoomFacility(rf15);

        KRSE_904.addMinibarItem(m1);
        KRSE_904.addMinibarItem(m2);
        KRSE_904.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSE_904);

        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_904"));

        Room KRSE_1201 = new Room("KRSE_1201", "1201", "Suite", 4, "Occupied", h9);

        KRSE_1201.addRoomFacility(rf1);
        KRSE_1201.addRoomFacility(rf2);
        KRSE_1201.addRoomFacility(rf3);
        KRSE_1201.addRoomFacility(rf4);
        KRSE_1201.addRoomFacility(rf5);
        KRSE_1201.addRoomFacility(rf6);
        KRSE_1201.addRoomFacility(rf7);
        KRSE_1201.addRoomFacility(rf8);
        KRSE_1201.addRoomFacility(rf9);
        KRSE_1201.addRoomFacility(rf10);
        KRSE_1201.addRoomFacility(rf11);
        KRSE_1201.addRoomFacility(rf12);
        KRSE_1201.addRoomFacility(rf13);
        KRSE_1201.addRoomFacility(rf14);
        KRSE_1201.addRoomFacility(rf15);
        KRSE_1201.addRoomFacility(rf16);
        KRSE_1201.addRoomFacility(rf17);
        KRSE_1201.addRoomFacility(rf18);
        KRSE_1201.addRoomFacility(rf19);

        KRSE_1201.addMinibarItem(m1);
        KRSE_1201.addMinibarItem(m2);
        KRSE_1201.addMinibarItem(m3);
        KRSE_1201.addMinibarItem(m4);
        KRSE_1201.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRSE_1201);

        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_1201"));

        Room KRSE_1202 = new Room("KRSE_1202", "1202", "Suite", 4, "Available", h9);

        KRSE_1202.addRoomFacility(rf1);
        KRSE_1202.addRoomFacility(rf2);
        KRSE_1202.addRoomFacility(rf3);
        KRSE_1202.addRoomFacility(rf4);
        KRSE_1202.addRoomFacility(rf5);
        KRSE_1202.addRoomFacility(rf6);
        KRSE_1202.addRoomFacility(rf7);
        KRSE_1202.addRoomFacility(rf8);
        KRSE_1202.addRoomFacility(rf9);
        KRSE_1202.addRoomFacility(rf10);
        KRSE_1202.addRoomFacility(rf11);
        KRSE_1202.addRoomFacility(rf12);
        KRSE_1202.addRoomFacility(rf13);
        KRSE_1202.addRoomFacility(rf14);
        KRSE_1202.addRoomFacility(rf15);
        KRSE_1202.addRoomFacility(rf16);
        KRSE_1202.addRoomFacility(rf17);
        KRSE_1202.addRoomFacility(rf18);
        KRSE_1202.addRoomFacility(rf19);

        KRSE_1202.addMinibarItem(m1);
        KRSE_1202.addMinibarItem(m2);
        KRSE_1202.addMinibarItem(m3);
        KRSE_1202.addMinibarItem(m4);
        KRSE_1202.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRSE_1202);

        h9.addRoom(roomSessionLocal.getRoomByName("KRSE_1202"));

        em.flush();

    }

    public void initializeKRSWRoom() throws NoResultException {
        Hotel h10 = hotelSessionLocal.getHotelByName("Kent Ridge South West");
        MinibarItem m1 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Potato Chips");
        MinibarItem m2 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Chocolate");
        MinibarItem m3 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Mineral Water");
        MinibarItem m4 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Coke");
        MinibarItem m5 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Wine");
        RoomFacility rf1 = roomFacilitySessionLocal.getRoomFacilityByName("Wifi");
        RoomFacility rf2 = roomFacilitySessionLocal.getRoomFacilityByName("Minibar");
        RoomFacility rf3 = roomFacilitySessionLocal.getRoomFacilityByName("Aircon");
        RoomFacility rf4 = roomFacilitySessionLocal.getRoomFacilityByName("Cable TV");
        RoomFacility rf5 = roomFacilitySessionLocal.getRoomFacilityByName("Telephone");
        RoomFacility rf6 = roomFacilitySessionLocal.getRoomFacilityByName("Water Heater");
        RoomFacility rf7 = roomFacilitySessionLocal.getRoomFacilityByName("Bathroom with Shower Head");
        RoomFacility rf8 = roomFacilitySessionLocal.getRoomFacilityByName("HairDryer");
        RoomFacility rf9 = roomFacilitySessionLocal.getRoomFacilityByName("Safe Deposit Box");
        RoomFacility rf10 = roomFacilitySessionLocal.getRoomFacilityByName("Water Kettle with complimentary teas/coffee");
        RoomFacility rf11 = roomFacilitySessionLocal.getRoomFacilityByName("Room with Windows");
        RoomFacility rf12 = roomFacilitySessionLocal.getRoomFacilityByName("Bathtub");
        RoomFacility rf13 = roomFacilitySessionLocal.getRoomFacilityByName("Mini Fridge");
        RoomFacility rf14 = roomFacilitySessionLocal.getRoomFacilityByName("Coffee Machine");
        RoomFacility rf15 = roomFacilitySessionLocal.getRoomFacilityByName("Balcony");
        RoomFacility rf16 = roomFacilitySessionLocal.getRoomFacilityByName("Microwave");
        RoomFacility rf17 = roomFacilitySessionLocal.getRoomFacilityByName("Gaming Console");
        RoomFacility rf18 = roomFacilitySessionLocal.getRoomFacilityByName("Entertainment System");
        RoomFacility rf19 = roomFacilitySessionLocal.getRoomFacilityByName("Blackout Curtains");
        RoomFacility rf20 = roomFacilitySessionLocal.getRoomFacilityByName("High Ceiling");
        RoomFacility rf21 = roomFacilitySessionLocal.getRoomFacilityByName("Jaccuzi");
        RoomFacility rf22 = roomFacilitySessionLocal.getRoomFacilityByName("Kitchen");

        FunctionRoom KRSWFR1 = new FunctionRoom("KRSWFR1", 20, "Available", 20000.00, h10);
        FunctionRoom KRSWFR2 = new FunctionRoom("KRSWFR2", 100, "Available", 100000.00, h10);
        FunctionRoom KRSWFR3 = new FunctionRoom("KRSWFR3", 50, "Available", 50000.00, h10);
        FunctionRoom KRSWFR4 = new FunctionRoom("KRSWFR4", 70, "Available", 70000.00, h10);
        FunctionRoom KRSWFR5 = new FunctionRoom("KRSWFR5", 80, "Available", 80000.00, h10);

        functionRoomSessionLocal.createFunctionRoom(KRSWFR1);
        functionRoomSessionLocal.createFunctionRoom(KRSWFR2);
        functionRoomSessionLocal.createFunctionRoom(KRSWFR3);
        functionRoomSessionLocal.createFunctionRoom(KRSWFR4);
        functionRoomSessionLocal.createFunctionRoom(KRSWFR5);

        h10.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRSWFR1"));
        h10.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRSWFR2"));
        h10.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRSWFR3"));
        h10.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRSWFR4"));
        h10.addFunctionRoom(functionRoomSessionLocal.getFunctionRoomByName("KRSWFR5"));

        Room KRSWS1 = new Room("KRSW_201", "201", "Standard", 2, "Available", h10);
        KRSWS1.addRoomFacility(rf1);
        KRSWS1.addRoomFacility(rf2);
        KRSWS1.addRoomFacility(rf3);
        KRSWS1.addRoomFacility(rf4);
        KRSWS1.addRoomFacility(rf5);
        KRSWS1.addRoomFacility(rf6);
        KRSWS1.addRoomFacility(rf7);
        KRSWS1.addRoomFacility(rf8);
        KRSWS1.addRoomFacility(rf9);
        KRSWS1.addRoomFacility(rf10);
        KRSWS1.addMinibarItem(m1);
        KRSWS1.addMinibarItem(m2);
        KRSWS1.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSWS1);
        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_201"));

        Room KRSWS2 = new Room("KRSW_202", "202", "Standard", 2, "Available", h10);
        KRSWS2.addRoomFacility(rf1);
        KRSWS2.addRoomFacility(rf2);
        KRSWS2.addRoomFacility(rf3);
        KRSWS2.addRoomFacility(rf4);
        KRSWS2.addRoomFacility(rf5);
        KRSWS2.addRoomFacility(rf6);
        KRSWS2.addRoomFacility(rf7);
        KRSWS2.addRoomFacility(rf8);
        KRSWS2.addRoomFacility(rf9);
        KRSWS2.addRoomFacility(rf10);
        KRSWS2.addMinibarItem(m1);
        KRSWS2.addMinibarItem(m2);
        KRSWS2.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSWS2);
        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_202"));

        Room KRSWS3 = new Room("KRSW_203", "203", "Standard", 2, "Available", h10);
        KRSWS3.addRoomFacility(rf1);
        KRSWS3.addRoomFacility(rf2);
        KRSWS3.addRoomFacility(rf3);
        KRSWS3.addRoomFacility(rf4);
        KRSWS3.addRoomFacility(rf5);
        KRSWS3.addRoomFacility(rf6);
        KRSWS3.addRoomFacility(rf7);
        KRSWS3.addRoomFacility(rf8);
        KRSWS3.addRoomFacility(rf9);
        KRSWS3.addRoomFacility(rf10);
        KRSWS3.addMinibarItem(m1);
        KRSWS3.addMinibarItem(m2);
        KRSWS3.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSWS3);
        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_203"));

        Room KRSWS4 = new Room("KRSW_204", "204", "Standard", 2, "Available", h10);
        KRSWS4.addRoomFacility(rf1);
        KRSWS4.addRoomFacility(rf2);
        KRSWS4.addRoomFacility(rf3);
        KRSWS4.addRoomFacility(rf4);
        KRSWS4.addRoomFacility(rf5);
        KRSWS4.addRoomFacility(rf6);
        KRSWS4.addRoomFacility(rf7);
        KRSWS4.addRoomFacility(rf8);
        KRSWS4.addRoomFacility(rf9);
        KRSWS4.addRoomFacility(rf10);
        KRSWS4.addMinibarItem(m1);
        KRSWS4.addMinibarItem(m2);
        KRSWS4.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSWS4);
        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_204"));

        Room KRSWS5 = new Room("KRSW_205", "205", "Standard", 2, "Unavailable", h10);
        KRSWS5.addRoomFacility(rf1);
        KRSWS5.addRoomFacility(rf2);
        KRSWS5.addRoomFacility(rf3);
        KRSWS5.addRoomFacility(rf4);
        KRSWS5.addRoomFacility(rf5);
        KRSWS5.addRoomFacility(rf6);
        KRSWS5.addRoomFacility(rf7);
        KRSWS5.addRoomFacility(rf8);
        KRSWS5.addRoomFacility(rf9);
        KRSWS5.addRoomFacility(rf10);
        KRSWS5.addMinibarItem(m1);
        KRSWS5.addMinibarItem(m2);
        KRSWS5.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSWS5);
        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_205"));

        Room KRSWS6 = new Room("KRSW_206", "206", "Standard", 2, "Available", h10);
        KRSWS6.addRoomFacility(rf1);
        KRSWS6.addRoomFacility(rf2);
        KRSWS6.addRoomFacility(rf3);
        KRSWS6.addRoomFacility(rf4);
        KRSWS6.addRoomFacility(rf5);
        KRSWS6.addRoomFacility(rf6);
        KRSWS6.addRoomFacility(rf7);
        KRSWS6.addRoomFacility(rf8);
        KRSWS6.addRoomFacility(rf9);
        KRSWS6.addRoomFacility(rf10);
        KRSWS6.addMinibarItem(m1);
        KRSWS6.addMinibarItem(m2);
        KRSWS6.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSWS6);
        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_206"));

        Room KRSWS11 = new Room("KRSW_301", "301", "Standard", 2, "Available", h10);
        KRSWS11.addRoomFacility(rf1);
        KRSWS11.addRoomFacility(rf2);
        KRSWS11.addRoomFacility(rf3);
        KRSWS11.addRoomFacility(rf4);
        KRSWS11.addRoomFacility(rf5);
        KRSWS11.addRoomFacility(rf6);
        KRSWS11.addRoomFacility(rf7);
        KRSWS11.addRoomFacility(rf8);
        KRSWS11.addRoomFacility(rf9);
        KRSWS11.addRoomFacility(rf10);
        KRSWS11.addMinibarItem(m1);
        KRSWS11.addMinibarItem(m2);
        KRSWS11.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSWS11);
        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_301"));

        Room KRSWS12 = new Room("KRSW_302", "302", "Standard", 2, "Available", h10);
        KRSWS12.addRoomFacility(rf1);
        KRSWS12.addRoomFacility(rf2);
        KRSWS12.addRoomFacility(rf3);
        KRSWS12.addRoomFacility(rf4);
        KRSWS12.addRoomFacility(rf5);
        KRSWS12.addRoomFacility(rf6);
        KRSWS12.addRoomFacility(rf7);
        KRSWS12.addRoomFacility(rf8);
        KRSWS12.addRoomFacility(rf9);
        KRSWS12.addRoomFacility(rf10);
        KRSWS12.addMinibarItem(m1);
        KRSWS12.addMinibarItem(m2);
        KRSWS12.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSWS12);
        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_302"));

        Room KRSWS13 = new Room("KRSW_303", "303", "Standard", 2, "Available", h10);
        KRSWS13.addRoomFacility(rf1);
        KRSWS13.addRoomFacility(rf2);
        KRSWS13.addRoomFacility(rf3);
        KRSWS13.addRoomFacility(rf4);
        KRSWS13.addRoomFacility(rf5);
        KRSWS13.addRoomFacility(rf6);
        KRSWS13.addRoomFacility(rf7);
        KRSWS13.addRoomFacility(rf8);
        KRSWS13.addRoomFacility(rf9);
        KRSWS13.addRoomFacility(rf10);
        KRSWS13.addMinibarItem(m1);
        KRSWS13.addMinibarItem(m2);
        KRSWS13.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSWS13);
        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_303"));

        Room KRSWS14 = new Room("KRSW_304", "304", "Standard", 2, "Available", h10);
        KRSWS14.addRoomFacility(rf1);
        KRSWS14.addRoomFacility(rf2);
        KRSWS14.addRoomFacility(rf3);
        KRSWS14.addRoomFacility(rf4);
        KRSWS14.addRoomFacility(rf5);
        KRSWS14.addRoomFacility(rf6);
        KRSWS14.addRoomFacility(rf7);
        KRSWS14.addRoomFacility(rf8);
        KRSWS14.addRoomFacility(rf9);
        KRSWS14.addRoomFacility(rf10);
        KRSWS14.addMinibarItem(m1);
        KRSWS14.addMinibarItem(m2);
        KRSWS14.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSWS14);
        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_304"));

        Room KRSWS15 = new Room("KRSW_305", "305", "Standard", 2, "Available", h10);
        KRSWS15.addRoomFacility(rf1);
        KRSWS15.addRoomFacility(rf2);
        KRSWS15.addRoomFacility(rf3);
        KRSWS15.addRoomFacility(rf4);
        KRSWS15.addRoomFacility(rf5);
        KRSWS15.addRoomFacility(rf6);
        KRSWS15.addRoomFacility(rf7);
        KRSWS15.addRoomFacility(rf8);
        KRSWS15.addRoomFacility(rf9);
        KRSWS15.addRoomFacility(rf10);
        KRSWS15.addMinibarItem(m1);
        KRSWS15.addMinibarItem(m2);
        KRSWS15.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSWS15);
        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_305"));

        Room KRSWS16 = new Room("KRSW_306", "306", "Standard", 2, "Available", h10);
        KRSWS16.addRoomFacility(rf1);
        KRSWS16.addRoomFacility(rf2);
        KRSWS16.addRoomFacility(rf3);
        KRSWS16.addRoomFacility(rf4);
        KRSWS16.addRoomFacility(rf5);
        KRSWS16.addRoomFacility(rf6);
        KRSWS16.addRoomFacility(rf7);
        KRSWS16.addRoomFacility(rf8);
        KRSWS16.addRoomFacility(rf9);
        KRSWS16.addRoomFacility(rf10);
        KRSWS16.addMinibarItem(m1);
        KRSWS16.addMinibarItem(m2);
        KRSWS16.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSWS16);
        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_306"));

        Room KRSWS21 = new Room("KRSW_401", "401", "Standard", 2, "Occupied", h10);
        KRSWS21.addRoomFacility(rf1);
        KRSWS21.addRoomFacility(rf2);
        KRSWS21.addRoomFacility(rf3);
        KRSWS21.addRoomFacility(rf4);
        KRSWS21.addRoomFacility(rf5);
        KRSWS21.addRoomFacility(rf6);
        KRSWS21.addRoomFacility(rf7);
        KRSWS21.addRoomFacility(rf8);
        KRSWS21.addRoomFacility(rf9);
        KRSWS21.addRoomFacility(rf10);
        KRSWS21.addMinibarItem(m1);
        KRSWS21.addMinibarItem(m2);
        KRSWS21.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSWS21);
        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_401"));

        Room KRSWS22 = new Room("KRSW_402", "402", "Standard", 2, "Available", h10);
        KRSWS22.addRoomFacility(rf1);
        KRSWS22.addRoomFacility(rf2);
        KRSWS22.addRoomFacility(rf3);
        KRSWS22.addRoomFacility(rf4);
        KRSWS22.addRoomFacility(rf5);
        KRSWS22.addRoomFacility(rf6);
        KRSWS22.addRoomFacility(rf7);
        KRSWS22.addRoomFacility(rf8);
        KRSWS22.addRoomFacility(rf9);
        KRSWS22.addRoomFacility(rf10);
        KRSWS22.addMinibarItem(m1);
        KRSWS22.addMinibarItem(m2);
        KRSWS22.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSWS22);
        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_402"));

        Room KRSWS23 = new Room("KRSW_403", "403", "Standard", 2, "Available", h10);
        KRSWS23.addRoomFacility(rf1);
        KRSWS23.addRoomFacility(rf2);
        KRSWS23.addRoomFacility(rf3);
        KRSWS23.addRoomFacility(rf4);
        KRSWS23.addRoomFacility(rf5);
        KRSWS23.addRoomFacility(rf6);
        KRSWS23.addRoomFacility(rf7);
        KRSWS23.addRoomFacility(rf8);
        KRSWS23.addRoomFacility(rf9);
        KRSWS23.addRoomFacility(rf10);
        KRSWS23.addMinibarItem(m1);
        KRSWS23.addMinibarItem(m2);
        KRSWS23.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSWS23);
        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_403"));

        Room KRSWS24 = new Room("KRSW_404", "404", "Standard", 2, "Unavailable", h10);
        KRSWS24.addRoomFacility(rf1);
        KRSWS24.addRoomFacility(rf2);
        KRSWS24.addRoomFacility(rf3);
        KRSWS24.addRoomFacility(rf4);
        KRSWS24.addRoomFacility(rf5);
        KRSWS24.addRoomFacility(rf6);
        KRSWS24.addRoomFacility(rf7);
        KRSWS24.addRoomFacility(rf8);
        KRSWS24.addRoomFacility(rf9);
        KRSWS24.addRoomFacility(rf10);
        KRSWS24.addMinibarItem(m1);
        KRSWS24.addMinibarItem(m2);
        KRSWS24.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSWS24);
        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_404"));

        Room KRSWS25 = new Room("KRSW_405", "405", "Standard", 2, "Available", h10);
        KRSWS25.addRoomFacility(rf1);
        KRSWS25.addRoomFacility(rf2);
        KRSWS25.addRoomFacility(rf3);
        KRSWS25.addRoomFacility(rf4);
        KRSWS25.addRoomFacility(rf5);
        KRSWS25.addRoomFacility(rf6);
        KRSWS25.addRoomFacility(rf7);
        KRSWS25.addRoomFacility(rf8);
        KRSWS25.addRoomFacility(rf9);
        KRSWS25.addRoomFacility(rf10);
        KRSWS25.addMinibarItem(m1);
        KRSWS25.addMinibarItem(m2);
        KRSWS25.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSWS25);
        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_405"));

        Room KRSWS26 = new Room("KRSW_406", "406", "Standard", 2, "Available", h10);
        KRSWS26.addRoomFacility(rf1);
        KRSWS26.addRoomFacility(rf2);
        KRSWS26.addRoomFacility(rf3);
        KRSWS26.addRoomFacility(rf4);
        KRSWS26.addRoomFacility(rf5);
        KRSWS26.addRoomFacility(rf6);
        KRSWS26.addRoomFacility(rf7);
        KRSWS26.addRoomFacility(rf8);
        KRSWS26.addRoomFacility(rf9);
        KRSWS26.addRoomFacility(rf10);
        KRSWS26.addMinibarItem(m1);
        KRSWS26.addMinibarItem(m2);
        KRSWS26.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSWS26);
        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_406"));

        Room KRSWS31 = new Room("KRSW_501", "501", "Standard", 2, "Occupied", h10);
        KRSWS31.addRoomFacility(rf1);
        KRSWS31.addRoomFacility(rf2);
        KRSWS31.addRoomFacility(rf3);
        KRSWS31.addRoomFacility(rf4);
        KRSWS31.addRoomFacility(rf5);
        KRSWS31.addRoomFacility(rf6);
        KRSWS31.addRoomFacility(rf7);
        KRSWS31.addRoomFacility(rf8);
        KRSWS31.addRoomFacility(rf9);
        KRSWS31.addRoomFacility(rf10);
        KRSWS31.addMinibarItem(m1);
        KRSWS31.addMinibarItem(m2);
        KRSWS31.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSWS31);
        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_501"));

        Room KRSWS32 = new Room("KRSW_502", "502", "Standard", 2, "Available", h10);
        KRSWS32.addRoomFacility(rf1);
        KRSWS32.addRoomFacility(rf2);
        KRSWS32.addRoomFacility(rf3);
        KRSWS32.addRoomFacility(rf4);
        KRSWS32.addRoomFacility(rf5);
        KRSWS32.addRoomFacility(rf6);
        KRSWS32.addRoomFacility(rf7);
        KRSWS32.addRoomFacility(rf8);
        KRSWS32.addRoomFacility(rf9);
        KRSWS32.addRoomFacility(rf10);
        KRSWS32.addMinibarItem(m1);
        KRSWS32.addMinibarItem(m2);
        KRSWS32.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSWS32);
        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_502"));

        Room KRSWS33 = new Room("KRSW_503", "503", "Standard", 2, "Available", h10);
        KRSWS33.addRoomFacility(rf1);
        KRSWS33.addRoomFacility(rf2);
        KRSWS33.addRoomFacility(rf3);
        KRSWS33.addRoomFacility(rf4);
        KRSWS33.addRoomFacility(rf5);
        KRSWS33.addRoomFacility(rf6);
        KRSWS33.addRoomFacility(rf7);
        KRSWS33.addRoomFacility(rf8);
        KRSWS33.addRoomFacility(rf9);
        KRSWS33.addRoomFacility(rf10);
        KRSWS33.addMinibarItem(m1);
        KRSWS33.addMinibarItem(m2);
        KRSWS33.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSWS33);
        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_503"));

        Room KRSWS34 = new Room("KRSW_504", "504", "Standard", 2, "Available", h10);
        KRSWS34.addRoomFacility(rf1);
        KRSWS34.addRoomFacility(rf2);
        KRSWS34.addRoomFacility(rf3);
        KRSWS34.addRoomFacility(rf4);
        KRSWS34.addRoomFacility(rf5);
        KRSWS34.addRoomFacility(rf6);
        KRSWS34.addRoomFacility(rf7);
        KRSWS34.addRoomFacility(rf8);
        KRSWS34.addRoomFacility(rf9);
        KRSWS34.addRoomFacility(rf10);
        KRSWS34.addMinibarItem(m1);
        KRSWS34.addMinibarItem(m2);
        KRSWS34.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSWS34);
        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_504"));

        Room KRSWS35 = new Room("KRSW_505", "505", "Standard", 2, "Available", h10);
        KRSWS35.addRoomFacility(rf1);
        KRSWS35.addRoomFacility(rf2);
        KRSWS35.addRoomFacility(rf3);
        KRSWS35.addRoomFacility(rf4);
        KRSWS35.addRoomFacility(rf5);
        KRSWS35.addRoomFacility(rf6);
        KRSWS35.addRoomFacility(rf7);
        KRSWS35.addRoomFacility(rf8);
        KRSWS35.addRoomFacility(rf9);
        KRSWS35.addRoomFacility(rf10);
        KRSWS35.addMinibarItem(m1);
        KRSWS35.addMinibarItem(m2);
        KRSWS35.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSWS35);
        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_505"));

        Room KRSWS36 = new Room("KRSW_506", "506", "Standard", 2, "Available", h10);
        KRSWS36.addRoomFacility(rf1);
        KRSWS36.addRoomFacility(rf2);
        KRSWS36.addRoomFacility(rf3);
        KRSWS36.addRoomFacility(rf4);
        KRSWS36.addRoomFacility(rf5);
        KRSWS36.addRoomFacility(rf6);
        KRSWS36.addRoomFacility(rf7);
        KRSWS36.addRoomFacility(rf8);
        KRSWS36.addRoomFacility(rf9);
        KRSWS36.addRoomFacility(rf10);
        KRSWS36.addMinibarItem(m1);
        KRSWS36.addMinibarItem(m2);
        KRSWS36.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSWS36);
        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_506"));

        Room KRSWS41 = new Room("KRSW_601", "601", "Standard", 2, "Available", h10);
        KRSWS41.addRoomFacility(rf1);
        KRSWS41.addRoomFacility(rf2);
        KRSWS41.addRoomFacility(rf3);
        KRSWS41.addRoomFacility(rf4);
        KRSWS41.addRoomFacility(rf5);
        KRSWS41.addRoomFacility(rf6);
        KRSWS41.addRoomFacility(rf7);
        KRSWS41.addRoomFacility(rf8);
        KRSWS41.addRoomFacility(rf9);
        KRSWS41.addRoomFacility(rf10);
        KRSWS41.addMinibarItem(m1);
        KRSWS41.addMinibarItem(m2);
        KRSWS41.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSWS41);
        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_601"));

        Room KRSWS42 = new Room("KRSW_602", "602", "Standard", 2, "Available", h10);
        KRSWS42.addRoomFacility(rf1);
        KRSWS42.addRoomFacility(rf2);
        KRSWS42.addRoomFacility(rf3);
        KRSWS42.addRoomFacility(rf4);
        KRSWS42.addRoomFacility(rf5);
        KRSWS42.addRoomFacility(rf6);
        KRSWS42.addRoomFacility(rf7);
        KRSWS42.addRoomFacility(rf8);
        KRSWS42.addRoomFacility(rf9);
        KRSWS42.addRoomFacility(rf10);
        KRSWS42.addMinibarItem(m1);
        KRSWS42.addMinibarItem(m2);
        KRSWS42.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSWS42);
        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_602"));

        Room KRSWS43 = new Room("KRSW_603", "603", "Standard", 2, "Occupied", h10);
        KRSWS43.addRoomFacility(rf1);
        KRSWS43.addRoomFacility(rf2);
        KRSWS43.addRoomFacility(rf3);
        KRSWS43.addRoomFacility(rf4);
        KRSWS43.addRoomFacility(rf5);
        KRSWS43.addRoomFacility(rf6);
        KRSWS43.addRoomFacility(rf7);
        KRSWS43.addRoomFacility(rf8);
        KRSWS43.addRoomFacility(rf9);
        KRSWS43.addRoomFacility(rf10);
        KRSWS43.addMinibarItem(m1);
        KRSWS43.addMinibarItem(m2);
        KRSWS43.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSWS43);
        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_603"));

        Room KRSWS44 = new Room("KRSW_604", "604", "Standard", 2, "Available", h10);
        KRSWS44.addRoomFacility(rf1);
        KRSWS44.addRoomFacility(rf2);
        KRSWS44.addRoomFacility(rf3);
        KRSWS44.addRoomFacility(rf4);
        KRSWS44.addRoomFacility(rf5);
        KRSWS44.addRoomFacility(rf6);
        KRSWS44.addRoomFacility(rf7);
        KRSWS44.addRoomFacility(rf8);
        KRSWS44.addRoomFacility(rf9);
        KRSWS44.addRoomFacility(rf10);
        KRSWS44.addMinibarItem(m1);
        KRSWS44.addMinibarItem(m2);
        KRSWS44.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSWS44);
        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_604"));

        Room KRSWS45 = new Room("KRSW_605", "605", "Standard", 2, "Available", h10);
        KRSWS45.addRoomFacility(rf1);
        KRSWS45.addRoomFacility(rf2);
        KRSWS45.addRoomFacility(rf3);
        KRSWS45.addRoomFacility(rf4);
        KRSWS45.addRoomFacility(rf5);
        KRSWS45.addRoomFacility(rf6);
        KRSWS45.addRoomFacility(rf7);
        KRSWS45.addRoomFacility(rf8);
        KRSWS45.addRoomFacility(rf9);
        KRSWS45.addRoomFacility(rf10);
        KRSWS45.addMinibarItem(m1);
        KRSWS45.addMinibarItem(m2);
        KRSWS45.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSWS45);
        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_605"));

        Room KRSWS46 = new Room("KRSW_606", "606", "Standard", 2, "Available", h10);
        KRSWS46.addRoomFacility(rf1);
        KRSWS46.addRoomFacility(rf2);
        KRSWS46.addRoomFacility(rf3);
        KRSWS46.addRoomFacility(rf4);
        KRSWS46.addRoomFacility(rf5);
        KRSWS46.addRoomFacility(rf6);
        KRSWS46.addRoomFacility(rf7);
        KRSWS46.addRoomFacility(rf8);
        KRSWS46.addRoomFacility(rf9);
        KRSWS46.addRoomFacility(rf10);
        KRSWS46.addMinibarItem(m1);
        KRSWS46.addMinibarItem(m2);
        KRSWS46.addMinibarItem(m3);
        roomSessionLocal.createRoom(KRSWS46);
        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_606"));

        Room KRSW_1001 = new Room("KRSW_1001", "1001", "Deluxe", 3, "Available", h10);

        KRSW_1001.addRoomFacility(rf1);
        KRSW_1001.addRoomFacility(rf2);
        KRSW_1001.addRoomFacility(rf3);
        KRSW_1001.addRoomFacility(rf4);
        KRSW_1001.addRoomFacility(rf5);
        KRSW_1001.addRoomFacility(rf6);
        KRSW_1001.addRoomFacility(rf7);
        KRSW_1001.addRoomFacility(rf8);
        KRSW_1001.addRoomFacility(rf9);
        KRSW_1001.addRoomFacility(rf10);
        KRSW_1001.addRoomFacility(rf11);
        KRSW_1001.addRoomFacility(rf12);
        KRSW_1001.addRoomFacility(rf13);

        KRSW_1001.addMinibarItem(m1);
        KRSW_1001.addMinibarItem(m2);
        KRSW_1001.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSW_1001);

        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_1001"));

        Room KRSW_1002 = new Room("KRSW_1002", "1002", "Deluxe", 3, "Available", h10);

        KRSW_1002.addRoomFacility(rf1);
        KRSW_1002.addRoomFacility(rf2);
        KRSW_1002.addRoomFacility(rf3);
        KRSW_1002.addRoomFacility(rf4);
        KRSW_1002.addRoomFacility(rf5);
        KRSW_1002.addRoomFacility(rf6);
        KRSW_1002.addRoomFacility(rf7);
        KRSW_1002.addRoomFacility(rf8);
        KRSW_1002.addRoomFacility(rf9);
        KRSW_1002.addRoomFacility(rf10);
        KRSW_1002.addRoomFacility(rf11);
        KRSW_1002.addRoomFacility(rf12);
        KRSW_1002.addRoomFacility(rf13);

        KRSW_1002.addMinibarItem(m1);
        KRSW_1002.addMinibarItem(m2);
        KRSW_1002.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSW_1002);

        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_1002"));

        Room KRSW_1003 = new Room("KRSW_1003", "1003", "Deluxe", 3, "Unavailable", h10);

        KRSW_1003.addRoomFacility(rf1);
        KRSW_1003.addRoomFacility(rf2);
        KRSW_1003.addRoomFacility(rf3);
        KRSW_1003.addRoomFacility(rf4);
        KRSW_1003.addRoomFacility(rf5);
        KRSW_1003.addRoomFacility(rf6);
        KRSW_1003.addRoomFacility(rf7);
        KRSW_1003.addRoomFacility(rf8);
        KRSW_1003.addRoomFacility(rf9);
        KRSW_1003.addRoomFacility(rf10);
        KRSW_1003.addRoomFacility(rf11);
        KRSW_1003.addRoomFacility(rf12);
        KRSW_1003.addRoomFacility(rf13);

        KRSW_1003.addMinibarItem(m1);
        KRSW_1003.addMinibarItem(m2);
        KRSW_1003.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSW_1003);

        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_1003"));

        Room KRSW_1101 = new Room("KRSW_1101", "1101", "Deluxe", 3, "Occupied", h10);

        KRSW_1101.addRoomFacility(rf1);
        KRSW_1101.addRoomFacility(rf2);
        KRSW_1101.addRoomFacility(rf3);
        KRSW_1101.addRoomFacility(rf4);
        KRSW_1101.addRoomFacility(rf5);
        KRSW_1101.addRoomFacility(rf6);
        KRSW_1101.addRoomFacility(rf7);
        KRSW_1101.addRoomFacility(rf8);
        KRSW_1101.addRoomFacility(rf9);
        KRSW_1101.addRoomFacility(rf10);
        KRSW_1101.addRoomFacility(rf11);
        KRSW_1101.addRoomFacility(rf12);
        KRSW_1101.addRoomFacility(rf13);

        KRSW_1101.addMinibarItem(m1);
        KRSW_1101.addMinibarItem(m2);
        KRSW_1101.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSW_1101);

        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_1101"));

        Room KRSW_1102 = new Room("KRSW_1102", "1102", "Deluxe", 3, "Available", h10);

        KRSW_1102.addRoomFacility(rf1);
        KRSW_1102.addRoomFacility(rf2);
        KRSW_1102.addRoomFacility(rf3);
        KRSW_1102.addRoomFacility(rf4);
        KRSW_1102.addRoomFacility(rf5);
        KRSW_1102.addRoomFacility(rf6);
        KRSW_1102.addRoomFacility(rf7);
        KRSW_1102.addRoomFacility(rf8);
        KRSW_1102.addRoomFacility(rf9);
        KRSW_1102.addRoomFacility(rf10);
        KRSW_1102.addRoomFacility(rf11);
        KRSW_1102.addRoomFacility(rf12);
        KRSW_1102.addRoomFacility(rf13);

        KRSW_1102.addMinibarItem(m1);
        KRSW_1102.addMinibarItem(m2);
        KRSW_1102.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSW_1102);

        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_1102"));

        Room KRSW_1103 = new Room("KRSW_1103", "1103", "Deluxe", 3, "Available", h10);

        KRSW_1103.addRoomFacility(rf1);
        KRSW_1103.addRoomFacility(rf2);
        KRSW_1103.addRoomFacility(rf3);
        KRSW_1103.addRoomFacility(rf4);
        KRSW_1103.addRoomFacility(rf5);
        KRSW_1103.addRoomFacility(rf6);
        KRSW_1103.addRoomFacility(rf7);
        KRSW_1103.addRoomFacility(rf8);
        KRSW_1103.addRoomFacility(rf9);
        KRSW_1103.addRoomFacility(rf10);
        KRSW_1103.addRoomFacility(rf11);
        KRSW_1103.addRoomFacility(rf12);
        KRSW_1103.addRoomFacility(rf13);

        KRSW_1103.addMinibarItem(m1);
        KRSW_1103.addMinibarItem(m2);
        KRSW_1103.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSW_1103);

        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_1103"));

        Room KRSW_701 = new Room("KRSW_701", "701", "Premium", 4, "Available", h10);

        KRSW_701.addRoomFacility(rf1);
        KRSW_701.addRoomFacility(rf2);
        KRSW_701.addRoomFacility(rf3);
        KRSW_701.addRoomFacility(rf4);
        KRSW_701.addRoomFacility(rf5);
        KRSW_701.addRoomFacility(rf6);
        KRSW_701.addRoomFacility(rf7);
        KRSW_701.addRoomFacility(rf8);
        KRSW_701.addRoomFacility(rf9);
        KRSW_701.addRoomFacility(rf10);
        KRSW_701.addRoomFacility(rf11);
        KRSW_701.addRoomFacility(rf12);
        KRSW_701.addRoomFacility(rf13);
        KRSW_701.addRoomFacility(rf14);
        KRSW_701.addRoomFacility(rf15);

        KRSW_701.addMinibarItem(m1);
        KRSW_701.addMinibarItem(m2);
        KRSW_701.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSW_701);

        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_701"));

        Room KRSW_702 = new Room("KRSW_702", "702", "Premium", 4, "Available", h10);

        KRSW_702.addRoomFacility(rf1);
        KRSW_702.addRoomFacility(rf2);
        KRSW_702.addRoomFacility(rf3);
        KRSW_702.addRoomFacility(rf4);
        KRSW_702.addRoomFacility(rf5);
        KRSW_702.addRoomFacility(rf6);
        KRSW_702.addRoomFacility(rf7);
        KRSW_702.addRoomFacility(rf8);
        KRSW_702.addRoomFacility(rf9);
        KRSW_702.addRoomFacility(rf10);
        KRSW_702.addRoomFacility(rf11);
        KRSW_702.addRoomFacility(rf12);
        KRSW_702.addRoomFacility(rf13);
        KRSW_702.addRoomFacility(rf14);
        KRSW_702.addRoomFacility(rf15);

        KRSW_702.addMinibarItem(m1);
        KRSW_702.addMinibarItem(m2);
        KRSW_702.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSW_702);

        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_702"));

        Room KRSW_703 = new Room("KRSW_703", "703", "Premium", 4, "Occupied", h10);

        KRSW_703.addRoomFacility(rf1);
        KRSW_703.addRoomFacility(rf2);
        KRSW_703.addRoomFacility(rf3);
        KRSW_703.addRoomFacility(rf4);
        KRSW_703.addRoomFacility(rf5);
        KRSW_703.addRoomFacility(rf6);
        KRSW_703.addRoomFacility(rf7);
        KRSW_703.addRoomFacility(rf8);
        KRSW_703.addRoomFacility(rf9);
        KRSW_703.addRoomFacility(rf10);
        KRSW_703.addRoomFacility(rf11);
        KRSW_703.addRoomFacility(rf12);
        KRSW_703.addRoomFacility(rf13);
        KRSW_703.addRoomFacility(rf14);
        KRSW_703.addRoomFacility(rf15);

        KRSW_703.addMinibarItem(m1);
        KRSW_703.addMinibarItem(m2);
        KRSW_703.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSW_703);

        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_703"));

        Room KRSW_704 = new Room("KRSW_704", "704", "Premium", 4, "Available", h10);

        KRSW_704.addRoomFacility(rf1);
        KRSW_704.addRoomFacility(rf2);
        KRSW_704.addRoomFacility(rf3);
        KRSW_704.addRoomFacility(rf4);
        KRSW_704.addRoomFacility(rf5);
        KRSW_704.addRoomFacility(rf6);
        KRSW_704.addRoomFacility(rf7);
        KRSW_704.addRoomFacility(rf8);
        KRSW_704.addRoomFacility(rf9);
        KRSW_704.addRoomFacility(rf10);
        KRSW_704.addRoomFacility(rf11);
        KRSW_704.addRoomFacility(rf12);
        KRSW_704.addRoomFacility(rf13);
        KRSW_704.addRoomFacility(rf14);
        KRSW_704.addRoomFacility(rf15);

        KRSW_704.addMinibarItem(m1);
        KRSW_704.addMinibarItem(m2);
        KRSW_704.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSW_704);

        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_704"));

        Room KRSW_801 = new Room("KRSW_801", "801", "Premium", 4, "Available", h10);

        KRSW_801.addRoomFacility(rf1);
        KRSW_801.addRoomFacility(rf2);
        KRSW_801.addRoomFacility(rf3);
        KRSW_801.addRoomFacility(rf4);
        KRSW_801.addRoomFacility(rf5);
        KRSW_801.addRoomFacility(rf6);
        KRSW_801.addRoomFacility(rf7);
        KRSW_801.addRoomFacility(rf8);
        KRSW_801.addRoomFacility(rf9);
        KRSW_801.addRoomFacility(rf10);
        KRSW_801.addRoomFacility(rf11);
        KRSW_801.addRoomFacility(rf12);
        KRSW_801.addRoomFacility(rf13);
        KRSW_801.addRoomFacility(rf14);
        KRSW_801.addRoomFacility(rf15);

        KRSW_801.addMinibarItem(m1);
        KRSW_801.addMinibarItem(m2);
        KRSW_801.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSW_801);

        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_801"));

        Room KRSW_802 = new Room("KRSW_802", "802", "Premium", 4, "Available", h10);

        KRSW_802.addRoomFacility(rf1);
        KRSW_802.addRoomFacility(rf2);
        KRSW_802.addRoomFacility(rf3);
        KRSW_802.addRoomFacility(rf4);
        KRSW_802.addRoomFacility(rf5);
        KRSW_802.addRoomFacility(rf6);
        KRSW_802.addRoomFacility(rf7);
        KRSW_802.addRoomFacility(rf8);
        KRSW_802.addRoomFacility(rf9);
        KRSW_802.addRoomFacility(rf10);
        KRSW_802.addRoomFacility(rf11);
        KRSW_802.addRoomFacility(rf12);
        KRSW_802.addRoomFacility(rf13);
        KRSW_802.addRoomFacility(rf14);
        KRSW_802.addRoomFacility(rf15);

        KRSW_802.addMinibarItem(m1);
        KRSW_802.addMinibarItem(m2);
        KRSW_802.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSW_802);

        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_802"));

        Room KRSW_803 = new Room("KRSW_803", "803", "Premium", 4, "Available", h10);

        KRSW_803.addRoomFacility(rf1);
        KRSW_803.addRoomFacility(rf2);
        KRSW_803.addRoomFacility(rf3);
        KRSW_803.addRoomFacility(rf4);
        KRSW_803.addRoomFacility(rf5);
        KRSW_803.addRoomFacility(rf6);
        KRSW_803.addRoomFacility(rf7);
        KRSW_803.addRoomFacility(rf8);
        KRSW_803.addRoomFacility(rf9);
        KRSW_803.addRoomFacility(rf10);
        KRSW_803.addRoomFacility(rf11);
        KRSW_803.addRoomFacility(rf12);
        KRSW_803.addRoomFacility(rf13);
        KRSW_803.addRoomFacility(rf14);
        KRSW_803.addRoomFacility(rf15);

        KRSW_803.addMinibarItem(m1);
        KRSW_803.addMinibarItem(m2);
        KRSW_803.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSW_803);

        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_803"));

        Room KRSW_804 = new Room("KRSW_804", "804", "Premium", 4, "Unavailable", h10);

        KRSW_804.addRoomFacility(rf1);
        KRSW_804.addRoomFacility(rf2);
        KRSW_804.addRoomFacility(rf3);
        KRSW_804.addRoomFacility(rf4);
        KRSW_804.addRoomFacility(rf5);
        KRSW_804.addRoomFacility(rf6);
        KRSW_804.addRoomFacility(rf7);
        KRSW_804.addRoomFacility(rf8);
        KRSW_804.addRoomFacility(rf9);
        KRSW_804.addRoomFacility(rf10);
        KRSW_804.addRoomFacility(rf11);
        KRSW_804.addRoomFacility(rf12);
        KRSW_804.addRoomFacility(rf13);
        KRSW_804.addRoomFacility(rf14);
        KRSW_804.addRoomFacility(rf15);

        KRSW_804.addMinibarItem(m1);
        KRSW_804.addMinibarItem(m2);
        KRSW_804.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSW_804);

        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_804"));

        Room KRSW_901 = new Room("KRSW_901", "901", "Premium", 4, "Occupied", h10);

        KRSW_901.addRoomFacility(rf1);
        KRSW_901.addRoomFacility(rf2);
        KRSW_901.addRoomFacility(rf3);
        KRSW_901.addRoomFacility(rf4);
        KRSW_901.addRoomFacility(rf5);
        KRSW_901.addRoomFacility(rf6);
        KRSW_901.addRoomFacility(rf7);
        KRSW_901.addRoomFacility(rf8);
        KRSW_901.addRoomFacility(rf9);
        KRSW_901.addRoomFacility(rf10);
        KRSW_901.addRoomFacility(rf11);
        KRSW_901.addRoomFacility(rf12);
        KRSW_901.addRoomFacility(rf13);
        KRSW_901.addRoomFacility(rf14);
        KRSW_901.addRoomFacility(rf15);

        KRSW_901.addMinibarItem(m1);
        KRSW_901.addMinibarItem(m2);
        KRSW_901.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSW_901);

        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_901"));

        Room KRSW_902 = new Room("KRSW_902", "902", "Premium", 4, "Available", h10);

        KRSW_902.addRoomFacility(rf1);
        KRSW_902.addRoomFacility(rf2);
        KRSW_902.addRoomFacility(rf3);
        KRSW_902.addRoomFacility(rf4);
        KRSW_902.addRoomFacility(rf5);
        KRSW_902.addRoomFacility(rf6);
        KRSW_902.addRoomFacility(rf7);
        KRSW_902.addRoomFacility(rf8);
        KRSW_902.addRoomFacility(rf9);
        KRSW_902.addRoomFacility(rf10);
        KRSW_902.addRoomFacility(rf11);
        KRSW_902.addRoomFacility(rf12);
        KRSW_902.addRoomFacility(rf13);
        KRSW_902.addRoomFacility(rf14);
        KRSW_902.addRoomFacility(rf15);

        KRSW_902.addMinibarItem(m1);
        KRSW_902.addMinibarItem(m2);
        KRSW_902.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSW_902);

        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_902"));

        Room KRSW_903 = new Room("KRSW_903", "903", "Premium", 4, "Available", h10);

        KRSW_903.addRoomFacility(rf1);
        KRSW_903.addRoomFacility(rf2);
        KRSW_903.addRoomFacility(rf3);
        KRSW_903.addRoomFacility(rf4);
        KRSW_903.addRoomFacility(rf5);
        KRSW_903.addRoomFacility(rf6);
        KRSW_903.addRoomFacility(rf7);
        KRSW_903.addRoomFacility(rf8);
        KRSW_903.addRoomFacility(rf9);
        KRSW_903.addRoomFacility(rf10);
        KRSW_903.addRoomFacility(rf11);
        KRSW_903.addRoomFacility(rf12);
        KRSW_903.addRoomFacility(rf13);
        KRSW_903.addRoomFacility(rf14);
        KRSW_903.addRoomFacility(rf15);

        KRSW_903.addMinibarItem(m1);
        KRSW_903.addMinibarItem(m2);
        KRSW_903.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSW_903);

        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_903"));

        Room KRSW_904 = new Room("KRSW_904", "904", "Premium", 4, "Available", h10);

        KRSW_904.addRoomFacility(rf1);
        KRSW_904.addRoomFacility(rf2);
        KRSW_904.addRoomFacility(rf3);
        KRSW_904.addRoomFacility(rf4);
        KRSW_904.addRoomFacility(rf5);
        KRSW_904.addRoomFacility(rf6);
        KRSW_904.addRoomFacility(rf7);
        KRSW_904.addRoomFacility(rf8);
        KRSW_904.addRoomFacility(rf9);
        KRSW_904.addRoomFacility(rf10);
        KRSW_904.addRoomFacility(rf11);
        KRSW_904.addRoomFacility(rf12);
        KRSW_904.addRoomFacility(rf13);
        KRSW_904.addRoomFacility(rf14);
        KRSW_904.addRoomFacility(rf15);

        KRSW_904.addMinibarItem(m1);
        KRSW_904.addMinibarItem(m2);
        KRSW_904.addMinibarItem(m3);

        roomSessionLocal.createRoom(KRSW_904);

        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_904"));

        Room KRSW_1201 = new Room("KRSW_1201", "1201", "Suite", 4, "Occupied", h10);

        KRSW_1201.addRoomFacility(rf1);
        KRSW_1201.addRoomFacility(rf2);
        KRSW_1201.addRoomFacility(rf3);
        KRSW_1201.addRoomFacility(rf4);
        KRSW_1201.addRoomFacility(rf5);
        KRSW_1201.addRoomFacility(rf6);
        KRSW_1201.addRoomFacility(rf7);
        KRSW_1201.addRoomFacility(rf8);
        KRSW_1201.addRoomFacility(rf9);
        KRSW_1201.addRoomFacility(rf10);
        KRSW_1201.addRoomFacility(rf11);
        KRSW_1201.addRoomFacility(rf12);
        KRSW_1201.addRoomFacility(rf13);
        KRSW_1201.addRoomFacility(rf14);
        KRSW_1201.addRoomFacility(rf15);
        KRSW_1201.addRoomFacility(rf16);
        KRSW_1201.addRoomFacility(rf17);
        KRSW_1201.addRoomFacility(rf18);
        KRSW_1201.addRoomFacility(rf19);

        KRSW_1201.addMinibarItem(m1);
        KRSW_1201.addMinibarItem(m2);
        KRSW_1201.addMinibarItem(m3);
        KRSW_1201.addMinibarItem(m4);
        KRSW_1201.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRSW_1201);

        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_1201"));

        Room KRSW_1202 = new Room("KRSW_1202", "1202", "Suite", 4, "Unavailable", h10);

        KRSW_1202.addRoomFacility(rf1);
        KRSW_1202.addRoomFacility(rf2);
        KRSW_1202.addRoomFacility(rf3);
        KRSW_1202.addRoomFacility(rf4);
        KRSW_1202.addRoomFacility(rf5);
        KRSW_1202.addRoomFacility(rf6);
        KRSW_1202.addRoomFacility(rf7);
        KRSW_1202.addRoomFacility(rf8);
        KRSW_1202.addRoomFacility(rf9);
        KRSW_1202.addRoomFacility(rf10);
        KRSW_1202.addRoomFacility(rf11);
        KRSW_1202.addRoomFacility(rf12);
        KRSW_1202.addRoomFacility(rf13);
        KRSW_1202.addRoomFacility(rf14);
        KRSW_1202.addRoomFacility(rf15);
        KRSW_1202.addRoomFacility(rf16);
        KRSW_1202.addRoomFacility(rf17);
        KRSW_1202.addRoomFacility(rf18);
        KRSW_1202.addRoomFacility(rf19);

        KRSW_1202.addMinibarItem(m1);
        KRSW_1202.addMinibarItem(m2);
        KRSW_1202.addMinibarItem(m3);
        KRSW_1202.addMinibarItem(m4);
        KRSW_1202.addMinibarItem(m5);

        roomSessionLocal.createRoom(KRSW_1202);

        h10.addRoom(roomSessionLocal.getRoomByName("KRSW_1202"));

        em.flush();

        HouseKeepingOrder ho1 = new HouseKeepingOrder(roomSessionLocal.getRoomByName("KRG_401"), "Incomplete", 4, new Date(), new Date(), null, "Toothpaste and hairnet", "toiletries", true);
        HouseKeepingOrder ho2 = new HouseKeepingOrder(roomSessionLocal.getRoomByName("KRG_402"), "Incomplete", 4, new Date(), new Date(), null, "Spoilt TV", "maintenance", false);
        HouseKeepingOrder ho3 = new HouseKeepingOrder(roomSessionLocal.getRoomByName("KRG_405"), "Incomplete", 4, new Date(), new Date(), null, "Spilled drinks", "housekeeping", true);
        HouseKeepingOrder ho4 = new HouseKeepingOrder(roomSessionLocal.getRoomByName("KRG_406"), "Incomplete", 4, new Date(), new Date(), null, "Dusty table", "housekeeping", true);
        HouseKeepingOrder ho5 = new HouseKeepingOrder(roomSessionLocal.getRoomByName("KRG_402"), "Incomplete", 4, new Date(), new Date(), null, "Tv not working", "maintenance", true);
        HouseKeepingOrder ho8 = new HouseKeepingOrder(roomSessionLocal.getRoomByName("KRG_408"), "Incomplete", 4, new Date(), new Date(), null, "Mineral water refill", "housekeeping", true);
        HouseKeepingOrder ho9 = new HouseKeepingOrder(roomSessionLocal.getRoomByName("KRG_407"), "Incomplete", 4, new Date(), new Date(), null, "Clogged Sink", "maintenance", true);

        houseKeepingOrderSessionLocal.createHouseKeepingOrder(ho1);
        houseKeepingOrderSessionLocal.createHouseKeepingOrder(ho2);
        houseKeepingOrderSessionLocal.createHouseKeepingOrder(ho3);
        houseKeepingOrderSessionLocal.createHouseKeepingOrder(ho4);
        houseKeepingOrderSessionLocal.createHouseKeepingOrder(ho5);
        houseKeepingOrderSessionLocal.createHouseKeepingOrder(ho8);
        houseKeepingOrderSessionLocal.createHouseKeepingOrder(ho9);
        em.flush();
    }

    public void intializeRoomBookingsAndCustomer() throws ParseException, NoResultException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Customer customer1 = new Customer();
        customer1.setAccountStatus(true);
        customer1.setDateJoined(new Date());
        customer1.setEmail("Congx2@hotmail.com");
        customer1.setMember(true);
        customer1.setMobileNum("94308808");
        customer1.setFirstName("Lim");
        customer1.setLastName("Dian Cong");
        customer1.setGender("Male");
        customer1.setPassword(encryptPassword("1234"));
        customer1.setPoints(20);

        customerSessionLocal.createCustomer(customer1);
        em.flush();

        Customer customer2 = new Customer();
        customer2.setAccountStatus(true);
        customer2.setDateJoined(new Date());
        customer2.setEmail("Stanley@hotmail.com");
        customer2.setMember(true);
        customer2.setMobileNum("97628485");
        customer2.setFirstName("Loh");
        customer2.setLastName("Stanley");
        customer2.setGender("Male");
        customer2.setPassword(encryptPassword("1234"));
        customer2.setPoints(1);

        customerSessionLocal.createCustomer(customer2);
        em.flush();

        Customer customer3 = new Customer();
        customer3.setAccountStatus(true);
        customer3.setDateJoined(new Date());
        customer3.setEmail("NgFengLong@hotmail.com");
        customer3.setMember(true);
        customer3.setMobileNum("9668913");
        customer3.setFirstName("Ng");
        customer3.setLastName("Feng Long");
        customer3.setGender("Male");
        customer3.setPassword(encryptPassword("1234"));
        customer3.setPoints(1);

        customerSessionLocal.createCustomer(customer3);
        em.flush();

        CreditCard creditCard1 = new CreditCard();
        creditCard1.setCardNum(encryptPassword("1234123412341234"));
        creditCard1.setCvv(encryptPassword("123"));
        creditCard1.setExpiryDate("2021-04-01");

        Date date = format.parse("2019-04-01");

        RoomBooking rm1 = new RoomBooking();
        rm1.setBookInDate(new Date());
        rm1.setBookOutDate(date);
        rm1.setBookedBy(customer1);
        rm1.setBookedRoom(roomSessionLocal.getRoomByName("KRG_302"));
        rm1.setEmailAddress(customer1.getEmail());
        rm1.setHasTransport(false);
        rm1.setLastName(customer1.getLastName());
        rm1.setPassportNum("A0173719Y");
        rm1.setPrice(2000.0);
        rm1.setRoomType("Standard");
        rm1.setStatus("Incomplete");
        bookingSessionLocal.createRoomBooking(rm1);
		
        PaymentTransaction PT1 = new PaymentTransaction();
        PT1.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT1.setCreditCard(null);
        PT1.setPayer(customer1);
        PT1.setTransactionDateTime(new Date());
        PT1.setInitialPayment(2000);
        PT1.setFinalPayment(2000);
        paymentTransactionSessionLocal.createPaymentTransaction(PT1);		

         RoomBooking rb1 = new RoomBooking();
        rb1.setBookInDate(format.parse("2019-03-10"));
        rb1.setBookOutDate(format.parse("2019-03-13"));
        rb1.setStatus("checkedOut");
        rb1.setPrice(450.0);
        rb1.setBookedRoom(roomSessionLocal.getRoomByName("KRG_202"));
        rb1.setHasTransport(false);
//        rb1.setBookedBy(customerSessionLocal.getCustomerByEmail("neoguoh202@hotmail.com"));
        rb1.setBookedBy(customerSessionLocal.getCustomerByEmail("zell1502@hotmail.com"));
        rb1.setEmailAddress("zell1502@hotmail.com");
        rb1.setPassportNum("E2342213B");
        rb1.setRoomType("Standard");
        rb1.setFirstName("Zack");
        rb1.setLastName("Neo Guohui");
        rb1.setHasExtraBed(false);
        bookingSessionLocal.createRoomBooking(rb1);
        RoomBooking newrb1 = bookingSessionLocal.getLastRoomBooking();
        
        RoomBooking rb2 = new RoomBooking();
        rb2.setBookInDate(format.parse("2019-01-10"));
        rb2.setBookOutDate(format.parse("2019-01-13"));
        rb2.setStatus("checkedOut");
        rb2.setPrice(450.0);
        rb2.setBookedRoom(roomSessionLocal.getRoomByName("KRG_402"));
        rb2.setHasTransport(false);
        rb2.setBookedBy(customerSessionLocal.getCustomerByEmail("zell1502@hotmail.com"));
        rb2.setEmailAddress("zell1502@hotmail.com");
        rb2.setPassportNum("E2342213B");
        rb2.setRoomType("Standard");
        rb2.setFirstName("Zack");
        rb2.setLastName("Neo Guohui");
        rb2.setHasExtraBed(true);
        bookingSessionLocal.createRoomBooking(rb2);
        RoomBooking newrb2 = bookingSessionLocal.getLastRoomBooking();
        
        RoomBooking rb3 = new RoomBooking();
        rb3.setBookInDate(format.parse("2019-05-10"));
        rb3.setBookOutDate(format.parse("2019-05-13"));
        rb3.setStatus("reserved");
        rb3.setPrice(450.0);
        rb3.setBookedRoom(roomSessionLocal.getRoomByName("KRG_302"));
        rb3.setHasTransport(false);
        rb3.setBookedBy(customerSessionLocal.getCustomerByEmail("zell1502@hotmail.com"));
        rb3.setEmailAddress("zell1502@hotmail.com");
        rb3.setPassportNum("E2342213B");
        rb3.setRoomType("Standard");
        rb3.setFirstName("Zack");
        rb3.setLastName("Neo Guohui");
        rb3.setHasExtraBed(true);
        bookingSessionLocal.createRoomBooking(rb3);
        RoomBooking newrb3 = bookingSessionLocal.getLastRoomBooking();        

//***************Laundry Order***************
        LaundryOrder lo1 = new LaundryOrder();
        lo1.setRoom(roomSessionLocal.getRoomByName("KRG_202"));
        lo1.setOrderDateTime(formatTime.parse("2019-03-10 12:28:29"));
        lo1.setStatus("Delivered");
        lo1.setCompleteDateTime(format.parse("2019-03-12"));
        lo1.setHouseKeeper(staffSessionLocal.getStaffByNric("S1730049J"));
        lo1.setSpecialRequest("you may come in and collect the coat if I'm not in the room");
        laundrySessionLocal.createLaundryOrder(lo1);
        LaundryOrder newlo1 = laundrySessionLocal.getLastLaundryOrder();
       

        LaundryOrderedItem loi1 = new LaundryOrderedItem();
        loi1.setQty(2);
        loi1.setLaundryType(laundrySessionLocal.getLaundryTypeByName("Top (Dry-Wash)"));
        loi1.setDescription("2x Black Coat");
        laundrySessionLocal.createLaundryOrderedItem(loi1);
        newlo1.addLaundryOrderedItem(laundrySessionLocal.getLastLaundryOrderedItem());

        LaundryOrderedItem loi2 = new LaundryOrderedItem();
        loi2.setQty(1);
        loi2.setLaundryType(laundrySessionLocal.getLaundryTypeByName("Top (Wash)"));
        loi2.setDescription("1x Coat");
        laundrySessionLocal.createLaundryOrderedItem(loi2);
        newlo1.addLaundryOrderedItem(laundrySessionLocal.getLastLaundryOrderedItem());
        newlo1.setTotalPrice(42.0);
        newrb1.addLaundryOrder(newlo1);

        LaundryOrder lo5 = new LaundryOrder();
        lo5.setRoom(roomSessionLocal.getRoomByName("KRG_402"));
        lo5.setOrderDateTime( formatTime.parse("2019-03-10 10:45:00"));
        lo5.setStatus("Delivered");
        lo5.setCompleteDateTime(format.parse("2019-01-12"));
        lo5.setHouseKeeper(staffSessionLocal.getStaffByNric("S1730049J"));
        lo5.setSpecialRequest("");
        laundrySessionLocal.createLaundryOrder(lo5);
        LaundryOrder newlo2 = laundrySessionLocal.getLastLaundryOrder();

        LaundryOrderedItem loi5 = new LaundryOrderedItem();
        loi5.setQty(1);
        loi5.setLaundryType(laundrySessionLocal.getLaundryTypeByName("Top (Dry-Wash)"));
        loi5.setDescription("1x Black Coat");
        laundrySessionLocal.createLaundryOrderedItem(loi5);
        newlo2.addLaundryOrderedItem(laundrySessionLocal.getLastLaundryOrderedItem());

        newlo2.setTotalPrice(15.0);
        newrb1.addLaundryOrder(newlo2);
        
        LaundryOrder lo6 = new LaundryOrder();
        lo6.setRoom(roomSessionLocal.getRoomByName("KRG_501"));
        lo6.setOrderDateTime ( formatTime.parse("2019-03-10 14:44:34"));
        lo6.setStatus("Pending");
        lo6.setCompleteDateTime(format.parse("2019-04-25"));
        lo6.setSpecialRequest("");
        laundrySessionLocal.createLaundryOrder(lo6);
        
        LaundryOrder lo7 = new LaundryOrder();
        lo7.setRoom(roomSessionLocal.getRoomByName("KRG_801"));
        lo7.setOrderDateTime ( formatTime.parse("2019-03-10 17:24:43"));
        lo7.setStatus("Pending");
        lo7.setCompleteDateTime(format.parse("2019-04-25"));
        lo7.setSpecialRequest("");
        laundrySessionLocal.createLaundryOrder(lo7);        
        
        LaundryOrder lo8 = new LaundryOrder();
        lo8.setRoom(roomSessionLocal.getRoomByName("KRG_704"));
        lo8.setOrderDateTime (formatTime.parse("2019-03-10 21:00:00"));
        lo8.setStatus("In Progress");
        lo8.setCompleteDateTime(format.parse("2019-04-24"));
        lo8.setHouseKeeper(staffSessionLocal.getStaffByNric("S1730049J"));
        lo8.setSpecialRequest("");
        laundrySessionLocal.createLaundryOrder(lo8);
        LaundryOrder newlo3 = laundrySessionLocal.getLastLaundryOrder();
        
        LaundryOrderedItem loi6 = new LaundryOrderedItem();
        loi6.setQty(1);
        loi6.setLaundryType(laundrySessionLocal.getLaundryTypeByName("Bottom (Dry-Wash)"));
        loi6.setDescription("1x Yoga Pants");
        laundrySessionLocal.createLaundryOrderedItem(loi6);
        newlo3.addLaundryOrderedItem(laundrySessionLocal.getLastLaundryOrderedItem());
        newlo3.setTotalPrice(15.0);
        
        LaundryOrder lo9 = new LaundryOrder();
        lo9.setRoom(roomSessionLocal.getRoomByName("KRG_603"));
        lo9.setOrderDateTime(formatTime.parse("2019-03-10 16:28:29"));
        lo9.setStatus("In Progress");
        lo9.setCompleteDateTime(format.parse("2019-04-24"));
        lo9.setHouseKeeper(staffSessionLocal.getStaffByNric("S1730049J"));
        lo9.setSpecialRequest("");
        laundrySessionLocal.createLaundryOrder(lo9);
        LaundryOrder newlo4 = laundrySessionLocal.getLastLaundryOrder();
        
        LaundryOrderedItem loi7 = new LaundryOrderedItem();
        loi7.setQty(1);
        loi7.setLaundryType(laundrySessionLocal.getLaundryTypeByName("Top (Dry-Wash)"));
        loi7.setDescription("1x Coat");
        laundrySessionLocal.createLaundryOrderedItem(loi7);
        newlo4.addLaundryOrderedItem(laundrySessionLocal.getLastLaundryOrderedItem());
        newlo4.setTotalPrice(15.0); 
        
        LaundryOrder lo10 = new LaundryOrder();
        lo10.setRoom(roomSessionLocal.getRoomByName("KRG_608"));
        lo10.setOrderDateTime( formatTime.parse("2019-03-10 15:28:29"));
        lo10.setStatus("Ready for Delivery");
        lo10.setCompleteDateTime(format.parse("2019-04-22"));
        lo10.setHouseKeeper(staffSessionLocal.getStaffByNric("S1730049J"));
        lo10.setSpecialRequest("");
        laundrySessionLocal.createLaundryOrder(lo10);
        LaundryOrder newlo5 = laundrySessionLocal.getLastLaundryOrder();
        
        LaundryOrderedItem loi8 = new LaundryOrderedItem();
        loi8.setQty(1);
        loi8.setLaundryType(laundrySessionLocal.getLaundryTypeByName("Top (Dry-Wash)"));
        loi8.setDescription("1x Jacket");
        laundrySessionLocal.createLaundryOrderedItem(loi8);
        newlo5.addLaundryOrderedItem(laundrySessionLocal.getLastLaundryOrderedItem());
        newlo5.setTotalPrice(15.0);
        
        LaundryOrder lo11 = new LaundryOrder();
        lo11.setRoom(roomSessionLocal.getRoomByName("KRG_704"));
        lo11.setOrderDateTime(formatTime.parse("2019-03-10 12:38:29"));
        lo11.setStatus("Ready for Delivery");
        lo11.setCompleteDateTime(format.parse("2019-04-22"));
        lo11.setHouseKeeper(staffSessionLocal.getStaffByNric("S1730049J"));
        lo11.setSpecialRequest("");
        laundrySessionLocal.createLaundryOrder(lo11);
        LaundryOrder newlo6 = laundrySessionLocal.getLastLaundryOrder();
        
        LaundryOrderedItem loi9 = new LaundryOrderedItem();
        loi9.setQty(1);
        loi9.setLaundryType(laundrySessionLocal.getLaundryTypeByName("Top (Dry-Wash)"));
        loi9.setDescription("1x Suit");
        laundrySessionLocal.createLaundryOrderedItem(loi9);
        newlo6.addLaundryOrderedItem(laundrySessionLocal.getLastLaundryOrderedItem());
        newlo6.setTotalPrice(15.0);            
        
        

//***************Food Order***************        
        FoodOrder fo1 = new FoodOrder();
        foodMenuItemSessionLocal.createFoodOrder(fo1);
        FoodOrder newfo1 = foodMenuItemSessionLocal.getLastFoodOrder();

        FoodOrderedItem foi1 = new FoodOrderedItem();
        foi1.setQty(2);
        foi1.setFood(foodMenuItemSessionLocal.getFoodMenuItemByName("Coke"));
        foodMenuItemSessionLocal.createFoodOrderedItem(foi1);
        newfo1.addFoodOrderedItem(foodMenuItemSessionLocal.getLastFoodOrderedItem());

        FoodOrderedItem foi2 = new FoodOrderedItem();
        foi2.setQty(1);
        foi2.setFood(foodMenuItemSessionLocal.getFoodMenuItemByName("Pancakes"));
        foodMenuItemSessionLocal.createFoodOrderedItem(foi2);
        newfo1.addFoodOrderedItem(foodMenuItemSessionLocal.getLastFoodOrderedItem());

        FoodOrderedItem foi3 = new FoodOrderedItem();
        foi3.setQty(1);
        foi3.setFood(foodMenuItemSessionLocal.getFoodMenuItemByName("Scrambled Eggs"));
        foodMenuItemSessionLocal.createFoodOrderedItem(foi3);
        newfo1.addFoodOrderedItem(foodMenuItemSessionLocal.getLastFoodOrderedItem());

        newfo1.setTotalPrice(54.0);
        newrb1.addFoodOrder(newfo1);

        FoodOrder fo2 = new FoodOrder();
        foodMenuItemSessionLocal.createFoodOrder(fo2);
        FoodOrder newfo2 = foodMenuItemSessionLocal.getLastFoodOrder();

        FoodOrderedItem foi4 = new FoodOrderedItem();
        foi4.setQty(2);
        foi4.setFood(foodMenuItemSessionLocal.getFoodMenuItemByName("Coke"));
        foodMenuItemSessionLocal.createFoodOrderedItem(foi4);
        newfo2.addFoodOrderedItem(foodMenuItemSessionLocal.getLastFoodOrderedItem());

        FoodOrderedItem foi5 = new FoodOrderedItem();
        foi5.setQty(1);
        foi5.setFood(foodMenuItemSessionLocal.getFoodMenuItemByName("Pancakes"));
        foodMenuItemSessionLocal.createFoodOrderedItem(foi5);
        newfo2.addFoodOrderedItem(foodMenuItemSessionLocal.getLastFoodOrderedItem());

        newfo2.setTotalPrice(32.0);
        newrb2.addFoodOrder(newfo2);

//***************Minibar Order***************        
        MinibarOrder mo1 = new MinibarOrder();
        houseKeepingOrderSessionLocal.createMinibarOrder(mo1);
        MinibarOrder newmo1 = houseKeepingOrderSessionLocal.getLastMinibarOrder();

        MinibarOrderedItem moi1 = new MinibarOrderedItem();
        moi1.setQty(2);
        moi1.setMinibarItem(houseKeepingOrderSessionLocal.getMinibarItemByItemName("Coke"));
        houseKeepingOrderSessionLocal.createMinibarOrderedItem(moi1);
        newmo1.addMinibarOrderedItem(houseKeepingOrderSessionLocal.getLastMinibarOrderedItem());

        MinibarOrderedItem moi2 = new MinibarOrderedItem();
        moi2.setQty(5);
        moi2.setMinibarItem(houseKeepingOrderSessionLocal.getMinibarItemByItemName("Beer"));
        houseKeepingOrderSessionLocal.createMinibarOrderedItem(moi2);
        newmo1.addMinibarOrderedItem(houseKeepingOrderSessionLocal.getLastMinibarOrderedItem());

        newmo1.setTotalPrice(89.0);
        newrb1.addMinibarOrder(newmo1);

        MinibarOrder mo2 = new MinibarOrder();
        houseKeepingOrderSessionLocal.createMinibarOrder(mo2);
        MinibarOrder newmo2 = houseKeepingOrderSessionLocal.getLastMinibarOrder();

        MinibarOrderedItem moi3 = new MinibarOrderedItem();
        moi3.setQty(2);
        moi3.setMinibarItem(houseKeepingOrderSessionLocal.getMinibarItemByItemName("Coke"));
        houseKeepingOrderSessionLocal.createMinibarOrderedItem(moi3);
        newmo2.addMinibarOrderedItem(houseKeepingOrderSessionLocal.getLastMinibarOrderedItem());

        newmo2.setTotalPrice(14.0);
        newrb2.addMinibarOrder(newmo2);

        em.flush();
		
        RoomBooking rb10 = new RoomBooking();
        rb10.setBookedRoom(roomSessionLocal.getRoomByName("KRG_202"));
        rb10.setBookInDate(format.parse("2019-02-18"));
        bookingSessionLocal.createRoomBooking(rb10);
        PaymentTransaction PT2 = new PaymentTransaction();
        PT2.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT2.setTransactionDateTime(format.parse("2019-01-30"));
        PT2.setFinalPayment(753.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT2);

        RoomBooking rb11 = new RoomBooking();
        rb11.setBookedRoom(roomSessionLocal.getRoomByName("KRG_203"));
        rb11.setBookInDate(format.parse("2019-02-10"));
        bookingSessionLocal.createRoomBooking(rb11);
        PaymentTransaction PT3 = new PaymentTransaction();
        PT3.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT3.setTransactionDateTime(format.parse("2019-02-03"));
        PT3.setFinalPayment(826.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT3);

        RoomBooking rb12 = new RoomBooking();
        rb12.setBookedRoom(roomSessionLocal.getRoomByName("KRN_205"));
        rb12.setBookInDate(format.parse("2019-03-19"));
        bookingSessionLocal.createRoomBooking(rb12);
        PaymentTransaction PT4 = new PaymentTransaction();
        PT4.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT4.setTransactionDateTime(format.parse("2019-03-10"));
        PT4.setFinalPayment(241.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT4);

        RoomBooking rb13 = new RoomBooking();
        rb13.setBookedRoom(roomSessionLocal.getRoomByName("KRN_205"));
        rb13.setBookInDate(format.parse("2019-04-28"));
        bookingSessionLocal.createRoomBooking(rb13);
        PaymentTransaction PT5 = new PaymentTransaction();
        PT5.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT5.setTransactionDateTime(format.parse("2019-04-19"));
        PT5.setFinalPayment(612.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT5);

        RoomBooking rb14 = new RoomBooking();
        rb14.setBookedRoom(roomSessionLocal.getRoomByName("KRS_205"));
        rb14.setBookInDate(format.parse("2019-03-19"));
        bookingSessionLocal.createRoomBooking(rb14);
        PaymentTransaction PT6 = new PaymentTransaction();
        PT6.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        RoomBooking rb15 = new RoomBooking();
        rb15.setBookedRoom(roomSessionLocal.getRoomByName("KRS_206"));
        rb15.setBookInDate(format.parse("2019-03-19"));
        bookingSessionLocal.createRoomBooking(rb15);
        PT6.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT6.setTransactionDateTime(format.parse("2019-03-10"));
        PT6.setFinalPayment(816.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT6);

//KRW Payment Transaction        
        RoomBooking rb300 = new RoomBooking();
        rb300.setBookedRoom(roomSessionLocal.getRoomByName("KRW_205"));
        rb300.setBookInDate(format.parse("2019-01-29"));
        bookingSessionLocal.createRoomBooking(rb300);
        PaymentTransaction PT300 = new PaymentTransaction();
        PT300.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT300.setTransactionDateTime(format.parse("2019-01-10"));
        PT300.setFinalPayment(716.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT300);

        RoomBooking rb301 = new RoomBooking();
        rb301.setBookedRoom(roomSessionLocal.getRoomByName("KRW_305"));
        rb301.setBookInDate(format.parse("2019-01-29"));
        bookingSessionLocal.createRoomBooking(rb301);
        PaymentTransaction PT301 = new PaymentTransaction();
        PT301.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT301.setTransactionDateTime(format.parse("2019-01-12"));
        PT301.setFinalPayment(434.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT301);

        RoomBooking rb302 = new RoomBooking();
        rb302.setBookedRoom(roomSessionLocal.getRoomByName("KRW_203"));
        rb302.setBookInDate(format.parse("2019-01-19"));
        bookingSessionLocal.createRoomBooking(rb302);
        PaymentTransaction PT302 = new PaymentTransaction();
        PT302.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT302.setTransactionDateTime(format.parse("2019-01-02"));
        PT302.setFinalPayment(697.39);
        paymentTransactionSessionLocal.createPaymentTransaction(PT302);

        RoomBooking rb303 = new RoomBooking();
        rb303.setBookedRoom(roomSessionLocal.getRoomByName("KRW_403"));
        rb303.setBookInDate(format.parse("2019-01-07"));
        bookingSessionLocal.createRoomBooking(rb303);
        PaymentTransaction PT303 = new PaymentTransaction();
        PT303.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT303.setTransactionDateTime(format.parse("2019-01-03"));
        PT303.setFinalPayment(652.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT303);

        RoomBooking rb304 = new RoomBooking();
        rb304.setBookedRoom(roomSessionLocal.getRoomByName("KRW_203"));
        rb304.setBookInDate(format.parse("2019-01-19"));
        bookingSessionLocal.createRoomBooking(rb304);
        PaymentTransaction PT304 = new PaymentTransaction();
        PT304.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT304.setTransactionDateTime(format.parse("2019-01-13"));
        PT304.setFinalPayment(417.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT304);

        RoomBooking rb305 = new RoomBooking();
        rb305.setBookedRoom(roomSessionLocal.getRoomByName("KRW_204"));
        rb305.setBookInDate(format.parse("2019-01-27"));
        bookingSessionLocal.createRoomBooking(rb305);
        PaymentTransaction PT305 = new PaymentTransaction();
        PT305.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT305.setTransactionDateTime(format.parse("2019-01-23"));
        PT305.setFinalPayment(817.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT305);

        RoomBooking rb306 = new RoomBooking();
        rb306.setBookedRoom(roomSessionLocal.getRoomByName("KRW_206"));
        rb306.setBookInDate(format.parse("2019-01-18"));
        bookingSessionLocal.createRoomBooking(rb306);
        PaymentTransaction PT306 = new PaymentTransaction();
        PT306.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT306.setTransactionDateTime(format.parse("2019-01-12"));
        PT306.setFinalPayment(471.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT306);

        RoomBooking rb307 = new RoomBooking();
        rb307.setBookedRoom(roomSessionLocal.getRoomByName("KRW_207"));
        rb307.setBookInDate(format.parse("2019-01-30"));
        bookingSessionLocal.createRoomBooking(rb307);
        PaymentTransaction PT307 = new PaymentTransaction();
        PT307.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT307.setTransactionDateTime(format.parse("2019-01-27"));
        PT307.setFinalPayment(512.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT307);

        RoomBooking rb308 = new RoomBooking();
        rb308.setBookedRoom(roomSessionLocal.getRoomByName("KRW_406"));
        rb308.setBookInDate(format.parse("2019-01-18"));
        bookingSessionLocal.createRoomBooking(rb308);
        PaymentTransaction PT308 = new PaymentTransaction();
        PT308.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT308.setTransactionDateTime(format.parse("2019-01-15"));
        PT308.setFinalPayment(671.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT308);

        RoomBooking rb309 = new RoomBooking();
        rb309.setBookedRoom(roomSessionLocal.getRoomByName("KRW_306"));
        rb309.setBookInDate(format.parse("2019-01-29"));
        bookingSessionLocal.createRoomBooking(rb309);
        PaymentTransaction PT309 = new PaymentTransaction();
        PT309.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT309.setTransactionDateTime(format.parse("2019-01-25"));
        PT309.setFinalPayment(273.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT309);

        RoomBooking rb310 = new RoomBooking();
        rb310.setBookedRoom(roomSessionLocal.getRoomByName("KRW_203"));
        rb310.setBookInDate(format.parse("2019-01-17"));
        bookingSessionLocal.createRoomBooking(rb310);
        PaymentTransaction PT310 = new PaymentTransaction();
        PT310.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT310.setTransactionDateTime(format.parse("2019-01-09"));
        PT310.setFinalPayment(134.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT310);

        RoomBooking rb311 = new RoomBooking();
        rb311.setBookedRoom(roomSessionLocal.getRoomByName("KRW_203"));
        rb311.setBookInDate(format.parse("2019-02-17"));
        bookingSessionLocal.createRoomBooking(rb311);
        PaymentTransaction PT311 = new PaymentTransaction();
        PT311.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT311.setTransactionDateTime(format.parse("2019-02-09"));
        PT311.setFinalPayment(512.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT311);

        RoomBooking rb312 = new RoomBooking();
        rb312.setBookedRoom(roomSessionLocal.getRoomByName("KRW_503"));
        rb312.setBookInDate(format.parse("2019-02-25"));
        bookingSessionLocal.createRoomBooking(rb312);
        PaymentTransaction PT312 = new PaymentTransaction();
        PT312.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT312.setTransactionDateTime(format.parse("2019-02-19"));
        PT312.setFinalPayment(521.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT312);

        RoomBooking rb313 = new RoomBooking();
        rb313.setBookedRoom(roomSessionLocal.getRoomByName("KRW_504"));
        rb313.setBookInDate(format.parse("2019-02-27"));
        bookingSessionLocal.createRoomBooking(rb313);
        PaymentTransaction PT313 = new PaymentTransaction();
        PT313.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT313.setTransactionDateTime(format.parse("2019-02-21"));
        PT313.setFinalPayment(512.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT313);

        RoomBooking rb314 = new RoomBooking();
        rb314.setBookedRoom(roomSessionLocal.getRoomByName("KRW_504"));
        rb314.setBookInDate(format.parse("2019-02-09"));
        bookingSessionLocal.createRoomBooking(rb314);
        PaymentTransaction PT314 = new PaymentTransaction();
        PT314.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT314.setTransactionDateTime(format.parse("2019-02-04"));
        PT314.setFinalPayment(471.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT314);

        RoomBooking rb315 = new RoomBooking();
        rb315.setBookedRoom(roomSessionLocal.getRoomByName("KRW_505"));
        rb315.setBookInDate(format.parse("2019-02-09"));
        bookingSessionLocal.createRoomBooking(rb315);
        PaymentTransaction PT315 = new PaymentTransaction();
        PT315.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT315.setTransactionDateTime(format.parse("2019-02-05"));
        PT315.setFinalPayment(531.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT315);

        RoomBooking rb316 = new RoomBooking();
        rb316.setBookedRoom(roomSessionLocal.getRoomByName("KRW_506"));
        rb316.setBookInDate(format.parse("2019-02-09"));
        bookingSessionLocal.createRoomBooking(rb316);
        PaymentTransaction PT316 = new PaymentTransaction();
        PT316.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT316.setTransactionDateTime(format.parse("2019-02-06"));
        PT316.setFinalPayment(531.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT316);

        RoomBooking rb317 = new RoomBooking();
        rb317.setBookedRoom(roomSessionLocal.getRoomByName("KRW_507"));
        rb317.setBookInDate(format.parse("2019-02-10"));
        bookingSessionLocal.createRoomBooking(rb317);
        PaymentTransaction PT317 = new PaymentTransaction();
        PT317.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT317.setTransactionDateTime(format.parse("2019-02-07"));
        PT317.setFinalPayment(531.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT317);

        RoomBooking rb318 = new RoomBooking();
        rb318.setBookedRoom(roomSessionLocal.getRoomByName("KRW_508"));
        rb318.setBookInDate(format.parse("2019-02-13"));
        bookingSessionLocal.createRoomBooking(rb318);
        PaymentTransaction PT318 = new PaymentTransaction();
        PT318.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT318.setTransactionDateTime(format.parse("2019-02-08"));
        PT318.setFinalPayment(271.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT318);

        RoomBooking rb319 = new RoomBooking();
        rb319.setBookedRoom(roomSessionLocal.getRoomByName("KRW_601"));
        rb319.setBookInDate(format.parse("2019-02-15"));
        bookingSessionLocal.createRoomBooking(rb319);
        PaymentTransaction PT319 = new PaymentTransaction();
        PT319.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT319.setTransactionDateTime(format.parse("2019-02-09"));
        PT319.setFinalPayment(416.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT319);

        RoomBooking rb320 = new RoomBooking();
        rb320.setBookedRoom(roomSessionLocal.getRoomByName("KRW_602"));
        rb320.setBookInDate(format.parse("2019-02-21"));
        bookingSessionLocal.createRoomBooking(rb320);
        PaymentTransaction PT320 = new PaymentTransaction();
        PT320.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT320.setTransactionDateTime(format.parse("2019-02-10"));
        PT320.setFinalPayment(321.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT320);

        RoomBooking rb321 = new RoomBooking();
        rb321.setBookedRoom(roomSessionLocal.getRoomByName("KRW_201"));
        rb321.setBookInDate(format.parse("2019-03-09"));
        bookingSessionLocal.createRoomBooking(rb321);
        PaymentTransaction PT321 = new PaymentTransaction();
        PT321.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT321.setTransactionDateTime(format.parse("2019-03-01"));
        PT321.setFinalPayment(271.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT321);

        RoomBooking rb322 = new RoomBooking();
        rb322.setBookedRoom(roomSessionLocal.getRoomByName("KRW_202"));
        rb322.setBookInDate(format.parse("2019-03-09"));
        bookingSessionLocal.createRoomBooking(rb322);
        PaymentTransaction PT322 = new PaymentTransaction();
        PT322.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT322.setTransactionDateTime(format.parse("2019-03-02"));
        PT322.setFinalPayment(513.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT322);

        RoomBooking rb323 = new RoomBooking();
        rb323.setBookedRoom(roomSessionLocal.getRoomByName("KRW_203"));
        rb323.setBookInDate(format.parse("2019-03-09"));
        bookingSessionLocal.createRoomBooking(rb323);
        PaymentTransaction PT323 = new PaymentTransaction();
        PT323.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT323.setTransactionDateTime(format.parse("2019-03-03"));
        PT323.setFinalPayment(812.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT323);

        RoomBooking rb324 = new RoomBooking();
        rb324.setBookedRoom(roomSessionLocal.getRoomByName("KRW_204"));
        rb324.setBookInDate(format.parse("2019-03-09"));
        bookingSessionLocal.createRoomBooking(rb324);
        PaymentTransaction PT324 = new PaymentTransaction();
        PT324.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT324.setTransactionDateTime(format.parse("2019-03-04"));
        PT324.setFinalPayment(231.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT324);

        RoomBooking rb325 = new RoomBooking();
        rb325.setBookedRoom(roomSessionLocal.getRoomByName("KRW_205"));
        rb325.setBookInDate(format.parse("2019-03-10"));
        bookingSessionLocal.createRoomBooking(rb325);
        PaymentTransaction PT325 = new PaymentTransaction();
        PT325.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT325.setTransactionDateTime(format.parse("2019-03-05"));
        PT325.setFinalPayment(432.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT325);

        RoomBooking rb326 = new RoomBooking();
        rb326.setBookedRoom(roomSessionLocal.getRoomByName("KRW_301"));
        rb326.setBookInDate(format.parse("2019-03-10"));
        bookingSessionLocal.createRoomBooking(rb326);
        PaymentTransaction PT326 = new PaymentTransaction();
        PT326.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT326.setTransactionDateTime(format.parse("2019-03-06"));
        PT326.setFinalPayment(531.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT326);

        RoomBooking rb327 = new RoomBooking();
        rb327.setBookedRoom(roomSessionLocal.getRoomByName("KRW_302"));
        rb327.setBookInDate(format.parse("2019-03-09"));
        bookingSessionLocal.createRoomBooking(rb327);
        PaymentTransaction PT327 = new PaymentTransaction();
        PT327.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT327.setTransactionDateTime(format.parse("2019-03-07"));
        PT327.setFinalPayment(412.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT327);

        RoomBooking rb328 = new RoomBooking();
        rb328.setBookedRoom(roomSessionLocal.getRoomByName("KRW_303"));
        rb328.setBookInDate(format.parse("2019-03-19"));
        bookingSessionLocal.createRoomBooking(rb328);
        PaymentTransaction PT328 = new PaymentTransaction();
        PT328.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT328.setTransactionDateTime(format.parse("2019-03-08"));
        PT328.setFinalPayment(573.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT328);

        RoomBooking rb329 = new RoomBooking();
        rb329.setBookedRoom(roomSessionLocal.getRoomByName("KRW_304"));
        rb329.setBookInDate(format.parse("2019-03-14"));
        bookingSessionLocal.createRoomBooking(rb329);
        PaymentTransaction PT329 = new PaymentTransaction();
        PT329.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT329.setTransactionDateTime(format.parse("2019-03-09"));
        PT329.setFinalPayment(463.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT329);

        RoomBooking rb330 = new RoomBooking();
        rb330.setBookedRoom(roomSessionLocal.getRoomByName("KRW_305"));
        rb330.setBookInDate(format.parse("2019-03-18"));
        bookingSessionLocal.createRoomBooking(rb330);
        PaymentTransaction PT330 = new PaymentTransaction();
        PT330.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT330.setTransactionDateTime(format.parse("2019-03-10"));
        PT330.setFinalPayment(431.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT330);

        RoomBooking rb331 = new RoomBooking();
        rb331.setBookedRoom(roomSessionLocal.getRoomByName("KRW_201"));
        rb331.setBookInDate(format.parse("2019-04-18"));
        bookingSessionLocal.createRoomBooking(rb331);
        PaymentTransaction PT331 = new PaymentTransaction();
        PT331.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT331.setTransactionDateTime(format.parse("2019-04-10"));
        PT331.setFinalPayment(431.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT331);

        RoomBooking rb332 = new RoomBooking();
        rb332.setBookedRoom(roomSessionLocal.getRoomByName("KRW_202"));
        rb332.setBookInDate(format.parse("2019-04-18"));
        bookingSessionLocal.createRoomBooking(rb332);
        PaymentTransaction PT332 = new PaymentTransaction();
        PT332.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT332.setTransactionDateTime(format.parse("2019-04-11"));
        PT332.setFinalPayment(213.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT332);

        RoomBooking rb333 = new RoomBooking();
        rb333.setBookedRoom(roomSessionLocal.getRoomByName("KRW_203"));
        rb333.setBookInDate(format.parse("2019-04-18"));
        bookingSessionLocal.createRoomBooking(rb333);
        PaymentTransaction PT333 = new PaymentTransaction();
        PT333.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT333.setTransactionDateTime(format.parse("2019-04-12"));
        PT333.setFinalPayment(531.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT333);

        RoomBooking rb334 = new RoomBooking();
        rb334.setBookedRoom(roomSessionLocal.getRoomByName("KRW_204"));
        rb334.setBookInDate(format.parse("2019-04-19"));
        bookingSessionLocal.createRoomBooking(rb334);
        PaymentTransaction PT334 = new PaymentTransaction();
        PT334.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT334.setTransactionDateTime(format.parse("2019-04-13"));
        PT334.setFinalPayment(321.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT334);

        RoomBooking rb335 = new RoomBooking();
        rb335.setBookedRoom(roomSessionLocal.getRoomByName("KRW_205"));
        rb335.setBookInDate(format.parse("2019-04-19"));
        bookingSessionLocal.createRoomBooking(rb335);
        PaymentTransaction PT335 = new PaymentTransaction();
        PT335.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT335.setTransactionDateTime(format.parse("2019-04-14"));
        PT335.setFinalPayment(612.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT335);

        RoomBooking rb336 = new RoomBooking();
        rb336.setBookedRoom(roomSessionLocal.getRoomByName("KRW_301"));
        rb336.setBookInDate(format.parse("2019-04-19"));
        bookingSessionLocal.createRoomBooking(rb336);
        PaymentTransaction PT336 = new PaymentTransaction();
        PT336.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT336.setTransactionDateTime(format.parse("2019-04-15"));
        PT336.setFinalPayment(421.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT336);

        RoomBooking rb337 = new RoomBooking();
        rb337.setBookedRoom(roomSessionLocal.getRoomByName("KRW_302"));
        rb337.setBookInDate(format.parse("2019-04-19"));
        bookingSessionLocal.createRoomBooking(rb337);
        PaymentTransaction PT337 = new PaymentTransaction();
        PT337.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT337.setTransactionDateTime(format.parse("2019-04-16"));
        PT337.setFinalPayment(414.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT337);

        RoomBooking rb338 = new RoomBooking();
        rb338.setBookedRoom(roomSessionLocal.getRoomByName("KRW_303"));
        rb338.setBookInDate(format.parse("2019-04-19"));
        bookingSessionLocal.createRoomBooking(rb338);
        PaymentTransaction PT338 = new PaymentTransaction();
        PT338.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT338.setTransactionDateTime(format.parse("2019-04-17"));
        PT338.setFinalPayment(461.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT338);

        RoomBooking rb339 = new RoomBooking();
        rb339.setBookedRoom(roomSessionLocal.getRoomByName("KRG_304"));
        rb339.setBookInDate(format.parse("2019-04-28"));
        bookingSessionLocal.createRoomBooking(rb339);
        PaymentTransaction PT339 = new PaymentTransaction();
        PT339.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT339.setTransactionDateTime(format.parse("2019-04-18"));
        PT339.setFinalPayment(231.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT339);

//KRNE PaymentTransaction
        RoomBooking rb340 = new RoomBooking();
        rb340.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_201"));
        rb340.setBookInDate(format.parse("2019-02-09"));
        bookingSessionLocal.createRoomBooking(rb340);
        PaymentTransaction PT340 = new PaymentTransaction();
        PT340.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT340.setTransactionDateTime(format.parse("2019-01-01"));
        PT340.setFinalPayment(231.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT340);

        RoomBooking rb341 = new RoomBooking();
        rb341.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_202"));
        rb341.setBookInDate(format.parse("2019-01-09"));
        bookingSessionLocal.createRoomBooking(rb341);
        PaymentTransaction PT341 = new PaymentTransaction();
        PT341.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT341.setTransactionDateTime(format.parse("2019-01-02"));
        PT341.setFinalPayment(414.27);
        paymentTransactionSessionLocal.createPaymentTransaction(PT341);

        RoomBooking rb342 = new RoomBooking();
        rb342.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_203"));
        rb342.setBookInDate(format.parse("2019-02-09"));
        bookingSessionLocal.createRoomBooking(rb342);
        PaymentTransaction PT342 = new PaymentTransaction();
        PT342.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT342.setTransactionDateTime(format.parse("2019-01-03"));
        PT342.setFinalPayment(513.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT342);

        RoomBooking rb343 = new RoomBooking();
        rb343.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_204"));
        rb343.setBookInDate(format.parse("2019-01-09"));
        bookingSessionLocal.createRoomBooking(rb343);
        PaymentTransaction PT343 = new PaymentTransaction();
        PT343.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT343.setTransactionDateTime(format.parse("2019-01-04"));
        PT343.setFinalPayment(216.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT343);

        RoomBooking rb344 = new RoomBooking();
        rb344.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_205"));
        rb344.setBookInDate(format.parse("2019-01-09"));
        bookingSessionLocal.createRoomBooking(rb344);
        PaymentTransaction PT344 = new PaymentTransaction();
        PT344.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT344.setTransactionDateTime(format.parse("2019-01-05"));
        PT344.setFinalPayment(216.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT344);

        RoomBooking rb345 = new RoomBooking();
        rb345.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_301"));
        rb345.setBookInDate(format.parse("2019-01-12"));
        bookingSessionLocal.createRoomBooking(rb345);
        PaymentTransaction PT345 = new PaymentTransaction();
        PT345.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT345.setTransactionDateTime(format.parse("2019-01-06"));
        PT345.setFinalPayment(431.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT345);

        RoomBooking rb346 = new RoomBooking();
        rb346.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_302"));
        rb346.setBookInDate(format.parse("2019-01-12"));
        bookingSessionLocal.createRoomBooking(rb346);
        PaymentTransaction PT346 = new PaymentTransaction();
        PT346.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT346.setTransactionDateTime(format.parse("2019-01-07"));
        PT346.setFinalPayment(713.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT346);

        RoomBooking rb347 = new RoomBooking();
        rb347.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_303"));
        rb347.setBookInDate(format.parse("2019-01-13"));
        bookingSessionLocal.createRoomBooking(rb347);
        PaymentTransaction PT347 = new PaymentTransaction();
        PT347.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT347.setTransactionDateTime(format.parse("2019-01-08"));
        PT347.setFinalPayment(241.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT347);

        RoomBooking rb348 = new RoomBooking();
        rb348.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_304"));
        rb348.setBookInDate(format.parse("2019-01-14"));
        bookingSessionLocal.createRoomBooking(rb348);
        PaymentTransaction PT348 = new PaymentTransaction();
        PT348.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT348.setTransactionDateTime(format.parse("2019-01-09"));
        PT348.setFinalPayment(231.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT348);

        RoomBooking rb349 = new RoomBooking();
        rb349.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_305"));
        rb349.setBookInDate(format.parse("2019-02-09"));
        bookingSessionLocal.createRoomBooking(rb349);
        PaymentTransaction PT349 = new PaymentTransaction();
        PT349.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT349.setTransactionDateTime(format.parse("2019-01-10"));
        PT349.setFinalPayment(419.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT349);

        RoomBooking rb350 = new RoomBooking();
        rb350.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_201"));
        rb350.setBookInDate(format.parse("2019-02-09"));
        bookingSessionLocal.createRoomBooking(rb350);
        PaymentTransaction PT350 = new PaymentTransaction();
        PT350.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT350.setTransactionDateTime(format.parse("2019-02-01"));
        PT350.setFinalPayment(431.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT350);

        RoomBooking rb351 = new RoomBooking();
        rb351.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_202"));
        rb351.setBookInDate(format.parse("2019-02-09"));
        bookingSessionLocal.createRoomBooking(rb351);
        PaymentTransaction PT351 = new PaymentTransaction();
        PT351.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT351.setTransactionDateTime(format.parse("2019-02-02"));
        PT351.setFinalPayment(172.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT351);

        RoomBooking rb352 = new RoomBooking();
        rb352.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_203"));
        rb352.setBookInDate(format.parse("2019-02-09"));
        bookingSessionLocal.createRoomBooking(rb352);
        PaymentTransaction PT352 = new PaymentTransaction();
        PT352.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT352.setTransactionDateTime(format.parse("2019-02-03"));
        PT352.setFinalPayment(328.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT352);

        RoomBooking rb353 = new RoomBooking();
        rb353.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_204"));
        rb353.setBookInDate(format.parse("2019-02-08"));
        bookingSessionLocal.createRoomBooking(rb353);
        PaymentTransaction PT353 = new PaymentTransaction();
        PT353.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT353.setTransactionDateTime(format.parse("2019-02-04"));
        PT353.setFinalPayment(123.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT353);

        RoomBooking rb354 = new RoomBooking();
        rb354.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_205"));
        rb354.setBookInDate(format.parse("2019-02-09"));
        bookingSessionLocal.createRoomBooking(rb354);
        PaymentTransaction PT354 = new PaymentTransaction();
        PT354.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT354.setTransactionDateTime(format.parse("2019-02-05"));
        PT354.setFinalPayment(281.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT354);

        RoomBooking rb355 = new RoomBooking();
        rb355.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_301"));
        rb355.setBookInDate(format.parse("2019-02-09"));
        bookingSessionLocal.createRoomBooking(rb355);
        PaymentTransaction PT355 = new PaymentTransaction();
        PT355.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT355.setTransactionDateTime(format.parse("2019-02-06"));
        PT355.setFinalPayment(231.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT355);

        RoomBooking rb356 = new RoomBooking();
        rb356.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_302"));
        rb356.setBookInDate(format.parse("2019-02-14"));
        bookingSessionLocal.createRoomBooking(rb356);
        PaymentTransaction PT356 = new PaymentTransaction();
        PT356.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT356.setTransactionDateTime(format.parse("2019-02-07"));
        PT356.setFinalPayment(128.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT356);

        RoomBooking rb357 = new RoomBooking();
        rb357.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_303"));
        rb357.setBookInDate(format.parse("2019-02-13"));
        bookingSessionLocal.createRoomBooking(rb357);
        PaymentTransaction PT357 = new PaymentTransaction();
        PT357.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT357.setTransactionDateTime(format.parse("2019-02-08"));
        PT357.setFinalPayment(361.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT357);

        RoomBooking rb358 = new RoomBooking();
        rb358.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_304"));
        rb358.setBookInDate(format.parse("2019-02-14"));
        bookingSessionLocal.createRoomBooking(rb358);
        PaymentTransaction PT358 = new PaymentTransaction();
        PT358.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT358.setTransactionDateTime(format.parse("2019-02-09"));
        PT358.setFinalPayment(162.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT358);

        RoomBooking rb359 = new RoomBooking();
        rb359.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_305"));
        rb359.setBookInDate(format.parse("2019-02-17"));
        bookingSessionLocal.createRoomBooking(rb359);
        PaymentTransaction PT359 = new PaymentTransaction();
        PT359.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT359.setTransactionDateTime(format.parse("2019-02-10"));
        PT359.setFinalPayment(281.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT359);

        RoomBooking rb360 = new RoomBooking();
        rb360.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_201"));
        rb360.setBookInDate(format.parse("2019-03-18"));
        bookingSessionLocal.createRoomBooking(rb360);
        PaymentTransaction PT360 = new PaymentTransaction();
        PT360.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT360.setTransactionDateTime(format.parse("2019-03-01"));
        PT360.setFinalPayment(291.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT360);

        RoomBooking rb361 = new RoomBooking();
        rb361.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_202"));
        rb361.setBookInDate(format.parse("2019-03-09"));
        bookingSessionLocal.createRoomBooking(rb361);
        PaymentTransaction PT361 = new PaymentTransaction();
        PT361.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT361.setTransactionDateTime(format.parse("2019-03-02"));
        PT361.setFinalPayment(321.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT361);

        RoomBooking rb362 = new RoomBooking();
        rb362.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_203"));
        rb362.setBookInDate(format.parse("2019-03-09"));
        bookingSessionLocal.createRoomBooking(rb362);
        PaymentTransaction PT362 = new PaymentTransaction();
        PT362.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT362.setTransactionDateTime(format.parse("2019-03-03"));
        PT362.setFinalPayment(427.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT362);

        RoomBooking rb363 = new RoomBooking();
        rb363.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_204"));
        rb363.setBookInDate(format.parse("2019-03-16"));
        bookingSessionLocal.createRoomBooking(rb363);
        PaymentTransaction PT363 = new PaymentTransaction();
        PT363.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT363.setTransactionDateTime(format.parse("2019-03-04"));
        PT363.setFinalPayment(321.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT363);

        RoomBooking rb364 = new RoomBooking();
        rb364.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_205"));
        rb364.setBookInDate(format.parse("2019-03-09"));
        bookingSessionLocal.createRoomBooking(rb364);
        PaymentTransaction PT364 = new PaymentTransaction();
        PT364.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT364.setTransactionDateTime(format.parse("2019-03-05"));
        PT364.setFinalPayment(328.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT364);

        RoomBooking rb365 = new RoomBooking();
        rb365.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_301"));
        rb365.setBookInDate(format.parse("2019-03-17"));
        bookingSessionLocal.createRoomBooking(rb365);
        PaymentTransaction PT365 = new PaymentTransaction();
        PT365.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT365.setTransactionDateTime(format.parse("2019-03-06"));
        PT365.setFinalPayment(216.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT365);

        RoomBooking rb366 = new RoomBooking();
        rb366.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_302"));
        rb366.setBookInDate(format.parse("2019-03-17"));
        bookingSessionLocal.createRoomBooking(rb366);
        PaymentTransaction PT366 = new PaymentTransaction();
        PT366.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT366.setTransactionDateTime(format.parse("2019-03-07"));
        PT366.setFinalPayment(419.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT366);

        RoomBooking rb367 = new RoomBooking();
        rb367.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_303"));
        rb367.setBookInDate(format.parse("2019-03-15"));
        bookingSessionLocal.createRoomBooking(rb367);
        PaymentTransaction PT367 = new PaymentTransaction();
        PT367.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT367.setTransactionDateTime(format.parse("2019-03-08"));
        PT367.setFinalPayment(271.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT367);

        RoomBooking rb368 = new RoomBooking();
        rb368.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_304"));
        rb368.setBookInDate(format.parse("2019-03-13"));
        bookingSessionLocal.createRoomBooking(rb368);
        PaymentTransaction PT368 = new PaymentTransaction();
        PT368.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT368.setTransactionDateTime(format.parse("2019-03-09"));
        PT368.setFinalPayment(127.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT368);

        RoomBooking rb369 = new RoomBooking();
        rb369.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_305"));
        rb369.setBookInDate(format.parse("2019-03-19"));
        bookingSessionLocal.createRoomBooking(rb369);
        PaymentTransaction PT369 = new PaymentTransaction();
        PT369.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT369.setTransactionDateTime(format.parse("2019-03-10"));
        PT369.setFinalPayment(417.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT369);

        RoomBooking rb370 = new RoomBooking();
        rb370.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_201"));
        rb370.setBookInDate(format.parse("2019-04-18"));
        bookingSessionLocal.createRoomBooking(rb370);
        PaymentTransaction PT370 = new PaymentTransaction();
        PT370.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT370.setTransactionDateTime(format.parse("2019-04-11"));
        PT370.setFinalPayment(321.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT370);

        RoomBooking rb371 = new RoomBooking();
        rb371.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_202"));
        rb371.setBookInDate(format.parse("2019-05-09"));
        bookingSessionLocal.createRoomBooking(rb371);
        PaymentTransaction PT371 = new PaymentTransaction();
        PT371.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT371.setTransactionDateTime(format.parse("2019-04-12"));
        PT371.setFinalPayment(417.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT371);

        RoomBooking rb372 = new RoomBooking();
        rb372.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_203"));
        rb372.setBookInDate(format.parse("2019-04-17"));
        bookingSessionLocal.createRoomBooking(rb372);
        PaymentTransaction PT372 = new PaymentTransaction();
        PT372.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT372.setTransactionDateTime(format.parse("2019-04-13"));
        PT372.setFinalPayment(261.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT372);

        RoomBooking rb373 = new RoomBooking();
        rb373.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_204"));
        rb373.setBookInDate(format.parse("2019-04-18"));
        bookingSessionLocal.createRoomBooking(rb373);
        PaymentTransaction PT373 = new PaymentTransaction();
        PT373.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT373.setTransactionDateTime(format.parse("2019-04-14"));
        PT373.setFinalPayment(172.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT373);

        RoomBooking rb374 = new RoomBooking();
        rb374.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_205"));
        rb374.setBookInDate(format.parse("2019-04-19"));
        bookingSessionLocal.createRoomBooking(rb374);
        PaymentTransaction PT374 = new PaymentTransaction();
        PT374.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT374.setTransactionDateTime(format.parse("2019-04-15"));
        PT374.setFinalPayment(218.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT374);

        RoomBooking rb375 = new RoomBooking();
        rb375.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_301"));
        rb375.setBookInDate(format.parse("2019-04-19"));
        bookingSessionLocal.createRoomBooking(rb375);
        PaymentTransaction PT375 = new PaymentTransaction();
        PT375.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT375.setTransactionDateTime(format.parse("2019-04-16"));
        PT375.setFinalPayment(271.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT375);

        RoomBooking rb376 = new RoomBooking();
        rb376.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_302"));
        rb376.setBookInDate(format.parse("2019-04-21"));
        bookingSessionLocal.createRoomBooking(rb376);
        PaymentTransaction PT376 = new PaymentTransaction();
        PT376.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT376.setTransactionDateTime(format.parse("2019-04-17"));
        PT376.setFinalPayment(162.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT376);

        RoomBooking rb377 = new RoomBooking();
        rb377.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_303"));
        rb377.setBookInDate(format.parse("2019-04-21"));
        bookingSessionLocal.createRoomBooking(rb377);
        PaymentTransaction PT377 = new PaymentTransaction();
        PT377.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT377.setTransactionDateTime(format.parse("2019-04-17"));
        PT377.setFinalPayment(281.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT377);

        RoomBooking rb378 = new RoomBooking();
        rb378.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_304"));
        rb378.setBookInDate(format.parse("2019-04-24"));
        bookingSessionLocal.createRoomBooking(rb378);
        PaymentTransaction PT378 = new PaymentTransaction();
        PT378.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT378.setTransactionDateTime(format.parse("2019-04-18"));
        PT378.setFinalPayment(172.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT378);

        RoomBooking rb379 = new RoomBooking();
        rb379.setBookedRoom(roomSessionLocal.getRoomByName("KRNE_305"));
        rb379.setBookInDate(format.parse("2019-04-24"));
        bookingSessionLocal.createRoomBooking(rb379);
        PaymentTransaction PT379 = new PaymentTransaction();
        PT379.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT379.setTransactionDateTime(format.parse("2019-04-19"));
        PT379.setFinalPayment(321.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT379);

//KRNW PaymentTransaction
        RoomBooking rb380 = new RoomBooking();
        rb380.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_201"));
        rb380.setBookInDate(format.parse("2019-02-09"));
        bookingSessionLocal.createRoomBooking(rb380);
        PaymentTransaction PT380 = new PaymentTransaction();
        PT380.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT380.setTransactionDateTime(format.parse("2019-01-01"));
        PT380.setFinalPayment(321.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT380);

        RoomBooking rb381 = new RoomBooking();
        rb381.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_202"));
        rb381.setBookInDate(format.parse("2019-01-09"));
        bookingSessionLocal.createRoomBooking(rb381);
        PaymentTransaction PT381 = new PaymentTransaction();
        PT381.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT381.setTransactionDateTime(format.parse("2019-01-02"));
        PT381.setFinalPayment(218.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT381);

        RoomBooking rb382 = new RoomBooking();
        rb382.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_203"));
        rb382.setBookInDate(format.parse("2019-01-09"));
        bookingSessionLocal.createRoomBooking(rb382);
        PaymentTransaction PT382 = new PaymentTransaction();
        PT382.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT382.setTransactionDateTime(format.parse("2019-01-03"));
        PT382.setFinalPayment(123.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT382);

        RoomBooking rb383 = new RoomBooking();
        rb383.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_204"));
        rb383.setBookInDate(format.parse("2019-01-12"));
        bookingSessionLocal.createRoomBooking(rb383);
        PaymentTransaction PT383 = new PaymentTransaction();
        PT383.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT383.setTransactionDateTime(format.parse("2019-01-04"));
        PT383.setFinalPayment(127.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT383);

        RoomBooking rb384 = new RoomBooking();
        rb384.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_205"));
        rb384.setBookInDate(format.parse("2019-02-09"));
        bookingSessionLocal.createRoomBooking(rb384);
        PaymentTransaction PT384 = new PaymentTransaction();
        PT384.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT384.setTransactionDateTime(format.parse("2019-01-05"));
        PT384.setFinalPayment(127.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT384);

        RoomBooking rb385 = new RoomBooking();
        rb385.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_301"));
        rb385.setBookInDate(format.parse("2019-01-27"));
        bookingSessionLocal.createRoomBooking(rb385);
        PaymentTransaction PT385 = new PaymentTransaction();
        PT385.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT385.setTransactionDateTime(format.parse("2019-01-06"));
        PT385.setFinalPayment(126.48);
        paymentTransactionSessionLocal.createPaymentTransaction(PT385);

        RoomBooking rb386 = new RoomBooking();
        rb386.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_302"));
        rb386.setBookInDate(format.parse("2019-01-28"));
        bookingSessionLocal.createRoomBooking(rb386);
        PaymentTransaction PT386 = new PaymentTransaction();
        PT386.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT386.setTransactionDateTime(format.parse("2019-01-07"));
        PT386.setFinalPayment(126.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT386);

        RoomBooking rb387 = new RoomBooking();
        rb387.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_303"));
        rb387.setBookInDate(format.parse("2019-01-18"));
        bookingSessionLocal.createRoomBooking(rb387);
        PaymentTransaction PT387 = new PaymentTransaction();
        PT387.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT387.setTransactionDateTime(format.parse("2019-01-08"));
        PT387.setFinalPayment(216.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT387);

        RoomBooking rb388 = new RoomBooking();
        rb388.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_304"));
        rb388.setBookInDate(format.parse("2019-01-29"));
        bookingSessionLocal.createRoomBooking(rb388);
        PaymentTransaction PT388 = new PaymentTransaction();
        PT388.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT388.setTransactionDateTime(format.parse("2019-01-09"));
        PT388.setFinalPayment(182.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT388);

        RoomBooking rb389 = new RoomBooking();
        rb389.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_305"));
        rb389.setBookInDate(format.parse("2019-01-28"));
        bookingSessionLocal.createRoomBooking(rb389);
        PaymentTransaction PT389 = new PaymentTransaction();
        PT389.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT389.setTransactionDateTime(format.parse("2019-01-10"));
        PT389.setFinalPayment(238.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT389);

        RoomBooking rb390 = new RoomBooking();
        rb390.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_201"));
        rb390.setBookInDate(format.parse("2019-02-09"));
        bookingSessionLocal.createRoomBooking(rb390);
        PaymentTransaction PT390 = new PaymentTransaction();
        PT390.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT390.setTransactionDateTime(format.parse("2019-02-01"));
        PT390.setFinalPayment(127.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT390);

        RoomBooking rb391 = new RoomBooking();
        rb391.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_202"));
        rb391.setBookInDate(format.parse("2019-02-19"));
        bookingSessionLocal.createRoomBooking(rb391);
        PaymentTransaction PT391 = new PaymentTransaction();
        PT391.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT391.setTransactionDateTime(format.parse("2019-02-02"));
        PT391.setFinalPayment(381.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT391);

        RoomBooking rb392 = new RoomBooking();
        rb392.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_203"));
        rb392.setBookInDate(format.parse("2019-02-19"));
        bookingSessionLocal.createRoomBooking(rb392);
        PaymentTransaction PT392 = new PaymentTransaction();
        PT392.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT392.setTransactionDateTime(format.parse("2019-02-03"));
        PT392.setFinalPayment(162.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT392);

        RoomBooking rb393 = new RoomBooking();
        rb393.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_204"));
        rb393.setBookInDate(format.parse("2019-02-18"));
        bookingSessionLocal.createRoomBooking(rb393);
        PaymentTransaction PT393 = new PaymentTransaction();
        PT393.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT393.setTransactionDateTime(format.parse("2019-02-04"));
        PT393.setFinalPayment(217.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT393);

        RoomBooking rb394 = new RoomBooking();
        rb394.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_205"));
        rb394.setBookInDate(format.parse("2019-02-17"));
        bookingSessionLocal.createRoomBooking(rb394);
        PaymentTransaction PT394 = new PaymentTransaction();
        PT394.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT394.setTransactionDateTime(format.parse("2019-02-05"));
        PT394.setFinalPayment(183.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT394);

        RoomBooking rb395 = new RoomBooking();
        rb395.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_301"));
        rb395.setBookInDate(format.parse("2019-02-09"));
        bookingSessionLocal.createRoomBooking(rb395);
        PaymentTransaction PT395 = new PaymentTransaction();
        PT395.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT395.setTransactionDateTime(format.parse("2019-02-06"));
        PT395.setFinalPayment(172.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT395);

        RoomBooking rb396 = new RoomBooking();
        rb396.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_302"));
        rb396.setBookInDate(format.parse("2019-02-18"));
        bookingSessionLocal.createRoomBooking(rb396);
        PaymentTransaction PT396 = new PaymentTransaction();
        PT396.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT396.setTransactionDateTime(format.parse("2019-02-07"));
        PT396.setFinalPayment(127.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT396);

        RoomBooking rb397 = new RoomBooking();
        rb397.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_303"));
        rb397.setBookInDate(format.parse("2019-02-19"));
        bookingSessionLocal.createRoomBooking(rb397);
        PaymentTransaction PT397 = new PaymentTransaction();
        PT397.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT397.setTransactionDateTime(format.parse("2019-02-08"));
        PT397.setFinalPayment(291.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT397);

        RoomBooking rb398 = new RoomBooking();
        rb398.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_304"));
        rb398.setBookInDate(format.parse("2019-02-18"));
        bookingSessionLocal.createRoomBooking(rb398);
        PaymentTransaction PT398 = new PaymentTransaction();
        PT398.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT398.setTransactionDateTime(format.parse("2019-02-09"));
        PT398.setFinalPayment(172.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT398);

        RoomBooking rb399 = new RoomBooking();
        rb399.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_305"));
        rb399.setBookInDate(format.parse("2019-02-14"));
        bookingSessionLocal.createRoomBooking(rb399);
        PaymentTransaction PT399 = new PaymentTransaction();
        PT399.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT399.setTransactionDateTime(format.parse("2019-02-10"));
        PT399.setFinalPayment(417.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT399);

        RoomBooking rb400 = new RoomBooking();
        rb400.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_201"));
        rb400.setBookInDate(format.parse("2019-03-09"));
        bookingSessionLocal.createRoomBooking(rb400);
        PaymentTransaction PT400 = new PaymentTransaction();
        PT400.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT400.setTransactionDateTime(format.parse("2019-03-01"));
        PT400.setFinalPayment(128.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT400);

        RoomBooking rb401 = new RoomBooking();
        rb401.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_202"));
        rb401.setBookInDate(format.parse("2019-03-09"));
        bookingSessionLocal.createRoomBooking(rb401);
        PaymentTransaction PT401 = new PaymentTransaction();
        PT401.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT401.setTransactionDateTime(format.parse("2019-03-02"));
        PT401.setFinalPayment(312.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT401);

        RoomBooking rb402 = new RoomBooking();
        rb402.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_203"));
        rb402.setBookInDate(format.parse("2019-03-09"));
        bookingSessionLocal.createRoomBooking(rb402);
        PaymentTransaction PT402 = new PaymentTransaction();
        PT402.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT402.setTransactionDateTime(format.parse("2019-03-03"));
        PT402.setFinalPayment(192.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT402);

        RoomBooking rb403 = new RoomBooking();
        rb403.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_204"));
        rb403.setBookInDate(format.parse("2019-03-12"));
        bookingSessionLocal.createRoomBooking(rb403);
        PaymentTransaction PT403 = new PaymentTransaction();
        PT403.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT403.setTransactionDateTime(format.parse("2019-03-04"));
        PT403.setFinalPayment(172.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT403);

        RoomBooking rb404 = new RoomBooking();
        rb404.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_205"));
        rb404.setBookInDate(format.parse("2019-03-09"));
        bookingSessionLocal.createRoomBooking(rb404);
        PaymentTransaction PT404 = new PaymentTransaction();
        PT404.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT404.setTransactionDateTime(format.parse("2019-03-05"));
        PT404.setFinalPayment(192.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT404);

        RoomBooking rb405 = new RoomBooking();
        rb405.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_301"));
        rb405.setBookInDate(format.parse("2019-03-13"));
        bookingSessionLocal.createRoomBooking(rb405);
        PaymentTransaction PT405 = new PaymentTransaction();
        PT405.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT405.setTransactionDateTime(format.parse("2019-03-06"));
        PT405.setFinalPayment(271.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT405);

        RoomBooking rb406 = new RoomBooking();
        rb406.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_302"));
        rb406.setBookInDate(format.parse("2019-03-13"));
        bookingSessionLocal.createRoomBooking(rb406);
        PaymentTransaction PT406 = new PaymentTransaction();
        PT406.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT406.setTransactionDateTime(format.parse("2019-03-07"));
        PT406.setFinalPayment(192.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT406);

        RoomBooking rb407 = new RoomBooking();
        rb407.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_303"));
        rb407.setBookInDate(format.parse("2019-03-18"));
        bookingSessionLocal.createRoomBooking(rb407);
        PaymentTransaction PT407 = new PaymentTransaction();
        PT407.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT407.setTransactionDateTime(format.parse("2019-03-08"));
        PT407.setFinalPayment(182.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT407);

        RoomBooking rb408 = new RoomBooking();
        rb408.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_304"));
        rb408.setBookInDate(format.parse("2019-03-17"));
        bookingSessionLocal.createRoomBooking(rb408);
        PaymentTransaction PT408 = new PaymentTransaction();
        PT408.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT408.setTransactionDateTime(format.parse("2019-03-09"));
        PT408.setFinalPayment(384.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT408);

        RoomBooking rb409 = new RoomBooking();
        rb409.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_305"));
        rb409.setBookInDate(format.parse("2019-03-18"));
        bookingSessionLocal.createRoomBooking(rb409);
        PaymentTransaction PT409 = new PaymentTransaction();
        PT409.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT409.setTransactionDateTime(format.parse("2019-03-10"));
        PT409.setFinalPayment(173.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT409);

        RoomBooking rb410 = new RoomBooking();
        rb410.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_201"));
        rb410.setBookInDate(format.parse("2019-04-09"));
        bookingSessionLocal.createRoomBooking(rb410);
        PaymentTransaction PT410 = new PaymentTransaction();
        PT410.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT410.setTransactionDateTime(format.parse("2019-04-01"));
        PT410.setFinalPayment(173.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT410);

        RoomBooking rb411 = new RoomBooking();
        rb411.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_202"));
        rb411.setBookInDate(format.parse("2019-04-09"));
        bookingSessionLocal.createRoomBooking(rb411);
        PaymentTransaction PT411 = new PaymentTransaction();
        PT411.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT411.setTransactionDateTime(format.parse("2019-04-02"));
        PT411.setFinalPayment(172.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT411);

        RoomBooking rb412 = new RoomBooking();
        rb412.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_201"));
        rb412.setBookInDate(format.parse("2019-04-09"));
        bookingSessionLocal.createRoomBooking(rb412);
        PaymentTransaction PT412 = new PaymentTransaction();
        PT412.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT412.setTransactionDateTime(format.parse("2019-04-03"));
        PT412.setFinalPayment(281.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT412);

        RoomBooking rb413 = new RoomBooking();
        rb413.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_204"));
        rb413.setBookInDate(format.parse("2019-04-18"));
        bookingSessionLocal.createRoomBooking(rb413);
        PaymentTransaction PT413 = new PaymentTransaction();
        PT413.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT413.setTransactionDateTime(format.parse("2019-04-04"));
        PT413.setFinalPayment(372.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT413);

        RoomBooking rb414 = new RoomBooking();
        rb414.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_205"));
        rb414.setBookInDate(format.parse("2019-04-12"));
        bookingSessionLocal.createRoomBooking(rb414);
        PaymentTransaction PT414 = new PaymentTransaction();
        PT414.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT414.setTransactionDateTime(format.parse("2019-04-05"));
        PT414.setFinalPayment(292.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT414);

        RoomBooking rb415 = new RoomBooking();
        rb415.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_301"));
        rb415.setBookInDate(format.parse("2019-04-15"));
        bookingSessionLocal.createRoomBooking(rb415);
        PaymentTransaction PT415 = new PaymentTransaction();
        PT415.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT415.setTransactionDateTime(format.parse("2019-04-06"));
        PT415.setFinalPayment(271.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT415);

        RoomBooking rb416 = new RoomBooking();
        rb416.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_302"));
        rb416.setBookInDate(format.parse("2019-04-17"));
        bookingSessionLocal.createRoomBooking(rb416);
        PaymentTransaction PT416 = new PaymentTransaction();
        PT416.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT416.setTransactionDateTime(format.parse("2019-04-07"));
        PT416.setFinalPayment(471.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT416);

        RoomBooking rb417 = new RoomBooking();
        rb417.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_303"));
        rb417.setBookInDate(format.parse("2019-04-19"));
        bookingSessionLocal.createRoomBooking(rb417);
        PaymentTransaction PT417 = new PaymentTransaction();
        PT417.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT417.setTransactionDateTime(format.parse("2019-04-08"));
        PT417.setFinalPayment(172.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT417);

        RoomBooking rb418 = new RoomBooking();
        rb418.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_304"));
        rb418.setBookInDate(format.parse("2019-04-18"));
        bookingSessionLocal.createRoomBooking(rb418);
        PaymentTransaction PT418 = new PaymentTransaction();
        PT418.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT418.setTransactionDateTime(format.parse("2019-04-09"));
        PT418.setFinalPayment(182.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT418);

        RoomBooking rb419 = new RoomBooking();
        rb419.setBookedRoom(roomSessionLocal.getRoomByName("KRNW_305"));
        rb419.setBookInDate(format.parse("2019-04-17"));
        bookingSessionLocal.createRoomBooking(rb419);
        PaymentTransaction PT419 = new PaymentTransaction();
        PT419.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT419.setTransactionDateTime(format.parse("2019-04-10"));
        PT419.setFinalPayment(283.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT419);

//KRSE PaymentTransaction
        RoomBooking rb420 = new RoomBooking();
        rb420.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_201"));
        rb420.setBookInDate(format.parse("2019-02-09"));
        bookingSessionLocal.createRoomBooking(rb420);
        PaymentTransaction PT420 = new PaymentTransaction();
        PT420.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT420.setTransactionDateTime(format.parse("2019-01-01"));
        PT420.setFinalPayment(231.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT420);

        RoomBooking rb421 = new RoomBooking();
        rb421.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_202"));
        rb421.setBookInDate(format.parse("2019-02-19"));
        bookingSessionLocal.createRoomBooking(rb421);
        PaymentTransaction PT421 = new PaymentTransaction();
        PT421.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT421.setTransactionDateTime(format.parse("2019-01-02"));
        PT421.setFinalPayment(231.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT421);

        RoomBooking rb422 = new RoomBooking();
        rb422.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_203"));
        rb422.setBookInDate(format.parse("2019-01-09"));
        bookingSessionLocal.createRoomBooking(rb422);
        PaymentTransaction PT422 = new PaymentTransaction();
        PT422.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT422.setTransactionDateTime(format.parse("2019-01-03"));
        PT422.setFinalPayment(123.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT422);

        RoomBooking rb423 = new RoomBooking();
        rb423.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_204"));
        rb423.setBookInDate(format.parse("2019-01-09"));
        bookingSessionLocal.createRoomBooking(rb423);
        PaymentTransaction PT423 = new PaymentTransaction();
        PT423.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT423.setTransactionDateTime(format.parse("2019-01-04"));
        PT423.setFinalPayment(123.84);
        paymentTransactionSessionLocal.createPaymentTransaction(PT423);

        RoomBooking rb424 = new RoomBooking();
        rb424.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_205"));
        rb424.setBookInDate(format.parse("2019-01-17"));
        bookingSessionLocal.createRoomBooking(rb424);
        PaymentTransaction PT424 = new PaymentTransaction();
        PT424.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT424.setTransactionDateTime(format.parse("2019-01-05"));
        PT424.setFinalPayment(213.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT424);

        RoomBooking rb425 = new RoomBooking();
        rb425.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_301"));
        rb425.setBookInDate(format.parse("2019-01-13"));
        bookingSessionLocal.createRoomBooking(rb425);
        PaymentTransaction PT425 = new PaymentTransaction();
        PT425.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT425.setTransactionDateTime(format.parse("2019-01-06"));
        PT425.setFinalPayment(241.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT425);

        RoomBooking rb426 = new RoomBooking();
        rb426.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_302"));
        rb426.setBookInDate(format.parse("2019-01-15"));
        bookingSessionLocal.createRoomBooking(rb426);
        PaymentTransaction PT426 = new PaymentTransaction();
        PT426.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT426.setTransactionDateTime(format.parse("2019-01-07"));
        PT426.setFinalPayment(142.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT426);

        RoomBooking rb427 = new RoomBooking();
        rb427.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_303"));
        rb427.setBookInDate(format.parse("2019-01-17"));
        bookingSessionLocal.createRoomBooking(rb427);
        PaymentTransaction PT427 = new PaymentTransaction();
        PT427.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT427.setTransactionDateTime(format.parse("2019-01-08"));
        PT427.setFinalPayment(421.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT427);

        RoomBooking rb428 = new RoomBooking();
        rb428.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_304"));
        rb428.setBookInDate(format.parse("2019-01-16"));
        bookingSessionLocal.createRoomBooking(rb428);
        PaymentTransaction PT428 = new PaymentTransaction();
        PT428.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT428.setTransactionDateTime(format.parse("2019-01-09"));
        PT428.setFinalPayment(123.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT428);

        RoomBooking rb429 = new RoomBooking();
        rb429.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_305"));
        rb429.setBookInDate(format.parse("2019-01-17"));
        bookingSessionLocal.createRoomBooking(rb429);
        PaymentTransaction PT429 = new PaymentTransaction();
        PT429.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT429.setTransactionDateTime(format.parse("2019-01-10"));
        PT429.setFinalPayment(281.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT429);

        RoomBooking rb430 = new RoomBooking();
        rb430.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_306"));
        rb430.setBookInDate(format.parse("2019-02-09"));
        bookingSessionLocal.createRoomBooking(rb430);
        PaymentTransaction PT430 = new PaymentTransaction();
        PT430.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT430.setTransactionDateTime(format.parse("2019-02-01"));
        PT430.setFinalPayment(281.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT430);

        RoomBooking rb431 = new RoomBooking();
        rb431.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_202"));
        rb431.setBookInDate(format.parse("2019-02-12"));
        bookingSessionLocal.createRoomBooking(rb431);
        PaymentTransaction PT431 = new PaymentTransaction();
        PT431.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT431.setTransactionDateTime(format.parse("2019-02-02"));
        PT431.setFinalPayment(237.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT431);

        RoomBooking rb432 = new RoomBooking();
        rb432.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_203"));
        rb432.setBookInDate(format.parse("2019-02-17"));
        bookingSessionLocal.createRoomBooking(rb432);
        PaymentTransaction PT432 = new PaymentTransaction();
        PT432.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT432.setTransactionDateTime(format.parse("2019-02-03"));
        PT432.setFinalPayment(182.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT432);

        RoomBooking rb433 = new RoomBooking();
        rb433.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_204"));
        rb433.setBookInDate(format.parse("2019-02-12"));
        bookingSessionLocal.createRoomBooking(rb433);
        PaymentTransaction PT433 = new PaymentTransaction();
        PT433.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT433.setTransactionDateTime(format.parse("2019-02-04"));
        PT433.setFinalPayment(192.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT433);

        RoomBooking rb434 = new RoomBooking();
        rb434.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_205"));
        rb434.setBookInDate(format.parse("2019-02-17"));
        bookingSessionLocal.createRoomBooking(rb434);
        PaymentTransaction PT434 = new PaymentTransaction();
        PT434.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT434.setTransactionDateTime(format.parse("2019-02-05"));
        PT434.setFinalPayment(247.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT434);

        RoomBooking rb435 = new RoomBooking();
        rb435.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_301"));
        rb435.setBookInDate(format.parse("2019-02-12"));
        bookingSessionLocal.createRoomBooking(rb435);
        PaymentTransaction PT435 = new PaymentTransaction();
        PT435.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT435.setTransactionDateTime(format.parse("2019-02-06"));
        PT435.setFinalPayment(128.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT435);

        RoomBooking rb436 = new RoomBooking();
        rb436.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_302"));
        rb436.setBookInDate(format.parse("2019-02-12"));
        bookingSessionLocal.createRoomBooking(rb436);
        PaymentTransaction PT436 = new PaymentTransaction();
        PT436.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT436.setTransactionDateTime(format.parse("2019-02-07"));
        PT436.setFinalPayment(182.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT436);

        RoomBooking rb437 = new RoomBooking();
        rb437.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_303"));
        rb437.setBookInDate(format.parse("2019-02-14"));
        bookingSessionLocal.createRoomBooking(rb437);
        PaymentTransaction PT437 = new PaymentTransaction();
        PT437.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT437.setTransactionDateTime(format.parse("2019-02-08"));
        PT437.setFinalPayment(472.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT437);

        RoomBooking rb438 = new RoomBooking();
        rb438.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_304"));
        rb438.setBookInDate(format.parse("2019-02-17"));
        bookingSessionLocal.createRoomBooking(rb438);
        PaymentTransaction PT438 = new PaymentTransaction();
        PT438.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT438.setTransactionDateTime(format.parse("2019-02-09"));
        PT438.setFinalPayment(281.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT438);

        RoomBooking rb439 = new RoomBooking();
        rb439.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_305"));
        rb439.setBookInDate(format.parse("2019-02-17"));
        bookingSessionLocal.createRoomBooking(rb439);
        PaymentTransaction PT439 = new PaymentTransaction();
        PT439.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT439.setTransactionDateTime(format.parse("2019-02-10"));
        PT439.setFinalPayment(217.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT439);

        RoomBooking rb440 = new RoomBooking();
        rb440.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_201"));
        rb440.setBookInDate(format.parse("2019-03-18"));
        bookingSessionLocal.createRoomBooking(rb440);
        PaymentTransaction PT440 = new PaymentTransaction();
        PT440.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT440.setTransactionDateTime(format.parse("2019-03-01"));
        PT440.setFinalPayment(271.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT440);

        RoomBooking rb441 = new RoomBooking();
        rb441.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_202"));
        rb441.setBookInDate(format.parse("2019-03-13"));
        bookingSessionLocal.createRoomBooking(rb441);
        PaymentTransaction PT441 = new PaymentTransaction();
        PT441.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT441.setTransactionDateTime(format.parse("2019-03-02"));
        PT441.setFinalPayment(281.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT441);

        RoomBooking rb442 = new RoomBooking();
        rb442.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_203"));
        rb442.setBookInDate(format.parse("2019-03-09"));
        bookingSessionLocal.createRoomBooking(rb442);
        PaymentTransaction PT442 = new PaymentTransaction();
        PT442.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT442.setTransactionDateTime(format.parse("2019-03-03"));
        PT442.setFinalPayment(293.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT442);

        RoomBooking rb443 = new RoomBooking();
        rb443.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_204"));
        rb443.setBookInDate(format.parse("2019-03-17"));
        bookingSessionLocal.createRoomBooking(rb443);
        PaymentTransaction PT443 = new PaymentTransaction();
        PT443.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT443.setTransactionDateTime(format.parse("2019-03-04"));
        PT443.setFinalPayment(172.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT443);

        RoomBooking rb444 = new RoomBooking();
        rb444.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_205"));
        rb444.setBookInDate(format.parse("2019-03-09"));
        bookingSessionLocal.createRoomBooking(rb444);
        PaymentTransaction PT444 = new PaymentTransaction();
        PT444.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT444.setTransactionDateTime(format.parse("2019-03-05"));
        PT444.setFinalPayment(313.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT444);

        RoomBooking rb445 = new RoomBooking();
        rb445.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_301"));
        rb445.setBookInDate(format.parse("2019-04-09"));
        bookingSessionLocal.createRoomBooking(rb445);
        PaymentTransaction PT445 = new PaymentTransaction();
        PT445.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT445.setTransactionDateTime(format.parse("2019-03-06"));
        PT445.setFinalPayment(387.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT445);

        RoomBooking rb446 = new RoomBooking();
        rb446.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_302"));
        rb446.setBookInDate(format.parse("2019-03-19"));
        bookingSessionLocal.createRoomBooking(rb446);
        PaymentTransaction PT446 = new PaymentTransaction();
        PT446.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT446.setTransactionDateTime(format.parse("2019-03-07"));
        PT446.setFinalPayment(271.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT446);

        RoomBooking rb447 = new RoomBooking();
        rb447.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_303"));
        rb447.setBookInDate(format.parse("2019-03-12"));
        bookingSessionLocal.createRoomBooking(rb447);
        PaymentTransaction PT447 = new PaymentTransaction();
        PT447.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT447.setTransactionDateTime(format.parse("2019-03-08"));
        PT447.setFinalPayment(273.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT447);

        RoomBooking rb448 = new RoomBooking();
        rb448.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_304"));
        rb448.setBookInDate(format.parse("2019-03-17"));
        bookingSessionLocal.createRoomBooking(rb448);
        PaymentTransaction PT448 = new PaymentTransaction();
        PT448.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT448.setTransactionDateTime(format.parse("2019-03-09"));
        PT448.setFinalPayment(148.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT448);

        RoomBooking rb449 = new RoomBooking();
        rb449.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_305"));
        rb449.setBookInDate(format.parse("2019-03-19"));
        bookingSessionLocal.createRoomBooking(rb449);
        PaymentTransaction PT449 = new PaymentTransaction();
        PT449.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT449.setTransactionDateTime(format.parse("2019-03-10"));
        PT449.setFinalPayment(175.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT449);

        RoomBooking rb450 = new RoomBooking();
        rb450.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_201"));
        rb450.setBookInDate(format.parse("2019-04-09"));
        bookingSessionLocal.createRoomBooking(rb450);
        PaymentTransaction PT450 = new PaymentTransaction();
        PT450.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT450.setTransactionDateTime(format.parse("2019-04-01"));
        PT450.setFinalPayment(182.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT450);

        RoomBooking rb451 = new RoomBooking();
        rb451.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_202"));
        rb451.setBookInDate(format.parse("2019-04-12"));
        bookingSessionLocal.createRoomBooking(rb451);
        PaymentTransaction PT451 = new PaymentTransaction();
        PT451.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT451.setTransactionDateTime(format.parse("2019-04-02"));
        PT451.setFinalPayment(281.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT451);

        RoomBooking rb452 = new RoomBooking();
        rb452.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_203"));
        rb452.setBookInDate(format.parse("2019-04-12"));
        bookingSessionLocal.createRoomBooking(rb452);
        PaymentTransaction PT452 = new PaymentTransaction();
        PT452.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT452.setTransactionDateTime(format.parse("2019-04-03"));
        PT452.setFinalPayment(281.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT452);

        RoomBooking rb453 = new RoomBooking();
        rb453.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_204"));
        rb453.setBookInDate(format.parse("2019-04-17"));
        bookingSessionLocal.createRoomBooking(rb453);
        PaymentTransaction PT453 = new PaymentTransaction();
        PT453.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT453.setTransactionDateTime(format.parse("2019-04-04"));
        PT453.setFinalPayment(192.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT453);

        RoomBooking rb454 = new RoomBooking();
        rb454.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_205"));
        rb454.setBookInDate(format.parse("2019-04-18"));
        bookingSessionLocal.createRoomBooking(rb454);
        PaymentTransaction PT454 = new PaymentTransaction();
        PT454.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT454.setTransactionDateTime(format.parse("2019-04-05"));
        PT454.setFinalPayment(102.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT454);

        RoomBooking rb455 = new RoomBooking();
        rb455.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_301"));
        rb455.setBookInDate(format.parse("2019-04-17"));
        bookingSessionLocal.createRoomBooking(rb455);
        PaymentTransaction PT455 = new PaymentTransaction();
        PT455.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT455.setTransactionDateTime(format.parse("2019-04-06"));
        PT455.setFinalPayment(182.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT455);

        RoomBooking rb456 = new RoomBooking();
        rb456.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_302"));
        rb456.setBookInDate(format.parse("2019-04-18"));
        bookingSessionLocal.createRoomBooking(rb456);
        PaymentTransaction PT456 = new PaymentTransaction();
        PT456.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT456.setTransactionDateTime(format.parse("2019-04-07"));
        PT456.setFinalPayment(213.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT456);

        RoomBooking rb457 = new RoomBooking();
        rb457.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_303"));
        rb457.setBookInDate(format.parse("2019-04-18"));
        bookingSessionLocal.createRoomBooking(rb457);
        PaymentTransaction PT457 = new PaymentTransaction();
        PT457.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT457.setTransactionDateTime(format.parse("2019-04-08"));
        PT457.setFinalPayment(281.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT457);

        RoomBooking rb458 = new RoomBooking();
        rb458.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_304"));
        rb458.setBookInDate(format.parse("2019-04-18"));
        bookingSessionLocal.createRoomBooking(rb458);
        PaymentTransaction PT458 = new PaymentTransaction();
        PT458.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT458.setTransactionDateTime(format.parse("2019-04-09"));
        PT458.setFinalPayment(182.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT458);

        RoomBooking rb459 = new RoomBooking();
        rb459.setBookedRoom(roomSessionLocal.getRoomByName("KRSE_305"));
        rb459.setBookInDate(format.parse("2019-04-18"));
        bookingSessionLocal.createRoomBooking(rb459);
        PaymentTransaction PT459 = new PaymentTransaction();
        PT459.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT459.setTransactionDateTime(format.parse("2019-04-10"));
        PT459.setFinalPayment(283.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT459);

//KRSW PaymentTransaction 
        RoomBooking rb460 = new RoomBooking();
        rb460.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_201"));
        rb460.setBookInDate(format.parse("2019-01-09"));
        bookingSessionLocal.createRoomBooking(rb460);
        PaymentTransaction PT460 = new PaymentTransaction();
        PT460.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT460.setTransactionDateTime(format.parse("2019-01-01"));
        PT460.setFinalPayment(281.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT460);

        RoomBooking rb461 = new RoomBooking();
        rb461.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_202"));
        rb461.setBookInDate(format.parse("2019-01-19"));
        bookingSessionLocal.createRoomBooking(rb461);
        PaymentTransaction PT461 = new PaymentTransaction();
        PT461.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT461.setTransactionDateTime(format.parse("2019-01-02"));
        PT461.setFinalPayment(172.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT461);

        RoomBooking rb462 = new RoomBooking();
        rb462.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_203"));
        rb462.setBookInDate(format.parse("2019-01-09"));
        bookingSessionLocal.createRoomBooking(rb462);
        PaymentTransaction PT462 = new PaymentTransaction();
        PT462.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT462.setTransactionDateTime(format.parse("2019-01-03"));
        PT462.setFinalPayment(231.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT462);

        RoomBooking rb463 = new RoomBooking();
        rb463.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_204"));
        rb463.setBookInDate(format.parse("2019-02-09"));
        bookingSessionLocal.createRoomBooking(rb463);
        PaymentTransaction PT463 = new PaymentTransaction();
        PT463.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT463.setTransactionDateTime(format.parse("2019-01-04"));
        PT463.setFinalPayment(231.42);
        paymentTransactionSessionLocal.createPaymentTransaction(PT463);

        RoomBooking rb464 = new RoomBooking();
        rb464.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_205"));
        rb464.setBookInDate(format.parse("2019-01-09"));
        bookingSessionLocal.createRoomBooking(rb464);
        PaymentTransaction PT464 = new PaymentTransaction();
        PT464.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT464.setTransactionDateTime(format.parse("2019-01-05"));
        PT464.setFinalPayment(421.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT464);

        RoomBooking rb465 = new RoomBooking();
        rb465.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_301"));
        rb465.setBookInDate(format.parse("2019-01-13"));
        bookingSessionLocal.createRoomBooking(rb465);
        PaymentTransaction PT465 = new PaymentTransaction();
        PT465.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT465.setTransactionDateTime(format.parse("2019-01-06"));
        PT465.setFinalPayment(123.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT465);

        RoomBooking rb466 = new RoomBooking();
        rb466.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_302"));
        rb466.setBookInDate(format.parse("2019-01-17"));
        bookingSessionLocal.createRoomBooking(rb466);
        PaymentTransaction PT466 = new PaymentTransaction();
        PT466.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT466.setTransactionDateTime(format.parse("2019-01-07"));
        PT466.setFinalPayment(231.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT466);

        RoomBooking rb467 = new RoomBooking();
        rb467.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_303"));
        rb467.setBookInDate(format.parse("2019-01-14"));
        bookingSessionLocal.createRoomBooking(rb467);
        PaymentTransaction PT467 = new PaymentTransaction();
        PT467.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT467.setTransactionDateTime(format.parse("2019-01-08"));
        PT467.setFinalPayment(321.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT467);

        RoomBooking rb468 = new RoomBooking();
        rb468.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_304"));
        rb468.setBookInDate(format.parse("2019-01-18"));
        bookingSessionLocal.createRoomBooking(rb468);
        PaymentTransaction PT468 = new PaymentTransaction();
        PT468.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT468.setTransactionDateTime(format.parse("2019-01-09"));
        PT468.setFinalPayment(142.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT468);

        RoomBooking rb469 = new RoomBooking();
        rb469.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_305"));
        rb469.setBookInDate(format.parse("2019-01-12"));
        bookingSessionLocal.createRoomBooking(rb469);
        PaymentTransaction PT469 = new PaymentTransaction();
        PT469.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT469.setTransactionDateTime(format.parse("2019-01-10"));
        PT469.setFinalPayment(231.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT469);

        RoomBooking rb470 = new RoomBooking();
        rb470.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_201"));
        rb470.setBookInDate(format.parse("2019-02-09"));
        bookingSessionLocal.createRoomBooking(rb470);
        PaymentTransaction PT470 = new PaymentTransaction();
        PT470.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT470.setTransactionDateTime(format.parse("2019-02-01"));
        PT470.setFinalPayment(421.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT470);

        RoomBooking rb471 = new RoomBooking();
        rb471.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_202"));
        rb471.setBookInDate(format.parse("2019-02-12"));
        bookingSessionLocal.createRoomBooking(rb471);
        PaymentTransaction PT471 = new PaymentTransaction();
        PT471.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT471.setTransactionDateTime(format.parse("2019-02-02"));
        PT471.setFinalPayment(231.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT471);

        RoomBooking rb472 = new RoomBooking();
        rb472.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_203"));
        rb472.setBookInDate(format.parse("2019-02-14"));
        bookingSessionLocal.createRoomBooking(rb472);
        PaymentTransaction PT472 = new PaymentTransaction();
        PT472.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT472.setTransactionDateTime(format.parse("2019-02-03"));
        PT472.setFinalPayment(123.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT472);

        RoomBooking rb473 = new RoomBooking();
        rb473.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_204"));
        rb473.setBookInDate(format.parse("2019-02-13"));
        bookingSessionLocal.createRoomBooking(rb473);
        PaymentTransaction PT473 = new PaymentTransaction();
        PT473.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT473.setTransactionDateTime(format.parse("2019-02-04"));
        PT473.setFinalPayment(232.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT473);

        RoomBooking rb474 = new RoomBooking();
        rb474.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_205"));
        rb474.setBookInDate(format.parse("2019-02-18"));
        bookingSessionLocal.createRoomBooking(rb474);
        PaymentTransaction PT474 = new PaymentTransaction();
        PT474.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT474.setTransactionDateTime(format.parse("2019-02-05"));
        PT474.setFinalPayment(231.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT474);

        RoomBooking rb475 = new RoomBooking();
        rb475.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_301"));
        rb475.setBookInDate(format.parse("2019-02-12"));
        bookingSessionLocal.createRoomBooking(rb475);
        PaymentTransaction PT475 = new PaymentTransaction();
        PT475.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT475.setTransactionDateTime(format.parse("2019-02-06"));
        PT475.setFinalPayment(123.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT475);

        RoomBooking rb476 = new RoomBooking();
        rb476.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_302"));
        rb476.setBookInDate(format.parse("2019-02-13"));
        bookingSessionLocal.createRoomBooking(rb476);
        PaymentTransaction PT476 = new PaymentTransaction();
        PT476.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT476.setTransactionDateTime(format.parse("2019-02-07"));
        PT476.setFinalPayment(182.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT476);

        RoomBooking rb477 = new RoomBooking();
        rb477.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_303"));
        rb477.setBookInDate(format.parse("2019-02-18"));
        bookingSessionLocal.createRoomBooking(rb477);
        PaymentTransaction PT477 = new PaymentTransaction();
        PT477.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT477.setTransactionDateTime(format.parse("2019-02-08"));
        PT477.setFinalPayment(217.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT477);

        RoomBooking rb478 = new RoomBooking();
        rb478.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_304"));
        rb478.setBookInDate(format.parse("2019-02-13"));
        bookingSessionLocal.createRoomBooking(rb478);
        PaymentTransaction PT478 = new PaymentTransaction();
        PT478.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT478.setTransactionDateTime(format.parse("2019-02-09"));
        PT478.setFinalPayment(182.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT478);

        RoomBooking rb479 = new RoomBooking();
        rb479.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_305"));
        rb479.setBookInDate(format.parse("2019-02-19"));
        bookingSessionLocal.createRoomBooking(rb479);
        PaymentTransaction PT479 = new PaymentTransaction();
        PT479.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT479.setTransactionDateTime(format.parse("2019-02-10"));
        PT479.setFinalPayment(123.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT479);

        RoomBooking rb480 = new RoomBooking();
        rb480.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_201"));
        rb480.setBookInDate(format.parse("2019-03-09"));
        bookingSessionLocal.createRoomBooking(rb480);
        PaymentTransaction PT480 = new PaymentTransaction();
        PT480.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT480.setTransactionDateTime(format.parse("2019-03-01"));
        PT480.setFinalPayment(321.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT480);

        RoomBooking rb481 = new RoomBooking();
        rb481.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_202"));
        rb481.setBookInDate(format.parse("2019-03-13"));
        bookingSessionLocal.createRoomBooking(rb481);
        PaymentTransaction PT481 = new PaymentTransaction();
        PT481.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT481.setTransactionDateTime(format.parse("2019-03-02"));
        PT481.setFinalPayment(327.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT481);

        RoomBooking rb482 = new RoomBooking();
        rb482.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_203"));
        rb482.setBookInDate(format.parse("2019-03-18"));
        bookingSessionLocal.createRoomBooking(rb482);
        PaymentTransaction PT482 = new PaymentTransaction();
        PT482.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT482.setTransactionDateTime(format.parse("2019-03-03"));
        PT482.setFinalPayment(142.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT482);

        RoomBooking rb483 = new RoomBooking();
        rb483.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_204"));
        rb483.setBookInDate(format.parse("2019-03-18"));
        bookingSessionLocal.createRoomBooking(rb483);
        PaymentTransaction PT483 = new PaymentTransaction();
        PT483.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT483.setTransactionDateTime(format.parse("2019-03-04"));
        PT483.setFinalPayment(218.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT483);

        RoomBooking rb484 = new RoomBooking();
        rb484.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_205"));
        rb484.setBookInDate(format.parse("2019-03-18"));
        bookingSessionLocal.createRoomBooking(rb484);
        PaymentTransaction PT484 = new PaymentTransaction();
        PT484.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT484.setTransactionDateTime(format.parse("2019-03-05"));
        PT484.setFinalPayment(271.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT484);

        RoomBooking rb485 = new RoomBooking();
        rb485.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_301"));
        rb485.setBookInDate(format.parse("2019-03-12"));
        bookingSessionLocal.createRoomBooking(rb485);
        PaymentTransaction PT485 = new PaymentTransaction();
        PT485.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT485.setTransactionDateTime(format.parse("2019-03-06"));
        PT485.setFinalPayment(217.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT485);

        RoomBooking rb486 = new RoomBooking();
        rb486.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_302"));
        rb486.setBookInDate(format.parse("2019-03-09"));
        bookingSessionLocal.createRoomBooking(rb486);
        PaymentTransaction PT486 = new PaymentTransaction();
        PT486.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT486.setTransactionDateTime(format.parse("2019-03-06"));
        PT486.setFinalPayment(231.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT486);

        RoomBooking rb487 = new RoomBooking();
        rb487.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_303"));
        rb487.setBookInDate(format.parse("2019-03-18"));
        bookingSessionLocal.createRoomBooking(rb487);
        PaymentTransaction PT487 = new PaymentTransaction();
        PT487.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT487.setTransactionDateTime(format.parse("2019-03-08"));
        PT487.setFinalPayment(219.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT487);

        RoomBooking rb488 = new RoomBooking();
        rb488.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_304"));
        rb488.setBookInDate(format.parse("2019-03-18"));
        bookingSessionLocal.createRoomBooking(rb488);
        PaymentTransaction PT488 = new PaymentTransaction();
        PT488.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT488.setTransactionDateTime(format.parse("2019-03-09"));
        PT488.setFinalPayment(327.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT488);

        RoomBooking rb489 = new RoomBooking();
        rb489.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_305"));
        rb489.setBookInDate(format.parse("2019-03-19"));
        bookingSessionLocal.createRoomBooking(rb489);
        PaymentTransaction PT489 = new PaymentTransaction();
        PT489.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT489.setTransactionDateTime(format.parse("2019-03-10"));
        PT489.setFinalPayment(318.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT489);

        RoomBooking rb490 = new RoomBooking();
        rb490.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_201"));
        rb490.setBookInDate(format.parse("2019-04-09"));
        bookingSessionLocal.createRoomBooking(rb490);
        PaymentTransaction PT490 = new PaymentTransaction();
        PT490.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT490.setTransactionDateTime(format.parse("2019-04-01"));
        PT490.setFinalPayment(218.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT490);

        RoomBooking rb491 = new RoomBooking();
        rb491.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_202"));
        rb491.setBookInDate(format.parse("2019-04-12"));
        bookingSessionLocal.createRoomBooking(rb491);
        PaymentTransaction PT491 = new PaymentTransaction();
        PT491.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT491.setTransactionDateTime(format.parse("2019-04-02"));
        PT491.setFinalPayment(271.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT491);

        RoomBooking rb492 = new RoomBooking();
        rb492.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_203"));
        rb492.setBookInDate(format.parse("2019-04-18"));
        bookingSessionLocal.createRoomBooking(rb492);
        PaymentTransaction PT492 = new PaymentTransaction();
        PT492.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT492.setTransactionDateTime(format.parse("2019-04-03"));
        PT492.setFinalPayment(318.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT492);

        RoomBooking rb493 = new RoomBooking();
        rb493.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_204"));
        rb493.setBookInDate(format.parse("2019-04-18"));
        bookingSessionLocal.createRoomBooking(rb493);
        PaymentTransaction PT493 = new PaymentTransaction();
        PT493.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT493.setTransactionDateTime(format.parse("2019-04-04"));
        PT493.setFinalPayment(182.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT493);

        RoomBooking rb494 = new RoomBooking();
        rb494.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_205"));
        rb494.setBookInDate(format.parse("2019-04-12"));
        bookingSessionLocal.createRoomBooking(rb494);
        PaymentTransaction PT494 = new PaymentTransaction();
        PT494.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT494.setTransactionDateTime(format.parse("2019-04-05"));
        PT494.setFinalPayment(284.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT494);

        RoomBooking rb495 = new RoomBooking();
        rb495.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_301"));
        rb495.setBookInDate(format.parse("2019-04-12"));
        bookingSessionLocal.createRoomBooking(rb495);
        PaymentTransaction PT495 = new PaymentTransaction();
        PT495.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT495.setTransactionDateTime(format.parse("2019-04-06"));
        PT495.setFinalPayment(128.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT495);

        RoomBooking rb496 = new RoomBooking();
        rb496.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_302"));
        rb496.setBookInDate(format.parse("2019-04-17"));
        bookingSessionLocal.createRoomBooking(rb496);
        PaymentTransaction PT496 = new PaymentTransaction();
        PT496.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT496.setTransactionDateTime(format.parse("2019-04-07"));
        PT496.setFinalPayment(482.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT496);

        RoomBooking rb497 = new RoomBooking();
        rb497.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_303"));
        rb497.setBookInDate(format.parse("2019-04-13"));
        bookingSessionLocal.createRoomBooking(rb497);
        PaymentTransaction PT497 = new PaymentTransaction();
        PT497.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT497.setTransactionDateTime(format.parse("2019-04-08"));
        PT497.setFinalPayment(271.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT497);

        RoomBooking rb498 = new RoomBooking();
        rb498.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_304"));
        rb498.setBookInDate(format.parse("2019-04-13"));
        bookingSessionLocal.createRoomBooking(rb498);
        PaymentTransaction PT498 = new PaymentTransaction();
        PT498.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT498.setTransactionDateTime(format.parse("2019-04-09"));
        PT498.setFinalPayment(318.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT498);

        RoomBooking rb499 = new RoomBooking();
        rb499.setBookedRoom(roomSessionLocal.getRoomByName("KRSW_305"));
        rb499.setBookInDate(format.parse("2019-04-18"));
        bookingSessionLocal.createRoomBooking(rb499);
        PaymentTransaction PT499 = new PaymentTransaction();
        PT499.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT499.setTransactionDateTime(format.parse("2019-04-10"));
        PT499.setFinalPayment(281.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT499);		


    }
	
    public void initializeRoomBooking2() throws ParseException, NoResultException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        RoomBooking rb20 = new RoomBooking();
        rb20.setBookedRoom(roomSessionLocal.getRoomByName("KRG_201"));
        rb20.setBookInDate(format.parse("2019-01-09"));
        bookingSessionLocal.createRoomBooking(rb20);
        PaymentTransaction PT10 = new PaymentTransaction();
        PT10.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT10.setTransactionDateTime(format.parse("2019-01-05"));
        PT10.setFinalPayment(392.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT10);

        RoomBooking rb21 = new RoomBooking();
        rb21.setBookedRoom(roomSessionLocal.getRoomByName("KRG_202"));
        rb21.setBookInDate(format.parse("2019-01-10"));
        bookingSessionLocal.createRoomBooking(rb21);
        PaymentTransaction PT11 = new PaymentTransaction();
        PT11.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT11.setTransactionDateTime(format.parse("2019-01-06"));
        PT11.setFinalPayment(1129.72);
        paymentTransactionSessionLocal.createPaymentTransaction(PT11);

        RoomBooking rb22 = new RoomBooking();
        rb22.setBookedRoom(roomSessionLocal.getRoomByName("KRG_303"));
        rb22.setBookInDate(format.parse("2019-01-13"));
        bookingSessionLocal.createRoomBooking(rb22);
        PaymentTransaction PT12 = new PaymentTransaction();
        PT12.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT12.setTransactionDateTime(format.parse("2019-01-09"));
        PT12.setFinalPayment(695.50);
        paymentTransactionSessionLocal.createPaymentTransaction(PT12);

        RoomBooking rb23 = new RoomBooking();
        rb23.setBookedRoom(roomSessionLocal.getRoomByName("KRG_206"));
        rb23.setBookInDate(format.parse("2019-01-18"));
        bookingSessionLocal.createRoomBooking(rb23);
        PaymentTransaction PT13 = new PaymentTransaction();
        PT13.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT13.setTransactionDateTime(format.parse("2019-01-14"));
        PT13.setFinalPayment(554.45);
        paymentTransactionSessionLocal.createPaymentTransaction(PT13);

        RoomBooking rb24 = new RoomBooking();
        rb24.setBookedRoom(roomSessionLocal.getRoomByName("KRG_405"));
        rb24.setBookInDate(format.parse("2019-01-19"));
        bookingSessionLocal.createRoomBooking(rb24);
        PaymentTransaction PT14 = new PaymentTransaction();
        PT14.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT14.setTransactionDateTime(format.parse("2019-01-14"));
        PT14.setFinalPayment(386.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT14);

        RoomBooking rb25 = new RoomBooking();
        rb25.setBookedRoom(roomSessionLocal.getRoomByName("KRG_506"));
        rb25.setBookInDate(format.parse("2019-01-22"));
        bookingSessionLocal.createRoomBooking(rb25);
        PaymentTransaction PT15 = new PaymentTransaction();
        PT15.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT15.setTransactionDateTime(format.parse("2019-01-17"));
        PT15.setFinalPayment(256.10);
        paymentTransactionSessionLocal.createPaymentTransaction(PT15);

        RoomBooking rb26 = new RoomBooking();
        rb26.setBookedRoom(roomSessionLocal.getRoomByName("KRG_207"));
        rb26.setBookInDate(format.parse("2019-01-22"));
        bookingSessionLocal.createRoomBooking(rb26);
        PaymentTransaction PT16 = new PaymentTransaction();
        PT16.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT16.setTransactionDateTime(format.parse("2019-01-18"));
        PT16.setFinalPayment(768.20);
        paymentTransactionSessionLocal.createPaymentTransaction(PT16);

        RoomBooking rb27 = new RoomBooking();
        rb27.setBookedRoom(roomSessionLocal.getRoomByName("KRG_408"));
        rb27.setBookInDate(format.parse("2019-01-26"));
        bookingSessionLocal.createRoomBooking(rb27);
        PaymentTransaction PT17 = new PaymentTransaction();
        PT17.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT17.setTransactionDateTime(format.parse("2019-01-21"));
        PT17.setFinalPayment(1024.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT17);

        RoomBooking rb28 = new RoomBooking();
        rb28.setBookedRoom(roomSessionLocal.getRoomByName("KRG_209"));
        rb28.setBookInDate(format.parse("2019-02-03"));
        bookingSessionLocal.createRoomBooking(rb28);
        PaymentTransaction PT18 = new PaymentTransaction();
        PT18.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT18.setTransactionDateTime(format.parse("2019-01-26"));
        PT18.setFinalPayment(512.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT18);

        RoomBooking rb29 = new RoomBooking();
        rb29.setBookedRoom(roomSessionLocal.getRoomByName("KRG_510"));
        rb29.setBookInDate(format.parse("2019-02-04"));
        bookingSessionLocal.createRoomBooking(rb29);
        PaymentTransaction PT19 = new PaymentTransaction();
        PT19.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT19.setTransactionDateTime(format.parse("2019-01-27"));
        PT19.setFinalPayment(461.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT19);

        RoomBooking rb30 = new RoomBooking();
        rb30.setBookedRoom(roomSessionLocal.getRoomByName("KRG_505"));
        rb30.setBookInDate(format.parse("2019-02-09"));
        bookingSessionLocal.createRoomBooking(rb30);
        PaymentTransaction PT20 = new PaymentTransaction();
        PT20.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT20.setTransactionDateTime(format.parse("2019-02-02"));
        PT20.setFinalPayment(652.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT20);

        RoomBooking rb31 = new RoomBooking();
        rb31.setBookedRoom(roomSessionLocal.getRoomByName("KRG_209"));
        rb31.setBookInDate(format.parse("2019-02-09"));
        bookingSessionLocal.createRoomBooking(rb31);
        PaymentTransaction PT21 = new PaymentTransaction();
        PT21.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT21.setTransactionDateTime(format.parse("2019-02-04"));
        PT21.setFinalPayment(763.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT21);

        RoomBooking rb32 = new RoomBooking();
        rb32.setBookedRoom(roomSessionLocal.getRoomByName("KRG_802"));
        rb32.setBookInDate(format.parse("2019-02-11"));
        bookingSessionLocal.createRoomBooking(rb32);
        PaymentTransaction PT22 = new PaymentTransaction();
        PT22.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT22.setTransactionDateTime(format.parse("2019-02-06"));
        PT22.setFinalPayment(1024.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT22);

        RoomBooking rb33 = new RoomBooking();
        rb33.setBookedRoom(roomSessionLocal.getRoomByName("KRG_908"));
        rb33.setBookInDate(format.parse("2019-02-11"));
        bookingSessionLocal.createRoomBooking(rb33);
        PaymentTransaction PT23 = new PaymentTransaction();
        PT23.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT23.setTransactionDateTime(format.parse("2019-02-07"));
        PT23.setFinalPayment(2261.70);
        paymentTransactionSessionLocal.createPaymentTransaction(PT23);

        RoomBooking rb35 = new RoomBooking();
        rb35.setBookedRoom(roomSessionLocal.getRoomByName("KRG_1204"));
        rb35.setBookInDate(format.parse("2019-02-14"));
        bookingSessionLocal.createRoomBooking(rb35);
        PaymentTransaction PT24 = new PaymentTransaction();
        PT24.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT24.setTransactionDateTime(format.parse("2019-02-09"));
        PT24.setFinalPayment(3213.50);
        paymentTransactionSessionLocal.createPaymentTransaction(PT24);

        RoomBooking rb36 = new RoomBooking();
        rb36.setBookedRoom(roomSessionLocal.getRoomByName("KRG_606"));
        rb36.setBookInDate(format.parse("2019-02-15"));
        bookingSessionLocal.createRoomBooking(rb36);
        PaymentTransaction PT25 = new PaymentTransaction();
        PT25.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT25.setTransactionDateTime(format.parse("2019-02-10"));
        PT25.setFinalPayment(1532.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT25);

        RoomBooking rb37 = new RoomBooking();
        rb37.setBookedRoom(roomSessionLocal.getRoomByName("KRG_708"));
        rb37.setBookInDate(format.parse("2019-02-19"));
        bookingSessionLocal.createRoomBooking(rb37);
        PaymentTransaction PT26 = new PaymentTransaction();
        PT26.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT26.setTransactionDateTime(format.parse("2019-02-13"));
        PT26.setFinalPayment(791.60);
        paymentTransactionSessionLocal.createPaymentTransaction(PT26);

        RoomBooking rb38 = new RoomBooking();
        rb38.setBookedRoom(roomSessionLocal.getRoomByName("KRG_904"));
        rb38.setBookInDate(format.parse("2019-02-19"));
        bookingSessionLocal.createRoomBooking(rb38);
        PaymentTransaction PT27 = new PaymentTransaction();
        PT27.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT27.setTransactionDateTime(format.parse("2019-02-14"));
        PT27.setFinalPayment(1822.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT27);

        RoomBooking rb39 = new RoomBooking();
        rb39.setBookedRoom(roomSessionLocal.getRoomByName("KRG_902"));
        rb39.setBookInDate(format.parse("2019-02-23"));
        bookingSessionLocal.createRoomBooking(rb39);
        PaymentTransaction PT28 = new PaymentTransaction();
        PT28.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT28.setTransactionDateTime(format.parse("2019-02-19"));
        PT28.setFinalPayment(962.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT28);

        RoomBooking rb40 = new RoomBooking();
        rb40.setBookedRoom(roomSessionLocal.getRoomByName("KRG_904"));
        rb40.setBookInDate(format.parse("2019-02-25"));
        bookingSessionLocal.createRoomBooking(rb40);
        PaymentTransaction PT29 = new PaymentTransaction();
        PT29.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT29.setTransactionDateTime(format.parse("2019-02-22"));
        PT29.setFinalPayment(452.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT29);

        RoomBooking rb41 = new RoomBooking();
        rb41.setBookedRoom(roomSessionLocal.getRoomByName("KRG_402"));
        rb41.setBookInDate(format.parse("2019-03-05"));
        bookingSessionLocal.createRoomBooking(rb41);
        PaymentTransaction PT30 = new PaymentTransaction();
        PT30.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT30.setTransactionDateTime(format.parse("2019-03-02"));
        PT30.setFinalPayment(587.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT30);

        RoomBooking rb42 = new RoomBooking();
        rb42.setBookedRoom(roomSessionLocal.getRoomByName("KRG_602"));
        rb42.setBookInDate(format.parse("2019-03-07"));
        bookingSessionLocal.createRoomBooking(rb42);
        PaymentTransaction PT31 = new PaymentTransaction();
        PT31.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT31.setTransactionDateTime(format.parse("2019-03-03"));
        PT31.setFinalPayment(599.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT31);

        RoomBooking rb43 = new RoomBooking();
        rb43.setBookedRoom(roomSessionLocal.getRoomByName("KRG_703"));
        rb43.setBookInDate(format.parse("2019-03-14"));
        bookingSessionLocal.createRoomBooking(rb43);
        PaymentTransaction PT32 = new PaymentTransaction();
        PT32.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT32.setTransactionDateTime(format.parse("2019-03-07"));
        PT32.setFinalPayment(768.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT32);

        RoomBooking rb44 = new RoomBooking();
        rb44.setBookedRoom(roomSessionLocal.getRoomByName("KRG_1103"));
        rb44.setBookInDate(format.parse("2019-03-13"));
        bookingSessionLocal.createRoomBooking(rb44);
        PaymentTransaction PT33 = new PaymentTransaction();
        PT33.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT33.setTransactionDateTime(format.parse("2019-03-09"));
        PT33.setFinalPayment(1238.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT33);

        RoomBooking rb45 = new RoomBooking();
        rb45.setBookedRoom(roomSessionLocal.getRoomByName("KRG_1402"));
        rb45.setBookInDate(format.parse("2019-03-18"));
        bookingSessionLocal.createRoomBooking(rb45);
        PaymentTransaction PT34 = new PaymentTransaction();
        PT34.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT34.setTransactionDateTime(format.parse("2019-03-13"));
        PT34.setFinalPayment(4388.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT34);

        RoomBooking rb46 = new RoomBooking();
        rb46.setBookedRoom(roomSessionLocal.getRoomByName("KRG_1203"));
        rb46.setBookInDate(format.parse("2019-03-22"));
        bookingSessionLocal.createRoomBooking(rb46);
        PaymentTransaction PT35 = new PaymentTransaction();
        PT35.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT35.setTransactionDateTime(format.parse("2019-03-17"));
        PT35.setFinalPayment(1462.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT35);

        RoomBooking rb47 = new RoomBooking();
        rb47.setBookedRoom(roomSessionLocal.getRoomByName("KRG_203"));
        rb47.setBookInDate(format.parse("2019-03-26"));
        bookingSessionLocal.createRoomBooking(rb47);
        PaymentTransaction PT36 = new PaymentTransaction();
        PT36.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT36.setTransactionDateTime(format.parse("2019-03-19"));
        PT36.setFinalPayment(732.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT36);

        RoomBooking rb48 = new RoomBooking();
        rb48.setBookedRoom(roomSessionLocal.getRoomByName("KRG_703"));
        rb48.setBookInDate(format.parse("2019-03-25"));
        bookingSessionLocal.createRoomBooking(rb48);
        PaymentTransaction PT37 = new PaymentTransaction();
        PT37.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT37.setTransactionDateTime(format.parse("2019-03-21"));
        PT37.setFinalPayment(741.40);
        paymentTransactionSessionLocal.createPaymentTransaction(PT37);

        RoomBooking rb49 = new RoomBooking();
        rb49.setBookedRoom(roomSessionLocal.getRoomByName("KRG_707"));
        rb49.setBookInDate(format.parse("2019-03-29"));
        bookingSessionLocal.createRoomBooking(rb49);
        PaymentTransaction PT38 = new PaymentTransaction();
        PT38.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT38.setTransactionDateTime(format.parse("2019-03-25"));
        PT38.setFinalPayment(552.99);
        paymentTransactionSessionLocal.createPaymentTransaction(PT38);

        RoomBooking rb50 = new RoomBooking();
        rb50.setBookedRoom(roomSessionLocal.getRoomByName("KRG_808"));
        rb50.setBookInDate(format.parse("2019-04-01"));
        bookingSessionLocal.createRoomBooking(rb50);
        PaymentTransaction PT39 = new PaymentTransaction();
        PT39.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT39.setTransactionDateTime(format.parse("2019-03-28"));
        PT39.setFinalPayment(746.10);
        paymentTransactionSessionLocal.createPaymentTransaction(PT39);

        RoomBooking rb51 = new RoomBooking();
        rb51.setBookedRoom(roomSessionLocal.getRoomByName("KRG_407"));
        rb51.setBookInDate(format.parse("2019-04-07"));
        bookingSessionLocal.createRoomBooking(rb51);
        PaymentTransaction PT40 = new PaymentTransaction();
        PT40.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT40.setTransactionDateTime(format.parse("2019-04-04"));
        PT40.setFinalPayment(1372.30);
        paymentTransactionSessionLocal.createPaymentTransaction(PT40);

        RoomBooking rb52 = new RoomBooking();
        rb52.setBookedRoom(roomSessionLocal.getRoomByName("KRG_905"));
        rb52.setBookInDate(format.parse("2019-04-15"));
        bookingSessionLocal.createRoomBooking(rb52);
        PaymentTransaction PT41 = new PaymentTransaction();
        PT41.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT41.setTransactionDateTime(format.parse("2019-04-09"));
        PT41.setFinalPayment(2166.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT41);

        RoomBooking rb53 = new RoomBooking();
        rb53.setBookedRoom(roomSessionLocal.getRoomByName("KRG_403"));
        rb53.setBookInDate(format.parse("2019-04-15"));
        bookingSessionLocal.createRoomBooking(rb53);
        PaymentTransaction PT42 = new PaymentTransaction();
        PT42.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT42.setTransactionDateTime(format.parse("2019-04-10"));
        PT42.setFinalPayment(1114.70);
        paymentTransactionSessionLocal.createPaymentTransaction(PT42);

        RoomBooking rb54 = new RoomBooking();
        rb54.setBookedRoom(roomSessionLocal.getRoomByName("KRG_608"));
        rb54.setBookInDate(format.parse("2019-04-16"));
        bookingSessionLocal.createRoomBooking(rb54);
        PaymentTransaction PT43 = new PaymentTransaction();
        PT43.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT43.setTransactionDateTime(format.parse("2019-04-10"));
        PT43.setFinalPayment(832.10);
        paymentTransactionSessionLocal.createPaymentTransaction(PT43);

        RoomBooking rb55 = new RoomBooking();
        rb55.setBookedRoom(roomSessionLocal.getRoomByName("KRG_702"));
        rb55.setBookInDate(format.parse("2019-04-16"));
        bookingSessionLocal.createRoomBooking(rb55);
        PaymentTransaction PT44 = new PaymentTransaction();
        PT44.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT44.setTransactionDateTime(format.parse("2019-04-11"));
        PT44.setFinalPayment(874.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT44);

        RoomBooking rb56 = new RoomBooking();
        rb56.setBookedRoom(roomSessionLocal.getRoomByName("KRG_303"));
        rb56.setBookInDate(format.parse("2019-04-17"));
        bookingSessionLocal.createRoomBooking(rb56);
        PaymentTransaction PT45 = new PaymentTransaction();
        PT45.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT45.setTransactionDateTime(format.parse("2019-04-12"));
        PT45.setFinalPayment(522.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT45);

        RoomBooking rb57 = new RoomBooking();
        rb57.setBookedRoom(roomSessionLocal.getRoomByName("KRG_904"));
        rb57.setBookInDate(format.parse("2019-04-18"));
        bookingSessionLocal.createRoomBooking(rb57);
        PaymentTransaction PT46 = new PaymentTransaction();
        PT46.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT46.setTransactionDateTime(format.parse("2019-04-13"));
        PT46.setFinalPayment(8483.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT46);

        RoomBooking rb58 = new RoomBooking();
        rb58.setBookedRoom(roomSessionLocal.getRoomByName("KRG_1201"));
        rb58.setBookInDate(format.parse("2019-04-19"));
        bookingSessionLocal.createRoomBooking(rb58);
        PaymentTransaction PT47 = new PaymentTransaction();
        PT47.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT47.setTransactionDateTime(format.parse("2019-04-14"));
        PT47.setFinalPayment(10512.70);
        paymentTransactionSessionLocal.createPaymentTransaction(PT47);

        RoomBooking rb59 = new RoomBooking();
        rb59.setBookedRoom(roomSessionLocal.getRoomByName("KRG_1202"));
        rb59.setBookInDate(format.parse("2019-04-22"));
        bookingSessionLocal.createRoomBooking(rb59);
        PaymentTransaction PT48 = new PaymentTransaction();
        PT48.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT48.setTransactionDateTime(format.parse("2019-04-17"));
        PT48.setFinalPayment(5622.60);
        paymentTransactionSessionLocal.createPaymentTransaction(PT48);

        RoomBooking rb60 = new RoomBooking();
        rb60.setBookedRoom(roomSessionLocal.getRoomByName("KRG_1203"));
        rb60.setBookInDate(format.parse("2019-04-25"));
        bookingSessionLocal.createRoomBooking(rb60);
        PaymentTransaction PT49 = new PaymentTransaction();
        PT49.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT49.setTransactionDateTime(format.parse("2019-04-19"));
        PT49.setFinalPayment(6782.60);
        paymentTransactionSessionLocal.createPaymentTransaction(PT49);

        RoomBooking rb61 = new RoomBooking();
        rb61.setBookedRoom(roomSessionLocal.getRoomByName("KRC_602"));
        rb61.setBookInDate(format.parse("2019-01-05"));
        bookingSessionLocal.createRoomBooking(rb61);
        PaymentTransaction PT50 = new PaymentTransaction();
        PT50.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT50.setTransactionDateTime(format.parse("2019-01-01"));
        PT50.setFinalPayment(602.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT50);

        RoomBooking rb62 = new RoomBooking();
        rb62.setBookedRoom(roomSessionLocal.getRoomByName("KRC_1201"));
        rb62.setBookInDate(format.parse("2019-01-06"));
        bookingSessionLocal.createRoomBooking(rb62);
        PaymentTransaction PT51 = new PaymentTransaction();
        PT51.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT51.setTransactionDateTime(format.parse("2019-01-02"));
        PT51.setFinalPayment(2932.40);
        paymentTransactionSessionLocal.createPaymentTransaction(PT51);

        RoomBooking rb63 = new RoomBooking();
        rb63.setBookedRoom(roomSessionLocal.getRoomByName("KRC_303"));
        rb63.setBookInDate(format.parse("2019-01-06"));
        bookingSessionLocal.createRoomBooking(rb63);
        PaymentTransaction PT52 = new PaymentTransaction();
        PT52.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT52.setTransactionDateTime(format.parse("2019-01-03"));
        PT52.setFinalPayment(456.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT52);

        RoomBooking rb64 = new RoomBooking();
        rb64.setBookedRoom(roomSessionLocal.getRoomByName("KRC_502"));
        rb64.setBookInDate(format.parse("2019-01-08"));
        bookingSessionLocal.createRoomBooking(rb64);
        PaymentTransaction PT53 = new PaymentTransaction();
        PT53.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT53.setTransactionDateTime(format.parse("2019-01-04"));
        PT53.setFinalPayment(923.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT53);

        RoomBooking rb65 = new RoomBooking();
        rb65.setBookedRoom(roomSessionLocal.getRoomByName("KRC_1202"));
        rb65.setBookInDate(format.parse("2019-01-13"));
        bookingSessionLocal.createRoomBooking(rb65);
        PaymentTransaction PT54 = new PaymentTransaction();
        PT54.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT54.setTransactionDateTime(format.parse("2019-01-10"));
        PT54.setFinalPayment(2296.78);
        paymentTransactionSessionLocal.createPaymentTransaction(PT54);

        RoomBooking rb66 = new RoomBooking();
        rb66.setBookedRoom(roomSessionLocal.getRoomByName("KRC_508"));
        rb66.setBookInDate(format.parse("2019-01-16"));
        bookingSessionLocal.createRoomBooking(rb66);
        PaymentTransaction PT55 = new PaymentTransaction();
        PT55.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT55.setTransactionDateTime(format.parse("2019-01-11"));
        PT55.setFinalPayment(587.32);
        paymentTransactionSessionLocal.createPaymentTransaction(PT55);

        RoomBooking rb67 = new RoomBooking();
        rb67.setBookedRoom(roomSessionLocal.getRoomByName("KRC_904"));
        rb67.setBookInDate(format.parse("2019-01-19"));
        bookingSessionLocal.createRoomBooking(rb67);
        PaymentTransaction PT56 = new PaymentTransaction();
        PT56.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT56.setTransactionDateTime(format.parse("2019-01-12"));
        PT56.setFinalPayment(1621.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT56);

        RoomBooking rb68 = new RoomBooking();
        rb68.setBookedRoom(roomSessionLocal.getRoomByName("KRC_1203"));
        rb68.setBookInDate(format.parse("2019-01-28"));
        bookingSessionLocal.createRoomBooking(rb68);
        PaymentTransaction PT57 = new PaymentTransaction();
        PT57.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT57.setTransactionDateTime(format.parse("2019-01-23"));
        PT57.setFinalPayment(4981.50);
        paymentTransactionSessionLocal.createPaymentTransaction(PT57);

        RoomBooking rb69 = new RoomBooking();
        rb69.setBookedRoom(roomSessionLocal.getRoomByName("KRC_1202"));
        rb69.setBookInDate(format.parse("2019-01-29"));
        bookingSessionLocal.createRoomBooking(rb69);
        PaymentTransaction PT58 = new PaymentTransaction();
        PT58.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT58.setTransactionDateTime(format.parse("2019-01-24"));
        PT58.setFinalPayment(1100.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT58);

        RoomBooking rb70 = new RoomBooking();
        rb70.setBookedRoom(roomSessionLocal.getRoomByName("KRC_702"));
        rb70.setBookInDate(format.parse("2019-02-01"));
        bookingSessionLocal.createRoomBooking(rb70);
        PaymentTransaction PT59 = new PaymentTransaction();
        PT59.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT59.setTransactionDateTime(format.parse("2019-01-25"));
        PT59.setFinalPayment(692.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT59);

        RoomBooking rb71 = new RoomBooking();
        rb71.setBookedRoom(roomSessionLocal.getRoomByName("KRC_302"));
        rb71.setBookInDate(format.parse("2019-02-09"));
        bookingSessionLocal.createRoomBooking(rb71);
        PaymentTransaction PT60 = new PaymentTransaction();
        PT60.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT60.setTransactionDateTime(format.parse("2019-02-04"));
        PT60.setFinalPayment(762.50);
        paymentTransactionSessionLocal.createPaymentTransaction(PT60);

        RoomBooking rb72 = new RoomBooking();
        rb72.setBookedRoom(roomSessionLocal.getRoomByName("KRC_303"));
        rb72.setBookInDate(format.parse("2019-02-09"));
        bookingSessionLocal.createRoomBooking(rb72);
        PaymentTransaction PT61 = new PaymentTransaction();
        PT61.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT61.setTransactionDateTime(format.parse("2019-02-05"));
        PT61.setFinalPayment(742.30);
        paymentTransactionSessionLocal.createPaymentTransaction(PT61);

        RoomBooking rb73 = new RoomBooking();
        rb73.setBookedRoom(roomSessionLocal.getRoomByName("KRC_305"));
        rb73.setBookInDate(format.parse("2019-02-10"));
        bookingSessionLocal.createRoomBooking(rb73);
        PaymentTransaction PT62 = new PaymentTransaction();
        PT62.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT62.setTransactionDateTime(format.parse("2019-02-05"));
        PT62.setFinalPayment(815.40);
        paymentTransactionSessionLocal.createPaymentTransaction(PT62);

        RoomBooking rb74 = new RoomBooking();
        rb74.setBookedRoom(roomSessionLocal.getRoomByName("KRC_403"));
        rb74.setBookInDate(format.parse("2019-02-15"));
        bookingSessionLocal.createRoomBooking(rb74);
        PaymentTransaction PT63 = new PaymentTransaction();
        PT63.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT63.setTransactionDateTime(format.parse("2019-02-09"));
        PT63.setFinalPayment(814.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT63);

        RoomBooking rb75 = new RoomBooking();
        rb75.setBookedRoom(roomSessionLocal.getRoomByName("KRC_404"));
        rb75.setBookInDate(format.parse("2019-02-19"));
        bookingSessionLocal.createRoomBooking(rb75);
        PaymentTransaction PT64 = new PaymentTransaction();
        PT64.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT64.setTransactionDateTime(format.parse("2019-02-14"));
        PT64.setFinalPayment(862.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT64);

        RoomBooking rb76 = new RoomBooking();
        rb76.setBookedRoom(roomSessionLocal.getRoomByName("KRC_405"));
        rb76.setBookInDate(format.parse("2019-02-22"));
        bookingSessionLocal.createRoomBooking(rb76);
        PaymentTransaction PT65 = new PaymentTransaction();
        PT65.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT65.setTransactionDateTime(format.parse("2019-02-16"));
        PT65.setFinalPayment(987.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT65);

        RoomBooking rb77 = new RoomBooking();
        rb77.setBookedRoom(roomSessionLocal.getRoomByName("KRC_506"));
        rb77.setBookInDate(format.parse("2019-02-22"));
        bookingSessionLocal.createRoomBooking(rb77);
        PaymentTransaction PT66 = new PaymentTransaction();
        PT66.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT66.setTransactionDateTime(format.parse("2019-02-17"));
        PT66.setFinalPayment(916.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT66);

        RoomBooking rb78 = new RoomBooking();
        rb78.setBookedRoom(roomSessionLocal.getRoomByName("KRC_603"));
        rb78.setBookInDate(format.parse("2019-02-27"));
        bookingSessionLocal.createRoomBooking(rb78);
        PaymentTransaction PT67 = new PaymentTransaction();
        PT67.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT67.setTransactionDateTime(format.parse("2019-02-21"));
        PT67.setFinalPayment(1227.60);
        paymentTransactionSessionLocal.createPaymentTransaction(PT67);

        RoomBooking rb79 = new RoomBooking();
        rb79.setBookedRoom(roomSessionLocal.getRoomByName("KRC_605"));
        rb79.setBookInDate(format.parse("2019-02-26"));
        bookingSessionLocal.createRoomBooking(rb79);
        PaymentTransaction PT68 = new PaymentTransaction();
        PT68.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT68.setTransactionDateTime(format.parse("2019-02-22"));
        PT68.setFinalPayment(681.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT68);

        RoomBooking rb80 = new RoomBooking();
        rb80.setBookedRoom(roomSessionLocal.getRoomByName("KRC_703"));
        rb80.setBookInDate(format.parse("2019-03-02"));
        bookingSessionLocal.createRoomBooking(rb80);
        PaymentTransaction PT69 = new PaymentTransaction();
        PT69.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT69.setTransactionDateTime(format.parse("2019-02-25"));
        PT69.setFinalPayment(975.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT69);

        RoomBooking rb81 = new RoomBooking();
        rb81.setBookedRoom(roomSessionLocal.getRoomByName("KRC_707"));
        rb81.setBookInDate(format.parse("2019-03-09"));
        bookingSessionLocal.createRoomBooking(rb81);
        PaymentTransaction PT70 = new PaymentTransaction();
        PT70.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT70.setTransactionDateTime(format.parse("2019-03-02"));
        PT70.setFinalPayment(1843.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT70);

        RoomBooking rb82 = new RoomBooking();
        rb82.setBookedRoom(roomSessionLocal.getRoomByName("KRC_802"));
        rb82.setBookInDate(format.parse("2019-03-08"));
        bookingSessionLocal.createRoomBooking(rb82);
        PaymentTransaction PT71 = new PaymentTransaction();
        PT71.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT71.setTransactionDateTime(format.parse("2019-03-03"));
        PT71.setFinalPayment(694.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT71);

        RoomBooking rb83 = new RoomBooking();
        rb83.setBookedRoom(roomSessionLocal.getRoomByName("KRC_903"));
        rb83.setBookInDate(format.parse("2019-03-11"));
        bookingSessionLocal.createRoomBooking(rb83);
        PaymentTransaction PT72 = new PaymentTransaction();
        PT72.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT72.setTransactionDateTime(format.parse("2019-03-06"));
        PT72.setFinalPayment(9752.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT72);

        RoomBooking rb84 = new RoomBooking();
        rb84.setBookedRoom(roomSessionLocal.getRoomByName("KRC_904"));
        rb84.setBookInDate(format.parse("2019-03-14"));
        bookingSessionLocal.createRoomBooking(rb84);
        PaymentTransaction PT73 = new PaymentTransaction();
        PT73.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT73.setTransactionDateTime(format.parse("2019-03-10"));
        PT73.setFinalPayment(1452.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT73);

        RoomBooking rb85 = new RoomBooking();
        rb85.setBookedRoom(roomSessionLocal.getRoomByName("KRC_302"));
        rb85.setBookInDate(format.parse("2019-03-17"));
        bookingSessionLocal.createRoomBooking(rb85);
        PaymentTransaction PT74 = new PaymentTransaction();
        PT74.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT74.setTransactionDateTime(format.parse("2019-03-13"));
        PT74.setFinalPayment(841.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT74);

        RoomBooking rb86 = new RoomBooking();
        rb86.setBookedRoom(roomSessionLocal.getRoomByName("KRC_304"));
        rb86.setBookInDate(format.parse("2019-03-22"));
        bookingSessionLocal.createRoomBooking(rb86);
        PaymentTransaction PT75 = new PaymentTransaction();
        PT75.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT75.setTransactionDateTime(format.parse("2019-03-16"));
        PT75.setFinalPayment(952.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT75);

        RoomBooking rb87 = new RoomBooking();
        rb87.setBookedRoom(roomSessionLocal.getRoomByName("KRC_306"));
        rb87.setBookInDate(format.parse("2019-03-22"));
        bookingSessionLocal.createRoomBooking(rb87);
        PaymentTransaction PT76 = new PaymentTransaction();
        PT76.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT76.setTransactionDateTime(format.parse("2019-03-19"));
        PT76.setFinalPayment(651.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT76);

        RoomBooking rb88 = new RoomBooking();
        rb88.setBookedRoom(roomSessionLocal.getRoomByName("KRC_403"));
        rb88.setBookInDate(format.parse("2019-03-28"));
        bookingSessionLocal.createRoomBooking(rb88);
        PaymentTransaction PT77 = new PaymentTransaction();
        PT77.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT77.setTransactionDateTime(format.parse("2019-03-21"));
        PT77.setFinalPayment(694.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT77);

        RoomBooking rb89 = new RoomBooking();
        rb89.setBookedRoom(roomSessionLocal.getRoomByName("KRC_405"));
        rb89.setBookInDate(format.parse("2019-03-28"));
        bookingSessionLocal.createRoomBooking(rb89);
        PaymentTransaction PT78 = new PaymentTransaction();
        PT78.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT78.setTransactionDateTime(format.parse("2019-03-24"));
        PT78.setFinalPayment(6152.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT78);

        RoomBooking rb90 = new RoomBooking();
        rb90.setBookedRoom(roomSessionLocal.getRoomByName("KRC_503"));
        rb90.setBookInDate(format.parse("2019-03-29"));
        bookingSessionLocal.createRoomBooking(rb90);
        PaymentTransaction PT79 = new PaymentTransaction();
        PT79.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT79.setTransactionDateTime(format.parse("2019-03-25"));
        PT79.setFinalPayment(916.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT79);

        RoomBooking rb91 = new RoomBooking();
        rb91.setBookedRoom(roomSessionLocal.getRoomByName("KRC_608"));
        rb91.setBookInDate(format.parse("2019-03-29"));
        bookingSessionLocal.createRoomBooking(rb91);
        PaymentTransaction PT80 = new PaymentTransaction();
        PT80.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT80.setTransactionDateTime(format.parse("2019-03-25"));
        PT80.setFinalPayment(969.30);
        paymentTransactionSessionLocal.createPaymentTransaction(PT80);

        RoomBooking rb92 = new RoomBooking();
        rb92.setBookedRoom(roomSessionLocal.getRoomByName("KRC_703"));
        rb92.setBookInDate(format.parse("2019-03-30"));
        bookingSessionLocal.createRoomBooking(rb92);
        PaymentTransaction PT81 = new PaymentTransaction();
        PT81.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT81.setTransactionDateTime(format.parse("2019-03-26"));
        PT81.setFinalPayment(2123.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT81);

        RoomBooking rb93 = new RoomBooking();
        rb93.setBookedRoom(roomSessionLocal.getRoomByName("KRC_1201"));
        rb93.setBookInDate(format.parse("2019-04-08"));
        bookingSessionLocal.createRoomBooking(rb93);
        PaymentTransaction PT82 = new PaymentTransaction();
        PT82.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT82.setTransactionDateTime(format.parse("2019-04-01"));
        PT82.setFinalPayment(2181.80);
        paymentTransactionSessionLocal.createPaymentTransaction(PT82);

        RoomBooking rb94 = new RoomBooking();
        rb94.setBookedRoom(roomSessionLocal.getRoomByName("KRC_1202"));
        rb94.setBookInDate(format.parse("2019-04-06"));
        bookingSessionLocal.createRoomBooking(rb94);
        PaymentTransaction PT83 = new PaymentTransaction();
        PT83.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT83.setTransactionDateTime(format.parse("2019-04-01"));
        PT83.setFinalPayment(2561.90);
        paymentTransactionSessionLocal.createPaymentTransaction(PT83);

        RoomBooking rb95 = new RoomBooking();
        rb95.setBookedRoom(roomSessionLocal.getRoomByName("KRC_1204"));
        rb95.setBookInDate(format.parse("2019-04-07"));
        bookingSessionLocal.createRoomBooking(rb95);
        PaymentTransaction PT84 = new PaymentTransaction();
        PT84.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT84.setTransactionDateTime(format.parse("2019-04-03"));
        PT84.setFinalPayment(990.90);
        paymentTransactionSessionLocal.createPaymentTransaction(PT84);

        RoomBooking rb96 = new RoomBooking();
        rb96.setBookedRoom(roomSessionLocal.getRoomByName("KRC_602"));
        rb96.setBookInDate(format.parse("2019-04-09"));
        bookingSessionLocal.createRoomBooking(rb96);
        PaymentTransaction PT85 = new PaymentTransaction();
        PT85.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT85.setTransactionDateTime(format.parse("2019-04-05"));
        PT85.setFinalPayment(842.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT85);

        RoomBooking rb97 = new RoomBooking();
        rb97.setBookedRoom(roomSessionLocal.getRoomByName("KRC_603"));
        rb97.setBookInDate(format.parse("2019-04-11"));
        bookingSessionLocal.createRoomBooking(rb97);
        PaymentTransaction PT86 = new PaymentTransaction();
        PT86.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT86.setTransactionDateTime(format.parse("2019-04-06"));
        PT86.setFinalPayment(815.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT86);

        RoomBooking rb98 = new RoomBooking();
        rb98.setBookedRoom(roomSessionLocal.getRoomByName("KRC_702"));
        rb98.setBookInDate(format.parse("2019-04-13"));
        bookingSessionLocal.createRoomBooking(rb98);
        PaymentTransaction PT87 = new PaymentTransaction();
        PT87.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT87.setTransactionDateTime(format.parse("2019-04-08"));
        PT87.setFinalPayment(877.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT87);

        RoomBooking rb99 = new RoomBooking();
        rb99.setBookedRoom(roomSessionLocal.getRoomByName("KRC_703"));
        rb99.setBookInDate(format.parse("2019-04-15"));
        bookingSessionLocal.createRoomBooking(rb99);
        PaymentTransaction PT88 = new PaymentTransaction();
        PT88.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT88.setTransactionDateTime(format.parse("2019-04-09"));
        PT88.setFinalPayment(988.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT88);

        RoomBooking rb100 = new RoomBooking();
        rb100.setBookedRoom(roomSessionLocal.getRoomByName("KRC_704"));
        rb100.setBookInDate(format.parse("2019-04-15"));
        bookingSessionLocal.createRoomBooking(rb100);
        PaymentTransaction PT89 = new PaymentTransaction();
        PT89.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT89.setTransactionDateTime(format.parse("2019-04-10"));
        PT89.setFinalPayment(1044.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT89);

        RoomBooking rb101 = new RoomBooking();
        rb101.setBookedRoom(roomSessionLocal.getRoomByName("KRC_802"));
        rb101.setBookInDate(format.parse("2019-04-16"));
        bookingSessionLocal.createRoomBooking(rb101);
        PaymentTransaction PT90 = new PaymentTransaction();
        PT90.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT90.setTransactionDateTime(format.parse("2019-04-11"));
        PT90.setFinalPayment(1122.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT90);

        RoomBooking rb102 = new RoomBooking();
        rb102.setBookedRoom(roomSessionLocal.getRoomByName("KRC_706"));
        rb102.setBookInDate(format.parse("2019-04-19"));
        bookingSessionLocal.createRoomBooking(rb102);
        PaymentTransaction PT91 = new PaymentTransaction();
        PT91.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT91.setTransactionDateTime(format.parse("2019-04-14"));
        PT91.setFinalPayment(677.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT91);

        RoomBooking rb103 = new RoomBooking();
        rb103.setBookedRoom(roomSessionLocal.getRoomByName("KRN_602"));
        rb103.setBookInDate(format.parse("2019-01-15"));
        bookingSessionLocal.createRoomBooking(rb103);
        PaymentTransaction PT92 = new PaymentTransaction();
        PT92.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT92.setTransactionDateTime(format.parse("2019-01-07"));
        PT92.setFinalPayment(774.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT92);

        RoomBooking rb104 = new RoomBooking();
        rb104.setBookedRoom(roomSessionLocal.getRoomByName("KRN_802"));
        rb104.setBookInDate(format.parse("2019-01-14"));
        bookingSessionLocal.createRoomBooking(rb104);
        PaymentTransaction PT93 = new PaymentTransaction();
        PT93.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT93.setTransactionDateTime(format.parse("2019-01-08"));
        PT93.setFinalPayment(683.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT93);

        RoomBooking rb105 = new RoomBooking();
        rb105.setBookedRoom(roomSessionLocal.getRoomByName("KRN_703"));
        rb105.setBookInDate(format.parse("2019-01-15"));
        bookingSessionLocal.createRoomBooking(rb105);
        PaymentTransaction PT94 = new PaymentTransaction();
        PT94.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT94.setTransactionDateTime(format.parse("2019-01-09"));
        PT94.setFinalPayment(1636.50);
        paymentTransactionSessionLocal.createPaymentTransaction(PT94);

        RoomBooking rb106 = new RoomBooking();
        rb106.setBookedRoom(roomSessionLocal.getRoomByName("KRN_804"));
        rb106.setBookInDate(format.parse("2019-01-14"));
        bookingSessionLocal.createRoomBooking(rb106);
        PaymentTransaction PT95 = new PaymentTransaction();
        PT95.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT95.setTransactionDateTime(format.parse("2019-01-10"));
        PT95.setFinalPayment(2522.30);
        paymentTransactionSessionLocal.createPaymentTransaction(PT95);

        RoomBooking rb107 = new RoomBooking();
        rb107.setBookedRoom(roomSessionLocal.getRoomByName("KRN_205"));
        rb107.setBookInDate(format.parse("2019-01-15"));
        bookingSessionLocal.createRoomBooking(rb107);
        PaymentTransaction PT96 = new PaymentTransaction();
        PT96.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT96.setTransactionDateTime(format.parse("2019-01-11"));
        PT96.setFinalPayment(831.70);
        paymentTransactionSessionLocal.createPaymentTransaction(PT96);

        RoomBooking rb108 = new RoomBooking();
        rb108.setBookedRoom(roomSessionLocal.getRoomByName("KRN_206"));
        rb108.setBookInDate(format.parse("2019-01-18"));
        bookingSessionLocal.createRoomBooking(rb108);
        PaymentTransaction PT97 = new PaymentTransaction();
        PT97.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT97.setTransactionDateTime(format.parse("2019-01-13"));
        PT97.setFinalPayment(984.40);
        paymentTransactionSessionLocal.createPaymentTransaction(PT97);

        RoomBooking rb109 = new RoomBooking();
        rb109.setBookedRoom(roomSessionLocal.getRoomByName("KRN_207"));
        rb109.setBookInDate(format.parse("2019-01-20"));
        bookingSessionLocal.createRoomBooking(rb109);
        PaymentTransaction PT98 = new PaymentTransaction();
        PT98.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT98.setTransactionDateTime(format.parse("2019-01-15"));
        PT98.setFinalPayment(907.60);
        paymentTransactionSessionLocal.createPaymentTransaction(PT98);

        RoomBooking rb110 = new RoomBooking();
        rb110.setBookedRoom(roomSessionLocal.getRoomByName("KRN_208"));
        rb110.setBookInDate(format.parse("2019-01-22"));
        bookingSessionLocal.createRoomBooking(rb110);
        PaymentTransaction PT99 = new PaymentTransaction();
        PT99.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT99.setTransactionDateTime(format.parse("2019-01-18"));
        PT99.setFinalPayment(784.30);
        paymentTransactionSessionLocal.createPaymentTransaction(PT99);

        RoomBooking rb111 = new RoomBooking();
        rb111.setBookedRoom(roomSessionLocal.getRoomByName("KRN_302"));
        rb111.setBookInDate(format.parse("2019-01-24"));
        bookingSessionLocal.createRoomBooking(rb111);
        PaymentTransaction PT100 = new PaymentTransaction();
        PT100.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT100.setTransactionDateTime(format.parse("2019-01-19"));
        PT100.setFinalPayment(875.40);
        paymentTransactionSessionLocal.createPaymentTransaction(PT100);

        RoomBooking rb112 = new RoomBooking();
        rb112.setBookedRoom(roomSessionLocal.getRoomByName("KRN_303"));
        rb112.setBookInDate(format.parse("2019-01-29"));
        bookingSessionLocal.createRoomBooking(rb112);
        PaymentTransaction PT101 = new PaymentTransaction();
        PT101.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT101.setTransactionDateTime(format.parse("2019-01-23"));
        PT101.setFinalPayment(923.50);
        paymentTransactionSessionLocal.createPaymentTransaction(PT101);

        RoomBooking rb113 = new RoomBooking();
        rb113.setBookedRoom(roomSessionLocal.getRoomByName("KRN_304"));
        rb113.setBookInDate(format.parse("2019-02-05"));
        bookingSessionLocal.createRoomBooking(rb113);
        PaymentTransaction PT102 = new PaymentTransaction();
        PT102.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT102.setTransactionDateTime(format.parse("2019-02-01"));
        PT102.setFinalPayment(1098.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT102);

        RoomBooking rb114 = new RoomBooking();
        rb114.setBookedRoom(roomSessionLocal.getRoomByName("KRN_305"));
        rb114.setBookInDate(format.parse("2019-02-08"));
        bookingSessionLocal.createRoomBooking(rb114);
        PaymentTransaction PT103 = new PaymentTransaction();
        PT103.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT103.setTransactionDateTime(format.parse("2019-02-03"));
        PT103.setFinalPayment(3983.80);
        paymentTransactionSessionLocal.createPaymentTransaction(PT103);

        RoomBooking rb115 = new RoomBooking();
        rb115.setBookedRoom(roomSessionLocal.getRoomByName("KRN_306"));
        rb115.setBookInDate(format.parse("2019-02-09"));
        bookingSessionLocal.createRoomBooking(rb115);
        PaymentTransaction PT104 = new PaymentTransaction();
        PT104.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT104.setTransactionDateTime(format.parse("2019-02-04"));
        PT104.setFinalPayment(976.60);
        paymentTransactionSessionLocal.createPaymentTransaction(PT104);

        RoomBooking rb116 = new RoomBooking();
        rb116.setBookedRoom(roomSessionLocal.getRoomByName("KRN_402"));
        rb116.setBookInDate(format.parse("2019-02-11"));
        bookingSessionLocal.createRoomBooking(rb116);
        PaymentTransaction PT105 = new PaymentTransaction();
        PT105.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT105.setTransactionDateTime(format.parse("2019-02-06"));
        PT105.setFinalPayment(1512.06);
        paymentTransactionSessionLocal.createPaymentTransaction(PT105);

        RoomBooking rb117 = new RoomBooking();
        rb117.setBookedRoom(roomSessionLocal.getRoomByName("KRN_403"));
        rb117.setBookInDate(format.parse("2019-02-11"));
        bookingSessionLocal.createRoomBooking(rb117);
        PaymentTransaction PT106 = new PaymentTransaction();
        PT106.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT106.setTransactionDateTime(format.parse("2019-02-08"));
        PT106.setFinalPayment(1024.70);
        paymentTransactionSessionLocal.createPaymentTransaction(PT106);

        RoomBooking rb118 = new RoomBooking();
        rb118.setBookedRoom(roomSessionLocal.getRoomByName("KRN_404"));
        rb118.setBookInDate(format.parse("2019-02-13"));
        bookingSessionLocal.createRoomBooking(rb118);
        PaymentTransaction PT107 = new PaymentTransaction();
        PT107.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT107.setTransactionDateTime(format.parse("2019-02-10"));
        PT107.setFinalPayment(809.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT107);

        RoomBooking rb119 = new RoomBooking();
        rb119.setBookedRoom(roomSessionLocal.getRoomByName("KRN_405"));
        rb119.setBookInDate(format.parse("2019-02-18"));
        bookingSessionLocal.createRoomBooking(rb119);
        PaymentTransaction PT108 = new PaymentTransaction();
        PT108.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT108.setTransactionDateTime(format.parse("2019-02-11"));
        PT108.setFinalPayment(690.70);
        paymentTransactionSessionLocal.createPaymentTransaction(PT108);

        RoomBooking rb120 = new RoomBooking();
        rb120.setBookedRoom(roomSessionLocal.getRoomByName("KRN_502"));
        rb120.setBookInDate(format.parse("2019-02-18"));
        bookingSessionLocal.createRoomBooking(rb120);
        PaymentTransaction PT109 = new PaymentTransaction();
        PT109.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT109.setTransactionDateTime(format.parse("2019-02-14"));
        PT109.setFinalPayment(612.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT109);

        RoomBooking rb121 = new RoomBooking();
        rb121.setBookedRoom(roomSessionLocal.getRoomByName("KRN_503"));
        rb121.setBookInDate(format.parse("2019-02-20"));
        bookingSessionLocal.createRoomBooking(rb121);
        PaymentTransaction PT110 = new PaymentTransaction();
        PT110.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT110.setTransactionDateTime(format.parse("2019-02-17"));
        PT110.setFinalPayment(457.60);
        paymentTransactionSessionLocal.createPaymentTransaction(PT110);

        RoomBooking rb122 = new RoomBooking();
        rb122.setBookedRoom(roomSessionLocal.getRoomByName("KRN_504"));
        rb122.setBookInDate(format.parse("2019-02-24"));
        bookingSessionLocal.createRoomBooking(rb122);
        PaymentTransaction PT111 = new PaymentTransaction();
        PT111.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT111.setTransactionDateTime(format.parse("2019-02-18"));
        PT111.setFinalPayment(602.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT111);

        RoomBooking rb123 = new RoomBooking();
        rb123.setBookedRoom(roomSessionLocal.getRoomByName("KRN_505"));
        rb123.setBookInDate(format.parse("2019-03-08"));
        bookingSessionLocal.createRoomBooking(rb123);
        PaymentTransaction PT112 = new PaymentTransaction();
        PT112.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT112.setTransactionDateTime(format.parse("2019-03-03"));
        PT112.setFinalPayment(982.30);
        paymentTransactionSessionLocal.createPaymentTransaction(PT112);

        RoomBooking rb124 = new RoomBooking();
        rb124.setBookedRoom(roomSessionLocal.getRoomByName("KRN_506"));
        rb124.setBookInDate(format.parse("2019-03-10"));
        bookingSessionLocal.createRoomBooking(rb124);
        PaymentTransaction PT113 = new PaymentTransaction();
        PT113.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT113.setTransactionDateTime(format.parse("2019-03-06"));
        PT113.setFinalPayment(868.70);
        paymentTransactionSessionLocal.createPaymentTransaction(PT113);

        RoomBooking rb125 = new RoomBooking();
        rb125.setBookedRoom(roomSessionLocal.getRoomByName("KRN_507"));
        rb125.setBookInDate(format.parse("2019-03-14"));
        bookingSessionLocal.createRoomBooking(rb125);
        PaymentTransaction PT114 = new PaymentTransaction();
        PT114.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT114.setTransactionDateTime(format.parse("2019-03-09"));
        PT114.setFinalPayment(2081.80);
        paymentTransactionSessionLocal.createPaymentTransaction(PT114);

        RoomBooking rb126 = new RoomBooking();
        rb126.setBookedRoom(roomSessionLocal.getRoomByName("KRN_508"));
        rb126.setBookInDate(format.parse("2019-03-15"));
        bookingSessionLocal.createRoomBooking(rb126);
        PaymentTransaction PT115 = new PaymentTransaction();
        PT115.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT115.setTransactionDateTime(format.parse("2019-03-11"));
        PT115.setFinalPayment(1272.90);
        paymentTransactionSessionLocal.createPaymentTransaction(PT115);

        RoomBooking rb127 = new RoomBooking();
        rb127.setBookedRoom(roomSessionLocal.getRoomByName("KRN_602"));
        rb127.setBookInDate(format.parse("2019-03-20"));
        bookingSessionLocal.createRoomBooking(rb127);
        PaymentTransaction PT116 = new PaymentTransaction();
        PT116.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT116.setTransactionDateTime(format.parse("2019-03-15"));
        PT116.setFinalPayment(612.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT116);

        RoomBooking rb128 = new RoomBooking();
        rb128.setBookedRoom(roomSessionLocal.getRoomByName("KRN_603"));
        rb128.setBookInDate(format.parse("2019-03-24"));
        bookingSessionLocal.createRoomBooking(rb128);
        PaymentTransaction PT117 = new PaymentTransaction();
        PT117.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT117.setTransactionDateTime(format.parse("2019-03-18"));
        PT117.setFinalPayment(889.20);
        paymentTransactionSessionLocal.createPaymentTransaction(PT117);

        RoomBooking rb129 = new RoomBooking();
        rb129.setBookedRoom(roomSessionLocal.getRoomByName("KRN_604"));
        rb129.setBookInDate(format.parse("2019-03-29"));
        bookingSessionLocal.createRoomBooking(rb129);
        PaymentTransaction PT118 = new PaymentTransaction();
        PT118.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT118.setTransactionDateTime(format.parse("2019-03-25"));
        PT118.setFinalPayment(542.80);
        paymentTransactionSessionLocal.createPaymentTransaction(PT118);

        RoomBooking rb130 = new RoomBooking();
        rb130.setBookedRoom(roomSessionLocal.getRoomByName("KRN_605"));
        rb130.setBookInDate(format.parse("2019-04-03"));
        bookingSessionLocal.createRoomBooking(rb130);
        PaymentTransaction PT119 = new PaymentTransaction();
        PT119.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT119.setTransactionDateTime(format.parse("2019-03-26"));
        PT119.setFinalPayment(982.80);
        paymentTransactionSessionLocal.createPaymentTransaction(PT119);

        RoomBooking rb131 = new RoomBooking();
        rb131.setBookedRoom(roomSessionLocal.getRoomByName("KRN_703"));
        rb131.setBookInDate(format.parse("2019-04-03"));
        bookingSessionLocal.createRoomBooking(rb131);
        PaymentTransaction PT120 = new PaymentTransaction();
        PT120.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT120.setTransactionDateTime(format.parse("2019-03-27"));
        PT120.setFinalPayment(632.90);
        paymentTransactionSessionLocal.createPaymentTransaction(PT120);

        RoomBooking rb132 = new RoomBooking();
        rb132.setBookedRoom(roomSessionLocal.getRoomByName("KRN_704"));
        rb132.setBookInDate(format.parse("2019-04-05"));
        bookingSessionLocal.createRoomBooking(rb132);
        PaymentTransaction PT121 = new PaymentTransaction();
        PT121.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT121.setTransactionDateTime(format.parse("2019-03-28"));
        PT121.setFinalPayment(843.90);
        paymentTransactionSessionLocal.createPaymentTransaction(PT121);

        RoomBooking rb133 = new RoomBooking();
        rb133.setBookedRoom(roomSessionLocal.getRoomByName("KRN_705"));
        rb133.setBookInDate(format.parse("2019-04-08"));
        bookingSessionLocal.createRoomBooking(rb133);
        PaymentTransaction PT122 = new PaymentTransaction();
        PT122.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT122.setTransactionDateTime(format.parse("2019-04-02"));
        PT122.setFinalPayment(629.90);
        paymentTransactionSessionLocal.createPaymentTransaction(PT122);

        RoomBooking rb134 = new RoomBooking();
        rb134.setBookedRoom(roomSessionLocal.getRoomByName("KRN_706"));
        rb134.setBookInDate(format.parse("2019-04-09"));
        bookingSessionLocal.createRoomBooking(rb134);
        PaymentTransaction PT123 = new PaymentTransaction();
        PT123.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT123.setTransactionDateTime(format.parse("2019-04-04"));
        PT123.setFinalPayment(2008.80);
        paymentTransactionSessionLocal.createPaymentTransaction(PT123);

        RoomBooking rb135 = new RoomBooking();
        rb135.setBookedRoom(roomSessionLocal.getRoomByName("KRN_707"));
        rb135.setBookInDate(format.parse("2019-04-10"));
        bookingSessionLocal.createRoomBooking(rb135);
        PaymentTransaction PT124 = new PaymentTransaction();
        PT124.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT124.setTransactionDateTime(format.parse("2019-04-06"));
        PT124.setFinalPayment(1937.20);
        paymentTransactionSessionLocal.createPaymentTransaction(PT124);

        RoomBooking rb136 = new RoomBooking();
        rb136.setBookedRoom(roomSessionLocal.getRoomByName("KRN_801"));
        rb136.setBookInDate(format.parse("2019-04-13"));
        bookingSessionLocal.createRoomBooking(rb136);
        PaymentTransaction PT125 = new PaymentTransaction();
        PT125.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT125.setTransactionDateTime(format.parse("2019-04-08"));
        PT125.setFinalPayment(999.90);
        paymentTransactionSessionLocal.createPaymentTransaction(PT125);

        RoomBooking rb137 = new RoomBooking();
        rb137.setBookedRoom(roomSessionLocal.getRoomByName("KRN_1202"));
        rb137.setBookInDate(format.parse("2019-04-18"));
        bookingSessionLocal.createRoomBooking(rb137);
        PaymentTransaction PT126 = new PaymentTransaction();
        PT126.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT126.setTransactionDateTime(format.parse("2019-04-09"));
        PT126.setFinalPayment(2517.70);
        paymentTransactionSessionLocal.createPaymentTransaction(PT126);

        RoomBooking rb138 = new RoomBooking();
        rb138.setBookedRoom(roomSessionLocal.getRoomByName("KRN_1203"));
        rb138.setBookInDate(format.parse("2019-04-16"));
        bookingSessionLocal.createRoomBooking(rb138);
        PaymentTransaction PT127 = new PaymentTransaction();
        PT127.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT127.setTransactionDateTime(format.parse("2019-04-11"));
        PT127.setFinalPayment(1439.80);
        paymentTransactionSessionLocal.createPaymentTransaction(PT127);

        RoomBooking rb139 = new RoomBooking();
        rb139.setBookedRoom(roomSessionLocal.getRoomByName("KRN_1204"));
        rb139.setBookInDate(format.parse("2019-04-19"));
        bookingSessionLocal.createRoomBooking(rb139);
        PaymentTransaction PT128 = new PaymentTransaction();
        PT128.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT128.setTransactionDateTime(format.parse("2019-04-13"));
        PT128.setFinalPayment(590.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT128);

        RoomBooking rb140 = new RoomBooking();
        rb140.setBookedRoom(roomSessionLocal.getRoomByName("KRN_901"));
        rb140.setBookInDate(format.parse("2019-04-20"));
        bookingSessionLocal.createRoomBooking(rb140);
        PaymentTransaction PT129 = new PaymentTransaction();
        PT129.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT129.setTransactionDateTime(format.parse("2019-04-15"));
        PT129.setFinalPayment(899.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT129);

        RoomBooking rb141 = new RoomBooking();
        rb141.setBookedRoom(roomSessionLocal.getRoomByName("KRN_902"));
        rb141.setBookInDate(format.parse("2019-04-20"));
        bookingSessionLocal.createRoomBooking(rb141);
        PaymentTransaction PT130 = new PaymentTransaction();
        PT130.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT130.setTransactionDateTime(format.parse("2019-04-17"));
        PT130.setFinalPayment(777.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT130);

        RoomBooking rb142 = new RoomBooking();
        rb142.setBookedRoom(roomSessionLocal.getRoomByName("KRN_903"));
        rb142.setBookInDate(format.parse("2019-01-05"));
        bookingSessionLocal.createRoomBooking(rb142);
        PaymentTransaction PT131 = new PaymentTransaction();
        PT131.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT131.setTransactionDateTime(format.parse("2019-01-01"));
        PT131.setFinalPayment(678.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT131);

        RoomBooking rb143 = new RoomBooking();
        rb143.setBookedRoom(roomSessionLocal.getRoomByName("KRE_602"));
        rb143.setBookInDate(format.parse("2019-01-06"));
        bookingSessionLocal.createRoomBooking(rb143);
        PaymentTransaction PT132 = new PaymentTransaction();
        PT132.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT132.setTransactionDateTime(format.parse("2019-01-02"));
        PT132.setFinalPayment(969.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT132);

        RoomBooking rb144 = new RoomBooking();
        rb144.setBookedRoom(roomSessionLocal.getRoomByName("KRE_605"));
        rb144.setBookInDate(format.parse("2019-01-10"));
        bookingSessionLocal.createRoomBooking(rb144);
        PaymentTransaction PT133 = new PaymentTransaction();
        PT133.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT133.setTransactionDateTime(format.parse("2019-01-06"));
        PT133.setFinalPayment(599.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT133);

        RoomBooking rb145 = new RoomBooking();
        rb145.setBookedRoom(roomSessionLocal.getRoomByName("KRE_301"));
        rb145.setBookInDate(format.parse("2019-01-18"));
        bookingSessionLocal.createRoomBooking(rb145);
        PaymentTransaction PT134 = new PaymentTransaction();
        PT134.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT134.setTransactionDateTime(format.parse("2019-01-13"));
        PT134.setFinalPayment(958.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT134);

        RoomBooking rb146 = new RoomBooking();
        rb146.setBookedRoom(roomSessionLocal.getRoomByName("KRE_401"));
        rb146.setBookInDate(format.parse("2019-01-20"));
        bookingSessionLocal.createRoomBooking(rb146);
        PaymentTransaction PT135 = new PaymentTransaction();
        PT135.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT135.setTransactionDateTime(format.parse("2019-01-15"));
        PT135.setFinalPayment(1921.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT135);

        RoomBooking rb147 = new RoomBooking();
        rb147.setBookedRoom(roomSessionLocal.getRoomByName("KRE_507"));
        rb147.setBookInDate(format.parse("2019-01-22"));
        bookingSessionLocal.createRoomBooking(rb147);
        PaymentTransaction PT136 = new PaymentTransaction();
        PT136.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT136.setTransactionDateTime(format.parse("2019-01-18"));
        PT136.setFinalPayment(1024.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT136);

        RoomBooking rb148 = new RoomBooking();
        rb148.setBookedRoom(roomSessionLocal.getRoomByName("KRE_503"));
        rb148.setBookInDate(format.parse("2019-01-24"));
        bookingSessionLocal.createRoomBooking(rb148);
        PaymentTransaction PT137 = new PaymentTransaction();
        PT137.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT137.setTransactionDateTime(format.parse("2019-01-19"));
        PT137.setFinalPayment(2109.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT137);

        RoomBooking rb149 = new RoomBooking();
        rb149.setBookedRoom(roomSessionLocal.getRoomByName("KRE_504"));
        rb149.setBookInDate(format.parse("2019-01-28"));
        bookingSessionLocal.createRoomBooking(rb149);
        PaymentTransaction PT138 = new PaymentTransaction();
        PT138.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT138.setTransactionDateTime(format.parse("2019-01-21"));
        PT138.setFinalPayment(986.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT138);

        RoomBooking rb150 = new RoomBooking();
        rb150.setBookedRoom(roomSessionLocal.getRoomByName("KRE_505"));
        rb150.setBookInDate(format.parse("2019-01-29"));
        bookingSessionLocal.createRoomBooking(rb150);
        PaymentTransaction PT139 = new PaymentTransaction();
        PT139.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT139.setTransactionDateTime(format.parse("2019-01-23"));
        PT139.setFinalPayment(875.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT139);

        RoomBooking rb151 = new RoomBooking();
        rb151.setBookedRoom(roomSessionLocal.getRoomByName("KRE_601"));
        rb151.setBookInDate(format.parse("2019-01-29"));
        bookingSessionLocal.createRoomBooking(rb151);
        PaymentTransaction PT140 = new PaymentTransaction();
        PT140.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT140.setTransactionDateTime(format.parse("2019-01-25"));
        PT140.setFinalPayment(1212.50);
        paymentTransactionSessionLocal.createPaymentTransaction(PT140);

        RoomBooking rb152 = new RoomBooking();
        rb152.setBookedRoom(roomSessionLocal.getRoomByName("KRE_606"));
        rb152.setBookInDate(format.parse("2019-02-07"));
        bookingSessionLocal.createRoomBooking(rb152);
        PaymentTransaction PT141 = new PaymentTransaction();
        PT141.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT141.setTransactionDateTime(format.parse("2019-02-01"));
        PT141.setFinalPayment(879.40);
        paymentTransactionSessionLocal.createPaymentTransaction(PT141);

        RoomBooking rb153 = new RoomBooking();
        rb153.setBookedRoom(roomSessionLocal.getRoomByName("KRE_607"));
        rb153.setBookInDate(format.parse("2019-02-08"));
        bookingSessionLocal.createRoomBooking(rb153);
        PaymentTransaction PT142 = new PaymentTransaction();
        PT142.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT142.setTransactionDateTime(format.parse("2019-02-03"));
        PT142.setFinalPayment(878.50);
        paymentTransactionSessionLocal.createPaymentTransaction(PT142);

        RoomBooking rb154 = new RoomBooking();
        rb154.setBookedRoom(roomSessionLocal.getRoomByName("KRE_604"));
        rb154.setBookInDate(format.parse("2019-02-09"));
        bookingSessionLocal.createRoomBooking(rb154);
        PaymentTransaction PT143 = new PaymentTransaction();
        PT143.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT143.setTransactionDateTime(format.parse("2019-02-04"));
        PT143.setFinalPayment(875.30);
        paymentTransactionSessionLocal.createPaymentTransaction(PT143);

        RoomBooking rb155 = new RoomBooking();
        rb155.setBookedRoom(roomSessionLocal.getRoomByName("KRE_602"));
        rb155.setBookInDate(format.parse("2019-02-11"));
        bookingSessionLocal.createRoomBooking(rb155);
        PaymentTransaction PT144 = new PaymentTransaction();
        PT144.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT144.setTransactionDateTime(format.parse("2019-02-08"));
        PT144.setFinalPayment(767.40);
        paymentTransactionSessionLocal.createPaymentTransaction(PT144);

        RoomBooking rb156 = new RoomBooking();
        rb156.setBookedRoom(roomSessionLocal.getRoomByName("KRE_603"));
        rb156.setBookInDate(format.parse("2019-02-18"));
        bookingSessionLocal.createRoomBooking(rb156);
        PaymentTransaction PT145 = new PaymentTransaction();
        PT145.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT145.setTransactionDateTime(format.parse("2019-02-14"));
        PT145.setFinalPayment(768.90);
        paymentTransactionSessionLocal.createPaymentTransaction(PT145);

        RoomBooking rb157 = new RoomBooking();
        rb157.setBookedRoom(roomSessionLocal.getRoomByName("KRE_504"));
        rb157.setBookInDate(format.parse("2019-02-23"));
        bookingSessionLocal.createRoomBooking(rb157);
        PaymentTransaction PT146 = new PaymentTransaction();
        PT146.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT146.setTransactionDateTime(format.parse("2019-02-17"));
        PT146.setFinalPayment(986.50);
        paymentTransactionSessionLocal.createPaymentTransaction(PT146);

        RoomBooking rb158 = new RoomBooking();
        rb158.setBookedRoom(roomSessionLocal.getRoomByName("KRE_405"));
        rb158.setBookInDate(format.parse("2019-02-23"));
        bookingSessionLocal.createRoomBooking(rb158);
        PaymentTransaction PT147 = new PaymentTransaction();
        PT147.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT147.setTransactionDateTime(format.parse("2019-02-18"));
        PT147.setFinalPayment(872.05);
        paymentTransactionSessionLocal.createPaymentTransaction(PT147);

        RoomBooking rb159 = new RoomBooking();
        rb159.setBookedRoom(roomSessionLocal.getRoomByName("KRE_306"));
        rb159.setBookInDate(format.parse("2019-02-27"));
        bookingSessionLocal.createRoomBooking(rb159);
        PaymentTransaction PT148 = new PaymentTransaction();
        PT148.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT148.setTransactionDateTime(format.parse("2019-02-21"));
        PT148.setFinalPayment(687.50);
        paymentTransactionSessionLocal.createPaymentTransaction(PT148);

        RoomBooking rb160 = new RoomBooking();
        rb160.setBookedRoom(roomSessionLocal.getRoomByName("KRE_407"));
        rb160.setBookInDate(format.parse("2019-03-02"));
        bookingSessionLocal.createRoomBooking(rb160);
        PaymentTransaction PT149 = new PaymentTransaction();
        PT149.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT149.setTransactionDateTime(format.parse("2019-02-24"));
        PT149.setFinalPayment(1234.50);
        paymentTransactionSessionLocal.createPaymentTransaction(PT149);

        RoomBooking rb161 = new RoomBooking();
        rb161.setBookedRoom(roomSessionLocal.getRoomByName("KRE_206"));
        rb161.setBookInDate(format.parse("2019-03-01"));
        bookingSessionLocal.createRoomBooking(rb161);
        PaymentTransaction PT150 = new PaymentTransaction();
        PT150.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT150.setTransactionDateTime(format.parse("2019-02-24"));
        PT150.setFinalPayment(1266.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT150);

        RoomBooking rb162 = new RoomBooking();
        rb162.setBookedRoom(roomSessionLocal.getRoomByName("KRE_305"));
        rb162.setBookInDate(format.parse("2019-03-07"));
        bookingSessionLocal.createRoomBooking(rb162);
        PaymentTransaction PT151 = new PaymentTransaction();
        PT151.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT151.setTransactionDateTime(format.parse("2019-03-04"));
        PT151.setFinalPayment(652.50);
        paymentTransactionSessionLocal.createPaymentTransaction(PT151);

        RoomBooking rb163 = new RoomBooking();
        rb163.setBookedRoom(roomSessionLocal.getRoomByName("KRE_403"));
        rb163.setBookInDate(format.parse("2019-03-12"));
        bookingSessionLocal.createRoomBooking(rb163);
        PaymentTransaction PT152 = new PaymentTransaction();
        PT152.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT152.setTransactionDateTime(format.parse("2019-03-09"));
        PT152.setFinalPayment(886.70);
        paymentTransactionSessionLocal.createPaymentTransaction(PT152);

        RoomBooking rb164 = new RoomBooking();
        rb164.setBookedRoom(roomSessionLocal.getRoomByName("KRE_402"));
        rb164.setBookInDate(format.parse("2019-03-15"));
        bookingSessionLocal.createRoomBooking(rb164);
        PaymentTransaction PT153 = new PaymentTransaction();
        PT153.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT153.setTransactionDateTime(format.parse("2019-03-10"));
        PT153.setFinalPayment(762.40);
        paymentTransactionSessionLocal.createPaymentTransaction(PT153);

        RoomBooking rb165 = new RoomBooking();
        rb165.setBookedRoom(roomSessionLocal.getRoomByName("KRE_401"));
        rb165.setBookInDate(format.parse("2019-03-18"));
        bookingSessionLocal.createRoomBooking(rb165);
        PaymentTransaction PT154 = new PaymentTransaction();
        PT154.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT154.setTransactionDateTime(format.parse("2019-03-12"));
        PT154.setFinalPayment(1563.20);
        paymentTransactionSessionLocal.createPaymentTransaction(PT154);

        RoomBooking rb166 = new RoomBooking();
        rb166.setBookedRoom(roomSessionLocal.getRoomByName("KRE_308"));
        rb166.setBookInDate(format.parse("2019-03-22"));
        bookingSessionLocal.createRoomBooking(rb166);
        PaymentTransaction PT155 = new PaymentTransaction();
        PT155.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT155.setTransactionDateTime(format.parse("2019-03-14"));
        PT155.setFinalPayment(902.60);
        paymentTransactionSessionLocal.createPaymentTransaction(PT155);

        RoomBooking rb167 = new RoomBooking();
        rb167.setBookedRoom(roomSessionLocal.getRoomByName("KRE_307"));
        rb167.setBookInDate(format.parse("2019-03-23"));
        bookingSessionLocal.createRoomBooking(rb167);
        PaymentTransaction PT156 = new PaymentTransaction();
        PT156.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT156.setTransactionDateTime(format.parse("2019-03-17"));
        PT156.setFinalPayment(852.50);
        paymentTransactionSessionLocal.createPaymentTransaction(PT156);

        RoomBooking rb168 = new RoomBooking();
        rb168.setBookedRoom(roomSessionLocal.getRoomByName("KRE_306"));
        rb168.setBookInDate(format.parse("2019-03-24"));
        bookingSessionLocal.createRoomBooking(rb168);
        PaymentTransaction PT157 = new PaymentTransaction();
        PT157.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT157.setTransactionDateTime(format.parse("2019-03-20"));
        PT157.setFinalPayment(653.60);
        paymentTransactionSessionLocal.createPaymentTransaction(PT157);

        RoomBooking rb169 = new RoomBooking();
        rb169.setBookedRoom(roomSessionLocal.getRoomByName("KRE_305"));
        rb169.setBookInDate(format.parse("2019-03-29"));
        bookingSessionLocal.createRoomBooking(rb169);
        PaymentTransaction PT158 = new PaymentTransaction();
        PT158.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT158.setTransactionDateTime(format.parse("2019-03-23"));
        PT158.setFinalPayment(987.60);
        paymentTransactionSessionLocal.createPaymentTransaction(PT158);

        RoomBooking rb170 = new RoomBooking();
        rb170.setBookedRoom(roomSessionLocal.getRoomByName("KRE_304"));
        rb170.setBookInDate(format.parse("2019-04-01"));
        bookingSessionLocal.createRoomBooking(rb170);
        PaymentTransaction PT159 = new PaymentTransaction();
        PT159.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT159.setTransactionDateTime(format.parse("2019-03-25"));
        PT159.setFinalPayment(778.40);
        paymentTransactionSessionLocal.createPaymentTransaction(PT159);

        RoomBooking rb171 = new RoomBooking();
        rb171.setBookedRoom(roomSessionLocal.getRoomByName("KRE_303"));
        rb171.setBookInDate(format.parse("2019-04-02"));
        bookingSessionLocal.createRoomBooking(rb171);
        PaymentTransaction PT160 = new PaymentTransaction();
        PT160.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT160.setTransactionDateTime(format.parse("2019-03-27"));
        PT160.setFinalPayment(864.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT160);

        RoomBooking rb172 = new RoomBooking();
        rb172.setBookedRoom(roomSessionLocal.getRoomByName("KRE_302"));
        rb172.setBookInDate(format.parse("2019-04-07"));
        bookingSessionLocal.createRoomBooking(rb172);
        PaymentTransaction PT161 = new PaymentTransaction();
        PT161.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT161.setTransactionDateTime(format.parse("2019-04-02"));
        PT161.setFinalPayment(764.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT161);

        RoomBooking rb173 = new RoomBooking();
        rb173.setBookedRoom(roomSessionLocal.getRoomByName("KRE_208"));
        rb173.setBookInDate(format.parse("2019-04-09"));
        bookingSessionLocal.createRoomBooking(rb173);
        PaymentTransaction PT162 = new PaymentTransaction();
        PT162.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT162.setTransactionDateTime(format.parse("2019-04-03"));
        PT162.setFinalPayment(616.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT162);

        RoomBooking rb174 = new RoomBooking();
        rb174.setBookedRoom(roomSessionLocal.getRoomByName("KRE_207"));
        rb174.setBookInDate(format.parse("2019-04-10"));
        bookingSessionLocal.createRoomBooking(rb174);
        PaymentTransaction PT163 = new PaymentTransaction();
        PT163.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT163.setTransactionDateTime(format.parse("2019-04-04"));
        PT163.setFinalPayment(612.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT163);

        RoomBooking rb175 = new RoomBooking();
        rb175.setBookedRoom(roomSessionLocal.getRoomByName("KRE_206"));
        rb175.setBookInDate(format.parse("2019-04-12"));
        bookingSessionLocal.createRoomBooking(rb175);
        PaymentTransaction PT164 = new PaymentTransaction();
        PT164.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT164.setTransactionDateTime(format.parse("2019-04-07"));
        PT164.setFinalPayment(887.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT164);

        RoomBooking rb176 = new RoomBooking();
        rb176.setBookedRoom(roomSessionLocal.getRoomByName("KRE_204"));
        rb176.setBookInDate(format.parse("2019-04-18"));
        bookingSessionLocal.createRoomBooking(rb176);
        PaymentTransaction PT165 = new PaymentTransaction();
        PT165.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT165.setTransactionDateTime(format.parse("2019-04-10"));
        PT165.setFinalPayment(776.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT165);

        RoomBooking rb177 = new RoomBooking();
        rb177.setBookedRoom(roomSessionLocal.getRoomByName("KRE_203"));
        rb177.setBookInDate(format.parse("2019-04-19"));
        bookingSessionLocal.createRoomBooking(rb177);
        PaymentTransaction PT166 = new PaymentTransaction();
        PT166.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT166.setTransactionDateTime(format.parse("2019-04-11"));
        PT166.setFinalPayment(665.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT166);

        RoomBooking rb178 = new RoomBooking();
        rb178.setBookedRoom(roomSessionLocal.getRoomByName("KRE_202"));
        rb178.setBookInDate(format.parse("2019-04-19"));
        bookingSessionLocal.createRoomBooking(rb178);
        PaymentTransaction PT167 = new PaymentTransaction();
        PT167.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT167.setTransactionDateTime(format.parse("2019-04-12"));
        PT167.setFinalPayment(870.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT167);

        RoomBooking rb179 = new RoomBooking();
        rb179.setBookedRoom(roomSessionLocal.getRoomByName("KRE_201"));
        rb179.setBookInDate(format.parse("2019-04-19"));
        bookingSessionLocal.createRoomBooking(rb179);
        PaymentTransaction PT168 = new PaymentTransaction();
        PT168.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT168.setTransactionDateTime(format.parse("2019-04-14"));
        PT168.setFinalPayment(975.70);
        paymentTransactionSessionLocal.createPaymentTransaction(PT168);

        RoomBooking rb180 = new RoomBooking();
        rb180.setBookedRoom(roomSessionLocal.getRoomByName("KRE_602"));
        rb180.setBookInDate(format.parse("2019-04-22"));
        bookingSessionLocal.createRoomBooking(rb180);
        PaymentTransaction PT169 = new PaymentTransaction();
        PT169.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT169.setTransactionDateTime(format.parse("2019-04-16"));
        PT169.setFinalPayment(987.50);
        paymentTransactionSessionLocal.createPaymentTransaction(PT169);

        RoomBooking rb181 = new RoomBooking();
        rb181.setBookedRoom(roomSessionLocal.getRoomByName("KRE_603"));
        rb181.setBookInDate(format.parse("2019-04-22"));
        bookingSessionLocal.createRoomBooking(rb181);
        PaymentTransaction PT170 = new PaymentTransaction();
        PT170.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT170.setTransactionDateTime(format.parse("2019-04-17"));
        PT170.setFinalPayment(785.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT170);

        RoomBooking rb182 = new RoomBooking();
        rb182.setBookedRoom(roomSessionLocal.getRoomByName("KRE_1204"));
        rb182.setBookInDate(format.parse("2019-04-23"));
        bookingSessionLocal.createRoomBooking(rb182);
        PaymentTransaction PT171 = new PaymentTransaction();
        PT171.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT171.setTransactionDateTime(format.parse("2019-04-19"));
        PT171.setFinalPayment(1412.50);
        paymentTransactionSessionLocal.createPaymentTransaction(PT171);

//KRS Payment Transaction
        RoomBooking rb500 = new RoomBooking();
        rb500.setBookedRoom(roomSessionLocal.getRoomByName("KRS_201"));
        rb500.setBookInDate(format.parse("2019-01-09"));
        bookingSessionLocal.createRoomBooking(rb500);
        PaymentTransaction PT500 = new PaymentTransaction();
        PT500.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT500.setTransactionDateTime(format.parse("2019-01-01"));
        PT500.setFinalPayment(481.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT500);

        RoomBooking rb501 = new RoomBooking();
        rb501.setBookedRoom(roomSessionLocal.getRoomByName("KRS_202"));
        rb501.setBookInDate(format.parse("2019-01-09"));
        bookingSessionLocal.createRoomBooking(rb501);
        PaymentTransaction PT501 = new PaymentTransaction();
        PT501.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT501.setTransactionDateTime(format.parse("2019-01-02"));
        PT501.setFinalPayment(381.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT501);

        RoomBooking rb502 = new RoomBooking();
        rb502.setBookedRoom(roomSessionLocal.getRoomByName("KRS_203"));
        rb502.setBookInDate(format.parse("2019-01-13"));
        bookingSessionLocal.createRoomBooking(rb502);
        PaymentTransaction PT502 = new PaymentTransaction();
        PT502.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT502.setTransactionDateTime(format.parse("2019-01-03"));
        PT502.setFinalPayment(838.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT502);

        RoomBooking rb503 = new RoomBooking();
        rb503.setBookedRoom(roomSessionLocal.getRoomByName("KRS_204"));
        rb503.setBookInDate(format.parse("2019-01-12"));
        bookingSessionLocal.createRoomBooking(rb503);
        PaymentTransaction PT503 = new PaymentTransaction();
        PT503.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT503.setTransactionDateTime(format.parse("2019-01-04"));
        PT503.setFinalPayment(248.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT503);

        RoomBooking rb504 = new RoomBooking();
        rb504.setBookedRoom(roomSessionLocal.getRoomByName("KRS_205"));
        rb504.setBookInDate(format.parse("2019-01-09"));
        bookingSessionLocal.createRoomBooking(rb504);
        PaymentTransaction PT504 = new PaymentTransaction();
        PT504.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT504.setTransactionDateTime(format.parse("2019-01-05"));
        PT504.setFinalPayment(471.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT504);

        RoomBooking rb505 = new RoomBooking();
        rb505.setBookedRoom(roomSessionLocal.getRoomByName("KRS_301"));
        rb505.setBookInDate(format.parse("2019-01-09"));
        bookingSessionLocal.createRoomBooking(rb505);
        PaymentTransaction PT505 = new PaymentTransaction();
        PT505.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT505.setTransactionDateTime(format.parse("2019-01-06"));
        PT505.setFinalPayment(481.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT505);

        RoomBooking rb506 = new RoomBooking();
        rb506.setBookedRoom(roomSessionLocal.getRoomByName("KRS_302"));
        rb506.setBookInDate(format.parse("2019-01-13"));
        bookingSessionLocal.createRoomBooking(rb506);
        PaymentTransaction PT506 = new PaymentTransaction();
        PT506.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT506.setTransactionDateTime(format.parse("2019-01-07"));
        PT506.setFinalPayment(518.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT506);

        RoomBooking rb507 = new RoomBooking();
        rb507.setBookedRoom(roomSessionLocal.getRoomByName("KRS_303"));
        rb507.setBookInDate(format.parse("2019-01-12"));
        bookingSessionLocal.createRoomBooking(rb507);
        PaymentTransaction PT507 = new PaymentTransaction();
        PT507.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT507.setTransactionDateTime(format.parse("2019-01-08"));
        PT507.setFinalPayment(371.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT507);

        RoomBooking rb508 = new RoomBooking();
        rb508.setBookedRoom(roomSessionLocal.getRoomByName("KRS_304"));
        rb508.setBookInDate(format.parse("2019-01-14"));
        bookingSessionLocal.createRoomBooking(rb508);
        PaymentTransaction PT508 = new PaymentTransaction();
        PT508.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT508.setTransactionDateTime(format.parse("2019-01-09"));
        PT508.setFinalPayment(371.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT508);

        RoomBooking rb509 = new RoomBooking();
        rb509.setBookedRoom(roomSessionLocal.getRoomByName("KRS_305"));
        rb509.setBookInDate(format.parse("2019-01-18"));
        bookingSessionLocal.createRoomBooking(rb509);
        PaymentTransaction PT509 = new PaymentTransaction();
        PT509.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT509.setTransactionDateTime(format.parse("2019-01-10"));
        PT509.setFinalPayment(487.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT509);

        RoomBooking rb510 = new RoomBooking();
        rb510.setBookedRoom(roomSessionLocal.getRoomByName("KRS_401"));
        rb510.setBookInDate(format.parse("2019-02-13"));
        bookingSessionLocal.createRoomBooking(rb510);
        PaymentTransaction PT510 = new PaymentTransaction();
        PT510.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT510.setTransactionDateTime(format.parse("2019-02-01"));
        PT510.setFinalPayment(162.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT510);

        RoomBooking rb511 = new RoomBooking();
        rb511.setBookedRoom(roomSessionLocal.getRoomByName("KRS_402"));
        rb511.setBookInDate(format.parse("2019-02-09"));
        bookingSessionLocal.createRoomBooking(rb511);
        PaymentTransaction PT511 = new PaymentTransaction();
        PT511.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT511.setTransactionDateTime(format.parse("2019-02-02"));
        PT511.setFinalPayment(481.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT511);

        RoomBooking rb512 = new RoomBooking();
        rb512.setBookedRoom(roomSessionLocal.getRoomByName("KRS_403"));
        rb512.setBookInDate(format.parse("2019-02-19"));
        bookingSessionLocal.createRoomBooking(rb512);
        PaymentTransaction PT512 = new PaymentTransaction();
        PT512.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT512.setTransactionDateTime(format.parse("2019-02-03"));
        PT512.setFinalPayment(471.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT512);

        RoomBooking rb513 = new RoomBooking();
        rb513.setBookedRoom(roomSessionLocal.getRoomByName("KRS_404"));
        rb513.setBookInDate(format.parse("2019-02-14"));
        bookingSessionLocal.createRoomBooking(rb513);
        PaymentTransaction PT513 = new PaymentTransaction();
        PT513.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT513.setTransactionDateTime(format.parse("2019-02-04"));
        PT513.setFinalPayment(827.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT513);

        RoomBooking rb514 = new RoomBooking();
        rb514.setBookedRoom(roomSessionLocal.getRoomByName("KRS_405"));
        rb514.setBookInDate(format.parse("2019-02-18"));
        bookingSessionLocal.createRoomBooking(rb514);
        PaymentTransaction PT514 = new PaymentTransaction();
        PT514.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT514.setTransactionDateTime(format.parse("2019-02-05"));
        PT514.setFinalPayment(184.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT514);

        RoomBooking rb515 = new RoomBooking();
        rb515.setBookedRoom(roomSessionLocal.getRoomByName("KRS_501"));
        rb515.setBookInDate(format.parse("2019-02-13"));
        bookingSessionLocal.createRoomBooking(rb515);
        PaymentTransaction PT515 = new PaymentTransaction();
        PT515.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT515.setTransactionDateTime(format.parse("2019-02-06"));
        PT515.setFinalPayment(287.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT515);

        RoomBooking rb516 = new RoomBooking();
        rb516.setBookedRoom(roomSessionLocal.getRoomByName("KRS_502"));
        rb516.setBookInDate(format.parse("2019-02-13"));
        bookingSessionLocal.createRoomBooking(rb516);
        PaymentTransaction PT516 = new PaymentTransaction();
        PT516.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT516.setTransactionDateTime(format.parse("2019-02-07"));
        PT516.setFinalPayment(746.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT516);

        RoomBooking rb517 = new RoomBooking();
        rb517.setBookedRoom(roomSessionLocal.getRoomByName("KRS_503"));
        rb517.setBookInDate(format.parse("2019-02-13"));
        bookingSessionLocal.createRoomBooking(rb517);
        PaymentTransaction PT517 = new PaymentTransaction();
        PT517.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT517.setTransactionDateTime(format.parse("2019-02-08"));
        PT517.setFinalPayment(678.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT517);

        RoomBooking rb518 = new RoomBooking();
        rb518.setBookedRoom(roomSessionLocal.getRoomByName("KRS_504"));
        rb518.setBookInDate(format.parse("2019-02-18"));
        bookingSessionLocal.createRoomBooking(rb518);
        PaymentTransaction PT518 = new PaymentTransaction();
        PT518.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT518.setTransactionDateTime(format.parse("2019-02-09"));
        PT518.setFinalPayment(256.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT518);

        RoomBooking rb519 = new RoomBooking();
        rb519.setBookedRoom(roomSessionLocal.getRoomByName("KRS_505"));
        rb519.setBookInDate(format.parse("2019-02-18"));
        bookingSessionLocal.createRoomBooking(rb519);
        PaymentTransaction PT519 = new PaymentTransaction();
        PT519.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT519.setTransactionDateTime(format.parse("2019-02-10"));
        PT519.setFinalPayment(568.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT519);

        RoomBooking rb520 = new RoomBooking();
        rb520.setBookedRoom(roomSessionLocal.getRoomByName("KRS_201"));
        rb520.setBookInDate(format.parse("2019-03-09"));
        bookingSessionLocal.createRoomBooking(rb520);
        PaymentTransaction PT520 = new PaymentTransaction();
        PT520.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT520.setTransactionDateTime(format.parse("2019-03-01"));
        PT520.setFinalPayment(256.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT520);

        RoomBooking rb521 = new RoomBooking();
        rb521.setBookedRoom(roomSessionLocal.getRoomByName("KRS_202"));
        rb521.setBookInDate(format.parse("2019-03-12"));
        bookingSessionLocal.createRoomBooking(rb521);
        PaymentTransaction PT521 = new PaymentTransaction();
        PT521.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT521.setTransactionDateTime(format.parse("2019-03-02"));
        PT521.setFinalPayment(673.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT521);

        RoomBooking rb522 = new RoomBooking();
        rb522.setBookedRoom(roomSessionLocal.getRoomByName("KRS_203"));
        rb522.setBookInDate(format.parse("2019-03-12"));
        bookingSessionLocal.createRoomBooking(rb522);
        PaymentTransaction PT522 = new PaymentTransaction();
        PT522.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT522.setTransactionDateTime(format.parse("2019-03-03"));
        PT522.setFinalPayment(725.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT522);

        RoomBooking rb523 = new RoomBooking();
        rb523.setBookedRoom(roomSessionLocal.getRoomByName("KRS_204"));
        rb523.setBookInDate(format.parse("2019-03-09"));
        bookingSessionLocal.createRoomBooking(rb523);
        PaymentTransaction PT523 = new PaymentTransaction();
        PT523.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT523.setTransactionDateTime(format.parse("2019-03-04"));
        PT523.setFinalPayment(273.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT523);

        RoomBooking rb524 = new RoomBooking();
        rb524.setBookedRoom(roomSessionLocal.getRoomByName("KRS_205"));
        rb524.setBookInDate(format.parse("2019-03-19"));
        bookingSessionLocal.createRoomBooking(rb524);
        PaymentTransaction PT524 = new PaymentTransaction();
        PT524.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT524.setTransactionDateTime(format.parse("2019-03-05"));
        PT524.setFinalPayment(723.69);
        paymentTransactionSessionLocal.createPaymentTransaction(PT524);

        RoomBooking rb525 = new RoomBooking();
        rb525.setBookedRoom(roomSessionLocal.getRoomByName("KRS_301"));
        rb525.setBookInDate(format.parse("2019-03-12"));
        bookingSessionLocal.createRoomBooking(rb525);
        PaymentTransaction PT525 = new PaymentTransaction();
        PT525.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT525.setTransactionDateTime(format.parse("2019-03-06"));
        PT525.setFinalPayment(743.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT525);

        RoomBooking rb526 = new RoomBooking();
        rb526.setBookedRoom(roomSessionLocal.getRoomByName("KRS_302"));
        rb526.setBookInDate(format.parse("2019-03-17"));
        bookingSessionLocal.createRoomBooking(rb526);
        PaymentTransaction PT526 = new PaymentTransaction();
        PT526.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT526.setTransactionDateTime(format.parse("2019-03-07"));
        PT526.setFinalPayment(264.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT526);

        RoomBooking rb527 = new RoomBooking();
        rb527.setBookedRoom(roomSessionLocal.getRoomByName("KRS_303"));
        rb527.setBookInDate(format.parse("2019-03-13"));
        bookingSessionLocal.createRoomBooking(rb527);
        PaymentTransaction PT527 = new PaymentTransaction();
        PT527.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT527.setTransactionDateTime(format.parse("2019-03-08"));
        PT527.setFinalPayment(726.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT527);

        RoomBooking rb528 = new RoomBooking();
        rb528.setBookedRoom(roomSessionLocal.getRoomByName("KRS_304"));
        rb528.setBookInDate(format.parse("2019-03-12"));
        bookingSessionLocal.createRoomBooking(rb528);
        PaymentTransaction PT528 = new PaymentTransaction();
        PT528.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT528.setTransactionDateTime(format.parse("2019-03-09"));
        PT528.setFinalPayment(724.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT528);

        RoomBooking rb529 = new RoomBooking();
        rb529.setBookedRoom(roomSessionLocal.getRoomByName("KRS_305"));
        rb529.setBookInDate(format.parse("2019-03-14"));
        bookingSessionLocal.createRoomBooking(rb529);
        PaymentTransaction PT529 = new PaymentTransaction();
        PT529.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT529.setTransactionDateTime(format.parse("2019-03-10"));
        PT529.setFinalPayment(264.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT529);

        RoomBooking rb530 = new RoomBooking();
        rb530.setBookedRoom(roomSessionLocal.getRoomByName("KRS_401"));
        rb530.setBookInDate(format.parse("2019-04-19"));
        bookingSessionLocal.createRoomBooking(rb530);
        PaymentTransaction PT530 = new PaymentTransaction();
        PT530.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT530.setTransactionDateTime(format.parse("2019-04-01"));
        PT530.setFinalPayment(648.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT530);

        RoomBooking rb531 = new RoomBooking();
        rb531.setBookedRoom(roomSessionLocal.getRoomByName("KRS_402"));
        rb531.setBookInDate(format.parse("2019-04-18"));
        bookingSessionLocal.createRoomBooking(rb531);
        PaymentTransaction PT531 = new PaymentTransaction();
        PT531.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT531.setTransactionDateTime(format.parse("2019-04-02"));
        PT531.setFinalPayment(263.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT531);

        RoomBooking rb532 = new RoomBooking();
        rb532.setBookedRoom(roomSessionLocal.getRoomByName("KRS_403"));
        rb532.setBookInDate(format.parse("2019-04-17"));
        bookingSessionLocal.createRoomBooking(rb532);
        PaymentTransaction PT532 = new PaymentTransaction();
        PT532.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT532.setTransactionDateTime(format.parse("2019-04-03"));
        PT532.setFinalPayment(643.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT532);

        RoomBooking rb533 = new RoomBooking();
        rb533.setBookedRoom(roomSessionLocal.getRoomByName("KRS_404"));
        rb533.setBookInDate(format.parse("2019-04-18"));
        bookingSessionLocal.createRoomBooking(rb533);
        PaymentTransaction PT533 = new PaymentTransaction();
        PT533.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT533.setTransactionDateTime(format.parse("2019-04-04"));
        PT533.setFinalPayment(723.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT533);

        RoomBooking rb534 = new RoomBooking();
        rb534.setBookedRoom(roomSessionLocal.getRoomByName("KRS_405"));
        rb534.setBookInDate(format.parse("2019-04-18"));
        bookingSessionLocal.createRoomBooking(rb534);
        PaymentTransaction PT534 = new PaymentTransaction();
        PT534.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT534.setTransactionDateTime(format.parse("2019-04-05"));
        PT534.setFinalPayment(724.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT534);

        RoomBooking rb535 = new RoomBooking();
        rb535.setBookedRoom(roomSessionLocal.getRoomByName("KRS_501"));
        rb535.setBookInDate(format.parse("2019-04-13"));
        bookingSessionLocal.createRoomBooking(rb535);
        PaymentTransaction PT535 = new PaymentTransaction();
        PT535.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT535.setTransactionDateTime(format.parse("2019-04-06"));
        PT535.setFinalPayment(326.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT535);

        RoomBooking rb536 = new RoomBooking();
        rb536.setBookedRoom(roomSessionLocal.getRoomByName("KRS_502"));
        rb536.setBookInDate(format.parse("2019-04-17"));
        bookingSessionLocal.createRoomBooking(rb536);
        PaymentTransaction PT536 = new PaymentTransaction();
        PT536.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT536.setTransactionDateTime(format.parse("2019-04-07"));
        PT536.setFinalPayment(274.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT536);

        RoomBooking rb537 = new RoomBooking();
        rb537.setBookedRoom(roomSessionLocal.getRoomByName("KRS_503"));
        rb537.setBookInDate(format.parse("2019-04-18"));
        bookingSessionLocal.createRoomBooking(rb537);
        PaymentTransaction PT537 = new PaymentTransaction();
        PT537.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT537.setTransactionDateTime(format.parse("2019-04-08"));
        PT537.setFinalPayment(743.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT537);

        RoomBooking rb538 = new RoomBooking();
        rb538.setBookedRoom(roomSessionLocal.getRoomByName("KRS_504"));
        rb538.setBookInDate(format.parse("2019-04-14"));
        bookingSessionLocal.createRoomBooking(rb538);
        PaymentTransaction PT538 = new PaymentTransaction();
        PT538.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT538.setTransactionDateTime(format.parse("2019-04-09"));
        PT538.setFinalPayment(284.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT538);

        RoomBooking rb539 = new RoomBooking();
        rb539.setBookedRoom(roomSessionLocal.getRoomByName("KRS_505"));
        rb539.setBookInDate(format.parse("2019-04-17"));
        bookingSessionLocal.createRoomBooking(rb539);
        PaymentTransaction PT539 = new PaymentTransaction();
        PT539.addRoomBooking(bookingSessionLocal.getLastRoomBooking());
        PT539.setTransactionDateTime(format.parse("2019-04-10"));
        PT539.setFinalPayment(472.0);
        paymentTransactionSessionLocal.createPaymentTransaction(PT539);

    }
	

    public void intializeRequests() throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        MaintainenceOrder mo1 = new MaintainenceOrder();
        mo1.setDateReported(format.parse("2019-03-01"));
        mo1.setDateResolved(format.parse("2019-03-29"));
        mo1.setDescription("Toilet flush not working");
        mo1.setIsResolved(true);
        mo1.setLocation("Level 2 Male public toilet");
        maintainenceOrderSessionLocal.createMaintainenceOrder(mo1);
        em.flush();

        MaintainenceOrder mo2 = new MaintainenceOrder();
        mo2.setDateReported(format.parse("2019-03-01"));
        mo2.setDescription("Light keeps flickering");
        mo2.setIsResolved(false);
        mo2.setLocation("Level 4 female public toilet");
        maintainenceOrderSessionLocal.createMaintainenceOrder(mo2);
        em.flush();

        LostAndFoundReport lf1 = new LostAndFoundReport();
        lf1.setContactNum("98765432");
        lf1.setIsResolved(true);
        lf1.setItemDescription("Black in colour, item is for a 5 years old kid");
        lf1.setItemName("black teddy bear");
        lf1.setReportType("Lost");
        lf1.setReportedDate(new Date());

        lostAndFoundSessionLocal.createLostAndFoundReport(lf1);
        em.flush();

        LostAndFoundReport lf2 = new LostAndFoundReport();

        lf2.setIsResolved(false);
        lf2.setItemDescription("colour worm stuff toy, found it in the room");
        lf2.setItemName("stuff toy");
        lf2.setReportType("Found");
        lf2.setReportedDate(new Date());

        lostAndFoundSessionLocal.createLostAndFoundReport(lf2);
        em.flush();

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

}
