/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.HotelFacility;
import entity.RoomFacility;
import error.NoResultException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author MC
 */
@Stateless
public class RoomFacilitySession implements RoomFacilitySessionLocal {
   
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<RoomFacility> getAllRoomFacilities() {
        Query q;
        q = em.createQuery("SELECT rf FROM RoomFacility rf");
        return q.getResultList();
    }
    
    @Override
    public RoomFacility getRoomFacilityByID(Long rfID) throws NoResultException {
        RoomFacility rf = em.find(RoomFacility.class, rfID);
        if (rf != null) {
            return rf;
        } else {
            throw new NoResultException("Room Facility not found.");
        }
    }
    
    @Override
    public void createRoomFacility(RoomFacility rf) {
        em.persist(rf);
    }
    
    @Override
    public void deleteRoomFacility(Long rfID) throws NoResultException {
        RoomFacility rf = em.find(RoomFacility.class, rfID);
        if (rf != null) {
            em.remove(rf);
        } else {
            throw new NoResultException("Room Facility not found");
        }
    }

    @Override
    public void updateRoomFacility(RoomFacility rf) throws NoResultException {
      RoomFacility oldRF = em.find(RoomFacility.class, rf.getRoomFacilityID());
        if (oldRF != null) {
            oldRF.setRoomFacilityCategory(rf.getRoomFacilityCategory());
            oldRF.setRoomFacilityName(rf.getRoomFacilityName());
        } else {
            throw new NoResultException("Hotel Not found");
        }
    }    
}
