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
public class WeeklySchedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long weeklyScheduleID;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    private Shift mon;
    private Shift tue;
    private Shift wed;
    private Shift thurs;
    private Shift fri;
    private Shift sat;
    private Shift sun;
    
    
    public Long getWeeklyScheduleID() {
        return weeklyScheduleID;
    }

    public void setWeeklyScheduleID(Long weeklyScheduleID) {
        this.weeklyScheduleID = weeklyScheduleID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (weeklyScheduleID != null ? weeklyScheduleID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WeeklySchedule)) {
            return false;
        }
        WeeklySchedule other = (WeeklySchedule) object;
        if ((this.weeklyScheduleID == null && other.weeklyScheduleID != null) || (this.weeklyScheduleID != null && !this.weeklyScheduleID.equals(other.weeklyScheduleID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.WeeklySchedule[ weeklyScheduleID=" + weeklyScheduleID + " ]";
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Shift getMon() {
        return mon;
    }

    public void setMon(Shift mon) {
        this.mon = mon;
    }

    public Shift getTue() {
        return tue;
    }

    public void setTue(Shift tue) {
        this.tue = tue;
    }

    public Shift getWed() {
        return wed;
    }

    public void setWed(Shift wed) {
        this.wed = wed;
    }

    public Shift getThurs() {
        return thurs;
    }

    public void setThurs(Shift thurs) {
        this.thurs = thurs;
    }

    public Shift getFri() {
        return fri;
    }

    public void setFri(Shift fri) {
        this.fri = fri;
    }

    public Shift getSat() {
        return sat;
    }

    public void setSat(Shift sat) {
        this.sat = sat;
    }

    public Shift getSun() {
        return sun;
    }

    public void setSun(Shift sun) {
        this.sun = sun;
    }

}
