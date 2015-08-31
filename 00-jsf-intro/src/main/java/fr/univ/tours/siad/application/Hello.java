package fr.univ.tours.siad.application;

import fr.univ.tours.siad.util.log.Traceable;
import fr.univ.tours.siad.util.ws.rs.Ping;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by francois on 31/08/15.
 */
@Path("test")
public class Hello implements Ping {

    @Inject
    Logger logger;
    private ExecutorService executorService = Executors.newCachedThreadPool();

    @GET @Traceable
    public void hello(@Suspended
    final AsyncResponse asyncResponse) {
        executorService.submit(new Runnable() {
            public void run() {
                asyncResponse.resume(doHello());
            }
        });
    }

    @Traceable
    public String doHello() {
        logger.debug("on dit bonjour !!");
        return "hello world !! YO !";
    }
}
