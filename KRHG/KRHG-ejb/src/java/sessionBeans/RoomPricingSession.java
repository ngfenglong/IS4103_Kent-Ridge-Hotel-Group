/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.RoomPricing;
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
public class RoomPricingSession implements RoomPricingSessionLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createRoomPricing(RoomPricing rp) {
        em.persist(rp);
    }

    @Override
    public List<RoomPricing> getAllRoomPricings() {
        Query q;
        q = em.createQuery("SELECT rp FROM RoomPricing rp");
        return q.getResultList();
    }

    @Override
    public void deleteRoomPricing(Long rpID) throws NoResultException {
        RoomPricing rp = em.find(RoomPricing.class, rpID);
        if (rp != null) {
            em.remove(rp);
        } else {
            throw new NoResultException("Room Pricing not found");
        }
    }

    @Override
    public void updateRoomPricing(RoomPricing rp) throws NoResultException {
        RoomPricing oldRP = em.find(RoomPricing.class, rp.getRoomPricingID());
        if (oldRP != null) {
            oldRP.setPricingName(rp.getPricingName());
            oldRP.setPrice(rp.getPrice());
        } else {
            throw new NoResultException("Room Pricing Not found");
        }
    }

}
