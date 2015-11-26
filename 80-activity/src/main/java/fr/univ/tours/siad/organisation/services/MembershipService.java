package fr.univ.tours.siad.organisation.services;

import fr.univ.tours.siad.organisation.services.util.SiadDatabase;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * SIAD - JavaEE et WS
 *
 * @author francois
 */
@Stateless
public class MembershipService {

    @Inject
    @SiadDatabase
    private EntityManager entityManager;

}
