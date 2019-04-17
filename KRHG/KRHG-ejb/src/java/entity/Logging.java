/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Logging implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long loggingID;
    private String loggingType;
    private String loggingName;

    @Temporal(TemporalType.DATE)
    private Date loggingDateTime;
    private String operatorName;

    public Logging() {

    }

    public Logging(String loggingType, String loggingName, String operatorName) {
        this.loggingType = loggingType;
        this.loggingName = loggingName;
        this.loggingDateTime = java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        this.operatorName = operatorName;
    }

    public Logging(Long loggingID, String loggingType, String loggingName, String operatorName) {
        this.loggingID = loggingID;
        this.loggingType = loggingType;
        this.loggingName = loggingName;
        this.loggingDateTime = java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        this.operatorName = operatorName;
    }

    public Long getLoggingID() {
        return loggingID;
    }

    public void setLoggingID(Long loggingID) {
        this.loggingID = loggingID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (loggingID != null ? loggingID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logging)) {
            return false;
        }
        Logging other = (Logging) object;
        if ((this.loggingID == null && other.loggingID != null) || (this.loggingID != null && !this.loggingID.equals(other.loggingID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Logging[ loggingID=" + loggingID + " ]";
    }

    /**
     * @return the loggingType
     */
    public String getLoggingType() {
        return loggingType;
    }

    /**
     * @param loggingType the loggingType to set
     */
    public void setLoggingType(String loggingType) {
        this.loggingType = loggingType;
    }

    /**
     * @return the loggingName
     */
    public String getLoggingName() {
        return loggingName;
    }

    /**
     * @param loggingName the loggingName to set
     */
    public void setLoggingName(String loggingName) {
        this.loggingName = loggingName;
    }

    /**
     * @return the loggingDateTime
     */
    public Date getLoggingDateTime() {
        return loggingDateTime;
    }

    /**
     * @param loggingDateTime the loggingDateTime to set
     */
    public void setLoggingDateTime(Date loggingDateTime) {
        this.loggingDateTime = loggingDateTime;
    }

    /**
     * @return the operatorName
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * @param operatorName the operatorName to set
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

}
