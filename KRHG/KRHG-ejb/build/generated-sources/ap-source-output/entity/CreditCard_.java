package entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-21T20:34:47")
@StaticMetamodel(CreditCard.class)
public class CreditCard_ { 

    public static volatile SingularAttribute<CreditCard, Date> expiryDate;
    public static volatile SingularAttribute<CreditCard, String> cardNum;
    public static volatile SingularAttribute<CreditCard, String> cvv;
    public static volatile SingularAttribute<CreditCard, Long> creditCardID;

}