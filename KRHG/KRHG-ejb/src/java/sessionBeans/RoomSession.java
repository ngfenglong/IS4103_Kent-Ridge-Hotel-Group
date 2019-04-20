/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.CleaningSchedule;
import entity.ExtraSurcharge;
import entity.HolidaySurcharge;
import entity.Hotel;
import entity.MinibarItem;
import entity.Room;
import entity.RoomFacility;
import error.NoResultException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class RoomSession implements RoomSessionLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Room> getAllRooms() {
        Query q;
        q = em.createQuery("SELECT r FROM Room r");
        return q.getResultList();
    }

    @Override
    public Room getRoomByID(Long rID) throws NoResultException {
        Room r = em.find(Room.class, rID);
        if (r != null) {
            return r;
        } else {
            throw new NoResultException("Room not found.");
        }
    }

    @Override
    public Room getRoomByRoomNumber(String roomNumber) throws NoResultException {
        Query q;
        q = em.createQuery("SELECT r FROM Room r WHERE "
                + "LOWER(r.roomNumber) = :roomNumber");
        q.setParameter("roomNumber", roomNumber.toLowerCase());

        if (!q.getResultList().isEmpty()) {
            return (Room) q.getResultList().get(0);
        } else {
            throw new NoResultException("Room not found.");
        }
    }

    @Override
    public Room getRoomByName(String roomName) throws NoResultException {
        Query q;
        q = em.createQuery("SELECT r FROM Room r WHERE "
                + "LOWER(r.roomName) = :roomName");
        q.setParameter("roomName", roomName.toLowerCase());

        if (!q.getResultList().isEmpty()) {
            return (Room) q.getResultList().get(0);
        } else {
            throw new NoResultException("Room not found.");
        }
    }

    @Override
    public List<Room> getRoomByStatus(String status, String hotelCodeName) throws NoResultException {
        Query q;
        if (hotelCodeName == null) {
            q = em.createQuery("SELECT r FROM Room r WHERE "
                    + "LOWER(r.status) = :status");
            q.setParameter("status", status.toLowerCase());

            if (!q.getResultList().isEmpty()) {
                return q.getResultList();
            } else {
                throw new NoResultException("Room not found.");
            }
        } else {
            q = em.createQuery("SELECT r FROM Room r WHERE "
                    + "LOWER(r.status) = :status AND "
                    + "LOWER(r.hotel.hotelCodeName) = :hotelCodeName");
            q.setParameter("status", status.toLowerCase());
            q.setParameter("hotelCodeName", hotelCodeName.toLowerCase());

            if (!q.getResultList().isEmpty()) {
                return q.getResultList();
            } else {
                throw new NoResultException("Room not found.");
            }
        }
    }

    @Override
    public List<Room> getRoomByType(String roomType, String hotelCodeName, String status) throws NoResultException {
        Query q;
        if (hotelCodeName.isEmpty()) {
            q = em.createQuery("SELECT r FROM Room r WHERE "
                    + "LOWER(r.roomType) = :roomType AND "
                    + "LOWER(r.status) = :status");
            q.setParameter("roomType", roomType.toLowerCase());
            q.setParameter("status", status.toLowerCase());
        } else {
            q = em.createQuery("SELECT r FROM Room r WHERE "
                    + "LOWER(r.hotel.hotelCodeName) = :hotelCodeName AND "
                    + "LOWER(r.roomType) = :roomType AND "
                    + "LOWER(r.status) = :status");
            q.setParameter("roomType", roomType.toLowerCase());
            q.setParameter("hotelCodeName", hotelCodeName.toLowerCase());
            q.setParameter("status", status.toLowerCase());

        }
        if (!q.getResultList().isEmpty()) {
            return q.getResultList();
        } else {
            throw new NoResultException("Room not found.");
        }
    }

    @Override
    public List<Room> getRoomByHotelNameAndRoomType(String roomType, String hotelCodeName) throws NoResultException {
        Query q;
        if (hotelCodeName == null) {
            q = em.createQuery("SELECT r FROM Room r WHERE "
                    + "LOWER(r.roomType) = :roomType");
            q.setParameter("roomType", roomType.toLowerCase());

            if (!q.getResultList().isEmpty()) {
                return q.getResultList();
            } else {
                throw new NoResultException("Room not found.");
            }
        } else {
            q = em.createQuery("SELECT r FROM Room r WHERE "
                    + "LOWER(r.roomType) = :roomType AND "
                    + "LOWER(r.hotel.hotelCodeName) = :hotelCodeName");
            q.setParameter("roomType", roomType.toLowerCase());
            q.setParameter("hotelCodeName", hotelCodeName.toLowerCase());

            if (!q.getResultList().isEmpty()) {
                return q.getResultList();
            } else {
                throw new NoResultException("Room not found.");
            }
        }
    }

    @Override
    public List<Room> getSingleRoomByType(String roomType, String hotelCodeName, String status) throws NoResultException {
        Query q;
        if (hotelCodeName.isEmpty()) {
            q = em.createQuery("SELECT r FROM Room r WHERE "
                    + "LOWER(r.roomType) = :roomType AND "
                    + "LOWER(r.status) = :status");
            q.setParameter("roomType", roomType.toLowerCase());
            q.setParameter("status", status.toLowerCase());
        } else {
            q = em.createQuery("SELECT r FROM Room r WHERE "
                    + "LOWER(r.hotel.hotelCodeName) = :hotelCodeName AND "
                    + "LOWER(r.roomType) = :roomType AND "
                    + "LOWER(r.status) = :status");
            q.setParameter("roomType", roomType.toLowerCase());
            q.setParameter("hotelCodeName", hotelCodeName.toLowerCase());
            q.setParameter("status", status.toLowerCase());

        }
        if (!q.getResultList().isEmpty()) {
            return q.setMaxResults(1).getResultList();
        } else {
            throw new NoResultException("Room not found.");
        }
    }

    @Override
    public List<Room> getRoomByPax(String roomPax, String roomHotel) throws NoResultException {
        Query q;
        if (roomHotel.isEmpty()) {
            q = em.createQuery("SELECT r FROM Room r WHERE "
                    + "LOWER(r.roomPax) = :roomPax");
            q.setParameter("roomPax", roomPax.toLowerCase());

            if (!q.getResultList().isEmpty()) {
                return q.getResultList();
            } else {
                throw new NoResultException("Room not found.");
            }
        } else {
            q = em.createQuery("SELECT r FROM Room r WHERE "
                    + "LOWER(r.roomHotel) = :roomHotel AND"
                    + "LOWER(r.roomPax) = :roomPax");
            q.setParameter("roomHotel", roomHotel.toLowerCase());
            q.setParameter("roomPax", roomPax.toLowerCase());

            if (!q.getResultList().isEmpty()) {
                return q.getResultList();
            } else {
                throw new NoResultException("Room not found.");
            }
        }
    }

    @Override
    public List<Room> getRoomByHotel(String roomHotel) throws NoResultException {
        Query q;
        q = em.createQuery("SELECT r FROM Room r WHERE "
                + "LOWER(r.hotel.hotelCodeName) = :roomHotel");
        q.setParameter("roomHotel", roomHotel.toLowerCase());

        if (!q.getResultList().isEmpty()) {
            return q.getResultList();
        } else {
            throw new NoResultException("Room not found.");
        }
    }

    @Override
    public void deleteRoom(Long rID) throws NoResultException {
        Room r = em.find(Room.class, rID);
        if (r != null) {
            em.remove(r);
        } else {
            throw new NoResultException("Room not found");
        }
    }

    @Override
    public void createRoom(Room r) {
        em.persist(r);
    }

    @Override
    public void updateRoom(Room r) throws NoResultException {
        Room oldR = em.find(Room.class, r.getRoomID());
        if (oldR != null) {
            oldR.setRoomName(r.getRoomName());
            oldR.setRoomType(r.getRoomType());
            oldR.setRoomPax(r.getRoomPax());
            oldR.setRoomFacilities(r.getRoomFacilities());
            oldR.setMiniBarItems(r.getMiniBarItems());
            oldR.setStatus(r.getStatus());
        } else {
            throw new NoResultException("Room Not found");
        }
    }

    @Override
    public void addRoomFacility(Long rID, RoomFacility rf) {
        Room r = em.find(Room.class, rID);
        try {
            r.addRoomFacility(rf);
            em.flush();
        } catch (NoResultException ex) {
            Logger.getLogger(RoomSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void removeRoomFacility(Long rID, RoomFacility rf) {
        Room r = em.find(Room.class, rID);
        try {
            r.removeRoomFacility(rf);
            em.flush();
        } catch (NoResultException ex) {
            Logger.getLogger(RoomSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    @Override
    public void addMinibarItem(Long rID, MinibarItem mi) {
        Room r = em.find(Room.class, rID);
        try {
            r.addMinibarItem(mi);
            em.flush();
        } catch (NoResultException ex) {
            Logger.getLogger(RoomSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void removeMinibarItem(Long rID, MinibarItem mi) {
        Room r = em.find(Room.class, rID);
        try {
            r.removeMinibarItem(mi);
            em.flush();
        } catch (NoResultException ex) {
            Logger.getLogger(RoomSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createHolidaySurcharge(HolidaySurcharge hs) {
        em.persist(hs);
    }

    @Override
    public void deleteHolidaySurcharge(Long hsID) throws NoResultException {
        HolidaySurcharge hs = em.find(HolidaySurcharge.class, hsID);
        if (hs != null) {
            em.remove(hs);
        } else {
            throw new NoResultException("Holiday Surcharge not found");
        }
    }

    @Override
    public void updateHolidaySurcarhge(HolidaySurcharge hs) throws NoResultException {
        HolidaySurcharge oldHS = em.find(HolidaySurcharge.class, hs.getHolidaySurchargeID());
        if (oldHS != null) {
            oldHS.setHolidayName(hs.getHolidayName());
            oldHS.setHolidaySurchargePrice(hs.getHolidaySurchargePrice());
        } else {
            throw new NoResultException("Holiday Surcharge not found");
        }
    }

    @Override
    public List<HolidaySurcharge> getAllHolidaySurcharge() {
        Query q;
        q = em.createQuery("SELECT hs FROM HolidaySurcharge hs");
        return q.getResultList();
    }

    @Override
    public HolidaySurcharge getHolidaySurchargeByID(Long hsID) throws NoResultException {
        HolidaySurcharge hs = em.find(HolidaySurcharge.class, hsID);
        if (hs != null) {
            return hs;
        } else {
            throw new NoResultException("Holiday Surcharge not found");
        }
    }

    @Override
    public HolidaySurcharge getHolidaySurchargeByName(String name) throws NoResultException {
        Query q;
        q = em.createQuery("SELECT hs FROM HolidaySurcharge hs WHERE "
                + "LOWER(hs.holidayName) = :holidayName");
        q.setParameter("holidayName", name.toLowerCase());

        if (!q.getResultList().isEmpty()) {
            return (HolidaySurcharge) q.getResultList().get(0);
        } else {
            throw new NoResultException("Holiday Surcharge not found.");
        }

    }

    @Override
    public HolidaySurcharge getHolidaySurchargeByDate(Date date) throws NoResultException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createExtraSurcharge(ExtraSurcharge es) {
        em.persist(es);
    }

    @Override
    public void deleteExtraSurcharge(Long esID) throws NoResultException {
        ExtraSurcharge es = em.find(ExtraSurcharge.class, esID);
        if (es != null) {
            em.remove(es);
        } else {
            throw new NoResultException("Extra Surcharge not found");
        }
    }

    @Override
    public void updateExtraSurcarhge(ExtraSurcharge es) throws NoResultException {
        ExtraSurcharge oldES = em.find(ExtraSurcharge.class, es.getSurchargeID());
        if (oldES != null) {
            oldES.setSurchargeName(es.getSurchargeName());
            oldES.setSurchargePrice(es.getSurchargePrice());
            oldES.setDaysToCharge(es.getDaysToCharge());
            oldES.setSurchargeFrom(es.getSurchargeFrom());
            oldES.setSurchargeTo(es.getSurchargeTo());
            em.flush();
        } else {
            throw new NoResultException("Extra Surcharge not found");
        }
    }

    @Override
    public List<ExtraSurcharge> getAllExtraSurcharge() {
        Query q;
        q = em.createQuery("SELECT es FROM ExtraSurcharge es");
        return q.getResultList();
    }

    @Override
    public ExtraSurcharge getExtraSurchargeByID(Long esID) throws NoResultException {
        ExtraSurcharge es = em.find(ExtraSurcharge.class, esID);
        if (es != null) {
            return es;
        } else {
            throw new NoResultException("Extra Surcharge not found");
        }
    }

    @Override
    public ExtraSurcharge getExtraSurchargeByName(String name) throws NoResultException {
        Query q;
        q = em.createQuery("SELECT es FROM ExtraSurcharge es WHERE "
                + "LOWER(es.surchargeName) = :surchargeName");
        q.setParameter("surchargeName", name.toLowerCase());

        if (!q.getResultList().isEmpty()) {
            return (ExtraSurcharge) q.getResultList().get(0);
        } else {
            throw new NoResultException("Extra Surcharge not found.");
        }
    }

    @Override
    public void createMinibarItem(MinibarItem mi) {
        em.persist(mi);
    }

    @Override
    public void deleteMinibarItem(Long miID) throws NoResultException {
        MinibarItem mi = em.find(MinibarItem.class, miID);
        if (mi != null) {
            em.remove(mi);
        } else {
            throw new NoResultException("Minibar Item not found");
        }
    }

    @Override
    public void updateMinibarItem(MinibarItem mi) throws NoResultException {
        MinibarItem oldMI = em.find(MinibarItem.class, mi.getMinibarItemID());
        if (oldMI != null) {
            oldMI.setItemName(mi.getItemName());
            oldMI.setPrice(mi.getPrice());
            oldMI.setQty(mi.getQty());
            oldMI.setStatus(mi.isStatus());
            em.flush();
        } else {
            throw new NoResultException("Minibar Item not found");
        }
    }

    @Override
    public List<MinibarItem> getAllMinibarItem() {
        Query q;
        q = em.createQuery("SELECT mi FROM MinibarItem mi");
        return q.getResultList();
    }
    
    @Override
    public List<MinibarItem> getAllMinibarItemWithAvailable() {
        Query q;
        q = em.createQuery("SELECT mi FROM MinibarItem mi");
        List<MinibarItem> filterList =  q.getResultList();
        List<MinibarItem> returnList = new ArrayList<MinibarItem>();
        
        for(MinibarItem mi: filterList){
            if(mi.status == true){
                MinibarItem tempMI = mi;
                returnList.add(tempMI);
            }
        }
        
        return returnList;
    }

    @Override
    public MinibarItem getMinibarItemByID(Long miID) throws NoResultException {
        MinibarItem mi = em.find(MinibarItem.class, miID);
        if (mi != null) {
            return mi;
        } else {
            throw new NoResultException("Minibar Item not found");
        }
    }

    @Override
    public MinibarItem getMinibarItemByName(String name) throws NoResultException {
        Query q;
        q = em.createQuery("SELECT mi FROM MinibarItem mi WHERE "
                + "LOWER(mi.itemName) = :itemName");
        q.setParameter("itemName", name.toLowerCase());

        if (!q.getResultList().isEmpty()) {
            return (MinibarItem) q.getResultList().get(0);
        } else {
            throw new NoResultException("Minibar Item not found");
        }
    }

}
