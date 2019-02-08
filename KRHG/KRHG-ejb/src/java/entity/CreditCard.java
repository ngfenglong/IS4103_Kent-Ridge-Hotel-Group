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
public class CreditCard implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long creditCardID;
    private String cardNum;
    private String vvc;
    
    @Temporal(TemporalType.DATE)
    private Date expiryDate;

    public Long getCreditCardId() {
        return creditCardID;
    }

    public void setCreditCardId(Long creditCardID) {
        this.creditCardID = creditCardID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (creditCardID != null ? creditCardID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditCard)) {
            return false;
        }
        CreditCard other = (CreditCard) object;
        if ((this.creditCardID == null && other.creditCardID != null) || (this.creditCardID != null && !this.creditCardID.equals(other.creditCardID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CreditCard[ id=" + creditCardID + " ]";
    }
    
    
    /**
     * @return the cardNum
     */
    public String getCardNum() {
        return cardNum;
    }

    /**
     * @param cardNum the cardNum to set
     */
    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    /**
     * @return the vvc
     */
    public String getVvc() {
        return vvc;
    }

    /**
     * @param vvc the vvc to set
     */
    public void setVvc(String vvc) {
        this.vvc = vvc;
    }

    /**
     * @return the expiryDate
     */
    public Date getExpiryDate() {
        return expiryDate;
    }

    /**
     * @param expiryDate the expiryDate to set
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
