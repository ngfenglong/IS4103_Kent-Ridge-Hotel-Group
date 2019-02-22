package entity;

import entity.Feedback;
import entity.HotelFacility;
import entity.Room;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-22T17:45:24")
@StaticMetamodel(Hotel.class)
public class Hotel_ { 

    public static volatile ListAttribute<Hotel, Room> rooms;
    public static volatile SingularAttribute<Hotel, Integer> hotelStar;
    public static volatile SingularAttribute<Hotel, String> hotelContact;
    public static volatile ListAttribute<Hotel, HotelFacility> hotelFacilities;
    public static volatile ListAttribute<Hotel, Feedback> feedbacks;
    public static volatile SingularAttribute<Hotel, String> hotelCodeName;
    public static volatile SingularAttribute<Hotel, String> hotelAddress;
    public static volatile SingularAttribute<Hotel, Long> hotelID;
    public static volatile SingularAttribute<Hotel, String> hotelName;

}