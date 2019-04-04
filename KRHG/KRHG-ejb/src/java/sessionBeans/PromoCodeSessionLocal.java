/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.PromoCode;
import error.NoResultException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author MC
 */
@Local
public interface PromoCodeSessionLocal {

    public void createPromoCode(PromoCode pc);

    public void updatePromoCode(PromoCode pc) throws NoResultException;

    public List<PromoCode> getAllPromoCodes();

    public void deletePromoCode(PromoCode pcID) throws NoResultException;

}
