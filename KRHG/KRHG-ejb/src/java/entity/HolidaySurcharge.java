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
public class HolidaySurcharge implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long holidaySurchargeID;
    private String holidayName;
    @Temporal(TemporalType.DATE)
    private Date holidayDate;
    private double holidaySurchargePrice;
    
    public HolidaySurcharge(){
    
    }

    public HolidaySurcharge(String holidayName, Date holidayDate, double holidaySurchargePrice) {
        this.holidayName = holidayName;
        this.holidayDate = holidayDate;
        this.holidaySurchargePrice = holidaySurchargePrice;
    }
    
    public Long getHolidaySurchargeID() {
        return holidaySurchargeID;
    }

    public void setHolidaySurchargeID(Long holidaySurchargeID) {
        this.holidaySurchargeID = holidaySurchargeID;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public Date getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(Date holidayDate) {
        this.holidayDate = holidayDate;
    }

    public double getHolidaySurchargePrice() {
        return holidaySurchargePrice;
    }

    public void setHolidaySurchargePrice(double holidaySurchargePrice) {
        this.holidaySurchargePrice = holidaySurchargePrice;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (holidaySurchargeID != null ? holidaySurchargeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HolidaySurcharge)) {
            return false;
        }
        HolidaySurcharge other = (HolidaySurcharge) object;
        if ((this.holidaySurchargeID == null && other.holidaySurchargeID != null) || (this.holidaySurchargeID != null && !this.holidaySurchargeID.equals(other.holidaySurchargeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.HolidaySurcharge[ holidaySurchargeID=" + holidaySurchargeID + " ]";
    }
    
}
