package entity;

import entity.Staff;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-22T17:45:24")
@StaticMetamodel(AnnualLeave.class)
public class AnnualLeave_ { 

    public static volatile SingularAttribute<AnnualLeave, Boolean> approveStatus;
    public static volatile SingularAttribute<AnnualLeave, Integer> numOfDays;
    public static volatile SingularAttribute<AnnualLeave, Date> endDate;
    public static volatile SingularAttribute<AnnualLeave, Staff> createdBy;
    public static volatile SingularAttribute<AnnualLeave, Staff> approvedBy;
    public static volatile SingularAttribute<AnnualLeave, Long> annualLeaveID;
    public static volatile SingularAttribute<AnnualLeave, Date> startDate;

}