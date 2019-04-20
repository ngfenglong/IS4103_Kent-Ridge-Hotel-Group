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

/**
 *
 * @author fengl
 */
@Entity
public class RoomPricing implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomPricingID;
    private String pricingName;
    private double price;

    public RoomPricing() {
    }

    public RoomPricing(String pricingName, double price) {
        this.pricingName = pricingName;
        this.price = price;
    }

    public Long getRoomPricingID() {
        return roomPricingID;
    }

    public void setRoomPricingID(Long roomPricingID) {
        this.roomPricingID = roomPricingID;
    }

    public String getPricingName() {
        return pricingName;
    }

    public void setPricingName(String pricingName) {
        this.pricingName = pricingName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomPricingID != null ? roomPricingID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoomPricing)) {
            return false;
        }
        
        RoomPricing other = (RoomPricing) object;
        if ((this.roomPricingID == null && other.roomPricingID != null) || (this.roomPricingID != null && !this.roomPricingID.equals(other.roomPricingID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RoomPricing[ roomPricingID=" + roomPricingID + " ]";
    }

    public String hotelCode() {
        String s = pricingName;
        String[] data = s.split("_", 2);
        return data[0]; 
    }
    
    public String roomType(){
        String s = pricingName;
        String[] data = s.split("_", 2);
        return data[1]; 
    }

}
