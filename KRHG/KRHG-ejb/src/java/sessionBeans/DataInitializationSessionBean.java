/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.CreditCard;
import entity.Customer;
import entity.Hotel;
import entity.HotelFacility;
import entity.HouseKeepingOrder;
import entity.LostAndFoundReport;
import entity.MaintainenceOrder;
import entity.MinibarItem;
import entity.Room;
import entity.RoomBooking;
import entity.RoomFacility;
import entity.Staff;
import entity.StaffType;
import error.NoResultException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.GregorianCalendar;
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
                intializeRequests();
            } catch (NoResultException ex) {
                ex.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }

    public void initializeData() throws NoResultException {
//*********************************************Staff Type************************************************
        StaffType st1 = new StaffType("Housekeeping Staff");
        staffSessionLocal.createStaffType(st1);
        st1 = staffSessionLocal.getStaffTypeByName("Housekeeping Staff");

        StaffType st2 = new StaffType("Housekeeping Manager");
        staffSessionLocal.createStaffType(st2);
        st2 = staffSessionLocal.getStaffTypeByName("Housekeeping Manager");

        StaffType st3 = new StaffType("HR Staff");
        staffSessionLocal.createStaffType(st3);
        st3 = staffSessionLocal.getStaffTypeByName("HR Staff");

        StaffType st4 = new StaffType("HR Manager");
        staffSessionLocal.createStaffType(st4);
        st4 = staffSessionLocal.getStaffTypeByName("HR Manager");

        StaffType st5 = new StaffType("Sales and Marketing Staff");
        staffSessionLocal.createStaffType(st5);
        st5 = staffSessionLocal.getStaffTypeByName("Sales and Marketing Staff");

        StaffType st6 = new StaffType("Sales and Marketing Manager");
        staffSessionLocal.createStaffType(st6);
        st6 = staffSessionLocal.getStaffTypeByName("Sales and Marketing Manager");

        StaffType st7 = new StaffType("IT Staff");
        staffSessionLocal.createStaffType(st7);
        st7 = staffSessionLocal.getStaffTypeByName("IT Staff");

        StaffType st8 = new StaffType("IT Manager");
        staffSessionLocal.createStaffType(st8);
        st8 = staffSessionLocal.getStaffTypeByName("IT Manager");

        StaffType st9 = new StaffType("Front Desk Staff");
        staffSessionLocal.createStaffType(st9);
        st9 = staffSessionLocal.getStaffTypeByName("Front Desk Staff");

        StaffType st10 = new StaffType("Front Desk Manager");
        staffSessionLocal.createStaffType(st10);
        st10 = staffSessionLocal.getStaffTypeByName("Front Desk Manager");

        StaffType st11 = new StaffType("Finance Manager");
        staffSessionLocal.createStaffType(st11);
        st11 = staffSessionLocal.getStaffTypeByName("Finance Manager");

        StaffType st12 = new StaffType("Finance Staff");
        staffSessionLocal.createStaffType(st12);
        st12 = staffSessionLocal.getStaffTypeByName("Finance Staff");

//*********************************************Staff************************************************
        Staff s0 = new Staff("Test User", "test", encryptPassword("test"), "test@krhg.com.sg", "88244165", "male", "S9226940Z", "7 Lok Yang Vista", new Date(), "Kent Ridge North", "Laundry Staff", "Laundry", 7, true, "Ee Pui Ling", "7 Lok Yang Vista", "68701722");
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
        staffSessionLocal.createStaff(s0);

        Staff s1 = new Staff("Kenny Ee", "kennyee", encryptPassword("password"), "kennyee@krhg.com.sg", "88244165", "Male", "S9226940Z", "7 Lok Yang Vista", new Date(), "Kent Ridge West", "Laundry Staff", "Laundry", 7, true, "Ee Pui Ling", "7 Lok Yang Vista", "68701722");
        s1.addAccountRights(st1);
        staffSessionLocal.createStaff(s1);

        Staff s2 = new Staff("David Chia Zhi Jie", "davidchia", encryptPassword("password"), "davidchia@krhg.com.sg", "98861094", "Male", "S6831300G", "Blk 226 Lorong 7 Pasir Ris, #04-32", new Date(), "Kent Ridge Grand", "Kitchen Manager", "Kitchen", 14, true, "Chia Xian Siew", "Blk 226 Lorong 7 Pasir Ris, #04-32", "67494068");
        s2.addAccountRights(st2);
        staffSessionLocal.createStaff(s2);

        Staff s3 = new Staff("Alice Chai", "alicechai", encryptPassword("password"), "alicechai@krhg.com.sg", "93070252", "Female", "S3543767C", "Blk 377 Serangoon North Street 88, #15-09", new Date(), "Kent Ridge Central", "Housekeeping Manager", "Housekeeping", 14, true, "Chai Li Ting", "Blk 377 Serangoon North Street 88, #15-09", "61935979");
        s3.addAccountRights(st2);
        staffSessionLocal.createStaff(s3);

        Staff s4 = new Staff("Siti Riduan", "sitiriduan", encryptPassword("password"), "sitiriduan@krhg.com.sg", "93497066", "Female", "S1730049J", "Blk 29 Geylang Street 21, #01-27", new Date(), "Kent Ridge Central", "Housekeeping Staff", "Housekeeping", 7, true, "Riduan Mohd Yaccob", "Blk 29 Geylang Street 21, #01-27", "67603364");
        s4.addAccountRights(st1);
        staffSessionLocal.createStaff(s4);

        Staff s5 = new Staff("Geoffrey Gan", "geoffreygan", encryptPassword("password"), "geoffreygan@krhg.com.sg", "91574480", "Male", "F9117753Q", "Blk 364 Geylang Street 17, #18-06", new Date(), "Kent Ridge North East", "Housekeeping Manager", "Housekeeping", 14, true, "Gan Kim Hock", "Blk 364 Geylang Street 17, #18-06", "61446071");
        s5.addAccountRights(st2);
        staffSessionLocal.createStaff(s5);

        Staff s6 = new Staff("Khor Yuanruo Gene", "genekhor", encryptPassword("password"), "genekhor@krhg.com.sg", "95189616", "Male", "S7665201E", "Blk 34 Tampines Street 74, #11-44", new Date(), "Kent Ridge West", "Laundry Manager", "Laundry", 14, true, "Khor Ru Shan", "Blk 34 Tampines Street 74, #11-44", "66910568");
        s6.addAccountRights(st2);
        staffSessionLocal.createStaff(s6);

        Staff s7 = new Staff("Dakota Chee", "dakotachee", encryptPassword("password"), "dakotachee@krhg.com.sg", "94094757", "Female", "S0297606D", "Blk 38 Lorong 8 Rochor, #01-04", new Date(), "Kent Ridge South East", "Housekeeping Manager", "Housekeeping", 14, true, "Chee Hong Chye", "Blk 38 Lorong 8 Rochor, #01-04", "68669896");
        s7.addAccountRights(st2);
        staffSessionLocal.createStaff(s7);

        Staff s8 = new Staff("Winston Shum", "winstonshum", encryptPassword("password"), "winstonshum@krhg.com.sg", "96531553", "Male", "S8400752H", "6 Dover Green, #02-31", new Date(), "Kent Ridge North", "Laundry Staff", "Laundry", 7, true, "Shum Chee Ping", "6 Dover Green, #02-31", "64907198");
        s8.addAccountRights(st1);
        staffSessionLocal.createStaff(s8);

        Staff s9 = new Staff("Jonathan Loy", "jonathanloy", encryptPassword("password"), "jonathanloy@krhg.com.sg", "96717486", "Male", "S8017091B", "6 Innova Estate", new Date(), "Kent Ridge East", "Kitchen Manager", "Kitchen", 14, true, "Loy Tian Kiew", "6 Innova Estate", "65353918");
        s9.addAccountRights(st2);
        staffSessionLocal.createStaff(s9);

        Staff s10 = new Staff("Anabelle Loh", "anabelleloh", encryptPassword("password"), "anabelleloh@krhg.com.sg", "91526387", "Female", "F7623475L", "57 Jalan Damai", new Date(), "Kent Ridge West", "Housekeeping Staff", "Housekeeping", 7, true, "Loh Siew Kim", "57 Jalan Damai", "67262650");
        s10.addAccountRights(st1);
        staffSessionLocal.createStaff(s10);

        Staff s11 = new Staff("Isabell Teoh", "isabellteoh", encryptPassword("password"), "isabellteoh@krhg.com.sg", "94070476", "Female", "S3085023H", "Blk 382 Toa Payoh Street 27, #16-08", new Date(), "Kent Ridge East", "Housekeeping Staff", "Housekeeping", 7, true, "Teoh Chai Yee", "Blk 382 Toa Payoh Street 27, #16-08", "65648264");
        s11.addAccountRights(st1);
        staffSessionLocal.createStaff(s11);

        Staff s12 = new Staff("Esther Chai", "estherchai", encryptPassword("password"), "estherchai@krhg.com.sg", "87698197", "Female", "S8765702G", "1 Bukit Ho Swee Crescent", new Date(), "Kent Ridge West", "Front Desk Manager", "Front Desk", 14, true, "Chai Sum Ping", "1 Bukit Ho Swee Crescent", "67143224");
        s12.addAccountRights(st10);
        staffSessionLocal.createStaff(s12);

        Staff s13 = new Staff("Eric Ong", "ericong", encryptPassword("password"), "ericong@krhg.com.sg", "92237097", "Male", "F8880046W", "7 Bukit Gombak Park, #15-04", new Date(), "HQ", "Admin Staff", "Admin/HR", 7, true, "Ong Tian Cheng", "7 Bukit Gombak Park, #15-04", "60899072");
        s13.addAccountRights(st3);
        staffSessionLocal.createStaff(s13);

        Staff s14 = new Staff("Tan Qing Yi Fatin", "fatintan", encryptPassword("password"), "fatintan@krhg.com.sg", "96228753", "Female", "S8499523A", "4 Tanjong Pagar Lane, #15-15", new Date(), "Kent Ridge East", "Front Desk Manager", "Front Desk", 14, true, "Tan Kim Heng", "4 Tanjong Pagar Lane, #15-15", "66669098");
        s14.addAccountRights(st10);
        staffSessionLocal.createStaff(s14);

        Staff s15 = new Staff("Julia Khim", "juliakhim", encryptPassword("password"), "juliakhim@krhg.com.sg", "96790401", "Female", "S0465886H", "Blk 13 Bedok Street 70, #06-02", new Date(), "Kent Ridge East", "Kitchen Staff", "Kitchen", 7, true, "Khim Seng Chye", "Blk 13 Bedok Street 70, #06-02", "68716399");
        s15.addAccountRights(st1);
        staffSessionLocal.createStaff(s15);

        Staff s16 = new Staff("Kim Goh", "kimgoh", encryptPassword("password"), "kimgoh@krhg.com.sg", "88646281", "Female", "S8213938I", "Blk 144 Pasir Ris Street 76, #05-12", new Date(), "Kent Ridge Grand", "Housekeeping Manager", "Housekeeping", 14, true, "Goh Kum Swee", "Blk 144 Pasir Ris Street 76, #05-12", "61972198");
        s16.addAccountRights(st2);
        staffSessionLocal.createStaff(s16);

        Staff s17 = new Staff("Hudson Lazaroo", "hudsonlazaroo", encryptPassword("password"), "hudsonlazaroo@krhg.com.sg", "96880485", "Male", "S0478883D", "6 Cairnhill Crescent", new Date(), "HQ", "Finance/ Operations Director", "Senior Management", 21, true, "Lazaroo Adam", "6 Cairnhill Crescent", "62651389");
        s17.addAccountRights(st11);
        staffSessionLocal.createStaff(s17);

        Staff s18 = new Staff("Theo Liew Weide", "theoliewweide", encryptPassword("password"), "theoliewweide@krhg.com.sg", "94343719", "Male", "S7394844D", "4 Serangoon North Center", new Date(), "Kent Ridge North", "Kitchen Manager", "Kitchen", 14, true, "Liew Siew Guan", "4 Serangoon North Center", "68614319");
        s18.addAccountRights(st2);
        staffSessionLocal.createStaff(s18);

        Staff s19 = new Staff("Nettie Loh", "nettieloh", encryptPassword("password"), "nettieloh@krhg.com.sg", "96739136", "Female", "S8223938C", "9 Farrer Walk, #01-32", new Date(), "Kent Ridge Central", "Front Desk Manager", "Front Desk", 14, true, "Loh Tian Hock", "9 Farrer Walk, #01-32", "68907701");
        s19.addAccountRights(st10);
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
        s23.addAccountRights(st2);
        staffSessionLocal.createStaff(s23);

        Staff s24 = new Staff("Adlina Chye Wan Yee", "adlinachye", encryptPassword("password"), "adlinachye@krhg.com.sg", "99337234", "Female", "S7748702F", "Blk 183 Sengkang Street 76, #13-17", new Date(), "Kent Ridge North", "Front Desk Manager", "Front Desk", 14, true, "Chye Siew Hun", "Blk 183 Sengkang Street 76, #13-17", "65276645");
        s24.addAccountRights(st10);
        staffSessionLocal.createStaff(s24);

        Staff s25 = new Staff("Kimberly Soin", "kimberlysoin", encryptPassword("password"), "kimberlysoin@krhg.com.sg", "90910322", "Female", "F7040165R", "41 Bidadari Road", new Date(), "Kent Ridge North East", "Maintenance", "Maintenance", 7, true, "Soin Ang Yue", "41 Bidadari Road", "68111147");
        s25.addAccountRights(st1);
        staffSessionLocal.createStaff(s25);

        Staff s26 = new Staff("Betty Ho", "bettyho", encryptPassword("password"), "bettyho@krhg.com.sg", "86707383", "Female", "S8302585I", "Blk 30 Bukit Panjang Street 70, #16-17", new Date(), "Kent Ridge South", "Laundry Staff", "Laundry", 7, true, "Ho Chin Yup", "Blk 30 Bukit Panjang Street 70, #16-17", "62678519");
        s26.addAccountRights(st1);
        staffSessionLocal.createStaff(s26);

        Staff s27 = new Staff("Stephen Chua", "stephenchua", encryptPassword("password"), "stephenchua@krhg.com.sg", "98687431", "Male", "S7603298Z", "9 Dunearn Lane", new Date(), "HQ", "Sales and Marketing Staff", "Sales and Marketing", 7, true, "Chua Hock Oei", "9 Dunearn Lane", "61991461");
        s27.addAccountRights(st5);
        staffSessionLocal.createStaff(s27);

        Staff s28 = new Staff("Samson Lum", "samsonlum", encryptPassword("password"), "samsonlum@krhg.com.sg", "95985187", "Male", "S9823405E", "Blk 148 Pasir Ris Street 25, #14-33", new Date(), "Kent Ridge Central", "Front Desk Staff", "Front Desk", 7, true, "Lum Siew Hun", "Blk 148 Pasir Ris Street 25, #14-33", "60262406");
        s28.addAccountRights(st9);
        staffSessionLocal.createStaff(s28);

        Staff s29 = new Staff("Dave Kwok Yiew Hsien", "davekwok", encryptPassword("password"), "davekwok@krhg.com.sg", "82523699", "Male", "S7548652I", "4 Jalan Kuang", new Date(), "Kent Ridge Central", "Kitchen Manager", "Kitchen", 14, true, "Kwok Chee Siew", "4 Jalan Kuang", "63818471");
        s29.addAccountRights(st2);
        staffSessionLocal.createStaff(s29);

        Staff s30 = new Staff("Lawrence Goh Weida", "lawrencegoh", encryptPassword("password"), "lawrencegoh@krhg.com.sg", "97112720", "Male", "S1992887Z", "Blk 13 Lorong 7 Chong Boon, #06-08", new Date(), "Kent Ridge Grand", "Maintenance", "Maintenance", 7, true, "Goh Mei Ting", "Blk 13 Lorong 7 Chong Boon, #06-08", "68376309");
        s30.addAccountRights(st1);
        staffSessionLocal.createStaff(s30);

        Staff s31 = new Staff("Joshua Lam", "joshualam", encryptPassword("password"), "joshualam@krhg.com.sg", "97315439", "Male", "S0246547G", "48 Telok Blangah View", new Date(), "Kent Ridge Grand", "Kitchen Staff", "Kitchen", 7, true, "Lam Hue Ting", "48 Telok Blangah View", "66601252");
        s31.addAccountRights(st1);
        staffSessionLocal.createStaff(s31);

        Staff s32 = new Staff("Emma Choo", "emmachoo", encryptPassword("password"), "emmachoo@krhg.com.sg", "97010952", "Female", "S3009421B", "13 Jalan Lye Kwee", new Date(), "Kent Ridge North West", "Maintenance", "Maintenance", 7, true, "Choo Mong Lam", "13 Jalan Lye Kwee", "66816679");
        s32.addAccountRights(st1);
        staffSessionLocal.createStaff(s32);

        Staff s33 = new Staff("Ava Fong", "avafong", encryptPassword("password"), "avafong@krhg.com.sg", "95990306", "Female", "S9556331G", "16 Ghim Moh Park", new Date(), "Kent Ridge North East", "Front Desk Manager", "Front Desk", 14, true, "Fong Siew Fong", "16 Ghim Moh Park", "60822895");
        s33.addAccountRights(st10);
        staffSessionLocal.createStaff(s33);

        Staff s34 = new Staff("Chin Hwee Hoon Phoebe", "phoebechin", encryptPassword("password"), "phoebechin@krhg.com.sg", "95014355", "Female", "S7558969G", "7 Boon Keng View", new Date(), "Kent Ridge North West", "General Manager", "Front Desk", 18, true, "Chin Fok Lam", "7 Boon Keng View", "61316647");
        s34.addAccountRights(st10);
        staffSessionLocal.createStaff(s34);

        Staff s35 = new Staff("Mike Chin", "mikechin", encryptPassword("password"), "mikechin@krhg.com.sg", "99985141", "Male", "S9004555E", "Blk 12 Lorong 5 Chong Boon, #09-18", new Date(), "Kent Ridge North West", "Front Desk Staff", "Front Desk", 7, true, "Chin Beng Chun", "Blk 12 Lorong 5 Chong Boon, #09-18", "68272848");
        s35.addAccountRights(st9);
        staffSessionLocal.createStaff(s35);

        Staff s36 = new Staff("Vanessa Sathyalingam", "vanessasathya", encryptPassword("password"), "vanessasathya@krhg.com.sg", "84122183", "Female", "S4498733C", "47 Chin Bee View, #01-40", new Date(), "Kent Ridge North East", "Laundry Manager", "Laundry", 14, true, "Sathyalingam Shanugam", "47 Chin Bee View, #01-40", "65954146");
        s36.addAccountRights(st2);
        staffSessionLocal.createStaff(s36);

        Staff s37 = new Staff("Renie Yang", "renieyang", encryptPassword("password"), "renieyang@krhg.com.sg", "99227891", "Female", "S7890299Z", "Blk 26 Kallang Street 81, #18-03", new Date(), "Kent Ridge North West", "Kitchen Manager", "Kitchen", 14, true, "Yang Hui Ting", "Blk 26 Kallang Street 81, #18-03", "65996087");
        s37.addAccountRights(st2);
        staffSessionLocal.createStaff(s37);

        Staff s38 = new Staff("Felicia Loy", "felicialoy", encryptPassword("password"), "felicialoy@krhg.com.sg", "95186486", "Female", "S6998351J", "Blk 383 Lorong 1 Mattar, #18-15", new Date(), "Kent Ridge South", "Kitchen Manager", "Kitchen", 14, true, "Loy Num Hee", "Blk 383 Lorong 1 Mattar, #18-15", "69092851");
        s38.addAccountRights(st2);
        staffSessionLocal.createStaff(s38);

        Staff s39 = new Staff("Janice Critchley", "janicecritchley", encryptPassword("password"), "janicecritchley@krhg.com.sg", "83084182", "Female", "S6998114C", "Blk 49 Yishun Street 32, #13-17", new Date(), "Kent Ridge Grand", "General Manager", "Front Desk", 7, true, "Critchley Joseph", "Blk 49 Yishun Street 32, #13-17", "64108229");
        s39.addAccountRights(st10);
        staffSessionLocal.createStaff(s39);

        Staff s40 = new Staff("Low Si Ting", "lowsiting", encryptPassword("password"), "lowsiting@krhg.com.sg", "82237276", "Female", "S7461407H", "Blk 40 Jurong East Street 82, #01-31", new Date(), "Kent Ridge South East", "Front Desk Manager", "Front Desk", 14, true, "Low Li Hung", "Blk 40 Jurong East Street 82, #01-31", "64214229");
        s40.addAccountRights(st10);
        staffSessionLocal.createStaff(s40);

        Staff s41 = new Staff("Irwin Hong Kok Hwee", "irwinhong", encryptPassword("password"), "irwinhong@krhg.com.sg", "94538996", "Male", "S6832133F", "Blk 379 Clementi Street 32, #02-04", new Date(), "Kent Ridge North", "General Manager", "Front Desk", 18, true, "Hong Tong Gek", "Blk 379 Clementi Street 32, #02-04", "61142355");
        s41.addAccountRights(st10);
        staffSessionLocal.createStaff(s41);

        Staff s42 = new Staff("Ling Min Qi Connie", "connieling", encryptPassword("password"), "connieling@krhg.com.sg", "84964685", "Female", "S1834949C", "Blk 486 Ang Mo Kio Street 83, #13-22", new Date(), "Kent Ridge North", "Maintenance", "Maintenance", 7, true, "Ling Siew King", "Blk 486 Ang Mo Kio Street 83, #13-22", "64274870");
        s42.addAccountRights(st1);
        staffSessionLocal.createStaff(s42);

        Staff s43 = new Staff("Joel To Yueh", "joelyueh", encryptPassword("password"), "joelyueh@krhg.com.sg", "99021935", "Male", "S7268516D", "28 Dover Field", new Date(), "Kent Ridge North", "Laundry Manager", "Laundry", 14, true, "To Gek King", "28 Dover Field", "62657357");
        s43.addAccountRights(st2);
        staffSessionLocal.createStaff(s43);

        Staff s44 = new Staff("Eileen Yao", "eileenyao", encryptPassword("password"), "eileenyao@krhg.com.sg", "90355949", "Female", "F3218960K", "Blk 430 Toa Payoh Street 33, #18-16", new Date(), "Kent Ridge North", "Kitchen Staff", "Kitchen", 7, true, "Yao Beh Peo", "Blk 430 Toa Payoh Street 33, #18-16", "65871688");
        s44.addAccountRights(st1);
        staffSessionLocal.createStaff(s44);

        Staff s45 = new Staff("Devi Yao", "deviyao", encryptPassword("password"), "deviyao@krhg.com.sg", "93790021", "Female", "S2063617C", "Blk 30 Aljunied Street 73, #07-17", new Date(), "Kent Ridge West", "Housekeeping Manager", "Housekeeping", 14, true, "Yao Cheng Ji", "Blk 30 Aljunied Street 73, #07-17", "60930788");
        s45.addAccountRights(st2);
        staffSessionLocal.createStaff(s45);

        Staff s46 = new Staff("Ooi Wei Quan Chris", "chrisooi", encryptPassword("password"), "chrisooi@krhg.com.sg", "90301549", "Male", "F0402355L", "Blk 29 Geylang Street 80, #09-15", new Date(), "Kent Ridge Grand", "Kitchen Staff", "Kitchen", 7, true, "Ooi Beng Chin", "Blk 29 Geylang Street 80, #09-15", "62736175");
        s46.addAccountRights(st1);
        staffSessionLocal.createStaff(s46);

        Staff s47 = new Staff("Siti Mohamed", "sitimohamed", encryptPassword("password"), "sitimohamed@krhg.com.sg", "93224894", "Female", "S3776248B", "77 Chong Pang Avenue North", new Date(), "Kent Ridge South East", "General Manager", "Front Desk", 18, true, "Mohamed Rahid", "77 Chong Pang Avenue North", "62491275");
        s47.addAccountRights(st10);
        staffSessionLocal.createStaff(s47);

        Staff s48 = new Staff("Gabriel Kumar", "gabrielkumar", encryptPassword("password"), "gabrielkumar@krhg.com.sg", "94466436", "Male", "S7093840E", "5 Pioneer Heights", new Date(), "Kent Ridge South West", "Kitchen Manager", "Kitchen", 14, true, "Kumar Joseph", "5 Pioneer Heights", "69160906");
        s48.addAccountRights(st2);
        staffSessionLocal.createStaff(s48);

        Staff s49 = new Staff("Stephen Keller", "stephenkeller", encryptPassword("password"), "stephenkeller@krhg.com.sg", "80099266", "Male", "S7438436F", "Blk 17 Tampines Street 14, #09-10", new Date(), "Kent Ridge South West", "Front Desk Manager", "Front Desk", 14, true, "Keller Henry", "Blk 17 Tampines Street 14, #09-10", "60459076");
        s49.addAccountRights(st10);
        staffSessionLocal.createStaff(s49);

        Staff s50 = new Staff("Elisa Lim", "elisalim", encryptPassword("password"), "elisalim@krhg.com.sg", "86218501", "Female", "S0407098D", "52 Jalan Jambo Ayer", new Date(), "HQ", "Managing Director", "Senior Management", 21, true, "Lim Chuan Hoo", "52 Jalan Jambo Ayer", "62282110");
        s50.addAccountRights(st11);
        staffSessionLocal.createStaff(s50);

        Staff s51 = new Staff("Harrison Lopez", "harrisonlopez", encryptPassword("password"), "harrisonlopez@krhg.com.sg", "90979239", "Male", "F7710098U", "8 Sungei Gedong Garden", new Date(), "Kent Ridge South", "Maintenance", "Maintenance", 7, true, "Lopez Maria", "8 Sungei Gedong Garden", "66200708");
        s51.addAccountRights(st1);
        staffSessionLocal.createStaff(s51);

        Staff s52 = new Staff("Adrian To", "adrianto", encryptPassword("password"), "adrianto@krhg.com.sg", "94828126", "Male", "S7870185D", "Blk 46 Toa Payoh Street 32, #18-13", new Date(), "Kent Ridge North West", "Laundry Manager", "Laundry", 14, true, "To Seep Koo", "Blk 46 Toa Payoh Street 32, #18-13", "67740236");
        s52.addAccountRights(st2);
        staffSessionLocal.createStaff(s52);

        Staff s53 = new Staff("Kyle Neo", "kyleneo", encryptPassword("password"), "kyleneo@krhg.com.sg", "88892891", "Male", "S9713064G", "5 Admiralty Terrace, #09-39", new Date(), "Kent Ridge West", "Front Desk Staff", "Front Desk", 7, true, "Neo Beng Young", "5 Admiralty Terrace, #09-39", "64285195");
        s53.addAccountRights(st9);
        staffSessionLocal.createStaff(s53);

        Staff s54 = new Staff("Gladys Ee", "gladysee", encryptPassword("password"), "gladysee@krhg.com.sg", "89038687", "Female", "F1447887Q", "7 Bugis Walk", new Date(), "Kent Ridge Central", "Kitchen Staff", "Kitchen", 7, true, "Ee Yung Hun", "7 Bugis Walk", "62498487");
        s54.addAccountRights(st1);
        staffSessionLocal.createStaff(s54);

        Staff s55 = new Staff("Fong Su Wei", "fongsuwei", encryptPassword("password"), "fongsuwei@krhg.com.sg", "86278601", "Female", "S4540945G", "3 Pasir Ris View", new Date(), "Kent Ridge South West", "Laundry Manager", "Laundry", 14, true, "Fong Mooi Chi", "3 Pasir Ris View", "60984582");
        s55.addAccountRights(st2);
        staffSessionLocal.createStaff(s55);

        Staff s56 = new Staff("Preeti Khor", "preetikhor", encryptPassword("password"), "preetikhor@krhg.com.sg", "99525185", "Female", "S8957735G", "9 Pioneer Heights", new Date(), "Kent Ridge South", "Front Desk Staff", "Front Desk", 7, true, "Khor Chee Poh", "9 Pioneer Heights", "63459295");
        s56.addAccountRights(st9);
        staffSessionLocal.createStaff(s56);

        Staff s57 = new Staff("Kim Kok Bok Ai", "kimkok", encryptPassword("password"), "kimkok@krhg.com.sg", "80942114", "Female", "S7445023G", "Blk 16 Lorong 6 MacPherson, #10-38", new Date(), "Kent Ridge South", "Front Desk Manager", "Front Desk", 14, true, "Kok Peh Cheah", "Blk 16 Lorong 6 MacPherson, #10-38", "69276817");
        s57.addAccountRights(st10);
        staffSessionLocal.createStaff(s57);

        Staff s58 = new Staff("Choy Beng Mooi", "choybengmooi", encryptPassword("password"), "choybengmooi@krhg.com.sg", "90509346", "Female", "F3435864T", "Blk 14 Ang Mo Kio Street 87, #05-45", new Date(), "HQ", "Admin Manager", "Admin/HR", 14, true, "Choy Wee Tat", "Blk 14 Ang Mo Kio Street 87, #05-45", "60260999");
        s58.addAccountRights(st4);
        staffSessionLocal.createStaff(s58);

        Staff s59 = new Staff("Amy Chee", "amychee", encryptPassword("password"), "amychee@krhg.com.sg", "87116256", "Female", "S8868188F", "73 Buona Vista Point", new Date(), "Kent Ridge North", "Front Desk Staff", "Front Desk", 7, true, "Chee Chien Boo", "73 Buona Vista Point", "69194607");
        s59.addAccountRights(st9);
        staffSessionLocal.createStaff(s59);

        Staff s60 = new Staff("Maria Poh", "mariapoh", encryptPassword("password"), "mariapoh@krhg.com.sg", "96564109", "Female", "S9708115H", "Blk 44 Lorong 6 Serangoon Gardens, #09-25", new Date(), "Kent Ridge East", "Laundry Staff", "Laundry", 7, true, "Poh Tuan Siew", "Blk 44 Lorong 6 Serangoon Gardens, #09-25", "68165135");
        s60.addAccountRights(st1);
        staffSessionLocal.createStaff(s60);

        Staff s61 = new Staff("Charles Boo", "charlesboo", encryptPassword("password"), "charlesboo@krhg.com.sg", "97940015", "Male", "S2433605J", "5 Tai Seng Green, #02-40", new Date(), "Kent Ridge South", "Laundry Manager", "Laundry", 14, true, "Boo Lieu Ling", "5 Tai Seng Green, #02-40", "61282380");
        s61.addAccountRights(st2);
        staffSessionLocal.createStaff(s61);

        Staff s62 = new Staff("Zhang Chi Chien", "zhangchichien", encryptPassword("password"), "zhangchichien@krhg.com.sg", "99597384", "Female", "S8421189C", "Blk 435 Geylang Street 87, #10-19", new Date(), "Kent Ridge West", "Kitchen Manager", "Kitchen", 14, true, "Zhang Cheng Yue", "Blk 435 Geylang Street 87, #10-19", "66068027");
        s62.addAccountRights(st2);
        staffSessionLocal.createStaff(s62);

        Staff s63 = new Staff("Ahmad Mohamad", "ahmadmohamad", encryptPassword("password"), "ahmadmohamad@krhg.com.sg", "93489085", "Male", "S0471497J", "Blk 31 Geylang East Street 82, #11-20", new Date(), "Kent Ridge Grand", "Laundry Staff", "Laundry", 7, true, "Mohamad Khalid", "Blk 31 Geylang East Street 82, #11-20", "69096201");
        s63.addAccountRights(st1);
        staffSessionLocal.createStaff(s63);

        Staff s64 = new Staff("Caleb Peh", "calebpeh", encryptPassword("password"), "calebpeh@krhg.com.sg", "91293623", "Male", "F6965199L", "Blk 41 Aljunied Street 13, #10-08", new Date(), "Kent Ridge South", "Housekeeping Staff", "Housekeeping", 7, true, "Peh Chuan Nam", "Blk 41 Aljunied Street 13, #10-08", "63717937");
        s64.addAccountRights(st1);
        staffSessionLocal.createStaff(s64);

        Staff s65 = new Staff("Victor Khor Wee Tat", "victorkhor", encryptPassword("password"), "victorkhor@krhg.com.sg", "99183262", "Male", "S7779810B", "86 Pasir Panjang Terrace", new Date(), "HQ", "Finance Staff", "Finance", 7, true, "Khor Leow Sun", "86 Pasir Panjang Terrace", "62833234");
        s65.addAccountRights(st3);
        staffSessionLocal.createStaff(s65);

        Staff s66 = new Staff("Adrian Cheah", "adriancheah", encryptPassword("password"), "adriancheah@krhg.com.sg", "80716031", "Male", "S7880506D", "Blk 143 Jurong East Terrace", new Date(), "Kent Ridge South West", "Front Desk Staff", "Front Desk", 7, true, "Cheah Hui Ling", "Blk 143 Jurong East Terrace", "60068470");
        s66.addAccountRights(st9);
        staffSessionLocal.createStaff(s66);

        Staff s67 = new Staff("Elwin Ling", "elwinling", encryptPassword("password"), "elwinling@krhg.com.sg", "90800799", "Male", "F3374906Q", "1 Boon Lay View, #11-42", new Date(), "Kent Ridge West", "Kitchen Staff", "Kitchen", 7, true, "Ling Soon Kit", "1 Boon Lay View, #11-42", "63364630");
        s67.addAccountRights(st1);
        staffSessionLocal.createStaff(s67);

        Staff s68 = new Staff("Walter Lieu", "walterlieu", encryptPassword("password"), "walterlieu@krhg.com.sg", "84778283", "Male", "S3010260F", "Blk 230 Hougang Street 70, #05-22", new Date(), "Kent Ridge South West", "General Manager", "Front Desk", 18, true, "Lieu Toong Hut", "Blk 230 Hougang Street 70, #05-22", "69534452");
        s68.addAccountRights(st10);
        staffSessionLocal.createStaff(s68);

        Staff s69 = new Staff("Nia Lieu", "nialieu", encryptPassword("password"), "nialieu@krhg.com.sg", "99091599", "Female", "S7531244Z", "Blk 47 Serangoon North Street 19, #16-15", new Date(), "Kent Ridge Central", "Laundry Manager", "Laundry", 14, true, "Lieu Hung Cheng", "Blk 47 Serangoon North Street 19, #16-15", "60770079");
        s69.addAccountRights(st2);
        staffSessionLocal.createStaff(s69);

        Staff s70 = new Staff("Dave Boo", "daveboo", encryptPassword("password"), "daveboo@krhg.com.sg", "85461304", "Male", "S2316051Z", "84 Woodlands Hill, #15-20", new Date(), "Kent Ridge North", "Housekeeping Manager", "Housekeeping", 14, true, "Boo Yu Poh", "84 Woodlands Hill, #15-20", "65798399");
        s70.addAccountRights(st2);
        staffSessionLocal.createStaff(s70);

        Staff s71 = new Staff("Garry Scully", "garryscully", encryptPassword("password"), "garryscully@krhg.com.sg", "99946025", "Male", "S9250025Z", "2 Jurong East Walk, #06-28", new Date(), "Kent Ridge Grand", "Front Desk Staff", "Front Desk", 7, true, "Scully Freddy", "2 Jurong East Walk, #06-28", "67793245");
        s71.addAccountRights(st9);
        staffSessionLocal.createStaff(s71);

        Staff s72 = new Staff("Farida Kadir", "faridakadir", encryptPassword("password"), "faridakadir@krhg.com.sg", "98728893", "Female", "S7400526H", "Blk 425 Geylang Street 14, #02-27", new Date(), "Kent Ridge South East", "Kitchen Manager", "Kitchen", 14, true, "Kadir Yaakob", "Blk 425 Geylang Street 14, #02-27", "67834848");
        s72.addAccountRights(st2);
        staffSessionLocal.createStaff(s72);

        Staff s73 = new Staff("Khalil Ismail", "khalilismail", encryptPassword("password"), "khalilismail@krhg.com.sg", "84919099", "Male", "S2302951J", "9 Jalan Lim Tai See", new Date(), "Kent Ridge South", "General Manager", "Front Desk", 18, true, "Ismail Danial", "9 Jalan Lim Tai See", "68202843");
        s73.addAccountRights(st10);
        staffSessionLocal.createStaff(s73);

        Staff s74 = new Staff("Haziq Mohamed", "haziqmohamed", encryptPassword("password"), "haziqmohamed@krhg.com.sg", "92448615", "Male", "F9226149L", "4 Jalan Ayer", new Date(), "Kent Ridge South", "Housekeeping Manager", "Housekeeping", 14, true, "Mohamed Jihan", "4 Jalan Ayer", "69960072");
        s74.addAccountRights(st2);
        staffSessionLocal.createStaff(s74);

        Staff s75 = new Staff("Gemmie Yu", "gemmieyu", encryptPassword("password"), "gemmieyu@krhg.com.sg", "96387568", "Female", "S9350425I", "Blk 375 Serangoon Gardens Street 31, #10-12", new Date(), "Kent Ridge Grand", "Front Desk Staff", "Front Desk", 7, true, "Yu Chi Loh", "Blk 375 Serangoon Gardens Street 31, #10-12", "61657615");
        s75.addAccountRights(st9);
        staffSessionLocal.createStaff(s75);

        Staff s76 = new Staff("Ayden Leow", "aydenleow", encryptPassword("password"), "aydenleow@krhg.com.sg", "96525791", "Male", "S9815008J", "6 Jalan Seruling", new Date(), "Kent Ridge North East", "Front Desk Staff", "Front Desk", 7, true, "Leow Leong Lee", "6 Jalan Seruling", "66606837");
        s76.addAccountRights(st9);
        staffSessionLocal.createStaff(s76);

        Staff s77 = new Staff("Benjamin Choi", "benjaminchoi", encryptPassword("password"), "benjaminchoi@krhg.com.sg", "81887812", "Male", "S6921042B", "Blk 14 Bedok Street 82, #09-43", new Date(), "Kent Ridge Central", "General Manager", "Front Desk", 18, true, "Choi Steven", "Blk 14 Bedok Street 82, #09-43", "62297369");
        s77.addAccountRights(st10);
        staffSessionLocal.createStaff(s77);

        Staff s78 = new Staff("Aisha Tan", "aishatan", encryptPassword("password"), "aishatan@krhg.com.sg", "99527233", "Female", "S8952070C", "55 Jalan Tenteram", new Date(), "Kent Ridge Grand", "Laundry Staff", "Laundry", 7, true, "Tan Ah Kow", "55 Jalan Tenteram", "67823754");
        s78.addAccountRights(st1);
        staffSessionLocal.createStaff(s78);

        Staff s79 = new Staff("Luke Cheng", "lukecheng", encryptPassword("password"), "lukecheng@krhg.com.sg", "88764415", "Male", "S8097994J", "Blk 19 Marine Parade Street 77, #02-04", new Date(), "Kent Ridge North West", "Front Desk Manager", "Front Desk", 14, true, "Cheng Low Hung", "Blk 19 Marine Parade Street 77, #02-04", "60245816");
        s79.addAccountRights(st10);
        staffSessionLocal.createStaff(s79);

        Staff s80 = new Staff("Marcia Loh", "marcialoh", encryptPassword("password"), "marcialoh@krhg.com.sg", "91720323", "Female", "F9478179P", "Blk 30 Lorong 2 Seletar, #12-38", new Date(), "Kent Ridge South East", "Maintenance", "Maintenance", 7, true, "Loh Tan Low", "Blk 30 Lorong 2 Seletar, #12-38", "64228680");
        s80.addAccountRights(st1);
        staffSessionLocal.createStaff(s80);

        Staff s81 = new Staff("Ari Salim", "arisalim", encryptPassword("password"), "arisalim@krhg.com.sg", "97022200", "Male", "S2765749D", "9 Watten Center, #02-25", new Date(), "Kent Ridge South West", "Housekeeping Manager", "Housekeeping", 14, true, "Salim Adam", "9 Watten Center, #02-25", "63860258");
        s81.addAccountRights(st2);
        staffSessionLocal.createStaff(s81);

        Staff s82 = new Staff("Frederique Leong", "frederiqueleong", encryptPassword("password"), "frederiqueleong@krhg.com.sg", "81452127", "Female", "S7312804H", "Blk 359 Aljunied Street 39, #16-09", new Date(), "Kent Ridge South East", "Laundry Manager", "Laundry", 14, true, "Leong Yew Fatt", "Blk 359 Aljunied Street 39, #16-09", "63563648");
        s82.addAccountRights(st2);
        staffSessionLocal.createStaff(s82);

        Staff s83 = new Staff("Samson Lin", "samsonlin", encryptPassword("password"), "samsonlin@krhg.com.sg", "93263527", "Male", "S3488482Z", "Blk 11 Marine Parade Street 14, #18-22", new Date(), "Kent Ridge Grand", "Housekeeping Staff", "Housekeeping", 7, true, "Lin Yue Chan", "Blk 11 Marine Parade Street 14, #18-22", "67928717");
        s83.addAccountRights(st1);
        staffSessionLocal.createStaff(s83);

        Staff s84 = new Staff("Roman Oh Wei-Lun", "romanoh", encryptPassword("password"), "romanoh@krhg.com.sg", "87723481", "Male", "S8226907Z", "Blk 279 Lorong 7 Chin Bee, #08-19", new Date(), "Kent Ridge Central", "Laundry Staff", "Laundry", 7, true, "Oh Lih Bin", "Blk 279 Lorong 7 Chin Bee, #08-19", "62306331");
        s84.addAccountRights(st1);
        staffSessionLocal.createStaff(s84);

        Staff s85 = new Staff("Amar Salleh", "amarsalleh", encryptPassword("password"), "amarsalleh@krhg.com.sg", "83530914", "Male", "S7329826A", "5 Bukit Gombak Point, #15-41", new Date(), "HQ", "Sales and Marketing Manager", "Sales and Marketing", 14, true, "Salleh Farizan", "5 Bukit Gombak Point, #15-41", "66604735");
        s85.addAccountRights(st6);
        staffSessionLocal.createStaff(s85);

        Staff s86 = new Staff("Tong Teng Kiat Irving", "irvingtong", encryptPassword("password"), "irvingtong@krhg.com.sg", "86293792", "Male", "S9260331H", "Blk 12 Boon Lay Street 72, #12-12", new Date(), "Kent Ridge South East", "Front Desk Staff", "Front Desk", 7, true, "Tong Long Kung", "Blk 12 Boon Lay Street 72, #12-12", "69235006");
        s86.addAccountRights(st9);
        staffSessionLocal.createStaff(s86);

        Staff s87 = new Staff("Geoffrey Tan", "geoffreytan", encryptPassword("password"), "geoffreytan@krhg.com.sg", "91077218", "Male", "F6947231K", "3 Chong Pang Drive, #08-38", new Date(), "Kent Ridge South", "Kitchen Staff", "Kitchen", 7, true, "Tan Geok Lin", "3 Chong Pang Drive, #08-38", "68583265");
        s87.addAccountRights(st1);
        staffSessionLocal.createStaff(s87);

        Staff s88 = new Staff("Gemmie Ramaswamy", "gemmierama", encryptPassword("password"), "gemmierama@krhg.com.sg", "83716773", "Female", "S3711992Z", "Blk 45 Aljunied Street 71, #10-42", new Date(), "Kent Ridge East", "Housekeeping Manager", "Housekeeping", 14, true, "Ramaswamy Stephen", "Blk 45 Aljunied Street 71, #10-42", "67111197");
        s88.addAccountRights(st2);
        staffSessionLocal.createStaff(s88);

        Staff s89 = new Staff("Jaclyn Ong", "jaclynong", encryptPassword("password"), "jaclynong@krhg.com.sg", "85919244", "Female", "S1924229C", "Blk 246 Hougang Street 34, #17-10", new Date(), "Kent Ridge South West", "Maintenance", "Maintenance", 7, true, "Ong Loh Whye", "Blk 246 Hougang Street 34, #17-10", "64449483");
        s89.addAccountRights(st1);
        staffSessionLocal.createStaff(s89);

        Staff s90 = new Staff("Ibrahim Wahid", "ibrahimwahid", encryptPassword("password"), "ibrahimwahid@krhg.com.sg", "83835972", "Male", "S1920500B", "Blk 33 Bukit Panjang Street 16, #03-18", new Date(), "Kent Ridge West", "Maintenance", "Maintenance", 7, true, "Wahid Mizra", "Blk 33 Bukit Panjang Street 16, #03-18", "63574402");
        s90.addAccountRights(st1);
        staffSessionLocal.createStaff(s90);

        Staff s91 = new Staff("Nigel Fong", "nigelfong", encryptPassword("password"), "nigelfong@krhg.com.sg", "97490268", "Male", "S2846520C", "6 Woodlands Point, #01-06", new Date(), "Kent Ridge North East", "General Manager", "Front Desk", 18, true, "Fong Lin Ting", "6 Woodlands Point, #01-06", "66647150");
        s91.addAccountRights(st10);
        staffSessionLocal.createStaff(s91);

        Staff s92 = new Staff("Rena Lim", "renalim", encryptPassword("password"), "renalim@krhg.com.sg", "99032688", "Female", "S7100209H", "Blk 19 Serangoon Gardens Street 21, #16-23", new Date(), "Kent Ridge North East", "Kitchen Manager", "Kitchen", 14, true, "Lim Thing Boong", "Blk 19 Serangoon Gardens Street 21, #16-23", "61412476");
        s92.addAccountRights(st2);
        staffSessionLocal.createStaff(s92);

        Staff s93 = new Staff("Hiram Mohamad", "hirammohamad", encryptPassword("password"), "hirammohamad@krhg.com.sg", "95844103", "Male", "S7153893A", "44 Jalan Sampurna", new Date(), "Kent Ridge Grand", "Maintenance Manager", "Maintenance", 14, true, "Mohamad Shah", "44 Jalan Sampurna", "66169332");
        s93.addAccountRights(st2);
        staffSessionLocal.createStaff(s93);

        Staff s94 = new Staff("Larissa Low", "larissalow", encryptPassword("password"), "larissalow@krhg.com.sg", "80316248", "Female", "S6927334C", "49 Geylang East Heights", new Date(), "Kent Ridge Grand", "Laundry Manager", "Laundry", 14, true, "Low Si Hung", "49 Geylang East Heights", "60679559");
        s94.addAccountRights(st2);
        staffSessionLocal.createStaff(s94);

        Staff s95 = new Staff("Adrienne Chye Chern Nee", "adriennechye", encryptPassword("password"), "adriennechye@krhg.com.sg", "94228920", "Female", "S7123873C", "6 Jalan Kemboja", new Date(), "Kent Ridge North West", "Housekeeping Manager", "Housekeeping", 14, true, "Chye Kim Chew", "6 Jalan Kemboja", "66454464");
        s95.addAccountRights(st2);
        staffSessionLocal.createStaff(s95);

        Staff s96 = new Staff("Lynn Tan", "lynntan", encryptPassword("password"), "lynntan@krhg.com.sg", "87401940", "Female", "S9849384J", "Blk 127 Buona Vista Field, #09-23", new Date(), "Kent Ridge East", "Front Desk Staff", "Front Desk", 7, true, "Tan Bock Chye", "Blk 127 Buona Vista Field, #09-23", "67718013");
        s96.addAccountRights(st9);
        staffSessionLocal.createStaff(s96);

        Staff s97 = new Staff("Sydnie Gafoor", "sydniegafoor", encryptPassword("password"), "sydniegafoor@krhg.com.sg", "94450056", "Female", "S7800121F", "Blk 31 Sengkang Street 21, #17-41", new Date(), "Kent Ridge Grand", "Front Desk Manager", "Front Desk", 14, true, "Gafoor Joseph", "Blk 31 Sengkang Street 21, #17-41", "67835078");
        s97.addAccountRights(st10);
        staffSessionLocal.createStaff(s97);

        Staff s98 = new Staff("Stanton Lee", "stantonlee", encryptPassword("password"), "stantonlee@krhg.com.sg", "98574752", "Male", "S2969652G", "37 Jalan Lembah Bedok", new Date(), "Kent Ridge West", "General Manager", "Front Desk", 18, true, "Lee Chin Loy", "37 Jalan Lembah Bedok", "65266377");
        s98.addAccountRights(st10);
        staffSessionLocal.createStaff(s98);

        Staff s99 = new Staff("Jackson Zhen", "jacksonzhen", encryptPassword("password"), "jacksonzhen@krhg.com.sg", "97238443", "Male", "S3987612D", "Blk 201 Lorong 3 Hougang, #10-11", new Date(), "Kent Ridge East", "General Manager", "Front Desk", 18, true, "Zhen Shao Liang", "Blk 201 Lorong 3 Hougang, #10-11", "66964374");
        s99.addAccountRights(st10);
        staffSessionLocal.createStaff(s99);

        Staff s100 = new Staff("Tiffany Teo", "tiffanyteo", encryptPassword("password"), "tiffanyteo@krhg.com.sg", "91832153", "Female", "F8656804R", "45 Serangoon Gardens Park, #18-18", new Date(), "Kent Ridge North", "Housekeeping Staff", "Housekeeping", 7, true, "Teo Mei Zhen", "45 Serangoon Gardens Park, #18-18", "64022273");
        s100.addAccountRights(st1);
        staffSessionLocal.createStaff(s100);

        Staff s101 = new Staff("Jonas Chye", "jonasc144", encryptPassword("password"), "jonaschye@krhg.com.sg", "86674988", "Male", "S2301378E", "Blk 48 Lorong 7 Boon Keng, #14-01", new Date(), "HQ", "IT Staff", "IT", 7, true, "Chye Hun Soon", "Blk 48 Lorong 7 Boon Keng, #14-01", "68997170");
        s101.addAccountRights(st7);
        staffSessionLocal.createStaff(s101);

        Staff s102 = new Staff("Victor Yong", "victoryon", encryptPassword("password"), "victoryong@krhg.com.sg", "98334463", "Male", "S7617697D", "1 Boon Keng Garden", new Date(), "HQ", "IT Manager", "IT", 14, true, "Meryvyn Yong", "1 Boon Keng Garden", "69768091");
        s102.addAccountRights(st8);
        staffSessionLocal.createStaff(s102);

        Staff s103 = new Staff("Sammy Sim Jinrong", "sammysi306", encryptPassword("password"), "sammysim@krhg.com.sg", "97581486", "Male", "S8054421F", "17 Tampines Lane", new Date(), "HQ", "IT Staff", "IT", 7, true, "Sim Iew Leak", "17 Tampines Lane", "61617202");
        s103.addAccountRights(st7);
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

        MinibarItem m2 = new MinibarItem("Chocolate", 5, 8.00);
        houseKeepingOrderSessionLocal.createMinibarItem(m2);
        m2 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Chocolate");

        MinibarItem m3 = new MinibarItem("Mineral Water", 3, 5.00);
        houseKeepingOrderSessionLocal.createMinibarItem(m3);
        m3 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Mineral Water");

        MinibarItem m4 = new MinibarItem("Coke", 3, 7.00);
        houseKeepingOrderSessionLocal.createMinibarItem(m4);
        m4 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Coke");

        MinibarItem m5 = new MinibarItem("Wine", 5, 60.00);
        houseKeepingOrderSessionLocal.createMinibarItem(m5);
        m5 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Wine");

        MinibarItem m6 = new MinibarItem("Beer", 5, 15.00);
        houseKeepingOrderSessionLocal.createMinibarItem(m6);
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

        Calendar myCalendar = new GregorianCalendar(2019, 2, 4); 
        Date d1 = myCalendar.getTime();
        Customer c1 = new Customer("Zack", "Neo Guohui", "S7511901A", encryptPassword("test"), 187, "neoguoh202@hotmail.com", "+6591321876", d1, "E2342213B", true);
        customerSessionLocal.createCustomer(c1);
        c1 = customerSessionLocal.getCustomerByPassportNum("E2342213B");

        Calendar myCalendar2 = new GregorianCalendar(2019, 1, 28);
        Date d2 = myCalendar2.getTime();
        Customer c2 = new Customer("Pillay", "Maureen", "S8273571B", encryptPassword("test"), 31877, "maureenpi1@yahoo.com.sg", "+6582375237", d2, "E5323423E", false);
        customerSessionLocal.createCustomer(c2);
        c2 = customerSessionLocal.getCustomerByPassportNum("E5323423E");

        Calendar myCalendar3 = new GregorianCalendar(2019, 3, 1);
        Date d3 = myCalendar3.getTime();
        Customer c3 = new Customer("Mei Fang", "Chia", "S1073264F", encryptPassword("test"), 1023823, "chiame317@gmail.com", "+6591312719", d3, "E2545443C", true);
        customerSessionLocal.createCustomer(c3);
        c3 = customerSessionLocal.getCustomerByPassportNum("E2545443C");

        Calendar myCalendar4 = new GregorianCalendar(2019, 1, 19); 
        Date d4 = myCalendar4.getTime();
        Customer c4 = new Customer("Randal", "Ho", "S9467910I", encryptPassword("test"), 5099, "randalh753@gmail.com", "+6593243287", d4, "E4354363D", false);
        customerSessionLocal.createCustomer(c4);
        c4 = customerSessionLocal.getCustomerByPassportNum("E4354363D");

        Calendar myCalendar5 = new GregorianCalendar(2019, 4, 5);
        Date d5 = myCalendar5.getTime();
        Customer c5 = new Customer("Lok Li", "Choo", "S9122868H", encryptPassword("test"), 38271, "loklich35@hotmail.com", "+6598768896", d5, "E3453353H", true);
        customerSessionLocal.createCustomer(c5);
        c5 = customerSessionLocal.getCustomerByPassportNum("E3453353H");

        Calendar myCalendar6 = new GregorianCalendar(2019, 2, 27);
        Date d6 = myCalendar6.getTime();
        Customer c6 = new Customer("Mervin", "Ee", "S2419077C", encryptPassword("test"), 200192, "mervinee@live.com", "+6587652376", d6, "A16436253", true);
        customerSessionLocal.createCustomer(c6);
        c6 = customerSessionLocal.getCustomerByPassportNum("A16436253");

        Calendar myCalendar7 = new GregorianCalendar(2019, 3, 6);
        Date d7 = myCalendar7.getTime();
        Customer c7 = new Customer("Galuh Jabal", "Thamrin", "F0416856U", encryptPassword("test"), 382, "thamrinjb@live.com", "+622169195347", d7, "B2342869", true);
        customerSessionLocal.createCustomer(c7);
        c7 = customerSessionLocal.getCustomerByPassportNum("B2342869");

        Calendar myCalendar8 = new GregorianCalendar(2019, 4, 14);
        Date d8 = myCalendar8.getTime();
        Customer c8 = new Customer("Bao Cong", "Chen", "F0642755M", encryptPassword("test"), 4819, "chenbc@163.com", "+8613067076921", d8, "EA2676233", true);
        customerSessionLocal.createCustomer(c8);
        c8 = customerSessionLocal.getCustomerByPassportNum("EA2676233");

        Calendar myCalendar9 = new GregorianCalendar(2019, 3, 31);
        Date d9 = myCalendar9.getTime();
        Customer c9 = new Customer("Meg Kou", "Thong", "G1797808Q", encryptPassword("test"), 2938, "thongmk@hotmail.com", "+60188617734", d9, "A23782622", true);
        customerSessionLocal.createCustomer(c9);
        c9 = customerSessionLocal.getCustomerByPassportNum("A23782622");

        Calendar myCalendar10 = new GregorianCalendar(2019, 4, 15);
        Date d10 = myCalendar10.getTime();
        Customer c10 = new Customer("Cuong Khanh", "Anh Nhiem", "G0278921W", encryptPassword("test"), 9281, "cukhanhn@gmail.com", "+84843506870", d10, "B3228963", true);
        customerSessionLocal.createCustomer(c10);
        c10 = customerSessionLocal.getCustomerByPassportNum("B3228963");

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

        Room KRGS3 = new Room("KRG_203", "203", "Standard", 2, "Available", h1);
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

        Room KRGS4 = new Room("KRG_204", "204", "Standard", 2, "Available", h1);
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

        Room KRGS8 = new Room("KRG_208", "208", "Standard", 2, "Available", h1);
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

        Room KRGS11 = new Room("KRG_301", "301", "Standard", 2, "Available", h1);
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

        Room KRGS13 = new Room("KRG_303", "303", "Standard", 2, "Available", h1);
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

        Room KRGS15 = new Room("KRG_305", "305", "Standard", 2, "Available", h1);
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

        Room KRGS16 = new Room("KRG_306", "306", "Standard", 2, "Available", h1);
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

        Room KRGS19 = new Room("KRG_309", "309", "Standard", 2, "Available", h1);
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

        Room KRGS20 = new Room("KRG_310", "310", "Standard", 2, "Available", h1);
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

        Room KRGS22 = new Room("KRG_402", "402", "Standard", 2, "Available", h1);
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

        Room KRGS24 = new Room("KRG_404", "404", "Standard", 2, "Available", h1);
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

        Room KRGS25 = new Room("KRG_405", "405", "Standard", 2, "Available", h1);
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

        Room KRGS26 = new Room("KRG_406", "406", "Standard", 2, "Available", h1);
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

        Room KRGS27 = new Room("KRG_407", "407", "Standard", 2, "Available", h1);
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

        Room KRGS28 = new Room("KRG_408", "408", "Standard", 2, "Available", h1);
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

        Room KRGS30 = new Room("KRG_410", "410", "Standard", 2, "Available", h1);
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

        Room KRGS32 = new Room("KRG_502", "502", "Standard", 2, "Available", h1);
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

        Room KRGS35 = new Room("KRG_505", "505", "Standard", 2, "Available", h1);
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

        Room KRGS37 = new Room("KRG_507", "507", "Standard", 2, "Available", h1);
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

        Room KRGS38 = new Room("KRG_508", "508", "Standard", 2, "Available", h1);
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

        Room KRGS41 = new Room("KRG_601", "601", "Standard", 2, "Available", h1);
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

        Room KRGS44 = new Room("KRG_604", "604", "Standard", 2, "Available", h1);
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

        Room KRGS46 = new Room("KRG_606", "606", "Standard", 2, "Available", h1);
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

        Room KRGS49 = new Room("KRG_609", "609", "Standard", 2, "Available", h1);
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

        Room KRG_1003 = new Room("KRG_1003", "1003", "Deluxe", 3, "Available", h1);

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

        Room KRG_1006 = new Room("KRG_1006", "1006", "Deluxe", 3, "Available", h1);

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

        Room KRG_1101 = new Room("KRG_1101", "1101", "Deluxe", 3, "Available", h1);

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

        Room KRG_1104 = new Room("KRG_1104", "1104", "Deluxe", 3, "Available", h1);

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

        Room KRG_1105 = new Room("KRG_1105", "1105", "Deluxe", 3, "Available", h1);

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

        Room KRG_702 = new Room("KRG_702", "702", "Premium", 4, "Available", h1);

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

        Room KRG_703 = new Room("KRG_703", "703", "Premium", 4, "Available", h1);

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

        Room KRG_705 = new Room("KRG_705", "705", "Premium", 4, "Available", h1);

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

        Room KRG_801 = new Room("KRG_801", "801", "Premium", 4, "Available", h1);

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

        Room KRG_802 = new Room("KRG_802", "802", "Premium", 4, "Available", h1);

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

        Room KRG_804 = new Room("KRG_804", "804", "Premium", 4, "Available", h1);

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

        Room KRG_806 = new Room("KRG_806", "806", "Premium", 4, "Available", h1);

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

        Room KRG_808 = new Room("KRG_808", "808", "Premium", 4, "Available", h1);

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

        Room KRG_903 = new Room("KRG_903", "903", "Premium", 4, "Available", h1);

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

        Room KRG_904 = new Room("KRG_904", "904", "Premium", 4, "Available", h1);

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

        Room KRG_908 = new Room("KRG_908", "908", "Premium", 4, "Available", h1);

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

        Room KRG_1202 = new Room("KRG_1202", "1202", "Suite", 4, "Available", h1);

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

        Room KRG_1203 = new Room("KRG_1203", "1203", "Suite", 4, "Available", h1);

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

        Room KRG_1205 = new Room("KRG_1205", "1205", "Suite", 4, "Available", h1);

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

        Room KRG_1301 = new Room("KRG_1301", "1301", "Suite", 4, "Available", h1);

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

        Room KRG_1304 = new Room("KRG_1304", "1304", "Suite", 4, "Available", h1);

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

        Room KRGP1 = new Room("KRG_1401", "1401", "Penthouse", 4, "Available", h1);
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

        Room KRGP4 = new Room("KRG_1404", "1404", "Penthouse", 4, "Available", h1);
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

        Room KRCS2 = new Room("KRC_202", "202", "Standard", 2, "Available", h2);
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

        Room KRCS5 = new Room("KRC_205", "205", "Standard", 2, "Available", h2);
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

        Room KRCS11 = new Room("KRC_301", "301", "Standard", 2, "Available", h2);
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

        Room KRCS12 = new Room("KRC_302", "302", "Standard", 2, "Available", h2);
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

        Room KRCS14 = new Room("KRC_304", "304", "Standard", 2, "Available", h2);
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

        Room KRCS17 = new Room("KRC_307", "307", "Standard", 2, "Available", h2);
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

        Room KRCS21 = new Room("KRC_401", "401", "Standard", 2, "Available", h2);
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

        Room KRCS25 = new Room("KRC_405", "405", "Standard", 2, "Available", h2);
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

        Room KRCS26 = new Room("KRC_406", "406", "Standard", 2, "Available", h2);
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

        Room KRCS28 = new Room("KRC_408", "408", "Standard", 2, "Available", h2);
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

        Room KRCS32 = new Room("KRC_502", "502", "Standard", 2, "Available", h2);
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

        Room KRCS34 = new Room("KRC_504", "504", "Standard", 2, "Available", h2);
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

        Room KRCS36 = new Room("KRC_506", "506", "Standard", 2, "Available", h2);
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

        Room KRCS41 = new Room("KRC_601", "601", "Standard", 2, "Available", h2);
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

        Room KRCS43 = new Room("KRC_603", "603", "Standard", 2, "Available", h2);
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

        Room KRCS45 = new Room("KRC_605", "605", "Standard", 2, "Available", h2);
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

        Room KRCS48 = new Room("KRC_608", "608", "Standard", 2, "Available", h2);
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

        Room KRC_1003 = new Room("KRC_1003", "1003", "Deluxe", 3, "Available", h2);

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

        Room KRC_1101 = new Room("KRC_1101", "1101", "Deluxe", 3, "Available", h2);

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

        Room KRC_1102 = new Room("KRC_1102", "1102", "Deluxe", 3, "Available", h2);

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

        Room KRC_1104 = new Room("KRC_1104", "1104", "Deluxe", 3, "Available", h2);

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

        Room KRC_701 = new Room("KRC_701", "701", "Premium", 4, "Available", h2);

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

        Room KRC_704 = new Room("KRC_704", "704", "Premium", 4, "Available", h2);

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

        Room KRC_705 = new Room("KRC_705", "705", "Premium", 4, "Available", h2);

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

        Room KRC_707 = new Room("KRC_707", "707", "Premium", 4, "Available", h2);

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

        Room KRC_803 = new Room("KRC_803", "803", "Premium", 4, "Available", h2);

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

        Room KRC_804 = new Room("KRC_804", "804", "Premium", 4, "Available", h2);

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

        Room KRC_806 = new Room("KRC_806", "806", "Premium", 4, "Available", h2);

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

        Room KRC_901 = new Room("KRC_901", "901", "Premium", 4, "Available", h2);

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

        Room KRC_902 = new Room("KRC_902", "902", "Premium", 4, "Available", h2);

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

        Room KRC_905 = new Room("KRC_905", "905", "Premium", 4, "Available", h2);

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

        Room KRC_1201 = new Room("KRC_1201", "1201", "Suite", 4, "Available", h2);

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

        Room KRC_1202 = new Room("KRC_1202", "1202", "Suite", 4, "Available", h2);

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

        Room KRNS1 = new Room("KRN_201", "201", "Standard", 2, "Available", h3);
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

        Room KRNS4 = new Room("KRN_204", "204", "Standard", 2, "Available", h3);
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

        Room KRNS8 = new Room("KRN_208", "208", "Standard", 2, "Available", h3);
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

        Room KRNS12 = new Room("KRN_302", "302", "Standard", 2, "Available", h3);
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

        Room KRNS13 = new Room("KRN_303", "303", "Standard", 2, "Available", h3);
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

        Room KRNS16 = new Room("KRN_306", "306", "Standard", 2, "Available", h3);
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

        Room KRNS22 = new Room("KRN_402", "402", "Standard", 2, "Available", h3);
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

        Room KRNS23 = new Room("KRN_403", "403", "Standard", 2, "Available", h3);
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

        Room KRNS27 = new Room("KRN_407", "407", "Standard", 2, "Available", h3);
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

        Room KRNS32 = new Room("KRN_502", "502", "Standard", 2, "Available", h3);
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

        Room KRNS36 = new Room("KRN_506", "506", "Standard", 2, "Available", h3);
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

        Room KRNS42 = new Room("KRN_602", "602", "Standard", 2, "Available", h3);
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

        Room KRNS45 = new Room("KRN_605", "605", "Standard", 2, "Available", h3);
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

        Room KRNS48 = new Room("KRN_608", "608", "Standard", 2, "Available", h3);
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

        Room KRN_1003 = new Room("KRN_1003", "1003", "Deluxe", 3, "Available", h3);

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

        Room KRN_1004 = new Room("KRN_1004", "1004", "Deluxe", 3, "Available", h3);

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

        Room KRN_1102 = new Room("KRN_1102", "1102", "Deluxe", 3, "Available", h3);

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

        Room KRN_1105 = new Room("KRN_1105", "1105", "Deluxe", 3, "Available", h3);

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

        Room KRN_701 = new Room("KRN_701", "701", "Premium", 4, "Available", h3);

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

        Room KRN_703 = new Room("KRN_703", "703", "Premium", 4, "Available", h3);

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

        Room KRN_706 = new Room("KRN_706", "706", "Premium", 4, "Available", h3);

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

        Room KRN_802 = new Room("KRN_802", "802", "Premium", 4, "Available", h3);

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

        Room KRN_805 = new Room("KRN_805", "805", "Premium", 4, "Available", h3);

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

        Room KRN_807 = new Room("KRN_807", "807", "Premium", 4, "Available", h3);

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

        Room KRN_901 = new Room("KRN_901", "901", "Premium", 4, "Available", h3);

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

        Room KRN_903 = new Room("KRN_903", "903", "Premium", 4, "Available", h3);

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

        Room KRN_906 = new Room("KRN_906", "906", "Premium", 4, "Available", h3);

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

        Room KRN_1201 = new Room("KRN_1201", "1201", "Suite", 4, "Available", h3);

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

        Room KRN_1204 = new Room("KRN_1204", "1204", "Suite", 4, "Available", h3);

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

        Room KRSS3 = new Room("KRS_203", "203", "Standard", 2, "Available", h4);
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

        Room KRSS6 = new Room("KRS_206", "206", "Standard", 2, "Available", h4);
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

        Room KRSS8 = new Room("KRS_208", "208", "Standard", 2, "Available", h4);
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

        Room KRSS12 = new Room("KRS_302", "302", "Standard", 2, "Available", h4);
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

        Room KRSS14 = new Room("KRS_304", "304", "Standard", 2, "Available", h4);
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

        Room KRSS18 = new Room("KRS_308", "308", "Standard", 2, "Available", h4);
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

        Room KRSS22 = new Room("KRS_402", "402", "Standard", 2, "Available", h4);
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

        Room KRSS23 = new Room("KRS_403", "403", "Standard", 2, "Available", h4);
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

        Room KRSS26 = new Room("KRS_406", "406", "Standard", 2, "Available", h4);
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

        Room KRSS31 = new Room("KRS_501", "501", "Standard", 2, "Available", h4);
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

        Room KRSS32 = new Room("KRS_502", "502", "Standard", 2, "Available", h4);
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

        Room KRSS35 = new Room("KRS_505", "505", "Standard", 2, "Available", h4);
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

        Room KRSS38 = new Room("KRS_508", "508", "Standard", 2, "Available", h4);
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

        Room KRSS41 = new Room("KRS_601", "601", "Standard", 2, "Available", h4);
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

        Room KRSS43 = new Room("KRS_603", "603", "Standard", 2, "Available", h4);
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

        Room KRSS45 = new Room("KRS_605", "605", "Standard", 2, "Available", h4);
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

        Room KRSS48 = new Room("KRS_608", "608", "Standard", 2, "Available", h4);
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

        Room KRS_1001 = new Room("KRS_1001", "1001", "Deluxe", 3, "Available", h4);

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

        Room KRS_1003 = new Room("KRS_1003", "1003", "Deluxe", 3, "Available", h4);

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

        Room KRS_1101 = new Room("KRS_1101", "1101", "Deluxe", 3, "Available", h4);

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

        Room KRS_1102 = new Room("KRS_1102", "1102", "Deluxe", 3, "Available", h4);

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

        Room KRS_1104 = new Room("KRS_1104", "1104", "Deluxe", 3, "Available", h4);

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

        Room KRS_701 = new Room("KRS_701", "701", "Premium", 4, "Available", h4);

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

        Room KRS_702 = new Room("KRS_702", "702", "Premium", 4, "Available", h4);

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

        Room KRS_705 = new Room("KRS_705", "705", "Premium", 4, "Available", h4);

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

        Room KRS_801 = new Room("KRS_801", "801", "Premium", 4, "Available", h4);

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

        Room KRS_802 = new Room("KRS_802", "802", "Premium", 4, "Available", h4);

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

        Room KRS_806 = new Room("KRS_806", "806", "Premium", 4, "Available", h4);

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

        Room KRS_902 = new Room("KRS_902", "902", "Premium", 4, "Available", h4);

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

        Room KRS_904 = new Room("KRS_904", "904", "Premium", 4, "Available", h4);

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

        Room KRS_906 = new Room("KRS_906", "906", "Premium", 4, "Available", h4);

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

        Room KRS_907 = new Room("KRS_907", "907", "Premium", 4, "Available", h4);

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

        Room KRS_1202 = new Room("KRS_1202", "1202", "Suite", 4, "Available", h4);

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

        Room KRS_1204 = new Room("KRS_1204", "1204", "Suite", 4, "Available", h4);

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

        Room KRES1 = new Room("KRE_201", "201", "Standard", 2, "Available", h5);
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

        Room KRES3 = new Room("KRE_203", "203", "Standard", 2, "Available", h5);
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

        Room KRES6 = new Room("KRE_206", "206", "Standard", 2, "Available", h5);
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

        Room KRES7 = new Room("KRE_207", "207", "Standard", 2, "Available", h5);
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

        Room KRES11 = new Room("KRE_301", "301", "Standard", 2, "Available", h5);
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

        Room KRES14 = new Room("KRE_304", "304", "Standard", 2, "Available", h5);
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

        Room KRES17 = new Room("KRE_307", "307", "Standard", 2, "Available", h5);
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

        Room KRES18 = new Room("KRE_308", "308", "Standard", 2, "Available", h5);
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

        Room KRES23 = new Room("KRE_403", "403", "Standard", 2, "Available", h5);
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

        Room KRES26 = new Room("KRE_406", "406", "Standard", 2, "Available", h5);
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

        Room KRES31 = new Room("KRE_501", "501", "Standard", 2, "Available", h5);
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

        Room KRES34 = new Room("KRE_504", "504", "Standard", 2, "Available", h5);
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

        Room KRES38 = new Room("KRE_508", "508", "Standard", 2, "Available", h5);
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

        Room KRES41 = new Room("KRE_601", "601", "Standard", 2, "Available", h5);
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

        Room KRES45 = new Room("KRE_605", "605", "Standard", 2, "Available", h5);
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

        Room KRES47 = new Room("KRE_607", "607", "Standard", 2, "Available", h5);
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

        Room KRE_1201 = new Room("KRE_1201", "1201", "Suite", 4, "Available", h5);

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

        Room KRE_1204 = new Room("KRE_1204", "1204", "Suite", 4, "Available", h5);

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

        Room KRWS3 = new Room("KRW_203", "203", "Standard", 2, "Available", h6);
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

        Room KRWS5 = new Room("KRW_205", "205", "Standard", 2, "Available", h6);
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

        Room KRWS11 = new Room("KRW_301", "301", "Standard", 2, "Available", h6);
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

        Room KRWS12 = new Room("KRW_302", "302", "Standard", 2, "Available", h6);
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

        Room KRWS15 = new Room("KRW_305", "305", "Standard", 2, "Available", h6);
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

        Room KRWS21 = new Room("KRW_401", "401", "Standard", 2, "Available", h6);
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

        Room KRWS23 = new Room("KRW_403", "403", "Standard", 2, "Available", h6);
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

        Room KRWS24 = new Room("KRW_404", "404", "Standard", 2, "Available", h6);
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

        Room KRWS26 = new Room("KRW_406", "406", "Standard", 2, "Available", h6);
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

        Room KRWS28 = new Room("KRW_408", "408", "Standard", 2, "Available", h6);
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

        Room KRWS32 = new Room("KRW_502", "502", "Standard", 2, "Available", h6);
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

        Room KRWS34 = new Room("KRW_504", "504", "Standard", 2, "Available", h6);
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

        Room KRWS38 = new Room("KRW_508", "508", "Standard", 2, "Available", h6);
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

        Room KRWS43 = new Room("KRW_603", "603", "Standard", 2, "Available", h6);
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

        Room KRWS44 = new Room("KRW_604", "604", "Standard", 2, "Available", h6);
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

        Room KRWS48 = new Room("KRW_608", "608", "Standard", 2, "Available", h6);
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

        Room KRW_1004 = new Room("KRW_1004", "1004", "Deluxe", 3, "Available", h6);

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

        Room KRW_1102 = new Room("KRW_1102", "1102", "Deluxe", 3, "Available", h6);

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

        Room KRW_1105 = new Room("KRW_1105", "1105", "Deluxe", 3, "Available", h6);

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

        Room KRW_702 = new Room("KRW_702", "702", "Premium", 4, "Available", h6);

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

        Room KRW_703 = new Room("KRW_703", "703", "Premium", 4, "Available", h6);

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

        Room KRW_707 = new Room("KRW_707", "707", "Premium", 4, "Available", h6);

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

        Room KRW_801 = new Room("KRW_801", "801", "Premium", 4, "Available", h6);

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

        Room KRW_804 = new Room("KRW_804", "804", "Premium", 4, "Available", h6);

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

        Room KRW_806 = new Room("KRW_806", "806", "Premium", 4, "Available", h6);

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

        Room KRW_903 = new Room("KRW_903", "903", "Premium", 4, "Available", h6);

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

        Room KRW_905 = new Room("KRW_905", "905", "Premium", 4, "Available", h6);

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

        Room KRW_906 = new Room("KRW_906", "906", "Premium", 4, "Available", h6);

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

        Room KRW_1202 = new Room("KRW_1202", "1202", "Suite", 4, "Available", h6);

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

        Room KRNES2 = new Room("KRNE_202", "202", "Standard", 2, "Available", h7);
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

        Room KRNES3 = new Room("KRNE_203", "203", "Standard", 2, "Available", h7);
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

        Room KRNES6 = new Room("KRNE_206", "206", "Standard", 2, "Available", h7);
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

        Room KRNES13 = new Room("KRNE_303", "303", "Standard", 2, "Available", h7);
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

        Room KRNES16 = new Room("KRNE_306", "306", "Standard", 2, "Available", h7);
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

        Room KRNES24 = new Room("KRNE_404", "404", "Standard", 2, "Available", h7);
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

        Room KRNES31 = new Room("KRNE_501", "501", "Standard", 2, "Available", h7);
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

        Room KRNES34 = new Room("KRNE_504", "504", "Standard", 2, "Available", h7);
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

        Room KRNES35 = new Room("KRNE_505", "505", "Standard", 2, "Available", h7);
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

        Room KRNES42 = new Room("KRNE_602", "602", "Standard", 2, "Available", h7);
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

        Room KRNES45 = new Room("KRNE_605", "605", "Standard", 2, "Available", h7);
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

        Room KRNE_1003 = new Room("KRNE_1003", "1003", "Deluxe", 3, "Available", h7);

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

        Room KRNE_1101 = new Room("KRNE_1101", "1101", "Deluxe", 3, "Available", h7);

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

        Room KRNE_1102 = new Room("KRNE_1102", "1102", "Deluxe", 3, "Available", h7);

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

        Room KRNE_704 = new Room("KRNE_704", "704", "Premium", 4, "Available", h7);

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

        Room KRNE_802 = new Room("KRNE_802", "802", "Premium", 4, "Available", h7);

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

        Room KRNE_803 = new Room("KRNE_803", "803", "Premium", 4, "Available", h7);

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

        Room KRNE_902 = new Room("KRNE_902", "902", "Premium", 4, "Available", h7);

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

        Room KRNE_904 = new Room("KRNE_904", "904", "Premium", 4, "Available", h7);

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

        Room KRNE_1201 = new Room("KRNE_1201", "1201", "Suite", 4, "Available", h7);

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

        Room KRNWS3 = new Room("KRNW_203", "203", "Standard", 2, "Available", h8);
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

        Room KRNWS5 = new Room("KRNW_205", "205", "Standard", 2, "Available", h8);
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

        Room KRNWS11 = new Room("KRNW_301", "301", "Standard", 2, "Available", h8);
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

        Room KRNWS14 = new Room("KRNW_304", "304", "Standard", 2, "Available", h8);
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

        Room KRNWS22 = new Room("KRNW_402", "402", "Standard", 2, "Available", h8);
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

        Room KRNWS23 = new Room("KRNW_403", "403", "Standard", 2, "Available", h8);
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

        Room KRNWS31 = new Room("KRNW_501", "501", "Standard", 2, "Available", h8);
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

        Room KRNWS35 = new Room("KRNW_505", "505", "Standard", 2, "Available", h8);
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

        Room KRNWS36 = new Room("KRNW_506", "506", "Standard", 2, "Available", h8);
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

        Room KRNWS44 = new Room("KRNW_604", "604", "Standard", 2, "Available", h8);
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

        Room KRNW_1001 = new Room("KRNW_1001", "1001", "Deluxe", 3, "Available", h8);

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

        Room KRNW_1101 = new Room("KRNW_1101", "1101", "Deluxe", 3, "Available", h8);

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

        Room KRNW_701 = new Room("KRNW_701", "701", "Premium", 4, "Available", h8);

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

        Room KRNW_703 = new Room("KRNW_703", "703", "Premium", 4, "Available", h8);

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

        Room KRNW_802 = new Room("KRNW_802", "802", "Premium", 4, "Available", h8);

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

        Room KRNW_803 = new Room("KRNW_803", "803", "Premium", 4, "Available", h8);

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

        Room KRNW_902 = new Room("KRNW_902", "902", "Premium", 4, "Available", h8);

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

        Room KRNW_903 = new Room("KRNW_903", "903", "Premium", 4, "Available", h8);

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

        Room KRNW_1201 = new Room("KRNW_1201", "1201", "Suite", 4, "Available", h8);

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

        Room KRSES2 = new Room("KRSE_202", "202", "Standard", 2, "Available", h9);
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

        Room KRSES5 = new Room("KRSE_205", "205", "Standard", 2, "Available", h9);
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

        Room KRSES12 = new Room("KRSE_302", "302", "Standard", 2, "Available", h9);
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

        Room KRSES21 = new Room("KRSE_401", "401", "Standard", 2, "Available", h9);
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

        Room KRSES31 = new Room("KRSE_501", "501", "Standard", 2, "Available", h9);
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

        Room KRSES33 = new Room("KRSE_503", "503", "Standard", 2, "Available", h9);
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

        Room KRSES41 = new Room("KRSE_601", "601", "Standard", 2, "Available", h9);
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

        Room KRSES45 = new Room("KRSE_605", "605", "Standard", 2, "Available", h9);
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

        Room KRSE_1101 = new Room("KRSE_1101", "1101", "Deluxe", 3, "Available", h9);

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

        Room KRSE_704 = new Room("KRSE_704", "704", "Premium", 4, "Available", h9);

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

        Room KRSE_804 = new Room("KRSE_804", "804", "Premium", 4, "Available", h9);

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

        Room KRSE_901 = new Room("KRSE_901", "901", "Premium", 4, "Available", h9);

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

        Room KRSE_1201 = new Room("KRSE_1201", "1201", "Suite", 4, "Available", h9);

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

        Room KRSWS5 = new Room("KRSW_205", "205", "Standard", 2, "Available", h10);
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

        Room KRSWS21 = new Room("KRSW_401", "401", "Standard", 2, "Available", h10);
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

        Room KRSWS24 = new Room("KRSW_404", "404", "Standard", 2, "Available", h10);
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

        Room KRSWS31 = new Room("KRSW_501", "501", "Standard", 2, "Available", h10);
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

        Room KRSWS43 = new Room("KRSW_603", "603", "Standard", 2, "Available", h10);
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

        Room KRSW_1003 = new Room("KRSW_1003", "1003", "Deluxe", 3, "Available", h10);

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

        Room KRSW_1101 = new Room("KRSW_1101", "1101", "Deluxe", 3, "Available", h10);

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

        Room KRSW_703 = new Room("KRSW_703", "703", "Premium", 4, "Available", h10);

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

        Room KRSW_804 = new Room("KRSW_804", "804", "Premium", 4, "Available", h10);

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

        Room KRSW_901 = new Room("KRSW_901", "901", "Premium", 4, "Available", h10);

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

        Room KRSW_1201 = new Room("KRSW_1201", "1201", "Suite", 4, "Available", h10);

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

        Room KRSW_1202 = new Room("KRSW_1202", "1202", "Suite", 4, "Available", h10);

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

        HouseKeepingOrder ho1 = new HouseKeepingOrder(roomSessionLocal.getRoomByName("KRG_401"), "incomplete", 4, new Date(), new Date(), null, "Toothpaste and hairnet", "toiletries");
        HouseKeepingOrder ho2 = new HouseKeepingOrder(roomSessionLocal.getRoomByName("KRG_402"), "incomplete", 4, new Date(), new Date(), null, "Spoilt TV", "maintenance");
        HouseKeepingOrder ho3 = new HouseKeepingOrder(roomSessionLocal.getRoomByName("KRG_405"), "incomplete", 4, new Date(), new Date(), null, "Spilled drinks", "housekeeping");
        HouseKeepingOrder ho4 = new HouseKeepingOrder(roomSessionLocal.getRoomByName("KRG_406"), "incomplete", 4, new Date(), new Date(), null, "Dusty table", "housekeeping");
        HouseKeepingOrder ho5 = new HouseKeepingOrder(roomSessionLocal.getRoomByName("KRG_402"), "incomplete", 4, new Date(), new Date(), null, "Tv not working", "maintenance");
        HouseKeepingOrder ho6 = new HouseKeepingOrder(roomSessionLocal.getRoomByName("KRG_403"), "incomplete", 4, new Date(), new Date(), null, "1 pants & 1 shirt", "laundry");
        HouseKeepingOrder ho7 = new HouseKeepingOrder(roomSessionLocal.getRoomByName("KRG_409"), "incomplete", 4, new Date(), new Date(), null, "5 pants & 5 shirts", "laundry");
        HouseKeepingOrder ho8 = new HouseKeepingOrder(roomSessionLocal.getRoomByName("KRG_408"), "incomplete", 4, new Date(), new Date(), null, "Mineral water refill", "housekeeping");
        HouseKeepingOrder ho9 = new HouseKeepingOrder(roomSessionLocal.getRoomByName("KRG_407"), "incomplete", 4, new Date(), new Date(), null, "Clogged Sink", "maintenance");

        houseKeepingOrderSessionLocal.createHouseKeepingOrder(ho1);
        houseKeepingOrderSessionLocal.createHouseKeepingOrder(ho2);
        houseKeepingOrderSessionLocal.createHouseKeepingOrder(ho3);
        houseKeepingOrderSessionLocal.createHouseKeepingOrder(ho4);
        houseKeepingOrderSessionLocal.createHouseKeepingOrder(ho5);
        houseKeepingOrderSessionLocal.createHouseKeepingOrder(ho6);
        houseKeepingOrderSessionLocal.createHouseKeepingOrder(ho7);
        houseKeepingOrderSessionLocal.createHouseKeepingOrder(ho8);
        houseKeepingOrderSessionLocal.createHouseKeepingOrder(ho9);
        em.flush();
    }

    public void intializeRoomBookingsAndCustomer() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Customer customer1 = new Customer();
        customer1.setAccountStatus(true);
        customer1.setDateJoined(new Date());
        customer1.setEmail("Congx2@hotmail.com");
        customer1.setMember(true);
        customer1.setMobileNum("94308808");
        customer1.setFirstName("Lim");
        customer1.setLastName("Dian Cong");
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
        customer3.setPassword(encryptPassword("1234"));
        customer3.setPoints(1);

        customerSessionLocal.createCustomer(customer3);
        em.flush();

        CreditCard creditCard1 = new CreditCard();
        creditCard1.setCardNum(encryptPassword("1234123412341234"));
        creditCard1.setCvv(encryptPassword("123"));
        creditCard1.setExpiryDate(format.parse("2021-04-01"));

        Date date = format.parse("2019-04-01");

        RoomBooking rm1 = new RoomBooking();
        rm1.setBookInDate(new Date());
        rm1.setBookOutDate(date);
        rm1.setBookedBy(customer1);
        rm1.setCreditCard(null);
        rm1.setEmailAddress(customer1.getEmail());
        rm1.setHasTransport(false);
        rm1.setName(customer1.getLastName());
        rm1.setPassportNum("A0173719Y");
        rm1.setPrice(2000.0);
        rm1.setRoomType("Standard");
        rm1.setStatus("Incomplete");

        bookingSessionLocal.createRoomBooking(rm1);
        em.flush();

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
