package entity;

import entity.Room;
import entity.Staff;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-21T20:34:47")
@StaticMetamodel(HouseKeepingOrder.class)
public class HouseKeepingOrder_ { 

    public static volatile SingularAttribute<HouseKeepingOrder, Date> orderDateTime;
    public static volatile SingularAttribute<HouseKeepingOrder, Date> completeDateTime;
    public static volatile SingularAttribute<HouseKeepingOrder, Long> houseKeepingOrderID;
    public static volatile SingularAttribute<HouseKeepingOrder, String> specialRequest;
    public static volatile SingularAttribute<HouseKeepingOrder, Staff> houseKeeper;
    public static volatile SingularAttribute<HouseKeepingOrder, Room> room;
    public static volatile SingularAttribute<HouseKeepingOrder, String> status;

}