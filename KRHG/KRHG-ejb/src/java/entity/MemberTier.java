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

/**
 *
 * @author fengl
 */
@Entity
public class MemberTier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tierID;
    private String tierName;
    private int tierPoints;

    public MemberTier() {
    }

    public MemberTier(String tierName, int tierPoints) {
        this();
        this.tierName = tierName;
        this.tierPoints = tierPoints;
    }

    public Long getTierID() {
        return tierID;
    }

    public void setTierID(Long tierID) {
        this.tierID = tierID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tierID != null ? tierID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MemberTier)) {
            return false;
        }
        MemberTier other = (MemberTier) object;
        if ((this.tierID == null && other.tierID != null) || (this.tierID != null && !this.tierID.equals(other.tierID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MemberTier[ tierID=" + tierID + " ]";
    }

    public String getTierName() {
        return tierName;
    }

    public void setTierName(String tierName) {
        this.tierName = tierName;
    }

    public int getTierPoints() {
        return tierPoints;
    }

    public void setTierPoints(int tierPoints) {
        this.tierPoints = tierPoints;
    }
    
}
