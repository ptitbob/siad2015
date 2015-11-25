package fr.univ.tours.siad.organisation.services;

import fr.univ.tours.siad.organisation.exception.PersonNotFoundException;
import fr.univ.tours.siad.organisation.model.Address;
import fr.univ.tours.siad.organisation.model.City;
import fr.univ.tours.siad.organisation.model.Person;
import fr.univ.tours.siad.organisation.services.util.SiadDatabase;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * SIAD - JavaEE et WS
 *
 * @author francois
 */
@Stateless
public class PersonService {

    @Inject
    @SiadDatabase
    private EntityManager entityManager;

    /**
     * Renvoi la liste de toutes les personnes
     * @return liste
     */
    public List<Person> findAll() {
        return entityManager.createNamedQuery(Person.FIND_ALL, Person.class).getResultList();
    }

    /**
     * Renvoi la liste de toutes les personne d'une ville
     * @param cityInseeId numero INSEE de la vile
     * @return liste
     */
    public List<Person> findByCity(String cityInseeId) {
        return entityManager.createNamedQuery(Person.FIND_BY_CITY, Person.class)
                .setParameter(City.INSEEID, cityInseeId)
                .getResultList();
    }

    /**
     * Crée une personne en base
     * @param person personne
     * @return personne persisté
     */
    public Person create(Person person) {
        if (person.getAddress() != null && person.getAddress().getId() == null) {
            entityManager.persist(person.getAddress());
        }
        entityManager.persist(person);
        return person;
    }

    /**
     * Renvoi une personne selon son identifiant
     * @param personId identifiant de la personne
     * @return personne
     */
    public Person get(Long personId) {
        return entityManager.find(Person.class, personId);
    }

    /**
     * Mise à jour des données de la personne
     * @param person personne
     * @throws PersonNotFoundException si la personne n'a pas été retrouvé
     */
    public void update(Person person) throws PersonNotFoundException {
        // on retrouve la personne passé en paramètre dans la base
        Person existingPerson = get(person.getId());
        if (existingPerson == null) {
            // si la personne n'est pas trouvé, une exception est levé
            throw new PersonNotFoundException("La personne avec l'Id " + person.getId() + " n'est pas presente en base");
        }
        existingPerson.setBirth(person.getBirth());
        existingPerson.setFirstname(person.getFirstname());
        existingPerson.setSurname(person.getSurname());
        entityManager.merge(existingPerson);
    }

    /**
     * Suppression de la personne
     * @param personId id de la personne
     */
    public void delete(Long personId) {
        Person person = get(personId);
        if (person != null) {
            entityManager.remove(person);
        }
    }

}
