/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Room;
import error.NoResultException;
import java.util.List;
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
    public List<Room> getRoomByType(String roomType, String roomHotel) throws NoResultException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Room> getRoomByPax(String roomPax, String roomHotel) throws NoResultException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Room> getRoomByHotel(String roomHotel) throws NoResultException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteRoom(Long rID) throws NoResultException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createRoom(Room r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateRoom(Room r) throws NoResultException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
