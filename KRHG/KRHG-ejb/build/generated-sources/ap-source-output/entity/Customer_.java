package entity;

import entity.RoomBooking;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-22T17:45:24")
@StaticMetamodel(Customer.class)
public class Customer_ { 

    public static volatile SingularAttribute<Customer, Boolean> accountStatus;
    public static volatile SingularAttribute<Customer, String> password;
    public static volatile SingularAttribute<Customer, String> mobileNum;
    public static volatile SingularAttribute<Customer, String> passportNum;
    public static volatile ListAttribute<Customer, RoomBooking> currentBookings;
    public static volatile SingularAttribute<Customer, Date> dateJoined;
    public static volatile SingularAttribute<Customer, Long> customerID;
    public static volatile ListAttribute<Customer, RoomBooking> bookingHistories;
    public static volatile SingularAttribute<Customer, String> nric;
    public static volatile SingularAttribute<Customer, String> email;
    public static volatile SingularAttribute<Customer, Integer> points;

}