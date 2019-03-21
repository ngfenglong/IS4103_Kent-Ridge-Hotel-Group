/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.MaintainenceOrder;
import error.NoResultException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class MaintainenceOrderSession implements MaintainenceOrderSessionLocal {
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public List<MaintainenceOrder> getAllMaintainenceOrder() throws NoResultException {
        Query q;
        q = em.createQuery("SELECT mo FROM MaintainenceOrder mo ");
        return q.getResultList();
    }

    @Override
    public MaintainenceOrder getMaintainenceOrderByID(Long maintainenceOrderID) throws NoResultException {
        MaintainenceOrder mo = em.find(MaintainenceOrder.class, maintainenceOrderID);
        if (mo != null) {
            return mo;
        } else {
            throw new NoResultException("Maintainence Order not found.");
        }
    }

    @Override
    public void updateMaintainenceOrder(MaintainenceOrder maintainenceOrder) throws NoResultException {
        MaintainenceOrder oldMaintainenceOrder = em.find(MaintainenceOrder.class, maintainenceOrder.getRequestID());
        if (oldMaintainenceOrder != null) {
            oldMaintainenceOrder.setLocation(maintainenceOrder.getLocation());
            oldMaintainenceOrder.setDateReported(maintainenceOrder.getDateReported());
            oldMaintainenceOrder.setDateResolved(maintainenceOrder.getDateResolved());
            oldMaintainenceOrder.setIsResolved(maintainenceOrder.getIsResolved());
            oldMaintainenceOrder.setStatus(maintainenceOrder.getStatus());
            oldMaintainenceOrder.setDescription(maintainenceOrder.getDescription());
        } else {
            throw new NoResultException("Maintainence Order not found");
        }

    }

    @Override
    public void deleteMaintainenceOrder(Long maintainenceOrderID) throws NoResultException {
        MaintainenceOrder maintainenceOrder = em.find(MaintainenceOrder.class, maintainenceOrderID);

        if (maintainenceOrder != null) {
            em.remove(maintainenceOrder);
        } else {
            throw new NoResultException("Maintainence Order not found");
        }
    }

    @Override
    public void createMaintainenceOrder(MaintainenceOrder maintainenceOrder) {
        em.persist(maintainenceOrder);
    }

}
