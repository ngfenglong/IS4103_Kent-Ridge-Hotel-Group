/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author fengl
 */
@Entity
public class Feedback implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long feedBackID;
    private String feedBackTitle;
    private String feedBackFrom;
    private String feedBackMsg;
    private int feedbackRating;

    @Temporal(TemporalType.DATE)
    private Date feedBackDate;
    @ManyToOne (fetch = FetchType.LAZY)
    private Hotel hotel;

    public Feedback() {
    }

    public Feedback(Long feedBackID, String feedBackTitle, String feedBackFrom, String feedBackMsg) {
        this();
        this.feedBackID = feedBackID;
        this.feedBackTitle = feedBackTitle;
        this.feedBackFrom = feedBackFrom;
        this.feedBackMsg = feedBackMsg;
    }//for junit    

    public Long getFeedBackID() {
        return feedBackID;
    }

    public void setFeedBackID(Long feedBackID) {
        this.feedBackID = feedBackID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feedBackID != null ? feedBackID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Feedback)) {
            return false;
        }
        Feedback other = (Feedback) object;
        if ((this.feedBackID == null && other.feedBackID != null) || (this.feedBackID != null && !this.feedBackID.equals(other.feedBackID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Feedback[ feedBackID=" + feedBackID + " ]";
    }
    
    
    /**
     * @return the feedBackTitle
     */
    public String getFeedBackTitle() {
        return feedBackTitle;
    }

    /**
     * @param feedBackTitle the feedBackTitle to set
     */
    public void setFeedBackTitle(String feedBackTitle) {
        this.feedBackTitle = feedBackTitle;
    }

    /**
     * @return the feedBackFrom
     */
    public String getFeedBackFrom() {
        return feedBackFrom;
    }

    /**
     * @param feedBackFrom the feedBackFrom to set
     */
    public void setFeedBackFrom(String feedBackFrom) {
        this.feedBackFrom = feedBackFrom;
    }

    /**
     * @return the feedBackMsg
     */
    public String getFeedBackMsg() {
        return feedBackMsg;
    }

    /**
     * @param feedBackMsg the feedBackMsg to set
     */
    public void setFeedBackMsg(String feedBackMsg) {
        this.feedBackMsg = feedBackMsg;
    }

    /**
     * @return the feedBackDate
     */
    public Date getFeedBackDate() {
        return feedBackDate;
    }

    /**
     * @param feedBackDate the feedBackDate to set
     */
    public void setFeedBackDate(Date feedBackDate) {
        this.feedBackDate = feedBackDate;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    
    public int getFeedbackRating() {
        return feedbackRating;
    }

    public void setFeedbackRating(int feedbackRating) {
        this.feedbackRating = feedbackRating;
    }    
    
    
}
