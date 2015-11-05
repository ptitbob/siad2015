package fr.univ.tours.siad.ws.rs.exception;

/**
 * Exception si les n° INSEE ne correspondent pas.
 *
 * SIAD - JavaEE et WS
 *
 * @author francois
 */
public class IllegalInseeIdPresentationException extends Throwable {
    public IllegalInseeIdPresentationException(String expectedInseeId, String inseeId) {
        super(String.format("Les numéro INSEE ne correspondent pas, attendu : %s, fourni: %s", expectedInseeId, inseeId));
    }
}
