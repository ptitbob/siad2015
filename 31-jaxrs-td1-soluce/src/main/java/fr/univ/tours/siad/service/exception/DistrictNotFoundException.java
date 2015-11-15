package fr.univ.tours.siad.service.exception;

/**
 * SIAD - JavaEE et WS
 *
 * @author francois
 */
public class DistrictNotFoundException extends Exception {
    public DistrictNotFoundException(String districtInseeId) {
        super(String.format("Le departement avec le numero INSEE %s n'existe pas", districtInseeId));
    }
}
