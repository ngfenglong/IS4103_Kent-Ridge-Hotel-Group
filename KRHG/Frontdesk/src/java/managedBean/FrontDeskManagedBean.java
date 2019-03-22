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
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
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
@Named(value = "frontDeskManagedBean")
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
    private String passportNumber;
    private List<RoomBooking> todaysbookings;
    private List<RoomBooking> Searchbookings;
    private RoomBooking roombooking;

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

    public FrontDeskManagedBean() {
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
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

            Searchbookings = bookSessionLocal.getRoomBookingByPassportNum(passportNumber);
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
            maintainDescription = null;
            mlocation = null;
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

    public String createMaintainence() {
        MaintainenceOrder maintainence = new MaintainenceOrder();
        maintainence.setDateReported(new Date());
        maintainence.setDescription(maintainDescription);
        maintainence.setLocation(mlocation);
        maintainence.setStatus("Unresolve");
        maintainenceOrderSessionLocal.createMaintainenceOrder(maintainence);
        return "maintainenceDetails.xhtml";
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
}
