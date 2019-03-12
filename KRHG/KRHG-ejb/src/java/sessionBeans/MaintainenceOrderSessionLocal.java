/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.MaintainenceOrder;
import error.NoResultException;
import java.util.List;
import javax.ejb.Local;


@Local
public interface MaintainenceOrderSessionLocal {
    public List<MaintainenceOrder> getAllMaintainenceOrder() throws NoResultException;
    public MaintainenceOrder getMaintainenceOrderByID(Long maintainenceOrderID) throws NoResultException;
    public void updateMaintainenceOrder (MaintainenceOrder maintainenceOrder) throws NoResultException;
    public void deleteMaintainenceOrder (Long maintainenceOrderID) throws NoResultException;
    public void createMaintainenceOrder (MaintainenceOrder maintainenceOrder);
}
