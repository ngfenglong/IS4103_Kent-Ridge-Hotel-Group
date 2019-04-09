/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import error.NoResultException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    @OneToOne
    private Room room;
    @Temporal(TemporalType.DATE)
    private Date orderDateTime;
    private String status;
    @Temporal(TemporalType.DATE)
    private Date completeDateTime;
    @OneToOne
    private Staff houseKeeper;
    private String specialRequest;
    @OneToMany
    private List<LaundryOrderedItem> laundryOrderedItems;
    private double totalPrice;


    public LaundryOrder(){
        laundryOrderedItems = new ArrayList<LaundryOrderedItem>();
    }
    
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
    
    public List<LaundryOrderedItem> getLaundryOrderedItems() {
        return laundryOrderedItems;
    }

    public void setLaundryOrderedItems(List<LaundryOrderedItem> laundryOrderedItems) {
        this.laundryOrderedItems = laundryOrderedItems;
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }    
    
    public void addLaundryOrderedItem(LaundryOrderedItem loi) throws NoResultException {
        if (loi != null && !this.getLaundryOrderedItems().contains(loi)) {
            this.getLaundryOrderedItems().add(loi);
        } else {
            throw new NoResultException("Laundry Item already added to Laundry Order");
        }
    }

    public void removeLaundryOrderedItem(LaundryOrderedItem loi) throws NoResultException {
        if (loi != null && this.getLaundryOrderedItems().contains(loi)) {
            this.getLaundryOrderedItems().add(loi);
        } else {
            throw new NoResultException("Laundry Item not inside Laundry Order");
        }
    } 

}
