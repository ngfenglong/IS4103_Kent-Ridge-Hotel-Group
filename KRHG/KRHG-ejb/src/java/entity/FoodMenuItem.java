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
public class FoodMenuItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long FoodMenuItemID;
    private String FoodMenuItemName;
    private String FoodMenuItemDescription;
    private Boolean availability;
    private Double unitPrice;
    public Long getFoodMenuItemID() {
        return FoodMenuItemID;
    }

    public void setFoodMenuItemID(Long FoodMenuItemID) {
        this.FoodMenuItemID = FoodMenuItemID;
    }
    
        /**
     * @return the FoodMenuItemName
     */
    public String getFoodMenuItemName() {
        return FoodMenuItemName;
    }

    /**
     * @param FoodMenuItemName the FoodMenuItemName to set
     */
    public void setFoodMenuItemName(String FoodMenuItemName) {
        this.FoodMenuItemName = FoodMenuItemName;
    }

    /**
     * @return the FoodMenuItemDescription
     */
    public String getFoodMenuItemDescription() {
        return FoodMenuItemDescription;
    }

    /**
     * @param FoodMenuItemDescription the FoodMenuItemDescription to set
     */
    public void setFoodMenuItemDescription(String FoodMenuItemDescription) {
        this.FoodMenuItemDescription = FoodMenuItemDescription;
    }

    /**
     * @return the availability
     */
    public Boolean getAvailability() {
        return availability;
    }

    /**
     * @param availability the availability to set
     */
    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    /**
     * @return the unitPrice
     */
    public Double getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (FoodMenuItemID != null ? FoodMenuItemID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FoodMenuItem)) {
            return false;
        }
        FoodMenuItem other = (FoodMenuItem) object;
        if ((this.FoodMenuItemID == null && other.FoodMenuItemID != null) || (this.FoodMenuItemID != null && !this.FoodMenuItemID.equals(other.FoodMenuItemID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FoodMenuItem[ FoodMenuItemID=" + FoodMenuItemID + " ]";
    }
    
}
