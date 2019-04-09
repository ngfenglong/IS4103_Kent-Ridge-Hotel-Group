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
import javax.persistence.OneToOne;

/**
 *
 * @author MC
 */
@Entity
public class LaundryOrderedItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long laundryOrderedItemID;
    @OneToOne
    private LaundryType laundryType;
    private int qty;
    private String description;


    public LaundryType getLaundryType() {
        return laundryType;
    }

    public void setLaundryType(LaundryType laundryType) {
        this.laundryType = laundryType;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getLaundryOrderedItemID() {
        return laundryOrderedItemID;
    }

    public void setLaundryOrderedItemID(Long laundryOrderedItemID) {
        this.laundryOrderedItemID = laundryOrderedItemID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (laundryOrderedItemID != null ? laundryOrderedItemID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the laundryOrderedItemID fields are not set
        if (!(object instanceof LaundryOrderedItem)) {
            return false;
        }
        LaundryOrderedItem other = (LaundryOrderedItem) object;
        if ((this.laundryOrderedItemID == null && other.laundryOrderedItemID != null) || (this.laundryOrderedItemID != null && !this.laundryOrderedItemID.equals(other.laundryOrderedItemID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.LaundryOrderedItem[ laundryOrderedItemID=" + laundryOrderedItemID + " ]";
    }

}
