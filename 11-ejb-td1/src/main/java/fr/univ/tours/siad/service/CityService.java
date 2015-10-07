package fr.univ.tours.siad.service;

import fr.univ.tours.siad.util.data.SiadDatabase;
import fr.univ.tours.siad.util.data.bean.City;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created by francois on 08/10/15.
 */
@Stateless
public class CityService {

    @Inject @SiadDatabase
    private EntityManager entityManager;

    public City retrieveCityByInseeId(String chefLieuId) {
        return null;
    }
}
