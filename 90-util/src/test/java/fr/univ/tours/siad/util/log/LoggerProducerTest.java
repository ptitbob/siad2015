package fr.univ.tours.siad.util.log;

import org.apache.logging.log4j.Logger;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

/**
 * Created by francois on 28/08/15.
 */
@RunWith(Arquillian.class)
public class LoggerProducerTest {

    @Deployment
    public static WebArchive createJavaTestArchive() {

        return ShrinkWrap.create(WebArchive.class)
                .addPackages(true, "fr.univ.tours.siad.util")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml")
                ;
    }

    @Inject
    private Logger logger;

    @Inject
    private TestCDI testCDI;

    @Test
    public void lognerIntanciate() {
        logger.entry();
        Assert.assertNotNull("Le logger doit etre instancie", logger);
        logger.debug("Logger instancié");
        logger.info("dis bonjour : " + testCDI.sayHello());
        logger.exit();
    }
}