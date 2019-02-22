package entity;

import entity.Staff;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-22T15:07:43")
@StaticMetamodel(WorkSchedule.class)
public class WorkSchedule_ { 

    public static volatile SingularAttribute<WorkSchedule, Staff> approver;
    public static volatile SingularAttribute<WorkSchedule, Integer> tue;
    public static volatile SingularAttribute<WorkSchedule, Integer> thur;
    public static volatile SingularAttribute<WorkSchedule, Integer> sat;
    public static volatile SingularAttribute<WorkSchedule, Staff> allocatedTo;
    public static volatile SingularAttribute<WorkSchedule, Integer> wed;
    public static volatile SingularAttribute<WorkSchedule, Date> dateOfTheWeek;
    public static volatile SingularAttribute<WorkSchedule, Long> workScheduleID;
    public static volatile SingularAttribute<WorkSchedule, Integer> fri;
    public static volatile SingularAttribute<WorkSchedule, Integer> mon;
    public static volatile SingularAttribute<WorkSchedule, Integer> sun;
    public static volatile SingularAttribute<WorkSchedule, String> approvedStatus;

}