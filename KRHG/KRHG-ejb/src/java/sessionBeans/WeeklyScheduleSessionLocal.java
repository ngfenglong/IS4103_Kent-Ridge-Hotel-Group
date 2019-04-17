/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Staff;
import entity.WeeklySchedule;
import error.NoResultException;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fengl
 */
@Local
public interface WeeklyScheduleSessionLocal {
    public List<WeeklySchedule> getAllWeeklySchedule();
    public WeeklySchedule getWeeklyScheduleByID(Long wID)  throws NoResultException ;
    public List<WeeklySchedule> getWeeklyScheduleByWeek(Date d);
    public List<WeeklySchedule> getWorkWeeklyScheduleByStaff(Staff s);
    public void createSchedule(WeeklySchedule w);
    public void deleteWeeklySchedule(Long wID) throws NoResultException;
    public void updateWeeklySchedule(WeeklySchedule w) throws NoResultException;
    
    public List<String> getDistinctDate(); 
}
