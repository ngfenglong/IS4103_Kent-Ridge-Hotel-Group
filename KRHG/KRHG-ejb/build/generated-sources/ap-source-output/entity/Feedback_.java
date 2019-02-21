package entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-21T20:34:47")
@StaticMetamodel(Feedback.class)
public class Feedback_ { 

    public static volatile SingularAttribute<Feedback, Date> feedBackDate;
    public static volatile SingularAttribute<Feedback, String> feedBackMsg;
    public static volatile SingularAttribute<Feedback, String> feedBackFrom;
    public static volatile SingularAttribute<Feedback, Long> feedBackID;
    public static volatile SingularAttribute<Feedback, String> feedBackTitle;

}