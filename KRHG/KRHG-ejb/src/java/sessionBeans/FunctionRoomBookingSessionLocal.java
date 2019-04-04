/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.FoodOrder;
import entity.FunctionRoomBooking;
import error.NoResultException;
import java.util.List;
import javax.ejb.Local;

@Local
public interface FunctionRoomBookingSessionLocal {
        
    public void createFunctionRoomBooking(FunctionRoomBooking frb);
    public List<FunctionRoomBooking> getAllFunctionRoomBookings();
    public FunctionRoomBooking getFunctionRoomBookingByID(Long frbID) throws NoResultException;
    public void updateRoom(FunctionRoomBooking frb) throws NoResultException;
    
 
}
