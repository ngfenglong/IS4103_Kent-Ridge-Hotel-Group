/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Staff;
import entity.WeeklySchedule;
import error.NoResultException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class WeeklyScheduleSession implements WeeklyScheduleSessionLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<WeeklySchedule> getAllWeeklySchedule() {
        Query q;
        q = em.createQuery("SELECT w FROM WeeklySchedule w");
        return q.getResultList();
    }

    @Override
    public WeeklySchedule getWeeklyScheduleByID(Long wID) throws NoResultException {
        WeeklySchedule w = em.find(WeeklySchedule.class, wID);
        if (w != null) {
            return w;
        } else {
            throw new NoResultException("WeeklySchedule not found.");
        }
    }

    @Override
    public List<WeeklySchedule> getWeeklyScheduleByWeek(Date d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<WeeklySchedule> getWorkWeeklyScheduleByStaff(Staff s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createSchedule(WeeklySchedule w) {
        em.persist(w);
    }

    @Override
    public void deleteWeeklySchedule(Long wID) throws NoResultException {
        WeeklySchedule w = em.find(WeeklySchedule.class, wID);
        if (w != null) {
            em.remove(w);
        } else {
            throw new NoResultException("WeeklySchedule not found");
        }
    }

    @Override
    public void updateWeeklySchedule(WeeklySchedule w) throws NoResultException {
        WeeklySchedule oldW = em.find(WeeklySchedule.class, w.getWeeklyScheduleID());
        if (oldW != null) {
            oldW.setStartDate(w.getStartDate());
            oldW.setMon(w.getMon());
            oldW.setTue(w.getTue());
            oldW.setWed(w.getWed());
            oldW.setThurs(w.getThurs());
            oldW.setFri(w.getFri());
            oldW.setSat(w.getSat());
            oldW.setSun(w.getSun());
            em.flush();
        } else {
            throw new NoResultException("WeeklySchedule Not found");
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<String> getDistinctDate() {
        Query q;
        q = em.createQuery("SELECT w FROM WeeklySchedule w");
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        List<String> returnList = new ArrayList<String>();
        List<WeeklySchedule> checkList = q.getResultList();
        for (WeeklySchedule w : checkList) {
            if (returnList.isEmpty()) {
                String strDate = dateFormat.format(w.getStartDate());
                returnList.add(strDate);
            } else if (!returnList.contains(dateFormat.format(w.getStartDate()))) {
                String strDate = dateFormat.format(w.getStartDate());
                returnList.add(strDate);
            }
        }

        return returnList;
    }

}
