/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import entity.Staff;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Congx2
 */
@FacesConverter(value = "converter.StaffConverter")
public class StaffConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty() || value.equals("null")) {
            return null;
        }
        List<Staff> staffs = (List<Staff>) context.getExternalContext().getSessionMap().get("converter.StaffConverterv.staffs");
        for (Staff staff : staffs) {
            if (staff.getStaffID().toString().equals(value)) {
                return staff;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }

        if (value instanceof String) {
            return "";
        }
        if (value instanceof Staff) {

            Staff staff = (Staff) value;
            if (staff.getStaffID() == null) {
                return "";
            }
            return staff.getStaffID().toString();
        }
        return null;
    }

}
