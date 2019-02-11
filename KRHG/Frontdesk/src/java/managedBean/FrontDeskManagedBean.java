/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Congx2
 */
@Named(value = "frontDeskManagedBean")
@RequestScoped
public class FrontDeskManagedBean {

    /**
     * Creates a new instance of FrontDeskManagedBean
     */
    private String customerName;

    public FrontDeskManagedBean() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String CustomerName) {
        this.customerName = CustomerName;
    }

    public void search() {

    }

}
