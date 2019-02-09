package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class StaffType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long staffTypeID;
    private String staffTypeName;
    
    
    public Long getStaffTypeId() {
        return staffTypeID;
    }

    public void setStaffTypeId(Long staffTypeID) {
        this.staffTypeID = staffTypeID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (staffTypeID != null ? staffTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StaffType)) {
            return false;
        }
        StaffType other = (StaffType) object;
        if ((this.staffTypeID == null && other.staffTypeID != null) || (this.staffTypeID != null && !this.staffTypeID.equals(other.staffTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.StaffType[ staffTypeID=" + staffTypeID + " ]";
    }
    
    /**
     * @return the staffTypeName
     */
    public String getStaffTypeName() {
        return staffTypeName;
    }

    /**
     * @param staffTypeName the staffTypeName to set
     */
    public void setStaffTypeName(String staffTypeName) {
        this.staffTypeName = staffTypeName;
    }
}
