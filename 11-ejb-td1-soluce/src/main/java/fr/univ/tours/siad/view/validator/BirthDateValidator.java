package fr.univ.tours.siad.view.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Created by francois on 30/09/15.
 */
@FacesValidator("centurychild")
public class BirthDateValidator implements Validator {

    public static final LocalDate MINUS_DATE = LocalDate.of(1950, Month.JANUARY, 1);

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value != null) {
            if (value instanceof Date) { // programmation defensive
                LocalDate date = ((Date) value).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate legalAgeDate = LocalDate.now().minus(18, ChronoUnit.YEARS);
                if (date.isBefore(MINUS_DATE) || date.isAfter(legalAgeDate)) {
                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "La date ne respecte pas les règes", "La date doit être supérieur à 1950 et la personne doit être majeure"));
                }
            } else {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "La validation de l'age doit se faire sur une date", "details"));
            }
        }
    }
}
