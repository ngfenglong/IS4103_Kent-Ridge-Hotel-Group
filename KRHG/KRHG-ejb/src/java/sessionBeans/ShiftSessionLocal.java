/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Shift;
import error.NoResultException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fengl
 */
@Local
public interface ShiftSessionLocal {
    public List<Shift> getAllShift();
    public Shift getShiftByName(String name)  throws NoResultException ;
    public Shift getShiftID(Long sID) throws NoResultException ;
    public void createShift(Shift s);
    public void updateShift(Shift s) throws NoResultException ;
    public void deleteShift(Long sID) throws NoResultException;

}
