package fr.univ.tours.siad.ws.rs.mapper;

import fr.univ.tours.siad.ws.rs.exception.IllegalInseeIdPresentationException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Si le client envoi une URL un n° INSEE et des données n'ayant pas le meme n° INSEE, une erreur HTTP 451
 *
 * SIAD - JavaEE et WS
 *
 * @author francois
 */
@Provider
public class IllegalInseeIdPresentationExceptionMapper implements ExceptionMapper<IllegalInseeIdPresentationException> {
    @Override
    public Response toResponse(IllegalInseeIdPresentationException exception) {
        return Response.status(451).build();
    }
}
