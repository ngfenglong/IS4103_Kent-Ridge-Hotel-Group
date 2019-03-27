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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author fengl
 */
@Entity
public class HouseKeepingOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long houseKeepingOrderID;
    private Room room;
    private String status;
    @Temporal(TemporalType.DATE)
    private Date orderDateTime;
    @Temporal(TemporalType.DATE)
    private Date completeDateTime;
    @OneToOne
    private Staff houseKeeper;
    private String specialRequest;
    private int level;
    private String requestType;
    public HouseKeepingOrder() {
        
    }
    
    public HouseKeepingOrder(Room room, String status, int level, Date orderDateTime, Date completeDateTime, Staff houseKeeper, String specialRequest, String requestType) {
        this();
        this.room = room;
        this.status = status;
        this.level = level;
        this.orderDateTime = orderDateTime;
        this.completeDateTime = completeDateTime;
        this.houseKeeper = houseKeeper;
        this.specialRequest = specialRequest;
        this.requestType = requestType;
    }
    
    
    /**
     * @return the requestType
     */
    public String getRequestType() {
        return requestType;
    }

    /**
     * @param requestType the requestType to set
     */
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
    
         /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    public Long getHouseKeepingOrderID() {
        return houseKeepingOrderID;
    }

    public void setHouseKeepingOrderID(Long houseKeepingOrderID) {
        this.houseKeepingOrderID = houseKeepingOrderID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (houseKeepingOrderID != null ? houseKeepingOrderID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HouseKeepingOrder)) {
            return false;
        }
        HouseKeepingOrder other = (HouseKeepingOrder) object;
        if ((this.houseKeepingOrderID == null && other.houseKeepingOrderID != null) || (this.houseKeepingOrderID != null && !this.houseKeepingOrderID.equals(other.houseKeepingOrderID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.HouseKeepingOrder[ houseKeepingOrderID=" + houseKeepingOrderID + " ]";
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(Date orderDateTime) {
        this.orderDateTime = orderDateTime;
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

}
