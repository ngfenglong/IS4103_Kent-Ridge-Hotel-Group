/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Feedback;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author MC
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FeedbackSessionTest {

    static EJBContainer container;
    static FeedbackSessionLocal instance;
    static Long ID1 = 10000000L;
    static Long ID2 = 20000001L;

    public FeedbackSessionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(
                "org.glassfish.ejb.embedded.glassfish.configuration.file",
                "C:\\Users\\MC\\AppData\\Roaming\\NetBeans\\8.2\\config\\GF_4.1\\domain1\\config\\domain.xml");
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);//custom glassfish 4.1 path
        instance = (FeedbackSessionLocal) container.getContext().lookup("java:global/classes/FeedbackSession");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        instance.deleteFeedback(ID1);

    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void a_testCreateFeedback() throws Exception {
        int sizeBefore = instance.getAllFeedbacks().size();
        Feedback f1 = new Feedback(ID1, "Test Title 1", "Test From 1", "Test message 1");
        Feedback f2 = new Feedback(ID2, "Test Title 2", "Test From 2", "Test message 2");
        instance.createFeedback(f1);
        instance.createFeedback(f2);
        int sizeAfter = instance.getAllFeedbacks().size();
        int exp = sizeBefore + 2;
        assertEquals(exp, sizeAfter);
    }

    @Test
    public void b_testGetAllFeedbacks() throws Exception {
        List<Feedback> result = instance.getAllFeedbacks();
        assertFalse(result.isEmpty());
    }

    @Test
    public void c_testGetFeedbackByID() throws Exception {
        Feedback result = instance.getFeedbackByID(ID2);
        String exp = "Test message 2";
        assertEquals(exp, result.getFeedBackMsg());
    }

    @Test
    public void d_testDeleteFeedback() throws Exception {
        int sizeBefore = instance.getAllFeedbacks().size();
        instance.deleteFeedback(ID2);
        int exp = sizeBefore - 1;
        int sizeAfter = instance.getAllFeedbacks().size();
        assertEquals(exp, sizeAfter);
    }

    @Test
    public void e_testUpdateFeedback() throws Exception {
        String expResult = "New Msg";
        Feedback result = instance.getFeedbackByID(ID1);
        result.setFeedBackMsg(expResult);
        instance.updateFeedback(result);
        Feedback newResult = instance.getFeedbackByID(ID1);
        assertEquals(expResult, newResult.getFeedBackMsg());
    }

}
