/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.FoodOrder;
import error.NoResultException;
import java.util.List;
import javax.ejb.Local;

@Local
public interface FoodOrderSessionLocal {
        
    public List<FoodOrder> getAllFoodOrder();
    public FoodOrder getAllFoodOrderByID(Long foodOrderID) throws NoResultException;
    public void updateFoodOrder (FoodOrder foodOrder) throws NoResultException;
    public void deleteFoodOrder (Long foodOrderID) throws NoResultException;
    public void createFoodOrder (FoodOrder foodOrder);
     public FoodOrder getLastFoodOrdered() throws NoResultException; 
}
