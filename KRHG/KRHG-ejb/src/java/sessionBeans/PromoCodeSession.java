/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.PromoCode;
import error.NoResultException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author MC
 */
@Stateless
public class PromoCodeSession implements PromoCodeSessionLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createPromoCode(PromoCode pc) {
        em.persist(pc);
    }

    @Override
    public List<PromoCode> getAllPromoCodes() {
        Query q;
        q = em.createQuery("SELECT pc FROM PromoCode pc");
        return q.getResultList();
    }

    @Override
    public void updatePromoCode(PromoCode pc) throws NoResultException {
        PromoCode oldPC = em.find(PromoCode.class, pc.getPromoCodeID());
        if (oldPC != null) {
            oldPC.setPromoCode(pc.getPromoCode());
            oldPC.setStartDate(pc.getStartDate());
            oldPC.setEndDate(pc.getEndDate());
            oldPC.setStatus(pc.getStatus());
            oldPC.setDiscount(pc.getDiscount());
        } else {
            throw new NoResultException("Promo Code Not found");
        }
    }
    
    @Override
    public void deletePromoCode(PromoCode pcID) throws NoResultException {
        PromoCode pc = em.find(PromoCode.class, pcID);
        if (pc != null) {
            em.remove(pc);
        } else {
            throw new NoResultException("Promo Code not found");
        }
    }    

}
