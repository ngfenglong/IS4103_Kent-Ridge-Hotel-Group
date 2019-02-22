package entity;

import entity.FoodMenuItem;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-21T20:34:47")
@StaticMetamodel(FoodOrder.class)
public class FoodOrder_ { 

    public static volatile SingularAttribute<FoodOrder, FoodMenuItem> foodOrdered;
    public static volatile SingularAttribute<FoodOrder, Long> foodOrderID;
    public static volatile SingularAttribute<FoodOrder, Double> totalPrice;
    public static volatile SingularAttribute<FoodOrder, Integer> qty;
    public static volatile SingularAttribute<FoodOrder, String> specialRequest;

}