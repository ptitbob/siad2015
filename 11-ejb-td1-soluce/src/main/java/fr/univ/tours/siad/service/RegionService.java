package fr.univ.tours.siad.service;

import fr.univ.tours.siad.util.data.SiadDatabase;
import fr.univ.tours.siad.util.data.bean.Region;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by francois on 08/10/15.
 */
@Stateless
public class RegionService {

    @Inject @SiadDatabase
    private EntityManager entityManager;

    public List<Region> findAll() {
        return entityManager.createNamedQuery(Region.FIND_ALL, Region.class).getResultList();
    }

    public Region getById(Long regionId) {
        return entityManager.find(Region.class, regionId);
    }
}
