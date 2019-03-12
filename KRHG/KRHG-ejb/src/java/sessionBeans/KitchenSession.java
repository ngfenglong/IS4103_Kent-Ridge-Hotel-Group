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
public class KitchenSession implements KitchenSessionLocal {
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public List<FoodMenuItem> getAllFoodMenuItem() {
        Query q;
        q = em.createQuery("SELECT fmi FROM FoodMenuItem fmi ");
        return q.getResultList();
    }

    @Override
    public FoodMenuItem getFoodMenuItemByID(Long foodMenuItemID) throws NoResultException {
        FoodMenuItem fmi = em.find(FoodMenuItem.class, foodMenuItemID);
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
                + "LOWER(fmi.FoodMenuItemName) = :foodMenuItemName");
        q.setParameter("foodMenuItemName", foodMenuItemName.toLowerCase());

        if (!q.getResultList().isEmpty()) {
            return (FoodMenuItem) q.getResultList().get(0);
        } else {
            throw new NoResultException("Food Menu Item not found.");
        }
    }

    @Override
    public void updateFoodMenuItem(FoodMenuItem foodMenuItem) throws NoResultException {
        FoodMenuItem oldFoodMenuItem = em.find(FoodMenuItem.class, foodMenuItem.getFoodMenuItemID());
        if (oldFoodMenuItem != null) {
            oldFoodMenuItem.setFoodMenuItemName(foodMenuItem.getFoodMenuItemName());
            oldFoodMenuItem.setFoodMenuItemDescription(foodMenuItem.getFoodMenuItemDescription());
            oldFoodMenuItem.setAvailability(foodMenuItem.getAvailability());
            oldFoodMenuItem.setUnitPrice(foodMenuItem.getUnitPrice());
          
        } else {
            throw new NoResultException("Food Menu Item not found");
        }
    }

    @Override
    public void deleteFoodMenuItem(Long foodMenuItemID) throws NoResultException {
        FoodMenuItem foodMenuItem = em.find(FoodMenuItem.class, foodMenuItemID);
        if (foodMenuItem != null) {
            em.remove(foodMenuItem);
        } else {
            throw new NoResultException("Food Menu Item not found");
        }
    }

    @Override
    public void createFoodMenuItem(FoodMenuItem foodMenuItem) {
        em.persist(foodMenuItem);
    }

}
