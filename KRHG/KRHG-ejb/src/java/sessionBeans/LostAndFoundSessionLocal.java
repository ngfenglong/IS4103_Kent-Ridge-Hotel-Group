/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.LostAndFoundReport;
import error.NoResultException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author dk349
 */
@Local
public interface LostAndFoundSessionLocal {
    public List<LostAndFoundReport> getAllLostAndFoundReport() throws NoResultException;
    public LostAndFoundReport getLostAndFoundReportID(Long lostAndFoundReportID) throws NoResultException;
    public List<LostAndFoundReport> getLostAndFoundReportByItemName(String itemName) throws NoResultException;
    public List<LostAndFoundReport> getLostAndFoundReportByReportType(String reportType) throws NoResultException;
    public void updateLostAndFoundReport (LostAndFoundReport lostAndFoundReport) throws NoResultException;
    public void deleteLostAndFoundReport (Long lostAndFoundReportID) throws NoResultException;
    public void createLostAndFoundReport (LostAndFoundReport lostAndFoundReport);
}
