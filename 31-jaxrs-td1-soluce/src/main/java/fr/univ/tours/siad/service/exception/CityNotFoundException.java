package fr.univ.tours.siad.service.exception;

/**
 * SIAD - JavaEE et WS
 *
 * @author francois
 */
public class CityNotFoundException extends Exception {
    public CityNotFoundException(String cityInseeId) {
        super(String.format("La ville INSEE %s n'existe pas", cityInseeId));
    }
}
