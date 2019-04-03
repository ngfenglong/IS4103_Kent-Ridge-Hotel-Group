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

    public void createFoodMenuItem(FoodMenuItem fmi);

    public List<FoodMenuItem> getAllFoodMenuItems();

    public FoodMenuItem getFoodMenuItemByID(Long fmiID) throws NoResultException;

    public FoodMenuItem getFoodMenuItemByName(String name) throws NoResultException;

    public void deleteFoodMenuItem(Long fmiID) throws NoResultException;

    public void updateFoodMenuItem(FoodMenuItem fmi) throws NoResultException;

}
