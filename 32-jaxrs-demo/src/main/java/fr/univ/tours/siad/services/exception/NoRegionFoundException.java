package fr.univ.tours.siad.services.exception;

/**
 * SIAD - JavaEE et WS
 *
 * @author francois
 */
public class NoRegionFoundException extends Exception {
    public NoRegionFoundException(String regionInseeId) {
        super(String.format("La region %s n'a pu être localisé", regionInseeId));
    }
}
