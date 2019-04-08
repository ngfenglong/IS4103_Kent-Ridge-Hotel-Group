/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Hotel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author MC
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HotelSessionTest {

    static EJBContainer container;
    static HotelSessionLocal instance;
    
    static Long hotelID1 = 10000000L;
    static Long hotelID2 = 10000001L;

    public HotelSessionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(
                "org.glassfish.ejb.embedded.glassfish.configuration.file",
                "C:\\Users\\MC\\AppData\\Roaming\\NetBeans\\8.2\\config\\GF_4.1\\domain1\\config\\domain.xml"
        );//custom glassfish 4.1 path
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
        instance = (HotelSessionLocal) container.getContext().lookup("java:global/classes/HotelSession");
    }

    @AfterClass
    public static void tearDownClass() throws Exception{
        instance.deleteHotel(hotelID1);
        container.close();

    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() throws Exception{
        
    }
    
    @Test
    public void a_testCreateHotel() throws Exception {
        int sizeBefore = instance.getAllHotels().size();
        Hotel h1 = new Hotel(hotelID1, "Test Hotel 1", "Test1", "Test Address", 5, "6123 1000", "test_1.jpg");
        Hotel h2 = new Hotel(hotelID2, "Test Hotel 2", "Test2", "Test Address", 5, "6123 1001", "test_2.jpg");
        instance.createHotel(h1);
        instance.createHotel(h2);
        int sizeAfter = instance.getAllHotels().size();
        int exp = sizeBefore + 2;
        assertEquals(exp, sizeAfter);
    }
    
    @Test
    public void b_testGetAllHotels() throws Exception {
        List<Hotel> result = instance.getAllHotels();
        assertFalse(result.isEmpty());
    }
    
    @Test
    public void c_testGetHotelByID() throws Exception {
        Hotel result = instance.getHotelByID(hotelID2);
        String expResult = "Test2";
        assertEquals(expResult, result.getHotelCodeName());
    }
    
    @Test
    public void d_testGetHotelByName() throws Exception {
        Hotel result = instance.getHotelByName("Test Hotel 2");
        String expResult = "Test2";
        assertEquals(expResult, result.getHotelCodeName());
    }
    
    @Test
    public void e_testDeleteHotel() throws Exception {
        int sizeBefore = instance.getAllHotels().size();
        instance.deleteHotel(hotelID2);
        int sizeAfter = instance.getAllHotels().size();
        int exp = sizeAfter + 1;
        assertEquals (exp, sizeBefore);
    }
    
    @Test
    public void f_testUpdateHotel() throws Exception {
        String expResult = "NewName";
        Hotel result = instance.getHotelByID(hotelID1);
        result.setHotelName(expResult);
        instance.updateHotel(result);
        Hotel newResult = instance.getHotelByID(hotelID1);
        assertEquals(expResult, newResult.getHotelName());
    }


}
