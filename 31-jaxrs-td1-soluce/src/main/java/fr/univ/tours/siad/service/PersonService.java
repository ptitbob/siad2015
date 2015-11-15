package fr.univ.tours.siad.service;

import fr.univ.tours.siad.util.data.SiadDatabase;
import fr.univ.tours.siad.util.data.bean.Address;
import fr.univ.tours.siad.util.data.bean.Person;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.List;

/**
 * Service CRUD-List pour les personnes
 * Et Autres services :)
 */
@Stateless
public class PersonService {

    @Inject
    @SiadDatabase
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    /**
     * Renvoi la liste de tous les personnes presente en base
     *
     * @return liste de personne
     */
    public List<Person> getPersonList() {
        return entityManager.createNamedQuery(Person.FIND_ALL, Person.class)
                .getResultList();
    }

    /**
     * Création d'une personne (en base)
     *
     * @param person personne a persister
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void create(Person person) {
        logger.debug("Création de la persone : " + person);
        if (person.getAddress() != null && person.getAddress().getId() == null) {
            create(person.getAddress());
        }
        entityManager.persist(person);
        person.setReference(StringUtils.leftPad(String.valueOf(person.getId()), 7, "0"));
        update(person);
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void create(Address address) {
        entityManager.persist(address);
    }

    /**
     * Renvoi une personne selon son Identifiant
     *
     * @param personId Identifiant de la personne
     * @return Personne initialisé
     */
    public Person getById(Long personId) {
        return entityManager.find(Person.class, personId);
    }

    /**
     * Met à jour les données de la personne (en base)
     *
     * @param person personne
     */
    public void update(Person person) {
        logger.debug("Mise à jour de la personne : " + person);
        entityManager.merge(person);
    }

    /**
     * Supprime une personne de la base
     *
     * @param personId identifiant de la personne a supprimer
     */
    public void delete(Long personId) {
        Person person = getById(personId);
        logger.debug("Suppresion de la personne " + person);
        if (person.getAddress() != null && person.getAddress().getId() != null) {
            entityManager.remove(person.getAddress());
        }
        entityManager.remove(person);
    }

    /**
     * Renvoi une personne selon sa reference
     * @param personneReference reference de la personne
     * @return Person ou Null
     */
    public Person findByReference(String personneReference) {
        try {
            return entityManager.createNamedQuery(Person.FIND_BY_REFERENCE, Person.class)
                    .setParameter(Person.REFERENCE, personneReference)
                    .getSingleResult();
        } catch (NonUniqueResultException | NoResultException e) {
            logger.error("Resultat non tangible pour la reference " + personneReference, e);
            return null;
        }
    }

}
