/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;


import entity.FunctionRoomBooking;
import error.NoResultException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Anselm
 */
@Stateless
public class FunctionRoomBookingSession implements FunctionRoomBookingSessionLocal {

@PersistenceContext
    private EntityManager em;

    @Override
    public void createFunctionRoomBooking(FunctionRoomBooking frb) {
        em.persist(frb);
    }

    @Override
    public List<FunctionRoomBooking> getAllFunctionRoomBookings() {
        Query q;
        q = em.createQuery("SELECT frb FROM FunctionRoomBooking frb");
        return q.getResultList();
    }

    @Override
    public FunctionRoomBooking getAllFunctionRoomBookingByID(Long frbID) throws NoResultException {
        FunctionRoomBooking frb = em.find(FunctionRoomBooking.class, frbID);
        if (frb != null) {
            return frb;
        } else {
            throw new NoResultException("Function Room Booking not found.");
        }
    }
    

    @Override
    public void updateFunctionRoomBooking(FunctionRoomBooking frb) throws NoResultException {
        FunctionRoomBooking oldFRB = em.find(FunctionRoomBooking.class, frb.getFunctionRoomBookingID());
        if (oldFRB != null) {
            oldFRB.setBookedFunctionRoom(frb.getBookedFunctionRoom());
            oldFRB.setBookedTo(frb.getBookedTo());
            oldFRB.setBookedFrom(frb.getBookedFrom());
        } else {
            throw new NoResultException("Function Room Booking Not found");
        }
    }

    @Override
    public void deleteFoodOrder(Long foodOrderID) throws NoResultException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
