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
public class AnnualLeave implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long annualLeaveID;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    private int numOfDays;
    private boolean approveStatus;
    @OneToOne
    private Staff approvedBy;
    @OneToOne
    private Staff createdBy;

    public Long getAnnualLeaveID() {
        return annualLeaveID;
    }

    public void setAnnualLeaveID(Long annualLeaveID) {
        this.annualLeaveID = annualLeaveID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (annualLeaveID != null ? annualLeaveID.hashCode() : 0);
        return hash;
        
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnnualLeave)) {
            return false;
        }
        AnnualLeave other = (AnnualLeave) object;
        if ((this.annualLeaveID == null && other.annualLeaveID != null) || (this.annualLeaveID != null && !this.annualLeaveID.equals(other.annualLeaveID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Leave[ annualLeaveID=" + annualLeaveID + " ]";
    }

    public Staff getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Staff createdBy) {
        this.createdBy = createdBy;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getNumOfDays() {
        return numOfDays;
    }

    public void setNumOfDays(int numOfDays) {
        this.numOfDays = numOfDays;
    }

    public boolean isApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(boolean approveStatus) {
        this.approveStatus = approveStatus;
    }

    public Staff getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Staff approvedBy) {
        this.approvedBy = approvedBy;
    }

}
