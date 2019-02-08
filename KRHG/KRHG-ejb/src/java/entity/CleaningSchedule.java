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

@Entity
public class CleaningSchedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cleaningScheduleID;
    
    @Temporal(TemporalType.DATE)
    private Date dateOfCleaning;
    private Room room;

    public Long getId() {
        return cleaningScheduleID;
    }

    public void setId(Long cleaningScheduleID) {
        this.cleaningScheduleID = cleaningScheduleID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cleaningScheduleID != null ? cleaningScheduleID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CleaningSchedule)) {
            return false;
        }
        CleaningSchedule other = (CleaningSchedule) object;
        if ((this.cleaningScheduleID == null && other.cleaningScheduleID != null) || (this.cleaningScheduleID != null && !this.cleaningScheduleID.equals(other.cleaningScheduleID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CleaningSchedule[ id=" + cleaningScheduleID + " ]";
    }
    
    /**
     * @return the dateOfCleaning
     */
    public Date getDateOfCleaning() {
        return dateOfCleaning;
    }

    /**
     * @param dateOfCleaning the dateOfCleaning to set
     */
    public void setDateOfCleaning(Date dateOfCleaning) {
        this.dateOfCleaning = dateOfCleaning;
    }

    /**
     * @return the room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * @param room the room to set
     */
    public void setRoom(Room room) {
        this.room = room;
    }
}
