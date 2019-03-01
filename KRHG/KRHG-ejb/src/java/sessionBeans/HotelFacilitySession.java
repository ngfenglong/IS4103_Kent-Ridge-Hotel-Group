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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class HotelFacilitySession implements HotelFacilitySessionLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<HotelFacility> getAllHotelFacilities() {
        Query q;
        q = em.createQuery("SELECT hf FROM HotelFacility hf");
        return q.getResultList();
    }

    @Override
    public HotelFacility getHotelFacilityByID(Long hfID) throws NoResultException {
        HotelFacility hf = em.find(HotelFacility.class, hfID);
        if (hf != null) {
            return hf;
        } else {
            throw new NoResultException("Hotel Facility not found.");
        }
    }

    @Override
    public void createHotelFacility(HotelFacility hf) {
        em.persist(hf);
    }

    @Override
    public void deleteHotelFacility(Long hfID) throws NoResultException {
        HotelFacility hf = em.find(HotelFacility.class, hfID);
        if (hf != null) {
            em.remove(hf);
        } else {
            throw new NoResultException("Hotel Facility not found");
        }
    }

    @Override
    public void updateHotelFacility(HotelFacility hf) throws NoResultException {
        HotelFacility oldHF = em.find(HotelFacility.class, hf.getHotelFacilityID());
        if (oldHF != null) {
            oldHF.setHotelFacilityName(hf.getHotelFacilityName());
            oldHF.setHotelFacilityDescription(hf.getHotelFacilityDescription());
            oldHF.setHotelFacilityImage(hf.getHotelFacilityImage());
        } else {
            throw new NoResultException("Hotel Not found");
        }
    }

    @Override
    public HotelFacility getHotelFacilityByName(String hfName) throws NoResultException {
        Query q;
        q = em.createQuery("SELECT h FROM HotelFacility h WHERE "
                + "LOWER(h.hotelFacilityName) = :hotelFacilityName");
        q.setParameter("hotelFacilityName", hfName.toLowerCase());

        if (!q.getResultList().isEmpty()) {
            return (HotelFacility) q.getResultList().get(0);
        } else {
            throw new NoResultException("HotelFacility not found.");
        }
    }

}
