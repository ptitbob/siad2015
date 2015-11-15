package fr.univ.tours.siad.service;

import fr.univ.tours.siad.service.exception.CityNotFoundException;
import fr.univ.tours.siad.service.exception.DistrictNotFoundException;
import fr.univ.tours.siad.util.data.SiadDatabase;
import fr.univ.tours.siad.util.data.bean.City;
import fr.univ.tours.siad.util.data.bean.District;
import fr.univ.tours.siad.util.data.bean.Region;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.List;

/**
 * SIAD - JavaEE et WS
 *
 * @author francois
 */
@Stateless
public class AdministrativeEntityService {

    @Inject
    private Logger logger;

    @Inject @SiadDatabase
    private EntityManager entityManager;

    public AdministrativeEntityService() {
    }

    public List<District> getDistrictList() {
        return entityManager.createNamedQuery(District.FIND_ALL, District.class).getResultList();
    }

    public District getDistrict(String districtInseeId) throws DistrictNotFoundException {
        try {
        return entityManager.createNamedQuery(District.FIND_BY_INSEEID, District.class).setParameter(District.INSEEID, districtInseeId).getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            throw new DistrictNotFoundException(districtInseeId);
        }
    }

    public List<District> getDistrictList(String regionInseeId) {
        return entityManager.createNamedQuery(District.FIND_BY_REGION, District.class).setParameter(Region.INSEEID, regionInseeId).getResultList();
    }

    public City getCity(String cityInseeId) throws CityNotFoundException {
        try {
        return entityManager.createNamedQuery(City.FIND_BY_INSEE, City.class).setParameter(City.INSEEID, cityInseeId).getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            throw new CityNotFoundException(cityInseeId);
        }
    }
}
