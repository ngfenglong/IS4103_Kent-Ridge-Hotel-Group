/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.CleaningSchedule;
import error.NoResultException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Congx2
 */
@Local
public interface CleaningScheduleSessionLocal {
    public List<CleaningSchedule> getAllCleaningSchedule();
    public CleaningSchedule getCleaningScheduleByID(Long cID) throws NoResultException;
    public List<CleaningSchedule> getCleaningScheduleByHotelCodeName(String hotelCodeName) throws NoResultException;
    public void deleteCleaningSchedule(Long cID) throws NoResultException;
    public void createCleaningSchedule(CleaningSchedule c); 
    public void updateCleaningSchedule(CleaningSchedule c) throws NoResultException;
}
