/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Shift;
import error.NoResultException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author fengl
 */
@Stateless
public class ShiftSession implements ShiftSessionLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Shift> getAllShift() {
        Query q;
        q = em.createQuery("SELECT s FROM Shift s");
        return q.getResultList();
    }

    @Override
    public Shift getShiftByName(String name) throws NoResultException {
        Query q;
        q = em.createQuery("SELECT s FROM Shift s WHERE "
                + "LOWER(s.shiftName) = :shiftName");
        q.setParameter("shiftName", name.toLowerCase());

        if (!q.getResultList().isEmpty()) {
            return (Shift) q.getResultList().get(0);
        } else {
            throw new NoResultException("Shift not found.");
        }
    }

    @Override
    public Shift getShiftID(Long sID) throws NoResultException {
        Shift s = em.find(Shift.class, sID);
        if (s != null) {
            return s;
        } else {
            throw new NoResultException("Shift not found.");
        }
    }

    @Override
    public void createShift(Shift s) {
        em.persist(s);
    }

    @Override
    public void updateShift(Shift s) throws NoResultException {
        Shift oldS = em.find(Shift.class, s.getShiftID());
        if (oldS != null) {
            oldS.setEndTime(s.getEndTime());
            oldS.setShiftName(s.getShiftName());
            oldS.setStartTime(s.getStartTime());
            em.flush();
        } else {
            throw new NoResultException("Shift Not found");
        }
    }

    @Override
    public void deleteShift(Long sID) throws NoResultException {
        Shift s = em.find(Shift.class, sID);
        if (s != null) {
            em.remove(s);
        } else {
            throw new NoResultException("Shift not found");
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
