/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.FunctionRoom;
import error.NoResultException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author MC
 */
@Stateless
public class FunctionRoomSession implements FunctionRoomSessionLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createFunctionRoom(FunctionRoom fr) {
        em.persist(fr);
    }

    @Override
    public List<FunctionRoom> getAllFunctionRooms() {
        Query q;
        q = em.createQuery("SELECT fr FROM FunctionRoom fr");
        return q.getResultList();
    }

    @Override
    public FunctionRoom getFunctionRoomByID(Long frID) throws NoResultException {
        FunctionRoom fr = em.find(FunctionRoom.class, frID);
        if (fr != null) {
            return fr;
        } else {
            throw new NoResultException("Function Room not found.");
        }
    }

    @Override
    public FunctionRoom getFunctionRoomByName(String functionRoomName) throws NoResultException {
        Query q;
        q = em.createQuery("SELECT fr FROM FunctionRoom fr WHERE "
                + "LOWER(fr.functionRoomName) = :functionRoomName");
        q.setParameter("functionRoomName", functionRoomName.toLowerCase());

        if (!q.getResultList().isEmpty()) {
            return (FunctionRoom) q.getResultList().get(0);
        } else {
            throw new NoResultException("Function Room not found.");
        }
    }

    @Override
    public void updateRoom(FunctionRoom fr) throws NoResultException {
        FunctionRoom oldFR = em.find(FunctionRoom.class, fr.getFunctionRoomID());
        if (oldFR != null) {
            oldFR.setFunctionRoomName(fr.getFunctionRoomName());
            oldFR.setFunctionRoomPax(fr.getFunctionRoomPax());
            oldFR.setStatus(fr.getStatus());
            oldFR.setPrice(fr.getPrice());
            oldFR.setHotel(fr.getHotel());
        } else {
            throw new NoResultException("Function Room Not found");
        }
    }
}
