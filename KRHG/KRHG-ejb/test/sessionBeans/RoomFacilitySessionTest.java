/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.RoomFacility;
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


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoomFacilitySessionTest {

    static EJBContainer container;
    static RoomFacilitySessionLocal instance;
    static Long ID1 = 10000000L;
    static Long ID2 = 10000001L;

    public RoomFacilitySessionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(
                "org.glassfish.ejb.embedded.glassfish.configuration.file",
                "C:\\Users\\MC\\AppData\\Roaming\\NetBeans\\8.2\\config\\GF_4.1\\domain1\\config\\domain.xml"
        );//custom glassfish 4.1 path
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
        instance = (RoomFacilitySessionLocal) container.getContext().lookup("java:global/classes/RoomFacilitySession");
    }

    @AfterClass
    public static void tearDownClass() throws Exception{
        instance.deleteRoomFacility(ID1);
        container.close();
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void a_testCreateRoomFacility() throws Exception {
        int sizeBefore = instance.getAllRoomFacilities().size();
        RoomFacility rf1 = new RoomFacility(ID1, "Test1");
        instance.createRoomFacility(rf1);
        RoomFacility rf2 = new RoomFacility(ID2, "Test2");
        instance.createRoomFacility(rf2);
        int sizeAfter = instance.getAllRoomFacilities().size();
        int exp = sizeBefore + 2;
        assertEquals(exp, sizeAfter);


    }

    @Test
    public void b_testGetAllRoomFacilities() throws Exception {
        List<RoomFacility> result = instance.getAllRoomFacilities();
        assertFalse(result.isEmpty());        

    }

    @Test
    public void c_testGetRoomFacilityByID() throws Exception {
        RoomFacility result = instance.getRoomFacilityByID(ID1);
        String exp = "Test1";
        assertEquals(exp, result.getRoomFacilityName());        
    }


    @Test
    public void d_testDeleteRoomFacility() throws Exception {
        int sizeBefore = instance.getAllRoomFacilities().size();
        instance.deleteRoomFacility(ID2);
        int exp = sizeBefore - 1;
        int sizeAfter = instance.getAllRoomFacilities().size();
        assertEquals(exp, sizeAfter);        
    }
    
    @Test
    public void e_testGetRoomFacilityByName() throws Exception {
        RoomFacility result = instance.getRoomFacilityByName("Test1");
        String exp = "Test1";
        assertEquals(exp, result.getRoomFacilityName());        

    }    

    @Test
    public void f_testUpdateRoomFacility() throws Exception {
        String expResult = "NewName";
        RoomFacility result = instance.getRoomFacilityByID(ID1);
        result.setRoomFacilityName(expResult);
        instance.updateRoomFacility(result);
        RoomFacility newResult = instance.getRoomFacilityByID(ID1);
        assertEquals(expResult, newResult.getRoomFacilityName());        
        

    }



}
