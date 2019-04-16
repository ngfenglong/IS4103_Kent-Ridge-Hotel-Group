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
public class MinibarStock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long minibarStockID;
    private String minibarItemName;
    private String hotelCodeName;
    private int currentStock;
    private int alert;

    public MinibarStock() {

    }
    
    public MinibarStock(String minibarItemName, String hotelCodeName, int currentStock, int alert) {
        this.minibarItemName = minibarItemName;
        this.hotelCodeName = hotelCodeName;
        this.currentStock = currentStock;
        this.alert = alert;
    }

    public Long getMinibarStockID() {
        return minibarStockID;
    }

    public void setMinibarStockID(Long minibarStockID) {
        this.minibarStockID = minibarStockID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (minibarStockID != null ? minibarStockID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the minibarStockID fields are not set
        if (!(object instanceof MinibarStock)) {
            return false;
        }
        MinibarStock other = (MinibarStock) object;
        if ((this.minibarStockID == null && other.minibarStockID != null) || (this.minibarStockID != null && !this.minibarStockID.equals(other.minibarStockID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MinibarStock[ minibarStockID=" + minibarStockID + " ]";
    }

    public String getMinibarItemName() {
        return minibarItemName;
    }

    public void setMinibarItemName(String minibarItemName) {
        this.minibarItemName = minibarItemName;
    }

    public String getHotelCodeName() {
        return hotelCodeName;
    }

    public void setHotelCodeName(String hotelCodeName) {
        this.hotelCodeName = hotelCodeName;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    public int getAlert() {
        return alert;
    }

    public void setAlert(int alert) {
        this.alert = alert;
    }

}
