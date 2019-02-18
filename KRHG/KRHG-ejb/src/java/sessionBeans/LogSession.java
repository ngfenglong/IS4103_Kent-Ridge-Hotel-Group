/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Logging;
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
public class LogSession implements LogSessionLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Logging> getAllLoggingActivities() {
        Query q;
        q = em.createQuery("SELECT l FROM Logging l");
        return q.getResultList();
    }

    @Override
    public Logging getLoggingByID(Long logID) throws NoResultException {
        Logging l = em.find(Logging.class, logID);
        if (l != null) {
            return l;
        } else {
            throw new NoResultException("Logging not found.");
        }
    }

    @Override
    public List<Logging> getLoggingByType(String t) throws NoResultException {
        List<Logging> returnList = new ArrayList<Logging>();
        List<Logging> filterList = getAllLoggingActivities();
        for (Logging l : filterList) {
            if (l.getLoggingType().equals(t)) {
                returnList.add(l);
            }
        }
        return returnList;
    }

    @Override
    public List<Logging> getLoggingByDate(Date d) throws NoResultException {
         List<Logging> returnList = new ArrayList<Logging>();
        List<Logging> filterList = getAllLoggingActivities();
        for (Logging l : filterList) {
            if (l.getLoggingDateTime().compareTo(d) == 0) {
                returnList.add(l);
            }
        }
        return returnList;
    }

    @Override
    public void createLogging(Logging l) {
        em.persist(l);
    }

    @Override
    public void deleteLogging(Long logID) throws NoResultException {
    Logging l = em.find(Logging.class, logID);
        if (l != null) {
            em.remove(l);
        } else {
            throw new NoResultException("Log not found");
        }
    }

}
