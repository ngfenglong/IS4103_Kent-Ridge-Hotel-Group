/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices.restful;

import entity.HouseKeepingOrder;
import entity.Room;
import error.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sessionBeans.HouseKeepingOrderSessionLocal;

/**
 * REST Web Service
 *
 * @author dk349
 */
@Path("housekeepingorders")
public class HousekeepingordersResource {

    HouseKeepingOrderSessionLocal houseKeepingOrderSession = lookupHouseKeepingOrderSessionLocal();
//    @EJB
//    private HouseKeepingOrderSessionLocal houseKeepingOrderSession;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<HouseKeepingOrder> getAllHouseKeepingOrders() {

//        System.out.println("Running 55");

        try {
            List<HouseKeepingOrder> getList = houseKeepingOrderSession.getAllHouseKeepingOrder();
            List<HouseKeepingOrder> returnList = new ArrayList<>();
            for (HouseKeepingOrder h : getList) {
                HouseKeepingOrder tempHouseKeepingOrder = new HouseKeepingOrder();
                tempHouseKeepingOrder.setHouseKeepingOrderID(h.getHouseKeepingOrderID());
                tempHouseKeepingOrder.setRoom(h.getRoom());
                Room tempRoom = new Room();
                tempRoom.setRoomID(h.getRoom().getRoomID());
                tempRoom.setRoomNumber(h.getRoom().getRoomNumber());
                tempRoom.setStatus(h.getRoom().getStatus());
                tempHouseKeepingOrder.setRoom(tempRoom);
                tempHouseKeepingOrder.setStatus(h.getStatus());
                tempHouseKeepingOrder.setIsSpecialRequest(h.getIsSpecialRequest());
                tempHouseKeepingOrder.setSpecialRequest(h.getSpecialRequest());
                tempHouseKeepingOrder.setLevel(h.getLevel());
                returnList.add(tempHouseKeepingOrder);
            }

            return returnList;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    private HouseKeepingOrderSessionLocal lookupHouseKeepingOrderSessionLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (HouseKeepingOrderSessionLocal) c.lookup("java:global/KRHG/KRHG-ejb/HouseKeepingOrderSession!sessionBeans.HouseKeepingOrderSessionLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }

    }

    @GET
    @Path("/query")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchHouseKeepingOrder(@QueryParam("level") String level, @QueryParam("hotelCodeName") String hotelCodeName, @QueryParam("isSpecialRequest") boolean isSpecialRequest) throws NoResultException {
//        System.out.println("444Searching based on level: " + level + " Hotel Codename: " + hotelCodeName + " Special request: " + isSpecialRequest);
        if (level != null) {
//            List<HouseKeepingOrder> getList = houseKeepingOrderSession.getHouseKeepingOrderByLevel(Integer.parseInt(level));
//              List<HouseKeepingOrder> getList = houseKeepingOrderSession.getHouseKeepingOrderByLevelAndHotelCodeName(Integer.parseInt(level), hotelCodeName);
            List<HouseKeepingOrder> getList = houseKeepingOrderSession.getHouseKeepingOrderByLevelAndHotelCodeNameAndStatus(Integer.parseInt(level), hotelCodeName, "complete", isSpecialRequest);
//            List<HouseKeepingOrder> returnList = new ArrayList<>();
            List<HouseKeepingOrder> returnList = new ArrayList<>();
            for (HouseKeepingOrder h : getList) {
                HouseKeepingOrder tempHouseKeepingOrder = new HouseKeepingOrder();
                tempHouseKeepingOrder.setHouseKeepingOrderID(h.getHouseKeepingOrderID());
                tempHouseKeepingOrder.setRoom(h.getRoom());
                Room tempRoom = new Room();
                tempRoom.setRoomID(h.getRoom().getRoomID());
                tempRoom.setRoomNumber(h.getRoom().getRoomNumber());
                tempRoom.setStatus(h.getRoom().getStatus());
                tempHouseKeepingOrder.setRoom(tempRoom);
                tempHouseKeepingOrder.setStatus(h.getStatus());
                tempHouseKeepingOrder.setSpecialRequest(h.getSpecialRequest());
                tempHouseKeepingOrder.setLevel(h.getLevel());
                tempHouseKeepingOrder.setRequestType(h.getRequestType());
                tempHouseKeepingOrder.setIsSpecialRequest(h.getIsSpecialRequest());
                returnList.add(tempHouseKeepingOrder);
            }
            GenericEntity<List<HouseKeepingOrder>> entity = new GenericEntity<List<HouseKeepingOrder>>(returnList) {
            };
            return Response.status(200).entity(entity).build();
//            return returnList;
        } else {
             return Response.status(404).entity(Json.createObjectBuilder().add("", "").build())
                    .type(MediaType.APPLICATION_JSON).build();
        }
    }

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateHouseKeepingOrder(@QueryParam("id") String hoID) {
        try {

            HouseKeepingOrder ho = houseKeepingOrderSession.getHouseKeepingOrderID(Long.parseLong(hoID));
            if(ho.getStatus().equals("Incomplete")) {
                ho.setStatus("In Progress");
            } else {
                ho.setStatus("Complete");
            }
            houseKeepingOrderSession.updateHouseKeepingOrder(ho);
            return Response.status(200).entity(Json.createObjectBuilder().add("hoid", hoID).build()).build();
        } catch (Exception e) {
            e.printStackTrace();
//            return new ArrayList<>();
//            List<HouseKeepingOrder> returnList = new ArrayList<>();
//            JsonObject exception = Json.createObjectBuilder()
//                    .add("error", "Not found")
//                    .build();
            System.out.println("Entered exception");
            return Response.status(404).entity(Json.createObjectBuilder().add("", "").build())
                    .type(MediaType.APPLICATION_JSON).build();
        }
    } 

}
