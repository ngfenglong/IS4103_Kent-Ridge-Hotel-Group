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

@Entity
public class RoomFacility implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomFacilityID;
    private String roomFacilityCategory;
    private String roomFacilityName;

    public Long getRoomFacilityId() {
        return roomFacilityID;
    }

    public void setRoomFacilityId(Long roomFacilityID) {
        this.roomFacilityID = roomFacilityID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomFacilityID != null ? roomFacilityID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoomFacility)) {
            return false;
        }
        RoomFacility other = (RoomFacility) object;
        if ((this.roomFacilityID == null && other.roomFacilityID != null) || (this.roomFacilityID != null && !this.roomFacilityID.equals(other.roomFacilityID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RoomFacility[ roomFacilityID=" + roomFacilityID + " ]";
    }
    
    
    /**
     * @return the roomFacilityCategory
     */
    public String getRoomFacilityCategory() {
        return roomFacilityCategory;
    }

    /**
     * @param roomFacilityCategory the roomFacilityCategory to set
     */
    public void setRoomFacilityCategory(String roomFacilityCategory) {
        this.roomFacilityCategory = roomFacilityCategory;
    }

    /**
     * @return the roomFacilityName
     */
    public String getRoomFacilityName() {
        return roomFacilityName;
    }

    /**
     * @param roomFacilityName the roomFacilityName to set
     */
    public void setRoomFacilityName(String roomFacilityName) {
        this.roomFacilityName = roomFacilityName;
    }
    
}
