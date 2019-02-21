/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.AnnualLeave;
import entity.Staff;
import entity.WorkSchedule;
import error.NoResultException;
import java.util.ArrayList;
import java.util.Date;
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
public class RosterSession implements RosterSessionLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<WorkSchedule> getAllWorkSchedule() {
        Query q;
        q = em.createQuery("SELECT w FROM WorkSchedule w");
        return q.getResultList();
    }

    @Override
    public WorkSchedule getWorkSceduleByID(Long wID) throws NoResultException {
        WorkSchedule w = em.find(WorkSchedule.class, wID);
        if (w != null) {
            return w;
        } else {
            throw new NoResultException("WorkSchedule not found.");
        }
    }

    @Override
    public List<WorkSchedule> getWorkScheduleByWeek(Date d) {
        List<WorkSchedule> returnList = new ArrayList<WorkSchedule>();
        List<WorkSchedule> filterList = getAllWorkSchedule();
        for (WorkSchedule w : filterList) {
            if (w.getDateOfTheWeek().compareTo(d) == 0) {
                returnList.add(w);
            }
        }
        return returnList;
    }

    @Override
    public List<WorkSchedule> getWorkScheduleByStaff(Staff s) {
        Query q;
        if (s != null) {
            q = em.createQuery("SELECT w FROM WorkSchedule w WHERE "
                    + "w.allocatedTo IN :allocatedTo");
            q.setParameter("allocatedTo", s);
        } else {
            return new ArrayList<WorkSchedule>();
        }

        return q.getResultList();
    }

    @Override
    public List<WorkSchedule> getWorkScheuduleByApproved() {
        List<WorkSchedule> returnList = new ArrayList<WorkSchedule>();
        List<WorkSchedule> filterList = getAllWorkSchedule();
        for (WorkSchedule w : filterList) {
            if (w.getApprovedStatus().toLowerCase().equals("approved")) {
                returnList.add(w);
            }
        }
        return returnList;
    }

    @Override
    public List<WorkSchedule> getWorkScheduleByApprover(Staff s) {
        Query q;
        if (s != null) {
            q = em.createQuery("SELECT w FROM WorkSchedule w WHERE "
                    + "w.approver IN :approver");
            q.setParameter("approver", s);
        } else {
            return new ArrayList<WorkSchedule>();
        }
        return q.getResultList();
    }

    @Override
    public void createSchedule(WorkSchedule w) {
        em.persist(w);
    }

    @Override
    public void deleteWorkSchedule(Long wID) throws NoResultException {
        WorkSchedule w = em.find(WorkSchedule.class, wID);
        if (w != null) {
            em.remove(w);
        } else {
            throw new NoResultException("Work Schedule not found");
        }
    }

    @Override
    public void applyLeave(AnnualLeave l) {
        em.persist(l);
    }

    @Override
    public void cancelLeave(Long aID) throws NoResultException{
        AnnualLeave a = em.find(AnnualLeave.class, aID);
        if (a != null) {
            em.remove(a);
        } else {
            throw new NoResultException("Annual Leave not found");
        }
    }

    @Override
    public double viewPaySlip(Long sID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void allocateSchedule(Long lID, Staff s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void approveSchedule(WorkSchedule w) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void approveLeave(AnnualLeave l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void autoScheduleByWeek(Date d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
