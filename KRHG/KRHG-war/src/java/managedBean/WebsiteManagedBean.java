/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.ExtraSurcharge;
import entity.Feedback;
import entity.HolidaySurcharge;
import entity.Hotel;
import entity.HotelFacility;
import entity.MinibarItem;
import entity.Room;
import entity.RoomFacility;
import error.NoResultException;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessionBeans.FeedbackSessionLocal;
import sessionBeans.HotelFacilitySessionLocal;
import sessionBeans.HotelSessionLocal;
import sessionBeans.LogSessionLocal;
import sessionBeans.RoomFacilitySessionLocal;
import sessionBeans.RoomSessionLocal;

/**
 *
 * @author fengl
 */
@ManagedBean
@SessionScoped
public class WebsiteManagedBean implements Serializable {

    /**
     * Creates a new instance of WebsiteManagedBean
     */
    @EJB
    HotelSessionLocal hotelSessionLocal;
    @EJB
    HotelFacilitySessionLocal hotelFacilitySessionLocal;
    @EJB
    RoomFacilitySessionLocal roomFacilitySessionLocal;
    @EJB
    RoomSessionLocal roomSessionLocal;
    @EJB
    FeedbackSessionLocal feedbackSessionLocal;
    @EJB
    LogSessionLocal logSessionLocal;

    Hotel selectedHotel;

    public WebsiteManagedBean() {
    }

    public String viewHotel(Long hID) throws NoResultException {
        selectedHotel = hotelSessionLocal.getHotelByID(hID);

        return "booking1.xhtml?faces-redirect=true";
    }

    public String getDisplayRoomImage(String roomType) {
        if (roomType.equals("Standard")) {
            return "booking_1.jpg";
        } else if (roomType.equals("Deluxe")) {
            return "booking_2.jpg";
        } else if (roomType.equals("Premium")) {
            return "booking_3.jpg";
        } else if (roomType.equals("Suite")) {
            return "booking_4.jpg";
        } else {
            return "booking_5.jpg";
        }
    }

    public List<HotelFacility> getSelectedHotelFacility(Long hID) throws NoResultException{
        Hotel tempHotel = hotelSessionLocal.getHotelByID(hID);
        return tempHotel.getHotelFacilities();
    }
    
    public List<RoomFacility> getDisplayRoomFacilities(String type) throws NoResultException {
        return hotelSessionLocal.getRoomDisplayByRoomType(selectedHotel.getHotelID(), type).getRoomFacilities();
    }

    public List<String> getHotelRoomTypes() throws NoResultException {
        return hotelSessionLocal.getRoomTypes(selectedHotel.getHotelID());  
    }

    public List<Hotel> getAllHotels() {
        return hotelSessionLocal.getAllHotels();
    }

    public List<HotelFacility> getAllHotelFacility() {
        return hotelFacilitySessionLocal.getAllHotelFacilities();
    }

    public List<HolidaySurcharge> getAllHolidaySurcharge() {
        return roomSessionLocal.getAllHolidaySurcharge();
    }

    public List<MinibarItem> getAllMinibarItem() {
        return roomSessionLocal.getAllMinibarItem();
    }

    public List<RoomFacility> getAllRoomFacility() {
        return roomFacilitySessionLocal.getAllRoomFacilities();
    }

    public List<ExtraSurcharge> getAllSurcharge() {
        return roomSessionLocal.getAllExtraSurcharge();
    }

    public List<Feedback> getAllFeedbacks() {
        return feedbackSessionLocal.getAllFeedbacks();
    }

    public HotelSessionLocal getHotelSessionLocal() {
        return hotelSessionLocal;
    }

    public void setHotelSessionLocal(HotelSessionLocal hotelSessionLocal) {
        this.hotelSessionLocal = hotelSessionLocal;
    }

    public HotelFacilitySessionLocal getHotelFacilitySessionLocal() {
        return hotelFacilitySessionLocal;
    }

    public void setHotelFacilitySessionLocal(HotelFacilitySessionLocal hotelFacilitySessionLocal) {
        this.hotelFacilitySessionLocal = hotelFacilitySessionLocal;
    }

    public RoomFacilitySessionLocal getRoomFacilitySessionLocal() {
        return roomFacilitySessionLocal;
    }

    public void setRoomFacilitySessionLocal(RoomFacilitySessionLocal roomFacilitySessionLocal) {
        this.roomFacilitySessionLocal = roomFacilitySessionLocal;
    }

    public RoomSessionLocal getRoomSessionLocal() {
        return roomSessionLocal;
    }

    public void setRoomSessionLocal(RoomSessionLocal roomSessionLocal) {
        this.roomSessionLocal = roomSessionLocal;
    }

    public FeedbackSessionLocal getFeedbackSessionLocal() {
        return feedbackSessionLocal;
    }

    public void setFeedbackSessionLocal(FeedbackSessionLocal feedbackSessionLocal) {
        this.feedbackSessionLocal = feedbackSessionLocal;
    }

    public LogSessionLocal getLogSessionLocal() {
        return logSessionLocal;
    }

    public void setLogSessionLocal(LogSessionLocal logSessionLocal) {
        this.logSessionLocal = logSessionLocal;
    }

    public Hotel getSelectedHotel() {
        return selectedHotel;
    }

    public void setSelectedHotel(Hotel selectedHotel) {
        this.selectedHotel = selectedHotel;
    }

}
