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
 * @author MC
 */
@Entity
public class LaundryType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long laundryTypeID;
    private String laundryName;
    private double price;

    public Long getLaundryTypeID() {
        return laundryTypeID;
    }

    public void setLaundryTypeID(Long laundryTypeID) {
        this.laundryTypeID = laundryTypeID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (laundryTypeID != null ? laundryTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the laundryTypeID fields are not set
        if (!(object instanceof LaundryType)) {
            return false;
        }
        LaundryType other = (LaundryType) object;
        if ((this.laundryTypeID == null && other.laundryTypeID != null) || (this.laundryTypeID != null && !this.laundryTypeID.equals(other.laundryTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.LaundryType[ laundryTypeID=" + laundryTypeID + " ]";
    }
    
    public String getLaundryName() {
        return laundryName;
    }

    public void setLaundryName(String laundryName) {
        this.laundryName = laundryName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }    
}
