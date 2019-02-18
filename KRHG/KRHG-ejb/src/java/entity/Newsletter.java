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
public class Newsletter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long newsletterID;
    @Temporal(TemporalType.DATE)
    private Date newsletterDate;
    private Staff sentBy;
    private String newsletterFile;

    public Long getNewsLetterID() {
        return newsletterID;
    }

    public void setNewsLetterID(Long newsletterID) {
        this.newsletterID = newsletterID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (newsletterID != null ? newsletterID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Newsletter)) {
            return false;
        }
        Newsletter other = (Newsletter) object;
        if ((this.newsletterID == null && other.newsletterID != null) || (this.newsletterID != null && !this.newsletterID.equals(other.newsletterID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Newsletter[ newsletterID=" + newsletterID + " ]";
    }

    public Date getNewsletterDate() {
        return newsletterDate;
    }

    public void setNewsletterDate(Date newsletterDate) {
        this.newsletterDate = newsletterDate;
    }

    public Staff getSentBy() {
        return sentBy;
    }

    public void setSentBy(Staff sentBy) {
        this.sentBy = sentBy;
    }

    public String getNewsletterFile() {
        return newsletterFile;
    }

    public void setNewsletterFile(String newsletterFile) {
        this.newsletterFile = newsletterFile;
    }
    
}
