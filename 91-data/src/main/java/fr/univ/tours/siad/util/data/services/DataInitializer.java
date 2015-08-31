package fr.univ.tours.siad.util.data.services;

import fr.univ.tours.siad.util.log.Traceable;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * Created by francois on 31/08/15.
 */

@Startup
@Singleton
public class DataInitializer {

    @Inject
    private Logger logger;

    @PostConstruct @Traceable
    public void initializeDate() {
        logger.debug("----------------- Data initialisation -----------------");
    }
}
