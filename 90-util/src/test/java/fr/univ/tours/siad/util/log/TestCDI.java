package fr.univ.tours.siad.util.log;

import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Created by francois on 28/08/15.
 */
@Traceable
public class TestCDI {

    @Inject
    private Logger logger;

    public String sayHello() {
        logger.debug("Je dis bonjour");
        return "hello";
    }
}
