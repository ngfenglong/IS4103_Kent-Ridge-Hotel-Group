/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices.restful;

import entity.LostAndFoundReport;
import error.NoResultException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sessionBeans.LostAndFoundSessionLocal;

/**
 * REST Web Service
 *
 * @author dk349
 */
@Path("lostandfoundreport")
public class LostAndFoundReportResource {

    LostAndFoundSessionLocal lostAndFoundSession = lookupLostAndFoundSessionLocal();
        
    private LostAndFoundSessionLocal lookupLostAndFoundSessionLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (LostAndFoundSessionLocal) c.lookup("java:global/KRHG/KRHG-ejb/LostAndFoundSession!sessionBeans.LostAndFoundSessionLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }

    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LostAndFoundReport> getAlllostAndFoundReport() {

        System.out.println("Running 55");

        try {
            List<LostAndFoundReport> getList = lostAndFoundSession.getAllLostAndFoundReport();
            return getList;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }
    
    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createLostAndFoundReport(@QueryParam("itemName") String itemName, @QueryParam("itemDescription") String itemDescription) {

        System.out.println("Running 55");
        LostAndFoundReport newReport = new LostAndFoundReport();
        newReport.setIsResolved(false);
        newReport.setItemDescription(itemDescription);
        newReport.setItemName(itemName);
        newReport.setReportType("Found");
        newReport.setReportedDate(new Date());
       

        try {
            lostAndFoundSession.createLostAndFoundReport(newReport);
            return Response.status(204).entity(Json.createObjectBuilder().build()).build();
        } catch (Exception e) {
            JsonObject exception = Json.createObjectBuilder()
                    .add("error", "Failed to create Lost and Found Report")
                    .build();
            return Response.status(404).entity(exception)
                    .type(MediaType.APPLICATION_JSON).build();
        }
    }
    
}
