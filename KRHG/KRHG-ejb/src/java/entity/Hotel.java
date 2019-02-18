/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author fengl
 */
@Entity
public class Hotel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long hotelID;
    private String hotelName;
    private String hotelCodeName;
    private String hotelAddress;
    private int hotelStar;
    private String hotelContact;
    @OneToMany
    private ArrayList<Room> rooms;
    @OneToMany
    private ArrayList<HotelFacility> hotelFacilities;
    public Long getHotelID() {
        return hotelID;
    }

    public void setHotelID(Long hotelID) {
        this.hotelID = hotelID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getHotelID() != null ? getHotelID().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hotel)) {
            return false;
        }
        Hotel other = (Hotel) object;
        if ((this.getHotelID() == null && other.getHotelID() != null) || (this.getHotelID() != null && !this.hotelID.equals(other.hotelID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Hotel[ hotelID=" + getHotelID() + " ]";
    }

    /**
     * @return the hotelName
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * @param hotelName the hotelName to set
     */
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    /**
     * @return the hotelCodeName
     */
    public String getHotelCodeName() {
        return hotelCodeName;
    }

    /**
     * @param hotelCodeName the hotelCodeName to set
     */
    public void setHotelCodeName(String hotelCodeName) {
        this.hotelCodeName = hotelCodeName;
    }

    /**
     * @return the hotelAddress
     */
    public String getHotelAddress() {
        return hotelAddress;
    }

    /**
     * @param hotelAddress the hotelAddress to set
     */
    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    /**
     * @return the hotelFacilities
     */
    public ArrayList<HotelFacility> getHotelFacilities() {
        return hotelFacilities;
    }

    /**
     * @param hotelFacilities the hotelFacilities to set
     */
    public void setHotelFacilities(ArrayList<HotelFacility> hotelFacilities) {
        this.hotelFacilities = hotelFacilities;
    }

    /**
     * @return the hotelStar
     */
    public int getHotelStar() {
        return hotelStar;
    }

    /**
     * @param hotelStar the hotelStar to set
     */
    public void setHotelStar(int hotelStar) {
        this.hotelStar = hotelStar;
    }

    /**
     * @return the hotelContact
     */
    public String getHotelContact() {
        return hotelContact;
    }

    /**
     * @param hotelContact the hotelContact to set
     */
    public void setHotelContact(String hotelContact) {
        this.hotelContact = hotelContact;
    }

    /**
     * @return the rooms
     */
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     * @param rooms the rooms to set
     */
    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }
    
}
