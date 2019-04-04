/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.LaundryOrder;
import entity.Room;
import entity.Staff;
import error.NoResultException;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class LaundrySession implements LaundrySessionLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<LaundryOrder> getAllLaundryOrder() {
        Query q;
        q = em.createQuery("SELECT lo FROM LaundryOrder lo ");
        return q.getResultList();
    }

    @Override
    public LaundryOrder getLaundryOrderByID(Long laundryOrderID) throws NoResultException {
        LaundryOrder lo = em.find(LaundryOrder.class, laundryOrderID);
        if (lo != null) {
            return lo;
        } else {
            throw new NoResultException("Laundry Order not found.");
        }

    }

    @Override
    public List<LaundryOrder> getLaundryOrderByOrderDateTime(Date startDate, Date endDate, String status) throws NoResultException {
        Query q;
        q = em.createQuery("SELECT lo FROM LaundryOrder lo WHERE "
                + "lo.orderDateTime >= :startDate"
                + " AND lo.orderDateTime <= :endDate"
                + " AND LOWER(lo.status) = :status");
        q.setParameter("startDate", startDate);
        q.setParameter("endDate", endDate);
        q.setParameter("status", status.toLowerCase());

        if (!q.getResultList().isEmpty()) {
            return q.getResultList();
        } else {
            throw new NoResultException("Laundry Order not found.");
        }
    }

    @Override
    public List<LaundryOrder> getLaundryOrderByCompleteDateTime(Date startDate, Date endDate, String status) throws NoResultException {
        Query q;
        q = em.createQuery("SELECT lo FROM LaundryOrder lo WHERE "
                + "lo.completeDateTime >= :startDate"
                + " AND lo.completeDateTime <= :endDate"
                + " AND LOWER(lo.status) = :status");
        q.setParameter("startDate", startDate);
        q.setParameter("endDate", endDate);
        q.setParameter("status", status.toLowerCase());

        if (!q.getResultList().isEmpty()) {
            return q.getResultList();
        } else {
            throw new NoResultException("Laundry Order not found.");
        }
    }

    @Override
    public List<LaundryOrder> getLaundryOrderByRoom(Room r, String status) throws NoResultException {
        Query q;
        q = em.createQuery("SELECT lo FROM LaundryOrder lo WHERE "
                + "lo.room = :room"
                + " AND LOWER(lo.status) = :status");
        q.setParameter("room", r);
        q.setParameter("status", status.toLowerCase());

        if (!q.getResultList().isEmpty()) {
            return q.getResultList();
        } else {
            throw new NoResultException("Laundry Order not found.");
        }
    }

    @Override
    public List<LaundryOrder> getLaundryOrderByHouseKeeper(Staff s, String status) throws NoResultException {
        Query q;
        q = em.createQuery("SELECT lo FROM LaundryOrder lo WHERE "
                + "lo.houseKeeper = :houseKeeper"
                + " AND LOWER(lo.status) = :status");
        q.setParameter("houseKeeper", s);
        q.setParameter("status", status.toLowerCase());

        if (!q.getResultList().isEmpty()) {
            return q.getResultList();
        } else {
            throw new NoResultException("Laundry Order not found.");
        }
    }

    @Override
    public void deleteLaundryOrder(LaundryOrder lo) throws NoResultException {
        LaundryOrder laundryOrder = em.find(LaundryOrder.class, lo.getLaundryOrderID());
        if (laundryOrder != null) {
            em.remove(laundryOrder);
        } else {
            throw new NoResultException("Laundry Order not found");
        }

    }

    @Override
    public void createLaundryOrder(LaundryOrder lo) {
        em.persist(lo);
    }

    @Override
    public void updateLaundryOrder(LaundryOrder lo) throws NoResultException {
        LaundryOrder oldLo = em.find(LaundryOrder.class, lo.getLaundryOrderID());
        if (oldLo != null) {
            oldLo.setRoom(lo.getRoom());
            oldLo.setOrderDateTime(lo.getOrderDateTime());
            oldLo.setStatus(lo.getStatus());
            oldLo.setCompleteDateTime(lo.getCompleteDateTime());
            oldLo.setHouseKeeper(lo.getHouseKeeper());
            oldLo.setSpecialRequest(lo.getSpecialRequest());
            oldLo.setQty(lo.getQty());
        } else {
            throw new NoResultException("Laundry Order Not found");
        }
    }

    @Override
    public LaundryOrder getLastLaundryOrder() {
        Query q = em.createQuery("SELECT lo FROM LaundryOrder lo ORDER BY lo.laundryOrderID DESC");
        return (LaundryOrder) q.getResultList().get(0);
    }

}
