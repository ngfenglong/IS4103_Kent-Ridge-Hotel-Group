/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author fengl
 */
@Entity
public class LaundryOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long laundryOrderID;
    private Room room;
    @Temporal(TemporalType.DATE)
    private Date orderDateTime;
    private String status;
    private Date completeDateTime;
    private Staff houseKeeper;
    private String specialRequest;
    private int qty;

    public Long getLaundryOrderID() {
        return laundryOrderID;
    }

    public void setLaundryOrderID(Long laundryOrderID) {
        this.laundryOrderID = laundryOrderID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (laundryOrderID != null ? laundryOrderID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LaundryOrder)) {
            return false;
        }
        LaundryOrder other = (LaundryOrder) object;
        if ((this.laundryOrderID == null && other.laundryOrderID != null) || (this.laundryOrderID != null && !this.laundryOrderID.equals(other.laundryOrderID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.LaundryOrder[ laundryOrderID=" + laundryOrderID + " ]";
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(Date orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCompleteDateTime() {
        return completeDateTime;
    }

    public void setCompleteDateTime(Date completeDateTime) {
        this.completeDateTime = completeDateTime;
    }

    public Staff getHouseKeeper() {
        return houseKeeper;
    }

    public void setHouseKeeper(Staff houseKeeper) {
        this.houseKeeper = houseKeeper;
    }

    public String getSpecialRequest() {
        return specialRequest;
    }

    public void setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    
}
