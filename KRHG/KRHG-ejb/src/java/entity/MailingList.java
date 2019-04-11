/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import error.NoResultException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    private List<String> listToSend;

    public MailingList() {
        listToSend = new ArrayList<String>();
    }

    public MailingList(String listName, ArrayList<String> listToSend) {
        this();
        this.listName = listName;
        this.listToSend = listToSend;
    }

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

    public List<String> getListToSend() {
        return listToSend;
    }

    /**
     * @return the listToSend
     */
    public void setListToSend(List<String> listToSend) {
        this.listToSend = listToSend;
    }

    /**
     * @param listToSend the listToSend to set
     */
    public void addList(String newEntry) throws NoResultException {
        if (!newEntry.equals("") && !this.getListToSend().contains(newEntry)) {
            this.getListToSend().add(newEntry);
        } else {
            throw new NoResultException("Entry already added to Hotel");
        }
    }

    public void removeList(String newEntry) throws NoResultException {
        if (!newEntry.equals("") && this.getListToSend().contains(newEntry)) {
            this.getListToSend().remove(newEntry);
        } else {
            throw new NoResultException("Entry has not been added to MailingList");
        }
    }
}
