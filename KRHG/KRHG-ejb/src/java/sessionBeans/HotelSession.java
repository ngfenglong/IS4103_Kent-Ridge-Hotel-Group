/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Hotel;
import entity.HotelFacility;
import error.NoResultException;
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

}
