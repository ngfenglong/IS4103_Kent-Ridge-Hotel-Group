/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Staff;
import entity.StaffType;
import java.util.Date;
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
public class StaffSessionTest {

    static EJBContainer container;
    static StaffSessionLocal instance;

    static Long ID1 = 10000000L; //for staff
    static Long ID2 = 10000001L; //for staff
    static Long ID3 = 20000001L; //for stafftype
    static Long ID4 = 20000002L; //for stafftype

    public StaffSessionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {

        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(
                "org.glassfish.ejb.embedded.glassfish.configuration.file",
                "C:\\Users\\MC\\AppData\\Roaming\\NetBeans\\8.2\\config\\GF_4.1\\domain1\\config\\domain.xml"
        );//custom glassfish 4.1 path
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
        instance = (StaffSessionLocal) container.getContext().lookup("java:global/classes/StaffSession");

    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        instance.deleteStaff(ID2);
        instance.deleteStaffType(ID3);
        container.close();
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void a_testCreateStaff() throws Exception {
        int sizeBefore = instance.getAllStaffs().size();
        Staff s0 = new Staff(ID1, "Test User", "test", "test", "test@krhg.com.sg", "88244145", "male", "S9540000J", "6 Lok Yang Vista", new Date(), "Kent Ridge North", "Laundry Staff", "Laundry", 7, true, "Test Pui Ling", "6 Lok Yang Vista", "68701721");
        Staff s1 = new Staff(ID2, "Kenny Ee", "kenyee", "password", "kenyee@krhg.com.sg", "88244165", "Male", "S9226941Z", "7 Lok Yang Vista", new Date(), "Kent Ridge West", "Laundry Staff", "Laundry", 7, true, "Ee Pui Ling", "7 Lok Yang Vista", "68701722");
        instance.createStaff(s0);
        instance.createStaff(s1);
        int sizeAfter = instance.getAllStaffs().size();
        int exp = sizeBefore + 2;
        assertEquals(exp, sizeAfter);
    }

    @Test
    public void b_testCreateStaffType() throws Exception {
        int sizeBefore = instance.getAllStaffTypes().size();
        StaffType st1 = new StaffType(ID3, "Housekeeping Staff");
        StaffType st2 = new StaffType(ID4, "Housekeeping Manager");
        instance.createStaffType(st1);
        instance.createStaffType(st2);
        int sizeAfter = instance.getAllStaffTypes().size();
        int exp = sizeBefore + 2;
        assertEquals(exp, sizeAfter);
    }

    @Test
    public void c_testGetAllStaffs() throws Exception {
        List<Staff> result = instance.getAllStaffs();
        assertFalse(result.isEmpty());
    }

    @Test
    public void d_testGetStaffByID() throws Exception {
        Staff result = instance.getStaffByID(ID2);
        String expResult = "kenyee";
        assertEquals(expResult, result.getUserName());

    }

    @Test
    public void e_testDeleteStaff() throws Exception {
        int sizeBefore = instance.getAllStaffs().size();
        instance.deleteStaff(ID1);
        int sizeAfter = instance.getAllStaffs().size();
        int exp = sizeAfter + 1;
        assertEquals("delete staff", exp, sizeBefore);
    }
    @Test
    public void f_testGetStaffByNRIC() throws Exception {
        String NRIC = "S9226941Z";
        Staff result = instance.getStaffByNric(NRIC);
        String expResult = "kenyee";
        assertEquals(expResult, result.getUserName());
    }

    @Test
    public void g_testDeactivateStaff() throws Exception {
        Staff result = instance.getStaffByID(ID2);
        instance.deactivateStaff(result);
        Staff newResult = instance.getStaffByID(ID2);
        boolean status = newResult.getAccountStatus();
        boolean expResult = false;
        assertEquals(expResult, status);
    }

    @Test
    public void h_testActivateStaff() throws Exception {
        Staff result = instance.getStaffByID(ID2);
        instance.activateStaff(result);
        Staff newResult = instance.getStaffByID(ID2);
        boolean status = newResult.getAccountStatus();
        boolean expResult = true;
        assertEquals(expResult, status);
    }

    @Test
    public void i_testUpdateStaff() throws Exception {
        String expResult = "TestName";
        Staff result = instance.getStaffByID(ID2);
        result.setName("TestName");
        instance.updateStaff(result);
        Staff newResult = instance.getStaffByID(ID2);
        assertEquals(expResult, newResult.getName());
    }

    @Test
    public void j_testLoginSuccess() throws Exception {
        boolean expResult = true;
        Staff s = new Staff("kenyee", "password");
        boolean result = instance.Login(s);
        assertEquals(result, expResult);
    }

    @Test
    public void k_testLoginFail() throws Exception {
        boolean expResult = false;
        Staff s = new Staff("kenyee", "wrongpassword");
        boolean result = instance.Login(s);
        assertEquals(result, expResult);
    }

    @Test
    public void l_testGetStaffByUsername() throws Exception {
        String username = "kenyee";
        Staff s = instance.getStaffByUsename(username);
        assertEquals(username, s.getUserName());
    }

    @Test
    public void m_testDeleteStaffType() throws Exception {
        int sizeBefore = instance.getAllStaffTypes().size();
        instance.deleteStaffType(ID4);
        int sizeAfter = instance.getAllStaffTypes().size();
        int exp = sizeAfter + 1;
        assertEquals("delete staff", exp, sizeBefore);
    }
    
    @Test
    public void n_testGetAllStaffTypes() throws Exception {
        List<StaffType> result = instance.getAllStaffTypes();
        assertFalse(result.isEmpty());
    }

    @Test
    public void o_testGetStaffTypeByID() throws Exception {
        StaffType result = instance.getStaffTypeByID(ID3);
        String expResult = "Housekeeping Staff";
        assertEquals(expResult, result.getStaffTypeName());

    }

    @Test
    public void p_testGetStaffTypeByName() throws Exception {
        String name = "Housekeeping Staff";
        StaffType st = instance.getStaffTypeByName(name);
        assertEquals(name, st.getStaffTypeName());

    }

    @Test
    public void q_testGetStaffByEmail() throws Exception {
        String email = "kenyee@krhg.com.sg";
        String expResult = "kenyee";
        Staff s = instance.getStaffByEmail(email);
        assertEquals(expResult, s.getUserName());
    }

}
