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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomID;
    private String roomName;
    private String roomNumber;
    private String roomType;
    private int roomPax;
    private String status;
    @ManyToOne
    private Hotel hotel;
    @OneToMany
    private List<RoomFacility> roomFacilities;
    @OneToMany
    private List<MinibarItem> miniBarItems;
    @OneToMany(mappedBy = "room")
    private List<CleaningSchedule> cleaningSchedules;

    public Room() {
        this.roomFacilities = new ArrayList<RoomFacility>();
        this.miniBarItems = new ArrayList<MinibarItem>();
        this.cleaningSchedules = new ArrayList<CleaningSchedule>();
    }

    public Room(String roomName, String roomNumber, String roomType, int roomPax, String status, Hotel hotel) {
        this();
        this.roomName = roomName;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomPax = roomPax;
        this.status = status;
        this.hotel = hotel;
    }

    public Long getRoomID() {
        return roomID;
    }

    public void setRoomID(Long roomID) {
        this.roomID = roomID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomID != null ? roomID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Room)) {
            return false;
        }
        Room other = (Room) object;
        if ((this.roomID == null && other.roomID != null) || (this.roomID != null && !this.roomID.equals(other.roomID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Room[ roomID=" + roomID + " ]";
    }

    /**
     * @return the roomType
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * @param roomType the roomType to set
     */
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    /**
     * @return the roomPax
     */
    public int getRoomPax() {
        return roomPax;
    }

    /**
     * @param roomPax the roomPax to set
     */
    public void setRoomPax(int roomPax) {
        this.roomPax = roomPax;
    }

    /**
     * @return the roomHotel
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * @param roomHotel the roomHotel to set
     */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * @return the roomFacilities
     */
    public List<RoomFacility> getRoomFacilities() {
        return roomFacilities;
    }

    /**
     * @param roomFacilities the roomFacilities to set
     */
    public void setRoomFacilities(List<RoomFacility> roomFacilities) {
        this.roomFacilities = roomFacilities;
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

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public List<MinibarItem> getMiniBarItems() {
        return miniBarItems;
    }

    public void setMiniBarItems(List<MinibarItem> miniBarItems) {
        this.miniBarItems = miniBarItems;
    }

    public List<CleaningSchedule> getCleaningSchedules() {
        return cleaningSchedules;
    }

    public void setCleaningSchedules(List<CleaningSchedule> cleaningSchedules) {
        this.cleaningSchedules = cleaningSchedules;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void addCleaningSchedule(CleaningSchedule cleaningSchedule) throws NoResultException {
        if (cleaningSchedule != null && !this.getCleaningSchedules().contains(cleaningSchedule)) {
            this.getCleaningSchedules().add(cleaningSchedule);
        } else {
            throw new NoResultException("Cleaning schedule already added to Room");
        }
    }

    public void removeCleaningSchedule(CleaningSchedule cleaningSchedule) throws NoResultException {
        if (cleaningSchedule != null && this.getCleaningSchedules().contains(cleaningSchedule)) {
            this.getCleaningSchedules().remove(cleaningSchedule);
        } else {
            throw new NoResultException("Cleaning schedule has not been added to Room");
        }
    }

    public void addMinibarItem(MinibarItem minibarItem) throws NoResultException {
        if (minibarItem != null && !this.getMiniBarItems().contains(minibarItem)) {
            this.getMiniBarItems().add(minibarItem);
        } else {
            throw new NoResultException("Minibar Item already added to Room");
        }
    }

    public void removeMinibarItem(MinibarItem minibarItem) throws NoResultException {
        if (minibarItem != null && this.getMiniBarItems().contains(minibarItem)) {
            this.getMiniBarItems().remove(minibarItem);
        } else {
            throw new NoResultException("Minibar Item has not been added to Room");
        }
    }

    public void addRoomFacility(RoomFacility roomFacility) throws NoResultException {
        if (roomFacility != null && !this.getRoomFacilities().contains(roomFacility)) {
            this.getRoomFacilities().add(roomFacility);
        } else {
            throw new NoResultException("Room Facility is already added to Room");
        }
    }

    public void removeRoomFacility(RoomFacility roomFacility) throws NoResultException {
        if (roomFacility != null && this.getRoomFacilities().contains(roomFacility)) {
            this.getRoomFacilities().remove(roomFacility);
        } else {
            throw new NoResultException("Room Facility has not been added to Room");
        }
    }

}
