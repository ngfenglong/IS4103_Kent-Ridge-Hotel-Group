/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.MemberTier;
import error.NoResultException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author fengl
 */
@Stateless
public class MemberTierSession implements MemberTierSessionLocal {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<MemberTier> getAllMemberTier() throws NoResultException {
        Query q;
        q = em.createQuery("SELECT m FROM MemberTier m ");
        return q.getResultList();
    }

    @Override
    public MemberTier getMemberTierByID(Long mID) throws NoResultException {
        MemberTier m = em.find(MemberTier.class, mID);
        if (m != null) {
            return m;
        } else {
            throw new NoResultException("MemberTier not found.");
        }
    }

    @Override
    public MemberTier getMemberTierByName(String tierName) throws NoResultException {
        Query q;
        q = em.createQuery("SELECT m FROM MemberTier m WHERE "
                + "LOWER(m.tierName) = :tierName");
        q.setParameter("tierName", tierName.toLowerCase());

        if (!q.getResultList().isEmpty()) {
            return (MemberTier) q.getResultList().get(0);
        } else {
            throw new NoResultException("MemberTier not found.");
        }
    }

    @Override
    public void updateMemberTier(MemberTier m) throws NoResultException {
        MemberTier oldM = em.find(MemberTier.class, m.getTierID());
        if (oldM != null) {
            oldM.setTierName(m.getTierName());
            oldM.setTierPoints(m.getTierPoints());
        } else {
            throw new NoResultException("MemberTier not found.");
        }
    }

    @Override
    public void deleteMemberTier(Long mID) throws NoResultException {
        MemberTier m = em.find(MemberTier.class, mID);

        if (m != null) {
            em.remove(m);
        } else {
            throw new NoResultException("MemberTier not found");
        }
    }

    @Override
    public void createMemberTier(MemberTier m) {
        em.persist(m);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
