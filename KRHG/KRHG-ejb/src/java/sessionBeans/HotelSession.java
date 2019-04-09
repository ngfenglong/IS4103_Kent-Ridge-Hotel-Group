/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Feedback;
import entity.FunctionRoom;
import entity.Hotel;
import entity.HotelFacility;
import entity.Room;
import error.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class HotelSession implements HotelSessionLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Hotel> getAllHotels() {
        Query q;
        q = em.createQuery("SELECT h FROM Hotel h");
        return q.getResultList();
    }

    @Override
    public Hotel getHotelByID(Long hID) throws NoResultException {
        Hotel h = em.find(Hotel.class, hID);
        if (h != null) {
            return h;
        } else {
            throw new NoResultException("Hotel not found.");
        }
    }

    @Override
    public Hotel getHotelByName(String hotelName) throws NoResultException {
        Query q;
        q = em.createQuery("SELECT h FROM Hotel h WHERE "
                + "LOWER(h.hotelName) = :hotelName");
        q.setParameter("hotelName", hotelName.toLowerCase());

        if (!q.getResultList().isEmpty()) {
            return (Hotel) q.getResultList().get(0);
        } else {
            throw new NoResultException("Hotel not found.");
        }
    }

    @Override
    public void deleteHotel(Long hID) throws NoResultException {
        Hotel h = em.find(Hotel.class, hID);
        if (h != null) {
            List<Feedback> listofFeedBacks = h.getFeedbacks();
            List<FunctionRoom> listofFunctionRooms = h.getFunctionRooms();
            List<Room> listofRooms = h.getRooms();
            List<HotelFacility> listofHotelFacility = h.getHotelFacilities();
            
            for(int i = 0; i < listofFeedBacks.size(); i++){
                h.removeFeedback(listofFeedBacks.get(i));
            }
            for(int i = 0; i < listofFunctionRooms.size(); i++){
                h.removeFunctionRoom(listofFunctionRooms.get(i));
            }
            for(int i = 0; i < listofRooms.size(); i++){
                h.removeRoom(listofRooms.get(i));
            }
            
            for(int i = 0; i < listofHotelFacility.size(); i++){
                h.removeHotelFacility(listofHotelFacility.get(i));
            }
            
            em.remove(h);
        } else {
            throw new NoResultException("Hotel not found");
        }
    }

    @Override
    public void createHotel(Hotel h) {
        em.persist(h);
    }

    @Override
    public void updateHotel(Hotel h) throws NoResultException {
        Hotel oldH = em.find(Hotel.class, h.getHotelID());
        if (oldH != null) {
            oldH.setHotelName(h.getHotelName());
            oldH.setHotelAddress(h.getHotelAddress());
            oldH.setHotelContact(h.getHotelContact());
            oldH.setHotelFacilities(h.getHotelFacilities());
        } else {
            throw new NoResultException("Hotel Not found");
        }
    }

    @Override
    public void removeHotelFacility(Long hID, HotelFacility hf) {
        Hotel h = em.find(Hotel.class, hID);
        try {
            h.removeHotelFacility(hf);
            em.flush();
        } catch (NoResultException ex) {
            Logger.getLogger(HotelSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addHotelFacility(Long hID, HotelFacility hf) {
        Hotel h = em.find(Hotel.class, hID);
        try {
            h.addHotelFacility(hf);
            em.flush();
        } catch (NoResultException ex) {
            Logger.getLogger(HotelSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void removeRoom(Long hID, Room r) {
        Hotel h = em.find(Hotel.class, hID);
        try {
            h.removeRoom(r);
            em.flush();
        } catch (NoResultException ex) {
            Logger.getLogger(HotelSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addRoom(Long hID, Room r) {
        Hotel h = em.find(Hotel.class, hID);
        try {
            h.addRoom(r);
            em.flush();
        } catch (NoResultException ex) {
            Logger.getLogger(HotelSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Room getRoomDisplayByRoomType(Long hID, String roomType) throws NoResultException {
        Hotel h = em.find(Hotel.class, hID);
        List<Room> roomList = h.getRooms();
        Room displayRoom;
        for (Room r : roomList) {
            if (r.getRoomType().toLowerCase().equals(roomType.toLowerCase())) {
                displayRoom = r;
                return displayRoom;
            }
        }
        throw new NoResultException("Room Not found");
    }

    @Override
    public List<String> getRoomTypes(Long hID) throws NoResultException {
        Hotel h = em.find(Hotel.class, hID);
        List<Room> roomList = h.getRooms();
        List<String> roomTypes = new ArrayList<String>();
        roomTypes.add("Standard");
        for (Room r : roomList) {
            if (!roomTypes.contains(r.getRoomType())) {
                 roomTypes.add(r.getRoomType());
            }
        }
        return roomTypes;
    }

}
