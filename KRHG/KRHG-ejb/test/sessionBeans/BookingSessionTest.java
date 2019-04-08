/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Customer;
import entity.HotelFacilityBooking;
import entity.RoomBooking;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookingSessionTest {

    static EJBContainer container;
    static BookingSessionLocal instance;
    static CustomerSessionLocal instance2;
    static HotelFacilitySessionLocal instance3;

    static Long ID1 = 10000001L;//rb1
    static Long ID2 = 10000002L;//rb2
    static Long ID3 = 20000001L;//hb1
    static Long ID4 = 20000002L;//hb2
    static Long ID5 = 30000001L;//c1
    static Long ID6 = 30000002L;//c2

    static Calendar myCalendar = new GregorianCalendar(2019, 3, 11); //bookin
    static Date d1 = myCalendar.getTime();
    static Calendar myCalendar2 = new GregorianCalendar(2019, 3, 14); //bookout
    static Date d2 = myCalendar2.getTime();

    public BookingSessionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(
                "org.glassfish.ejb.embedded.glassfish.configuration.file",
                "C:\\Users\\MC\\AppData\\Roaming\\NetBeans\\8.2\\config\\GF_4.1\\domain1\\config\\domain.xml"
        );//custom glassfish 4.1 path
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
        instance = (BookingSessionLocal) container.getContext().lookup("java:global/classes/BookingSession");
        instance2 = (CustomerSessionLocal) container.getContext().lookup("java:global/classes/CustomerSession");
        instance3 = (HotelFacilitySessionLocal) container.getContext().lookup("java:global/classes/HotelFacilitySession");

        Customer c1 = new Customer(ID5, "test1@hotmail.com", "test1");
        Customer c2 = new Customer(ID6, "test2@hotmail.com", "test2");
        instance2.createCustomer(c1);
        instance2.createCustomer(c2);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        instance.deleteRoomBooking(ID1);
        instance.deleteRoomBooking(ID2);
        instance.deleteHotelFacilityBooking(ID3);
        instance.deleteHotelFacilityBooking(ID4);
        instance2.deleteCustomer(ID5);
        instance2.deleteCustomer(ID6);
        container.close();
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void a_testCreateRoomBooking() throws Exception {
        int sizeBefore = instance.getAllRoomBooking().size();
        Customer c1 = instance2.getCustomerByID(ID5);
        Customer c2 = instance2.getCustomerByID(ID6);
        RoomBooking b1 = new RoomBooking(ID1, d1, d2, "paid", c1);
        RoomBooking b2 = new RoomBooking(ID2, d1, d2, "paid", c2);
        instance.createRoomBooking(b1);
        instance.createRoomBooking(b2);
        int sizeAfter = instance.getAllRoomBooking().size();
        int exp = sizeBefore + 2;
        assertEquals(exp, sizeAfter);
    }

    @Test
    public void b_testCreateHotelFacilityBooking() throws Exception {
        Customer c1 = instance2.getCustomerByID(ID5);
        Customer c2 = instance2.getCustomerByID(ID6);        
        int sizeBefore = instance.getAllHotelFacilityBooking().size();
        HotelFacilityBooking hfb1 = new HotelFacilityBooking(ID3, d1, d2, "paid", c1);
        HotelFacilityBooking hfb2 = new HotelFacilityBooking(ID4, d1, d2, "paid", c2);
        instance.createHotelFacilityBooking(hfb1);
        instance.createHotelFacilityBooking(hfb2);
        int sizeAfter = instance.getAllHotelFacilityBooking().size();
        int exp = sizeBefore + 2;
        assertEquals(exp, sizeAfter);
    }

    @Test
    public void c_testGetAllRoomBooking() throws Exception {
        List<RoomBooking> result = instance.getAllRoomBooking();
        assertFalse(result.isEmpty());
    }

    @Test
    public void d_testGetRoomBookingByID() throws Exception {
        RoomBooking result = instance.getRoomBookingByID(ID1);
        String expResult = "paid";
        assertEquals(expResult, result.getStatus());
    }

    @Test
    public void e_testGetRoomBookingByEmail() throws Exception {
        List<RoomBooking> result = instance.getRoomBookingByEmail("test1@hotmail", d1, d2, "paid");
        assertFalse(result.isEmpty());
    }

    @Test
    public void f_testGetRoomBookingByDate() throws Exception {
        List<RoomBooking> result = instance.getRoomBookingByDate(d1, d2, "paid");
        assertFalse(result.isEmpty());
    }

    @Test
    public void g_testUpdateRoomBooking() throws Exception {
        String expResult = "cancelled";
        RoomBooking result = instance.getRoomBookingByID(ID1);
        result.setStatus("cancelled");
        instance.updateRoomBooking(result);
        RoomBooking newResult = instance.getRoomBookingByID(ID1);
        assertEquals(expResult, newResult.getStatus());
    }

    @Test
    public void h_testDeleteRoomBooking() throws Exception {
        int sizeBefore = instance.getAllRoomBooking().size();
        instance.deleteRoomBooking(ID1);
        int sizeAfter = instance.getAllRoomBooking().size();
        int exp = sizeAfter + 1;
        assertEquals(exp, sizeBefore);
    }

    @Test
    public void i_testGetAllHotelFacilityBooking() throws Exception {
        List<HotelFacilityBooking> result = instance.getAllHotelFacilityBooking();
        assertFalse(result.isEmpty());
    }

    @Test
    public void j_testGetHotelFacilityBookingByID() throws Exception {
        HotelFacilityBooking result = instance.getHotelFacilityBookingByID(ID3);
        String expResult = "paid";
        assertEquals(expResult, result.getStatus());
    }
//
//    @Test
//    public void k_testGetHotelFacilityBookingByEmail() throws Exception {
//        HotelFacilityBooking result = instance.getHotelFacilityBookingByEmail("test2@hotmail.com");
//        assertFalse(result.isEmpty());
//    }
//
//    @Test
//    public void testGetHotelFacilityBookingByDate() throws Exception {
//        HotelFacilityBooking result = instance.getHotelFacilityBookingByID(d1, d2);
//        assertFalse(result.isEmpty());
//    }

    @Test
    public void l_testUpdateHotelFacilityBooking() throws Exception {
        String expResult = "cancelled";
        HotelFacilityBooking result = instance.getHotelFacilityBookingByID(ID5);
        result.setStatus("cancelled");
        instance.updateHotelFacilityBooking(result);
        HotelFacilityBooking newResult = instance.getHotelFacilityBookingByID(ID5);
        assertEquals(expResult, newResult.getStatus());
    }

    @Test
    public void m_testDeleteHotelFacilityBooking() throws Exception {
        int sizeBefore = instance.getAllHotelFacilityBooking().size();
        instance.deleteHotelFacilityBooking(ID5);
        int sizeAfter = instance.getAllHotelFacilityBooking().size();
        int exp = sizeAfter + 1;
        assertEquals(exp, sizeBefore);
    }

}
