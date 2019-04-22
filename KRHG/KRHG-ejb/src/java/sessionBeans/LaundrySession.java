/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.LaundryOrder;
import entity.LaundryOrderedItem;
import entity.LaundryType;
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
    public List<LaundryOrderedItem> getAllLaundryOrderedItemByLaundryOrderID(Long loID)throws NoResultException{
        LaundryOrder laundryOrder = em.find(LaundryOrder.class, loID);
        return laundryOrder.getLaundryOrderedItems();
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
        System.out.println("in update laundry order");
        System.out.println(lo.getLaundryOrderID());
        LaundryOrder oldLo = em.find(LaundryOrder.class, lo.getLaundryOrderID());
        if (oldLo != null) {
            oldLo.setRoom(lo.getRoom());
            System.out.println(lo.getRoom());
            oldLo.setOrderDateTime(lo.getOrderDateTime());
            System.out.println(lo.getOrderDateTime());
            oldLo.setStatus(lo.getStatus());
            oldLo.setCompleteDateTime(lo.getCompleteDateTime());
            oldLo.setHouseKeeper(lo.getHouseKeeper());
            oldLo.setSpecialRequest(lo.getSpecialRequest());
            oldLo.setTotalPrice(lo.getTotalPrice());
            oldLo.setLaundryOrderedItems(lo.getLaundryOrderedItems());
            for(LaundryOrderedItem loi: lo.getLaundryOrderedItems()){
                System.out.println(loi.getLaundryOrderedItemID());
                System.out.println(loi.getDescription());
            }
            em.flush();
        } else {
            throw new NoResultException("Laundry Order Not found");
        }
    }

    @Override
    public LaundryOrder getLastLaundryOrder() {
        Query q = em.createQuery("SELECT lo FROM LaundryOrder lo ORDER BY lo.laundryOrderID DESC");
        return (LaundryOrder) q.getResultList().get(0);
    }

    @Override
    public void createLaundryType(LaundryType lt) {
        em.persist(lt);
    }
    
    @Override
    public List<LaundryType> getAllLaundryTypes() {
        Query q;
        q = em.createQuery("SELECT lt FROM LaundryType lt");
        return q.getResultList();
    }
    
    @Override
    public void deleteLaundryType(LaundryType lt) throws NoResultException {
        LaundryType laundryType = em.find(LaundryType.class, lt.getLaundryTypeID());
        if (laundryType != null) {
            em.remove(laundryType);
        } else {
            throw new NoResultException("Laundry Type not found");
        }
    }
    
    @Override
    public void updateLaundryType(LaundryType lt) throws NoResultException {
        LaundryType oldLt = em.find(LaundryType.class, lt.getLaundryTypeID());
        if (oldLt != null) {
            oldLt.setLaundryName(lt.getLaundryName());
            oldLt.setPrice(lt.getPrice());
        } else {
            throw new NoResultException("Laundry Type Not found");
        }
    }    

    @Override
    public void createLaundryOrderedItem(LaundryOrderedItem loi) {
        em.persist(loi);
    }
    
    @Override
    public void deleteLaundryOrderedItem(LaundryOrderedItem loi) throws NoResultException {
        LaundryOrderedItem laundryOrderedItem = em.find(LaundryOrderedItem.class, loi.getLaundryOrderedItemID());
        if (laundryOrderedItem != null) {
            em.remove(laundryOrderedItem);
        } else {
            throw new NoResultException("Laundry Ordered Item not found");
        }
    }            

    @Override
    public LaundryType getLastLaundryType() throws NoResultException {
        Query q = em.createQuery("SELECT lt FROM LaundryType lt ORDER BY lt.laundryTypeID DESC");
        return (LaundryType) q.getResultList().get(0);
    }
    
    @Override
    public LaundryOrderedItem getLastLaundryOrderedItem() throws NoResultException {
        Query q = em.createQuery("SELECT loi FROM LaundryOrderedItem loi ORDER BY loi.laundryOrderedItemID DESC");
        return (LaundryOrderedItem) q.getResultList().get(0);        
    }
    
    @Override
    public LaundryType getLaundryTypeByName(String laundryName) throws NoResultException {
        Query q;
        q = em.createQuery("SELECT ln FROM LaundryType ln WHERE "
                + "LOWER(ln.laundryName) = :laundryName");
        q.setParameter("laundryName", laundryName.toLowerCase());

        if (!q.getResultList().isEmpty()) {
            return (LaundryType) q.getResultList().get(0);
        } else {
            throw new NoResultException("Laundry Type not found.");
        }
    }    

    @Override
    public LaundryOrder getLastLaudryOrder() {
          Query q = em.createQuery("SELECT l FROM LaundryOrder l ORDER BY l.laundryOrderID DESC");
        return (LaundryOrder) q.getResultList().get(0);
    }
    
}
