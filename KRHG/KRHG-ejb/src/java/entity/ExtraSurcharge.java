/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
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
public class ExtraSurcharge implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long surchargeID;
    private String surchargeName;
    @Temporal(TemporalType.DATE)
    private Date surchargeFrom;
    @Temporal(TemporalType.DATE)
    private Date SurchargeTo;
    private ArrayList<String> daysToChange;
    private double surchargePrice;

    public Long getSurchargeID() {
        return surchargeID;
    }

    public void setSurchargeID(Long surchargeID) {
        this.surchargeID = surchargeID;
    }

    public String getSurchargeName() {
        return surchargeName;
    }

    public void setSurchargeName(String surchargeName) {
        this.surchargeName = surchargeName;
    }

    public Date getSurchargeFrom() {
        return surchargeFrom;
    }

    public void setSurchargeFrom(Date surchargeFrom) {
        this.surchargeFrom = surchargeFrom;
    }

    public Date getSurchargeTo() {
        return SurchargeTo;
    }

    public void setSurchargeTo(Date SurchargeTo) {
        this.SurchargeTo = SurchargeTo;
    }

    public ArrayList<String> getDaysToChange() {
        return daysToChange;
    }

    public void setDaysToChange(ArrayList<String> daysToChange) {
        this.daysToChange = daysToChange;
    }

    public double getSurchargePrice() {
        return surchargePrice;
    }

    public void setSurchargePrice(double surchargePrice) {
        this.surchargePrice = surchargePrice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (surchargeID != null ? surchargeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExtraSurcharge)) {
            return false;
        }
        ExtraSurcharge other = (ExtraSurcharge) object;
        if ((this.surchargeID == null && other.surchargeID != null) || (this.surchargeID != null && !this.surchargeID.equals(other.surchargeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ExtraSurcharge[ surchargeID=" + surchargeID + " ]";
    }

}
