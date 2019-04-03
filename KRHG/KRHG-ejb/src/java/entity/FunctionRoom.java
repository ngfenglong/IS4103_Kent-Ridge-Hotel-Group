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

/**
 *
 * @author MC
 */
@Entity
public class FunctionRoom implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long functionRoomID;
    private String functionRoomName;
    private int functionRoomPax;
    private String status; //Available, Unavailable, Occupied
    private double price;
    @ManyToOne
    private Hotel hotel;
    
    public FunctionRoom() {
        
    }
    
    public FunctionRoom(String functionRoomName, int functionRoomPax, String status, double price, Hotel hotel) {
        this.functionRoomName = functionRoomName;
        this.functionRoomPax = functionRoomPax;
        this.status = status;
        this.price = price;
        this.hotel = hotel;
    }

    public Long getFunctionRoomID() {
        return functionRoomID;
    }

    public void setFunctionRoomID(Long functionRoomID) {
        this.functionRoomID = functionRoomID;
    }

    public String getFunctionRoomName() {
        return functionRoomName;
    }

    public void setFunctionRoomName(String functionRoomName) {
        this.functionRoomName = functionRoomName;
    }

    public int getFunctionRoomPax() {
        return functionRoomPax;
    }

    public void setFunctionRoomPax(int functionRoomPax) {
        this.functionRoomPax = functionRoomPax;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (functionRoomID != null ? functionRoomID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FunctionRoom)) {
            return false;
        }
        FunctionRoom other = (FunctionRoom) object;
        if ((this.functionRoomID == null && other.functionRoomID != null) || (this.functionRoomID != null && !this.functionRoomID.equals(other.functionRoomID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FunctionRoom[functionRoomID=" + functionRoomID + " ]";
    }

}
