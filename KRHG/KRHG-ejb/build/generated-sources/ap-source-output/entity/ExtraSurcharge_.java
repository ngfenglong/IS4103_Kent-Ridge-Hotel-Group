package entity;

import java.util.ArrayList;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-22T15:07:43")
@StaticMetamodel(ExtraSurcharge.class)
public class ExtraSurcharge_ { 

    public static volatile SingularAttribute<ExtraSurcharge, Long> surchargeID;
    public static volatile SingularAttribute<ExtraSurcharge, ArrayList> daysToChange;
    public static volatile SingularAttribute<ExtraSurcharge, Date> SurchargeTo;
    public static volatile SingularAttribute<ExtraSurcharge, Double> surchargePrice;
    public static volatile SingularAttribute<ExtraSurcharge, String> surchargeName;
    public static volatile SingularAttribute<ExtraSurcharge, Date> surchargeFrom;

}