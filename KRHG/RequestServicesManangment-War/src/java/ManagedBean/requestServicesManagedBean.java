/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import entity.FoodMenuItem;
import entity.FoodOrder;
import entity.FoodOrderedItem;
import entity.Hotel;
import entity.HouseKeepingOrder;
import entity.LaundryOrder;
import entity.LaundryOrderedItem;
import entity.LaundryType;

import entity.RoomBooking;

import entity.MinibarStock;

import entity.Staff;
import error.NoResultException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.Part;
import sessionBeans.BookingSessionLocal;
import sessionBeans.FoodMenuItemSessionLocal;
import sessionBeans.FoodOrderSessionLocal;
import sessionBeans.HotelSessionLocal;
import sessionBeans.HouseKeepingOrderSessionLocal;
import sessionBeans.KitchenSessionLocal;
import sessionBeans.LaundrySessionLocal;
import sessionBeans.LogSessionLocal;
import sessionBeans.MaintainenceOrderSessionLocal;
import sessionBeans.RoomSessionLocal;
import sessionBeans.StaffSessionLocal;
import sun.misc.IOUtils;

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
    @EJB
    private BookingSessionLocal roomBookingSessionLocal;
    @EJB
    private KitchenSessionLocal kitchenSessionlocal;
    @EJB
    private FoodMenuItemSessionLocal foodMenuItemSessionLocal;
    @EJB
    private LogSessionLocal logSessionLocal;
    @EJB
    private FoodOrderSessionLocal foodOrderSessionLocal;
    @EJB
    private LaundrySessionLocal laundrySessionLocal;
    /**
     * Creates a new instance of requestServicesManagedBean
     */
    private String hotelCode = "KRN";
    private String hotelGroup;
    private boolean styleCheck = false;
    private boolean styleCheck2 = false;
    private boolean styleCheck3 = false;
    private String logActivityName;
    private String noImageStr = "Noimage.jpg";

    //housekeeping
    private List<HouseKeepingOrder> allHousekeepingOrders;
    private List<HouseKeepingOrder> getIncompleteHousekeepingOrders;
    private List<Staff> getHousekeepingStaff;
    private Staff assignedHouseKeeper;
    private String housekeeperName;

    //inventory 
    private List<MinibarStock> getMiniBarItems;
    private Integer[] minibarStockWithoutUpdate;
    private String updateMsg;

    //foodMenu
    private FoodMenuItem selectedFoodItem;
    private String newFoodName;
    private String newFoodCategory;
    private String newFoodDescription;
    private double newFoodPrice;
    private Part foodImage;

    //foodOrder
    private FoodOrder selectedFoodOrder;
    private Long selectedFoodOrderID;
//    private List <FoodOrderedItem> newFoodOrderedItems;
//    private String newFoodOrderRoomNumber;
//    private String newFoodOrderSpecialRequest;
//    private String newFoodOrderedPrice;

    //laundry
    private LaundryOrder selectedLaundryOrder;
    private Long selectedLaundryOrderID;
    private List<LaundryOrderedItem> listOfLaundryItems;
    private String specialLaundryRequest;
    private String selectedLaundryStaff;
    private String OWDWDesc;
    private int OWDWQty;
    private String OWWDesc;
    private int OWWQty;
    private String BDWDesc;
    private int BDWQty;
    private String BWDesc;
    private int BWQty;
    private String IWWDesc;
    private int IWWQty;
    private String IWDWDesc;
    private int IWDWQty;
    private String TWDesc;
    private int TWQty;
    private String TDWDesc;
    private int TDWQty;

    public requestServicesManagedBean() {

    }

    public String getStyleClass() {

        return styleCheck ? "cd-popup is-visible" : "cd-popup";

    }

    public String getStyleClass2() {
        return styleCheck2 ? "cd-popup is-visible" : "cd-popup";
    }

    public String getStyleClass3() {
        return styleCheck3 ? "cd-popup is-visible" : "cd-popup";
    }

    /**
     *
     */
    public void modalToggle(Long id) {
        selectedFoodOrderID = id;
        styleCheck = true;
    }

    public String getFoodOrderRoom(FoodOrder fo) throws NoResultException {
        List<RoomBooking> allRoomBookings = roomBookingSessionLocal.getAllRoomBooking();
        for (RoomBooking r : allRoomBookings) {
            for (FoodOrder f : r.getListOfFoodOrders()) {
                if (f.getFoodOrderID() == fo.getFoodOrderID()) {
                    return r.getBookedRoom().getRoomNumber();
                }
            }
        }
        return "";
    }

    public List<Staff> getAllLaundryStaff() {
        List<Staff> allstaff = staffsession.getAllStaffs();
        List<Staff> allLaundryStaff = new ArrayList();
        for (Staff s : allstaff) {
            if (s.getDepartment().toUpperCase().equals("LAUNDRY")) {
                allLaundryStaff.add(s);
            }
        }

        return allLaundryStaff;

    }

    public void updateLaundryOrder() throws NoResultException {
        System.out.println("in updateLaundryOrder");
        List<LaundryOrderedItem> allItems = new ArrayList();
        double totalPrice = 0;
        if (getTDWQty() != 0) {

            LaundryOrderedItem loi = new LaundryOrderedItem();
            loi.setQty(getTDWQty());
            loi.setDescription(getTDWDesc());
            loi.setLaundryType(laundrySessionLocal.getLaundryTypeByName("Top (Dry-Wash)"));
            laundrySessionLocal.createLaundryOrderedItem(loi);
            loi.setLaundryOrderedItemID(laundrySessionLocal.getLastLaundryOrderedItem().getLaundryOrderedItemID());
            allItems.add(loi);
            totalPrice += (TDWQty * laundrySessionLocal.getLaundryTypeByName("Top (Dry-Wash)").getPrice());
        }
        if (getIWWQty() != 0) {

            LaundryOrderedItem loi = new LaundryOrderedItem();
            loi.setQty(getIWWQty());
            loi.setDescription(getIWWDesc());
            loi.setLaundryType(laundrySessionLocal.getLaundryTypeByName("Inner Wear (Wash)"));
            laundrySessionLocal.createLaundryOrderedItem(loi);
            loi.setLaundryOrderedItemID(laundrySessionLocal.getLastLaundryOrderedItem().getLaundryOrderedItemID());
            allItems.add(loi);
            totalPrice += (IWWQty * laundrySessionLocal.getLaundryTypeByName("Inner Wear (Wash)").getPrice());
        }

        if (getBDWQty() != 0) {

            LaundryOrderedItem loi = new LaundryOrderedItem();
            loi.setQty(getBDWQty());
            loi.setDescription(getBDWDesc());
            loi.setLaundryType(laundrySessionLocal.getLaundryTypeByName("Bottom (Dry-Wash)"));
            laundrySessionLocal.createLaundryOrderedItem(loi);
            loi.setLaundryOrderedItemID(laundrySessionLocal.getLastLaundryOrderedItem().getLaundryOrderedItemID());
            allItems.add(loi);
            totalPrice += (BDWQty * laundrySessionLocal.getLaundryTypeByName("Bottom (Dry-Wash)").getPrice());
        }
        if (getOWDWQty() != 0) {

            LaundryOrderedItem loi = new LaundryOrderedItem();
            loi.setQty(getOWDWQty());
            loi.setDescription(getOWDWDesc());
            loi.setLaundryType(laundrySessionLocal.getLaundryTypeByName("Outer Wear (Dry-Wash)"));
            laundrySessionLocal.createLaundryOrderedItem(loi);
            loi.setLaundryOrderedItemID(laundrySessionLocal.getLastLaundryOrderedItem().getLaundryOrderedItemID());
            allItems.add(loi);
            totalPrice += (OWDWQty * laundrySessionLocal.getLaundryTypeByName("Outer Wear (Dry-Wash)").getPrice());
        }

        if (getIWDWQty() != 0) {

            LaundryOrderedItem loi = new LaundryOrderedItem();
            loi.setQty(getOWWQty());
            loi.setDescription(getIWDWDesc());
            loi.setLaundryType(laundrySessionLocal.getLaundryTypeByName("Inner Wear (Dry-Wash)"));
            laundrySessionLocal.createLaundryOrderedItem(loi);
            loi.setLaundryOrderedItemID(laundrySessionLocal.getLastLaundryOrderedItem().getLaundryOrderedItemID());
            allItems.add(loi);
            totalPrice += (IWDWQty * laundrySessionLocal.getLaundryTypeByName("Inner Wear (Dry-Wash)").getPrice());
        }
        if (getTWQty() != 0) {

            LaundryOrderedItem loi = new LaundryOrderedItem();
            loi.setQty(getTWQty());
            loi.setDescription(getTWDesc());
            loi.setLaundryType(laundrySessionLocal.getLaundryTypeByName("Top (Wash)"));
            laundrySessionLocal.createLaundryOrderedItem(loi);
            loi.setLaundryOrderedItemID(laundrySessionLocal.getLastLaundryOrderedItem().getLaundryOrderedItemID());
            allItems.add(loi);
            totalPrice += (TWQty * laundrySessionLocal.getLaundryTypeByName("Top (Wash)").getPrice());
        }
        if (getOWWQty() != 0) {

            LaundryOrderedItem loi = new LaundryOrderedItem();
            loi.setQty(getOWWQty());
            loi.setDescription(getOWWDesc());
            loi.setLaundryType(laundrySessionLocal.getLaundryTypeByName("Outer Wear (Wash)"));
            laundrySessionLocal.createLaundryOrderedItem(loi);
            loi.setLaundryOrderedItemID(laundrySessionLocal.getLastLaundryOrderedItem().getLaundryOrderedItemID());
            allItems.add(loi);
            totalPrice += (OWWQty * laundrySessionLocal.getLaundryTypeByName("Outer Wear (Wash)").getPrice());
        }
        if (getBWQty() != 0) {

            LaundryOrderedItem loi = new LaundryOrderedItem();
            loi.setQty(getBWQty());
            loi.setDescription(getBWDesc());
            loi.setLaundryType(laundrySessionLocal.getLaundryTypeByName("Bottom (Wash)"));
            laundrySessionLocal.createLaundryOrderedItem(loi);
            loi.setLaundryOrderedItemID(laundrySessionLocal.getLastLaundryOrderedItem().getLaundryOrderedItemID());
            allItems.add(loi);
            totalPrice += (BWQty * laundrySessionLocal.getLaundryTypeByName("Bottom (Wash)").getPrice());
        }

        LaundryOrder currentLaundryOrder = laundrySessionLocal.getLaundryOrderByID(selectedLaundryOrderID);
        currentLaundryOrder.setLaundryOrderedItems(allItems);
        currentLaundryOrder.setTotalPrice(totalPrice);
        currentLaundryOrder.setStatus("IN PROGRESS");

        TDWQty = 0;
        IWWQty = 0;
        BDWQty = 0;
        OWDWQty = 0;
        IWDWQty = 0;
        TWQty = 0;
        OWWQty = 0;
        BWQty = 0;

        TDWDesc = null;
        IWWDesc = null;
        BDWDesc = null;
        OWDWDesc = null;
        IWDWDesc = null;
        TWDesc = null;
        OWWDesc = null;
        BWDesc = null;

        System.out.println(currentLaundryOrder.getLaundryOrderID());
        laundrySessionLocal.updateLaundryOrder(currentLaundryOrder);
        System.out.println("currentLaundryStaffSelected:" + getSelectedLaundryStaff());
        currentLaundryOrder.setHouseKeeper(staffsession.getStaffByNric(getSelectedLaundryStaff()));
        System.out.println("currentLaundryStaffInput:" + currentLaundryOrder.getHouseKeeper().getName());
        currentLaundryOrder.setSpecialRequest(specialLaundryRequest);
        System.out.println(laundrySessionLocal.getLaundryOrderByID(currentLaundryOrder.getLaundryOrderID()));

    }

    public List<LaundryType> getAllLaundryTypes() {
        return laundrySessionLocal.getAllLaundryTypes();
    }

    public List<LaundryOrder> getAllLaundryOrdersByStatus(String status) {
        List<LaundryOrder> getLaundryOrders = laundrySessionLocal.getAllLaundryOrder();
        List<LaundryOrder> laundryOrdersWithStatus = new ArrayList();
        for (LaundryOrder lo : getLaundryOrders) {
            if (lo.getStatus().toUpperCase().equals(status)) {
                laundryOrdersWithStatus.add(lo);
            }
        }
        return laundryOrdersWithStatus;
    }

    public List<LaundryOrderedItem> getAllLaundryOrderedItems() throws NoResultException {
        List<LaundryOrderedItem> allLOI = laundrySessionLocal.getAllLaundryOrderedItemByLaundryOrderID(selectedLaundryOrderID);

        return allLOI;
    }

    public void minusqty(String variable) {

        if (variable.equals("TDWQty") && TDWQty != 0) {
            TDWQty -= 1;
        } else if (variable.equals("IWWQty") && IWWQty != 0) {
            IWWQty -= 1;
        } else if (variable.equals("BDWQty") && BDWQty != 0) {
            BDWQty -= 1;
        } else if (variable.equals("OWDWQty") && OWDWQty != 0) {
            OWDWQty -= 1;
        } else if (variable.equals("IWDWQty") && IWDWQty != 0) {
            IWDWQty -= 1;
        } else if (variable.equals("TWQty") && TWQty != 0) {
            TWQty -= 1;
        } else if (variable.equals("OWWQty") && OWWQty != 0) {
            OWWQty -= 1;
        } else if (variable.equals("BWQty") && BWQty != 0) {
            BWQty -= 1;
        }
    }

    public void plusqty(String variable) {

        if (variable.equals("TDWQty")) {
            TDWQty += 1;
            System.out.println("TDWQty" + TDWQty);
        } else if (variable.equals("IWWQty")) {
            IWWQty += 1;
            System.out.println("IWWQty:" + IWWQty);
        } else if (variable.equals("BDWQty")) {
            BDWQty += 1;
            System.out.println("BDWQty:" + BDWQty);
        } else if (variable.equals("OWDWQty")) {
            OWDWQty += 1;
            System.out.println("OWDWQty:" + OWDWQty);
        } else if (variable.equals("IWDWQty")) {
            IWDWQty += 1;
            System.out.println("IWDWQty:" + IWDWQty);
        } else if (variable.equals("TWQty")) {
            TWQty += 1;
            System.out.println("TWQty:" + TWQty);
        } else if (variable.equals("OWWQty")) {
            OWWQty += 1;
            System.out.println("OWWQty:" + OWWQty);
        } else if (variable.equals("BWQty")) {
            BWQty += 1;
            System.out.println("BWQty" + BWQty);
        }
    }

    public List<FoodOrder> getAllFoodOrders() {

        List<FoodOrder> allFoodOrders = foodOrderSessionLocal.getAllFoodOrder();
        List<FoodOrder> AllOngoingFoodOrders = new ArrayList();
        for (FoodOrder f : allFoodOrders) {
            if (f.getStatus().equals("ORDERED")) {

                AllOngoingFoodOrders.add(f);
            }
        }

        return AllOngoingFoodOrders;

    }

    public List<FoodOrder> getAllFoodDelivery() {

        List<FoodOrder> allFoodDelivery = foodOrderSessionLocal.getAllFoodOrder();
        List<FoodOrder> AllFoodDeliveries = new ArrayList();
        for (FoodOrder f : allFoodDelivery) {
            if (f.getStatus().equals("COMPLETE")) {

                AllFoodDeliveries.add(f);
            }
        }

        return AllFoodDeliveries;

    }

    public String markFoodOrderComplete() throws NoResultException {
        System.out.println("in mark food order complete");
        System.out.println(selectedFoodOrderID);
        FoodOrder newFO = new FoodOrder();
        try {
            newFO = foodOrderSessionLocal.getAllFoodOrderByID(selectedFoodOrderID);
            newFO.setStatus("COMPLETE");
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        selectedFoodOrderID = null;
        foodOrderSessionLocal.updateFoodOrder(newFO);

        return "foodOrder.xhtml?faces-redirect=true";
    }

    public String markFoodOrderDelivered(Long id) throws NoResultException {
        System.out.println("in mark food order delivered");
        System.out.println(id);
        FoodOrder newFO = new FoodOrder();
        try {
            newFO = foodOrderSessionLocal.getAllFoodOrderByID(id);
            newFO.setStatus("DELIVERED");
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        selectedFoodOrderID = null;
        foodOrderSessionLocal.updateFoodOrder(newFO);

        return "foodDelivered.xhtml?faces-redirect=true";
    }

    /**
     *
     * @return @throws NoResultException
     */
    public List<FoodMenuItem> getAllFoodMenuItemsOrdered(FoodOrder fo) throws NoResultException {

        List<FoodOrderedItem> foodOrdered = fo.getFoodOrdered();

        List<FoodMenuItem> fmiOrdered = new ArrayList();
        for (FoodOrderedItem f : foodOrdered) {
            if (f != null) {
                fmiOrdered.add(f.getFood());
            }
        }
        return fmiOrdered;
    }

    public List<FoodMenuItem> getFoodItemsFromFoodOrderID() throws NoResultException {

        FoodOrder fo = foodOrderSessionLocal.getAllFoodOrderByID(selectedFoodOrderID);
        List<FoodOrderedItem> foodOrdered = fo.getFoodOrdered();
        List<FoodMenuItem> fmiOrdered = new ArrayList();
        for (FoodOrderedItem f : foodOrdered) {
            if (f != null) {
                fmiOrdered.add(f.getFood());
            }
        }
        return fmiOrdered;
    }

    public List<FoodMenuItem> getAllDayBreakfastMenu() {

        List<FoodMenuItem> fullMenu = kitchenSessionlocal.getAllFoodMenuItem();
        List<FoodMenuItem> AllDayBreakfastMenu = new ArrayList();
        for (FoodMenuItem f : fullMenu) {
            if (f.getCategory().equalsIgnoreCase("All Day Breakfast") && f.getStatus() == true && f.getAvailability() == true) {
                AllDayBreakfastMenu.add(f);
            }
        }

        return AllDayBreakfastMenu;

    }

    public List<FoodMenuItem> getStartersMenu() {

        List<FoodMenuItem> fullMenu = kitchenSessionlocal.getAllFoodMenuItem();
        List<FoodMenuItem> startersMenu = new ArrayList();
        for (FoodMenuItem f : fullMenu) {
            if (f.getCategory().equalsIgnoreCase("Starters") && f.getStatus() == true && f.getAvailability() == true) {
                startersMenu.add(f);
            }
        }

        return startersMenu;

    }

    public String getSpecialRequest() throws NoResultException {
        FoodOrder foodOrder = foodOrderSessionLocal.getAllFoodOrderByID(selectedFoodOrderID);

        return foodOrder.getSpecialRequest();
    }

    public List<FoodMenuItem> getMainsMenu() {

        List<FoodMenuItem> fullMenu = kitchenSessionlocal.getAllFoodMenuItem();
        List<FoodMenuItem> mainsMenu = new ArrayList();
        for (FoodMenuItem f : fullMenu) {
            if (f.getCategory().equalsIgnoreCase("Mains") && f.getStatus() == true && f.getAvailability() == true) {
                mainsMenu.add(f);
            }
        }

        return mainsMenu;

    }

    public List<FoodMenuItem> getFOAMenu() {

        List<FoodMenuItem> fullMenu = kitchenSessionlocal.getAllFoodMenuItem();
        List<FoodMenuItem> FOAMenu = new ArrayList();
        for (FoodMenuItem f : fullMenu) {
            if (f.getCategory().equalsIgnoreCase("Flavours of Asia") && f.getStatus() == true && f.getAvailability() == true) {
                FOAMenu.add(f);
            }
        }

        return FOAMenu;

    }

    public List<FoodMenuItem> getSABMenu() {

        List<FoodMenuItem> fullMenu = kitchenSessionlocal.getAllFoodMenuItem();
        List<FoodMenuItem> SABMenu = new ArrayList();
        for (FoodMenuItem f : fullMenu) {
            if (f.getCategory().equalsIgnoreCase("Sandwiches and Burgers") && f.getStatus() == true && f.getAvailability() == true) {
                SABMenu.add(f);
            }
        }

        return SABMenu;

    }

    public List<FoodMenuItem> getAlcBeverageMenu() {

        List<FoodMenuItem> fullMenu = kitchenSessionlocal.getAllFoodMenuItem();
        List<FoodMenuItem> alcBeverageMenu = new ArrayList();
        for (FoodMenuItem f : fullMenu) {
            if (f.getCategory().equalsIgnoreCase("Beverages (Alcohol)") && f.getStatus() == true && f.getAvailability() == true) {
                alcBeverageMenu.add(f);
            }
        }

        return alcBeverageMenu;

    }

    public List<FoodMenuItem> getBeverageMenu() {

        List<FoodMenuItem> fullMenu = kitchenSessionlocal.getAllFoodMenuItem();
        List<FoodMenuItem> BeverageMenu = new ArrayList();
        for (FoodMenuItem f : fullMenu) {
            if (f.getCategory().equalsIgnoreCase("Beverages (Chilled Juices)") && f.getStatus() == true && f.getAvailability() == true) {
                BeverageMenu.add(f);
            }
        }

        return BeverageMenu;

    }

    public List<FoodMenuItem> getOOSMenu() {

        List<FoodMenuItem> fullMenu = kitchenSessionlocal.getAllFoodMenuItem();
        List<FoodMenuItem> OOSMenu = new ArrayList();
        for (FoodMenuItem f : fullMenu) {
            if (f.getAvailability() == false && f.getStatus() == true) {
                OOSMenu.add(f);
            }
        }

        return OOSMenu;

    }

    public String setUnavailable(Long fmiID) throws NoResultException {
        selectedFoodItem = foodMenuItemSessionLocal.getFoodMenuItemByID(fmiID);
        selectedFoodItem.setAvailability(false);
        foodMenuItemSessionLocal.updateFoodMenuItem(selectedFoodItem);
        return "foodMenu.xhtml?faces-redirect=true";
    }

    public String setAvailable(Long fmiID) throws NoResultException {
        selectedFoodItem = foodMenuItemSessionLocal.getFoodMenuItemByID(fmiID);
        selectedFoodItem.setAvailability(true);
        foodMenuItemSessionLocal.updateFoodMenuItem(selectedFoodItem);

        return "foodMenu.xhtml?faces-redirect=true";
    }

    public void selectFoodMenuItem(Long fmiID) throws NoResultException {
        styleCheck = true;
        selectedFoodItem = foodMenuItemSessionLocal.getFoodMenuItemByID(fmiID);
        System.out.println("Current Selection:" + selectedFoodItem.getFoodMenuItemName());

        System.out.println("in selectFoodMenuItem:" + selectedFoodItem.getFoodMenuItemName());
    }

    public void selectFoodMenuItem2(Long fmiID) throws NoResultException {
        styleCheck2 = true;
        selectedFoodItem = foodMenuItemSessionLocal.getFoodMenuItemByID(fmiID);
        System.out.println("Current Selection:" + selectedFoodItem.getFoodMenuItemName());

        System.out.println("in selectFoodMenuItem:" + selectedFoodItem.getFoodMenuItemName());
    }

    public void selectFoodOrder(Long foID) throws NoResultException {
        selectedFoodOrderID = foID;
        System.out.println("Current Selection:" + selectedFoodOrderID);

    }

    public void selectLaundryOrder(Long loID) throws NoResultException {
        selectedLaundryOrderID = loID;
        selectedLaundryOrder = laundrySessionLocal.getLaundryOrderByID(loID);
        System.out.println("laundryorderselected: " + selectedLaundryOrderID);
    }

    public void addFoodMenuItemToggle1() throws NoResultException {
        newFoodCategory = "All Day Breakfast";
        styleCheck3 = true;
        System.out.println(newFoodCategory);
    }

    public void addFoodMenuItemToggle2() throws NoResultException {
        newFoodCategory = "Starters";
        styleCheck3 = true;
    }

    public void addFoodMenuItemToggle3() throws NoResultException {
        newFoodCategory = "Mains";
        styleCheck3 = true;
    }

    public void addFoodMenuItemToggle4() throws NoResultException {
        newFoodCategory = "Flavours of Asia";
        styleCheck3 = true;
    }

    public void addFoodMenuItemToggle5() throws NoResultException {
        newFoodCategory = "Sandwiches and Burgers";
        styleCheck3 = true;
    }

    public void addFoodMenuItemToggle6() throws NoResultException {
        newFoodCategory = "Beverages (Alcohol)";
        styleCheck3 = true;
    }

    public void addFoodMenuItemToggle7() throws NoResultException {
        newFoodCategory = "Beverages (Chilled Juices)";
        styleCheck3 = true;
    }

    public String cancelingDeleteFoodItem() {
        styleCheck = false;
        return "foodMenu.xhtml?faces-redirect=true";
    }

    public String cancelingDeleteOrder() {
        styleCheck = false;
        return "foodOrder.xhtml?faces-redirect=true";
    }

    public String cancelingDeleteFoodItem2() {
        styleCheck2 = false;
        return "foodMenu.xhtml?faces-redirect=true";
    }

    public String cancelingAddFoodItem() {

        styleCheck3 = false;
        newFoodName = null;
        newFoodCategory = null;
        newFoodDescription = null;
        newFoodPrice = 0;
        System.out.println("styleCheck3");
        return "foodMenu.xhtml?faces-redirect=true";
    }

    public String deleteFoodOrder() throws NoResultException {

        FoodOrder fo = foodOrderSessionLocal.getAllFoodOrderByID(selectedFoodOrderID);
        fo.setStatus("CANCELLED");
        foodOrderSessionLocal.updateFoodOrder(fo);
        styleCheck = false;
        selectedFoodOrderID = null;
//        setLogActivityName(fo.getFoodOrderID().toString());
//        FacesContext context = FacesContext.getCurrentInstance();
//        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
//        Logging l = new Logging("Food Order", "Soft Delete " + getLogActivityName() + " from System", loggedInName);
//        logSessionLocal.createLogging(l);
        return "foodOrder.xhtml?faces-redirect=true";
    }

    public String deleteFoodItem() throws NoResultException {
        FoodMenuItem fmi = selectedFoodItem;
        System.out.println("Food menu item ID: " + fmi.getFoodMenuItemID());
        System.out.println("Food menu item Status: " + fmi.getStatus());
        fmi.setStatus(false);
        foodMenuItemSessionLocal.updateFoodMenuItem(fmi);
        setLogActivityName(fmi.getFoodMenuItemName());
        FacesContext context = FacesContext.getCurrentInstance();
//        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
//        Logging l = new Logging("Minibar", "Soft Delete " + getLogActivityName() + " from System", loggedInName);
//        logSessionLocal.createLogging(l);

        styleCheck = false;
        return "foodMenu.xhtml?faces-redirect=true";
    }

    public FoodMenuItem getSelectedFoodItem() {
        return selectedFoodItem;
    }

    public void setSelectedFoodItem(FoodMenuItem selectedFoodItem) {
        this.selectedFoodItem = selectedFoodItem;
    }

    public String saveFoodMenuItem() throws NoResultException {
        if (foodImage != null) {
            try {

                InputStream bytes = foodImage.getInputStream();
                //Files.copy(bytes, path, StandardCopyOption.REPLACE_EXISTING);

                URL ftp = new URL("ftp://zetegrdb:iqDcPqo8ornE@zetegral.website/public_html/krhgImages/" + foodImage.getSubmittedFileName());
                URLConnection conn = ftp.openConnection();
                conn.setDoOutput(true);
                OutputStream out = conn.getOutputStream();
                // Copy an InputStream to that OutputStream then
                out.write(IOUtils.readFully(bytes, -1, false));
                out.close();
                selectedFoodItem.setFoodImage(foodImage.getSubmittedFileName());

            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        foodMenuItemSessionLocal.updateFoodMenuItem(selectedFoodItem);
        styleCheck2 = false;
        FacesContext context = FacesContext.getCurrentInstance();
//        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
//        Logging l = new Logging("FoodMenuItem", "Update " + selectedFoodItem.getFoodMenuItemName()+ " details", loggedInName);
//        logSessionLocal.createLogging(l);
        selectedFoodItem = null;

        return "foodMenu.xhtml?faces-redirect=true";
    }

    public String addFoodMenuItem() throws NoResultException {
        FoodMenuItem fmi = new FoodMenuItem();
        String imgFile = getNoImageStr();
        fmi.setFoodMenuItemName(newFoodName);
        fmi.setFoodMenuItemDescription(newFoodDescription);
        fmi.setAvailability(Boolean.TRUE);
        fmi.setStatus(Boolean.TRUE);
        fmi.setUnitPrice(newFoodPrice);
        fmi.setCategory(newFoodCategory);

        if (foodImage != null) {
            imgFile = foodImage.getSubmittedFileName();
            try {

                InputStream bytes = foodImage.getInputStream();
                //Files.copy(bytes, path, StandardCopyOption.REPLACE_EXISTING);

                URL ftp = new URL("ftp://zetegrdb:iqDcPqo8ornE@zetegral.website/public_html/krhgImages/" + foodImage.getSubmittedFileName());
                URLConnection conn = ftp.openConnection();
                conn.setDoOutput(true);
                OutputStream out = conn.getOutputStream();
                // Copy an InputStream to that OutputStream then
                out.write(IOUtils.readFully(bytes, -1, false));
                out.close();

            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        fmi.setFoodImage(imgFile);
        foodMenuItemSessionLocal.createFoodMenuItem(fmi);

        logActivityName = newFoodName;
//        FacesContext context = FacesContext.getCurrentInstance();
//        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
//        Logging l = new Logging("Hotel", "Add " + logActivityName + " to System", loggedInName);
//        logSessionLocal.createLogging(l);

        newFoodCategory = null;
        newFoodDescription = null;
        newFoodName = null;
        newFoodPrice = 0;
        styleCheck3 = false;

        return "foodMenu.xhtml?faces-redirect=true";
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

    public List<HouseKeepingOrder> getGetIncompleteHousekeepingOrders() throws NoResultException {
        return getIncompleteHousekeepingOrders = getIncompleteHousekeepingOrders();
    }

    public void setGetIncompleteHousekeepingOrders(List<HouseKeepingOrder> getIncompleteHousekeepingOrders) {
        this.getIncompleteHousekeepingOrders = getIncompleteHousekeepingOrders;
    }

    public List<Staff> getGetHousekeepingStaff() {
        getHousekeepingStaff = getStaffBasedOnDepartmentAndHotelCode("Housekeeping");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("converter.StaffConverterv.staffs", getHousekeepingStaff);
        return getHousekeepingStaff;
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

    public void update(HouseKeepingOrder housekeeping) throws NoResultException {
        System.err.println("house: " + housekeeping);
        System.out.println("weifughweuiguwebguwbegwe");
        if (assignedHouseKeeper != null) {

            housekeeperName = assignedHouseKeeper.getName();
            //Staff staff = staffsession.getStaffByID(assignedHouseKeeper);

            housekeeping.setHouseKeeper(assignedHouseKeeper);

            housekeepingsessionlocal.updateHouseKeepingOrder(housekeeping);
        }
        styleCheck = true;
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

    public List<MinibarStock> getGetMiniBarItems() {
        return getMiniBarItems = getMinibarByHotelCode();
    }

    public void setGetMiniBarItems(List<MinibarStock> getMiniBarItems) {
        this.getMiniBarItems = getMiniBarItems;
    }

    public List<MinibarStock> getMinibarByHotelCode() {
        List<MinibarStock> newlist = new ArrayList<>();

        minibarStockWithoutUpdate = new Integer[6];
        int count = 0;
        for (MinibarStock ms : housekeepingsessionlocal.getAllMinibarStock()) {
            if (ms.getHotelCodeName().equals(hotelCode)) {
                newlist.add(ms);
                minibarStockWithoutUpdate[count] = 0;
                count++;
            }
        }
        return newlist;
    }

    public double calculation(int number) {
        Double change = new Double(number);
        double result = (change / 1000) * 10;

        return result;
    }

    /**
     * @return the logActivityName
     */
    public String getLogActivityName() {
        return logActivityName;
    }

    /**
     * @param logActivityName the logActivityName to set
     */
    public void setLogActivityName(String logActivityName) {
        this.logActivityName = logActivityName;
    }

    public boolean isStyleCheck() {
        return styleCheck;
    }

    public void setStyleCheck(boolean styleCheck) {
        this.styleCheck = styleCheck;
    }

    public boolean isStyleCheck2() {
        return styleCheck2;
    }

    public void setStyleCheck2(boolean styleCheck2) {
        this.styleCheck2 = styleCheck2;
    }

    /**
     * @return the foodImage
     */
    public Part getFoodImage() {
        return foodImage;
    }

    /**
     * @param foodImage the foodImage to set
     */
    public void setFoodImage(Part foodImage) {
        this.foodImage = foodImage;
    }

    /**
     * @return the noImageStr
     */
    public String getNoImageStr() {
        return noImageStr;
    }

    /**
     * @param noImageStr the noImageStr to set
     */
    public void setNoImageStr(String noImageStr) {
        this.noImageStr = noImageStr;
    }

    public String getHousekeeperName() {
        return housekeeperName;
    }

    public void setHousekeeperName(String housekeeperName) {
        this.housekeeperName = housekeeperName;
    }

    /**
     * @return the styleCheck3
     */
    public boolean isStyleCheck3() {
        return styleCheck3;
    }

    /**
     * @param styleCheck3 the styleCheck3 to set
     */
    public void setStyleCheck3(boolean styleCheck3) {
        this.styleCheck3 = styleCheck3;
    }

    /**
     * @return the newFoodName
     */
    public String getNewFoodName() {
        return newFoodName;
    }

    /**
     * @param newFoodName the newFoodName to set
     */
    public void setNewFoodName(String newFoodName) {
        this.newFoodName = newFoodName;
    }

    /**
     * @return the newFoodCategory
     */
    public String getNewFoodCategory() {
        return newFoodCategory;
    }

    /**
     * @param newFoodCategory the newFoodCategory to set
     */
    public void setNewFoodCategory(String newFoodCategory) {
        this.newFoodCategory = newFoodCategory;
    }

    /**
     * @return the newFoodDescription
     */
    public String getNewFoodDescription() {
        return newFoodDescription;
    }

    /**
     * @param newFoodDescription the newFoodDescription to set
     */
    public void setNewFoodDescription(String newFoodDescription) {
        this.newFoodDescription = newFoodDescription;
    }

    /**
     * @return the newFoodPrice
     */
    public double getNewFoodPrice() {
        return newFoodPrice;
    }

    /**
     * @param newFoodPrice the newFoodPrice to set
     */
    public void setNewFoodPrice(double newFoodPrice) {
        this.newFoodPrice = newFoodPrice;
    }

    /**
     * @return the selectedFoodOrder
     */
    public FoodOrder getSelectedFoodOrder() {
        return selectedFoodOrder;
    }

    /**
     * @param selectedFoodOrder the selectedFoodOrder to set
     */
    public void setSelectedFoodOrder(FoodOrder selectedFoodOrder) {
        this.selectedFoodOrder = selectedFoodOrder;
    }

    /**
     * @return the selectedFoodOrderID
     */
    public Long getSelectedFoodOrderID() {
        return selectedFoodOrderID;
    }

    /**
     * @param selectedFoodOrderID the selectedFoodOrderID to set
     */
    public void setSelectedFoodOrderID(Long selectedFoodOrderID) {
        this.selectedFoodOrderID = selectedFoodOrderID;

    }

    /**
     * @return the selectedLaundryOrder
     */
    public LaundryOrder getSelectedLaundryOrder() {
        return selectedLaundryOrder;
    }

    /**
     * @param selectedLaundryOrder the selectedLaundryOrder to set
     */
    public void setSelectedLaundryOrder(LaundryOrder selectedLaundryOrder) {
        this.selectedLaundryOrder = selectedLaundryOrder;
    }

    /**
     * @return the selectedLaundryOrderID
     */
    public Long getSelectedLaundryOrderID() {
        return selectedLaundryOrderID;
    }

    /**
     * @param selectedLaundryOrderID the selectedLaundryOrderID to set
     */
    public void setSelectedLaundryOrderID(Long selectedLaundryOrderID) {
        this.selectedLaundryOrderID = selectedLaundryOrderID;
    }

    /**
     * @return the listOfLaundryItems
     */
    public List<LaundryOrderedItem> getListOfLaundryItems() {
        return listOfLaundryItems;
    }

    /**
     * @param listOfLaundryItems the listOfLaundryItems to set
     */
    public void setListOfLaundryItems(List<LaundryOrderedItem> listOfLaundryItems) {
        this.listOfLaundryItems = listOfLaundryItems;
    }

    /**
     * @return the OWDWDesc
     */
    public String getOWDWDesc() {
        return OWDWDesc;
    }

    /**
     * @param OWDWDesc the OWDWDesc to set
     */
    public void setOWDWDesc(String OWDWDesc) {
        this.OWDWDesc = OWDWDesc;
    }

    /**
     * @return the OWDWQty
     */
    public int getOWDWQty() {
        return OWDWQty;
    }

    /**
     * @param OWDWQty the OWDWQty to set
     */
    public void setOWDWQty(int OWDWQty) {
        this.OWDWQty = OWDWQty;
    }

    /**
     * @return the OWWDesc
     */
    public String getOWWDesc() {
        return OWWDesc;
    }

    /**
     * @param OWWDesc the OWWDesc to set
     */
    public void setOWWDesc(String OWWDesc) {
        this.OWWDesc = OWWDesc;
    }

    /**
     * @return the OWWQty
     */
    public int getOWWQty() {
        return OWWQty;
    }

    /**
     * @param OWWQty the OWWQty to set
     */
    public void setOWWQty(int OWWQty) {
        this.OWWQty = OWWQty;
    }

    /**
     * @return the BDWDesc
     */
    public String getBDWDesc() {
        return BDWDesc;
    }

    /**
     * @param BDWDesc the BDWDesc to set
     */
    public void setBDWDesc(String BDWDesc) {
        this.BDWDesc = BDWDesc;
    }

    /**
     * @return the BDWQty
     */
    public int getBDWQty() {
        return BDWQty;
    }

    /**
     * @param BDWQty the BDWQty to set
     */
    public void setBDWQty(int BDWQty) {
        this.BDWQty = BDWQty;
    }

    /**
     * @return the IWWDesc
     */
    public String getIWWDesc() {
        return IWWDesc;
    }

    /**
     * @param IWWDesc the IWWDesc to set
     */
    public void setIWWDesc(String IWWDesc) {
        this.IWWDesc = IWWDesc;
    }

    /**
     * @return the IWWQty
     */
    public int getIWWQty() {
        return IWWQty;
    }

    /**
     * @param IWWQty the IWWQty to set
     */
    public void setIWWQty(int IWWQty) {
        this.IWWQty = IWWQty;
    }

    /**
     * @return the IWDWDesc
     */
    public String getIWDWDesc() {
        return IWDWDesc;
    }

    /**
     * @param IWDWDesc the IWDWDesc to set
     */
    public void setIWDWDesc(String IWDWDesc) {
        this.IWDWDesc = IWDWDesc;
    }

    /**
     * @return the IWDWQty
     */
    public int getIWDWQty() {
        return IWDWQty;
    }

    /**
     * @param IWDWQty the IWDWQty to set
     */
    public void setIWDWQty(int IWDWQty) {
        this.IWDWQty = IWDWQty;
    }

    /**
     * @return the TWDesc
     */
    public String getTWDesc() {
        return TWDesc;
    }

    /**
     * @param TWDesc the TWDesc to set
     */
    public void setTWDesc(String TWDesc) {
        this.TWDesc = TWDesc;
    }

    /**
     * @return the TWQty
     */
    public int getTWQty() {
        return TWQty;
    }

    /**
     * @param TWQty the TWQty to set
     */
    public void setTWQty(int TWQty) {
        this.TWQty = TWQty;
    }

    /**
     * @return the TDWDesc
     */
    public String getTDWDesc() {
        return TDWDesc;
    }

    /**
     * @param TDWDesc the TDWDesc to set
     */
    public void setTDWDesc(String TDWDesc) {
        this.TDWDesc = TDWDesc;
    }

    /**
     * @param TDWQty the TDWQty to set
     */
    public void setTDWQty(String TDWQty) {
        this.setTDWQty(TDWQty);
    }

    /**
     * @return the BWDesc
     */
    public String getBWDesc() {
        return BWDesc;
    }

    /**
     * @param BWDesc the BWDesc to set
     */
    public void setBWDesc(String BWDesc) {
        this.BWDesc = BWDesc;
    }

    /**
     * @return the BWQty
     */
    public int getBWQty() {
        return BWQty;
    }

    /**
     * @param BWQty the BWQty to set
     */
    public void setBWQty(int BWQty) {
        this.BWQty = BWQty;
    }

    /**
     * @param TDWQty the TDWQty to set
     */
    public void setTDWQty(int TDWQty) {
        this.TDWQty = TDWQty;
    }

    /**
     * @return the TDWQty
     */
    public int getTDWQty() {
        return TDWQty;
    }

    /**
     * @return the specialLaundryRequest
     */
    public String getSpecialLaundryRequest() {
        return specialLaundryRequest;
    }

    /**
     * @param specialLaundryRequest the specialLaundryRequest to set
     */
    public void setSpecialLaundryRequest(String specialLaundryRequest) {
        this.specialLaundryRequest = specialLaundryRequest;
    }

    /**
     * @return the selectedLaundryStaff
     */
    public String getSelectedLaundryStaff() {
        return selectedLaundryStaff;
    }

    /**
     * @param selectedLaundryStaff the selectedLaundryStaff to set
     */
    public void setSelectedLaundryStaff(String selectedLaundryStaff) {
        this.selectedLaundryStaff = selectedLaundryStaff;
    }

    public double defineTheNumber(int alert, int stock) {
        Double alertChange = new Double(alert);
        Double stockChange = new Double(stock);

        double stockDiff = (stockChange / alertChange) * 30;
        DecimalFormat decim = new DecimalFormat("0.00");
        return Double.parseDouble(decim.format(stockDiff));
    }

    public Integer[] getMinibarStockWithoutUpdate() {
        return minibarStockWithoutUpdate;
    }

    public String updateInventory() throws NoResultException {
        updateMsg = "There are no updates";
        for (int i = 0; i < 6; i++) {
            MinibarStock ms = getMiniBarItems.get(i);

            Integer current = ms.getCurrentStock();
            ms.setCurrentStock(current + minibarStockWithoutUpdate[i]);
            if (minibarStockWithoutUpdate[i] != 0) {
                updateMsg = "Minibar stock updated";
            }
            housekeepingsessionlocal.updateMinibarStock(ms);
            System.out.println(ms.getCurrentStock());
        }
        styleCheck = true;
        return "Inventory.xhtml";
    }

    public String inventoryOK() {
        styleCheck = false;

        return "Inventory.xhtml?faces-redirect=true";
    }

    public String housekeepingOK() {
        styleCheck = false;

        return "housekeeping.xhtml?faces-redirect=true";
    }

    public String getUpdateMsg() {
        return updateMsg;
    }

    public void setUpdateMsg(String updateMsg) {
        this.updateMsg = updateMsg;
    }

}
