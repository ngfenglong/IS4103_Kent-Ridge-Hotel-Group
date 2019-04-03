/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.FoodMenuItem;
import entity.HouseKeepingOrder;
import entity.Room;
import error.NoResultException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import sessionBeans.FoodMenuItemSessionLocal;
import sessionBeans.HouseKeepingOrderSessionLocal;
import sessionBeans.LogSessionLocal;
import sessionBeans.RoomSessionLocal;
/**
 *
 * @author dk349
 */
@ManagedBean
@SessionScoped
public class HotelStayManagedBean implements Serializable {

    /**
     * @return the bookedLaundryOrder
     */
    public HouseKeepingOrder getBookedLaundryOrder() {
        return bookedLaundryOrder;
    }

    /**
     * @param bookedLaundryOrder the bookedLaundryOrder to set
     */
    public void setBookedLaundryOrder(HouseKeepingOrder bookedLaundryOrder) {
        this.bookedLaundryOrder = bookedLaundryOrder;
    }

    /**
     * @return the bookedHouseKeepingOrder
     */
    public HouseKeepingOrder getBookedHouseKeepingOrder() {
        return bookedHouseKeepingOrder;
    }

    /**
     * @param bookedHouseKeepingOrder the bookedHouseKeepingOrder to set
     */
    public void setBookedHouseKeepingOrder(HouseKeepingOrder bookedHouseKeepingOrder) {
        this.bookedHouseKeepingOrder = bookedHouseKeepingOrder;
    }

    
    @EJB
    private LogSessionLocal logSessionLocal;
    
    @EJB
    private FoodMenuItemSessionLocal foodMenuItemSessionLocal;
    
    @EJB
    private RoomSessionLocal roomSessionLocal;
    
    @EJB
    private HouseKeepingOrderSessionLocal houseKeepingSessionLocal;
    
    private Room currentRoom = null;
    private String username = null;
    private String password = null;
    private HashMap<Long,Integer> foodMenuOrder = new HashMap<>();
    private double totalPrice;
    private boolean extraTowels = false;
    private boolean extraShowerAmenities = false;
    private boolean restockMinibar = false;
    private String houseKeepingRequestDetails = "";
    private SimpleDateFormat laundryCollectionDate;
    private SimpleDateFormat laundryCollectionTime;
    private List<FoodMenuItem> foodMenuItemList;
    private HouseKeepingOrder bookedHouseKeepingOrder;
    private HouseKeepingOrder bookedLaundryOrder;
    
                          
    public HotelStayManagedBean() {
    }
    
    @PostConstruct
    public void init() {
        try {
            setFoodMenuItemList(foodMenuItemSessionLocal.getAllFoodMenuItems());
            System.out.println("Food Menu Item: " + foodMenuItemList);
        }catch(Exception e){
            System.out.println("No Result found");
        }
    }
    
    public String login() {
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        try {
            if(username.equals(roomSessionLocal.getRoomByName(username).getRoomName()) && password.equals(roomSessionLocal.getRoomByName(username).getRoomName())) {
                setCurrentRoom(roomSessionLocal.getRoomByName(username));
                return "index.xhtml?faces-redirect=true";
            } 
        } catch (Exception e) {
            
        }
        return "login.xhtml?faces-redirect=true";
    }
    
    public void addToCart(FoodMenuItem foodMenuItem) {
        if (foodMenuOrder.containsKey(foodMenuItem.getFoodMenuItemID())) {
            int currentQty = foodMenuOrder.get(foodMenuItem.getFoodMenuItemID());
            foodMenuOrder.replace(foodMenuItem.getFoodMenuItemID(), currentQty+1);      
        } else {
            foodMenuOrder.put(foodMenuItem.getFoodMenuItemID(), 1);      
        }   
        
        totalPrice = totalPrice + foodMenuItem.getUnitPrice();
        
    }
    
    public void updateQty(FoodMenuItem foodMenuItem, int quantity) {
        totalPrice = totalPrice - foodMenuOrder.get(foodMenuItem.getFoodMenuItemID()*foodMenuItem.getUnitPrice());
        foodMenuOrder.replace(foodMenuItem.getFoodMenuItemID(), quantity);
        totalPrice = totalPrice + foodMenuOrder.get(foodMenuItem.getFoodMenuItemID()*foodMenuItem.getUnitPrice());
    }
    
    public void checkOutOrder() {
        //ADD a total price to final payment!
        foodMenuOrder.clear();
    }
    
    public void bookHouseKeeping(SimpleDateFormat HousekeepingTime) {
        HouseKeepingOrder hkOrder = new HouseKeepingOrder();
        hkOrder.setOrderDateTime(new Date());
        hkOrder.setSpecialRequest(houseKeepingRequestDetails);
        hkOrder.setRoom(currentRoom);
        hkOrder.setLevel(0); //Need to set properly
        hkOrder.setStatus("incomplete");
        hkOrder.setRequestType("housekeeping");
        houseKeepingSessionLocal.createHouseKeepingOrder(hkOrder);
        setBookedHouseKeepingOrder(hkOrder);
    }
    
    public void editHouseKeepingOrder(SimpleDateFormat HousekeepingTime) {
        try {
        getBookedHouseKeepingOrder().setOrderDateTime(new Date());
        getBookedHouseKeepingOrder().setSpecialRequest(houseKeepingRequestDetails);
        houseKeepingSessionLocal.updateHouseKeepingOrder(getBookedHouseKeepingOrder());
        setBookedHouseKeepingOrder(getBookedHouseKeepingOrder());
        } catch (Exception e) {
            
        }
    }
    
    public void cancelBookHouseKeeping() {
        try {
            houseKeepingSessionLocal.deleteHouseKeepingOrder(getBookedHouseKeepingOrder().getHouseKeepingOrderID());
        } catch (Exception e) {
            
        }
    }
    
    public void bookLaundryService() {
        HouseKeepingOrder hkOrder = new HouseKeepingOrder();
        hkOrder.setOrderDateTime(new Date());
        hkOrder.setSpecialRequest(houseKeepingRequestDetails);
        hkOrder.setRoom(currentRoom);
        hkOrder.setLevel(0); //Need to set properly
        hkOrder.setStatus("incomplete");
        hkOrder.setRequestType("laundry");
        houseKeepingSessionLocal.createHouseKeepingOrder(hkOrder);
        setBookedHouseKeepingOrder(hkOrder);
    }
    
    public void cancelLaundryService() {
        try {
            houseKeepingSessionLocal.deleteHouseKeepingOrder(getBookedLaundryOrder().getHouseKeepingOrderID());
        } catch (Exception e) {
            
        }
    }
    
    public void editLaundryService(SimpleDateFormat HousekeepingTime) {
        try {
        getBookedHouseKeepingOrder().setOrderDateTime(new Date());
        getBookedHouseKeepingOrder().setSpecialRequest(houseKeepingRequestDetails);
        houseKeepingSessionLocal.updateHouseKeepingOrder(getBookedHouseKeepingOrder());
        setBookedHouseKeepingOrder(getBookedHouseKeepingOrder());
        } catch (Exception e) {
            
        }
    }
    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the foodMenuOrder
     */
    public HashMap<Long,Integer> getFoodMenuOrder() {
        return foodMenuOrder;
    }

    /**
     * @param foodMenuOrder the foodMenuOrder to set
     */
    public void setFoodMenuOrder(HashMap<Long,Integer> foodMenuOrder) {
        this.foodMenuOrder = foodMenuOrder;
    }

    /**
     * @return the totalPrice
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return the extraTowels
     */
    public boolean isExtraTowels() {
        return extraTowels;
    }

    /**
     * @param extraTowels the extraTowels to set
     */
    public void setExtraTowels(boolean extraTowels) {
        this.extraTowels = extraTowels;
    }

    /**
     * @return the extraShowerAmenities
     */
    public boolean isExtraShowerAmenities() {
        return extraShowerAmenities;
    }

    /**
     * @param extraShowerAmenities the extraShowerAmenities to set
     */
    public void setExtraShowerAmenities(boolean extraShowerAmenities) {
        this.extraShowerAmenities = extraShowerAmenities;
    }

    /**
     * @return the restockMinibar
     */
    public boolean isRestockMinibar() {
        return restockMinibar;
    }

    /**
     * @param restockMinibar the restockMinibar to set
     */
    public void setRestockMinibar(boolean restockMinibar) {
        this.restockMinibar = restockMinibar;
    }

    /**
     * @return the houseKeepingRequestDetails
     */
    public String getHouseKeepingRequestDetails() {
        return houseKeepingRequestDetails;
    }

    /**
     * @param houseKeepingRequestDetails the houseKeepingRequestDetails to set
     */
    public void setHouseKeepingRequestDetails(String houseKeepingRequestDetails) {
        this.houseKeepingRequestDetails = houseKeepingRequestDetails;
    }

    /**
     * @return the laundryCollectionDate
     */
    public SimpleDateFormat getLaundryCollectionDate() {
        return laundryCollectionDate;
    }

    /**
     * @param laundryCollectionDate the laundryCollectionDate to set
     */
    public void setLaundryCollectionDate(SimpleDateFormat laundryCollectionDate) {
        this.laundryCollectionDate = laundryCollectionDate;
    }

    /**
     * @return the laundryCollectionTime
     */
    public SimpleDateFormat getLaundryCollectionTime() {
        return laundryCollectionTime;
    }

    /**
     * @param laundryCollectionTime the laundryCollectionTime to set
     */
    public void setLaundryCollectionTime(SimpleDateFormat laundryCollectionTime) {
        this.laundryCollectionTime = laundryCollectionTime;
    }

    /**
     * @return the foodMenuItemList
     */
    public List<FoodMenuItem> getFoodMenuItemList() {
        return foodMenuItemList;
    }

    /**
     * @param foodMenuItemList the foodMenuItemList to set
     */
    public void setFoodMenuItemList(List<FoodMenuItem> foodMenuItemList) {
        this.foodMenuItemList = foodMenuItemList;
    }
    
    /**
     * @return the currentRoom
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * @param currentRoom the currentRoom to set
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}
