/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import entity.HouseKeepingOrder;
import error.NoResultException;
import javax.inject.Named;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessionBeans.HouseKeepingOrderSessionLocal;
import sessionBeans.MaintainenceOrderSessionLocal;

/**
 *
 * @author Congx2
 */
@Named(value = "requestServicesManagedBean")
@ManagedBean
@SessionScoped
public class requestServicesManagedBean implements Serializable {

    @EJB
    private MaintainenceOrderSessionLocal maintainenceOrdersessionlocal;
    @EJB
    private HouseKeepingOrderSessionLocal housekeepingsessionlocal;
    /**
     * Creates a new instance of requestServicesManagedBean
     */
    public List<HouseKeepingOrder> housekeepingOrder;

    public requestServicesManagedBean() {

    }

    @PostConstruct
    public void init() {
        try {
            setHousekeepingOrder(housekeepingsessionlocal.getAllHouseKeepingOrder());
        }catch(NoResultException e){
            System.out.println("No Result found");
        }
    }

    public MaintainenceOrderSessionLocal getMaintainenceOrdersessionlocal() {
        return maintainenceOrdersessionlocal;
    }

    public void setMaintainenceOrdersessionlocal(MaintainenceOrderSessionLocal maintainenceOrdersessionlocal) {
        this.maintainenceOrdersessionlocal = maintainenceOrdersessionlocal;
    }

    public HouseKeepingOrderSessionLocal getHousekeepingsessionlocal() {
        return housekeepingsessionlocal;
    }

    public void setHousekeepingsessionlocal(HouseKeepingOrderSessionLocal housekeepingsessionlocal) {
        this.housekeepingsessionlocal = housekeepingsessionlocal;
    }

    public List<HouseKeepingOrder> getHousekeepingOrder() {
        return housekeepingOrder;
    }

    public void setHousekeepingOrder(List<HouseKeepingOrder> housekeepingOrder) {
        this.housekeepingOrder = housekeepingOrder;
    }

}
