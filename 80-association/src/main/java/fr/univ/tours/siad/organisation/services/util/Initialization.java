package fr.univ.tours.siad.organisation.services.util;

import fr.univ.tours.siad.organisation.model.Activity;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created by francois on 19/10/15.
 */
@Startup
@Singleton
public class Initialization {

    @Inject
    private Logger logger;

    @Inject @SiadDatabase
    private EntityManager entityManager;

    @PostConstruct
    public void initializeApplication() {
        logger.info("Initilisation de l'application");
        Activity activity = new Activity();
        activity.setName("test");
        entityManager.persist(activity);
    }

}
