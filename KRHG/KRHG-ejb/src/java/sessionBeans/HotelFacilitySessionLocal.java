/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.HotelFacility;
import error.NoResultException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author MC
 */
@Local
public interface HotelFacilitySessionLocal {
    public List<HotelFacility> getAllHotelFacilities();
    public HotelFacility getHotelFacilityByID(Long hfID) throws NoResultException;
    public HotelFacility getHotelFacilityByName(String hfName) throws NoResultException;
    public void createHotelFacility(HotelFacility hf);
    public void deleteHotelFacility(Long hfID) throws NoResultException;
    public void updateHotelFacility(HotelFacility hf) throws NoResultException;
    
    
    
}
