/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import error.NoResultException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
    private String hotelImage;

    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms;
    @OneToMany
    private List<HotelFacility> hotelFacilities;
    @OneToMany(mappedBy = "hotel")
    private List<Feedback> feedbacks;

    public Hotel() {
        rooms = new ArrayList<>();
        hotelFacilities = new ArrayList<>();
        feedbacks = new ArrayList<>();
    }

    public Hotel(String hotelName, String hotelCodeName, String hotelAddress, int hotelStar, String hotelContact, String hotelImage) {
        this();
        this.hotelName = hotelName;
        this.hotelCodeName = hotelCodeName;
        this.hotelAddress = hotelAddress;
        this.hotelStar = hotelStar;
        this.hotelContact = hotelContact;
        this.hotelImage = hotelImage;
    }

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
    public List<HotelFacility> getHotelFacilities() {
        return hotelFacilities;
    }

    /**
     * @param hotelFacilities the hotelFacilities to set
     */
    public void setHotelFacilities(List<HotelFacility> hotelFacilities) {
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
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * @param rooms the rooms to set
     */
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public String getHotelImage() {
        return hotelImage;
    }

    public void setHotelImage(String hotelImage) {
        this.hotelImage = hotelImage;
    }

    
    
    public void addHotelFacility(HotelFacility hotelFacility) throws NoResultException {
        if (hotelFacility != null && !this.getHotelFacilities().contains(hotelFacility)) {
            this.getHotelFacilities().add(hotelFacility);
        } else {
            throw new NoResultException("HotelFacility already added to Hotel");
        }
    }

    public void removeHotelFacility(HotelFacility hotelFacility) throws NoResultException {
        if (hotelFacility != null && this.getHotelFacilities().contains(hotelFacility)) {
            this.getHotelFacilities().remove(hotelFacility);
        } else {
            throw new NoResultException("HotelFacility has not been added to Hotel");
        }
    }

    public void addFeedback(Feedback feedback) throws NoResultException {
        if (feedback != null && !this.getFeedbacks().contains(feedback)) {
            this.getFeedbacks().add(feedback);
        } else {
            throw new NoResultException("HotelFacility already added to Hotel");
        }
    }

    public void removeFeedback(Feedback feedback) throws NoResultException {
        if (feedback != null && this.getFeedbacks().contains(feedback)) {
            this.getFeedbacks().remove(feedback);
        } else {
            throw new NoResultException("HotelFacility has not been added to Hotel");
        }
    }

    public void addRoom(Room room) throws NoResultException {
        if (room != null && !this.getRooms().contains(room)) {
            this.getRooms().add(room);
        } else {
            throw new NoResultException("Room already added to Hotel");
        }
    }

    public void removeRoom(Room room) throws NoResultException {
        if (room != null && this.getRooms().contains(room)) {
            this.getRooms().remove(room);
        } else {
            throw new NoResultException("Room has not been added to Hotel");
        }
    }

}
