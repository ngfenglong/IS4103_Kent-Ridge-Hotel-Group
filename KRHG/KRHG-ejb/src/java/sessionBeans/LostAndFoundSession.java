/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.LostAndFoundReport;
import error.NoResultException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class LostAndFoundSession implements LostAndFoundSessionLocal {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public List<LostAndFoundReport> getAllLostAndFoundReport() throws NoResultException {
        Query q;
        q = em.createQuery("SELECT lnfr FROM LostAndFoundReport lnfr ");
        return q.getResultList();
    }

    @Override
    public LostAndFoundReport getLostAndFoundReportID(Long lostAndFoundReportID) throws NoResultException {
        LostAndFoundReport lnfr = em.find(LostAndFoundReport.class, lostAndFoundReportID);
        if (lnfr != null) {
            return lnfr;
        } else {
            throw new NoResultException("Lost And Found Report not found.");
        }
    }

    @Override
    public List<LostAndFoundReport> getLostAndFoundReportByItemName(String itemName) throws NoResultException {
        Query q;      
        q = em.createQuery("SELECT lnfr FROM LostAndFoundReport lnfr WHERE "
                        + "lnfr.itemName = :itemName");
                q.setParameter("itemName", itemName.toLowerCase());
        if (!q.getResultList().isEmpty()) {
            return q.getResultList();
        } else {
            throw new NoResultException("Lost And Found Report not found.");
        }
    }

    @Override
    public List<LostAndFoundReport> getLostAndFoundReportByReportType(String reportType) throws NoResultException {
        Query q;      
        q = em.createQuery("SELECT lnfr FROM LostAndFoundReport lnfr WHERE "
                        + "lnfr.reportType = :reportType");
                q.setParameter("reportType", reportType.toLowerCase());
        if (!q.getResultList().isEmpty()) {
            return q.getResultList();
        } else {
            throw new NoResultException("Lost And Found Report not found.");
        }
    }

    @Override
    public void updateLostAndFoundReport(LostAndFoundReport lostAndFoundReport) throws NoResultException {
        LostAndFoundReport oldLostAndFoundReport = em.find(LostAndFoundReport.class, lostAndFoundReport.getReportID());
        if (oldLostAndFoundReport != null) {
            oldLostAndFoundReport.setItemName(lostAndFoundReport.getItemName());
            oldLostAndFoundReport.setReportedDate(lostAndFoundReport.getReportedDate());
            oldLostAndFoundReport.setItemDescription(lostAndFoundReport.getItemDescription());
            oldLostAndFoundReport.setContactNum(lostAndFoundReport.getContactNum());
            oldLostAndFoundReport.setReportType(lostAndFoundReport.getReportType());
            oldLostAndFoundReport.setIsResolved(lostAndFoundReport.getIsResolved());
            oldLostAndFoundReport.setKeywords(lostAndFoundReport.getKeywords());
            oldLostAndFoundReport.setItemImage(lostAndFoundReport.getItemImage());
            oldLostAndFoundReport.setReportedBy(lostAndFoundReport.getReportedBy());
        } else {
            throw new NoResultException("Lost And Found Report not found");
        }
    }

    @Override
    public void deleteLostAndFoundReport(Long lostAndFoundReportID) throws NoResultException {
        LostAndFoundReport lostAndFoundReport = em.find(LostAndFoundReport.class, lostAndFoundReportID);

        if (lostAndFoundReport != null) {
            em.remove(lostAndFoundReport);
        } else {
            throw new NoResultException("Lost And Found Report not found");
        }
    }

    @Override
    public void createLostAndFoundReport(LostAndFoundReport lostAndFoundReport) {
        em.persist(lostAndFoundReport);
    }

   
}
