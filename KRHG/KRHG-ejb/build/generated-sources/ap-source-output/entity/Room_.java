package entity;

import entity.CleaningSchedule;
import entity.Hotel;
import entity.MinibarItem;
import entity.RoomFacility;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-22T15:07:43")
@StaticMetamodel(Room.class)
public class Room_ { 

    public static volatile SingularAttribute<Room, String> roomPax;
    public static volatile SingularAttribute<Room, String> roomHotel;
    public static volatile ListAttribute<Room, RoomFacility> roomFacilities;
    public static volatile SingularAttribute<Room, Hotel> hotel;
    public static volatile ListAttribute<Room, MinibarItem> miniBarItems;
    public static volatile SingularAttribute<Room, Long> roomID;
    public static volatile SingularAttribute<Room, String> roomName;
    public static volatile SingularAttribute<Room, String> roomType;
    public static volatile ListAttribute<Room, CleaningSchedule> cleaningSchedules;
    public static volatile SingularAttribute<Room, String> status;

}