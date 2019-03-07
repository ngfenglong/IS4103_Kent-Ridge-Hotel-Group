/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.ExtraSurcharge;
import entity.Feedback;
import entity.HolidaySurcharge;
import entity.Hotel;
import entity.HotelFacility;
import entity.Logging;
import entity.MinibarItem;
import entity.Room;
import entity.RoomFacility;
import entity.Staff;
import entity.StaffType;
import error.NoResultException;
import etc.RandomPassword;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import sessionBeans.FeedbackSessionLocal;
import sessionBeans.HotelFacilitySessionLocal;

import sessionBeans.HotelSessionLocal;
import sessionBeans.HouseKeepingOrderSessionLocal;
import sessionBeans.LogSessionLocal;
import sessionBeans.RoomFacilitySessionLocal;
import sessionBeans.RoomSessionLocal;
import sessionBeans.StaffSessionLocal;

/**
 *
 * @author fengl
 */
@ManagedBean
@SessionScoped
public class HotelManagedBean implements Serializable {

    @EJB
    HotelSessionLocal hotelSessionLocal;
    @EJB
    HotelFacilitySessionLocal hotelFacilitySessionLocal;
    @EJB
    HouseKeepingOrderSessionLocal houseKeepingOrderSessionLocal;
    @EJB
    RoomFacilitySessionLocal roomFacilitySessionLocal;
    @EJB
    RoomSessionLocal roomSessionLocal;
    @EJB
    FeedbackSessionLocal feedbackSessionLocal;
    @EJB
    LogSessionLocal logSessionLocal;
    @EJB
    StaffSessionLocal staffSessionLocal;

    private String loggedInUser;

    private String logActivityName;

    public String selectedHotel;
    public Staff selectedStaff;
    public HotelFacility selectedFacilityObj;
    public HolidaySurcharge selectedHoliday;
    public Hotel selectedHotelObj;
    public MinibarItem selectedMinibarItem;
    public Room selectedRoom;
    public RoomFacility selectedRoomFacility;
    public ExtraSurcharge selectedSurcharge;

    public String hotelName;
    public String hotelCode;
    public String address;
    public int hotelStar;
    public String contactNumber;

    //Create Room
    public String roomName;
    public String roomNumber;
    public String roomType;
    public int roomPax;
    public String[] minibarItems;
    public String[] roomFacilities;
    public String addRoomHotelName;

    public String selectedFacility;

    public String hfName;
    public String hfImage;
    public String hfDescription;

    public String holName;
    public String holDate;
    public double holPrice;

    public String miName;
    public double miPrice;

    public String rfName;
    public String rfCategory;
    public String rfIconImg;

    public String esName;
    public String esDateFrom;
    public String esDateTo;
    public String[] esDaysToCharge;
    public double esPrice;

    public String stName;
    public String stPassword;
    public String stEmail;
    public String stPhoneNumber;
    public String stGender;
    public String stNric;
    public String stAddress;
    public String stHotel;
    public String stJobTitle;
    public String stDepartment;
    public int stLeave;
    public String stNokName;
    public String stNokAddress;
    public String stNokPhoneNumber;
    public String[] stStaffType;

    @ManagedProperty(value = "#{authenticationManagedBean}")
    private AuthenticationManagedBean authBean;

    public AuthenticationManagedBean getAuthBean() {
        return authBean;
    }

    public void setAuthBean(AuthenticationManagedBean authBean) {
        this.authBean = authBean;
    }

    public HotelManagedBean() {

    }

    public List<Hotel> getAllHotels() {
        return hotelSessionLocal.getAllHotels();
    }

    public List<HotelFacility> getAllHotelFacility() {
        return hotelFacilitySessionLocal.getAllHotelFacilities();
    }

    public List<HolidaySurcharge> getAllHolidaySurcharge() {
        return roomSessionLocal.getAllHolidaySurcharge();
    }

    public List<MinibarItem> getAllMinibarItem() {
        return roomSessionLocal.getAllMinibarItem();
    }

    public List<HotelFacility> getHotelFacilities() throws NoResultException {
        Hotel hotel = hotelSessionLocal.getHotelByName(selectedHotel);
        return hotel.getHotelFacilities();
    }

    public List<Feedback> getHotelFeedbacks() throws NoResultException {
        Hotel hotel = hotelSessionLocal.getHotelByName(selectedHotel);
        return hotel.getFeedbacks();
    }

    public List<RoomFacility> getAllRoomFacility() {
        return roomFacilitySessionLocal.getAllRoomFacilities();
    }

    public List<ExtraSurcharge> getAllSurcharge() {
        return roomSessionLocal.getAllExtraSurcharge();
    }

    public List<Feedback> getAllFeedbacks() {
        return feedbackSessionLocal.getAllFeedbacks();
    }

    public List<Logging> getAllLogging() {
        return logSessionLocal.getAllLoggingActivities();
    }

    public List<StaffType> getAllStaffType() {
        return staffSessionLocal.getAllStaffTypes();
    }

    public List<Staff> getAllStaff() {
        return staffSessionLocal.getAllStaffs();
    }

    public String saveFacility() throws NoResultException {
        hotelFacilitySessionLocal.updateHotelFacility(selectedFacilityObj);
        
        return "ViewAllFacility.xhtml?faces-redirect=true";
    }
    public String saveHoliday() throws NoResultException {
        roomSessionLocal.updateHolidaySurcarhge(selectedHoliday);
        
        return "ViewHolidays.xhtml?faces-redirect=true";
    }
    public String saveHotel() throws NoResultException {
        hotelSessionLocal.updateHotel(selectedHotelObj);
        
        return "ViewAllHotels.xhtml?faces-redirect=true";
    }
    public String saveMinibarItem() throws NoResultException {
        roomSessionLocal.updateMinibarItem(selectedMinibarItem);
        
        return "ViewMinibarItems.xhtml?faces-redirect=true";
    }
    public String saveProfile() {

        return "index.xhtml?faces-redirect=true";
    }
    public String saveRoom() throws NoResultException {
        roomSessionLocal.updateRoom(selectedRoom);
        
        return "ViewRooms.xhtml?faces-redirect=true";
    }
    public String saveRoomFacility() throws NoResultException {
        roomFacilitySessionLocal.updateRoomFacility(selectedRoomFacility);
        
        return "ViewRoomFacility.xhtml?faces-redirect=true";
    }
    public String saveStaff() throws NoResultException {
        staffSessionLocal.updateStaff(selectedStaff);

        return "ViewStaff.xhtml?faces-redirect=true";
    }
    public String saveSurcharge() throws NoResultException {
        roomSessionLocal.updateExtraSurcarhge(selectedSurcharge);
        
        return "ViewSucharge.xhtml?faces-redirect=true";
    }

    public String editSurcharge(Long sID) throws NoResultException {
        selectedSurcharge = roomSessionLocal.getExtraSurchargeByID(sID);

        return "EditSurcharge.xhtml?faces-redirect=true";
    }

    public String editStaff(Long sID) throws NoResultException{
        selectedStaff = staffSessionLocal.getStaffByID(sID);

        return "EditStaff.xhtml?faces-redirect=true";
    }

    public String editRoomFacility(Long rfID) throws NoResultException {
        selectedRoomFacility = roomFacilitySessionLocal.getRoomFacilityByID(rfID);

        return "EditRoomFacility.xhtml?faces-redirect=true";
    }

    public String editRoom(Long rID) throws NoResultException {
        selectedRoom = roomSessionLocal.getRoomByID(rID);

        return "EditSurcharge.xhtml?faces-redirect=true";
    }

    public String editProfile(Long pID) {
        return "EditProfile.xhtml?faces-redirect=true";
    }

    public String editMinibarItem(Long miID) throws NoResultException {
        selectedMinibarItem = roomSessionLocal.getMinibarItemByID(miID);

        return "EditMinibarItems.xhtml?faces-redirect=true";
    }

    public String editHotel(Long hID) throws NoResultException {
        selectedHotelObj = hotelSessionLocal.getHotelByID(hID);

        return "EditHotel.xhtml?faces-redirect=true";
    }

    public String editHolidaySurcharge(Long hID) throws NoResultException {
        selectedHoliday = roomSessionLocal.getHolidaySurchargeByID(hID);

        return "EditHolidays.xhtml?faces-redirect=true";
    }

    public String editHotelFacility(Long fID) throws NoResultException {
        selectedFacilityObj = hotelFacilitySessionLocal.getHotelFacilityByID(fID);

        return "EditFacility.xhtml?faces-redirect=true";
    }

    public String saveHotelFacility() {

        return "ViewAllFacility.xhtml?faces-redirect=true";
    }

    public String generateNewPassword() {
        stPassword = new RandomPassword().generateRandomPassword();

        return "AddStaff.xhtml?faces-redirect=true";
    }

    public String deleteHotelFacility(Long hfID) throws NoResultException {
        List<Hotel> hotels = hotelSessionLocal.getAllHotels();
        HotelFacility hf = hotelFacilitySessionLocal.getHotelFacilityByID(hfID);
        logActivityName = hf.getHotelFacilityName();
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();

        for (Hotel h : hotels) {
            if (h.getHotelFacilities().contains(hf)) {
                hotelSessionLocal.removeHotelFacility(h.getHotelID(), hf);
            }
        }
        hotelFacilitySessionLocal.deleteHotelFacility(hfID);
        Logging l = new Logging("Hotel Facility", "Delete " + logActivityName + " from Hotel Facility", loggedInName);
        logSessionLocal.createLogging(l);

        return "ViewAllFacility.xhtml?faces-redirect=true";
    }

    public String deleteLog(Long lID) throws NoResultException {
        logSessionLocal.deleteLogging(lID);

        return "ViewLog.xhtml?faces-redirect=true";
    }

    public String removeFacilityFromHotel(Long hfID) throws NoResultException {
        Hotel h = hotelSessionLocal.getHotelByName(selectedHotel);
        HotelFacility hf = hotelFacilitySessionLocal.getHotelFacilityByID(hfID);
        logActivityName = hf.getHotelFacilityName();
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        String tempHotelName = h.getHotelName();

        hotelSessionLocal.removeHotelFacility(h.getHotelID(), hf);
        Logging l = new Logging("Hotel Facility", "Remove " + logActivityName + " from " + tempHotelName, loggedInName);
        logSessionLocal.createLogging(l);

        return "ViewFacility.xhtml?faces-redirect=true";
    }

    public String deleteHotel(Long hID) throws NoResultException {
        logActivityName = hotelSessionLocal.getHotelByID(hID).getHotelName();
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        hotelSessionLocal.deleteHotel(hID);

        Logging l = new Logging("Hotel", "Delete " + logActivityName + " from System", loggedInName);
        logSessionLocal.createLogging(l);

        return "ViewAllHotels.xhtml?faces-redirect=true";
    }

    public String deleteRoomFacility(Long rfID) throws NoResultException {
        List<Room> rooms = roomSessionLocal.getAllRooms();
        RoomFacility rf = roomFacilitySessionLocal.getRoomFacilityByID(rfID);

        for (Room r : rooms) {
            if (r.getRoomFacilities().contains(rf)) {
                roomSessionLocal.removeRoomFacility(r.getRoomID(), rf);
            }
        }
        logActivityName = rf.getRoomFacilityName();
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        roomFacilitySessionLocal.deleteRoomFacility(rfID);
        Logging l = new Logging("Room Facility", "Delete " + logActivityName + " from Room Facility", loggedInName);
        logSessionLocal.createLogging(l);

        return "ViewRoomFacility.xhtml?faces-redirect=true";
    }

    public String deleteFeedback(Long fID) throws NoResultException {
        logActivityName = feedbackSessionLocal.getFeedbackByID(fID).getFeedBackTitle();
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        feedbackSessionLocal.deleteFeedback(fID);
        Logging l = new Logging("Feedback", "Delete " + logActivityName + " from System", loggedInName);
        logSessionLocal.createLogging(l);

        return "ViewFeedback.xhtml?faces-redirect=true";
    }

    public String deleteHolidaySurcharge(Long hsID) throws NoResultException {
        logActivityName = roomSessionLocal.getHolidaySurchargeByID(hsID).getHolidayName();
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        roomSessionLocal.deleteHolidaySurcharge(hsID);
        Logging l = new Logging("Holiday Surcharge", "Delete " + logActivityName + " from System", loggedInName);
        logSessionLocal.createLogging(l);

        return "ViewHolidays.xhtml?faces-redirect=true";
    }

    public String deleteMinibarItem(Long miID) throws NoResultException {
        List<Room> rooms = roomSessionLocal.getAllRooms();
        MinibarItem mi = roomSessionLocal.getMinibarItemByID(miID);
        for (Room r : rooms) {
            if (r.getMiniBarItems().contains(mi)) {
                roomSessionLocal.removeMinibarItem(r.getRoomID(), mi);
            }
        }
        logActivityName = mi.getItemName();
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        roomSessionLocal.deleteMinibarItem(miID);
        Logging l = new Logging("Minibar", "Delete " + logActivityName + " from System", loggedInName);
        logSessionLocal.createLogging(l);

        return "ViewMinibarItems.xhtml?faces-redirect=true";
    }

    public String deleteRoom(Long rID) throws NoResultException {
        List<Hotel> hotels = hotelSessionLocal.getAllHotels();
        Room r = roomSessionLocal.getRoomByID(rID);
        String tempHotelName = "";
        for (Hotel h : hotels) {
            if (h.getRooms().contains(r)) {
                hotelSessionLocal.removeRoom(h.getHotelID(), r);
                tempHotelName = h.getHotelName();
            }
        }
        logActivityName = r.getRoomName();
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        roomSessionLocal.deleteRoom(rID);
        Logging l = new Logging("Room", "Delete " + logActivityName + " from " + tempHotelName, loggedInName);
        logSessionLocal.createLogging(l);

        return "ViewRooms.xhtml?faces-redirect=true";
    }

    public String deleteSurcharge(Long sID) throws NoResultException {
        logActivityName = roomSessionLocal.getExtraSurchargeByID(sID).getSurchargeName();
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        roomSessionLocal.deleteExtraSurcharge(sID);
        Logging l = new Logging("Extra Surcharge", "Delete " + logActivityName + " from System", loggedInName);
        logSessionLocal.createLogging(l);

        return "ViewSucharge.xhtml?faces-redirect=true";
    }

    public String deleteStaff(Long sID) throws NoResultException {
        logActivityName = staffSessionLocal.getStaffByID(sID).getUserName();
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        staffSessionLocal.deleteStaff(sID);
        Logging l = new Logging("Staff", "Delete " + logActivityName + " from System", loggedInName);
        logSessionLocal.createLogging(l);

        return "ViewStaff.xhtml?faces-redirect=true";
    }

    public String changeStatus(Long sID) throws NoResultException {
        logActivityName = staffSessionLocal.getStaffByID(sID).getUserName();
        Staff tempStaff = staffSessionLocal.getStaffByID(sID);

        if (tempStaff.getAccountStatus() == true) {
            staffSessionLocal.deactivateStaff(tempStaff);
        } else {
            staffSessionLocal.activateStaff(tempStaff);
        }

        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        Logging l = new Logging("Staff", "Change " + logActivityName + "'s status", loggedInName);
        logSessionLocal.createLogging(l);

        return "ViewStaff.xhtml?faces-redirect=true";
    }

    public List<HotelFacility> getAddableHotelFacilities() throws NoResultException {
        List<HotelFacility> allFacilities = hotelFacilitySessionLocal.getAllHotelFacilities();
        List<HotelFacility> unwantedList = getHotelFacilities();
        List<HotelFacility> returnList = new ArrayList<HotelFacility>();
        for (HotelFacility h : allFacilities) {
            boolean check = false;
            for (HotelFacility h2 : unwantedList) {
                if (h.getHotelFacilityName().equals(h2.getHotelFacilityName())) {
                    check = true;
                }
            }
            if (check == false) {
                returnList.add(h);
            }
        }

        return returnList;
    }

    public String addRoom() throws NoResultException {
        Room r = new Room();
        r.setRoomName(roomName);
        r.setRoomNumber(roomNumber);
        r.setRoomType(roomType);
        r.setRoomPax(roomPax);
        r.setStatus("Available");
        ArrayList<MinibarItem> mbList = new ArrayList<MinibarItem>();
        ArrayList<RoomFacility> rfList = new ArrayList<RoomFacility>();

        if (minibarItems != null) {
            for (int i = 0; i < minibarItems.length; i++) {
                mbList.add(houseKeepingOrderSessionLocal.getMinibarItemByItemName(minibarItems[i]));
            }
        }

        if (roomFacilities != null) {
            for (int i = 0; i < roomFacilities.length; i++) {
                rfList.add(roomFacilitySessionLocal.getRoomFacilityByName(roomFacilities[i]));
            }
        }

        roomSessionLocal.createRoom(r);
        r = roomSessionLocal.getRoomByName(roomName);
        Hotel h = hotelSessionLocal.getHotelByName(addRoomHotelName);

        logActivityName = roomName;
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        hotelSessionLocal.addRoom(h.getHotelID(), r);
        Logging l = new Logging("Room", "Add " + logActivityName + " to " + addRoomHotelName, loggedInName);
        logSessionLocal.createLogging(l);

        roomName = null;
        roomNumber = null;
        roomType = null;
        roomPax = 1;
        minibarItems = null;
        roomFacilities = null;
        selectedHotel = addRoomHotelName;

        return "ViewRooms.xhtml?faces-redirect=true";
    }

    public String addFacilityToHotel() throws NoResultException {
        Hotel hotel = hotelSessionLocal.getHotelByName(selectedHotel);
        hotelSessionLocal.addHotelFacility(hotel.getHotelID(), hotelFacilitySessionLocal.getHotelFacilityByName(selectedFacility));

        logActivityName = hotelFacilitySessionLocal.getHotelFacilityByName(selectedFacility).getHotelFacilityName();
        FacesContext context = FacesContext.getCurrentInstance();

        Staff loggedInStaff = (Staff) context.getApplication().createValueBinding("#{authenticationManagedBean.loggedInStaff}").getValue(context);
        String loggedInName = loggedInStaff.getName();
        Logging l = new Logging("Hotel Facility", "Add " + logActivityName + " to " + selectedHotel, loggedInName);
        logSessionLocal.createLogging(l);

        return "ViewFacility.xhtml?faces-redirect=true";
    }

    public String createNewHotel() {
        Hotel hotel = new Hotel();
        hotel.setHotelName(hotelName);
        hotel.setHotelCodeName(hotelCode);
        hotel.setHotelContact(contactNumber);
        hotel.setHotelStar(hotelStar);
        hotel.setHotelAddress(address);
        hotelSessionLocal.createHotel(hotel);

        logActivityName = hotelName;
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        Logging l = new Logging("Hotel", "Add " + logActivityName + " to System", loggedInName);
        logSessionLocal.createLogging(l);

        hotelName = null;
        hotelCode = null;
        contactNumber = null;
        hotelStar = 1;
        address = null;

        return "index.xhtml?faces-redirect=true";
    }

    public String createHotelFacility() {
        HotelFacility hf = new HotelFacility();
        hf.setHotelFacilityImage(hfImage);
        hf.setHotelFacilityName(hfName);
        hf.setHotelFacilityDescription(hfDescription);
        hotelFacilitySessionLocal.createHotelFacility(hf);

        logActivityName = hfName;
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        Logging l = new Logging("Hotel Facility", "Add " + logActivityName + " to System", loggedInName);
        logSessionLocal.createLogging(l);

        hfImage = null;
        hfName = null;
        hfDescription = null;

        return "ViewAllFacility.xhtml?faces-redirect=true";
    }

    public String createHolidaySurcharge() throws ParseException {
        HolidaySurcharge hs = new HolidaySurcharge();
        hs.setHolidayName(holName);
        hs.setHolidaySurchargePrice(holPrice);
        hs.setHolidayDate(new SimpleDateFormat("dd/MM/yyyy").parse(holDate));
        roomSessionLocal.createHolidaySurcharge(hs);

        logActivityName = holName;
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        Logging l = new Logging("Holiday Surhcarge", "Add " + logActivityName + " to System", loggedInName);
        logSessionLocal.createLogging(l);

        holName = null;
        holPrice = 0.0;
        holDate = null;

        return "ViewHolidays.xhtml?faces-redirect=true";
    }

    public String createMinibarItem() {
        MinibarItem mi = new MinibarItem();
        mi.setItemName(miName);
        mi.setQty(1);
        mi.setPrice(miPrice);
        roomSessionLocal.createMinibarItem(mi);

        logActivityName = miName;
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        Logging l = new Logging("Minibar", "Add " + logActivityName + " to System", loggedInName);
        logSessionLocal.createLogging(l);

        miName = null;
        miPrice = 0.0;

        return "ViewMinibarItems.xhtml?faces-redirect=true";
    }

    public String displayDays(ArrayList<String> days) {
        String returnString = "";
        for (String s : days) {
            returnString = returnString + s + ", ";
        }
        if (returnString.length() > 0) {
            returnString = returnString.substring(0, returnString.length() - 2);
        }

        return returnString;
    }

    public String displayStaffTypes() {
        String returnString = "";
        for (StaffType s : selectedStaff.getAccountRights()) {
            returnString = returnString + s.getStaffTypeName() + ", ";
        }
        if (returnString.length() > 0) {
            returnString = returnString.substring(0, returnString.length() - 2);
        }

        return returnString;
    }

    public String createRoomFacility() {
        RoomFacility rf = new RoomFacility();
        rf.setRoomFacilityName(rfName);
        rf.setRoomFacilityCategory(rfCategory);
        rf.setIconImg(rfIconImg);
//        if (file != null) {
//            imgFile = file.getSubmittedFileName();
//            try {
//
//                InputStream bytes = file.getInputStream();
//                //Files.copy(bytes, path, StandardCopyOption.REPLACE_EXISTING);
//
//                URL ftp = new URL("ftp://zetegrdb:iqDcPqo8ornE@zetegral.website/public_html/krhgImages/" + file.getSubmittedFileName());
//                URLConnection conn = ftp.openConnection();
//                conn.setDoOutput(true);
//                OutputStream out = conn.getOutputStream();
//                // Copy an InputStream to that OutputStream then
//                out.write(IOUtils.readFully(bytes, -1, false));
//                out.close();
//
//            } catch (Exception e) {
//                e.printStackTrace(System.out);
//            }
//        }
        roomFacilitySessionLocal.createRoomFacility(rf);

        logActivityName = rfName;
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        Logging l = new Logging("Room Facility", "Add " + logActivityName + " to System", loggedInName);
        logSessionLocal.createLogging(l);

        rfName = null;
        rfCategory = null;
        rfIconImg = null;

        return "ViewRoomFacility.xhtml?faces-redirect=true";
    }

    public String createSurcharge() throws ParseException {
        ExtraSurcharge es = new ExtraSurcharge();
        es.setSurchargeName(esName);
        es.setSurchargeFrom(new SimpleDateFormat("dd/MM/yyyy").parse(esDateFrom));
        es.setSurchargeTo(new SimpleDateFormat("dd/MM/yyyy").parse(esDateTo));
        es.setSurchargePrice(esPrice);
        ArrayList<String> daysList = new ArrayList<String>();
        if (esDaysToCharge != null) {
            for (int i = 0; i < esDaysToCharge.length; i++) {
                daysList.add(esDaysToCharge[i]);
            }
        }
        es.setDaysToCharge(daysList);
        roomSessionLocal.createExtraSurcharge(es);

        logActivityName = esName;
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        Logging l = new Logging("Extra Surcharge", "Add " + logActivityName + " to System", loggedInName);
        logSessionLocal.createLogging(l);

        esName = null;
        esDateFrom = null;
        esDateTo = null;
        esPrice = 0.0;
        daysList = null;

        return "ViewSucharge.xhtml?faces-redirect=true";
    }

    public String createStaff() throws NoResultException {
        Staff s = new Staff();
        String tempUsername = stName.trim().replaceAll("\\s", "");
        s.setName(stName.trim());
        s.setPassword(stPassword);
        s.setEmail(stEmail);
        s.setPhoneNumber(stPhoneNumber);
        s.setGender(stGender);
        s.setNric(stNric);
        s.setAddress(stAddress);
        s.setJoinDate(java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        s.setHotelGroup(stHotel);
        s.setJobTitle(stJobTitle);
        s.setDepartment(stDepartment);
        s.setEntitledLeaves(stLeave);
        s.setAccountStatus(true);
        s.setNokName(stNokName);
        s.setNokAddress(stNokAddress);
        s.setNokPhoneNumber(stNokPhoneNumber);

        ArrayList<StaffType> tempStaffTypes = new ArrayList<StaffType>();
        if (stStaffType != null) {
            for (int i = 0; i < stStaffType.length; i++) {
                tempStaffTypes.add(staffSessionLocal.getStaffTypeByName(stStaffType[i].toString()));
            }
        }
        s.setAccountRights(tempStaffTypes);

        staffSessionLocal.createStaff(s);
        stName = null;
        stPassword = null;
        stEmail = null;
        stPhoneNumber = null;
        stGender = null;
        stNric = null;
        stAddress = null;
        stHotel = null;
        stJobTitle = null;
        stDepartment = null;
        stLeave = 7;
        stNokName = null;
        stNokAddress = null;
        stNokPhoneNumber = null;
        stStaffType = null;

        logActivityName = tempUsername;
        FacesContext context = FacesContext.getCurrentInstance();
        String loggedInName = context.getApplication().createValueBinding("#{authenticationManagedBean.name}").getValue(context).toString();
        Logging l = new Logging("Staff", "Create new staff " + tempUsername + " to System", loggedInName);
        logSessionLocal.createLogging(l);

        return "ViewStaff.xhtml?faces-redirect=true";
    }

    public String viewStaffDetail(Long sID) throws NoResultException {
        selectedStaff = staffSessionLocal.getStaffByID(sID);

        return "ViewStaffProfile.xhtml?faces-redirect=true";
    }

    public List<MinibarItem> getMinibarItemList() {
        return houseKeepingOrderSessionLocal.getAllMinibarItem();
    }

    public List<RoomFacility> getRoomFacilityList() {
        return roomFacilitySessionLocal.getAllRoomFacilities();
    }

    public List<Room> getHotelRooms() throws NoResultException {
        Hotel hotel = hotelSessionLocal.getHotelByName(selectedHotel);
        return hotel.getRooms();
    }

    public void setHotelSessionLocal(HotelSessionLocal hotelSessionLocal) {
        this.hotelSessionLocal = hotelSessionLocal;
    }

    public String viewHotelFacilities(String hotelName) {
        selectedHotel = hotelName;

        return "ViewFacility.xhtml?faces-redirect=true";
    }

    public String viewHotelRooms(String hotelName) {
        selectedHotel = hotelName;

        return "ViewRooms.xhtml?faces-redirect=true";
    }

    public String viewHotelFeedbacks(String hotelName) {
        selectedHotel = hotelName;

        return "ViewFeedback.xhtml?faces-redirect=true";
    }

    public String getSelectedHotel() {
        return selectedHotel;
    }

    public void setSelectedHotel(String selectedHotel) {
        this.selectedHotel = selectedHotel;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getHotelStar() {
        return hotelStar;
    }

    public void setHotelStar(int hotelStar) {
        this.hotelStar = hotelStar;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public HotelFacilitySessionLocal getHotelFacilitySessionLocal() {
        return hotelFacilitySessionLocal;
    }

    public void setHotelFacilitySessionLocal(HotelFacilitySessionLocal hotelFacilitySessionLocal) {
        this.hotelFacilitySessionLocal = hotelFacilitySessionLocal;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getRoomPax() {
        return roomPax;
    }

    public void setRoomPax(int roomPax) {
        this.roomPax = roomPax;
    }

    public String getSelectedFacility() {
        return selectedFacility;
    }

    public void setSelectedFacility(String selectedFacility) {
        this.selectedFacility = selectedFacility;
    }

    public String[] getMinibarItems() {
        return minibarItems;
    }

    public void setMinibarItems(String[] minibarItems) {
        this.minibarItems = minibarItems;
    }

    public String[] getRoomFacilities() {
        return roomFacilities;
    }

    public void setRoomFacilities(String[] roomFacilities) {
        this.roomFacilities = roomFacilities;
    }

    public HouseKeepingOrderSessionLocal getHouseKeepingOrderSessionLocal() {
        return houseKeepingOrderSessionLocal;
    }

    public void setHouseKeepingOrderSessionLocal(HouseKeepingOrderSessionLocal houseKeepingOrderSessionLocal) {
        this.houseKeepingOrderSessionLocal = houseKeepingOrderSessionLocal;
    }

    public RoomFacilitySessionLocal getRoomFacilitySessionLocal() {
        return roomFacilitySessionLocal;
    }

    public void setRoomFacilitySessionLocal(RoomFacilitySessionLocal roomFacilitySessionLocal) {
        this.roomFacilitySessionLocal = roomFacilitySessionLocal;
    }

    public RoomSessionLocal getRoomSessionLocal() {
        return roomSessionLocal;
    }

    public void setRoomSessionLocal(RoomSessionLocal roomSessionLocal) {
        this.roomSessionLocal = roomSessionLocal;
    }

    public String getHfName() {
        return hfName;
    }

    public void setHfName(String hfName) {
        this.hfName = hfName;
    }

    public String getHfImage() {
        return hfImage;
    }

    public void setHfImage(String hfImage) {
        this.hfImage = hfImage;
    }

    public String getHfDescription() {
        return hfDescription;
    }

    public void setHfDescription(String hfDescription) {
        this.hfDescription = hfDescription;
    }

    public String getHolName() {
        return holName;
    }

    public void setHolName(String holName) {
        this.holName = holName;
    }

    public String getHolDate() {
        return holDate;
    }

    public void setHolDate(String holDate) {
        this.holDate = holDate;
    }

    public double getHolPrice() {
        return holPrice;
    }

    public void setHolPrice(double holPrice) {
        this.holPrice = holPrice;
    }

    public String getMiName() {
        return miName;
    }

    public void setMiName(String miName) {
        this.miName = miName;
    }

    public double getMiPrice() {
        return miPrice;
    }

    public void setMiPrice(double miPrice) {
        this.miPrice = miPrice;
    }

    public String getRfName() {
        return rfName;
    }

    public void setRfName(String rfName) {
        this.rfName = rfName;
    }

    public String getRfCategory() {
        return rfCategory;
    }

    public void setRfCategory(String rfCategory) {
        this.rfCategory = rfCategory;
    }

    public String getRfIconImg() {
        return rfIconImg;
    }

    public void setRfIconImg(String rfIconImg) {
        this.rfIconImg = rfIconImg;
    }

    public String getEsName() {
        return esName;
    }

    public void setEsName(String esName) {
        this.esName = esName;
    }

    public String getEsDateFrom() {
        return esDateFrom;
    }

    public void setEsDateFrom(String esDateFrom) {
        this.esDateFrom = esDateFrom;
    }

    public String getEsDateTo() {
        return esDateTo;
    }

    public void setEsDateTo(String esDateTo) {
        this.esDateTo = esDateTo;
    }

    public String[] getEsDaysToCharge() {
        return esDaysToCharge;
    }

    public void setEsDaysToCharge(String[] esDaysToCharge) {
        this.esDaysToCharge = esDaysToCharge;
    }

    public double getEsPrice() {
        return esPrice;
    }

    public void setEsPrice(double esPrice) {
        this.esPrice = esPrice;
    }

    public HotelSessionLocal getHotelSessionLocal() {
        return hotelSessionLocal;
    }

    public String getAddRoomHotelName() {
        return addRoomHotelName;
    }

    public void setAddRoomHotelName(String addRoomHotelName) {
        this.addRoomHotelName = addRoomHotelName;
    }

    public FeedbackSessionLocal getFeedbackSessionLocal() {
        return feedbackSessionLocal;
    }

    public void setFeedbackSessionLocal(FeedbackSessionLocal feedbackSessionLocal) {
        this.feedbackSessionLocal = feedbackSessionLocal;
    }

    public LogSessionLocal getLogSessionLocal() {
        return logSessionLocal;
    }

    public void setLogSessionLocal(LogSessionLocal logSessionLocal) {
        this.logSessionLocal = logSessionLocal;
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public String getLogActivityName() {
        return logActivityName;
    }

    public void setLogActivityName(String logActivityName) {
        this.logActivityName = logActivityName;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public String getStPassword() {
        return stPassword;
    }

    public void setStPassword(String stPassword) {
        this.stPassword = stPassword;
    }

    public String getStEmail() {
        return stEmail;
    }

    public void setStEmail(String stEmail) {
        this.stEmail = stEmail;
    }

    public String getStPhoneNumber() {
        return stPhoneNumber;
    }

    public void setStPhoneNumber(String stPhoneNumber) {
        this.stPhoneNumber = stPhoneNumber;
    }

    public String getStGender() {
        return stGender;
    }

    public void setStGender(String stGender) {
        this.stGender = stGender;
    }

    public String getStNric() {
        return stNric;
    }

    public void setStNric(String stNric) {
        this.stNric = stNric;
    }

    public String getStAddress() {
        return stAddress;
    }

    public void setStAddress(String stAddress) {
        this.stAddress = stAddress;
    }

    public String getStHotel() {
        return stHotel;
    }

    public void setStHotel(String stHotel) {
        this.stHotel = stHotel;
    }

    public String getStJobTitle() {
        return stJobTitle;
    }

    public void setStJobTitle(String stJobTitle) {
        this.stJobTitle = stJobTitle;
    }

    public String getStDepartment() {
        return stDepartment;
    }

    public void setStDepartment(String stDepartment) {
        this.stDepartment = stDepartment;
    }

    public int getStLeave() {
        return stLeave;
    }

    public void setStLeave(int stLeave) {
        this.stLeave = stLeave;
    }

    public String getStNokName() {
        return stNokName;
    }

    public void setStNokName(String stNokName) {
        this.stNokName = stNokName;
    }

    public String getStNokAddress() {
        return stNokAddress;
    }

    public void setStNokAddress(String stNokAddress) {
        this.stNokAddress = stNokAddress;
    }

    public String getStNokPhoneNumber() {
        return stNokPhoneNumber;
    }

    public void setStNokPhoneNumber(String stNokPhoneNumber) {
        this.stNokPhoneNumber = stNokPhoneNumber;
    }

    public String[] getStStaffType() {
        return stStaffType;
    }

    public void setStStaffType(String[] stStaffType) {
        this.stStaffType = stStaffType;
    }

    public StaffSessionLocal getStaffSessionLocal() {
        return staffSessionLocal;
    }

    public void setStaffSessionLocal(StaffSessionLocal staffSessionLocal) {
        this.staffSessionLocal = staffSessionLocal;
    }

    public Staff getSelectedStaff() {
        return selectedStaff;
    }

    public void setSelectedStaff(Staff selectedStaff) {
        this.selectedStaff = selectedStaff;
    }

    public HotelFacility getSelectedFacilityObj() {
        return selectedFacilityObj;
    }

    public void setSelectedFacilityObj(HotelFacility selectedFacilityObj) {
        this.selectedFacilityObj = selectedFacilityObj;
    }

    public HolidaySurcharge getSelectedHoliday() {
        return selectedHoliday;
    }

    public void setSelectedHoliday(HolidaySurcharge selectedHoliday) {
        this.selectedHoliday = selectedHoliday;
    }

    public Hotel getSelectedHotelObj() {
        return selectedHotelObj;
    }

    public void setSelectedHotelObj(Hotel selectedHotelObj) {
        this.selectedHotelObj = selectedHotelObj;
    }

    public MinibarItem getSelectedMinibarItem() {
        return selectedMinibarItem;
    }

    public void setSelectedMinibarItem(MinibarItem selectedMinibarItem) {
        this.selectedMinibarItem = selectedMinibarItem;
    }

    public Room getSelectedRoom() {
        return selectedRoom;
    }

    public void setSelectedRoom(Room selectedRoom) {
        this.selectedRoom = selectedRoom;
    }

    public RoomFacility getSelectedRoomFacility() {
        return selectedRoomFacility;
    }

    public void setSelectedRoomFacility(RoomFacility selectedRoomFacility) {
        this.selectedRoomFacility = selectedRoomFacility;
    }

    public ExtraSurcharge getSelectedSurcharge() {
        return selectedSurcharge;
    }

    public void setSelectedSurcharge(ExtraSurcharge selectedSurcharge) {
        this.selectedSurcharge = selectedSurcharge;
    }

}
