package fr.univ.tours.siad.service;

import fr.univ.tours.siad.util.data.SiadDatabase;
import fr.univ.tours.siad.util.data.bean.City;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 * Created by francois on 08/10/15.
 */
@Stateless
public class CityService {

    @Inject @SiadDatabase
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    public City retrieveCityByInseeId(String cityInseeId) {
        try {
            return entityManager.createNamedQuery(City.FIND_BY_INSEE, City.class).setParameter(City.INSEEID, cityInseeId).getSingleResult();
        } catch (NoResultException e) {
            logger.error("Impossible de trouver la ville avec le n° INSEE : " + cityInseeId);
            return null;
        }
    }
}
