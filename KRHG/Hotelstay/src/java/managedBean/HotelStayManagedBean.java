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
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import sessionBeans.FoodMenuItemSessionLocal;
import sessionBeans.HouseKeepingOrderSessionLocal;
import sessionBeans.LogSessionLocal;
import sessionBeans.RoomSessionLocal;

/**
 *
 * @author dk349
 */
@ManagedBean(name = "hotelStayManagedBean", eager = true)
@SessionScoped
public class HotelStayManagedBean implements Serializable {

    /**
     * @return the roomNumber
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * @param roomNumber the roomNumber to set
     */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
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
    private int quantity;
    private int totalQty;
    private double totalPrice;
    private double taxes;
    private double totalPriceWithTax;
    private boolean extraTowels = false;
    private boolean extraShowerAmenities = false;
    private boolean restockMinibar = false;
    private String houseKeepingRequestDetails = "";
    private Date laundryCollectionDate = new Date();
    private Date laundryCollectionTime = new Date();
    private Date houseKeepingCollectionTime = new Date();
    private List<FoodMenuItem> foodMenuItemList;
    private List<FoodMenuItem> breakfastItemList = new ArrayList();
    private List<FoodMenuItem> startersItemList = new ArrayList();
    private List<FoodMenuItem> sandwichesAndBurgersItemList = new ArrayList();
    private List<FoodMenuItem> asiaItemList = new ArrayList();
    private List<FoodMenuItem> mainsItemList = new ArrayList();
    private List<FoodMenuItem> alcoholItemList = new ArrayList();
    private List<FoodMenuItem> juicesItemList = new ArrayList();
    private List<FoodMenuItem> softDrinksItemList = new ArrayList();
    private List<FoodMenuItem> coffeeItemList = new ArrayList();
    private List<FoodMenuItem> teaItemList = new ArrayList();
    private List<FoodMenuItem> foodMenuOrderList = new ArrayList();
    private HashMap<Long, Integer> foodMenuOrder = new HashMap<>();
    private HouseKeepingOrder bookedHouseKeepingOrder;
    private HouseKeepingOrder bookedLaundryOrder;
    private String roomNumber;  

    public HotelStayManagedBean() {
    }

    @PostConstruct
    public void init() {
        try {
            setFoodMenuItemList(foodMenuItemSessionLocal.getAllFoodMenuItems());
            //breakfastItemList.add(foodMenuItemList.get(0));
            //System.out.println("Food Menu Item: " + foodMenuItemList);
            for (FoodMenuItem food : foodMenuItemList) {
                //System.out.println("Food Menu Item: " + food.getFoodMenuItemName());
                switch (food.getCategory()) {
                    case "All Day Breakfast":
                        breakfastItemList.add(food);
                        break;
                    case "Starters":
                        startersItemList.add(food);
                        break;
                    case "Sandwiches and Burgers":
                        sandwichesAndBurgersItemList.add(food);
                        break;
                    case "Flavours of Asia":
                        asiaItemList.add(food);
                        break;
                    case "Mains":
                        mainsItemList.add(food);
                        break;
                    case "Beverages (Alcohol)":
                        alcoholItemList.add(food);
                        break;
                    case "Beverages (Chilled Juices)":
                        juicesItemList.add(food);
                        break;
                    case "Soft Drinks":
                        softDrinksItemList.add(food);
                        break;
                    case "Coffee":
                        coffeeItemList.add(food);
                        break;
                    case "Tea":
                        teaItemList.add(food);
                        break;
                    default:
                        break;
                }
            }
            System.out.println("Breakfast: " + breakfastItemList);
            System.out.println("Starters: " + startersItemList);
            System.out.println("Sandwich: " + sandwichesAndBurgersItemList);
            System.out.println("Asia: " + asiaItemList);
            System.out.println("Mains: " + mainsItemList);
            System.out.println("Alcohol: " + alcoholItemList);
            System.out.println("Juices: " + juicesItemList);
            System.out.println("Soft Drinks: " + softDrinksItemList);
            System.out.println("Coffee: " + coffeeItemList);
            System.out.println("Tea: " + teaItemList);
        } catch (Exception e) {
            System.out.println("No Result found");
            System.out.println(e);
        }
    }

    public String login() throws NoResultException {
        System.out.println("3Username: " + username);
        System.out.println("4Password: " + password);
        System.out.println("Food Menu Item: " + foodMenuItemList);
        if (username != null && password != null) {
            try {
                setCurrentRoom(roomSessionLocal.getRoomByName(username));
                if (username.equals(getCurrentRoom().getRoomName()) && password.equals(getCurrentRoom().getRoomName())) {
                    setPassword("");
                    if(Integer.valueOf(currentRoom.getRoomNumber()) < 1000) {
                        setRoomNumber("0" + currentRoom.getRoomNumber());
                    } else {
                        setRoomNumber(currentRoom.getRoomNumber());
                    }
                    System.out.println("Credentials correct!");
                    System.out.println("Setting Room to: " + roomNumber);
                    return "index.xhtml?faces-redirect=true";
                }
            } catch (NoResultException | NumberFormatException e) {
                System.out.println("Invalid credentials!");
                //setCurrentRoom(null);
                setUsername("");
                setPassword("");
                return "login.xhtml";
            }
        } else {
            System.out.println("Username or password is empty returning back to login");
            return "login.xhtml";
        }
        return "login.xhtml";
    }

    public void addToCart(FoodMenuItem foodMenuItem) {      
        System.out.println("-------------------------------------");
        System.out.println("ADD TO CART");
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        Map<String,String> request = ctx.getExternalContext().getRequestParameterMap();
        System.out.println("Request: " + request.toString());
        String data = request.toString();
        //String data = request.get("foodMenuForm" + UINamingContainer.getSeparatorChar(ctx) + "qty"); //the id for the form : id of the text
        System.out.println("Data: " + data);
        String fields[] = data.split(",");
        String qtyKeyValue[] = fields[1].split("=");
        setQuantity(Integer.parseInt(qtyKeyValue[1]));
        System.out.println("Quantity: " + getQuantity());
        
        if (getQuantity() != 0) {
            if (foodMenuOrder.containsKey(foodMenuItem.getFoodMenuItemID())) {
                System.out.println("Existing Item");
                int currentQty = foodMenuOrder.get(foodMenuItem.getFoodMenuItemID());
                System.out.println("Current Quantity: " + currentQty);
                foodMenuOrder.replace(foodMenuItem.getFoodMenuItemID(), currentQty + getQuantity());
            } else {
                System.out.println("New Item");
                foodMenuOrder.put(foodMenuItem.getFoodMenuItemID(), getQuantity());
                getFoodMenuOrderList().add(foodMenuItem);
            }
        }

        System.out.println("Total Price: " + totalPrice);
        System.out.println("Unit Price: " + foodMenuItem.getUnitPrice());
        System.out.println("Quantity: " + getQuantity());
        totalQty = totalQty + getQuantity();
        totalPrice = totalPrice + (foodMenuItem.getUnitPrice() * getQuantity());
        taxes = 0.07 * totalPrice;
        totalPriceWithTax = 1.07 * totalPrice;
        setQuantity(0);
        System.out.println("Total Price: " + totalPrice);
        System.out.println("-------------------------------------");
    }

    public void updateQty(FoodMenuItem foodMenuItem, int quantity) {
        System.out.println("-------------------------------------");
        System.out.println("UPDATE QUANTITY");
        System.out.println("Quantity: " + quantity);
        totalQty = totalQty - quantity;
        totalPrice = totalPrice - foodMenuOrder.get(foodMenuItem.getFoodMenuItemID()) * foodMenuItem.getUnitPrice();
        System.out.println("Before Total Price: " + totalPrice);
        foodMenuOrder.replace(foodMenuItem.getFoodMenuItemID(), quantity);
        totalQty = totalQty + quantity;
        totalPrice = totalPrice + foodMenuOrder.get(foodMenuItem.getFoodMenuItemID()) * foodMenuItem.getUnitPrice();
        System.out.println("After Total Price: " + totalPrice);
        System.out.println("-------------------------------------");
    }

    public void checkOutOrder() {
        //ADD a total price to final payment! Send email receipt!
        System.out.println("-------------------------------------");
        System.out.println("CHECKOUT ORDER");
        foodMenuOrder.clear();
        foodMenuOrderList.clear();
        quantity = 0;
        totalQty = 0;
        totalPrice = 0;
        taxes = 0;
        totalPriceWithTax = 0;
        System.out.println("-------------------------------------");
    }

    public int getLevel(String roomnumber) {
        System.out.println("-------------------------------------");
        System.out.println("GET LEVEL");
        if (roomnumber.length() == 3) {
            return Integer.parseInt(roomnumber.substring(0, 1));
        } else {
            return Integer.parseInt(roomnumber.substring(0, 2));
        }
    }
    
    public void testingPurpose(ActionEvent event) {
        System.out.println("Testing purposes");
    }

    public void bookHouseKeeping() {
        System.out.println("Trying to book house keeping ");
        System.out.println("Book housekeeping: Setting Room to: " + currentRoom.getRoomNumber());
        System.out.println("Timing to book: " + houseKeepingCollectionTime.toString());
        System.out.println("Requests to book: " + houseKeepingRequestDetails);
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
        timeFormat.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
        dateTimeFormat.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));  
        String currentDate = dateFormat.format(new Date());
        String inputTime = timeFormat.format(houseKeepingCollectionTime);
        Date selectedDate = new Date();
        
        try {
            cal.setTime(dateFormat.parse(currentDate));
            cal.add(Calendar.DAY_OF_MONTH, 4);
            String deadline = dateFormat.format(cal.getTime());
            selectedDate = dateTimeFormat.parse(deadline + " " + inputTime);
            System.out.println("Current Date: " + currentDate);
            System.out.println("Deadline: " + deadline);
            System.out.println("Selected Date: " + selectedDate);
        } catch (ParseException ex) {
            Logger.getLogger(HotelStayManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
              
        HouseKeepingOrder hkOrder = new HouseKeepingOrder();
        hkOrder.setOrderDateTime(new Date());
        hkOrder.setCompleteDateTime(selectedDate);
        hkOrder.setSpecialRequest(houseKeepingRequestDetails);
        hkOrder.setRoom(currentRoom);
        hkOrder.setLevel(getLevel(currentRoom.getRoomNumber())); //Need to set properly
        hkOrder.setStatus("incomplete");
              
        if (houseKeepingRequestDetails.isEmpty()) {
            System.out.println("No Special Request!");
            hkOrder.setIsSpecialRequest(false);          
        } else {
            System.out.println("Special Requests: " + houseKeepingRequestDetails);
            hkOrder.setIsSpecialRequest(true);
            
            //Set boolean to extraTowels, extraShowerAmenities and restockMinibar
            
            if (extraTowels == true || extraShowerAmenities == true) {
                System.out.println("Toiletries Request");
                hkOrder.setRequestType("toiletries");
            }
            
            System.out.println("Housekeeping Request");
            hkOrder.setRequestType("housekeeping");
        }
        
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
    public HashMap<Long, Integer> getFoodMenuOrder() {
        return foodMenuOrder;
    }

    /**
     * @param foodMenuOrder the foodMenuOrder to set
     */
    public void setFoodMenuOrder(HashMap<Long, Integer> foodMenuOrder) {
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
    public Date getLaundryCollectionDate() {
        return laundryCollectionDate;
    }

    /**
     * @param laundryCollectionDate the laundryCollectionDate to set
     */
    public void setLaundryCollectionDate(Date laundryCollectionDate) {
        this.laundryCollectionDate = laundryCollectionDate;
    }

    /**
     * @return the laundryCollectionTime
     */
    public Date getLaundryCollectionTime() {
        return laundryCollectionTime;
    }

    /**
     * @param laundryCollectionTime the laundryCollectionTime to set
     */
    public void setLaundryCollectionTime(Date laundryCollectionTime) {
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

    /**
     * @return the breakfastItemList
     */
    public List<FoodMenuItem> getBreakfastItemList() {
        return breakfastItemList;
    }

    /**
     * @param breakfastItemList the breakfastItemList to set
     */
    public void setBreakfastItemList(List<FoodMenuItem> breakfastItemList) {
        this.breakfastItemList = breakfastItemList;
    }

    /**
     * @return the startersItemList
     */
    public List<FoodMenuItem> getStartersItemList() {
        return startersItemList;
    }

    /**
     * @param startersItemList the startersItemList to set
     */
    public void setStartersItemList(List<FoodMenuItem> startersItemList) {
        this.startersItemList = startersItemList;
    }

    /**
     * @return the sandwichesAndBurgersItemList
     */
    public List<FoodMenuItem> getSandwichesAndBurgersItemList() {
        return sandwichesAndBurgersItemList;
    }

    /**
     * @param sandwichesAndBurgersItemList the sandwichesAndBurgersItemList to
     * set
     */
    public void setSandwichesAndBurgersItemList(List<FoodMenuItem> sandwichesAndBurgersItemList) {
        this.sandwichesAndBurgersItemList = sandwichesAndBurgersItemList;
    }

    /**
     * @return the asiaItemList
     */
    public List<FoodMenuItem> getAsiaItemList() {
        return asiaItemList;
    }

    /**
     * @param asiaItemList the asiaItemList to set
     */
    public void setAsiaItemList(List<FoodMenuItem> asiaItemList) {
        this.asiaItemList = asiaItemList;
    }

    /**
     * @return the mainsItemList
     */
    public List<FoodMenuItem> getMainsItemList() {
        return mainsItemList;
    }

    /**
     * @param mainsItemList the mainsItemList to set
     */
    public void setMainsItemList(List<FoodMenuItem> mainsItemList) {
        this.mainsItemList = mainsItemList;
    }

    /**
     * @return the alcoholItemList
     */
    public List<FoodMenuItem> getAlcoholItemList() {
        return alcoholItemList;
    }

    /**
     * @param alcoholItemList the alcoholItemList to set
     */
    public void setAlcoholItemList(List<FoodMenuItem> alcoholItemList) {
        this.alcoholItemList = alcoholItemList;
    }

    /**
     * @return the juicesItemList
     */
    public List<FoodMenuItem> getJuicesItemList() {
        return juicesItemList;
    }

    /**
     * @param juicesItemList the juicesItemList to set
     */
    public void setJuicesItemList(List<FoodMenuItem> juicesItemList) {
        this.juicesItemList = juicesItemList;
    }

    /**
     * @return the softDrinksItemList
     */
    public List<FoodMenuItem> getSoftDrinksItemList() {
        return softDrinksItemList;
    }

    /**
     * @param softDrinksItemList the softDrinksItemList to set
     */
    public void setSoftDrinksItemList(List<FoodMenuItem> softDrinksItemList) {
        this.softDrinksItemList = softDrinksItemList;
    }

    /**
     * @return the coffeeItemList
     */
    public List<FoodMenuItem> getCoffeeItemList() {
        return coffeeItemList;
    }

    /**
     * @param coffeeItemList the coffeeItemList to set
     */
    public void setCoffeeItemList(List<FoodMenuItem> coffeeItemList) {
        this.coffeeItemList = coffeeItemList;
    }

    /**
     * @return the teaItemList
     */
    public List<FoodMenuItem> getTeaItemList() {
        return teaItemList;
    }

    /**
     * @param teaItemList the teaItemList to set
     */
    public void setTeaItemList(List<FoodMenuItem> teaItemList) {
        this.teaItemList = teaItemList;
    }

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
    
     /**
     * @return the houseKeepingCollectionTime
     */
    public Date getHouseKeepingCollectionTime() {
        return houseKeepingCollectionTime;
    }

    /**
     * @param houseKeepingCollectionTime the houseKeepingCollectionTime to set
     */
    public void setHouseKeepingCollectionTime(Date houseKeepingCollectionTime) {
        this.houseKeepingCollectionTime = houseKeepingCollectionTime;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the foodMenuOrderList
     */
    public List<FoodMenuItem> getFoodMenuOrderList() {
        return foodMenuOrderList;
    }

    /**
     * @param foodMenuOrderList the foodMenuOrderList to set
     */
    public void setFoodMenuOrderList(List<FoodMenuItem> foodMenuOrderList) {
        this.foodMenuOrderList = foodMenuOrderList;
    }

    /**
     * @return the taxes
     */
    public double getTaxes() {
        return taxes;
    }

    /**
     * @param taxes the taxes to set
     */
    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }

    /**
     * @return the totalPriceWithTax
     */
    public double getTotalPriceWithTax() {
        return totalPriceWithTax;
    }

    /**
     * @param totalPriceWithTax the totalPriceWithTax to set
     */
    public void setTotalPriceWithTax(double totalPriceWithTax) {
        this.totalPriceWithTax = totalPriceWithTax;
    }

    /**
     * @return the totalQty
     */
    public int getTotalQty() {
        return totalQty;
    }

    /**
     * @param totalQty the totalQty to set
     */
    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }
}
