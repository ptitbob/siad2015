package fr.univ.tours.siad.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created by francois on 30/09/15.
 */
@FacesConverter("uppercase") /* Le convertisseur est nommé uppercase */
public class UppercaseConverter implements Converter {

    /**
     * Traduit la valeur venant de la page.
     * @param context context Faces
     * @param component composant
     * @param value valeur envoyées
     * @return objet converti à partir de la valeur en chaine
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return null;
    }

    /**
     * Envoi la valeur en chaine de l'objet
     * @param context context Faces
     * @param component composant
     * @param value objet
     * @return
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((String) value).toUpperCase();
    }

}
