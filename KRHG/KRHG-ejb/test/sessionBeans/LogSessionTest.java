/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Logging;
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

/**
 *
 * @author MC
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LogSessionTest {

    static EJBContainer container;
    static LogSessionLocal instance;
    static Long ID1 = 40000000L;
    static Long ID2 = 40000001L;

    public LogSessionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(
                "org.glassfish.ejb.embedded.glassfish.configuration.file",
                "C:\\Users\\MC\\AppData\\Roaming\\NetBeans\\8.2\\config\\GF_4.1\\domain1\\config\\domain.xml"
        );//custom glassfish 4.1 path
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
        instance = (LogSessionLocal) container.getContext().lookup("java:global/classes/LogSession");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        instance.deleteLogging(ID1);
        //instance.deleteLogging(ID2);
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {

    }

    @Test
    public void a_testCreateLogging() throws Exception {
        int sizeBefore = instance.getAllLoggingActivities().size();
        Logging l1 = new Logging(ID1, "TestType1", "LogName1", "Test1");
        Logging l2 = new Logging(ID2, "TestType2", "LogName2", "Test2");
        instance.createLogging(l1);
        instance.createLogging(l2);
        int sizeAfter = instance.getAllLoggingActivities().size();
        int exp = sizeBefore + 2;
        assertEquals(exp, sizeAfter);
    }

    @Test
    public void b_testGetAllLoggingActivities() throws Exception {
        List<Logging> result = instance.getAllLoggingActivities();
        assertFalse(result.isEmpty());
    }

    @Test
    public void c_testGetLoggingByID() throws Exception {
        Logging result = instance.getLoggingByID(ID2);
        String expResult = "LogName2";
        assertEquals(expResult, result.getLoggingName());
    }

    @Test
    public void d_testGetLoggingByType() throws Exception {
        List<Logging> result = instance.getLoggingByType("TestType1");
        assertFalse(result.isEmpty());
    }
    
    @Test
    public void e_testDeleteLogging() throws Exception {
        int sizeBefore = instance.getAllLoggingActivities().size();
        instance.deleteLogging(ID2);
        int sizeAfter = instance.getAllLoggingActivities().size();
        int exp = sizeBefore - 1;
        assertEquals(exp, sizeAfter);
    }


}
