package entity;

import entity.Room;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-21T20:34:47")
@StaticMetamodel(CleaningSchedule.class)
public class CleaningSchedule_ { 

    public static volatile SingularAttribute<CleaningSchedule, Date> dateOfCleaning;
    public static volatile SingularAttribute<CleaningSchedule, Long> cleaningScheduleID;
    public static volatile SingularAttribute<CleaningSchedule, Room> room;

}