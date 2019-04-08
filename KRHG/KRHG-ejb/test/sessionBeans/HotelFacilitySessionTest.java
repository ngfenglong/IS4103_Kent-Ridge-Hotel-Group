/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.HotelFacility;
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
public class HotelFacilitySessionTest {

    static EJBContainer container;
    static HotelFacilitySessionLocal instance;

    static Long ID1 = 30000000L;
    static Long ID2 = 30000001L;

    public HotelFacilitySessionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(
                "org.glassfish.ejb.embedded.glassfish.configuration.file",
                "C:\\Users\\MC\\AppData\\Roaming\\NetBeans\\8.2\\config\\GF_4.1\\domain1\\config\\domain.xml"
        );//custom glassfish 4.1 path
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
        instance = (HotelFacilitySessionLocal) container.getContext().lookup("java:global/classes/HotelFacilitySession");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        instance.deleteHotelFacility(ID1);
        //instance.deleteHotelFacility(ID2);
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void a_testCreateHotelFacility() throws Exception {
        int sizeBefore = instance.getAllHotelFacilities().size();
        HotelFacility hf1 = new HotelFacility(ID1, "Test Name 1", "Test Desc 1", "Test1.jpg");
        HotelFacility hf2 = new HotelFacility(ID2, "Test Name 2", "Test Desc 2", "Test2.jpg");
        instance.createHotelFacility(hf1);
        instance.createHotelFacility(hf2);
        int sizeAfter = instance.getAllHotelFacilities().size();
        int exp = sizeBefore + 2;
        assertEquals(exp, sizeAfter);
    }

    @Test
    public void b_testGetAllHotelFacilities() throws Exception {
        List<HotelFacility> result = instance.getAllHotelFacilities();
        assertFalse(result.isEmpty());

    }

    @Test
    public void c_testGetHotelFacilityByID() throws Exception {
        HotelFacility result = instance.getHotelFacilityByID(ID2);
        String exp = "Test Name 2";
        assertEquals(exp, result.getHotelFacilityName());
    }
    
    @Test
    public void d_testGetHotelFacilityByName() throws Exception {
        HotelFacility result = instance.getHotelFacilityByName("Test Name 1");
        String exp = "Test Name 1";
        assertEquals(exp, result.getHotelFacilityName());
    }

    @Test
    public void e_testDeleteHotelFacility() throws Exception {
        int sizeBefore = instance.getAllHotelFacilities().size();
        instance.deleteHotelFacility(ID2);
        int exp = sizeBefore - 1;
        int sizeAfter = instance.getAllHotelFacilities().size();
        assertEquals(exp, sizeAfter);
    }

    @Test
    public void f_testUpdateHotelFacility() throws Exception {
        String expResult = "NewName";
        HotelFacility result = instance.getHotelFacilityByID(ID1);
        result.setHotelFacilityName(expResult);
        instance.updateHotelFacility(result);
        HotelFacility newResult = instance.getHotelFacilityByID(ID1);
        assertEquals(expResult, newResult.getHotelFacilityName());

    }



}
