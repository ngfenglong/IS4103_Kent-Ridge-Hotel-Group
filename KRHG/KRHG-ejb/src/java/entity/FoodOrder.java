/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import error.NoResultException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Entity
public class FoodOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long foodOrderID;
    @OneToMany
    private List<FoodOrderedItem> foodOrdered;
    private Double totalPrice;
    private String specialRequest;
    private String status;
    private String orderTime;


    
    
    
    public FoodOrder() {
        foodOrdered = new ArrayList<FoodOrderedItem>();
        status = "ORDERED";
        orderTime =  sdf.format(new Timestamp(System.currentTimeMillis()));
        specialRequest = "No Onions!";      
    }

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

    public List<FoodOrderedItem> getFoodOrdered() {
        return foodOrdered;
    }

    public void setFoodOrdered(List<FoodOrderedItem> foodOrdered) {
        this.foodOrdered = foodOrdered;
    }

    public void addFoodOrderedItem(FoodOrderedItem fmi) throws NoResultException {
        if (fmi != null && !this.getFoodOrdered().contains(fmi)) {
            this.getFoodOrdered().add(fmi);
        } else {
            throw new NoResultException("Food already added to Food Order");
        }
    }

    public void removeFoodOrderedItem(FoodOrderedItem fmi) throws NoResultException {
        if (fmi != null && this.getFoodOrdered().contains(fmi)) {
            this.getFoodOrdered().remove(fmi);
        } else {
            throw new NoResultException("Food already added to Food Order");
        }
    }

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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the orderTime
     */
    public String getOrderTime() {
        return orderTime;
    }

    /**
     * @param orderTime the orderTime to set
     */
    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
}

