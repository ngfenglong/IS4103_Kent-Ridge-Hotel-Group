/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Logging;
import error.NoResultException;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fengl
 */
@Local
public interface LogSessionLocal {
    
    public List<Logging> getAllLoggingActivities();
    public Logging getLoggingByID(Long logID) throws NoResultException;
    public List<Logging> getLoggingByType(String t)throws NoResultException;
    public List<Logging> getLoggingByDate(Date d) throws NoResultException;
    public void createLogging(Logging l);
    public void deleteLogging(Long logID) throws NoResultException;
}
