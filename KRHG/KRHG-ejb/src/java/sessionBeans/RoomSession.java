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
            return (Room)q.getResultList().get(0);
        } else {
            throw new NoResultException("Room not found.");
        }
    }

    @Override
    public List<Room> getRoomByType(String roomType, String roomHotel) throws NoResultException {
        Query q;
        if (roomHotel.isEmpty()) {
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
                    + "LOWER(r.roomHotel) = :roomHotel AND" 
                    + "LOWER(r.roomType) = :roomType");
            q.setParameter("roomHotel", roomHotel.toLowerCase());
            q.setParameter("roomType", roomType.toLowerCase());

            if (!q.getResultList().isEmpty()) {
                return q.getResultList();
            } else {
                throw new NoResultException("Room not found.");
            }
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
                + "LOWER(r.roomHote) = :roomHotel");
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
            oldR.setStatus(r.getStatus());
        } else {
            throw new NoResultException("Room Not found");
        }
    }

}
