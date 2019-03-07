/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Hotel;
import entity.HotelFacility;
import entity.MinibarItem;
import entity.Room;
import entity.RoomFacility;
import entity.Staff;
import entity.StaffType;
import error.NoResultException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public DataInitializationSessionBean() {
    }

    @PostConstruct
    public void postConstruct() {
        if (em.find(Hotel.class, 1l) == null) {
            try {
                initializeData();
            } catch (NoResultException ex) {

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
        HotelFacility hf1 = new HotelFacility("Restaurant", "Treat yourself to a tempting array of local and international culinary delights at KRHG Restaurant.", ""); //restaurant.png
        hotelFacilitySessionLocal.createHotelFacility(hf1);
        HotelFacility restaurant = hotelFacilitySessionLocal.getAllHotelFacilities().get(0);

        HotelFacility hf2 = new HotelFacility("Gym", "Discover the fitness centre located in our hotels which features the latest gym equipment", "");//gym.png
        hotelFacilitySessionLocal.createHotelFacility(hf2);
        HotelFacility gym = hotelFacilitySessionLocal.getAllHotelFacilities().get(1);

        HotelFacility hf3 = new HotelFacility("Spa & Massage", "Treat yourself to a relaxing experience at our hotel spa.", "");//spa.png
        hotelFacilitySessionLocal.createHotelFacility(hf3);
        HotelFacility spa = hotelFacilitySessionLocal.getAllHotelFacilities().get(2);

        HotelFacility hf4 = new HotelFacility("Swimming Pool", "An outdoor swimming pool and a children’s pool are available at our hotel.", "");//swimmingPool.png
        hotelFacilitySessionLocal.createHotelFacility(hf4);
        HotelFacility swimming = hotelFacilitySessionLocal.getAllHotelFacilities().get(3);

        HotelFacility hf5 = new HotelFacility("Recreational Room", "Relax and have fun in our recreational room which consists of 8-ball Pool Table, Arcade Machines, Gaming console and more!", "");//recreationalRoom.png
        hotelFacilitySessionLocal.createHotelFacility(hf5);
        HotelFacility recreational = hotelFacilitySessionLocal.getAllHotelFacilities().get(4);

        HotelFacility hf6 = new HotelFacility("Function Room", "Host successful events at our meeting rooms in KRHG.", "");//functionRoom.png
        hotelFacilitySessionLocal.createHotelFacility(hf6);
        HotelFacility functionroom = hotelFacilitySessionLocal.getAllHotelFacilities().get(5);

        HotelFacility hf7 = new HotelFacility("Concierge  Service", "We offer luxus concierge services that will make your stay at our luxury hotel in KRHG unforgettable", "");//concierge.png
        hotelFacilitySessionLocal.createHotelFacility(hf7);
        HotelFacility concierge = hotelFacilitySessionLocal.getAllHotelFacilities().get(6);

        HotelFacility hf8 = new HotelFacility("Medical Facility", "We have 24/7 medical centre with professional doctors and nurse to assist you your medical needs.", "");//medical.png
        hotelFacilitySessionLocal.createHotelFacility(hf8);
        HotelFacility medical = hotelFacilitySessionLocal.getAllHotelFacilities().get(7);

        HotelFacility hf9 = new HotelFacility("Gift Shops", "Every souvenir from our Gift Shop holds a story of your unique experiences enjoyed here with us.", "");//giftShop.png
        hotelFacilitySessionLocal.createHotelFacility(hf9);
        HotelFacility gift = hotelFacilitySessionLocal.getAllHotelFacilities().get(8);

        HotelFacility hf10 = new HotelFacility("Convenience Store", "Selling basic items such as eggs, milk and bread, to today’s one-stop-shop convenient solution, offering a wide variety of products, fresh food, and services.", ""); //convenienceStore.png
        hotelFacilitySessionLocal.createHotelFacility(hf10);
        HotelFacility convenience = hotelFacilitySessionLocal.getAllHotelFacilities().get(9);

        HotelFacility hf11 = new HotelFacility("Valet Parking", "We have valets to help you park your vehicle in our complimentary carpark exclusively for guests!", "");//valet.png
        hotelFacilitySessionLocal.createHotelFacility(hf11);
        HotelFacility valet = hotelFacilitySessionLocal.getAllHotelFacilities().get(10);

        HotelFacility hf12 = new HotelFacility("Bar and Lounge", "KRHG Bar is a good place to meet your friends, take a pit-stop between excursions, or just relax in the lounge", "");//bar.png
        hotelFacilitySessionLocal.createHotelFacility(hf12);
        HotelFacility bar = hotelFacilitySessionLocal.getAllHotelFacilities().get(11);

        HotelFacility hf13 = new HotelFacility("Baby-Friendly Playground", "We have an inddor playground which is toddler-friendly to let your child run wild and have fun!", "");//playground.png
        hotelFacilitySessionLocal.createHotelFacility(hf13);
        HotelFacility playground = hotelFacilitySessionLocal.getAllHotelFacilities().get(12);

        HotelFacility hf14 = new HotelFacility("Casino", "The KRHG casino offers a choice of table games to suit all players. There are more than 20 games played at the gaming tables which are available.", "");//casino.png
        hotelFacilitySessionLocal.createHotelFacility(hf14);
        HotelFacility casino = hotelFacilitySessionLocal.getAllHotelFacilities().get(13);

//*********************************************HOTEL************************************************
        Hotel h1 = new Hotel("Kent Ridge Grand", "KRG", "63 Somerset Rd Singapore 238163", 5, "6123 1000");
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

        Hotel h2 = new Hotel("Kent Ridge Central", "KRC", "33 Ang Mo Kio Ave 3 Singapore 569933", 4, "6123 1100");
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

        Hotel h3 = new Hotel("Kent Ridge North", "KRN", "930 Yishun Ave 2 Singapore 769098", 4, "6123 1200");
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

        Hotel h4 = new Hotel("Kent Ridge South", "KRS", "39 Bayfront Ave Singapore 118956", 4, "6123 1300");
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

        Hotel h5 = new Hotel("Kent Ridge East", "KRE", "5 Pasir Ris Cl Singapore 519599", 4, "6123 1400");
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

        Hotel h6 = new Hotel("Kent Ridge West", "KRW", "6 Jurong East Central Singapore 609731", 4, "6123 1500");
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

        Hotel h7 = new Hotel("Kent Ridge North East", "KRNE", "78 Buangkok View Singapore 534191", 3, "6123 1600");
        h7.addHotelFacility(restaurant);
        h7.addHotelFacility(gym);
        h7.addHotelFacility(spa);
        h7.addHotelFacility(swimming);
        h7.addHotelFacility(recreational);
        h7.addHotelFacility(functionroom);
        hotelSessionLocal.createHotel(h7);
        h7 = hotelSessionLocal.getHotelByName("Kent Ridge North East");

        Hotel h8 = new Hotel("Kent Ridge North West", "KRNW", "21 Choa Chu Kang Ave 4 Singapore 689812", 3, "6123 1700");
        h8.addHotelFacility(restaurant);
        h8.addHotelFacility(gym);
        h8.addHotelFacility(spa);
        h8.addHotelFacility(swimming);
        h8.addHotelFacility(recreational);
        h8.addHotelFacility(functionroom);
        hotelSessionLocal.createHotel(h8);
        h8 = hotelSessionLocal.getHotelByName("Kent Ridge North West");

        Hotel h9 = new Hotel("Kent Ridge South East", "KRSE", "80 Marine Parade Rd Singapore 449269", 3, "6123 1800");
        h9.addHotelFacility(restaurant);
        h9.addHotelFacility(gym);
        h9.addHotelFacility(spa);
        h9.addHotelFacility(swimming);
        h9.addHotelFacility(recreational);
        h9.addHotelFacility(functionroom);
        hotelSessionLocal.createHotel(h9);
        h9 = hotelSessionLocal.getHotelByName("Kent Ridge South East");

        Hotel h10 = new Hotel("Kent Ridge South West", "KRSW", "36 Lower Kent Ridge Road Singapore 119077", 3, "6123 1900");
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

        //MinibarItem m6 = new MinibarItem("Beer", 5, 15.00);
        //houseKeepingOrderSessionLocal.createMinibarItem(m6);
        //m6 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Beer");
//*********************************************ROOM FACILIY************************************************
        RoomFacility rf1 = new RoomFacility("Wifi", "Communication", "");//wifi.png
        roomFacilitySessionLocal.createRoomFacility(rf1);
        rf1 = roomFacilitySessionLocal.getRoomFacilityByName("Wifi");

        RoomFacility rf2 = new RoomFacility("Minibar", "Refreshment and Dining", "");//miniBar.png
        roomFacilitySessionLocal.createRoomFacility(rf2);
        rf2 = roomFacilitySessionLocal.getRoomFacilityByName("Minibar");

        RoomFacility rf3 = new RoomFacility("Aircon", "Others", "");//aircon.png
        roomFacilitySessionLocal.createRoomFacility(rf3);
        rf3 = roomFacilitySessionLocal.getRoomFacilityByName("Aircon");

        RoomFacility rf4 = new RoomFacility("Cable TV", "Entertainment", "");//tv.png
        roomFacilitySessionLocal.createRoomFacility(rf4);
        rf4 = roomFacilitySessionLocal.getRoomFacilityByName("Cable TV");

        RoomFacility rf5 = new RoomFacility("Telephone", "Communication", "");//telephone.png
        roomFacilitySessionLocal.createRoomFacility(rf5);
        rf5 = roomFacilitySessionLocal.getRoomFacilityByName("Telephone");

        RoomFacility rf6 = new RoomFacility("Water Heater", "Bathroom", "");//waterHeater.png
        roomFacilitySessionLocal.createRoomFacility(rf6);
        rf6 = roomFacilitySessionLocal.getRoomFacilityByName("Water Heater");

        RoomFacility rf7 = new RoomFacility("Bathroom with Shower Head", "Bathroom", "");//shower.png
        roomFacilitySessionLocal.createRoomFacility(rf7);
        rf7 = roomFacilitySessionLocal.getRoomFacilityByName("Bathroom with Shower Head");

        RoomFacility rf8 = new RoomFacility("HairDryer", "Bathroom", "");//hairdryer.png
        roomFacilitySessionLocal.createRoomFacility(rf8);
        rf8 = roomFacilitySessionLocal.getRoomFacilityByName("HairDryer");

        RoomFacility rf9 = new RoomFacility("Safe Deposit Box", "Others", "");//safe.png
        roomFacilitySessionLocal.createRoomFacility(rf9);
        rf9 = roomFacilitySessionLocal.getRoomFacilityByName("Safe Deposit Box");

        RoomFacility rf10 = new RoomFacility("Water Kettle with complimentary teas/coffee", "Refreshment and Dining", "");//coffeeTea.png
        roomFacilitySessionLocal.createRoomFacility(rf10);
        rf10 = roomFacilitySessionLocal.getRoomFacilityByName("Water Kettle with complimentary teas/coffee");

        RoomFacility rf11 = new RoomFacility("Room with Windows", "Others", "");//windows.png
        roomFacilitySessionLocal.createRoomFacility(rf11);
        rf11 = roomFacilitySessionLocal.getRoomFacilityByName("Room with Windows");

        RoomFacility rf12 = new RoomFacility("Bathtub", "Bathroom", "");//baththub.png
        roomFacilitySessionLocal.createRoomFacility(rf12);
        rf12 = roomFacilitySessionLocal.getRoomFacilityByName("Bathtub");

        RoomFacility rf13 = new RoomFacility("Mini Fridge", "Refreshment and Dining", "");//miniFridge.png
        roomFacilitySessionLocal.createRoomFacility(rf13);
        rf13 = roomFacilitySessionLocal.getRoomFacilityByName("Mini Fridge");

        RoomFacility rf14 = new RoomFacility("Coffee Machine", "Refreshment and Dining", "");//coffeeMachine.png
        roomFacilitySessionLocal.createRoomFacility(rf14);
        rf14 = roomFacilitySessionLocal.getRoomFacilityByName("Coffee Machine");

        RoomFacility rf15 = new RoomFacility("Balcony", "Others", "");//balcony.png
        roomFacilitySessionLocal.createRoomFacility(rf15);
        rf15 = roomFacilitySessionLocal.getRoomFacilityByName("Balcony");

        RoomFacility rf16 = new RoomFacility("Microwave", "Refreshment and Dining", "");//microwave.png
        roomFacilitySessionLocal.createRoomFacility(rf16);
        rf16 = roomFacilitySessionLocal.getRoomFacilityByName("Microwave");

        RoomFacility rf17 = new RoomFacility("Gaming Console", "Entertainment", "");//gaming.png
        roomFacilitySessionLocal.createRoomFacility(rf17);
        rf17 = roomFacilitySessionLocal.getRoomFacilityByName("Gaming Console");

        RoomFacility rf18 = new RoomFacility("Entertainment System", "Entertainment", "");//entertainmentSystem.png
        roomFacilitySessionLocal.createRoomFacility(rf18);
        rf18 = roomFacilitySessionLocal.getRoomFacilityByName("Entertainment System");

        RoomFacility rf19 = new RoomFacility("Blackout Curtains", "Others", "");//curtain.png
        roomFacilitySessionLocal.createRoomFacility(rf19);
        rf19 = roomFacilitySessionLocal.getRoomFacilityByName("Blackout Curtains");

        RoomFacility rf20 = new RoomFacility("High Ceiling", "Others", "");//highCeiling.png
        roomFacilitySessionLocal.createRoomFacility(rf20);
        rf20 = roomFacilitySessionLocal.getRoomFacilityByName("High Ceiling");

        RoomFacility rf21 = new RoomFacility("Jaccuzi", "Bathroom", "");//jacuzzi.png
        roomFacilitySessionLocal.createRoomFacility(rf21);
        rf21 = roomFacilitySessionLocal.getRoomFacilityByName("Jaccuzi");

        RoomFacility rf22 = new RoomFacility("Kitchen", "Refreshment and Dining", "");//kitchen.png
        roomFacilitySessionLocal.createRoomFacility(rf22);
        rf22 = roomFacilitySessionLocal.getRoomFacilityByName("Kitchen");

//*********************************************ROOM************************************************
//KR GRAND
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
        KRG_1201.addRoomFacility(rf12);
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
        KRG_1202.addRoomFacility(rf12);
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
        KRG_1203.addRoomFacility(rf12);
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
        KRG_1204.addRoomFacility(rf12);
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
        KRG_1205.addRoomFacility(rf12);
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
        KRG_1301.addRoomFacility(rf12);
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
        KRG_1302.addRoomFacility(rf12);
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
        KRG_1303.addRoomFacility(rf12);
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
        KRG_1304.addRoomFacility(rf12);
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
        KRG_1305.addRoomFacility(rf12);
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
        h1.addRoom(roomSessionLocal.getRoomByName("KRHG_1403"));

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
        h1.addRoom(roomSessionLocal.getRoomByName("KRHG_1404"));

//KR Central
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
        KRC_1201.addRoomFacility(rf12);
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
        KRC_1202.addRoomFacility(rf12);
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

//KR North
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
        KRN_1201.addRoomFacility(rf12);
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
        KRN_1202.addRoomFacility(rf12);
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

//KR South
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
        KRS_1201.addRoomFacility(rf12);
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
        KRS_1202.addRoomFacility(rf12);
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

//KR East
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
        KRE_1201.addRoomFacility(rf12);
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
        KRE_1202.addRoomFacility(rf12);
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

//KR West
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
        KRW_1201.addRoomFacility(rf12);
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
        KRW_1202.addRoomFacility(rf12);
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

//KR North East
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
        KRNE_1201.addRoomFacility(rf12);
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
        KRNE_1202.addRoomFacility(rf12);
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

//KR North West
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
        KRNW_1201.addRoomFacility(rf12);
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
        KRNW_1202.addRoomFacility(rf12);
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

//KR South East
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
        KRSE_1201.addRoomFacility(rf12);
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
        KRSE_1202.addRoomFacility(rf12);
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

//KR South West
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
        KRSW_1201.addRoomFacility(rf12);
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
        KRSW_1202.addRoomFacility(rf12);
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
