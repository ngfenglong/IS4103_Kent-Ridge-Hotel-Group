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

        //*********************************************Staff************************************************
        Staff s0 = new Staff("Test User", "test", encryptPassword("test"), "test@krhg.com.sg", "88244165", "male", "S9226940Z", "7 Lok Yang Vista", new Date(), "Kent Ridge North", "Laundry Staff", "Laundry", 7, true, "Ee Pui Ling", "7 Lok Yang Vista", "68701722");
        Staff s1 = new Staff("Kenny Ee", "kennyee", encryptPassword("123456"), "kennyee@krhg.com.sg", "88244165", "male", "S9226940Z", "7 Lok Yang Vista", new Date(), "Kent Ridge North", "Laundry Staff", "Laundry", 7, true, "Ee Pui Ling", "7 Lok Yang Vista", "68701722");
        Staff s2 = new Staff("David Chia Zhi Jie", "davidchia", encryptPassword("123456"), "davidchia@krhg.com.sg", "98861094", "male", "S6831300G", "Blk 226 Lorong 7 Pasir Ris, #04-32", new Date(), "Kent Ridge Grand", "Kitchen Manager", "Kitchen", 14, true, "Chia Xian Siew", "Blk 226 Lorong 7 Pasir Ris, #04-32", "67494068");
        Staff s3 = new Staff("Alice Chai", "alicechai", encryptPassword("123456"), "alicechai@krhg.com.sg", "93070252", "Female", "S3543767C", "Blk 377 Serangoon North Street 88, #15-09", new Date(), "Kent Ridge Central", "Housekeeping Manager", "Housekeeping", 14, true, "Chai Li Ting", "Blk 377 Serangoon North Street 88, #15-09", "61935979");
        Staff s4 = new Staff("Siti Riduan", "sitiriduan", encryptPassword("123456"), "sitiriduan@krhg.com.sg", "93497066", "Female", "S1730049J", "Blk 29 Geylang Street 21, #01-27", new Date(), "Kent Ridge Central", "Housekeeping Staff", "Housekeeping", 7, true, "Riduan Mohd Yaccob", "Blk 29 Geylang Street 21, #01-27", "67603364");
        Staff s5 = new Staff("Geoffrey Gan", "geoffreygan", encryptPassword("123456"), "geoffreygan@krhg.com.sg", "91574480", "male", "F9117753Q", "Blk 364 Geylang Street 17, #18-06", new Date(), "Kent Ridge North East", "Housekeeping Manager", "Housekeeping", 14, true, "Gan Kim Hock", "Blk 364 Geylang Street 17, #18-06", "61446071");

        staffSessionLocal.createStaff(s0);
        staffSessionLocal.createStaff(s1);
        staffSessionLocal.createStaff(s2);
        staffSessionLocal.createStaff(s3);
        staffSessionLocal.createStaff(s4);
        staffSessionLocal.createStaff(s5);

        //*********************************************HOTEL FACILIY************************************************
        HotelFacility hf1 = new HotelFacility("Wifi Intenet", "Free 4G Internet service to wherever you go", "");
        HotelFacility hf2 = new HotelFacility("Swimming Pool", "Free access to the swimming pool from 7am to 10pm", "");
        HotelFacility hf3 = new HotelFacility("Snooker Table", "Free snooker game from 10am to 2am", "");
        hotelFacilitySessionLocal.createHotelFacility(hf1);
        hotelFacilitySessionLocal.createHotelFacility(hf2);
        hotelFacilitySessionLocal.createHotelFacility(hf3);

        HotelFacility wifi = hotelFacilitySessionLocal.getAllHotelFacilities().get(0);
        HotelFacility swimmingPool = hotelFacilitySessionLocal.getAllHotelFacilities().get(1);
        HotelFacility snooker = hotelFacilitySessionLocal.getAllHotelFacilities().get(2);

        //*********************************************HOTEL ************************************************
        Hotel h1 = new Hotel("Kent Ridge North", "KR-N", "Ang Mo Kio Ave 10 Singapore 512345", 5, "91234567");
        h1.addHotelFacility(wifi);
        h1.addHotelFacility(swimmingPool);
        h1.addHotelFacility(snooker);
        Hotel h2 = new Hotel("Kent Ridge North East", "KR-NE", "Hougang Ave 10 Singapore 512345", 5, "91234567");
        h2.addHotelFacility(wifi);
        h2.addHotelFacility(swimmingPool);
        h2.addHotelFacility(snooker);
        Hotel h3 = new Hotel("Kent Ridge East", "KR-E", "Tampines Ave 10 Singapore 512345", 5, "91234567");
        h3.addHotelFacility(wifi);
        h3.addHotelFacility(snooker);
        Hotel h4 = new Hotel("Kent Ridge South East", "KR-SE", "Marina Bay Singapore 512345", 5, "91234567");
        h4.addHotelFacility(wifi);
        Hotel h5 = new Hotel("Kent Ridge South", "KR-S", "Orchard Singapore 512345", 5, "91234567");
        h5.addHotelFacility(wifi);
        h5.addHotelFacility(snooker);
        Hotel h6 = new Hotel("Kent Ridge South West", "KR-SW", "Jurong Ave 10 Singapore 512345", 5, "91234567");
        h6.addHotelFacility(wifi);
        h6.addHotelFacility(swimmingPool);

        hotelSessionLocal.createHotel(h1);
        hotelSessionLocal.createHotel(h2);
        hotelSessionLocal.createHotel(h3);
        hotelSessionLocal.createHotel(h4);
        hotelSessionLocal.createHotel(h5);
        hotelSessionLocal.createHotel(h6);

        h1 = hotelSessionLocal.getHotelByName("Kent Ridge North");
        h2 = hotelSessionLocal.getHotelByName("Kent Ridge North East");
        h3 = hotelSessionLocal.getHotelByName("Kent Ridge East");
        h4 = hotelSessionLocal.getHotelByName("Kent Ridge South East");
        h5 = hotelSessionLocal.getHotelByName("Kent Ridge South");
        h6 = hotelSessionLocal.getHotelByName("Kent Ridge South West");

        //*********************************************Room Facility************************************************
        MinibarItem m1 = new MinibarItem("Potato Chips", 1, 3.40);
        MinibarItem m2 = new MinibarItem("Saporro Beer", 1, 4.50);
        MinibarItem m3 = new MinibarItem("Cup Noodle", 1, 2.50);
        MinibarItem m4 = new MinibarItem("Mineral Water", 1, 2.00);

        houseKeepingOrderSessionLocal.createMinibarItem(m1);
        houseKeepingOrderSessionLocal.createMinibarItem(m2);
        houseKeepingOrderSessionLocal.createMinibarItem(m3);
        houseKeepingOrderSessionLocal.createMinibarItem(m4);

        m1 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Potato Chips");
        m2 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Saporro Beer");
        m3 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Cup Noodle");
        m4 = houseKeepingOrderSessionLocal.getMinibarItemByItemName("Mineral Water");

        //*********************************************Room Facility************************************************
        RoomFacility rf1 = new RoomFacility("Bathtub", "");
        RoomFacility rf2 = new RoomFacility("TV", "");
        RoomFacility rf3 = new RoomFacility("Room Facility 3", "");

        roomFacilitySessionLocal.createRoomFacility(rf1);
        roomFacilitySessionLocal.createRoomFacility(rf2);
        roomFacilitySessionLocal.createRoomFacility(rf3);

        rf1 = roomFacilitySessionLocal.getRoomFacilityByName("Bathtub");
        rf2 = roomFacilitySessionLocal.getRoomFacilityByName("TV");
        rf3 = roomFacilitySessionLocal.getRoomFacilityByName("Room Facility 3");

        //**********************************************Room************************************************
        Room krn1 = new Room("KRN_101", "101", "Standard", 2, "Available", h1);
        krn1.addRoomFacility(rf1);
        krn1.addRoomFacility(rf2);
        krn1.addMinibarItem(m1);
        krn1.addMinibarItem(m2);
        krn1.addMinibarItem(m3);
        Room krn2 = new Room("KRN_101", "102", "Standard", 2, "Available", h1);
        krn2.addRoomFacility(rf1);
        krn2.addRoomFacility(rf2);
        krn2.addMinibarItem(m1);
        krn2.addMinibarItem(m2);
        krn2.addMinibarItem(m3);
        krn2.addMinibarItem(m4);
        Room krn3 = new Room("KRN_101", "601", "Premium", 4, "Available", h1);
        krn3.addRoomFacility(rf1);
        krn3.addRoomFacility(rf2);
        krn3.addRoomFacility(rf3);
        krn3.addMinibarItem(m1);
        krn3.addMinibarItem(m4);
        Room krn4 = new Room("KRN_101", "901", "Deluxe", 6, "Available", h1);
        krn4.addRoomFacility(rf1);
        krn4.addRoomFacility(rf2);
        krn4.addMinibarItem(m2);
        krn4.addMinibarItem(m3);
        krn4.addMinibarItem(m4);
        Room krn5 = new Room("KRN_101", "1101", "Suite", 10, "Available", h1);
        krn5.addRoomFacility(rf1);
        krn5.addRoomFacility(rf2);
        krn5.addMinibarItem(m1);
        krn5.addMinibarItem(m2);
        krn5.addMinibarItem(m4);

        Room krne1 = new Room("KRNE_101", "101", "Standard", 2, "Available", h2);
        krne1.addRoomFacility(rf1);
        krne1.addRoomFacility(rf2);
        krne1.addRoomFacility(rf3);
        krne1.addMinibarItem(m1);
        krne1.addMinibarItem(m2);
        krne1.addMinibarItem(m3);
        krne1.addMinibarItem(m4);
        Room krne2 = new Room("KRNE_102", "102", "Standard", 2, "Available", h2);
        krne2.addRoomFacility(rf1);
        krne2.addRoomFacility(rf2);
        krne2.addMinibarItem(m1);
        krne2.addMinibarItem(m3);
        krne2.addMinibarItem(m4);
        Room krne3 = new Room("KRNE_601", "601", "Premium", 4, "Available", h2);
        krne3.addRoomFacility(rf1);
        krne3.addRoomFacility(rf2);
        krne3.addMinibarItem(m1);
        krne3.addMinibarItem(m2);
        krne3.addMinibarItem(m4);
        Room krne4 = new Room("KRNE_901", "901", "Deluxe", 6, "Available", h2);
        krne4.addRoomFacility(rf1);
        krne4.addRoomFacility(rf2);
        krne4.addRoomFacility(rf3);
        krne4.addMinibarItem(m2);
        krne4.addMinibarItem(m3);
        Room krne5 = new Room("KRNE_1101", "1101", "Suite", 10, "Available", h2);
        krne5.addRoomFacility(rf1);
        krne5.addRoomFacility(rf2);
        krne5.addMinibarItem(m2);
        krne5.addMinibarItem(m3);
        krne5.addMinibarItem(m4);

        Room kre1 = new Room("KRE_101", "101", "Standard", 2, "Available", h3);
        kre1.addRoomFacility(rf1);
        kre1.addRoomFacility(rf2);
        kre1.addMinibarItem(m3);
        kre1.addMinibarItem(m4);
        Room kre2 = new Room("KRE_102", "102", "Standard", 2, "Available", h3);
        kre2.addRoomFacility(rf1);
        kre2.addRoomFacility(rf2);
        kre2.addRoomFacility(rf3);
        kre2.addMinibarItem(m1);
        kre2.addMinibarItem(m2);
        kre2.addMinibarItem(m3);
        kre2.addMinibarItem(m4);
        Room kre3 = new Room("KRE_601", "601", "Premium", 4, "Available", h3);
        kre3.addRoomFacility(rf1);
        kre3.addRoomFacility(rf2);
        kre3.addRoomFacility(rf3);
        kre3.addMinibarItem(m1);
        kre3.addMinibarItem(m2);
        kre3.addMinibarItem(m3);
        kre3.addMinibarItem(m4);
        Room kre4 = new Room("KRE_901", "901", "Deluxe", 6, "Available", h3);
        kre4.addRoomFacility(rf1);
        kre4.addRoomFacility(rf2);
        kre4.addRoomFacility(rf3);
        kre4.addMinibarItem(m1);
        kre4.addMinibarItem(m2);
        kre4.addMinibarItem(m3);
        kre4.addMinibarItem(m4);
        Room kre5 = new Room("KRE_1101", "1101", "Suite", 10, "Available", h3);
        kre5.addRoomFacility(rf1);
        kre5.addRoomFacility(rf2);
        kre5.addMinibarItem(m1);
        kre5.addMinibarItem(m3);

        Room krse1 = new Room("KRSE_101", "101", "Standard", 2, "Available", h4);
        krse1.addRoomFacility(rf1);
        krse1.addRoomFacility(rf2);
        krse1.addRoomFacility(rf3);
        krse1.addMinibarItem(m1);
        krse1.addMinibarItem(m2);
        krse1.addMinibarItem(m3);
        Room krse2 = new Room("KRSE_102", "102", "Standard", 2, "Available", h4);
        krse2.addRoomFacility(rf1);
        krse2.addRoomFacility(rf2);
        krse2.addMinibarItem(m1);
        krse2.addMinibarItem(m4);
        Room krse3 = new Room("KRSE_601", "601", "Premium", 4, "Available", h4);
        krse3.addRoomFacility(rf1);
        krse3.addRoomFacility(rf2);
        krse3.addMinibarItem(m2);
        krse3.addMinibarItem(m3);
        Room krse4 = new Room("KRSE_901", "901", "Deluxe", 6, "Available", h4);
        krse4.addRoomFacility(rf1);
        krse4.addRoomFacility(rf2);
        krse4.addMinibarItem(m1);
        krse4.addMinibarItem(m2);
        Room krse5 = new Room("KRSE_1101", "1101", "Suite", 10, "Available", h4);
        krse5.addRoomFacility(rf1);
        krse5.addRoomFacility(rf2);
        krse5.addMinibarItem(m1);
        krse5.addMinibarItem(m3);
        krse5.addMinibarItem(m4);

        Room krs1 = new Room("KRS_101", "101", "Standard", 2, "Available", h5);
        krs1.addRoomFacility(rf1);
        krs1.addRoomFacility(rf2);
        krs1.addMinibarItem(m3);
        krs1.addMinibarItem(m4);
        Room krs2 = new Room("KRS_102", "102", "Standard", 2, "Available", h5);
        krs2.addRoomFacility(rf1);
        krs2.addRoomFacility(rf2);
        krs2.addMinibarItem(m2);
        krs2.addMinibarItem(m3);
        krs2.addMinibarItem(m4);
        Room krs3 = new Room("KRS_601", "601", "Premium", 4, "Available", h5);
        krs3.addRoomFacility(rf1);
        krs3.addRoomFacility(rf2);
        krs3.addRoomFacility(rf3);
        krs3.addMinibarItem(m1);
        krs3.addMinibarItem(m2);
        krs3.addMinibarItem(m3);
        Room krs4 = new Room("KRS_901", "901", "Deluxe", 6, "Available", h5);
        krs4.addRoomFacility(rf1);
        krs4.addRoomFacility(rf2);
        krs4.addMinibarItem(m1);
        krs4.addMinibarItem(m2);
        Room krs5 = new Room("KRS_1101", "1101", "Suite", 10, "Available", h5);
        krs5.addRoomFacility(rf1);
        krs5.addRoomFacility(rf2);
        krs5.addMinibarItem(m2);
        krs5.addMinibarItem(m3);

        Room krsw1 = new Room("KRSW_101", "101", "Standard", 2, "Available", h6);
        krsw1.addRoomFacility(rf1);
        krsw1.addRoomFacility(rf2);
        krsw1.addMinibarItem(m2);
        krsw1.addMinibarItem(m3);
        krsw1.addMinibarItem(m4);
        Room krsw2 = new Room("KRSW_102", "102", "Standard", 2, "Available", h6);
        krsw2.addRoomFacility(rf1);
        krsw2.addRoomFacility(rf2);
        krsw2.addMinibarItem(m1);
        krsw2.addMinibarItem(m2);
        krsw2.addMinibarItem(m3);
        Room krsw3 = new Room("KRSW_601", "601", "Premium", 4, "Available", h6);
        krsw3.addRoomFacility(rf1);
        krsw3.addRoomFacility(rf2);
        krsw3.addRoomFacility(rf3);
        krsw3.addMinibarItem(m2);
        krsw3.addMinibarItem(m4);
        Room krsw4 = new Room("KRSW_901", "901", "Deluxe", 6, "Available", h6);
        krsw4.addRoomFacility(rf1);
        krsw4.addRoomFacility(rf2);
        krsw4.addMinibarItem(m2);
        krsw4.addMinibarItem(m3);
        Room krsw5 = new Room("KRSW_1101", "1101", "Suite", 10, "Available", h6);
        krsw5.addRoomFacility(rf1);
        krsw5.addRoomFacility(rf2);
        krsw5.addMinibarItem(m2);
        krsw5.addMinibarItem(m3);
        krsw5.addMinibarItem(m4);

        roomSessionLocal.createRoom(krn1);
        roomSessionLocal.createRoom(krn2);
        roomSessionLocal.createRoom(krn3);
        roomSessionLocal.createRoom(krn4);
        roomSessionLocal.createRoom(krn5);

        roomSessionLocal.createRoom(krne1);
        roomSessionLocal.createRoom(krne2);
        roomSessionLocal.createRoom(krne3);
        roomSessionLocal.createRoom(krne4);
        roomSessionLocal.createRoom(krne5);

        roomSessionLocal.createRoom(kre1);
        roomSessionLocal.createRoom(kre2);
        roomSessionLocal.createRoom(kre3);
        roomSessionLocal.createRoom(kre4);
        roomSessionLocal.createRoom(kre5);

        roomSessionLocal.createRoom(krse1);
        roomSessionLocal.createRoom(krse2);
        roomSessionLocal.createRoom(krse3);
        roomSessionLocal.createRoom(krse4);
        roomSessionLocal.createRoom(krse5);

        roomSessionLocal.createRoom(krs1);
        roomSessionLocal.createRoom(krs2);
        roomSessionLocal.createRoom(krs3);
        roomSessionLocal.createRoom(krs4);
        roomSessionLocal.createRoom(krs5);

        roomSessionLocal.createRoom(krsw1);
        roomSessionLocal.createRoom(krsw2);
        roomSessionLocal.createRoom(krsw3);
        roomSessionLocal.createRoom(krsw4);
        roomSessionLocal.createRoom(krsw5);

        h1.addRoom(roomSessionLocal.getRoomByName("KRN_101"));
        h1.addRoom(roomSessionLocal.getRoomByName("KRN_102"));
        h1.addRoom(roomSessionLocal.getRoomByName("KRN_601"));
        h1.addRoom(roomSessionLocal.getRoomByName("KRN_901"));
        h1.addRoom(roomSessionLocal.getRoomByName("KRN_1101"));

        h2.addRoom(roomSessionLocal.getRoomByName("KRNE_101"));
        h2.addRoom(roomSessionLocal.getRoomByName("KRNE_102"));
        h2.addRoom(roomSessionLocal.getRoomByName("KRNE_601"));
        h2.addRoom(roomSessionLocal.getRoomByName("KRNE_901"));
        h2.addRoom(roomSessionLocal.getRoomByName("KRNE_1101"));

        h3.addRoom(roomSessionLocal.getRoomByName("KRE_101"));
        h3.addRoom(roomSessionLocal.getRoomByName("KRE_102"));
        h3.addRoom(roomSessionLocal.getRoomByName("KRE_601"));
        h3.addRoom(roomSessionLocal.getRoomByName("KRE_901"));
        h3.addRoom(roomSessionLocal.getRoomByName("KRE_1101"));

        h4.addRoom(roomSessionLocal.getRoomByName("KRSE_101"));
        h4.addRoom(roomSessionLocal.getRoomByName("KRSE_102"));
        h4.addRoom(roomSessionLocal.getRoomByName("KRSE_601"));
        h4.addRoom(roomSessionLocal.getRoomByName("KRSE_901"));
        h4.addRoom(roomSessionLocal.getRoomByName("KRSE_1101"));

        h5.addRoom(roomSessionLocal.getRoomByName("KRS_101"));
        h5.addRoom(roomSessionLocal.getRoomByName("KRS_102"));
        h5.addRoom(roomSessionLocal.getRoomByName("KRS_601"));
        h5.addRoom(roomSessionLocal.getRoomByName("KRS_901"));
        h5.addRoom(roomSessionLocal.getRoomByName("KRS_1101"));

        h6.addRoom(roomSessionLocal.getRoomByName("KRSW_101"));
        h6.addRoom(roomSessionLocal.getRoomByName("KRSW_102"));
        h6.addRoom(roomSessionLocal.getRoomByName("KRSW_601"));
        h6.addRoom(roomSessionLocal.getRoomByName("KRSW_901"));
        h6.addRoom(roomSessionLocal.getRoomByName("KRSW_1101"));
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
