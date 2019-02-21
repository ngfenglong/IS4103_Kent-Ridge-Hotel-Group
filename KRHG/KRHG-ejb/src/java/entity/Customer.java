/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import error.NoResultException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author fengl
 */
@Entity
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerID;
    private String nric;
    private String password;
    private int points;
    @OneToMany(mappedBy = "bookedBy")
    private ArrayList<RoomBooking> bookingHistories;
    @OneToMany
    private ArrayList<RoomBooking> currentBookings;
    private String email;
    private String mobileNum;
    @Temporal(TemporalType.DATE)
    private Date dateJoined;
    private String passportNum;
    private boolean accountStatus;

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public String getNric() {
        return nric;
    }

    public void setNric(String nric) {
        this.nric = nric;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public ArrayList<RoomBooking> getBookingHistories() {
        return bookingHistories;
    }

    public void setBookingHistories(ArrayList<RoomBooking> currentBookings) {
        this.bookingHistories = bookingHistories;
    }

    public ArrayList<RoomBooking> getCurrentBookings() {
        return currentBookings;
    }

    public void setCurrentBookings(ArrayList<RoomBooking> currentBookings) {
        this.currentBookings = currentBookings;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public String getPassportNum() {
        return passportNum;
    }

    public void setPassportNum(String passportNum) {
        this.passportNum = passportNum;
    }

    public boolean isAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(boolean accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerID != null ? customerID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.customerID == null && other.customerID != null) || (this.customerID != null && !this.customerID.equals(other.customerID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Customer[ customerID=" + customerID + " ]";
    }

    public void removeRoomBooking(RoomBooking roomBooking) throws NoResultException {
        if (roomBooking != null && this.getBookingHistories().contains(roomBooking)) {
            this.getBookingHistories().remove(roomBooking);
        } else {
            throw new NoResultException("Room booking has not been added");
        }
    }

    public void addRoomBooking(RoomBooking roomBooking) throws NoResultException {
        if (roomBooking != null && !this.getBookingHistories().contains(roomBooking)) {
            this.getBookingHistories().add(roomBooking);
        } else {
            throw new NoResultException("Room booking has already been done");
        }
    }
}
