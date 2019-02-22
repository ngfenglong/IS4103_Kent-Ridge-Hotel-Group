package entity;

import entity.Room;
import entity.RoomFacility;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-22T15:07:43")
@StaticMetamodel(MaintainenceOrder.class)
public class MaintainenceOrder_ { 

    public static volatile SingularAttribute<MaintainenceOrder, Date> dateResolved;
    public static volatile SingularAttribute<MaintainenceOrder, Long> requestID;
    public static volatile SingularAttribute<MaintainenceOrder, Date> dateReported;
    public static volatile SingularAttribute<MaintainenceOrder, String> description;
    public static volatile SingularAttribute<MaintainenceOrder, RoomFacility> facility;
    public static volatile SingularAttribute<MaintainenceOrder, Boolean> isResolved;
    public static volatile SingularAttribute<MaintainenceOrder, Room> room;
    public static volatile SingularAttribute<MaintainenceOrder, String> status;

}