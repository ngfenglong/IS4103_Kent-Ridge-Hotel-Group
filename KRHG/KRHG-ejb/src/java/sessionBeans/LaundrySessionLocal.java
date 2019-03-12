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
import javax.ejb.Local;

/**
 *
 * @author dk349
 */
@Local
public interface LaundrySessionLocal {
    public List<LaundryOrder> getAllLaundryOrder();
    public LaundryOrder getLaundryOrderByID(Long laundryOrderID) throws NoResultException;
    public List<LaundryOrder> getLaundryOrderByOrderDateTime(Date startDate, Date endDate,String status) throws NoResultException;
    public List<LaundryOrder> getLaundryOrderByCompleteDateTime(Date startDate, Date endDate,String status) throws NoResultException;
    public List<LaundryOrder> getLaundryOrderByRoom(Room r,String status) throws NoResultException;
    public List<LaundryOrder> getLaundryOrderByHouseKeeper(Staff s,String status) throws NoResultException;
    public void deleteLaundryOrder(LaundryOrder lo) throws NoResultException;
    public void createLaundryOrder(LaundryOrder lo); 
    public void updateLaundryOrder(LaundryOrder lo) throws NoResultException;
}
