/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Staff;
import entity.StaffType;
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
    public Staff getStaffByEmail(String email) throws NoResultException;
    public Staff getStaffByUsename(String username) throws NoResultException;
    public void deleteStaff(Long sID) throws NoResultException;
    public void createStaff(Staff s);
    public void deactivateStaff(Staff s) throws NoResultException;
    public void activateStaff(Staff s) throws NoResultException;
    public void updateStaff(Staff s) throws NoResultException;   
    
    
    public void createStaffType(StaffType st);
    public void deleteStaffType(Long stID) throws NoResultException;
    public List<StaffType> getAllStaffTypes();
    public StaffType getStaffTypeByID(Long stID) throws NoResultException;
    public StaffType getStaffTypeByName(String typeName) throws NoResultException;
        
    //Logging in
    public boolean Login(Staff s);
    public boolean LoginWithType(Staff s, String type);
    public void changePasword(Staff s, String newPass);
}
