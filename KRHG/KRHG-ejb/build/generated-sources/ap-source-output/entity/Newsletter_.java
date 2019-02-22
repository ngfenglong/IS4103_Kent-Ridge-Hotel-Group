package entity;

import entity.Staff;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-22T17:45:24")
@StaticMetamodel(Newsletter.class)
public class Newsletter_ { 

    public static volatile SingularAttribute<Newsletter, Date> newsletterDate;
    public static volatile SingularAttribute<Newsletter, String> newsletterFile;
    public static volatile SingularAttribute<Newsletter, Long> newsletterID;
    public static volatile SingularAttribute<Newsletter, Staff> sentBy;

}