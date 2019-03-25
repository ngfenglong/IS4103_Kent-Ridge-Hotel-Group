/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.MailingList;
import entity.MemberTier;
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
public class MailingListSession implements MailingListSessionLocal {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<MailingList> getAllMailingList() throws NoResultException {
        Query q;
        q = em.createQuery("SELECT m FROM MailingList m");

        return q.getResultList();

    }

    @Override
    public MailingList getMailingListByID(Long mID) throws NoResultException {
        MailingList m = em.find(MailingList.class, mID);
        if (m != null) {
            return m;
        } else {
            throw new NoResultException("Mailing List not found.");
        }
    }

    @Override
    public MailingList getMailingListByName(String listName) throws NoResultException {
        Query q;
        q = em.createQuery("SELECT m FROM MailingList m WHERE "
                + "LOWER(m.listName) = :listName");
        q.setParameter("listName", listName.toLowerCase());

        if (!q.getResultList().isEmpty()) {
            return (MailingList) q.getResultList().get(0);
        } else {
            throw new NoResultException("Mailing List not found.");
        }
    }

    @Override
    public void updateMailingList(MailingList m) throws NoResultException {
        MailingList oldM = em.find(MailingList.class, m.getListID());
        if (oldM != null) {
            oldM.setListName(m.getListName());
            oldM.setListToSend(m.getListToSend());
        } else {
            throw new NoResultException("Mailing List not found.");
        }
    }

    @Override
    public void deleteMailingList(Long mID) throws NoResultException {
        MailingList m = em.find(MailingList.class, mID);
        if (m != null) {
            em.remove(m);
        } else {
            throw new NoResultException("MailingList not found");
        }
    }

    @Override
    public void createMailingList(MailingList m) {
        em.persist(m);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
