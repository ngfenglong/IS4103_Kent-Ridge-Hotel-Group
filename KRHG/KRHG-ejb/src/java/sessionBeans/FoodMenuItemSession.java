/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.FoodMenuItem;
import error.NoResultException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class FoodMenuItemSession implements FoodMenuItemSessionLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createCustomer(FoodMenuItem fmi) {
        em.persist(fmi);
    }
    
    @Override
    public List<FoodMenuItem> getAllFoodMenuItems() {
        Query q;
        q = em.createQuery("SELECT fmi FROM FoodMenuItem fmi");
        return q.getResultList();
    }
    @Override
    public FoodMenuItem getFoodMenuItemByID(Long fmiID) throws NoResultException {
        FoodMenuItem fmi = em.find(FoodMenuItem.class, fmiID);
        if (fmi != null) {
            return fmi;
        } else {
            throw new NoResultException("Food Menu Item not found.");
        }
    }
    
    
    
 
}
