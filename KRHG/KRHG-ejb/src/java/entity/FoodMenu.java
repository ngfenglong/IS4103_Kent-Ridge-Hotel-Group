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

@Entity
public class FoodMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long menuID;
    private String menuName;
    private String menuDescription;
    private Boolean availability;
    private Double unitPrice;
    public Long getMenuId() {
        return menuID;
    }

    public void setMenuId(Long menuID) {
        this.menuID = menuID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (menuID != null ? menuID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FoodMenu)) {
            return false;
        }
        FoodMenu other = (FoodMenu) object;
        if ((this.menuID == null && other.menuID != null) || (this.menuID != null && !this.menuID.equals(other.menuID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FoodMenu[ id=" + menuID + " ]";
    }
    
}
