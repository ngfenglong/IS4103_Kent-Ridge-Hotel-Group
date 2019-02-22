/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Hotel;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author dk349
 */
@Singleton
@LocalBean
@Startup
public class DataInitializationSessionBean {

    @PersistenceContext(unitName = "KRHG-ejbPU")
    private EntityManager em;
    
    @EJB
    HotelSessionLocal hotelSessionLocal;
    
    

    public DataInitializationSessionBean() {
    }
    
    @PostConstruct
    public void postConstruct(){
           if (em.find(Hotel.class, 1l) == null) {
            initializeData();
        }
    }

    
    public void initializeData(){
        Hotel h1 = new Hotel("Kent Ridge North", "KR-N","Ang Mo Kio Ave 10 Singapore 512345", 5, "91234567");
        Hotel h2 = new Hotel("Kent Ridge North East", "KR-NE","Hougang Ave 10 Singapore 512345", 5, "91234567");
        Hotel h3 = new Hotel("Kent Ridge East", "KR-E","Tampines Ave 10 Singapore 512345", 5, "91234567");
        Hotel h4 = new Hotel("Kent Ridge South East", "KR-SE","Marina Bay Singapore 512345", 5, "91234567");
        Hotel h5 = new Hotel("Kent Ridge South", "KR-S","Orchard Singapore 512345", 5, "91234567");
        Hotel h6 = new Hotel("Kent Ridge South West", "KR-SW","Jurong Ave 10 Singapore 512345", 5, "91234567");
        
        hotelSessionLocal.createHotel(h1);
        hotelSessionLocal.createHotel(h2);
        hotelSessionLocal.createHotel(h3);
        hotelSessionLocal.createHotel(h4);
        hotelSessionLocal.createHotel(h5);
        hotelSessionLocal.createHotel(h6);
        
    }

}
