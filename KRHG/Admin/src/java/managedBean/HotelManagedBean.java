/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.Hotel;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import sessionBeans.HotelSessionLocal;

/**
 *
 * @author fengl
 */
@ManagedBean
@SessionScoped
public class HotelManagedBean implements Serializable{

    @EJB
    HotelSessionLocal hotelSessionLocal;
    
    private String testing;
    
    public HotelManagedBean() {
        
    }
    
    public List<Hotel> getAllHotels(){
        return hotelSessionLocal.getAllHotels();
    }



    public HotelSessionLocal getHotelSessionLocal() {
        return hotelSessionLocal;
    }

    public void setHotelSessionLocal(HotelSessionLocal hotelSessionLocal) {
        this.hotelSessionLocal = hotelSessionLocal;
    }

    public String getTesting() {
        return testing;
    }

    public void setTesting(String testing) {
        this.testing = "Test Massage";
    }
    
    
}
