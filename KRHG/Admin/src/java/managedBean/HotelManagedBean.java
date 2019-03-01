/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.Hotel;
import entity.HotelFacility;
import entity.MinibarItem;
import entity.Room;
import entity.RoomFacility;
import error.NoResultException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import sessionBeans.HotelFacilitySessionLocal;

import sessionBeans.HotelSessionLocal;
import sessionBeans.HouseKeepingOrderSessionLocal;
import sessionBeans.RoomFacilitySessionLocal;
import sessionBeans.RoomSessionLocal;

/**
 *
 * @author fengl
 */
@ManagedBean
@SessionScoped
public class HotelManagedBean implements Serializable {

    @EJB
    HotelSessionLocal hotelSessionLocal;
    @EJB
    HotelFacilitySessionLocal hotelFacilitySessionLocal;
    @EJB
    HouseKeepingOrderSessionLocal houseKeepingOrderSessionLocal;
    @EJB
    RoomFacilitySessionLocal roomFacilitySessionLocal;
    @EJB
    RoomSessionLocal roomSessionLocal;

    public String selectedHotel;

    public String hotelName;
    public String hotelCode;
    public String address;
    public int hotelStar;
    public String contactNumber;

    //Create Room
    public String roomName;
    public String roomNumber;
    public String roomType;
    public int roomPax;
    public String[] minibarItems;
    public String[] roomFacilities;

    public String selectedFacility;

    public HotelManagedBean() {

    }

    public List<Hotel> getAllHotels() {
        return hotelSessionLocal.getAllHotels();
    }

    public HotelSessionLocal getHotelSessionLocal() {
        return hotelSessionLocal;
    }

    public List<HotelFacility> getHotelFacilities() throws NoResultException {
        Hotel hotel = hotelSessionLocal.getHotelByName(selectedHotel);
        return hotel.getHotelFacilities();
    }

    public List<HotelFacility> getAddableHotelFacilities() throws NoResultException {
        List<HotelFacility> allFacilities = hotelFacilitySessionLocal.getAllHotelFacilities();
        List<HotelFacility> unwantedList = getHotelFacilities();
        List<HotelFacility> returnList = new ArrayList<HotelFacility>();
        for (HotelFacility h : allFacilities) {
            boolean check = false;
            for (HotelFacility h2 : unwantedList) {
                if (h.getHotelFacilityName().equals(h2.getHotelFacilityName())) {
                    check = true;
                }
            }
            if (check == false) {
                returnList.add(h);
            }
        }
        return returnList;
    }

    public String addRoom() throws NoResultException {
        Room r = new Room();
        r.setRoomName(roomName);
        r.setRoomNumber(roomNumber);
        r.setRoomType(roomType);
        r.setRoomPax(roomPax);
        r.setStatus("Available");
        ArrayList<MinibarItem> mbList = new ArrayList<MinibarItem>();
        ArrayList<RoomFacility> rfList = new ArrayList<RoomFacility>();
        
        
        if (minibarItems != null) {
            for (int i = 0; i < minibarItems.length ; i++) {
                mbList.add(houseKeepingOrderSessionLocal.getMinibarItemByItemName(minibarItems[i]));
            }
        }
        
        if (roomFacilities != null) {
            for (int i = 0; i < roomFacilities.length ; i++) {
                rfList.add(roomFacilitySessionLocal.getRoomFacilityByName(roomFacilities[i]));
            }
        }
        
        roomSessionLocal.createRoom(r);
        r = roomSessionLocal.getRoomByName(roomName);
        Hotel h = hotelSessionLocal.getHotelByName(selectedHotel);
        hotelSessionLocal.addRoom(h.getHotelID(), r);
        
        return "ViewRooms.xhtml?faces-redirect=true";
    }

    public String addFacilityToHotel() throws NoResultException {
        Hotel hotel = hotelSessionLocal.getHotelByName(selectedHotel);
        hotelSessionLocal.addHotelFacility(hotel.getHotelID(), hotelFacilitySessionLocal.getHotelFacilityByName(selectedFacility));

        return "index.xhtml?faces-redirect=true";
    }

    public String createNewHotel() {
        Hotel hotel = new Hotel();
        hotel.setHotelName(hotelName);
        hotel.setHotelCodeName(hotelCode);
        hotel.setHotelContact(contactNumber);
        hotel.setHotelStar(hotelStar);
        hotel.setHotelAddress(address);
        hotelSessionLocal.createHotel(hotel);
        return "MainPage.xhtml?faces-redirect=true";
    }

    public List<MinibarItem> getMinibarItemList() {
        return houseKeepingOrderSessionLocal.getAllMinibarItem();
    }

    public List<RoomFacility> getRoomFacilityList() {
        return roomFacilitySessionLocal.getAllRoomFacilities();
    }

    public List<Room> getHotelRooms() throws NoResultException {
        Hotel hotel = hotelSessionLocal.getHotelByName(selectedHotel);
        return hotel.getRooms();
    }

    public void setHotelSessionLocal(HotelSessionLocal hotelSessionLocal) {
        this.hotelSessionLocal = hotelSessionLocal;
    }

    public String viewHotelFacilities(String hotelName) {
        selectedHotel = hotelName;

        return "ViewFacility.xhtml?faces-redirect=true";
    }

    public String viewHotelRooms(String hotelName) {
        selectedHotel = hotelName;

        return "ViewRooms.xhtml?faces-redirect=true";
    }

    public String getSelectedHotel() {
        return selectedHotel;
    }

    public void setSelectedHotel(String selectedHotel) {
        this.selectedHotel = selectedHotel;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getHotelStar() {
        return hotelStar;
    }

    public void setHotelStar(int hotelStar) {
        this.hotelStar = hotelStar;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public HotelFacilitySessionLocal getHotelFacilitySessionLocal() {
        return hotelFacilitySessionLocal;
    }

    public void setHotelFacilitySessionLocal(HotelFacilitySessionLocal hotelFacilitySessionLocal) {
        this.hotelFacilitySessionLocal = hotelFacilitySessionLocal;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getRoomPax() {
        return roomPax;
    }

    public void setRoomPax(int roomPax) {
        this.roomPax = roomPax;
    }

    public String getSelectedFacility() {
        return selectedFacility;
    }

    public void setSelectedFacility(String selectedFacility) {
        this.selectedFacility = selectedFacility;
    }

    public String[] getMinibarItems() {
        return minibarItems;
    }

    public void setMinibarItems(String[] minibarItems) {
        this.minibarItems = minibarItems;
    }

    public String[] getRoomFacilities() {
        return roomFacilities;
    }

    public void setRoomFacilities(String[] roomFacilities) {
        this.roomFacilities = roomFacilities;
    }

}
