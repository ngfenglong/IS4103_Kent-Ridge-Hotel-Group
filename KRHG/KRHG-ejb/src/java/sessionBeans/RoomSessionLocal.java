/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.CleaningSchedule;
import entity.MinibarItem;
import entity.Room;
import entity.RoomFacility;
import error.NoResultException;
import java.util.List;
import javax.ejb.Local;

@Local
public interface RoomSessionLocal {
    public List<Room> getAllRooms();
    public Room getRoomByID(Long cID) throws NoResultException;
    public Room getRoomByName(String roomName) throws NoResultException;
    public List<Room> getRoomByType(String roomType, String roomHotel) throws NoResultException;
    public List<Room> getRoomByPax(String roomPax, String roomHotel) throws NoResultException;
    public List<Room> getRoomByHotel(String roomHotel) throws NoResultException;
    public void deleteRoom(Long rID) throws NoResultException;
    public void createRoom(Room r); 
    public void updateRoom(Room r) throws NoResultException;
    public void addRoomFacility(Long rID, RoomFacility rf);
    public void removeRoomFacility(Long rID, RoomFacility rf);
    public void addCleaningSchedule(Long rID, CleaningSchedule cs);
    public void removeCleaningSchedule(Long rID, CleaningSchedule cs);
    public void addMinibarItem(Long rID, MinibarItem mi);
    public void removeMinibarItem(Long rID, MinibarItem mi);
}
