/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.HouseKeepingOrder;
import entity.LaundryOrder;
import entity.LostAndFoundReport;
import entity.MaintainenceOrder;
import entity.Room;
import error.NoResultException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import sessionBeans.HouseKeepingOrderSessionLocal;
import sessionBeans.LaundrySessionLocal;
import sessionBeans.LostAndFoundSessionLocal;
import sessionBeans.MaintainenceOrderSessionLocal;
import sessionBeans.RoomSessionLocal;

/**
 *
 * @author Congx2
 */
@ManagedBean
@SessionScoped
public class requestAndServiceManagedBean implements Serializable {

    @EJB
    private LaundrySessionLocal laundrySessionLocal;
    @EJB
    private LostAndFoundSessionLocal lostAndFoundSessionLocal;
    @EJB
    private MaintainenceOrderSessionLocal maintainenceOrderSessionLocal;
    @EJB
    private HouseKeepingOrderSessionLocal houseKeepingOrderSessionLocal;
    @EJB
    private RoomSessionLocal roomSessionLocal;
    /**
     * Creates a new instance of requestAndServiceManagedBean
     */
    //laundry 
    private List<LaundryOrder> allLaundryOrders;
    private String laundryRoomNumber;
    private int laundryQuantity;
    private String laundrySpecialRequest;

    //Lost and found
    private List<LostAndFoundReport> allLostAndFounds;
    private String lfreportType;
    private String lfItemName;
    private String lfContactNumber;
    private String lfDescription;
    private LostAndFoundReport editLF;
    private Boolean lfStatus;

    //Maintenance
    private List<MaintainenceOrder> allMaintainenceOrders;
    private String mlocation;
    private String maintainDescription;

    //housekeeping
    private List<HouseKeepingOrder> allHousekeepingOrder;
    private List<Room> allOccupiedRooms;
    private String hkroom;
    private String hkSpecialRequest;
    private HouseKeepingOrder editHK;
    private String hkRequestStatus;
    private String hkRequestType;

    public requestAndServiceManagedBean() {
    }

    public List<LaundryOrder> getAllLaundryOrders() {
        return allLaundryOrders = laundrySessionLocal.getAllLaundryOrder();
    }

    public void setAllLaundryOrders(List<LaundryOrder> allLaundryOrders) {
        this.allLaundryOrders = allLaundryOrders;
    }

    public List<LostAndFoundReport> getAllLostAndFounds() {
        lfContactNumber = null;
        lfDescription = null;
        lfItemName = null;
        lfStatus = null;
        lfreportType = null;
        editLF = null;
        try {
            return allLostAndFounds = lostAndFoundSessionLocal.getAllLostAndFoundReport();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void setAllLostAndFounds(List<LostAndFoundReport> allLostAndFounds) {
        this.allLostAndFounds = allLostAndFounds;
    }

    public Boolean getLfStatus() {
        return lfStatus;
    }

    public void setLfStatus(Boolean lfStatus) {
        this.lfStatus = lfStatus;
    }

    public String getLfreportType() {
        return lfreportType;
    }

    public void setLfreportType(String lfreportType) {
        this.lfreportType = lfreportType;
    }

    public String getLfItemName() {
        return lfItemName;
    }

    public void setLfItemName(String lfItemName) {
        this.lfItemName = lfItemName;
    }

    public String getLfContactNumber() {
        return lfContactNumber;
    }

    public void setLfContactNumber(String lfContactNumber) {
        this.lfContactNumber = lfContactNumber;
    }

    public String getLfDescription() {
        return lfDescription;
    }

    public void setLfDescription(String lfDescription) {
        this.lfDescription = lfDescription;
    }

    public String makeLFRequest() {
        LostAndFoundReport lf = new LostAndFoundReport();
        lf.setItemName(lfItemName);
        lf.setReportType(lfreportType);
        lf.setContactNum(lfContactNumber);
        lf.setItemDescription(lfDescription);
        lf.setIsResolved(false);
        lf.setReportedDate(new Date());

        lostAndFoundSessionLocal.createLostAndFoundReport(lf);
        return "lostAndFoundDetails.xhtml?faces-redirect=true";
    }

    public List<MaintainenceOrder> getAllMaintainenceOrders() {
        try {

            return allMaintainenceOrders = maintainenceOrderSessionLocal.getAllMaintainenceOrder();

        } catch (NoResultException e) {
            return null;
        }
    }

    public void setAllMaintainenceOrders(List<MaintainenceOrder> allMaintainenceOrders) {
        this.allMaintainenceOrders = allMaintainenceOrders;
    }

    public String getMlocation() {
        return mlocation;
    }

    public void setMlocation(String mlocation) {
        this.mlocation = mlocation;
    }

    public String getMaintainDescription() {
        return maintainDescription;
    }

    public void setMaintainDescription(String maintainDescription) {
        this.maintainDescription = maintainDescription;
    }

    public String createMaintainence() throws IOException {

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        PrintWriter out = response.getWriter();
        MaintainenceOrder maintainence = new MaintainenceOrder();
        maintainence.setDateReported(new Date());
        maintainence.setDescription(maintainDescription);
        maintainence.setLocation(mlocation);
        maintainence.setStatus("Unresolve");

        maintainDescription = null;
        mlocation = null;
        maintainenceOrderSessionLocal.createMaintainenceOrder(maintainence);

        out.println("<script type=\"text/javascript\">");
        out.println("alert('Register Succesful!');");
        out.println("</script>");

        return "maintenance.xhtml?faces-redirect=true";
    }

    public List<HouseKeepingOrder> getAllHousekeepingOrder() {
        hkSpecialRequest = null;
        hkroom = null;

        //split by hotel
        try {
            return allHousekeepingOrder = houseKeepingOrderSessionLocal.getAllHouseKeepingOrder();
        } catch (NoResultException e) {
            return allHousekeepingOrder = null;
        }
    }

    public void setAllHousekeepingOrder(List<HouseKeepingOrder> allHousekeepingOrder) {
        this.allHousekeepingOrder = allHousekeepingOrder;
    }

    public List<Room> getAllOccupiedRooms() {
        // get only those occupiedroom
        // also by hotel
        return allOccupiedRooms = roomSessionLocal.getAllRooms();
    }

    public void setAllOccupiedRooms(List<Room> allOccupiedRooms) {
        this.allOccupiedRooms = allOccupiedRooms;
    }

    public String getHkroom() {
        return hkroom;
    }

    public void setHkroom(String hkroom) {
        this.hkroom = hkroom;
    }

    public String getHkSpecialRequest() {
        return hkSpecialRequest;
    }

    public void setHkSpecialRequest(String hkSpecialRequest) {
        this.hkSpecialRequest = hkSpecialRequest;
    }

    public String createHouseKeeping() throws NoResultException, IOException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        PrintWriter out = response.getWriter();

        HouseKeepingOrder hkOrder = new HouseKeepingOrder();

        hkOrder.setRoom(roomSessionLocal.getRoomByName(hkroom));
        hkOrder.setSpecialRequest(hkSpecialRequest);
        hkOrder.setSpecialRequest(hkRequestType);
        hkOrder.setOrderDateTime(new Date());
        hkOrder.setStatus("incomplete");

        houseKeepingOrderSessionLocal.createHouseKeepingOrder(hkOrder);

        out.println("<script type=\"text/javascript\">");
        out.println("alert('Housekeeping request created Succesful!');");
        out.println("</script>");

        return "housekeeping.xhtmlfaces-redirect=true";
    }

    public String getLaundryRoomNumber() {
        return laundryRoomNumber;
    }

    public void setLaundryRoomNumber(String laundryRoomNumber) {
        this.laundryRoomNumber = laundryRoomNumber;
    }

    public int getLaundryQuantity() {
        return laundryQuantity;
    }

    public void setLaundryQuantity(int laundryQuantity) {
        this.laundryQuantity = laundryQuantity;
    }

    public String getLaundrySpecialRequest() {
        return laundrySpecialRequest;
    }

    public void setLaundrySpecialRequest(String laundrySpecialRequest) {
        this.laundrySpecialRequest = laundrySpecialRequest;
    }

    public String createLaundry() throws NoResultException, IOException {

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        PrintWriter out = response.getWriter();

        LaundryOrder LO = new LaundryOrder();
        LO.setRoom(roomSessionLocal.getRoomByName(laundryRoomNumber));
        LO.setOrderDateTime(new Date());
        LO.setSpecialRequest(laundrySpecialRequest);
        LO.setStatus("Pending");
        
        laundrySessionLocal.createLaundryOrder(LO);

        HouseKeepingOrder HKo = new HouseKeepingOrder();
        HKo.setLevel(getLevel(roomSessionLocal.getRoomByName(laundryRoomNumber).getRoomNumber()));
        HKo.setSpecialRequest(laundrySpecialRequest);
        HKo.setStatus("incomplete");
        HKo.setRequestType("laundry");
        HKo.setRoom(roomSessionLocal.getRoomByName(laundryRoomNumber));
        HKo.setOrderDateTime(new Date());
        
        houseKeepingOrderSessionLocal.createHouseKeepingOrder(HKo);

        out.println("<script type=\"text/javascript\">");
        out.println("alert('Laundry created Succesful!');");
        out.println("</script>");

        return "laundry.xhtml?faces-redirect=true";
    }

    public int getLevel(String roomnumber) {
            if(roomnumber.length()==3){
              return Integer.parseInt(roomnumber.substring(0,1));
            }else{
                return Integer.parseInt(roomnumber.substring(0,2));
            }
    }

    public String editLostAndFound(LostAndFoundReport LF) {
        editLF = LF;
        setLfContactNumber(LF.getContactNum());
        setLfDescription(LF.getItemDescription());
        setLfreportType(LF.getReportType());
        setLfItemName(LF.getItemName());
        setLfStatus(editLF.getIsResolved());

        return "editLostAndFound.xhtml?faces-redirect=true";
    }

    public String updateLFRequest() throws IOException, NoResultException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        PrintWriter out = response.getWriter();
        editLF.setContactNum(lfContactNumber);
        editLF.setItemName(lfItemName);
        editLF.setReportType(lfreportType);
        editLF.setContactNum(lfContactNumber);
        editLF.setItemDescription(lfDescription);
        editLF.setIsResolved(lfStatus);

        lostAndFoundSessionLocal.updateLostAndFoundReport(editLF);

        out.println("<script type=\"text/javascript\">");
        out.println("alert('Lost And Found report updated Succesfully!');");
        out.println("</script>");

        return "lostAndFound.xhtml?faces-redirect=true";
    }

    public HouseKeepingOrder getEditHK() {
        return editHK;
    }

    public void setEditHK(HouseKeepingOrder editHK) {
        this.editHK = editHK;
    }

    public String getHkRequestStatus() {
        return hkRequestStatus;
    }

    public void setHkRequestStatus(String hkRequestStatus) {
        this.hkRequestStatus = hkRequestStatus;
    }

    public String editHousekeeping(HouseKeepingOrder hk) {
        hkSpecialRequest = hk.getSpecialRequest();
        hkRequestStatus = hk.getStatus();
        hkRequestType = hk.getRequestType();
        editHK = hk;

        return "housekeepingEdit.xhtml?faces-redirect=true";
    }

    public String updateEditHKRequest() throws NoResultException, IOException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        PrintWriter out = response.getWriter();

        editHK.setSpecialRequest(hkSpecialRequest);
        editHK.setRequestType(hkRequestType);
        if (hkRequestStatus.equalsIgnoreCase("complete")) {
            editHK.setCompleteDateTime(new Date());
        }
        editHK.setStatus(hkRequestStatus);

        houseKeepingOrderSessionLocal.updateHouseKeepingOrder(editHK);

        out.println("<script type=\"text/javascript\">");
        out.println("alert('Lost And Found report updated Succesfully!');");
        out.println("</script>");

        return "housekeeping.xhtml?faces-redirect=true";
    }

    public String getHkRequestType() {
        return hkRequestType;
    }

    public void setHkRequestType(String hkRequestType) {
        this.hkRequestType = hkRequestType;
    }

}
