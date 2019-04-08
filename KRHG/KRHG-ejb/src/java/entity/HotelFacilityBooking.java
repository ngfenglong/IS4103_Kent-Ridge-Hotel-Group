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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class HotelFacilityBooking implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long hotelFacilityBookingID;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    private String status;
    private double price;
    @OneToOne
    private HotelFacility bookedHotelFacility;
    @OneToOne
    private Customer bookedBy;
    
    public HotelFacilityBooking(){
        
    }

    public HotelFacilityBooking(Long hotelFacilityBookingID, Date startDate, Date endDate, String status, Customer bookedBy) {
        this.hotelFacilityBookingID = hotelFacilityBookingID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.bookedBy = bookedBy;
    }//for junit
    
    public Long getHotelFacilityBookingID() {
        return hotelFacilityBookingID;
    }

    public void setHotelFacilityBookingID(Long hotelFacilityBookingID) {
        this.hotelFacilityBookingID = hotelFacilityBookingID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hotelFacilityBookingID != null ? hotelFacilityBookingID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HotelFacilityBooking)) {
            return false;
        }
        HotelFacilityBooking other = (HotelFacilityBooking) object;
        if ((this.hotelFacilityBookingID == null && other.hotelFacilityBookingID != null) || (this.hotelFacilityBookingID != null && !this.hotelFacilityBookingID.equals(other.hotelFacilityBookingID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.HotelFacilityBooking[ hotelFacilityBookingID=" + hotelFacilityBookingID + " ]";
    }
    
    
    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the bookedHotelFacility
     */
    public HotelFacility getBookedHotelFacility() {
        return bookedHotelFacility;
    }

    /**
     * @param bookedHotelFacility the bookedHotelFacility to set
     */
    public void setBookedHotelFacility(HotelFacility bookedHotelFacility) {
        this.bookedHotelFacility = bookedHotelFacility;
    }

    /**
     * @return the bookedBy
     */
    public Customer getBookedBy() {
        return bookedBy;
    }

    /**
     * @param bookedBy the bookedBy to set
     */
    public void setBookedBy(Customer bookedBy) {
        this.bookedBy = bookedBy;
    }


}
