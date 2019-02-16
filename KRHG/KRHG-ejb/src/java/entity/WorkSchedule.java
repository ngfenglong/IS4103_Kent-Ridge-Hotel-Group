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
public class WorkSchedule implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long workScheduleID;
    @Temporal(TemporalType.DATE)
    private Date workScheduleDate;
    private int workScheduleSlot;
    private String approvedStatus;
    private Staff approver;
    private Staff allocatedTo;

    public Long getWorkScheduleID() {
        return workScheduleID;
    }

    public void setWorkScheduleID(Long workScheduleID) {
        this.workScheduleID = workScheduleID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (workScheduleID != null ? workScheduleID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WorkSchedule)) {
            return false;
        }
        WorkSchedule other = (WorkSchedule) object;
        if ((this.workScheduleID == null && other.workScheduleID != null) || (this.workScheduleID != null && !this.workScheduleID.equals(other.workScheduleID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.WorkingSchedule[ workScheduleID=" + workScheduleID + " ]";
    }
    
     /**
     * @return the workScheduleDate
     */
    public Date getWorkScheduleDate() {
        return workScheduleDate;
    }

    /**
     * @param workScheduleDate the workScheduleDate to set
     */
    public void setWorkScheduleDate(Date workScheduleDate) {
        this.workScheduleDate = workScheduleDate;
    }

    /**
     * @return the workScheduleSlot
     */
    public int getWorkScheduleSlot() {
        return workScheduleSlot;
    }

    /**
     * @param workScheduleSlot the workScheduleSlot to set
     */
    public void setWorkScheduleSlot(int workScheduleSlot) {
        this.workScheduleSlot = workScheduleSlot;
    }

    /**
     * @return the approvedStatus
     */
    public String getApprovedStatus() {
        return approvedStatus;
    }

    /**
     * @param approvedStatus the approvedStatus to set
     */
    public void setApprovedStatus(String approvedStatus) {
        this.approvedStatus = approvedStatus;
    }

    /**
     * @return the approver
     */
    public Staff getApprover() {
        return approver;
    }

    /**
     * @param approver the approver to set
     */
    public void setApprover(Staff approver) {
        this.approver = approver;
    }

    /**
     * @return the allocatedTo
     */
    public Staff getAllocatedTo() {
        return allocatedTo;
    }

    /**
     * @param allocatedTo the allocatedTo to set
     */
    public void setAllocatedTo(Staff allocatedTo) {
        this.allocatedTo = allocatedTo;
    }
    
}
