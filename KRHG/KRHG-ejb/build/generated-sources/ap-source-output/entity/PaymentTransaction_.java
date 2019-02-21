package entity;

import entity.CreditCard;
import entity.Customer;
import entity.FunctionRoomBooking;
import entity.RoomBooking;
import entity.TransportBooking;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-21T20:34:47")
@StaticMetamodel(PaymentTransaction.class)
public class PaymentTransaction_ { 

    public static volatile SingularAttribute<PaymentTransaction, Double> initialPayment;
    public static volatile SingularAttribute<PaymentTransaction, Double> totalPrice;
    public static volatile SingularAttribute<PaymentTransaction, Double> finalPayment;
    public static volatile SingularAttribute<PaymentTransaction, Date> transactionDateTime;
    public static volatile SingularAttribute<PaymentTransaction, FunctionRoomBooking> functionRoomBooked;
    public static volatile ListAttribute<PaymentTransaction, RoomBooking> roomsBooked;
    public static volatile SingularAttribute<PaymentTransaction, CreditCard> creditCard;
    public static volatile SingularAttribute<PaymentTransaction, TransportBooking> transportBooked;
    public static volatile SingularAttribute<PaymentTransaction, Customer> payer;
    public static volatile SingularAttribute<PaymentTransaction, Long> transactionID;
    public static volatile SingularAttribute<PaymentTransaction, String> paymentType;

}