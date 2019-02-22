package entity;

import entity.Room;
import entity.Staff;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-22T15:07:43")
@StaticMetamodel(LaundryOrder.class)
public class LaundryOrder_ { 

    public static volatile SingularAttribute<LaundryOrder, Date> orderDateTime;
    public static volatile SingularAttribute<LaundryOrder, Date> completeDateTime;
    public static volatile SingularAttribute<LaundryOrder, Long> laundryOrderID;
    public static volatile SingularAttribute<LaundryOrder, Integer> qty;
    public static volatile SingularAttribute<LaundryOrder, String> specialRequest;
    public static volatile SingularAttribute<LaundryOrder, Staff> houseKeeper;
    public static volatile SingularAttribute<LaundryOrder, Room> room;
    public static volatile SingularAttribute<LaundryOrder, String> status;

}