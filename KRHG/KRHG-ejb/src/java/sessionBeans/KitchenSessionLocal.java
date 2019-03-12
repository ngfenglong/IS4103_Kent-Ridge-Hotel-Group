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
//Kitchen Session is used to handle foodMenuItem
@Local
public interface KitchenSessionLocal {
    public List<FoodMenuItem> getAllFoodMenuItem();
    public FoodMenuItem getFoodMenuItemByID(Long foodMenuItemID) throws NoResultException;
    public FoodMenuItem getFoodMenuItemByName(String foodMenuItemName) throws NoResultException;
    public void updateFoodMenuItem (FoodMenuItem foodMenuItem) throws NoResultException;
    public void deleteFoodMenuItem (Long foodMenuItemID) throws NoResultException;
    public void createFoodMenuItem (FoodMenuItem foodMenuItem);
}
