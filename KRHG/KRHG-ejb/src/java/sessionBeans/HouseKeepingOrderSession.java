/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.HouseKeepingOrder;
import entity.MinibarItem;
import entity.MinibarOrder;
import entity.MinibarOrderedItem;
import entity.MinibarStock;
import error.NoResultException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author dk349
 */
@Stateless
public class HouseKeepingOrderSession implements HouseKeepingOrderSessionLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<HouseKeepingOrder> getAllHouseKeepingOrder() throws NoResultException {
        Query q;
        q = em.createQuery("SELECT ho FROM HouseKeepingOrder ho");
        return q.getResultList();
    }
    
    @Override
    public List<HouseKeepingOrder> getHouseKeepingOrderByLevel(int level) throws NoResultException {
        Query q;
        q = em.createQuery("SELECT ho FROM HouseKeepingOrder ho WHERE "
                + "ho.level = :level");
        q.setParameter("level", level);

        if (!q.getResultList().isEmpty()) {
            return q.getResultList();
        } else {
            throw new NoResultException("House Keeping Order not found.");
        }
    }
    
    @Override
    public List<HouseKeepingOrder> getHouseKeepingOrderByLevelAndHotelCodeName(int level, String hotelCodeName) throws NoResultException {
        Query q;
        q = em.createQuery("SELECT ho FROM HouseKeepingOrder ho WHERE "
                + "ho.level = :level AND "
                + "LOWER(ho.room.hotel.hotelCodeName) = :hotelCodeName");
        q.setParameter("level", level);
        q.setParameter("hotelCodeName", hotelCodeName.toLowerCase());

        if (!q.getResultList().isEmpty()) {
            return q.getResultList();
        } else {
            throw new NoResultException("House Keeping Order not found.");
        }
    }
    
    @Override
    public List<HouseKeepingOrder> getHouseKeepingOrderByLevelAndHotelCodeNameAndStatus(int level, String hotelCodeName, String status, boolean isSpecialRequest) throws NoResultException {
        Query q;
        q = em.createQuery("SELECT ho FROM HouseKeepingOrder ho WHERE "
                + "ho.level = :level AND "
                + "LOWER(ho.room.hotel.hotelCodeName) = :hotelCodeName AND "
                + "LOWER(ho.status) != :status AND "
                + "ho.isSpecialRequest = :isSpecialRequest"
                + " ORDER BY ho.room.roomNumber ASC");
        q.setParameter("level", level);
        q.setParameter("hotelCodeName", hotelCodeName.toLowerCase());
        q.setParameter("status", status.toLowerCase());
        q.setParameter("isSpecialRequest", isSpecialRequest);
        System.out.println("Status is: " + status.toLowerCase());
        if (!q.getResultList().isEmpty()) {
            return q.getResultList();
        } else {
            throw new NoResultException("House Keeping Order not found.");
        }
    }

    @Override
    public HouseKeepingOrder getHouseKeepingOrderID(Long houseKeepingOrderID) throws NoResultException {
        HouseKeepingOrder ho = em.find(HouseKeepingOrder.class, houseKeepingOrderID);
        if (ho != null) {
            return ho;
        } else {
            throw new NoResultException("HouseKeeping Order not found.");
        }
    }

    @Override
    public void updateHouseKeepingOrder(HouseKeepingOrder houseKeepingOrder) throws NoResultException {
        HouseKeepingOrder oldHouseKeepingOrder = em.find(HouseKeepingOrder.class, houseKeepingOrder.getHouseKeepingOrderID());
        if (oldHouseKeepingOrder != null) {
            oldHouseKeepingOrder.setRoom(houseKeepingOrder.getRoom());
            oldHouseKeepingOrder.setStatus(houseKeepingOrder.getStatus());
            oldHouseKeepingOrder.setOrderDateTime(houseKeepingOrder.getOrderDateTime());
            oldHouseKeepingOrder.setCompleteDateTime(houseKeepingOrder.getCompleteDateTime());
            oldHouseKeepingOrder.setHouseKeeper(houseKeepingOrder.getHouseKeeper());
            oldHouseKeepingOrder.setSpecialRequest(houseKeepingOrder.getSpecialRequest());
            oldHouseKeepingOrder.setIsSpecialRequest(houseKeepingOrder.getIsSpecialRequest());
        } else {
            throw new NoResultException("HouseKeeping Order not found");
        }

    }

    @Override
    public void deleteHouseKeepingOrder(Long houseKeepingOrderID) throws NoResultException {
        HouseKeepingOrder houseKeepingOrder = em.find(HouseKeepingOrder.class, houseKeepingOrderID);

        if (houseKeepingOrder != null) {
            em.remove(houseKeepingOrder);
        } else {
            throw new NoResultException("HouseKeeping Order not found");
        }
    }

    @Override
    public void createHouseKeepingOrder(HouseKeepingOrder houseKeepingOrder) {
        em.persist(houseKeepingOrder);
    }

    @Override
    public List<MinibarItem> getAllMinibarItem(){
        Query q;
        q = em.createQuery("SELECT m FROM MinibarItem m ");
        return q.getResultList();
    }

    @Override
    public MinibarItem getMinibarItemByID(Long mID) throws NoResultException {
        MinibarItem m = em.find(MinibarItem.class, mID);
        if (m != null) {
            return m;
        } else {
            throw new NoResultException("MinibarItem not found.");
        }
    }

    @Override
    public MinibarItem getMinibarItemByItemName(String mName) throws NoResultException {
        Query q;
        q = em.createQuery("SELECT m FROM MinibarItem m WHERE "
                + "LOWER(m.itemName) = :itemName");
        q.setParameter("itemName", mName.toLowerCase());

        if (!q.getResultList().isEmpty()) {
            return (MinibarItem) q.getResultList().get(0);
        } else {
            throw new NoResultException("MinibarItem not found.");
        }
    }

    @Override
    public void updateMinibarItem(MinibarItem m) throws NoResultException {
        MinibarItem oldMinibarItem = em.find(MinibarItem.class, m.getMinibarItemID());
        if (oldMinibarItem != null) {
            oldMinibarItem.setItemName(oldMinibarItem.getItemName());
            oldMinibarItem.setQty(oldMinibarItem.getQty());
            oldMinibarItem.setPrice(oldMinibarItem.getPrice());
            em.flush();
        } else {
            throw new NoResultException("MinibarItem not found");
        }
    }

    @Override
    public void deleteMinibarItem(Long mID) throws NoResultException {
        MinibarItem m = em.find(MinibarItem.class, mID);
        if (m != null) {
            em.remove(m);
        } else {
            throw new NoResultException("MinibarItem Order not found");
        }
    }

    @Override
    public void createMinibarItem(MinibarItem m) {
        em.persist(m);
    }
    
    @Override 
    public void createMinibarOrder(MinibarOrder mo) {
        em.persist(mo);
    }
    
    @Override
    public void createMinibarOrderedItem (MinibarOrderedItem moi) {
        em.persist(moi);
    }
    
    @Override 
    public MinibarOrder getLastMinibarOrder() throws NoResultException {
        Query q = em.createQuery("SELECT mo FROM MinibarOrder mo ORDER BY mo.minibarOrderID DESC");
        return (MinibarOrder) q.getResultList().get(0);
    }
    
    @Override
    public MinibarOrderedItem getLastMinibarOrderedItem() throws NoResultException {
        Query q = em.createQuery("SELECT moi FROM MinibarOrderedItem moi ORDER BY moi.minibarOrderedItemID DESC");
        return (MinibarOrderedItem) q.getResultList().get(0);
    }
    
    @Override
    public void createMinibarStock(MinibarStock ms) {
        em.persist(ms);
    }
    
    @Override
    public List<MinibarStock> getAllMinibarStock() {
        Query q;
        q = em.createQuery("SELECT ms FROM MinibarStock ms");
        return q.getResultList();
    }
    
    @Override
    public MinibarStock getMinibarStockByNameAndHotelCode(String minibarItemName, String hotelCodeName) throws NoResultException{
        Query q;
        q = em.createQuery("SELECT ms FROM MinibarStock ms WHERE "
                + "LOWER(ms.minibarItemName) = :minibarItemName AND LOWER(ms.hotelCodeName) = :hotelCodeName");
        q.setParameter("minibarItemName", minibarItemName.toLowerCase());
        q.setParameter("hotelCodeName", hotelCodeName.toLowerCase());

        if (!q.getResultList().isEmpty()) {
            return (MinibarStock) q.getResultList().get(0);
        } else {
            throw new NoResultException("MinibarStock not found.");
        }
    }
    
    @Override
    public void updateMinibarStock(MinibarStock ms) throws NoResultException{
        MinibarStock oldMinibarStock = em.find(MinibarStock.class, ms.getMinibarStockID());
        if (oldMinibarStock != null) {
            oldMinibarStock.setMinibarItemName(oldMinibarStock.getMinibarItemName());
            oldMinibarStock.setCurrentStock(oldMinibarStock.getCurrentStock());
            oldMinibarStock.setHotelCodeName(oldMinibarStock.getHotelCodeName());
            oldMinibarStock.setAlert(oldMinibarStock.getAlert());
            em.flush();
        } else {
            throw new NoResultException("MinibarStock not found");
        }
    }
    
    @Override
    public void deleteMinibarStock(Long msID) throws NoResultException {
        MinibarStock ms = em.find(MinibarStock.class, msID);
        if (ms != null) {
            em.remove(ms);
        } else {
            throw new NoResultException("MinibarStock not found");
        }
    }    

}
