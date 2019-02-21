package entity;

import entity.Staff;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-21T20:34:47")
@StaticMetamodel(LostAndFoundReport.class)
public class LostAndFoundReport_ { 

    public static volatile SingularAttribute<LostAndFoundReport, String> contactNum;
    public static volatile SingularAttribute<LostAndFoundReport, String> reportType;
    public static volatile SingularAttribute<LostAndFoundReport, String> itemName;
    public static volatile SingularAttribute<LostAndFoundReport, Long> reportID;
    public static volatile SingularAttribute<LostAndFoundReport, ArrayList> keywords;
    public static volatile SingularAttribute<LostAndFoundReport, Date> reportedDate;
    public static volatile SingularAttribute<LostAndFoundReport, String> itemDescription;
    public static volatile SingularAttribute<LostAndFoundReport, Staff> reportedBy;
    public static volatile SingularAttribute<LostAndFoundReport, Boolean> isResolved;
    public static volatile SingularAttribute<LostAndFoundReport, String> itemImage;

}