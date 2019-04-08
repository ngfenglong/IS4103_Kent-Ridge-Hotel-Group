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
import javax.persistence.ManyToMany;
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
    @Temporal(TemporalType.DATE)
    private Date bookingDate;
    private String status;
    private boolean hasTransport;
    private boolean hasExtraBed;
    private double price;
    @OneToOne
    private Room bookedRoom;
    @OneToOne
    private Customer bookedBy;
    @Temporal(TemporalType.DATE)
    private Date transportTime;
    private String pickUpLocation;
    private String emailAddress;
    private String passportNum;
    private String firstName;
    private String lastName;
    private String roomType;
    @OneToMany
    private List<LaundryOrder> listOfLaundryOrders = new ArrayList<>();
    @OneToMany
    private List<FoodOrder> listOfFoodOrders = new ArrayList<>();
    @OneToMany
    private List<MinibarOrder> listOfMinibarOrders = new ArrayList<>();

    public RoomBooking() {

    }
    
    public RoomBooking(Long roomBookingID, Date bookInDate, Date bookOutDate, String status, Customer bookedBy) {
        this();
        this.roomBookingID = roomBookingID;
        this.bookInDate = bookInDate;
        this.bookOutDate = bookOutDate;
        this.status = status;
        this.bookedBy = bookedBy;
    } //for junit    

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

    public boolean getHasTransport() {
        return hasTransport;
    }

    public void setHasTransport(boolean hasTransport) {
        this.hasTransport = hasTransport;
    }

    public Date getTransportTime() {
        return transportTime;
    }

    public void setTransportTime(Date transportTime) {
        this.transportTime = transportTime;
    }

    public String getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(String pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public Customer getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(Customer bookedBy) {
        this.bookedBy = bookedBy;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassportNum() {
        return passportNum;
    }

    public void setPassportNum(String passportNum) {
        this.passportNum = passportNum;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
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

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomBookingID != null ? roomBookingID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
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

    public boolean getHasExtraBed() {
        return hasExtraBed;
    }

    public void setHasExtraBed(boolean hasExtraBed) {
        this.hasExtraBed = hasExtraBed;
    }

    public List<LaundryOrder> getListOfLaundryOrders() {
        return listOfLaundryOrders;
    }

    public void setListOfLaundryOrders(List<LaundryOrder> listOfLaundryOrders) {
        this.listOfLaundryOrders = listOfLaundryOrders;
    }

    public List<FoodOrder> getListOfFoodOrders() {
        return listOfFoodOrders;
    }

    public void setListOfFoodOrders(List<FoodOrder> listOfFoodOrders) {
        this.listOfFoodOrders = listOfFoodOrders;
    }

    public List<MinibarOrder> getListOfMinibarOrders() {
        return listOfMinibarOrders;
    }

    public void setListOfMinibarOrders(List<MinibarOrder> listOfMinibarOrders) {
        this.listOfMinibarOrders = listOfMinibarOrders;
    }

    public void addLaundryOrder(LaundryOrder lo) throws NoResultException {
        if (lo != null && !this.getListOfLaundryOrders().contains(lo)) {
            this.getListOfLaundryOrders().add(lo);
        } else {
            throw new NoResultException("Laundry Order already added to Room Booking");
        }
    }

    public void removeLaundryOrder(LaundryOrder lo) throws NoResultException {
        if (lo != null && this.getListOfLaundryOrders().contains(lo)) {
            this.getListOfLaundryOrders().remove(lo);
        } else {
            throw new NoResultException("Laundry Order has not been added to Room Booking");
        }
    }

    public void addFoodOrder(FoodOrder fo) throws NoResultException {
        if (fo != null && !this.getListOfFoodOrders().contains(fo)) {
            this.getListOfFoodOrders().add(fo);
        } else {
            throw new NoResultException("Food Order already added to Room Booking");
        }
    }

    public void removeFoodOrder(FoodOrder fo) throws NoResultException {
        if (fo != null && this.getListOfFoodOrders().contains(fo)) {
            this.getListOfFoodOrders().remove(fo);
        } else {
            throw new NoResultException("Food Order has not been added to Room Booking");
        }
    }

    public void addMinibarOrder(MinibarOrder mbi) throws NoResultException {
        if (mbi != null && !this.getListOfMinibarOrders().contains(mbi)) {
            this.getListOfMinibarOrders().add(mbi);
        } else {
            throw new NoResultException("Minibar Order already added to Room Booking");
        }
    }

    public void removeMinibarOrder(MinibarOrder mbi) throws NoResultException {
        if (mbi != null && this.getListOfMinibarOrders().contains(mbi)) {
            this.getListOfMinibarOrders().remove(mbi);
        } else {
            throw new NoResultException("Minibar Order has not been added to Room Booking");
        }
    }
}
