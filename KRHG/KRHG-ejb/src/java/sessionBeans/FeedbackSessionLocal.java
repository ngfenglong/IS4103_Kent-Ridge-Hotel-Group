/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Feedback;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Anselm
 */
@Local
public interface FeedbackSessionLocal {
    public List<Feedback> getAllFeedbacks();
    public Feedback getFeedbackByID(Long fID);
    public void deleteFeedback(Long fID);
    public void createFeedback(Feedback f);
}
