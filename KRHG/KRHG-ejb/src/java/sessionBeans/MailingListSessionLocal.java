/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.MailingList;
import error.NoResultException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fengl
 */
@Local
public interface MailingListSessionLocal {
    public List<MailingList> getAllMailingList() throws NoResultException;
    public MailingList getMailingListByID(Long mID) throws NoResultException;
    public MailingList getMailingListByName(String listName) throws NoResultException;
    public void updateMailingList (MailingList m) throws NoResultException;
    public void deleteMailingList (Long mID) throws NoResultException;
    public void createMailingList (MailingList m);
}
