/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.ExtraSurcharge;
import entity.HolidaySurcharge;
import entity.MinibarItem;
import entity.Room;
import entity.RoomFacility;
import error.NoResultException;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface RoomSessionLocal {
    public List<Room> getAllRooms();
    public Room getRoomByID(Long cID) throws NoResultException;
    public Room getRoomByRoomNumber(String roomNumber) throws NoResultException;
    public List<Room> getRoomByStatus(String status, String hotelCodeName) throws NoResultException;
    public Room getRoomByName(String roomName) throws NoResultException;
    public List<Room> getRoomByType(String roomType, String hotelCodeName, String status) throws NoResultException;
    public List<Room> getSingleRoomByType(String roomType, String hotelCodeName, String status) throws NoResultException;
    public List<Room> getRoomByPax(String roomPax, String roomHotel) throws NoResultException;
    public List<Room> getRoomByHotel(String roomHotel) throws NoResultException;
    public List<Room> getRoomByHotelCodeName(String hotelCodeName) throws NoResultException;
    public List<Room> getRoomByHotelName(String hotelName) throws NoResultException;
    public void deleteRoom(Long rID) throws NoResultException;
    public void createRoom(Room r); 
    public void updateRoom(Room r) throws NoResultException;
    public void addRoomFacility(Long rID, RoomFacility rf);
    public void removeRoomFacility(Long rID, RoomFacility rf);
  
    public void addMinibarItem(Long rID, MinibarItem mi);
    public void removeMinibarItem(Long rID, MinibarItem mi);
     public List<Room> getRoomByHotelNameAndRoomType(String roomType, String hotelCodeName) throws NoResultException;
    
    public void createHolidaySurcharge(HolidaySurcharge hs);
    public void deleteHolidaySurcharge(Long hsID) throws NoResultException;
    public void updateHolidaySurcarhge(HolidaySurcharge hs) throws NoResultException;
    public List<HolidaySurcharge> getAllHolidaySurcharge();
    public HolidaySurcharge getHolidaySurchargeByID(Long hsID) throws NoResultException;
    public HolidaySurcharge getHolidaySurchargeByName(String name) throws NoResultException;
    public HolidaySurcharge getHolidaySurchargeByDate(Date date) throws NoResultException;
    
    public void createExtraSurcharge(ExtraSurcharge es);
    public void deleteExtraSurcharge(Long esID) throws NoResultException;
    public void updateExtraSurcarhge(ExtraSurcharge es) throws NoResultException;
    public List<ExtraSurcharge> getAllExtraSurcharge();
    public ExtraSurcharge getExtraSurchargeByID(Long esID) throws NoResultException;
    public ExtraSurcharge getExtraSurchargeByName(String name) throws NoResultException;

    public void createMinibarItem(MinibarItem mi);
    public void deleteMinibarItem(Long miID) throws NoResultException;
    public void updateMinibarItem(MinibarItem mi) throws NoResultException;
    public List<MinibarItem> getAllMinibarItem();
    public List<MinibarItem> getAllMinibarItemWithAvailable();
    public MinibarItem getMinibarItemByID(Long miID) throws NoResultException;
    public MinibarItem getMinibarItemByName(String name) throws NoResultException;
    
    
}
