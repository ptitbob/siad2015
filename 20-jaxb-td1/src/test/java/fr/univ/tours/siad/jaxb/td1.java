package fr.univ.tours.siad.jaxb;

import fr.univ.tours.siad.jaxb.model.person.Address;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.logging.Logger;

public class td1 {

    private static final Logger LOGGER = Logger.getLogger("td1");

    @BeforeClass
    public static final void initialisationClass() {

    }

    @Test
    public void testAddress() {
        LOGGER.info("TD 1.1 --> Test de l'adresse");
        Address address = new Address(1L, "Tours", "37000", "ligne1", "Ligne2");
    }
}
