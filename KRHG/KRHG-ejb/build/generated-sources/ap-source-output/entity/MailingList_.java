package entity;

import java.util.ArrayList;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-22T17:45:24")
@StaticMetamodel(MailingList.class)
public class MailingList_ { 

    public static volatile SingularAttribute<MailingList, Long> listID;
    public static volatile SingularAttribute<MailingList, ArrayList> listToSend;
    public static volatile SingularAttribute<MailingList, String> listName;

}