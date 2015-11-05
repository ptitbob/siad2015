package fr.univ.tours.siad.ws.rs.mapper;

import fr.univ.tours.siad.services.exception.NoRegionFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Une région non trouvé renvoi une erreur HTTP erreur 450 :)
 * SIAD - JavaEE et WS
 *
 * @author francois
 */
@Provider
public class NoRegionFoundExceptionMapper implements ExceptionMapper<NoRegionFoundException> {
    @Override
    public Response toResponse(NoRegionFoundException exception) {
        return Response.status(450).build();
    }
}
