        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author MC
 */
@Entity
public class HotelFacility implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long hotelFacilityID;
    private String hotelFacilityName;
    private String hotelFacilityDescription;
    private String hotelFacilityImage;


    public HotelFacility() {
    }

    public HotelFacility(String hotelFacilityName, String hotelFacilityDescription, String hotelFacilityImage) {
        this();
        this.hotelFacilityName = hotelFacilityName;
        this.hotelFacilityDescription = hotelFacilityDescription;
        this.hotelFacilityImage = hotelFacilityImage;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getHotelFacilityID() != null ? getHotelFacilityID().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HotelFacility)) {
            return false;
        }
        HotelFacility other = (HotelFacility) object;
        if ((this.getHotelFacilityID() == null && other.getHotelFacilityID() != null) || (this.getHotelFacilityID() != null && !this.hotelFacilityID.equals(other.hotelFacilityID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.HotelFacility[ hotelFacilityID=" + getHotelFacilityID() + " ]";
    }

    /**
     * @return the hotelFacilityID
     */
    public Long getHotelFacilityID() {
        return hotelFacilityID;
    }

    /**
     * @param hotelFacilityID the hotelFacilityID to set
     */
    public void setHotelFacilityID(Long hotelFacilityID) {
        this.hotelFacilityID = hotelFacilityID;
    }

    /**
     * @return the hotelFacilityName
     */
    public String getHotelFacilityName() {
        return hotelFacilityName;
    }

    /**
     * @param hotelFacilityName the hotelFacilityName to set
     */
    public void setHotelFacilityName(String hotelFacilityName) {
        this.hotelFacilityName = hotelFacilityName;
    }

    /**
     * @return the hotelFacilityDescription
     */
    public String getHotelFacilityDescription() {
        return hotelFacilityDescription;
    }

    /**
     * @param hotelFacilityDescription the hotelFacilityDescription to set
     */
    public void setHotelFacilityDescription(String hotelFacilityDescription) {
        this.hotelFacilityDescription = hotelFacilityDescription;
    }

    /**
     * @return the hotelFacilityImage
     */
    public String getHotelFacilityImage() {
        return hotelFacilityImage;
    }

    /**
     * @param hotelFacilityImage the hotelFacilityImage to set
     */
    public void setHotelFacilityImage(String hotelFacilityImage) {
        this.hotelFacilityImage = hotelFacilityImage;
    }


}
