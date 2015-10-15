package fr.univ.tours.siad.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created by francois on 08/10/15.
 */
@FacesConverter("districtInsee")
public class DistrictNumberConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value.length() < 2 ? ("0" + value) : value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(Integer.valueOf((String) value)); // pour supprimer les leading zero :)
    }
}
