package fr.univ.tours.siad.service;

import fr.univ.tours.siad.util.data.SiadDatabase;
import fr.univ.tours.siad.util.data.bean.District;
import fr.univ.tours.siad.util.data.bean.Region;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by francois on 08/10/15.
 */
@Stateless
public class DistrictService {

    @Inject @SiadDatabase
    private EntityManager entityManager;

    public List<District> findFor(Region region) {
        return entityManager.createNamedQuery(District.FIND_BY_REGION, District.class).setParameter(Region.INSEEID, region.getInseeId()).getResultList();
    }

    public District getById(Long districtId) {
        return entityManager.find(District.class, districtId);
    }
}
