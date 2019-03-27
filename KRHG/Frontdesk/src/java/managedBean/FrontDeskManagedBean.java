/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.Customer;
import entity.HouseKeepingOrder;
import entity.LaundryOrder;
import entity.LostAndFoundReport;
import entity.MaintainenceOrder;
import entity.Room;
import entity.RoomBooking;
import error.NoResultException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import sessionBeans.BookingSessionLocal;
import sessionBeans.CustomerSessionLocal;
import sessionBeans.HouseKeepingOrderSessionLocal;
import sessionBeans.LaundrySessionLocal;
import sessionBeans.LostAndFoundSessionLocal;
import sessionBeans.MaintainenceOrderSessionLocal;
import sessionBeans.RoomSessionLocal;

/**
 *
 * @author Congx2
 */
@ManagedBean
@SessionScoped
public class FrontDeskManagedBean {

    /**
     * Creates a new instance of FrontDeskManagedBean
     */
    @EJB
    private RoomSessionLocal roomSessionLocal;
    @EJB
    private BookingSessionLocal bookSessionLocal;
    @EJB
    private CustomerSessionLocal customerSessionLocal;
    @EJB
    private LaundrySessionLocal laundrySessionLocal;
    @EJB
    private LostAndFoundSessionLocal lostAndFoundSessionLocal;
    @EJB
    private MaintainenceOrderSessionLocal maintainenceOrderSessionLocal;
    @EJB
    private HouseKeepingOrderSessionLocal houseKeepingOrderSessionLocal;

    //customer check in
    private String customerName;
    private String customerRoom;
    private String checkinPassportNumber;
    private List<RoomBooking> todaysbookings;
    private List<RoomBooking> Searchbookings;
    private RoomBooking roombooking;

    private List<RoomBooking> todayCheckOutRoom;

    //laundry 
    private List<LaundryOrder> allLaundryOrders;

    //manage account
    private List<Customer> allCustomer;

    //Lost and found
    private List<LostAndFoundReport> allLostAndFounds;
    private String lfreportType;
    private String lfItemName;
    private String lfContactNumber;
    private String lfDescription;

    //Maintenance
    private List<MaintainenceOrder> allMaintainenceOrders;
    private String mlocation;
    private String maintainDescription;

    //housekeeping
    private List<HouseKeepingOrder> allHousekeepingOrder;
    private List<Room> allOccupiedRooms;
    private String hkroom;
    private String hkSpecialRequest;

    //customer
    private String CreateCustomerEmail;
    private String CreateCustomerPassword;
    private String createCustomerMobileNumber;
    private String CreateCustomerName;
    private String createConfirmCustomerPassword;

    //customer edit
    private Customer editCustomer;
    private String editCustomerName;
    private int editCustomerPoint;
    private String editCustomerEmail;
    private String editCustomerMobileNumber;
    private boolean editCustomerStatus;

    public FrontDeskManagedBean() {
    }

    public String getPassportNumber() {
        return checkinPassportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.checkinPassportNumber = passportNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String CustomerName) {
        this.customerName = CustomerName;
    }

    public String getCustomerRoom() {
        return customerRoom;
    }

    public void setCustomerRoom(String customerRoom) {
        this.customerRoom = customerRoom;
    }

    public String searchCustomerForCheckin() {
        try {

            Searchbookings = bookSessionLocal.getRoomBookingByPassportNum(checkinPassportNumber);
            checkinPassportNumber = null;
            return "checkinResult.xhtml";
        } catch (NoResultException e) {
            return "checkinResult.xhtml";
        }
    }

    public List<RoomBooking> getTodaysbookings() {
        return todaysbookings;
    }

    public void setTodaysbookings(List<RoomBooking> todaysbookings) {
        this.todaysbookings = todaysbookings;
    }

    public List<RoomBooking> getSearchbookings() {
        return Searchbookings;
    }

    public void setSearchbookings(List<RoomBooking> Searchbookings) {
        this.Searchbookings = Searchbookings;
    }

    public List<Customer> getAllCustomer() {
        return allCustomer = customerSessionLocal.getAllCustomers();
    }

    public void setAllCustomer(List<Customer> allCustomer) {
        this.allCustomer = allCustomer;
    }

    public List<RoomBooking> getTodayBooking() {
        try {
            return todaysbookings = bookSessionLocal.getAllRoomBookingByDate();
        } catch (NoResultException e) {
            return todaysbookings = null;
        }
    }

    public List<LaundryOrder> getAllLaundryOrders() {
        return allLaundryOrders = laundrySessionLocal.getAllLaundryOrder();
    }

    public void setAllLaundryOrders(List<LaundryOrder> allLaundryOrders) {
        this.allLaundryOrders = allLaundryOrders;
    }

    public List<LostAndFoundReport> getAllLostAndFounds() {
        try {
            return allLostAndFounds = lostAndFoundSessionLocal.getAllLostAndFoundReport();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void setAllLostAndFounds(List<LostAndFoundReport> allLostAndFounds) {
        this.allLostAndFounds = allLostAndFounds;
    }

    public String getLfreportType() {
        return lfreportType;
    }

    public void setLfreportType(String lfreportType) {
        this.lfreportType = lfreportType;
    }

    public String getLfItemName() {
        return lfItemName;
    }

    public void setLfItemName(String lfItemName) {
        this.lfItemName = lfItemName;
    }

    public String getLfContactNumber() {
        return lfContactNumber;
    }

    public void setLfContactNumber(String lfContactNumber) {
        this.lfContactNumber = lfContactNumber;
    }

    public String getLfDescription() {
        return lfDescription;
    }

    public void setLfDescription(String lfDescription) {
        this.lfDescription = lfDescription;
    }

    public String makeLFRequest() {
        LostAndFoundReport lf = new LostAndFoundReport();
        lf.setItemName(lfItemName);
        lf.setReportType(lfreportType);
        lf.setContactNum(lfContactNumber);
        lf.setItemDescription(lfDescription);
        lf.setIsResolved(false);
        lf.setReportedDate(new Date());

        lostAndFoundSessionLocal.createLostAndFoundReport(lf);
        return "lostAndFoundDetails.xhtml";
    }

    public List<MaintainenceOrder> getAllMaintainenceOrders() {
        try {

            return allMaintainenceOrders = maintainenceOrderSessionLocal.getAllMaintainenceOrder();

        } catch (NoResultException e) {
            return null;
        }
    }

    public void setAllMaintainenceOrders(List<MaintainenceOrder> allMaintainenceOrders) {
        this.allMaintainenceOrders = allMaintainenceOrders;
    }

    public String getMlocation() {
        return mlocation;
    }

    public void setMlocation(String mlocation) {
        this.mlocation = mlocation;
    }

    public String getMaintainDescription() {
        return maintainDescription;
    }

    public void setMaintainDescription(String maintainDescription) {
        this.maintainDescription = maintainDescription;
    }

    public String createMaintainence() throws IOException {

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        PrintWriter out = response.getWriter();
        MaintainenceOrder maintainence = new MaintainenceOrder();
        maintainence.setDateReported(new Date());
        maintainence.setDescription(maintainDescription);
        maintainence.setLocation(mlocation);
        maintainence.setStatus("Unresolve");

        maintainDescription = null;
        mlocation = null;
        maintainenceOrderSessionLocal.createMaintainenceOrder(maintainence);

        out.println("<script type=\"text/javascript\">");
        out.println("alert('Register Succesful!');");
        out.println("</script>");

        return "maintenance.xhtml?faces-redirect=true";
    }

    public List<HouseKeepingOrder> getAllHousekeepingOrder() {
        try {
            return allHousekeepingOrder = houseKeepingOrderSessionLocal.getAllHouseKeepingOrder();
        } catch (NoResultException e) {
            return allHousekeepingOrder = null;
        }
    }

    public void setAllHousekeepingOrder(List<HouseKeepingOrder> allHousekeepingOrder) {
        this.allHousekeepingOrder = allHousekeepingOrder;
    }

    public List<Room> getAllOccupiedRooms() {
        return allOccupiedRooms = roomSessionLocal.getAllRooms();
    }

    public void setAllOccupiedRooms(List<Room> allOccupiedRooms) {
        this.allOccupiedRooms = allOccupiedRooms;
    }

    public String getHkroom() {
        return hkroom;
    }

    public void setHkroom(String hkroom) {
        this.hkroom = hkroom;
    }

    public String getHkSpecialRequest() {
        return hkSpecialRequest;
    }

    public void setHkSpecialRequest(String hkSpecialRequest) {
        this.hkSpecialRequest = hkSpecialRequest;
    }
//stop here

    public String createHouseKeeping() {
        HouseKeepingOrder hkOrder = new HouseKeepingOrder();
        //  hkOrder.setRoom(roomSessionLocal.getRoom);
        hkOrder.setSpecialRequest(hkSpecialRequest);
        return null;
    }

    public String createAccount() throws IOException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        PrintWriter out = response.getWriter();

        if (CreateCustomerPassword.equals(createConfirmCustomerPassword)) {
            Customer c = new Customer();
            c.setEmail(CreateCustomerEmail);
            c.setMobileNum(createCustomerMobileNumber);
            c.setName(CreateCustomerName);
            c.setPassword(encryptPassword(CreateCustomerPassword));
            c.setAccountStatus(true);
            c.setPoints(0);
            c.setDateJoined(java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            customerSessionLocal.createCustomer(c);

            CreateCustomerEmail = null;
            createCustomerMobileNumber = null;
            CreateCustomerName = null;
            CreateCustomerPassword = null;
            createConfirmCustomerPassword = null;

            out.println("<script type=\"text/javascript\">");
            out.println("alert('Register Succesful!');");
            out.println("</script>");

            return "walkin.xhtml?faces-redirect=true";

        } else {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Confirm Password doesn't match! !');");
            out.println("</script>");

            return "customerAccount.xhtml?faces-redirect=true";
        }
    }

    public String viewAccount(Customer customer) {
        editCustomer = customer;
        editCustomerName = customer.getName();

        editCustomerPoint = customer.getPoints();
        editCustomerEmail = customer.getEmail();
        editCustomerMobileNumber = customer.getMobileNum();
        editCustomerStatus = customer.getAccountStatus();

        return "manageAccountEdit.xhtml?faces-redirect=true";
    }

    public String getEditCustomerName() {
        return editCustomerName;
    }

    public void setEditCustomerName(String editCustomerName) {
        this.editCustomerName = editCustomerName;
    }

    public int getEditCustomerPoint() {
        return editCustomerPoint;
    }

    public void setEditCustomerPoint(int editCustomerPoint) {
        this.editCustomerPoint = editCustomerPoint;
    }

    public String getEditCustomerEmail() {
        return editCustomerEmail;
    }

    public void setEditCustomerEmail(String editCustomerEmail) {
        this.editCustomerEmail = editCustomerEmail;
    }

    public String getEditCustomerMobileNumber() {
        return editCustomerMobileNumber;
    }

    public void setEditCustomerMobileNumber(String editCustomerMobileNumber) {
        this.editCustomerMobileNumber = editCustomerMobileNumber;
    }

    public boolean isEditCustomerStatus() {
        return editCustomerStatus;
    }

    public void setEditCustomerStatus(boolean editCustomerStatus) {
        this.editCustomerStatus = editCustomerStatus;
    }

    public String getCreateCustomerEmail() {
        return CreateCustomerEmail;
    }

    public void setCreateCustomerEmail(String CreateCustomerEmail) {
        this.CreateCustomerEmail = CreateCustomerEmail;
    }

    public String getCreateCustomerPassword() {
        return CreateCustomerPassword;
    }

    public void setCreateCustomerPassword(String CreateCustomerPassword) {
        this.CreateCustomerPassword = CreateCustomerPassword;
    }

    public String getCreateCustomerMobileNumber() {
        return createCustomerMobileNumber;
    }

    public void setCreateCustomerMobileNumber(String createCustomerMobileNumber) {
        this.createCustomerMobileNumber = createCustomerMobileNumber;
    }

    public String getCreateCustomerName() {
        return CreateCustomerName;
    }

    public void setCreateCustomerName(String CreateCustomerName) {
        this.CreateCustomerName = CreateCustomerName;
    }

    public String getCreateConfirmCustomerPassword() {
        return createConfirmCustomerPassword;
    }

    public void setCreateConfirmCustomerPassword(String createConfirmCustomerPassword) {
        this.createConfirmCustomerPassword = createConfirmCustomerPassword;
    }

    public String UpdateAccount() throws NoResultException, IOException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        PrintWriter out = response.getWriter();
        editCustomer.setAccountStatus(editCustomerStatus);
        editCustomer.setName(editCustomerName);
        editCustomer.setEmail(editCustomerEmail);
        editCustomer.setPoints(editCustomerPoint);
        editCustomer.setMobileNum(editCustomerMobileNumber);
        customerSessionLocal.updateCustomer(editCustomer);

        return "managedAccount.xhtml?faces-redirect=true";
    }

    private static String encryptPassword(String password) {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sha1;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    public String getCheckinPassportNumber() {
        return checkinPassportNumber;
    }

    public void setCheckinPassportNumber(String checkinPassportNumber) {
        this.checkinPassportNumber = checkinPassportNumber;
    }

    public List<RoomBooking> getTodayCheckOutRoom() {
        // return todayCheckOutRoom = bookSessionLocal.getAllRoomBookingByCheckOutDate(todayDate);

        return null;
    }

   // public String checkOut(RoomBooking checkoutroombookings) {

    //}

    public void setTodayCheckOutRoom(List<RoomBooking> todayCheckOutRoom) {
        this.todayCheckOutRoom = todayCheckOutRoom;
    }

}
