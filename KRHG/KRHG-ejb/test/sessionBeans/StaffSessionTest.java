/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Staff;
import entity.StaffType;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fengl
 */
public class StaffSessionTest {

    static EJBContainer container;
    static StaffSessionLocal instance;

    public StaffSessionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws NamingException {
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        instance = (StaffSessionLocal) container.getContext().lookup("java:global/classes/StaffSession");
    }

    @AfterClass
    public static void tearDownClass() {
        container.close();
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllStaffs method, of class StaffSession.
     */
    @Test
    public void testGetAllStaffs() throws Exception {
        System.out.println("getAllStaffs");

        List<Staff> expResult = null;
        List<Staff> result = instance.getAllStaffs();
        assertEquals(result, null);

    }

}
