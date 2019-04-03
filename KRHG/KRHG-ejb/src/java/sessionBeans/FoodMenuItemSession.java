/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.FoodMenuItem;
import error.NoResultException;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class FoodMenuItemSession implements FoodMenuItemSessionLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createFoodMenuItem(FoodMenuItem fmi) {
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
    
    @Override
    public FoodMenuItem getFoodMenuItemByName(String foodMenuItemName) throws NoResultException {
        Query q;
        q = em.createQuery("SELECT fmi FROM FoodMenuItem fmi WHERE "
                + "LOWER(fmi.foodMenuItemName) = :foodMenuItemName");
        q.setParameter("foodMenuItemName", foodMenuItemName.toLowerCase());

        if (!q.getResultList().isEmpty()) {
            return (FoodMenuItem) q.getResultList().get(0);
        } else {
            throw new NoResultException("FoodMenuItem not found.");
        }
    }
    
    @Override
    public void deleteFoodMenuItem(Long fmiID) throws NoResultException {
        FoodMenuItem fmi = em.find(FoodMenuItem.class, fmiID);
        if (fmi != null) {
            em.remove(fmi);
        } else {
            throw new NoResultException("FoodMenuItem not found");
        }
    }
    
    @Override
    public void updateFoodMenuItem(FoodMenuItem fmi) throws NoResultException {
        FoodMenuItem oldFmi = em.find(FoodMenuItem.class, fmi.getFoodMenuItemID());
        if (oldFmi != null) {
            oldFmi.setCategory(fmi.getCategory());
            oldFmi.setAvailability(fmi.getAvailability());
            oldFmi.setFoodMenuItemName(fmi.getFoodMenuItemName());
            oldFmi.setFoodMenuItemDescription(fmi.getFoodMenuItemDescription());
            oldFmi.setUnitPrice(fmi.getUnitPrice());
            oldFmi.setFoodImage(fmi.getFoodImage());

 
        } else {
            throw new NoResultException("Customer Not found");
        }
    }
    
    
    
 
}
