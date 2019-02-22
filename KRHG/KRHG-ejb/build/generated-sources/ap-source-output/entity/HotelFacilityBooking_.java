package entity;

import entity.Customer;
import entity.HotelFacility;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-22T17:45:24")
@StaticMetamodel(HotelFacilityBooking.class)
public class HotelFacilityBooking_ { 

    public static volatile SingularAttribute<HotelFacilityBooking, HotelFacility> bookedHotelFacility;
    public static volatile SingularAttribute<HotelFacilityBooking, Date> endDate;
    public static volatile SingularAttribute<HotelFacilityBooking, Double> price;
    public static volatile SingularAttribute<HotelFacilityBooking, Customer> bookedBy;
    public static volatile SingularAttribute<HotelFacilityBooking, Long> hotelFacilityBookingID;
    public static volatile SingularAttribute<HotelFacilityBooking, Date> startDate;
    public static volatile SingularAttribute<HotelFacilityBooking, String> status;

}