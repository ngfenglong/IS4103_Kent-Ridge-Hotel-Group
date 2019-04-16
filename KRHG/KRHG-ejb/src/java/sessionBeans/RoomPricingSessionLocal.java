/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.RoomPricing;
import error.NoResultException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author MC
 */
@Local
public interface RoomPricingSessionLocal {
    public void createRoomPricing(RoomPricing rp);
    public List<RoomPricing> getAllRoomPricings();
    public List<RoomPricing> getAllRoomPricingsByHotel(String hotelCode);
    public RoomPricing getRoomPricingByName(String roomPricingName) throws NoResultException;
    public RoomPricing getRoomPricingByID(Long rpID) throws NoResultException;
    public void deleteRoomPricing(Long rpID) throws NoResultException;
    public void updateRoomPricing(RoomPricing rp) throws NoResultException;
    
    
}
