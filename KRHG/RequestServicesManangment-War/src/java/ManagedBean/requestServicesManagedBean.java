/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import entity.Hotel;
import entity.HouseKeepingOrder;
import entity.MinibarItem;
import entity.Staff;
import error.NoResultException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import sessionBeans.HotelSessionLocal;
import sessionBeans.HouseKeepingOrderSessionLocal;
import sessionBeans.MaintainenceOrderSessionLocal;
import sessionBeans.RoomSessionLocal;
import sessionBeans.StaffSessionLocal;

/**
 *
 * @author Congx2
 */
@ManagedBean
@SessionScoped
public class requestServicesManagedBean implements Serializable {

    @EJB
    private MaintainenceOrderSessionLocal maintainenceOrdersessionlocal;
    @EJB
    private HouseKeepingOrderSessionLocal housekeepingsessionlocal;
    @EJB
    private HotelSessionLocal hotelSessionlocal;
    @EJB
    private StaffSessionLocal staffsession;
    @EJB
    private RoomSessionLocal roomsessionlocal;
    /**
     * Creates a new instance of requestServicesManagedBean
     */
    private String hotelCode = "KRN";
    private String hotelGroup;

    //housekeeping
    private List<HouseKeepingOrder> allHousekeepingOrders;
    private List<HouseKeepingOrder> getIncompleteHousekeepingOrders;
    private List<Staff> getHousekeepingStaff;
    private Staff assignedHouseKeeper;

    //inventory 
    private List<MinibarItem> getMiniBarItems;

    public requestServicesManagedBean() {

    }

    public List<HouseKeepingOrder> getAllHousekeepingOrders() throws NoResultException {
        return housekeepingsessionlocal.getAllHouseKeepingOrder();
    }

    public String hotelCodeToHotelGroup(String hotelCode) {
        for (Hotel h : hotelSessionlocal.getAllHotels()) {
            if (h.getHotelCodeName().equalsIgnoreCase(hotelCode)) {
                return h.getHotelName();
            }
        }
        return null;
    }

    public List<HouseKeepingOrder> getIncompleteHousekeepingOrders() throws NoResultException {
        List<HouseKeepingOrder> newList = new ArrayList<>();

        for (HouseKeepingOrder ho : housekeepingsessionlocal.getAllHouseKeepingOrder()) {
            if (ho.getStatus().equalsIgnoreCase("incomplete")) {
                newList.add(ho);
            }
        }
        return newList;
    }

    public void setAllHousekeepingOrders(List<HouseKeepingOrder> allHousekeepingOrders) {
        this.allHousekeepingOrders = allHousekeepingOrders;
    }

    public List<HouseKeepingOrder> getGetIncompleteHousekeepingOrders() {
        return getIncompleteHousekeepingOrders;
    }

    public void setGetIncompleteHousekeepingOrders(List<HouseKeepingOrder> getIncompleteHousekeepingOrders) {
        this.getIncompleteHousekeepingOrders = getIncompleteHousekeepingOrders;
    }

    public List<Staff> getGetHousekeepingStaff() {
        return getHousekeepingStaff = getStaffBasedOnDepartmentAndHotelCode("Housekeeping");
    }

    public void setGetHousekeepingStaff(List<Staff> getHousekeepingStaff) {
        this.getHousekeepingStaff = getHousekeepingStaff;
    }

    public List<Staff> getStaffBasedOnDepartmentAndHotelCode(String department) {
        hotelGroup = hotelCodeToHotelGroup(hotelCode);
        List<Staff> newlist = new ArrayList<>();
        for (Staff s : staffsession.getAllStaffs()) {
            if (s.getHotelGroup().equalsIgnoreCase(hotelGroup)) {
                newlist.add(s);
            }
        }
        return newlist;
    }

    public void valueChangeMethod(ValueChangeEvent e) {
        System.out.println(e.getNewValue());
        
        System.out.println("hello");
    }

    public void update(HouseKeepingOrder ho) throws NoResultException {
        if (ho != null) {

            //Staff staff = staffsession.getStaffByID(assignedHouseKeeper);
            ho.setHouseKeeper(assignedHouseKeeper);
            System.out.println(assignedHouseKeeper.getName());
            housekeepingsessionlocal.updateHouseKeepingOrder(ho);
        }
    }

    public String convertDateFormat(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("hh:mmaa");
        return dateFormat.format(date);
    }

    public Staff getAssignedHouseKeeper() {
        return assignedHouseKeeper;
    }

    public void setAssignedHouseKeeper(Staff assignedHouseKeeper) {
        this.assignedHouseKeeper = assignedHouseKeeper;
    }

    public void hello(Long staff, Long HKO) throws NoResultException {
        if (staff != null) {
            Staff staffy = staffsession.getStaffByID(staff);
            HouseKeepingOrder hk = housekeepingsessionlocal.getHouseKeepingOrderID(HKO);
            hk.setHouseKeeper(staffy);
            HouseKeepingOrder jk = new HouseKeepingOrder();
            housekeepingsessionlocal.createHouseKeepingOrder(jk);
            housekeepingsessionlocal.updateHouseKeepingOrder(hk);
        }
    }

    public List<MinibarItem> getGetMiniBarItems() {
        return getMiniBarItems = roomsessionlocal.getAllMinibarItem();
    }

    public void setGetMiniBarItems(List<MinibarItem> getMiniBarItems) {
        this.getMiniBarItems = getMiniBarItems;
    }

    public double calculation(int number) {
        Double change = new Double(number);
        double result = (change / 1000) * 10;

        return result;
    }
}
