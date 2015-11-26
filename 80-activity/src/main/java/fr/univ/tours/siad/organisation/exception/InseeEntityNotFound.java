package fr.univ.tours.siad.organisation.exception;

/**
 * SIAD - JavaEE et WS
 *
 * @author francois
 */
public class InseeEntityNotFound extends Exception {
    public InseeEntityNotFound(Exception e, String message) {
        super(message, e);
    }
}
