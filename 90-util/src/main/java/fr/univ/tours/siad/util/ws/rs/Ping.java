package fr.univ.tours.siad.util.ws.rs;

import fr.univ.tours.siad.util.log.Traceable;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;

/**
 * Created by francois on 31/08/15.
 */
public interface Ping {

    @GET
    @Path("ping")
    @Traceable
    default Response Ping(@Suspended AsyncResponse asyncResponse) {
        return Response.noContent().build();
    }

}
