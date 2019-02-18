/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.RoomFacility;
import error.NoResultException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author MC
 */
@Local
public interface RoomFacilitySessionLocal {
    
    public List<RoomFacility> getAllRoomFacilities();
    public RoomFacility getRoomFacilityByID(Long rfID) throws NoResultException;
    public void createRoomFacility(RoomFacility rf);
    public void deleteRoomFacility(Long rfID) throws NoResultException;
    public void updateRoomFacility(RoomFacility rf) throws NoResultException;
}
