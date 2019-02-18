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

/**
 *
 * @author fengl
 */
@Entity
public class RoomBooking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomBookingID;
    @Temporal(TemporalType.DATE)
    private Date bookInDate;
    @Temporal(TemporalType.DATE)
    private Date bookOutDate;
    private String status;
    private double price;
    @OneToOne
    private Room bookedRoom;
    @OneToOne
    private Customer boFokedBy;
    private boolean hasTransport;
    @Temporal(TemporalType.DATE)
    private Date tranportTime;
    private String pickUpLocation;
    @OneToMany
    private ArrayList<HolidaySurcharge> holidaySurcharges;
    @OneToMany
    private ArrayList<ExtraSurcharge> extraSurcharge;

    public Long getRoomBookingID() {
        return roomBookingID;
    }

    public void setRoomBookingID(Long roomBookingID) {
        this.roomBookingID = roomBookingID;
    }

    public Date getBookInDate() {
        return bookInDate;
    }

    public void setBookInDate(Date bookInDate) {
        this.bookInDate = bookInDate;
    }

    public Date getBookOutDate() {
        return bookOutDate;
    }

    public void setBookOutDate(Date bookOutDate) {
        this.bookOutDate = bookOutDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Room getBookedRoom() {
        return bookedRoom;
    }

    public void setBookedRoom(Room bookedRoom) {
        this.bookedRoom = bookedRoom;
    }

    public Customer getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(Customer bookedBy) {
        this.bookedBy = bookedBy;
    }

    public boolean isHasTransport() {
        return hasTransport;
    }

    public void setHasTransport(boolean hasTransport) {
        this.hasTransport = hasTransport;
    }

    public Date getTranportTime() {
        return tranportTime;
    }

    public void setTranportTime(Date tranportTime) {
        this.tranportTime = tranportTime;
    }

    public String getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(String pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public ArrayList<HolidaySurcharge> getHolidaySurcharges() {
        return holidaySurcharges;
    }

    public void setHolidaySurcharges(ArrayList<HolidaySurcharge> holidaySurcharges) {
        this.holidaySurcharges = holidaySurcharges;
    }

    public ArrayList<ExtraSurcharge> getExtraSurcharge() {
        return extraSurcharge;
    }

    public void setExtraSurcharge(ArrayList<ExtraSurcharge> extraSurcharge) {
        this.extraSurcharge = extraSurcharge;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomBookingID != null ? roomBookingID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoomBooking)) {
            return false;
        }
        RoomBooking other = (RoomBooking) object;
        if ((this.roomBookingID == null && other.roomBookingID != null) || (this.roomBookingID != null && !this.roomBookingID.equals(other.roomBookingID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RoomBooking[ roomBookingID=" + roomBookingID + " ]";
    }

}
