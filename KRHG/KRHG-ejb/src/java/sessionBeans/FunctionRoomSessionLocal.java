/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.FunctionRoom;
import error.NoResultException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author MC
 */
@Local
public interface FunctionRoomSessionLocal {
    public void createFunctionRoom(FunctionRoom fr);
    public List<FunctionRoom> getAllFunctionRooms();
    public FunctionRoom getFunctionRoomByID(Long frID) throws NoResultException;
    public FunctionRoom getFunctionRoomByName(String functionRoomName) throws NoResultException;
    public void updateRoom(FunctionRoom fr) throws NoResultException;
}
