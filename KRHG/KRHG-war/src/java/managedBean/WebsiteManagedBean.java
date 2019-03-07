/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import javax.inject.Named;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author fengl
 */
@ManagedBean
@SessionScoped
public class WebsiteManagedBean implements Serializable {

    /**
     * Creates a new instance of WebsiteManagedBean
     */
    
    private String forgetPassEmail = "test";
    
    
    public WebsiteManagedBean() {
    }

    public String getForgetPassEmail() {
        return forgetPassEmail;
    }

    public void setForgetPassEmail(String forgetPassEmail) {
        this.forgetPassEmail = forgetPassEmail;
    }
   
    public String testMethod(){
        return "index.xhtml?faces-redirect=true";
    }
    
}
