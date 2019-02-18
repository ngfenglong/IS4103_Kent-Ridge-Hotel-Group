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

@Entity
public class FunctionRoomBooking implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long functionRoomBookingID;
    @Temporal(TemporalType.DATE)
    private Date bookedFrom;
    @Temporal(TemporalType.DATE)
    private Date bookedTo;
    @OneToOne
    private PaymentTransaction paymentTransaction;

    public Long getFunctionRoomBookingID() {
        return functionRoomBookingID;
    }

    public void setFunctionRoomBookingID(Long functionRoomBookingID) {
        this.functionRoomBookingID = functionRoomBookingID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (functionRoomBookingID != null ? functionRoomBookingID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FunctionRoomBooking)) {
            return false;
        }
        FunctionRoomBooking other = (FunctionRoomBooking) object;
        if ((this.functionRoomBookingID == null && other.functionRoomBookingID != null) || (this.functionRoomBookingID != null && !this.functionRoomBookingID.equals(other.functionRoomBookingID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FunctionRoomBooking[ functionRoomBookingID=" + functionRoomBookingID + " ]";
    }
    
    /**
     * @return the bookedFrom
     */
    public Date getBookedFrom() {
        return bookedFrom;
    }

    /**
     * @param bookedFrom the bookedFrom to set
     */
    public void setBookedFrom(Date bookedFrom) {
        this.bookedFrom = bookedFrom;
    }

    /**
     * @return the bookedTo
     */
    public Date getBookedTo() {
        return bookedTo;
    }

    /**
     * @param bookedTo the bookedTo to set
     */
    public void setBookedTo(Date bookedTo) {
        this.bookedTo = bookedTo;
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
