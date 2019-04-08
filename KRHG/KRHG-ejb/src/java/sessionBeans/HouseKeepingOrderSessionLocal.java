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
import error.NoResultException;
import java.util.List;
import javax.ejb.Local;
import javax.persistence.Query;

/**
 *
 * @author dk349
 */
@Local
public interface HouseKeepingOrderSessionLocal {
    public List<HouseKeepingOrder> getAllHouseKeepingOrder() throws NoResultException;
    public List<HouseKeepingOrder> getHouseKeepingOrderByLevel(int level) throws NoResultException;
    public List<HouseKeepingOrder> getHouseKeepingOrderByLevelAndHotelCodeName(int level, String hotelCodeName) throws NoResultException;
    public List<HouseKeepingOrder> getHouseKeepingOrderByLevelAndHotelCodeNameAndStatus(int level, String hotelCodeName, String status) throws NoResultException;
    public HouseKeepingOrder getHouseKeepingOrderID(Long houseKeepingOrderID) throws NoResultException;
    public void updateHouseKeepingOrder (HouseKeepingOrder houseKeepingOrder) throws NoResultException;
    public void deleteHouseKeepingOrder (Long houseKeepingOrderID) throws NoResultException;
    public void createHouseKeepingOrder (HouseKeepingOrder houseKeepingOrder);
    
    
    //Minibar Item
    public List<MinibarItem> getAllMinibarItem();
    public MinibarItem getMinibarItemByID(Long mID) throws NoResultException;
    public MinibarItem getMinibarItemByItemName(String mName) throws NoResultException;
    public void updateMinibarItem (MinibarItem m) throws NoResultException;
    public void deleteMinibarItem (Long mID) throws NoResultException;
    public void createMinibarItem (MinibarItem m);
    public void createMinibarOrder(MinibarOrder mo);
    public void createMinibarOrderedItem (MinibarOrderedItem moi);
    public MinibarOrder getLastMinibarOrder() throws NoResultException; 
    public MinibarOrderedItem getLastMinibarOrderedItem() throws NoResultException; 
    
}
