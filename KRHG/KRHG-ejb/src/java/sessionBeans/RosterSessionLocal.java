/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.AnnualLeave;
import entity.Staff;
import entity.WorkSchedule;
import error.NoResultException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fengl
 */
@Local
public interface RosterSessionLocal {
    public List<WorkSchedule> getAllWorkSchedule();
    public WorkSchedule getWorkSceduleByID(Long wID)  throws NoResultException ;
    public List<WorkSchedule> getWorkScheduleByWeek(Date d);
    public List<WorkSchedule> getWorkScheduleByStaff(Staff s);
    public List<WorkSchedule> getWorkScheuduleByApproved();
    public List<WorkSchedule> getWorkScheduleByApprover(Staff s);
    public void createSchedule(WorkSchedule w);
    public void deleteWorkSchedule(Long wID) throws NoResultException;
    

    public void applyLeave(AnnualLeave l);
    public void cancelLeave(Long aID) throws NoResultException;
    public double viewPaySlip(Long sID);
    public void allocateSchedule(Long lID, Staff s);
    public void approveSchedule(WorkSchedule w);
    public void approveLeave(AnnualLeave l);
    public void autoScheduleByWeek(Date d);
}
