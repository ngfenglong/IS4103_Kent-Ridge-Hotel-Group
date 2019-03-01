/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Hotel;
import entity.HotelFacility;
import entity.Room;
import error.NoResultException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author MC
 */
@Local
public interface HotelSessionLocal {
    public List<Hotel> getAllHotels();
    public Hotel getHotelByID(Long hID) throws NoResultException;
    public Hotel getHotelByName(String hotelName) throws NoResultException;
    public void deleteHotel(Long hID) throws NoResultException;
    public void createHotel(Hotel h);
    public void updateHotel(Hotel h) throws NoResultException;
    public void removeHotelFacility(Long hID, HotelFacility hf);
    public void addHotelFacility(Long hID, HotelFacility hf);
    public void removeRoom(Long hID, Room r);
    public void addRoom(Long hID, Room r);
    
}
