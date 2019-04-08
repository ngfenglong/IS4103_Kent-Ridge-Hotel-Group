/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.CreditCard;
import entity.ExtraSurcharge;
import entity.Feedback;
import entity.HolidaySurcharge;
import entity.Hotel;
import entity.HotelFacility;
import entity.MinibarItem;
import entity.PaymentTransaction;
import entity.Room;
import entity.RoomBooking;
import entity.RoomFacility;
import error.NoResultException;
import javax.inject.Named;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import sessionBeans.BookingSessionLocal;
import sessionBeans.FeedbackSessionLocal;
import sessionBeans.HotelFacilitySessionLocal;
import sessionBeans.HotelSessionLocal;
import sessionBeans.LogSessionLocal;
import sessionBeans.PaymentTransactionSessionLocal;
import sessionBeans.RoomFacilitySessionLocal;
import sessionBeans.RoomPricingSessionLocal;
import sessionBeans.RoomSessionLocal;

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
    @EJB
    HotelSessionLocal hotelSessionLocal;
    @EJB
    HotelFacilitySessionLocal hotelFacilitySessionLocal;
    @EJB
    RoomFacilitySessionLocal roomFacilitySessionLocal;
    @EJB
    RoomSessionLocal roomSessionLocal;
    @EJB
    FeedbackSessionLocal feedbackSessionLocal;
    @EJB
    LogSessionLocal logSessionLocal;
    @EJB
    BookingSessionLocal bookingSessionLocal;
    @EJB
    RoomPricingSessionLocal roomPricingSessionLocal;
    @EJB
    PaymentTransactionSessionLocal paymentTransactionSessionLocal;

    @ManagedProperty(value = "#{websiteAuthenticationManagedBean}")
    private WebsiteAuthenticationManagedBean authBean;

    public WebsiteAuthenticationManagedBean getAuthBean() {
        return authBean;
    }

    public void setAuthBean(WebsiteAuthenticationManagedBean authBean) {
        this.authBean = authBean;
    }

    Hotel selectedHotel;
    String checkInTB;
    String checkOutTB;
    int guestTB;
    String roomTypeTB;
    int noOfRoomTB;

    String genderTB;
    String fNameTB;
    String lNameTB;
    String emailAddressTB;
    String contactNumberTB;
    String paymentMethod;
    String creditCardTB;
    String cvsTB;
    String expiryDateTB;
    String passportNumberTB;

    String pickUpLocationTB;
    int seatsTB;
    String pickUpDateTB;
    String pickUpTimeTB;

    List<Room> roomAvailable = new ArrayList<Room>();
    boolean hasRoom;

    public WebsiteManagedBean() {
    }

    public String viewHotel(Long hID) throws NoResultException {
        selectedHotel = hotelSessionLocal.getHotelByID(hID);

        return "booking1.xhtml?faces-redirect=true";
    }

    public String getDisplayRoomImage(String roomType) {
        if (roomType.equals("Standard")) {
            return "booking_1.jpg";
        } else if (roomType.equals("Deluxe")) {
            return "booking_2.jpg";
        } else if (roomType.equals("Premium")) {
            return "booking_3.jpg";
        } else if (roomType.equals("Suite")) {
            return "booking_4.jpg";
        } else {
            return "booking_5.jpg";
        }
    }

    public String checkAvailability() throws NoResultException, ParseException {
        List<RoomBooking> allBooking = bookingSessionLocal.getAllRoomBookingByStatus("checkedout", selectedHotel.getHotelCodeName());
        List<RoomBooking> checkList = new ArrayList<RoomBooking>();
        List<Room> returnList = new ArrayList<Room>();
        hasRoom = false;

        //Convert String to Date
        Date tempCheckIn = new SimpleDateFormat("yyyy-MM-dd").parse(checkInTB);
        Date tempCheckOut = new SimpleDateFormat("yyyy-MM-dd").parse(checkOutTB);

        //Get roombooking by type & Status
        if (allBooking != null) {
            for (RoomBooking rb : allBooking) {
                if (rb.getRoomType().equals(roomTypeTB) && rb.getStatus().equals("checkedout")) {
                    RoomBooking tempRoomBooking = rb;
                    checkList.add(tempRoomBooking);
                }
            }

            allBooking = checkList;
            checkList.clear();
            for (RoomBooking rb : allBooking) {
                // Didn't Hit the Booking Date
                if ((tempCheckIn.before(rb.getBookInDate()) && tempCheckOut.before(rb.getBookInDate())) || (tempCheckIn.after(rb.getBookInDate()) && tempCheckOut.after(rb.getBookInDate()))) {

                } // Hit the booking date AKA Not Available
                else {
                    RoomBooking tempRoomBooking = rb;
                    checkList.add(tempRoomBooking);
                }
            }

            if (checkList.size() < roomSessionLocal.getRoomByHotelNameAndRoomType(roomTypeTB, selectedHotel.getHotelCodeName()).size()) {
                hasRoom = true;
                List<Room> filterList = roomSessionLocal.getRoomByHotelNameAndRoomType(roomTypeTB, selectedHotel.getHotelCodeName());
                for (Room r : filterList) {
                    if (!r.getStatus().toLowerCase().equals("occupied")) {
                        Room tempRoom = r;
                        returnList.add(r);
                    }
                }
                roomAvailable = returnList;
            }
        } else {
            roomAvailable = roomSessionLocal.getRoomByHotelNameAndRoomType(roomTypeTB, selectedHotel.getHotelCodeName());
        }
        return "CheckAvail.xhtml?faces-redirect=true";
    }

    public String makePayment() throws ParseException, NoResultException {

        List<RoomBooking> roomsBooked = new ArrayList<RoomBooking>();
        PaymentTransaction p = new PaymentTransaction();

        double totalPrice = 0;
        for (int i = 0; i < noOfRoomTB; i++) {
            RoomBooking rb = new RoomBooking();
            rb.setBookInDate(new SimpleDateFormat("yyyy-MM-dd").parse(checkInTB));
            rb.setBookOutDate(new SimpleDateFormat("yyyy-MM-dd").parse(checkOutTB));
            rb.setBookingDate(java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            rb.setPassportNum(passportNumberTB);
            rb.setPrice(roomPricingSessionLocal.getRoomPricingByName(selectedHotel.getHotelCodeName() + "_" + roomTypeTB).getPrice());
            totalPrice += roomPricingSessionLocal.getRoomPricingByName(selectedHotel.getHotelCodeName() + "_" + roomTypeTB).getPrice();
            rb.setStatus("reserved");
            if (authBean.getLoggedInCustomer() == null) {
                rb.setBookedBy(authBean.getLoggedInCustomer());
                rb.setFirstName(fNameTB);
                rb.setLastName(lNameTB);
                rb.setEmailAddress(emailAddressTB);
                rb.setRoomType(roomTypeTB);
            } else {
                rb.setFirstName(fNameTB);
                rb.setLastName(lNameTB);
                rb.setEmailAddress(emailAddressTB);
            }
            if (!pickUpLocationTB.equals("-")) {
                rb.setHasTransport(true);
                rb.setTransportTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(pickUpDateTB + " " + pickUpTimeTB));
            } else {
                rb.setHasTransport(false);
            }
            bookingSessionLocal.createRoomBooking(rb);
            rb = bookingSessionLocal.getLastRoomBooking();
            roomsBooked.add(rb);
        }
        CreditCard cc = new CreditCard();
        cc.setCardNum(creditCardTB);
        cc.setCvv(cvsTB);
        cc.setExpiryDate(new SimpleDateFormat("yy/MM").parse(expiryDateTB));
        paymentTransactionSessionLocal.createCreditCard(cc);
        cc = paymentTransactionSessionLocal.getLastCreditCard();
        p.setCreditCard(cc);
        p.setTotalPrice(totalPrice);
        p.setInitialPayment(totalPrice);

        p.setPaymentType(paymentMethod);
        p.setRoomsBooked(roomsBooked);
        paymentTransactionSessionLocal.createPaymentTransaction(p);

        String msg = "<!DOCTYPE html>\n"
                + "<html >\n"
                + "  <head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <title>Email Template</title>\n"
                + "    \n"
                + "    \n"
                + "    \n"
                + "    \n"
                + "    \n"
                + "    \n"
                + "    \n"
                + "    \n"
                + "  </head>\n"
                + "\n"
                + "  <body>\n"
                + "\n"
                + "    <!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "\n"
                + "<head>\n"
                + "  <title>Confirmation Email</title>\n"
                + "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n"
                + "  <meta name=\"viewport\" content=\"width=device-width\">\n"
                + "</head>\n"
                + "\n"
                + "<body style=\"margin: 0\">\n"
                + "  <style type=\"text/css\">\n"
                + "    body {\n"
                + "      margin: 0;\n"
                + "      }\n"
                + "      h1 a:hover {\n"
                + "      font-size: 30px; color: #333;\n"
                + "      }\n"
                + "      h1 a:active {\n"
                + "      font-size: 30px; color: #333;\n"
                + "      }\n"
                + "      h1 a:visited {\n"
                + "      font-size: 30px; color: #333;\n"
                + "      }\n"
                + "      a:hover {\n"
                + "      text-decoration: none;\n"
                + "      }\n"
                + "      a:active {\n"
                + "      text-decoration: none;\n"
                + "      }\n"
                + "      a:visited {\n"
                + "      text-decoration: none;\n"
                + "      }\n"
                + "      .button__text:hover {\n"
                + "      color: #fff; text-decoration: none;\n"
                + "      }\n"
                + "      .button__text:active {\n"
                + "      color: #fff; text-decoration: none;\n"
                + "      }\n"
                + "      .button__text:visited {\n"
                + "      color: #fff; text-decoration: none;\n"
                + "      }\n"
                + "      a:hover {\n"
                + "      color: #080e66;\n"
                + "      }\n"
                + "      a:active {\n"
                + "      color: #080e66;\n"
                + "      }\n"
                + "      a:visited {\n"
                + "      color: #080e66;\n"
                + "      }\n"
                + "      @media (max-width: 600px) {\n"
                + "        .container {\n"
                + "          width: 94% !important;\n"
                + "        }\n"
                + "        .main-action-cell {\n"
                + "          float: none !important; margin-right: 0 !important;\n"
                + "        }\n"
                + "        .secondary-action-cell {\n"
                + "          text-align: center; width: 100%;\n"
                + "        }\n"
                + "        .header {\n"
                + "          margin-top: 20px !important; margin-bottom: 2px !important;\n"
                + "        }\n"
                + "        .shop-name__cell {\n"
                + "          display: block;\n"
                + "        }\n"
                + "        .order-number__cell {\n"
                + "          display: block; text-align: left !important; margin-top: 20px;\n"
                + "        }\n"
                + "        .button {\n"
                + "          width: 100%;\n"
                + "        }\n"
                + "        .or {\n"
                + "          margin-right: 0 !important;\n"
                + "        }\n"
                + "        .apple-wallet-button {\n"
                + "          text-align: center;\n"
                + "        }\n"
                + "        .customer-info__item {\n"
                + "          display: block; width: 100% !important;\n"
                + "        }\n"
                + "        .spacer {\n"
                + "          display: none;\n"
                + "        }\n"
                + "        .subtotal-spacer {\n"
                + "          display: none;\n"
                + "        }\n"
                + "      }\n"
                + "  </style>\n"
                + "  <table class=\"body\" style=\"border-collapse: collapse; border-spacing: 0; height: 100% !important; width: 100% !important\">\n"
                + "    <tr>\n"
                + "      <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                + "        <table class=\"header row\" style=\"border-collapse: collapse; border-spacing: 0; margin: 40px 0 20px; width: 100%\">\n"
                + "          <tr>\n"
                + "            <td class=\"header__cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                + "              <center>\n"
                + "                <table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">\n"
                + "                  <tr>\n"
                + "                    <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                + "                      <table class=\"row\" style=\"border-collapse: collapse; border-spacing: 0; width: 100%\">\n"
                + "                        <tr>\n"
                + "                          <td class=\"shop-name__cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                + "                            <img src=\"images/KRHGblack.png\" width=\"180\">\n"
                + "                          </td>\n"
                + "                          <td class=\"order-number__cell\" style=\"color: #999; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; font-size: 14px; text-align: right\"\n"
                + "                            align=\"right\"> <span class=\"order-number__text\" style=\"font-size: 16px\">\n"
                + "                        Booking No.: 7548129610\n"
                + "                      </span>\n"
                + "\n"
                + "                          </td>\n"
                + "                        </tr>\n"
                + "                      </table>\n"
                + "                    </td>\n"
                + "                  </tr>\n"
                + "                </table>\n"
                + "              </center>\n"
                + "            </td>\n"
                + "          </tr>\n"
                + "        </table>\n"
                + "\n"
                + "        <br><br><br>\n"
                + "        \n"
                + "        <table class=\"row content\" style=\"border-collapse: collapse; border-spacing: 0; width: 100%\">\n"
                + "          <tr>\n"
                + "            <td class=\"content__cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px\">\n"
                + "              <center>\n"
                + "                <table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">\n"
                + "                  <tr>\n"
                + "                    <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                + "                      <h2 style=\"font-size: 24px; font-weight: normal; margin: 0 0 10px\">Your Booking has been confirmed!</h2>\n"
                + "\n"
                + "                      <p style=\"color: #777; font-size: 16px; line-height: 150%; margin: 0\">Dear Shiah Leei Loh,<br><br>\n"
                + "                        Your booking at Kent Ridge Grand \n"
                + "                        for May 15, 2019 - May 17, 2019 \n"
                + "                        (Booking No. 7548129610) has been confirmed. You don't need to do anything further—just enjoy your stay! <br><br>\n"
                + "                        If you require a receipt, please inquire at the hotel.</p>\n"
                + "                      <table class=\"row actions\" style=\"border-collapse: collapse; border-spacing: 0; margin-top: 20px; width: 100%\">\n"
                + "                        <tr>\n"
                + "                          <td class=\"actions__cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                + "                            <table class=\"button main-action-cell\" style=\"border-collapse: collapse; border-spacing: 0; float: left; margin-right: 15px\">\n"
                + "                              <tr>\n"
                + "                                <td class=\"button__cell\" style=\"background: #080e66; border-radius: 4px; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding: 20px 25px; text-align: center\"\n"
                + "                                  align=\"center\" bgcolor=\"#080e66\">\n"
                + "                                  <a href=\"#\" class=\"button__text\" style=\"color: #fff; font-size: 16px; text-decoration: none\">Manage My Booking</a>\n"
                + "                                </td>\n"
                + "                              </tr>\n"
                + "                            </table>\n"
                + "                          </td>\n"
                + "                        </tr>\n"
                + "                      </table>\n"
                + "                    </td>\n"
                + "                  </tr>\n"
                + "                </table>\n"
                + "              </center>\n"
                + "            </td>\n"
                + "          </tr>\n"
                + "        </table>\n"
                + "        <table class=\"row section\" style=\"border-collapse: collapse; border-spacing: 0; border-top-color: #e5e5e5; border-top-style: solid; border-top-width: 1px; width: 100%\">\n"
                + "          <tr>\n"
                + "            <td class=\"section__cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding: 40px 0\">\n"
                + "              <center>\n"
                + "                <table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">\n"
                + "                  <tr>\n"
                + "                    <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                + "                      <h3 style=\"font-size: 20px; font-weight: normal; margin: 0 0 25px\">Booking Details</h3>\n"
                + "\n"
                + "                    </td>\n"
                + "                  </tr>\n"
                + "                </table>\n"
                + "                <table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">\n"
                + "                  <tr>\n"
                + "                    <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                + "                      <table class=\"row\" style=\"border-collapse: collapse; border-spacing: 0; width: 100%\">\n"
                + "                        <tr class=\"order-list__item order-list__item--single\" style=\"border-bottom-color: #e5e5e5; border-bottom-style: none !important; border-bottom-width: 1px; width: 100%\">\n"
                + "                          <td class=\"order-list__item__cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding: 0\">\n"
                + "                            <table style=\"border-collapse: collapse; border-spacing: 0\">\n"
                + "                              <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                + "                                <img src=\"images/KRG.jpg\" align=\"left\" width=\"130\" height=\"100\" class=\"order-list__product-image\" style=\"border: 1px solid #e5e5e5; border-radius: 8px; margin-right: 15px\">\n"
                + "                              </td>\n"
                + "                              <td class=\"order-list__product-description-cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; width: 100%\"> \n"
                + "                                <span class=\"order-list__item-title\" style=\"color: rgb(37, 13, 175); font-size: 16px; font-weight: 600; line-height: 1.4\">Kent Ridge Grand</span>\n"
                + "                                <br><br>\n"
                + "                                <span class=\"order-list__item-title\" style=\"color: rgb(0, 0, 0); font-size: 16px; font-weight: 600; line-height: 1.4\">63 Somerset Road, Singapore 238163</span>\n"
                + "                                <br><br>\n"
                + "                                <span class=\"order-list__item-title\" style=\"color: rgb(0, 0, 0); font-size: 16px; font-weight: 600; line-height: 1.4\">+65 6798 4432</span>\n"
                + "\n"
                + "                              </td>\n"
                + "                            </table>\n"
                + "                          </td>\n"
                + "                        </tr>\n"
                + "                      </table>\n"
                + "\n"
                + "        <table class=\"row section\" style=\"border-collapse: collapse; border-spacing: 0; border-top-color: #e5e5e5; border-top-style: solid; border-top-width: 1px; width: 100%\">\n"
                + "          <tr>\n"
                + "            <td class=\"section__cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding: 40px 0\">\n"
                + "              <center>\n"
                + "                <table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">\n"
                + "                  <tr>\n"
                + "                    <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                + "                      <h3 style=\"font-size: 20px; font-weight: normal; margin: 0 0 25px\">Customer Information</h3>\n"
                + "\n"
                + "                    </td>\n"
                + "                  </tr>\n"
                + "                </table>\n"
                + "                <table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">\n"
                + "                  <tr>\n"
                + "                    <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                + "                      <table class=\"row\" style=\"border-collapse: collapse; border-spacing: 0; width: 100%\">\n"
                + "                        <tr>\n"
                + "                          <td class=\"customer-info__item\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px; width: 50%\">\n"
                + "                            <h4 style=\"color: #555; font-size: 16px; font-weight: 500; margin: 0 0 5px\">Your booking</h4>\n"
                + "\n"
                + "                           \n"
                + "                          </td>\n"
                + "                          <td class=\"customer-info__item\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px; width: 50%\">\n"
                + "                            <h4 style=\"color: #555; font-size: 16px; font-weight: 500; margin: 0 0 5px\">1 room, 2 nights</h4>\n"
                + "\n"
                + "                          </td>\n"
                + "                        </tr>\n"
                + "                      </table>\n"
                + "                      \n"
                + "                    </td>\n"
                + "                  </tr>\n"
                + "                </table>\n"
                + "\n"
                + "                <hr style=\"border-top: dotted 1px;\" />\n"
                + "\n"
                + "                <table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">\n"
                + "                    <tr>\n"
                + "                      <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                + "                        <table class=\"row\" style=\"border-collapse: collapse; border-spacing: 0; width: 100%\">\n"
                + "                          <tr>\n"
                + "                            <td class=\"customer-info__item\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px; width: 50%\">\n"
                + "                              <h4 style=\"color: #555; font-size: 16px; font-weight: 500; margin: 0 0 5px\">Check-in</h4>\n"
                + "  \n"
                + "                             \n"
                + "                            </td>\n"
                + "                            <td class=\"customer-info__item\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px; width: 50%\">\n"
                + "                              <h4 style=\"color: #555; font-size: 16px; font-weight: 500; margin: 0 0 5px\">May 15, 2019<br> 14:00 - 06:00 (Following morning)</h4> \n"
                + "  \n"
                + "                            </td>\n"
                + "                          </tr>\n"
                + "                        </table>\n"
                + "                        \n"
                + "                      </td>\n"
                + "                    </tr>\n"
                + "                  </table>\n"
                + "\n"
                + "                  <hr style=\"border-top: dotted 1px;\" />\n"
                + "\n"
                + "                  <table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">\n"
                + "                    <tr>\n"
                + "                      <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                + "                        <table class=\"row\" style=\"border-collapse: collapse; border-spacing: 0; width: 100%\">\n"
                + "                          <tr>\n"
                + "                            <td class=\"customer-info__item\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px; width: 50%\">\n"
                + "                              <h4 style=\"color: #555; font-size: 16px; font-weight: 500; margin: 0 0 5px\">Check-out</h4>\n"
                + "  \n"
                + "                             \n"
                + "                            </td>\n"
                + "                            <td class=\"customer-info__item\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px; width: 50%\">\n"
                + "                              <h4 style=\"color: #555; font-size: 16px; font-weight: 500; margin: 0 0 5px\">May 17, 2019 <br> Until 12:00</h4> \n"
                + "  \n"
                + "                            </td>\n"
                + "                          </tr>\n"
                + "                        </table>\n"
                + "                        \n"
                + "                      </td>\n"
                + "                    </tr>\n"
                + "                  </table>\n"
                + "\n"
                + "                  <hr style=\"border-top: dotted 1px;\" />\n"
                + "\n"
                + "                  <table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">\n"
                + "                    <tr>\n"
                + "                      <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                + "                        <table class=\"row\" style=\"border-collapse: collapse; border-spacing: 0; width: 100%\">\n"
                + "                          <tr>\n"
                + "                            <td class=\"customer-info__item\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px; width: 50%\">\n"
                + "                              <h4 style=\"color: #555; font-size: 16px; font-weight: 500; margin: 0 0 5px\">Guest Name:</h4>\n"
                + "  \n"
                + "                             \n"
                + "                            </td>\n"
                + "                            <td class=\"customer-info__item\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px; width: 50%\">\n"
                + "                              <h4 style=\"color: #555; font-size: 16px; font-weight: 500; margin: 0 0 5px\">Shiah Leei Loh</h4> \n"
                + "  \n"
                + "                            </td>\n"
                + "                          </tr>\n"
                + "                        </table>\n"
                + "                        \n"
                + "                      </td>\n"
                + "                    </tr>\n"
                + "                  </table>\n"
                + "\n"
                + "                  <h4 style=\"color: rgb(114, 114, 114); font-size: 10px; font-weight: 500; margin: 0 0 5px\">Failure to arrive on time, might cause forfeiture of rooms</h4> \n"
                + "\n"
                + "                \n"
                + "              </center>\n"
                + "            </td>\n"
                + "          </tr>\n"
                + "        </table>\n"
                + "\n"
                + "        <table class=\"row section\" style=\"border-collapse: collapse; border-spacing: 0; border-top-color: #e5e5e5; border-top-style: solid; border-top-width: 1px; width: 100%\">\n"
                + "            <tr>\n"
                + "              <td class=\"section__cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding: 40px 0\">\n"
                + "                <center>\n"
                + "                  <table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">\n"
                + "                    <tr>\n"
                + "                      <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                + "                        <h3 style=\"font-size: 20px; font-weight: normal; margin: 0 0 25px\">Prepay Online</h3>\n"
                + "  \n"
                + "                      </td>\n"
                + "                    </tr>\n"
                + "                  </table>\n"
                + "                  <table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">\n"
                + "                    <tr>\n"
                + "                      <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                + "                        <table class=\"row\" style=\"border-collapse: collapse; border-spacing: 0; width: 100%\">\n"
                + "                          <tr class=\"order-list__item order-list__item--single\" style=\"border-bottom-color: #e5e5e5; border-bottom-style: none !important; border-bottom-width: 1px; width: 100%\">\n"
                + "                            <td class=\"order-list__item__cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding: 0\">\n"
                + "                              <table style=\"border-collapse: collapse; border-spacing: 0\">\n"
                + "                                \n"
                + "                                <td class=\"order-list__product-description-cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; width: 100%\"> \n"
                + "                                    <span class=\"order-list__item-title\" style=\"color: #555; font-size: 16px; font-weight: 600; line-height: 1.4\">Room Rate</span>\n"
                + "                                  <br>\n"
                + "                                </td>\n"
                + "                                <td class=\"order-list__price-cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; white-space: nowrap\">\n"
                + "                                  <p class=\"order-list__item-price\" style=\"color: #555; font-size: 16px; font-weight: 600; line-height: 150%; margin: 0 0 0 15px; text-align: right\" align=\"right\">SGD $480.00</p>\n"
                + "                                </td>\n"
                + "                              </table>\n"
                + "                            </td>\n"
                + "                          </tr>\n"
                + "                        </table>\n"
                + "\n"
                + "                        <hr style=\"border-top: dotted 1px;\" />\n"
                + "\n"
                + "                        <table class=\"row\" style=\"border-collapse: collapse; border-spacing: 0; width: 100%\">\n"
                + "                            <tr class=\"order-list__item order-list__item--single\" style=\"border-bottom-color: #e5e5e5; border-bottom-style: none !important; border-bottom-width: 1px; width: 100%\">\n"
                + "                              <td class=\"order-list__item__cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding: 0\">\n"
                + "                                <table style=\"border-collapse: collapse; border-spacing: 0\">\n"
                + "                                  \n"
                + "                                  <td class=\"order-list__product-description-cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; width: 100%\"> \n"
                + "                                      <span class=\"order-list__item-title\" style=\"color: #555; font-size: 16px; font-weight: 600; line-height: 1.4\">Taxes & Fees</span>\n"
                + "                                    <br>\n"
                + "                                  </td>\n"
                + "                                  <td class=\"order-list__price-cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; white-space: nowrap\">\n"
                + "                                    <p class=\"order-list__item-price\" style=\"color: #555; font-size: 16px; font-weight: 600; line-height: 150%; margin: 0 0 0 15px; text-align: right\" align=\"right\">SGD $0.00</p>\n"
                + "                                  </td>\n"
                + "                                </table>\n"
                + "                              </td>\n"
                + "                            </tr>\n"
                + "                          </table>\n"
                + "\n"
                + "                          <hr style=\"border-top: dotted 1px;\" />\n"
                + "\n"
                + "                          <table class=\"row\" style=\"border-collapse: collapse; border-spacing: 0; width: 100%\">\n"
                + "                            <tr class=\"order-list__item order-list__item--single\" style=\"border-bottom-color: #e5e5e5; border-bottom-style: none !important; border-bottom-width: 1px; width: 100%\">\n"
                + "                              <td class=\"order-list__item__cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding: 0\">\n"
                + "                                <table style=\"border-collapse: collapse; border-spacing: 0\">\n"
                + "                                  \n"
                + "                                  <td class=\"order-list__product-description-cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; width: 100%\"> \n"
                + "                                      <span class=\"order-list__item-title\" style=\"color: #555; font-size: 16px; font-weight: 600; line-height: 1.4\">Total</span>\n"
                + "                                    <br>\n"
                + "                                  </td>\n"
                + "                                  <td class=\"order-list__price-cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; white-space: nowrap\">\n"
                + "                                    <p class=\"order-list__item-price\" style=\"color: #555; font-size: 16px; font-weight: 600; line-height: 150%; margin: 0 0 0 15px; text-align: right\" align=\"right\">SGD $480.00</p>\n"
                + "                                  </td>\n"
                + "                                </table>\n"
                + "                              </td>\n"
                + "                            </tr>\n"
                + "                          </table>\n"
                + "                        \n"
                + "                      </td>\n"
                + "                    </tr>\n"
                + "                  </table>\n"
                + "                  <br><br>\n"
                + "                  <h4 style=\"color: rgb(114, 114, 114); font-size: 10px; font-weight: 500; margin: 0 0 5px\">Your room rate has been prepaid to the hotel.You may still need to pay additional taxes & fees at the hotel front desk according to local regulations.</h4> \n"
                + "                </center>\n"
                + "              </td>\n"
                + "            </tr>\n"
                + "          </table>\n"
                + "\n"
                + "          <table class=\"row section\" style=\"border-collapse: collapse; border-spacing: 0; border-top-color: #e5e5e5; border-top-style: solid; border-top-width: 1px; width: 100%\">\n"
                + "            <tr>\n"
                + "              <td class=\"section__cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding: 40px 0\">\n"
                + "                <center>\n"
                + "                  <table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">\n"
                + "                    <tr>\n"
                + "                      <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                + "                        <h3 style=\"font-size: 20px; font-weight: normal; margin: 0 0 25px\">Room Information</h3>\n"
                + "  \n"
                + "                      </td>\n"
                + "                    </tr>\n"
                + "                  </table>\n"
                + "                  <table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">\n"
                + "                    <tr>\n"
                + "                      <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                + "                        <table class=\"row\" style=\"border-collapse: collapse; border-spacing: 0; width: 100%\">\n"
                + "                          <tr>\n"
                + "                            <td class=\"customer-info__item\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px; width: 50%\">\n"
                + "                              <h4 style=\"color: #555; font-size: 16px; font-weight: 500; margin: 0 0 5px\">Room Type</h4>\n"
                + "  \n"
                + "                             \n"
                + "                            </td>\n"
                + "                            <td class=\"customer-info__item\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px; width: 50%\">\n"
                + "                              <h4 style=\"color: #555; font-size: 16px; font-weight: 500; margin: 0 0 5px\">Standard Room</h4>\n"
                + "  \n"
                + "                            </td>\n"
                + "                          </tr>\n"
                + "                        </table>\n"
                + "                        \n"
                + "                      </td>\n"
                + "                    </tr>\n"
                + "                  </table>\n"
                + "\n"
                + "                  <hr style=\"border-top: dotted 1px;\" />\n"
                + "\n"
                + "\n"
                + "                  \n"
                + "                  <table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">\n"
                + "                      <tr>\n"
                + "                        <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                + "                          <table class=\"row\" style=\"border-collapse: collapse; border-spacing: 0; width: 100%\">\n"
                + "                            <tr>\n"
                + "                              <td class=\"customer-info__item\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px; width: 50%\">\n"
                + "                                <h4 style=\"color: #555; font-size: 16px; font-weight: 500; margin: 0 0 5px\">Max. Occupancy</h4>\n"
                + "    \n"
                + "                               \n"
                + "                              </td>\n"
                + "                              <td class=\"customer-info__item\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px; width: 50%\">\n"
                + "                                <h4 style=\"color: #555; font-size: 16px; font-weight: 500; margin: 0 0 5px\">2</h4> \n"
                + "    \n"
                + "                              </td>\n"
                + "                            </tr>\n"
                + "                          </table>\n"
                + "                          \n"
                + "                        </td>\n"
                + "                      </tr>\n"
                + "                    </table>\n"
                + "\n"
                + "                    <hr style=\"border-top: dotted 1px;\" />\n"
                + "\n"
                + "                    <table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">\n"
                + "                      <tr>\n"
                + "                        <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                + "                          <table class=\"row\" style=\"border-collapse: collapse; border-spacing: 0; width: 100%\">\n"
                + "                            <tr>\n"
                + "                              <td class=\"customer-info__item\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px; width: 50%\">\n"
                + "                                <h4 style=\"color: #555; font-size: 16px; font-weight: 500; margin: 0 0 5px\">Meals</h4>\n"
                + "    \n"
                + "                               \n"
                + "                              </td>\n"
                + "                              <td class=\"customer-info__item\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px; width: 50%\">\n"
                + "                                <h4 style=\"color: #555; font-size: 16px; font-weight: 500; margin: 0 0 5px\">Breakfast not included</h4> \n"
                + "    \n"
                + "                              </td>\n"
                + "                            </tr>\n"
                + "                          </table>\n"
                + "                          \n"
                + "                        </td>\n"
                + "                      </tr>\n"
                + "                    </table>\n"
                + "\n"
                + "                    <hr style=\"border-top: dotted 1px;\" />\n"
                + "\n"
                + "                    <table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">\n"
                + "                      <tr>\n"
                + "                        <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                + "                          <table class=\"row\" style=\"border-collapse: collapse; border-spacing: 0; width: 100%\">\n"
                + "                            <tr>\n"
                + "                              <td class=\"customer-info__item\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px; width: 50%\">\n"
                + "                                <h4 style=\"color: #555; font-size: 16px; font-weight: 500; margin: 0 0 5px\">Bed Type</h4>\n"
                + "    \n"
                + "                               \n"
                + "                              </td>\n"
                + "                              <td class=\"customer-info__item\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px; width: 50%\">\n"
                + "                                <h4 style=\"color: #555; font-size: 16px; font-weight: 500; margin: 0 0 5px\">1 Queen Bed</h4> \n"
                + "    \n"
                + "                              </td>\n"
                + "                            </tr>\n"
                + "                          </table>\n"
                + "                          \n"
                + "                        </td>\n"
                + "                      </tr>\n"
                + "                    </table>\n"
                + "\n"
                + "                    <hr style=\"border-top: dotted 1px;\" />\n"
                + "\n"
                + "                    <table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">\n"
                + "                        <tr>\n"
                + "                          <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                + "                            <table class=\"row\" style=\"border-collapse: collapse; border-spacing: 0; width: 100%\">\n"
                + "                              <tr>\n"
                + "                                <td class=\"customer-info__item\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px; width: 50%\">\n"
                + "                                  <h4 style=\"color: #555; font-size: 16px; font-weight: 500; margin: 0 0 5px\">Cancellation Policy</h4>\n"
                + "      \n"
                + "                                 \n"
                + "                                </td>\n"
                + "                                <td class=\"customer-info__item\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px; width: 50%\">\n"
                + "                                  <h4 style=\"color: #555; font-size: 16px; font-weight: 500; margin: 0 0 5px\">You may cancel or change for free before May 1, 2019, 18:00\n"
                + "                                    (hotel's local time). You’ll be charged the total price of the booking if you cancel or change after the deadline or do not\n"
                + "                                    show up.</h4> \n"
                + "      \n"
                + "                                </td>\n"
                + "                              </tr>\n"
                + "                            </table>\n"
                + "                            \n"
                + "                          </td>\n"
                + "                        </tr>\n"
                + "                      </table>\n"
                + "\n"
                + "                      <hr style=\"border-top: dotted 1px;\" />\n"
                + "\n"
                + "                      <table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">\n"
                + "                        <tr>\n"
                + "                          <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                + "                            <table class=\"row\" style=\"border-collapse: collapse; border-spacing: 0; width: 100%\">\n"
                + "                              <tr>\n"
                + "                                <td class=\"customer-info__item\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px; width: 50%\">\n"
                + "                                  <h4 style=\"color: #555; font-size: 16px; font-weight: 500; margin: 0 0 5px\">Extra Bed</h4>\n"
                + "      \n"
                + "                                 \n"
                + "                                </td>\n"
                + "                                <td class=\"customer-info__item\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px; width: 50%\">\n"
                + "                                  <h4 style=\"color: #555; font-size: 16px; font-weight: 500; margin: 0 0 5px\">Extra bed available for an additional SGD $150.00</h4> \n"
                + "      \n"
                + "                                </td>\n"
                + "                              </tr>\n"
                + "                            </table>\n"
                + "                            \n"
                + "                          </td>\n"
                + "                        </tr>\n"
                + "                      </table>\n"
                + "\n"
                + "                      <hr style=\"border-top: dotted 1px;\" />\n"
                + "\n"
                + "                      <table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">\n"
                + "                        <tr>\n"
                + "                          <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                + "                            <table class=\"row\" style=\"border-collapse: collapse; border-spacing: 0; width: 100%\">\n"
                + "                              <tr>\n"
                + "                                <td class=\"customer-info__item\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px; width: 50%\">\n"
                + "                                  <h4 style=\"color: #555; font-size: 16px; font-weight: 500; margin: 0 0 5px\">Window</h4>\n"
                + "      \n"
                + "                                 \n"
                + "                                </td>\n"
                + "                                <td class=\"customer-info__item\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px; width: 50%\">\n"
                + "                                  <h4 style=\"color: #555; font-size: 16px; font-weight: 500; margin: 0 0 5px\">Has window</h4> \n"
                + "      \n"
                + "                                </td>\n"
                + "                              </tr>\n"
                + "                            </table>\n"
                + "                            \n"
                + "                          </td>\n"
                + "                        </tr>\n"
                + "                      </table>\n"
                + "\n"
                + "\n"
                + "                      <hr style=\"border-top: dotted 1px;\" />\n"
                + "\n"
                + "                      <table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">\n"
                + "                        <tr>\n"
                + "                          <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                + "                            <table class=\"row\" style=\"border-collapse: collapse; border-spacing: 0; width: 100%\">\n"
                + "                              <tr>\n"
                + "                                <td class=\"customer-info__item\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px; width: 50%\">\n"
                + "                                  <h4 style=\"color: #555; font-size: 16px; font-weight: 500; margin: 0 0 5px\">Included Gifts</h4>\n"
                + "      \n"
                + "                                 \n"
                + "                                </td>\n"
                + "                                <td class=\"customer-info__item\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px; width: 50%\">\n"
                + "                                  <h4 style=\"color: #555; font-size: 16px; font-weight: 500; margin: 0 0 5px\">Late checkout until 13:00. (Jan 11, 2018 to Dec 31, 2019)</h4> \n"
                + "      \n"
                + "                                </td>\n"
                + "                              </tr>\n"
                + "                            </table>\n"
                + "                            \n"
                + "                          </td>\n"
                + "                        </tr>\n"
                + "                      </table> \n"
                + "                      \n"
                + "                  \n"
                + "                </center>\n"
                + "              </td>\n"
                + "            </tr>\n"
                + "          </table>\n"
                + "\n"
                + "        <table class=\"row section\" style=\"border-collapse: collapse; border-spacing: 0; border-top-color: #e5e5e5; border-top-style: solid; border-top-width: 1px; width: 100%\">\n"
                + "            <tr>\n"
                + "              <td class=\"section__cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding: 40px 0\">\n"
                + "                <center>\n"
                + "                  <table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">\n"
                + "                    <tr>\n"
                + "                      <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                + "                        <h3 style=\"font-size: 20px; font-weight: normal; margin: 0 0 25px\">Hotel Policies</h3>\n"
                + "  \n"
                + "                      </td>\n"
                + "                    </tr>\n"
                + "                  </table>\n"
                + "                  <table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">\n"
                + "                    <tr>\n"
                + "                      <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                + "                        <table class=\"row\" style=\"border-collapse: collapse; border-spacing: 0; width: 100%\">\n"
                + "                          <tr>\n"
                + "                            <td class=\"customer-info__item\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px; width: 50%\">\n"
                + "                              <h4 style=\"color: #555; font-size: 16px; font-weight: 500; margin: 0 0 5px\">Check-in and Checkout</h4>\n"
                + "  \n"
                + "                             \n"
                + "                            </td>\n"
                + "                            <td class=\"customer-info__item\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px; width: 50%\">\n"
                + "                              <h4 style=\"color: #555; font-size: 16px; font-weight: 500; margin: 0 0 5px\">Check-in : from 14:00 Check-out : before 12:00</h4>\n"
                + "  \n"
                + "                            </td>\n"
                + "                          </tr>\n"
                + "                        </table>\n"
                + "                        \n"
                + "                      </td>\n"
                + "                    </tr>\n"
                + "                  </table>\n"
                + "                  <hr style=\"border-top: dotted 1px;\" />\n"
                + "\n"
                + "                  <table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">\n"
                + "                    <tr>\n"
                + "                      <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                + "                        <table class=\"row\" style=\"border-collapse: collapse; border-spacing: 0; width: 100%\">\n"
                + "                          <tr>\n"
                + "                            <td class=\"customer-info__item\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px; width: 50%\">\n"
                + "                              <h4 style=\"color: #555; font-size: 16px; font-weight: 500; margin: 0 0 5px\">Children and Extra Beds</h4>\n"
                + "  \n"
                + "                             \n"
                + "                            </td>\n"
                + "                            <td class=\"customer-info__item\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px; width: 50%\">\n"
                + "                              <h4 style=\"color: #555; font-size: 16px; font-weight: 500; margin: 0 0 5px\">Children are welcome at this hotel.</h4>\n"
                + "  \n"
                + "                            </td>\n"
                + "                          </tr>\n"
                + "                        </table>\n"
                + "                        \n"
                + "                      </td>\n"
                + "                    </tr>\n"
                + "                  </table>\n"
                + "                  <hr style=\"border-top: dotted 1px;\" />\n"
                + "\n"
                + "                  <table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">\n"
                + "                    <tr>\n"
                + "                      <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                + "                        <table class=\"row\" style=\"border-collapse: collapse; border-spacing: 0; width: 100%\">\n"
                + "                          <tr>\n"
                + "                            <td class=\"customer-info__item\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px; width: 50%\">\n"
                + "                              <h4 style=\"color: #555; font-size: 16px; font-weight: 500; margin: 0 0 5px\">Pets</h4>\n"
                + "  \n"
                + "                             \n"
                + "                            </td>\n"
                + "                            <td class=\"customer-info__item\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding-bottom: 40px; width: 50%\">\n"
                + "                              <h4 style=\"color: #555; font-size: 16px; font-weight: 500; margin: 0 0 5px\">No pets allowed.</h4>\n"
                + "  \n"
                + "                            </td>\n"
                + "                          </tr>\n"
                + "                        </table>\n"
                + "                        \n"
                + "                      </td>\n"
                + "                    </tr>\n"
                + "                  </table>\n"
                + "                  \n"
                + "                </center>\n"
                + "              </td>\n"
                + "            </tr>\n"
                + "          </table>\n"
                + "        <table class=\"row footer\" style=\"border-collapse: collapse; border-spacing: 0; border-top-color: #e5e5e5; border-top-style: solid; border-top-width: 1px; width: 100%\">\n"
                + "          <tr>\n"
                + "            <td class=\"footer__cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding: 35px 0\">\n"
                + "              <center>\n"
                + "                <table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">\n"
                + "                  <tr>\n"
                + "                    <td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">\n"
                + "                      <p class=\"disclaimer__subtext\" style=\"color: #999; font-size: 14px; line-height: 150%; margin: 0\">If you have any questions, contact us at\n"
                + "                        <a href=\"#\" style=\"color: #080e66; font-size: 14px; text-decoration: none\">reservations@KRHG.com</a>\n"
                + "                      </p>\n"
                + "                    </td>\n"
                + "                  </tr>\n"
                + "                </table>\n"
                + "              </center>\n"
                + "            </td>\n"
                + "          </tr>\n"
                + "        </table>\n"
                + "      </td>\n"
                + "    </tr>\n"
                + "  </table>\n"
                + "</body>\n"
                + "\n"
                + "</html>\n"
                + "    \n"
                + "    \n"
                + "    \n"
                + "    \n"
                + "    \n"
                + "  </body>\n"
                + "</html>";
        sendEmail(emailAddressTB, "Confirm Booking", msg);

        return "index.xhtml?faces-redirect=true";
    }

    public static void sendEmail(String recipient, String subject, String msg) {

        String username = "automessage.kentridgehotelgroup@gmail.com";
        String password = "krhg1234";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("Do-not-reply@KentRidgeHotelGroup.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setContent(msg, "text/html");

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public String goConfirmation() {

        return "confirmation.xhtml?faces-redirect=true";
    }

    public String goPayment() {
        if (authBean.getLoggedInCustomer() != null) {
            fNameTB = authBean.getLoggedInCustomer().getFirstName();
            lNameTB = authBean.getLoggedInCustomer().getLastName();
            emailAddressTB = authBean.getLoggedInCustomer().getEmail();
            genderTB = authBean.getLoggedInCustomer().getGender();
        }

        return "payment.xhtml?faces-redirect=true";
    }

    public String getRoomPrice() throws NoResultException {
        return "$" + roomPricingSessionLocal.getRoomPricingByName(selectedHotel.getHotelCodeName() + "_" + roomTypeTB).getPrice() + "0";
        //    return "120.0";
    }

    public List<HotelFacility> getSelectedHotelFacility(Long hID) throws NoResultException {
        Hotel tempHotel = hotelSessionLocal.getHotelByID(hID);
        return tempHotel.getHotelFacilities();
    }

    public List<RoomFacility> getDisplayRoomFacilities(String type) throws NoResultException {
        return hotelSessionLocal.getRoomDisplayByRoomType(selectedHotel.getHotelID(), type).getRoomFacilities();
    }

    public List<String> getHotelRoomTypes() throws NoResultException {
        return hotelSessionLocal.getRoomTypes(selectedHotel.getHotelID());
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

    public List<RoomFacility> getAllRoomFacility() {
        return roomFacilitySessionLocal.getAllRoomFacilities();
    }

    public List<ExtraSurcharge> getAllSurcharge() {
        return roomSessionLocal.getAllExtraSurcharge();
    }

    public List<Feedback> getAllFeedbacks() {
        return feedbackSessionLocal.getAllFeedbacks();
    }

    public HotelSessionLocal getHotelSessionLocal() {
        return hotelSessionLocal;
    }

    public void setHotelSessionLocal(HotelSessionLocal hotelSessionLocal) {
        this.hotelSessionLocal = hotelSessionLocal;
    }

    public HotelFacilitySessionLocal getHotelFacilitySessionLocal() {
        return hotelFacilitySessionLocal;
    }

    public void setHotelFacilitySessionLocal(HotelFacilitySessionLocal hotelFacilitySessionLocal) {
        this.hotelFacilitySessionLocal = hotelFacilitySessionLocal;
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

    public Hotel getSelectedHotel() {
        return selectedHotel;
    }

    public void setSelectedHotel(Hotel selectedHotel) {
        this.selectedHotel = selectedHotel;
    }

    public String getCheckInTB() {
        return checkInTB;
    }

    public void setCheckInTB(String checkInTB) {
        this.checkInTB = checkInTB;
    }

    public String getCheckOutTB() {
        return checkOutTB;
    }

    public void setCheckOutTB(String checkOutTB) {
        this.checkOutTB = checkOutTB;
    }

    public int getGuestTB() {
        return guestTB;
    }

    public void setGuestTB(int guestTB) {
        this.guestTB = guestTB;
    }

    public String getRoomTypeTB() {
        return roomTypeTB;
    }

    public void setRoomTypeTB(String roomTypeTB) {
        this.roomTypeTB = roomTypeTB;
    }

    public BookingSessionLocal getBookingSessionLocal() {
        return bookingSessionLocal;
    }

    public void setBookingSessionLocal(BookingSessionLocal bookingSessionLocal) {
        this.bookingSessionLocal = bookingSessionLocal;
    }

    public List<Room> getRoomAvailable() {
        return roomAvailable;
    }

    public void setRoomAvailable(List<Room> roomAvailable) {
        this.roomAvailable = roomAvailable;
    }

    public boolean isHasRoom() {
        return hasRoom;
    }

    public void setHasRoom(boolean hasRoom) {
        this.hasRoom = hasRoom;
    }

    public int getNoOfRoomTB() {
        return noOfRoomTB;
    }

    public void setNoOfRoomTB(int noOfRoomTB) {
        this.noOfRoomTB = noOfRoomTB;
    }

    public String getGenderTB() {
        return genderTB;
    }

    public void setGenderTB(String genderTB) {
        this.genderTB = genderTB;
    }

    public String getfNameTB() {
        return fNameTB;
    }

    public void setfNameTB(String fNameTB) {
        this.fNameTB = fNameTB;
    }

    public String getlNameTB() {
        return lNameTB;
    }

    public void setlNameTB(String lNameTB) {
        this.lNameTB = lNameTB;
    }

    public String getEmailAddressTB() {
        return emailAddressTB;
    }

    public void setEmailAddressTB(String emailAddressTB) {
        this.emailAddressTB = emailAddressTB;
    }

    public String getContactNumberTB() {
        return contactNumberTB;
    }

    public void setContactNumberTB(String contactNumberTB) {
        this.contactNumberTB = contactNumberTB;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCreditCardTB() {
        return creditCardTB;
    }

    public void setCreditCardTB(String creditCardTB) {
        this.creditCardTB = creditCardTB;
    }

    public String getCvsTB() {
        return cvsTB;
    }

    public void setCvsTB(String cvsTB) {
        this.cvsTB = cvsTB;
    }

    public String getExpiryDateTB() {
        return expiryDateTB;
    }

    public void setExpiryDateTB(String expiryDateTB) {
        this.expiryDateTB = expiryDateTB;
    }

    public String getPickUpLocationTB() {
        return pickUpLocationTB;
    }

    public void setPickUpLocationTB(String pickUpLocationTB) {
        this.pickUpLocationTB = pickUpLocationTB;
    }

    public int getSeatsTB() {
        return seatsTB;
    }

    public void setSeatsTB(int seatsTB) {
        this.seatsTB = seatsTB;
    }

    public String getPickUpDateTB() {
        return pickUpDateTB;
    }

    public void setPickUpDateTB(String pickUpDateTB) {
        this.pickUpDateTB = pickUpDateTB;
    }

    public String getPickUpTimeTB() {
        return pickUpTimeTB;
    }

    public void setPickUpTimeTB(String pickUpTimeTB) {
        this.pickUpTimeTB = pickUpTimeTB;
    }

    public String getPassportNumberTB() {
        return passportNumberTB;
    }

    public void setPassportNumberTB(String passportNumberTB) {
        this.passportNumberTB = passportNumberTB;
    }

    public RoomPricingSessionLocal getRoomPricingSessionLocal() {
        return roomPricingSessionLocal;
    }

    public void setRoomPricingSessionLocal(RoomPricingSessionLocal roomPricingSessionLocal) {
        this.roomPricingSessionLocal = roomPricingSessionLocal;
    }

    public PaymentTransactionSessionLocal getPaymentTransactionSessionLocal() {
        return paymentTransactionSessionLocal;
    }

    public void setPaymentTransactionSessionLocal(PaymentTransactionSessionLocal paymentTransactionSessionLocal) {
        this.paymentTransactionSessionLocal = paymentTransactionSessionLocal;
    }

}
