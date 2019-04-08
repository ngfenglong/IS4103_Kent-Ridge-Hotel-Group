/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Room;
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


/**
 *
 * @author MC
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoomSessionTest {

    static EJBContainer container;
    static RoomSessionLocal instance;
    static RoomFacilitySessionLocal instance2;

    static Long ID1 = 10000000L; //for room
    static Long ID2 = 10000001L; //for room
    static Long ID3 = 20000001L; //for roomfacility
    static Long ID4 = 20000002L; //for roomfacility

    public RoomSessionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(
                "org.glassfish.ejb.embedded.glassfish.configuration.file",
                "C:\\Users\\MC\\AppData\\Roaming\\NetBeans\\8.2\\config\\GF_4.1\\domain1\\config\\domain.xml"
        );//custom glassfish 4.1 path
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
        instance = (RoomSessionLocal) container.getContext().lookup("java:global/classes/RoomSession");
        instance2 = (RoomFacilitySessionLocal) container.getContext().lookup("java:global/classes/RoomFacilitySession");
        
        RoomFacility rf1 = new RoomFacility(ID3, "Test1");
        instance2.createRoomFacility(rf1);
        RoomFacility rf2 = new RoomFacility(ID4, "Test2");
        instance2.createRoomFacility(rf2);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        instance.deleteRoom(ID1);
        instance2.deleteRoomFacility(ID3);
        instance2.deleteRoomFacility(ID4);
        //instance.deleteRoom(ID2);
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void a_testCreateRoom() throws Exception {
        int sizeBefore = instance.getAllRooms().size();
        Room r1 = new Room(ID1, "Test1", "101", "Standard", 2, "Available");
        Room r2 = new Room(ID2, "Test2", "102", "Standard", 2, "Available");
        instance.createRoom(r1);
        instance.createRoom(r2);
        int sizeAfter = instance.getAllRooms().size();
        int exp = sizeBefore + 2;
        assertEquals(exp, sizeAfter);
    }

    @Test
    public void b_testGetAllRooms() throws Exception {
        List<Room> result = instance.getAllRooms();
        assertFalse(result.isEmpty());
    }

    @Test
    public void c_testGetRoomByID() throws Exception {
        Room result = instance.getRoomByID(ID2);
        String expResult = "Test2";
        assertEquals(expResult, result.getRoomName());
    }

    @Test
    public void d_testGetRoomByName() throws Exception {
        Room result = instance.getRoomByName("Test2");
        String expResult = "Test2";
        assertEquals(expResult, result.getRoomName());
    }

    @Test
    public void e_testGetRoomByType() throws Exception {
        List<Room> result = instance.getRoomByType("Standard", "", "Available");
        assertFalse(result.isEmpty());
    }

    @Test
    public void f_testDeleteRoom() throws Exception {
        int sizeBefore = instance.getAllRooms().size();
        instance.deleteRoom(ID2);
        int sizeAfter = instance.getAllRooms().size();
        int exp = sizeBefore - 1;
        assertEquals(exp, sizeAfter);
    }

    @Test
    public void g_testUpdateRoom() throws Exception {
        String expResult = "TestName";
        Room result = instance.getRoomByID(ID1);
        result.setRoomName("TestName");
        instance.updateRoom(result);
        Room newResult = instance.getRoomByID(ID1);
        assertEquals(expResult, newResult.getRoomName());
    }

    @Test
    public void h_testAddRoomFacility() throws Exception {
        Room result = instance.getRoomByID(ID1);
        RoomFacility rf = instance2.getRoomFacilityByID(ID3);
        int sizeBefore = result.getRoomFacilities().size();
        instance.addRoomFacility(ID1, rf);
        Room newResult = instance.getRoomByID(ID1);
        int sizeAfter = newResult.getRoomFacilities().size();
        int exp = sizeBefore + 1;
        assertEquals(exp, sizeAfter);
        
    }

}
