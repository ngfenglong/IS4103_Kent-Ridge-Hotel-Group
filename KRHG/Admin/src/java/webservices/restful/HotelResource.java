/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices.restful;

import entity.Hotel;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import sessionBeans.HotelSessionLocal;

/**
 * REST Web Service
 *
 * @author fengl
 */
@Path("hotel")
public class HotelResource {

    @EJB
    private HotelSessionLocal hotelSessionLocal;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Hotel> getAllHotes() {
        
        List<Hotel> getList = hotelSessionLocal.getAllHotels();
        List<Hotel> returnList = new ArrayList<>();
        for (Hotel h: getList){
            Hotel tempHotel = new Hotel();
            tempHotel.setHotelName(h.getHotelName());
            tempHotel.setHotelAddress(h.getHotelAddress());
            tempHotel.setHotelCodeName(h.getHotelCodeName());
            tempHotel.setHotelContact(h.getHotelContact());
            tempHotel.setHotelStar(h.getHotelStar());
            tempHotel.setHotelImage(h.getHotelImage());
            returnList.add(tempHotel);
        }
        
        
        return returnList;
    }

}
