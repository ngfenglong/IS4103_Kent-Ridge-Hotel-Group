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

@Entity
public class CreditCard implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long creditCardID;
    private String cardNum;
    private String cvv;
    private String name;
    private String expiryDate;

    public CreditCard(){
        
    }

    public CreditCard(String cardNum, String cvv, String name, String expiryDate) {
        this.cardNum = cardNum;
        this.cvv = cvv;
        this.name = name;
        this.expiryDate = expiryDate;
    }
    
    public Long getCreditCardID() {
        return creditCardID;
    }

    public void setCreditCardID(Long creditCardID) {
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
        return "entity.CreditCard[ creditCardID=" + creditCardID + " ]";
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
     * @return the cvv
     */
    public String getCvv() {
        return cvv;
    }

    /**
     * @param cvv the cvv to set
     */
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

}
