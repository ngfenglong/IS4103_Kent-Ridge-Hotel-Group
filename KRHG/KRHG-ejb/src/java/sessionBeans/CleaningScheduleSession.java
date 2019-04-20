/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.CleaningSchedule;
import error.NoResultException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Congx2
 */
@Stateless
public class CleaningScheduleSession implements CleaningScheduleSessionLocal {

    @PersistenceContext
    private EntityManager em;
    
    
    
    @Override
    public List<CleaningSchedule> getAllCleaningSchedule() {
        Query q;
        q = em.createQuery("SELECT cs FROM CleaningSchedule cs ");
        return q.getResultList();
    }

    @Override
    public CleaningSchedule getCleaningScheduleByID(Long cID) throws NoResultException {
        CleaningSchedule cs = em.find(CleaningSchedule.class, cID);
        if (cs != null) {
            return cs;
        } else {
            throw new NoResultException("Cleaning Schedule not found.");
        }
    }

    @Override
    public List<CleaningSchedule> getCleaningScheduleByHotelCodeName(String hotelCodeName) throws NoResultException {
        Query q;
        if (hotelCodeName == null) {
            q = em.createQuery("SELECT cs FROM CleaningSchedule cs");
        } else {
            q = em.createQuery("SELECT cs FROM CleaningSchedule cs WHERE "
                    + "LOWER(cs.hotelCodeName) = :hotelCodeName ORDER BY cs.roomlevel ASC");
            q.setParameter("hotelCodeName", hotelCodeName.toLowerCase());
        }
        if (!q.getResultList().isEmpty()) {
            return q.getResultList();
        } else {
            throw new NoResultException("Cleaning Schedule not found");
        }
    }

    @Override
    public void deleteCleaningSchedule(Long cID) throws NoResultException {
        CleaningSchedule cleaningSchedule = em.find(CleaningSchedule.class, cID);
        if (cleaningSchedule != null) {
            em.remove(cleaningSchedule);
        } else {
            throw new NoResultException("Cleaning Schedule not found");
        }
    }

    @Override
    public void createCleaningSchedule(CleaningSchedule c) {
        em.persist(c);
    }

    @Override
    public void updateCleaningSchedule(CleaningSchedule c) throws NoResultException {
        CleaningSchedule oldCleaningSchedule = em.find(CleaningSchedule.class, c.getCleaningScheduleID());
        if (oldCleaningSchedule != null) {
            oldCleaningSchedule.setHotelCodeName(c.getHotelCodeName());
            oldCleaningSchedule.setLevel(c.getLevel());
            oldCleaningSchedule.setListOfStaff(c.getListOfStaff());
        } else {
            throw new NoResultException("Cleaning Schedule not found");
        }
    }
}
