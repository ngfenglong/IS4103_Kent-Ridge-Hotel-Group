/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Customer;
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
public class CustomerSessionTest {

    static EJBContainer container;
    static CustomerSessionLocal instance;

    static Long ID1 = 10000001L;
    static Long ID2 = 10000002L;

    static String EMAIL1 = "test1@test.com";
    static String EMAIL2 = "test2@test.com";


    static String MOBILE1 = "96454554";
    static String MOBILE2 = "96454555";

    static String PASSWORD = "password";

    static int POINTS = 10000;

    public CustomerSessionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(
                "org.glassfish.ejb.embedded.glassfish.configuration.file",
                "C:\\Users\\MC\\AppData\\Roaming\\NetBeans\\8.2\\config\\GF_4.1\\domain1\\config\\domain.xml"
        );//custom glassfish 4.1 path
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
        instance = (CustomerSessionLocal) container.getContext().lookup("java:global/classes/CustomerSession");

    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        //instance.deleteCustomer(ID1);
        instance.deleteCustomer(ID2);
        container.close();
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void a_testCreateCustomer() throws Exception {
        int sizeBefore = instance.getAllCustomers().size();
        Customer c1 = new Customer(ID1, "Test1", "Test1", "Male", PASSWORD, POINTS, EMAIL1, MOBILE1, new Date(), true);
        Customer c2 = new Customer(ID2, "Test2", "Test2", "Male", PASSWORD, POINTS, EMAIL2, MOBILE2, new Date(), true);
        instance.createCustomer(c1);
        instance.createCustomer(c2);
        int sizeAfter = instance.getAllCustomers().size();
        int exp = sizeBefore + 2;
        assertEquals(exp, sizeAfter);
    }

    @Test
    public void b_testGetAllCustomers() throws Exception {
        List<Customer> result = instance.getAllCustomers();
        assertFalse(result.isEmpty());
    }

    @Test
    public void c_testGetCustomerByID() throws Exception {
        Customer result = instance.getCustomerByID(ID1);
        String exp = "Test1";
        assertEquals(exp, result.getFirstName());
    }


    @Test
    public void e_testGetCustomerByEmail() throws Exception {
        Customer result = instance.getCustomerByEmail(EMAIL1);
        String exp = "Test1";
        assertEquals(exp, result.getFirstName());
    }

    @Test
    public void f_testGetCustomerByMobileNum() throws Exception {
        Customer result = instance.getCustomerByMobileNum(MOBILE1);
        String exp = "Test1";
        assertEquals(exp, result.getFirstName());
    }


    @Test
    public void h_testDeleteCustomer() throws Exception {
        int sizeBefore = instance.getAllCustomers().size();
        instance.deleteCustomer(ID1);
        int sizeAfter = instance.getAllCustomers().size();
        int exp = sizeBefore - 1;
        assertEquals(exp, sizeAfter);

    }

    @Test
    public void i_testUpdateCustomer() throws Exception {
        Customer result = instance.getCustomerByID(ID2);
        String exp = "newEmail@test.com";
        result.setEmail(exp);
        instance.updateCustomer(result);
        Customer newResult = instance.getCustomerByID(ID2);
        assertEquals(exp, newResult.getEmail());

    }

    @Test
    public void j_testLoginTrue() throws Exception {
        boolean exp = true;
        Customer c = new Customer("newEmail@test.com", PASSWORD);
        boolean result = instance.Login(c);
        assertEquals(exp, result);

    }

    @Test
    public void k_testLoginFalse() throws Exception {
        boolean exp = false;
        Customer c = new Customer("newEmail@test.com", "wrong password");
        boolean result = instance.Login(c);
        assertEquals(exp, result);

    }

    @Test
    public void l_testChangePasword() throws Exception {
        Customer c = instance.getCustomerByID(ID2);
        String exp = "newpassword";
        instance.changePasword(c, exp);
        String result = instance.getCustomerByID(ID2).getPassword();
        assertEquals(exp, result);
    }

    @Test
    public void m_testDeactivateAccount() throws Exception {
        instance.deactivateAccount(ID2);
        boolean result = instance.getCustomerByID(ID2).getAccountStatus();
        boolean exp = false;
        assertEquals(exp, result);
    }

    @Test
    public void n_testActivateAccount() throws Exception {
        instance.activateAccount(ID2);
        boolean result = instance.getCustomerByID(ID2).getAccountStatus();
        boolean exp = true;
        assertEquals(exp, result);
    }


}
