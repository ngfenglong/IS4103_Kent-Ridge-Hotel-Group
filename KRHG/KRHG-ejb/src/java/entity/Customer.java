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
import java.util.List;
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
    private String firstName;
    private String lastName;
    private String password;
    private int points;
    private String gender;
    @OneToMany
    private List<RoomBooking> bookingHistories;
    @OneToMany
    private List<RoomBooking> currentBookings;
    private String email;
    private String mobileNum;
    @Temporal(TemporalType.DATE)
    private Date dateJoined;
    private boolean accountStatus;
    private boolean member;

    public Customer() {
        bookingHistories = new ArrayList<RoomBooking>();
        currentBookings = new ArrayList<RoomBooking>();
    }
    
    public Customer(String email, String password){
        this();
        this.email = email;
        this.password = password;
    }

    public Customer(String firstName, String lastName, String gender, String nric, String password, int points, String email, String mobileNum, Date dateJoined, String passportNum, boolean accountStatus) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.password = password;
        this.points = points;
        this.email = email;
        this.mobileNum = mobileNum;
        this.dateJoined = dateJoined;
        this.accountStatus = accountStatus;
    }
    
    
    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    

    public String getGender() {
        return gender;
    }


    public void setGender(String gender) {
        this.gender = gender;
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

    public List<RoomBooking> getBookingHistories() {
        return bookingHistories;
    }

    public void setBookingHistories(List<RoomBooking> currentBookings) {
        this.bookingHistories = currentBookings;
    }

    public List<RoomBooking> getCurrentBookings() {
        return currentBookings;
    }

    public void setCurrentBookings(List<RoomBooking> currentBookings) {
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

    public boolean getMember() {
        return member;
    }

    public void setMember(boolean member) {
        this.member = member;
    }
    

    public boolean getAccountStatus() {
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
