/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CleaningSchedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cleaningScheduleID;

    private String hotelCodeName;

    private String roomlevel;
    @OneToMany
    private List<Staff> listOfStaff;

    public CleaningSchedule() {
        listOfStaff = new ArrayList<>();
    }

    public CleaningSchedule(String hotelCodeName, String level) {
        this();
        this.hotelCodeName = hotelCodeName;
        this.roomlevel = level;
    }

    /**
     * @return the hotelCodeName
     */
    public String getHotelCodeName() {
        return hotelCodeName;
    }

    /**
     * @param hotelCodeName the hotelCodeName to set
     */
    public void setHotelCodeName(String hotelCodeName) {
        this.hotelCodeName = hotelCodeName;
    }

    /**
     * @return the level
     */
    public String getLevel() {
        return roomlevel;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(String level) {
        this.roomlevel = level;
    }

    public Long getCleaningScheduleID() {
        return cleaningScheduleID;
    }

    /**
     * @return the listOfStaff
     */
    public List<Staff> getListOfStaff() {
        return listOfStaff;
    }

    /**
     * @param listOfStaff the listOfStaff to set
     */
    public void setListOfStaff(List<Staff> listOfStaff) {
        this.listOfStaff = listOfStaff;
    }

    public void setCleaningScheduleID(Long cleaningScheduleID) {
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
        return "entity.CleaningSchedule[ cleaningScheduleID=" + cleaningScheduleID + " ]";
    }

}
