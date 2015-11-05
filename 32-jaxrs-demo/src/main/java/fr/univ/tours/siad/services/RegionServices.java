package fr.univ.tours.siad.services;

import fr.univ.tours.siad.services.exception.NoRegionFoundException;
import fr.univ.tours.siad.util.data.SiadDatabase;
import fr.univ.tours.siad.util.data.bean.District;
import fr.univ.tours.siad.util.data.bean.Region;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
public class RegionServices {

    @Inject
    @SiadDatabase
    private EntityManager entityManager;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Region> getRegionList() {
        return entityManager.createNamedQuery(Region.FIND_ALL, Region.class).getResultList();
    }

    /**
     * Renvoi la region en fonction de son numero INSEE
     * @param regionInseeId numero INSEE
     * @return region
     * @throws NoRegionFoundException si la region n'a pas été trouvé
     */
    public Region getRegionByInseeId(String regionInseeId) throws NoRegionFoundException {
        Region region;
        try {
            region = entityManager.createNamedQuery(Region.FIND_BY_INSEEID, Region.class).setParameter(Region.INSEEID, regionInseeId).getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            throw new NoRegionFoundException(regionInseeId);
        }
        return region;
    }

    /**
     * Renvoi la compte des départements
     * @param regionInseeId numero INSEE de la region
     * @return compte
     */
    public Long getDistrictCountFor(String regionInseeId) {
        Long districtCount = entityManager.createNamedQuery(District.COUNT_FOR_REGION, Long.class).setParameter(Region.INSEEID, regionInseeId).getSingleResult();
        return districtCount;
    }

    /**
     * Création d'une région
     * @param name nom
     * @param upperName nom normalisé
     * @return region créé
     */
    public Region createRegion(String name, String upperName) {
        Region region = new Region();
        region.setName(name);
        region.setUpperName(upperName);
        entityManager.persist(region);
        region.setInseeId(String.valueOf(500 + region.getId()));
        entityManager.merge(region);
        return region;
    }

    public void updateRegion(Region region) {
        entityManager.merge(region);
    }

    public void removeRegionById(String inseeId) throws NoRegionFoundException {
        Region region = getRegionByInseeId(inseeId);
        entityManager.remove(region);
    }
}
