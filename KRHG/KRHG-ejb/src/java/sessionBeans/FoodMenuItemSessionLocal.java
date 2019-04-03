/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.FoodMenuItem;
import error.NoResultException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author MC
 */
@Local
public interface FoodMenuItemSessionLocal {
    public void createCustomer(FoodMenuItem fmi);
    public List<FoodMenuItem> getAllFoodMenuItems();
    public FoodMenuItem getFoodMenuItemByID(Long fmiID) throws NoResultException;
            
    
}
