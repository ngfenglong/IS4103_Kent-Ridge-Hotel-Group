/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.MemberTier;
import error.NoResultException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fengl
 */
@Local
public interface MemberTierSessionLocal {
    public List<MemberTier> getAllMemberTier() throws NoResultException;
    public MemberTier getMemberTierByID(Long mID) throws NoResultException;
    public MemberTier getMemberTierByName(String tierName) throws NoResultException;
    public void updateMemberTier (MemberTier m) throws NoResultException;
    public void deleteMemberTier (Long mID) throws NoResultException;
    public void createMemberTier (MemberTier m);
}
