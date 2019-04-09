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
import javax.ejb.Local;

/**
 *
 * @author dk349
 */
@Local
public interface LaundrySessionLocal {

    public List<LaundryOrder> getAllLaundryOrder();

    public LaundryOrder getLaundryOrderByID(Long laundryOrderID) throws NoResultException;

    public List<LaundryOrder> getLaundryOrderByOrderDateTime(Date startDate, Date endDate, String status) throws NoResultException;

    public List<LaundryOrder> getLaundryOrderByCompleteDateTime(Date startDate, Date endDate, String status) throws NoResultException;

    public List<LaundryOrder> getLaundryOrderByRoom(Room r, String status) throws NoResultException;

    public List<LaundryOrder> getLaundryOrderByHouseKeeper(Staff s, String status) throws NoResultException;

    public void deleteLaundryOrder(LaundryOrder lo) throws NoResultException;

    public void createLaundryOrder(LaundryOrder lo);

    public void updateLaundryOrder(LaundryOrder lo) throws NoResultException;

    public LaundryOrder getLastLaundryOrder();
    
    public void createLaundryOrderedItem(LaundryOrderedItem loi);
    
    public void deleteLaundryOrderedItem(LaundryOrderedItem loi) throws NoResultException;
    
    public void createLaundryType(LaundryType lt);
    
    public List<LaundryType> getAllLaundryTypes();
    
    public void deleteLaundryType(LaundryType lt) throws NoResultException;
    
    public void updateLaundryType(LaundryType lt) throws NoResultException;
    
    public LaundryType getLastLaundryType() throws NoResultException;
    
    public LaundryOrderedItem getLastLaundryOrderedItem() throws NoResultException;
    
    public LaundryType getLaundryTypeByName(String laundryName) throws NoResultException;    
    
}
