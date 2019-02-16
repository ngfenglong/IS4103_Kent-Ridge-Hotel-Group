/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Room implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomID;
    private String roomName;
    private String roomType;
    private String roomPax;
    private String roomHotel;
    private ArrayList<RoomFacility> roomFacilities;
    private String status;

    public Long getRoomID() {
        return roomID;
    }

    public void setRoomID(Long roomID) {
        this.roomID = roomID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomID != null ? roomID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Room)) {
            return false;
        }
        Room other = (Room) object;
        if ((this.roomID == null && other.roomID != null) || (this.roomID != null && !this.roomID.equals(other.roomID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Room[ roomID=" + roomID + " ]";
    }
    
    
    /**
     * @return the roomName
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * @param roomName the roomName to set
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * @return the roomType
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * @param roomType the roomType to set
     */
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    /**
     * @return the roomPax
     */
    public String getRoomPax() {
        return roomPax;
    }

    /**
     * @param roomPax the roomPax to set
     */
    public void setRoomPax(String roomPax) {
        this.roomPax = roomPax;
    }

    /**
     * @return the roomHotel
     */
    public String getRoomHotel() {
        return roomHotel;
    }

    /**
     * @param roomHotel the roomHotel to set
     */
    public void setRoomHotel(String roomHotel) {
        this.roomHotel = roomHotel;
    }

    /**
     * @return the roomFacilities
     */
    public ArrayList<RoomFacility> getRoomFacilities() {
        return roomFacilities;
    }

    /**
     * @param roomFacilities the roomFacilities to set
     */
    public void setRoomFacilities(ArrayList<RoomFacility> roomFacilities) {
        this.roomFacilities = roomFacilities;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
}
