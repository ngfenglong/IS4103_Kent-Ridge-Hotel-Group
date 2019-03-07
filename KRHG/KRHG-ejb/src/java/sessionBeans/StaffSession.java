/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Hotel;
import entity.Staff;
import entity.StaffType;
import error.NoResultException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class StaffSession implements StaffSessionLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Staff> getAllStaffs() {
        Query q;
        q = em.createQuery("SELECT s FROM Staff s");
        return q.getResultList();
    }

    @Override
    public Staff getStaffByID(Long sID) throws NoResultException {
        Staff s = em.find(Staff.class, sID);
        if (s != null) {
            return s;
        } else {
            throw new NoResultException("Staff not found.");
        }
    }

    @Override
    public Staff getStaffByNric(String nric) throws NoResultException {
        Query q;
        q = em.createQuery("SELECT s FROM Staff s WHERE "
                + "LOWER(s.nric) = :nric");
        q.setParameter("nric", nric.toLowerCase());

        if (!q.getResultList().isEmpty()) {
            return (Staff) q.getResultList().get(0);
        } else {
            throw new NoResultException("Staff not found.");
        }
    }

    @Override
    public void deleteStaff(Long sID) throws NoResultException {
        Staff s = em.find(Staff.class, sID);
        if (s != null) {
            em.remove(s);
        } else {
            throw new NoResultException("Staff not found");
        }
    }

    @Override
    public void createStaff(Staff s) {
        em.persist(s);
    }

    @Override
    public void deactivateStaff(Staff s) throws NoResultException {
        Staff oldS = em.find(Staff.class, s.getStaffID());
        if (oldS != null) {
            oldS.setAccountStatus(false);
            em.flush();
        } else {
            throw new NoResultException("Staff Not found");
        }
    }

    @Override
    public void activateStaff(Staff s) throws NoResultException {
        Staff oldS = em.find(Staff.class, s.getStaffID());
        if (oldS != null) {
            oldS.setAccountStatus(true);
            em.flush();
        } else {
            throw new NoResultException("Staff Not found");
        }
    }

    @Override
    public void updateStaff(Staff s) throws NoResultException {
        Staff oldS = em.find(Staff.class, s.getStaffID());
        if (oldS != null) {
            oldS.setName(s.getName());
            oldS.setUserName(s.getUserName());
            oldS.setEmail(s.getEmail());
            oldS.setPhoneNumber(s.getPhoneNumber());
            oldS.setGender(s.getGender());
            oldS.setNric(s.getNric());
            oldS.setAddress(s.getAddress());
            oldS.setHotelGroup(s.getHotelGroup());
            oldS.setJobTitle(s.getJobTitle());
            oldS.setDepartment(s.getDepartment());
            oldS.setManagerInCharge(s.getManagerInCharge());
            oldS.setEntitledLeaves(s.getEntitledLeaves());
            oldS.setAccountRights(s.getAccountRights());
            oldS.setNokName(s.getNokName());
            oldS.setNokAddress(s.getNokAddress());
            oldS.setNokPhoneNumber(s.getNokPhoneNumber());
        } else {
            throw new NoResultException("Hotel Not found");
        }
    }

    @Override
    public boolean Login(Staff s) {
        Query q = em.createQuery("SELECT s FROM Staff s WHERE "
                + "LOWER(s.userName) = :userName");
        q.setParameter("userName", s.getUserName().toLowerCase());

        if (!q.getResultList().isEmpty()) {
            Staff checkStaff = (Staff) q.getResultList().get(0);
            if (checkStaff.getPassword().equals(s.getPassword())) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    @Override
    public void changePasword(Staff s, String newPass) {
        Staff staff = em.find(Staff.class, s.getStaffID());
        staff.setPassword(newPass);
        em.flush();
    }

    @Override
    public boolean LoginWithType(Staff s, String type) {
        Query q = em.createQuery("SELECT s FROM Staff s WHERE "
                + "LOWER(s.userName) = :userName");
        q.setParameter("userName", s.getUserName().toLowerCase());

        if (!q.getResultList().isEmpty()) {
            Staff checkStaff = (Staff) q.getResultList().get(0);
            if (checkStaff.getPassword().equals(s.getPassword())) {
                List<StaffType> accountRights = checkStaff.getAccountRights();
                for (StaffType st : accountRights) {
                    if (st.getStaffTypeName().equals(type)) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        } else {
            return false;
        }
    }

    @Override
    public Staff getStaffByUsename(String username) throws NoResultException {
        Query q;
        q = em.createQuery("SELECT s FROM Staff s WHERE "
                + "LOWER(s.userName) = :userName");
        q.setParameter("userName", username.toLowerCase());

        if (!q.getResultList().isEmpty()) {
            return (Staff) q.getResultList().get(0);
        } else {
            throw new NoResultException("Staff not found.");
        }
    }

    @Override
    public void createStaffType(StaffType st) {
        em.persist(st);
    }

    @Override
    public void deleteStaffType(Long stID) throws NoResultException {
        StaffType st = em.find(StaffType.class, stID);
        if (st != null) {
            em.remove(st);
        } else {
            throw new NoResultException("Staff Type not found");
        }
    }

    @Override
    public List<StaffType> getAllStaffTypes() {
        Query q;
        q = em.createQuery("SELECT st FROM StaffType st");
        return q.getResultList();
    }

    @Override
    public StaffType getStaffTypeByID(Long stID) throws NoResultException {
        StaffType st = em.find(StaffType.class, stID);
        if (st != null) {
            return st;
        } else {
            throw new NoResultException("Staff Type not found.");
        }
    }

    @Override
    public StaffType getStaffTypeByName(String typeName) throws NoResultException {
        Query q;
        q = em.createQuery("SELECT st FROM StaffType st WHERE "
                + "LOWER(st.staffTypeName) = :staffTypeName");
        q.setParameter("staffTypeName", typeName.toLowerCase());

        if (!q.getResultList().isEmpty()) {
            return (StaffType) q.getResultList().get(0);
        } else {
            throw new NoResultException("Staff Type not found.");
        }
    }

    @Override
    public Staff getStaffByEmail(String email) throws NoResultException {
        Query q;
        q = em.createQuery("SELECT s FROM Staff s WHERE "
                + "LOWER(s.email) = :email");
        q.setParameter("email", email.toLowerCase());

        if (!q.getResultList().isEmpty()) {
            return (Staff) q.getResultList().get(0);
        } else {
            throw new NoResultException("Staff not found.");
        }
    }

}
