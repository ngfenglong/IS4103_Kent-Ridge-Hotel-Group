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
public class MinibarOrderedItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long minibarOrderedItemID;
    private int qty;
    @OneToOne
    private MinibarItem minibarItem;
    @ManyToOne
    private MinibarOrder minibarOrder;

    public Long getMinibarOrderedItemID() {
        return minibarOrderedItemID;
    }

    public void setMinibarOrderedItemID(Long minibarOrderedItemID) {
        this.minibarOrderedItemID = minibarOrderedItemID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (minibarOrderedItemID != null ? minibarOrderedItemID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the minibarOrderedItemID fields are not set
        if (!(object instanceof MinibarOrderedItem)) {
            return false;
        }
        MinibarOrderedItem other = (MinibarOrderedItem) object;
        if ((this.minibarOrderedItemID == null && other.minibarOrderedItemID != null) || (this.minibarOrderedItemID != null && !this.minibarOrderedItemID.equals(other.minibarOrderedItemID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MinibarOrderedItem[ minibarOrderedItemID=" + minibarOrderedItemID + " ]";
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public MinibarItem getMinibarItem() {
        return minibarItem;
    }

    public void setMinibarItem(MinibarItem minibarItem) {
        this.minibarItem = minibarItem;
    }

    public MinibarOrder getMinibarOrder() {
        return minibarOrder;
    }

    public void setMinibarOrder(MinibarOrder minibarOrder) {
        this.minibarOrder = minibarOrder;
    }
    
}
