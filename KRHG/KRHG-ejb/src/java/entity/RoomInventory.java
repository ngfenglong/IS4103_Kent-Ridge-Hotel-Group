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
public class RoomInventory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomInventoryID;
    private String inventoryName;
    private int qty;
    

    public Long getroomInventoryId() {
        return roomInventoryID;
    }

    public void setRoomInventoryId(Long roomInventoryID) {
        this.roomInventoryID = roomInventoryID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomInventoryID != null ? roomInventoryID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoomInventory)) {
            return false;
        }
        RoomInventory other = (RoomInventory) object;
        if ((this.roomInventoryID == null && other.roomInventoryID != null) || (this.roomInventoryID != null && !this.roomInventoryID.equals(other.roomInventoryID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RoomInventory[ id=" + roomInventoryID + " ]";
    }
    
    /**
     * @return the inventoryName
     */
    public String getInventoryName() {
        return inventoryName;
    }

    /**
     * @param inventoryName the inventoryName to set
     */
    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
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
