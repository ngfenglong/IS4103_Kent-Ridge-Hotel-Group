/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.HotelFacilityBooking;
import entity.RoomBooking;
import error.NoResultException;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 * Booking for both roomBooking and hotelFacility
 */
@Local
public interface BookingSessionLocal {
    public List<RoomBooking> getAllRoomBooking() throws NoResultException;
    public RoomBooking getRoomBookingByID(Long roomBookingID) throws NoResultException;
//  getRoomBookingBookedBy searches for rooms booked by a specific customer  
    public List<RoomBooking> getRoomBookingByEmail(String email, Date bookInDate, Date bookOutDate, String status) throws NoResultException;
    public List<RoomBooking> getRoomBookingByDate(Date bookInDate, Date bookOutDate, String status) throws NoResultException;
    public void updateRoomBooking (RoomBooking roomBooking) throws NoResultException;
    public void deleteRoomBooking (Long roomBookingID) throws NoResultException;
    public void createRoomBooking (RoomBooking roomBooking);
    
    public List<HotelFacilityBooking> getAllHotelFacilityBooking() throws NoResultException;
    public HotelFacilityBooking getHotelFacilityBookingByID(Long hotelFacilityID) throws NoResultException;
    public List<HotelFacilityBooking> getHotelFacilityBookingByEmail(String email, Date startDate, Date endDate, String status) throws NoResultException;
    public List<HotelFacilityBooking> getHotelFacilityBookingByDate(Date bookInDate, Date bookOutDate, String status) throws NoResultException;
    public void updateHotelFacilityBooking (HotelFacilityBooking hotelFacilityBooking) throws NoResultException;
    public void deleteHotelFacilityBooking (Long hotelFacilityBookingID) throws NoResultException;
    public void createHotelFacilityBooking (HotelFacilityBooking hotelFacilityBooking);       
}
