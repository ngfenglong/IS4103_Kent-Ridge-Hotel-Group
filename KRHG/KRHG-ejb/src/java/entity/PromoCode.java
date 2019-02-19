/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author fengl
 */
@Entity
public class PromoCode implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long promoCodeID;
    private String promoCode;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    private String status;
    private double discount;

    public Long getPromoCodeID() {
        return promoCodeID;
    }

    public void setPromoCodeID(Long promoCodeID) {
        this.promoCodeID = promoCodeID;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (promoCodeID != null ? promoCodeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PromoCode)) {
            return false;
        }
        PromoCode other = (PromoCode) object;
        if ((this.promoCodeID == null && other.promoCodeID != null) || (this.promoCodeID != null && !this.promoCodeID.equals(other.promoCodeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PromoCode[ promoCodeID=" + promoCodeID + " ]";
    }

}
