package fr.univ.tours.siad.util.data;

import fr.univ.tours.siad.util.log.Traceable;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by francois on 31/08/15.
 */

@Startup
@Singleton
public class DataInitializer {

    @Inject @SiadDatabase
    private EntityManager entityManager;

    @Inject
    private RegionServices regionServices;

    @Inject
    private Logger logger;

    @PostConstruct @Traceable
    public void initializeData() {
        logger.debug("----------------- Data initialisation -----------------");
        initializeRegion();
    }

    private void initializeRegion() {
        if (regionServices.getCount() == 0) {
            logger.info("Initialisation des donnée des région");
            File file = new File(getClass().getClassLoader().getResource("reg2015.txt").getFile());
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    logger.info(scanner.nextLine());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}
