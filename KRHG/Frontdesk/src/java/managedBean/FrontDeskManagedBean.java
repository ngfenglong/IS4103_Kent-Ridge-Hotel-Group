/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import error.NoResultException;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import sessionBeans.BookingSessionLocal;
import sessionBeans.RoomSessionLocal;

/**
 *
 * @author Congx2
 */
@Named(value = "frontDeskManagedBean")
@SessionScoped
public class FrontDeskManagedBean {

    /**
     * Creates a new instance of FrontDeskManagedBean
     */
    @EJB
    private RoomSessionLocal roomSessionLocal;
    
    @EJB
    private BookingSessionLocal bookSessionLocal;
    
    private String customerName;
    private String customerRoom;
    private Long passportNumber;
   // private List<room>

    public FrontDeskManagedBean() {
    }

    public Long getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(Long passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String CustomerName) {
        this.customerName = CustomerName;
    }

    public String getCustomerRoom() {
        return customerRoom;
    }

    public void setCustomerRoom(String customerRoom) {
        this.customerRoom = customerRoom;
    }

 // public String searchCustomerForCheckin() {
       // try{
       // bookSessionLocal.getRoomBookingByID(passportNumber);
        
      //  }
       // catch(NoResultException e){
       //     return 
      //  }
    //}

    public void searchRoomForCheckout() {

    }

    public void searchCustomer() {

    }
  /*  public List<> getTodayBooking(){
     //   bookSessionLocal.get
    }
*/

}
