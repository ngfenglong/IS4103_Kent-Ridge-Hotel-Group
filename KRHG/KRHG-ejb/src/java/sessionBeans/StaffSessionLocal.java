/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Staff;
import error.NoResultException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author MC
 */
@Local
public interface StaffSessionLocal {
    public List<Staff> getAllStaffs();
    public Staff getStaffByID(Long sID) throws NoResultException;
    public Staff getStaffByNric(String nric) throws NoResultException;
    public void deleteStaff(Long sID) throws NoResultException;
    public void createStaff(Staff s);
    public void deactivateStaff(Staff s) throws NoResultException;
    public void updateStaff(Staff s) throws NoResultException;   
    
    
    //Logging in
    public boolean Login(Staff s);
    public void changePasword(Staff s, String newPass);
}
