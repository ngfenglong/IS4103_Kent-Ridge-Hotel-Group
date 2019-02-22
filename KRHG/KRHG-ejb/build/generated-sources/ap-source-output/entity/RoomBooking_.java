package entity;

import entity.Customer;
import entity.Room;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-22T15:07:43")
@StaticMetamodel(RoomBooking.class)
public class RoomBooking_ { 

    public static volatile SingularAttribute<RoomBooking, Long> roomBookingID;
    public static volatile SingularAttribute<RoomBooking, Date> tranportTime;
    public static volatile SingularAttribute<RoomBooking, Double> price;
    public static volatile SingularAttribute<RoomBooking, Date> bookOutDate;
    public static volatile SingularAttribute<RoomBooking, Date> bookInDate;
    public static volatile SingularAttribute<RoomBooking, Boolean> hasTransport;
    public static volatile SingularAttribute<RoomBooking, Room> bookedRoom;
    public static volatile SingularAttribute<RoomBooking, Customer> bookedBy;
    public static volatile SingularAttribute<RoomBooking, String> pickUpLocation;
    public static volatile SingularAttribute<RoomBooking, String> status;

}