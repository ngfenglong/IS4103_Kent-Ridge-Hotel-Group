/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MailingList implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long listID;
    private String listName;
    private ArrayList<String> listToSend;

    public Long getListID() {
        return listID;
    }

    public void setListID(Long listID) {
        this.listID = listID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (listID != null ? listID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MailingList)) {
            return false;
        }
        MailingList other = (MailingList) object;
        if ((this.listID == null && other.listID != null) || (this.listID != null && !this.listID.equals(other.listID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MailingList[ listID=" + listID + " ]";
    }
 
    /**
     * @return the listName
     */
    public String getListName() {
        return listName;
    }

    /**
     * @param listName the listName to set
     */
    public void setListName(String listName) {
        this.listName = listName;
    }

    /**
     * @return the listToSend
     */
    public ArrayList<String> getListToSend() {
        return listToSend;
    }

    /**
     * @param listToSend the listToSend to set
     */
    public void setListToSend(ArrayList<String> listToSend) {
        this.listToSend = listToSend;
    }
    
}
