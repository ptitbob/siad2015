package fr.univ.tours.siad.organisation.services;

import fr.univ.tours.siad.organisation.exception.InseeEntityNotFound;
import fr.univ.tours.siad.organisation.model.City;
import fr.univ.tours.siad.organisation.model.ZipCode;
import fr.univ.tours.siad.organisation.services.util.SiadDatabase;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 * SIAD - JavaEE et WS
 *
 * @author francois
 */
@Stateless
public class InseeService {

    @Inject
    @SiadDatabase
    private EntityManager entityManager;

    @Inject
    private Logger logger;


    /**
     * renvoi la ville en fonction d'un code postal
     *
     * @param zipcode code postal
     * @return ville
     * @throws InseeEntityNotFound si le code postal n'a pas été trouvé ou si le code postal n'est pas unique.
     */
    public City findCityByZipcode(String zipcode) throws InseeEntityNotFound {
        try {
            ZipCode zipCodeBean = entityManager
                    .createNamedQuery(ZipCode.FIND_BY_ZIPCODE, ZipCode.class)
                    .setParameter(ZipCode.ZIPCODE, zipcode)
                    .getSingleResult();
            return zipCodeBean.getCity();
        } catch (NoResultException | NonUniqueResultException e) {
            logger.error("Code postal non trouvé", e);
            throw new InseeEntityNotFound(e, "Codepostal non trouvé pour " + zipcode);
        }
    }

}
