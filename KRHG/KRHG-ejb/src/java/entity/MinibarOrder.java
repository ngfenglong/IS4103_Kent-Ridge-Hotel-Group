/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import error.NoResultException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 *
 * @author MC
 */
@Entity
public class MinibarOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long minibarOrderID;
    @OneToMany
    private List<MinibarOrderedItem> minibarItemOrdered;
    private Double totalPrice;

    public MinibarOrder() {
        minibarItemOrdered = new ArrayList<MinibarOrderedItem>();
    }

    public Long getMinibarOrderID() {
        return minibarOrderID;
    }

    public void setMinibarOrderID(Long minibarOrderID) {
        this.minibarOrderID = minibarOrderID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (minibarOrderID != null ? minibarOrderID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the minibarOrderID fields are not set
        if (!(object instanceof MinibarOrder)) {
            return false;
        }
        MinibarOrder other = (MinibarOrder) object;
        if ((this.minibarOrderID == null && other.minibarOrderID != null) || (this.minibarOrderID != null && !this.minibarOrderID.equals(other.minibarOrderID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MinibarOrder[ minibarOrderID=" + minibarOrderID + " ]";
    }

    public List<MinibarOrderedItem> getMinibarItemOrdered() {
        return minibarItemOrdered;
    }

    public void setMinibarItemOrdered(List<MinibarOrderedItem> minibarItemOrdered) {
        this.minibarItemOrdered = minibarItemOrdered;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public void addMinibarOrderedItem(MinibarOrderedItem moi) throws NoResultException {
        if (moi != null && !this.getMinibarItemOrdered().contains(moi)) {
            this.getMinibarItemOrdered().add(moi);
        } else {
            throw new NoResultException("Minibar Item already added to Order");
        }
    }

    public void removeMinibarOrderedItem(MinibarOrderedItem moi) throws NoResultException {
        if (moi != null && this.getMinibarItemOrdered().contains(moi)) {
            this.getMinibarItemOrdered().remove(moi);
        } else {
            throw new NoResultException("Minibar Item does not exist in Order");
        }
    }    

}
