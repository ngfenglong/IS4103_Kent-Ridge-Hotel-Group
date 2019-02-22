package entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-22T17:45:24")
@StaticMetamodel(PromoCode.class)
public class PromoCode_ { 

    public static volatile SingularAttribute<PromoCode, Long> promoCodeID;
    public static volatile SingularAttribute<PromoCode, Date> endDate;
    public static volatile SingularAttribute<PromoCode, Double> discount;
    public static volatile SingularAttribute<PromoCode, String> promoCode;
    public static volatile SingularAttribute<PromoCode, Date> startDate;
    public static volatile SingularAttribute<PromoCode, String> status;

}