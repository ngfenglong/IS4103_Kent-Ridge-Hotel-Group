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
public class TransportBooking implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transportBookingID;
    @Temporal(TemporalType.DATE)
    private Date bookedDateTime;
    @Temporal(TemporalType.DATE)
    private Date transportDateTime;
    private PaymentTransaction paymentTransaction;

    public Long getTransportBookingID() {
        return transportBookingID;
    }

    public void setTransportBookingID(Long transportBookingID) {
        this.transportBookingID = transportBookingID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transportBookingID != null ? transportBookingID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransportBooking)) {
            return false;
        }
        TransportBooking other = (TransportBooking) object;
        if ((this.transportBookingID == null && other.transportBookingID != null) || (this.transportBookingID != null && !this.transportBookingID.equals(other.transportBookingID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TransportBooking[ transportBookingID=" + transportBookingID + " ]";
    }
    
    
    /**
     * @return the bookedDateTime
     */
    public Date getBookedDateTime() {
        return bookedDateTime;
    }

    /**
     * @param bookedDateTime the bookedDateTime to set
     */
    public void setBookedDateTime(Date bookedDateTime) {
        this.bookedDateTime = bookedDateTime;
    }

    /**
     * @return the transportDateTime
     */
    public Date getTransportDateTime() {
        return transportDateTime;
    }

    /**
     * @param transportDateTime the transportDateTime to set
     */
    public void setTransportDateTime(Date transportDateTime) {
        this.transportDateTime = transportDateTime;
    }

    /**
     * @return the paymentTransaction
     */
    public PaymentTransaction getPaymentTransaction() {
        return paymentTransaction;
    }

    /**
     * @param paymentTransaction the paymentTransaction to set
     */
    public void setPaymentTransaction(PaymentTransaction paymentTransaction) {
        this.paymentTransaction = paymentTransaction;
    }

}
