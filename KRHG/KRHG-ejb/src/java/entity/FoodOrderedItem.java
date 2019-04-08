/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author MC
 */
@Entity
public class FoodOrderedItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long foodOrderedItemID;
    private int qty;
    @OneToOne
    private FoodMenuItem food;
    @ManyToOne
    private FoodOrder order;

    public Long getFoodOrderedItemID() {
        return foodOrderedItemID;
    }

    public void setFoodOrderedItemID(Long foodOrderedItemID) {
        this.foodOrderedItemID = foodOrderedItemID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (foodOrderedItemID != null ? foodOrderedItemID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the foodOrderedItemID fields are not set
        if (!(object instanceof FoodOrderedItem)) {
            return false;
        }
        FoodOrderedItem other = (FoodOrderedItem) object;
        if ((this.foodOrderedItemID == null && other.foodOrderedItemID != null) || (this.foodOrderedItemID != null && !this.foodOrderedItemID.equals(other.foodOrderedItemID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FoodOrderedItem[ foodOrderedItemID=" + foodOrderedItemID + " ]";
    }
    

    public int getQty() {
        return qty;
    }


    public void setQty(int qty) {
        this.qty = qty;
    }


    public FoodMenuItem getFood() {
        return food;
    }


    public void setFood(FoodMenuItem food) {
        this.food = food;
    }


    public FoodOrder getOrder() {
        return order;
    }


    public void setOrder(FoodOrder order) {
        this.order = order;
    }    
    
}
