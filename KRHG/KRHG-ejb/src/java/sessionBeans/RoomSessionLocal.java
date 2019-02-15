/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Room;
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
}
