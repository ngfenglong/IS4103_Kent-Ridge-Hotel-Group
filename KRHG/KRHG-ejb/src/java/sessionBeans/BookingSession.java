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
import javax.ejb.Stateless;

@Stateless
public class BookingSession implements BookingSessionLocal {

    @Override
    public List<RoomBooking> getAllRoomBooking() throws NoResultException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoomBooking getRoomBookingByID(Long roomBookingID) throws NoResultException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RoomBooking> getRoomBookingByEmail(String email, Date bookInDate, Date bookOutDate, String status) throws NoResultException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RoomBooking> getRoomBookingByDate(Date bookInDate, Date bookOutDate, String status) throws NoResultException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateRoomBooking(RoomBooking roomBooking) throws NoResultException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteRoomBooking(RoomBooking roomBooking) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createRoomBooking(RoomBooking roomBooking) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HotelFacilityBooking> getAllHotelFacilityBooking() throws NoResultException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HotelFacilityBooking getHotelFacilityBookingByID(Long hotelFacilityID) throws NoResultException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HotelFacilityBooking> getHotelFacilityBookingByEmail(String email, Date startDate, Date endDate, String status) throws NoResultException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HotelFacilityBooking> getHotelFacilityBookingByDate(Date bookInDate, Date bookOutDate, String status) throws NoResultException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateHotelFacilityBooking(HotelFacilityBooking hotelFacilityBooking) throws NoResultException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteHotelFacilityBooking(HotelFacilityBooking hotelFacilityBooking) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createHotelFacilityBooking(HotelFacilityBooking hotelFacilityBooking) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
