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

@Entity
public class FoodOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long foodOrderID;
    private FoodMenu foodOrdered;
    private Double totalPrice;
    private String specialRequest;
    private int qty;
    
    public Long getFoodOrderId() {
        return foodOrderID;
    }

    public void setId(Long foodOrderID) {
        this.foodOrderID = foodOrderID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (foodOrderID != null ? foodOrderID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FoodOrder)) {
            return false;
        }
        FoodOrder other = (FoodOrder) object;
        if ((this.foodOrderID == null && other.foodOrderID != null) || (this.foodOrderID != null && !this.foodOrderID.equals(other.foodOrderID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FoodOrder[ id=" + foodOrderID + " ]";
    }
    
    /**
     * @return the foodOrdered
     */
    public FoodMenu getFoodOrdered() {
        return foodOrdered;
    }

    /**
     * @param foodOrdered the foodOrdered to set
     */
    public void setFoodOrdered(FoodMenu foodOrdered) {
        this.foodOrdered = foodOrdered;
    }

    /**
     * @return the totalPrice
     */
    public Double getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return the specialRequest
     */
    public String getSpecialRequest() {
        return specialRequest;
    }

    /**
     * @param specialRequest the specialRequest to set
     */
    public void setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest;
    }

    /**
     * @return the qty
     */
    public int getQty() {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(int qty) {
        this.qty = qty;
    }

}
