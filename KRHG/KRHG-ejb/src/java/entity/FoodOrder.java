/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class FoodOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long foodOrderID;
    @OneToOne
    private List<FoodMenuItem> foodOrdered;
    private Double totalPrice;
    private String specialRequest;

    
    public Long getFoodOrderID() {
        return foodOrderID;
    }

    public void setFoodOrderID(Long foodOrderID) {
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
        return "entity.FoodOrder[ foodOrderID=" + foodOrderID + " ]";
    }
    
    /**
     * @return the foodOrdered
     */
    public List<FoodMenuItem> getFoodOrdered() {
        return foodOrdered;
    }

    /**
     * @param foodOrdered the foodOrdered to set
     */
    public void setFoodOrdered(List<FoodMenuItem> foodOrdered) {
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

}
