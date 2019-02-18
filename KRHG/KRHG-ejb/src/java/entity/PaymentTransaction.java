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

@Entity
public class PaymentTransaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionID;
    private double totalPrice;
    private double initialPayment;
    private double finalPayment;
    private CreditCard creditCard;
    private String paymentType;
    private Customer payer;
    @Temporal(TemporalType.DATE)
    private Date transactionDateTime;
    private ArrayList<RoomBooking> roomsBooked;
    private FunctionRoomBooking functionRoomBooked;
    private TransportBooking transportBooked;

    public Long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Long transactionID) {
        this.transactionID = transactionID;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getInitialPayment() {
        return initialPayment;
    }

    public void setInitialPayment(double initialPayment) {
        this.initialPayment = initialPayment;
    }

    public double getFinalPayment() {
        return finalPayment;
    }

    public void setFinalPayment(double finalPayment) {
        this.finalPayment = finalPayment;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Customer getPayer() {
        return payer;
    }

    public void setPayer(Customer payer) {
        this.payer = payer;
    }

    public Date getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(Date transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public ArrayList<RoomBooking> getRoomsBooked() {
        return roomsBooked;
    }

    public void setRoomsBooked(ArrayList<RoomBooking> roomsBooked) {
        this.roomsBooked = roomsBooked;
    }

    public FunctionRoomBooking getFunctionRoomBooked() {
        return functionRoomBooked;
    }

    public void setFunctionRoomBooked(FunctionRoomBooking functionRoomBooked) {
        this.functionRoomBooked = functionRoomBooked;
    }

    public TransportBooking getTransportBooked() {
        return transportBooked;
    }

    public void setTransportBooked(TransportBooking transportBooked) {
        this.transportBooked = transportBooked;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionID != null ? transactionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentTransaction)) {
            return false;
        }
        PaymentTransaction other = (PaymentTransaction) object;
        if ((this.transactionID == null && other.transactionID != null) || (this.transactionID != null && !this.transactionID.equals(other.transactionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PaymentTransaction[ transactionID=" + transactionID + " ]";
    }

}
