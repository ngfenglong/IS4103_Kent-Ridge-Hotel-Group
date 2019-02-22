package entity;

import entity.AnnualLeave;
import entity.Staff;
import entity.StaffType;
import entity.WorkSchedule;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-22T17:45:24")
@StaticMetamodel(Staff.class)
public class Staff_ { 

    public static volatile SingularAttribute<Staff, String> hotelGroup;
    public static volatile SingularAttribute<Staff, String> nokAddress;
    public static volatile SingularAttribute<Staff, String> address;
    public static volatile SingularAttribute<Staff, Integer> entitledLeaves;
    public static volatile SingularAttribute<Staff, String> gender;
    public static volatile ListAttribute<Staff, StaffType> accountRights;
    public static volatile SingularAttribute<Staff, WorkSchedule> workSchedule;
    public static volatile SingularAttribute<Staff, String> jobTitle;
    public static volatile SingularAttribute<Staff, String> nokPhoneNumber;
    public static volatile ListAttribute<Staff, AnnualLeave> appliedLeave;
    public static volatile SingularAttribute<Staff, String> userName;
    public static volatile SingularAttribute<Staff, String> nric;
    public static volatile SingularAttribute<Staff, Staff> managerInCharge;
    public static volatile SingularAttribute<Staff, Boolean> accountStatus;
    public static volatile SingularAttribute<Staff, String> password;
    public static volatile SingularAttribute<Staff, String> phoneNumber;
    public static volatile SingularAttribute<Staff, Date> joinDate;
    public static volatile SingularAttribute<Staff, String> nokName;
    public static volatile SingularAttribute<Staff, String> name;
    public static volatile SingularAttribute<Staff, String> department;
    public static volatile SingularAttribute<Staff, Long> staffID;
    public static volatile SingularAttribute<Staff, String> email;

}